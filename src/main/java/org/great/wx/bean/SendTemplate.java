package org.great.wx.bean;

public class SendTemplate {
	private String template_id = "q0Tqox93zAkXGPRRjem0BYchWBwP5YE_0nF7K4qTntQ";
	private String touser="o3BO7uObiwTgPfQ5NTh9fQJed_7I";
	private String url = "";
	private String miniprogram = "";
	private MessageTemplate data = new MessageTemplate();

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMiniprogram() {
		return miniprogram;
	}

	public void setMiniprogram(String miniprogram) {
		this.miniprogram = miniprogram;
	}

	public MessageTemplate getData() {
		return data;
	}

	public void setData(MessageTemplate data) {
		this.data = data;
	}

}
