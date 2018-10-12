package com.acewill.slefpos.bean.canxingjianbean;

/**
 * Author：Anch
 * Date：2018/8/1 13:58
 * Desc：
 */
public class CxjWshYuJieRes {

	/**
	 * success : true
	 * msg : OK
	 * data : {"deno_coupon_ids":"","activity_ids":"","activityname":"","type":"微信卡","oid":-1,"wuid":"1300575","cno":"1300575","prechecktime":"16:43:54","upchecktime":"16:43:54","wid":26}
	 */

	private boolean success;
	private String   msg;
	private DataBean data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * deno_coupon_ids :
		 * activity_ids :
		 * activityname :
		 * type : 微信卡
		 * oid : -1
		 * wuid : 1300575
		 * cno : 1300575
		 * prechecktime : 16:43:54
		 * upchecktime : 16:43:54
		 * wid : 26
		 */

		private String deno_coupon_ids;
		private String activity_ids;
		private String activityname;
		private String type;
		private int    oid;
		private String wuid;
		private String cno;
		private String prechecktime;
		private String upchecktime;
		private int    wid;

		public String getDeno_coupon_ids() {
			return deno_coupon_ids;
		}

		public void setDeno_coupon_ids(String deno_coupon_ids) {
			this.deno_coupon_ids = deno_coupon_ids;
		}

		public String getActivity_ids() {
			return activity_ids;
		}

		public void setActivity_ids(String activity_ids) {
			this.activity_ids = activity_ids;
		}

		public String getActivityname() {
			return activityname;
		}

		public void setActivityname(String activityname) {
			this.activityname = activityname;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getOid() {
			return oid;
		}

		public void setOid(int oid) {
			this.oid = oid;
		}

		public String getWuid() {
			return wuid;
		}

		public void setWuid(String wuid) {
			this.wuid = wuid;
		}

		public String getCno() {
			return cno;
		}

		public void setCno(String cno) {
			this.cno = cno;
		}

		public String getPrechecktime() {
			return prechecktime;
		}

		public void setPrechecktime(String prechecktime) {
			this.prechecktime = prechecktime;
		}

		public String getUpchecktime() {
			return upchecktime;
		}

		public void setUpchecktime(String upchecktime) {
			this.upchecktime = upchecktime;
		}

		public int getWid() {
			return wid;
		}

		public void setWid(int wid) {
			this.wid = wid;
		}
	}
}
