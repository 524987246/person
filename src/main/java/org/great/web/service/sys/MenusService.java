package org.great.web.service.sys;

import java.util.List;

import javax.annotation.Resource;

import org.great.web.bean.buz.Menus;
import org.great.web.mapper.sys.MenusMapper;
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
