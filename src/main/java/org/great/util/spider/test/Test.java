package org.great.util.spider.test;

import java.util.List;

import org.great.util.myutil.MyPrintUtil;
import org.great.util.spider.core.ExtractService;
import org.great.util.spider.rule.Rule;
import org.great.util.spider.util.ResultToObj;
import org.jsoup.select.Elements;

public class Test {

	public static void main(String[] args) {
		// Rule rule = new Rule("http://www.11315.com/newsearch",
		// new String[] { "regionMc","regionDm","searchTypeHead","name" }, new
		// String[] { "选择地区","" ,"","s"},
		// "innerBox", Rule.CLASS, Rule.GET);
		// Rule(url,参数名称，参数值，所需内容的标签class，对象，请求方式)
		Rule rule = new Rule("http://fz.58.com/job", new String[] { "final",
				"jump", "key" }, new String[] {
				"1", "1", "java" },
				"list_con", Rule.CLASS, Rule.GET);
		Elements results = ExtractService.extract(rule);
		// 格式化返回结果
		List<Object> extracts = ResultToObj.ToFiveEight(results);
		MyPrintUtil.printList(extracts);
	}

	// @org.junit.Test
	// public void getDatasByClass() {
	// Rule rule = new Rule(
	// "http://www1.sxcredit.gov.cn/public/infocomquery.do?method=publicIndexQuery",
	// new String[] { "query.enterprisename",
	// "query.registationnumber" }, new String[] { "兴网", "" },
	// "cont_right", Rule.CLASS, Rule.POST);
	// List<LinkTypeData> extracts = ExtractService.extract(rule);
	// printf(extracts);
	// }
	//
	// @org.junit.Test
	// public void getDatasByCssQuery() {
	// Rule rule = new Rule("http://www.11315.com/search",
	// new String[] { "name" }, new String[] { "兴网" },
	// "div.g-mn div.con-model", Rule.SELECTION, Rule.GET);
	// List<LinkTypeData> extracts = ExtractService.extract(rule);
	// printf(extracts);
	// }
}
