package com.people.dptwb.tools;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 日期格式化类
 * 
 * @author lihang
 * @version $vision: 1.0 $ $Date: 2011/08/20 16:40:28 $
 */
public class CmnUtDate extends java.util.Date {
	private static final long serialVersionUID = 1L;
	
	private static String strFormat1 = "yyyy-MM-dd HH:mm";
	private static String strFormat2 = "yyyy-MM-dd";
	private static String strFormat3 = "yyyy/MM/dd";
	private static String strFormat4 = "yyyy/MM/dd HH:mm";
	private static String strFormat5 = "HH:mm";
	private static String strFormat6 = "h:mm a";
	private static String strFormat7 = "yyyyMMddHHmmss";
	private static String strFormat8 = "yyyy-MM-dd HH:mm:ss";
	private static String strFormat9 = "ddMMMyy";
	private static String strFormat10 = "yyyyMMdd";
	private static String strFormat15 = "yyMM";
	private static String strFormat17 = "MM/dd/yyyy";
	private static String strFormat18 = "dd/MM/yyyy";
	private static String strFormat19 = "yy-MM-dd";
	
	/**
	 * 构造函数
	 */
	public CmnUtDate() {
		super(getSystemDate().getTime().getTime());
	}

	/**
	 * 当前时间
	 * 
	 * @return 时间Timestamp
	 */
	public java.sql.Timestamp parseTime() {
		return new java.sql.Timestamp(this.getTime());
	}

	/**
	 * 当前时间
	 * 
	 * @return 时间Date
	 */
	public java.sql.Date parseDate() {
		return new java.sql.Date(this.getTime());
	}

	/**
	 * 取当前系统时间
	 * 
	 * @return 时间Calendar
	 */
	public static Calendar getSystemDate() {
		return Calendar.getInstance();
	}

	/*
	 * 取当前系统时间 @return 时间Calendar 
	 */ 
	/* public static Calendar getSystemDate() { ResultSet rs = null; Connection conn = null; Calendar cal = null; try { DataSource ds = (DataSource) IoC.get("DataSource");
	 * conn = ds.getConnection(); MyQueryRunner runner = new MyQueryRunner(); rs = runner.rsQuery(conn, SYSTEM_DATE_SQL, null); String sDate = ""; String sTime = ""; if (rs.next()) { String ss =
	 * rs.getString(1); sDate = ss.substring(0, 8); sTime = ss.substring(9); } if (!sDate.equals("") && !sTime.equals("")) { String sYear = sDate.substring(0, 4); String sMonth = sDate.substring(4,
	 * 6); String sDay = sDate.substring(6, 8); String sHH = sTime.substring(0, 2); String sMI = sTime.substring(3, 5); String sSS = sTime.substring(6, 8); cal = new
	 * GregorianCalendar(Integer.parseInt(sYear), Integer.parseInt(sMonth) - 1, Integer.parseInt(sDay), Integer.parseInt(sHH), Integer.parseInt(sMI), Integer.parseInt(sSS)); } else { cal =
	 * Calendar.getInstance(); } } catch (SQLException ex) { ex.printStackTrace(); } finally { try { if (rs != null) { rs.close(); rs = null; } if (conn != null) { conn.close(); conn = null; } } catch
	 * (Exception ee) { } } return cal; }
	 */

	/**
	 * 取得指定日期几天后的日期
	 * 
	 * @param date 日期
	 * @param afterDays 天数
	 * @return 日期
	 */
	public static java.util.Date getAfterDay(java.util.Date date, int afterDays) {
		GregorianCalendar cal = new GregorianCalendar();
		if (date == null) {
			cal.setTime(new CmnUtDate());
		} else {
			cal.setTime(date);
		}
		cal.add(java.util.Calendar.DATE, afterDays);
		return cal.getTime();
	}

	/**
	 * 获得几个月后的日期 addby(yangwr) 2005/08/30
	 * 
	 * @param sDate 日期
	 * @param afterMonth 月数
	 * @return 日期"yyyy-MM-dd HH:mm"
	 */
	public static String getAfterMonth(String sDate, int afterMonth) {
		SimpleDateFormat sFormat1 = new SimpleDateFormat(strFormat1);
		
		java.util.Date date = null;
		try {
			date = sFormat1.parse(sDate);
			date = getAfterMonth(date, afterMonth);
			return sFormat1.format(date);
		} catch (ParseException e) {

			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获得几个月后的日期
	 * 
	 * @param date 日期
	 * @param afterMonth 月数
	 * @return 日期Date
	 */
	public static java.util.Date getAfterMonth(java.util.Date date, int afterMonth) {
		GregorianCalendar cal = new GregorianCalendar();
		if (date == null) {
			cal.setTime(new CmnUtDate());
		} else {
			cal.setTime(date);
		}
		cal.add(java.util.Calendar.MONTH, afterMonth);
		return cal.getTime();
	}

	/**
	 * 取得指定日期几天后的日期
	 * 
	 * @param sDate 日期
	 * @param afterDays 天数
	 * @return 日期
	 */
	public static String getAfterDay(String sDate, int afterDays) {
		java.util.Date date = null;
		try {
			date = convertDate(sDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		date = getAfterDay(date, afterDays);
		return formatDate(date);
	}

	/**
	 * 日期格式化
	 * 
	 * @param date 日期
	 * @return 日期"yyyy-MM-dd"
	 */
	public static String formatDate(java.util.Date date) {
		SimpleDateFormat sFormat2 = new SimpleDateFormat(strFormat2);
		if (date == null) {
			return "";
		}
		return sFormat2.format(date);
	}

	/**
	 * 转换类型
	 * 
	 * @param sDate 日期"yyyy-MM-dd"
	 * @return 日期Date
	 */
	public static java.util.Date convertDate(String sDate) {
		SimpleDateFormat sFormat2 = new SimpleDateFormat(strFormat2);
		try {
			return sFormat2.parse(sDate);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 转换类型
	 * 
	 * @param sDate 日期"yyyy-MM-dd HH:mm:ss"
	 * @return 日期Date
	 */
	public static java.util.Date convertDate8(String sDate) {
		SimpleDateFormat sFormat8 = new SimpleDateFormat(strFormat8);
		try {
			return sFormat8.parse(sDate);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 转换类型
	 * 
	 * @param sDate 日期"yyyy-MM-dd HH:mm"
	 * @return 日期Date
	 * @throws Exception
	 */
	public static java.util.Date convertDate1(String sDate) throws Exception {
		SimpleDateFormat sFormat1 = new SimpleDateFormat(strFormat1);
		return sFormat1.parse(sDate);
	}

	/**
	 * 转换类型
	 * 
	 * @param sDate 日期"yyyy-MM-dd HH:mm"
	 * @return 日期Timestamp
	 */
	public static Timestamp convertTimestamp1(String sDate) {
		long lDate = 0;

		try {
			lDate = convertDate1(sDate).getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Timestamp(lDate);

	}

	/**
	 * 转换类型
	 * 
	 * @param sDate 日期"yyyy-MM-dd"
	 * @return 日期Timestamp
	 */
	public static Timestamp convertTimestamp2(String sDate) {

		long lDate = 0;

		try {
			lDate = convertDate(sDate).getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Timestamp(lDate);

	}

	// 2005-05-05 00:00:00
	/**
	 * 转换类型
	 * 
	 * @param sDate 日期
	 * @return 日期Timestamp
	 */
	public static Timestamp convertTimestamp(String sDate) {
		/*
		 * long lDate = 0; if(sDate.length() == 10){ try { lDate = convertDate(sDate).getTime(); } catch (Exception e) { e.printStackTrace(); } } return new Timestamp(lDate);
		 */
		if (sDate.length() == 10) {
			sDate = sDate + " 00:00:00";
		}
		if (sDate.length() == 16) {
			sDate = sDate + ":00";
		}
		return Timestamp.valueOf(sDate);
	}

	// 2005-05-05 23:59:59.0
	/**
	 * 转换类型
	 * 
	 * @param sDate 日期
	 * @return 日期Timestamp
	 */
	public static Timestamp convertTimestampE(String sDate) {

		if (sDate.length() == 10) {
			sDate = sDate + " 23:59:59.0";
		}

		return Timestamp.valueOf(sDate);
	}

	/**
	 * 取得时间差
	 * 
	 * @param date1 日期1
	 * @param date2 日期2
	 * @return 日期2-日期1的毫秒时间差
	 */
	public static long getDateDifference(java.util.Date date1, java.util.Date date2) {
		Calendar cld1Work = Calendar.getInstance();
		Calendar cld2Work = Calendar.getInstance();
		Calendar cld1 = Calendar.getInstance();
		Calendar cld2 = Calendar.getInstance();
		long lTime1;
		long lTime2;

		cld1Work.setTime(date1);
		cld2Work.setTime(date2);
		cld1.clear();
		cld2.clear();
		cld1.set(cld1Work.get(Calendar.YEAR), cld1Work.get(Calendar.MONTH), cld1Work.get(Calendar.DATE));
		cld2.set(cld2Work.get(Calendar.YEAR), cld2Work.get(Calendar.MONTH), cld2Work.get(Calendar.DATE));
		lTime1 = (cld1.getTime()).getTime();
		lTime2 = (cld2.getTime()).getTime();

		return (lTime2 - lTime1) / (1000 * 60 * 60 * 24);
	}
	public static String getDateDifferenceToStr(String start_date,String end_date){
		String ret = "";
	    DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
	    
	    try{
	        Date first = format.parse(start_date);
	        Date second = format.parse(end_date);
	        ret = String.valueOf(getDateDifference(first,second)+1);
	        
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	    
	    return ret;
		
	}
	
	/**
	 * 日期类型转化
	 * 
	 * @param sTsp 日期串
	 * @param iType 0:yyyy年M月d日； <br>
	 *            1:yyyy-M-d; <br>
	 *            2:yyyy/M/d <br>
	 *            3:yyyy年MM月dd日; <br>
	 *            4:yyyy-MM-dd; <br>
	 *            5:yyyy/MM/dd; <br>
	 *            6:yyyy-MM-dd HH:mm; <br>
	 *            7:yyyy/MM/dd HH:mm; <br>
	 *            8:HH:mm <br>
	 *            9:h:mm a <br>
	 *            10:yyyyMMdd <br>
	 *            11:yyyyMMddHHmmSS<br>
	 *            12:yyyy-MM-dd HH:mm:ss <br>
	 *            13:HH:mm:ss <br>
	 *            14:ddMMMyy <br>
	 *            15:yyMM
	 *            17:MM/dd/yyyy
	 *            18:dd/MM/yyyy
	 *            19:yy-MM-dd
	 * @return 日期串
	 */
	public static String formatDate(String sTsp, int iType) {

		if (sTsp == null || "".equals(sTsp)) {
			return "";
		}

		if (sTsp.length() == 10) {
			return formatDate(convertTimestamp(sTsp), iType);
		} else if (sTsp.length() > 10) {
			String[] sDatas = sTsp.split("\\.");
			if (sDatas.length > 2) {
				if (sDatas.length > 1) {
					String[] sDates = sDatas[0].split("-");
					if (sDates[1].length() == 1)
						sDates[1] = "0" + sDates[1];
					if (sDates[2].length() == 1)
						sDates[2] = "0" + sDates[2];

					for (int i = 1; i < 4; i++) {
						sDatas[i] = sDatas[i].trim();
						if (sDatas[i].length() == 1)
							sDatas[i] = "0" + sDatas[i];
					}

					sTsp = sDates[0] + "-" + sDates[1] + "-" + sDates[2] + " " + sDatas[1] + ":" + sDatas[2] + ":" + sDatas[3] + ".000000000";
				}
			}

			return formatDate(Timestamp.valueOf(sTsp), iType);
		} else {
			return "";
		}
	}

	/**
	 * 日期类型转化
	 * 
	 * @param tsp 日期
	 * @param iType 0:yyyy年M月d日； <br>
	 *            1:yyyy-M-d; <br>
	 *            2:yyyy/M/d <br>
	 *            3:yyyy年MM月dd日; <br>
	 *            4:yyyy-MM-dd; <br>
	 *            5:yyyy/MM/dd; <br>
	 *            6:yyyy-MM-dd HH:mm; <br>
	 *            7:yyyy/MM/dd HH:mm; <br>
	 *            8:HH:mm <br>
	 *            9:h:mm a <br>
	 *            10:yyyyMMdd <br>
	 *            11:yyyyMMddHHmmSS<br>
	 *            12:yyyy-MM-dd HH:mm:ss <br>
	 *            13:HH:mm:ss <br>
	 *            14:ddMMMyy <br>
	 *            15:yyMM <br>
	 *            16:上一个月日期yyyyMMdd
	 *            17:MM/dd/yyyy
	 *            18:dd/MM/yyyy
	 *            19:yy-MM-dd;
	 * @return 日期串
	 */
	public static String formatDate(Timestamp tsp, int iType) {
		
		SimpleDateFormat sFormat1  = new SimpleDateFormat(strFormat1 );
		SimpleDateFormat sFormat2  = new SimpleDateFormat(strFormat2 );
		SimpleDateFormat sFormat3  = new SimpleDateFormat(strFormat3 );
		SimpleDateFormat sFormat4  = new SimpleDateFormat(strFormat4 );
		SimpleDateFormat sFormat5  = new SimpleDateFormat(strFormat5 );
		SimpleDateFormat sFormat6  = new SimpleDateFormat(strFormat6 );
		SimpleDateFormat sFormat7  = new SimpleDateFormat(strFormat7 );
		SimpleDateFormat sFormat8  = new SimpleDateFormat(strFormat8 );
		SimpleDateFormat sFormat9  = new SimpleDateFormat(strFormat9 );
		SimpleDateFormat sFormat10 = new SimpleDateFormat(strFormat10);
		SimpleDateFormat sFormat15 = new SimpleDateFormat(strFormat15);
		SimpleDateFormat sFormat17 = new SimpleDateFormat(strFormat17);
		SimpleDateFormat sFormat18 = new SimpleDateFormat(strFormat18);
		SimpleDateFormat sFormat19 = new SimpleDateFormat(strFormat19);

		GregorianCalendar cal = new GregorianCalendar();
		// java.util.Date dDate = null;

		if (tsp == null) {
			cal.setTime(new CmnUtDate());
		} else {
			cal.setTime(tsp);
		}

		String sDate = "";
		// 0:yyyy年M月d日

		if (iType == 0) {
			int iYear = cal.get(Calendar.YEAR);
			int iMonth = cal.get(Calendar.MONTH) + 1;
			int iDay = cal.get(Calendar.DAY_OF_MONTH);

			sDate = "" + iYear + "年" + iMonth + "月" + iDay + "日";
		}
		// 1:yyyy-M-d
		if (iType == 1) {
			int iYear = cal.get(Calendar.YEAR);
			int iMonth = cal.get(Calendar.MONTH) + 1;
			int iDay = cal.get(Calendar.DAY_OF_MONTH);

			sDate = "" + iYear + "-" + iMonth + "-" + iDay;
		}
		// 2:yyyy/M/d
		if (iType == 2) {
			int iYear = cal.get(Calendar.YEAR);
			int iMonth = cal.get(Calendar.MONTH) + 1;
			int iDay = cal.get(Calendar.DAY_OF_MONTH);

			sDate = "" + iYear + "/" + iMonth + "/" + iDay;
		}
		// 3:yyyy年MM月dd日;
		if (iType == 3) {
			String strYear = String.valueOf(cal.get(Calendar.YEAR));
			String strMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
			strMonth = "00" + strMonth;
			strMonth = strMonth.substring(strMonth.length() - 2, strMonth.length());
			String strDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
			strDay = "00" + strDay;
			strDay = strDay.substring(strDay.length() - 2, strDay.length());

			sDate = "" + strYear + "年" + strMonth + "月" + strDay + "日";
		}
		// 4:yyyy-MM-dd;
		if (iType == 4) {
			sDate = sFormat2.format(cal.getTime());
		}
		// 5:yyyy/MM/dd;
		if (iType == 5) {
			sDate = sFormat3.format(cal.getTime());
		}
		// 6:yyyy-MM-dd HH:mm;
		if (iType == 6) {
			sDate = sFormat1.format(cal.getTime());
		}
		// 7:yyyy/MM/dd HH:mm;
		if (iType == 7) {
			sDate = sFormat4.format(cal.getTime());
		}
		// 8:HH:mm
		if (iType == 8) {
			sDate = sFormat5.format(cal.getTime());
		}
		// 9:h:mm a
		if (iType == 9) {
			sDate = sFormat6.format(cal.getTime());
		}
		// 10:h:mm a
		if (iType == 10) {
			sDate = sFormat10.format(cal.getTime());
		}
		if (iType == 11) {
			sDate = sFormat7.format(cal.getTime());
		}
		// 12:yyyy-MM-dd HH:mm:ss;
		if (iType == 12) {
			sDate = sFormat8.format(cal.getTime());
		}
		// 13:HH:mm:ss;
		if (iType == 13) {
			sDate = sFormat8.format(cal.getTime());
			sDate = sDate.substring(10);
		}
		// 14:ddMMMyy
		if (iType == 14) {
			sDate = sFormat9.format(cal.getTime());
		}
		// 15:yyMM
		if (iType == 15) {
			sDate = sFormat15.format(cal.getTime());
		}
		if (iType == 16) {
			int iYear = cal.get(Calendar.YEAR);
			int iMonth = cal.get(Calendar.MONTH) + 1;
			int iDay = cal.get(Calendar.DAY_OF_MONTH);
			switch (iMonth) {
			case 1: {
				cal.set(iYear, Calendar.JANUARY, iDay);
				break;
			}
			case 2: {
				cal.set(iYear, Calendar.FEBRUARY, iDay);
				break;
			}
			case 3: {
				cal.set(iYear, Calendar.MARCH, iDay);
				break;
			}
			case 4: {
				cal.set(iYear, Calendar.APRIL, iDay);
				break;
			}
			case 5: {
				cal.set(iYear, Calendar.MAY, iDay);
				break;
			}
			case 6: {
				cal.set(iYear, Calendar.JUNE, iDay);
				break;
			}
			case 7: {
				cal.set(iYear, Calendar.JULY, iDay);
				break;
			}
			case 8: {
				cal.set(iYear, Calendar.AUGUST, iDay);
				break;
			}
			case 9: {
				cal.set(iYear, Calendar.SEPTEMBER, iDay);
				break;
			}
			case 10: {
				cal.set(iYear, Calendar.OCTOBER, iDay);
				break;
			}
			case 11: {
				cal.set(iYear, Calendar.NOVEMBER, iDay);
				break;
			}
			case 12: {
				cal.set(iYear, Calendar.DECEMBER, iDay);
				break;
			}
			}
			cal.add(Calendar.MONTH, -1);
			sDate = sFormat2.format(cal.getTime());

		}
		// 17:MM/dd/yyyy
		if (iType == 17) {
			sDate = sFormat17.format(cal.getTime());
		}
		
		// 18:dd/MM/yyyy
		if (iType == 18) {
			sDate = sFormat18.format(cal.getTime());
		}
		// 19:yy-MM-dd;
		if (iType == 19) {
			sDate = sFormat19.format(cal.getTime());
		}
		return sDate;
	}

	/**
	 * 取得n分钟前的时间
	 * 
	 * @param lminute
	 * @return 时间Timestamp
	 */
	public static Timestamp gettimebefore(long lminute) {

		Timestamp tsp = new CmnUtDate().parseTime();

		long lngTime = tsp.getTime() - lminute * 60 * 1000;

		return new Timestamp(lngTime);

	}

	/**
	 * 取得n分钟前的时间
	 * 
	 * @param date 日期
	 * @param lminute
	 * @return 时间Timestamp
	 */
	public static Timestamp gettimebefore(java.util.Date date, long lminute) {

		long lngTime = date.getTime() - lminute * 60 * 1000;

		return new Timestamp(lngTime);

	}

	/**
	 * 获取国内机票航程时间 <br>
	 * 如果结束时间小于开始时间，按照第二天算
	 * 
	 * @param start 四位数字字符串
	 * @param end 四位数字字符串
	 * @return 例1小时30分钟
	 */
	public static String getFlyingTime(String start, String end) {
		int iSt = Integer.valueOf(start).intValue();
		int iEn = Integer.valueOf(end).intValue();
		String rtn = null;
		// 是否第二天
		if (iEn <= iSt) {
			iEn += 2400;
		}
		// 小时
		int hour = iEn / 100 - 1 - iSt / 100;
		// 分钟
		int mini = iEn % 100 + 60 - iSt % 100;

		if (mini >= 60) {
			mini -= 60;
			hour += 1;
		}
		if (mini == 0) {
			rtn = hour + "小时";
		} else if (hour == 0) {
			rtn = mini + "分钟";
		} else {
			rtn = hour + "小时" + mini + "分钟";
		}
		return rtn;
	}

	/**
	 * 获取国内机票航程时间(外文版) <br>
	 * 如果结束时间小于开始时间，按照第二天算
	 * 
	 * @param start 四位数字字符串
	 * @param end 四位数字字符串
	 * @return 例1小时30分钟
	 */
	public static String getMultiLangFlyingTime(String start, String end) {
		int iSt = Integer.valueOf(start).intValue();
		int iEn = Integer.valueOf(end).intValue();
		String rtn = null;
		// 是否第二天
		if (iEn <= iSt) {
			iEn += 2400;
		}
		// 小时
		int hour = iEn / 100 - 1 - iSt / 100;
		// 分钟
		int mini = iEn % 100 + 60 - iSt % 100;

		if (mini >= 60) {
			mini -= 60;
			hour += 1;
		}
		if (mini == 0) {
			rtn = hour + "hr ";
		} else if (hour == 0) {
			rtn = mini + "mn";
		} else {
			rtn = hour + "hr " + mini + "mn";
		}

		return rtn;
	}

	/**
	 * 获取国内机票航程时间 <br>
	 * 如果结束时间小于开始时间，按照第二天算
	 * 
	 * @param start 四位数字字符串
	 * @param end 四位数字字符串
	 * @return 飞行时间（分钟）
	 */
	public static int getFlyingMunites(String start, String end) {
		int iSt = Integer.valueOf(start).intValue();
		int iEn = Integer.valueOf(end).intValue();
		int rtn = 0;
		// 是否第二天
		if (iEn <= iSt) {
			iEn += 2400;
		}
		// 小时
		int hour = iEn / 100 - 1 - iSt / 100;
		// 分钟
		int mini = iEn % 100 + 60 - iSt % 100;

		rtn = hour * 60 + mini;

		return rtn;
	}

	/**
	 * 将java.sql.timestamp，<br>
	 * 转换为用于sql的字符串(to_timestamp('2007-04-18 00:00:00.0' , 'yyyy-mm-dd hh24:mi:ssxff'))
	 * 
	 * @param timestamp 待转换的timestamp
	 * @return 例如to_timestamp('2007-04-18 00:00:00.0' , 'yyyy-mm-dd hh24:mi:ssxff')
	 */
	public static String toTimeStamp(Timestamp timestamp) {
		String sql = "to_timestamp('" + timestamp.toString() + "' , 'yyyy-mm-dd hh24:mi:ssxff')";
		return sql;
	}

	/**
	 * 将格式如2007-04-18 00:00:00.0的字符串，<br>
	 * 转换为用于sql的字符串(to_timestamp('2007-04-18 00:00:00.0' , 'yyyy-mm-dd hh24:mi:ssxff'))
	 * 
	 * @param s 待转换的字符串
	 * @return 例如to_timestamp('2007-04-18 00:00:00.0' , 'yyyy-mm-dd hh24:mi:ssxff')
	 */
	public static String toTimeStamp(String s) {
		String sql = "to_timestamp('" + s + "' , 'yyyy-mm-dd hh24:mi:ssxff')";
		return sql;
	}

	/**
	 * 计算 给定日期的当月最后一天并显示
	 * 
	 * @param s 日期
	 * @return 最后一天
	 */
	public static String LastDay(String s) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sFormat.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		GregorianCalendar gc = new GregorianCalendar();
		if (date == null)
			gc.setTime(new CmnUtDate());
		else
			gc.setTime(date);

		gc.add(Calendar.MONTH, 1);
		gc.add(Calendar.DATE, -date.getDate());
		DateFormat df = DateFormat.getDateInstance();
		Date dateTemp = gc.getTime();
		return df.format(dateTemp);
	}
	
	
	/**
	 * 计算 给定日期的当月最后一天并显示
	 * @param s 日期
	 * @return 最后一天
	 */
	public static String getMonthLastDay(String s) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sFormat.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		GregorianCalendar gc = new GregorianCalendar();
		if (date == null)
			gc.setTime(new CmnUtDate());
		else
			gc.setTime(date);
		
		gc.add(Calendar.MONTH, 1);
		gc.add(Calendar.DATE, -date.getDate());
		
//		DateFormat df = DateFormat.getDateInstance();
		Date dateTemp = gc.getTime();		
		return sFormat.format(dateTemp);
	}

	
//	//add by licheng begin
//
//	/**
//	 * 日期类型转化
//	 * @param String sTsp
//	 * 			d MMM yyyy
//	 * @return
//	 */
//	public static String formatWeek(String weekDay,String lanid) {
//		
//		if (weekDay == null || "".equals(weekDay)) {
//			return "";
//		}
//		
//		if (weekDay.equals("1")) {
//			return formatDate(convertTimestamp("2007-01-15"), lanid, 2).toUpperCase();
//		}else if (weekDay.equals("2")) {
//			return formatDate(convertTimestamp("2007-01-16"), lanid, 2).toUpperCase();
//		}else if (weekDay.equals("3")) {
//			return formatDate(convertTimestamp("2007-01-17"), lanid, 2).toUpperCase();
//		}else if (weekDay.equals("4")) {
//			return formatDate(convertTimestamp("2007-01-18"), lanid, 2).toUpperCase();
//		}else if (weekDay.equals("5")) {
//			return formatDate(convertTimestamp("2007-01-19"), lanid, 2).toUpperCase();
//		}else if (weekDay.equals("6")) {
//			return formatDate(convertTimestamp("2007-01-20"), lanid, 2).toUpperCase();
//		}else if (weekDay.equals("7")) {
//			return formatDate(convertTimestamp("2007-01-21"), lanid, 2).toUpperCase();
//		}else {
//		}
//			return "";
//		}
	
//	
//	/**
//	 * 日期类型转化
//	 * @param String sTsp
//	 * 			d MMM yyyy
//	 * @return
//	 */
//	public static String formatDate(String sTsp,String lanid,int iType) {
//		
//		if (sTsp == null || "".equals(sTsp)) {
//			return "";
//		}
//		
//		if (sTsp.length() == 10) {
//			return formatDate(convertTimestamp(sTsp), lanid, iType);
//		} else if (sTsp.length() > 10) {			
//			return formatDate(Timestamp.valueOf(sTsp), lanid, iType);
//		} else {
//			return "";
//		}
//	}
	

	//add by licheng end
	
	/**
	 * 日期类型转化
	 * @param birthDay yyyy-mm-dd
	 * @return
	 */
	public static String getAge(String sBirthDay){
		if(sBirthDay==null || sBirthDay.equals("")){
			return "";
		}
		
		int age=0;
		//今天
		Date currentDate = (new CmnUtDate()).parseDate();
				
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		
		Date birthDay = CmnUtDate.convertDate(sBirthDay);
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                "The birthDay is before Now.It's unbelievable!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        
        //生日
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                //monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                } else {
                    //do nothing
                }
            } else {
                //monthNow>monthBirth
                age--;
            }
        } else {
            //monthNow<monthBirth
            //donothing
        }
        if(age==0){
        	return "婴儿";
        }
		return String.valueOf(age);
		
	}
	/**
	 * 取当前日期(yyyy-mm-dd)
	 * 
	 * @return 时间Timestamp
	 */
	public static String getTodayDate() {
		return formatDate(new CmnUtDate().parseTime(),4);
	}

	/**
	 * 取当前日期(yyyy-mm-dd) 星期几
	 * 
	 * @return 
	 */
	public static String getWeekDayName(String dayTime) {

		 String dayNames[] = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};		
		 Date d=convertDate(dayTime);
		 Calendar cal = Calendar.getInstance(); 
		 cal.setTime(d); 
		 int day= cal.get(Calendar.DAY_OF_WEEK); 
		 String week_day_name = dayNames[day - 1];
		 return week_day_name;
	}
	
	/**
	 * 格式化日期(yy-mm-dd)
	 * 
	 * @return 
	 */
	public static String formatToYY_mm_dd(Date date){
		 SimpleDateFormat YY_mm_dd = new SimpleDateFormat("yy-MM-dd"); 
		 String str = YY_mm_dd.format(date);
		 return str;
	}
	
	 /**   
     * 功能：得到当前月份月初 格式为：xxxx-yy-zz (eg: 2010-10-01)   
     * @return String   
     * @author pure   
     */   
   public static String thisMonth() {    
	  Calendar localTime;  
	  int x;
	  int y;
	  localTime = Calendar.getInstance();    
       String strY = null;    
       x = localTime.get(Calendar.YEAR);    
       y = localTime.get(Calendar.MONTH) + 1;    
       strY = y >= 10 ? String.valueOf(y) : ("0" + y);return x + "-" + strY + "-01";    
   }    


/*	public static void main(String[] args){
		System.out.println(formatDate("2009-05-01 20:30:00","EN",4));
		System.out.println(formatDate("2009-05-01 20:30:00","DE",4));
		System.out.println(formatDate("2009-05-01 20:30:00","ES",4));
		System.out.println(formatDate("2009-05-01 20:30:00","FR",4));
		System.out.println(formatDate("2009-05-01 20:30:00","IT",4));
		System.out.println(formatDate("2009-05-01 20:30:00","JP",4));
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY,Integer.parseInt("20:30".substring(0,2)));
		cal.set(Calendar.MINUTE,Integer.parseInt("20:30".substring(3)));
		 
		String departDatetime = CmnUtDate.formatDate(new Timestamp(cal.getTime().getTime()),"EN",4);
		System.out.println(departDatetime);
	}*/
}
