package org.great.web.bean.sys;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 部门管理
 * 
 * @author xiej
 * @date 2016-12-27
 * @since 1.0
 */
@Component
@Scope("prototype")
public class Dept extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7868942919987195607L;
	/**
	 * 支付名称
	 */
	private String name;
	/**
	 * 支付名称
	 */
	private Long parentId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
