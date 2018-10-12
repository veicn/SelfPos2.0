package com.acewill.slefpos.bean.paybean;

/**
 * Author：Anch
 * Date：2018/5/4 11:44
 * Desc：支付时所需要的参数都在这里
 */
public class PayModel {
	private String totalAmount;//订单总金额
	private String outTradeNo;//订单流水号
	private String authCode;//授权码(刷卡得到的码)
	private String store_name;//门店名称(支付宝需要)
	private String paymentStr;//支付参数(支付宝需要)
	private String subject;//订单主题(美团需要)
	private String body;//订单内容(美团需要)
	private String payType;//根据支付类型生成二维码(美团需要)
	private String wxGoodsDetail;//根据支付类型生成二维码(美团需要)
	private String timeStart;
	private String timeExpire;
	private String deviceInfo;


	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public short getExpireMinutes() {
		return expireMinutes;
	}

	public void setExpireMinutes(short expireMinutes) {
		this.expireMinutes = expireMinutes;
	}

	private short expireMinutes;//订单关闭时间(美团需要)


	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}


	public String getPaymentStr() {
		return paymentStr;
	}

	public void setPaymentStr(String paymentStr) {
		this.paymentStr = paymentStr;
	}

	public String getWxGoodsDetail() {
		return wxGoodsDetail;
	}

	public void setWxGoodsDetail(String wxGoodsDetail) {
		this.wxGoodsDetail = wxGoodsDetail;
	}

	public String getTimeExpire() {
		return timeExpire;
	}

	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
}
