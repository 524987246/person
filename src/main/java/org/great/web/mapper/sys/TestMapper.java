package org.great.web.mapper.sys;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.great.web.bean.buz.WebError;

/**
 * 用户mapper
 * 
 * @author 谢军
 * 
 */
public interface TestMapper {

	List<Map> find1(Map<String, Object> map);

	List<Map> find2(Map<String, Object> map);
}
