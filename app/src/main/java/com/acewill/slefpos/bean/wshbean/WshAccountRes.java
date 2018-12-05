package com.acewill.slefpos.bean.wshbean;

import java.util.List;

/**
 * Author：Anch
 * Date：2017/6/5 17:41
 * Desc：
 */
public class WshAccountRes {


	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	private String errmsg;
	private int    result;
	public List<WshAccount> getWshAccountList() {
		return content;
	}

	public void setWshAccountList(List<WshAccount> wshAccountList) {
		content = wshAccountList;
	}

	List<WshAccount> content;
}
