package com.acewill.slefpos.bean.cartbean;

/**
 * Author：Anch
 * Date：2018/2/9 16:32
 * Desc：
 */
public class StickyCartDish {

	private String kindId;

	public StickyCartDish(String title) {
		this.kindId = title;
	}

	public String getKindId() {
		return kindId;
	}

	public void setKindId(String kindId) {
		this.kindId = kindId;
	}
}
