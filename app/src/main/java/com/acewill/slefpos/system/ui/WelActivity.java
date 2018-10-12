package com.acewill.slefpos.system.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.TimeConfigure;
import com.acewill.slefpos.base.BaseActivity;
import com.acewill.slefpos.bean.smarantstorebean.ImageEntity;
import com.acewill.slefpos.bean.smarantstorebean.ImagesData;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.orderui.main.contract.WelContract;
import com.acewill.slefpos.orderui.main.model.WelModel;
import com.acewill.slefpos.orderui.main.presenter.WelPresenter;
import com.acewill.slefpos.orderui.main.ui.services.ScreenProtectService;
import com.acewill.slefpos.orderui.main.uidataprovider.SmarantDataProvider;
import com.bumptech.glide.Glide;
import com.jaydenxiao.common.commonutils.SPUtils;
import com.jaydenxiao.common.commonwidget.BannerImageLoader;
import com.jaydenxiao.common.commonwidget.NewBanner;
import com.jaydenxiao.common.compressorutils.FileUtil;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Author：Anch
 * Date：2018/1/27 16:18
 * Desc：
 */
public class WelActivity extends BaseActivity<WelPresenter, WelModel> implements WelContract.View {
	private static final String TAG = "WelActivity";
	public  boolean   isBound;
	private NewBanner banner;

	@Override
	public int getLayoutId() {
		return R.layout.act_wel;
	}

	@Override
	public void initPresenter() {
		mPresenter.setVM(this, mModel);
	}

	@Override
	public void initView(Bundle savedInstanceState) {
		initWelLayout();
		initService();
		Log.e(TAG, "terminalOuid>" + SPUtils.getSharedStringData(mContext, "terminalOuid"));
		initText();
		if (SPUtils.getSharedIntData(mContext, "baseInit") == SystemConfig.System_Smarant) {
			mPresenter.goLoadMenuData();
		}
	}


	private void initWelLayout() {
		banner = (NewBanner) findViewById(R.id.order_banner);
		List<String> imagesList = new ArrayList<>();
		banner.setOnBannerListener(new OnBannerListener() {
			@Override
			public void OnBannerClick(int position) {
				TimeConfigure.CURRENTSCREENPRPOTECTTIME = -1;
				try {
					service.startTimer();
				} catch (Exception e) {
					e.printStackTrace();
				}
				finish();
			}
		});

		if ((SystemConfig.isSmarantSystem || SystemConfig.isCanXingJianSystem) && SmarantDataProvider
				.getImageList() != null) {
			for (ImageEntity entity : SmarantDataProvider.getImageList().getFiles()) {
				if ("WELCOME".equals(entity.getFiletypeKey()))
					imagesList.add(entity.getFilename());
			}
		} else if (SystemConfig.isSyncSystem) {
			List<String> welcome = FileUtil.getSyncImageList("WELCOME");
			if (welcome != null)
				imagesList.addAll(welcome);
		}
		if (imagesList != null && imagesList.size() > 0) {
			banner.setImageLoader(new BannerImageLoader(mContext
					.getResources().getDrawable(R.mipmap.default_img)));
			banner.setImages(imagesList);
		}
		//banner设置方法全部调用完毕时最后调用
		banner.setIndicatorVisible(View.GONE);
		banner.start();
	}

	private void initService() {
		Intent intent = new Intent(this, ScreenProtectService.class);
		intent.putExtra("from", "ScreenProtectedActivity_new");
		Log.e("DemoLog", "----------------------------------------------------------------------");
		Log.e("DemoLog", "ScreenProtectedActivity_new 执行 bindService");
		bindService(intent, conn, BIND_AUTO_CREATE);
	}


	//全局定义
	private              long lastClickTime         = 0L;
	private static final int  FAST_CLICK_DELAY_TIME = 1500;  // 快速点击间隔

	private void initText() {
		findViewById(R.id.screenprotected_layout).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (System.currentTimeMillis() - lastClickTime < FAST_CLICK_DELAY_TIME) {
					return;
				}
				lastClickTime = System.currentTimeMillis();

				//                exit();
				TimeConfigure.CURRENTSCREENPRPOTECTTIME = -1;
				try {
					service.startTimer();
				} catch (Exception e) {
					e.printStackTrace();
				}
				finish();
			}
		});

		Typeface typeFace1 = Typeface.createFromAsset(getAssets(), "fonts/xiyuanfan.ttf");
		Typeface typeFace2 = Typeface.createFromAsset(getAssets(), "fonts/yingwenziti.ttf");
		// 应用字体
		tv_1.setTypeface(typeFace1);
		tv_2.setTypeface(typeFace2);

		ImageView screen_bg
				= (ImageView) findViewById(R.id.iv_splash);
		ImagesData list = SmarantDataProvider.getImageList();
		if (list != null) {
			List<ImageEntity> files = list.getFiles();
			if (files != null) {
				for (ImageEntity entity : files) {
					if ("BACKGROUND_IMAGE".equals(entity.getFiletypeKey()))
						Glide.with(this).load(entity.getFilename()).error(R.mipmap.bg)
								.into(screen_bg);
				}
			}
		}

	}

	@Bind(R.id.tv_1)
	TextView tv_1;
	@Bind(R.id.tv_2)
	TextView tv_2;

	@Override
	public void loadDataSuccess() {
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

	private ScreenProtectService service;
	private ServiceConnection conn = new ServiceConnection() {


		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			isBound = true;
			ScreenProtectService.MyBinder myBinder = (ScreenProtectService.MyBinder) binder;
			service = myBinder.getService();
			Log.e("DemoLog", "ActivityA onServiceConnected");
			//			int num = service.getRandomNumber();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			isBound = false;
			Log.e("DemoLog", "ActivityA onServiceDisconnected");
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbindService(conn);
		Log.e(TAG, "onDestroy");
	}

}
