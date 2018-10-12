package com.acewill.slefpos.bean.smarantstorebean;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/1/25 17:52
 * Desc：
 */
public class KichenStallsData {

	/**
	 * result : 0
	 * content : [{"appid":"59841423","brandid":47,"storeid":1,"stallsid":1113,"stallsName":"档口222","printerid":1234,"kdsid":-1,"summaryReceiptCounts":0,"dishReceiptCounts":1,"dishIdList":[8,9,10,11,13,14,15,16,17,19],"kitchenPrintMode":"PER_DISH"},{"appid":"59841423","brandid":47,"storeid":1,"stallsid":1121,"stallsName":"档口188","printerid":1223,"kdsid":-1,"summaryReceiptCounts":1,"dishReceiptCounts":0,"dishIdList":[1,2,3,4,5,6,12,18,20,21,22,23,24,25,26,27,28,29,30,31],"kitchenPrintMode":"PER_ITEM"}]
	 * errmsg : 0
	 */

	private int result;
	private String            errmsg;
	private List<KichenStallEntity> content;

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

	public List<KichenStallEntity> getContent() {
		return content;
	}

	public void setContent(List<KichenStallEntity> content) {
		this.content = content;
	}

}
