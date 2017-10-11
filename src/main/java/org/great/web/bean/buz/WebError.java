package org.great.web.bean.buz;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 功能Bean
 * 
 * @author xiej
 * @date 2016-12-27
 * @since 1.0
 */
@Component
@Scope("prototype")
public class WebError implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8130897940463465675L;
	/**
	 * 主键id
	 */
	private Integer sid;
	/**
	 * 错误代码
	 */
	private Integer serrorid;
	/**
	 * 错误名称
	 */
	private String sname;
	/**
	 * 错误原因
	 */
	private String sreason;
	/**
	 * 解决方案
	 */
	private String sresolvent;
	/**
	 * 状态:1启用2禁用
	 */
	private Integer isemploy;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getSerrorid() {
		return serrorid;
	}

	public void setSerrorid(Integer serrorid) {
		this.serrorid = serrorid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSreason() {
		return sreason;
	}

	public void setSreason(String sreason) {
		this.sreason = sreason;
	}

	public String getSresolvent() {
		return sresolvent;
	}

	public void setSresolvent(String sresolvent) {
		this.sresolvent = sresolvent;
	}

	public Integer getIsemploy() {
		return isemploy;
	}

	public void setIsemploy(Integer isemploy) {
		this.isemploy = isemploy;
	}

	@Override
	public String toString() {
		return "WebError [sid=" + sid + ", serrorid=" + serrorid + ", sname="
				+ sname + ", sreason=" + sreason + ", sresolvent=" + sresolvent
				+ ", isemploy=" + isemploy + "]";
	}

}
