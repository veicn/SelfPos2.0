package com.acewill.slefpos.bean.paybean;

/**
 * Author：Anch
 * Date：2018/5/4 14:04
 * Desc：
 */
public class PayResultType {
	/**
	 * 支付成功
	 */
	public static final int PAY_SUCCESS = 10000;
	/**
	 * 支付失败
	 */
	public static final int PAY_FAIL    = 20000;

	/**
	 * 支付正在处理中
	 */
	public static final int PAY_INPROGRESS = 30000;
	/**
	 * 支付出现异常
	 */
	public static final int PAY_ERROR      = 40000;
	/**
	 * 支付状态未知
	 */
	public static final int PAY_UNKNOW     = 50000;

	/**
	 * 支付超时
	 */
	public static final int PAY_TIME_OUT = 60000;

	/**
	 * 需要用户输入密码
	 */
	public static final int USER_PAYING = 70000;

}
