package com.framework.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Title: MD5加密工具类</p>
 * <p>Description: MD5加密工具类</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company:dhcc</p>
 * @author aden cheng
 * @version 1.0
 */
public final class MD5 {
	
    public static String encrypt(String password) {

        try {
            MessageDigest alg = MessageDigest.getInstance("MD5");
            alg.update(password.getBytes());
            byte[] digesta = alg.digest();
            return byte2hex(digesta);
        } catch (NoSuchAlgorithmException NsEx) {
            return null;
        }
    }
    
    private static String byte2hex(byte[] bstr) {
        StringBuffer hs = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < bstr.length; n++) {
            stmp = (java.lang.Integer.toHexString(bstr[n] & 0XFF));
            if (stmp.length() == 1){
            		hs.append("0");
            		hs.append(stmp);
            }else{
                hs.append(stmp);
            }    
        }
        return hs.toString();
    }

	public static void main(String [] args){
		if(args[0] != null){
			System.out.println(args[0]);
			System.out.println(MD5.encrypt(args[0]));
		}
	}
}
