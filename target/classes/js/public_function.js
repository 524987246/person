function dir(obj) {
	if (console == null) {
		alert(obj);
		return;
	}
	console.dir(obj);
}
//判断浏览器类型
function myBrowser() {
	var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
	var isOpera = userAgent.indexOf("Opera") > -1;
	if (isOpera) {
		return "Opera"
	}
	//判断是否Opera浏览器
	if (userAgent.indexOf("Firefox") > -1) {
		return "Firefox";
	}
	//判断是否Firefox浏览器
	if (userAgent.indexOf("Chrome") > -1) {
		return "Chrome";
	}
	;
	if (userAgent.indexOf("Safari") > -1) {
		return "Safari";
	}
	;
	//判断是否Safari浏览器
	if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
		return "IE";
	}
	; //判断是否IE浏览器
}
function isJSON(str) {
	if (typeof str == 'string') {
		try {
			var obj = JSON.parse(str);
			if (typeof obj == 'object' && obj) {
				return true;
			} else {
				return false;
			}

		} catch (e) {
			console.log('error：' + str + '!!!' + e);
			return false;
		}
	}
	console.log('It is not a string!')
}
/**
 * 项目项目根路径
 * http://127.0.0.1:8080/.....
 * @return 127.0.0.1:8080
 */
function getRootUrl() {
	var str = window.location.ancestorOrigins[0];
	str = str.split("://")[1];
	return str;
}
function ProjectUrl(str) {
	// var currentPath = window.document.location.href;
	// 获取主机地址之后的目录，如： /ssm/index.jsp
	// var pathName = window.document.location.pathname;
	// var pos = currentPath.indexOf(pathName);
	// 获取带"/"的项目名，如：/ssm
	// var projectName = pathName
	// .substring(0, pathName.substr(1).indexOf('/') + 1);
	str = basePath + "/" + str;
	return str;
}
// html特殊字符反编译
function charTrans(str) {
	if (str != null) {
		str = str.replace(/\&gt;/g, ">");
		str = str.replace(/\&quot;/g, '"');
		str = str.replace(/\&lt;/g, "<");
		str = str.replace(/\&amp;/g, "&");
	} else {
		str = "";
	}
	return str;
}

// html标签语言转换
// 服务器接收数据需全部替换.replaceAll("-<-", "<")
function charCompila(str) {
	if (str != null) {
		str = str.replace(/\</g, "-<-");
	} else {
		str = "";
	}
	return str;
}
// 过长文本截取
function PaperIntercep(obj) {
	var content = datanull(obj);
	if (content.length > 8) {
		content = content.substring(0, 8) + "<span color='green'>...</span>";
	}
	return content;
}

// 验证手机号码的格式
function checkPhone(phoneObj) {
	var checkFormat = /^((1[3-8]{1}[0-9]{1})+\d{8})$/;
	if (!checkFormat.test($(phoneObj).val())) {
		return false;
	}
	return true;
}

// 验证emaill的格式
function checkEmail(emailObj) {
	var checkFormat = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	if (!checkFormat.test($(emailObj).val())) {
		return false;
	}
	return true;
}
// 判断验证码输入是否正确
function checkCode(str) {
	// 除数字字母外
	if (str.length != 4) {
		return false;
	}
	for (var i = 0; i < str.length; i++) {
		if (str.charCodeAt(i) < 48
			|| (str.charCodeAt(i) > 57 && str.charCodeAt(i) < 65)
			|| (str.charCodeAt(i) > 90 && str.charCodeAt(i) < 97)
			|| str.charCodeAt(i) > 122) {
			return false;
		}
	}
	return true;
}


// 正整数验证
function checkNum(val) {
	var checktest = /^[0-9]*[0-9][0-9]*$/; // /^-?\\d+$/
	if (checktest.test($(val).val())) {
		return true;
	}
	return false;
}


// 是否是有效的身份证(中国)
function isIDCard(sidno) {
	var iSum = 0;
	var sId = $(sidno).val();
	var aCity = {
		11 : "北京",
		12 : "天津",
		13 : "河北",
		14 : "山西",
		15 : "内蒙古",
		21 : "辽宁",
		22 : "吉林",
		23 : "黑龙江",
		31 : "上海",
		32 : "江苏",
		33 : "浙江",
		34 : "安徽",
		35 : "福建",
		36 : "江西",
		37 : "山东",
		41 : "河南",
		42 : "湖北",
		43 : "湖南",
		44 : "广东",
		45 : "广西",
		46 : "海南",
		50 : "重庆",
		51 : "四川",
		52 : "贵州",
		53 : "云南",
		54 : "西藏",
		61 : "陕西",
		62 : "甘肃",
		63 : "青海",
		64 : "宁夏",
		65 : "新疆",
		71 : "台湾",
		81 : "香港",
		82 : "澳门",
		91 : "国外"
	};

	if (!(/^\d{17}(\d|x)$/i.test(sId))) {
		return false;
	}
	sId = sId.replace(/x$/i, "a");
	// 非法地区
	if (!aCity[parseInt(sId.substr(0, 2))]) {
		return false;
	}

	var sBirthday = sId.substr(6, 4) + "-" + Number(sId.substr(10, 2)) + "-" + Number(sId.substr(12, 2));

	var d = new Date(sBirthday.replace(/-/g, "/"));

	// 非法生日
	if (sBirthday != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate())) {
		return false;
	}
	for (var i = 17; i >= 0; i = i - 1) {
		iSum += (Math.pow(2, i) % 11) * parseInt(sId.charAt(17 - i), 11);
	}
	if (iSum % 11 != 1) {
		return false;
	}
	return true;
}


/**
 * 头像显示
 * 
 * @param file
 */
function preview(file) {
	if (file.files && file.files[0]) {
		var reader = new FileReader();
		reader.onload = function(evt) {
			$(file).next().html('<img  height=180px width=180px  src="' + evt.target.result + '" />');
		}
		reader.readAsDataURL(file.files[0]);
	} else {
		$(file).next().html('<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>')
	}
}
/**
 * 数据空值替换
 * 
 * @param str
 * @returns
 */
function datanull(str) {
	if (str == null) {
		str = "";
	}
	return str;
}
/**
 * 表格数据转换成对象
 * 
 * @param tableClassName表格class属性,原始对象,主键名称
 * @returns {对象} 修改时,如与原来数据相同,则返回空
 */
function formConObj(tableClassName, object) {
	// 创建对象
	var data = {};
	// 存储对象属性
	var nameary = [];
	// 获取所有input对象
	var inputs = $("." + tableClassName + " input");
	// 遍历
	inputs.each(function(i, item) {
		if ($(item).val().trim() != '') {
			// 为对象添加属性
			data[$(item).attr("name")] = $(item).val().trim();
		}
		nameary.push($(item).attr("name"));
	});
	var textareas = $("." + tableClassName + " textarea");
	textareas.each(function(i, item) {
		if ($(item).val().trim() != '') {
			data[$(item).attr("name")] = $(item).val().trim();
		}
		nameary.push($(item).attr("name"));
	});
	// var selects=$("."+tableClassName+" select");
	// selects.each(function(i, item) {
	// if($(item).val()!=''){
	// data[$(item).attr("name")]=$(item).val().trim();
	// }
	// nameary.push($(item).attr("name"));
	// });
	// 判断新旧对象属性值是否相同,如果相同即返回null,不相同返回对象
	if (object != null) {
		for (var i = 0; i < nameary.length; i++) {
			var strtable = data[nameary[i]] == null ? "" : data[nameary[i]];
			var strobj = object[nameary[i]] == null ? "" : object[nameary[i]];
			if (strtable != strobj) {
				return data;
			}
		}
		return null;
	}
	return data;
}




/**
 * 数字校验
 * 
 * @param obj
 */
function num_input_check(obj) {
	$(obj).val($(obj).val().replace(/\D/g, ''));
	var flag = ($(obj).val().trim().length != 0);
	input_import(obj, flag);
}