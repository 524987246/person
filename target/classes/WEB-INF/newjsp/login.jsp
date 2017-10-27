<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link href="${ctx}/statis/newjsp/static/h-ui/css/H-ui.min.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx}/statis/newjsp/static/h-ui.admin/css/H-ui.login.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx}/statis/newjsp/static/h-ui.admin/css/style.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx}/statis/newjsp/lib/Hui-iconfont/1.0.8/iconfont.css"
	rel="stylesheet" type="text/css" />
<script src="${ctx}/js/jquery-2.2.1.js"></script>
<script type="text/javascript"
	src="${ctx}/statis/newjsp/static/h-ui/js/H-ui.min.js"></script>
<script src="${ctx}/js/cookieUtil.js"></script>
<title>后台登录</title>
</head>
<body>
	<input type="hidden" id="TenantId" name="TenantId" value="" />
	<div class="header"></div>
	<div class="loginWraper">
		<div id="loginform" class="loginBox">
			<form class="form form-horizontal"
				action="${ctx }/Reception/base/login.html" method="post">
				<div class="row cl">
					<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
					<div class="formControls col-xs-8">
						<input id="loginName" name="loginName" type="text"
							placeholder="账户" class="input-text size-L">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
					<div class="formControls col-xs-8">
						<input id="password" name="password" type="password"
							placeholder="密码" class="input-text size-L">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-3"></label>
					<div class="formControls col-xs-8">
						<span style="color:red;">${message }</span>
					</div>
				</div>
				<!--  <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input class="input-text size-L" type="text" placeholder="验证码" onblur="if(this.value==''){this.value='验证码:'}" onclick="if(this.value=='验证码:'){this.value='';}" value="验证码:" style="width:150px;">
          <img src=""> <a id="kanbuq" href="javascript:;">看不清，换一张</a> </div>
      </div> -->
				<div class="row cl">
					<div class="formControls col-xs-8 col-xs-offset-3">
						<label for="online"> <input type="checkbox" name="online"
							id="chkRememberPwd" value=""> 使我保持登录状态
						</label>
					</div>
				</div>
				<div class="row cl">
					<div class="formControls col-xs-8 col-xs-offset-3">
						<input name="" onclick="login()" type="button"
							class="btn btn-success radius size-L"
							value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
						<!-- <input
							name="" type="button" class="btn btn-success radius size-L"
							value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;" onclick="login()"> -->
						<input name="" type="reset" class="btn btn-default radius size-L"
							value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="footer">Copyright 公司名称</div>
	<script type="text/javascript">
		var basePath = '${ctx}';
		$(function() {
			GetLastUser();
			if ($("#loginName").val() != '' && $("#password").val() != '') {
				login();
			}
		})
		var login = function() {
			var password = $("#password").val();
			var loginName = $("#loginName").val();
			var url = "Reception/base/login.html";
			var data = {
				loginName : loginName,
				password : password
			}
			url = basePath + "/" + url;
			data = JSON.stringify(data);
			$.ajax({
				url : url,
				type : "POST",
				data : data,
				async : true,
				contentType : "application/json",
				dataType : "json",
				success : function(data) {
					if (data == "1") {
						SetPwdAndChk();
						url = basePath + "/Reception/base/index.html";
						window.location.href = url;
					}
				},
				error : function(data) {
					alert("请求错误")
				}
			});
		}
		function GetLastUser() {
			var id = "49BAC005-7D5B-4231-8CEA-16939BEACD67"; //GUID标识符
			var loginName = GetCookie(id);
			if (loginName != null) {
				$("#loginName").val(loginName);
			} else {
				$("#loginName").val("");
			}
			GetPwdAndChk();
		}
		//cookies 记录账号密码
		function SetPwdAndChk() {
			//取用户名
			var loginName = $("#loginName").val();
			//将最后一个用户信息写入到Cookie
			SetLastUser(loginName);
			//如果记住密码选项被选中
			if ($("#chkRememberPwd").prop('checked') == true) {
				//取密码值
				var password = $("#password").val();
				var expdate = new Date();
				expdate.setTime(expdate.getTime() + 14 * (24 * 60 * 60 * 1000));
				//将用户名和密码写入到Cookie
				SetCookie(loginName, password, expdate);
			} else {
				//如果没有选中记住密码,则立即过期
				ResetCookie();
			}
		}
	
		function SetLastUser(loginName) {
			var id = "49BAC005-7D5B-4231-8CEA-16939BEACD67";
			var expdate = new Date();
			//当前时间加上两周的时间
			expdate.setTime(expdate.getTime() + 14 * (24 * 60 * 60 * 1000));
			SetCookie(id, loginName, expdate);
		}
		//用户名失去焦点时调用该方法
	
		function GetPwdAndChk() {
			var loginName = $("#loginName").val();
			var password = GetCookie(loginName);
			if (password != null) {
				$("#chkRememberPwd").prop('checked', true);
				$("#password").val(password);
			} else {
				$("#chkRememberPwd").prop('checked', false);
				$("#password").val("");
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
		function getCookieVal(offset) {
			var endstr = document.cookie.indexOf(";", offset);
			if (endstr == -1)
				endstr = document.cookie.length;
			return unescape(document.cookie.substring(offset, endstr));
		}
		function SetCookie(name, value, expires) {
			var argv = SetCookie.arguments;
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
	
		function ResetCookie() {
			var loginName = $("#loginName").val();
			var expdate = new Date();
			SetCookie(loginName, null, expdate);
		}
	</script>
</body>
</html>