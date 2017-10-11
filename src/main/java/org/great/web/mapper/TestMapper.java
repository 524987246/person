package org.great.web.mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户mapper
 * 
 * @author 谢军
 * 
 */
public interface TestMapper {

	List<Map<String, Object>> find1(Map<String, Object> map);

	List<Map<String, Object>> find2(Map<String, Object> map);
}
