package org.great.web.controller.sys;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.great.util.Dboperate;
import org.great.util.InputCheck;
import org.great.util.generate.AutoVelocity;
import org.great.web.bean.buz.DbName;
import org.great.web.jdbc.ColumnEntity;
import org.great.web.jdbc.QueryDao;
import org.great.web.service.sys.SystemManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.io.IOUtils;

/**
 * 代码生成
 * 
 * @author xiej
 * @date 2016-12-27
 * @since 1.0
 */
@Controller
@RequestMapping("/Reception/manage")
public class SysGenerate {
	@Resource
	private SystemManageService manageServices;

	/**
	 * 传输数据
	 */
	private String msg;

	/**
	 * 获取页面
	 * 
	 * @return
	 */
	@RequestMapping("/generate.html")
	public String toweberror() {
		return "jsp/generate/generate";
	}

	/**
	 * 获取数据库类型信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/dbinfo.html", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String dbinfo() {
		DbName dbName = new DbName();
		List<DbName> list = manageServices.getDbName(dbName);
		msg = JSONArray.fromObject(list).toString();
		return msg;
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return tbnames
	 */
	@RequestMapping(value = "/connection.html", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public void connection(DbName dbName, HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
//		System.out.println(dbName.toString());
		try {
			if (!InputCheck.NotNullsString(dbName.getSdriver(), 2000)) {
				response.getWriter().print("错误代码,输入有误");
				response.getWriter().flush();
				response.getWriter().close();
				return;
			}
			Connection con=manageServices.getConnection(dbName);
			boolean bo = false;
			if (con != null) {
				bo = true;
			}
			response.getWriter().print(bo);
			response.getWriter().flush();
			response.getWriter().close();
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/dbnames.html", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public void dbnames(DbName dbName, HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
//		System.out.println("dbnames=="+dbName.toString());
		try {
			if (!InputCheck.NotNullsString(dbName.getSdriver(), 2000)) {
				response.getWriter().print("错误代码,输入有误");
				response.getWriter().flush();
				response.getWriter().close();
				return;
			}
			Connection con=manageServices.getConnection(dbName);
			String bo = "{'type':'false'}";
			if (con != null) {
				List<String> list=manageServices.querydbnames(dbName.getStype(),con);
				bo=JSONArray.fromObject(list).toString();
			}
			response.getWriter().print(bo);
			response.getWriter().flush();
			response.getWriter().close();
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/tbnames.html", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public void tbnames(DbName dbName, HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		try {
			if (!InputCheck.NotNullsString(dbName.getSdriver(), 2000)) {
				response.getWriter().print("错误代码,输入有误");
				response.getWriter().flush();
				response.getWriter().close();
				return;
			}
			Connection con=manageServices.getConnection(dbName);
			String bo = "{'type':'false'}";
			if (con != null) {
				List<Map<String,String>> list=manageServices.querytbnames(dbName,con);
				bo=JSONArray.fromObject(list).toString();
			}
			response.getWriter().print(bo);
			response.getWriter().flush();
			response.getWriter().close();
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * 生成代码
	 */
	@RequestMapping(value = "/code", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public void code(DbName dbName, HttpServletResponse response) throws IOException {
		AutoVelocity autoVelocity=new AutoVelocity();
		Connection con=manageServices.getConnection(dbName);
		List<ColumnEntity> resultlist = manageServices.generatorCode(dbName,con);
		String str=autoVelocity.autocode(resultlist,dbName.getTbname());
		str = "{'type':'"+str+"'}";
		response.getWriter().print(str);
		response.getWriter().flush();
		response.getWriter().close();
		//		response.reset();
//		response.setHeader("Content-Disposition", "attachment; filename=\"openBoot.zip\"");
//		response.addHeader("Content-Length", "" + data.length);
//		response.setContentType("application/octet-stream; charset=UTF-8");
//
//		IOUtils.write(data, response.getOutputStream());
	}
	// /**
	// * 获取错误数据
	// *
	// * @return
	// */
	// @RequestMapping(value = "/info.html", method = RequestMethod.POST,
	// produces = "text/html;charset=UTF-8")
	// @ResponseBody
	// public String info(@RequestBody String str, HttpServletRequest request,
	// Model model) {
	// Gson gson = new Gson();
	// // System.out.println("str===="+str);
	// Message message = gson.fromJson(str, Message.class);
	// WebError webError = new WebError();
	// if (message.getStr() != null) {
	// webError = gson.fromJson(message.getStr(), WebError.class);
	// // System.out.println(webError.toString());
	// }
	// message.setPage_new(message.getPage_new() * message.getPage_num());
	// message.setPage_num(message.getPage_num() + 1);
	// List<WebError> list = webErrorServices.findWebErrorByWebError(webError,
	// message.getPage_new(), message.getPage_num());
	// msg = gson.toJson(list);
	// return msg;
	// }
	//
	// /**
	// * 更新数据状态(伪删除)
	// *
	// * @return
	// */
	// @RequestMapping(value = "/remove.html", method = RequestMethod.POST,
	// produces = "text/html;charset=UTF-8")
	// @ResponseBody
	// public String remove(@RequestBody String str, HttpServletRequest request,
	// Model model) {
	// boolean bo = webErrorServices.delWebErrorBySid(str, 2);
	// return String.valueOf(bo);
	// }
	//

	//
	// /**
	// * 添加
	// *
	// * @return
	// */
	// @RequestMapping(value = "/add.html", method = RequestMethod.POST,
	// produces = "text/html;charset=UTF-8")
	// public void add(WebError webError, HttpServletRequest request,
	// HttpServletResponse response) {
	// response.setCharacterEncoding("utf-8");
	// try {
	// if (!InputCheck.NotNullsString(
	// String.valueOf(webError.getSerrorid()), 10)) {
	// response.getWriter().print("错误代码,输入有误");
	// response.getWriter().flush();
	// response.getWriter().close();
	// return;
	// }
	// boolean bo = webErrorServices.insertWebError(webError);
	// response.getWriter().print(bo);
	// response.getWriter().flush();
	// response.getWriter().close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
}
