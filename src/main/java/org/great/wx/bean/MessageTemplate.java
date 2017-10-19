package org.great.wx.bean;

public class MessageTemplate {

	private SendMesBaseInfo first = new SendMesBaseInfo();
	private SendMesBaseInfo keyword1 = new SendMesBaseInfo();
	private SendMesBaseInfo keyword2 = new SendMesBaseInfo();
	private SendMesBaseInfo remark = new SendMesBaseInfo();

	public SendMesBaseInfo getFirst() {
		return first;
	}

	public void setFirst(SendMesBaseInfo first) {
		this.first = first;
	}

	public SendMesBaseInfo getKeyword1() {
		return keyword1;
	}

	public void setKeyword1(SendMesBaseInfo keyword1) {
		this.keyword1 = keyword1;
	}

	public SendMesBaseInfo getKeyword2() {
		return keyword2;
	}

	public void setKeyword2(SendMesBaseInfo keyword2) {
		this.keyword2 = keyword2;
	}

	public SendMesBaseInfo getRemark() {
		return remark;
	}

	public void setRemark(SendMesBaseInfo remark) {
		this.remark = remark;
	}

}
