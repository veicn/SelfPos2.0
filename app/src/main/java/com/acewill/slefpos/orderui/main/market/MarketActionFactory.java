package com.acewill.slefpos.orderui.main.market;


import com.acewill.slefpos.bean.smarantstorebean.Market;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
public class MarketActionFactory {

	public static MarketActionInterface create(int triggerType, int markettype, Market market) {
		MarketActionInterface actionInterface = null;
		if (triggerType == 0 && markettype == 0) {
			actionInterface = new OrderDiscountMarketAction(market);//全单打折
		} else if (triggerType == 0 && markettype == 1) {
			actionInterface = new OrderCouponMarketAction(market);//全单优惠
		} else
		if (triggerType == 2 && markettype == 0) {
			actionInterface = new DishDiscountMarketAction(market);//单品打折
		} else if (triggerType == 2 && markettype == 1) {
			actionInterface = new DishSpecialPriceMarketAction(market);//单品特价
		} else if (triggerType == 2 && markettype == 2) {
			actionInterface = new DishDonateMarketAction(market);//单品赠送
		} else if (triggerType == 2 && markettype == 3) {
			if (market.getTheSecondType() == 0) {//第二份折扣
				actionInterface = new DishSecondDiscountMarketAction(market);
			} else {//第二份实价
				actionInterface = new DishSecondSpecialPriceMarketAction(market);
			}
		}
		return actionInterface;
	}
}
