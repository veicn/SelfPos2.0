package com.acewill.slefpos.bean.paybean;

/**
 * Author：Anch
 * Date：2018/7/30 15:45
 * Desc：智慧快餐退款参数
 */
public class SmarantRefundReq {

	private String total_fee;
	private String paymentNo;
	private String paymentTypeId;

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	public String getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(String paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public String getReturnUserName() {
		return returnUserName;
	}

	public void setReturnUserName(String returnUserName) {
		this.returnUserName = returnUserName;
	}

	private String returnUserName;


}
