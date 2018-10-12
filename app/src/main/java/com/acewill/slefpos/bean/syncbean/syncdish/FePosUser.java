package com.acewill.slefpos.bean.syncbean.syncdish;

import java.io.Serializable;

/**
 * Author：Anch
 * Date：2018/6/9 14:52
 * Desc：
 */
public class FePosUser implements Serializable{


	/**
	 * ouid : EqCUGKT2RN2-2UGBVVvGJw
	 * verUuid : zOw3pJVKSu6cucMrcpwQFA
	 * statusFlag : Y
	 * statusTime : 2018-05-22T16:04:03.403+08:00
	 * crtTime : 2018-05-22T16:04:03.408+08:00
	 * storeshopOuid : jMcFjdTVQFyOQQXNk8sR9w
	 * authType : N
	 * lang : zh_CN
	 * langPriority : 1
	 * name : FEC店员
	 * pwd : VUiWVFWafl0JgePrWX02X6Gg21t+KT4RCNJP2SSaAAvdwZNqQHzqOMQ7Lxrx9g0tl0WX8+yg3N/OVLu2woL1rQ==~NgA8WhlYW+YHjcALmt3mnA==~18
	 * sex : U
	 * userId : FEC店员
	 */

	private String ouid;
	private String verUuid;
	private String statusFlag;
	private String statusTime;
	private String crtTime;
	private String storeshopOuid;
	private String authType;
	private String lang;
	private String langPriority;
	private String name;
	private String pwd;
	private String sex;
	private String userId;

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

	public String getStoreshopOuid() {
		return storeshopOuid;
	}

	public void setStoreshopOuid(String storeshopOuid) {
		this.storeshopOuid = storeshopOuid;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getLangPriority() {
		return langPriority;
	}

	public void setLangPriority(String langPriority) {
		this.langPriority = langPriority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
