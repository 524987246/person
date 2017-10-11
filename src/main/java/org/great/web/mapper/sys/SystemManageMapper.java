package org.great.web.mapper.sys;

import java.util.List;

import org.great.web.bean.buz.DbName;

/**
 * 用户mapper
 * 
 * @author 谢军
 * 
 */
public interface SystemManageMapper {

//	/**
//	 * 查找
//	 * 
//	 * @param
//	 * @return
//	 */
//	List<WebError> findWebErrorByWebError(@Param("webError") WebError webError,
//			@Param("page_new") int page_new, @Param("page_num") int page_num);


	List<DbName> getDbName(DbName dbName);
}
