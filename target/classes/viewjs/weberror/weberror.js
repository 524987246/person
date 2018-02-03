var urlarray = {
	InfoUrl : "/Reception/weberror/info.html",
	removeUrl : "/Reception/weberror/remove.html",
	addUrl : "/Reception/weberror/add.html",
	updateUrl : "/Reception/weberror/update.html"
};
$(function() {
	getPageInfo();
	$("input[type='checkbox']").prop("checked", false);
	$("#up_page").attr("disabled", "disabled").hide();
	$("#next_page").attr("disabled", "disabled").hide();
	$("#page_num").val(page_num);
});
var page_new = 0;// 当前页面
var page_num = 10;// 每页显示的条数
var remove_id = 0;// 删除数据时的主键id
var objarray;// 保存每次查询的结果
var obj_num;// 修改对象在数组的序号
var formtable = "tableContent";// 主要表单的名称
/**
 * 查询信息
 * 
 * @param str
 */
function getPageInfo(str) {
	// ajaxsendbefore();
	var msg = "{'page_new':" + page_new + ",'page_num':" + page_num + "}";
	if (str != null) {
		msg = msg.substring(0, msg.length - 1) + ",'str':" + str + "}";
	}
	var url = ProjectUrl(urlarray.InfoUrl);
	$.ajax({
		url : url,
		type : "POST",
		data : msg,
		async : true,
		contentType : "application/json",
		dataType : "json",
		success : function(data) {
			var flag = false;
			// data=data.replace(/\\/g,'');
			// console.log(data);
			var info = $.parseJSON(data);
			//console.log(info);
			objarray = info;
			$("#tableinfo tr:gt(0)").remove();
			var html = "";
			if (info.length == 0) {
				html = "<tr><td colspan='6' rowspan='3'>查无数据</td></tr>";
				$("#tableinfo").append(html);
			}
			$.each(info, function(i, item) {
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
	html += '</td><td>' + datanull(item.serrorid) + '</td><td>'
			+ datanull(item.sname);
	html += '</td><td>' + datanull(item.sreason) + '</td><td>'
			+ datanull(item.sresolvent);
	html += '</td><td><a title="查看" href="javascript:void(0)"'
			+ 'style="color:#14a5f1" onclick="updatedlg(' + i
			+ ',this,1)"><span  class="glyphicon glyphicon-search"'
			+ ' ></span></a>&nbsp;&nbsp;&nbsp;';
	html += '<a title="修改" href="javascript:void(0)" style="color:green"'
			+ 'onclick="updatedlg(' + i + ',this,2)"><span class="glyphicon '
			+ 'glyphicon-pencil" ></span></a>&nbsp;&nbsp;&nbsp;';
	html += '<a title="删除" href="javascript:void(0)"'
			+ 'style="color:red" onclick="remove_dig(' + i
			+ ')"><span  class="glyphicon glyphicon-remove"' + ' ></span></a>';
	html += '</td><td><input type="checkbox" value="' + i + '"></td></tr>';
	return html;
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
	if (num != null) {
		page_new += num;
	}
	var inputs = $(".query");
	var html = '"{';
	var temp = "";
	inputs.each(function(index, item) {
		if ($(item).val().trim() != "") {
			temp = "'" + $(item).attr("name") + "':";
			if ($(item).attr("datatype") == "number") {
				temp += $(item).val().trim() + ",";
			} else {
				temp += "'" + $(item).val().trim() + "',";
			}
			html += temp;
		}
	})
	html = html.substring(0, html.length - 1);
	html += '}"';
	if (html.trim().length == 3) {
		getPageInfo();
		return;
	}
	getPageInfo(html);
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
