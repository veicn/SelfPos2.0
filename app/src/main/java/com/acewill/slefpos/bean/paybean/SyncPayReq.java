package com.acewill.slefpos.bean.paybean;

import com.acewill.slefpos.bean.syncbean.SyncShoppingBean;

/**
 * Author：Anch
 * Date：2018/5/10 11:35
 * Desc：
 */
public class SyncPayReq {

	/**
	 * signModel : DEBUG
	 * consumerKey : qtM74y8KQvaCaQ7gLrLAjA
	 * timestamp : 1525832430669
	 * content : {"companyOuid":"iQq1c-qUQ66Gt4HDR-oFxQ","shopId":"YpXU0wmBQEW5ryGQc6elNA","deviceId":"0365","payMode":"A","payPlatform":"alipay","shopNo":"1001","outTradeNo":"6roceWYmQSK9v3Nu5uFBqw","txNo":"28051403651805080001","bizNo":"lAmbE3ViQl61Ua1tDWTm7A","authCode":"123","totalAmount":200,"body":"雁栖湖店","codeType":"A","deviceNo":"0365","cashierNo":"cyt\t","currency":"CNY","shopping":{"bizNo":"lAmbE3ViQl61Ua1tDWTm7A","ordersNo":"lAmbE3ViQl61Ua1tDWTm7A","orderPromoAmount":0,"orderAmount":2,"totalAmount":2,"totalQty":1,"promoItems":[],"items":[{"ouid":"8xgpEpwkQv6HRSEE0HcKRQ","skuId":"22","skuName":"2","qty":1,"usableQty":1,"skuPrice":2,"promoAmount":0,"itemAmount":2,"condimentsItem":[]}],"payments":[]}}
	 * sign : b258e0c90d6dca8cea7eae18c2df3c4c
	 */

	private String signModel;
	private String      consumerKey;
	private long        timestamp;
	private ContentBean content;
	private String      sign;

	public String getSignModel() {
		return signModel;
	}

	public void setSignModel(String signModel) {
		this.signModel = signModel;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public ContentBean getContent() {
		return content;
	}

	public void setContent(ContentBean content) {
		this.content = content;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public static class ContentBean {
		/**
		 * companyOuid : iQq1c-qUQ66Gt4HDR-oFxQ
		 * shopId : YpXU0wmBQEW5ryGQc6elNA
		 * deviceId : 0365
		 * payMode : A
		 * payPlatform : alipay
		 * shopNo : 1001
		 * outTradeNo : 6roceWYmQSK9v3Nu5uFBqw
		 * txNo : 28051403651805080001
		 * bizNo : lAmbE3ViQl61Ua1tDWTm7A
		 * authCode : 123
		 * totalAmount : 200
		 * body : 雁栖湖店
		 * codeType : A
		 * deviceNo : 0365
		 * cashierNo : cyt
		 * currency : CNY
		 * shopping : {"bizNo":"lAmbE3ViQl61Ua1tDWTm7A","ordersNo":"lAmbE3ViQl61Ua1tDWTm7A","orderPromoAmount":0,"orderAmount":2,"totalAmount":2,"totalQty":1,"promoItems":[],"items":[{"ouid":"8xgpEpwkQv6HRSEE0HcKRQ","skuId":"22","skuName":"2","qty":1,"usableQty":1,"skuPrice":2,"promoAmount":0,"itemAmount":2,"condimentsItem":[]}],"payments":[]}
		 */

		private String           companyOuid;
		private String           shopId;
		private String           deviceId;
		private String           payMode;
		private String           payPlatform;
		private String           shopNo;
		private String           outTradeNo;
		private String           txNo;
		private String           bizNo;
		private String           authCode;
		private int              totalAmount;
		private String           body;
		private String           codeType;
		private String           deviceNo;
		private String           cashierNo;
		private String           currency;
		private SyncShoppingBean shopping;

		public String getCompanyOuid() {
			return companyOuid;
		}

		public void setCompanyOuid(String companyOuid) {
			this.companyOuid = companyOuid;
		}

		public String getShopId() {
			return shopId;
		}

		public void setShopId(String shopId) {
			this.shopId = shopId;
		}

		public String getDeviceId() {
			return deviceId;
		}

		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
		}

		public String getPayMode() {
			return payMode;
		}

		public void setPayMode(String payMode) {
			this.payMode = payMode;
		}

		public String getPayPlatform() {
			return payPlatform;
		}

		public void setPayPlatform(String payPlatform) {
			this.payPlatform = payPlatform;
		}

		public String getShopNo() {
			return shopNo;
		}

		public void setShopNo(String shopNo) {
			this.shopNo = shopNo;
		}

		public String getOutTradeNo() {
			return outTradeNo;
		}

		public void setOutTradeNo(String outTradeNo) {
			this.outTradeNo = outTradeNo;
		}

		public String getTxNo() {
			return txNo;
		}

		public void setTxNo(String txNo) {
			this.txNo = txNo;
		}

		public String getBizNo() {
			return bizNo;
		}

		public void setBizNo(String bizNo) {
			this.bizNo = bizNo;
		}

		public String getAuthCode() {
			return authCode;
		}

		public void setAuthCode(String authCode) {
			this.authCode = authCode;
		}

		public int getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(int totalAmount) {
			this.totalAmount = totalAmount;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

		public String getCodeType() {
			return codeType;
		}

		public void setCodeType(String codeType) {
			this.codeType = codeType;
		}

		public String getDeviceNo() {
			return deviceNo;
		}

		public void setDeviceNo(String deviceNo) {
			this.deviceNo = deviceNo;
		}

		public String getCashierNo() {
			return cashierNo;
		}

		public void setCashierNo(String cashierNo) {
			this.cashierNo = cashierNo;
		}

		public String getCurrency() {
			return currency;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}


		public SyncShoppingBean getShopping() {
			return shopping;
		}

		public void setShopping(SyncShoppingBean shopping) {
			this.shopping = shopping;
		}
	}
}
