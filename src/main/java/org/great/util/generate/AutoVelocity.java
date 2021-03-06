package org.great.util.generate;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.great.util.FileUtil;
import org.great.util.NameRandom;
import org.great.util.myutil.MyStringUtils;
import org.great.util.myutil.MyZipUtil;
import org.great.web.jdbc.ColumnEntity;

public class AutoVelocity {

	// 模板集合
	private List<Template> templatelist = new ArrayList<Template>();
	// 文件路径集合
	private List<String> list = new ArrayList<String>();
	// baseBean已含有属性
	private Set<String> butset = new HashSet<String>();
	// 生成代码路径
	private String filepath = "\\download\\";

	public AutoVelocity() {
		butset.add("id");
		butset.add("create_by");
		butset.add("create_date");
		butset.add("update_by");
		butset.add("update_date");
		butset.add("remarks");
		butset.add("isemploy");
		// 初始化模板引擎
		VelocityEngine ve = new VelocityEngine();
		// 配置velocity 输出编码格式
		ve.setProperty("input.encoding", "utf-8");
		ve.setProperty("output.encoding", "utf-8");
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
		// 获取模板文件
		// 模板文件路径在src/main/resources下
		templatelist.add(ve.getTemplate("template\\bean.vm"));
		templatelist.add(ve.getTemplate("template\\mapper.vm"));
		templatelist.add(ve.getTemplate("template\\service.vm"));
		templatelist.add(ve.getTemplate("template\\controller.vm"));
		templatelist.add(ve.getTemplate("template\\xml.vm"));
		templatelist.add(ve.getTemplate("template\\list.jsp.vm"));
		templatelist.add(ve.getTemplate("template\\list.js.vm"));
		templatelist.add(ve.getTemplate("template\\one.jsp.vm"));
		templatelist.add(ve.getTemplate("template\\one.js.vm"));
		// 临时文件生成的目标路径,压缩完成后删除
		list.add("src\\test\\replaceflage.java");
		list.add("src\\test\\replaceflageMapper.java");
		list.add("src\\test\\replaceflageService.java");
		list.add("src\\test\\replaceflageController.java");
		list.add("src\\test\\replaceflage.xml");
		list.add("src\\test\\replaceflage-list.jsp");
		list.add("src\\test\\replaceflage-list.js");
		list.add("src\\test\\replaceflage-one.jsp");
		list.add("src\\test\\replaceflage-one.js");
	}

	public String autocode(List<ColumnEntity> resultlist, String tbname) {
		ArrayList<String> filenamelist = new ArrayList<String>();
		//
		VelocityContext ctx = new VelocityContext();
		// String path = "";
		// 生成模板文件
		ctx.put("tablename", tbname);
		String fileName = getFileName(tbname);
		ctx.put("objectName", fileName);
		ctx.put("package",getPackageName(tbname));
		ctx.put("url", MyStringUtils.setStringByChar(tbname, "_", "/"));
		ctx.put("auth", MyStringUtils.setStringByChar(tbname, "_", ":"));
		tbname = FileUtil.setfilenam(tbname);
		resultlist = setAttrType(resultlist);// 设置java属性
		ctx.put("name", tbname);
		String str = tbname.substring(0, 1).toLowerCase() + tbname.substring(1);
		ctx.put("name2", str);// 首字母小写
		ctx.put("collist", resultlist);

		for (int i = 0; i < templatelist.size(); i++) {
			Template template = templatelist.get(i);

			// System.out.println(sw.toString());
			// bean 不用装编码模式,其他需要用iso8859-1转成utf-8
			String name = list.get(i);
			if (list.get(i).lastIndexOf(".js") != -1) {
				name=name.replace("replaceflage", fileName);
			} else if (list.get(i).lastIndexOf(".jsp") != -1) {
				name=name.replace("replaceflage", fileName);
			} else {
				name=name.replace("replaceflage", tbname);
			}
			// 输出
			StringWriter sw = new StringWriter();
			template.merge(ctx, sw);
			FileUtil.writeFile2(sw.toString(), name);
			filenamelist.add(name);
		}
		// 压缩
		String zipFileName = NameRandom.filename(null) + ".zip";
		try {
			boolean bo = MyZipUtil.zipCompression(filepath + zipFileName, filenamelist);
			if (bo) {
				// 删除文件
				for (String filename : filenamelist) {
					File file = new File(filename);
					FileUtil.deleteFile(file);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zipFileName;
	}

	public List<ColumnEntity> setAttrType(List<ColumnEntity> list) {
		List<ColumnEntity> templist = new ArrayList<ColumnEntity>();
		// 设置数据与java类型匹配
		for (ColumnEntity temp : list) {
			String str = "";
			str = temp.getColumnName();
			if (butset.contains(str)) {
				continue;
			}
			// 属性首字符大写
			str = temp.getColumnName();
			// 下划线名称进行更换
			temp.setAttrName(MyStringUtils.setColName2(str));
			temp.setAttrname(MyStringUtils.setColName(str));
			str = temp.getDataType();
			if (str.equals("int")) {
				temp.setAttrType("Long");
			} else if (str.equals("varchar")) {
				temp.setAttrType("String");
			} else if (str.equals("double")) {
				temp.setAttrType("Double");
			} else if (str.equals("datetime")) {
				temp.setAttrType("String");
			} else if (str.equals("char")) {
				temp.setAttrType("String");
			} else if (str.equals("bigint")) {
				temp.setAttrType("Long");
			} else if (str.equals("date")) {
				temp.setAttrType("String");
			} else {
				temp.setAttrType("请为数据库类型\"" + str + "\"设置对应java类型");
			}

			templist.add(temp);
		}
		return templist;

	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public static String getFileName(String name) {
		int i = name.lastIndexOf("_");
		name = name.substring(i + 1);
		return name;
	}

	public static String getPackageName(String name) {
		int i = name.lastIndexOf("_");
		if(i>0){
			name = name.substring(0,i);
		}
		return name;
	}
}
