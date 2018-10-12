package com.acewill.slefpos.bean.smarantstorebean;

/**
 * Author：Anch
 * Date：2018/1/25 17:45
 * Desc：
 */
public class SelfPosConfigurationData {

	/**
	 * result : 0
	 * content : {"appid":"59841423","brandid":47,"storeid":1,"screenSaverTime":120,"showShoppingChart":false,"checkMember":true,"isScanAndInput":1,"inputTableNumber":true,"orderKDS":null,"kitchenPrint":true,"kitchenPrintPrice":false,"minTable":1,"maxTable":999999,"informKDS":false,"packageLenovo":false,"printKitchenQrcode":false,"printInvoiceQrcode":false,"printOrderQrcode":false}
	 * errmsg : 0
	 */

	private int                        result;
	private SelfposConfigurationEntity content;
	private String                     errmsg;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public SelfposConfigurationEntity getContent() {
		return content;
	}

	public void setContent(SelfposConfigurationEntity content) {
		this.content = content;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
