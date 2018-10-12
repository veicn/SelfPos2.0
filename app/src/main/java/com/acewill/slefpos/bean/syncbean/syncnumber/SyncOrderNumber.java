package com.acewill.slefpos.bean.syncbean.syncnumber;

import org.litepal.crud.LitePalSupport;

/**
 * Author：Anch
 * Date：2018/7/25 16:08
 * Desc：
 */
public class SyncOrderNumber  extends LitePalSupport {
	public int getOrderreq() {
		return orderreq;
	}

	public void setOrderreq(int orderreq) {
		this.orderreq = orderreq;
	}

	private int orderreq;

}
