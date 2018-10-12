package com.acewill.slefpos.orderui.main.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Author：Anch
 * Date：2018/9/17 10:49
 * Desc：
 */
public class RegisterUtil {
	//companyAuthCode为公司注册编码
	public static String getAuthCode(String terminalOuid, String appVersion, String companyAuthCode) {
		StringBuilder sb = new StringBuilder().append(terminalOuid).append("5")
				.append(appVersion).append(companyAuthCode);
		return md5HexString(sb.toString());
	}

	/**
	 * 获取字符串的Hex编码MD5码
	 *
	 * @param text 字符串
	 * @return 返回给定字符串的Hex编码MD5码（全为小写）
	 */
	public static String md5HexString(String text) {
		try {
			return toHex(md5(text.getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取字符数组的MD5码
	 *
	 * @param bytes 字符数组
	 * @return 返回给定字符数组的MD5码
	 */
	public static byte[] md5(byte[] bytes) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(bytes);
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 以十六进制字符串形式显示byte数组
	 * <p>
	 * 与{@link #fromHex(String)}方法互逆
	 *
	 * @param bytes 字符数组
	 * @return 返回字节数组的十六进制形式
	 */
	public static String toHex(byte[] bytes) {
		StringBuilder builder = new StringBuilder();
		for (byte b : bytes) {
			int i = (b & 0xFF);
			if (i < 16) {
				builder.append("0");
			}
			builder.append(Integer.toHexString(i));
		}
		return builder.toString();
	}

	public static String buildInstanceSid(String companyOuid,
	                                      String storeshopOuid, String posOuid) {
		String instance = companyOuid + storeshopOuid + posOuid;
		String instanceSid = "5"+ instance;
		long oddValue = 0L; // 奇數
		long evenValue = 0L; // 偶數
		for (int i = 0; i < instanceSid.length(); i++) {
			int value = (int) instanceSid.charAt(i);
			if (i % 2 == 0) { // 偶數
				String str = String.valueOf(value);
				evenValue += value * str.charAt(str.length() - 1);
			} else { // 奇數
				oddValue += value;
			}
		}
		String posfix1Str = String.valueOf(evenValue);
		char posfix1 = String.valueOf(posfix1Str).charAt(posfix1Str.length() - 1); // 取第最后一位
		String posfix2Str = String.valueOf(evenValue - oddValue);
		char posfix2 = posfix2Str.charAt(posfix2Str.length() - 1); // 取第最后一位
		String verifyCode = String.valueOf(posfix1) + String.valueOf(posfix2);
		return instanceSid + verifyCode;
	}
}
