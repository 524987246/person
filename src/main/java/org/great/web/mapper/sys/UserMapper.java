package org.great.web.mapper.sys;

import org.great.web.bean.sys.User;
/**
 * 用户mapper
 * @author 谢军
 *
 */
public interface UserMapper {
	User findUserByUser(User user);//查询单个用户,用于登录查找之类 
}
