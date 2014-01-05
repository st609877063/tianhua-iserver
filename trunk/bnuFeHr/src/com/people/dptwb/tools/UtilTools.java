package com.people.dptwb.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 实用工具类
 */
public class UtilTools {

	public static final Logger logger = Logger.getLogger(UtilTools.class);

	public static final String DATE_TYPE_1 = "yyyy-MM-dd";
	public static final String DATE_TYPE_2 = "yyyyMMdd";
	public static final String DATE_TYPE_3 = "yyyy-MM-dd HH:mm";
	public static final String DATE_TYPE_4 = "dd-HH";
	public static final String DATE_TYPE_5 = "yyyy年MM月dd日 HH:mm";
	public static final String DATE_TYPE_6 = "MM月dd日 HH:mm";
	public static final String DATE_TYPE_7 = "dd日 HH:mm";
	public static final String DATE_TYPE_SHOT_1 = "yyyyMM";
	public static final String DATE_TYPE_SHOT_2 = "HH:mm";
	public static final String DATE_TYPE_SHOT_3 = "yyyyMMddHHmm";
	public static final String DATE_TYPE_RSS = "EEE, d MMM yyyy HH:mm:ss";
	public static final String DATE_TYPE_RSS2 = "EEE, d MMM yyyy HH:mm:ss Z";
	public static final String DATE_TYPE_RSS_PEOPLE = "EEE, d M yyyy HH:mm:ss";
	public static final String DATE_TYPE_THIRDPLANT="EEE MMM dd HH:mm:ss z yyyy";//第三方平台日期转换
	public static final String PEOPLE_SHORT_DOMAIN = "http://peopleurl.cn/";
	public static final int PEOPLE_SHORT_DOMAIN_LENGTH = 20;
	public static final int PEOPLE_SHORT_URL_LENGTH = 26;
	public static final String DATE_TYPE_TOPIC = "yyyy年MM月dd日";
	
	public static final String DATE_TYPE_TO_DATE = "yyyy-MM-dd";
	public static final String DATE_TYPE_TO_DATE_CHINESE = "yyyy年MM月dd日";
	public static final String DATE_TYPE_TO_DATE_SHORT = "yyyyMMdd";
	public static final String DATE_TYPE_TO_MINUTE = "yyyy-MM-dd HH:mm";
	public static final String DATE_TYPE_TO_SECOND = "yyyy-MM-dd HH:mm:ss";
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	public static final SimpleDateFormat DATEFORMAT_TO_DATE = new SimpleDateFormat(DATE_TYPE_TO_DATE);
	public static final SimpleDateFormat DATEFORMAT_TO_DATE_SHORT = new SimpleDateFormat(DATE_TYPE_TO_DATE_SHORT);
	public static final SimpleDateFormat DATEFORMAT_TO_DATE_CHINESE = new SimpleDateFormat(DATE_TYPE_TO_DATE_CHINESE);
	public static final SimpleDateFormat DATEFORMAT_TO_MINUTE = new SimpleDateFormat(DATE_TYPE_TO_MINUTE);
	public static final SimpleDateFormat DATEFORMAT_TO_SECOND = new SimpleDateFormat(DATE_TYPE_TO_SECOND);
	public static final SimpleDateFormat THIRDPLANT_DATE = new SimpleDateFormat(DATE_TYPE_THIRDPLANT,Locale.US);

	public static String getOnlineTimeStr(Long timestamp) {

		if (timestamp == null || timestamp == 0L) {
			return "";
		}

		int mm = 0, d = 0, h = 0, m = 0;
		String ret = "";

		if (timestamp > 60 * 60 * 24 * 30) {
			mm = (int) (timestamp / 60 / 60 / 24 / 30);
			timestamp = timestamp - (mm * 60 * 60 * 24 * 30);
		}
		if (timestamp > 60 * 60 * 24) {
			d = (int) (timestamp / 60 / 60 / 24);
			timestamp = timestamp - (d * 60 * 60 * 24);
		}
		if (timestamp > 60 * 60) {
			h = (int) (timestamp / 60 / 60);
			timestamp = timestamp - (h * 60 * 60);
		}
		if (timestamp > 60) {
			m = (int) (timestamp / 60);
			timestamp = timestamp - (m * 60);
		}

		// mm = (int) (timestamp / 60 / 60 / 24 / 30);
		// d = (int) (timestamp / 60 / 60 / 24);
		// h = (int) (timestamp / 60 / 60);
		// m = (int) (timestamp / 60);

		if (mm > 0)
			ret += (mm + "月");
		if (d > 0)
			ret += (d + "天");
		if (h > 0)
			ret += (h + "小时");
		if (m > 0)
			ret += (m + "分钟");

		return ret;
	}

	/**
	 * 返回时间戳对应的日期，格式：1分钟内等
	 * @param timestamp
	 * @return
	 */
	public static String getDisplayTime(Long timestamp) {
		if (timestamp == null || timestamp == 0L) {
			return "";
		} else {
			String timeformat = null;
			Long temptime_l = timestamp * 1000;
			Long nowtime = System.currentTimeMillis();

			Long passtime = nowtime - temptime_l;
			String strtime = "";
			if (passtime <= 60000) {
				strtime = "1分钟内";
			}
			if (passtime > 60000 && passtime < 60000 * 60) {
				strtime = (passtime / 60000) + "分钟前";
			}
			if (passtime >= 60 * 60000 && passtime < 12 * 60 * 60 * 1000) {
				strtime = (passtime / 3600000) + "小时前";
			}
			if (strtime.length() > 0) {
				return strtime;
			}

			timeformat = new SimpleDateFormat("yyyy-MM-dd").format(temptime_l);
			return timeformat;
		}
	}
	
	
	/**
	 * 计算第三方平台绑定有效期的文字表示
	 * @param expiryTime
	 * @return
	 */
	public static String getRemainExpiryTimeStr(long remainTime) {
		String strTime = "";
		long remainDay = 0L;
		if (remainTime <= 0L) {
			return "已过期";
		} else {
			
			if (remainTime > 60 * 60 * 24) {
				// 有效期超过1天，计算天数
				Double temptime_d = Math.ceil((Long.valueOf(remainTime).doubleValue() / (60 * 60 * 24)));
				remainDay = temptime_d.longValue();
				strTime = remainDay + "天";
			}
			else {
				// 有效期小于1天，计算小时数
				Double temptime_d = Math.ceil((Long.valueOf(remainTime).doubleValue() / (60 * 60)));
				remainDay = temptime_d.longValue();
				strTime = remainDay + "小时";
			}
		}
		
		return strTime;
	}
	
	

	/**
	 * 返回时间戳对应的日期，格式：yyyy-MM-dd
	 * @param timestamp
	 * @return
	 */
	public static String getDisplayTimeToDate(Long timestamp) {
		if (timestamp == null || timestamp == 0L) {
			return "";
		} else {
			String timeformat = null;
			Long temptime_l = timestamp * 1000;
			timeformat = DATEFORMAT_TO_DATE.format(temptime_l);
			return timeformat;
		}
	}

	/**
	 * 返回时间戳对应的日期，格式：yyyy年MM月dd日
	 * @param timestamp
	 * @return
	 */
	public static String getDisplayTimeOnlyDateChinese(Long timestamp) {
		if (timestamp == null || timestamp == 0L) {
			return "";
		} else {
			String timeformat = null;
			Long temptime_l = timestamp * 1000;
			timeformat = DATEFORMAT_TO_DATE_CHINESE.format(temptime_l);
			return timeformat;
		}
	}
	
	public static String getDisplayTimeOnlyDateShort(Long timestamp) {
		if (timestamp == null || timestamp == 0L) {
			return "";
		} else {
			String timeformat = null;
			Long temptime_l = timestamp * 1000;
			timeformat = DATEFORMAT_TO_DATE_SHORT.format(temptime_l);
			return timeformat;
		}
	}
	
	/**
	 * 返回时间戳对应的日期，格式：yyyy-MM-dd HH:mm
	 * @param timestamp
	 * @return
	 */
	public static String getDisplayTimeToMinute(Long timestamp) {
		if (timestamp == null || timestamp == 0L) {
			return "";
		}
		String timeformat = null;
		Long temptime_l = timestamp * 1000;
		timeformat = DATEFORMAT_TO_MINUTE.format(temptime_l);
		return timeformat;
	}
	

	/**
	 * 返回时间戳对应的日期，格式：yyyy-MM-dd HH:mm:ss
	 * @param timestamp
	 * @return
	 */
	public static String getAdminDisplayTime(Long timestamp) {
		if (timestamp == null || timestamp == 0L) {
			return "";
		}
		String timeformat = null;
		Long temptime_l = timestamp * 1000;
		timeformat = DATEFORMAT_TO_SECOND.format(temptime_l);
		return timeformat;
	}

	/**
	 * 将当前时间戳转化成数据库对应位数
	 * 
	 * @return 10位时间戳，精确到秒
	 */
	public static Long getTimestamp() {
		long timestamp = 0L;
		long temptime_l = System.currentTimeMillis();
		Double temptime_d = Math.floor((Long.valueOf(temptime_l).doubleValue() / 1000));
		timestamp = temptime_d.longValue();
		return timestamp;
	}

	/**
	 * 将当前时间戳转化成数据库对应位数
	 * 
	 * @param time
	 * @return
	 */
	public static Long getTimestamp(long time) {
		long timestamp = 0L;
		long temptime_d = (long) Math.floor((time / 1000));
		timestamp = temptime_d;
		return timestamp;
	}
	
	
	/**
	 * 将当前时间戳转化成数据库对应位数
	 * 
	 * @param time
	 * @return
	 */
	public static Long getTimestampNew(long time) {
		long timestamp = 0L;
		long temptime_d = (long) Math.floor((time));
		timestamp = temptime_d;
		return timestamp;
	}

	/**
	 * 获取时间字符串
	 */
	public static String dateToString(String type, Date date) {
		SimpleDateFormat format = new SimpleDateFormat(type);
		String dateStr = format.format(date);
		return dateStr;
	}

	/**
	 * 获取时间字符串
	 * 
	 * @param type
	 * @param date
	 * @param locale
	 * @return
	 */
	public static String dateToString(String type, Date date, Locale locale) {
		SimpleDateFormat format = new SimpleDateFormat(type, locale);
		String dateStr = format.format(date);
		return dateStr;
	}

	/**
	 * 获取时间
	 */
	public static Date stringToDate(String type, String dateStr) {
		SimpleDateFormat format = new SimpleDateFormat(type);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			logger.error("时间转换错误", e);
		}
		return date;
	}

	/**
	 * 获取时间
	 * 
	 * @param type
	 * @param dateStr
	 * @param locale
	 * @return
	 */
	public static Date stringToDate(String type, String dateStr, Locale locale) {
		SimpleDateFormat format = new SimpleDateFormat(type, locale);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			logger.error("时间转换错误", e);
		}
		return date;
	}

	public static Long getGiveTimestamp(String formatStr, String dateStr) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			logger.error("转换字符串：" + dateStr + "时出现异常");
			throw e;
		}
		Long timestamp = 0L;
		Long temptime_l = date.getTime();
		Double temptime_d = Math.floor((temptime_l.doubleValue() / 1000));
		timestamp = temptime_d.longValue();
		return timestamp;
	}

	/**
	 * 将List<object>转以split参数分隔的字符串
	 */
	public static String listToString(List<?> list, String split) {
		StringBuilder str = new StringBuilder();
		String returnStr = "";
		if (list.size() > 0) {
			for (Object obj : list) {
				str.append(obj.toString()).append(split);
			}
			returnStr = str.substring(0, str.length() - 1);
		}
		return returnStr;
	}

	/**
	 * 将Set<object>转以split参数分隔的字符串
	 */
	public static String listToString(Set<?> list, String split) {
		StringBuilder str = new StringBuilder();
		String returnStr = "";
		if (list.size() > 0) {
			Iterator<?> itr = list.iterator();
			while (itr.hasNext()) {
				Object obj = itr.next();
				str.append(obj.toString()).append(split);
			}
			returnStr = str.substring(0, str.length() - 1);
		}
		return returnStr;
	}

	/**
	 * 将数组<object>转以逗号分隔的字符串
	 */
	public static String listToString(Object[] list, String split) {
		StringBuilder str = new StringBuilder();
		String returnStr = "";
		if (list.length > 0) {
			for (Object obj : list) {
				str.append(obj.toString()).append(split);
			}
			returnStr = str.substring(0, str.length() - 1);
		}
		return returnStr;
	}

	

	/**
	 * 过滤html代码
	 * 
	 * @param obj
	 * @return
	 */
	public static String HTMLEncode(String str) {
		String encodestr = str;
		encodestr = encodestr.replace("&", "&amp;");
		encodestr = encodestr.replace("<", "&lt;");
		encodestr = encodestr.replace(">", "&gt;");
		encodestr = encodestr.replace("\"", "&quot;");
		return encodestr;
	}

	/**
	 * Decode html代码
	 * 
	 * @param obj
	 * @return
	 */
	public static String HTMLDecode(String str) {
		String decodestr = str;
		decodestr = decodestr.replace("&lt;", "<");
		decodestr = decodestr.replace("&gt;", ">");
		decodestr = decodestr.replace("&quot;", "\"");
		decodestr = decodestr.replace("&amp;", "&");
		return decodestr;
	}
	
	
	/**
	 * 获取随机数字
	 * 
	 * @param num
	 *            随机数字的位数
	 * @return
	 */
	public static String genRandomNumber(int num) {
		
		String str = "";
		long ltmp = 0L;
		for (int i = 0; i < num; i++) {
			ltmp = Math.round(Math.random() * 9);
			str += String.valueOf(ltmp);
		}
		return str;
	}
	

	/**
	 * 获取随机字符串
	 * 
	 * @param num
	 *            随机字符串的位数
	 * @return
	 */
	public static String genRandomCode(int num) {
		String inviteCode = "";
		for (int i = 0; i < num; i++) {
			inviteCode += getRandomChar();
		}
		return inviteCode;
	}

	public static String getRandomChar() {
		int rand = (int) Math.round(Math.random() * 2);
		long itmp = 0L;
		char ctmp = '\u0000';

		switch (rand) {
		case 1:
			itmp = Math.round(Math.random() * 25 + 65);
			ctmp = (char) itmp;
			break;
		case 2:
			itmp = Math.round(Math.random() * 25 + 97);
			ctmp = (char) itmp;
			break;
		default:
			itmp = Math.round(Math.random() * 9);
			return String.valueOf(itmp);
		}
		return String.valueOf(ctmp);
	}

	/** *********************汉字************************* */
	/**
	 * 返回第一个汉字的位置,或系统保留字
	 */
	public static int getFirstCNCharacter(String str) {
		int position = -1;
		char[] strToChar = str.toCharArray();
		if (strToChar.length > 0) {
			for (int i = 0; i < strToChar.length; i++) {
				if (isGBK(strToChar[i]) || isKeyWord(strToChar[i])) {
					position = i;
					return position;
				}
			}
		}
		return position;
	}

	public static boolean isGBK(char c) {
		Character ch = new Character(c);
		String sCh = ch.toString();
		try {
			byte[] bb = sCh.getBytes("GBK");
			if (bb.length > 1) {
				return true;
			}
		} catch (java.io.UnsupportedEncodingException ex) {
			return false;
		}
		return false;
	}

	/**
	 * 检查是否是系统保留字
	 * */
	public static boolean isKeyWord(char c) {
		char[] keyWord = { '[', ']' };
		for (int i = 0; i < keyWord.length; i++) {
			if (c == keyWord[i]) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkSearchContent(String content) {
		if (StringUtils.isNotBlank(content) && !content.matches("^([a-zA-Z0-9]{4,16}|[\u4e00-\u9fa5]{2,8})$")) {
			return false;
		}
		return true;
	}

	public static boolean checkPersonUrl(String personUrl) {
		Pattern pt1 = Pattern.compile("^[a-z0-9]{6,20}$");
		Pattern pt2 = Pattern.compile("^[0-9]{6,20}$");
		if (StringUtils.isNotBlank(personUrl) && (!pt1.matcher(personUrl).matches() || pt2.matcher(personUrl).matches())) {
			return false;
		}
		return true;
	}

	/** 把字符串数组转化成MAP */
	public static List<String> StringArray2List(String[] strings) {
		List<String> list = null;
		if (strings.length > 0) {
			list = new ArrayList<String>();
			for (String string : strings) {
				list.add(string);
			}
		}
		return list;
	}

	/** *************************IP******************************* */
	/**
	 * 检查IP
	 */
	public static boolean checkIp(String ip) {
		Pattern pattern = Pattern
				.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");

		Matcher matcher = pattern.matcher(ip);

		return matcher.matches();
	}

	/**
	 * 将IP转化为十进制整数
	 */
	public static Long ipConvert(String ip) throws NumberFormatException {
		Long ipreturn = 0L;
		if (checkIp(ip)) {
			String ip32 = "";
			String[] goodip = ip.split("\\.");
			for (int i = 0; i < goodip.length; i++) {
				String ip8 = Integer.toBinaryString(Integer.parseInt(goodip[i]));
				ip8 = StringUtils.leftPad(ip8, 8, "0");
				ip32 += ip8;
			}
			ipreturn = Long.parseLong(ip32, 2);
		}
		return ipreturn;
	}

	// 去除整型数组里面重复的值
	public static Integer[] array_unique(Integer[] a) {
		List<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < a.length; i++) {
			if (!list.contains(a[i])) {
				list.add(a[i]);
			}
		}
		return list.toArray(new Integer[list.size()]);
	}

	/**
	 * 获得随机正整数
	 * 
	 * @param max
	 *            随机最大值
	 * @param offset
	 *            偏移值
	 * @return max>offset 随机正整数，else -1
	 * */
	public static Integer RandomInt(int max, int offset) {
		int rand = -1;
		if (max > offset) {
			rand = (int) (Math.random() * max);
			if (max - offset < rand) {
				rand = max - offset;
			}
		}
		return rand;
	}

	/**
	 * null字符串处理
	 */
	public static String nvl(Object s) {
		if (s == null)
			return "";
		else
			return s.toString();
	}

	public static boolean copyFile(String srcName, String destName) {
		boolean re = false;
		FileOutputStream fos = null;
		FileInputStream fis = null;
		FileChannel foc = null;
		FileChannel fic = null;

		try {
			fos = new FileOutputStream(destName);
			fis = new FileInputStream(srcName);
			foc = fos.getChannel();
			fic = fis.getChannel();
			foc.transferFrom(fic, 0, fic.size());
			re = true;
		} catch (Exception e) {
			logger.error("文件复制时出现异常，源路径：" + srcName + "，目标路径：" + destName, e);
		} finally {
			if (foc != null) {
				try {
					foc.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (fic != null) {
				try {
					fic.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return re;
	}

	public static boolean fileExist(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			return true;
		} else {
			return false;
		}
	}

	public static File mkdir(String path) {
		File dirFile = null;
		try {
			dirFile = new File(path);
			boolean bFile = dirFile.exists();
			if (bFile) {
				// System.out.println("The folder exists.");
			} else {
				// System.out.println("The folder do not exist，now trying to create a one...");
				bFile = dirFile.mkdir();
				if (bFile) {
					System.out.println("创建文件夹");
				} else {
					System.out.println(" 文件夹创建失败，清确认磁盘没有写保护并且空件足够");
				}
			}
		} catch (Exception err) {
			System.err.println("ELS - Chart : 文件夹创建发生异常");
			err.printStackTrace();
		}
		return dirFile;
	}
	
	
	public static String cleanHTMLScript(String str) {
		if (StringUtils.isBlank(str)) {
			return str;
		}

		String message = null;
		try {
			message = URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error("进行URLDecoder时出现异常", e);
		}
		if (StringUtils.isNotBlank(message)) {
			message = message.replace("<", "");
			message = message.replace(">", "");
			message = message.replace("\"", "");
			message = message.replace("\\", "");
			// message = message.replace ("/","");
			message = message.replace("(", "");
			message = message.replace(")", "");
		}
		return message;
	}

	/********************************* 公用方法 begin *************************************************/
	public static long getEndTime() {
		long curr = UtilTools.getTimestamp();
		long yestoday = curr - (24 * 60 * 60);
		String dateStr = UtilTools.getAdminDisplayTime(yestoday);
		dateStr = dateStr.substring(0, 10) + " 23:59:59";
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date1 = null;
		try {
			date1 = sdf.parse(dateStr);
		} catch (ParseException e) {
			logger.error("getEndTime()日期转换时出现异常", e);
		}
		long startTime = date1.getTime();
		startTime = UtilTools.getTimestamp(startTime);
		return startTime;
	}

	public static long getOneMonthBeforeTime() {
		long curr = UtilTools.getTimestamp();
		long yestoday = curr - (24 * 60 * 60 * 7 * 30);
		String dateStr = UtilTools.getAdminDisplayTime(yestoday);
		dateStr = dateStr.substring(0, 10) + " 23:59:59";
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date1 = null;
		try {
			date1 = sdf.parse(dateStr);
		} catch (ParseException e) {
			logger.error("getOneMonthBeforeTime()日期转换时出现异常", e);
		}
		long startTime = date1.getTime();
		startTime = UtilTools.getTimestamp(startTime);
		return startTime;
	}

	public static int getDaysAfterTime(int dayNum) {
		int day = 0;
		long curr = UtilTools.getTimestamp();
		long tomorrday = curr + (24 * 60 * 60 * dayNum);
		String dateStr = DATEFORMAT_TO_DATE_SHORT.format(tomorrday * 1000);
		if(StringUtils.isNumeric(dateStr)) {
			day = Integer.parseInt(dateStr);
		}
		return day;
	}

	public static int getDaysBetween(int endDay) {
		int day = 0;
		long curr = UtilTools.getTimestamp();
		String dateStr = endDay + " 23:59:59";
		String pattern = "yyyyMMdd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date1 = null;
		try {
			date1 = sdf.parse(dateStr);
			long endTime = date1.getTime();
			endTime = UtilTools.getTimestamp(endTime);
			day = (int) ((endTime-curr)/(24 * 60 * 60));
		} catch (ParseException e) {
			logger.error("getDaysBetween()日期转换时出现异常", e);
		}
		
		return day;
	}
	
	public static int getCurrentDay() {
		int currDay = 0;
		try {
			currDay = Integer.parseInt(sdf.format(new Date(System.currentTimeMillis())));
		} catch (Exception e) {
			logger.error("日期转换时出现异常", e);
		}
		return currDay;
	}

	public static String getCurrentDay(String fomat) {
		String currDay = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(fomat);
			currDay = sdf.format(new Date(System.currentTimeMillis()));
		} catch (Exception e) {
			logger.error("日期转换时出现异常", e);
		}
		return currDay;
	}
	
	
	
	/**
	 * 检查是否超过多少小时
	 * 
	 * @return
	 */
	public static boolean checkPassTime(long lastTime, long currTime, int intervalTime) {
		if ((lastTime + intervalTime) < currTime) {
			return true;
		}
		return false;
	}

	/**
	 * 检查是否需要刷新数据（超过1天）
	 * 
	 * @param lastTime
	 * @return
	 */
	public static boolean checkRefreshFlag(int lastDay, int currDay) {
		if (currDay > lastDay) {
			return true;
		}
		return false;
	}

	/**第三方平台转换本平台时间格式
	 * @param dataStr： Fri May 17 10:30:13 CST 2013
	 * @return
	 * @throws ParseException
	 */
	public static long dateToTimeLong(String dataStr) throws ParseException{
		
		return UtilTools.getTimestamp(UtilTools.THIRDPLANT_DATE.parse(dataStr).getTime());
		
	}
	/********************************* 公用方法 end *************************************************/

	/**
	 * 字符串转map 类似 video : {picurl : xxx,player : xxx,realurl : xxx,shorturl :
	 * xxx,title : xxx}
	 * 
	 * @param jsonstr
	 * @return
	 */
	public static Map<String, String> strToMap(String jsonstr) {
		List<Integer> str1= new ArrayList<Integer>();
		List<Integer> str2= new ArrayList<Integer>();
		for(int i=0;i<jsonstr.length();i++){
			if(jsonstr.charAt(i)=='{'){
				str1.add(i);
			}
			else if(jsonstr.charAt(i)=='}'){
				str2.add(i);
			}
		}
		String[] str = String.valueOf(jsonstr.subSequence(str1.get(0) + 1, str2.get(str2.size()-1))).split(",");
		Map<String, String> strmap = new HashMap<String, String>();
		for (int j = 0; str.length>0&&j < str.length; j++) {
			if(!str[j].contains("=")){
				continue;
			}
			strmap.put(str[j].substring(0, str[j].indexOf("=")).trim(),
					str[j].substring(str[j].indexOf("=") + 1));
		}
		return strmap;
	}
	
	public static void main(String[] args) {
		System.out.println(UtilTools.getDaysAfterTime(180));
	}

}