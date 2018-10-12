package com.acewill.slefpos.bean.syncbean.syncpay;

/**
 * Author：Anch
 * Date：2018/5/17 14:13
 * Desc：
 */
public class SyncPayResultType {
	/**
	 * 未知交易结果
	 */
	public static final String UNKNOW = "0";
	/**
	 * 交易创建
	 */
	public static final String CREATE = "1";

	/**
	 * 交易失败
	 */
	public static final String FAILED = "2";

	/**
	 * 交易成功
	 */
	public static final String SUCCESS = "3";

	/**
	 * 交易结束(不可退款)
	 */
	public static final String FINISHED = "4";

	/**
	 * 交易关闭
	 */
	public static final String CLOSE = " 5";

	/**
	 * 交易已退款
	 */
	public static final String REFUND = "6";
}
