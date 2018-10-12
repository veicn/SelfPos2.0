/*
 * Copyright © 2013-2014.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.acewill.slefpos.system.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.text.MessageFormat;

/**
 * https://ant.apache.org/manual/Tasks/checksum.html
 * 
 * @author leo.tu.taipei@gmail.com, leo@ratepo.com
 */
public class ChecksumUtil {
	static protected Logger log = LoggerFactory.getLogger(ChecksumUtil.class);

	public static enum OutputFormat {
		CHECKSUM("{0}"), MD5SUM("{0} *{1}"), SVF("MD5 ({1}) = {0}");

		private final String format;

		private OutputFormat(String format) {
			this.format = format;
		}
	}

	public static enum Algorithm {
		MD5("MD5"), SHA("SHA"), SHA256("SHA-256"), SHA512("SHA-512");

		private final String algorithm;

		private Algorithm(String algorithm) {
			this.algorithm = algorithm;
		}
	}

	protected Algorithm algorithm;

	protected int readBufferSize = 8 * 1024;

	public static void main(String[] args) {
		try {
//			//File src = new File("C:/Program Files (x86)/RatepoPOS/lib/xz-1.2.jar.temp");
			File src = new File("C:\\Users\\ratepo\\.m2\\repository\\junit\\junit\\4.11\\junit-4.11.jar");
//			//
			Algorithm algorithm = Algorithm.MD5;
//			// Algorithm algorithm = Algorithm.SHA;
//			// //Algorithm algorithm = Algorithm.SHA256;
//			// // Algorithm algorithm = Algorithm.SHA512;
//			// //
			ChecksumUtil tester = new ChecksumUtil(algorithm);
			//InputStream is = new ByteArrayInputStream("abc".getBytes(LangUtil.ISO_8859_1)); 
			InputStream is = new FileInputStream(src);
			String result = tester.checksum(is);
			log.info(algorithm + ":checksum=[" + result + "]");
//			//
//			// tester = new ChecksumUtil(algorithm);
//			// fis = new FileInputStream(src);
//			// String result = tester.checksum(fis);
//			// log.debug(algorithm + ":checksum=[" + result + "]");
//			// fis.close();
//			// //
//			// // tester = new ChecksumUtil(algorithm);
//			// // fis = new FileInputStream(src);
//			// // result = tester.svf(fis, src.getName());
//			// // log.debug(algorithm + ":svf     =[" + result + "]");
//			// //fis.close();
		} catch (Exception e) {
			log.error("", e);
		}
	}

	public ChecksumUtil() {
		this(Algorithm.MD5);
	}

	public ChecksumUtil(Algorithm algorithm) {
		this.algorithm = algorithm;
	}

	public String checksum(byte[] input) throws Exception {
		return checksum(new ByteArrayInputStream(input)) ;
	}
	
	public String checksum(InputStream input) throws Exception {
		String checksum = generateChecksum(input);
		MessageFormat format = new MessageFormat(OutputFormat.CHECKSUM.format);
		String result = format.format(new Object[] { checksum });
		return result;
	}

	public String md5sum(InputStream input, String name) throws Exception {
		if (algorithm != Algorithm.MD5) {
			throw new Exception("(algorithm != Algorithm.MD5)");
		}
		String checksum = generateChecksum(input);
		MessageFormat format = new MessageFormat(OutputFormat.MD5SUM.format);
		String result = format.format(new Object[] { checksum, name });
		return result;
	}

	public String svf(InputStream input, String name) throws Exception {
		if (algorithm != Algorithm.MD5) {
			throw new Exception("(algorithm != Algorithm.MD5)");
		}
		String checksum = generateChecksum(input);
		MessageFormat format = new MessageFormat(OutputFormat.SVF.format);
		String result = format.format(new Object[] { checksum, name });
		return result;
	}

	public Algorithm getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}

	public void setReadBufferSize(int readBufferSize) {
		this.readBufferSize = readBufferSize;
	}

	/**
	 * Generate checksum using the message digest created earlier.
	 */
	private String generateChecksum(InputStream input) throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance(algorithm.algorithm);
		byte[] buf = new byte[readBufferSize];
		// messageDigest.reset();
		DigestInputStream dis = new DigestInputStream(input, messageDigest);
		// while (dis.read(buf, 0, readBufferSize) != -1) {
		long count = 0;
		int n = 0;
		while (-1 != (n = dis.read(buf))) {
			// messageDigest.update(buf, 0, n);
			count += n;
		}
		if (count > Integer.MAX_VALUE) {
			log.warn("(count > Integer.MAX_VALUE), count={}", count);
		}
		// dis.close();
		byte[] fileDigest = messageDigest.digest();
		String checksum = createDigestString(fileDigest);
		return checksum;
	}

	private String createDigestString(byte[] fileDigest) {
		StringBuffer checksumSb = new StringBuffer(64);
		for (int i = 0; i < fileDigest.length; i++) {
			String hexStr = Integer.toHexString(0x00ff & fileDigest[i]);
			if (hexStr.length() < 2) {
				checksumSb.append("0");
			}
			checksumSb.append(hexStr);
		}
		return checksumSb.toString();
	}
	
}
