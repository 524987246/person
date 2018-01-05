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
import org.great.util.myutil.MyResult;
import org.great.web.bean.sys.SysRole;
import org.great.web.bean.sys.User;
import org.great.web.service.sys.SysRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author xiejun
 * @date 2017-10-23 09:52:10
 * @since 3.0
 */
@Controller
@RequestMapping("/Reception/sys/role")
public class SysRoleController {
	@Resource
	private SysRoleService sysRoleService;

	/**
	 * 获取页面
	 * 
	 * @return
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "to.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String to(Model model) {
		/*Long totalCount = sysRoleService.queryTotal(new SysRole());
		model.addAttribute("totalCount", totalCount);*/
		return "newjsp/sys/sysRole-list";
	}

	/**
	 * 获取数据
	 * 
	 * 
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "list.html", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public MyResult info(@RequestBody SysRole sysRole, HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(sysRole.getPage_new(), sysRole.getPage_size());
		List<SysRole> list = sysRoleService.findList(sysRole);
		PageInfo<SysRole> page = new PageInfo<SysRole>(list);
		map.put("page", page);
		map.put("obj", sysRole);
		return MyResult.ok(map);
	}

	/**
	 * 获取数据
	 * 
	 * 
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "one.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String one(SysRole sysRole, HttpServletRequest request, Model model) {
		if (sysRole.getId() != null) {
			sysRole = sysRoleService.get(sysRole.getId());
		}
		model.addAttribute("sysRole", sysRole);
		return "newjsp/sys/sysRole-one";
	}

	@RequiresPermissions("sys:role:save")
	@RequestMapping(value = "save.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public MyResult save(@RequestBody SysRole sysRole, HttpServletRequest request) {
		String str = ValidtorUtil.validbean(sysRole);
		if (MyStringUtils.isEmpty(str)) {
			return MyResult.error(str);
		}
		int i = sysRoleService.save(sysRole);
		return i > 0 ? MyResult.ok("保存成功") : MyResult.error("保存失败");
	}

	@RequiresPermissions("sys:role:update")
	@RequestMapping(value = "update.html")
	@ResponseBody
	public MyResult update(@RequestBody SysRole sysRole, HttpServletRequest request) {
		String str = ValidtorUtil.validbean(sysRole);
		if (MyStringUtils.isEmpty(str)) {
			return MyResult.error(str);
		}
		int i = sysRoleService.update(sysRole);
		return i > 0 ? MyResult.ok("修改成功") : MyResult.error("修改成功");
	}

	@RequiresPermissions("sys:role:delete")
	@RequestMapping(value = "del.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public MyResult delete(@RequestBody SysRole sysRole, HttpServletRequest request) {
		int i = sysRoleService.delete(sysRole);
		return i > 0 ? MyResult.ok("删除成功") : MyResult.error("删除失败");
	}

	@RequiresPermissions("sys:role:batchdelete")
	@RequestMapping(value = "batchdelete.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public MyResult batchdelete(@RequestBody SysRole sysRole, HttpServletRequest request) {
		int i = sysRoleService.batchdelete(sysRole);
		return i > 0 ? MyResult.ok("批量" + i + "个删除成功") : MyResult.error("批量删除失败");
	}
}
