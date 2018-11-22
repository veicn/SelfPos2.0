package com.acewill.slefpos.orderui.main.market;

/**
 * Created by tangyunzhong on 17/2/28.
 */
public enum MarketType {

    MARKET_RATE(0),
    MARKET_CASH(1),
    MARKET_GIFT(2),
    MARKET_SECOND(3);

    private int value;
    private MarketType(int value)
    {
        this.value = value;
    }

    public static MarketType valueof(int value)
    {
        switch(value)
        {
            case 0:
                return MARKET_RATE;
            case 1:
                return MARKET_CASH;
            case 2:
                return MARKET_GIFT;
            case 3:
                return MARKET_SECOND;
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
                return "折扣";
            case 1:
                return "优惠金额";
            case 2:
                return "有买有送";
            case 3:
                return "第二份特价";
            default:
                return "未知类型";
        }
    }

}
