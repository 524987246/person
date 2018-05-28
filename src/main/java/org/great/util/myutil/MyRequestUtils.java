package org.great.util.myutil;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class MyRequestUtils {
	public static String path = "E:\\ess_shop-1.0.0\\WEB-INF\\classes\\com\\ess\\consts\\controller";

	public static void main(String[] args) {
		try {
			String basePack = "org.great.web.controller";
			List<String> requestPath = getAllRequesth2(basePack);
			for (String string : requestPath) {
				// System.out.println(string);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据包名,获取所有请求路径
	 * 
	 * @param basePack
	 * @return
	 * @throws ClassNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static List<String> getAllRequesth2(String basePack)
			throws ClassNotFoundException, UnsupportedEncodingException {
		// 包名
		// 先把包名转换为路径,首先得到项目的classpath
		String classpath = URLDecoder.decode(MyRequestUtils.class.getResource("/").getPath(), "UTF-8");
		// 然后把我们的包名basPach转换为路径名
		basePack = basePack.replace(".", File.separator);
		// 然后把classpath和basePack合并
		String searchPath = classpath + basePack;
		List<File> list = new ArrayList<File>();
		List<String> requestPath = new ArrayList<String>();
		getfiles(list, searchPath);
		for (File file : list) {
			String s = file.getAbsolutePath();
			s = URLDecoder.decode(s, "UTF-8");
			s = s.replace(classpath.replace("/", "\\").replaceFirst("\\\\", ""), "").replace("\\", ".");
			s = s.substring(0, s.lastIndexOf("."));
			Class cls = Class.forName(s);
			if (cls.isAnnotationPresent(RequestMapping.class)) {
				requestPath.addAll(getAnnotations(cls));
			}
		}
		return requestPath;

	}

	/**
	 * 根据绝对路径,获取所有请求路径
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static List<String> getAllRequesth() throws ClassNotFoundException {
		List<File> list = new ArrayList<File>();
		List<String> requestPath = new ArrayList<String>();
		getfiles(list, path);
		for (File file : list) {
			String s = file.getAbsolutePath();
			s = s.replace("E:\\ess_shop-1.0.0\\WEB-INF\\classes\\", "").replace("\\", ".").replace(".class", "");
			Class cls = Class.forName(s);
			if (cls.isAnnotationPresent(RequestMapping.class)) {
				requestPath.addAll(getAnnotations(cls));
			}
		}
		return requestPath;
	}

	public static List<String> getAnnotations(Class cls) {
		List<String> requestPath = new ArrayList<String>();
		RequestMapping rmp = (RequestMapping) cls.getAnnotation(RequestMapping.class);
		String[] base = rmp.value();
		Method[] methods = cls.getMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(RequestMapping.class)) {
				RequestMapping temp = (RequestMapping) method.getAnnotation(RequestMapping.class);
				String requestMethod = "";
				for (RequestMethod str : temp.method()) {
					requestMethod += str;
				}
				for (String path1 : base) {
					for (String path2 : temp.value()) {
						String str = path1 + path2;
						requestPath.add(str);
						System.out.println("{\"path\":\"" + str + "\",\"requestMethod\":\"" + requestMethod + "\"}");
					}
				}

			}
		}
		return requestPath;
	}

	public static void getfiles(List<File> list, String path) {
		try {
			path = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		File file = new File(path);
		if (file.isDirectory()) {
			for (File temp : file.listFiles()) {
				getfiles(list, temp.getAbsolutePath());
			}
		} else {
			list.add(file);
		}
	}
}
