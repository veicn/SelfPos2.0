package com.acewill.slefpos.bean.smarantstorebean;

/**
 * Author：Anch
 * Date：2018/1/24 18:56
 * Desc：
 */
public class BaseEntity<T> {
	private String result;

	public String getResult() {
		return result;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	private T content;

	public void setResult(String result) {
		this.result = result;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	private String errmsg;

}
