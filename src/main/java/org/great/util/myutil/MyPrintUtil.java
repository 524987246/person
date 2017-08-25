package org.great.util.myutil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class MyPrintUtil {
	public static void printMap(Map<String, Object> map) {
		Iterator iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			print(key);
			print("==");
			print(map.get(key));
			println("");
		}
	}

	public static void printList(List<Object> list) {
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

	@Test
	public void mytest() {
		Map<String, Object> tempMap = new HashMap<String, Object>();
		tempMap.put("a", 1);
		tempMap.put("b", 2);
		tempMap.put("c", 3);
		printMap(tempMap);
	}
}
