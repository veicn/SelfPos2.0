package com.acewill.slefpos.bean.syncbean.syncinit;

/**
 * Author：Anch
 * Date：2018/4/19 17:17
 * Desc：
 */
public class SyncInitResponseData {

	/**
	 * header : {"version":"2.30.0","clientReference":"E4ll7JkIRwCDFD0Ec6bv3A","txReference":"QUXPxURTQW2YrkgWZikYbQ","txTime":"2018-04-16T18:34:33","timeZone":"Asia/Shanghai","locale":"zh_CN","instanceSid":"5","success":true,"statusCode":"200","statusDescription":"OK"}
	 * body : {"cipherKey":"MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKeb9xXthMK/5bfmWy9cXoudqgYNAZV1uEYP+ZHozpPZWA4LcFbYRLPSA6QThohgYDq5RDISUsHySE+OCqnO7ZkCAwEAAQ=="}
	 */

	private HeaderBean header;
	private BodyBean body;

	public HeaderBean getHeader() {
		return header;
	}

	public void setHeader(HeaderBean header) {
		this.header = header;
	}

	public BodyBean getBody() {
		return body;
	}

	public void setBody(BodyBean body) {
		this.body = body;
	}

	public static class HeaderBean {
		/**
		 * version : 2.30.0
		 * clientReference : E4ll7JkIRwCDFD0Ec6bv3A
		 * txReference : QUXPxURTQW2YrkgWZikYbQ
		 * txTime : 2018-04-16T18:34:33
		 * timeZone : Asia/Shanghai
		 * locale : zh_CN
		 * instanceSid : 5
		 * success : true
		 * statusCode : 200
		 * statusDescription : OK
		 *
		 * {"header":{"instanceSid":"5","version":"1.0.0","clientReference":"KZvzSm_5TPGAfWx-VPJT-w","txReference":"j5BeGDGZSJWBnj4NliCa1g","locale":"zh_CN","success":false,"statusCode":"417","statusDescription":"版本过低，请安装新版本"}}
		 */

		private String version;
		private String  clientReference;
		private String  txReference;
		private String  txTime;
		private String  timeZone;
		private String  locale;
		private String  instanceSid;
		private boolean success;
		private String  statusCode;
		private String  statusDescription;

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

		public String getInstanceSid() {
			return instanceSid;
		}

		public void setInstanceSid(String instanceSid) {
			this.instanceSid = instanceSid;
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

	public static class BodyBean {
		/**
		 * cipherKey : MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKeb9xXthMK/5bfmWy9cXoudqgYNAZV1uEYP+ZHozpPZWA4LcFbYRLPSA6QThohgYDq5RDISUsHySE+OCqnO7ZkCAwEAAQ==
		 */

		private String cipherKey;


		public String getCipherKey() {
			return cipherKey;
		}

		public void setCipherKey(String cipherKey) {
			this.cipherKey = cipherKey;
		}
	}
}
