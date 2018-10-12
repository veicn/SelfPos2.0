package com.acewill.slefpos.bean.syncbean;

/**
 * Author：Anch
 * Date：2018/5/3 16:59
 * Desc：
 */
public class TestBean {

	/**
	 * header : {"instanceSid":"2","version":"1.0.0","clientReference":"8iMoUJCUQjGzt7KRuXw5Iw","txReference":"F9Vxc-DbTU2h6iEmSNhhmg","txTime":"2018-05-03T16:58:53.863+08:00","timeZone":"Asia/Shanghai","locale":"zh_CN","success":false,"statusCode":"401","statusDescription":"超过POS机台最大可用数量"}
	 */

	private HeaderBean header;

	public HeaderBean getHeader() {
		return header;
	}

	public void setHeader(HeaderBean header) {
		this.header = header;
	}

	public static class HeaderBean {
		/**
		 * instanceSid : 2
		 * version : 1.0.0
		 * clientReference : 8iMoUJCUQjGzt7KRuXw5Iw
		 * txReference : F9Vxc-DbTU2h6iEmSNhhmg
		 * txTime : 2018-05-03T16:58:53.863+08:00
		 * timeZone : Asia/Shanghai
		 * locale : zh_CN
		 * success : false
		 * statusCode : 401
		 * statusDescription : 超过POS机台最大可用数量
		 */

		private String instanceSid;
		private String  version;
		private String  clientReference;
		private String  txReference;
		private String  txTime;
		private String  timeZone;
		private String  locale;
		private boolean success;
		private String  statusCode;
		private String  statusDescription;

		public String getInstanceSid() {
			return instanceSid;
		}

		public void setInstanceSid(String instanceSid) {
			this.instanceSid = instanceSid;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getClientReference() {
			return clientReference;
		}

		public void setClientReference(String clientReference) {
			this.clientReference = clientReference;
		}

		public String getTxReference() {
			return txReference;
		}

		public void setTxReference(String txReference) {
			this.txReference = txReference;
		}

		public String getTxTime() {
			return txTime;
		}

		public void setTxTime(String txTime) {
			this.txTime = txTime;
		}

		public String getTimeZone() {
			return timeZone;
		}

		public void setTimeZone(String timeZone) {
			this.timeZone = timeZone;
		}

		public String getLocale() {
			return locale;
		}

		public void setLocale(String locale) {
			this.locale = locale;
		}

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public String getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(String statusCode) {
			this.statusCode = statusCode;
		}

		public String getStatusDescription() {
			return statusDescription;
		}

		public void setStatusDescription(String statusDescription) {
			this.statusDescription = statusDescription;
		}
	}
}
