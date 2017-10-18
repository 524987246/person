package org.great.web.bean.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mysql.fabric.xmlrpc.base.Array;

/**
 * 菜单bean
 * 
 * @author xiej
 * @date 2016-12-27
 * @since 1.0
 */
@Component
@Scope("prototype")
public class Menu extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7220703854469704132L;
	/**
	 * 名称
	 */
	@NotEmpty(message = "名称不能为空")
	private String name;
	/**
	 * 路径
	 */
	private String url;
	/**
	 * 父类id
	 */
	private Long parentId;
	/**
	 * 授权(多个用逗号分隔如：user:list,user:create)
	 */
	private String perms;
	/**
	 * 类型 0：目录 1：菜单 2：按钮
	 */
	private String type;
	/**
	 * 菜单图标
	 */
	private String icon;
	/**
	 * 排序
	 */
	private String orderNum;

	private List<Menu> childlist = new ArrayList<Menu>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	@Override
	public String toString() {
		return "Menu [name=" + name + ", url=" + url + ", parentId=" + parentId + ", perms=" + perms + ", type=" + type
				+ ", icon=" + icon + ", orderNum=" + orderNum + "]";
	}

	public List<Menu> getChildlist() {
		return childlist;
	}

	public void setChildlist(List<Menu> childlist) {
		this.childlist = childlist;
	}

}
