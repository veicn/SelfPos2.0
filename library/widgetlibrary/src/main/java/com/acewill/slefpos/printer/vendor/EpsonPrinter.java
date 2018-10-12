package com.acewill.slefpos.printer.vendor;

import com.acewill.slefpos.printer.BarcodePosition;
import com.acewill.slefpos.printer.PrinterCommand;
import com.acewill.slefpos.printer.PrinterWidth;

import java.io.IOException;


/**
 * 爱普生网络打印机
 * Created by linmingren on 16/8/3.
 */
public class EpsonPrinter extends WifiPrinter {

    public EpsonPrinter(String host, PrinterWidth width) {
        super(host,42);
        if(width== PrinterWidth.WIDTH_56MM){
            maxCharacterSizePerSize = 32;
        }else if(width==PrinterWidth.WIDTH_76MM){
            maxCharacterSizePerSize = 37;
        }
    }

    /**
     * 只支持长度12的二维码
     * @param barcode
     * @throws IOException
     */
    public void printBarcode(String barcode) throws IOException {
       /* if (barcode.getBytes().length != 12) {
            //只支持长度在[11,12]的字符串
            return;
        }*/
        outputStream.write(PrinterCommand.EPSON_BAR_CODE2(120, 4, BarcodePosition.BOTTOM));
        outputStream.write(barcode.getBytes());
        outputStream.write(0x00); //表明条形码指令结束
    }
}
