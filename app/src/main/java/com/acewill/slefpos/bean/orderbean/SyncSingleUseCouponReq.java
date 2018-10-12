package com.acewill.slefpos.bean.orderbean;

import com.acewill.slefpos.bean.syncbean.SyncShoppingBean;

/**
 * Author：Anch
 * Date：2018/6/1 18:00
 * Desc：
 */
class SyncSingleUseCouponReq {

	/**
	 * consumerKey : 【接口提供方给出】
	 * companyOuid : 【同步时提供】
	 * data : {"shopId":"【门店编号】","memberNo":"【会员号】","exchangeNo":"【用券交易号，唯一，调用方产生】","couponNo":"【券号】","shopping":{"orderPromoAmount":1,"orderAmount":1,"items":[{"skuId":"【商品编号】","qty":1,"promoAmount":1,"itemAmount":1}]}}
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
		 * shopId : 【门店编号】
		 * memberNo : 【会员号】
		 * exchangeNo : 【用券交易号，唯一，调用方产生】
		 * couponNo : 【券号】
		 * shopping : {"orderPromoAmount":1,"orderAmount":1,"items":[{"skuId":"【商品编号】","qty":1,"promoAmount":1,"itemAmount":1}]}
		 */

		private String       shopId;
		private String       memberNo;
		private String       exchangeNo;
		private String       couponNo;
		private SyncShoppingBean shopping;

		public String getShopId() {
			return shopId;
		}

		public void setShopId(String shopId) {
			this.shopId = shopId;
		}

		public String getMemberNo() {
			return memberNo;
		}

		public void setMemberNo(String memberNo) {
			this.memberNo = memberNo;
		}

		public String getExchangeNo() {
			return exchangeNo;
		}

		public void setExchangeNo(String exchangeNo) {
			this.exchangeNo = exchangeNo;
		}

		public String getCouponNo() {
			return couponNo;
		}

		public void setCouponNo(String couponNo) {
			this.couponNo = couponNo;
		}

		public SyncShoppingBean getShopping() {
			return shopping;
		}

		public void setShopping(SyncShoppingBean shopping) {
			this.shopping = shopping;
		}

	}
}
