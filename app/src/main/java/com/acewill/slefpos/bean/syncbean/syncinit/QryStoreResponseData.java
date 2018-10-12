package com.acewill.slefpos.bean.syncbean.syncinit;

import java.util.List;

/**
 * Author：Anch
 * Date：2018/4/23 15:31
 * Desc：
 */
public class QryStoreResponseData {

	/**
	 * header : {"version":"2.30.0","clientReference":"E4ll7JkIRwCDFD0Ec6bv3A","txReference":"sq36qncRQ2eb-57AEUKTYg","txTime":"2018-04-17T09:30:29","timeZone":"Asia/Shanghai","locale":"zh_CN","instanceSid":"5","success":true,"statusCode":"200","statusDescription":"OK"}
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
		 * txReference : sq36qncRQ2eb-57AEUKTYg
		 * txTime : 2018-04-17T09:30:29
		 * timeZone : Asia/Shanghai
		 * locale : zh_CN
		 * instanceSid : 5
		 * success : true
		 * statusCode : 200
		 * statusDescription : OK
		 */
		private String  instanceSid;
		private String  version;
		private String  clientReference;
		private String  txReference;
		private String  txTime;
		private String  timeZone;
		private String  locale;
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
		public FeCompanyBean getFeCompany() {
			return feCompany;
		}

		public void setFeCompany(FeCompanyBean feCompany) {
			this.feCompany = feCompany;
		}

		public List<FeStoreshop> getFeStoreshop() {
			return feStoreshop;
		}

		public void setFeStoreshop(List<FeStoreshop> feStoreshop) {
			this.feStoreshop = feStoreshop;
		}

		private FeCompanyBean     feCompany;
		private List<FeStoreshop> feStoreshop;

		public class FeCompanyBean {

			/**
			 * ouid : iQq1c-qUQ66Gt4HDR-oFxQ
			 * verUuid : gMWFVsnLQgG9WpH1rRxeOw
			 * statusFlag : Y
			 * statusTime : 2018-02-28T15:53:27
			 * crtTime : 2016-06-27T17:43:36
			 * updTime : 2018-04-16T10:30:16
			 * address : 11厦门市思明区软件园望海路12号之一129楼
			 * companyCateOuid : zFr3A1BaQwSQrYZN1ylqcw
			 * companyId : 0514
			 * country : CN
			 * currency : CNY
			 * decimalPlace : 2
			 * email : sunny@syncpo.com
			 * fax : 34567890we
			 * fullName : 海滨林二妹甜品店
			 * fullName2 : 22256789
			 * lang : zh_CN
			 * lang2 : zh_TW
			 * logo :
			 * name : Happy Lemon-edit
			 * posAmount : 500
			 * ratepoId : 0514
			 * tel : 4567890
			 * vatNo : 123
			 * webSite : 45678
			 */

			private String ouid;
			private String verUuid;
			private String statusFlag;
			private String statusTime;
			private String crtTime;
			private String updTime;
			private String address;
			private String companyCateOuid;
			private String companyId;
			private String country;
			private String currency;
			private int    decimalPlace;
			private String email;
			private String fax;
			private String fullName;
			private String fullName2;
			private String lang;
			private String lang2;
			private String logo;
			private String name;
			private int    posAmount;
			private String ratepoId;
			private String tel;
			private String vatNo;
			private String webSite;

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

			public String getUpdTime() {
				return updTime;
			}

			public void setUpdTime(String updTime) {
				this.updTime = updTime;
			}

			public String getAddress() {
				return address;
			}

			public void setAddress(String address) {
				this.address = address;
			}

			public String getCompanyCateOuid() {
				return companyCateOuid;
			}

			public void setCompanyCateOuid(String companyCateOuid) {
				this.companyCateOuid = companyCateOuid;
			}

			public String getCompanyId() {
				return companyId;
			}

			public void setCompanyId(String companyId) {
				this.companyId = companyId;
			}

			public String getCountry() {
				return country;
			}

			public void setCountry(String country) {
				this.country = country;
			}

			public String getCurrency() {
				return currency;
			}

			public void setCurrency(String currency) {
				this.currency = currency;
			}

			public int getDecimalPlace() {
				return decimalPlace;
			}

			public void setDecimalPlace(int decimalPlace) {
				this.decimalPlace = decimalPlace;
			}

			public String getEmail() {
				return email;
			}

			public void setEmail(String email) {
				this.email = email;
			}

			public String getFax() {
				return fax;
			}

			public void setFax(String fax) {
				this.fax = fax;
			}

			public String getFullName() {
				return fullName;
			}

			public void setFullName(String fullName) {
				this.fullName = fullName;
			}

			public String getFullName2() {
				return fullName2;
			}

			public void setFullName2(String fullName2) {
				this.fullName2 = fullName2;
			}

			public String getLang() {
				return lang;
			}

			public void setLang(String lang) {
				this.lang = lang;
			}

			public String getLang2() {
				return lang2;
			}

			public void setLang2(String lang2) {
				this.lang2 = lang2;
			}

			public String getLogo() {
				return logo;
			}

			public void setLogo(String logo) {
				this.logo = logo;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public int getPosAmount() {
				return posAmount;
			}

			public void setPosAmount(int posAmount) {
				this.posAmount = posAmount;
			}

			public String getRatepoId() {
				return ratepoId;
			}

			public void setRatepoId(String ratepoId) {
				this.ratepoId = ratepoId;
			}

			public String getTel() {
				return tel;
			}

			public void setTel(String tel) {
				this.tel = tel;
			}

			public String getVatNo() {
				return vatNo;
			}

			public void setVatNo(String vatNo) {
				this.vatNo = vatNo;
			}

			public String getWebSite() {
				return webSite;
			}

			public void setWebSite(String webSite) {
				this.webSite = webSite;
			}
		}

		public static class FeStoreshop {

			/**
			 * ouid : xoR15nCiQcydqMhIbhpgHw
			 * name : 测试门店
			 * ratepoId : 1035
			 * storeshopId : 1036
			 */

			private String ouid;
			private String name;
			private String ratepoId;
			private String storeshopId;

			public String getOuid() {
				return ouid;
			}

			public void setOuid(String ouid) {
				this.ouid = ouid;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getRatepoId() {
				return ratepoId;
			}

			public void setRatepoId(String ratepoId) {
				this.ratepoId = ratepoId;
			}

			public String getStoreshopId() {
				return storeshopId;
			}

			public void setStoreshopId(String storeshopId) {
				this.storeshopId = storeshopId;
			}

			@Override
			public String toString() {
				return "FeStoreshop{" +
						"ouid='" + ouid + '\'' +
						", name='" + name + '\'' +
						", ratepoId='" + ratepoId + '\'' +
						", storeshopId='" + storeshopId + '\'' +
						'}';
			}
		}
	}
}
