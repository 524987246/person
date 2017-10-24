package org.great.web.bean.sys;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 菜单bean
 * 
 * @author xiej
 * @date 2016-12-27
 * @since 1.0
 */
@Component
@Scope("prototype")
public class MenuOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4512116367911422313L;

	private Long id;

	private List<MenuOrder> children;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<MenuOrder> getChildren() {
		return children;
	}

	public void setChildren(List<MenuOrder> children) {
		this.children = children;
	}

}
