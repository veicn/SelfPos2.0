package com.acewill.slefpos.orderui.main.market;


import com.acewill.slefpos.bean.cartbean.CartDish;
import com.acewill.slefpos.bean.smarantstorebean.Market;
import com.acewill.slefpos.utils.priceutils.PriceUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2017/5/24 0024.
 * 单品买赠
 * 方案描述：
 * 这个方案设计的太复杂了，条件中有数量的要求，还需要在可执行中找到相关的菜品
 */
public class DishDonateMarketAction implements MarketActionInterface {
	private Market market;
	private BigDecimal[] pricearray     = new BigDecimal[2];
	private BigDecimal   specialPrice   = new BigDecimal("0");//某个单品优惠的金额*个数
	private BigDecimal   danPinSumPrice = new BigDecimal("0");

	public DishDonateMarketAction(Market market) {
		this.market = market;
	}

	public DishDonateMarketAction() {
	}

	@Override
	public BigDecimal[] updateCost(List<CartDish> itemList) {

		// 实现的思路如下：之前已经把各个菜品项拆分为一个一个独立项目；然后先建立条件列表和可执行列表；
		// 在调价列表中找出满足方案条件的品项；在可执行列表中找出可执行项；进行方案的处理；
		// 然后递归，重新来一遍，直到没有可以再次执行的情况为止。

		// 符合条件的品项列表
		List<CartDish> triggerItemList = new ArrayList<CartDish>();
		// 可以执行方案的品项列表
		List<CartDish> executeItemList = new ArrayList<CartDish>();

		// 分别生成条件和执行列表
		for (CartDish item : itemList) {
			if (item.isCalculate()) {
				// 品项已经参与过其他的方案，不在参与该方案
				continue;
			}

			if (market.getTriggerDishList().contains(Integer.parseInt(item.getDishID())))
				triggerItemList.add(item);
			if (market.getMarketDishList().contains(Integer.parseInt(item.getDishID())))
				executeItemList.add(item);
		}

		// 对两个列表进行排序！默认排序是价格由高到低
		Collections.sort(triggerItemList, new Comparator<CartDish>() {
			@Override
			public int compare(CartDish o1, CartDish o2) {
				return PriceUtil.compare(o2.getCost(), o1.getCost());
			}
		});
		Collections.sort(executeItemList, new Comparator<CartDish>() {
			@Override
			public int compare(CartDish o1, CartDish o2) {
				return PriceUtil.compare(o2.getCost(), o1.getCost());
			}
		});

		// 找到符合条件的菜品品项；因为有数量上的要求，所以需要使用列表来控制！
		int            triggerDishCount    = market.getTriggerDishCount();
		List<CartDish> realTriggerDishList = new ArrayList<CartDish>();
		for (CartDish item : triggerItemList) {
			if (realTriggerDishList.isEmpty())
				realTriggerDishList.add(item);
			else {
				// 判断品项与之前的是否相同，如果不同，说明之前的品项已经不和要求
				boolean isSame = true;
				for (CartDish triggerItem : realTriggerDishList) {
					if (!triggerItem.getDishID().equals(item.getDishID())) {
						isSame = false;
						break;
					}
				}
				if (!isSame)
					realTriggerDishList.clear();
				realTriggerDishList.add(item);
			}

			if (realTriggerDishList.size() == triggerDishCount)
				break;
		}

		if (realTriggerDishList.size() == triggerDishCount) {
			// 找到符合条件的品项了，下面查找可以执行的品项
			List<CartDish> executeItem = null;
			executeItem = new ArrayList<>();
			for (CartDish canExecuteItem : executeItemList) {
				// 可以执行的品项，不能与条件中的品项是同一个
				boolean isInTrigger = false;
				for (CartDish triggerItem : realTriggerDishList) {
					if (triggerItem.getItemIndex() == canExecuteItem.getItemIndex()) {
						isInTrigger = true;
						break;
					}
				}
				if (isInTrigger)
					continue; // 这个包含在了条件之中，不能执行方案，继续找
				else {
					if (executeItem != null && !executeItem.isEmpty() && executeItem
							.size() == market.getGiftDishCount()) {
						break;
					}
					executeItem.add(canExecuteItem);
					continue;
				}
			}

			if (executeItem == null || executeItem.isEmpty()) {
				// 没有可以执行的品项了， 直接返回
				return null;
			} else {
				for (CartDish model : executeItem) {
					// 对可以执行的品项进行方案的处理
					// 赠送了，就是价格为0
					model.setCalculate(true);
					specialPrice = PriceUtil
							.add(specialPrice, new BigDecimal(model.getCost()));

					MarketObject object = new MarketObject(market
							.getMarketName(), new BigDecimal(model.getCost()), market
							.getMarketType() + "");
					model.setDiscountAmount(object.getReduceCash().floatValue());
					ArrayList<MarketObject> objects = new ArrayList<>();
					objects.add(object);
					model.setMarketList(objects);
					model.setCost("0");
					model.setGift(true);
				}
				for (CartDish triggerItem : realTriggerDishList) {
					triggerItem.setCalculate(true);
					//					triggerItem.setTemp_priced(triggerItem.getTemp_priced());
					danPinSumPrice = PriceUtil.add(danPinSumPrice, PriceUtil
							.multiply(triggerItem
									.getPrice(), triggerItem.getQuantity()));
				}

				// 然后递归
				updateCost(itemList);
			}
		} else {
			// 没有符合条件的品项，直接返回
			return null;
		}
		pricearray[0] = danPinSumPrice;
		pricearray[1] = specialPrice;
		return pricearray;
	}

	//	private static void setMarketObjectList(DishModel dish, Market market, BigDecimal marketDishCost) {
	//		List<MarketObject> marketList = null;
	//		marketList = new ArrayList<>();
	//		MarketObject marketObject = new MarketObject(market.getMarketName(), marketDishCost);
	//		marketList.add(marketObject);
	//		dish.setMarketList(marketList);
	//	}

	@Override
	public Market getMarket() {
		return market;
	}
}
