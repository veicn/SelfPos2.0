package com.acewill.slefpos.bean.meituanbean;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/7/24 16:33
 * Desc：
 */
public class ExecuteMeituanCodeResult {

	/**
	 * result : 0
	 * content : {"count":null,"couponBuyPrice":null,"couponEndTime":null,"dealBeginTime":null,"dealPrice":null,"minConsume":null,"couponCode":null,"dealId":41777850,"dealTitle":"kfpttest_zl5_02人餐","dealValue":0.11,"message":"","result":0,"dealMenu":null,"orderId":null,"couponCodes":["283733250559"],"poiid":"118746077","couponCancelStatus":null,"couponStatusDesc":null,"couponUseTime":null,"verifyAcct":null,"verifyType":null,"voucher":false}
	 * errmsg : 0
	 */

	private int result;
	private ContentBean content;
	private String      errmsg;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public ContentBean getContent() {
		return content;
	}

	public void setContent(ContentBean content) {
		this.content = content;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public static class ContentBean {
		/**
		 * count : null
		 * couponBuyPrice : null
		 * couponEndTime : null
		 * dealBeginTime : null
		 * dealPrice : null
		 * minConsume : null
		 * couponCode : null
		 * dealId : 41777850
		 * dealTitle : kfpttest_zl5_02人餐
		 * dealValue : 0.11
		 * message :
		 * result : 0
		 * dealMenu : null
		 * orderId : null
		 * couponCodes : ["283733250559"]
		 * poiid : 118746077
		 * couponCancelStatus : null
		 * couponStatusDesc : null
		 * couponUseTime : null
		 * verifyAcct : null
		 * verifyType : null
		 * voucher : false
		 */

		private Object count;
		private Object       couponBuyPrice;
		private Object       couponEndTime;
		private Object       dealBeginTime;
		private Object       dealPrice;
		private Object       minConsume;
		private Object       couponCode;
		private int          dealId;
		private String       dealTitle;
		private double       dealValue;
		private String       message;
		private int          result;
		private Object       dealMenu;
		private Object       orderId;
		private String       poiid;
		private Object       couponCancelStatus;
		private Object       couponStatusDesc;
		private Object       couponUseTime;
		private Object       verifyAcct;
		private Object       verifyType;
		private boolean      voucher;
		private List<String> couponCodes;

		public Object getCount() {
			return count;
		}

		public void setCount(Object count) {
			this.count = count;
		}

		public Object getCouponBuyPrice() {
			return couponBuyPrice;
		}

		public void setCouponBuyPrice(Object couponBuyPrice) {
			this.couponBuyPrice = couponBuyPrice;
		}

		public Object getCouponEndTime() {
			return couponEndTime;
		}

		public void setCouponEndTime(Object couponEndTime) {
			this.couponEndTime = couponEndTime;
		}

		public Object getDealBeginTime() {
			return dealBeginTime;
		}

		public void setDealBeginTime(Object dealBeginTime) {
			this.dealBeginTime = dealBeginTime;
		}

		public Object getDealPrice() {
			return dealPrice;
		}

		public void setDealPrice(Object dealPrice) {
			this.dealPrice = dealPrice;
		}

		public Object getMinConsume() {
			return minConsume;
		}

		public void setMinConsume(Object minConsume) {
			this.minConsume = minConsume;
		}

		public Object getCouponCode() {
			return couponCode;
		}

		public void setCouponCode(Object couponCode) {
			this.couponCode = couponCode;
		}

		public int getDealId() {
			return dealId;
		}

		public void setDealId(int dealId) {
			this.dealId = dealId;
		}

		public String getDealTitle() {
			return dealTitle;
		}

		public void setDealTitle(String dealTitle) {
			this.dealTitle = dealTitle;
		}

		public double getDealValue() {
			return dealValue;
		}

		public void setDealValue(double dealValue) {
			this.dealValue = dealValue;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public int getResult() {
			return result;
		}

		public void setResult(int result) {
			this.result = result;
		}

		public Object getDealMenu() {
			return dealMenu;
		}

		public void setDealMenu(Object dealMenu) {
			this.dealMenu = dealMenu;
		}

		public Object getOrderId() {
			return orderId;
		}

		public void setOrderId(Object orderId) {
			this.orderId = orderId;
		}

		public String getPoiid() {
			return poiid;
		}

		public void setPoiid(String poiid) {
			this.poiid = poiid;
		}

		public Object getCouponCancelStatus() {
			return couponCancelStatus;
		}

		public void setCouponCancelStatus(Object couponCancelStatus) {
			this.couponCancelStatus = couponCancelStatus;
		}

		public Object getCouponStatusDesc() {
			return couponStatusDesc;
		}

		public void setCouponStatusDesc(Object couponStatusDesc) {
			this.couponStatusDesc = couponStatusDesc;
		}

		public Object getCouponUseTime() {
			return couponUseTime;
		}

		public void setCouponUseTime(Object couponUseTime) {
			this.couponUseTime = couponUseTime;
		}

		public Object getVerifyAcct() {
			return verifyAcct;
		}

		public void setVerifyAcct(Object verifyAcct) {
			this.verifyAcct = verifyAcct;
		}

		public Object getVerifyType() {
			return verifyType;
		}

		public void setVerifyType(Object verifyType) {
			this.verifyType = verifyType;
		}

		public boolean isVoucher() {
			return voucher;
		}

		public void setVoucher(boolean voucher) {
			this.voucher = voucher;
		}

		public List<String> getCouponCodes() {
			return couponCodes;
		}

		public void setCouponCodes(List<String> couponCodes) {
			this.couponCodes = couponCodes;
		}
	}
}
