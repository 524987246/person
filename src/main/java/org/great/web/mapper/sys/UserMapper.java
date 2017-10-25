package org.great.web.mapper.sys;

import org.great.web.bean.sys.User;
import org.great.web.mapper.BaseMapper;

public interface UserMapper extends BaseMapper<User> {
	User get(User user);// 查询单个用户,用于登录查找之类
}
