package org.great.web.service.sys;

import java.util.List;

import javax.annotation.Resource;

import org.great.web.bean.sys.SysRole;
import org.great.web.bean.sys.User;
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
public class UserRoleService {
	@Resource
	private UserRoleMapper userRoleMapper;

	public UserRole get(Long id) {
		UserRole userRole = userRoleMapper.get(id);
		return userRole;
	}

	public List<UserRole> findList(UserRole userRole) {
		List<UserRole> list = userRoleMapper.findList(userRole);
		return list;
	}

	@Transactional(readOnly = false)
	public int save(UserRole userRole) {
		userRole.setBaseInfo();
		int i = userRoleMapper.save(userRole);
		return i;
	}

	@Transactional(readOnly = false)
	public int update(UserRole userRole) {
		userRole.setBaseInfo();
		int i = userRoleMapper.update(userRole);
		return i;
	}

	@Transactional(readOnly = false)
	public int delete(UserRole userRole) {
		userRole.setIsemploy(2);
		userRole.setBaseInfo();
		int i = userRoleMapper.delete(userRole);
		return i;
	}

	@Transactional(readOnly = false)
	public int batchdelete(UserRole userRole) {
		userRole.setIsemploy(2);
		userRole.setBaseInfo();
		int i = userRoleMapper.batchdelete(userRole);
		return i;
	}

	@Transactional(readOnly = false)
	public Long queryTotal(UserRole userRole) {
		Long totalCount = userRoleMapper.queryTotal(userRole);
		return totalCount;
	}

	@Transactional(readOnly = false)
	public void updateByUser(User user) {
		// 删除所有角色
		UserRole userRole = new UserRole();
		userRole.setIsemploy(2);
		userRole.setBaseInfo();
		userRole.setUserId(user.getId());
		userRoleMapper.deleteByUserRole(userRole);
		// 重新添加
		List<SysRole> list = user.getRolelist();
		if (list != null) {
			userRole.setIsemploy(1);
			for (SysRole sysRole : list) {
				userRole.setRoleId(sysRole.getId());
				userRoleMapper.save(userRole);
			}
		}
	}
}
