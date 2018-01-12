package org.great.util.myutil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.great.config.JsonConfigFactory;

public class MyJsonUtils {
	/**
	 * 对象 TO JSONObject对象
	 * 
	 * @param obj
	 * @return JSONObject对象 json
	 * @since 包:net.sf.json
	 */
	public static net.sf.json.JSONObject objToJsonObject(Object obj) {
		net.sf.json.JSONObject jsonStu = net.sf.json.JSONObject.fromObject(obj, JsonConfigFactory.getInstance());
		return jsonStu;
	}

	/**
	 * 对象 TO Json字符串
	 * 
	 * @param obj
	 * @return Json字符串 json
	 * @since 包:net.sf.json
	 */
	public static String objToJson(Object obj) {
		net.sf.json.JSONObject jsonStu = net.sf.json.JSONObject.fromObject(obj);
		return jsonStu.toString();
	}

	/**
	 * Json字符串 ==>对象
	 * 
	 * @param Json字符串
	 * @return 对象
	 * @since 包:net.sf.json
	 * 
	 */
	public static Object strToObj(String str) {
		Object obj = net.sf.json.JSONObject.toBean(objToJsonObject(str), Object.class);
		return obj;
	}

	/**
	 * Json字符串 ==>集合
	 * 
	 * @param Json字符串
	 * @return 集合
	 * @since 包:net.sf.json
	 * 
	 */
	public static List<Object> strToList(String str) {
		List<Object> list = (List<Object>) net.sf.json.JSONArray.toList(net.sf.json.JSONArray.fromObject(str),
				Object.class);
		return list;
	}

	/**
	 * 集合 ==>Json字符串
	 * 
	 * @param 集合
	 * @return Json字符串
	 * @since 包:net.sf.json
	 * 
	 */
	public static String listToStr(List<Object> list) {
		String msg = net.sf.json.JSONArray.fromObject(list).toString();
		return msg;
	}

	/**
	 * Json字符串 ==>数组
	 * 
	 * @param Json字符串
	 * @return 数组
	 * @since 包:net.sf.json
	 * 
	 */
	public static Object[] strToArray(String str) {
		Object[] list2 = (Object[]) net.sf.json.JSONArray.toArray(net.sf.json.JSONArray.fromObject(str), Object.class);
		return list2;
	}

	/**
	 * 对象 TO Json字符串
	 * 
	 * @param obj
	 * @return Json字符串 json
	 * @throws JsonProcessingException
	 * @since 包:com.fasterxml.jackson.databind.
	 */
	public static String objToJson2(Object obj) throws com.fasterxml.jackson.core.JsonProcessingException {
		com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
		String json = mapper.writeValueAsString(obj);
		return json;
	}

	/**
	 * Json字符串 ==>集合
	 * 
	 * @param Json字符串
	 * @return 集合
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @since 包:com.fasterxml.jackson.databind
	 * 
	 */
	public static List<Object> strToList2(String str) throws com.fasterxml.jackson.core.JsonParseException,
			com.fasterxml.jackson.databind.JsonMappingException, IOException {
		com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
		com.fasterxml.jackson.databind.JavaType javaType = getCollectionType(mapper, ArrayList.class, Object.class);
		List<Object> list = new ArrayList<Object>();
		if (MyStringUtils.isEmpty(str)) {
			list = (List<Object>) mapper.readValue(str, javaType);
		}
		return list;
	}

	public static com.fasterxml.jackson.databind.JavaType getCollectionType(
			com.fasterxml.jackson.databind.ObjectMapper mapper, Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

}
