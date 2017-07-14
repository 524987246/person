package org.great.util.attrUtil;

import java.util.Date;

public class Student {
	private String name;
	private Integer id;
	private Date date;
	private Long age;

	public Student(String name, Integer id, Date date, Long age) {
		super();
		this.name = name;
		this.id = id;
		this.date = date;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", id=" + id + ", date=" + date
				+ ", age=" + age + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

}
