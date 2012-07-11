package com.res.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTools {

	public static final String DATE_TYPE_1 = "yyyy-MM-dd";
	public static final String DATE_TYPE_2 = "yyyyMMdd";
	public static final String DATE_TYPE_3 = "yyyy-MM-dd HH:mm";
	public static final String DATE_TYPE_4 = "dd-HH";
	public static final String DATE_TYPE_5 = "yyyy年MM月dd日 HH:mm";
	public static final String DATE_TYPE_6 = "MM月dd日 HH:mm";
	public static final String DATE_TYPE_7 = "dd日 HH:mm";
	public static final String DATE_TYPE_SHOT_1 = "yyyyMM";
	public static final String DATE_TYPE_SHOT_2 = "HH:mm";
	public static final String DATE_TYPE_RSS = "EEE, d MMM yyyy HH:mm:ss";
	public static final String DATE_TYPE_RSS_PEOPLE = "EEE, d M yyyy HH:mm:ss";
	public static final String DATE_TYPE_TOPIC = "yyyy年MM月dd日";

	// 标准日期格式的format
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			DATE_TYPE_1);

	/**
	 * 将当前时间戳转化成数据库对应位数
	 * 
	 * @return 10位时间戳，精确到秒
	 */
	public static Long getTimestamp() {
		Long timestamp = 0L;
		Long temptime_l = System.currentTimeMillis();
		Double temptime_d = Math.floor((temptime_l.doubleValue() / 1000));
		timestamp = temptime_d.longValue();
		return timestamp;
	}

	public static Long getTimestamp(String strDate) throws ParseException {
		Date date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		date = sdf.parse(strDate);
		Long timestamp = 0L;
		Long temptime_l = date.getTime();
		Double temptime_d = Math.floor((temptime_l.doubleValue() / 1000));
		timestamp = temptime_d.longValue();
		return timestamp;
	}

	/**
	 * 将数据库取出的时间戳转化成页面显示日期
	 * 
	 * @param 10位时间戳timestamp
	 * @return 格式为dateFormat所设置的显示格式
	 */
	public static String getDisplayTime(Long timestamp) {
		if (null == timestamp || timestamp == 0L) {
			return "";
		} else {
			String timeformat = null;
			Long temptime_l = timestamp * 1000;
			timeformat = dateFormat.format(temptime_l);
			return timeformat;
		}
	}
	
	public static Date stringToDate(String type, String dateStr) {
		SimpleDateFormat format = new SimpleDateFormat(type);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
		}
		return date;
	}
	
	/**
	 * 获取时间字符串
	 */
	public static String dateToString(String type, Date date) {
		SimpleDateFormat format = new SimpleDateFormat(type);
		String dateStr = format.format(date);
		return dateStr;
	}
	
	
	public static void main(String[] args) {
		System.out.println(DateTools.getTimestamp());
		System.out.println(DateTools.getDisplayTime(DateTools.getTimestamp()));
	}
}