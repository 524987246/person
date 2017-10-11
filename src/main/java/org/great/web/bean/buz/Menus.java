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
public class Menus implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 91491755642823776L;
	/**
	 * 主键id
	 */
	private Integer sid;
	/**
	 * 功能名称
	 */
	private String sname;
	/**
	 * 功能路径
	 */
	private String spath;
	/**
	 * 功能父类id
	 */
	private Integer npartentid;
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
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSpath() {
		return spath;
	}
	public void setSpath(String spath) {
		this.spath = spath;
	}
	public Integer getNpartentid() {
		return npartentid;
	}
	public void setNpartentid(Integer npartentid) {
		this.npartentid = npartentid;
	}
	public Integer getIsemploy() {
		return isemploy;
	}
	public void setIsemploy(Integer isemploy) {
		this.isemploy = isemploy;
	}
	
}
