package org.great.util;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * java爬虫工具,未测试
 * @author xiej
 * @date 2017-04-6
 * @since 1.0
 */
public class CrawlerUtil {

	private static final String URL="http://fz.58.com/tech/?key=java&cmcskey=java&final=1&jump=1&specialtype=gls";
	public static void main(String[] args) {
		URL url = null;
		URLConnection urlconn = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		String regex = "^<dl*</dl>$";
		Pattern p = Pattern.compile(regex);
		try {
			url = new URL(CrawlerUtil.URL);
			urlconn = url.openConnection();
			pw = new PrintWriter(new FileWriter("d:/url.txt"), true);// 这里我们把收集到的链接存储在了E盘底下的一个叫做url的txt文件中
			br = new BufferedReader(new InputStreamReader(
					urlconn.getInputStream()));
			String buf = null;
			while ((buf = br.readLine()) != null) {
				Matcher buf_m = p.matcher(buf);
				System.out.println(buf);
				while (buf_m.find()) {
					pw.println(buf_m.group());
				}
			}
			System.out.println("获取成功！");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw.close();
		}
	}

}
