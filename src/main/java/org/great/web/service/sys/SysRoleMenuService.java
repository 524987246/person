package org.great.web.service.sys;

import java.util.List;

import javax.annotation.Resource;

import org.great.web.bean.sys.RoleMenu;
import org.great.web.bean.sys.SysRole;
import org.great.web.mapper.sys.SysRoleMenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author xiejun
 * 
 */
@Service
@Transactional(readOnly = true)
public class SysRoleMenuService {
	@Resource
	private SysRoleMenuMapper sysRoleMenuMapper;

	public RoleMenu get(Long id) {
		RoleMenu roleMenu = sysRoleMenuMapper.get(id);
		return roleMenu;
	}

	public List<RoleMenu> findList(RoleMenu roleMenu) {
		List<RoleMenu> list = sysRoleMenuMapper.findList(roleMenu);
		return list;
	}

	@Transactional(readOnly = false)
	public int save(RoleMenu roleMenu) {
		roleMenu.setBaseInfo();
		int i = sysRoleMenuMapper.save(roleMenu);
		return i;
	}

	@Transactional(readOnly = false)
	public int update(RoleMenu roleMenu) {
		roleMenu.setBaseInfo();
		int i = sysRoleMenuMapper.update(roleMenu);
		return i;
	}

	@Transactional(readOnly = false)
	public int delete(RoleMenu roleMenu) {
		roleMenu.setIsemploy(2);
		roleMenu.setBaseInfo();
		int i = sysRoleMenuMapper.delete(roleMenu);
		return i;
	}

	@Transactional(readOnly = false)
	public int batchdelete(RoleMenu roleMenu) {
		roleMenu.setIsemploy(2);
		roleMenu.setBaseInfo();
		int i = sysRoleMenuMapper.batchdelete(roleMenu);
		return i;
	}

	@Transactional(readOnly = false)
	public void updateRoleMenu(SysRole sysRole) {
		// 删除角色的所有菜单,重新添加
		RoleMenu roleMenu = new RoleMenu();
		roleMenu.setRoleId(sysRole.getId());
		roleMenu.setIsemploy(2);
		roleMenu.setBaseInfo();
		sysRoleMenuMapper.deleteByRoleMenu(roleMenu);
		// 重新添加菜单
		List<Long> list = sysRole.getList();
		if (list != null) {
			roleMenu.setIsemploy(1);
			for (Number l : list) {
				roleMenu.setMenuId(l.longValue());
				sysRoleMenuMapper.save(roleMenu);
			}
		}
	}

	@Transactional(readOnly = false)
	public int deleteByRoleMenu(RoleMenu roleMenu) {
		roleMenu.setIsemploy(2);
		roleMenu.setBaseInfo();
		int i = sysRoleMenuMapper.deleteByRoleMenu(roleMenu);
		return i;
	}

	@Transactional(readOnly = false)
	public Long queryTotal(RoleMenu roleMenu) {
		Long totalCount = sysRoleMenuMapper.queryTotal(roleMenu);
		return totalCount;
	}
}
