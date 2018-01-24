package org.great.util.beanValidtor;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.great.util.beanValidtor.impl.MapNotOneValidatorImpl;
import org.great.util.beanValidtor.impl.StringIsDateValidatorImpl;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义参数校验注解 校验 List 集合中是否有null 元素
 */

@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = StringIsDateValidatorImpl.class) //// 此处指定了注解的实现类为ListNotHasNullValidatorImpl

public @interface StringIsDate {

	/**
	 * 添加value属性，可以作为校验时的条件,若不需要，可去掉此处定义
	 */
	int value() default 0;

	String message() default "不是时间格式";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * 定义map，为了让Bean的一个属性上可以添加多套规则
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RUNTIME)
	@Documented
	@interface str {
		StringIsDate value();
	}
}