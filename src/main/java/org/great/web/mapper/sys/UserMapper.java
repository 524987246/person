package org.great.web.mapper.sys;

import java.util.List;

import org.great.web.bean.sys.User;
import org.great.web.mapper.BaseMapper;

public interface UserMapper extends BaseMapper<User> {
	User get(User user);// 查询单个用户,用于登录查找之类

	List<User> checkLoginName(User user);// 登录名称验证
}
