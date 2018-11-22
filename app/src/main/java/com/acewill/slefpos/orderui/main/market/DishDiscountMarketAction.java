package com.acewill.slefpos.orderui.main.market;


import com.acewill.slefpos.bean.cartbean.CartDish;
import com.acewill.slefpos.bean.smarantstorebean.Market;
import com.acewill.slefpos.utils.priceutils.PriceUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/9 0009.
 * 单品折扣
 * 方案描述：
 * 方案中，菜品购买份数达到条件要求，就可以打折处理
 */
public class DishDiscountMarketAction implements MarketActionInterface {
	private Market market;
	private BigDecimal   specialPrice   = new BigDecimal("0");
	private BigDecimal   danPinSumPrice = new BigDecimal("0");
	private BigDecimal[] pricearray     = new BigDecimal[2];

	public DishDiscountMarketAction(Market market) {
		this.market = market;
	}

	public DishDiscountMarketAction() {
	}

	@Override
	public BigDecimal[] updateCost(List<CartDish> itemList) {

		// 之前已经把所有的订单项进行了拆分，这里需要重新统计是否符合条件中的菜品数量
		Map<Long, List<CartDish>> itemMap = new HashMap<Long, List<CartDish>>();
		for (CartDish item : itemList) {
			if (!item.isCalculate() && market.getTriggerDishList()
					.contains(Integer.parseInt(item.getDishID()))) {
				// 该品项符合条件，进入统计
				Long dishId = Long.parseLong(item.getDishID());
				if (itemMap.containsKey(dishId)) {
					itemMap.get(dishId).add(item);
				} else {
					List<CartDish> list = new ArrayList<CartDish>();
					list.add(item);
					itemMap.put(dishId, list);
				}
			}
		}

		if (market.getTriggerDishList() != null && market.getTriggerDishList().size() > 0) {
			// 活动条件中的数量
			int triggerDishCount = market.getTriggerDishCount();
			if (triggerDishCount < 1)
				return null;

			for (Long key : itemMap.keySet()) {
				List<CartDish> items = itemMap.get(key);
				if (items.size() >= triggerDishCount) {
					// 里面的品项都满足方案的条件，可以进行促销处理
					for (CartDish model : items) {
						double rate = market.getRate();
						BigDecimal price = PriceUtil.multiply(new BigDecimal(model
								.getCost()), new BigDecimal(rate + ""));// 这个是优惠之后的价格
						specialPrice = PriceUtil
								.add(specialPrice, PriceUtil
										.subtract(new BigDecimal(model
												.getCost()), price));//优惠了的价格
						danPinSumPrice = PriceUtil.add(danPinSumPrice, price);

						model.setCalculate(true);
						MarketObject object = new MarketObject(market.getMarketName(), PriceUtil
								.subtract(new BigDecimal(model
										.getCost()), price), market.getMarketType() + "");
						model.setDiscountAmount(object.getReduceCash().floatValue());
						ArrayList<MarketObject> objects = new ArrayList<>();
						objects.add(object);
						model.setMarketList(objects);
						//设置当前价格
						model.setCost(price.toString());
					}
				}
			}
			pricearray[0] = danPinSumPrice;
			pricearray[1] = specialPrice;
		}
		return pricearray;
	}

	@Override
	public Market getMarket() {
		return market;
	}
}
