package org.great.web.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类
 */
@ControllerAdvice
public class ErrorController {

	@ExceptionHandler(value = AuthorizationException.class) // 异常捕获
	public String defaultErrorHandler(HttpServletRequest request, Model model, Exception e) {
		System.out.println("AuthorizationException权限不足异常");
		model.addAttribute("e", e);
		model.addAttribute("message", "权限不足");
		return "newjsp/403";
	}
}
