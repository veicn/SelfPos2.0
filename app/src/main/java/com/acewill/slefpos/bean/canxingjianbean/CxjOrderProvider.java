package com.acewill.slefpos.bean.canxingjianbean;

import android.text.TextUtils;
import android.util.Log;

import com.acewill.slefpos.base.BaseApplication;
import com.acewill.slefpos.bean.cartbean.Cart;
import com.acewill.slefpos.bean.cartbean.CartDish;
import com.acewill.slefpos.bean.cartbean.Price;
import com.acewill.slefpos.bean.orderbean.Order;
import com.acewill.slefpos.bean.orderbean.Payment;
import com.acewill.slefpos.bean.paybean.PayMethod;
import com.acewill.slefpos.bean.uibean.UIPackageOptionItem;
import com.acewill.slefpos.bean.uibean.UITasteOption;
import com.acewill.slefpos.bean.wshbean.CXJWshCreateDeal;
import com.acewill.slefpos.bean.wshbean.WshAccount;
import com.acewill.slefpos.bean.wshbean.WshAccountCoupon;
import com.acewill.slefpos.bean.wshbean.WshActivity;
import com.acewill.slefpos.orderui.main.uidataprovider.WshDataProvider;
import com.google.gson.Gson;
import com.jaydenxiao.common.commonutils.SPUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Author：Anch
 * Date：2018/8/1 14:17
 * Desc：
 */
public class CxjOrderProvider {
	private static final String TAG = "CxjOrderProvider";
	private static CxjOrderProvider mProvider;
	private        boolean          first;
	private List<CXJNewOrderReq.SetmealsBean>    setmealsBeanList    = new ArrayList<>();
	private List<CXJNewOrderReq.NormalitemsBean> normalitemsBeenList = new ArrayList<>();
	private List<CxjCheckOutBean.CXJPayInfo>     mPayInfoList        = new ArrayList<>();

	public static CxjOrderProvider getInstance() {
		if (mProvider == null)
			mProvider = new CxjOrderProvider();
		return mProvider;
	}

	private CxjCheckOutBean checkBean;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");// 用于格式化日期,作为日志文件名的一部分

	public CxjCheckOutBean getCheckBean() {
		return checkBean;
	}

	private int oid;//餐行健oid ，保存在这里是为了在查询支付的结果的时候使用到

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	private long orderIdentity;

	public CxjCheckOutBean setCheackOutBean(String payCode) {
		mPayInfoList.clear();
		checkBean = new CxjCheckOutBean();
		checkBean.setAccessid("");
		checkBean.setOrderidentity(getOrderIdentity());
		checkBean.setMid(-1);
		checkBean.setUsername("1");
		checkBean.setPwd("1");
		checkBean.setPeople("1");
		checkBean.setTid(1);
		checkBean.setTids(1);
		checkBean.setCtid("");
		checkBean.setSaleuid(1);
		checkBean.setOid(getOid());
		checkBean.setAlldiscount(0);
		checkBean.setBid("");
		checkBean.setOmids("");
		checkBean.setFreememo("");
		for (int i = 0; i < Order.getInstance().getPaymentList().size(); i++) {
			Payment payment = Order.getInstance().getPaymentList()
					.get(i);
			CxjCheckOutBean.CXJPayInfo            info        = new CxjCheckOutBean.CXJPayInfo();
			ArrayList<CxjCheckOutBean.CouponInfo> couponInfos = new ArrayList<>();
			info.setPaytype(payment.getPaymentTypeName());
			info.setPay(Float.parseFloat(payment.getValue()));
			info.setCost(Float.parseFloat(payment.getValue()));
			if (Integer
					.parseInt(payment.getPaymentTypeId()) == PayMethod.CXJ_POINT || Integer
					.parseInt(payment.getPaymentTypeId()) == PayMethod.CXJ_BALANCE) {
				info.setKind("2");
			} else if (Integer.parseInt(payment.getPaymentTypeId()) == PayMethod.CXJ_COUPON) {
				info.setKind("4");
				CxjCheckOutBean.CouponInfo couponInfo = new CxjCheckOutBean.CouponInfo();
				WshAccountCoupon           useCoupon  = Price.getInstance().getWshCoupon();
				couponInfo.setId(useCoupon.getCoupon_ids().get(0));
				couponInfo.setTitle(useCoupon.getTitle());
				info.setPaytype("微生活代金券");
				couponInfo.setType(useCoupon.getType());
				info.setPay(useCoupon.getDeno());

				if (CxjOrderProvider.getInstance().getDiscountValue() > 0) {
					float cost = useCoupon.getDeno() - CxjOrderProvider
							.getInstance().getDiscountValue();
					info.setCost(cost);
				}
				couponInfo.setTypeName(useCoupon.getTypeName());
				couponInfo.setStarttime(useCoupon.getEffective_time());
				couponInfo.setEndtime(useCoupon.getFailure_time());
				couponInfo.setEnableAmount(useCoupon.getEnable_amount().floatValue());
				couponInfo.setMaxUse(useCoupon.getMax_use());
				couponInfo.setMixUse(useCoupon.isMix_use());
				couponInfo.setTemplateId(Integer.parseInt(useCoupon.getTemplate_id()));
				couponInfo.setCount(1);
				couponInfo.setChecked(true);
				couponInfo.setDeno(Price.getInstance().getCouponValue());
				if (useCoupon.getType() == 2) {
					//菜品券
					couponInfo.setDid(String.valueOf(useCoupon.getProducts().get(0)));
				} else if (useCoupon.getType() == 1) {
					//代金券
					couponInfo.setDid("");
				}

				couponInfo.setDisable(false);
				couponInfos.add(couponInfo);
			} else {
				info.setKind("1");
			}
			info.setStatus("1");
			info.setGive(0);

			info.setPtid(Integer.parseInt(payment.getPaymentTypeId()));
			info.setChecked(true);
			info.setCoupons(couponInfos);
			if (Order.getInstance().isMember()) {
				WshAccount account = WshDataProvider.getWshAccount();
				info.setCardcode(account.getCno());
				info.setCno(account.getCno());
				info.setType(account.getType());
				List<WshActivity> acts = account.getActs();
				if (acts != null && acts.size() > 0) {
					WshActivity activity = acts.get(0);
					info.setActivityid(activity.getMaid());
					info.setActivityname(activity.getName());
				} else {
					info.setActivityid("");
					info.setActivityname("");
				}
			} else {
				info.setCardcode("");
				info.setType("");
				info.setActivityid("");
				info.setActivityname("");
			}
			mPayInfoList.add(info);
		}

		if (CxjOrderProvider.getInstance().getDiscountValue() > 0) {
			CxjCheckOutBean.CXJPayInfo info = new CxjCheckOutBean.CXJPayInfo();
			info.setPaytype("赠券免找");
			info.setKind("1");
			info.setStatus("1");
			info.setGive(0);
			info.setPay(CxjOrderProvider.getInstance().getDiscountValue());
			info.setCost(CxjOrderProvider.getInstance().getDiscountValue());
			info.setPtid(PayMethod.CXJ_FREE_COUPON);
			info.setCoupons(new ArrayList<CxjCheckOutBean.CouponInfo>());
			info.setChecked(true);
			mPayInfoList.add(info);
		}

		checkBean.setRecieve(Price.getInstance().getDishTotalWithMix().floatValue());
		checkBean.setFreeitems(new Object[]{});
		checkBean.setNormalitems(normalitemsBeenList);
		checkBean.setSetmeals(setmealsBeanList);
		checkBean.setPayinfo(mPayInfoList);

		checkBean.setChange(0);
		checkBean.setAdvance(0);
		checkBean.setPtid(-1);
		checkBean.setAccountpid("");
		checkBean.setCardmbid("");
		if (Order.getInstance().isMember()) {
			checkBean.setCardcode(WshDataProvider.getWshAccount().getCno());
		} else {
			checkBean.setCardcode("");
		}
		checkBean.setPassword("");
		checkBean.setBsnack(1);
		checkBean.setOrdertype(1);
		checkBean.setPhone("");
		checkBean.setMobile("");
		checkBean.setAddress("");
		checkBean.setName("");
		checkBean.setDiningtime("");
		checkBean.setAlipaycode(payCode);
		if (!first) {
			checkBean.setSendm(SPUtils
					.getSharedStringData(BaseApplication.getContext(), "tempMemberPassword"));
		} else {
			checkBean.setSendm("");
		}
		first = false;
		if (Order.getInstance().isMember()) {
			checkBean.setWid(String.valueOf(SPUtils
					.getSharedIntData(BaseApplication.getContext(), "wid")));//这个值微生活预结的时候返回的
		} else {
			checkBean.setWid("");//这个值是哪里来的？？
		}
		checkBean.setIspaid(0);
		String s = new Gson().toJson(checkBean);
		Log.e(TAG, "S>>>" + s);
		return checkBean;
	}

	private float discountValue;

	public CXJWshCreateDeal.Request createWshYjSetBean() {
		CXJWshCreateDeal.Request request = new CXJWshCreateDeal.Request();
		WshAccount               account = WshDataProvider.getWshAccount();
		request.setOid("");
		request.setUid("-1");
		request.setType(account.getType());
		request.setCno(account.getCno());
		request.setCardid(account.getCno());
		request.setWid("");
		request.setActivityid("");
		request.setActivityname("");
		if (Price.getInstance().getCouponNo() != null && Price.getInstance()
				.getCouponValue() > 0)
			request.setCouponid(account.getCoupons().get(0).getCoupon_ids().get(0));
		else
			request.setCouponid("");
		return request;
	}

	public CXJNewOrderReq createCxjNewOrderBean() {
		normalitemsBeenList.clear();
		setmealsBeanList.clear();
		setOrderIdentity(System.currentTimeMillis());
		CXJNewOrderReq req = new CXJNewOrderReq();
		req.setPeople("1");
		req.setOmids("");
		req.setFreememo("");
		req.setTids(1);
		req.setCtid("");
		req.setNewuid("1");
		req.setBid("");
		req.setOrdertype(1);
		req.setMid(-1);
		req.setSaleuid("");
		req.setIdentity(System.currentTimeMillis());

		for (CartDish cartDish : Cart.getInstance().getCartDishes()) {
			if (cartDish.getIsPackage() == 0) {
				//不是套餐
				CXJNewOrderReq.NormalitemsBean normalitemsBean = new CXJNewOrderReq.NormalitemsBean();
				normalitemsBean.setDid(Integer.parseInt(cartDish.getDishID()));
				normalitemsBean.setDish(cartDish.getDishName());
				normalitemsBean.setDuid(cartDish.getDishUnitID());
				normalitemsBean.setAmount(cartDish.getQuantity());
				normalitemsBean
						.setPrice(Float.parseFloat(Order.getInstance().isMember() ? cartDish
								.getMemberPrice() : cartDish
								.getPrice()));
				normalitemsBean.setAssistduid("");
				normalitemsBean.setAssistamount("");
				normalitemsBean.setOmids("");
				normalitemsBean.setFreememo("");
				List<CXJNewOrderReq.CookInfo> cookInfoList = new ArrayList<>();
				if (cartDish.getOptionList() != null) {
					for (UITasteOption option : cartDish.getOptionList()) {
						for (int i = 0; i < option.getQuantity(); i++) {
							CXJNewOrderReq.CookInfo info = new CXJNewOrderReq.CookInfo();
							info.setCid(option.getId());
							info.setPrice(String.valueOf(option.getPrice()));
							info.setBcal(1);
							info.setSeat(1);
							cookInfoList.add(info);
						}
					}
				}
				normalitemsBean.setCookinfo(cookInfoList);
				normalitemsBean.setBgift(0);
				normalitemsBean.setGrid("");
				normalitemsBean.setPrint(1);
				normalitemsBean.setBwait(0);
				normalitemsBeenList.add(normalitemsBean);
			} else {
				CXJNewOrderReq.SetmealsBean setmealsBean =
						new CXJNewOrderReq.SetmealsBean();
				setmealsBean.setDid(Integer.parseInt(cartDish.getDishID()));
				setmealsBean.setDish(cartDish.getDishName());
				setmealsBean.setDuid(cartDish.getDishUnitID());
				setmealsBean.setAmount(cartDish.getQuantity());
				setmealsBean.setPrice(Float.parseFloat(cartDish.getPrice()));
				setmealsBean.setOmids("");
				setmealsBean.setCookinfo(new ArrayList<CXJNewOrderReq.CookInfo>());
				setmealsBean.setBgift(0);
				ArrayList<CXJNewOrderReq.SetmealsBean.ItemsBean> itemsBeenList = new ArrayList<>();
				for (UIPackageOptionItem optionItem : cartDish.getSubItemList()) {
					CXJNewOrderReq.SetmealsBean.ItemsBean itemsBean = new CXJNewOrderReq.SetmealsBean.ItemsBean();
					itemsBean.setDid(Integer.parseInt(optionItem.getDishID()));
					itemsBean.setDish(optionItem.getDishName());
					itemsBean.setDuid(Integer.parseInt(optionItem.getDishUnitId()));
					itemsBean.setAmount(optionItem.getQuantity());
					itemsBean.setPrice(Float.parseFloat(optionItem.getPrice()));
					itemsBean.setAssistduid("");
					itemsBean.setAssistamount("");
					itemsBean.setOmids("");
					itemsBean.setFreememo("");
					itemsBean.setCookinfo(new ArrayList<CXJNewOrderReq.CookInfo>());
					itemsBean.setBgift(0);
					itemsBean.setGrid("");
					itemsBean.setPrint(1);
					itemsBean.setBwait(0);
					itemsBeenList.add(itemsBean);
				}
				setmealsBean.setItems(itemsBeenList);
				setmealsBean.setGrid("");
				setmealsBean.setPrint(1);
				setmealsBean.setBwait(0);
				setmealsBeanList.add(setmealsBean);
			}
		}
		req.setNormalitems(normalitemsBeenList);
		req.setSetmeals(setmealsBeanList);
		req.setFreeitems(new ArrayList<>());
		req.setAccessid("");
		return req;
	}

	public long getOrderIdentity() {
		return orderIdentity;
	}

	public void setOrderIdentity(long orderIdentity) {
		this.orderIdentity = orderIdentity;
	}

	public CxjPrecheckBean createPrecheckBean() {
		CxjPrecheckBean bean = new CxjPrecheckBean();
		bean.setOid(getOid());
		bean.setPrechecktype(1);
		bean.setRate(1);
		bean.setServicerate(1);
		bean.setExtradiscount("");
		bean.setMaid("");
		bean.setBsave(1);
		bean.setBprint(0);
		bean.setMarketgiftcouponcode("");
		bean.setAuthcode("");
		bean.setMarketcouponcode("");
		bean.setMarketcardcodevalue(WshDataProvider.getWshAccount().getUno());
		bean.setUsenumberamount(new ArrayList<>());
		bean.setChainmaid("");
		bean.setOrdertype(1);
		bean.setPrecheckcardcode(WshDataProvider.getWshAccount().getUno());
		bean.setPrecheckmbid("");
		bean.setPhone("");
		bean.setDsid("");
		bean.setBservicediscount(1);
		return bean;
	}

	/**
	 * 将餐行健会员转成微生活会员
	 *
	 * @param cxjMemberAccount
	 * @return
	 */
	public WshAccount toWshAccount(CxjMemberAccount cxjMemberAccount) {
		WshAccount wshAccount = new WshAccount();
		wshAccount.setCno(cxjMemberAccount.getCno());
		wshAccount.setName(cxjMemberAccount.getName());
		wshAccount.setType(cxjMemberAccount.getType());
		wshAccount.setGradeName(cxjMemberAccount.getGradeName());
		wshAccount.setBalance(Float.parseFloat(cxjMemberAccount.getBalance()));
		wshAccount.setCredit(Integer.parseInt(cxjMemberAccount.getCredit()));
		List<WshAccountCoupon> couponList = new ArrayList<>();
		if (cxjMemberAccount.getCoupons() != null && cxjMemberAccount.getCoupons().size() > 0) {
			for (CxjMemberAccount.CxjCoupons cxjCoupons : cxjMemberAccount.getCoupons()) {
				boolean isSameCoupon = false;
				boolean isSame       = false;
				for (WshAccountCoupon coupon : couponList) {
					if (coupon.getTitle().equals(cxjCoupons.getName()) && coupon
							.getType() == cxjCoupons.getType()) {
						if (coupon.getCoupon_ids()
								.contains(cxjCoupons.getId())) {
							isSameCoupon = true;
							break;
						}
						isSame = true;
						coupon.getCoupon_ids().add(cxjCoupons.getId());
						break;
					}
				}
				if (isSameCoupon || isSame) {
					continue;
				}
				WshAccountCoupon wshAccountCoupon = new WshAccountCoupon();
				wshAccountCoupon.setTitle(cxjCoupons.getName());
				wshAccountCoupon.setCanUse(false);
				wshAccountCoupon.setType(cxjCoupons.getType());
				wshAccountCoupon.setTypeName(cxjCoupons.getTypeName());
				wshAccountCoupon.setEffective_time(cxjCoupons.getStarttime());
				wshAccountCoupon.setFailure_time(cxjCoupons.getEndtime());
				wshAccountCoupon.setEnable_amount(new BigDecimal(cxjCoupons.getEnableAmount()));
				wshAccountCoupon.setMax_use(Integer.parseInt(cxjCoupons.getMaxUse()));
				wshAccountCoupon.setMix_use(cxjCoupons.isMixUse());
				wshAccountCoupon.setTemplate_id(cxjCoupons.getTemplateId());
				wshAccountCoupon.setDeno(Float.parseFloat(cxjCoupons.getPrice()));
				if (!TextUtils.isEmpty(cxjCoupons.getDid())) {
					List<Long> longs = new ArrayList<>();
					longs.add(Long.parseLong(cxjCoupons.getDid()));
					wshAccountCoupon.setProducts(longs);
				}
				List<String> couponIds = new ArrayList<>();
				couponIds.add(cxjCoupons.getId());
				wshAccountCoupon.setCoupon_ids(couponIds);
				couponList.add(wshAccountCoupon);
			}
		}
		wshAccount.setCoupons(couponList);
		wshAccount.setActs(cxjMemberAccount.getActs());
		return wshAccount;
	}

	public float getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(float discountValue) {
		this.discountValue = discountValue;
	}
}
