package com.acewill.slefpos.print.chikenprintlibrary.printer.vendor;


import com.acewill.slefpos.print.chikenprintlibrary.printer.PrinterWidth;

/**
 * 莹普通打印机
 * Created by Acewill on 2016/8/17.
 */
public class YptPrinter extends WifiPrinter {
    public YptPrinter(String host, PrinterWidth width) {
        super(host, width == PrinterWidth.WIDTH_80MM ? 42 : 32);
    }
}
