package com.acewill.slefpos.bean.syncbean.syncinit;

/**
 * Author：Anch
 * Date：2018/4/23 15:47
 * Desc：
 */
public class FinishPosResponseData {

	/**
	 * header : {"version":"2.30.0","clientReference":"E4ll7JkIRwCDFD0Ec6bv3A","txReference":"tZH2yZY9RLyDiwGXhyFneQ","txTime":"2018-04-16T18:01:56","timeZone":"Asia/Shanghai","locale":"zh_CN","instanceSid":"5","success":true,"statusCode":"200","statusDescription":"OK"}
	 * body : {"instanceSid":"2iQq1c-qUQ66Gt4HDR-oFxQp6SsGrr-S4iSYA11xmuFdACWkvBXFKTaKmotfcIycUJw47","pos":{"ouid":"CWkvBXFKTaKmotfcIycUJw","verUuid":"WDtZGQRBTEaQN3hSWVRAig","statusFlag":"Y","statusTime":"2018-04-16T18:01:56","crtTime":"2018-04-16T18:01:56","name":"0372","opVersion":"2.30.0","posId":"0372","posSecret":"838f4da36c26097bad74d03cb4a2fe44","ratepoId":"0372","storeshopOuid":"p6SsGrr-S4iSYA11xmuFdA"}}
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
		 * txReference : tZH2yZY9RLyDiwGXhyFneQ
		 * txTime : 2018-04-16T18:01:56
		 * timeZone : Asia/Shanghai
		 * locale : zh_CN
		 * instanceSid : 5
		 * success : true
		 * statusCode : 200
		 * statusDescription : OK
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
		 * instanceSid : 2iQq1c-qUQ66Gt4HDR-oFxQp6SsGrr-S4iSYA11xmuFdACWkvBXFKTaKmotfcIycUJw47
		 * pos : {"ouid":"CWkvBXFKTaKmotfcIycUJw","verUuid":"WDtZGQRBTEaQN3hSWVRAig","statusFlag":"Y","statusTime":"2018-04-16T18:01:56","crtTime":"2018-04-16T18:01:56","name":"0372","opVersion":"2.30.0","posId":"0372","posSecret":"838f4da36c26097bad74d03cb4a2fe44","ratepoId":"0372","storeshopOuid":"p6SsGrr-S4iSYA11xmuFdA"}
		 */

		private String instanceSid;
		private PosBean pos;

		public String getInstanceSid() {
			return instanceSid;
		}

		public void setInstanceSid(String instanceSid) {
			this.instanceSid = instanceSid;
		}

		public PosBean getPos() {
			return pos;
		}

		public void setPos(PosBean pos) {
			this.pos = pos;
		}

		public static class PosBean {
			/**
			 * ouid : CWkvBXFKTaKmotfcIycUJw
			 * verUuid : WDtZGQRBTEaQN3hSWVRAig
			 * statusFlag : Y
			 * statusTime : 2018-04-16T18:01:56
			 * crtTime : 2018-04-16T18:01:56
			 * name : 0372
			 * opVersion : 2.30.0
			 * posId : 0372
			 * posSecret : 838f4da36c26097bad74d03cb4a2fe44
			 * ratepoId : 0372
			 * storeshopOuid : p6SsGrr-S4iSYA11xmuFdA
			 */

			private String ouid;
			private String verUuid;
			private String statusFlag;
			private String statusTime;
			private String crtTime;
			private String name;
			private String opVersion;
			private String posId;
			private String posSecret;
			private String ratepoId;
			private String storeshopOuid;

			public String getOuid() {
				return ouid;
			}

			public void setOuid(String ouid) {
				this.ouid = ouid;
			}

			public String getVerUuid() {
				return verUuid;
			}

			public void setVerUuid(String verUuid) {
				this.verUuid = verUuid;
			}

			public String getStatusFlag() {
				return statusFlag;
			}

			public void setStatusFlag(String statusFlag) {
				this.statusFlag = statusFlag;
			}

			public String getStatusTime() {
				return statusTime;
			}

			public void setStatusTime(String statusTime) {
				this.statusTime = statusTime;
			}

			public String getCrtTime() {
				return crtTime;
			}

			public void setCrtTime(String crtTime) {
				this.crtTime = crtTime;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getOpVersion() {
				return opVersion;
			}

			public void setOpVersion(String opVersion) {
				this.opVersion = opVersion;
			}

			public String getPosId() {
				return posId;
			}

			public void setPosId(String posId) {
				this.posId = posId;
			}

			public String getPosSecret() {
				return posSecret;
			}

			public void setPosSecret(String posSecret) {
				this.posSecret = posSecret;
			}

			public String getRatepoId() {
				return ratepoId;
			}

			public void setRatepoId(String ratepoId) {
				this.ratepoId = ratepoId;
			}

			public String getStoreshopOuid() {
				return storeshopOuid;
			}

			public void setStoreshopOuid(String storeshopOuid) {
				this.storeshopOuid = storeshopOuid;
			}
		}
	}
}
