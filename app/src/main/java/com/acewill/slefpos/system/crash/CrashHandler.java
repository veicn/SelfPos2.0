package com.acewill.slefpos.system.crash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.acewill.slefpos.api.ApiConstants;
import com.acewill.slefpos.configure.BaseConfigure;
import com.acewill.slefpos.configure.TerminalConfigure;
import com.acewill.slefpos.emuee.HostType;
import com.acewill.slefpos.okhttp.OkHttpUtils;
import com.acewill.slefpos.okhttp.callback.StringCallback;
import com.jaydenxiao.common.commonutils.SPUtils;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.compressorutils.FileUtil;
import com.jaydenxiao.common.utils.logutil.FileLog;

import java.io.File;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class CrashHandler
		implements UncaughtExceptionHandler {
	private static final String TAG = "CrashHandler";
	private UncaughtExceptionHandler mDefaultHandler;// 系统默认的UncaughtException处理类
	private static CrashHandler INSTANCE = new CrashHandler();// CrashHandler实例
	private Context mContext;// 程序的Context对象
	private Map<String, String> info   = new HashMap<String, String>();// 用来存储设备信息和异常信息
	private SimpleDateFormat    format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");// 用于格式化日期,作为日志文件名的一部分

	/**
	 * 保证只有一个CrashHandler实例
	 */
	private CrashHandler() {

	}

	/**
	 * 获取CrashHandler实例 ,单例模式
	 */
	public static CrashHandler getInstance() {
		return INSTANCE;
	}

	/**
	 * 初始化
	 *
	 * @param context
	 */
	public void init(Context context) {
		mContext = context;
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();// 获取系统默认的UncaughtException处理器
		Thread.setDefaultUncaughtExceptionHandler(this);// 设置该CrashHandler为程序的默认处理器
	}

	/**
	 * 当UncaughtException发生时会转入该重写的方法来处理
	 */
	public void uncaughtException(Thread thread, Throwable ex) {

		if (!handleException(ex) && mDefaultHandler != null) {
			// 如果自定义的没有处理则让系统默认的异常处理器来处理
			mDefaultHandler.uncaughtException(thread, ex);
		} else {
			try {
				Thread.sleep(3000);// 如果处理了，让程序继续运行3秒再退出，保证文件保存并上传到服务器
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 退出程序
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(1);
		}
	}

	/**
	 * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
	 *
	 * @param ex 异常信息
	 * @return true 如果处理了该异常信息;否则返回false.
	 */
	public boolean handleException(Throwable ex) {
		if (ex == null) {
			return false;
		}
		ex.printStackTrace();
		//		new Thread() {
		//			public void run() {
		//				Looper.prepare();
		//				Looper.loop();
		//			}
		//		}.start();
		// 收集设备参数信息
		collectDeviceInfo(mContext);
		// 保存日志文件
		File file = FileUtil.saveCrashInfo2File(ex, info);
		uploadLog2(file);
		return true;
	}

	public String uploadLogUrl = "api/terminal/uploadLog";

	public String getUploadLogUrl() {
		String host = "";
		host = HostType.IS_SMARANT_DEBUG ? ApiConstants.TEST_HOST : ApiConstants.NORMAL_HOST;
		return host + uploadLogUrl;
	}

	public void uploadLog2(final File file) {
		if (file == null) {
			return;
		}
		OkHttpUtils
				.post()
				.addFile("file", file.getName(), file)
				.url(getUploadLogUrl())
				.addParams("appid", BaseConfigure.getAppid())
				.addParams("brandid", String.valueOf(BaseConfigure.getBrandid()))
				.addParams("storeid", String.valueOf(BaseConfigure.getStoreid()))
				.addParams("tname", TerminalConfigure.getTname())
				.addParams("terminalid", TerminalConfigure.getTerminalid())
				.addParams("token", BaseConfigure.getToken())
				.build()
				.connTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)
				.writeTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS).
				readTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)
				.execute(new StringCallback() {
					@Override
					public void onError(Call call, Exception e, int id) {
						e.printStackTrace();
					}

					@Override
					public void onResponse(String response, int id) {
						ToastUitl.showLong(mContext, "日志上传成功!");
					}
				});
	}

	/**
	 * 收集设备参数信息
	 *
	 * @param context
	 */
	public void collectDeviceInfo(Context context) {
		try {
			FileLog.log("设备分辨率信息>屏幕宽>" + SPUtils
					.getSharedIntData(mContext, "widthPixels") + ",屏幕高>" + SPUtils
					.getSharedIntData(mContext, "heightPixels") + "\n屏幕xdpi" + SPUtils
					.getSharedFloatData(mContext, "xdpi") + ",屏幕ydpi" + SPUtils
					.getSharedFloatData(mContext, "ydpi") + "\n屏幕Dpi密度" + SPUtils
					.getSharedFloatData(mContext, "density") + "，屏幕密度" + SPUtils
					.getSharedIntData(mContext, "densityDpi") + "\n");
			PackageManager pm = context.getPackageManager();// 获得包管理器
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(),
					PackageManager.GET_ACTIVITIES);// 得到该应用的信息，即主Activity
			if (pi != null) {
				String versionName = pi.versionName == null
						? "null"
						: pi.versionName;
				String versionCode = pi.versionCode + "";
				info.put("versionName", versionName);
				info.put("versionCode", versionCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Field[] fields = Build.class.getDeclaredFields();// 反射机制
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				info.put(field.getName(),
						field.get("")
								.toString());
				Log.d(TAG, field.getName() + ":" + field.get(""));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}


}
