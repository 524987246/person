package org.great.util.myutil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.GenericValidator;

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
		//UUID.randomUUID();
		Date date = new Date();
		StringBuffer s = new StringBuffer(MyDateUtils.dateToString(date, MyDateUtils.DATE_TIME_PATTERN));
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
	 * 判断字符串为null 或者为空
	 * @param str
	 * @return  true (null或空)  false (不为空)
	 */
	public static boolean isNullOrEmpty(String str) {
		boolean bo = false;
		if (str == null || str.trim().equals("")) {
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
	 * 获取某个字符在字符串中的所有位置
	 * 
	 * @param str
	 *            要替换的字符串
	 * @param flag
	 *            要替换的字符
	 * @param str1
	 *            替换后的字符
	 * @return 替换后的字符串
	 */
	public static String setStringByChar(String str, String flag, String str1) {
		List<Integer> list = getStringByChar(str, flag);
		return setStringByChar(str, list, str1);
	};

	/**
	 * 获取某个字符在字符串中的所有位置
	 * 
	 * @param str
	 *            要替换的字符串
	 * @param flag
	 *            要替换的字符
	 * @param str1
	 *            替换后的字符
	 * @return 替换后的字符串
	 */
	public static String setStringByChar(String str, String flag, String[] str1) {
		List<Integer> list = getStringByChar(str, flag);
		return setStringByChar(str, list, str1);
	};

	/**
	 * 字符串中某个字符进行替换
	 * 
	 * @param str
	 *            要替换的字符串
	 * @param list
	 *            字符的位置集合
	 * @param str1
	 *            替换后的字符
	 * @return 替换后的字符串
	 */
	public static String setStringByChar(String str, List<Integer> list, String str1) {
		String temp = "";
		int begin = 0;
		int end = 0;
		for (int i = 0; i < list.size(); i++) {
			end = list.get(i);
			temp += str.substring(begin, end) + str1;
			begin = end + 1;
		}
		if (end < str.length()) {
			if (end == 0) {
				temp += str;
			} else {
				temp += str.substring(end + 1);
			}
		}
		return temp;
	}

	/**
	 * 字符串中某个字符进行替换
	 * 
	 * @param str
	 *            要替换的字符串
	 * @param list
	 *            字符的位置集合
	 * @param str1
	 *            替换后的字符集合
	 * @return 替换后的字符串
	 */
	public static String setStringByChar(String str, List<Integer> list, String[] str1) {
		if (list.size() != str1.length) {
			return "替换长度有误";
		}
		String temp = "";
		int begin = 0;
		int end = 0;
		for (int i = 0; i < list.size(); i++) {
			end = list.get(i);
			temp += str.substring(begin, end) + str1[i];
			begin = end + 1;
		}
		if (end < str.length()) {
			temp += str.substring(end + 1);
		}
		return temp;
	}

	/**
	 * 验证日期/时间格式
	 * 
	 * @param dateStr
	 *            待验证的字符串
	 * @param format
	 *            类型
	 * @return 是 返回ture,否则 返回false
	 */
	public static boolean isDateTime(String dateStr, String format) {
		return GenericValidator.isDate(dateStr, format, true);
	}

	/**
	 * 判断是否是正确的时间格式
	 * @param strDate
	 * @return
	 */
	public static boolean isDate(String strDate) {
		Pattern pattern = Pattern.compile(
				"^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		Matcher m = pattern.matcher(strDate);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * parent_id ===>parentId;
	 * 
	 * @param name
	 * @return
	 */
	public static String setColName(String name) {
		if (isEmpty(name)) {
			String[] array = name.split("_");
			name = "";
			for (int i = 0; i < array.length; i++) {
				if (i == 0) {
					name += array[i];
				} else {
					name += array[i].substring(0, 1).toUpperCase() + array[i].substring(1);
					;
				}
			}
			name = name.trim();
		}
		return name;
	}

	/**
	 * parent_id ===>ParentId;
	 * 
	 * @param name
	 * @return
	 */
	public static String setColName2(String name) {
		if (isEmpty(name)) {
			String[] array = name.split("_");
			name = "";
			for (int i = 0; i < array.length; i++) {
				name += array[i].substring(0, 1).toUpperCase() + array[i].substring(1);
			}
			name = name.trim();
		}
		return name;
	}

	public static void main(String[] args) {
		String str = "parent";
		System.out.println(setColName(str));
		// String str =
		// "[{\"name\":\"a\",\"age\":\"1\"},{\"name\":\"b\",\"age\":\"2\"},{\"name\":\"c\",\"age\":\"3\"}]";
		// toListByJsonStr(str);
		// System.out.println(isDateTime("2017-10-09", "yyyy-MM-dd"));
	}

}
