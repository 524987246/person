package org.great.web.bean.sys;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
	@NotEmpty(message = "用户名不能为空")
	private String name;
	@Size(min = 8, max = 16, message = "密码应在8-16位")
	private String password;
	private String phone;
	private Integer grade;
	/**
	 * 状态:1启用2禁用
	 */
	private Integer isemploy;
	private Integer role;

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

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", phone=" + phone + ", grade=" + grade
				+ ", isemploy=" + isemploy + ", role=" + role + "]";
	}

}
