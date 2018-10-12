package com.acewill.slefpos.bean.paybean;

/**
 * Author：Anch
 * Date：2018/5/5 17:06
 * Desc：
 */
public class MeiTuanPayResult {
	/**
	 * status : FAIL
	 * errCode : TRADE_PAY_UNKOWN_ERROR
	 * errMsg : 用户支付中
	 * subCode : null
	 * subMsg : null
	 * random : lnwVIiXBSrjLcXxsnyWkaeCxSYzbsBjT
	 * sign : 6af861e2ab8e25465631bc53c46dff854b96ff5b624f3f6306af2cbb14b2b88c
	 * tradeNo : null
	 * transactionId : null
	 * outTradeNo : null
	 * totalFee : 0
	 * payTime : 0
	 * buyerId : null
	 * subBuyerId : null
	 * referno : null
	 * orderStatus : null
	 * tradeState : null
	 */

	private String status;
	private String errCode;
	private String errMsg;
	private Object subCode;
	private Object subMsg;
	private String random;
	private String sign;
	private String tradeNo;
	private String transactionId;
	private String outTradeNo;
	private int    totalFee;
	private long   payTime;
	private String buyerId;
	private String subBuyerId;
	private String referno;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Object getSubCode() {
		return subCode;
	}

	public void setSubCode(Object subCode) {
		this.subCode = subCode;
	}

	public Object getSubMsg() {
		return subMsg;
	}

	public void setSubMsg(Object subMsg) {
		this.subMsg = subMsg;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public int getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}

	public long getPayTime() {
		return payTime;
	}

	public void setPayTime(long payTime) {
		this.payTime = payTime;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getSubBuyerId() {
		return subBuyerId;
	}

	public void setSubBuyerId(String subBuyerId) {
		this.subBuyerId = subBuyerId;
	}

	public String getReferno() {
		return referno;
	}

	public void setReferno(String referno) {
		this.referno = referno;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}

	private String orderStatus;
	private String tradeState;

}
