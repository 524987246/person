package org.great.web.bean.sys;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 角色菜单bean
 * 
 * @author xiej
 * @date 2016-12-27
 * @since 1.0
 */
@Component
@Scope("prototype")
public class RoleMenu extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8349363760183393522L;
	/**
	 * 角色id
	 */
	private Long roleId;
	/**
	 * 菜单id
	 */
	private Long menuId;

	private Menu menu;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
