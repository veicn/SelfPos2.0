package com.acewill.slefpos.configure;

import android.text.TextUtils;

/**
 * Author：Anch
 * Date：2018/1/25 9:22
 * Desc：
 */
public class BaseConfigure {
	private static String appid   = "";
	private static int    brandid = 0;
	private static int    storeid = 0;

	public static String getToken() {
		return token;
	}

	public static void setToken(String token) {
		BaseConfigure.token = token;
	}

	private static String token = "";

	public static String getAppid() {
		return appid;
	}

	public static void setAppid(String appid) {
		BaseConfigure.appid = appid;
	}

	public static int getBrandid() {
		return brandid;
	}

	public static void setBrandid(int brandid) {
		BaseConfigure.brandid = brandid;
	}

	public static int getStoreid() {
		return storeid;
	}

	public static void setStoreid(int storeid) {
		BaseConfigure.storeid = storeid;
	}


	public static void init(String appid, int storeid, int brandid) {
		BaseConfigure.setAppid(appid);
		BaseConfigure.setBrandid(brandid);
		BaseConfigure.setStoreid(storeid);
	}

	public static boolean isInit() {
		if (TextUtils.isEmpty(BaseConfigure.getAppid()))
			return false;
		return true;
	}
}
