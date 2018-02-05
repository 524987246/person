package org.great.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.great.util.MD5Util;
import org.great.util.beanValidtor.ValidtorUtil;
import org.great.util.myutil.MyResult;
import org.great.util.myutil.MyStringUtils;
import org.great.util.myutil.MyUserUtils;
import org.great.web.bean.sys.User;
import org.great.web.service.sys.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Reception/base/")
public class BaseContoller {
	private static final Logger logger = LoggerFactory.getLogger(BaseContoller.class);
	private static final String LOGIN_URL = "newjsp/login";

	@Resource
	private UserService userService;

	@RequestMapping(value = "tologin.html", produces = "text/html;charset=UTF-8")
	public String tologin(Model model) {
		User user = MyUserUtils.getLoginUser();
		if (user != null) {
			model.addAttribute("user", user);
			return "newjsp/main";
		}
		return LOGIN_URL;
	}

	@RequestMapping(value = "logout.html", produces = "text/html;charset=UTF-8")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return LOGIN_URL;
	}

	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "login.html", produces = "text/html;charset=UTF-8")
	public MyResult login(@RequestBody User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		if (user == null) {
			model.addAttribute("message", "请输入用户名,密码");
			return MyResult.ok(LOGIN_URL);
		}
		String message = ValidtorUtil.validbean(user);
		if (MyStringUtils.isEmpty(message)) {
			model.addAttribute("message", message);
			return MyResult.ok(LOGIN_URL);
		}
		;
		UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(),
				MD5Util.MD5Encode(user.getPassword()));
		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();
		String loginnname = user.getName();
		try {
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
			logger.info("对用户[" + loginnname + "]进行登录验证..验证开始");
			currentUser.login(token);
			logger.info("对用户[" + loginnname + "]进行登录验证..验证通过");
		} catch (Exception uae) {
			//uae.printStackTrace();
			logger.info("对用户[" + loginnname + "]进行登录验证..验证未通过,未知账户");
		}
		// 验证是否登录成功
		if (currentUser.isAuthenticated()) {
			user = MyUserUtils.getLoginUser();
			String ctx = request.getContextPath();
			user.createMenuHtml(ctx);
			model.addAttribute("user", user);
			logger.info("用户[" + loginnname + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
			return MyResult.ok();
		} else {
			token.clear();
			model.addAttribute("message", "账号或密码有误");
			return MyResult.error("账号或密码有误");
		}
	}

	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "refreshBase.html", produces = "text/html;charset=UTF-8")
	public MyResult refreshBase(HttpServletRequest request) {
		User user = MyUserUtils.getLoginUser();
		if (user == null) {
			return MyResult.error();
		}
		User temp = userService.get(user.getId());
		String ctx = request.getContextPath();
		temp.createMenuHtml(ctx);
		MyUserUtils.updateUserInfo(temp);
		return MyResult.ok();
	}

	/**
	 * 主页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "index.html", produces = "text/html;charset=UTF-8")
	public String index(HttpServletRequest request, Model model) {
		// User user = MyUserUtils.getLoginUser(request);
		User user = MyUserUtils.getLoginUser();
		if (user == null) {
			return LOGIN_URL;
		}
		model.addAttribute("user", user);
		return "newjsp/main";
	}

	/**
	 * 主页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "403.html", produces = "text/html;charset=UTF-8")
	public String errorhtml(HttpServletRequest request, Model model) {
		return "newjsp/403";
	}

}
