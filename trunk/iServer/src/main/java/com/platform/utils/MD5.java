package com.platform.utils;

import java.security.MessageDigest;

public class MD5 {

	public MD5() {
	}

	public static String getMD5(String src_txt) {
		String algorithm = "MD5";
		byte b[];
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			digest.update(src_txt.getBytes());
			b = digest.digest();
			return bytesToHexStr(b);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String bytesToHexStr(byte b[]) {
		String hexString = new String();
		for (int i = 0; i < b.length; i++)
			hexString = hexString + Integer.toHexString(b[i] & 0xff);

		return hexString;
	}
	public static void main(String[] args){
		System.out.println(getMD5("admin"));	
	
	}
}