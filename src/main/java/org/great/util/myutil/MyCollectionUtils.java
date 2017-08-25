package org.great.util.myutil;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

public class MyCollectionUtils {

	/**
	 * list to Set
	 * @param list
	 * @return
	 */
	public static Set ListToSet(List list) {
		// list To Set
		Set set = new HashSet();
		set.addAll(list);
		// System.out.println(set.toString());
		return set;
	};

	/**
	 * Set to List
	 * @param set
	 * @return
	 */
	public static List SetToList(Set set) {
		List list = new ArrayList();
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
}
