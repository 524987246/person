package org.great.web.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 前台界面跳转
 * 
 * @author xiej
 * @date 2016-12-27
 * @since 1.0
 */
@Controller
@RequestMapping("/Reception/goto")
public class ToReceptionView {


	@RequestMapping("/main.html")
	public String tomain() {
		return "jsp/main";
	}
	@RequestMapping("/left.html")
	public String toleft() {
		return "include/left";
	}
	@RequestMapping("/head.html")
	public String tohead() {
		
		return "include/head";
	}
	@RequestMapping("/foot.html")
	public String tofoot() {
		return "include/foot";
	}
	@RequestMapping("/right.html")
	public String toright() {
		return "jsp/index";
	}
}
