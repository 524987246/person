package org.great.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.great.web.bean.Menus;
import org.great.web.mapper.MenusMapper;
import org.springframework.stereotype.Service;

/**
 * 用户表操作
 * 
 * @author 谢军
 * 
 */
@Service
public class MenusService {
	@Resource
	private MenusMapper menusMapper;

	public List<Menus> findUserByUser(Integer nparentid) {
		List<Menus>list=menusMapper.findMenus(nparentid);
		return list ;
	}
}
