package com.acewill.slefpos.printer;

/**
 * Created by linmingren on 16/8/3.
 */
public enum PrinterVendor {
    UNKNOWN("unknown"),
    EPSON("epson"), //爱普生
    RONDTA("rongta"), //容大
    YPT("ypt"); //滢普通

    private String name;

    PrinterVendor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static PrinterVendor fromName(String name) {
        for (PrinterVendor vendor : PrinterVendor.values()) {
            if (vendor.getName().equals(name)) {
                return vendor;
            }
        }

        return PrinterVendor.UNKNOWN;
    }
}
