package com.acewill.slefpos.bean.syncbean.syncpay;

/**
 * Author：Anch
 * Date：2018/5/17 11:36
 * Desc：
 */
public class SyncQueryPayResultReq {

	/**
	 * signModel : DEBUG
	 * consumerKey : qtM74y8KQvaCaQ7gLrLAjA
	 * timestamp : 1526527517384
	 * content : {"companyOuid":"iQq1c-qUQ66Gt4HDR-oFxQ","shopId":"YpXU0wmBQEW5ryGQc6elNA","deviceId":"0103","payMode":"W","payPlatform":"wxpay","outTradeNo":"12F6HtREv8C9N9cct7iLBH"}
	 * sign : 59e9c91d9afd05480859a20b777e7184
	 */

	private String signModel;
	private String      consumerKey;
	private long        timestamp;
	private ContentBean content;
	private String      sign;

	public String getSignModel() {
		return signModel;
	}

	public void setSignModel(String signModel) {
		this.signModel = signModel;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public static class ContentBean {
		/**
		 * companyOuid : iQq1c-qUQ66Gt4HDR-oFxQ
		 * shopId : YpXU0wmBQEW5ryGQc6elNA
		 * deviceId : 0103
		 * payMode : W
		 * payPlatform : wxpay
		 * outTradeNo : 12F6HtREv8C9N9cct7iLBH
		 */

		private String companyOuid;
		private String shopId;
		private String deviceId;
		private String payMode;
		private String payPlatform;
		private String outTradeNo;

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

		public String getOutTradeNo() {
			return outTradeNo;
		}

		public void setOutTradeNo(String outTradeNo) {
			this.outTradeNo = outTradeNo;
		}
	}
}
