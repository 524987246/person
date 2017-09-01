package org.great.util.myutil;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.formula.functions.T;

/**
 * 集合工具类
 * 
 * @author xiejun
 * @date 2017-8-25 10:30:03
 * @since 1.0
 */
public class MyCollectionUtils {

	/**
	 * list to Set
	 * 
	 * @param list
	 * @return
	 */
	public static Set<T> ListToSet(List<T> list) {
		// list To Set
		Set<T> set = new HashSet<T>();
		set.addAll(list);
		// System.out.println(set.toString());
		return set;
	};

	/**
	 * Set to List
	 * 
	 * @param set
	 * @return
	 */
	public static List<Object> SetToList(Set<Object> set) {
		List<Object> list = new ArrayList<Object>();
		// set To list
		list = Arrays.asList(set.toArray());
		System.out.println(list.toString());
		return list;
	};

	/**
	 * Map To Object
	 * 
	 * @param map
	 * @param beanClass
	 * @return
	 * @throws Exception
	 */
	public static Object mapToObject3(Map<String, Object> map, Class<?> beanClass) throws Exception {
		if (map == null)
			return null;

		Object obj = beanClass.newInstance();

		BeanUtils.populate(obj, map);

		return obj;
	}

	/**
	 * Object To Map
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<?, ?> objectToMap3(Object obj) {
		if (obj == null)
			return null;

		return new org.apache.commons.beanutils.BeanMap(obj);
	}

	/**
	 * @param list
	 * @return false 为空 true 不为空
	 */
	public static boolean listEmpty(List<?> list) {
		boolean bo = false;
		if (list != null && list.size() > 0) {
			bo = true;
		}
		return bo;
	}
}
