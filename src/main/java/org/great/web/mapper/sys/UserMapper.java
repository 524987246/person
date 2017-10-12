package org.great.web.mapper.sys;

import org.great.web.bean.sys.User;

public interface UserMapper {
	User get(User user);// 查询单个用户,用于登录查找之类
}
