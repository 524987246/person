package org.great.util;

import java.util.Date;
import java.util.Random;

public class TestUtils {
	public static void main(String[] args) {
		String str = "KL";
		String temp = "";
		for (int i = 0; i < 5; i++) {
			temp += filename(str, new Date());
			temp += ",";
		}
		System.out.println(temp);

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
