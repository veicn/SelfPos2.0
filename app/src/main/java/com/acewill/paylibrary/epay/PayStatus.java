package com.acewill.paylibrary.epay;

/**
 * Author：Anch
 * Date：2017/5/31 11:24
 * Desc：
 */
public class PayStatus {
	public String getSetPaySuccessOutTradeNo() {
		return setPaySuccessOutTradeNo;
	}

	public void setSetPaySuccessOutTradeNo(String setPaySuccessOutTradeNo) {
		this.setPaySuccessOutTradeNo = setPaySuccessOutTradeNo;
	}

	private String setPaySuccessOutTradeNo;

	public String getPayStatu() {
		return payStatu;
	}

	public void setPayStatu(String payStatu) {
		this.payStatu = payStatu;
	}

	private String payStatu;
	private String errmsg;

	public void setErrmsg(String des) {
		this.errmsg = des;
	}

	public String getErrmsg() {
		return errmsg;
	}
}
