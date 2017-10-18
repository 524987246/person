package org.great.wx.bean;

public class MessageTemplate {

	private String title = "订阅模板消息";
	private String primary_industry = "";
	private String deputy_industry = "";
	private String example = "";
	private String content = "{{result.DATA} }";

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrimary_industry() {
		return primary_industry;
	}

	public void setPrimary_industry(String primary_industry) {
		this.primary_industry = primary_industry;
	}

	public String getDeputy_industry() {
		return deputy_industry;
	}

	public void setDeputy_industry(String deputy_industry) {
		this.deputy_industry = deputy_industry;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
