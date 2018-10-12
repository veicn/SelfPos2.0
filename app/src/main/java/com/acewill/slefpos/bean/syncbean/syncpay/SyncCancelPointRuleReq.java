package com.acewill.slefpos.bean.syncbean.syncpay;

/**
 * Author：Anch
 * Date：2018/6/27 14:36
 * Desc：
 */
public class SyncCancelPointRuleReq {


	/**
	 * consumerKey : 【接口提供方给出】
	 * companyOuid : 【同步时提供】
	 * data : {" memberOuid":"【会员ouid】","onLineTxLogOuid":"【兑换交易号】","point":1,"currentSoDate":"【兑换日期】","posUserOuid":"【用户id】","storeshopOuid":"【门店】"}
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
		 *  memberOuid : 【会员ouid】
		 * onLineTxLogOuid : 【兑换交易号】
		 * point : 1
		 * currentSoDate : 【兑换日期】
		 * posUserOuid : 【用户id】
		 * storeshopOuid : 【门店】
		 */

		private String memberOuid;
		private String onLineTxLogOuid;
		private int    point;
		private String currentSoDate;
		private String posUserOuid;
		private String storeshopOuid;

		public String getMemberOuid() {
			return memberOuid;
		}

		public void setMemberOuid(String memberOuid) {
			this.memberOuid = memberOuid;
		}

		public String getOnLineTxLogOuid() {
			return onLineTxLogOuid;
		}

		public void setOnLineTxLogOuid(String onLineTxLogOuid) {
			this.onLineTxLogOuid = onLineTxLogOuid;
		}

		public int getPoint() {
			return point;
		}

		public void setPoint(int point) {
			this.point = point;
		}

		public String getCurrentSoDate() {
			return currentSoDate;
		}

		public void setCurrentSoDate(String currentSoDate) {
			this.currentSoDate = currentSoDate;
		}

		public String getPosUserOuid() {
			return posUserOuid;
		}

		public void setPosUserOuid(String posUserOuid) {
			this.posUserOuid = posUserOuid;
		}

		public String getStoreshopOuid() {
			return storeshopOuid;
		}

		public void setStoreshopOuid(String storeshopOuid) {
			this.storeshopOuid = storeshopOuid;
		}
	}
}
