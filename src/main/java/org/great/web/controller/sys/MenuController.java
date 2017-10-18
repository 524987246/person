package org.great.web.controller.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.great.util.beanValidtor.ValidtorUtil;
import org.great.util.myutil.MyStringUtils;
import org.great.web.bean.sys.Dept;
import org.great.web.bean.sys.Menu;
import org.great.web.service.sys.DeptService;
import org.great.web.service.sys.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 常见web错误controller
 * 
 * @author xiej
 * @date 2016-12-27 2017-3-21
 * @since 2.0
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
	public String to() {
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
		PageHelper.startPage(menu.getPage_new(), menu.getPage_size());
		List<Menu> list = menuService.findList(menu);
		PageInfo<Menu> page = new PageInfo<Menu>(list);
		map.put("page", page);
		menu.setPageInfo(page.getTotal());
		map.put("obj", menu);
		return map;
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

	@RequiresPermissions("sys:menu:update")
	@RequestMapping(value = "update.html", produces = "text/html;charset=UTF-8")
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
}
