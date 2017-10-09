package org.great.web.controller.sys;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.great.util.InputCheck;
import org.great.util.Message;
import org.great.web.bean.WebError;
import org.great.web.service.DeptService;
import org.great.web.service.WebErrorService;
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
@RequestMapping("/Reception/sys")
public class DeptController {
	@Resource
	private DeptService deptService;

	/**
	 * 获取页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/dept.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String to() {
		return "jsp/sys/dept";
	}

}
