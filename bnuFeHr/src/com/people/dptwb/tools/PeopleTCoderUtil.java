/** 
 * File: PeopleTCoder.java
 * Date: Oct 24, 2009
 * @author yl1935
 * Email:
 */
package com.people.dptwb.tools;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.people.dptwb.constant.Constant;

public class PeopleTCoderUtil {
	/**
	 * cookie加解密程序
	 */
	public String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; b != null && n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	public byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException();
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	public byte[] encrypt(byte[] src, byte[] key) throws Exception {

		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(key);

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Constant.DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		Cipher cipher = Cipher.getInstance(Constant.DES);

		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

		return cipher.doFinal(src);
	}

	public byte[] decrypt(byte[] src, byte[] key) throws Exception {

		SecureRandom sr = new SecureRandom();

		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Constant.DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance(Constant.DES);
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		return cipher.doFinal(src);
	}

	public String decrypt(String data, String key) {
		if (data != null)
			try {
				return new String(decrypt(hex2byte(data.getBytes()), key.getBytes()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	public String encrypt(String data, String key) {
		if (data != null)
			try {
				return byte2hex(encrypt(data.getBytes(), key.getBytes()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	public String decrypt(String data) {
		try {
			return new String(decrypt(hex2byte(data.getBytes()), Constant.STRING_CRYPT_KEY.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String encrypt(String data) {
		try {
			return byte2hex(encrypt(data.getBytes(), Constant.STRING_CRYPT_KEY.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(new PeopleTCoderUtil().encrypt("test"));
		System.out.println(new PeopleTCoderUtil().decrypt("7FE20520C4B68AE3"));
	}
}
