package org.great.web.mapper.sys;

import org.great.web.bean.sys.Role;
import org.great.web.bean.sys.RoleMenu;
import org.great.web.mapper.BaseMapper;

/**
 * 消费信息mapper
 * 
 * @author xiejun
 * 
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
	public int deleteByRoleMenu(Role role);
}
