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
package com.acewill.slefpos.api;

import com.acewill.slefpos.app.AppApplication;
import com.acewill.slefpos.emuee.HostType;
import com.jaydenxiao.common.commonutils.SPUtils;

public class ApiConstants {

	/**
	 * 本地服务测试
	 */
	public static final boolean USE_LOCAL_HOST = false;

	/**
	 * 本地服务测试
	 */
	public static final String LOCAL_HOST = "http://192.168.1.118:8080/";
	/**
	 * 智慧快餐测试后台
	 */
	public static final String TEST_HOST  = USE_LOCAL_HOST ? LOCAL_HOST : "http://sz.canxingjian.com/";

	/**
	 * 智慧快餐正式后台
	 */
	public static final String NORMAL_HOST    = "http://www.smarant.com:8080/";
	/**
	 * 同步时测试后台
	 */
	public static final String SYNC_TEST_HOST = "https://dexio.syncrock.com/";

	/**
	 * 同步时测试后台2
	 */
	public static final String SYNC_TEST_HOST2 = "http://syncapi.syncrock.com/";

	/**
	 * 同步时正式后台2
	 */
	public static final  String SYNC_NORMAL_HOST2        = "http://syncapi.syncpo.com/";
	/**
	 * 同步时正式后台
	 */
	public static final  String SYNC_NORMAL_HOST         = "https://dexio.syncpo.com/";
	/**
	 * 同步时支付宝测试
	 */
	public static final  String SYNC_ALI_PAY             = "http://alipay.syncrock.com/";
	/**
	 * 同步时支付宝正式
	 */
	public static final  String SYNC_ALI_PAY_NORMAL      = "http://alipay.syncpo.com/";
	/**
	 * 同步时微信支付测试
	 */
	public static final  String SYNC_WECHAT_PAY          = "http://wxpay.syncrock.com";
	/**
	 * 同步时微信支付测试
	 */
	public static final  String SYNC_WECHAT_PAY_NORMAL   = "http://wxpay.syncpo.com";
	/**
	 * 同步时会员支付测试
	 */
	public static final  String SYNC_MEMBER_PAY          = "http://pay.syncrock.com";
	/**
	 * 同步时会员支付测试
	 */
	public static final  String SYNC_MEMBER_PAY_NORMAL   = "http://pay.syncpo.com";
	/**
	 * 同步时支付测试
	 */
	private static final String SYNC_NEWORDER            = "https://link.syncrock.com";
	/**
	 * 同步时支付测试
	 */
	private static final String SYNC_NEWORDER_NORMAL     = "https://link.syncpo.com";
	/**
	 * 同步时会员登录测试
	 */
	public static final  String SYNC_MEMBER_LOGIN        = "https://insight.syncrock.com";
	/**
	 * 同步时会员登录测试
	 */
	public static final  String SYNC_MEMBER_LOGIN_NORMAL = "https://insight.syncpo.com";

	public static String getKds() {
		return kds;
	}

	public static void setKds(String kdss) {
		kds = kdss;
	}

	public static String getCanXingJianAdress() {
		return canxingjian_adress;
	}

	public static void setCanXingJianAdress(String address) {
		canxingjian_adress = address;
	}


	//	private static String jyjAddress = "";


	private static String kds = "";

	private static String canxingjian_adress = "";

	public static int getType() {
		return type;
	}

	public static void setType(int type) {
		ApiConstants.type = type;
	}

	private static int type = 0;

	//	public static String getJyjAddress() {
	//		return jyjAddress;
	//	}
	//
	//	public static void setJyjAddress(String jyjAddress) {
	//		jyjAddress = jyjAddress;
	//	}

	/**
	 * 获取对应的host
	 *
	 * @param hostType
	 * @return host
	 */
	public static String getHost(int hostType) {
		String host;
		switch (hostType) {
			case HostType.TEST_HOST:
				host = TEST_HOST;
				break;
			case HostType.LOCAL_HOST:
				host = LOCAL_HOST;
				break;
			case HostType.NORMAL_HOST:
				host = NORMAL_HOST;
				break;
			case HostType.SYNC_TEST_HOSTS:
				host = SYNC_TEST_HOST;
				break;
			case HostType.SYNC_NORMAL_HOSTS:
				host = SYNC_NORMAL_HOST;
				break;
			case HostType.KDS_HOST:
				host = getKds();
				break;
			case HostType.CANXINGJIAIN_IP_ADRESS:
				host = getCanXingJianAdress();
				break;
			case HostType.SYNC_ALI_PAY:
				host = SYNC_ALI_PAY;
				break;
			case HostType.SYNC_ALI_PAY_NORMAL:
				host = SYNC_ALI_PAY_NORMAL;
				break;
			case HostType.SYNC_NEWORDER:
				host = SYNC_NEWORDER;
				break;
			case HostType.SYNC_NEWORDER_NORMAL:
				host = SYNC_NEWORDER_NORMAL;
				break;
			case HostType.SYNC_WECHAT_PAY:
				host = SYNC_WECHAT_PAY;
				break;
			case HostType.SYNC_MEMBER_PAY:
				host = SYNC_MEMBER_PAY;
				break;
			case HostType.SYNC_MEMBER_PAY_NORMAL:
				host = SYNC_MEMBER_PAY_NORMAL;
				break;
			case HostType.SYNC_WECHAT_PAY_NORMAL:
				host = SYNC_WECHAT_PAY_NORMAL;
				break;
			case HostType.PUSH_ORDER_TO_JYJ:
				host = SPUtils.getSharedStringData(AppApplication.getContext(), "jyjAddress");
				break;
			case HostType.SYNC_MEMBER:
				host = SYNC_MEMBER_LOGIN;
				break;
			case HostType.SYNC_MEMBER_NORMAL:
				host = SYNC_MEMBER_LOGIN_NORMAL;
				break;

			case HostType.SYNC_TEST_HOSTS2:
				host = SYNC_TEST_HOST2;
				break;

			case HostType.SYNC_NORMAL_HOSTS2:
				host = SYNC_NORMAL_HOST2;
				break;

			default:
				host = "";
				break;
		}
		return host;
	}


}
