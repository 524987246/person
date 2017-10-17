package org.great.web.controller.sys;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.great.web.bean.sys.Menus;
import org.great.web.service.sys.MenusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;


/**
 * 功能获取
 * 
 * @author xiej
 * @date 2016-12-27
 * @since 1.0
 */
@Controller
@RequestMapping("/Reception/Menus")
public class ToMenus {
	@Resource
	private MenusService menusService;
	
	
	/**
	 * 获取功能菜单
	 * @param str
	 * @return
	 */
	@RequestMapping(value = "/toMenus.html", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String toMenus(@RequestBody String str,
			HttpServletRequest request, Model model) {
		Integer npartentid=Integer.valueOf(str);
		List<Menus> list=menusService.findUserByUser(npartentid);
		String message=JSONObject.toJSONString(list);
		return message;
	}
}
