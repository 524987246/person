package org.great.web.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.great.util.myutil.MyActionUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ch.qos.logback.core.joran.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理类
 */
@ControllerAdvice
public class ErrorController {
	private final static String ERROR_MESSAGE_TEMPLATE = "{\"message\":\"CONTETN\"}";

	@ExceptionHandler(value = AuthorizationException.class) // 异常捕获
	public String defaultErrorHandler(HttpServletResponse response, HttpServletRequest request, Model model,
			Exception e) {
		System.out.println("AuthorizationException权限不足异常");
		String requestType = request.getHeader("X-Requested-With");
		if (requestType == null) {
			model.addAttribute("e", e);
			model.addAttribute("message", "权限不足");
			return "error/403";
		} else {
			String message = ERROR_MESSAGE_TEMPLATE.replaceAll("CONTETN", "权限不足,请联系管理员");
			MyActionUtil.renderString(response, message, "application/json");
			return null;
		}

	}
}
