//全选及取消全选的方法
function seletstate(obj) {
	$("input[type='checkbox']").prop("checked", $(obj).is(':checked'));
}
function msgLayer(msg) {
	layer.open({
		title : '信息',
		content : msg
	});
}
//form表单转成json对象
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};
/*新开窗口*/
function Hui_admin_tab_js(title, href) {
	var bStop = false,
		bStopIndex = 0,
		topWindow = $(window.parent.document),
		show_navLi = topWindow.find("#min_title_list li"),
		iframe_box = topWindow.find("#iframe_box");
	//console.log(topWindow);
	if (!href || href == "") {
		alert("data-href不存在，v2.5版本之前用_href属性，升级后请改为data-href属性");
		return false;
	}
	if (!title) {
		alert("v2.5版本之后使用data-title属性");
		return false;
	}
	if (title == "") {
		alert("data-title属性不能为空");
		return false;
	}
	show_navLi.each(function() {
		if ($(this).find('span').attr("data-href") == href) {
			bStop = true;
			bStopIndex = show_navLi.index($(this));
			return false;
		}
	});
	if (!bStop) {
		creatIframe(href, title);
		min_titleList();
	} else {
		show_navLi.removeClass("active").eq(bStopIndex).addClass("active");
		iframe_box.find(".show_iframe").hide().eq(bStopIndex).show().find("iframe").attr("src", href);
	}
}

/**
 * @param obj
 * @param flag
 */
function input_import(obj, flag) {
	if (flag) {
		$(obj).parent().addClass("has-success");
		$(obj).parent().removeClass("has-error");
	} else {
		if ($(obj).parent().attr("class").lastIndexOf("not-null") != -1) {
			$(obj).parent().addClass("has-error");
		}
		$(obj).parent().removeClass("has-success");
	}
}

/**
 * 初始化表单验证
 */
function table_add_check() {
	var divs = $("." + formtable).children("div");
	divs.each(function(i, item) {
		// console.log($(item).attr("class"));
		if ($(item).attr("class").lastIndexOf("not-null") != -1) {
			$(item).addClass("has-error");
		}
	})
}

/**
 * 延时关闭提示框
 * 
 * @param timenum
 *            延时时间
 */
function Delayedclose(timenum) {
	setTimeout("$('#hintclose').trigger('click');", timenum);
}

/**
 * 表单内容验证
 * 
 * @param tableClassName表格class属性
 */
function formContentCheck(tableClassName) {
	var divs = $("." + tableClassName + " div");
	var flag = 0;
	divs.each(function(i, item) {
		flag = $(item).attr("class").lastIndexOf("has-error");
		if (flag != -1) {
			flag = $(item).children("input").length - 1;
			$(item).children("input").eq(flag).focus();
			flag = 0;
			return false;
		}
	});
	return flag;
}

/**
 * 发送ajax前先发送头消息
 */
function ajaxsendbefore() {
	var token = $("#token").val();
	var header = $("#header").val();
	$(document).ajaxSend(function(e, xhr, optios) {
		xhr.setRequestHeader(header, token);
	});
}

/**
 * 判断字符串在utf-8编码模式下,是否符合规定的长度
 * @param inputStr 要判断的字符串
 * @param min 最小长度(包含)
 * @param max 最大长度(包含)
 * @returns {Boolean}
 */
function counterStrLength(inputStr, min, max) {
	if (inputStr == null || inputStr.trim() == "") {
		return false;
	}
	if (inputStr.length < min) {
		return false;
	}
	var i = 0;
	var totalLength = 0;
	/* 计算utf-8编码情况下的字符串长度 */
	for (i = 0; i < inputStr.length; i++) {
		if (inputStr.charCodeAt(i) <= parseInt("0x7F")) {
			totalLength += 1;
		} else if (inputStr.charCodeAt(i) <= parseInt("0x7FF")) {
			totalLength += 2;
		} else if (inputStr.charCodeAt(i) <= parseInt("0xFFFF")) {
			totalLength += 3;
		} else if (inputStr.charCodeAt(i) <= parseInt("0x1FFFFF")) {
			totalLength += 4;
		} else if (inputStr.charCodeAt(i) <= parseInt("0x3FFFFFF")) {
			totalLength += 5;
		} else {
			totalLength += 6;
		}
	}
	if (totalLength > max) {
		return false;
	} else {
		return true;
	}
}
function stringToArray(str) {
	var array = new Array();
	if (str != null && str.length > 2) {
		str = str.substring(1, str.length - 1);
		var tempArray = str.split(",");
		for (var i = 0; i < tempArray.length; i++) {
			var obj = tempArray[i];
			array.push(obj.trim());
		}
	}
	return array;
}