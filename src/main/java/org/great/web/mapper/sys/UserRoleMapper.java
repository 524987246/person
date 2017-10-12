package org.great.web.mapper.sys;

import org.great.web.bean.sys.UserRole;
import org.great.web.mapper.BaseMapper;

public interface UserRoleMapper extends BaseMapper<UserRole> {

	public int deleteByUserRole(UserRole userRole);
}
