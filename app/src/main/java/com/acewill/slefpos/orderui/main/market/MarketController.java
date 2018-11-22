package com.acewill.slefpos.orderui.main.market;


import com.acewill.slefpos.R;
import com.acewill.slefpos.bean.cartbean.Cart;
import com.acewill.slefpos.bean.cartbean.CartDish;
import com.acewill.slefpos.bean.orderbean.Order;
import com.acewill.slefpos.bean.smarantstorebean.Market;
import com.acewill.slefpos.bean.wshbean.WshAccount;
import com.acewill.slefpos.orderui.main.uidataprovider.SmarantDataProvider;
import com.acewill.slefpos.orderui.main.uidataprovider.WshDataProvider;
import com.acewill.slefpos.utils.priceutils.PriceUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;


/**
 * Author：Anch
 * Date：2017/5/24 10:08
 * Desc：
 */
public class MarketController {


	//	/**
	//	 * 单品特价，获取特价的商品列表
	//	 *
	//	 * @return
	//	 */
	//	public static List<RecomendPackageBean> getRecommandList() {
	//		ArrayList<RecomendPackageBean> recomendList = new ArrayList<>();
	//		if (marketList != null) {
	//			for (int i = 0; i < marketList.size(); i++) {
	//				Market market = marketList.get(i);
	//				if (market.getMarketType() == 3) {
	//					//如果是单品特价
	//					List<Integer> idList = market.getMarketDishList();
	//					if (idList != null) {
	//						for (int j = 0; j < idList.size(); j++) {
	//							DishModel dish2 = DishDataController
	//									.getDishModel(String.valueOf(idList.get(j)),
	//											Cart.takeout ? 1 : 0);
	//							RecomendPackageBean bean = new RecomendPackageBean();
	//							bean.setImageName(dish2.imageName);
	//							bean.setPackageName(dish2.dishName);
	//							bean.setPrice(dish2.price);
	//							//							bean.setSpecialId(String.valueOf(idList.get(j)));
	//							//							bean.setTheSecondPrice(market.getTheSecondPrice());//设置半价的价格
	//							recomendList.add(bean);
	//						}
	//					}
	//				}
	//			}
	//		}
	//		return recomendList;
	//	}

	/**
	 * 判断有没有满减活动
	 */
	public static boolean hasManjian() {
		List<Market> markets = new ArrayList<>();

		if (SmarantDataProvider.getMarketList() != null && SmarantDataProvider.getMarketList()
				.getContent() != null) {
			List<Market> marketList = SmarantDataProvider.getMarketList().getContent();
			for (int i = 0; i < marketList.size();
			     i++) {
				Market  market = marketList.get(i);
				boolean canExcute;
				if (Order.getInstance().isMember()) {
					canExcute = market.isCommonExecute();//判断这个营销方案是否和会员价共同执行
				} else {
					canExcute = true;
				}
				boolean manjianBoolean = market.getTriggerType() == 0 && market
						.getMarketType() == 1 && canExcute;//满减 优惠方式是 现金优惠
				boolean zhekouBoolean = market.getTriggerType() == 0 && market
						.getMarketType() == 0 && canExcute;// 满减 优惠方式是 折扣
				if (manjianBoolean || zhekouBoolean) {
					//满减 并且  优惠金额
					markets.add(market);
				}
			}
		}
		return markets.size() > 0;
	}


	public static Map<Integer, Market> getManjianPrice() {

		Map<Integer, Market> pricemap = new IdentityHashMap<>();
		Map<Double, Market>  tempMap  = new IdentityHashMap<>();
		if (SmarantDataProvider.getMarketList() != null && SmarantDataProvider.getMarketList()
				.getContent() != null) {
			List<Market> marketList = SmarantDataProvider.getMarketList().getContent();
			List<Market> markets    = handleList(marketList, true);

			if (markets != null) {
				for (int i = 0; i < markets.size(); i++) {
					Market market = markets.get(i);
					//当有多个满减活动的时候，
					boolean canExcute;
					if (Order.getInstance().isMember()) {
						canExcute = market.isCommonExecute();
					} else {
						canExcute = true;
					}
					boolean manjianBoolean = market.getTriggerType() == 0 && market
							.getMarketType() == 1 && canExcute;//满减 优惠方式是 现金优惠
					boolean zhekouBoolean = market.getTriggerType() == 0 && market
							.getMarketType() == 0 && canExcute;// 满减 优惠方式是 折扣
					if (manjianBoolean || zhekouBoolean) {
						//触发类型  0--满减
						Double aDouble = new Double(market.getFullCash());
						tempMap.put(aDouble, market);
					}
				}

				List<Double> keyList = new ArrayList<>();
				//排序
				for (Double fullCash : tempMap.keySet()) {
					keyList.add(fullCash);
				}


				// 对两个列表进行排序！默认排序是价格由高到低
				Collections.sort(keyList, new Comparator<Double>() {
					@Override
					public int compare(Double o1, Double o2) {
						return (int) (o2 - o1);
					}
				});


				for (int i = 0; i < keyList.size(); i++) {
					pricemap.put(i, tempMap.get(keyList.get(i)));
				}

			}
		}
		return pricemap;
	}

	public static List<String> getManJianList() {
		List<String> manjianList = new ArrayList<>();
		if (SmarantDataProvider.getMarketList() != null && SmarantDataProvider.getMarketList()
				.getContent() != null) {
			List<Market> marketList = SmarantDataProvider.getMarketList().getContent();
			for (int i = 0; i < marketList.size(); i++) {
				Market market = marketList.get(i);
				//当有多个满减活动的时候，
				if (market.getTriggerType() == 0 && market.getMarketType() == 1) {
					//触发类型  0--满减
					manjianList.add(market.getMarketName());
				}
			}
		}
		return manjianList;
	}


	/**
	 * 智慧快餐营销方案
	 * 单品和全单都在这里
	 *
	 * @return
	 */
	public static BigDecimal[] excuteMarketPlan() {
		BigDecimal[] totalPriceArray = new BigDecimal[2];
		totalPriceArray[0] = new BigDecimal("0");
		totalPriceArray[1] = new BigDecimal("0");
		List<CartDish> cartDishes = Cart.getInstance().getCartDishes();
		//原订单信息
		//List<NetOrderItem> itemList = order.getItemList();
		//  System.out.println("原订单信息itemList: "+itemList);
		//有效的促销方案
		//根据marke 构造marketaction
		List<Market> markets = marketDishs();//这里得到的是每个dish参与的营销方案，也就是dish对应的营销方案

		markets = handleList(markets, false);
		List<MarketActionInterface> actionList = new ArrayList<>();
		for (Market market : markets) {
			List<Integer> marketList = market.getMarketDishList();
			//以上是为了获得触发条件的总个数
			List<CartDish> marketOrderItemList = new ArrayList<>();
			for (CartDish item : cartDishes) {
				for (Integer marketId : marketList) {
					if (String.valueOf(marketId).equals(item.getDishID())) {
						marketOrderItemList.add(item);
					}
				}
			}
			//			market.setMarketDishOrderItem(marketOrderItemList);
			//该时段是否执行
			MarketActionInterface anInterface = MarketActionFactory
					.create(market.getTriggerType(), market.getMarketType(), market);
			//是否共同执行
			if (anInterface != null && !Order.getInstance().isMember()) {
				actionList.add(anInterface);
			} else if (anInterface != null && market.isCommonExecute()) {
				actionList.add(anInterface);
			}
		}


		Collections.sort(actionList, new Comparator<MarketActionInterface>() {
			@Override
			public int compare(MarketActionInterface a1, MarketActionInterface a2) {
				Market m1 = a1.getMarket();
				Market m2 = a2.getMarket();

				int triggerType1 = m1.getTriggerType();
				int triggerType2 = m2.getTriggerType();

				if (triggerType1 == MarketTriggerType.SINGLE_DISH
						.value() && triggerType2 == MarketTriggerType.SINGLE_DISH.value()) {
					//如果单品的促销类型相同，按照 第二份特价>有买有送>优惠金额>折扣 优先第二份特价的方案
					if (m1.getMarketType() == m2.getMarketType()) {
						//第二份特价
						if (m1.getMarketType() == MarketType.MARKET_SECOND.value() && m2
								.getMarketType() == MarketType.MARKET_SECOND.value()) {
							//如果第二份类型相同，选择最大额度的
							if (m1.getTheSecondType() == m2.getTheSecondType() && m1
									.getTheSecondType() == MarketSecondDishType.SPECIAL_PRICE
									.value()) {
								return (int) (m1.getTheSecondPrice() * 100) - (int) (m2
										.getTheSecondPrice() * 100);
							}
							if (m1.getTheSecondType() == m2.getTheSecondType() && m1
									.getTheSecondType() == MarketSecondDishType.RATE.value()) {
								return (int) (m1.getTheSecondRate() * 100) - (int) (m2
										.getTheSecondRate() * 100);
							}
							return m2.getTheSecondType() - m1.getTheSecondType();
						}
						//买赠
						if (m1.getMarketType() == MarketType.MARKET_GIFT.value() && m2
								.getMarketType() == MarketType.MARKET_GIFT.value()) {
							return m2.getGiftDishCount() - m1.getGiftDishCount();
						}
						//优惠价
						if (m1.getMarketType() == MarketType.MARKET_CASH.value() && m2
								.getMarketType() == MarketType.MARKET_CASH.value()) {
							return (int) (m1.getCash() * 100) - (int) (m2.getCash() * 100);
						}
						//折扣
						if (m1.getMarketType() == MarketType.MARKET_RATE.value() && m2
								.getMarketType() == MarketType.MARKET_RATE.value()) {
							return (int) (m1.getRate() * 100) - (int) (m2.getRate() * 100);
						}
					}
					// 两个都是单品的优惠方案，那么按照 marketType 排序
					return m2.getMarketType() - m1.getMarketType();
				}
				if (triggerType1 == MarketTriggerType.FULL_REDUCE
						.value() && triggerType2 == MarketTriggerType.FULL_REDUCE.value()) {
					// 两个都是全单方案，按照 全单条件中金额大的优先
					double fullCash1 = m1.getFullCash();
					double fullCash2 = m2.getFullCash();
					//满减
					if (m1.getMarketType() == MarketType.MARKET_CASH.value() && m2
							.getMarketType() == MarketType.MARKET_CASH.value()) {
						return (int) (m2.getCash() * 100) - (int) (m1.getCash() * 100);
					}
					//折扣
					if (m1.getMarketType() == MarketType.MARKET_RATE.value() && m2
							.getMarketType() == MarketType.MARKET_RATE.value()) {
						return (int) (m1.getRate() * 100) - (int) (m2.getRate() * 100);
					}
					return (int) (fullCash2 * 100) - (int) (fullCash1 * 100);
				}

				// 单品的优惠方案排在前面
				return triggerType2 - triggerType1;
			}
		});


		//执行全单action
		// 执行方案之前，需要把item拆分，数量多于一个的情况下非常不好处理！
		List<CartDish> departItemList = new ArrayList<>();
		int            itemIndex      = 1;


		for (CartDish item : cartDishes) {
			int count = item.getQuantity();
			if (count == 1) {
				CartDish clone = item.myclone();
				clone.setItemIndex(itemIndex);
				itemIndex++;
				departItemList.add(clone);
			} else {
				for (int i = 0; i < count; i++) {
					CartDish singleItem = item.myclone();
					singleItem.setItemIndex(itemIndex);
					singleItem.setQuantity(1);
					itemIndex++;
					departItemList.add(singleItem);
				}
			}
		}
		//真正执行营销方案的地方
		for (MarketActionInterface ma : actionList) {
			BigDecimal[] pricearray = ma.updateCost(departItemList);
			if (pricearray == null || pricearray[0] == null || pricearray[1] == null)
				continue;
			totalPriceArray[0] = PriceUtil.add(totalPriceArray[0], pricearray[0]);//执行完营销方案之后需要支付的金额
			totalPriceArray[1] = PriceUtil.add(totalPriceArray[1], pricearray[1]);//优惠总金额
			//两个金额相加等于原总价
		}

		Cart.getInstance().setDepartItemList(departItemList);
		return totalPriceArray;
	}


	//检出在有效时间段内的活动
	public static List<Market> handleList(List<Market> list, boolean isALL) {
		List<Market> actlist = new ArrayList<>();

		for (Market market : list) {

			boolean dateFlag = true;
			boolean weekFlag = true;
			boolean timeFlag = true;

			if (Order.getInstance().isMember()) {
				if (market.isCommonExecute()) {
					if (market.isGrade()) {
						WshAccount account = WshDataProvider.getWshAccount();
						long       grade   = Long.parseLong(account.getGrade());
						List<Long> list1   = market.getGradeList();
						if (!list1.contains(grade)) {
							continue;
						}
					}
				} else {
					continue;
				}
			} else {
				if (market.isGrade()) {
					continue;
				}
			}


			if ((!market.isDateAvailable()) && (!market.isTimeAvailable()) && (!market
					.isWeekAvailable())) {
				actlist.add(market);//永久特价不用判断时间段
			} else {
				long   curTime = System.currentTimeMillis();
				double sdate   = market.getStartDate();
				double edate   = market.getEndDate();
				String hours   = MarketDateUtil.getHour();//当前小时
				String shours  = market.getStartTime();
				String ehours  = market.getEndTime();

				if (market.isDateAvailable()) {
					if (curTime < sdate || curTime > edate) //不在日期时间段内
						dateFlag = false;
				}

				if (market.isWeekAvailable() && dateFlag) {
					String week = market.getWeek();
					if (!week.contains(MarketDateUtil.getWeekOfDate())) //如果当天不在设置的时间段内
						weekFlag = false;
				}

				// FIXME 后台不判断时间,留给POS或者点餐机判断
				if (market.isTimeAvailable() && dateFlag && timeFlag) {
					if (MarketDateUtil.compareData(hours, shours) == -1 || MarketDateUtil
							.compareData(ehours, hours) == -1) //不在营销时间内
						timeFlag = false;
				}
				if (dateFlag && weekFlag && timeFlag)
					actlist.add(market);// 时间检测通过 , 符合要求
			}
		}
		//对 营销方案进行排序

		return actlist;
	}


	//获得菜品的促销方案
	public static List<Market> marketDishs() {
		List<CartDish> items   = Cart.getInstance().getCartDishes();
		List<Market>   markets = new ArrayList<>();
		for (CartDish item : items) {
			if (SmarantDataProvider.getMarketList() != null && SmarantDataProvider.getMarketList()
					.getContent() != null && SmarantDataProvider.getMarketList()
					.getContent().size() > 0) {
				List<Market> marketList = SmarantDataProvider.getMarketList()
						.getContent();
				for (Market market : marketList) {
					List<Integer> triggerDishList = null;
					if (market.getTriggerType() == 0)
						triggerDishList = market.getMarketDishList();
					else
						triggerDishList = market.getTriggerDishList();
					for (Integer i : triggerDishList) {
						if (item.getDishID().equals(String.valueOf(i)) && !markets.contains(market))
							markets.add(market);
					}
				}
			}
		}
		return markets;
	}

	public static List<MarketBean> getMarketBeanList() {


		List<MarketBean> marketBeenList = new ArrayList<>();
		if (SmarantDataProvider.getMarketList() != null) {
			List<Market> list = SmarantDataProvider.getMarketList().getContent();
			if (list != null) {
				for (Market market : list) {
					MarketBean bean = new MarketBean();
					bean.setMarketName(market.getMarketName());
					int drawable = getMarketDrawable(market);
					bean.setMarketImg(drawable);
					marketBeenList.add(bean);
				}
			}
		}

		return marketBeenList;
	}

	private static int getMarketDrawable(Market market) {
		int markettype = market.getMarketType();
		int drawable   = 0;
		switch (markettype) {
			case 0:
				drawable = R.mipmap.icon_zhekou;
				break;
			case 1:
				drawable = R.mipmap.icon_reduce;

				break;
			case 2:
				drawable = R.mipmap.icon_donate;
				break;
			case 3:
				drawable = R.mipmap.icon_special_price;
				break;

		}
		return drawable;
	}


}
