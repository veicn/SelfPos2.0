package com.acewill.slefpos.orderui.main.market;

/**
 * Created by tangyunzhong on 17/2/28.
 */
public enum MarketSecondDishType {

    RATE(0),
    SPECIAL_PRICE(1);

    private int value;
    private MarketSecondDishType(int value)
    {
        this.value = value;
    }

    public static MarketSecondDishType valueof(int value)
    {
        switch(value)
        {
            case 0:
                return RATE;
            case 1:
                return SPECIAL_PRICE;
            default:
                return null;
        }
    }

    public int value()
    {
        return this.value;
    }

    public String getString()
    {
        switch (value)
        {
            case 0:
                return "第二份菜品打折";
            case 1:
                return "第二份菜品特价";
            default:
                return "未知类型";
        }
    }

}
