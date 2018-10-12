package com.acewill.slefpos.bean.paybean;

/**
 * Author：Anch
 * Date：2018/5/4 13:54
 * Desc：
 */
public class PayMethod {
	/**
	 * 支付宝支付
	 */
	public static final int ALI         = 1;
	/**
	 * 微信支付
	 */
	public static final int WECHAT      = 2;
	/**
	 * 微生活储值支付
	 */
	public static final int CHUZHI      = 3;
	/**
	 * 微生活优惠券支付
	 */
	public static final int YOUHUIQUAN  = 4;
	/**
	 * 微生活积分支付
	 */
	public static final int JIFEN       = 5;
	/**
	 * 美团券支付
	 */
	public static final int MEITUANQUAN = -32;
	/**
	 * 美团支付
	 */
	public static final int MEITUAN     = -35;
	/**
	 * 菜品券支付
	 */
	public static final int CAIPINQUAN  = -33;
	/**
	 * 威富通支付
	 */
	public static final int SWIFF       = 0;
	/**
	 * 同步时会员储值支付
	 */
	public static final int SYNCBALANCE = 100;
	/**
	 * 同步时会员储值支付
	 */
	public static final int SYNCPOINT   = 101;
	/**
	 * 同步时会员储值支付
	 */
	public static final int SYNCCOUPON  = 102;
	/**
	 * 微生活的统称
	 */
	public static final int WSH         = 132123;

	/**
	 * 餐行健微信支付
	 */
	public static final int CXJ_WECHAT = -16;
	/**
	 * 餐行健支付宝支付
	 */
	public static final int CXJ_ALI    = -18;

	/**
	 * 餐行健积分
	 */
	public static final int CXJ_POINT   = -25;

	/**
	 * 餐行健储值
	 */
	public static final int CXJ_BALANCE = -11;

	/**
	 * 餐行健优惠券
	 */
	public static final int CXJ_COUPON = -23;
	/**
	 * 赠券免找
	 */
	public static final int CXJ_FREE_COUPON = -19;
}
