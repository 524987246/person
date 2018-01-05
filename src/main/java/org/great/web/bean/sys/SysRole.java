//hello SysRole

package org.great.web.bean.sys;

import java.io.Serializable;

/**
 * 
 * @author xiej
 * @since 1.0
 */
public class SysRole extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4346416246272394349L;
	/**
	 * 名称
	 */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*@Override
	public String toString() {
		return "DbName [" + "name=" + name + "]";
	}*/

}
