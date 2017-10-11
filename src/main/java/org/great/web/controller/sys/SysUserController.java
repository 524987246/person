package org.great.web.controller.sys;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.ValidationException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.great.util.InputCheck;
import org.great.util.Message;
import org.great.util.beanValidtor.ValidtorUtil;
import org.great.web.bean.buz.User;
import org.great.web.bean.buz.WebError;
import org.great.web.service.buz.WebErrorService;
import org.great.web.service.sys.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	 * 传输数据
	 */
	private String msg;

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
	public void add(User user, HttpServletRequest request,
			HttpServletResponse response) {
		String str = ValidtorUtil.validbean(user);
		if (str != null) {
			System.out.println("验证不通过");
			return;
		}
		System.out.println("验证通过");

	}
}
