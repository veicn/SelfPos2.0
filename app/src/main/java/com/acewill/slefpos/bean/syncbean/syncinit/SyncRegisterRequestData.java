package com.acewill.slefpos.bean.syncbean.syncinit;

/**
 * Author：Anch
 * Date：2018/9/17 10:59
 * Desc：
 */
public class SyncRegisterRequestData {

	/**
	 * terminalOuid : 12Fah3S7R18WFDe4yj71dJ
	 * terminalType : 5
	 * companyId : 0514
	 * appVersion : 18.9.0
	 * authCode : 37fb1c4d1cc2cc36088ed88bb00377cb
	 */
	private String companyOuid;
	private String storeshopOuid;
	private String terminalOuid;
	private String terminalType;
	private String companyId;
	private String appVersion;
	private String authCode;


	public String getTerminalOuid() {
		return terminalOuid;
	}

	public void setTerminalOuid(String terminalOuid) {
		this.terminalOuid = terminalOuid;
	}

	public String getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getStoreshopOuid() {
		return storeshopOuid;
	}

	public void setStoreshopOuid(String storeshopOuid) {
		this.storeshopOuid = storeshopOuid;
	}

	public String getCompanyOuid() {
		return companyOuid;
	}

	public void setCompanyOuid(String companyOuid) {
		this.companyOuid = companyOuid;
	}
}
