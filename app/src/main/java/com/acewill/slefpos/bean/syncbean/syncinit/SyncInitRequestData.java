package com.acewill.slefpos.bean.syncbean.syncinit;

/**
 * Author：Anch
 * Date：2018/4/19 17:14
 * Desc：
 */
public class SyncInitRequestData {

	/**
	 * header : {"version":"2.30.0","clientReference":"E4ll7JkIRwCDFD0Ec6bv3A","txReference":"QUXPxURTQW2YrkgWZikYbQ","txTime":"2018-04-16T18:24:26","timeZone":"Asia/Shanghai","locale":"zh_CN","instanceSid":"5"}
	 * body : {}
	 */

	private HeaderBean header;
	private BodyBean   body;

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
		 * txTime : 2018-04-16T18:24:26
		 * timeZone : Asia/Shanghai
		 * locale : zh_CN
		 * instanceSid : 5
		 */

		private String version;
		private String clientReference;
		private String txReference;
		private String txTime;
		private String timeZone;
		private String locale;
		private String instanceSid;

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
	}

	public static class BodyBean {
	}

	public static class qryStoreBodyBean extends BodyBean {
		private String ratepoId;
		private String registerAuthCode;

		public String getRatepoId() {
			return ratepoId;
		}

		public void setRatepoId(String ratepoId) {
			this.ratepoId = ratepoId;
		}

		public String getRegisterAuthCode() {
			return registerAuthCode;
		}

		public void setRegisterAuthCode(String registerAuthCode) {
			this.registerAuthCode = registerAuthCode;
		}
	}

	public static class FinishPoseBodyBean extends BodyBean {
		public String getCompanyOuid() {
			return companyOuid;
		}

		public void setCompanyOuid(String companyOuid) {
			this.companyOuid = companyOuid;
		}

		public String getStoreshopOuid() {
			return storeshopOuid;
		}

		public void setStoreshopOuid(String storeshopOuid) {
			this.storeshopOuid = storeshopOuid;
		}

		private String companyOuid;
		private String storeshopOuid;
	}

	public static class DownLoadRequestBean extends BodyBean {
		public String getCompanyOuid() {
			return companyOuid;
		}

		public void setCompanyOuid(String companyOuid) {
			this.companyOuid = companyOuid;
		}

		public String getStoreshopOuid() {
			return storeshopOuid;
		}

		public void setStoreshopOuid(String storeshopOuid) {
			this.storeshopOuid = storeshopOuid;
		}

		private String companyOuid;
		private String storeshopOuid;
		private String versionSeqNumber;

		public String getVersionSeqNumber() {
			return versionSeqNumber;
		}

		public void setVersionSeqNumber(String versionSeqNumber) {
			this.versionSeqNumber = versionSeqNumber;
		}
	}
}
