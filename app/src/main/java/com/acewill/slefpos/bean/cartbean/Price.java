package com.acewill.slefpos.bean.cartbean;

import android.text.TextUtils;
import android.util.Log;

import com.acewill.slefpos.bean.orderbean.Order;
import com.acewill.slefpos.bean.paybean.PayMethod;
import com.acewill.slefpos.bean.syncbean.Discount;
import com.acewill.slefpos.bean.syncbean.DiscountAmount;
import com.acewill.slefpos.bean.syncmember.SyncMemberLoginRes;
import com.acewill.slefpos.bean.uibean.UIDish;
import com.acewill.slefpos.bean.uibean.UIPackageOptionItem;
import com.acewill.slefpos.bean.uibean.UITasteOption;
import com.acewill.slefpos.bean.wshbean.WshAccountCoupon;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.orderui.main.market.MarketController;
import com.acewill.slefpos.orderui.main.uidataprovider.SmarantDataProvider;
import com.acewill.slefpos.orderui.main.uidataprovider.SyncDataProvider;
import com.acewill.slefpos.utils.priceutils.PriceUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author：Anch
 * Date：2018/2/27 16:40
 * Desc：
 */
public class Price {

	private static final String TAG = "Price";
	private static Price mPrice;

	private String actualCost;//这个是执行完优惠方案之后的所有菜品的价格，也就是实际要支付费money

	/**
	 * 获取价钱对象
	 *
	 * @return
	 */
	public static Price getInstance() {
		if (mPrice == null) {
			mPrice = new Price();
		}
		return mPrice;
	}


	public String getCurrentPrice(UIDish dish) {
		return Order.getInstance().isMember() ? dish.getMemberPrice() == null ? dish
				.getPrice() : dish.getMemberPrice() : dish.getPrice();
	}


	/**
	 * 菜品定制项价格
	 * 同步时系统和餐行健系统的的菜品定制项可以多份
	 *
	 * @param dish
	 * @return
	 */
	public BigDecimal getOptionPrice(UIDish dish) {
		BigDecimal bigDecimal = new BigDecimal(0);
		if (dish.getOptionList() != null && dish.getOptionList().size() > 0) {
			for (UITasteOption item : dish.getOptionList()) {

				if (item.getPrice() != 0) {
					bigDecimal = PriceUtil.add(bigDecimal, PriceUtil.multiply(String
							.valueOf(item.getPrice()), item.getQuantity()));
				}
			}
		}
		return bigDecimal;
	}

	/**
	 * 获取套餐的价格
	 *
	 * @param dish
	 * @return
	 */
	public BigDecimal getSubitemPrice(UIDish dish) {
		BigDecimal totalBigDecimal = new BigDecimal(0);

		if (dish.getSubItemList() != null && dish.getSubItemList().size() > 0) {
			for (UIPackageOptionItem item : dish.getSubItemList()) {
				BigDecimal bigDecimal = new BigDecimal(0);
				if (item.getOptionList() != null && item.getOptionList().size() > 0) {
					for (UITasteOption bean : item.getOptionList()) {
						if (bean.getPrice() != 0)
							bigDecimal = PriceUtil.add(bigDecimal, PriceUtil
									.multiply(String.valueOf(bean.getPrice()), bean.getQuantity()));
					}
				}
				if (SystemConfig.isSyncSystem) {
					bigDecimal = PriceUtil.multiply(PriceUtil.add(bigDecimal, item.getPrice()), item
							.getQuantity());
				} else if (SystemConfig.isSmarantSystem || SystemConfig.isCanXingJianSystem) {
					bigDecimal = PriceUtil.multiply(PriceUtil
							.add(bigDecimal, new BigDecimal(item.getExtraCost())), item
							.getQuantity());
				}
				totalBigDecimal = PriceUtil.add(totalBigDecimal, bigDecimal);
			}
		}
		return totalBigDecimal;
	}


	/**
	 * 会员代金券号,这个券不用来计算价格，只用来在结算的时候把该券使用掉，该字段和  cuponValue是共同存在的。
	 */
	private String couponNo;

	/**
	 * 会员优惠券的优惠金额，该金额是使用券可抵消的金额 , 该字段和  couponNo是共同存在的。
	 */
	private float couponValue;
	/**
	 * 使用的积分抵掉的金额
	 */
	private int   pointValue;
	/**
	 * 使用的积分抵掉的金额
	 */
	private float balance;

	/*
	* 会员日活动全单优惠的金额
	 */
	private float syncmemberactivitydiscountamount;

	public int getPointValue() {
		return pointValue;
	}

	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}

	public void setBalance(float total) {
		this.balance = total;
	}

	public float getBalance() {
		return balance;
	}


	/**
	 * 计算总价钱
	 *
	 * @return
	 */
	public String getTotal() {
		BigDecimal totalPrice = Price.getInstance().getDishTotalWithMix();


		// 减去优惠的价格
		if (SystemConfig.isSyncSystem && Order.getInstance().isMember() && SyncDataProvider
				.getSyncMemberAccount() != null) {
			//同步时会员日活动,此项必须第一步执行，里面有可能涉及到全单的情况
			totalPrice = syncDiscountAmount(totalPrice);

		} else if (SystemConfig.isSmarantSystem) {
			BigDecimal specialPrice = smarantDiscountAmount();
			totalPrice = PriceUtil.subtract(totalPrice, specialPrice);
			setActualCost(totalPrice.toString());
		}
		//0、美团团购券
		Map<String, Float> map = Price.getInstance().getDealsValueMap();
		for (String in : map.keySet()) {
			//map.keySet()返回的是所有key的值
			Float aFloat = map.get(in);
			totalPrice = PriceUtil
					.subtract(totalPrice, new BigDecimal(aFloat));
		}


		//1、代金券
		totalPrice = PriceUtil
				.subtract(totalPrice, new BigDecimal(Price.getInstance().getCouponValue()));

		//2、会员积分
		totalPrice = PriceUtil
				.subtract(totalPrice, new BigDecimal(Price.getInstance()
						.getPointValue()));

//		//打包费，打包费只能使用储值或者微信支付宝支付
//		totalPrice = PriceUtil
//				.add(totalPrice, new BigDecimal(Price.getInstance().getTotalWaidai_Cost()));

		//3、储值
		totalPrice = PriceUtil
				.subtract(totalPrice, new BigDecimal(Price.getInstance().getBalance()));


		Log.e(TAG, "totalPrice>" + Float.parseFloat(totalPrice.toString()));
		if (totalPrice.floatValue() < 0)
			return "0";
		return totalPrice.toString();
	}

	/**
	 * 智慧快餐的营销方案
	 *
	 * @return
	 */
	private BigDecimal smarantDiscountAmount() {
		/**
		 * 1、判断有没有满减活动
		 * 2、如果有商品没有参加满减的，在购物车中减去这个，没有参加满减的原价
		 * 3、这里应该有两个价格，一个是参加活动的价格，一个是不参加活动的价格
		 */
		BigDecimal specialPrice = new BigDecimal("0");//优惠的金额
		//购物车中的商品，判断有没有某个单品有它自己的营销方案

		/**
		 * 购物车中的菜品需要进行营销方案计算了，但是呢，我又不想它的值被改变，因此我copy一个list去计算
		 * 只要计算这个营销方案，那么得到的营销方案计算的结果都是一个新的
		 */
		List<CartDish> dishes   = Cart.getInstance().getCartDishes();
		List<CartDish> dishList = new ArrayList<>();
		for (CartDish cartDish : dishes) {
			CartDish cloneDish = cartDish.myclone();
			dishList.add(cloneDish);
		}
		for (CartDish item : dishList) {
			if (item.isCalculate()) {
				item.setCalculate(false); //重置所有的状态，下面将重新计算
			}
		}
		BigDecimal[] danPinSumPrice;
		if (SmarantDataProvider.getMarketList() == null || SmarantDataProvider.getMarketList()
				.getContent() == null || SmarantDataProvider.getMarketList()
				.getContent().size() == 0)
			danPinSumPrice = null;//计算单品的营销方案
		else
			danPinSumPrice = MarketController.excuteMarketPlan();//计算单品的营销方案,涉及到拆分和重组购物车数据的操作

		if (danPinSumPrice != null) {
			specialPrice = danPinSumPrice[1];
		}

		setDisCountPrice(specialPrice);
		return specialPrice;
	}

	private BigDecimal disCountPrice;//营销方案的价格

	/**
	 * 这部分是计算同步时的会员活动的营销活动
	 * 1、包含单品的情况
	 * 2、全单的情况，
	 * 上述两种情况互斥
	 *
	 * @param totalPrice
	 */
	private BigDecimal syncDiscountAmount(BigDecimal totalPrice) {
		Discount.getInstance().clear();
		SyncMemberLoginRes.DataBean account = SyncDataProvider.getSyncMemberAccount();
		if (account != null) {
			SyncMemberLoginRes.DataBean.MemberDateActivity memberdata = account
					.getMemberDateActivity();
			if (memberdata != null && memberdata.getItemList() != null && memberdata.getItemList()
					.size() > 0) {
				//某个商品特价或者是商品打折
				for (SyncMemberLoginRes.DataBean.ItemBean itemBean : memberdata.getItemList()) {
					for (CartDish cartDish : Cart.getInstance().getCartDishes()) {
						//特价
						if (cartDish.getDishID().equals(itemBean.getSkuOuid()) && itemBean
								.getDiscountType().equals("V")) {

							if (Float.parseFloat(cartDish.getPrice()) - itemBean
									.getDiscountValue() >= 0) {
								//正常情况，菜品的价格比特价的要高
								totalPrice = PriceUtil
										.subtract(totalPrice, PriceUtil
												.multiply(new BigDecimal(Float
														.parseFloat(cartDish
																.getPrice()) - itemBean
														.getDiscountValue()), cartDish
														.getQuantity()));
								//记录商品的优惠金额
								cartDish.setDiscountAmount(Float
										.parseFloat(cartDish
												.getPrice()) - itemBean
										.getDiscountValue());


								Discount.getInstance().getDiscountAmountList()
										.add(new DiscountAmount("会员价", PriceUtil
												.multiply(new BigDecimal(cartDish
														.getDiscountAmount()), cartDish
														.getQuantity()).floatValue(), "R", itemBean
												.getDiscountValue(), cartDish));
							} else {
								//异常情况，菜品的价格比特价的要低
								totalPrice = PriceUtil
										.add(totalPrice, PriceUtil
												.multiply(new BigDecimal(itemBean
														.getDiscountValue() - Float
														.parseFloat(cartDish
																.getPrice())), cartDish
														.getQuantity()));
								//记录商品的优惠金额
								cartDish.setDiscountAmount(Float
										.parseFloat(cartDish
												.getPrice()) - itemBean
										.getDiscountValue());

								Discount.getInstance().getDiscountAmountList()
										.add(new DiscountAmount("会员价", PriceUtil
												.multiply(new BigDecimal(cartDish
														.getDiscountAmount()), cartDish
														.getQuantity()).floatValue(), "R", itemBean
												.getDiscountValue(), cartDish));
							}
							//							}
						} else if (cartDish.getDishID()
								.equals(itemBean.getSkuOuid()) && itemBean
								.getDiscountType().equals("D")) {
							//单品折扣
							if (!TextUtils.isEmpty(SyncDataProvider
									.getSyncCompanySetByConfigKey("MIX.SINGLE.DISCOUNT")) && SyncDataProvider
									.getSyncCompanySetByConfigKey("MIX.SINGLE.DISCOUNT")
									.equals("Y")) {
								//这个控制商品优惠是否加料也一起优惠？是getCost  还是getPrice
								totalPrice = PriceUtil
										.subtract(totalPrice, PriceUtil.multiply(PriceUtil
												.subtract(new BigDecimal(cartDish
														.getCost()), PriceUtil
														.multiply(new BigDecimal(cartDish
																		.getCost()),
																PriceUtil
																		.divide(new BigDecimal(String
																				.valueOf(itemBean
																						.getDiscountValue())), new BigDecimal("100")))), cartDish
												.getQuantity()));
								//记录商品的优惠金额
								cartDish.setDiscountAmount(PriceUtil
										.subtract(new BigDecimal(cartDish.getCost()), PriceUtil
												.multiply(new BigDecimal(cartDish.getCost()),
														PriceUtil.divide(new BigDecimal(String
																.valueOf(itemBean
																		.getDiscountValue())), new BigDecimal("100"))))
										.floatValue());

								Discount.getInstance().getDiscountAmountList()
										.add(new DiscountAmount("会员价", PriceUtil
												.multiply(new BigDecimal(cartDish
														.getDiscountAmount()), cartDish
														.getQuantity()).floatValue(), itemBean
												.getDiscountType(), itemBean
												.getDiscountValue(), cartDish));
							} else {
								totalPrice = PriceUtil
										.subtract(totalPrice, PriceUtil.multiply(PriceUtil
												.subtract(new BigDecimal(cartDish
														.getPrice()), PriceUtil
														.multiply(new BigDecimal(cartDish
																		.getPrice()),
																PriceUtil
																		.divide(new BigDecimal(String
																				.valueOf(itemBean
																						.getDiscountValue())), new BigDecimal("100")))), cartDish
												.getQuantity()));
								//记录商品的优惠金额
								cartDish.setDiscountAmount(PriceUtil
										.subtract(new BigDecimal(cartDish.getPrice()), PriceUtil
												.multiply(new BigDecimal(cartDish.getPrice()),
														PriceUtil.divide(new BigDecimal(String
																.valueOf(itemBean
																		.getDiscountValue())), new BigDecimal("100"))))
										.floatValue());

								Discount.getInstance().getDiscountAmountList()
										.add(new DiscountAmount("会员价", PriceUtil
												.multiply(new BigDecimal(cartDish
														.getDiscountAmount()), cartDish
														.getQuantity()).floatValue(), itemBean
												.getDiscountType(), itemBean
												.getDiscountValue(), cartDish));
							}
						}
					}
				}
			} else {
				//没有单品，全单折扣

				if (memberdata != null && memberdata.getSingleDiscountType().equals("D")) {
					//					if (!TextUtils.isEmpty(SyncDataProvider
					//							.getSyncCompanySetByConfigKey("MIX.SINGLE.DISCOUNT")) && SyncDataProvider
					//							.getSyncCompanySetByConfigKey("MIX.SINGLE.DISCOUNT")
					//							.equals("Y")) {
					//这个控制商品优惠是否加料也一起优惠？是getCost  还是getPrice
					totalPrice = PriceUtil.multiply(
							getDishTotalWithMix(), PriceUtil.divide(new BigDecimal(memberdata
									.getSingleDiscountValue()), new BigDecimal("100")));
					//					记录全单优惠的金额，后面使用优惠券需要用到
					Price.getInstance().setSyncmemberactivitydiscountamount(PriceUtil
							.subtract(getDishTotalWithMix(), totalPrice).floatValue());

					Discount.getInstance().getDiscountAmountList()
							.add(new DiscountAmount("会员折扣", Price.getInstance()
									.getSyncmemberactivitydiscountamount(), memberdata
									.getSingleDiscountType(), (int) Float.parseFloat(memberdata
									.getSingleDiscountValue()), null));

					//					} else {
					//						totalPrice = PriceUtil.multiply(
					//								getDishTotalWithOutMix(), PriceUtil.divide(new BigDecimal(memberdata
					//										.getSingleDiscountValue()), new BigDecimal("100")));
					//						Price.getInstance().setSyncmemberactivitydiscountamount(PriceUtil
					//								.subtract(getDishTotalWithOutMix(), totalPrice).floatValue());
					//						totalPrice = PriceUtil.add(totalPrice, PriceUtil
					//								.subtract(getDishTotalWithMix(), getDishTotalWithOutMix()));
					//					}
				}
			}
		}
		return totalPrice;
	}


	public float getCouponValue() {
		return couponValue;
	}

	public void setCouponValue(float couponValue) {
		this.couponValue = couponValue;
	}

	public String getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}

	public boolean needMemberPay() {
		return (Price.getInstance().getCouponValue() > 0 && !Order.getInstance().getPayList()
				.contains(PayMethod.SYNCCOUPON)) ||
				(Price.getInstance().getPointValue() > 0 && !Order.getInstance().getPayList()
						.contains(PayMethod.SYNCPOINT)) || (Price.getInstance()
				.getBalance() > 0 && !Order.getInstance().getPayList()
				.contains(PayMethod.SYNCBALANCE));
	}


	/**
	 * @return 某个单品的实际价格 +做法价格+套餐项价格
	 */
	public String getDishCost(UIDish dish) {
		BigDecimal bigDecimal = new BigDecimal(0);
		bigDecimal = bigDecimal.add(Price.getInstance().getOptionPrice(dish));
		bigDecimal = bigDecimal.add(Price.getInstance().getSubitemPrice(dish));
		bigDecimal = new BigDecimal(Price.getInstance().getCurrentPrice(dish)).add(bigDecimal);
		return bigDecimal.toString();
	}

	/**
	 * 获取商品的总价，不包括加料的价格
	 * todo 这里有可能漏掉了套餐里面单品的价格
	 *
	 * @return
	 */
	public BigDecimal getDishTotalWithOutMix() {
		BigDecimal totalPrice = new BigDecimal("0");
		for (CartDish cartDish : Cart.getInstance().getCartDishes()) {
			totalPrice = PriceUtil.add(totalPrice, PriceUtil
					.multiply(new BigDecimal(cartDish.getPrice()), cartDish.getQuantity()));
		}
		return totalPrice;
	}

	/**
	 * 获取商品的总价，包括加料的价格
	 *
	 * @return
	 */
	public BigDecimal getDishTotalWithMix() {
		BigDecimal totalPrice = new BigDecimal("0");
		for (CartDish cartDish : Cart.getInstance().getCartDishes()) {
			totalPrice = PriceUtil.add(totalPrice, PriceUtil
					.multiply(new BigDecimal(cartDish.getCost()), cartDish.getQuantity()));
		}
		return totalPrice;
	}

	public float getSyncmemberactivitydiscountamount() {
		return syncmemberactivitydiscountamount;
	}

	public void setSyncmemberactivitydiscountamount(float syncmemberactivitydiscountamount) {
		this.syncmemberactivitydiscountamount = syncmemberactivitydiscountamount;
	}

	private List<WshAccountCoupon> mWshAccountCouponList;

	public List<WshAccountCoupon> getWshAccountCouponList() {
		return mWshAccountCouponList;
	}

	public void setWshAccountCouponList(List<WshAccountCoupon> wshAccountCouponList) {
		mWshAccountCouponList = wshAccountCouponList;
	}

	/**
	 * 获取会员日活动的总的优惠金额,不包含积分
	 *
	 * @return
	 */
	public float getTotalDiscountAmount() {
		if (Price.getInstance().getSyncmemberactivitydiscountamount() != 0) {
			return Price.getInstance().getSyncmemberactivitydiscountamount();
		}
		BigDecimal totalDiscountAmount = new BigDecimal("0");
		for (CartDish cartDish : Cart.getInstance().getCartDishes()) {
			totalDiscountAmount = PriceUtil
					.add(totalDiscountAmount, PriceUtil.multiply(new BigDecimal(String
							.valueOf(cartDish.getDiscountAmount())), cartDish.getQuantity()));
		}
		if (Price.getInstance().getActualCost() != null)
			totalDiscountAmount = PriceUtil
					.subtract(Price.getInstance().getDishTotalWithMix(), Price.getInstance()
							.getActualCost());
		if (totalDiscountAmount != null && totalDiscountAmount.floatValue() != 0) {
			return totalDiscountAmount.floatValue();
		}
		return 0;
	}

	public void clear() {
		Order.getInstance().clearFavor();
	}

	public SyncMemberLoginRes.DataBean.MemberCoupon getSyncCoupon() {
		return syncCoupon;
	}

	private SyncMemberLoginRes.DataBean.MemberCoupon syncCoupon;

	public void setSyncCoupon(SyncMemberLoginRes.DataBean.MemberCoupon coupon) {
		this.syncCoupon = coupon;
	}

	/**
	 * 存储券的code 和 券对应的值
	 */
	private Map<String, Float> dealsValueMap;

	public Map<String, Float> getDealsValueMap() {
		if (dealsValueMap == null)
			dealsValueMap = new HashMap<>();
		return dealsValueMap;
	}

	public void setDealsValueMap(Map<String, Float> dealsValueMap) {
		this.dealsValueMap = dealsValueMap;
	}

	public float getTotalWaidai_Cost() {
		BigDecimal totalWaidai = new BigDecimal("0");
		for (CartDish cartDish : Cart.getInstance().getCartDishes()) {
			totalWaidai = PriceUtil.add(totalWaidai, new BigDecimal(cartDish.getWaiDai_cost()));
		}
		return totalWaidai.floatValue();
	}

	private WshAccountCoupon mWshCoupon;//餐行健微生活代金券需要用到

	public void setWshCoupon(WshAccountCoupon coupon) {
		this.mWshCoupon = coupon;
	}


	public WshAccountCoupon getWshCoupon() {
		return mWshCoupon;
	}


	public String getActualCost() {
		return actualCost;
	}

	public void setActualCost(String actualCost) {
		this.actualCost = actualCost;
	}


	public BigDecimal getDisCountPrice() {
		if (disCountPrice == null)
			disCountPrice = new BigDecimal("0");
		return disCountPrice;
	}

	public void setDisCountPrice(BigDecimal disCountPrice) {
		this.disCountPrice = disCountPrice;
	}
}
