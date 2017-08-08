package org.great.web.controller.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/Reception/UEditor")
public class UEditorController {

	/**
	 * 上传图片
	 */
	@RequestMapping(value = "/image.html", method = RequestMethod.POST)
	public void uoloadimage(HttpServletRequest request, HttpServletResponse response) {

	}

	/**
	 * 上传图视频
	 */
	@RequestMapping(value = "/video.html", method = RequestMethod.POST)
	public void uoloadvideo(HttpServletRequest request, HttpServletResponse response) {

	}

	/**
	 * 上传图文件
	 */
	@RequestMapping(value = "/file.html", method = RequestMethod.POST)
	public void uoloadfile(HttpServletRequest request, HttpServletResponse response) {

	}

	/**
	 * 图片列表
	 */
	@RequestMapping(value = "/listimage.html", method = RequestMethod.POST)
	public void listimage(HttpServletRequest request, HttpServletResponse response) {

	}

	/**
	 * 文件列表
	 */
	@RequestMapping(value = "/listfile.html", method = RequestMethod.POST)
	public void listfile(HttpServletRequest request, HttpServletResponse response) {

	}
}