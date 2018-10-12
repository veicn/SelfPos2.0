package com.acewill.slefpos.print.chikenprintlibrary.printer;


import com.acewill.slefpos.print.chikenprintlibrary.model.PA_KitchenPrintMode;

/**
 * Created by DHH on 2017/1/6.
 */

public class PrintConfiguration {
    private int checkoutReceiptCounts = 1; //结账单需要打印几份？
    private int guestReceiptCounts = 1; //客用小票需要打印几份



    private boolean             printSummaryKitchenReceipt = false; //是否打印厨房的汇总单-- 所有菜在一张票上
    private PA_KitchenPrintMode kitchenPrintMode           = PA_KitchenPrintMode.PER_DISH;
    private boolean             printPackageName           = false; //打印套餐中的菜品时，是否在这些菜的前面一行打印 套餐名称

    /**
     * 结账单需要打印几份？
     * @return
     */
    public int getCheckoutReceiptCounts() {
        return checkoutReceiptCounts;
    }

    public void setCheckoutReceiptCounts(int checkoutReceiptCounts) {
        this.checkoutReceiptCounts = checkoutReceiptCounts;
    }

    public int getGuestReceiptCounts() {
        return guestReceiptCounts;
    }

    public void setGuestReceiptCounts(int guestReceiptCounts) {
        this.guestReceiptCounts = guestReceiptCounts;
    }

    public boolean isPrintSummaryKitchenReceipt() {
        return printSummaryKitchenReceipt;
    }

    public void setPrintSummaryKitchenReceipt(boolean printSummaryKitchenReceipt) {
        this.printSummaryKitchenReceipt = printSummaryKitchenReceipt;
    }

    public PA_KitchenPrintMode getKitchenPrintMode() {
        return kitchenPrintMode;
    }

    public void setKitchenPrintMode(PA_KitchenPrintMode kitchenPrintMode) {
        this.kitchenPrintMode = kitchenPrintMode;
    }

    public boolean isPrintPackageName() {
        return printPackageName;
    }

    public void setPrintPackageName(boolean printPackageName) {
        this.printPackageName = printPackageName;
    }
}
