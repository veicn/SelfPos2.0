package com.acewill.slefpos.orderui.main.market;

import com.acewill.slefpos.bean.cartbean.CartDish;
import com.acewill.slefpos.bean.smarantstorebean.Market;
import com.acewill.slefpos.utils.priceutils.PriceUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/20 0020.
 * 全单打折
 */

class OrderDiscountMarketAction implements MarketActionInterface {


	private Market market;
	private BigDecimal[] pricearray   = new BigDecimal[2];
	private BigDecimal   specialPrice = new BigDecimal("0");//执行该营销方案的菜品的总优惠
	private BigDecimal   sumPrice     = new BigDecimal("0");//执行该营销方案的菜品的总价


	public OrderDiscountMarketAction(Market market) {
		this.market = market;
	}

	@Override
	public BigDecimal[] updateCost(List<CartDish> itemList) {


		// 实现的思路如下：之前已经把各个菜品项拆分为一个一个独立项目；然后先建立条件列表和可执行列表；
		// 在调价列表中找出满足方案条件的品项；在可执行列表中找出可执行项；进行方案的处理；
		// 然后递归，重新来一遍，直到没有可以再次执行的情况为止。


		// 可以执行方案的品项列表
		List<CartDish> executeItemList = new ArrayList<CartDish>();

		// 分别生成条件和执行列表
		for (CartDish item : itemList) {
			if (item.isCalculate()) {
				// 品项已经参与过其他的方案，不在参与该方案
				continue;
			}

			if (market.getMarketDishList().contains(Integer.parseInt(item.getDishID())))
				executeItemList.add(item);
		}
		BigDecimal sum = new BigDecimal("0");
		for (CartDish model : executeItemList) {
			sum = PriceUtil.add(sum, model.getCost());
		}
		if (PriceUtil.subtract(sum, new BigDecimal(market.getFullCash())).floatValue() >= 0) {
			//如果已经参与了其中一种满减活动，那么就要标记它，下次它就不参与下一种满减的计算了
			for (CartDish cartDish : executeItemList) {
				cartDish.setCalculate(true);
				//将折扣平均到每一个菜里面
				BigDecimal price = PriceUtil
						.multiply(new BigDecimal(cartDish
								.getCost()), new BigDecimal(String
								.valueOf(market.getRate())));
				BigDecimal danpinspecialPrice = PriceUtil.subtract(new BigDecimal(cartDish
						.getCost()), price);
				MarketObject object = new MarketObject(market
						.getMarketName(), danpinspecialPrice, market
						.getMarketType() + "");
				cartDish.setDiscountAmount(danpinspecialPrice.floatValue());
				ArrayList<MarketObject> objects = new ArrayList<>();
				objects.add(object);
				cartDish.setCost(PriceUtil
						.subtract(new BigDecimal(cartDish.getCost()), danpinspecialPrice).toString());
				cartDish.setMarketList(objects);

				sumPrice = PriceUtil.add(sumPrice, cartDish.getCost());
				specialPrice = PriceUtil
						.add(specialPrice, new BigDecimal(cartDish.getDiscountAmount()));
			}
//			for (CartDish model : executeItemList) {
//				sumPrice = PriceUtil.add(sumPrice, model.getCost());
//			}
//			for (CartDish model : executeItemList) {
//				specialPrice = PriceUtil
//						.add(specialPrice, new BigDecimal(model.getDiscountAmount()));
//			}
		}
		pricearray[0] = sumPrice;
		pricearray[1] = specialPrice;

		return pricearray;
	}

	@Override
	public Market getMarket() {
		return market;
	}
}
