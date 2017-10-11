package org.great.web.controller.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.great.web.bean.sys.Role;
import org.great.web.service.sys.RoleService;
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
@RequestMapping("/Reception/sys")
public class RoleController {
	@Resource
	private RoleService roleService;

	/**
	 * 获取页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/role.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String to() {
		PageHelper.startPage(1, 2);
		return "jsp/sys/role";
	}

	/**
	 * 获取数据
	 * 
	 * 
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> list(@RequestBody Role role, HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(role.getPage_new(), role.getPage_size());
		List<Role> list = roleService.findList(role);
		PageInfo page = new PageInfo(list);
		map.put("page", page);
		role.setPageInfo(page.getTotal());
		map.put("obj", role);
		return map;
	}
}
