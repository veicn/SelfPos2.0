package com.acewill.slefpos.orderui.main.market;

/**
 * Created by tangyunzhong on 17/2/28.
 */
public enum MarketTriggerType {

    FULL_REDUCE(0),
    LOOP_FULL_REDUCE(1),
    SINGLE_DISH(2);

    private int value;
    private MarketTriggerType(int value)
    {
        this.value = value;
    }

    public static MarketTriggerType valueof(int value)
    {
        switch(value)
        {
            case 0:
                return FULL_REDUCE;
            case 1:
                return LOOP_FULL_REDUCE;
            case 2:
                return SINGLE_DISH;
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
                return "满减";
            case 1:
                return "循环满减";
            case 2:
                return "单品营销";
            default:
                return "未知类型";
        }
    }

}
