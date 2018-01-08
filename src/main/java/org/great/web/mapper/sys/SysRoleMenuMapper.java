package org.great.web.mapper.sys;

import org.great.web.bean.sys.RoleMenu;
import org.great.web.mapper.BaseMapper;

/**
 * 消费信息mapper
 * 
 * @author xiejun
 * 
 */
public interface SysRoleMenuMapper extends BaseMapper<RoleMenu> {
	public int deleteByRoleMenu(RoleMenu roleMenu);
}
