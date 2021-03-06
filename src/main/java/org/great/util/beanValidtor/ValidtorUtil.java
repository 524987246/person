package org.great.util.beanValidtor;

import java.awt.Menu;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.great.util.myutil.MyResult;
import org.great.util.myutil.MyStringUtils;
import org.great.web.bean.sys.User;

public class ValidtorUtil {
	/**
	 * 验证属性是否正常
	 * 
	 * @param 对象
	 * @return
	 */
	public static <T> String validbean(T t) {
		ValidatorFactory vFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = vFactory.getValidator();
		Set<ConstraintViolation<T>> set = validator.validate(t);
		if (set.size() > 0) {
			StringBuilder validateError = new StringBuilder();
			for (ConstraintViolation<T> val : set) {
				validateError.append(val.getMessage());
				validateError.append("\r\n");
			}
			return validateError.toString();
		}
		return null;
	}

}
