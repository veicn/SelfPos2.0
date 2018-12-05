package com.acewill.slefpos.configure;

/**
 * Author：Anch
 * Date：2018/4/28 9:51
 * Desc：系统配置
 */
public class SystemConfig {

	public static final int System_Smarant     = 1;//定义0表示智慧快餐系统
	public static final int System_Sync        = 2;//定义1表示同步时系统
	public static final int System_CanXingJian = 3;//定义3表示餐行健系统
	public static boolean isSyncSystem;
	public static boolean isSmarantSystem;
	public static boolean isCanXingJianSystem;

	public static boolean DEBUG = false;

	public static boolean showChooseEatMethod = true;
}
