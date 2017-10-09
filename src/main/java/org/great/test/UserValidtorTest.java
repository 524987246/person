package org.great.test;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.Size;

import org.great.util.beanValidtor.ListNotHasNull;
import org.great.util.beanValidtor.MapNotOne;
import org.hibernate.validator.constraints.Length;

public class UserValidtorTest {
	@Length(min = 5, message = "名称长度不得小于5个字符")
	private String name;
	@ListNotHasNull(message = "list集合不能有null元素")
	private List<String> list;
	@MapNotOne(message = "map集合不能为空且不能含有1")
	private Map<String, String> map;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

}
