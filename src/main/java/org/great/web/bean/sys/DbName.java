package org.great.web.bean.sys;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 数据库Bean
 * 
 * @author xiej
 * @date 2016-12-27
 * @since 1.0
 */
@Component
@Scope("prototype")
public class DbName implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8540662529458064782L;
	/**
	 * 主键id
	 */
	private Integer sid;
	/**
	 * 数据库类型
	 */
	private String stype;
	/**
	 * 数据库名称
	 */
	private String sname;
	/**
	 * 数据库账号
	 */
	private String username;
	/**
	 * 数据库密码
	 */
	private String userpwd;
	/**
	 * 驱动路径
	 */
	private String sdriver;
	/**
	 * 路径
	 */
	private String surl;
	/**
	 * 表名
	 */
	private String tbname;
	/**
	 * 表名集合
	 */
	private String[] tbnames;
	/**
	 * 状态:1启用2禁用
	 */
	private Integer isemploy;
	/**
	 * 当前页数
	 */
	private int page_new;
	/**
	 * 每页显示的条数
	 */
	private int page_num;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSdriver() {
		return sdriver;
	}

	public void setSdriver(String sdriver) {
		this.sdriver = sdriver;
	}

	public Integer getIsemploy() {
		return isemploy;
	}

	public void setIsemploy(Integer isemploy) {
		this.isemploy = isemploy;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getSurl() {
		return surl;
	}

	public void setSurl(String surl) {
		this.surl = surl;
	}

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	public String getTbname() {
		return tbname;
	}

	public void setTbname(String tbname) {
		this.tbname = tbname;
	}

	public String[] getTbnames() {
		return tbnames;
	}

	public void setTbnames(String[] tbnames) {
		this.tbnames = tbnames;
	}

	public int getPage_new() {
		return page_new;
	}

	public void setPage_new(int page_new) {
		this.page_new = page_new;
	}

	public int getPage_num() {
		return page_num;
	}

	public void setPage_num(int page_num) {
		this.page_num = page_num;
	}

	@Override
	public String toString() {
		return "DbName [sid=" + sid + ", stype=" + stype + ", sname=" + sname
				+ ", username=" + username + ", userpwd=" + userpwd
				+ ", sdriver=" + sdriver + ", surl=" + surl + ", tbname="
				+ tbname + ", tbnames=" + Arrays.toString(tbnames)
				+ ", isemploy=" + isemploy + ", page_new=" + page_new
				+ ", page_num=" + page_num + "]";
	}

}
