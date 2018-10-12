package com.acewill.slefpos.print.chikenprintlibrary.model;

/**
 * Created by Administrator on 2017-05-02.
 */
public enum PA_KitchenPrintMode
{
    PER_ITEM(0), //每份菜一张单 -- 比如一个菜点了3份， 会打印机3张单，上面的份数是1
    PER_DISH(1);//一个菜打印一张单 -- 比如一个菜点了3份， 也只打一张单，上面的份数是3

    private int value;

    PA_KitchenPrintMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static PA_KitchenPrintMode valueOf(int value)
    {
        switch (value)
        {
            case 0:
                return PER_ITEM;
            default:
                return PER_DISH;
        }
    }

    public static PA_KitchenPrintMode fromValue(String value)
    {
        if ("PER_DISH".equals(value))
            return PER_DISH;
        return PER_ITEM;
    }

    public String getString()
    {
        switch (value)
        {
            case 0:
                return "每份一单";
            default:
                return "每菜一单";
        }
    }
}
