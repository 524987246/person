package org.great.web.bean.sys;

import java.util.Date;

import org.great.util.myutil.MyStringUtils;
import org.great.web.bean.User;

public class BaseBean {
	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 当前页
	 */
	private Integer page_new;
	/**
	 * 显示个数
	 */
	private Integer page_size;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 创建者
	 */
	private User createBy;
	/**
	 * 创建日期
	 */
	private String createDate;
	/**
	 * 查询使用,开始时间
	 */
	private Date queryBeginDate;
	/**
	 * 查询使用,结束时间
	 */
	private Date queryEndDate;
	/**
	 * 更新者
	 */
	private User updateBy;
	/**
	 * 更新日期
	 */
	private String updateDate;
	/**
	 * 删除标记（1：正常；2：删除；3：审核）
	 */
	private Integer isemploy;

	public Long getId() {
		return id;
	}

	public void setSid(Long id) {
		this.id = id;
	}

	public Integer getPage_new() {
		return page_new;
	}

	public void setPage_new(Integer page_new) {
		this.page_new = page_new;
	}

	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Date getQueryBeginDate() {
		return queryBeginDate;
	}

	public void setQueryBeginDate(Date queryBeginDate) {
		this.queryBeginDate = queryBeginDate;
	}

	public Date getQueryEndDate() {
		return queryEndDate;
	}

	public void setQueryEndDate(Date queryEndDate) {
		this.queryEndDate = queryEndDate;
	}

	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getIsemploy() {
		return isemploy;
	}

	public void setIsemploy(Integer isemploy) {
		this.isemploy = isemploy;
	}

	/**
	 * 设置基础信息(创建人员,更新人员,创建时间,更新时间)
	 */
	protected void setBaseInfo() {
		if (this.id == null) {
			boolean bo=MyStringUtils.isEmpty(createDate);
			if(bo){
				MyStringUtils.isDateTime(createDate,"%Y-%m-%d");
			}
		}
	}

}
