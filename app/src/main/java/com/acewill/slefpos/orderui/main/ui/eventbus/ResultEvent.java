package com.acewill.slefpos.orderui.main.ui.eventbus;

/**
 * Author：Anch
 * Date：2018/1/4 11:25
 * Desc：
 */
public class ResultEvent {
	private int requestcode;
	private int position;

	public ResultEvent(int requestcode, int position) {
		this.requestcode = requestcode;
		this.position = position;
	}

	public int getRequestcode() {
		return requestcode;
	}

	public void setRequestcode(int requestcode) {
		this.requestcode = requestcode;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
