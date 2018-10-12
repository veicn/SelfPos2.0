package com.acewill.paylibrary;

import com.acewill.paylibrary.alipay.config.AlipayConfig;
import com.acewill.paylibrary.epay.AliBarPayAction;
import com.acewill.paylibrary.epay.EPayResult;
import com.acewill.paylibrary.epay.WeiXinScanPayAction;
import com.acewill.paylibrary.tencent.WXPay;
import com.acewill.slefpos.bean.paybean.PayMethod;

import java.math.BigDecimal;

public class MicropayTask extends EPayTask {

	@Override
	protected EPayResult doInBackground(PayReqModel... params) {
		PayReqModel payModel   = params[0];
		EPayResult  ePayResult = null;
		if (payModel.payType == PayMethod.ALI) {
			ePayResult = AliPay(payModel);

		} else if (payModel.payType == PayMethod.WECHAT) {
			ePayResult = weixinPay(payModel);
		}

		if (ePayResult == null) {
			System.out.println("pay nul");
			return null;
		}
		ePayResult.payType = EPayResult.SHUAKA;
		if (ePayResult.success == false) {
			System.out.println("pay false");
			return ePayResult;
		}
		//		System.err.println("pay query");
		//		if (payModel.payType == PayReqModel.PTID_SSS_ALI) {
		//			ePayResult = StartWaitingForPayCompletion(payModel);
		//		} else {
		//			ePayResult = rollbackTransaction(payModel);
		//		}
		return ePayResult;
	}


	private EPayResult AliPay(PayReqModel payModel) {
		EPayResult ePayResult;
		try {
			String totalAmount = payModel.totalAmount + "";
			if (payModel.isDebug) {
				totalAmount = "0.01";
			}
			ePayResult = AliBarPayAction.micropay(AlipayConfig.APPID,
					AlipayConfig.key, payModel.orderNo, payModel.authCode,
					totalAmount, payModel.store_name, payModel.aliGoodsItem, payModel.store_id, payModel.terminal_id);
			return ePayResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private EPayResult weixinPay(PayReqModel payModel) {
		String notify_url = "11111111";
		String trade_type = "NATIVE";
		String product_id = "0000";

		// System.out.println("weixin appid = " + WXPay.APPID + ",mchId = "
		// + WXPay.MCH_ID + ",order id = " + Common.cart.orderNo);
		int fenShouldPay = new BigDecimal(payModel.totalAmount).multiply(
				new BigDecimal(100)).intValue();

		if (payModel.isDebug) {
			fenShouldPay = 1;
		}

		String body = payModel.wxGoodsDetail;

		body = body.substring(0, Math.min(30, body.length()));

		EPayResult result = WeiXinScanPayAction.micropay(WXPay.APPID,
				WXPay.MCH_ID, WXPay.KEY, payModel.authCode, payModel.orderNo,
				fenShouldPay, body, WXPay.SUB_MCH_ID, product_id);
		return result;

	}
}
