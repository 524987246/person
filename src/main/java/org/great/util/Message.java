package org.great.util;

public class Message {
	/**
	 * 传输对象的字符串
	 */
	private String str;
	/**
	 * 当前页数
	 */
	private int page_new;
	/**
	 * 查询的内容
	 */
	private String chainfo;
	/**
	 * 每页显示的条数
	 */
	private int page_num;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public int getPage_new() {
		return page_new;
	}

	public void setPage_new(int page_new) {
		this.page_new = page_new;
	}

	public String getChainfo() {
		return chainfo;
	}

	public void setChainfo(String chainfo) {
		this.chainfo = chainfo;
	}

	public int getPage_num() {
		return page_num;
	}

	public void setPage_num(int page_num) {
		this.page_num = page_num;
	}

	@Override
	public String toString() {
		return "Message [str=" + str + ", page_new=" + page_new + ", chainfo=" + chainfo + ", page_num=" + page_num
				+ "]";
	}

}
