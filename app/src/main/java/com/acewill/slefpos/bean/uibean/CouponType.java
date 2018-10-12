package com.acewill.slefpos.bean.uibean;

/**
 * Author：Anch
 * Date：2018/6/6 18:08
 * Desc：自己建立的模型
 */
public class CouponType {
	private String type;

	public CouponType(String type, int size, boolean b) {
		this.type = type;
		this.type_size = size;
		this.isSelect = b;
	}

	public CouponType() {
	}

	public int getType_size() {
		return type_size;
	}

	public void setType_size(int type_size) {
		this.type_size = type_size;
	}

	private int type_size;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean select) {
		isSelect = select;
	}

	private boolean isSelect;


	//
}
