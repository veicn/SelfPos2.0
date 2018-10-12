package com.acewill.slefpos.bean.orderbean;

import java.util.ArrayList;

public class CheckCountResp {
	//	{"result":0,"content":"","errmsg":"0","dishIDData":[{"dishid":"10003","count":0}]}

	private int             result;
	private String          content;
	private String          errmsg;
	private  ArrayList<CheckDish> dishIDData;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public ArrayList<CheckDish> getDishIDData() {
		return dishIDData;
	}

	public void setDishIDData(ArrayList<CheckDish> dishIDData) {
		this.dishIDData = dishIDData;
	}

	public class CheckDish {
		private String dishid;

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		private int count;

		public String getDishid() {
			return dishid;
		}

		public void setDishid(String dishid) {
			this.dishid = dishid;
		}
	}
}
