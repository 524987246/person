package org.great.web.bean.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.Size;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.great.util.myutil.MyStringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6964957333569601350L;
	private Long id;
	private String name;
	@NotEmpty(message = "登录名不能为空")
	private String loginName;
	@Size(min = 8, max = 16, message = "密码应在8-16位")
	private String password;
	private String phone;
	private Integer grade;
	/**
	 * 状态:1启用2禁用
	 */
	private Integer isemploy;
	private Integer role;
	private List<Role> rolelist;
	private List<Menu> menulist;
	private Set<String> permlist;
	private boolean init_perm_flag = false;
	private SimpleAuthorizationInfo authorizationInfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getIsemploy() {
		return isemploy;
	}

	public void setIsemploy(Integer isemploy) {
		this.isemploy = isemploy;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public List<Role> getRolelist() {
		return rolelist;
	}

	public void setRolelist(List<Role> rolelist) {
		this.rolelist = rolelist;
	}

	public List<Menu> getMenulist() {
		return menulist;
	}

	public void setMenulist(List<Menu> menulist) {
		this.menulist = menulist;
	}

	public Set<String> getPermlist() {
		initPerm();
		return permlist;
	}

	public SimpleAuthorizationInfo getAuthorizationInfo() {
		return authorizationInfo;
	}

	public void setAuthorizationInfo(SimpleAuthorizationInfo authorizationInfo) {
		this.authorizationInfo = authorizationInfo;
	}

	/**
	 * 初始化权限
	 */
	private void initPerm() {
		if (init_perm_flag) {
			return;
		}
		init_perm_flag = true;
		Set<String> templist = new HashSet<String>();
		boolean bo = (this.menulist != null && this.menulist.size() > 0);
		if (!bo) {
			return;
		}
		for (Menu menu : this.menulist) {
			bo = MyStringUtils.isEmpty(menu.getPerms());
			if (bo) {
				String[] array = menu.getPerms().split(",");
				for (String str : array) {
					if (!templist.contains(str)) {
						templist.add(str);
					}
				}
			}
		}
		this.permlist = templist;
	}

	private String firstMenuHtml;
	private List<String> childHtml;

	public String getFirstMenuHtml() {
		return firstMenuHtml;
	}

	public void setFirstMenuHtml(String firstMenuHtml) {
		this.firstMenuHtml = firstMenuHtml;
	}

	public List<String> getChildHtml() {
		return childHtml;
	}

	public void setChildHtml(List<String> childHtml) {
		this.childHtml = childHtml;
	}

	/**
	 * 生成菜单html
	 */
	public void createMenuHtml(String ctx) {
		if (this.menulist == null || this.menulist.size() == 0) {
			return;
		}
		List<Menu> firstlist = new ArrayList<Menu>();
		Map<Long, Menu> map = new HashMap<Long, Menu>();
		for (int i = 0; i < this.menulist.size(); i++) {
			Menu temp = menulist.get(i);
			if (temp != null && null != temp.getId()) {
				map.put(temp.getId(), temp);
				if (temp.getParentId() == 0L) {
					firstlist.add(temp);
				}
			}

		}
		// 生成一级菜单的html字符串
		String firstMenuHtml = "";
		String template = "<li class=\"navbar-levelone\"><a href=\"javascript:;\">CONTENT</a></li>";
		for (int i = 0; i < firstlist.size(); i++) {
			Menu temp = firstlist.get(i);
			String html = "";
			if (i == 0) {
				html = template.replaceAll("CONTENT", temp.getName()).replace("navbar-levelone",
						"navbar-levelone current");
			} else {
				html = template.replaceAll("CONTENT", temp.getName());
			}
			firstMenuHtml += html;
		}
		this.setFirstMenuHtml(firstMenuHtml);
		// 剩余菜单生成
		for (Menu menu : this.menulist) {
			if (menu.getId() == null) {
				continue;
			}
			if (0L != menu.getParentId() && !"3".equals(menu.getType())) {
				Menu temp = map.get(menu.getParentId());
				if (null != temp) {
					temp.getChildlist().add(menu);
				}
			}
		}
		List<String> childHtml = new ArrayList<String>();
		for (Menu menu : firstlist) {
			// 生成每个一级菜单的所有子菜单
			String result = createHtml(menu, ctx);
			// System.out.println(result);
			childHtml.add(result);
		}
		this.childHtml = childHtml;
	}

	// 循环遍历创建子菜单
	private String createHtml(Menu menu, String ctx) {
		String result = "";
		for (Menu temp : menu.getChildlist()) {
			result += createChildMenuHtml(temp, ctx);
		}
		return result;
	}

	private String template = "<li><a data-href=\"URL\" data-title=\"NAME\" href=\"javascript:void(0)\">NAME</a></li>";
	private String templateMain = "<dl><dt><i class=\"Hui-iconfont\">ICON</i>NAME<i class=\"Hui-iconfont menu_dropdown-arrow\">&#xe6d5;</i></dt><dd><ul>CHILDHTML</ul></dd></dl>";

	private String createChildMenuHtml(Menu menu, String ctx) {
		String html = "";
		String result = "";
		String icon = menu.getIcon() == null ? "" : menu.getIcon();
		result = templateMain.replaceAll("ICON", icon).replaceAll("NAME", menu.getName());
		for (Menu temp : menu.getChildlist()) {
			if (temp.getChildlist().size() > 0) {
				html += "<li>" + createChildMenuHtml(temp, ctx) + "</li>";
			} else {
				html += template.replaceAll("URL", ctx + "/" + temp.getUrl()).replaceAll("NAME", temp.getName());
			}
		}
		result = result.replaceAll("CHILDHTML", html);
		return result;
	}
}
