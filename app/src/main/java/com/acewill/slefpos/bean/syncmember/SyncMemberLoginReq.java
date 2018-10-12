package com.acewill.slefpos.bean.syncmember;

import com.acewill.slefpos.bean.syncbean.SyncShoppingBean;

/**
 * Author：Anch
 * Date：2018/5/30 11:14
 * Desc：
 */
public class SyncMemberLoginReq {
	//	{"companyOuid":"1ctAaaMITEmCxb-ADb1skw","consumerKey":"2FMf6HR9RDOCN0tL7QHJag","data":{"authCode":"13822540060","shopId":"vptKCaOtSn-ItRy4CVjt4w","shopping":{"items":[{"skuId":"Qb9ztkTfTQK5YGLSPczM4w","promoAmount":1,"qty":1,"itemAmount":1}],"orderAmount":1,"orderPromoAmount":0}}}
	/**
	 * consumerKey : 【接口提供方给出】
	 * companyOuid : 【同步时提供】
	 * data : {"authCode":"【会员卡号/会员手机号】","shopId":"【门店编号】","shopping":{"orderPromoAmount":1,"orderAmount":1,"items":[{"skuId":"【商品编号】","qty":1,"promoAmount":1,"itemAmount":1}]}}
	 */

	private String   consumerKey;
	private String   companyOuid;
	private DataBean data;

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getCompanyOuid() {
		return companyOuid;
	}

	public void setCompanyOuid(String companyOuid) {
		this.companyOuid = companyOuid;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * authCode : 【会员卡号/会员手机号】
		 * shopId : 【门店编号】
		 * shopping : {"orderPromoAmount":1,"orderAmount":1,"items":[{"skuId":"【商品编号】","qty":1,"promoAmount":1,"itemAmount":1}]}
		 */

		private String       authCode;
		private String       shopId;
		private SyncShoppingBean shopping;

		public String getAuthCode() {
			return authCode;
		}

		public void setAuthCode(String authCode) {
			this.authCode = authCode;
		}

		public String getShopId() {
			return shopId;
		}

		public void setShopId(String shopId) {
			this.shopId = shopId;
		}

		public SyncShoppingBean getShopping() {
			return shopping;
		}

		public void setShopping(SyncShoppingBean shopping) {
			this.shopping = shopping;
		}

	}
}
