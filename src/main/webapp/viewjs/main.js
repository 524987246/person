$(function() {
	//getUserInfo();
});
function refreshBase() {
	var url = "Reception/base/refreshBase.html";
	url = ProjectUrl(url);
	$.ajax({
		url : url,
		type : "POST",
		async : true, //异步请求,默认true
		contentType : "application/json;charset=UTF-8",
		dataType : "json",
		success : function(data) {
			var index = layer.open({
				title : "信息",
				content : data.errmsg,
				btn : [ '确认', '取消' ],
				btn1 : function(index, layero) {
					location.replace(location.href);
				}
			});
		},
		error : function(data) {
			msgLayer("请求错误");
		}
	});
}
/*个人信息*/
function myselfinfo() {
	layer.open({
		type : 1,
		area : [ '300px', '200px' ],
		fix : false, //不固定
		maxmin : true,
		shade : 0.4,
		title : '查看信息',
		content : '<div>管理员信息</div>'
	});
}
//暂无使用
function getUserInfo() {
	var url = "Reception/sys/user/get.html";
	url = ProjectUrl(url);
	$.ajax({
		url : url,
		type : "POST",
		async : true, //异步请求,默认true
		contentType : "application/json;charset=UTF-8",
		dataType : "JSON",
		success : function(data) {
			//dir(data);
			createMenu(data.menulist);
		},
		error : function(data) {
			hint("请求异常");
		}
	});
}
var createMenu = function(menulist) {
	var firstMenu = new Array();
	for (var i = 0; i < menulist.length; i++) {
		var menu = menulist[i];
		if (menu.parentId == 0) {
			firstMenu.push(menu);
		}
	/*<dl id="menu-article">
		<dt>
			<i class="Hui-iconfont">&#xe616;</i> 资讯管理
			<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
		</dt>
		<dd>
			<ul>
				<li><a data-href="article-list.html" data-title="资讯管理"
					href="javascript:void(0)">资讯管理</a></li>
			</ul>
		</dd>
	</dl>*/
	}

	for (var i = 0; i < firstMenu.length; i++) {
		var template = '<li class="navbar-levelone"><a href="javascript:;">CONTENT</a></li>'
		var menu = firstMenu[i];
		var html = "";
		if (i == 0) {
			html = template.replace(/CONTENT/g, menu.name)
				.replace(/navbar-levelone/g, "navbar-levelone current");
		} else {
			html = template.replace(/CONTENT/g, menu.name);
		}
		dir(html);
		$(".firstmenu").append(html);
	}
}
$('#logout').on('click', function() {
	layer.confirm('确认退出？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		layer.close();
		ResetCookie();
		var url = "Reception/base/logout.html";
		url = ProjectUrl(url);
		window.location.href = url;
	}, function() {
		layer.close();
	});
});
function GetPwdAndChk() {
	var loginName = GetLastUser();
	var password = GetCookie(loginName);
	if (password != null) {
		$("#chkRememberPwd").prop('checked', true);
		$("#password").val(password);
	} else {
		$("#chkRememberPwd").prop('checked', false);
		$("#password").val("");
	}
}
function SetCookie(name, value, expires) {
	var argv = SetCookie.arguments;
	var argc = SetCookie.arguments.length;
	var expires = (argc > 2) ? argv[2] : null;
	var path = (argc > 3) ? argv[3] : "/";
	var domain = (argc > 4) ? argv[4] : null;
	var secure = (argc > 5) ? argv[5] : false;
	document.cookie = name + "=" + escape(value)
	+ ((expires == null) ? "" : ("; expires=" + expires
		.toGMTString()))
	+ ((path == null) ? "" : ("; path=" + path))
	+ ((domain == null) ? "" : ("; domain=" + domain))
	+ ((secure == true) ? "; secure" : "");
}
function ResetCookie() {
	var loginName = GetLastUser();
	if (loginName == null || loginName == "") {
		return;
	}
	var expdate = new Date();
	SetCookie(loginName, null, expdate);
}
function GetLastUser() {
	var id = "49BAC005-7D5B-4231-8CEA-16939BEACD69"; //GUID标识符
	var loginName = GetCookie(id);
	return loginName;
}
function GetCookie(name) {
	var arg = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	while (i < clen) {
		var j = i + alen;
		//alert(j);
		if (document.cookie.substring(i, j) == arg)
			return getCookieVal(j);
		i = document.cookie.indexOf(" ", i) + 1;
		if (i == 0)
			break;
	}
	return null;
}
function getCookieVal(offset) {
	var endstr = document.cookie.indexOf(";", offset);
	if (endstr == -1)
		endstr = document.cookie.length;
	return unescape(document.cookie.substring(offset, endstr));
}