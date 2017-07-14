package org.great.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.alibaba.fastjson.JSONObject;

public class Message {
	/**
	 * 传输对象的字符串
	 */
	private String str;
	/**
	 * 当前页数
	 */
	private int page_new;
	/**
	 * 查询的内容
	 */
	private String chainfo;
	/**
	 * 每页显示的条数
	 */
	private int page_num;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public int getPage_new() {
		return page_new;
	}

	public void setPage_new(int page_new) {
		this.page_new = page_new;
	}

	public String getChainfo() {
		return chainfo;
	}

	public void setChainfo(String chainfo) {
		this.chainfo = chainfo;
	}

	public int getPage_num() {
		return page_num;
	}

	public void setPage_num(int page_num) {
		this.page_num = page_num;
	}

	@Override
	public String toString() {
		return "Message [str=" + str + ", page_new=" + page_new + ", chainfo="
				+ chainfo + ", page_num=" + page_num + "]";
	}

	public static Object automaticInjection(Object object,
			JSONObject jsonObject, String transformation) throws Exception {
		Class<?> c = Class.forName(object.getClass().getName());// 这里的类名是全名,有包的话要加上包名
		Field[] fields = c.getDeclaredFields();// 成员变量组:包括public、private、proteced,但是不包括父类的申明字段。(getFields()
												// 仅限public)
		Method[] mothods = c.getMethods();// 方法组:所有公用（public）方法包括其继承类的公用方法，当然也包括它所实现接口的方法。(getDeclaredMethods()对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。当然也包括它所实现接口的方法。)
		for (int i = 0; i < fields.length; i++) {
			String keyIn = fields[i].getName();
			String keyOut = new String();
			for (int j = 0; j < keyIn.length(); j++) {
				if (Character.isUpperCase(keyIn.charAt(j))) {// 判断成员变量大小写
					keyOut += transformation
							+ keyIn.substring(j, j + 1).toLowerCase();
				} else {
					keyOut += keyIn.charAt(j);
				}
			}
			for (int j = 0; j < mothods.length; j++) {
				// 判断set的对应方法名
				if (mothods[j].getName().equals(
						"set" + keyIn.substring(0, 1).toUpperCase()
								+ keyIn.substring(1, keyIn.length()))) {
					Object o = jsonObject.get(keyOut);
					mothods[j].invoke(object, o == null ? null : o.toString());// 执行当前方法
				}
			}
		}
		return object;
	}

}
