package com.acewill.slefpos.orderui.main.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.base.BaseActivity;
import com.acewill.slefpos.bean.smarantstorebean.LoginEntity;
import com.acewill.slefpos.bean.syncbean.syncdish.FePosUserRes;
import com.acewill.slefpos.configure.BaseConfigure;
import com.acewill.slefpos.configure.StoreConfigure;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.configure.TerminalConfigure;
import com.acewill.slefpos.emuee.HostType;
import com.acewill.slefpos.orderui.main.contract.LoadDataContract;
import com.acewill.slefpos.orderui.main.model.LoadDataModel;
import com.acewill.slefpos.orderui.main.presenter.LoadDataPresenter;
import com.acewill.slefpos.orderui.main.ui.dialog.UserListDialog;
import com.acewill.slefpos.orderui.main.uidataprovider.SyncDataProvider;
import com.acewill.slefpos.print.Common;
import com.acewill.slefpos.print.WorkService;
import com.acewill.slefpos.print.chikenprintlibrary.PosKitchenPrintAdapter;
import com.acewill.slefpos.system.ui.CommonSetActivity;
import com.jaydenxiao.common.commonutils.SPUtils;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.utils.logutil.FileLog;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Author：Anch
 * Date：2018/1/25 11:08
 * Desc：
 */
public class LoadDataActivity extends BaseActivity<LoadDataPresenter, LoadDataModel> implements
		LoadDataContract.View {


	private static final String TAG = "LoadDataActivity";

	/**
	 * 入口
	 *
	 * @param activity
	 */
	public static void startAction(Activity activity) {
		Intent intent = new Intent(activity, EatMethodActivity.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.fade_in,
				com.jaydenxiao.common.R.anim.fade_out);
	}

	@Override
	public int getLayoutId() {
		return R.layout.act_loaddata;
	}

	@Override
	public void initPresenter() {
		mPresenter.setVM(this, mModel);
	}

	private boolean click;

	@Bind(R.id.system_name)
	TextView system_name;
	@Bind(R.id.btn_set)
	Button   btn_set;
	@Bind(R.id.left_time)
	TextView left_time;

	@OnClick(R.id.btn_set)
	public void btnSetClick() {
		click = true;
		mHandler.removeCallbacks(mRunnable);
		if (SystemConfig.isSmarantSystem || SystemConfig.isCanXingJianSystem) {
			startActivityForResult(CommonSetActivity.class, 1);
		} else if (SystemConfig.isSyncSystem) {
			FePosUserRes res = SyncDataProvider.getUserRes();
			if (res != null)
				showAdminLoginDialog(res);
			else {
				startActivityForResult(CommonSetActivity.class, 1);
			}
		}
	}

	private void showAdminLoginDialog(FePosUserRes res) {
		UserListDialog dialog = UserListDialog.newInstance(res, "loaddata");
		dialog.setCancelable(false);
		dialog.show(getSupportFragmentManager(), "UserListDialog");
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (BaseConfigure.isInit())
			mPresenter.goLoadData(SPUtils.getSharedIntData(this, "baseInit"));
		else {
			ToastUitl.showShort(mContext, "请绑定终端!");
		}
	}

	private int     currentTime = 3;
	private Handler mHandler    = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			currentTime--;
			if (currentTime == 0) {
				if (BaseConfigure.isInit()) {
					startProgressDialog("正在下载更新菜品数据，请稍后...");
					mPresenter.goLoadData(SPUtils.getSharedIntData(mContext, "baseInit"));
					mHandler.removeCallbacks(mRunnable);
				} else {
					ToastUitl.showLong(mContext, "请绑定终端!");
				}
			} else {
				left_time.setText("剩余" + currentTime + "s");
			}
			return false;
		}
	});

	public void registLoginStatu() {
		mRxManager.on(AppConstant.SYNCUSERLOGINCANCLE, new Action1<Object>() {
			@Override
			public void call(Object kind) {
				if (BaseConfigure.isInit()) {
					startProgressDialog("正在下载更新菜品数据，请稍后...");
					mPresenter.goLoadData(SPUtils.getSharedIntData(mContext, "baseInit"));
				} else {
					ToastUitl.showLong(mContext, "请绑定终端!");
				}
			}
		});
		mRxManager.on(AppConstant.SYNCUSERLOGINSUCCESS, new Action1<Object>() {
			@Override
			public void call(Object kind) {
				startActivityForResult(CommonSetActivity.class, 1);
			}
		});
	}

	private Runnable mRunnable = new Runnable() {
		@Override
		public void run() {
			if (click) {
				return;
			}
			mHandler.sendEmptyMessage(0);
			mHandler.postDelayed(mRunnable, 1000);
		}
	};


	@Override
	public void initView(Bundle savedInstanceState) {
		initSystem();
		initText();
		terminalLogin();
		registLoginStatu();
		if ((HostType.IS_SYNC_DEBUG || HostType.IS_SMARANT_DEBUG) && SPUtils
				.getSharedBooleanData(mContext, "PHONE_TEST")) {
			FileLog.log("手机测试，不去获取打印权限!");
		} else {
			startService(new Intent(this, WorkService.class));
		}
	}

	private void terminalLogin() {
		if (BaseConfigure.isInit())
			mPresenter.goLogin();
		else
			startActivityForResult(CommonSetActivity.class, 1);
	}

	private void initSystem() {
		BaseConfigure
				.init(SPUtils.getSharedStringData(mContext, "appid"), SPUtils
						.getSharedIntData(mContext, "storeid"), SPUtils
						.getSharedIntData(mContext, "brandid"));
		StoreConfigure.init(SPUtils.getSharedStringData(mContext, "longitute"), SPUtils
				.getSharedStringData(mContext, "latitute"), SPUtils
				.getSharedStringData(mContext, "sname"), SPUtils
				.getSharedStringData(mContext, "phone"), SPUtils
				.getSharedStringData(mContext, "mobile"), SPUtils
				.getSharedStringData(mContext, "address"), SPUtils
				.getSharedStringData(mContext, "brandName"), SPUtils
				.getSharedlongData(mContext, "storeEndTime"));
		TerminalConfigure.init(SPUtils
				.getSharedStringData(mContext, "tname"), SPUtils
				.getSharedStringData(mContext, "terminalmac"), SPUtils
				.getSharedStringData(mContext, "terminalid")
		);


		Common.PRINTER_PRODUCTID = SPUtils.getSharedStringData(mContext, "productId");
	}

	private void initText() {
		if (SystemConfig.isSyncSystem)
			system_name.setText("奥琦玮");
		else if (SystemConfig.isSmarantSystem)
			system_name.setText("智慧快餐");
		else if (SystemConfig.isCanXingJianSystem)
			system_name.setText("餐行健");
		Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/xiyuanfan.ttf");
		// 应用字体
		system_name.setTypeface(typeFace);
		btn_set.setTypeface(typeFace);
	}


	@Override
	public void showLoading(String title) {

	}

	@Override
	public void stopLoading() {

	}

	@Override
	public void showErrorTip(String msg) {

	}

	private int loadDataTime = 0;

	@Override
	public void returnData(boolean loadSuccess) {
		FileLog.log("第 "+loadDataTime+" 次下载数据>"+loadSuccess);
		Log.e(TAG,"第 "+loadDataTime+" 次下载数据>"+loadSuccess);
		Log.e(TAG, " 观察者 Observer的工作线程是: " + Thread.currentThread().getName());
		if (loadSuccess) {
			stopProgressDialog();
			startAction(this);
			if (SystemConfig.isSmarantSystem)
				PosKitchenPrintAdapter.getInstance(mContext).init();
			finish();
		} else {
			if (loadDataTime < 3) {
				loadDataTime++;
				mPresenter.goLoadData(SPUtils.getSharedIntData(mContext, "baseInit"));
			} else {
				stopProgressDialog();
				showAleartDialog("发生异常", "菜品下载更新失败，请检查网络后重试!");
			}

		}

	}

	/**
	 * 清楚缓存
	 */
	@Override
	public void clearSP() {
		BaseConfigure.setAppid("");
		BaseConfigure.setStoreid(0);
		BaseConfigure.setBrandid(0);
		TerminalConfigure.setTname("");
		TerminalConfigure.setTerminalid("");
		TerminalConfigure.setTerminalmac("");
		StoreConfigure.setLongitute("");
		StoreConfigure.setLatitute("");
		SPUtils.setSharedStringData(this, "appid", "");
		SPUtils.setSharedIntData(this, "storeid", 0);
		SPUtils.setSharedIntData(this, "brandid", 0);
		SPUtils.setSharedStringData(this, "tname", "");
		SPUtils.setSharedStringData(this, "sname", "");
		SPUtils.setSharedStringData(this, "phone", "");
		SPUtils.setSharedStringData(this, "mobile", "");
		SPUtils.setSharedStringData(this, "adress", "");
		SPUtils.setSharedlongData(this, "storeEndTime", 0);
		SPUtils.setSharedStringData(this, "currentVersion", "");
		SPUtils.setSharedStringData(this, "brandName", "");
		SPUtils.setSharedStringData(this, "terminalmac", "");
		SPUtils.setSharedStringData(this, "longitute", "");
		SPUtils.setSharedStringData(this, "latitute", "");
		SPUtils.setSharedStringData(this, "currentVersion", "1");
		SPUtils.setSharedStringData(this, "versionid", "selfhelp_ordermachine");
		SPUtils.setSharedStringData(this, "kdsid", "");
		startActivityForResult(CommonSetActivity.class, 1);
	}

	@Override
	public void returnLoginResult(LoginEntity loginEntity) {
		BaseConfigure.setToken(loginEntity.getToken());
		if (SystemConfig.isSmarantSystem) {
			StoreConfigure.setAddress(loginEntity.getAddress());
			StoreConfigure.setPhone(loginEntity.getPhone());
		}
		if (!TextUtils.isEmpty(BaseConfigure.getAppid())) {
			mHandler.postDelayed(mRunnable, 1000);
		} else {
			startActivityForResult(CommonSetActivity.class, 1);
		}
	}

}
