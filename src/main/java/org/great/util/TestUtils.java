package org.great.util;

import java.util.Date;
import java.util.Random;

import org.great.util.myutil.MyPrintUtil;
import org.junit.Test;

public class TestUtils {
	@Test
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
