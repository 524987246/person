package org.great.util.lucene;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.lang.reflect.Field;

/**
 * 打印信息辅助类
 * 
 * @author xiejun
 * @date 2017-8-25 10:30:03
 * @since 1.0
 */
public class MyPrintUtil {

	public static void printObject(Object obj) {
		if (obj == null) {
			print("null");
		}
		Field[] fields = obj.getClass().getDeclaredFields();// 根据Class对象获得属性
															// 私有的也可以获得
		String s = "";
		try {
			for (Field f : fields) {
				f.setAccessible(true); // 设置些属性是可以访问的
				Object val = f.get(obj); // 得到此属性的值
				String name = f.getName(); // 得到此属性的名称
				s = name + ":" + val + ",";
				println(s);
			}
		} catch (IllegalAccessException e) {
		}
	}

	public static void printMap(Map<? extends Object, ? extends Object> map) {
		if (map == null) {
			printlnError("map === NULL");
			return;
		}
		if (map.size() == 0) {
			printlnError("map 长度 === 0");
			return;
		}
		Iterator<? extends Object> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			print(key);
			print("==");
			print(map.get(key));
			println("");
		}
	}

	public static void printSet(Set<? extends Object> set) {
		if (set == null) {
			printlnError("set === NULL");
			return;
		}
		if (set.size() == 0) {
			printlnError("set 长度 === 0");
			return;
		}
		Iterator<? extends Object> iterator = set.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			println(key);
		}
	}

	public static void printList(List<? extends Object> list) {
		if (list == null) {
			printlnError("list === NULL");
			return;
		}
		if (list.size() == 0) {
			printlnError("list 长度 === 0");
			return;
		}
		for (Object object : list) {
			println(object);
		}
	}

	public static void println(Object object) {
		if (object != null) {
			System.out.println(object.toString());
		} else {
			System.out.println("null");
		}
	}

	public static void print(Object object) {
		if (object != null) {
			System.out.print(object.toString());
		} else {
			System.out.println("null");
		}
	}

	public static void printlnError(Object object) {

		if (object != null) {
			System.err.println(object.toString());
		} else {
			System.err.println("null");
		}
	}

}
