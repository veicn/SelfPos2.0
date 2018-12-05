package com.acewill.slefpos.orderui.main.uihelper;

import android.content.Context;
import android.text.TextUtils;

import com.acewill.slefpos.bean.cartbean.Cart;
import com.acewill.slefpos.bean.cartbean.CartDish;
import com.acewill.slefpos.bean.cartbean.Price;
import com.acewill.slefpos.bean.orderbean.Order;
import com.acewill.slefpos.bean.wshbean.WshAccount;
import com.acewill.slefpos.bean.wshbean.WshAccountCoupon;
import com.acewill.slefpos.bean.wshbean.WshCreateDeal;
import com.acewill.slefpos.orderui.main.uidataprovider.WshDataProvider;
import com.acewill.slefpos.utils.priceutils.PriceUtil;
import com.jaydenxiao.common.commonutils.SPUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Author：Anch
 * Date：2017/6/6 11:25
 * Desc：
 */
public class MemberPayHelper {

	private static final String TAG = "MemberPayDialog";

	/**
	 * 会员支付的时候，创建会员支付的Request
	 *
	 * @param context
	 * @param account
	 * @param balanceStr
	 * @param coupons
	 * @param creditStr
	 * @param money
	 */
	public static WshCreateDeal.Request creatDeal(Context context, WshAccount account, String balanceStr, List<WshAccountCoupon> coupons, String creditStr, BigDecimal money) {
		boolean                     isCanCreat1  = false;
		boolean                     isCanCreat2  = false;
		boolean                     isCanCreat3  = false;
		boolean                     isCanCreat4  = false;
		int                         consumAmount = 0;//消费金额/分
		final WshCreateDeal.Request request      = new WshCreateDeal.Request();
		request.setBiz_id(Order.getInstance().getBiz_id());
		request.setConsume_amount(0);//分
		request.setCount_num(1);
		request.setPayment_amount(0);
		request.setPayment_mode(1);
		request.setSub_balance(0);
		request.setSub_credit(0);
		request.setRemark("消费预览");
		if (!TextUtils.isEmpty(SPUtils.getSharedStringData(context, "tempMemberAccount")))
			request.setCno(SPUtils.getSharedStringData(context, "tempMemberAccount"));
		else {
			request.setCno(account.getUno());
		}
		request.setUid(account.getUid());


		//储值
		try {
			if (!TextUtils.isEmpty(balanceStr)) {
				isCanCreat1 = true;
				isCanCreat4 = true;
				int balance = Math.round(Float.parseFloat(balanceStr) * 100);
				consumAmount += balance;
				request.setSub_balance(balance);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return request;
		}

		//优惠券和礼品券,简单的做就是只用了一张优惠券
		if (coupons != null && coupons.size() > 0) {
			isCanCreat2 = true;
			isCanCreat4 = true;
			//			int          moneycoupons = 0;//菜品券金额
			List<String>                dinos     = new ArrayList<String>();
			List<String>                giftdinos = new ArrayList<String>();
			List<WshCreateDeal.Pruduct> products  = new ArrayList<>();
			for (WshAccountCoupon coupon : coupons) {
				if (coupon.getType() == 1) {
					consumAmount += coupon.getDeno() * 100;
					dinos.add(coupon.getCoupon_ids().get(0));
					request.setDeno_coupon_ids(dinos);
				} else if (coupon.getType() == 2) {
					int balance = Math.round(Float.parseFloat(coupon.getDishPirce()) * 100);
					consumAmount += balance;
					//					createPayment("", String
					//							.valueOf(coupon
					//									.getDishPirce()), PayReqModel.PTID_SSS_MEMBER_CAIPINQUAN, "会员礼品券");
					giftdinos.add(coupon.getCoupon_ids().get(0));
					//处理菜品参数
					//					DishModel orderItem = Cart.getInstance()
					//							.getCartDishById(String.valueOf(coupon.getProducts().get(0)));
					CartDish cartDish = Cart.getInstance()
							.getCartDishById(String.valueOf(coupon.getProducts().get(0)));
					WshCreateDeal.Pruduct pruduct = new WshCreateDeal.Pruduct();
					pruduct.name = cartDish.getDishName();
					pruduct.no = cartDish.getDishID();
					pruduct.num = cartDish.getQuantity();
					pruduct.price = PriceUtil
							.multiply(new BigDecimal(String
									.valueOf(cartDish.getCost())), new BigDecimal(100))
							.intValue();
					pruduct.is_activity = 1;//不参加活动
					products.add(pruduct);
				}
			}
			request.setProducts(products);
			request.setGift_coupons_ids(giftdinos);
		}


		//积分
		try {
			if (!TextUtils.isEmpty(creditStr)) {
				isCanCreat3 = true;
				isCanCreat4 = true;
				//				createPayment("", String
				//						.valueOf(creditStr), PayReqModel.PTID_SSS_MEMBER_JIFEN, "会员积分");
				consumAmount += Integer.parseInt(creditStr) * 100;
				request.setSub_credit(Integer.parseInt(creditStr));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return request;
		}

		if (isCanCreat4) {
			request.setConsume_amount(money.multiply(new BigDecimal("100")).intValue());
			BigDecimal paymentAmount = money.multiply(new BigDecimal("100"))
					.subtract(new BigDecimal(consumAmount));
			if (paymentAmount.compareTo(BigDecimal.ZERO) != 1) {
				paymentAmount = new BigDecimal("0");
			}
			request.setPayment_amount(paymentAmount.intValue());
		} else if (isCanCreat1 == false && isCanCreat2 == false && isCanCreat3 == false) {

			request.setPayment_amount(money.multiply(new BigDecimal("100")).intValue());
			request.setConsume_amount(money.multiply(new BigDecimal("100")).intValue());
		}

		if (isCanCreat1 == false && isCanCreat2 == false && isCanCreat3 == false || isCanCreat4) {
			return request;
			//			try {
			//				NewPos.getInstance(context).createDeal(request, new StringCallback() {
			//					@Override
			//					public void onError(okhttp3.Call call, Exception e, int id) {
			//
			//						//						payOrderView.createDealFail(mContext.getResources()
			//						//								.getString(R.string.net_error));
			//						paymentList.clear();
			//						e.printStackTrace();
			//					}
			//
			//					@Override
			//					public void onResponse(String response, int id) {
			//
			//						//判断这个价格有没有比购物车价格要高，如果高于购物车的价格，那么就相当于已经付清了，结账完毕,直接下单
			//						try {
			//							if (GsonUtils.getIntData("result", response) == 0) {
			//								//会员支付 这里有一个判断是否需要校验密码的
			//								//								MemberPayResultBean bean = GsonUtils
			//								//										.getSingleBean(response, MemberPayResultBean.class);
			//								//会员支付 这里有一个判断是否需要校验密码的
			//								if (bean.getContent().isVerify_password() && TextUtils
			//										.isEmpty(SharedPreferencesUtil
			//												.getTempMemberPassword(mContext))) {
			//									//									payOrderView.dismissProgress();
			//									showInputDialog(1, -1);
			//								} else if (bean.getContent()
			//										.isVerify_sms() && TextUtils.isEmpty(SharedPreferencesUtil
			//										.getTempMemberPassword(mContext))) {
			//									//									payOrderView.dismissProgress();
			//									showInputDialog(0, -1);
			//								} else {
			//									//									payOrderView.createDealSuccess(paymentList);
			//								}
			//							} else {
			//								//								payOrderView.createDealFail(GsonUtils.getErrMsg(response));
			//								paymentList.clear();
			//							}
			//						} catch (Exception e) {
			//							FileLog
			//									.log(Common.Error, MemberPayHelper.class, "creatDeal", "创建预交易信息失败", e
			//											.getMessage());
			//							paymentList.clear();
			//							//							payOrderView.createDealFail(mContext.getResources()
			//							//									.getString(R.string.code_error));
			//							e.printStackTrace();
			//						}
			//
			//						//低于购物车的价格，要用微信或者支付宝支付
			//
			//						//成功之后，购物车的价格要减去这个价格
			//					}
			//				});
			//			} catch (Exception e) {
			//				FileLog
			//						.log(Common.Error, MemberPayHelper.class, "creatDeal", "创建预交易信息失败", e
			//								.getMessage());
			//				paymentList.clear();
			//				//				payOrderView.createDealFail(context.getResources()
			//				//						.getString(R.string.code_error));
			//				e.printStackTrace();
			//			}
			//		} else {
			//			paymentList.clear();
			//			//			payOrderView.createDealFail("请选择一种有效的支付方式!");
		}
		return null;
	}


	public static List<WshAccountCoupon> getCoupons() {
		if (!Order.getInstance().isMember() || WshDataProvider.getWshAccount() == null)
			return new ArrayList<>();
		ArrayList<WshAccountCoupon> allCoupons       = new ArrayList<>();
		ArrayList<WshAccountCoupon> canUseCoupons    = new ArrayList<>();
		ArrayList<WshAccountCoupon> cannotUseCoupons = new ArrayList<>();

		List<WshAccountCoupon> beanList = new ArrayList<>();
		WshAccount             account  = WshDataProvider.getWshAccount();
		beanList = account.getCoupons();
		//将代金券和礼品券（包括菜品券分离出来）
		for (WshAccountCoupon coupon : beanList) {
			if (coupon.getType() == 1) {
				if (coupon.getEnable_amount() != null && coupon.getEnable_amount()
						.compareTo(BigDecimal.ZERO) == 1 && coupon.getEnable_amount()
						.compareTo(new BigDecimal("0")) == 1) {
					coupon.setCanUse(false);
					cannotUseCoupons.add(coupon);
				} else {
					coupon.setCanUse(true);
					canUseCoupons.add(coupon);
				}
			} else if (coupon.getType() == 2) {
				if (isCaipinquanCanuse(coupon)) {
					CartDish dishModel = Cart.getInstance()
							.getCartDishById(String.valueOf(coupon.getProducts().get(0)));
					coupon.setDishPirce(dishModel.getCost());
					coupon.setCanUse(true);
					canUseCoupons.add(coupon);
				} else {
					coupon.setCanUse(false);
					cannotUseCoupons.add(coupon);
				}
			} else {
				coupon.setCanUse(false);
				cannotUseCoupons.add(coupon);
			}

		}
		allCoupons.addAll(canUseCoupons);
		allCoupons.addAll(cannotUseCoupons);
		return allCoupons;
	}


	/**
	 * 获取可以使用的菜品券
	 *
	 * @param coupon
	 * @return
	 */
	public static boolean isCaipinquanCanuse(WshAccountCoupon coupon) {
		if (coupon.getType() == 2 && coupon.getProducts() != null && coupon.getProducts()
				.size() > 0) {
			for (CartDish model : Cart.getInstance().getCartDishes()) {
				if (String
						.valueOf(coupon.getProducts().get(0))
						.equals(model.getDishID()) && PriceUtil
						.subtract(Price.getInstance().getDishTotalWithMix(), coupon
								.getEnable_amount())
						.floatValue() >= 0) {
					coupon.setDishPirce(model.getCost());
					return true;
				}
			}
		}
		return false;
	}

}