package com.acewill.slefpos.bean.canxingjianbean;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/8/1 13:39
 * Desc：
 */
public class CxjMemberLoginRes {

	private boolean          success;
	private List<CxjMemberAccount> data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<CxjMemberAccount> getData() {
		return data;
	}

	public void setData(List<CxjMemberAccount> data) {
		this.data = data;
	}
}
