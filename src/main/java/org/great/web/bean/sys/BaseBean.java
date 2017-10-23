package org.great.web.bean.sys;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.great.config.BaseResoure;
import org.great.util.beanValidtor.ValidtorUtil;
import org.great.util.myutil.MyDateUtils;
import org.great.util.myutil.MyStringUtils;
import org.great.util.myutil.MyUserUtils;

public class BaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6235136741075310727L;
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
	 * 页面总数
	 */
	private Integer pageCount;
	/**
	 * 总个数
	 */
	private long totalCount;
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
	private String queryBeginDate;
	/**
	 * 查询使用,结束时间
	 */
	private String queryEndDate;
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
	/**
	 * 批量操作时的id集合(批量删除或更新)
	 */
	private List<Long> batchId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getQueryBeginDate() {
		return queryBeginDate;
	}

	public void setQueryBeginDate(String queryBeginDate) {
		this.queryBeginDate = queryBeginDate;
	}

	public String getQueryEndDate() {
		return queryEndDate;
	}

	public void setQueryEndDate(String queryEndDate) {
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

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 设置基础信息(创建人员,更新人员,创建时间,更新时间)
	 */
	public void setBaseInfo() {
		Long id = MyUserUtils.getLoginUser().getId();
		if (this.id == null) {
			boolean bo = MyStringUtils.isEmpty(this.createDate);
			if (bo) {
				bo = MyStringUtils.isDateTime(this.createDate, "yyyy-MM-dd");
			}
			if (!bo) {
				this.createDate = MyDateUtils.dateToString(null);
			}
			// 获取当前用户
			if (this.createBy == null || this.createBy.getId() == null) {
				this.createBy = new User();
				this.createBy.setId(id);
			}
		}
		if (this.updateBy == null) {
			this.updateBy = new User();
		}
		this.updateDate = MyDateUtils.dateToString(null);
		// 获取当前用户
		this.updateBy.setId(id);
	}

	/**
	 * 设置查询时间
	 * 
	 * @param time
	 *            时间间隔
	 * @param map
	 */
	public void setQueryDate(int time, Map<String, Object> map) {
		map = MyDateUtils.setQueryDate(this.queryBeginDate, this.queryEndDate, time, map);
		this.queryBeginDate = map.get("begintime").toString();
		this.queryEndDate = map.get("endtime").toString();
	}

	public void setPageInfo(Long totalCount) {
		if (this.page_new == null) {
			this.page_new = 1;
		}
		if (this.page_size == null) {
			this.page_size = BaseResoure.DEFALUT_PAGE_SIZE;
		}
		if (totalCount != null) {
			this.totalCount = totalCount;
		}
		if (this.totalCount == 0) {
			return;
		}
		this.pageCount = (int) Math.ceil((double) this.totalCount / this.page_size);
		int total = this.page_new * this.page_size;
		if (total > this.totalCount) {
			this.page_new = 1;
		}
	}

	public List<Long> getBatchId() {
		return batchId;
	}

	public void setBatchId(List<Long> batchId) {
		this.batchId = batchId;
	}
}
