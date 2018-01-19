package org.great.web.controller.test;

import org.great.util.NameRandom;
import org.great.web.WebSocketTest;
import org.great.web.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Reception")
public class ToJsp {
	@Autowired
	private TestService testService;
	@Autowired
	private WebSocketTest socketTest;

	@RequestMapping(value = "/websocket.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String websocket() {
		testService.test1();
		testService.test2();
		return "jsp/test/websocketClienk";
	}

	@ResponseBody
	@RequestMapping(value = "/send.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String sendwebsocket() {
		//测试群发功能,完成
		String str = NameRandom.filename(null);
		socketTest.onMessage(str);
		return str;
	}

	@RequestMapping(value = "/webUpload.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String webUpload() {
		return "jsp/test/webUpload";
	}

	@RequestMapping(value = "/toUEditor.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String toUEditor() {
		return "jsp/test/webUEditor";
	}

	/**
	 * 新后台页面测试
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toNewMain.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String toNewMain() {
		return "newjsp/login";
	}
}