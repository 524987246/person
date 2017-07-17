package org.great.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonAndObj {

	/**
	 * jsonToObj
	 * 
	 * @param liststr
	 * @param object
	 * @return
	 */
	public static Object jsonToObj(String liststr, Object object) {
		Object temp = (Object) JSONObject.toBean(JSONObject.fromObject(liststr), object.getClass());
		return temp;
	}

	/**
	 * ObjToJson
	 * 
	 * @param object
	 * @return
	 */
	public static String ObjToJson(Object object) {
		JSONObject jsonStu = JSONObject.fromObject(object);
		return jsonStu.toString();
	}

	/**
	 * ListToJson
	 * @param list
	 * @return
	 */
	public static String ListToJson(List<Object> list) {
		String msg = JSONArray.fromObject(list).toString();
		return msg;
	}
	/**
	 * jsonToList
	 * 
	 * @param liststr
	 * @param object
	 * @return
	 */
	public static List<Object> jsonToList(String liststr, Object object) {
		List<Object> list2 = (List<Object>) JSONArray.toList(JSONArray.fromObject(liststr), object.getClass());
		return list2;
	}

	/**
	 * jsonToArray
	 * 
	 * @param liststr
	 * @param object
	 * @return
	 */
	public static Object[] jsonToArray(String liststr, Object object) {
		Object[] list2 = (Object[]) JSONArray.toArray(JSONArray.fromObject(liststr), object.getClass());
		return list2;
	}
}
