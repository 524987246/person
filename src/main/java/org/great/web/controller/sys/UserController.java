package org.great.web.controller.sys;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.great.util.beanValidtor.ValidtorUtil;
import org.great.util.myutil.MyUserUtils;
import org.great.web.bean.sys.User;
import org.great.web.service.sys.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Reception/sys/user/")
public class UserController {
	@Resource
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "login.html", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String to(@RequestBody User user, HttpServletRequest request, Model model) {
		String str = ValidtorUtil.validbean(user);
		if (str != null) {
			return str;
		}
		user = userService.get(user);
		if (user == null) {
			str = "登录失败<br>用户名错误或密码错误";
		} else {
			// 保存登录后的用户
			MyUserUtils.saveLoginUser(user, request);
			str = "1";
		}
		return str;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "toMain.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String toNewMain(HttpServletRequest request, Model model) {
		User user = MyUserUtils.getLoginUser(request);
		if (user == null) {
			return "newjsp/login";
		}
		model.addAttribute("user", user);
		return "newjsp/main";
	}

	/**
	 * 测试之用,免登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "toMain2.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String toNewMain2(HttpServletRequest request, Model model) {
		User user = MyUserUtils.getLoginUser(request);
		if (user == null) {
			user = new User();
			user.setId(1L);
			user = userService.get(user);
		}
		MyUserUtils.saveLoginUser(user, request);
		model.addAttribute("user", user);
		return "newjsp/main";
	}

}
