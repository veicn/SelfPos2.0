package com.acewill.slefpos.bean.syncbean.syncmember;

/**
 * Author：Anch
 * Date：2018/7/12 14:13
 * Desc：
 */
public class CancelUseCouponReq {

	/**
	 * consumerKey : 【接口提供方给出】
	 * companyOuid : 【同步时提供】
	 * data : {"couponNo":"【券号】","shopId":"【门店编号】","origExchangeNo":"【使用券时的用券交易号】"}
	 */

	private String   consumerKey;
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
		 * couponNo : 【券号】
		 * shopId : 【门店编号】
		 * origExchangeNo : 【使用券时的用券交易号】
		 */

		private String couponNo;
		private String shopId;
		private String origExchangeNo;

		public String getCouponNo() {
			return couponNo;
		}

		public void setCouponNo(String couponNo) {
			this.couponNo = couponNo;
		}

		public String getShopId() {
			return shopId;
		}

		public void setShopId(String shopId) {
			this.shopId = shopId;
		}

		public String getOrigExchangeNo() {
			return origExchangeNo;
		}

		public void setOrigExchangeNo(String origExchangeNo) {
			this.origExchangeNo = origExchangeNo;
		}
	}
}
