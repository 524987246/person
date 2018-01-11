package org.great.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.great.util.beanValidtor.ValidtorUtil;
import org.great.util.myutil.MyStringUtils;
import org.great.web.bean.sys.BaseBean;
import org.great.web.bean.sys.User;
import org.junit.Test;

/**
 * 功能测试
 * 
 * @author xiejun
 *
 */
public class TestFunction {

	// @Test
	public void testValidtor() {
		UserValidtorTest user = new UserValidtorTest();
		Map<String, String> map = new HashMap<String, String>();
		// map.put("1","1");
		// map.put("2","22");
		// map.put("3","33");
		user.setMap(map);
		List<String> list = new ArrayList<String>();
		// list.add("1");
		list.add("1");
		user.setList(list);
		user.setName("12345");
		String str = ValidtorUtil.validbean(user);
		if (str != null) {
			System.out.println(str);
		}
	}

	@Test
	public void param() {
		/*String tableName = "sys_user";
		tableName=MyStringUtils.setStringByChar(tableName, "_", ":");
		System.out.println(tableName);
		tableName = "sys_user";
		tableName=MyStringUtils.setStringByChar(tableName, "_", "/");
		System.out.println(tableName);*/
		Class clazz = new User().getClass();
		 Class class1 = BaseBean.class;
		 System.out.println(clazz.toString());
		 System.out.println(class1.toString());
		 System.out.println(class1.isAssignableFrom(clazz));
	}
}
