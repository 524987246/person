package org.great.web.controller.test;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.great.web.service.SystemManageService;
import org.great.web.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 *                 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */

// @ServerEndpoint("/websocket")
@Controller
@RequestMapping("/Reception")
public class ToJsp {
	@Autowired
	private TestService testService;

	@RequestMapping(value = "/websocket.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String websocket() {
		testService.test1();
		testService.test2();
		return "jsp/test/websocketClienk";
	}

	@RequestMapping(value = "/webUpload.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String webUpload() {
		return "jsp/test/webUpload";
	}

	@RequestMapping(value = "/toUEditor.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String toUEditor() {
		return "jsp/test/webUEditor";
	}
}