package com.acewill.slefpos.bean.syncbean.syncmember;

/**
 * Author：Anch
 * Date：2018/6/1 17:02
 * Desc：
 */
public class SyncSingleUseCouponRes {

	/**
	 * code : 1008
	 * message : 该优惠券与适用商品不符
	 * data : {"couponNo":"0605060563395367","discountAmount":0,"ignoreCause":"该优惠券与适用商品不符","status":0}
	 */

	private int      code;
	private String   message;
	private DataBean data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * couponNo : 0605060563395367
		 * discountAmount : 0
		 * ignoreCause : 该优惠券与适用商品不符
		 * status : 0
		 */

		private String couponNo;
		private float  discountAmount;
		private String ignoreCause;
		private int    status;

		public String getCouponNo() {
			return couponNo;
		}

		public void setCouponNo(String couponNo) {
			this.couponNo = couponNo;
		}

		public float getDiscountAmount() {
			return discountAmount;
		}

		public void setDiscountAmount(float discountAmount) {
			this.discountAmount = discountAmount;
		}

		public String getIgnoreCause() {
			return ignoreCause;
		}

		public void setIgnoreCause(String ignoreCause) {
			this.ignoreCause = ignoreCause;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}
	}
}
