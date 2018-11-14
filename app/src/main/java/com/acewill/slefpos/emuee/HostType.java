/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.acewill.slefpos.emuee;

/**
 * 发布新版本时候，要将 false 改成true
 */
public class HostType {


	public static final boolean IS_SMARANT_DEBUG = true;
	public static final boolean IS_SYNC_DEBUG    = true;
	/**
	 * 多少种Host类型
	 */
	public static final int     TYPE_COUNT       = 16;
	/**
	 * 正式服务器
	 */
	public static final int     NORMAL_HOST      = 1;
	/**
	 * 测试服务器
	 */
	public static final int     TEST_HOST        = 2;

	/**
	 * 同步时测试服务器
	 */
	public static final int SYNC_TEST_HOSTS   = 3;
	/**
	 * 同步时正式服务器
	 */
	public static final int SYNC_NORMAL_HOSTS = 4;
	/**
	 * KDS地址
	 */
	public static final int KDS_HOST          = 5;

	/**
	 * 同步时支付宝测试
	 */
	public static final int SYNC_ALI_PAY = 6;

	/**
	 * 同步时支付宝正式
	 */
	public static final int SYNC_ALI_PAY_NORMAL    = 7;
	/**
	 * 同步时微信支付测试
	 */
	public static final int SYNC_WECHAT_PAY        = 8;
	/**
	 * 同步时微信支付正式
	 */
	public static final int SYNC_WECHAT_PAY_NORMAL = 9;
	/**
	 * 同步时会员支付
	 */
	public static final int SYNC_MEMBER_PAY        = 10;
	/**
	 * 同步时会员支付
	 */
	public static final int SYNC_MEMBER_PAY_NORMAL = 11;

	/**
	 * 同步时下单测试
	 */
	public static final int SYNC_NEWORDER        = 12;
	/**
	 * 同步时下单正式
	 */
	public static final int SYNC_NEWORDER_NORMAL = 13;


	/**
	 * 同步时会员测试(登录和会员相关支付都是这个地址)
	 */
	public static final int SYNC_MEMBER            = 14;
	/**
	 * 同步时会员正式(登录和会员相关支付都是这个地址)
	 */
	public static final int SYNC_MEMBER_NORMAL     = 15;
	/**
	 * 吉野家地址
	 */
	public static final int PUSH_ORDER_TO_JYJ      = 16;
	/**
	 * 餐行健下载数据的地址
	 */
	public static final int CANXINGJIAIN_IP_ADRESS = 17;
	/**
	 * 同步时测试服务器
	 */
	public static final int SYNC_TEST_HOSTS2       = 18;

	/**
	 * 同步时测试服务器
	 */
	public static final int SYNC_NORMAL_HOSTS2 = 19;

	/**
	 * 同步时测试服务器
	 */
	public static final int LOCAL_HOST = 20;
}
