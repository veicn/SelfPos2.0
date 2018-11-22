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
 * Created by zbb on 2017/5/9 0009.
 * 单品第二杯特价
 * 方案描述：
 * 这个方案设计的太复杂了，条件中有数量的要求，还需要在可执行中找到相关的菜品
 */
public class DishSecondSpecialPriceMarketAction implements MarketActionInterface {

	private Market market;
	private BigDecimal[] pricearray     = new BigDecimal[2];
	private BigDecimal   specialPrice   = new BigDecimal("0");
	private BigDecimal   danPinSumPrice = new BigDecimal("0");

	public DishSecondSpecialPriceMarketAction(Market market) {
		this.market = market;
	}

	public DishSecondSpecialPriceMarketAction() {
	}


	@Override
	public BigDecimal[] updateCost(List<CartDish> itemList) {


		// 实现的思路如下：之前已经把各个菜品项拆分为一个一个独立项目；然后先建立条件列表和可执行列表；
		// 在条件列表中找出满足方案条件的品项；在可执行列表中找出可执行项；进行方案的处理；
		// 然后递归，重新来一遍，直到没有可以再次执行的情况为止。

		// 符合条件的品项列表
		List<CartDish> triggerItemList = new ArrayList<CartDish>();
		// 可以r执行方案的品项列表
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
		if (triggerItemList.size() == 0 || executeItemList.size() == 0)
			return pricearray;
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
			CartDish executeItem = null;
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
					executeItem = canExecuteItem;
					break;
				}
			}

			if (executeItem == null) {
				// 没有可以执行的品项了， 直接返回
				return null;
			} else {
				//                // 对可以执行的品项进行方案的处理
				double cash = market.getTheSecondPrice();
				if (PriceUtil.subtract(new BigDecimal(executeItem
						.getCost()), new BigDecimal(cash + ""))
						.floatValue() >= 0) {
					specialPrice = PriceUtil
							.add(specialPrice, PriceUtil.multiply(PriceUtil
									.subtract(new BigDecimal(executeItem
											.getCost()), new BigDecimal(cash + "")), executeItem
									.getQuantity()));


					MarketObject object = new MarketObject(market
							.getMarketName(), PriceUtil.subtract(new BigDecimal(executeItem
							.getCost()), new BigDecimal(cash + "")), market
							.getMarketType() + "");
					executeItem.setDiscountAmount(object.getReduceCash().floatValue());//记录单品折扣
					ArrayList<MarketObject> objects = new ArrayList<>();
					objects.add(object);
					executeItem.setMarketList(objects);
					executeItem.setCost(cash + "");
					danPinSumPrice = PriceUtil.add(danPinSumPrice, PriceUtil
							.multiply(new BigDecimal(cash + ""), executeItem.getQuantity()));
					executeItem.setCalculate(true);
				}
				for (CartDish triggerItem : realTriggerDishList) {
					triggerItem.setCalculate(true);
					//					triggerItem.setCurrentPrice(triggerItem.getDishTotalPrice());
					danPinSumPrice = PriceUtil
							.add(danPinSumPrice, new BigDecimal(triggerItem.getCost()));
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


	@Override
	public Market getMarket() {
		return market;
	}
}
