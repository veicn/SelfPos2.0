package com.acewill.slefpos.bean.canxingjianbean;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/8/1 13:10
 * Desc：
 */
public class CxjLoginModel {

	/**
	 * success : 1
	 * funcodes : [100,101,102,103,105,106,107,108,109,110,111,112,113,114,115,117,122,131,132,130,133,134,135,136,137,138,139,141,142,158,160]
	 * username : 1
	 * rname : 管理员
	 * uid : 1
	 */

	private int success;
	private String        username;
	private String        rname;
	private String        uid;
	private List<Integer> funcodes;

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<Integer> getFuncodes() {
		return funcodes;
	}

	public void setFuncodes(List<Integer> funcodes) {
		this.funcodes = funcodes;
	}
}
