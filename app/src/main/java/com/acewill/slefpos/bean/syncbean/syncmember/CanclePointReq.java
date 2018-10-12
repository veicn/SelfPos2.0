package com.acewill.slefpos.bean.syncbean.syncmember;

/**
 * Author：Anch
 * Date：2018/7/12 14:23
 * Desc：
 */
public class CanclePointReq {

	/**
	 * consumerKey : 【接口提供方给出】
	 * companyOuid : 【同步时提供】
	 * data : {"memberPointLogOuid":"【积分变更记录Ouid"}
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
		public String getStoreshopOuid() {
			return storeshopOuid;
		}

		public void setStoreshopOuid(String storeshopOuid) {
			this.storeshopOuid = storeshopOuid;
		}

		/**
		 * memberPointLogOuid : 【积分变更记录Ouid
		 */
		private String storeshopOuid;
		private String memberPointLogOuid;

		public String getMemberPointLogOuid() {
			return memberPointLogOuid;
		}

		public void setMemberPointLogOuid(String memberPointLogOuid) {
			this.memberPointLogOuid = memberPointLogOuid;
		}


	}
}
