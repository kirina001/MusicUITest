package com.music.Tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateForMusic {
	public static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
	public static Calendar c = Calendar.getInstance();

	public static String currentDay() {
		String date = sf.format(new Date());
		System.out.println("当前日期：" + date);// new Date()为获取当前系统时间
		return date;
	}

	/**
	 * 过去七天
	 * 
	 * @return
	 */
	public static String lastSevenDays() {

//		System.out.println("当前日期:" + sf.format(c.getTime()));
		c.add(Calendar.DATE, -7);
		Date d = c.getTime();
		String day = sf.format(d);
		System.out.println("过去七天：" + day);
		return day;
	}

	/**
	 * 过去一个月
	 * 
	 * @return
	 */
	public static String lastMonth() {
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date m = c.getTime();
		String mon = sf.format(m);
		System.out.println("过去一个月：" + mon);
		return mon;
	}

	/**
	 * 过去三个月
	 * 
	 * @return
	 */
	public static String pastThreeMonths() {
		c.setTime(new Date());
		c.add(Calendar.MONTH, -3);
		Date m3 = c.getTime();
		String mon3 = sf.format(m3);
		System.out.println("过去三个月：" + mon3);
		return mon3;
	}

	/**
	 * 过去一年
	 * 
	 * @return
	 */
	public static String lastYear() {
		c.setTime(new Date());
		c.add(Calendar.YEAR, -1);
		Date y = c.getTime();
		String year = sf.format(y);
		System.out.println("过去一年：" + year);
		return year;
	}

	/**
	 * 未来一年
	 * 
	 * @return
	 */
	public static String nextYear() {
		c.setTime(new Date());
		c.add(Calendar.YEAR, +1);
		Date y = c.getTime();
		String year = sf.format(y);
		System.out.println("未来一年：" + year);
		return year;
	}

}
