package org.great.web.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.web.bean.buz.CostInfo;
import org.great.web.bean.sys.Dept;
import org.great.web.bean.sys.Role;

/**
 * 消费信息mapper
 * 
 * @author 谢军
 * 
 */
public interface RoleMapper {

	List<Role> findList(Role role);

}
