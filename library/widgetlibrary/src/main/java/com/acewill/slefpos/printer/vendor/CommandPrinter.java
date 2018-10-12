package com.acewill.slefpos.printer.vendor;

import android.graphics.Bitmap;

import com.acewill.slefpos.printer.Alignment;
import com.acewill.slefpos.printer.Barcode;
import com.acewill.slefpos.printer.BarcodePosition;
import com.acewill.slefpos.printer.BitmapRow;
import com.acewill.slefpos.printer.Column;
import com.acewill.slefpos.printer.PrinterCommand;
import com.acewill.slefpos.printer.PrinterInterface;
import com.acewill.slefpos.printer.Row;
import com.acewill.slefpos.printer.Separator;
import com.acewill.slefpos.printer.Table;
import com.acewill.slefpos.printer.TableWidthCalculator;
import com.acewill.slefpos.printer.TextRow;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 通过指令值打印内容 -- 网络打印机和蓝牙打印机都是这种模式
 * Created by Acewill on 2016/8/19.
 */
public class CommandPrinter implements PrinterInterface {
    protected  OutputStream outputStream;
    protected int maxCharacterSizePerSize; //每行最多几个英文字符？ 根据打印点数 和 字符的大小计算出来的结果在不同打印机上不一样

    public CommandPrinter(int maxCharacterSizePerSize) {
        this.maxCharacterSizePerSize = maxCharacterSizePerSize;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void init() throws IOException {

    }

    @Override
    public void printRow(Row row) throws IOException {
        if (row instanceof Separator) {
            printSeperator(((Separator)row).getContent());
            return;
        } else if (row instanceof Barcode) {
            printBarcode((Barcode)row);
        } else if (row instanceof TextRow) {
            printTextRow((TextRow) row);
        }else if (row instanceof BitmapRow) {
            printBmp((BitmapRow) row);
        }
        startPrint();
        reset();
    }

    private void printTextRow(TextRow row) throws IOException {
        outputStream.write(PrinterCommand.ROW_HEIGHT(row.getHeight()));
        outputStream.write(PrinterCommand.FONT_SCALE(row.getScaleWidth(),row.getScaleHeight()));
        outputStream.write(PrinterCommand.FONT_SCALE_ZS(row.getScaleWidth(),row.getScaleHeight()));
        outputStream.write(PrinterCommand.align(row.getAlign()));
        outputStream.write(PrinterCommand.FONT_BOLD(row.isBoldFont()));
        outputStream.write(PrinterCommand.REVERT_MODE(row.isRevertMode()));
        outputStream.write(PrinterCommand.SHOW_UNDER_LINE(row.isShowUnderline()));

        for (Column section : row.getColumnList()) {
            outputStream.write((section.getContent()).getBytes("GB2312")); //GBK也可以，UTF-8不行
        }

        outputStream.write("\n".getBytes());
    }
  //打印完一行，需要把字体大小, 对齐方式等恢复默认值
    private void reset() throws IOException{
    	outputStream.write(PrinterCommand.FONT_SCALE(1,1));
        outputStream.write(PrinterCommand.FONT_SCALE_ZS(1,1));
        outputStream.write(PrinterCommand.align(Alignment.CENTER));
        outputStream.write(PrinterCommand.FONT_BOLD(false));
        outputStream.write(PrinterCommand.REVERT_MODE(false));
        outputStream.write(PrinterCommand.SHOW_UNDER_LINE(false));
    }


    private void printSeperator(String seperator) throws IOException {
        //默认字体是REGULAR_ASCII
        for (int i = 0; i < this.maxCharacterSizePerSize; ++i) {
            outputStream.write(seperator.getBytes());
        }

        outputStream.write("\n".getBytes());
    }

    @Override
    public void printTable(Table table) throws IOException {
        TableWidthCalculator tc = new TableWidthCalculator(table, this.maxCharacterSizePerSize);
        tc.updateTableWidth();

        if (table.getTitle() != null) {
            printRow(table.getTitle());
        }


        for (Row row : table.getRows()) {
            printRow(row);
        }
        startPrint();
    }

    private void startPrint() throws IOException {
    	outputStream.write(PrinterCommand.PRINTLN);
	}

	//必须调用close，否则不会真正打印出来
    @Override
    public void close() throws IOException {
//    	outputStream.write(PrinterCommand.END_PRINT);
        outputStream.close();
    }

    int ToGrey(int rgb)
    {
        int blue = (rgb & 0x000000FF)>>0;
        int green = (rgb & 0x0000FF00) >> 8;
        int red = (rgb & 0x00FF0000) >> 16;
        return ( red*38 +  green * 75 +  blue * 15 )>>7;
    }



    //打印位图 -- 可用于打印二维码，或者logo, 实现直接从这里抄的：http://blog.csdn.net/laner0515/article/details/8457337
    private void printBmp(BitmapRow bitmapRow) throws IOException {
        Bitmap bitmap = bitmapRow.getBitmap();
        byte[] data = new byte[] { 0x1B, 0x33, 0x00 };
        outputStream.write(data);

        data[0] = 0;
        data[1] = 0;
        data[2] = 0;

        int pixelColor;

        // ESC * m nL nH 点阵图
        byte[] escBmp = new byte[] { 0x1B, 0x2A, 0x00, 0x00, 0x00 };
        escBmp[2] = 0x21;

        //nL, nH
        escBmp[3] = (byte)(bitmap.getWidth() % 256);
        escBmp[4] = (byte)(bitmap.getWidth() / 256);

        //data
        for (int i = 0; i < (bitmap.getHeight() / 24) + 1; i++)
        {
            outputStream.write(escBmp); //设置该行为位图模式

            for (int j = 0; j < bitmap.getWidth(); j++)
            {
                for (int k = 0; k < 24; k++)
                {
                    if (((i * 24) + k) < bitmap.getHeight())   // if within the BMP size
                    {
                        pixelColor = bitmap.getPixel(j, (i * 24) + k);
                        pixelColor = ToGrey(pixelColor);
                        int r = pixelColor & 0xFF;


                        if (r == 0)
                        {
                            data[k / 8] += (byte)(128 >> (k % 8));
                        }
                    }
                }

                outputStream.write(data); //位图数据
                data[0] = 0;
                data[1] = 0;
                data[2] = 0;    // Clear to Zero.
            }

            outputStream.write("\n".getBytes());

        }

        outputStream.write("\n".getBytes());
    }


    private void printBarcode(Barcode barcode) throws IOException {
        outputStream.write(PrinterCommand.BAR_CODE(240, BarcodePosition.BOTTOM));
        outputStream.write(barcode.getBarcode().getBytes());
        outputStream.write(0x00); //表明条形码指令结束
    }
    
	@Override
	public void cut() throws IOException {
		outputStream.write(PrinterCommand.END_PRINT);
	}
}
