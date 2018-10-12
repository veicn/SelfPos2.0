package com.acewill.slefpos.bean.smarantstorebean;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/1/25 17:09
 * Desc：
 */
public class PayTypeData {


	/**
	 * result : 0
	 * content : null
	 * errmsg : 0
	 * paymentTypes : [{"id":0,"appId":"59841423","brandId":47,"storeId":1,"name":"现金","category":"CASH","type":null,"checkBalance":true,"status":1,"extendsfromparent":0,"appIDs":"","keyStr":"","mchID":null,"subMchID":null,"appsecret":null,"filePath":null,"aliAppIDs":null,"aliKeyStr":null,"seq":0,"effectiveRealIncome":true,"realIncomeRate":null,"integral":true,"cashbox":true,"invoice":false,"display":true,"banRenamed":false,"countType":0,"numSeq":null,"statusStr":"启用","extendsfromparentStr":"否","storeNames":null},{"id":1,"appId":"59841423","brandId":47,"storeId":1,"name":"支付宝","category":"NON_CASH","type":null,"checkBalance":true,"status":1,"extendsfromparent":0,"appIDs":"2016030101175068","keyStr":"MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKOU+PitpG3Evs5SUVb6BQ/Cyw3Ef2ci1nKAIE/BYKlZ32dhJNGv3vooVf/Vja58rqO8LPRY1N00ZYtEw3P6npWLMW4QjPcCc/B0uJhKioxB5mDkxQ2MVf0G21HPesCcLilMOjhVt3AAB6OJoPn7EErTFKgWMPnEpuZXfen4MG4BAgMBAAECgYAxg30c4Ipdw2ix0M7YEdOIYsDNiQW7NRtOCyQ8n97fQ9nQU+IuKhrHA4CMcJpzD0BZRTMiPuUnk52M2yKCL4DibPZdplQ8G3TmRJiCXIzopAoxwnVCSwJCIQp7qR1T1d/6FszlrwnzPXmz+btGK5ALQUJsUnMp3pyy8KGt5JGgwQJBANcoT6c319Ipf6qfVLSvoqPYQah7yTkFN5+1dolHL+UP4QlkaWP3t3ERLVSOYOAl8ttog2HyBVWqeA7JfYGlmmkCQQDColI3foQZI4j3pNjR8Co4rJmlRQLrJpURtSwhXsXAwsj3n74KFHPlvOYaPVQQhiMkhacS5i0NK54vCpJmrNPZAkEAptC+USvezTcXoLY/+odiVh5JadPvw6Hj6pPK/8yNuc+B7sJHZBafx65FsxVgzukdTjfOBZabxDuTMgPOp0I6YQJBAJK1uDOMchZg6sWAxM66sZi2wboKIwENvB/06KbewUFjkgjVqHIAqLvrf6cXw14RPjwxYpakWiErV7ktxt0OOUECQH1E5jPbStwhb1RAZ2v/Z5CC9jezximA6eoDW3g3nzjlqDJ0VnKfYCVFkk9jE4Cx++22XQKo2j1jBbpRbLBEv10=","mchID":null,"subMchID":null,"appsecret":"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCjlPj4raRtxL7OUlFW+gUPwssNxH9nItZygCBPwWCpWd9nYSTRr976KFX/1Y2ufK6jvCz0WNTdNGWLRMNz+p6VizFuEIz3AnPwdLiYSoqMQeZg5MUNjFX9BttRz3rAnC4pTDo4VbdwAAejiaD5+xBK0xSoFjD5xKbmV33p+DBuAQIDAQAB","filePath":null,"aliAppIDs":null,"aliKeyStr":null,"seq":0,"effectiveRealIncome":false,"realIncomeRate":null,"integral":false,"cashbox":false,"invoice":false,"display":true,"banRenamed":false,"countType":0,"numSeq":null,"statusStr":"启用","extendsfromparentStr":"否","storeNames":null},{"id":2,"appId":"59841423","brandId":47,"storeId":1,"name":"微信","category":"NON_CASH","type":null,"checkBalance":true,"status":1,"extendsfromparent":0,"appIDs":"wx56e21a3b3e9e2f2e","keyStr":"abcdefghijklmnopqrstuvwxyz123456","mchID":"1345671601","subMchID":"","appsecret":"7011218d5ecbec25a9224fcba7b0dec7","filePath":"/wxCert/temp/20171228113001/apiclient_cert.p12","aliAppIDs":null,"aliKeyStr":null,"seq":0,"effectiveRealIncome":false,"realIncomeRate":null,"integral":false,"cashbox":false,"invoice":false,"display":true,"banRenamed":false,"countType":0,"numSeq":null,"statusStr":"启用","extendsfromparentStr":"否","storeNames":null},{"id":3,"appId":"59841423","brandId":47,"storeId":1,"name":"会员支付","category":"NON_CASH","type":"MEMBER","checkBalance":true,"status":1,"extendsfromparent":0,"appIDs":null,"keyStr":null,"mchID":null,"subMchID":null,"appsecret":null,"filePath":null,"aliAppIDs":null,"aliKeyStr":null,"seq":0,"effectiveRealIncome":false,"realIncomeRate":null,"integral":false,"cashbox":false,"invoice":false,"display":false,"banRenamed":false,"countType":0,"numSeq":null,"statusStr":"启用","extendsfromparentStr":"否","storeNames":null},{"id":4,"appId":"59841423","brandId":47,"storeId":1,"name":"会员优惠券","category":"NON_CASH","type":"COUPON","checkBalance":true,"status":1,"extendsfromparent":0,"appIDs":null,"keyStr":null,"mchID":null,"subMchID":null,"appsecret":null,"filePath":null,"aliAppIDs":null,"aliKeyStr":null,"seq":0,"effectiveRealIncome":false,"realIncomeRate":null,"integral":false,"cashbox":false,"invoice":false,"display":false,"banRenamed":false,"countType":0,"numSeq":null,"statusStr":"启用","extendsfromparentStr":"否","storeNames":null},{"id":5,"appId":"59841423","brandId":47,"storeId":1,"name":"微生活会员","category":"NON_CASH","type":"POINTS","checkBalance":true,"status":1,"extendsfromparent":0,"appIDs":null,"keyStr":null,"mchID":null,"subMchID":null,"appsecret":null,"filePath":null,"aliAppIDs":null,"aliKeyStr":null,"seq":0,"effectiveRealIncome":false,"realIncomeRate":null,"integral":false,"cashbox":false,"invoice":false,"display":true,"banRenamed":false,"countType":0,"numSeq":null,"statusStr":"启用","extendsfromparentStr":"否","storeNames":null},{"id":-33,"appId":"59841423","brandId":47,"storeId":1,"name":"微生活礼品券","category":null,"type":null,"checkBalance":false,"status":1,"extendsfromparent":0,"appIDs":null,"keyStr":null,"mchID":null,"subMchID":null,"appsecret":null,"filePath":null,"aliAppIDs":null,"aliKeyStr":null,"seq":0,"effectiveRealIncome":false,"realIncomeRate":null,"integral":false,"cashbox":false,"invoice":false,"display":false,"banRenamed":false,"countType":0,"numSeq":null,"statusStr":"启用","extendsfromparentStr":"否","storeNames":null},{"id":-9,"appId":"59841423","brandId":47,"storeId":1,"name":"Paymax微信","category":"NON_CASH","type":null,"checkBalance":true,"status":1,"extendsfromparent":1,"appIDs":"","keyStr":"","mchID":null,"subMchID":null,"appsecret":null,"filePath":null,"aliAppIDs":null,"aliKeyStr":null,"seq":0,"effectiveRealIncome":false,"realIncomeRate":null,"integral":false,"cashbox":false,"invoice":false,"display":true,"banRenamed":false,"countType":0,"numSeq":null,"statusStr":"启用","extendsfromparentStr":"是","storeNames":null},{"id":7,"appId":"59841423","brandId":47,"storeId":1,"name":"优惠券","category":"CASH","type":null,"checkBalance":true,"status":1,"extendsfromparent":0,"appIDs":null,"keyStr":null,"mchID":null,"subMchID":null,"appsecret":null,"filePath":null,"aliAppIDs":null,"aliKeyStr":null,"seq":7,"effectiveRealIncome":true,"realIncomeRate":1,"integral":true,"cashbox":true,"invoice":false,"display":true,"banRenamed":true,"countType":0,"numSeq":null,"statusStr":"启用","extendsfromparentStr":"否","storeNames":null}]
	 */

	private int result;
	private String                   errmsg;
	private List<PaymentTypesEntity> paymentTypes;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public List<PaymentTypesEntity> getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(List<PaymentTypesEntity> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}
}
