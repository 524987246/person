package org.great.web.mapper.buz;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.great.web.bean.buz.WebError;

/**
 * 用户mapper
 * 
 * @author 谢军
 * 
 */
public interface WebErrorMapper {

	/**
	 * 查找
	 * 
	 * @param
	 * @return
	 */
	List<WebError> findWebErrorByWebError(@Param("webError") WebError webError,
			@Param("page_new") int page_new, @Param("page_num") int page_num);

	/**
	 * 删除
	 * 
	 * @param sid
	 *            主键id
	 * @param isemploy
	 *            状态
	 * @return
	 */
	int delWebErrorBySid(@Param("sid") Integer sid,
			@Param("isemploy") Integer isemploy);

	/**
	 * 添加
	 * 
	 * @param webError
	 *            对象
	 * @return
	 */
	int insertWebError(WebError webError);

	/**
	 * 更新
	 * 
	 * @param webError对象
	 * @return
	 */
	int updateWebErrorBySid(WebError webError);
}
