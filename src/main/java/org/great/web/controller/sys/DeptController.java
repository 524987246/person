package org.great.web.controller.sys;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.great.util.InputCheck;
import org.great.util.Message;
import org.great.util.myutil.MyPrintUtil;
import org.great.web.bean.WebError;
import org.great.web.bean.sys.Dept;
import org.great.web.service.DeptService;
import org.great.web.service.WebErrorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
		PageHelper.startPage(1, 2);
		return "jsp/sys/dept";
	}

	/**
	 * 获取错误数据
	 * 
	 * 
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> info(@RequestBody Dept dept, HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		/*
		 * Integer totalCount = deptService.queryTotal(dept);
		 * dept.setPageInfo(totalCount);
		 */
		PageHelper.startPage(dept.getPage_new(), dept.getPage_size());
		List<Dept> list = deptService.findList(dept);
		map.put("list", list);
		PageInfo page = new PageInfo(list);
		map.put("page", page);
		dept.setPageInfo(page.getTotal());
		map.put("obj", dept);
		return map;
	}
}
