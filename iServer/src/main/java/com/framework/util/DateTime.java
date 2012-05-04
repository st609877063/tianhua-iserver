package com.framework.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {
        private int year = 0; //年

        private int month = 0; //月

        private int day = 0; //日

        private int hour = 0; //小时

        private int minute = 0; //分钟

        private int second = 0; //秒

        @SuppressWarnings("unused")
		private java.util.Calendar calendar; //日历变量

        public static final String datetimeFormat = "yyyy-MM-dd HH:mm:ss"; //日期时间格式

        public DateTime() {
                Calendar now = Calendar.getInstance();
                setDatetime(now);
        }

        public DateTime(String s) throws NumberFormatException {
                this();
                try {
                        if (s.length() > 1) {
                                this.year = Integer.parseInt(s.substring(0, 4));
                                this.month = Integer.parseInt(s.substring(5, 7));
                                this.day = Integer.parseInt(s.substring(8, 10));
                        }
                        if (s.length() > 10) {
                                this.hour = Integer.parseInt(s.substring(11, 13));
                                this.minute = Integer.parseInt(s.substring(14, 16));
                                this.second = Integer.parseInt(s.substring(17, 19));
                        } else {
                                this.hour = 0;
                                this.minute = 0;
                                this.second = 0;
                        }
                } catch (Exception e) {

                }
                check();
        }

        public DateTime(Calendar cale) {
                setDatetime(cale);
        }

        private void setDatetime(Calendar calendar) {
                this.year = calendar.get(Calendar.YEAR);
                this.month = calendar.get(Calendar.MONTH) + 1;
                this.day = calendar.get(Calendar.DAY_OF_MONTH);
                this.hour = calendar.get(Calendar.HOUR_OF_DAY);
                this.minute = calendar.get(Calendar.MINUTE);
                this.second = calendar.get(Calendar.SECOND);
        }

        public DateTime(Date d) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(d);
                setDatetime(calendar);
        }

        public DateTime addDays(int days) {
                return manipulate(Calendar.DATE, days);

        }

        public DateTime addHours(int hours) {
                return manipulate(Calendar.HOUR_OF_DAY, hours);
        }

        public DateTime addMinutes(int minutes) {
                return manipulate(Calendar.MINUTE, minutes);
        }

        public DateTime addMonths(int months) {
                return manipulate(Calendar.MONTH, months);
        }

        public DateTime addYears(int years) {
                return manipulate(Calendar.YEAR, years);
        }

        private void check() {
                if (this.year < 1 || this.year > 2500)
                        System.out.println("year is wrong");
                if (this.month < 1 || this.month > 12)
                        System.out.println("month is wrong");
                int flag = 0;
                if ((this.year % 4) == 0) {
                        flag = 1;
                        if (((this.year % 100) == 0) && ((this.year % 400) != 0))
                                flag = 0;
                        else
                                flag = 1;
                }
                if (this.day < 1)
                        System.out.println("day is wrong");
                if (this.month == 2)
                        if (flag == 0 && this.day > 28)
                                System.out.println("day is wrong");
                        else if (flag == 1 && this.day > 29)
                                System.out.println("day is wrong");
                        else if (this.day > 28)
                                System.out.println("day is wrong");
                if (this.hour < 0 || this.hour > 23)
                        System.out.println("hour is wrong");
                if (this.minute < 0 || this.minute > 59)
                        System.out.println("minute is wrong");
                if (this.second < 0 || this.second > 59)
                        System.out.println("second is wrong");
        }

        private String convert(int i) {
                if (i < 10)
                        return "0" + String.valueOf(i);
                else
                        return String.valueOf(i);
        }

        public static Date getCurrentDateByDate() {
                Calendar calendar = Calendar.getInstance();
                return calendar.getTime();
        }

        public static String getCurrentDateByString() {
                String datetime = new Timestamp(System.currentTimeMillis()).toString();
                return datetime.substring(0, 10);
        }

        public static String getCurrentDatetimeByString() {
                String datetime = new Timestamp(System.currentTimeMillis()).toString();
                return datetime.substring(0, 19);
        }

        public String getDate() {
                StringBuffer sb = new StringBuffer();
                sb.append(convert(this.year));
                sb.append("-");
                sb.append(convert(this.month));
                sb.append("-");
                sb.append(convert(this.day));
                return sb.toString();
        }

        public int getDateInt() {

                StringBuffer sb = new StringBuffer();
                sb.append(convert(this.year));
                sb.append(convert(this.month));
                sb.append(convert(this.day));
                return Integer.valueOf(sb.toString()).intValue();
        }

        public long getDateLong() {
                StringBuffer sb = new StringBuffer();
                sb.append(convert(this.year));
                sb.append(convert(this.month));
                sb.append(convert(this.day));
                return Long.valueOf(sb.toString()).longValue();

        }

        public static Date getDateFromString(String s) {
                try {
                        SimpleDateFormat sdf = new SimpleDateFormat(datetimeFormat);
                        return sdf.parse(s);
                } catch (ParseException e) {
                        System.out.println("Date Parse Error:" + e.getMessage());
                        return null;
                }
        }

        public int getDay() {
                return this.day;
        }

        public int getDayOfWeek() {
                Calendar cale = this.toCalendar();
                return cale.get(Calendar.DAY_OF_WEEK);
        }

        public int getFirstDayOfWeek() {
                Calendar cale = this.toCalendar();
                return cale.getFirstDayOfWeek();
        }

        /**
         * 
         * @return
         */
        public int getHour() {
                return this.hour;
        }

        /**
         * 
         * @return
         */
        public int getMinute() {
                return this.minute;
        }

        /**
         * 
         * @return
         */
        public int getMonth() {
                return this.month;
        }

        /**
         * 
         * @return
         */
        public String getNoDeliCurrentDate() {
                StringBuffer sb = new StringBuffer();
                sb.append(convert(this.year));
                sb.append(convert(this.month));
                sb.append(convert(this.day));
                return sb.toString();
        }

        /**
         * 
         * @return
         */
        public int getSeasonOfYear() {
                if (this.month > 0 && this.month < 4)
                        return 1;
                if (this.month > 3 && this.month < 7)
                        return 2;
                if (this.month > 6 && this.month < 10)
                        return 3;
                if (this.month > 9 && this.month <= 12)
                        return 4;
                return 0;
        }

        /**
         * 
         * @return
         */
        public int getSecond() {
                return this.second;
        }

        /**
         * 
         * @param d
         * @return
         */
        public static String getStringFromDate(Date d) {
                SimpleDateFormat sdf = new SimpleDateFormat(datetimeFormat);
                return sdf.format(d);
        }

        public int getWeekOfMonth() {
                Calendar cale = this.toCalendar();
                return cale.get(Calendar.WEEK_OF_MONTH);
        }

        public int getWeekOfYear() {
                Calendar cale = this.toCalendar();
                return cale.get(Calendar.WEEK_OF_YEAR);
        }

        /**
         * 
         * @return
         */
        public int getYear() {
                return this.year;
        }

        private DateTime manipulate(int type, int interval) {
                check();
                Calendar cale = Calendar.getInstance();
                cale.set(this.year, this.month - 1, this.day, this.hour, this.minute);
                @SuppressWarnings("unused")
				DateTime dl = new DateTime(cale);
                cale.add(type, interval);
                @SuppressWarnings("unused")
				DateTime d2 = new DateTime(cale);
                return new DateTime(cale);
        }

        /**
         * 
         * @param i
         */
        public void setDay(int i) {
                this.day = i;
        }

        /**
         * 
         * @param i
         */
        public void setHour(int i) {
                this.hour = i;
        }

        /**
         * 
         * @param i
         */
        public void setMinute(int i) {
                this.minute = i;
        }

        /**
         * 
         * @param i
         */
        public void setMonth(int i) {
                this.month = i;
        }

        /**
         * 
         * @param i
         */
        public void setSecond(int i) {
                this.second = i;
        }

        /**
         * 
         * @param i
         */
        public void setYear(int i) {
                this.year = i;
        }

        public Calendar toCalendar() {
                check();
                Calendar cale = Calendar.getInstance();
                cale.set(this.year, this.month - 1, this.day, this.hour, this.minute);
                return cale;
        }

        public Date toDate() {
                String s = toString();
                return getDateFromString(s);
        }

        public String toString() {
                StringBuffer sb = new StringBuffer(19);
                check();
                sb.append(convert(this.year));
                sb.append("-");
                sb.append(convert(this.month));
                sb.append("-");
                sb.append(convert(this.day));
                sb.append(" ");
                sb.append(convert(this.hour));
                sb.append(":");
                sb.append(convert(this.minute));
                sb.append(":");
                sb.append(convert(this.second));
                return sb.toString();
        }

        public static void main(String[] args) {
                DateTime dt = new DateTime();
                System.out.println(dt.getYear());
                System.out.println(dt.getMonth());
                System.out.println(dt.getDate());
                System.out.println(dt.getDateInt());
                System.out.println(dt.getDateLong());
                System.out.println(dt.getDay());
                System.out.println(dt.getDayOfWeek());
                System.out.println(dt.getFirstDayOfWeek());
                System.out.println(dt.getHour());
                System.out.println(dt.getMinute());
                System.out.println(dt.getMonth());
                System.out.println(dt.getNoDeliCurrentDate());
                System.out.println(dt.getSeasonOfYear());
                System.out.println(dt.getSecond());
                System.out.println(dt.getWeekOfMonth());
                System.out.println(dt.getWeekOfYear());
                System.out.println(DateTime.getCurrentDateByDate());
                System.out.println(DateTime.getCurrentDateByString());
                System.out.println(DateTime.getCurrentDatetimeByString());

                System.out.println("********************");
                System.out.println(dt.addMinutes(30));
                System.out.println(dt);

        }
}
