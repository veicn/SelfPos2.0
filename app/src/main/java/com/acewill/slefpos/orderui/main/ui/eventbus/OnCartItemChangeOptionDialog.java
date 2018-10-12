package com.acewill.slefpos.orderui.main.ui.eventbus;

/**
 * Author：Anch
 * Date：2018/7/12 17:54
 * Desc：
 */
public class OnCartItemChangeOptionDialog {
	private String kindId;
	private int multiQuantity;

	public OnCartItemChangeOptionDialog(String kindId, int multiQuantity) {
		this.kindId = kindId;
		this.multiQuantity = multiQuantity;
	}

	public String getKindId() {
		return kindId;
	}

	public void setKindId(String kindId) {
		this.kindId = kindId;
	}

	public int getMultiQuantity() {
		return multiQuantity;
	}

	public void setMultiQuantity(int multiQuantity) {
		this.multiQuantity = multiQuantity;
	}
}
