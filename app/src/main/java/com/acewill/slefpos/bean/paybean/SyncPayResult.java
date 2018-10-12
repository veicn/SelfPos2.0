package com.acewill.slefpos.bean.paybean;

/**
 * Author：Anch
 * Date：2018/5/5 16:22
 * Desc：
 */
public class SyncPayResult {

	/**
	 *
	 * {"response":{"returnCode":1000,"returnMessage":"入参校验异常[无有效的支付配置]","returnUuid":"5c028ddb-221c-4fa0-bdd4-b97ba47ced53"},"timestamp":"1533190252953","status":200}
	 *
	 * response : {"result":{"tradeId":"2yzQRi0aS52NKi8jfpE6hQ","shopId":"jMcFjdTVQFyOQQXNk8sR9w","deviceId":"0016","servicePlatformType":"2","payMode":"A","payPlatform":"alipay","outTradeNo":"863PoxHtRzWs9CeR6GYv2A","txNo":"286149165240381169","bizNo":"lsfiz03QQlacbul8pxF2wA","tradeNo":"2018051421001004290548104261","authCode":"286149165240381169","totalAmount":1,"shopActualAmount":1,"shopDiscountAmount":0,"userActualAmonut":1,"userDiscountAmount":0,"body":"点餐机测试","tradeAction":"1","tradeStatus":"3","tradeTime":20180514103113,"tradeEndTime":20180514103413,"failMsg":"支付成功","accountNo":"2088702226137295","shopNo":"1000","companyOuid":"1ctAaaMITEmCxb-ADb1skw","codeType":"A","deviceNo":"0016","cashierNo":"L","currency":"CNY","payConfigOuid":"-JwKSxGHQ8ufU88bJ78mrw"},"returnCode":0,"returnMessage":"成功[OK]","returnUuid":"df24d7a6-0b93-4f8a-ba91-524be254c1bc"}
	 * timestamp : 1526265074631
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
		 * result : {"tradeId":"2yzQRi0aS52NKi8jfpE6hQ","shopId":"jMcFjdTVQFyOQQXNk8sR9w","deviceId":"0016","servicePlatformType":"2","payMode":"A","payPlatform":"alipay","outTradeNo":"863PoxHtRzWs9CeR6GYv2A","txNo":"286149165240381169","bizNo":"lsfiz03QQlacbul8pxF2wA","tradeNo":"2018051421001004290548104261","authCode":"286149165240381169","totalAmount":1,"shopActualAmount":1,"shopDiscountAmount":0,"userActualAmonut":1,"userDiscountAmount":0,"body":"点餐机测试","tradeAction":"1","tradeStatus":"3","tradeTime":20180514103113,"tradeEndTime":20180514103413,"failMsg":"支付成功","accountNo":"2088702226137295","shopNo":"1000","companyOuid":"1ctAaaMITEmCxb-ADb1skw","codeType":"A","deviceNo":"0016","cashierNo":"L","currency":"CNY","payConfigOuid":"-JwKSxGHQ8ufU88bJ78mrw"}
		 * returnCode : 0
		 * returnMessage : 成功[OK]
		 * returnUuid : df24d7a6-0b93-4f8a-ba91-524be254c1bc
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
			 * tradeId : 2yzQRi0aS52NKi8jfpE6hQ
			 * shopId : jMcFjdTVQFyOQQXNk8sR9w
			 * deviceId : 0016
			 * servicePlatformType : 2
			 * payMode : A
			 * payPlatform : alipay
			 * outTradeNo : 863PoxHtRzWs9CeR6GYv2A
			 * txNo : 286149165240381169
			 * bizNo : lsfiz03QQlacbul8pxF2wA
			 * tradeNo : 2018051421001004290548104261
			 * authCode : 286149165240381169
			 * totalAmount : 1
			 * shopActualAmount : 1
			 * shopDiscountAmount : 0
			 * userActualAmonut : 1
			 * userDiscountAmount : 0
			 * body : 点餐机测试
			 * tradeAction : 1
			 * tradeStatus : 3
			 * tradeTime : 20180514103113
			 * tradeEndTime : 20180514103413
			 * failMsg : 支付成功
			 * accountNo : 2088702226137295
			 * shopNo : 1000
			 * companyOuid : 1ctAaaMITEmCxb-ADb1skw
			 * codeType : A
			 * deviceNo : 0016
			 * cashierNo : L
			 * currency : CNY
			 * payConfigOuid : -JwKSxGHQ8ufU88bJ78mrw
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
			private String tradeNo;
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
			private String failMsg;
			private String accountNo;
			private String shopNo;
			private String companyOuid;
			private String codeType;
			private String deviceNo;
			private String cashierNo;
			private String currency;
			private String payConfigOuid;

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

			public String getTradeNo() {
				return tradeNo;
			}

			public void setTradeNo(String tradeNo) {
				this.tradeNo = tradeNo;
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

			public String getFailMsg() {
				return failMsg;
			}

			public void setFailMsg(String failMsg) {
				this.failMsg = failMsg;
			}

			public String getAccountNo() {
				return accountNo;
			}

			public void setAccountNo(String accountNo) {
				this.accountNo = accountNo;
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

			public String getPayConfigOuid() {
				return payConfigOuid;
			}

			public void setPayConfigOuid(String payConfigOuid) {
				this.payConfigOuid = payConfigOuid;
			}
		}
	}
}
