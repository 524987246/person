package org.great.web.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.great.web.service.TestService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/")
public class TestController {
	@Resource
	private TestService testService;

	@RequestMapping(value = "one.html", produces = "text/html;charset=UTF-8")
	public List<Map<String, Object>> one(Model model) {
		return testService.test1();
	}

	@RequestMapping(value = "two.html", produces = "text/html;charset=UTF-8")
	public List<Map<String, Object>> two(Model model) {
		return testService.test2();
	}
}
