/**
 * 
 */
package com.people.dptwb.tools;

import java.io.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author mayq
 * 
 */
public class Base64Test {
	private static Base64Encoder encoder = new Base64Encoder();
	private static Base64Decoder decoder = new Base64Decoder();

	private static byte[] decode(byte[] sArr) throws Exception {
		return decoder.decodeBuffer(new String(sArr));
		
	}

	private static byte[] encode(byte[] sArr) {
		return encoder.encodeBuffer(sArr).getBytes();
	}

	private static byte[] readFile(File file) {
		byte[] byteArr = null;
		try {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));
			int len = bis.available();
			byteArr = new byte[len];
			bis.read(byteArr);
			bis.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return byteArr;
	}
	
	private static void writeFile(String fileStr, byte[] byteArr) {
		File file = new File(fileStr);
		try {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(file));
			bos.write(byteArr);
			bos.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = args[0];
		String source = null;
		String dest = null;
		String userPath = System.getProperty("user.dir");
		
		try {
			if (args.length == 3) {
				source = args[1];
				dest = args[2];
			}
			else {
				source = "source.txt";
				dest = "dest.txt";
			}
			source = userPath + File.separator + source;
			dest = userPath + File.separator + dest;
			byte[] byteArr = readFile(new File(source));
			byte[] destArr = null;
			
			if (s1.equals("1")) {
				//编码
				destArr = encode(byteArr);
				
			}
			else if (s1.equals("2")) {
				//解码
				destArr = decode(byteArr);
			}
			writeFile(dest, destArr);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
		}
		
		
		

	}

}
