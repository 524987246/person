package org.great.web.test;

import java.io.Serializable;

public class OldCompanyInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -142175499115325168L;
	private String list;
	private String list2;
	private String list3;
	private String companyName;
	private String companyId;
	private String companyStatus;
	private String parentid;

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public String getList2() {
		return list2;
	}

	public void setList2(String list2) {
		this.list2 = list2;
	}

	public String getList3() {
		return list3;
	}

	public void setList3(String list3) {
		this.list3 = list3;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyStatus() {
		return companyStatus;
	}

	public void setCompanyStatus(String companyStatus) {
		this.companyStatus = companyStatus;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

}
