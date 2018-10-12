package com.acewill.slefpos.bean.cartbean;

import java.io.Serializable;

/**
 * Author：Anch
 * Date：2017/4/30 17:49
 * Desc：
 */
public class SizeBean implements Serializable {
	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	int listSize = 0;
}
