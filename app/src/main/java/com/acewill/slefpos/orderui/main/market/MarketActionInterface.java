package com.acewill.slefpos.orderui.main.market;

import com.acewill.slefpos.bean.cartbean.CartDish;
import com.acewill.slefpos.bean.smarantstorebean.Market;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
public interface MarketActionInterface {
   // 单品营销处理这个函数
//    void updateCost(NetOrderItem item);
    // 圈单营销处理这个函数
    public BigDecimal[] updateCost(List<CartDish> itemList);

    public Market getMarket();
}
