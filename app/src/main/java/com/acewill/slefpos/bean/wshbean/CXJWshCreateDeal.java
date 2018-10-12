package com.acewill.slefpos.bean.wshbean;

/**
 * 预消费接口
 * Created by aqw on 2016/10/21.
 */
public class CXJWshCreateDeal {

	public static class Request {

		//		@Field("wid") String mid,
		//		@Field("oid") String oid,
		//		@Field("uid") String uid,
		//		@Field("cardid") String cardid,
		//		@Field("type") String type,
		//		@Field("activityid") String activityid,
		//		@Field("activityname") String activityname,
		//		@Field("couponid") String couponid,
		//		@Field("cno") String cno
		private String wid;
		private String oid;
		private String uid;
		private String cardid;
		private String type;
		private String activityid;
		private String activityname;
		private String couponid;
		private String cno;

		public String getWid() {
			return wid;
		}

		public void setWid(String wid) {
			this.wid = wid;
		}

		public String getOid() {
			return oid;
		}

		public void setOid(String oid) {
			this.oid = oid;
		}

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public String getCardid() {
			return cardid;
		}

		public void setCardid(String cardid) {
			this.cardid = cardid;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getActivityid() {
			return activityid;
		}

		public void setActivityid(String activityid) {
			this.activityid = activityid;
		}

		public String getActivityname() {
			return activityname;
		}

		public void setActivityname(String activityname) {
			this.activityname = activityname;
		}

		public String getCouponid() {
			return couponid;
		}

		public void setCouponid(String couponid) {
			this.couponid = couponid;
		}

		public String getCno() {
			return cno;
		}

		public void setCno(String cno) {
			this.cno = cno;
		}
	}


	public static class Pruduct {
		public String name;//菜品名称
		public String no;//菜品编号
		public int    num;//产品数量
		public int    price;//产品价格(分)
		public int    is_activity;//是否参加活动(1:参加，2：不参加)

		@Override
		public String toString() {
			return "Pruduct{" +
					"name='" + name + '\'' +
					", no='" + no + '\'' +
					", num=" + num +
					", price=" + price +
					", is_activity=" + is_activity +
					'}';
		}
	}

}
