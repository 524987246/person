package org.great.util.myutil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.great.config.BaseResoure;
import org.great.web.bean.sys.User;

/**
 * 用户工具类
 * 
 * @author xiejun
 *
 */
public class MyUserUtils {
	/**
	 * 保存登录用户
	 * 
	 * @param user
	 * @param request
	 */
	public static void saveLoginUser(User user, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute(BaseResoure.LOGIN_USER_FLAG, user);
	}

	/**
	 * 获取登录用户
	 * 
	 * @param user
	 * @param request
	 */
	public static User getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(BaseResoure.LOGIN_USER_FLAG);
		return user;
	}
}
