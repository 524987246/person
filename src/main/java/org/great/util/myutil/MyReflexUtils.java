package org.great.util.myutil;

import java.lang.reflect.Field;
import java.util.Map;
import net.sf.json.JSONObject;

public class MyReflexUtils {
	public static void printClassName(Object obj) {
		System.out.println("The class of " + obj + " is " + obj.getClass().getName());
	}

	/**
	 * 自动注入
	 * 
	 * @param a
	 *            对象类型
	 * @param map
	 *            主动注入的属性
	 * @return 对象
	 * @throws Exception
	 *             如对应属性名有不同,需修改方法
	 */
	public static Object printField(Class a, Map<String, Object> map) throws Exception {
		Object obj = a.newInstance();
		Field[] fields = obj.getClass().getDeclaredFields();
		Field.setAccessible(fields, true);
		for (Field field : fields) {
			String fieldName = field.getName();
			Object temp = map.get(fieldName);
			if (temp != null) {
				field.set(obj, temp);
			}
		}
		return obj;
	}

	/**
	 * 自动注入
	 * 
	 * @param a
	 *            对象类型
	 * @param object
	 *            对象
	 * @return
	 * @throws Exception
	 */
	public static Object printField(Class a, Object object) throws Exception {
		Map<String, Object> map = (Map<String, Object>) MyCollectionUtils.objectToMap(object);
		return printField(a, map);
	}

	/**
	 * 自动注入
	 * 
	 * @param a
	 *            对象类型
	 * @param jsonstr
	 *            json格式对象
	 * @return
	 * @throws Exception
	 */
	public static Object printField(Class a, String jsonstr) throws Exception {
		Object temp = (Object) JSONObject.toBean(JSONObject.fromObject(jsonstr), a.getClass());
		return printField(a, temp);
	}
}
