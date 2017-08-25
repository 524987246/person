package org.great.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.great.datasource.TargetDataSource;
import org.great.util.myutil.MyListUtil;
import org.great.web.bean.Menus;
import org.great.web.mapper.MenusMapper;
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
	public boolean test1() {
		boolean bo = false;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "2");
		List<Map> list = testMapper.find1(map);
		bo = MyListUtil.listEmpty(list);
		return bo;
	}

	// 多个数据库配置
	@TargetDataSource(name = "ds2")
	public boolean test2() {
		boolean bo = false;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "1");
		List<Map> list = testMapper.find2(map);
		bo = MyListUtil.listEmpty(list);
		return bo;
	}
}
