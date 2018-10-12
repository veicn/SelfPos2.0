package com.acewill.slefpos.configure;

/**
 * Author：Anch
 * Date：2018/1/25 9:50
 * Desc：
 */
public class TerminalConfigure {
	private static String tname;//终端名称
	private static String terminalmac;//终端码
	private static String currentVersion;//终端当前版本
	private static String versionid = "selfpos";//终端类型 点餐机就是 selfhelp_ordermachine

	public static String getTerminalid() {
		return terminalid;
	}

	public static void setTerminalid(String terminalid) {
		TerminalConfigure.terminalid = terminalid;
	}

	private static String terminalid;//终端类型 点餐机就是 selfhelp_ordermachine
	private static int    kdsid;//终端类型 点餐机就是 selfhelp_ordermachine

	public static void init(String tname, String terminalmac,String terminalid) {
		setTname(tname);
		setTerminalid(terminalid);
		setTerminalmac(terminalmac);
	}

	public static String getTname() {
		return tname;
	}

	public static void setTname(String tname) {
		TerminalConfigure.tname = tname;
	}

	public static String getTerminalmac() {
		return terminalmac;
	}

	public static void setTerminalmac(String terminalmac) {
		TerminalConfigure.terminalmac = terminalmac;
	}

	public static String getCurrentVersion() {
		return currentVersion;
	}

	public static void setCurrentVersion(String currentVersion) {
		TerminalConfigure.currentVersion = currentVersion;
	}

	public static String getVersionid() {
		return versionid;
	}

	public static void setVersionid(String versionid) {
		TerminalConfigure.versionid = versionid;
	}

	public static int getKdsid() {
		return kdsid;
	}

	public static void setKdsid(int kdsid) {
		TerminalConfigure.kdsid = kdsid;
	}
}
