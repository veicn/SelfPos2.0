package com.acewill.slefpos.orderui.main.uidataprovider;

import com.acewill.slefpos.bean.wshbean.WshAccount;

/**
 * Author：Anch
 * Date：2018/8/1 10:32
 * Desc：
 */
public class WshDataProvider {
	private static WshAccount wshAccount;

	public static void setWshAccountList(WshAccount swshAccount) {
		WshDataProvider.wshAccount = swshAccount;
	}

	public static WshAccount getWshAccount() {
		return wshAccount;
	}
}
