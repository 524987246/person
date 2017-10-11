package org.great.web.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.web.bean.buz.CostInfo;
import org.great.web.bean.sys.Dept;
import org.great.web.bean.sys.Role;
import org.great.web.mapper.BaseMapper;

/**
 * 消费信息mapper
 * 
 * @author xiejun
 * 
 */
public interface RoleMenuMapper extends BaseMapper<Role> {
	public int deleteByRoleMenu(Role role);
}
