package com.acewill.slefpos.bean.paybean;

/**
 * Author：Anch
 * Date：2018/7/20 14:06
 * Desc：
 */
public class MeiTuanSaomaRes  {


	/**
	 * status : SUCCESS
	 * errCode : null
	 * errMsg : null
	 * subCode : null
	 * subMsg : null
	 * random : EFBMqLLNTLdgmtdkgxWCgyAscoHcDEHb
	 * sign : 3905b68f3d75722b0191c30c7b9a1f03c3e7be9073290f79e0b4d2c74b483648
	 * outTradeNo : 1011532066540258
	 * qrCode : weixin://wxpay/bizpayurl?pr=afvMQTK
	 * transactionId : null
	 * appId : wx219053dcf16a2731
	 * timeStamp : 1532066540
	 * nonceStr : 5e42d265b56b461799e5031f14cbc3c5
	 * prepayId : wx201402206394677aa8088eb80289774149
	 * signType : MD5
	 * paySign : 65CC25A95B7B899CCC1B820240D348D3
	 * packageBody : null
	 * payToken : null
	 * tradeNo : null
	 */

	private String status;
	private Object errCode;
	private Object errMsg;
	private Object subCode;
	private Object subMsg;
	private String random;
	private String sign;
	private String outTradeNo;
	private String qrCode;
	private Object transactionId;
	private String appId;
	private String timeStamp;
	private String nonceStr;
	private String prepayId;
	private String signType;
	private String paySign;
	private Object packageBody;
	private Object payToken;
	private Object tradeNo;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getErrCode() {
		return errCode;
	}

	public void setErrCode(Object errCode) {
		this.errCode = errCode;
	}

	public Object getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(Object errMsg) {
		this.errMsg = errMsg;
	}

	public Object getSubCode() {
		return subCode;
	}

	public void setSubCode(Object subCode) {
		this.subCode = subCode;
	}

	public Object getSubMsg() {
		return subMsg;
	}

	public void setSubMsg(Object subMsg) {
		this.subMsg = subMsg;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public Object getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Object transactionId) {
		this.transactionId = transactionId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

	public Object getPackageBody() {
		return packageBody;
	}

	public void setPackageBody(Object packageBody) {
		this.packageBody = packageBody;
	}

	public Object getPayToken() {
		return payToken;
	}

	public void setPayToken(Object payToken) {
		this.payToken = payToken;
	}

	public Object getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(Object tradeNo) {
		this.tradeNo = tradeNo;
	}
}
