package com.acewill.slefpos.bean.wshbean;

/**
 * Author：Anch
 * Date：2017/6/8 10:34
 * Desc：
 */
public class CreateDealRes {

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
		/**
		 * tcId : 1569602053646724
		 * verify_sms : false
		 * verify_password : true
		 */

		private String tcId;
		private boolean verify_sms;
		private boolean verify_password;

		public String getTcId() {
			return tcId;
		}

		public void setTcId(String tcId) {
			this.tcId = tcId;
		}

		public boolean isVerify_sms() {
			return verify_sms;
		}

		public void setVerify_sms(boolean verify_sms) {
			this.verify_sms = verify_sms;
		}

		public boolean isVerify_password() {
			return verify_password;
		}

		public void setVerify_password(boolean verify_password) {
			this.verify_password = verify_password;
		}
	}
}
