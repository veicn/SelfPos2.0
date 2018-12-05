package com.acewill.slefpos.bean.paybean;

/**
 * Author：Anch
 * Date：2018/5/4 14:04
 * Desc：
 */
public class PayResultMsg {
	/**
	 * 支付成功
	 */
	public static final String PAY_SUCCESS = "支付成功";
	/**
	 * 支付失败
	 */
	public static final String PAY_FAIL    = "支付失败";

	/**
	 * 支付正在处理中
	 */
	public static final String PAY_INPROGRESS = "支付正在处理中";
	/**
	 * 支付出现异常
	 */
	public static final String PAY_ERROR      = "支付错误";
	/**
	 * 支付状态未知
	 */
	public static final String PAY_UNKNOW     = "支付未知异常";

	/**
	 * 支付超时
	 */
	public static final String PAY_TIME_OUT = "支付超时";
	/**
	 * 用户输入密码
	 */
	public static final String USER_PAYING  = "需要用户输入密码";

	/**
	 * 生成二维码失败
	 */
	public static final String FAIL_GENERATE_QR_CODE = "生成二维码失败";

	/**
	 * 支付完成
	 */
	public static final String PAY_COMPLETE = "支付完成";
}
