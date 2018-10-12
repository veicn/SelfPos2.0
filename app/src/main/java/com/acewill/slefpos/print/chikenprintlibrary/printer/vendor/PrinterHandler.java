package com.acewill.slefpos.print.chikenprintlibrary.printer.vendor;

/**
 * 用来接收打印机消息
 * Created by Acewill on 2016/8/18.
 */
public interface PrinterHandler {
    void handleMessage(PrinterMessage message);
}
