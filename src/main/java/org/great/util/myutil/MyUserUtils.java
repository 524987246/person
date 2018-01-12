package org.great.util.myutil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.great.config.BaseResoure;
import org.great.web.bean.sys.User;

import org.apache.commons.beanutils.BeanUtils;

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
	 */
	public static User getLoginUser() {
		/*
		 * if (ThreadContext.getSecurityManager() == null) { return null; }
		 */
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		return user;
	}

	/**
	 * 更新用户信息
	 */
	public static void updateUserInfo(User temp) {
		User user = getLoginUser();
		try {
			BeanUtils.copyProperties(user, temp);
			// System.out.println(user.toString());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 深度克隆
	 * 
	 * @param object
	 * @return
	 */
	public static Object myclone(Object object) {
		Object obj = null;
		try { // 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			obj = ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
