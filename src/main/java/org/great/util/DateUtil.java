package org.great.util;

import java.text.SimpleDateFormat;
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
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    
    /**
     * 时间计算
     * @param olddate
     * @param newdate
     * @return olddate与newdate 时间相差天数
     */
    public static long compareDate(Date olddate, Date newdate) {
		if (newdate == null) {
			newdate = new Date();
		}
		long diff = olddate.getTime() - newdate.getTime();
		long days = diff / (1000 * 60 * 60 * 24);
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
	 * @param begintime
	 * @param endtime
	 * @param map
	 * @return
	 */
	public static Map<String, Object> setQueryDate(String begintime,
			String endtime, Map<String, Object> map) {
		Boolean bo1 = (begintime == null || begintime.trim().equals(""));
		Boolean bo2 = (endtime == null || endtime.trim().equals(""));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		if (bo1 && bo2) {
			endtime = format(new Date(), "yyyy-MM-dd");
			begintime = getDate(null, -30);
		} else if (bo1 & !bo2) {
			//结束时间不为空
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
		if(map==null){
			map=new HashMap<String, Object>();
		}
		map.put("begintime", begintime);
		map.put("endtime", endtime);
		return map;
	}
}
