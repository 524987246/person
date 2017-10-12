package org.great.web.bean.sys;

import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UserRole extends BaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5865888170906711902L;
	/**
	 * 角色id
	 */
	private Long roleId;
	/**
	 * 用户id
	 */
	private Long userId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
