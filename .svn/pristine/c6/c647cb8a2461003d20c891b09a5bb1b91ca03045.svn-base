package org.great.util;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.great.wx.bean.TextMessage;

import com.thoughtworks.xstream.XStream;

public class MessageUtil {
	public static Map<String, String> getMapFromXml(HttpServletRequest request)
			throws IOException, DocumentException {
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
	public static String textMessageToxML(TextMessage textMessage) {
		XStream xStream=new XStream();
		xStream.alias("xml", textMessage.getClass());
		return xStream.toXML(textMessage);
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
	
	public static void main(String[] args) {
		MessageUtil.getXmlFromMap("F:/test.xml");
	}
}
