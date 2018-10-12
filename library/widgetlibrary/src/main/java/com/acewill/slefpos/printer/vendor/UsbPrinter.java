//package com.acewill.slefpos.printer.vendor;
//
//import android.content.Context;
//import android.hardware.usb.UsbDevice;
//import android.hardware.usb.UsbManager;
//import android.os.Handler;
//import android.os.Message;
//
//import com.acewill.slefpos.printer.PrinterInterface;
//import com.acewill.slefpos.printer.Row;
//import com.acewill.slefpos.printer.Table;
//import com.printer.sdk.PrinterConstants;
//import com.printer.sdk.PrinterInstance;
//import com.printer.sdk.usb.USBPort;
//
//import java.io.IOException;
//import java.util.HashMap;
//
//
///**
// * 容大安卓打印机
// * Created by Acewill on 2016/8/18.
// */
//public class UsbPrinter implements PrinterInterface {
//    private PrinterHandler  handler;
//    private Handler         internalHandler;
//    private Context         context;
//    private UsbDevice       usbDevice;
//    private PrinterInstance printerInstance;
//
//    public UsbPrinter(Context context, UsbDevice usbDevice, PrinterHandler handler) {
//        this.context = context;
//        this.usbDevice = usbDevice;
//        this.internalHandler = new RdPrinterHandler();
//        this.handler = handler;
//    }
//
//    //查找打印机连接了哪个USB设备
//    public static UsbDevice findUsbPrinter(UsbManager usbManager) {
//        if (usbManager == null) {
//            return null;
//        }
//
//        HashMap<String, UsbDevice> devices = usbManager.getDeviceList();
//
//        for (UsbDevice device : devices.values()) {
//            if (USBPort.isUsbPrinter(device)) {
//                return device;
//            }
//        }
//
//        return null;
//    }
//
//    @Override
//    public void init() throws IOException {
//        printerInstance = PrinterInstance.getPrinterInstance(context, usbDevice, internalHandler);
//    }
//
//    @Override
//    public void close() throws IOException {
//        if (printerInstance != null) {
//            printerInstance.closeConnection();
//            printerInstance = null;
//        }
//    }
//
//    @Override
//    public void printRow(Row row) throws IOException {
//       /* outputStream.write(PrinterCommand.ROW_HEIGHT(row.getHeight()));
//        outputStream.write(PrinterCommand.FONT_SCALE(row.getScaleWidth(),row.getScaleHeight()));
//        outputStream.write(PrinterCommand.align(row.getAlign()));
//        outputStream.write(PrinterCommand.FONT_BOLD(row.isBoldFont()));
//        outputStream.write(PrinterCommand.REVERT_MODE(row.isRevertMode()));
//        outputStream.write(PrinterCommand.SHOW_UNDER_LINE(row.isShowUnderline()));
//
//        for (Column section : row.getColumnList()) {
//            outputStream.write((section.getContent()).getBytes("GB2312")); //GBK也可以，UTF-8不行
//        }
//
//        outputStream.write("\n".getBytes());
//
//        //打印完一行，需要把字体大小, 对齐方式等恢复默认值
//        outputStream.write(PrinterCommand.FONT_SCALE(1,1));
//        outputStream.write(PrinterCommand.align(Alignment.CENTER));
//        outputStream.write(PrinterCommand.FONT_BOLD(false));
//        outputStream.write(PrinterCommand.REVERT_MODE(false));
//        outputStream.write(PrinterCommand.SHOW_UNDER_LINE(false));*/
//        printerInstance.printText("test\n");
//        printerInstance.printText("test\n");
//        printerInstance.printText("test\n");
//        printerInstance.printText("test\n");
//        printerInstance.printText("test\n");
//    }
//
//
//
//    @Override
//    public void printTable(Table table) throws IOException {
//
//    }
//
//
//    private class RdPrinterHandler extends Handler {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case PrinterConstants.Connect.SUCCESS:
//                    //连接成功
//                    handler.handleMessage(new PrinterMessage(4, "连接成功"));
//                    break;
//                case PrinterConstants.Connect.FAILED:
//                    //连接失败
//                    handler.handleMessage(new PrinterMessage(3, "连接失败"));
//                    break;
//                case PrinterConstants.Connect.CLOSED:
//                    //连接已经关闭
//                    handler.handleMessage(new PrinterMessage(2, "连接已经关闭"));
//                    break;
//                case PrinterConstants.Connect.NODEVICE:
//                    //设备不存在
//                    handler.handleMessage(new PrinterMessage(1, "设备不存在"));
//                    break;
//                case 0:
//                    //通信正常
//                    handler.handleMessage(new PrinterMessage(0, "通信正常"));
//                    break;
//                case -1:
//                    //通信异常
//                    handler.handleMessage(new PrinterMessage(-1, "通信异常"));
//                    break;
//                case -2:
//                    //打印机缺纸
//                    handler.handleMessage(new PrinterMessage(-2, "打印机缺纸"));
//                    break;
//                case -3:
//                    //打印机开盖
//                    handler.handleMessage(new PrinterMessage(-3, "打印机开盖"));
//                    break;
//                default:
//                    break;
//            }
//        }
//    }
//
//
//	@Override
//	public void cut() throws IOException {
//
//	};
//}
