package com.acewill.slefpos.bean.paybean;

/**
 * Author：Anch
 * Date：2018/4/24 11:56
 * Desc：
 */
public class BaseAliPayResult {


	/**
	 * result : 0
	 * content : {"alipay_trade_precreate_response":{"code":"10000","msg":"Success","out_trade_no":"1021525426078960","qr_code":"https:\/\/qr.alipay.com\/bax03409rxj8k5rrcufw00fc"},"sign":"SdXnu80EZ39/GVZFcocscb1OfcProXzPNV9eVhIileeZwPX7IaOul6D+mRFcW1c7bfp6+TlGkMjB0SZ/jPHucMzZDda5eGUIeLglFUX8JhOLGUoBSmFqRiCgfVQ3AFGS89nPMP/qUMCDMKNiXjVOYupN6vBIJ1LlGjNfBL9bYUw="}
	 * errmsg : 0
	 */

	private int result;
	private String content;
	private String errmsg;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
