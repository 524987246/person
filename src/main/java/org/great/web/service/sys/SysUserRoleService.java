package org.great.web.service.sys;

import java.util.List;

import javax.annotation.Resource;

import org.great.web.bean.sys.UserRole;
import org.great.web.mapper.sys.UserRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author xiejun
 * 
 */
@Service
@Transactional(readOnly = true)
public class SysUserRoleService {
	@Resource
	private UserRoleMapper userRoleMapper;

	public UserRole get(Long id) {
		UserRole sysUserRole = userRoleMapper.get(id);
		return sysUserRole;
	}

	public List<UserRole> findList(UserRole sysUserRole) {
		List<UserRole> list = userRoleMapper.findList(sysUserRole);
		return list;
	}

	@Transactional(readOnly = false)
	public int save(UserRole sysUserRole) {
		sysUserRole.setBaseInfo();
		int i = userRoleMapper.save(sysUserRole);
		return i;
	}

	@Transactional(readOnly = false)
	public int update(UserRole sysUserRole) {
		sysUserRole.setBaseInfo();
		int i = userRoleMapper.update(sysUserRole);
		return i;
	}

	@Transactional(readOnly = false)
	public int delete(UserRole sysUserRole) {
		sysUserRole.setIsemploy(2);
		sysUserRole.setBaseInfo();
		int i = userRoleMapper.delete(sysUserRole);
		return i;
	}

	@Transactional(readOnly = false)
	public int batchdelete(UserRole sysUserRole) {
		sysUserRole.setIsemploy(2);
		sysUserRole.setBaseInfo();
		int i = userRoleMapper.batchdelete(sysUserRole);
		return i;
	}

	@Transactional(readOnly = false)
	public Long queryTotal(UserRole sysUserRole) {
		Long totalCount = userRoleMapper.queryTotal(sysUserRole);
		return totalCount;
	}
}
