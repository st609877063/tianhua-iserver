package bnu.util;

public class Util {
	/**
	 * 把日期转换为考勤系统中的日期格式
	 * 
	 */
	public static String formatDate(int year,int month,int date){
		String mm = month+"";
		String dd = date+"";
		if(month<10){
			mm = "0"+month;
		}
		if(date<10){
			dd = "0"+date;
		}
		return year+"/"+mm+"/"+dd;
	}
	/**
	 * 把yyyy-mm-dd日期格式转换为yyyy/mm/dd格式
	 * @param date
	 * @return
	 */
	public static String formatDate(String date){
		if(date!=null&&!"".equals(date)){
			date=date.replace("-", "/");
		}else{
			date="&nbsp";
		}
		return date;
	}
}

