package org.great.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.web.bean.Menus;
/**
 * 用户mapper
 * @author 谢军
 *
 */
public interface MenusMapper {
	
	/**
	 * 查找功能
	 * @param nparentid 父类功能id
	 * @return
	 */
	List<Menus> findMenus(@Param("nparentid")Integer nparentid);
}
