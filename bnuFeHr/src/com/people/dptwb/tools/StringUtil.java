package com.people.dptwb.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;


public class StringUtil {
	public static String UTF8_3BYTE_REGEX = "[\u0800-\uFFFF]";
	public static String UTF8_2BYTE_REGEX = "[\u0080-\u07FF]";
	public static String UTF8_1BYTE_REGEX = "[\u0000-\u007F]";
	public static Pattern UTF8_3BYTE_STR_PATTERN = Pattern.compile(UTF8_3BYTE_REGEX);
	public static Pattern UTF8_2BYTE_STR_PATTERN = Pattern.compile(UTF8_2BYTE_REGEX);
	public static Pattern UTF8_1BYTE_STR_PATTERN = Pattern.compile(UTF8_1BYTE_REGEX);

	/**
	 * 
	 * 
	 * 
	 * <p>
	 * 取文本长度区分中文（中文算两个长度）
	 * </p>
	 * 
	 * @param text
	 * 
	 * @return int
	 * 
	 * @author tianyu
	 * 
	 * @date 2010-12-28
	 */

	public static int getLength(String text) {
		int length = 0;
		for (int i = 0; i < text.length(); i++) {
			if (new String(text.charAt(i) + "").getBytes().length > 1) {
				length += 2;
			} else {
				length += 1;
			}
		}
		return length / 2;
	}

	// ------------ 同方外包添加 开始 ---------------

	/**
	 * 将特殊字符转换(处理xml中的特殊字符)
	 * 
	 * @param str
	 * @return
	 */
	public static String specialCharChange(String str) {
		StringBuffer sb = new StringBuffer("");
		if (str == null) {
			return "";
		}
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			switch (ch) {
			case '>':
				sb.append("<![CDATA[&gt;]]>");
				break;
			case '<':
				sb.append("<![CDATA[&lt;]]>");
				break;
			case '"':
				sb.append("&quot;");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '&':
				sb.append("<![CDATA[&amp;]]>");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			default:
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	/**
	 * 是否相存在xml特殊字符)
	 * 
	 * @param str
	 * @return
	 */
	public boolean isSpecialCharIn(String str) {
		boolean Flag = false;
		if (str != null && str.indexOf(">") > 0)
			Flag = true;
		if (str != null && str.indexOf("<") > 0)
			Flag = true;
		if (str != null && str.indexOf("\"") > 0)
			Flag = true;
		if (str != null && str.indexOf("\'") > 0)
			Flag = true;
		if (str != null && str.indexOf("\n") > 0)
			Flag = true;
		if (str != null && str.indexOf("\r") > 0)
			Flag = true;
		if (str != null && str.indexOf("&") > 0)
			Flag = true;
		if (str != null && str.indexOf("\\") > 0)
			Flag = true;

		return Flag;

	}

	// str是否为数字
	public boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	// ------------ 同方外包添加 结束 ---------------

	/**
	 * 将GBK编码的字符串转换为UTF-8编码的字节数组
	 * 
	 * @param gbkStr
	 * @return
	 */
	public static byte[] gbkStr2utf8StrNew(String gbkStr) {
		if (StringUtils.isBlank(gbkStr)) {
			return null;
		}

		byte[] fullBytes = null;

		// Step 1: 得到GBK编码下的字符数组，一个字符对应这里的一个c
		char c[] = gbkStr.toCharArray();

		List<Byte> byteList = new ArrayList<Byte>();

		for (int i = 0; i < c.length; i++) {
			char c1 = c[i];
			byte[] reBytes = null;
			Matcher matcher = UTF8_3BYTE_STR_PATTERN.matcher(String.valueOf(c1));
			if (matcher.matches()) {
				// 是3字节UTF-8的字符
				reBytes = gbkStr2utf8Str3Byte(c1);

			} else {
				matcher = UTF8_2BYTE_STR_PATTERN.matcher(String.valueOf(c1));
				if (matcher.matches()) {
					// 是2字节UTF-8的字符
					reBytes = gbkStr2utf8Str2Byte(c1);
				} else {
					// 是1字节UTF-8的字符
					reBytes = gbkStr2utf8Str1Byte(c1);
				}
			}

			for (byte aByte : reBytes) {
				// 把得到的字节数字按顺序存放
				byteList.add(aByte);
			}
		}

		if (byteList.size() > 0) {
			// 转换字节LIST到结果字节数组
			Byte[] newBytes = byteList.toArray(new Byte[0]);
			fullBytes = new byte[newBytes.length];
			for (int i = 0; i < newBytes.length; i++) {
				fullBytes[i] = newBytes[i].byteValue();
			}
		}
		return fullBytes;
	}

	/**
	 * 一个GBK字符到3字节UTF-8字符的转换
	 * 
	 * @param gbkChr
	 * @return
	 */
	private static byte[] gbkStr2utf8Str3Byte(char gbkChr) {

		// Step 2: UTF-8使用3个字节存放一个中文字符，所以长度必须为字符的3 倍
		byte[] bf = new byte[3];

		// Step 3: 循环将字符的GBK编码转换成UTF-8编码

		// Step 3-1：将字符的ASCII编码转换成2进制值
		int m = (int) gbkChr;
		String word = Integer.toBinaryString(m);
		// System.out.println(word);

		// Step 3-2：将2进制值补足16位(2个字节的长度)
		StringBuffer sb = new StringBuffer();
		int len = 16 - word.length();
		for (int j = 0; j < len; j++) {
			sb.append("0");
		}
		// Step 3-3：得到该字符最终的2进制GBK编码
		// 形似：1000 0010 0111 1010
		sb.append(word);

		// Step 3-4：最关键的步骤，根据UTF-8的汉字编码规则，首字节
		// 以1110开头，次字节以10开头，第3字节以10开头。在原始的2进制
		// 字符串中插入标志位。最终的长度从16--->16+4+2+2=24。
		sb.insert(0, "1110");
		sb.insert(8, "10");
		sb.insert(16, "10");
		// System.out.println(sb.toString());

		// Step 3-5：将新的字符串进行分段截取，截为3个字节
		String s1 = sb.substring(0, 8);
		String s2 = sb.substring(8, 16);
		String s3 = sb.substring(16);

		// Step 3-6：最后的步骤，把代表3个字节的字符串按2进制的方式
		// 进行转换，变成2进制的整数，再转换成16进制值
		byte b0 = Integer.valueOf(s1, 2).byteValue();
		byte b1 = Integer.valueOf(s2, 2).byteValue();
		byte b2 = Integer.valueOf(s3, 2).byteValue();

		// Step 3-7：把转换后的3个字节按顺序存放到字节数组的对应位置

		bf[0] = b0;
		bf[1] = b1;
		bf[2] = b2;
		return bf;
	}

	/**
	 * 一个GBK字符到一个2字节UTF-8字符的转换
	 * 
	 * @param gbkChr
	 * @return
	 */
	private static byte[] gbkStr2utf8Str2Byte(char gbkChr) {
		// 转换的是2字节的UTF-8字符
		byte[] bf = new byte[2];

		// Step 3: 循环将字符的GBK编码转换成UTF-8编码

		// Step 3-1：将字符的ASCII编码转换成2进制值
		int m = (int) gbkChr;
		String word = Integer.toBinaryString(m);
		// System.out.println(word);

		// Step 3-2：将2进制值补足11位(2个字节的长度)
		StringBuffer sb = new StringBuffer();
		int len = 11 - word.length();
		for (int j = 0; j < len; j++) {
			sb.append("0");
		}
		// Step 3-3：得到该字符最终的2进制GBK编码
		// 形似：1000 0010 0111 1010
		sb.append(word);

		// Step 3-4：最关键的步骤，根据UTF-8的两字节区间编码规则，首字节
		// 以110开头，次字节以10开头，在原始的2进制
		// 字符串中插入标志位。最终的长度从11--->11+3+2=16。
		sb.insert(0, "110");
		sb.insert(8, "10");
		// System.out.println(sb.toString());

		// Step 3-5：将新的字符串进行分段截取，截为3个字节
		String s1 = sb.substring(0, 8);
		String s2 = sb.substring(8, 16);

		// Step 3-6：最后的步骤，把代表3个字节的字符串按2进制的方式
		// 进行转换，变成2进制的整数，再转换成16进制值
		byte b0 = Integer.valueOf(s1, 2).byteValue();
		byte b1 = Integer.valueOf(s2, 2).byteValue();

		// Step 3-7：把转换后的3个字节按顺序存放到字节数组的对应位置

		bf[0] = b0;
		bf[1] = b1;

		return bf;
	}

	/**
	 * 一个GBK字符到一个1字节UTF-8字符的转换
	 * 
	 * @param gbkChr
	 * @return
	 */
	private static byte[] gbkStr2utf8Str1Byte(char gbkChr) {

		// 一个字节的UTF-8字符
		byte[] bf = new byte[1];

		// Step 3: 循环将字符的GBK编码转换成UTF-8编码

		// Step 3-1：将字符的ASCII编码转换成2进制值
		int m = (int) gbkChr;
		String word = Integer.toBinaryString(m);
		// System.out.println(word);

		// Step 3-2：将2进制值补足8位(1个字节的长度)
		StringBuffer sb = new StringBuffer();
		int len = 11 - word.length();
		for (int j = 0; j < len; j++) {
			sb.append("0");
		}
		// Step 3-3：得到该字符最终的2进制GBK编码
		// 形似：1000 0010 0111 1010
		sb.append(word);

		// Step 3-6：最后的步骤，把代表3个字节的字符串按2进制的方式
		// 进行转换，变成2进制的整数，再转换成16进制值
		byte b0 = Integer.valueOf(sb.toString(), 2).byteValue();

		// Step 3-7：把转换后的3个字节按顺序存放到字节数组的对应位置

		bf[0] = b0;

		return bf;
	}

	/**
	 * Gbk2utf8.
	 * 
	 * @param chenese
	 *            the chenese
	 * 
	 * @return the byte[]
	 */
	public byte[] gbk2utf8(String chenese) {

		// Step 1: 得到GBK编码下的字符数组，一个中文字符对应这里的一个c
		char c[] = chenese.toCharArray();

		// Step 2: UTF-8使用3个字节存放一个中文字符，所以长度必须为字符的3 倍
		byte[] fullByte = new byte[3 * c.length];

		// Step 3: 循环将字符的GBK编码转换成UTF-8编码
		for (int i = 0; i < c.length; i++) {

			// Step 3-1：将字符的ASCII编码转换成2进制值
			int m = (int) c[i];
			String word = Integer.toBinaryString(m);
			System.out.println(word);

			// Step 3-2：将2进制值补足16位(2个字节的长度)
			StringBuffer sb = new StringBuffer();
			int len = 16 - word.length();
			for (int j = 0; j < len; j++) {
				sb.append("0");
			}
			// Step 3-3：得到该字符最终的2进制GBK编码
			// 形似：1000 0010 0111 1010
			sb.append(word);

			// Step 3-4：最关键的步骤，根据UTF-8的汉字编码规则，首字节
			// 以1110开头，次字节以10开头，第3字节以10开头。在原始的2进制
			// 字符串中插入标志位。最终的长度从16--->16+3+2+2=24。
			sb.insert(0, "1110");
			sb.insert(8, "10");
			sb.insert(16, "10");
			System.out.println(sb.toString());

			// Step 3-5：将新的字符串进行分段截取，截为3个字节
			String s1 = sb.substring(0, 8);
			String s2 = sb.substring(8, 16);
			String s3 = sb.substring(16);

			// Step 3-6：最后的步骤，把代表3个字节的字符串按2进制的方式
			// 进行转换，变成2进制的整数，再转换成16进制值
			byte b0 = Integer.valueOf(s1, 2).byteValue();
			byte b1 = Integer.valueOf(s2, 2).byteValue();
			byte b2 = Integer.valueOf(s3, 2).byteValue();

			// Step 3-7：把转换后的3个字节按顺序存放到字节数组的对应位置
			byte[] bf = new byte[3];
			bf[0] = b0;
			bf[1] = b1;
			bf[2] = b2;

			fullByte[i * 3] = bf[0];
			fullByte[i * 3 + 1] = bf[1];
			fullByte[i * 3 + 2] = bf[2];

			// Step 3-8：返回继续解析下一个中文字符
		}
		return fullByte;
	}

	public static void main(String[] args) {

		try {
			// char[] c1 = Character.toChars(\u0081);

			String s2 = "中文测试器aaa";
			System.out.println(s2);

			StringUtil util = new StringUtil();
			byte[] fullByte = util.gbkStr2utf8StrNew(s2);
			String fullStr = new String(fullByte);
			byte[] full1 = fullStr.getBytes("UTF-8");
			byte[] full2 = s2.getBytes("UTF-8");
			System.out.println("string from GBK to UTF-8 byte: " + fullStr);
			String infoContent = new String(s2.getBytes(), "UTF-8");
			String infoContent1 = new String(fullStr.getBytes("UTF-8"), "UTF-8");
			System.out.println(infoContent);
			System.out.println(infoContent1);
			// String s1 = "@";
			// int codePoint = s2.codePointAt(0);
			// System.out.println(Integer.toBinaryString(codePoint));
			// util.gbk2utf8New(s2);
			// byte[] fullByte = util.gbk2utf81Byte(s2.charAt(0));

			// System.out.println(s1.length());
			// char[] c1 = s1.toCharArray();
			// System.out.println(c1.length);

			// StringUtil util = new StringUtil();
			// byte[] fullByte = util.gbk2utf8("中文");
			// String fullStr = new String(fullByte, "UTF-8");
			// System.out.println("string from GBK to UTF-8 byte:? " + fullStr);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// try {
		// String s1 = "中文vc";
		// System.out.println(s1.length());
		// char[] c1 = s1.toCharArray();
		// System.out.println(c1.length);
		//
		// // StringUtil util = new StringUtil();
		// // byte[] fullByte = util.gbk2utf8("中文");
		// // String fullStr = new String(fullByte, "UTF-8");
		// // System.out.println("string from GBK to UTF-8 byte:? " + fullStr);
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

}
