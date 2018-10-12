package com.acewill.slefpos.bean.syncbean.syncorder;

/**
 * Author：Anch
 * Date：2018/5/17 10:18
 * Desc：
 */
public class SyncAcceptRes {

	/**
	 * 正常
	 * code : 200
	 * data : {"ordersOuid":"QV2K3SjTQPSdnre1U1COgw","ordersNo":"888888201805171001427007"}
	 * <p>
	 * 异常的时候，下面这个异常是后台改了口味的id，但是点餐机没有重启导致的
	 * {"code":400,"message":"The featureId is inexistence."}
	 */

	private int      code;
	private DataBean data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String message;//这个字段在异常的时候才会有

	public SyncAcceptRes(int i) {
		this.code = i;
	}

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
		 * ordersOuid : QV2K3SjTQPSdnre1U1COgw
		 * ordersNo : 888888201805171001427007
		 */

		private String ordersOuid;
		private String ordersNo;

		public String getOrdersOuid() {
			return ordersOuid;
		}

		public void setOrdersOuid(String ordersOuid) {
			this.ordersOuid = ordersOuid;
		}

		public String getOrdersNo() {
			return ordersNo;
		}

		public void setOrdersNo(String ordersNo) {
			this.ordersNo = ordersNo;
		}
	}
}
