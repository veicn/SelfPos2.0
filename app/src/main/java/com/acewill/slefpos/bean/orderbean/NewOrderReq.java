package com.acewill.slefpos.bean.orderbean;

import android.accounts.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Anch
 * Date：2018/5/8 9:46
 * Desc：
 */
public class NewOrderReq {
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	private String total;// " : 30.2,
	private String cost;// " : 10.2,
	private String source;// " : "点餐机1",
	private String discount;// " : 0.78, //折扣率
	private String comment;// " : "自动生成订单",
	private String createdBy;// " : "服务员1",
	private String customerAmount;// " : 2,
	private String subtraction;// " : 10, //全单减10块
	private String orderType;// " : "EAT_IN", 或者 " TAKE_OUT",
	private String paymentStatus;// " : "PAYED", 或者"NOT_PAYED", 如果是PAYED,
	// 那么需要带上paymentList.具体内容看订单结账接口
	private String createdAt;//
	// 那么需要带上paymentList.具体内容看订单结账接口
	private List<Long> tableIds = new ArrayList<Long>();
	private long            tableId;
	private List<OrderDish> itemList;
	private List<Payment>   paymentList;
	private String          paidAt;
	private long            id;
	private String          memberid; //对应微生活账号中的uid字段
	private String          memberName;//对应微生活账号中的name字段
	private String          memberPhoneNumber;//对应微生活账号中的phone字段
	private String          memberBizId;// String类型  微生活业务id
	private String          memberGrade;//会员等级名
	private String          businessId;
	private String          callNumber;
	/**
	 * 微生活结账会员对象
	 */
	private Account         accountMember;

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCustomerAmount() {
		return customerAmount;
	}

	public void setCustomerAmount(String customerAmount) {
		this.customerAmount = customerAmount;
	}

	public String getSubtraction() {
		return subtraction;
	}

	public void setSubtraction(String subtraction) {
		this.subtraction = subtraction;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public List<Long> getTableIds() {
		return tableIds;
	}

	public void setTableIds(List<Long> tableIds) {
		this.tableIds = tableIds;
	}

	public long getTableId() {
		return tableId;
	}

	public void setTableId(long tableId) {
		this.tableId = tableId;
	}

	public List<OrderDish> getItemList() {
		return itemList;
	}

	public void setItemList(List<OrderDish> itemList) {
		this.itemList = itemList;
	}

	public List<Payment> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}

	public String getPaidAt() {
		return paidAt;
	}

	public void setPaidAt(String paidAt) {
		this.paidAt = paidAt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPhoneNumber() {
		return memberPhoneNumber;
	}

	public void setMemberPhoneNumber(String memberPhoneNumber) {
		this.memberPhoneNumber = memberPhoneNumber;
	}

	public String getMemberBizId() {
		return memberBizId;
	}

	public void setMemberBizId(String memberBizId) {
		this.memberBizId = memberBizId;
	}

	public String getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getCallNumber() {
		return callNumber;
	}

	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}

	public Account getAccountMember() {
		return accountMember;
	}

	public void setAccountMember(Account accountMember) {
		this.accountMember = accountMember;
	}
}