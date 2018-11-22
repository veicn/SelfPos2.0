package com.acewill.slefpos.orderui.main.market;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Author：Anch
 * Date：2017/6/27 15:32
 * Desc：
 */
public class MarketDateUtil {
	private static final String datePatten = "yyyy-MM-dd HH:mm:ss";
	private static final String dayPattern = "yyyy-MM-dd";
	private static final String simpleDatePatten = "yyyyMMddHHmmss";
	private static final String HourAndMinutePatten = "yyyy-MM-dd HH:mm";

	private static final String HourMinuteSecond = "HH:mm:ss";

	private static final String DATE_PATTEN_HM = "yyyy-MM-dd HH:mm";

	//hourAndMinute 格式为 04:20, 15:33
	public static long getTimeStampFromHourAndMinute(String hourAndMinute) throws ParseException {
		long cururentTime = System.currentTimeMillis();
		String dayString = getDayStr(cururentTime);

		SimpleDateFormat format = new SimpleDateFormat(HourAndMinutePatten);
		return format.parse(dayString + " " + hourAndMinute).getTime();
	}

	public static long getTimeStamp_HM(String date)
	{
		if (date == null || "".equals(date))
			return -1;
		try
		{
			SimpleDateFormat format = new SimpleDateFormat(DATE_PATTEN_HM);
			return format.parse(date).getTime();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}

	public static String getTimeStr_HM(long timeStamp)
	{
		try
		{
			SimpleDateFormat format = new SimpleDateFormat(DATE_PATTEN_HM);
			return format.format(new Date(timeStamp));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static long getTimeStamp(String date)
	{
		if (date == null || "".equals(date))
			return -1;
		try
		{
			SimpleDateFormat format = new SimpleDateFormat(datePatten);
			return format.parse(date).getTime();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}

	public static String getTimeStr(long timeStamp)
	{
		try
		{
			SimpleDateFormat format = new SimpleDateFormat(datePatten);
			return format.format(new Date(timeStamp));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static String getTimeStr(){
		try
		{
			SimpleDateFormat format = new SimpleDateFormat(datePatten);
			return format.format(new Date());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static String getTimeDayStr(){
		try
		{
			SimpleDateFormat format = new SimpleDateFormat(dayPattern);
			return format.format(new Date());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static String getDateStr(long timeStamp)
	{
		try
		{
			SimpleDateFormat format = new SimpleDateFormat(simpleDatePatten);
			return format.format(new Date(timeStamp));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static String getHourMinuteSecondStr(long timeStamp)
	{
		try
		{
			SimpleDateFormat format = new SimpleDateFormat(HourMinuteSecond);
			return format.format(new Date(timeStamp));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static String getDayStr(long timeStamp)
	{
		try
		{
			SimpleDateFormat format = new SimpleDateFormat(dayPattern);
			return format.format(new Date(timeStamp));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}


	public static long getNextMonthTimeStamp(long currentTimeStamp)
	{
		Calendar c= Calendar.getInstance();
		Date date = new Date(currentTimeStamp);
		c.setTime(date);
		c.add(Calendar.MONTH,1); //将当前日期加一个月
		return c.getTimeInMillis();
	}

	//获取小时分钟字符串
	public static String getHour() {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date date = new Date();
		return format.format(date);
	}

	//比较两个时间，d1>d2返回1，d1<d2返回-1，d1=d2返回0
	public static int compareData(String d1,String d2){
		int i = 0;
		try {
			DateFormat dateFormat = new SimpleDateFormat("HH:mm");
			Date       dateTime1  = dateFormat.parse(d1);
			Date       dateTime2  = dateFormat.parse(d2);
			i = dateTime1.compareTo(dateTime2);
			System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	//获取当天星期
	public static String getWeekOfDate() {
		Date dt = new Date();
		String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * @param dateStr 支持yyyy-MM-dd， yyyy-MM-dd hh yyyy-MM-dd hh:mm yyyy-MM-dd hh:mm:ss 等格式
	 * @return
	 */
	public static Date string2Date(String dateStr) throws ParseException {
		if ("yyyy-MM-dd".length() == dateStr.length()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(dateStr);
		} else if ("yyyy-MM-dd hh".length() == dateStr.length()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh");
			return sdf.parse(dateStr);
		} else if ("yyyy-MM-dd hh:mm".length() == dateStr.length()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			return sdf.parse(dateStr);
		} else if ("yyyy-MM-dd hh:mm:ss".length() == dateStr.length()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return sdf.parse(dateStr);
		}

		return null;
	}

	//比较两个时间，d1>d2返回1，d1<d2返回-1，d1=d2返回0
	public static int compareTime(String d1,String d2){
		int res=d1.compareTo(d2);
		if(res>0)
			return 1;
		else if(res==0)
			return 0;
		else
			return -1;
	}

	// 获取指定日期下,是星期几
	public static String getWeek(Date date){
		String[] weeks = {"周日","周一","周二","周三","周四","周五","周六"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if(week_index<0){
			week_index = 0;
		}
		return weeks[week_index];
	}

	public static void main(String[] args)
	{
		long timestamp = System.currentTimeMillis();
		System.out.println(timestamp);
		String s = getTimeStr(timestamp);
		System.out.println(s);
		System.out.println(getTimeStamp(s));
		long n = getNextMonthTimeStamp(timestamp);
		System.out.println(n);
		System.out.println(getTimeStr(n));

		String a = "123";
		String [] b = a.split(",");
		System.out.println(b);
	}
}
