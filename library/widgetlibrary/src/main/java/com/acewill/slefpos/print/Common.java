package com.acewill.slefpos.print;

import android.os.Environment;

import java.io.File;
import java.util.Calendar;
import java.util.TimeZone;

public class Common {//假设配置
	public static final int PRINTER_RUSH_DISH             = 0X29;
	public static final int PRINTER_EXTRA_KITCHEN_RECEIPT = 0X33;


	/**
	 * selfpos使用的，不要删除
	 */
	public static boolean isScreenServiceStart = false;


	// public static String BASE_IP = "192.168.1.109";
	// public static String port = "8080";

	// public static String BASE_URL =
	// "http://192.168.1.109:8080/AcewillPosServer/";
	//	public sFileLogtatic int LAYOUT_TYPE = 1;
	public static int LAYOUT_TYPE_LIST  = 1;
	public static int LAYOUT_TYPE_GRID  = 2;
	public static int LAYOUT_TYPE_GRID3 = 3;

	public static       int     DISH_VERSION_NUM = 0;
	public static       int     LANGUAGE         = 0;
	public static final int     CHINESE          = 0;
	public static final int     ENGLISH          = 1;
	public static       boolean printer1Init     = false;
	public static       boolean printer2Init     = false;
	public final static String  errmsg           = "网络异常!";

	//	public static int totalSpace = 48;
	//	public static int leftSpace  = 32;
	//	public static int rightSpace = 41;
	public static int totalSpace = 48;
	public static int leftSpace  = 32;
	public static int rightSpace = 41;

	/**
	 * 是否启用debug模式 true 分钱 false 实价付款
	 */
	private final static boolean isDebug                    = false;
	/**
	 * 退菜客用打印的标识
	 */
	public static final  int     PRINTER_RETREAT_DISH_GUEST = 0X65;
	/**
	 * 退菜打印的标识
	 */
	public static final  int     PRINTER_RETREAT_DISH       = 0X28;
	/**
	 * 下单打印厨房小票的标识
	 */
	public static final  int     PRINTER_KITCHEN_ORDER      = 0X25;


	public static int DAY_OF_WEEK;

	public static int screenWidth;

	public static int    screenHeigh;
	public static float  screenScale;
	public static String PRINTER_PRODUCTID;
	public static String background;


	public static int ScreenProtectTime;//屏保时间

	public static int currentScreentProtectTime = -1;//屏保倒计时时间
	public static int memberNumberInput         = 1;
	public static int tableNumberInput          = 0;
	//	public static boolean isOpenService;


	public static final String PAYED     = "PAYED";
	public static final String NOT_PAYED = "NOT_PAYED";
	public static final String EAT_IN    = "EAT_IN";
	public static final String TAKE_OUT  = "TAKE_OUT";
	public static final String SALE_OUT  = "SALE_OUT";

	public static boolean HAVA_WX;
	public static boolean HAVA_ALI;
	public static boolean HAVA_SWIFTPASS;

	public static final int    WX_PAY        = 2;
	public static final int    ALI_PAY       = 1;
	public static final int    HY_PAY        = 6;
	public static final int    MEMBER_PAY    = 7;
	public static final int    SWIFTPASS_PAY = -8;//威富通支付
	public static       String videoPath     = Environment
			.getExternalStorageDirectory() + File.separator + "diancan2.0/video";
	public static       String Error         = "error";
	public static       String Response      = "response";
	public static       String Click         = "click";
	public static       String Request       = "request";
	public static       String Exception     = "exception";
	public static       String Log           = "log";

	public static boolean isDebug() {
		return isDebug;
	}

	static {
		final Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		DAY_OF_WEEK = c.get(Calendar.DAY_OF_WEEK);
	}
}
