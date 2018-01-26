package org.great.util.beanValidtor.impl;

import org.great.util.beanValidtor.StringIsDate;
import org.great.util.myutil.MyStringUtils;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import static org.great.util.myutil.MyStringUtils.*;

/**
 * 自定义注解ListNotHasNull 的实现类 用于判断List集合中是否含有null元素
 */

@Service
public class StringIsDateValidatorImpl implements ConstraintValidator<StringIsDate, String> {

	private int value;

	@Override
	public void initialize(StringIsDate constraintAnnotation) {
		// 传入value 值，可以在校验中使用
		this.value = constraintAnnotation.value();
	}

	public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
		System.out.println("str==" + str);
		if (isNullOrEmpty(str)) {
			return true;
		}
		if (isDate(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

}