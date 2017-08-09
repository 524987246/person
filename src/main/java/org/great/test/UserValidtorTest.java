package org.great.test;

import java.util.List;

import javax.validation.constraints.Size;

import org.great.util.beanValidtor.ListNotHasNull;
import org.hibernate.validator.constraints.Length;

public class UserValidtorTest {
	@Length(min=5,message="名称长度不得小于5个字符")
	private String name;
	@ListNotHasNull(message="list集合不能有null元素")
	private List<String> list;
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

}
