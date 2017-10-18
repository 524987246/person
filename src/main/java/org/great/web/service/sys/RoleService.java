package org.great.web.service.sys;

import java.util.List;

import javax.annotation.Resource;

import org.great.web.bean.sys.Role;
import org.great.web.mapper.sys.RoleMapper;
import org.springframework.stereotype.Service;

/**
 * 用户表操作
 * 
 * @author xiejun
 * 
 */
@Service
public class RoleService {
	@Resource
	private RoleMapper roleMapper;

	public Role get(Long id) {
		Role Role = roleMapper.get(id);
		return Role;
	}

	public List<Role> findList(Role role) {
		List<Role> list = roleMapper.findList(role);
		return list;
	}

	public int save(Role role) {
		role.setBaseInfo();
		int i = roleMapper.save(role);
		return i;
	}

	public int update(Role role) {
		role.setBaseInfo();
		int i = roleMapper.update(role);
		return i;
	}

	public int delete(Role role) {
		role.setIsemploy(2);
		role.setBaseInfo();
		int i = roleMapper.delete(role);
		return i;
	}

	public int batchdelete(Role role) {
		role.setIsemploy(2);
		role.setBaseInfo();
		int i = roleMapper.batchdelete(role);
		return i;
	}

	public Integer queryTotal(Role role) {
		Integer totalCount = roleMapper.queryTotal(role);
		return totalCount;
	}

}
