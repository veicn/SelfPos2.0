package com.acewill.slefpos.base;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;

import java.lang.ref.WeakReference;

/**
 * Author：Anch
 * Date：2018/5/4 15:13
 * Desc：
 */
public abstract class BaseHandler extends Handler {

	protected WeakReference<Activity> activityWeakReference;
	protected WeakReference<Fragment> fragmentWeakReference;

	private BaseHandler() {
	}//构造私有化,让调用者必须传递一个Activity 或者 Fragment的实例

	public BaseHandler(Activity activity) {
		this.activityWeakReference = new WeakReference<>(activity);
	}

	public BaseHandler(Fragment fragment) {
		this.fragmentWeakReference = new WeakReference<>(fragment);
	}

	@Override
	public void handleMessage(Message msg) {
		boolean isActivity = activityWeakReference == null || activityWeakReference
				.get() == null || activityWeakReference.get().isFinishing();
		boolean isFragment = fragmentWeakReference == null || fragmentWeakReference
				.get() == null || fragmentWeakReference.get().isRemoving();
		if (isActivity || isFragment) {
			handleMessage(msg, msg.what);
		} else {
			handleMessage(msg, What.ACTIVITY_GONE);
		}
	}

	/**
	 * 抽象方法用户实现,用来处理具体的业务逻辑
	 *
	 * @param msg
	 * @param what {@link What}
	 */
	public abstract void handleMessage(Message msg, int what);


	/**
	 * 用于规范 Message.what此属性,避免出现魔法数字
	 */
	public final class What {

		public static final int ZERO = 0;
		public static final int ONE  = 1;

		/**
		 * 标记异步操作返回时目标界面已经消失时的处理状态
		 */
		public static final int ACTIVITY_GONE = -1;
	}
}
