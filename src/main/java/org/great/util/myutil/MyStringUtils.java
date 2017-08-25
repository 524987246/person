package org.great.util.myutil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.validator.GenericValidator;
import org.great.util.DateUtil;

/**
 * String工具类
 * 
 * @author xiejun
 * @date 2017-8-25 10:30:03
 * @since 1.0
 */
public class MyStringUtils {
	/**
	 * 创建UUID
	 * 
	 * @return
	 */
	public static synchronized String makeUUID() {
		Date date = new Date();
		StringBuffer s = new StringBuffer(DateUtil.dateToString(date, DateUtil.DATE_TIME_PATTERN));
		return s.append((new Random().nextInt(900) + 100)).toString();
	}

	/**
	 * @param str
	 * @return false 为空 true 不为空
	 */
	public static boolean isEmpty(String str) {
		boolean bo = false;
		if (str != null && !str.trim().equals("")) {
			bo = true;
		}
		return bo;
	}

	/**
	 * 是否可转化为数字
	 * 
	 * @param o
	 * @return true 是, false 否
	 */
	public static boolean isToNum(Object o) {
		try {
			new BigDecimal(o.toString());
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 字符串 To List集合
	 * 
	 * @param str
	 *            格式[1,2,3,....]
	 * @return List<Object> list 通过list.size()判断是否转换成功
	 */
	public static List<Object> toList(String str, String splitChar) {
		List<Object> list = new ArrayList<Object>();
		if (isEmpty(str)) {
			str = str.substring(1, str.length() - 1);
			String[] arr = str.split(splitChar);
			for (String string : arr) {
				list.add(string);
			}
		}
		return list;
	}

	/**
	 * json字符串 To List集合
	 * 
	 * @param strJson
	 *            格式[{"name":"a","age":"1"},{"name":"b","age":"2"},....]
	 * @return List<Object> list 通过list.size()判断是否转换成功
	 */
	public static List<String> toListByJsonStr(String strJson) {
		List<String> list = new ArrayList<String>();
		if (isEmpty(strJson)) {
			strJson = strJson.substring(1, strJson.length() - 1);
			List<Integer> flaglist = getStringByChar(strJson, "},{");
			for (int i = 0; i < flaglist.size() + 1; i++) {
				int begin = (i == 0 ? i : (flaglist.get(i - 1) + 2));
				int end = (i == flaglist.size() ? (strJson.length() - 1) : flaglist.get(i));
				String temp = strJson.substring(begin, end) + "}";
				list.add(temp);
			}
		}
		return list;
	}

	/**
	 * 获取某个字符在字符串中的所有位置
	 * 
	 * @param str
	 * @param flag
	 * @return
	 */
	public static List<Integer> getStringByChar(String str, String flag) {
		List<Integer> list = new ArrayList<Integer>();
		int i = str.indexOf(flag);
		while (i != -1) {
			// System.out.println(i);
			list.add(i);
			i = str.indexOf(flag, (i + 1));
		}
		return list;
	}

	/**
	 * 验证日期/时间格式
	 * 
	 * @param dateStr
	 *            待验证的字符串
	 * @param format
	 *            类型
	 * @return 是返回ture,否则返回false
	 */
	public static boolean isDateTime(String dateStr, String format) {
		return GenericValidator.isDate(dateStr, format, true);
	}

	public static void main(String[] args) {
		String str = "[{\"name\":\"a\",\"age\":\"1\"},{\"name\":\"b\",\"age\":\"2\"},{\"name\":\"c\",\"age\":\"3\"}]";
		toListByJsonStr(str);
	}
}
