package org.great.util;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class ObjectToMap {
	
	/**
	 * Map To Object
	 * @param map
	 * @param beanClass
	 * @return
	 * @throws Exception
	 */
	public static Object mapToObject3(Map<String, Object> map,
			Class<?> beanClass) throws Exception {
		if (map == null)
			return null;

		Object obj = beanClass.newInstance();

		BeanUtils.populate(obj, map);

		return obj;
	}

	/**
	 * Object To Map
	 * @param obj
	 * @return
	 */
	public static Map<?, ?> objectToMap3(Object obj) {
		if (obj == null)
			return null;

		return new org.apache.commons.beanutils.BeanMap(obj);
	}
}
