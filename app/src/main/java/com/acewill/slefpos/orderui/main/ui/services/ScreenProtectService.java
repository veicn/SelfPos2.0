package com.acewill.slefpos.orderui.main.ui.services;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.acewill.slefpos.R;
import com.acewill.slefpos.app.TimeConfigure;
import com.acewill.slefpos.bean.cartbean.Cart;
import com.acewill.slefpos.bean.cartbean.Price;
import com.acewill.slefpos.bean.orderbean.Order;
import com.acewill.slefpos.configure.SystemConfig;
import com.acewill.slefpos.orderui.main.ui.activity.EatMethodActivity;
import com.acewill.slefpos.orderui.main.uidataprovider.SyncDataProvider;
import com.acewill.slefpos.orderui.main.uidataprovider.WshDataProvider;
import com.acewill.slefpos.print.Common;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.utils.logutil.FileLog;

import static java.lang.Thread.currentThread;

/**
 * Author：Anch
 * Date：2017/5/19 15:58
 * Desc：设计得有问题，这里是通过bindService的方法去开启的服务，但是每当关闭WelActivity界面的时候
 * 就去把这个服务关闭了，那么这里服务也就是停了啊， 屏保之所以能起作用，其实是因为这个线程单独运行的原因
 * ，如果程序内存不够用，那么这个线程就会被回收，那么屏保也就不起作用了。
 * <p>
 * 改进的方法应该是通过startService的方式去开启一个服务，不管WelActivity是否已经关闭，这个服务还是在运行着的
 */
public class ScreenProtectService extends Service {

	private static final String TAG           = "ScreenProtectService";
	private static final int    MSG_SHOW_TIPS = 100;

	private Runnable tipsShowRunable = new Runnable() {

		@Override
		public void run() {
			handler.sendEmptyMessage(MSG_SHOW_TIPS);
			handler.postDelayed(tipsShowRunable, 1000);
		}
	};
	//	int i = 60 * 5;

	public Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
				case MSG_SHOW_TIPS:
					//计时，如果5分钟没有操作就进入到屏保界面
					//关闭该service
					if (TimeConfigure.CURRENTSCREENPRPOTECTTIME > 0) {
						TimeConfigure.CURRENTSCREENPRPOTECTTIME--;
						if (SystemConfig.DEBUG)
							Log.e(TAG, "i==" + TimeConfigure.CURRENTSCREENPRPOTECTTIME);
					} else if (TimeConfigure.CURRENTSCREENPRPOTECTTIME == 0) {
						//						stopSelf();
						TimeConfigure.CURRENTSCREENPRPOTECTTIME--;
						Common.isScreenServiceStart = false;
						handler.removeCallbacks(tipsShowRunable);
						showScreenProtectActivity();
						if (SystemConfig.DEBUG)
							Log.e(TAG, "i==" + TimeConfigure.CURRENTSCREENPRPOTECTTIME);
					}

					if (TimeConfigure.CURRENTSCREENPRPOTECTTIME > 0 && TimeConfigure.CURRENTSCREENPRPOTECTTIME <= 10)
						ToastUitl
								.showShort(ScreenProtectService.this, "无操作 " + TimeConfigure.CURRENTSCREENPRPOTECTTIME + "s 后进入屏保页面");
					break;
			}

		}
	};

	private void showScreenProtectActivity() {
		//进入屏保的页面之前，其他的页面都要关闭，然后进入到
		Cart.getInstance().clear();
		Order.getInstance().clear();
		Price.getInstance().clear();
		SyncDataProvider.setSyncMemberAccount(null);
		WshDataProvider.setWshAccountList(null);
		Intent dialogIntent = new Intent(getBaseContext(), EatMethodActivity.class);
		dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		getApplication().startActivity(dialogIntent);
	}


	public class MyBinder extends Binder {
		public ScreenProtectService getService() {
			return ScreenProtectService.this;
		}
	}

	//通过binder实现调用者client与Service之间的通信
	private MyBinder binder = new MyBinder();

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public void onCreate() {
		super.onCreate();
		Log.e(TAG, "onCreate");
		Notification notifation = new Notification.Builder(this)
				.setContentTitle("自助点餐机")
				.setContentText("正在运行中...")
				.setSmallIcon(R.mipmap.logo)
				.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.logo))
				.build();
		NotificationManager manger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		manger.notify(0, notifation);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		FileLog.log(TAG + ">" + "onStartCommand");
		return START_NOT_STICKY;
	}

	//全局定义
	private              long lastClickTime         = 0L;
	private static final int  FAST_CLICK_DELAY_TIME = 3000;  // 快速点击间隔

	public void startTimer() {
		if (System.currentTimeMillis() - lastClickTime < FAST_CLICK_DELAY_TIME) {
			return;
		}
		lastClickTime = System.currentTimeMillis();
		if (!Common.isScreenServiceStart && TimeConfigure.CURRENTSCREENPRPOTECTTIME == -1) {
			TimeConfigure.resetScreenTime();
			Common.isScreenServiceStart = true;
			handler.postDelayed(tipsShowRunable, 1000);//每 10 秒执行一次
		} else {
			TimeConfigure.resetScreenTime();
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.e(TAG, " -> onBind, Thread: " + currentThread().getName());
		return binder;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.e(TAG, " -> onUnbind, from:" + intent.getStringExtra("from"));
		return false;
	}

	@Override
	public void onDestroy() {
		Log.e(TAG, "TestService -> onDestroy, Thread: " + currentThread().getName());
		super.onDestroy();
	}

}
