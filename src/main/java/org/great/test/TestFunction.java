package org.great.test;

import java.util.ArrayList;
import java.util.List;

import org.great.util.beanValidtor.ValidtorUtil;
import org.junit.Test;

/**
 * 功能测试
 * @author xiejun
 *
 */
public class TestFunction {
	
	@Test
	public void testValidtor() {
		UserValidtorTest user = new UserValidtorTest();
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		user.setList(list);
		user.setName("12345");
		String str = ValidtorUtil.validbean(user);
		if (str != null) {
			System.out.println(str);
		}
	}
}
