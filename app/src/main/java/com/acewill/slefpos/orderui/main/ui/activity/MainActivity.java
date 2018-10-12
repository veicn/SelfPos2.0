package com.acewill.slefpos.orderui.main.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.acewill.slefpos.R;
import com.acewill.slefpos.base.BaseActivity;
import com.acewill.slefpos.emuee.HostType;
import com.acewill.slefpos.orderui.main.ui.fragment.MainFragment;
import com.acewill.slefpos.print.Global;
import com.acewill.slefpos.print.PrintManager;
import com.acewill.slefpos.print.WorkService;
import com.jaydenxiao.common.commonutils.SPUtils;
import com.jaydenxiao.common.utils.logutil.FileLog;

/**
 * Author：Anch
 * Date：2018/1/31 18:02
 * Desc：
 */
public class MainActivity extends BaseActivity {


	private MainFragment mainFragment;
	private MHandler     mhandler;

	@Override
	public int getLayoutId() {
		return R.layout.act_main;
	}

	@Override
	public void initPresenter() {

	}

	@Override
	public void initView(Bundle savedInstanceState) {
		if ((HostType.IS_SYNC_DEBUG || HostType.IS_SMARANT_DEBUG) && SPUtils
				.getSharedBooleanData(mContext, "PHONE_TEST")) {
			FileLog.log("手机测试，不去获取打印权限!");
		} else {
			initPrinter();
		}
	}

	private void initPrinter() {
		startService(new Intent(this, WorkService.class));
		mhandler = new MHandler();
		PrintManager.init(mContext);
	}

	class MHandler
			extends Handler {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				/**
				 * DrawerService 的 onStartCommand会发送这个消息
				 */
				case Global.MSG_WORKTHREAD_SEND_CONNECTUSBRESULT: {
					// TODO: 2017/5/6 anch  没搞清楚这个是干什么用的
					int result = msg.arg1;
					Toast.makeText(MainActivity.this,
							(result == 1)
									? Global.toast_success
									: Global.toast_fail,
							Toast.LENGTH_SHORT)
							.show();
					WorkService.delHandler(mhandler);
					break;
				}

			}
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initFragment(savedInstanceState);
		//监听菜单显示或隐藏
	}

	private void initFragment(Bundle savedInstanceState) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		if (savedInstanceState != null) {
			mainFragment = (MainFragment) getSupportFragmentManager()
					.findFragmentByTag("mainFragment");

		} else {
			mainFragment = new MainFragment();
			transaction.add(R.id.container, mainFragment, "mainFragment");
		}
		transaction.show(mainFragment);
		transaction.commit();
	}


}
