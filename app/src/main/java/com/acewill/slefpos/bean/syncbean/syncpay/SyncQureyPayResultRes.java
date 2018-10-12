package com.acewill.slefpos.bean.syncbean.syncpay;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/5/17 14:07
 * Desc：
 */
public class SyncQureyPayResultRes {

	/**
	 * response : {"result":{"trade":{"tradeId":"QKv6WBElSYiyoOP9f0akjg","shopId":"jMcFjdTVQFyOQQXNk8sR9w","deviceId":"0023","servicePlatformType":"1","payMode":"W","payPlatform":"wxpay","outTradeNo":"XAhNYOe8TI2dXDM3Noustw","txNo":"135244418266949595","bizNo":"8QOjRmPLTEqzSYtbckNjDg","authCode":"135244418266949595","totalAmount":1,"shopActualAmount":0,"shopDiscountAmount":0,"userActualAmonut":0,"userDiscountAmount":0,"body":"(1000) 点餐机测试","tradeAction":"1","tradeStatus":"5","tradeTime":20180517135650,"tradeEndTime":20180517135950,"failMsg":"支付失败,交易已经撤销,交易关闭 , 异常代码: USERPAYING, 异常描述: 需要用户输入支付密码","failCode":4999,"shopNo":"1000","companyOuid":"1ctAaaMITEmCxb-ADb1skw","codeType":"A","deviceNo":"0023","cashierNo":"L","currency":"CNY","payConfigOuid":"zQojpdfFRxOqiqsEqJiVgA"},"refunds":[]},"returnCode":0,"returnMessage":"成功[OK]","returnUuid":"0eac501b-72ec-45b9-b016-5ab7011583ce"}
	 * timestamp : 1526536675432
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
		 * result : {"trade":{"tradeId":"QKv6WBElSYiyoOP9f0akjg","shopId":"jMcFjdTVQFyOQQXNk8sR9w","deviceId":"0023","servicePlatformType":"1","payMode":"W","payPlatform":"wxpay","outTradeNo":"XAhNYOe8TI2dXDM3Noustw","txNo":"135244418266949595","bizNo":"8QOjRmPLTEqzSYtbckNjDg","authCode":"135244418266949595","totalAmount":1,"shopActualAmount":0,"shopDiscountAmount":0,"userActualAmonut":0,"userDiscountAmount":0,"body":"(1000) 点餐机测试","tradeAction":"1","tradeStatus":"5","tradeTime":20180517135650,"tradeEndTime":20180517135950,"failMsg":"支付失败,交易已经撤销,交易关闭 , 异常代码: USERPAYING, 异常描述: 需要用户输入支付密码","failCode":4999,"shopNo":"1000","companyOuid":"1ctAaaMITEmCxb-ADb1skw","codeType":"A","deviceNo":"0023","cashierNo":"L","currency":"CNY","payConfigOuid":"zQojpdfFRxOqiqsEqJiVgA"},"refunds":[]}
		 * returnCode : 0
		 * returnMessage : 成功[OK]
		 * returnUuid : 0eac501b-72ec-45b9-b016-5ab7011583ce
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
			 * trade : {"tradeId":"QKv6WBElSYiyoOP9f0akjg","shopId":"jMcFjdTVQFyOQQXNk8sR9w","deviceId":"0023","servicePlatformType":"1","payMode":"W","payPlatform":"wxpay","outTradeNo":"XAhNYOe8TI2dXDM3Noustw","txNo":"135244418266949595","bizNo":"8QOjRmPLTEqzSYtbckNjDg","authCode":"135244418266949595","totalAmount":1,"shopActualAmount":0,"shopDiscountAmount":0,"userActualAmonut":0,"userDiscountAmount":0,"body":"(1000) 点餐机测试","tradeAction":"1","tradeStatus":"5","tradeTime":20180517135650,"tradeEndTime":20180517135950,"failMsg":"支付失败,交易已经撤销,交易关闭 , 异常代码: USERPAYING, 异常描述: 需要用户输入支付密码","failCode":4999,"shopNo":"1000","companyOuid":"1ctAaaMITEmCxb-ADb1skw","codeType":"A","deviceNo":"0023","cashierNo":"L","currency":"CNY","payConfigOuid":"zQojpdfFRxOqiqsEqJiVgA"}
			 * refunds : []
			 */

			private TradeBean trade;
			private List<?> refunds;

			public TradeBean getTrade() {
				return trade;
			}

			public void setTrade(TradeBean trade) {
				this.trade = trade;
			}

			public List<?> getRefunds() {
				return refunds;
			}

			public void setRefunds(List<?> refunds) {
				this.refunds = refunds;
			}

			public static class TradeBean {
				/**
				 * tradeId : QKv6WBElSYiyoOP9f0akjg
				 * shopId : jMcFjdTVQFyOQQXNk8sR9w
				 * deviceId : 0023
				 * servicePlatformType : 1
				 * payMode : W
				 * payPlatform : wxpay
				 * outTradeNo : XAhNYOe8TI2dXDM3Noustw
				 * txNo : 135244418266949595
				 * bizNo : 8QOjRmPLTEqzSYtbckNjDg
				 * authCode : 135244418266949595
				 * totalAmount : 1
				 * shopActualAmount : 0
				 * shopDiscountAmount : 0
				 * userActualAmonut : 0
				 * userDiscountAmount : 0
				 * body : (1000) 点餐机测试
				 * tradeAction : 1
				 * tradeStatus : 5
				 * tradeTime : 20180517135650
				 * tradeEndTime : 20180517135950
				 * failMsg : 支付失败,交易已经撤销,交易关闭 , 异常代码: USERPAYING, 异常描述: 需要用户输入支付密码
				 * failCode : 4999
				 * shopNo : 1000
				 * companyOuid : 1ctAaaMITEmCxb-ADb1skw
				 * codeType : A
				 * deviceNo : 0023
				 * cashierNo : L
				 * currency : CNY
				 * payConfigOuid : zQojpdfFRxOqiqsEqJiVgA
				 */

				private String tradeId;

				public String getTradeNo() {
					return tradeNo;
				}

				public void setTradeNo(String tradeNo) {
					this.tradeNo = tradeNo;
				}

				private String tradeNo;
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
				private String failMsg;
				private int    failCode;
				private String shopNo;
				private String companyOuid;
				private String codeType;
				private String deviceNo;
				private String cashierNo;
				private String currency;
				private String payConfigOuid;
				private String accountNo;

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

				public String getFailMsg() {
					return failMsg;
				}

				public void setFailMsg(String failMsg) {
					this.failMsg = failMsg;
				}

				public int getFailCode() {
					return failCode;
				}

				public void setFailCode(int failCode) {
					this.failCode = failCode;
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

				public String getAccountNo() {
					return accountNo;
				}

				public void setAccountNo(String accountNo) {
					this.accountNo = accountNo;
				}
			}
		}
	}
}
