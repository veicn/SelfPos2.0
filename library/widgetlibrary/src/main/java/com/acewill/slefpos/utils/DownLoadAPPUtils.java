package com.acewill.slefpos.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.File;

/**
 * Author：Anch
 * Date：2017/5/6 15:08
 * Desc：
 */
public class DownLoadAPPUtils {

	private static Activity         mContext;
	private static DownLoadAPPUtils utils;
	private static final int INSTALL = 10;


	public static DownLoadAPPUtils getInstance(Activity context) {
		mContext = context;
		if (utils == null) {
			utils = new DownLoadAPPUtils();
		}
		return utils;
	}

	public int getAPPVersionCode() {
		int            currentVersionCode = 0;
		PackageManager manager            = mContext.getPackageManager();
		try {
			PackageInfo info           = manager.getPackageInfo(mContext.getPackageName(), 0);
			String      appVersionName = info.versionName; // 版本名
			currentVersionCode = info.versionCode; // 版本号
			System.out.println(currentVersionCode + " " + appVersionName);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return currentVersionCode;
	}


	public String getAPPVersionCode2() {
		StringBuilder  sb                 = new StringBuilder();
		PackageManager manager            = mContext.getPackageManager();
		try {
			PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
			sb.append("V" + info.versionCode + " " + info.versionName);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public void install(File file) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		mContext.startActivityForResult(intent, INSTALL);
	}

}
