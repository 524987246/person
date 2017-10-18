package org.great.web.service.sys;

import java.util.List;

import javax.annotation.Resource;

import org.great.web.bean.sys.Menu;
import org.great.web.mapper.sys.MenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author xiejun
 * 
 */
@Service
@Transactional(readOnly = true)
public class MenuService {
	@Resource
	private MenuMapper menuMapper;

	public Menu get(Long id) {
		Menu Menu = menuMapper.get(id);
		return Menu;
	}

	public List<Menu> findList(Menu menu) {
		List<Menu> list = menuMapper.findList(menu);
		return list;
	}

	public int save(Menu menu) {
		menu.setBaseInfo();
		int i = menuMapper.save(menu);
		return i;
	}

	public int update(Menu menu) {
		menu.setBaseInfo();
		int i = menuMapper.update(menu);
		return i;
	}

	public int delete(Menu menu) {
		menu.setIsemploy(2);
		menu.setBaseInfo();
		int i = menuMapper.delete(menu);
		return i;
	}

	public int batchdelete(Menu menu) {
		menu.setIsemploy(2);
		menu.setBaseInfo();
		int i = menuMapper.batchdelete(menu);
		return i;
	}

	public Integer queryTotal(Menu menu) {
		Integer totalCount = menuMapper.queryTotal(menu);
		return totalCount;
	}

}
