package org.great.util.myutil;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.great.web.bean.sys.Menu;
import org.great.web.bean.sys.SysRole;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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

	/**
	 * 打印全部request中的参数
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, Object> printRequestPara(HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();
		Enumeration<?> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String next = enumeration.nextElement().toString();
			print(next);
			next = (request.getParameter(next) == null ? "空" : request.getParameter(next));
			print(" : " + next + ";");
			println("");
			param.put(next, request.getParameter(next));
		}
		return param;
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
		System.out.println(object.toString());
	}

	public static void print(Object object) {
		System.out.print(object.toString());
	}

	public static void printlnError(Object object) {
		System.err.println(object.toString());
	}

	@Test
	public void mytest() {
		// 输出方法测试
		SysRole role = new SysRole();
		Menu menu = new Menu();
		menu.setName("克隆前");
		List<Menu> list = new ArrayList<Menu>();
		list.add(menu);
		//role.set
		// MyPrintUtil.printList(list);
		Set<Menu> set = new HashSet<Menu>();
		set.add(menu);
		// MyPrintUtil.printSet(set);
		Map<String, Menu> map = new HashMap<String, Menu>();
		map.put("key", menu);
		MyPrintUtil.printMap(map);
	}
}
