package com.acewill.slefpos.app;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.acewill.slefpos.api.ApiConstants;
import com.acewill.slefpos.base.BaseApplication;
import com.acewill.slefpos.emuee.HostType;
import com.acewill.slefpos.print.ticketprint.SmarantTicketPrintHandler;
import com.acewill.slefpos.system.crash.CrashHandler;
import com.jaydenxiao.common.BuildConfig;
import com.jaydenxiao.common.baserx.RxManager;
import com.jaydenxiao.common.commonutils.LogUtils;

/**
 * APPLICATION
 */
public class AppApplication extends BaseApplication {

	private static RxManager mRxManager;

	@Override
	public void onCreate() {
		super.onCreate();
		//初始化logger
		LogUtils.logInit(BuildConfig.LOG_DEBUG);
		CrashHandler.getInstance().init(getApplicationContext());
		SmarantTicketPrintHandler.getInstance().initdotLint();
		//		SyncTicketPrintHandler.getInstance().initdotLint();
		ApiConstants
				.setType(HostType.IS_SYNC_DEBUG ? HostType.SYNC_TEST_HOSTS : HostType.SYNC_NORMAL_HOSTS);
		registManager();

	}



	private void registManager() {
		mRxManager = new RxManager();
	}

	public static RxManager getRxManager() {
		return mRxManager;
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}
}
