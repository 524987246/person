package org.great.util.spider.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.great.util.spider.bean.FiveEight;

public class ResultToObj {
	/**
	 * 返回58对象
	 * 
	 * @param results
	 * @return
	 */
	public static List<Object> ToFiveEight(Elements results) {
		List<Object> datas = new ArrayList<Object>();

		for (Element result : results) {

			Elements links = result.getElementsByTag("dl");
			for (int i = 0; i < links.size(); i++) {
				FiveEight data = new FiveEight();
				Element link = links.get(i);
				// 必要的筛选
//				 String linkHref = link.attr("href");
//				 String linkText = link.text();
//				String str = link.toString();
				// 输出获取到的html代码
//				 System.out.println(str);
				Elements dttag = link.getElementsByTag("dt");
				for (Element dttagtemp : dttag) {
					// 获取标题
					Elements atag = dttagtemp.getElementsByTag("a");
					data.setTitlename(atag.text());
					// 获取要求
					Elements spantag = dttagtemp.getElementsByTag("span");
					data.setRequirement(spantag.text());
					getFiveEightInfo(data,spantag.attr("onclick"));
				}
				Elements temp1 = link.getElementsByTag("dd");
				// 页面上为选择框，无用
				// data.setRequirement(temp1.get(0).text());
				// 公司名称
				data.setCompany(temp1.get(1).text());
				// 地址
				data.setAddress(temp1.get(2).text());
				// 时间
				data.setTime(temp1.get(2).text());
				datas.add(data);
			}
		}
		return datas;
	}

	public static FiveEight getFiveEightInfo(FiveEight fiveEight,String url) {
		//http://zp.service.58.com/api/infoPreview?
		//infoid=25842332669774&
		//callback=jQuery1102034422953448290805_1494828117712
		//&_=1494828117730
		//获取详细信息
		System.out.println(url);
		return fiveEight;
	}
}
