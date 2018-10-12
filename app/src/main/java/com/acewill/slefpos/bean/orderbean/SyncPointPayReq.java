package com.acewill.slefpos.bean.orderbean;

/**
 * Author：Anch
 * Date：2018/6/1 15:40
 * Desc：
 */
class SyncPointPayReq {


	/**
	 * companyOuid : 1ctAaaMITEmCxb-ADb1skw
	 * consumerKey : 2FMf6HR9RDOCN0tL7QHJag
	 * data : {" memberOuid":"","onLineTxLogOuid":1,"point":1,"currentSoDate":"","posUserOuid":"","storeshopOuid":""}
	 */

	private String companyOuid;
	private String   consumerKey;
	private DataBean data;

	public String getCompanyOuid() {
		return companyOuid;
	}

	public void setCompanyOuid(String companyOuid) {
		this.companyOuid = companyOuid;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 *  memberOuid :
		 * onLineTxLogOuid : 1
		 * point : 1
		 * currentSoDate :
		 * posUserOuid :
		 * storeshopOuid :
		 */

		private String memberOuid;
		private String    onLineTxLogOuid;
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
