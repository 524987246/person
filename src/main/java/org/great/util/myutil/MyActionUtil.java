package org.great.util.myutil;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * 请求返回数据工具类
 * 
 * @author xiej
 * @date 2017-9-11 14:02:13
 * @since 2.0
 */
public class MyActionUtil {
	/**
	 * 客户端返回JSON字符串
	 *
	 * @param response
	 * @param object
	 * @return
	 */
	public static String renderString(HttpServletResponse response, Object object) {
		return renderString(response, JSONObject.fromObject(object).toString(), "application/json");
	}

	/**
	 * 客户端返回字符串
	 *
	 * @param response
	 * @param string
	 * @return
	 */
	public static String renderString(HttpServletResponse response, String string, String type) {
		try {
			response.reset();
			response.setContentType(type);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(string);
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public static void sendContendClink(HttpServletResponse response, Object object) throws IOException {
		response.getWriter().print(object.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

}
