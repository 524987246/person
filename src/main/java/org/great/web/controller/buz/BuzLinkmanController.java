package org.great.web.controller.buz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.great.util.beanValidtor.ValidtorUtil;
import org.great.util.myutil.MyStringUtils;
import org.great.util.myutil.MyResult;
import org.great.web.bean.buz.BuzLinkman;
import org.great.web.service.buz.BuzLinkmanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 
 * @author xiejun
 * @date 2017-10-23 09:52:10
 * @since 3.0
 */
@Controller
@RequestMapping("/Reception/buz/linkman")
public class BuzLinkmanController {
	@Resource
	private BuzLinkmanService buzLinkmanService;

	/**
	 * 获取页面
	 * 
	 * @return
	 */
	@RequiresPermissions("buz:linkman:view")
	@RequestMapping(value = "to.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String to(Model model) {
		//Long totalCount = buzLinkmanService.findList(new BuzLinkman());
		//model.addAttribute("totalCount", totalCount);
		return "newjsp/buz/linkman-list";
	}

	/**
	 * 获取数据
	 * 
	 * 
	 */
	@RequiresPermissions("buz:linkman:view")
	@RequestMapping(value = "list.html", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public MyResult info(@RequestBody BuzLinkman buzLinkman, HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(buzLinkman.getPage_new(), buzLinkman.getPage_size());
		List<BuzLinkman> list = buzLinkmanService.findList(buzLinkman);
		PageInfo<BuzLinkman> page = new PageInfo<BuzLinkman>(list);
		map.put("page", page);
		map.put("buzLinkman", buzLinkman);
		return MyResult.ok(map);
	}

	/**
	 * 获取数据
	 * 
	 * 
	 */
	@RequiresPermissions("buz:linkman:view")
	@RequestMapping(value = "one.html", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String one(BuzLinkman buzLinkman, HttpServletRequest request, Model model) {
		if (buzLinkman.getId() != null) {
			buzLinkman = buzLinkmanService.get(buzLinkman.getId());
		}
		model.addAttribute("buzLinkman", buzLinkman);
		return "newjsp/buz/linkman-one";
	}

	@RequiresPermissions("buz:linkman:save")
	@RequestMapping(value = "save.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public MyResult save(@RequestBody BuzLinkman buzLinkman, HttpServletRequest request) {
		String str = ValidtorUtil.validbean(buzLinkman);
		if (MyStringUtils.isEmpty(str)) {
			return MyResult.error(str);
		}
		int i = buzLinkmanService.save(buzLinkman);
		return i > 0 ? MyResult.ok("保存成功") : MyResult.error("保存失败");
	}


	@RequiresPermissions("buz:linkman:update")
	@RequestMapping(value = "update.html")
	@ResponseBody
	public MyResult update(@RequestBody BuzLinkman buzLinkman, HttpServletRequest request) {
		String str = ValidtorUtil.validbean(buzLinkman);
		if (MyStringUtils.isEmpty(str)) {
			return MyResult.error(str);
		}
		int i = buzLinkmanService.update(buzLinkman);
		return i > 0 ? MyResult.ok("修改成功") : MyResult.error("修改成功");
	}

	@RequiresPermissions("buz:linkman:delete")
	@RequestMapping(value = "del.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public MyResult delete(@RequestBody BuzLinkman buzLinkman, HttpServletRequest request) {
		int i = buzLinkmanService.delete(buzLinkman);
		return i > 0 ? MyResult.ok("删除成功") : MyResult.error("删除失败");
	}
	
	@RequiresPermissions("buz:linkman:batchdelete")
	@RequestMapping(value = "batchdelete.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public MyResult batchdelete(@RequestBody BuzLinkman buzLinkman, HttpServletRequest request) {
		int i = buzLinkmanService.batchdelete(buzLinkman);
		return i > 0 ? MyResult.ok("批量" + i + "个删除成功") : MyResult.error("批量删除失败");
	}
}
