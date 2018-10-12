package com.acewill.paylibrary.epay;

public class EPayResult {
	public final static String PAY_CODE_IN_PROGRESS = "10003";
	public final static String PAY_CODE_OK          = "10000";
	public final static String PAY_CODE_FALSE       = "40004";

	public final static String TRADE_SUCCESS   = "TRADE_SUCCESS";
	public final static String PAY_STATUS_WAIT = "WAIT_BUYER_PAY";
	public final static String PAYINPROGRESS   = "PAYINPROGRESS";
	public final static String PAYCLOSED       = "TRADE_CLOSE";
	public static final int    SAOMA           = 1;
	public static final int    SHUAKA          = 2;

	public boolean success      = false;
	public String  desc         = "";
	public String  code         = "";
	public String  trade_status = "";
	public String  qr_code      = "";
	// 微信扫码
	public String  code_url     = "";

	// 支付宝流水订单号
	public String trade_no     = "";
	public String total_amount = "";

	// 微信扫码支付 --- 支付状态 SUCCESS NOTPAY
	public int result;
	public String trade_state = "";
	public String touser      = "";
	public int payType;

	@Override
	public String toString() {
		return "EPayResult{" +
				"success=" + success +
				", desc='" + desc + '\'' +
				", code='" + code + '\'' +
				", trade_status='" + trade_status + '\'' +
				", qr_code='" + qr_code + '\'' +
				", code_url='" + code_url + '\'' +
				", trade_no='" + trade_no + '\'' +
				", total_amount='" + total_amount + '\'' +
				", result=" + result +
				", trade_state='" + trade_state + '\'' +
				", touser='" + touser + '\'' +
				", payType='" + payType + '\'' +
				'}';
	}

}
