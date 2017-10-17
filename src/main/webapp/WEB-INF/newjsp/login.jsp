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
					<label class="form-label col-xs-3"><i class="Hui-iconfont">${msg}</i></label>
				</div>
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
				<!--  <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input class="input-text size-L" type="text" placeholder="验证码" onblur="if(this.value==''){this.value='验证码:'}" onclick="if(this.value=='验证码:'){this.value='';}" value="验证码:" style="width:150px;">
          <img src=""> <a id="kanbuq" href="javascript:;">看不清，换一张</a> </div>
      </div> -->
				<div class="row cl">
					<div class="formControls col-xs-8 col-xs-offset-3">
						<label for="online"> <input type="checkbox" name="online"
							id="online" value=""> 使我保持登录状态
						</label>
					</div>
				</div>
				<div class="row cl">
					<div class="formControls col-xs-8 col-xs-offset-3">
						<input name="" type="submit" class="btn btn-success radius size-L"
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
					alert(data);
					if (data == "1") {
						url = basePath + "/Reception/base/index.html";
						window.location.href = url;
					}
				},
				error : function(data) {
					alert("请求错误")
				}
			});
		}
	</script>
</body>
</html>