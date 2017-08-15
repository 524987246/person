package org.great.util;

import java.util.List;

public class MyListUtil {
	/**
	 * @param list
	 * @return false 为空 true 不为空
	 */
	public static boolean listEmpty(List list) {
		boolean bo = false;
		if (list != null && list.size() > 0) {
			bo = true;
		}
		return bo;
	}
}
