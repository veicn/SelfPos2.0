package com.acewill.slefpos.bean.paybean;

/**
 * Author：Anch
 * Date：2018/7/12 10:15
 * Desc：
 */
public class SyncRefundReq {

	/**
	 * sign : qtM74y8KQvaCaQ7gLrLAjA
	 * timestamp : 1526463196990
	 * content : {"companyOuid":"iQq1c-qUQ66Gt4HDR-oFxQ","shopId":"p6SsGrr-S4iSYA11xmuFdA","deviceId":"0412","payMode":"O","payPlatform":"zaofans","shopNo":"1913257117","originTradeId":"InnWr7c4QHWTiTl6Vav2Kg","refundOutTradeNo":"12F9pymUkJah6rLv5pC9jm","txNo":"28051404121805310003","bizNo":"12F9pymUd7UWjjVxQrX2SB","totalAmount":800}
	 */

	private String      sign;
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
		 * companyOuid : iQq1c-qUQ66Gt4HDR-oFxQ
		 * shopId : p6SsGrr-S4iSYA11xmuFdA
		 * deviceId : 0412
		 * payMode : O
		 * payPlatform : zaofans
		 * shopNo : 1913257117
		 * originTradeId : InnWr7c4QHWTiTl6Vav2Kg
		 * refundOutTradeNo : 12F9pymUkJah6rLv5pC9jm
		 * txNo : 28051404121805310003
		 * bizNo : 12F9pymUd7UWjjVxQrX2SB
		 * totalAmount : 800
		 */

		private String companyOuid;
		private String shopId;
		private String deviceId;
		private String payMode;
		private String payPlatform;
		private String shopNo;
		private String originTradeId;
		private String refundOutTradeNo;
		private String txNo;
		private String bizNo;
		private int  totalAmount;

		public String getCompanyOuid() {
			return companyOuid;
		}

		public void setCompanyOuid(String companyOuid) {
			this.companyOuid = companyOuid;
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

		public String getShopNo() {
			return shopNo;
		}

		public void setShopNo(String shopNo) {
			this.shopNo = shopNo;
		}

		public String getOriginTradeId() {
			return originTradeId;
		}

		public void setOriginTradeId(String originTradeId) {
			this.originTradeId = originTradeId;
		}

		public String getRefundOutTradeNo() {
			return refundOutTradeNo;
		}

		public void setRefundOutTradeNo(String refundOutTradeNo) {
			this.refundOutTradeNo = refundOutTradeNo;
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

		public int getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(int totalAmount) {
			this.totalAmount = totalAmount;
		}
	}
}
