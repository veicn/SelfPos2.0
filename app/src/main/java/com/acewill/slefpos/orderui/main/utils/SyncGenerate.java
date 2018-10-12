package com.acewill.slefpos.orderui.main.utils;

import android.content.Context;
import android.util.Log;

import com.jaydenxiao.common.commonutils.SPUtils;
import com.parse.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.UUID;

import javax.crypto.Cipher;

/**
 * Author：Anch
 * Date：2018/5/5 16:41
 * Desc：
 */
public class SyncGenerate {
	public static String getEncodeCipherKey(Context context,  String data) {

		try {
			byte[] publicKeyEncoded = new byte[0];
			publicKeyEncoded = Base64.
					decodeBase64(SPUtils.getSharedStringData(context, "cipherKey")
							.getBytes("ISO_8859_1"));
			X509EncodedKeySpec keySpec    = new X509EncodedKeySpec(publicKeyEncoded);
			KeyFactory         keyFactory = KeyFactory.getInstance("RSA");
			PublicKey          publicKey  = keyFactory.generatePublic(keySpec);
			Cipher             cipher     = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey); // publicKey
			//data 加密前的字符串
			byte[] dataBytes    = data.getBytes("UTF-8");
			byte[] encryptBytes = cipher.doFinal(dataBytes);
			//加密后的结果result
			String result = new String(Base64.encodeBase64(encryptBytes), "ISO_8859_1");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取UUID的字节数组。此方法与<code>{@link #(byte[])}</code>互逆
	 *
	 * @param uuid UUID对象
	 * @return 返回uuid的字节数组
	 */
	public static String getUUID(UUID uuid) {
		try {
			ByteBuffer buf = ByteBuffer.allocate(16);
			buf.putLong(uuid.getMostSignificantBits());
			buf.putLong(uuid.getLeastSignificantBits());
			byte[] bytes1 = Base64.encodeBase64(buf.array());
			String encode = new String(bytes1, "UTF-8");
			encode = encode.replace('+', '-');
			encode = encode.replace("/", "_");
			encode = encode.replace("=", "");
			Log.e("jlk", "jl");
			return encode;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
