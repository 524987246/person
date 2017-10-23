package org.great.util.myutil;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.great.util.myutil.MyStringUtils;
import org.junit.Test;

public class MyDateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static String dateToString(Date date) {
		return dateToString(date, DATE_PATTERN);
	}

	public static String dateToString(Date date, String pattern) {
		if (date == null) {
			date = new Date();
		}
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}

	/**
	 * String TO Date
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String dateStr, String pattern) throws ParseException {
		if (!MyStringUtils.isEmpty(pattern)) {
			pattern = DATE_PATTERN;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = sdf.parse(dateStr);
		return date;
	}

	/**
	 * 计算两个时间差
	 * 
	 * @param enddate
	 *            结束时间
	 * @param begindate
	 *            开始时间
	 * @param flag
	 *            1 精确到小时 2精确到天 3精确到分
	 * @return
	 * @throws ParseException
	 */
	public static long compareDate(String endStr, String beginStr, int flag) throws ParseException {
		Date begindate = stringToDate(beginStr, DATE_TIME_PATTERN);
		Date enddate = stringToDate(endStr, DATE_TIME_PATTERN);
		long date = compareDate(enddate, begindate, flag);
		return date;
	}

	/**
	 * 计算两个时间差
	 * 
	 * @param enddate
	 *            结束时间
	 * @param begindate
	 *            开始时间
	 * @param flag
	 *            1 精确到小时 2精确到天 3精确到分
	 * @return
	 */
	public static long compareDate(Date enddate, Date begindate, int flag) {
		if (begindate == null) {
			begindate = new Date();
		}
		long diff = enddate.getTime() - begindate.getTime();
		long days = 0;
		if (flag == 2) {// 天
			days = diff / (1000 * 60 * 60 * 24);
		} else if (flag == 1) {// 时
			days = diff / (1000 * 60 * 60);
		} else if (flag == 3) {// 分
			days = diff / (1000 * 60);
		}
		return days;
	}

	/**
	 * 得到num天后的日期
	 * 
	 * @param num
	 * @return
	 */
	public static String getDate(Date date, int num) {
		long time = 0;
		if (date == null) {
			date = new Date();
		}
		time = date.getTime();
		time = time + (1000L * 60 * 60 * 24 * num);
		String pattern = "yyyy-MM-dd";

		if (time > 0) {
			date.setTime(time);
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 设置基本查询,时间限制一个月
	 * 
	 * @param begintime
	 * @param endtime
	 * @param map
	 * @param time
	 *            间隔时间
	 * @return
	 */
	public static Map<String, Object> setQueryDate(String begintime, String endtime, int time,
			Map<String, Object> map) {
		Boolean bo1 = (begintime == null || begintime.trim().equals(""));
		Boolean bo2 = (endtime == null || endtime.trim().equals(""));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		if (bo1 && bo2) {
			endtime = dateToString(new Date(), "yyyy-MM-dd");
			begintime = getDate(null, -time);
		} else if (bo1 & !bo2) {
			// 结束时间不为空
			try {
				date = sdf.parse(endtime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			begintime = getDate(date, -time);
		} else if (!bo1 & bo2) {
			try {
				date = sdf.parse(begintime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			endtime = getDate(date, time);
		}
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		map.put("begintime", begintime);
		map.put("endtime", endtime);
		return map;
	}

	/**
	 * 获取总共请假时长
	 * 
	 * @param begindate
	 * @param enddate
	 * @throws ParseException
	 */
	public static long getworkhours(String begindate, String enddate) throws ParseException {
		long days = 0;
		days += (480 - getworkhours(begindate));
		days += getworkhours(enddate);
		days += (compareDate(enddate, begindate, 2) - 1) * 8 * 60;
		return days;
	}

	public static double getworkhours(Date date) throws ParseException {
		String dateStr = dateToString(date, DATE_TIME_PATTERN);
		double d = getworkhours(dateStr);
		return d;
	}

	/**
	 * 获取当天多少小时
	 * 
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static double getworkhours(String dateStr) throws ParseException {
		String morning_end = dateStr.substring(0, 9) + " 12:00:00";
		String morning_begin = dateStr.substring(0, 9) + " 08:30:00";
		String afternoon_end = dateStr.substring(0, 9) + " 17:30:00";
		String afternoon_begin = dateStr.substring(0, 9) + " 13:00:00";
		Date morning_end_date = stringToDate(morning_end, DATE_TIME_PATTERN);
		Date morning_begin_date = stringToDate(morning_begin, DATE_TIME_PATTERN);
		Date afternoon_end_date = stringToDate(afternoon_end, DATE_TIME_PATTERN);
		Date afternoon_begin_date = stringToDate(afternoon_begin, DATE_TIME_PATTERN);
		Date date = stringToDate(dateStr, DATE_TIME_PATTERN);
		double days = 0;
		if (date.compareTo(morning_end_date) < 0 && date.compareTo(morning_begin_date) > 0) {// 现在时间比早上时间大
			days = compareDate(date, morning_begin_date, 3);
		} else if (date.compareTo(morning_end_date) >= 0) {
			days += 210;
		}
		if (date.compareTo(afternoon_end_date) < 0 && date.compareTo(afternoon_begin_date) > 0) {
			days += compareDate(date, afternoon_begin_date, 3);
		} else if (date.compareTo(afternoon_end_date) >= 0) {
			days += 270;
		}
		// System.out.println(days);
		return days;
	}

	@Test
	public void classTest(String[] args) throws ParseException {
		String begin = "2017-8-24 09:00:00";
		String end = "2017-8-24 17:30:00";
		double date = getworkhours(begin, end);
		date /= 60;
		System.out.println(date);
		// getworkhours(begin, end);
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Date begindate = sdf.parse(begin);
		// Date enddate = sdf.parse(end);
		// long days = compareDate(enddate, begindate, 1);
		// System.out.println(days);
	}
}
