package org.great.web.service.sys;

import java.util.List;

import javax.annotation.Resource;

import org.great.web.bean.sys.RoleMenu;
import org.great.web.bean.sys.SysRole;
import org.great.web.mapper.sys.SysRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author xiejun
 * 
 */
@Service
@Transactional(readOnly = true)
public class SysRoleService {
	@Resource
	private SysRoleMapper sysRoleMapper;
	@Resource
	private SysRoleMenuService sysRoleMenuService;

	public SysRole get(Long id) {
		SysRole sysRole = sysRoleMapper.get(id);
		return sysRole;
	}

	public List<SysRole> findList(SysRole sysRole) {
		List<SysRole> list = sysRoleMapper.findList(sysRole);
		return list;
	}

	@Transactional(readOnly = false)
	public int save(SysRole sysRole) {
		sysRole.setBaseInfo();
		int i = sysRoleMapper.save(sysRole);
		sysRoleMenuService.updateRoleMenu(sysRole);
		return i;
	}

	@Transactional(readOnly = false)
	public int update(SysRole sysRole) {
		sysRole.setBaseInfo();
		int i = sysRoleMapper.update(sysRole);
		sysRoleMenuService.updateRoleMenu(sysRole);
		return i;
	}

	@Transactional(readOnly = false)
	public int delete(SysRole sysRole) {
		sysRole.setIsemploy(2);
		sysRole.setBaseInfo();
		int i = sysRoleMapper.delete(sysRole);
		return i;
	}

	@Transactional(readOnly = false)
	public int batchdelete(SysRole sysRole) {
		sysRole.setIsemploy(2);
		sysRole.setBaseInfo();
		int i = sysRoleMapper.batchdelete(sysRole);
		return i;
	}

	@Transactional(readOnly = false)
	public Long queryTotal(SysRole sysRole) {
		Long totalCount = sysRoleMapper.queryTotal(sysRole);
		return totalCount;
	}
}
