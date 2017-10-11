package org.great.web.controller.buz;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.great.util.InputCheck;
import org.great.util.Message;
import org.great.web.bean.buz.WebError;
import org.great.web.service.buz.WebErrorService;
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
@RequestMapping("/Reception/weberror")
public class WebErrorController {
	@Resource
	private WebErrorService webErrorServices;

	/**
	 * 传输数据
	 */
	private String msg;

	/**
	 * 获取常见错误页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/weberror.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String toweberror() {
		return "jsp/weberror/weberror";
	}

	/**
	 * 获取错误数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/info.html", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String info(@RequestBody Message message, HttpServletRequest request, Model model) {
		// System.out.println("str===="+str);
		// System.out.println(message.toString());
		// Message message = (Message)
		// JSONObject.toBean(JSONObject.fromObject(str), Message.class);
		WebError webError = new WebError();
		if (null != message && null != message.getStr()) {
			webError = (WebError) JSONObject.toBean(JSONObject.fromObject(message.getStr()), WebError.class);
			// System.out.println(webError.toString());
		}
		message.setPage_new(message.getPage_new() * message.getPage_num());
		message.setPage_num(message.getPage_num() + 1);
		List<WebError> list = webErrorServices.findWebErrorByWebError(webError, message.getPage_new(),
				message.getPage_num());

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
	public String remove(@RequestBody String str, HttpServletRequest request, Model model) {
		boolean bo = webErrorServices.delWebErrorBySid(str, 2);
		return String.valueOf(bo);
	}

	/**
	 * 更新
	 * 
	 * @return
	 */
	@RequestMapping(value = "/update.html", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public void update(WebError webError, HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		boolean bo = false;
		try {
			if (!InputCheck.NotNullsString(String.valueOf(webError.getSerrorid()), 10)) {
				response.getWriter().print("错误代码,输入有误");
				response.getWriter().flush();
				response.getWriter().close();
				return;
			}
			bo = webErrorServices.updateWebErrorBySid(webError);
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
	public void add(WebError webError, HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		try {
			if (!InputCheck.NotNullsString(String.valueOf(webError.getSerrorid()), 10)) {
				response.getWriter().print("错误代码,输入有误");
				response.getWriter().flush();
				response.getWriter().close();
				return;
			}
			boolean bo = webErrorServices.insertWebError(webError);
			response.getWriter().print(bo);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
