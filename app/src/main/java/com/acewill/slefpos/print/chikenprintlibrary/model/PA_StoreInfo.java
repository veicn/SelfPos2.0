package com.acewill.slefpos.print.chikenprintlibrary.model;

/**
 * Created by Administrator on 2017-05-03.
 */
public class PA_StoreInfo
{
    private boolean cardNumberMode = false; //true则为送餐模式， 需要在结账时弹框，输入一个餐牌号码   false：顾客自取

    private String storeMode;

    public boolean isCardNumberMode() {
        return cardNumberMode;
    }

    public void setCardNumberMode(boolean cardNumberMode) {
        this.cardNumberMode = cardNumberMode;
    }

    public String getStoreMode() {
        return storeMode;
    }

    public void setStoreMode(String storeMode) {
        this.storeMode = storeMode;
    }
}
