package org.great.web.service;

import javax.annotation.Resource;

import org.great.web.bean.User;
import org.great.web.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * 用户表操作
 * 
 * @author 谢军
 * 
 */
@Service
public class UserService {
	@Resource
	private UserMapper usermapper;

	/**
	 * 用于查找用户
	 * 
	 * @param user
	 * @return 数据库查询出的对象
	 */
	public User findUserByUser(User user) {
		User loginuser = usermapper.findUserByUser(user);
		return loginuser;
	}

	public UserMapper getUsermapper() {
		return usermapper;
	}

	public void setUsermapper(UserMapper usermapper) {
		this.usermapper = usermapper;
	}
}
