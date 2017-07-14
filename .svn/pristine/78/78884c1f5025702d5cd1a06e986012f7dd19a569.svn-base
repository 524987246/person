package org.great.web.controller.dailyfunction;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.great.util.InputCheck;
import org.great.util.Message;
import org.great.web.bean.CostInfo;
import org.great.web.service.CostInfoService;
import org.great.web.service.SystemManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 消费管理
 * 
 * @author xiej
 * @date 2017-03-21
 * @since 1.0
 */
@Controller
@RequestMapping("/Reception/daily")
public class CostInfoCotroller {
	@Resource
	private CostInfoService costInfoServices;

	/**
	 * 传输数据
	 */
	private String msg;

	/**
	 * 获取页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/costInfo.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String toCostInfo() {
		return "jsp/costinfo/costinfo";
	}

	/**
	 * 获取错误数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/info.html", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String info(@RequestBody Message message,
			HttpServletRequest request, Model model) {
		// System.out.println("str===="+str);
		//System.out.println(message.toString());
		// Message message = (Message)
		// JSONObject.toBean(JSONObject.fromObject(str), Message.class);
		CostInfo costInfo = new CostInfo();
		if (null != message && null != message.getStr()) {
			costInfo = (CostInfo) JSONObject.toBean(
					JSONObject.fromObject(message.getStr()), CostInfo.class);
			// System.out.println(CostInfo.toString());
		}
		message.setPage_new(message.getPage_new() * message.getPage_num());
		message.setPage_num(message.getPage_num() + 1);
		List<CostInfo> list = costInfoServices.findCostInfoByCostInfo(costInfo,
				message.getPage_new(), message.getPage_num());
		msg = JSONArray.fromObject(list).toString();
		return msg;
	}

	/**
	 * 更新数据状态(伪删除)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/remove.html", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String remove(@RequestBody String str, HttpServletRequest request,
			Model model) {
		boolean bo = costInfoServices.delCostInfoBySid(str, 2);
		return String.valueOf(bo);
	}

	/**
	 * 更新
	 * 
	 * @return
	 */
	@RequestMapping(value = "/update.html", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public void update(CostInfo CostInfo, HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		boolean bo = false;
		try {
			bo = costInfoServices.updateCostInfoBySid(CostInfo);
			response.getWriter().print(bo);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add.html", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public void add(CostInfo CostInfo, HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		try {
			boolean bo = costInfoServices.insertCostInfo(CostInfo);
			response.getWriter().print(bo);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
