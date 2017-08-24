package org.great.util;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DateUtil {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static String format(Date date) {
		return format(date, DATE_PATTERN);
	}

	public static String format(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
		}
		return null;
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
	 * @return
	 */
	public static Map<String, Object> setQueryDate(String begintime, String endtime, Map<String, Object> map) {
		Boolean bo1 = (begintime == null || begintime.trim().equals(""));
		Boolean bo2 = (endtime == null || endtime.trim().equals(""));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		if (bo1 && bo2) {
			endtime = format(new Date(), "yyyy-MM-dd");
			begintime = getDate(null, -30);
		} else if (bo1 & !bo2) {
			// 结束时间不为空
			try {
				date = sdf.parse(endtime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			begintime = getDate(date, -30);
		} else if (!bo1 & bo2) {
			try {
				date = sdf.parse(begintime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			endtime = getDate(date, 30);
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
	public static void getworkhours(String begindate, String enddate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date begindate_date = sdf.parse(begindate);
		Date enddate_date = sdf.parse(enddate);
		long days = 0;
		days += getworkhours(begindate);
		days += getworkhours(enddate);
		days += (compareDate(enddate_date, begindate_date, 2) - 1) * 8 * 60;
		System.out.println(days);
	}

	/**
	 * 获取当天多少小时
	 * 
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static long getworkhours(String dateStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String morning_end = dateStr.substring(0, 9) + " 12:00:00";
		String morning_begin = dateStr.substring(0, 9) + " 08:30:00";
		String afternoon_end = dateStr.substring(0, 9) + " 17:30:00";
		String afternoon_begin = dateStr.substring(0, 9) + " 13:00:00";
		Date morning_end_date = sdf.parse(morning_end);
		Date morning_begin_date = sdf.parse(morning_begin);
		Date afternoon_end_date = sdf.parse(afternoon_end);
		Date afternoon_begin_date = sdf.parse(afternoon_begin);
		Date date = sdf.parse(dateStr);
		long days = 0;
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

	public static void main(String[] args) throws ParseException {
		String begin = "2017-8-24 08:30:00";
		String end = "2017-8-25 17:30:00";
		getworkhours(begin, end);
		// getworkhours(begin, end);
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Date begindate = sdf.parse(begin);
		// Date enddate = sdf.parse(end);
		// long days = compareDate(enddate, begindate, 1);
		// System.out.println(days);
	}
}
