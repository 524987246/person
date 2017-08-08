//获取最后登录用户名
function GetLastUser() {
	var id = "49BAC005-7D5B-4231-8CEA-16939BEACD67"; //GUID标识符
	var username = GetCookie(id);
	if (username != null) {
		vm.username = username;
	} else {
		vm.username = "";
	}
	GetPwdAndChk();
}
//cookies 记录账号密码
function SetPwdAndChk() {
	//取用户名
	var username = vm.username;
	//将最后一个用户信息写入到Cookie
	SetLastUser(username);
	//如果记住密码选项被选中
	if ($("#chkRememberPwd").prop('checked') == true) {
		//取密码值
		var password = vm.password;
		var expdate = new Date();
		//设置过期时长
		expdate.setTime(expdate.getTime() + 14 * (24 * 60 * 60 * 1000));
		//将用户名和密码写入到Cookie
		SetCookie(username, password, expdate);
	} else {
		//如果没有选中记住密码,则立即过期
		ResetCookie();
	}
}
function SetLastUser(username) {
	var id = "49BAC005-7D5B-4231-8CEA-16939BEACD67";
	var expdate = new Date();
	//当前时间加上两周的时间
	expdate.setTime(expdate.getTime() + 14 * (24 * 60 * 60 * 1000));
	SetCookie(id, username, expdate);
}
//用户名失去焦点时调用该方法

function GetPwdAndChk() {
	var username = vm.username;
	var password = GetCookie(username);
	if (password != null) {
		$("#chkRememberPwd").prop('checked', true);
		vm.password = password;
	} else {
		$("#chkRememberPwd").prop('checked', false);
		vm.password = "";
	}
}
//取Cookie的值

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
//获取cookie值
function getCookieVal(offset) {
	var endstr = document.cookie.indexOf(";", offset);
	if (endstr == -1)
		endstr = document.cookie.length;
	return unescape(document.cookie.substring(offset, endstr));
}
//设置cookie值
function SetCookie(name, value, expires) {
	var argv = SetCookie.arguments;
	//本例中length = 3
	var argc = SetCookie.arguments.length;
	var expires = (argc > 2) ? argv[2] : null;
	var path = (argc > 3) ? argv[3] : null;
	var domain = (argc > 4) ? argv[4] : null;
	var secure = (argc > 5) ? argv[5] : false;
	document.cookie = name
	+ "="
	+ escape(value)
	+ ((expires == null) ? "" : ("; expires=" + expires
		.toGMTString()))
	+ ((path == null) ? "" : ("; path=" + path))
	+ ((domain == null) ? "" : ("; domain=" + domain))
	+ ((secure == true) ? "; secure" : "");
}
//刷新cookie值
function ResetCookie() {
	var username = vm.username;
	var expdate = new Date();
	SetCookie(username, null, expdate);
}