package org.great.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.great.datasource.TargetDataSource;
import org.great.util.myutil.MyCollectionUtils;
import org.great.web.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户表操作
 * 
 * @author 谢军
 * 
 */
@Service
public class TestService {
	@Autowired
	private TestMapper testMapper;

	// 多个数据库配置
	@TargetDataSource(name = "ds1")
	public List<Map<String, Object>> test1() {
		boolean bo = false;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "2");
		List<Map<String, Object>> list = testMapper.find1(map);
		bo = MyCollectionUtils.listEmpty(list);
		return list;
	}

	// 多个数据库配置
	@TargetDataSource(name = "ds2")
	public List<Map<String, Object>> test2() {
		boolean bo = false;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "1");
		List<Map<String, Object>> list = testMapper.find2(map);
		bo = MyCollectionUtils.listEmpty(list);
		return list;
	}
}
