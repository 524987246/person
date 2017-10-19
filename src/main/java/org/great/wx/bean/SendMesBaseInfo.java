package org.great.wx.bean;

public class SendMesBaseInfo {
	private String value;// 值
	private String color = "#173177";// 字体颜色

	public SendMesBaseInfo() {
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}