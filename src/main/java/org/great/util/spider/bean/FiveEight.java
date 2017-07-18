package org.great.util.spider.bean;

/**
 * 58同城数据对象
 * 
 * @author Administrator
 * 
 */
public class FiveEight {
	/**
	 * 标题
	 */
	private String titlename;

	/**
	 * 要求
	 */
	private String requirement;

	/**
	 * 公司
	 */
	private String company;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 时间
	 */
	private String time;

	public String getTitlename() {
		return titlename;
	}

	public void setTitlename(String titlename) {
		this.titlename = titlename;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "FiveEight [titlename=" + titlename + ", requirement="
				+ requirement + ", company=" + company + ", address=" + address
				+ ", time=" + time + "]";
	}

}
