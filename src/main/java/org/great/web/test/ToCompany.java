package org.great.web.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.great.util.myutil.MyResult;
import org.great.web.bean.sys.Dept;
import org.great.web.bean.sys.User;
import org.great.web.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Reception/addCompany")
public class ToCompany {

	@RequestMapping(value = "/to.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String addCompany() {
		return "jsp/test/addCompany";
	}

	/**
	 * 获取数据
	 * 
	 * 
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public MyResult info() {
		List<BuzCompany> addlist = DbCompany.getAddCompany();
		List<BuzCompany> newlist = DbCompany.getNewCompanyinfo(DbCompany.new_con);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("addlist", addlist);
		map.put("newlist", newlist);
		return MyResult.ok(map);
	}

}