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
	private String create_time;
	private String biz_id;

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

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getBiz_id() {
		return biz_id;
	}

	public void setBiz_id(String biz_id) {
		this.biz_id = biz_id;
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
