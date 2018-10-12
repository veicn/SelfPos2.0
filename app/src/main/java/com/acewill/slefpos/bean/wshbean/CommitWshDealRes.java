package com.acewill.slefpos.bean.wshbean;

/**
 * Author：Anch
 * Date：2017/6/8 10:34
 * Desc：
 */
public class CommitWshDealRes {

	/**
	 * result : 0
	 * content : {"tcId":"1569602053646724","verify_sms":false,"verify_password":true}
	 * errmsg : 0
	 */

	/**
	 * 错误
	 * {"result":3007,"content":null,"errmsg":"账户余额不足"}
	 */
	private int result;
	private ContentBean content;
	private String      errmsg;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public ContentBean getContent() {
		return content;
	}

	public void setContent(ContentBean content) {
		this.content = content;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public static class ContentBean {
		public String getDeal_id() {
			return deal_id;
		}

		public void setDeal_id(String deal_id) {
			this.deal_id = deal_id;
		}

		/**
		 * tcId : 1569602053646724
		 * verify_sms : false
		 * verify_password : true
		 */

		private String deal_id;
	}
}
