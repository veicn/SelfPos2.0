package com.acewill.slefpos.base;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.acewill.slefpos.app.TimeConfigure;
import com.acewill.slefpos.utils.permission.IPermission;
import com.jaydenxiao.common.BuildConfig;
import com.jaydenxiao.common.R;
import com.jaydenxiao.common.baseapp.AppManager;
import com.jaydenxiao.common.baserx.RxManager;
import com.jaydenxiao.common.commonutils.TUtil;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.LoadingDialog;
import com.jaydenxiao.common.commonwidget.StatusBarCompat;
import com.jaydenxiao.common.daynightmodeutils.ChangeModeController;
import com.umeng.analytics.MobclickAgent;
import com.wevey.selector.dialog.DialogOnClickListener;
import com.wevey.selector.dialog.MDAlertDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 基类
 */

/***************使用例子*********************/
//1.mvp模式
//public class SampleActivity extends BaseActivity<NewsChanelPresenter, NewsChannelModel>implements NewsChannelContract.View {
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//        mPresenter.setVM(this, mModel);
//    }
//
//    @Override
//    public void initView() {
//    }
//}
//2.普通模式
//public class SampleActivity extends BaseActivity {
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//    }
//
//    @Override
//    public void initView() {
//    }
//}
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends
		AppCompatActivity {
	public T         mPresenter;
	public E         mModel;
	public Context   mContext;
	public RxManager mRxManager;
	private boolean isConfigChange = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		BaseApplication.getInstance().addActivity(this);
		isConfigChange = false;
		mRxManager = new RxManager();
		doBeforeSetcontentView();
		setContentView(getLayoutId());
		ButterKnife.bind(this);
		mContext = this;
		mPresenter = TUtil.getT(this, 0);
		mModel = TUtil.getT(this, 1);
		if (mPresenter != null) {
			mPresenter.mContext = this;
		}
		this.initPresenter();
		this.initView(savedInstanceState);
	}

	/**
	 * 设置layout前配置
	 */
	private void doBeforeSetcontentView() {
		//设置昼夜主题
		initTheme();
		// 把actvity放到application栈中管理
		AppManager.getAppManager().addActivity(this);
		// 无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

//		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
			//去掉Activity上面的状态栏
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		} else {
//			// 默认着色状态栏
//			SetStatusBarColor();
//		}
		//隐藏底下操作按钮
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		getWindow().getDecorView()
				.setSystemUiVisibility(View.GONE);
	}

	/*********************子类实现*****************************/
	//获取布局文件
	public abstract int getLayoutId();

	//简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
	public abstract void initPresenter();

	//初始化view
	public abstract void initView(Bundle savedInstanceState);


	/**
	 * 设置主题
	 */
	private void initTheme() {
		ChangeModeController.setTheme(this, R.style.DayTheme, R.style.NightTheme);
	}

	/**
	 * 着色状态栏（4.4以上系统有效）
	 */
	protected void SetStatusBarColor() {
		StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.main_color));
	}

	/**
	 * 着色状态栏（4.4以上系统有效）
	 */
	protected void SetStatusBarColor(int color) {
		StatusBarCompat.setStatusBarColor(this, color);
	}

	/**
	 * 沉浸状态栏（4.4以上系统有效）
	 */
	protected void SetTranslanteBar() {
		StatusBarCompat.translucentStatusBar(this);
	}


	/**
	 * 通过Class跳转界面
	 **/
	public void startActivity(Class<?> cls) {
		startActivity(cls, null);
	}

	/**
	 * 通过Class跳转界面
	 **/
	public void startActivityForResult(Class<?> cls, int requestCode) {
		startActivityForResult(cls, null, requestCode);
	}

	/**
	 * 含有Bundle通过Class跳转界面
	 **/
	public void startActivityForResult(Class<?> cls, Bundle bundle,
	                                   int requestCode) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivityForResult(intent, requestCode);
	}

	/**
	 * 含有Bundle通过Class跳转界面
	 **/
	public void startActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	/**
	 * 开启浮动加载进度条
	 */
	public void startProgressDialog() {
		LoadingDialog.showDialogForLoading(this);
	}

	/**
	 * 开启浮动加载进度条
	 *
	 * @param msg
	 */
	public void startProgressDialog(String msg) {
		LoadingDialog.showDialogForLoading(this, msg, false);
	}

	/**
	 * 停止浮动加载进度条
	 */
	public void stopProgressDialog() {
		LoadingDialog.cancelDialogForLoading();
	}

	/**
	 * 短暂显示Toast提示(来自String)
	 **/
	public void showShortToast(String text) {
		ToastUitl.showShort(mContext, text);
	}

	/**
	 * 短暂显示Toast提示(id)
	 **/
	public void showShortToast(int resId) {
		ToastUitl.showShort(mContext, resId);
	}

	/**
	 * 长时间显示Toast提示(来自res)
	 **/
	public void showLongToast(int resId) {
		ToastUitl.showLong(mContext, resId);
	}

	/**
	 * 长时间显示Toast提示(来自String)
	 **/
	public void showLongToast(String text) {
		ToastUitl.showLong(mContext, text);
	}

	/**
	 * 带图片的toast
	 *
	 * @param text
	 * @param res
	 */
	public void showToastWithImg(String text, int res) {
		ToastUitl.showToastWithImg(mContext, text, res);
	}

	/**
	 * 网络访问错误提醒
	 */
	public void showNetErrorTip() {
		ToastUitl.showToastWithImg(mContext, getText(R.string.net_error)
				.toString(), R.drawable.ic_wifi_off);
	}

	public void showNetErrorTip(String error) {
		ToastUitl.showToastWithImg(mContext, error, R.drawable.ic_wifi_off);
	}

	@Override
	protected void onResume() {
		super.onResume();
		//debug版本不统计crash
		if (!BuildConfig.LOG_DEBUG) {
			//友盟统计
			MobclickAgent.onResume(this);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		//debug版本不统计crash
		if (!BuildConfig.LOG_DEBUG) {
			//友盟统计
			MobclickAgent.onPause(this);
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		isConfigChange = true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mPresenter != null)
			mPresenter.onDestroy();
		if (mRxManager != null) {
			mRxManager.clear();
		}
		if (!isConfigChange) {
			AppManager.getAppManager().finishActivity(this);
		}
		ButterKnife.unbind(this);

	}

	private static Activity getTopActivity() {

		if (BaseApplication.getInstance().getActivityList()
				.isEmpty()) {
			return null;
		} else {
			return BaseApplication.getInstance().getActivityList()
					.get(BaseApplication.getInstance().getActivityList()
							.size() - 1);
		}
	}

	private static IPermission mListener;

	public static void requestRunTimePermission(String[] permissions, IPermission listener) {
		Activity topActivity = getTopActivity();

		if (topActivity == null) {
			return;
		}
		mListener = listener;
		List<String> permissionList = new ArrayList<>();
		for (String permission : permissions) {
			if (ContextCompat.checkSelfPermission(topActivity,
					permission) != PackageManager.PERMISSION_GRANTED) {
				permissionList.add(permission);
			}
		}
		if (!permissionList.isEmpty()) {
			ActivityCompat.requestPermissions(topActivity,
					permissionList.toArray(new String[permissionList.size()]),
					REQUEST_CODE);
		} else {
			//doSomething
			mListener.onGranted();
		}
	}

	private final static int REQUEST_CODE = 1;

	@Override
	public void onRequestPermissionsResult(int requestCode,
	                                       @NonNull String[] permissions,
	                                       @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		switch (requestCode) {
			case REQUEST_CODE:
				if (grantResults.length > 0) {
					List<String> deniedPermissions = new ArrayList<>();
					for (int i = 0; i < grantResults.length; i++) {
						int    grantResult = grantResults[i];
						String permission  = permissions[i];
						if (grantResult != PackageManager.PERMISSION_GRANTED) {
							deniedPermissions.add(permission);
						}
					}
					if (deniedPermissions.isEmpty()) {
						mListener.onGranted();
					} else {
						mListener.onDenied(deniedPermissions);
					}
				}
				break;
			default:
				break;
		}
	}


	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		TimeConfigure.resetScreenTime();
		return super.dispatchTouchEvent(ev);
	}

	private MDAlertDialog alertDialog;

	protected void showAleartDialog(String title, String msg) {
		MDAlertDialog.Builder builder = new MDAlertDialog.Builder(mContext);
		builder.setTitleText(title);
		builder.setTitleTextColor(R.color.main_color_red);
		builder.setContentText(msg);
		builder.setContentTextSize(20);
		builder.setOnclickListener(new DialogOnClickListener() {
			@Override
			public void clickLeftButton(View view) {
				alertDialog.dismiss();
			}

			@Override
			public void clickRightButton(View view) {
				alertDialog.dismiss();
			}
		});
		alertDialog = new MDAlertDialog(builder);
		alertDialog.hideLeftButton();
		alertDialog.show();
	}
}
