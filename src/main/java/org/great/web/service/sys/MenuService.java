package org.great.web.service.sys;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.great.web.bean.sys.Menu;
import org.great.web.bean.sys.MenuOrder;
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
		Menu menu = menuMapper.get(id);
		return menu;
	}

	public List<Menu> findList(Menu menu) {
		List<Menu> list = menuMapper.findList(menu);
		return list;
	}

	@Transactional(readOnly = false)
	public int save(Menu menu) {
		menu.setBaseInfo();
		int i = menuMapper.save(menu);
		return i;
	}

	@Transactional(readOnly = false)
	public int update(Menu menu) {
		menu.setBaseInfo();
		int i = menuMapper.update(menu);
		return i;
	}

	@Transactional(readOnly = false)
	public int delete(Menu menu) {
		menu.setIsemploy(2);
		menu.setBaseInfo();
		int i = menuMapper.delete(menu);
		return i;
	}

	@Transactional(readOnly = false)
	public int batchdelete(Menu menu) {
		menu.setIsemploy(2);
		menu.setBaseInfo();
		int i = menuMapper.batchdelete(menu);
		return i;
	}

	@Transactional(readOnly = false)
	public Integer queryTotal(Menu menu) {
		Integer totalCount = menuMapper.queryTotal(menu);
		return totalCount;
	}

	@Transactional(readOnly = false)
	public void ordersave(List<MenuOrder> menuOrderlist) {
		List<Menu> list = new ArrayList<Menu>();
		int i = 0;
		for (MenuOrder menuOrder : menuOrderlist) {
			Menu menu = get(menuOrder.getId());
			menu.setOrderNum(i + "");
			i += 10;
			menu.setParentId(0L);
			list.add(menu);
			if (menuOrder.getChildren() != null && menuOrder.getChildren().size() > 0) {
				setMenuChild(menu, menuOrder.getChildren(), list);
			}
		}
		for (Menu menu : list) {
			update(menu);
			// System.out.println(menu);
		}
	}

	private void setMenuChild(Menu parentMenu, List<MenuOrder> list2, List<Menu> mainlist) {
		int i = 10;
		List<Menu> childlist = parentMenu.getChildlist();
		for (MenuOrder menuOrder : list2) {
			Menu menu = get(menuOrder.getId());
			menu.setOrderNum(i + "");
			childlist.add(menu);
			menu.setParentId(parentMenu.getId());
			i += 10;
			mainlist.add(menu);
			if (menuOrder.getChildren() != null && menuOrder.getChildren().size() > 0) {
				setMenuChild(menu, menuOrder.getChildren(), mainlist);
			}
		}
	}

}
