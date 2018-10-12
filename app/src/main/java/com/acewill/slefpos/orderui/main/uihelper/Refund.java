package com.acewill.slefpos.orderui.main.uihelper;

import com.acewill.slefpos.base.BaseApplication;
import com.acewill.slefpos.bean.cartbean.Price;
import com.acewill.slefpos.bean.paybean.PayMethod;
import com.acewill.slefpos.bean.paybean.SmarantRefundReq;
import com.acewill.slefpos.bean.paybean.SyncRevokeReq;
import com.acewill.slefpos.bean.syncbean.syncorder.SyncAcceptReq;
import com.acewill.slefpos.configure.SyncConfig;
import com.acewill.slefpos.orderui.main.presenter.OrderPresenter;
import com.jaydenxiao.common.baserx.RxManager;
import com.jaydenxiao.common.commonutils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Anch
 * Date：2018/7/11 9:10
 * Desc：
 */
public class Refund {

	private static Refund    mHelper;
	private static RxManager mRxManager;

	/**
	 * 退款
	 *
	 * @return
	 */
	public static Refund getInstance() {
		if (mHelper == null) {
			mHelper = new Refund();
			mRxManager = new RxManager();
		}
		return mHelper;
	}

	List<SyncAcceptReq.DataBean.PromosBean> promosList = null;

	public List<SyncAcceptReq.DataBean.PromosBean> getPromoBeanList() {
		if (promosList == null) {
			promosList = new ArrayList<>();
		}
		return promosList;
	}

	/**
	 * 退款
	 *
	 * @param presenter
	 */
	public static void refund(OrderPresenter presenter) {
	}

	public void createPromoBean(String type) {
		if ("O".equals(type) && Price.getInstance().getPointValue() > 0) {
			SyncAcceptReq.DataBean.PromosBean bean = new SyncAcceptReq.DataBean.PromosBean();
			bean.setPromoValue(String.valueOf(Price.getInstance().getPointValue()));
			bean.setPromoType("O");//積分抵現
			bean.setPromoName("积分抵现");
			bean.setPromoAmount(String
					.valueOf(Price.getInstance().getPointValue()));
			getPromoBeanList().add(bean);
		} else if ("C".equals(type) && Price.getInstance().getCouponValue() > 0) {
			SyncAcceptReq.DataBean.PromosBean bean = new SyncAcceptReq.DataBean.PromosBean();
			bean.setPromoType("C");//代金券
			bean.setPromoName(Price.getInstance().getSyncCoupon().getCouponName());
			bean.setPromoValue(String.valueOf(Price.getInstance().getCouponValue()));
			bean.setPromoAmount(String.valueOf(Price.getInstance().getCouponValue()));
			bean.setReferenceNo(Price.getInstance().getCouponNo());
			getPromoBeanList().add(bean);
		}
	}

	public void clear() {
		getPromoBeanList().clear();
		getSyncRevokeParam().clear();
	}

	public List<SyncRevokeReq>    list;
	public List<SmarantRefundReq> list2;

	public void addSyncRevokeParam(String payMode, String payPlatform, String outTradeNo) {
		SyncRevokeReq             req  = new SyncRevokeReq();
		SyncRevokeReq.ContentBean bean = new SyncRevokeReq.ContentBean();
		bean.setCompanyOuid(SPUtils
				.getSharedStringData(BaseApplication.getContext(), "companyOuid"));
		bean.setShopId(SPUtils.getSharedStringData(BaseApplication.getContext(), "shopId"));
		bean.setDeviceId(SPUtils.getSharedStringData(BaseApplication.getContext(), "posId"));
		bean.setPayMode(payMode);
		bean.setPayPlatform(payPlatform);
		bean.setOutTradeNo(outTradeNo);
		req.setTimestamp(System.currentTimeMillis());
		req.setSign(SyncConfig.SIGN);
		req.setContent(bean);
		getSyncRevokeParam().add(req);
	}

	public void addSmarantRevokeParam(String total_fee, String paymentNo, String paymentTypeId) {
		boolean hasWsh = false;
		for (SmarantRefundReq refundReq : getSmarantRevokeParam()) {
			if (String.valueOf(PayMethod.YOUHUIQUAN).equals(refundReq.getPaymentTypeId()) || String
					.valueOf(PayMethod.JIFEN).equals(refundReq.getPaymentTypeId()) ||
					String.valueOf(PayMethod.CHUZHI).equals(refundReq.getPaymentTypeId())) {
				hasWsh = true;
			}
		}
		if (hasWsh && (String.valueOf(PayMethod.YOUHUIQUAN).equals(paymentTypeId) || String
				.valueOf(PayMethod.JIFEN).equals(paymentTypeId) ||
				String.valueOf(PayMethod.CHUZHI).equals(paymentTypeId))) {
			return;
		}
		SmarantRefundReq refundReq = new SmarantRefundReq();
		refundReq.setPaymentNo(paymentNo);
		refundReq.setTotal_fee(total_fee);
		refundReq.setPaymentTypeId(paymentTypeId);
		getSmarantRevokeParam().add(refundReq);
	}

	public List<SmarantRefundReq> getSmarantRevokeParam() {
		if (list2 == null)
			list2 = new ArrayList<>();
		return list2;
	}

	public List<SyncRevokeReq> getSyncRevokeParam() {
		if (list == null)
			list = new ArrayList<>();
		return list;
	}
}
