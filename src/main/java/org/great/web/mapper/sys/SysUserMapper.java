package org.great.web.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.web.bean.buz.CostInfo;
import org.great.web.bean.buz.User;

/**
 * mapper
 * 
 * @author 谢军
 * 
 */
public interface SysUserMapper {

	public User findUserByUser(User user);
}
