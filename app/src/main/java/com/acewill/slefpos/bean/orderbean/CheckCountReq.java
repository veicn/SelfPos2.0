package com.acewill.slefpos.bean.orderbean;

public class CheckCountReq {
	public String dishid;
	public int count;

	public CheckCountReq(String dishid, int count) {
		this.dishid = dishid;
		this.count = count;
	}

	public CheckCountReq() {
	}
}
