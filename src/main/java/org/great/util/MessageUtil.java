package org.great.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.formula.functions.T;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.great.wx.bean.Article;
import org.great.wx.bean.NewsMessage;
import org.great.wx.bean.TextMessage;

import com.thoughtworks.xstream.XStream;

public class MessageUtil {
	// 定义消息类型
	public static final String MSGTYPE_TEXT = "text";
	public static final String MSGTYPE_NEWS = "news";
	public static final String MSGTYPE_IMAGE = "image";
	public static final String MSGTYPE_VOICE = "voice";
	public static final String MSGTYPE_MUSIC = "music";
	public static final String MSGTYPE_LOCATION = "location";
	public static final String MSGTYPE_LINK = "link";
	public static final String MSGTYPE_EVENT = "event";
	public static final String EVENT_SUBSCRIBE = "subscribe";
	public static final String EVENT_SCAN = "SCAN";
	public static final String EVENT_LOCATION = "location_select";
	public static final String EVENT_CLICK = "CLICK";
	public static final String EVENT_VIEW = "VIEW";
	public static final String EVENT_SCANCODE_PUSH = "scancode_push";

	/**
	 * xml to map 解析xml 并转换成map集合
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String, String> getMapFromXml(HttpServletRequest request) throws IOException, DocumentException {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		InputStream inputStream = request.getInputStream();
		Document doc = reader.read(inputStream);
		Element root = doc.getRootElement();
		List<Element> list = root.elements();
		for (Element element : list) {
			map.put(element.getName(), element.getText());
		}
		inputStream.close();
		return map;
	}

	/**
	 * 将文本消息转换成xml
	 * 
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToxML(Object object) {
		XStream xStream = new XStream();
		xStream.alias("xml", object.getClass());
		return xStream.toXML(object);
	}

	public static void getXmlFromMap(String path) {
		try {
			// DocumentHelper提供了创建Document对象的方法
			Document document = DocumentHelper.createDocument();
			// 添加节点信息
			Element rootElement = document.addElement("modules");
			// 这里可以继续添加子节点，也可以指定内容
			rootElement.setText("这个是module标签的文本信息");
			Element element = rootElement.addElement("module");
			Element nameElement = element.addElement("name");
			Element valueElement = element.addElement("value");
			Element descriptionElement = element.addElement("description");
			nameElement.setText("名称");
			nameElement.addAttribute("language", "java");// 为节点添加属性值
			valueElement.setText("值");
			valueElement.addAttribute("language", "c#");
			descriptionElement.setText("描述");
			descriptionElement.addAttribute("language", "sql server");
			System.out.println(document.asXML()); // 将document文档对象直接转换成字符串输出
			Writer fileWriter = new FileWriter(path);
			// dom4j提供了专门写入文件的对象XMLWriter
			XMLWriter xmlWriter = new XMLWriter(fileWriter);
			xmlWriter.write(document);
			xmlWriter.flush();
			xmlWriter.close();
			System.out.println("xml文档添加成功！");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 4.组合文本消息
	 * 
	 * @param toUserName
	 * @param fromUserName
	 * @param content
	 * @return
	 */
	public static String initText(String toUserName, String fromUserName, String content) {
		TextMessage textMessage = new TextMessage();
		textMessage.setFromUserName(toUserName);
		textMessage.setToUserName(fromUserName);
		textMessage.setContent(content);
		textMessage.setMsgType(MessageUtil.MSGTYPE_TEXT);
		textMessage.setCreateTime(new Date().getTime());
		return textMessageToxML(textMessage);
	}

	/**
	 * 5.组合图文消息
	 * 
	 * @param toUserName
	 * @param fromUserName
	 * @param content
	 * @return
	 */
	public static String initNewsMessage(String toUserName, String fromUserName, String content,
			List<Article> articleList) {
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setFromUserName(toUserName);
		newsMessage.setToUserName(fromUserName);
		newsMessage.setMsgType(MessageUtil.MSGTYPE_NEWS);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setArticles(articleList);
	/*	Article article = new Article();
		article.setTitle("haojiahong的博客");
		article.setDescription("我不是高手，我不会武功。");
		article.setPicUrl("http://pic.cnblogs.com/avatar/466668/20150530175722.png");
		article.setUrl("http://www.cnblogs.com/haojiahong");
		articleList.add(article);*/
		return textMessageToxML(newsMessage);
	}

	public static void main(String[] args) {
		MessageUtil.getXmlFromMap("F:/test.xml");
	}
}
