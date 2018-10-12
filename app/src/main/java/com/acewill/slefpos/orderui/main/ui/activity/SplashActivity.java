package com.acewill.slefpos.orderui.main.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.api.ApiConstants;
import com.acewill.slefpos.app.TimeConfigure;
import com.acewill.slefpos.base.BaseActivity;
import com.acewill.slefpos.configure.BaseConfigure;
import com.acewill.slefpos.configure.StoreConfigure;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.configure.TerminalConfigure;
import com.acewill.slefpos.emuee.HostType;
import com.acewill.slefpos.okhttp.OkHttpUtils;
import com.acewill.slefpos.okhttp.callback.StringCallback;
import com.acewill.slefpos.orderui.main.ui.syncactivity.SyncSetActivity;
import com.acewill.slefpos.system.ui.CommonSetActivity;
import com.acewill.slefpos.utils.DownLoadAPPUtils;
import com.acewill.slefpos.utils.permission.IPermission;
import com.bumptech.glide.Glide;
import com.jaydenxiao.common.commonutils.DialogPermissionUtil;
import com.jaydenxiao.common.commonutils.SPUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Anch
 * Date：2018/1/24 13:20
 * Desc：
 */
public class SplashActivity extends BaseActivity {

	private static final String TAG = "SplashActivity";
	@Bind(R.id.iv_logo)
	ImageView ivLogo;
	@Bind(R.id.tv_name)
	TextView  tvName;


	@OnClick(R.id.sync_system)
	public void onSyncSystemClick() {
		SPUtils.setSharedIntData(this, "baseInit", SystemConfig.System_Sync);
		if (TextUtils
				.isEmpty(SPUtils.getSharedStringData(mContext, "instanceSid"))) {
			startActivity(new Intent(SplashActivity.this, SyncSetActivity.class));
		} else {
			startActivity(new Intent(SplashActivity.this, LoadDataActivity.class));
		}
		SystemConfig.isSyncSystem = true;
		finish();
	}

	@OnClick(R.id.smarant_system)
	public void onSmarantSystemClick() {
		SPUtils.setSharedIntData(this, "baseInit", SystemConfig.System_Smarant);
		SystemConfig.isSmarantSystem = true;
		startActivity(new Intent(SplashActivity.this, LoadDataActivity.class));
		finish();
	}

	@OnClick(R.id.canxingjian_system)
	public void onCanXingJianSystemClick() {
		SPUtils.setSharedIntData(this, "baseInit", SystemConfig.System_CanXingJian);
		SystemConfig.isCanXingJianSystem = true;
		startActivity(new Intent(SplashActivity.this, LoadDataActivity.class));
		finish();
	}

	@OnClick(R.id.iv_logo)
	public void goMain() {

	}


	private boolean isBind() {
		BaseConfigure.setAppid(SPUtils.getSharedStringData(this, "appid"));
		BaseConfigure.setStoreid(SPUtils.getSharedIntData(this, "storeid"));
		BaseConfigure.setBrandid(SPUtils.getSharedIntData(this, "brandid"));
		TerminalConfigure.setTname(SPUtils.getSharedStringData(this, "tname"));
		TerminalConfigure.setTerminalid(SPUtils.getSharedStringData(this, "terminalid"));
		TerminalConfigure.setTerminalmac(SPUtils.getSharedStringData(this, "terminalmac"));
		StoreConfigure.setLongitute(SPUtils.getSharedStringData(this, "longitute"));
		StoreConfigure.setLatitute(SPUtils.getSharedStringData(this, "latitute"));
		TerminalConfigure.setKdsid(SPUtils.getSharedIntData(this, "kdsid"));
		StoreConfigure.setJyjAddress(SPUtils.getSharedStringData(this, "jyjAddress"));
		return !TextUtils.isEmpty(BaseConfigure.getAppid()) && BaseConfigure
				.getStoreid() != 0 && BaseConfigure.getBrandid() != 0;
	}

	@Override
	public int getLayoutId() {
		return R.layout.act_splash;
	}

	@Override
	public void initPresenter() {

	}


	@Override
	public void initView(Bundle savedInstanceState) {
		TimeConfigure.resetScreenTime();
		saveDeviceInfo();
		Glide.get(mContext).clearMemory();
		new Thread(new Runnable() {
			@Override
			public void run() {
				Glide.get(mContext).clearDiskCache();
			}
		}).start();
		int versionCode = DownLoadAPPUtils.getInstance(SplashActivity.this)
				.getAPPVersionCode();
		TerminalConfigure.setCurrentVersion(String.valueOf(versionCode));
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
			initPermission();
		} else {
			init();
		}
	}

	private void init() {
		int type = SPUtils.getSharedIntData(this, "baseInit");
		if (SPUtils.getSharedIntData(this, "baseInit") != 0) {
			if (type == SystemConfig.System_Smarant) {
				SystemConfig.isSmarantSystem = true;
			} else if (type == SystemConfig.System_Sync) {
				SystemConfig.isSyncSystem = true;
			} else if (type == SystemConfig.System_CanXingJian) {
				SystemConfig.isCanXingJianSystem = true;
				checkDbUpdate();
			}
			if ((type == SystemConfig.System_Smarant || type == SystemConfig.System_CanXingJian) && !isBind()) {
				startActivity(CommonSetActivity.class);
			} else if (type == SystemConfig.System_Sync && TextUtils
					.isEmpty(SPUtils.getSharedStringData(mContext, "instanceSid"))) {
				ApiConstants
						.setType(HostType.IS_SYNC_DEBUG ? HostType.SYNC_TEST_HOSTS : HostType.SYNC_NORMAL_HOSTS);
				startActivity(SyncSetActivity.class);
			} else {
				startActivity(new Intent(SplashActivity.this, LoadDataActivity.class));
			}
			finish();
		}
	}

	private void checkDbUpdate() {
		OkHttpUtils
				.post()
				.url(getSqliteUpadateUrl())
				.addParams("timestamp", (System.currentTimeMillis() / 1000) + "")
				.build()
				.execute(new StringCallback() {
					@Override
					public void onError(Call call, Exception e, int id) {
						Log.e(TAG, "checkDbUpdate_onError");
					}

					@Override
					public void onResponse(String response, int id) {
						Log.e(TAG, "checkDbUpdate_onResponse");
					}
				});
	}

	private String getSqliteUpadateUrl() {
		return "http://"
				+ SPUtils.getSharedStringData(mContext, "canxingjian_ip_address")
				+ "/order/api/api.php?do=isSqliteFileUpdated&app=ClientNewAndroidMobile";
	}

	private void saveDeviceInfo() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		//display = getWindowManager().getDefaultDisplay();
		//display.getMetrics(dm)（把屏幕尺寸信息赋值给DisplayMetrics dm）;
		SPUtils.setSharedIntData(mContext, "widthPixels", dm.widthPixels);
		SPUtils.setSharedIntData(mContext, "heightPixels", dm.heightPixels);
		SPUtils.setSharedIntData(mContext, "densityDpi", dm.densityDpi);
		SPUtils.setSharedFloatData(mContext, "xdpi", dm.xdpi);
		SPUtils.setSharedFloatData(mContext, "ydpi", dm.ydpi);
		SPUtils.setSharedFloatData(mContext, "density", dm.density);

	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	/**
	 * 申请程序运行时必要的权限
	 */
	private void initPermission() {
		String[] permission = new String[]{
				Manifest.permission.WRITE_EXTERNAL_STORAGE,
				Manifest.permission.READ_EXTERNAL_STORAGE};
		requestRunTimePermission(permission, new PermisionListenr());
		//		[android.permission.WRITE_EXTERNAL_STORAGE, android.permission.READ_EXTERNAL_STORAGE]
	}

	private class PermisionListenr
			implements IPermission {
		@Override
		public void onGranted() {
			init();
			Log.e(TAG, "onGrandtedByUser");
		}

		@Override
		public void onDenied(List<String> deniedPermission) {
			Log.e(TAG, "onDeniedByUser");
			Log.e(TAG, deniedPermission.toString());
			DialogPermissionUtil.PermissionDialog(SplashActivity.this, "缺少程序运行时需要的读写权限");
		}
	}

}
