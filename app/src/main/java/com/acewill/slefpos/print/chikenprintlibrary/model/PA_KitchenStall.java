package com.acewill.slefpos.print.chikenprintlibrary.model;

import java.util.List;

/**
 * Created by Administrator on 2017-05-02.
 */
public class PA_KitchenStall
{
    private String appid;
    private Long brandid;
    private Long storeid;
    private Long stallsid;
    private String stallsName;
    private Integer printerid;
    private Integer kdsid;
    private Integer summaryReceiptCounts = 0;
    private Integer dishReceiptCounts = 1;
    private List<Long> dishIdList;
    private PA_KitchenPrintMode kitchenPrintMode;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public Long getBrandid() {
        return brandid;
    }

    public void setBrandid(Long brandid) {
        this.brandid = brandid;
    }

    public Long getStoreid() {
        return storeid;
    }

    public void setStoreid(Long storeid) {
        this.storeid = storeid;
    }

    public Long getStallsid() {
        return stallsid;
    }

    public void setStallsid(Long stallsid) {
        this.stallsid = stallsid;
    }

    public String getStallsName() {
        return stallsName;
    }

    public void setStallsName(String stallsName) {
        this.stallsName = stallsName;
    }

    public Integer getPrinterid() {
        return printerid;
    }

    public void setPrinterid(Integer printerid) {
        this.printerid = printerid;
    }

    public Integer getKdsid() {
        return kdsid;
    }

    public void setKdsid(Integer kdsid) {
        this.kdsid = kdsid;
    }

    public Integer getSummaryReceiptCounts() {
        return summaryReceiptCounts;
    }

    public void setSummaryReceiptCounts(Integer summaryReceiptCounts) {
        this.summaryReceiptCounts = summaryReceiptCounts;
    }

    public Integer getDishReceiptCounts() {
        return dishReceiptCounts;
    }

    public void setDishReceiptCounts(Integer dishReceiptCounts) {
        this.dishReceiptCounts = dishReceiptCounts;
    }

    public List<Long> getDishIdList() {
        return dishIdList;
    }

    public void setDishIdList(List<Long> dishIdList) {
        this.dishIdList = dishIdList;
    }

    public PA_KitchenPrintMode getKitchenPrintMode() {
        return kitchenPrintMode;
    }

    public void setKitchenPrintMode(PA_KitchenPrintMode kitchenPrintMode) {
        this.kitchenPrintMode = kitchenPrintMode;
    }
}
