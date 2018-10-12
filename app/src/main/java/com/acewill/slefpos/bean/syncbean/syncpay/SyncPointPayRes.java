package com.acewill.slefpos.bean.syncbean.syncpay;

/**
 * Author：Anch
 * Date：2018/6/1 15:30
 * Desc：
 */
public class SyncPointPayRes {


	/**
	 * code : 0
	 * data : {"memberPointLogOuid":"f6zZy2yUR9adPICadCt6rg","pointUse":1937}
	 */

	private int code;
	private DataBean data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * memberPointLogOuid : f6zZy2yUR9adPICadCt6rg
		 * pointUse : 1937
		 */

		private String memberPointLogOuid;
		private int pointUse;

		public String getMemberPointLogOuid() {
			return memberPointLogOuid;
		}

		public void setMemberPointLogOuid(String memberPointLogOuid) {
			this.memberPointLogOuid = memberPointLogOuid;
		}

		public int getPointUse() {
			return pointUse;
		}

		public void setPointUse(int pointUse) {
			this.pointUse = pointUse;
		}
	}
}
