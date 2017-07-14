package org.great.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.web.bean.CostInfo;
import org.great.web.bean.User;

/**
 * mapper
 * 
 * @author 谢军
 * 
 */
public interface SysUserMapper {

	public User findUserByUser(User user);
}
