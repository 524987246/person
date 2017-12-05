package org.great.web.test;

import java.io.Serializable;
import java.util.Date;

/**
 * buzEntity
 * 
 * @author xiejun
 * @version 2017-05-31
 */
public class BuzCarinfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 493229766135392443L;
	private String carNum; // 车辆号码
	private String id; // 车辆唯一性id
	private BuzCompany buzCompany; // 归属公司
	private Date serviceDate; // 服务费到期时间
	private String linkman; // 联系人
	private String phone; // 联系电话
	private String delFlag;

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BuzCompany getBuzCompany() {
		return buzCompany;
	}

	public void setBuzCompany(BuzCompany buzCompany) {
		this.buzCompany = buzCompany;
	}

	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
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

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}