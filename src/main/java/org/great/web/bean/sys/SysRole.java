//hello SysRole

package org.great.web.bean.sys;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author xiej
 * @since 1.0
 */
public class SysRole extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4346416246272394349L;
	/**
	 * 名称
	 */
	private String name;

	/**
	 * 菜单集合
	 */
	private List<RoleMenu> roleMenulist;
	/**
	 * 菜单id集合
	 */
	private List<Long> list;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RoleMenu> getRoleMenulist() {
		return roleMenulist;
	}

	public void setRoleMenulist(List<RoleMenu> roleMenulist) {
		this.roleMenulist = roleMenulist;
	}

	public List<Long> getList() {
		return list;
	}

	public void setList(List<Long> list) {
		this.list = list;
	}

	/*
	 * @Override public String toString() { return "DbName [" + "name=" + name +
	 * "]"; }
	 */

}
