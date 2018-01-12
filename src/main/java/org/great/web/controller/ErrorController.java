package org.great.web.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.great.config.BaseResoure;
import org.great.util.myutil.MyActionUtil;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

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
		boolean bo = MyActionUtil.isAjax(request);
		if (!bo) {
			model.addAttribute("e", e);
			model.addAttribute("message", "权限不足");
			return BaseResoure.DEFAULT_ERROR_VIEW;
		} else {
			String message = ERROR_MESSAGE_TEMPLATE.replaceAll("CONTETN", "权限不足,请联系管理员");
			MyActionUtil.renderString(response, message, "application/json");
			return null;
		}
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String bad_request(HttpServletResponse response, HttpServletRequest request, Model model, Exception e) {
		System.out.println("400");
		String requestType = request.getHeader("X-Requested-With");
		if (requestType == null) {
			model.addAttribute("e", e);
			model.addAttribute("message", "BAD_REQUEST");
			return BaseResoure.DEFAULT_ERROR_VIEW;
		} else {
			String message = ERROR_MESSAGE_TEMPLATE.replaceAll("CONTETN", "BAD_REQUEST");
			MyActionUtil.renderString(response, message, "application/json");
			return null;
		}
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String not_found(HttpServletResponse response, HttpServletRequest request, Model model, Exception e) {
		System.out.println("404");
		String requestType = request.getHeader("X-Requested-With");
		if (requestType == null) {
			model.addAttribute("e", e);
			model.addAttribute("message", "NOT_FOUND");
			return BaseResoure.DEFAULT_ERROR_VIEW;
		} else {
			String message = ERROR_MESSAGE_TEMPLATE.replaceAll("CONTETN", "NOT_FOUND");
			MyActionUtil.renderString(response, message, "application/json");
			return null;
		}
	}

	@ExceptionHandler(value = NoHandlerFoundException.class)
	public String defaultErrorHandler(HttpServletResponse response, HttpServletRequest request, Exception e,
			Model model) throws Exception {
		System.out.println("404--@ExceptionHandler");
		boolean bo = MyActionUtil.isAjax(request);
		if (!bo) {
			model.addAttribute("e", e);
			model.addAttribute("message", "BAD_REQUEST");
			return BaseResoure.DEFAULT_ERROR_VIEW;
		} else {
			String message = ERROR_MESSAGE_TEMPLATE.replaceAll("CONTETN", "BAD_REQUEST");
			MyActionUtil.renderString(response, message, "application/json");
			return null;
		}
	}
}
