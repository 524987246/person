package org.great.web.controller.sys;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.great.util.FileUtil;
import org.great.util.InputCheck;
import org.great.util.generate.AutoVelocity;
import org.great.util.generate.AutoVelocity2;
import org.great.web.bean.sys.DbName;
import org.great.web.jdbc.ColumnEntity;
import org.great.web.service.sys.SystemManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
		String message = JSONArray.fromObject(list).toString();
		return message;
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return tbnames
	 */
	@RequestMapping(value = "/connection.html", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public void connection(DbName dbName, HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		// System.out.println(dbName.toString());
		try {
			if (!InputCheck.NotNullsString(dbName.getSdriver(), 2000)) {
				response.getWriter().print("错误代码,输入有误");
				response.getWriter().flush();
				response.getWriter().close();
				return;
			}
			Connection con = manageServices.getConnection(dbName);
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
	public void dbnames(DbName dbName, HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		// System.out.println("dbnames=="+dbName.toString());
		try {
			if (!InputCheck.NotNullsString(dbName.getSdriver(), 2000)) {
				response.getWriter().print("错误代码,输入有误");
				response.getWriter().flush();
				response.getWriter().close();
				return;
			}
			Connection con = manageServices.getConnection(dbName);
			String bo = "{'type':'false'}";
			if (con != null) {
				List<String> list = manageServices.querydbnames(dbName.getStype(), con);
				bo = JSONArray.fromObject(list).toString();
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
	public void tbnames(DbName dbName, HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		try {
			if (!InputCheck.NotNullsString(dbName.getSdriver(), 2000)) {
				response.getWriter().print("错误代码,输入有误");
				response.getWriter().flush();
				response.getWriter().close();
				return;
			}
			Connection con = manageServices.getConnection(dbName);
			String bo = "{'type':'false'}";
			if (con != null) {
				List<Map<String, String>> list = manageServices.querytbnames(dbName, con);
				bo = JSONArray.fromObject(list).toString();
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
	public void code(DbName dbName, HttpServletResponse response, HttpServletRequest request) throws IOException {
		// AutoVelocity autoVelocity = new AutoVelocity();
		AutoVelocity2 autoVelocity = new AutoVelocity2();
		String url = autoVelocity.getFilepath();
		url = request.getSession().getServletContext().getRealPath("/") + url;
		autoVelocity.setFilepath(url);// 设置生成代码路径
		Connection con = manageServices.getConnection(dbName);
		List<ColumnEntity> resultlist = manageServices.generatorCode(dbName, con);
		String str = autoVelocity.autocode(resultlist, dbName.getTbname());
		str = "{\"url\":\"" + str + "\"}";
		response.getWriter().print(str);
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 生成代码 暂无用
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updownload", method = RequestMethod.GET)
	public String updownload(String fileurl, String filename, HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		// String url =
		// request.getSession().getServletContext().getRealPath("/");
		String url = "/download/" + fileurl;
		request.setAttribute("filePath", url);// 即将下载的文件的相对路径
		request.setAttribute("fileName", filename);// 显示的文件名
		return "newjsp/sys/download";
	}

	/**
	 * 生成代码 暂用
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updownload2", method = RequestMethod.GET)
	public void updownload2(String fileurl, String filename, HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		// 设置文件MIME类型
		response.setContentType(request.getServletContext().getMimeType(filename));
		// 设置Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		// 读取目标文件，通过response将目标文件写到客户端
		// 获取目标文件的绝对路径
		String fullFileName = request.getServletContext().getRealPath("/download/" + fileurl);
		// System.out.println(fullFileName);
		// 读取文件
		InputStream in = new FileInputStream(fullFileName);
		OutputStream out = response.getOutputStream();
		// 写文件
		int b;
		while ((b = in.read()) != -1) {
			out.write(b);
		}
		in.close();
		out.close();
		File file = new File(fullFileName);
		FileUtil.deleteFile(file);
	}
}
