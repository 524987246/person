package org.great.util;

import java.io.UnsupportedEncodingException;

public class InputCheck {
	/**
	 * 非必填项判断
	 * 
	 * @param str
	 * @param i
	 * @return
	 */
	public static boolean AllowNullsString(String str, int i) {
		boolean bo = false;
		if (str == null) {
			bo = true;
		}
		if (str != null && str.trim().length() >= 0 && str.trim().length() <= i) {
			bo = true;
		}
		return bo;
	}

	/**
	 * 必填项判断
	 * 
	 * @param str
	 * @param i
	 * @return
	 */
	public static boolean NotNullsString(String str, int i) {
		boolean bo = false;
		if (str != null && str.trim().length() >= 1 && str.trim().length() <= i) {
			bo = true;
		}
		return bo;
	}

	/**
	 * 判断字符串在utf-8模式下的长度
	 * @param str 要判断的字符串
	 * @param length 规定的长度
	 * @return true/false
	 * @throws UnsupportedEncodingException
	 */
	public static boolean StringLengthCheck(String str, int length)
			throws UnsupportedEncodingException {
		boolean bo = false;
		int count = 0;
		count = str.getBytes("utf-8").length;
		bo=(count<=length);
		return bo;
	}
}
