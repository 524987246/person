var urlarray = {
	DbInfourl : "Reception/manage/dbinfo.html",
	InfoUrl : "Reception/manage/info.html",
	conUrl : "Reception/manage/connection.html",
	dbnameUrl : "Reception/manage/dbnames.html",
	tbnameUrl : "Reception/manage/tbnames.html",
	codeUrl : "Reception/manage/code.html",
	addUrl : "Reception/web/add.html",
	updateUrl : "Reception/web/update.html"
};
$(function() {
	// getPageInfo();
	getDBname();
	$("input[type='checkbox']").prop("checked", false);
	$("#up_page").attr("disabled", "disabled").hide();
	$("#next_page").attr("disabled", "disabled").hide();
	$("#page_num").val(page_num);
});
var page_new = 0;// 当前页面
var page_num = 10;// 每页显示的条数
var remove_id = 0;// 删除数据时的主键id
var dbarray;// 保存数据库信息集合
var objarray;// 保存每次查询的结果
var obj_num;// 修改对象在数组的序号
var formtable = "tableContent";// 主要表单的名称
var username = "";// 保存测试连接成功的账号
var userpwd = "";// 保存测试连接成功的密码
var tablearray;// 保存当前界面表格信息

/**
 * 获取数据库名称
 */
function getDBname() {
	var url = ProjectUrl(urlarray.DbInfourl);
	$.ajax({
		url : url,
		type : "POST",
		async : true,
		dataType : "json",
		contentType : "application/json",
		dataType : "json",
		success : function(data) {
			var info = JSON.parse(data);
			var html = "";
			var select = $("#dbtype");
			dbarray = info;
			obj_num = 0;
			for ( var i = 0; i < info.length; i++) {
				html = "<option value='" + i + "'>" + info[i].stype
						+ "</option>";
				$(select).append(html);
			}
		},
		error : function(data) {
			hint("请求异常");
		}
	});
}
/**
 * 更换数据库类型
 * 
 * @param obj
 */
function changeDbType(obj) {
	obj_num = $(obj).val();
}
/**
 * 测试连接
 */
function test_connection() {
	var url = ProjectUrl(urlarray.conUrl);
	var inputs = $(".query");
	username = $(".query").eq(0).val();
	if (username.trim() == "") {
		$(".query").eq(0).focus();
		hint("请输入账号");
		Delayedclose(5000);
		return;
	}
	userpwd = $(".query").eq(1).val();
	if (userpwd.trim() == "") {
		$(".query").eq(0).focus();
		hint("请输入密码");
		Delayedclose(5000);
		return;
	}
	var getdata = formConObj("form-inline");
	getdata.sdriver = dbarray[obj_num].sdriver;
	getdata.stype = dbarray[obj_num].stype;
	getdata.sname = dbarray[obj_num].sname;
	$.ajax({
		url : url,
		type : "POST",
		data : getdata,
		async : true,
		success : function(data) {
			if (data == "true") {
				hint("连接成功");
			} else if (data == "false") {
				inituser();
				hint("连接失败");
			} else {
				hint("异常数据===>" + data);
			}
		},
		error : function(data) {
			inituser();
			hint("请求异常");
		}
	});
}
function inituser() {
	username = "";
	userpwd = "";
}
/**
 * 查询所有数据库(实例化)名称
 */
function find_db() {
	var url = ProjectUrl(urlarray.dbnameUrl);
	var inputs = $(".query");
	if ($(".query").eq(0).val().trim() == "") {
		$(".query").eq(0).focus();
		hint("请输入数据库地址");
		Delayedclose(5000);
		return;
	}
	username = $(".query").eq(1).val();
	if (username.trim() == "") {
		$(".query").eq(1).focus();
		hint("请输入账号");
		Delayedclose(5000);
		return;
	}
	userpwd = $(".query").eq(2).val();
	if (userpwd.trim() == "") {
		$(".query").eq(2).focus();
		hint("请输入密码");
		Delayedclose(5000);
		return;
	}
	var getdata = formConObj("form-inline");
	getdata.sdriver = dbarray[obj_num].sdriver;
	getdata.stype = dbarray[obj_num].stype;
	$.ajax({
		url : url,
		type : "POST",
		data : getdata,
		async : true,
		dataType : "json",
		success : function(data) {
			if (data.indexOf("false") != -1) {
				inituser();
				hint("连接失败");
			} else {
				var table = $("#tables");
				var html = "";
				for ( var i = 0; i < data.length; i++) {
					if (i % 5 == 0) {
						html += "<tr>"
					}
					html += "<td><a onclick='settbname(this)'>" + data[i]
							+ "</a></td>";
					if (i % 5 == 4) {
						html += "</tr>"
					} else if (i == data.length - 1) {
						html += "</tr>"
					}
				}
				$("#tables").html("");
				$("#tables").append(html);
			}
		},
		error : function(data) {
			console.log(data);
			inituser();
			hint("连接失败");
		}
	});
}

/**
 * 查询数据库中所有表名
 */
function find_tb(sname) {
	var url = ProjectUrl(urlarray.tbnameUrl);
	if (username.trim() == "" || userpwd.trim() == "") {
		hint("请先测试连接");
		return;
	}
	var getdata = formConObj("form-inline");
	getdata.username = username;
	getdata.userpwd = userpwd;
	getdata.sdriver = dbarray[obj_num].sdriver;
	getdata.stype = dbarray[obj_num].stype;
	getdata.sname = sname;
	getdata.page_new = page_new;
	getdata.page_num = page_num;
	$.ajax({
		url : url,
		type : "POST",
		data : getdata,
		async : true,
		dataType : "json",
		success : function(data) {
			if (data.indexOf("false") != -1) {
				hint("连接失败");
			} else {
				// data是一个数组,存放表名称集合
				//console.dir(data);
				var flag = false;
				tablearray = data;
				var html = "";
				$("#tableinfo").html(html);
				if (tablearray.length == 0) {
					html = "<tr><td colspan='6' rowspan='3'>查无数据</td></tr>";
					$("#tableinfo").append(html);
				}
				$("#newname").text(getdata.sname);
				var table = $("#tableinfo");

				$.each(tablearray, function(i, item) {
					if (i != page_num) {
						html = tdInfo(i, item);
						$("#tableinfo").append(html);
					}
					if (i >= page_num) {
						flag = true;
					}
				});
				$("#page_new").text((page_new + 1));
				pageMethod(flag);
			}
		},
		error : function(data) {
			hint("请求异常");
		}
	});
}

/**
 * 生成主表信息
 * 
 * @param i
 * @param item
 * @returns {String}
 */
function tdInfo(i, item) {
	var html = "";
	html = '<tr><td>' + (page_new * page_num + i + 1);
	html += '</td><td>' + datanull(item.tableName) + '</td><td>'
			+ datanull(item.createTime);
	html += '</td><td>' + datanull(item.ENGINE) + '</td><td>'
			+ datanull(item.tableComment);
	html += '</td><td>';
	html += '<a title="代码生成" href="javascript:void(0)"'
			+ 'style="color:red" onclick="generate(' + i
			+ ')"><span  class="glyphicon glyphicon-remove"' + ' ></span></a>';
	html += '</td><td><input type="checkbox" value="' + i + '"></td></tr>';
	return html;
}


/**
 * 开始生成代码
 * @param i
 */
function generate(i) {
	var getdata = formConObj("form-inline");
	getdata.username = username;
	getdata.userpwd = userpwd;
	getdata.sdriver = dbarray[obj_num].sdriver;
	getdata.stype = dbarray[obj_num].stype;
	getdata.sname = $("#newname").text();
	console.log(i);
	console.dir(tablearray);
	getdata.tbname = tablearray[i].tableName;
	console.dir(getdata);
	//TODO 自动生成有问题，报以下错误
	//ERROR 8604 --- [nio-8081-exec-6] org.apache.velocity
	//: ResourceManager : unable to find resource 
	//'jsp/generate/generate.html.vm' 
	//in any resource loader.
	var url = ProjectUrl(urlarray.codeUrl);
	$.ajax({
		url : url,
		type : "POST",
		data : getdata,
		async : true,
		dataType : "json",
		success : function(data) {
			console.dir(data);
			alert(data);
		},
		error : function(data) {
			hint("请求异常");
		}
	});
}
/**
 * 分页方法
 */
function pageMethod(bo) {
	if (bo) {
		$("#next_page").removeAttr("disabled").show();
	} else {
		$("#next_page").attr("disabled", "disabled").hide();
	}
	if (page_new == 0) {
		$("#up_page").attr("disabled", "disabled").hide();
	} else {
		$("#up_page").removeAttr("disabled").show();
	}
}
/**
 * 查询方法
 */
function search(num) {
	var sname=$("#newname").text();
	if (num != null) {
		page_new += num;
	}
	find_tb(sname);
}
/**
 * 显示
 */
function settbname(obj) {
	var sname=$(obj).text();
	page_new = 0;//
	page_num = 10;//
	find_tb(sname);
}
/**
 * 改变当前页显示条数
 * 
 * @param obj
 */
function changepagenum(obj) {
	page_num = $(obj).val();
}

/**
 * 打开删除提示框
 * 
 * @param obj_num
 * 
 */
function remove_dig(obj_num) {
	remove_id = objarray[obj_num].sid;
	$("#removeDlg").trigger("click");
}

/**
 * 批量删除
 */
function batchdel() {
	remove_id = "";
	var checkboxs = $("td input[type='checkbox']");
	checkboxs.each(function(index, item) {
		if ($(item).is(':checked')) {
			remove_id += objarray[index].sid + ",";
		}
	})
	remove_id = remove_id.substring(0, remove_id.length - 1);
	if (remove_id.length == 0) {
		hint("请选择后再删除");
		return;
	}
	console.log(remove_id);
	$("#removeDlg").trigger("click");
}
/**
 * 确定删除按钮
 */
function remove_save() {
	var url = ProjectUrl(urlarray.removeUrl);
	$.ajax({
		url : url,
		type : "POST",
		data : "" + remove_id,
		async : true,
		dataType : "json",
		contentType : "application/json",
		success : function(data) {
			if (data) {
				$("#removeInfo").hide();
				hint("删除成功");
				if (objarray.length == 1 && page_new != 0) {
					page_new -= 1;
				}
				search();
			}
		},
		error : function(data) {
			hint("请求异常");
		}
	});
}
/**
 * 添加信息
 */
function addInfo() {
	table_add_check();// 初始化表单验证
	$(".titleInfo").text("添加");
	$(".inputcontent").val("");
	$(".inputcontent").removeAttr("disabled");
	$("#addOrUpdateDlg").trigger("click");
}
/**
 * 更新信息
 * 
 * @param num
 * @param obj
 * @param flag
 *            标志 1查看 2修改
 */
function updatedlg(num, obj, flag) {
	table_add_check();// 初始化表单验证

	if (flag == 1) {
		$(".titleInfo").text("查看信息");
		$(".inputcontent").attr("disabled", "true");
	} else if (flag == 2) {
		$(".titleInfo").text("修改信息");
		$(".inputcontent").removeAttr("disabled");
	}
	var content = $(obj).parent().parent().children("td");
	var inputcontent = $(".inputcontent");
	inputcontent.each(function(i, item) {
		if (i == 0) {
			$(item).val(objarray[num].sid);
		} else {
			$(item).val($(content).eq(i).text());
		}
		$(item).parent().removeClass("has-error");
	})
	obj_num = num;
	$("#addOrUpdateDlg").trigger("click");
}

/**
 * 添加或修改的保存方法
 */
function update_save() {
	var flag = formContentCheck(formtable);
	// console.log(flag);
	// 表单验证结果
	if (flag != -1) {
		return;
	}
	var url = "";
	var getdata;
	var str;
	if ($(".titleInfo").text() == "修改信息") {
		getdata = formConObj(formtable, objarray[obj_num]);
		if (getdata == null) {
			$("#close_addOrUpdate_dlg").trigger("click");
			hint("信息无改动");
			return;
		}
		url = ProjectUrl(urlarray.updateUrl);
		str = "修改";
	} else if ($(".titleInfo").text() == "添加") {
		url = ProjectUrl(urlarray.addUrl);
		getdata = formConObj(formtable);
		str = "添加";
	} else {
		$("#close_addOrUpdate_dlg").trigger("click");
		return;
	}
	$.ajax({
		url : url,
		type : "POST",
		data : getdata,
		async : true,
		dataType : "json",
		success : function(data) {
			$("#addOrUpdate").hide();
			if (data == "true") {
				hint(str + "成功");
				search();
			} else if (data == "false") {
				hint(str + "失败");
			} else {
				hint(data);
			}
			Delayedclose(5000);
		},
		error : function(data) {
			hint("请求异常");
		}
	});
}
