package org.great.web.controller.sys;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.great.util.beanValidtor.ValidtorUtil;
import org.great.web.bean.sys.User;
import org.great.web.service.sys.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 常见web错误controller
 * 
 * @author xiej
 * @date 2016-12-27 2017-3-21
 * @since 2.0
 */
@Controller
@RequestMapping("/Reception/user")
public class SysUserController {
	@Resource
	private SysUserService sysUserServices;

	/**
	 * 获取页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String tologin() {
		return "jsp/login";
	}

	/**
	 * 保存用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public void add(User user, HttpServletRequest request, HttpServletResponse response) {
		String str = ValidtorUtil.validbean(user);
		if (str != null) {
			System.out.println("验证不通过");
			return;
		}
		System.out.println("验证通过");

	}
}
