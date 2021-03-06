package com.acewill.paylibrary.tencent.common;

import com.acewill.slefpos.system.ui.ChecksumUtil;
import com.parse.codec.binary.Base64;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * User: rizenguo
 * Date: 2014/10/23
 * Time: 14:59
 */
public class Util {

	//鎵搇og鐢�
	private static Log logger = new Log(LoggerFactory.getLogger(Util.class));

	/**
	 * 閫氳繃鍙嶅皠鐨勬柟寮忛亶鍘嗗璞＄殑灞炴�у拰灞炴�у�硷紝鏂逛究璋冭瘯
	 *
	 * @param o 瑕侀亶鍘嗙殑瀵硅薄
	 * @throws Exception
	 */
	public static void reflect(Object o) throws Exception {
		Class   cls    = o.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			f.setAccessible(true);
			Util.log(f.getName() + " -> " + f.get(o));
		}
	}

	public static byte[] readInput(InputStream in) throws IOException {
		ByteArrayOutputStream out    = new ByteArrayOutputStream();
		int                   len    = 0;
		byte[]                buffer = new byte[1024];
		while ((len = in.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}
		out.close();
		in.close();
		return out.toByteArray();
	}

	public static String inputStreamToString(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int                   i;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}


	public static InputStream getStringStream(String sInputString) throws UnsupportedEncodingException {
		ByteArrayInputStream tInputStringStream = null;
		if (sInputString != null && !sInputString.trim().equals("")) {
			tInputStringStream = new ByteArrayInputStream(sInputString.getBytes("UTF-8"));
		}
		return tInputStringStream;
	}

	public static Object getObjectFromXML(String xml, Class tClass) throws Exception {
		System.out.println("------------getObjectFromXML : xml = " + xml);
		if (xml == null || xml.length() == 0) {
			return tClass.newInstance();
		}
		//
		XStream xStreamForResponseData = new XStream(new DomDriver());//new XStream();
		xStreamForResponseData.alias("xml", tClass);
		xStreamForResponseData.ignoreUnknownElements();
		return xStreamForResponseData.fromXML(xml);
	/*
		ByteArrayInputStream stream = null;
    	try
    	{
    		System.out.println(xml.toString());
	    	stream = new ByteArrayInputStream(xml.getBytes());
	    	List<?> res = new PullXmlUtils(tClass).parse( stream );
	    	
	    	return res.get(0);
    	}
    	finally
    	{
    		try
    		{
    			stream.close();
    		}
    		catch( Exception ex )
    		{
    			
    		}
    	}
    */
	}

	public static String getStringFromMap(Map<String, Object> map, String key, String defaultValue) {
		if (key == "" || key == null) {
			return defaultValue;
		}
		String result = (String) map.get(key);
		if (result == null) {
			return defaultValue;
		} else {
			return result;
		}
	}

	public static int getIntFromMap(Map<String, Object> map, String key) {
		if (key == "" || key == null) {
			return 0;
		}
		if (map.get(key) == null) {
			return 0;
		}
		return Integer.parseInt((String) map.get(key));
	}

	/**
	 * 鎵搇og鎺ュ彛
	 *
	 * @param log 瑕佹墦鍗扮殑log瀛楃涓�
	 * @return 杩斿洖log
	 */
	public static String log(Object log) {
		logger.i(log.toString());
		//System.out.println(log);
		return log.toString();
	}

	private static String splitSp = "~";

	/**
	 * 璇诲彇鏈湴鐨剎ml鏁版嵁锛屼竴鑸敤鏉ヨ嚜娴嬬敤
	 *
	 * @param localPath 鏈湴xml鏂囦欢璺緞
	 * @return 璇诲埌鐨剎ml瀛楃涓�
	 */
	public static String getLocalXMLString(String localPath) throws IOException {
		return Util.inputStreamToString(Util.class.getResourceAsStream(localPath));
	}

	public static boolean validatePassword(String storedPassword, String checkPassword) {
		//        if (StringUtils.isBlank(storedPassword) && StringUtils.isBlank(checkPassword)) {
		//            return true;
		//        }
		//        if (StringUtils.isBlank(storedPassword) || StringUtils.isBlank(checkPassword)) {
		//            return false;
		//        }
		try {
			String[] parts      = storedPassword.split(splitSp);
			boolean  splitMode  = parts.length == 3;
			String   hashStr    = parts[0];
			String   saltStr    = splitMode ? parts[1] : null;
			int      iterations = splitMode ? Integer.parseInt(parts[2]) : 0;
			byte[]   salt       = saltStr == null ? null : Base64.decodeBase64(saltStr);
			byte[]   hash       = Base64.decodeBase64(hashStr);
			int      keyLength  = splitMode ? hash.length * 8 : 0; // bits
			// log.debug("keyLength=[{}]", keyLength);
			// log.debug("iterations=[{}]", iterations);
			// log.debug("saltStr=[{}], saltStr.length={}", saltStr, saltStr.length());
			// log.debug("hashStr=[{}], hashStr.length={}", hashStr, hashStr.length());
			byte[] checkHash = hash(checkPassword.toCharArray(), salt, iterations, keyLength);
			return Arrays.equals(checkHash, hash);
		} catch (Exception e) {
			//            if (debug) {
			//                log.debug("storedPassword=[" + storedPassword + "], checkPassword=[" + checkPassword + "]", e);
			//            }
			return false;
		}
	}

	private static  byte[] hash(char[] password, byte[] salt, int iterationCount, int keyLength) throws Exception {
		if (salt != null && salt.length > 0 && iterationCount > 0 && keyLength > 0) {
			//			if (debug) {
			//				log.debug("with salt=" + salt);
			//			}
			PBEKeySpec       spec = new PBEKeySpec(password, salt, iterationCount, keyLength);
			SecretKeyFactory skf  = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			return skf.generateSecret(spec).getEncoded();
		} else {
			//			if (debug) {
			//				log.debug("without salt=" + salt);
			//			}
			ChecksumUtil algorithm = new ChecksumUtil(ChecksumUtil.Algorithm.SHA512);
			String result = algorithm
					.checksum(new ByteArrayInputStream(new String(password)
							.getBytes("UTF-8")));
			return result.getBytes("ISO_8859_1");
		}
	}
}

