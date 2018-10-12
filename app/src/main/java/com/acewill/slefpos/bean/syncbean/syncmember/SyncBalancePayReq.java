package com.acewill.slefpos.bean.syncbean.syncmember;

/**
 * Author：Anch
 * Date：2018/6/1 13:49
 * Desc：
 */
public class SyncBalancePayReq {


	/**
	 * consumerKey : 【接口提供方给出】
	 * companyOuid : 【同步时提供】
	 * data : {"authCode":"0605100005287415","shopId":"vptKCaOtSn-ItRy4CVjt4w","txAmount":1,"txNo":""}
	 */

	private String consumerKey;
	private String   companyOuid;
	private DataBean data;

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getCompanyOuid() {
		return companyOuid;
	}

	public void setCompanyOuid(String companyOuid) {
		this.companyOuid = companyOuid;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * authCode : 0605100005287415
		 * shopId : vptKCaOtSn-ItRy4CVjt4w
		 * txAmount : 1
		 * txNo :
		 */

		private String authCode;
		private String shopId;
		private float    txAmount;
		private String txNo;

		public String getAuthCode() {
			return authCode;
		}

		public void setAuthCode(String authCode) {
			this.authCode = authCode;
		}

		public String getShopId() {
			return shopId;
		}

		public void setShopId(String shopId) {
			this.shopId = shopId;
		}

		public float getTxAmount() {
			return txAmount;
		}

		public void setTxAmount(float txAmount) {
			this.txAmount = txAmount;
		}

		public String getTxNo() {
			return txNo;
		}

		public void setTxNo(String txNo) {
			this.txNo = txNo;
		}
	}
}
