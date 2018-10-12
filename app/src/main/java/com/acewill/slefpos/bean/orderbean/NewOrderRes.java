package com.acewill.slefpos.bean.orderbean;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/5/8 10:24
 * Desc：
 */
public class NewOrderRes {
	private int     result;
	private String  errmsg;
	private Content content;

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

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public static class Content {
		private String         callNumber;
		private String         id;
		private List<OrderDish> itemList;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public List<OrderDish> getItemList() {
			return itemList;
		}

		public void setItemList(List<OrderDish> itemList) {
			this.itemList = itemList;
		}

		public String getCallNumber() {
			return callNumber;
		}

		public void setCallNumber(String callNumber) {
			this.callNumber = callNumber;
		}
	}
}
