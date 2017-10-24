package org.great.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.great.util.myutil.MyPrintUtil;
import org.great.util.myutil.MyUserUtils;
import org.great.web.bean.sys.Menu;
import org.great.web.bean.sys.Role;

public class TestUtils {

	// @Test
	public void objtest() {
		// 克隆方法测试
		Role role = new Role();
		Menu menu = new Menu();
		menu.setName("克隆前");
		List<Menu> list = new ArrayList<Menu>();
		list.add(menu);
		role.setMenulist(list);
		Role role2 = (Role) MyUserUtils.myclone(role);
		role2.getMenulist().get(0).setName("克隆后");
		System.out.println(role2 == role);
		System.out.println(role2.getMenulist() == role.getMenulist());
		System.out.println(role.getMenulist().get(0).getName());
		System.out.println(role2.getMenulist().get(0).getName());
	}

	// @Test
	public void classTest(String[] args) {
		String str = "KL";
		String temp = "";
		for (int i = 0; i < 5; i++) {
			temp += filename(str, new Date());
			temp += ",";
		}
		MyPrintUtil.println(temp);

	}

	public static String filename(String str, Date date) {
		String temp = "";
		Random random = new Random();
		temp = str;
		for (int i = 0; i < 4; i++) {
			temp += random.nextInt(10);
		}
		return temp;
	}
}
