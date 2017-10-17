package org.great.web.bean.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Size;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.great.util.myutil.MyCollectionUtils;
import org.great.util.myutil.MyStringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mysql.fabric.xmlrpc.base.Array;

/**
 * 用户bean
 * 
 * @author 谢军
 * 
 */
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

	public SimpleAuthorizationInfo getAuthorizationInfo() {
		return authorizationInfo;
	}

	public void setAuthorizationInfo(SimpleAuthorizationInfo authorizationInfo) {
		this.authorizationInfo = authorizationInfo;
	}
}
