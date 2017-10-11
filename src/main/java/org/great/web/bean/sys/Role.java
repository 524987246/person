package org.great.web.bean.sys;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 角色bean
 * 
 * @author xiej
 * @date 2016-12-27
 * @since 1.0
 */
@Component
@Scope("prototype")
public class Role extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6010644101155550125L;
	/**
	 * 名称
	 */
	private String name;

	/**
	 * 菜单集合
	 */
	private List<Menu> menulist;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Menu> getMenulist() {
		return menulist;
	}

	public void setMenulist(List<Menu> menulist) {
		this.menulist = menulist;
	}

	@Override
	public String toString() {
		return "Dept [name=" + name + "]";
	}

}
