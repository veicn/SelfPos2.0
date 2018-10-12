package com.acewill.slefpos.bean.paybean;

/**
 * Author：Anch
 * Date：2018/7/12 10:06
 * Desc：
 */
public class SyncRefundRes {


	/**
	 * sign : qtM74y8KQvaCaQ7gLrLAjA
	 * timestamp : 1526463196990
	 * content : {"tradeId":"LNt_nWQqT2imaqliQ8V_kQ","shopId":"p6SsGrr-S4iSYA11xmuFdA","deviceId":"0412","servicePlatformType":"X","payMode":"O","payPlatform":"zaofans","outTradeNo":"12F9qHZxPqxg98XcBLFgh9","txNo":"28051404121805310004","bizNo":"3cogGIVeT96yxKl37YZ1fw","tradeNo":"201806011033043241821079705","totalAmount":800,"shopActualAmount":0,"shopDiscountAmount":0,"userActualAmonut":0,"userDiscountAmount":0,"body":"R&B巡茶（双湖广场店）","tradeAction":"1","tradeStatus":"5","tradeTime":20180601103303,"tradeEndTime":20180601103603}
	 */

	private String sign;
	private long        timestamp;
	private ContentBean content;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public ContentBean getContent() {
		return content;
	}

	public void setContent(ContentBean content) {
		this.content = content;
	}

	public static class ContentBean {
		/**
		 * tradeId : LNt_nWQqT2imaqliQ8V_kQ
		 * shopId : p6SsGrr-S4iSYA11xmuFdA
		 * deviceId : 0412
		 * servicePlatformType : X
		 * payMode : O
		 * payPlatform : zaofans
		 * outTradeNo : 12F9qHZxPqxg98XcBLFgh9
		 * txNo : 28051404121805310004
		 * bizNo : 3cogGIVeT96yxKl37YZ1fw
		 * tradeNo : 201806011033043241821079705
		 * totalAmount : 800
		 * shopActualAmount : 0
		 * shopDiscountAmount : 0
		 * userActualAmonut : 0
		 * userDiscountAmount : 0
		 * body : R&B巡茶（双湖广场店）
		 * tradeAction : 1
		 * tradeStatus : 5
		 * tradeTime : 20180601103303
		 * tradeEndTime : 20180601103603
		 */

		private String tradeId;
		private String shopId;
		private String deviceId;
		private String servicePlatformType;
		private String payMode;
		private String payPlatform;
		private String outTradeNo;
		private String txNo;
		private String bizNo;
		private String tradeNo;
		private int    totalAmount;
		private int    shopActualAmount;
		private int    shopDiscountAmount;
		private int    userActualAmonut;
		private int    userDiscountAmount;
		private String body;
		private String tradeAction;
		private String tradeStatus;
		private long   tradeTime;
		private long   tradeEndTime;

		public String getTradeId() {
			return tradeId;
		}

		public void setTradeId(String tradeId) {
			this.tradeId = tradeId;
		}

		public String getShopId() {
			return shopId;
		}

		public void setShopId(String shopId) {
			this.shopId = shopId;
		}

		public String getDeviceId() {
			return deviceId;
		}

		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
		}

		public String getServicePlatformType() {
			return servicePlatformType;
		}

		public void setServicePlatformType(String servicePlatformType) {
			this.servicePlatformType = servicePlatformType;
		}

		public String getPayMode() {
			return payMode;
		}

		public void setPayMode(String payMode) {
			this.payMode = payMode;
		}

		public String getPayPlatform() {
			return payPlatform;
		}

		public void setPayPlatform(String payPlatform) {
			this.payPlatform = payPlatform;
		}

		public String getOutTradeNo() {
			return outTradeNo;
		}

		public void setOutTradeNo(String outTradeNo) {
			this.outTradeNo = outTradeNo;
		}

		public String getTxNo() {
			return txNo;
		}

		public void setTxNo(String txNo) {
			this.txNo = txNo;
		}

		public String getBizNo() {
			return bizNo;
		}

		public void setBizNo(String bizNo) {
			this.bizNo = bizNo;
		}

		public String getTradeNo() {
			return tradeNo;
		}

		public void setTradeNo(String tradeNo) {
			this.tradeNo = tradeNo;
		}

		public int getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(int totalAmount) {
			this.totalAmount = totalAmount;
		}

		public int getShopActualAmount() {
			return shopActualAmount;
		}

		public void setShopActualAmount(int shopActualAmount) {
			this.shopActualAmount = shopActualAmount;
		}

		public int getShopDiscountAmount() {
			return shopDiscountAmount;
		}

		public void setShopDiscountAmount(int shopDiscountAmount) {
			this.shopDiscountAmount = shopDiscountAmount;
		}

		public int getUserActualAmonut() {
			return userActualAmonut;
		}

		public void setUserActualAmonut(int userActualAmonut) {
			this.userActualAmonut = userActualAmonut;
		}

		public int getUserDiscountAmount() {
			return userDiscountAmount;
		}

		public void setUserDiscountAmount(int userDiscountAmount) {
			this.userDiscountAmount = userDiscountAmount;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

		public String getTradeAction() {
			return tradeAction;
		}

		public void setTradeAction(String tradeAction) {
			this.tradeAction = tradeAction;
		}

		public String getTradeStatus() {
			return tradeStatus;
		}

		public void setTradeStatus(String tradeStatus) {
			this.tradeStatus = tradeStatus;
		}

		public long getTradeTime() {
			return tradeTime;
		}

		public void setTradeTime(long tradeTime) {
			this.tradeTime = tradeTime;
		}

		public long getTradeEndTime() {
			return tradeEndTime;
		}

		public void setTradeEndTime(long tradeEndTime) {
			this.tradeEndTime = tradeEndTime;
		}
	}
}
