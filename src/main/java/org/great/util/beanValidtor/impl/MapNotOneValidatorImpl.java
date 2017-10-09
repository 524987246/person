package org.great.util.beanValidtor.impl;

import org.great.util.beanValidtor.MapNotOne;
import org.great.util.myutil.MyPrintUtil;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.Iterator;
import java.util.Map;

/**
 * 自定义注解ListNotHasNull 的实现类 用于判断List集合中是否含有null元素
 */

@Service
public class MapNotOneValidatorImpl implements ConstraintValidator<MapNotOne, Map> {

	private int value;

	@Override
	public void initialize(MapNotOne constraintAnnotation) {
		// 传入value 值，可以在校验中使用
		this.value = constraintAnnotation.value();
	}

	public boolean isValid(Map map, ConstraintValidatorContext constraintValidatorContext) {
		MyPrintUtil.printMap(map);
		if (map == null) {
			return false;
		}
		if (map.size() == 0) {
			return false;
		}
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			if ("1".equals(map.get(key))) {
				return false;
			}
			;
		}
		return true;
	}

}