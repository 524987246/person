package org.great.web.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.great.util.beanValidtor.ValidtorUtil;
import org.great.util.myutil.MyStringUtils;
import org.great.web.bean.sys.Menu;
import org.great.web.bean.sys.MenuOrder;
import org.great.web.service.sys.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author xiejun
 * @date 2017-10-23 09:52:10
 * @since 3.0
 */
@Controller
@RequestMapping("/Reception/sys/menu/")
public class MenuController {
	@Resource
	private MenuService menuService;

	/**
	 * 获取页面
	 * 
	 * @return
	 */
	@RequiresPermissions("sys:menu:view")
	@RequestMapping(value = "to.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String to(Model model) {
		List<Menu> list = menuService.findList(new Menu());
		String html = getMenuHtml(list);
		model.addAttribute("menuHtml", html);
		model.addAttribute("list", list);
		return "newjsp/sys/menu-list";
	}

	/**
	 * 获取数据
	 * 
	 * 
	 */
	@RequiresPermissions("sys:menu:view")
	@RequestMapping(value = "list.html", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> info(@RequestBody Menu menu, HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 菜单管理,展示所有菜单
		// PageHelper.startPage(menu.getPage_new(), menu.getPage_size());
		List<Menu> list = menuService.findList(menu);
		map.put("list", list);
		map.put("menu", menu);
		return map;
	}

	/**
	 * 获取数据
	 * 
	 * 
	 */
	@RequiresPermissions("sys:menu:view")
	@RequestMapping(value = "one.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String one(Menu menu, HttpServletRequest request, Model model) {
		if (menu.getId() != null) {
			menu = menuService.get(menu.getId());
		}
		model.addAttribute("menu", menu);
		return "newjsp/sys/menu-one";
	}

	@RequiresPermissions("sys:menu:save")
	@RequestMapping(value = "save.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String save(@RequestBody Menu menu, HttpServletRequest request) {
		String str = ValidtorUtil.validbean(menu);
		if (MyStringUtils.isEmpty(str)) {
			return str;
		}
		int i = menuService.save(menu);
		str = i > 0 ? "保存成功" : "保存失败";
		return str;
	}

	@RequiresPermissions("sys:menu:ordersave")
	@RequestMapping(value = "ordersave.html")
	@ResponseBody
	public String ordersave(@RequestBody List<MenuOrder> menuOrderlist, HttpServletRequest request) {
		// String msg = JSONArray.fromObject(menuOrderlist).toString();
		menuService.ordersave(menuOrderlist);
		return "{\"message\":\"成功\"}";
	}

	@RequiresPermissions("sys:menu:update")
	@RequestMapping(value = "update.html")
	@ResponseBody
	public String update(@RequestBody Menu menu, HttpServletRequest request) {
		String str = ValidtorUtil.validbean(menu);
		if (MyStringUtils.isEmpty(str)) {
			return str;
		}
		int i = menuService.update(menu);
		str = i > 0 ? "修改成功" : "修改失败";
		return str;
	}

	@RequiresPermissions("sys:menu:delete")
	@RequestMapping(value = "del.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delete(@RequestBody Menu menu, HttpServletRequest request) {
		int i = menuService.delete(menu);
		String str = i > 0 ? "删除成功" : "删除失败";
		return str;
	}

	@RequiresPermissions("sys:menu:batchdelete")
	@RequestMapping(value = "batchdelete.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String batchdelete(@RequestBody Menu menu, HttpServletRequest request) {
		int i = menuService.batchdelete(menu);
		String str = i > 0 ? "批量删除成功" : "批量删除失败";
		return str;
	}

	private String getMenuHtml(List<Menu> list) {
		String html = "<div class=\"dd\"><ol class=\"dd-list\">";
		Map<Long, Menu> map = new HashMap<Long, Menu>();
		List<Menu> firstlist = new ArrayList<Menu>();
		for (Menu menu : list) {
			map.put(menu.getId(), menu);
		}
		for (Menu menu : list) {
			Long parentId = menu.getParentId();
			if (parentId != 0L) {
				Menu temp = map.get(menu.getParentId());
				if (temp != null) {
					temp.getChildlist().add(menu);
				}
			} else {
				firstlist.add(menu);
			}
		}
		for (Menu menu : firstlist) {
			html += createChildMenuHtml(menu);
		}
		html += "</ol></div>";
		return html;
	}

	private static String child_template = "<li class=\"dd-item\" data-id=\"ID\">"
			+ "<div class=\"dd-handle\">NAME</div>";

	private String createChildMenuHtml(Menu menu) {
		String html = "";
		// String result = "";
		// String icon = menu.getIcon() == null ? "" : menu.getIcon();
		html += child_template.replaceAll("ID", menu.getId().toString()).replaceAll("NAME", menu.getName());
		if (menu.getChildlist().size() > 0) {
			html += "<ol class=\"dd-list\">";
			for (Menu temp : menu.getChildlist()) {
				html += createChildMenuHtml(temp);
			}
			html += "</ol>";
		}
		html += "</li>";
		return html;
	}
}
