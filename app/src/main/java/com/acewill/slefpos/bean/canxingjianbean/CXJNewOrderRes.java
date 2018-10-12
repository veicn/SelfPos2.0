package com.acewill.slefpos.bean.canxingjianbean;

/**
 * Author：Anch
 * Date：2018/8/14 15:07
 * Desc：
 */
public class CXJNewOrderRes {

	/**
	 * success : true
	 * oid : 203
	 * msg :
	 */

	private boolean success;
	private int    oid;
	private String msg;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
