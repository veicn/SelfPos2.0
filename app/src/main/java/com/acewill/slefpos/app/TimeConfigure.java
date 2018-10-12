package com.acewill.slefpos.app;

/**
 * Author：Anch
 * Date：2018/2/27 14:13
 * Desc：
 */
public class TimeConfigure {
	public static final int  CLOSETIMEEND       = 15;//查询订单的次数小于6(0-5,共6次)次，每次间隔5000ms ,也就是查询30s
	public static final int  CLOSETIMEGAP       = 2000;//间隔时间 ms
	public static final int  CLOSETIMESTART     = 0;//第一次发起查询等待的时间,无需等待 ms
	public static final int  MAX                = Integer.MAX_VALUE;
	public static final int  TIMEOUTGAP         = 1000;//倒计时间隔时间 ms
	public static final int  TIMEOUTEND         = 0;//倒计时间终点s
	public static final int  TIMEOUTSTART       = 30;//倒计时间起始时间s
	public static final long ORDER_SUCCESS_TIME = 5000;//支付成功后停留界面的时间
	public static       int  SCREEMPROTECTTIME  = 180;//屏保时间,单位s


	/**
	 * 切记
	 * CLOSETIMEEND * CLOSETIMEGAP = TIMEOUTSTART
	 */


	public static int CURRENTSCREENPRPOTECTTIME = -1;//屏保倒计时时间

	/**
	 * 重置屏保时间
	 */
	public static void resetScreenTime() {
		CURRENTSCREENPRPOTECTTIME = SCREEMPROTECTTIME;
	}
}
