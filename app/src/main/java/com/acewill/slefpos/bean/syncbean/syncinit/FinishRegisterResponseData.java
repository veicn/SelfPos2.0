package com.acewill.slefpos.bean.syncbean.syncinit;

/**
 * Author：Anch
 * Date：2018/9/17 14:32
 * Desc：
 */
public class FinishRegisterResponseData {

	/**
	 * code : 200
	 * data : {"companyOuid":"1ctAaaMITEmCxb-ADb1skw","companyCateOuid":"1ZKsXR6XTOiePESaOjIuhw","storeshopOuid":"vptKCaOtSn-ItRy4CVjt4w","terminalOuid":"V_oAYiqcR6-4o-uPXwhSQA","locale":"zh_CN","secret":"fe6b18da0790cb30ad130d9945653be4e7ccea38e96da24050f54a21ea8ee6f1e76da15d2058ce0db84bf2617a0ca42c"}
	 */

	private int code;
	private DataBean data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * companyOuid : 1ctAaaMITEmCxb-ADb1skw
		 * companyCateOuid : 1ZKsXR6XTOiePESaOjIuhw
		 * storeshopOuid : vptKCaOtSn-ItRy4CVjt4w
		 * terminalOuid : V_oAYiqcR6-4o-uPXwhSQA
		 * locale : zh_CN
		 * secret : fe6b18da0790cb30ad130d9945653be4e7ccea38e96da24050f54a21ea8ee6f1e76da15d2058ce0db84bf2617a0ca42c
		 */

		private String companyOuid;
		private String companyCateOuid;
		private String storeshopOuid;
		private String terminalOuid;
		private String locale;
		private String secret;

		public String getCompanyOuid() {
			return companyOuid;
		}

		public void setCompanyOuid(String companyOuid) {
			this.companyOuid = companyOuid;
		}

		public String getCompanyCateOuid() {
			return companyCateOuid;
		}

		public void setCompanyCateOuid(String companyCateOuid) {
			this.companyCateOuid = companyCateOuid;
		}

		public String getStoreshopOuid() {
			return storeshopOuid;
		}

		public void setStoreshopOuid(String storeshopOuid) {
			this.storeshopOuid = storeshopOuid;
		}

		public String getTerminalOuid() {
			return terminalOuid;
		}

		public void setTerminalOuid(String terminalOuid) {
			this.terminalOuid = terminalOuid;
		}

		public String getLocale() {
			return locale;
		}

		public void setLocale(String locale) {
			this.locale = locale;
		}

		public String getSecret() {
			return secret;
		}

		public void setSecret(String secret) {
			this.secret = secret;
		}
	}
}
