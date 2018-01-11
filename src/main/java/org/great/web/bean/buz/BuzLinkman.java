//hello BuzLinkman

package org.great.web.bean.buz;

import java.io.Serializable;
import org.great.web.bean.sys.BaseBean;
/**
 * 
 * @author xiej
 * @since 1.0
 */
public class BuzLinkman  extends BaseBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7934271663268787460L;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 部门
	 */
	private String dept;
	/**
	 * 手机
	 */
	private String phone;
	/**
	 * 年龄
	 */
	private String age;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "DbName ["+
		"name=" + name + 
		"dept=" + dept + 
		"phone=" + phone + 
		"age=" + age + 
		"]";
	}

}
