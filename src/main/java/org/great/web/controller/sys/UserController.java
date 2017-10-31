package org.great.web.controller.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.great.util.beanValidtor.ValidtorUtil;
import org.great.util.myutil.MyResult;
import org.great.util.myutil.MyStringUtils;
import org.great.util.myutil.MyUserUtils;
import org.great.web.bean.sys.User;
import org.great.web.service.sys.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/Reception/sys/user/")
public class UserController {
	@Resource
	private UserService userService;

	/**
	 * 获取页面
	 * 
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "to.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String to(Model model) {
		Long totalCount = userService.queryTotal(new User());
		model.addAttribute("totalCount", totalCount);
		return "newjsp/sys/user-list";
	}

	/**
	 * 获取数据
	 * 
	 * 
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "list.html", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public MyResult info(@RequestBody User user, HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		Long totalCount = userService.queryTotal(user);
		user.setPageInfo(totalCount);
		PageHelper.startPage(user.getPage_new(), user.getPage_size());
		List<User> list = userService.findList(user);
		for (User user2 : list) {
			// 避免前台展示密码
			user2.setPassword("");
		}
		PageInfo<User> page = new PageInfo<User>(list);
		map.put("page", page);
		map.put("obj", user);
		return MyResult.ok(map);
	}

	/**
	 * 获取数据
	 * 
	 * 
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "one.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String one(User user, HttpServletRequest request, Model model) {
		if (user.getId() != null) {
			user = userService.get(user.getId());
		}
		model.addAttribute("obj", user);
		return "newjsp/sys/user-one";
	}

	@RequiresPermissions("sys:user:save")
	@RequestMapping(value = "save.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public MyResult save(@RequestBody User user, HttpServletRequest request) {
		String password = user.getPassword() == null ? "" : user.getPassword();
		String newpassword = user.getNewpassword() == null ? "" : user.getNewpassword();
		;
		String str = "";
		if (password.equals(newpassword)) {
			str = ValidtorUtil.validbean(user);
			if (MyStringUtils.isEmpty(str)) {
				return MyResult.error(str);
			}
		} else {
			return MyResult.error("两次输入密码不相同,请重新输入");
		}
		int i = userService.save(user);
		return i > 0 ? MyResult.ok("保存成功") : MyResult.error("保存失败");
	}

	@RequiresPermissions("sys:user:update")
	@RequestMapping(value = "update.html")
	@ResponseBody
	public MyResult update(@RequestBody User user, HttpServletRequest request) {
		String password = user.getPassword() == null ? "" : user.getPassword();
		String newpassword = user.getNewpassword() == null ? "" : user.getNewpassword();
		;
		String str = "";
		if (password.equals(newpassword)) {
			if (!password.equals("")) {
				str = ValidtorUtil.validbean(user);
				if (MyStringUtils.isEmpty(str)) {
					return MyResult.error(str);
				}
			}
		} else {
			return MyResult.error("两次输入密码不相同,请重新输入");
		}
		int i = userService.update(user);
		return i > 0 ? MyResult.ok("修改成功") : MyResult.error("修改成功");
	}

	@RequiresPermissions("sys:user:delete")
	@RequestMapping(value = "del.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public MyResult delete(@RequestBody User user, HttpServletRequest request) {
		int i = userService.delete(user);
		return i > 0 ? MyResult.ok("删除成功") : MyResult.error("删除失败");
	}

	@RequiresPermissions("sys:user:batchdelete")
	@RequestMapping(value = "batchdelete.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public MyResult batchdelete(@RequestBody User user, HttpServletRequest request) {
		int i = userService.batchdelete(user);
		return i > 0 ? MyResult.ok("批量" + i + "个删除成功") : MyResult.error("批量删除失败");
	}

	@RequiresPermissions("sys:user:view")
	@ResponseBody
	@RequestMapping(value = "get.html", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public MyResult get() {
		User user = MyUserUtils.getLoginUser();
		User temp = (User) MyUserUtils.myclone(user);
		temp.setPassword(null);
		return MyResult.ok(temp);
	}

	@ResponseBody
	@RequestMapping(value = "check.html", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public MyResult check(@RequestBody User user, HttpServletRequest request) {
		if (user.getId() == null) {
			user.setId(0L);
		}
		List<User> list = userService.checkLoginName(user);
		MyResult myResult = new MyResult();
		if (list != null && list.size() > 0) {
			myResult = MyResult.error("2");
		} else {
			myResult = MyResult.ok("1");
		}
		return myResult;
	}
}
