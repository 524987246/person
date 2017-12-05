package org.great.web.test;

import java.io.Serializable;
import java.util.Date;

/**
 * buzEntity
 * 
 * @author xiejun
 * @version 2017-06-13
 */
public class BuzCompany implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6768805912349655023L;
	private String name; // 企业名称
	private Date monitorDate; // 监控费到期时间
	private String linkman; // 联系人
	private String phone; // 联系电话
	private String id;// 企业id(修改记录时插入日志表所用)
	private String isMonitor;// 是否有监控服务 0 有 1没有
	private String parentIds;// 是否有监控服务 0 有 1没有
	private String parentId;// 是否有监控服务 0 有 1没有
	private String delFlag;
	private BuzCompany parentCompany;//父类公司对象

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getMonitorDate() {
		return monitorDate;
	}

	public void setMonitorDate(Date monitorDate) {
		this.monitorDate = monitorDate;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsMonitor() {
		return isMonitor;
	}

	public void setIsMonitor(String isMonitor) {
		this.isMonitor = isMonitor;
	}

	public BuzCompany getParentCompany() {
		return parentCompany;
	}

	public void setParentCompany(BuzCompany parentCompany) {
		this.parentCompany = parentCompany;
	}

}