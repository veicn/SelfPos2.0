package com.acewill.slefpos.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.multidex.MultiDex;

import com.jaydenxiao.common.utils.logutil.FileLog;

import org.litepal.LitePalApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * APPLICATION
 */
public class BaseApplication extends LitePalApplication {

	private static BaseApplication baseApplication;

	@Override
	public void onCreate() {
		super.onCreate();
		baseApplication = this;
		initSoundPool();
	}

	private SoundPool soundPool;

	private void initSoundPool() {
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		try {
			soundPool.load(getAssets().openFd("ordersuccesssound/order_success1.mp3"), 1);
		} catch (IOException e) {
			FileLog.log(e.getMessage());
			e.printStackTrace();
		}
	}

	public void playSound() {
		soundPool.play(1, 1, 1, 100, 0, 1);
	}

	public static Context getAppContext() {
		return baseApplication;
	}

	public List<Activity> getActivityList() {
		return allActivities;
	}

	public static BaseApplication getInstance() {
		return baseApplication;
	}

	public static Resources getAppResources() {
		return baseApplication.getResources();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	/**
	 * 分包
	 *
	 * @param base
	 */
	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}

	private List<Activity> allActivities;

	public void addActivity(Activity act) {
		if (allActivities == null) {
			allActivities = new ArrayList<>();
		}
		allActivities.add(act);
	}

	/**
	 * 结束除了它之外的acitivity
	 *
	 * @param
	 */
	public void closeAllActivity() {
		for (Activity activity : allActivities) {
			if (activity != null) {
				activity.finish();
			}
		}
	}
//	/**
//	 * 获取最上面的activity
//	 *
//	 * @param activity
//	 */
//	public void getTopActivity(Activity activity) {
//		if (activity != null) {
//			removeActivity(activity);
//			activity.finish();
//			activity = null;
//		}
//	}
	/**
	 * 结束指定的Activity
	 *
	 * @param activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			removeActivity(activity);
			activity.finish();
			activity = null;
		}
	}

	public void removeActivity(Activity act) {
		if (allActivities != null) {
			allActivities.remove(act);
		}
	}

	/**
	 * 应用退出，结束所有的activity
	 */
	public void exit() {
		for (Activity activity : allActivities) {
			if (activity != null) {
				activity.finish();
			}
		}
		System.exit(0);
	}
}
