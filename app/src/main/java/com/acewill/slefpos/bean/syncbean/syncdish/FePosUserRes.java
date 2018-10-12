package com.acewill.slefpos.bean.syncbean.syncdish;

import java.io.Serializable;
import java.util.List;

/**
 * Author：Anch
 * Date：2018/6/9 14:51
 * Desc：
 */
public class FePosUserRes implements Serializable{


	private List<FePosUser> FePosUser;

	public List<FePosUser> getFePosUser() {
		return FePosUser;
	}

	public void setFePosUser(List<FePosUser> FePosUser) {
		this.FePosUser = FePosUser;
	}

}
