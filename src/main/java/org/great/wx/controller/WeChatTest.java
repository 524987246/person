package org.great.wx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.great.wx.bean.TextMessage;
import org.great.wx.wxutil.MessageUtil;
import org.great.wx.wxutil.WeChatCheckUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 代码生成
 * 
 * @author xiej
 * @date 2016-12-27
 * @since 1.0
 */
@Controller
@RequestMapping("/WeChat/test")
public class WeChatTest {

	/**
	 * 微信端测试连接
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/connect.html", method = RequestMethod.GET)
	public void connect(HttpServletResponse response, HttpServletRequest request) throws IOException {
		System.out.println("微信服务测试开始");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		PrintWriter out = response.getWriter();
		if (WeChatCheckUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
	}

	/**
	 * 消息接收与发送
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/connect.html", method = RequestMethod.POST)
	public void receive(HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			Map<String, String> map = MessageUtil.getMapFromXml(request);
			String toUserName = map.get("ToUserName");
			String fromUserName = map.get("FromUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			String message = "";
			if (MessageUtil.MSGTYPE_TEXT.equals(msgType)) {
				TextMessage textMessage = new TextMessage();
				textMessage.setFromUserName(toUserName);
				textMessage.setToUserName(fromUserName);
				textMessage.setMsgType("text");
				textMessage.setCreateTime(new Date().getTime());
				textMessage.setContent("接收成功==>" + content + "<==,返回消息");
				message = MessageUtil.initText(toUserName, fromUserName, content);
			} else if (MessageUtil.MSGTYPE_EVENT.equals(msgType)) {
				// 事件类型再去细化判断
				String eventType = map.get("Event");
				if (MessageUtil.EVENT_SUBSCRIBE.equals(eventType)) {
					message = MessageUtil.initText(toUserName, fromUserName, "");
				}
			} else {
				message = MessageUtil.initText(toUserName, fromUserName, "");
			}
			out.print(message);
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}
}
