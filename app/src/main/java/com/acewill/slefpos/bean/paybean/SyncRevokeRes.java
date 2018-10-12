package com.acewill.slefpos.bean.paybean;

/**
 * Author：Anch
 * Date：2018/7/12 13:07
 * Desc：
 */
public class SyncRevokeRes {

	/**
	 * response : {"result":{"tradeId":"8X7mYTbDQ5C-_k7WSFIKBQ","shopId":"vptKCaOtSn-ItRy4CVjt4w","deviceId":"0092","servicePlatformType":"X","payMode":"M","payPlatform":"mehub","outTradeNo":"6d6MF1tYRWudr4KUTWaTlQ","txNo":"8888201807121306523670","bizNo":"E7ezvxCISCKdt0C2MhK9_Q","authCode":"0605100005287415","totalAmount":2600,"shopActualAmount":0,"shopDiscountAmount":0,"userActualAmonut":0,"userDiscountAmount":0,"body":"奥琦玮测试门店","tradeAction":"1","tradeStatus":"5","tradeTime":20180712130653,"tradeEndTime":20180712131153,"accountNo":"0605100005287415","balance":84879,"shopNo":"1001","companyOuid":"1ctAaaMITEmCxb-ADb1skw","codeType":"A","deviceNo":"0092","cashierNo":"湖人总冠军","currency":"CNY"},"returnCode":0,"returnMessage":"成功[OK]","returnUuid":"fdf98076-c79a-427f-a315-ec7c4ce8915d"}
	 * timestamp : 1531372019713
	 * status : 200
	 */

	private ResponseBean response;
	private String timestamp;
	private int    status;

	public ResponseBean getResponse() {
		return response;
	}

	public void setResponse(ResponseBean response) {
		this.response = response;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public static class ResponseBean {
		/**
		 * result : {"tradeId":"8X7mYTbDQ5C-_k7WSFIKBQ","shopId":"vptKCaOtSn-ItRy4CVjt4w","deviceId":"0092","servicePlatformType":"X","payMode":"M","payPlatform":"mehub","outTradeNo":"6d6MF1tYRWudr4KUTWaTlQ","txNo":"8888201807121306523670","bizNo":"E7ezvxCISCKdt0C2MhK9_Q","authCode":"0605100005287415","totalAmount":2600,"shopActualAmount":0,"shopDiscountAmount":0,"userActualAmonut":0,"userDiscountAmount":0,"body":"奥琦玮测试门店","tradeAction":"1","tradeStatus":"5","tradeTime":20180712130653,"tradeEndTime":20180712131153,"accountNo":"0605100005287415","balance":84879,"shopNo":"1001","companyOuid":"1ctAaaMITEmCxb-ADb1skw","codeType":"A","deviceNo":"0092","cashierNo":"湖人总冠军","currency":"CNY"}
		 * returnCode : 0
		 * returnMessage : 成功[OK]
		 * returnUuid : fdf98076-c79a-427f-a315-ec7c4ce8915d
		 */

		private ResultBean result;
		private int    returnCode;
		private String returnMessage;
		private String returnUuid;

		public ResultBean getResult() {
			return result;
		}

		public void setResult(ResultBean result) {
			this.result = result;
		}

		public int getReturnCode() {
			return returnCode;
		}

		public void setReturnCode(int returnCode) {
			this.returnCode = returnCode;
		}

		public String getReturnMessage() {
			return returnMessage;
		}

		public void setReturnMessage(String returnMessage) {
			this.returnMessage = returnMessage;
		}

		public String getReturnUuid() {
			return returnUuid;
		}

		public void setReturnUuid(String returnUuid) {
			this.returnUuid = returnUuid;
		}

		public static class ResultBean {
			/**
			 * tradeId : 8X7mYTbDQ5C-_k7WSFIKBQ
			 * shopId : vptKCaOtSn-ItRy4CVjt4w
			 * deviceId : 0092
			 * servicePlatformType : X
			 * payMode : M
			 * payPlatform : mehub
			 * outTradeNo : 6d6MF1tYRWudr4KUTWaTlQ
			 * txNo : 8888201807121306523670
			 * bizNo : E7ezvxCISCKdt0C2MhK9_Q
			 * authCode : 0605100005287415
			 * totalAmount : 2600
			 * shopActualAmount : 0
			 * shopDiscountAmount : 0
			 * userActualAmonut : 0
			 * userDiscountAmount : 0
			 * body : 奥琦玮测试门店
			 * tradeAction : 1
			 * tradeStatus : 5
			 * tradeTime : 20180712130653
			 * tradeEndTime : 20180712131153
			 * accountNo : 0605100005287415
			 * balance : 84879
			 * shopNo : 1001
			 * companyOuid : 1ctAaaMITEmCxb-ADb1skw
			 * codeType : A
			 * deviceNo : 0092
			 * cashierNo : 湖人总冠军
			 * currency : CNY
			 */

			private String tradeId;
			private String shopId;
			private String deviceId;
			private String servicePlatformType;
			private String payMode;
			private String payPlatform;
			private String outTradeNo;
			private String txNo;
			private String bizNo;
			private String authCode;
			private int    totalAmount;
			private int    shopActualAmount;
			private int    shopDiscountAmount;
			private int    userActualAmonut;
			private int    userDiscountAmount;
			private String body;
			private String tradeAction;
			private String tradeStatus;
			private long   tradeTime;
			private long   tradeEndTime;
			private String accountNo;
			private int    balance;
			private String shopNo;
			private String companyOuid;
			private String codeType;
			private String deviceNo;
			private String cashierNo;
			private String currency;

			public String getTradeId() {
				return tradeId;
			}

			public void setTradeId(String tradeId) {
				this.tradeId = tradeId;
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

			public String getServicePlatformType() {
				return servicePlatformType;
			}

			public void setServicePlatformType(String servicePlatformType) {
				this.servicePlatformType = servicePlatformType;
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

			public int getShopActualAmount() {
				return shopActualAmount;
			}

			public void setShopActualAmount(int shopActualAmount) {
				this.shopActualAmount = shopActualAmount;
			}

			public int getShopDiscountAmount() {
				return shopDiscountAmount;
			}

			public void setShopDiscountAmount(int shopDiscountAmount) {
				this.shopDiscountAmount = shopDiscountAmount;
			}

			public int getUserActualAmonut() {
				return userActualAmonut;
			}

			public void setUserActualAmonut(int userActualAmonut) {
				this.userActualAmonut = userActualAmonut;
			}

			public int getUserDiscountAmount() {
				return userDiscountAmount;
			}

			public void setUserDiscountAmount(int userDiscountAmount) {
				this.userDiscountAmount = userDiscountAmount;
			}

			public String getBody() {
				return body;
			}

			public void setBody(String body) {
				this.body = body;
			}

			public String getTradeAction() {
				return tradeAction;
			}

			public void setTradeAction(String tradeAction) {
				this.tradeAction = tradeAction;
			}

			public String getTradeStatus() {
				return tradeStatus;
			}

			public void setTradeStatus(String tradeStatus) {
				this.tradeStatus = tradeStatus;
			}

			public long getTradeTime() {
				return tradeTime;
			}

			public void setTradeTime(long tradeTime) {
				this.tradeTime = tradeTime;
			}

			public long getTradeEndTime() {
				return tradeEndTime;
			}

			public void setTradeEndTime(long tradeEndTime) {
				this.tradeEndTime = tradeEndTime;
			}

			public String getAccountNo() {
				return accountNo;
			}

			public void setAccountNo(String accountNo) {
				this.accountNo = accountNo;
			}

			public int getBalance() {
				return balance;
			}

			public void setBalance(int balance) {
				this.balance = balance;
			}

			public String getShopNo() {
				return shopNo;
			}

			public void setShopNo(String shopNo) {
				this.shopNo = shopNo;
			}

			public String getCompanyOuid() {
				return companyOuid;
			}

			public void setCompanyOuid(String companyOuid) {
				this.companyOuid = companyOuid;
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
		}
	}
}
