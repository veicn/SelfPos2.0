package com.acewill.slefpos.bean.smarantstorebean;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/1/25 17:54
 * Desc：
 */
public class KdsData {

	/**
	 * result : 0
	 * content : [{"id":1017,"appid":"59841423","brandid":47,"storeid":1,"ip":"192.168.1.132","kdsName":"KdsEntity"}]
	 * errmsg : 0
	 */

	private int result;
	private String            errmsg;
	private List<KdsEntity> content;

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

	public List<KdsEntity> getContent() {
		return content;
	}

	public void setContent(List<KdsEntity> content) {
		this.content = content;
	}

}
