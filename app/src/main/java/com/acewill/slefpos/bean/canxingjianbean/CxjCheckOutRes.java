package com.acewill.slefpos.bean.canxingjianbean;

/**
 * Author：Anch
 * Date：2018/8/1 13:59
 * Desc：
 */
public class CxjCheckOutRes {
	//	response:{"success":false,"msg":"\u8bf7\u6d88\u8d39\u8005\u8f93\u5165\u5bc6\u7801\u5b8c\u6210\u652f\u4ed8\uff0c\u8f93\u5165\u5bc6\u7801\u6210\u529f\u540e\u70b9\u51fb\u786e\u5b9a\u7ed3\u8d26","code":2,"oid":55,"paytype":2}
	//{"success":0,"msg":"券未设置失败，请重操作"}
	/**
	 * success : 1
	 * order : 41
	 * printInfo : [[]]
	 * printMenuInfo : []
	 * msg :
	 */

	private int           success;
	private String           order;
	private String        msg;

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
