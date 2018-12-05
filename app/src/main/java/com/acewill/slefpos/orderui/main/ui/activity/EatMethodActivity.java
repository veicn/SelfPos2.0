package com.acewill.slefpos.orderui.main.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.api.ApiConstants;
import com.acewill.slefpos.app.AppApplication;
import com.acewill.slefpos.app.AppConstant;
import com.acewill.slefpos.base.BaseActivity;
import com.acewill.slefpos.bean.canxingjianbean.CxjMemberAccount;
import com.acewill.slefpos.bean.canxingjianbean.CxjMemberLoginRes;
import com.acewill.slefpos.bean.canxingjianbean.CxjOrderProvider;
import com.acewill.slefpos.bean.cartbean.Cart;
import com.acewill.slefpos.bean.cartbean.Price;
import com.acewill.slefpos.bean.orderbean.Order;
import com.acewill.slefpos.bean.syncbean.syncdish.FePosUserRes;
import com.acewill.slefpos.bean.syncbean.syncnumber.SyncOrderNumber;
import com.acewill.slefpos.bean.syncmember.SyncMember;
import com.acewill.slefpos.bean.syncmember.SyncMemberLoginReq;
import com.acewill.slefpos.bean.syncmember.SyncMemberLoginRes;
import com.acewill.slefpos.bean.wshbean.WshAccount;
import com.acewill.slefpos.bean.wshbean.WshAccountRes;
import com.acewill.slefpos.configure.BaseConfigure;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.configure.TerminalConfigure;
import com.acewill.slefpos.emuee.HostType;
import com.acewill.slefpos.helper.ScanGunKeyEventHelper;
import com.acewill.slefpos.okhttp.OkHttpUtils;
import com.acewill.slefpos.okhttp.callback.StringCallback;
import com.acewill.slefpos.orderui.main.contract.MemberContract;
import com.acewill.slefpos.orderui.main.model.MemberModel;
import com.acewill.slefpos.orderui.main.presenter.MemberPresenter;
import com.acewill.slefpos.orderui.main.ui.dialog.MemberLoginDialog2;
import com.acewill.slefpos.orderui.main.ui.dialog.PrintOrderListDialog;
import com.acewill.slefpos.orderui.main.ui.dialog.UserListDialog;
import com.acewill.slefpos.orderui.main.uidataprovider.SmarantDataProvider;
import com.acewill.slefpos.orderui.main.uidataprovider.SyncDataProvider;
import com.acewill.slefpos.orderui.main.uidataprovider.WshDataProvider;
import com.acewill.slefpos.orderui.main.uihelper.Refund;
import com.acewill.slefpos.print.ticketprint.SmarantPrintUtil;
import com.acewill.slefpos.system.ui.WelActivity;
import com.acewill.slefpos.utils.DownLoadAPPUtils;
import com.acewill.slefpos.utils.priceutils.PriceUtil;
import com.acewill.slefpos.utils.scanutils.QRCodeScanUtil;
import com.acewill.slefpos.utils.uiutils.BitmapUtil;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jaydenxiao.common.commonutils.SPUtils;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.compressorutils.FileUtil;
import com.jaydenxiao.common.utils.logutil.FileLog;
import com.jaydenxiao.common.utils.springylib.SpringAnimationType;
import com.jaydenxiao.common.utils.springylib.SpringyAnimator;

import org.litepal.LitePal;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;
import rx.functions.Action1;

/**
 * Author：Anch
 * Date：2018/1/25 11:47
 * Desc：
 */
public class EatMethodActivity extends BaseActivity<MemberPresenter, MemberModel> implements
		MemberContract.View, ScanGunKeyEventHelper.OnScanSuccessListener {

	private static final String TAG = "EatMethodActivity";
	@Bind(R.id.fra_main_tel_iv)
	ImageView fra_main_tel_iv;
	@Bind(R.id.tv_version)
	TextView  tv_version;

	private ScanGunKeyEventHelper mScanGunKeyEventHelper;
	private QRCodeScanUtil        d2xxUtil;

	@Override
	public int getLayoutId() {
		return R.layout.act_eatmethod;
	}

	@Override
	public void initPresenter() {
		mPresenter.setVM(this, mModel);
	}

	/**
	 * Activity截获按键事件.发给ScanGunKeyEventHelper
	 *
	 * @param event
	 * @return
	 */
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (mScanGunKeyEventHelper != null) {
			mScanGunKeyEventHelper.analysisKeyEvent(event);
		} else {
		}


		return super.dispatchKeyEvent(event);
	}


	@Override
	public void onUsbScanSuccess(String barcode) {
		if (payInit)
			onScanSuccess(barcode);
		else {
			FileLog.log("扫码枪未初始化!");
		}
	}

	private boolean payInit;

	private void initScanGun() {
		if (!SystemConfig.DEBUG && SPUtils.getSharedIntData(mContext, "scantype") == 0) {
			if (d2xxUtil == null)
				d2xxUtil = QRCodeScanUtil.getInstance(mContext);
			if (d2xxUtil != null) {
				d2xxUtil.startWithRetries(new QRCodeScanUtil.D2xxListner() {
					@Override
					public void d2xxListener(String code) {
						if (payInit)
							onScanSuccess(code.toString().trim());
						else {
							FileLog.log("扫码枪未初始化!!");
						}
					}
				});
			}
		} else {
			if (mScanGunKeyEventHelper == null)
				mScanGunKeyEventHelper = new ScanGunKeyEventHelper(this);
		}
	}

	public void stopDxxUtil() {
		if (d2xxUtil != null)
			d2xxUtil.stop();
	}

	/**
	 * 停止扫码枪的监听
	 */
	public void stopScanGun() {
		if (d2xxUtil != null) {
			d2xxUtil.resetListener();// stop to call
			d2xxUtil.stopThread();
		}
	}

	@Override
	public void initView(Bundle savedInstanceState) {
		init();
		autoUploadLog();
		resetCallNumber();
	}

	private void resetCallNumber() {
		String today   = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String lastday = SPUtils.getSharedStringData(mContext, "lastday");
		if (!TextUtils.isEmpty(lastday) && !today.equals(lastday)) {
			LitePal.deleteAll(SyncOrderNumber.class);
		}
		SPUtils.setSharedStringData(mContext, "lastday", today);
	}


	List<View> views = new ArrayList<>();

	@Override
	protected void onResume() {
		super.onResume();
			/*
		 * animation for main screen logo

		SpringyAnimator iconSpring       = new SpringyAnimator(SpringAnimationType.SCALEXY, 4, 2.5, 0, 1);
		SpringyAnimator iconRotateSpring = new SpringyAnimator(SpringAnimationType.ROTATION, 10, 1.5, 180, 0);
		iconSpring.setDelay(300);
		iconRotateSpring.setDelay(300);
		iconSpring.startSpring(findViewById(R.id.icon));
		iconRotateSpring.startSpring(findViewById(R.id.icon));
		* */

		/*
		 * animation spring for tabs on main screen
		 * */
		final SpringyAnimator spring = new SpringyAnimator(SpringAnimationType.TRANSLATEY, 10, 5, getResources()
				.getDisplayMetrics().heightPixels, 0);
		for (int i = 0; i < views.size(); i++) {
			final int count = i;
			views.get(i).postDelayed(new Runnable() {
				@Override
				public void run() {
					spring.startSpring(views.get(count));
				}
			}, 300 * i);
		}
		scanInit();
		initScanGun();
	}


	private void scanInit() {
		//		FileLog.log("scanInit");
		payInit = true;
	}

	private void cancelScanInit() {
		//		FileLog.log("cancelScanInit");
		payInit = false;
	}

	@Override
	protected void onPause() {
		super.onPause();
		cancelScanInit();
	}

	@Bind(R.id.tv_takein)
	TextView tv_takeIn;
	@Bind(R.id.tv_takeout)
	TextView tv_takeOut;
	@Bind(R.id.fra_main_method_tv)
	TextView fra_main_method_tv;


	private void init() {
		views.add(findViewById(R.id.btn_takein));
		views.add(findViewById(R.id.btn_takeout));

		findViewById(R.id.btn_takeout).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				eatType = 1;
				//				if (SystemConfig.DEBUG)
				if ((SystemConfig.isSmarantSystem || SystemConfig.isCanXingJianSystem) && !SmarantDataProvider
						.getSelfposConfigurationdata().getContent().isCheckMember()) {
					startMain(false);
				} else if (SmarantDataProvider
						.getSelfposConfigurationdata().getContent().isCheckMember())
					showMemberLoginDialog();
				else
					startMain(false);
			}
		});

		findViewById(R.id.btn_takein).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				eatType = 0;
				//				if (SystemConfig.DEBUG)
				if ((SystemConfig.isSmarantSystem || SystemConfig.isCanXingJianSystem) && !SmarantDataProvider
						.getSelfposConfigurationdata().getContent().isCheckMember()) {
					startMain(false);
				} else if (SmarantDataProvider
						.getSelfposConfigurationdata() != null && SmarantDataProvider
						.getSelfposConfigurationdata().getContent() != null &&
						SmarantDataProvider.getSelfposConfigurationdata().getContent()
								.isCheckMember())
					showMemberLoginDialog();
				else
					startMain(false);
				//				startActivity(new Intent(EatMethodActivity.this, TestActivity.class));
				//				else
				//					startMain(false);
			}
		});
		startActivity(WelActivity.class);

		Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/xiyuanfan.ttf");
		// 应用字体
		tv_takeIn.setTypeface(typeFace);
		tv_takeOut.setTypeface(typeFace);
		fra_main_method_tv.setTypeface(typeFace);
		List<String> bottom_ad = FileUtil.getSyncImageList("BOTTOM_AD");
		if (bottom_ad != null && bottom_ad.size() > 0) {
			Glide.with(mContext).load(bottom_ad.get(0))
					.into(fra_main_tel_iv);
		} else {
			fra_main_tel_iv.setImageDrawable(getResources().getDrawable(R.mipmap.bottom_ad));
		}
		tv_version.setText(TerminalConfigure.getTerminalmac() + " " + DownLoadAPPUtils
				.getInstance(this)
				.getAPPVersionCode2());
		initListener();
	}


	private void initListener() {
		mRxManager.on(AppConstant.MEMBERID, new Action1<String>() {
			@Override
			public void call(String code) {
				doMemberLogin(code);
			}
		});
		mRxManager.on(AppConstant.SKIPMEMBERLOGIN, new Action1<Object>() {
			@Override
			public void call(Object memberid) {
				startMain(false);
			}
		});

		fra_main_tel_iv.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (SmarantPrintUtil.getPrintOrderList().size() > 0)
					showPrintOderListDialog();
				return false;
			}
		});
	}

	//全局定义
	private              long lastClickTime         = 0L;
	private static final int  FAST_CLICK_DELAY_TIME = 3000;  // 快速点击间隔

	private void showMemberLoginDialog() {
		Order.getInstance().setMember(false);
		if (System.currentTimeMillis() - lastClickTime < FAST_CLICK_DELAY_TIME) {
			return;
		}
		lastClickTime = System.currentTimeMillis();
		// ==1 表示两者都有
		if (SystemConfig.isSmarantSystem || SystemConfig.isCanXingJianSystem) {
			WshDataProvider.setWshAccountList(null);
			showDialog();
		} else {
			SyncDataProvider.clearMemberLoginInfo();
			showDialog();
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		stopScanGun();
	}

	private MemberLoginDialog2 mMemberLoginDialog;

	/**
	 * 会员登录弹窗
	 */
	private void showDialog() {
		mMemberLoginDialog = new MemberLoginDialog2(this);
		mMemberLoginDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				if (mScanGunKeyEventHelper != null) {
					disMissMemberDialog();
					mScanGunKeyEventHelper.analysisKeyEvent(event);
				} else {
					FileLog.log("扫码枪选择端口不正确，需要选择外界扫码枪!");
				}
				return false;
			}
		});
		mMemberLoginDialog.show();
		if (SystemConfig.isSyncSystem) {
			mMemberLoginDialog.setImage(Create2QR2(getMemberShipUri()));
		}
	}

	/**
	 * 同步时会员登录测试
	 */
	private final String SYNC_MEMBER_LOGIN        = "https://insight.syncrock.com";
	/**
	 * 同步时会员登录测试
	 */
	private final String SYNC_MEMBER_LOGIN_NORMAL = "http://insight.syncpo.com";

	/**
	 * https://insight.syncrock.com/fe/?companyOuid=XX0723XXQ7yUPSS7E2S6TA&sourceChannel=B&storeOuid=LsspPjd4SWy_G4imq79yKw
	 *
	 * @return
	 */
	private String getMemberShipUri() {
		String host = HostType.IS_SYNC_DEBUG ? SYNC_MEMBER_LOGIN : SYNC_MEMBER_LOGIN_NORMAL;
		String uri = host + "/fe/?companyOuid=" + SPUtils
				.getSharedStringData(mContext, "companyOuid") + "&sourceChannel=B&storeOuid=" + SPUtils
				.getSharedStringData(mContext, "shopId");

		//		String uri = host + "/#/?companyOuid=" + SPUtils
		//				.getSharedStringData(mContext, "companyOuid");
		//		return "http://insight.syncrock.com/#/?companyOuid=1ctAaaMITEmCxb-ADb1skw";
		//		"http://insight.syncrock.com/#/?companyOuid=1ctAaaMITEmCxb-ADb1skw"
		return uri;
	}

	//生成二维码的方法
	private Bitmap Create2QR2(String urls) {
		String uri          = urls;
		int    mScreenWidth = 0;
		Bitmap bitmap;
		try {
			DisplayMetrics dm = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(dm);
			mScreenWidth = dm.widthPixels;

			bitmap = BitmapUtil.createQRCode(uri, mScreenWidth);//自己写的方法
			if (bitmap != null) {
				return bitmap;
				//				imageView.setImageBitmap(bitmap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		startActivity(new Intent(this, WelActivity.class));
		if (!SystemConfig.showChooseEatMethod) {
			finish();
		}
	}

	//	private void showExitDialog() {
	//		android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
	//		builder.setTitle("提示");
	//		builder.setMessage("确定退出点餐界面吗");
	//		builder.setNegativeButton("取消", null);
	//		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
	//			@Override
	//			public void onClick(DialogInterface dialogInterface, int i) {
	//				//								AppApplication.getAppContext().exitApp();
	//				finish();
	//			}
	//		});
	//		builder.show();
	//	}


	@Override
	public void onBackPressed() {
		if (SystemConfig.isSmarantSystem || SystemConfig.isCanXingJianSystem) {
			AppApplication.getInstance().exit();
		} else {
			FePosUserRes res = SyncDataProvider.getUserRes();
			if (res != null)
				showAdminLoginDialog(res, "eatmethod");
		}
	}

	private void showAdminLoginDialog(FePosUserRes res, String fromWho) {
		UserListDialog dialog = UserListDialog.newInstance(res, fromWho);
		dialog.setCancelable(false);
		dialog.show(getSupportFragmentManager(), "UserListDialog");
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

	@Override
	public void returnWshMemberLoginResult(WshAccountRes wshAccountRes) {
//		FileLog.log("微生活登录的信息"+new Gson().toJson(wshAccountRes));
		if (wshAccountRes != null && wshAccountRes.getWshAccountList() != null) {
			disMissMemberDialog();
			WshAccount account = wshAccountRes.getWshAccountList().get(0);
			account.setBalance(PriceUtil
					.divide(new BigDecimal(account.getBalance()), new BigDecimal("100"))
					.floatValue());
			WshDataProvider.setWshAccountList(account);
			SPUtils.setSharedStringData(mContext, "tempMemberPassword", "");
			startMain(true);
		} else if (wshAccountRes != null) {
			scanInit();
			ToastUitl.showLong(mContext, wshAccountRes.getErrmsg());
		} else {
			scanInit();
			ToastUitl.showLong(mContext, "登录失败，请重试!");
		}
	}

	@Override
	public void returnCXJMemberLoginResult(CxjMemberLoginRes wshAccountRes) {
		if (wshAccountRes != null && wshAccountRes.getData() != null && wshAccountRes.getData()
				.size() > 0) {
			disMissMemberDialog();
			CxjMemberAccount cxjMemberAccount = wshAccountRes.getData().get(0);
			cxjMemberAccount.setBalance(PriceUtil
					.divide(new BigDecimal(cxjMemberAccount.getBalance()), new BigDecimal("100"))
					.toString());
			WshDataProvider.setWshAccountList(CxjOrderProvider.getInstance()
					.toWshAccount(cxjMemberAccount));
			SPUtils.setSharedStringData(mContext, "tempMemberPassword", "");
			startMain(true);
		} else if (wshAccountRes != null) {
			scanInit();
			ToastUitl.showLong(mContext, "登录失败，请重试!");
		} else {
			scanInit();
			ToastUitl.showLong(mContext, "登录失败，请重试!");
		}
	}

	private void disMissMemberDialog() {
		if (mMemberLoginDialog != null)
			mMemberLoginDialog.dismiss();
	}

	@Override
	public void returnSyncMemberLoginResult(SyncMemberLoginRes syncMemberLoginRes) {
		stopProgressDialog();
		if (syncMemberLoginRes != null && syncMemberLoginRes.getData() != null) {
			disMissMemberDialog();
			SyncDataProvider.setSyncMemberAccount(syncMemberLoginRes.getData());
			SPUtils.setSharedStringData(mContext, "memberOuid", syncMemberLoginRes.getData()
					.getOuid());
			startMain(true);
		} else if (syncMemberLoginRes != null) {
			scanInit();
			ToastUitl.showLong(mContext, syncMemberLoginRes
					.getMessage());
		} else {
			scanInit();
			ToastUitl.showLong(mContext, "登录失败，请重试!");
		}
	}


	private int eatType;//默认堂食，如果不选择就是堂食

	private void startMain(boolean isMember) {
		disMissMemberDialog();
		Order.getInstance().setTakeOut(eatType);
		Cart.getInstance().clear();
		Order.getInstance().clear();
		Price.getInstance().clear();
		Refund.getInstance().clear();
		Price.getInstance().setDealsValueMap(null);
		Order.getInstance().setDealsMap(null);
		Order.getInstance().setMember(isMember);
		startActivity(MainActivity.class);
	}


	private void onScanSuccess(String code) {
		FileLog.log("d2xxListener>QRCODE:" + code);
		if (TextUtils.isEmpty(code)) {
			scanInit();
			return;
		}
		doMemberLogin(code);
	}

	private void doMemberLogin(String code) {
		if (SystemConfig.isSmarantSystem) {
			mPresenter.wshMemberLogin(code, 0);
		} else if (SystemConfig.isCanXingJianSystem) {
			mPresenter.wshMemberLogin(code, 1);
		} else if (SystemConfig.isSyncSystem) {
			startProgressDialog("正在登录,请稍后...");
			SyncMemberLoginReq info = SyncMember.getInstance()
					.getMemberInfo(code);
			mPresenter.syncMemberLogin(new Gson().toJson(info));
		}
	}


	/**
	 * 自动上传昨天日志
	 */
	private void autoUploadLog() {
		String lastUploadTime = SPUtils.getSharedStringData(mContext, "lastUploadTime");
		//获取当前时间
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		//是不是同一天
		if (!today.equals(lastUploadTime)) {

			File log = FileUtil.getUploadLog(1);
			uploadLog2(log);
		}

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
						Log.e(TAG, "日志自动上传成功!");
						FileLog.log("日志自动上传成功!");
						String today = new SimpleDateFormat("yyyy-MM-dd")
								.format(new Date());
						SPUtils.setSharedStringData(mContext, "lastUploadTime", today);
					}
				});
	}


	/**
	 * 补打小票
	 */
	private void showPrintOderListDialog() {
		PrintOrderListDialog dialog = PrintOrderListDialog.newInstance();
		dialog.show(getSupportFragmentManager(), "PrintOrderListDialog");
	}
}
