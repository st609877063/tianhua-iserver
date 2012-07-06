package bnu.util;

public class Util {
	/**
	 * ������ת��Ϊ����ϵͳ�е����ڸ�ʽ
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
	 * ��yyyy-mm-dd���ڸ�ʽת��Ϊyyyy/mm/dd��ʽ
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

