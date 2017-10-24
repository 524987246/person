package org.great.util.myutil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import net.sf.json.JSONObject;

/**
 * 反射辅助类
 * 
 * @author xiejun
 * @date 2017-9-18 10:42:06
 * @since 1.0
 */
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
	public static Object printField(Class<?> a, Map<String, Object> map) throws Exception {
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
	public static Object printField(Class<?> a, Object object) throws Exception {
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
	public static Object printField(Class<?> a, String jsonstr) throws Exception {
		Object temp = (Object) JSONObject.toBean(JSONObject.fromObject(jsonstr), a.getClass());
		return printField(a, temp);
	}

	/**
	 * 反射注入
	 * 根据方法名住处
	 * @author 刘江鸿
	 * @since 1.0
	 * 
	 * @param object
	 * @param jsonObject
	 * @param transformation
	 * @return
	 * @throws Exception
	 */
	public static Object automaticInjection(Object object, JSONObject jsonObject, String transformation)
			throws Exception {
		Class<?> c = Class.forName(object.getClass().getName());// 这里的类名是全名,有包的话要加上包名
		Field[] fields = c.getDeclaredFields();
		// 成员变量组:包括public、private、proteced,但是不包括父类的申明字段。(getFields()
		// 仅限public)
		Method[] mothods = c.getMethods();
		// 方法组:所有公用（public）方法包括其继承类的公用方法，当然也包括它所实现接口的方法。(getDeclaredMethods()对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。当然也包括它所实现接口的方法。)
		for (int i = 0; i < fields.length; i++) {
			String keyIn = fields[i].getName();
			String keyOut = new String();
			for (int j = 0; j < keyIn.length(); j++) {
				if (Character.isUpperCase(keyIn.charAt(j))) {// 判断成员变量大小写
					keyOut += transformation + keyIn.substring(j, j + 1).toLowerCase();
				} else {
					keyOut += keyIn.charAt(j);
				}
			}
			for (int j = 0; j < mothods.length; j++) {
				// 判断set的对应方法名
				if (mothods[j].getName()
						.equals("set" + keyIn.substring(0, 1).toUpperCase() + keyIn.substring(1, keyIn.length()))) {
					Object o = jsonObject.get(keyOut);
					mothods[j].invoke(object, o == null ? null : o.toString());// 执行当前方法
				}
			}
		}
		return object;
	}
}
