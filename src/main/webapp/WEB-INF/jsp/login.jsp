<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>登录/注册</title>
<!--用百度的静态资源库的cdn安装bootstrap环境-->
<!-- Bootstrap 核心 CSS 文件 -->
<link href="${ctx}/css/bootstrap.css" rel="stylesheet">
<!--font-awesome 核心我CSS 文件-->
<link href="${ctx}/css/font-awesome.css" rel="stylesheet">
<!-- 在bootstrap.min.js 之前引入 -->
<script src="${ctx}/js/jquery-2.2.1.js"></script>
<!-- Bootstrap 核心 JavaScript 文件 -->
<!--jquery.validate-->
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/this_project_function.js" charset="utf-8">
</script>
<style type="text/css">
.form {
	background: rgba(255, 255, 255, 0.2);
	width: 400px;
	margin: 60px auto;
}

#login_form {
	display: block;
}

#register_form {
	display: none;
}

.fa {
	display: inline-block;
	top: 27px;
	left: 6px;
	position: relative;
	color: #ccc;
}

input[type="text"],input[type="password"] {
	padding-left: 26px;
}

.checkbox {
	padding-left: 21px;
}
</style>
</head>
<body
	style="background: url(${ctx}/images/login.jpg) no-repeat;background-size: cover;font-size: 16px;">
	<!--
			基础知识：
			网格系统:通过行和列布局
			行必须放在container内
			手机用col-xs-*
			平板用col-sm-*
			笔记本或普通台式电脑用col-md-*
			大型设备台式电脑用col-lg-*
			为了兼容多个设备，可以用多个col-*-*来控制；
		-->
	<!--
			从案例学知识，来做一个登录，注册页面
			用到font-awesome 4.3.0；bootstrap 3.3.0；jQuery Validate
		-->
	<div class="container">
		<div class="form row">
			<form class="form-horizontal col-sm-offset-3 col-md-offset-3"
				id="login_form">
				<h3 class="form-title">登录</h3>
				<div class="col-sm-9 col-md-9">
					<div class="form-group">
						<i class="fa fa-user fa-lg"></i> <input
							class="form-control required" type="text" placeholder="用户名"
							name="name" autofocus="autofocus" maxlength="20" />
					</div>
					<div class="form-group">
						<i class="fa fa-lock fa-lg"></i> <input
							class="form-control required" type="password"
							placeholder="密码" name="password" maxlength="16" />
					</div>
					<div class="form-group">
						<!-- <label class="checkbox"> <input type="checkbox"
							name="remember" value="1" /> </label>
						<hr />-->
						<a href="javascript:;" id="register_btn" class="">注册</a>
					</div> 
					<div class="form-group">
						<input type="submit" class="btn btn-success pull-right"
							value="登录 " />
					</div>
				</div>
			</form>
		</div>

		<div class="form row">
			<form class="form-horizontal col-sm-offset-3 col-md-offset-3" action="${ctx}/Reception/user/add.html"
				id="register_form">
				<h3 class="form-title">注册</h3>
				<div class="col-sm-9 col-md-9">
					<div class="form-group">
						<i class="fa fa-user fa-lg"></i> <input
							class="form-control" type="text" placeholder="用户名"
							name="name" autofocus="autofocus" />
					</div>
					<div class="form-group">
						<i class="fa fa-lock fa-lg"></i> <input
							class="form-control required" type="password"
							placeholder="密码" id="register_password" name="password" />
					</div>
					<div class="form-group">
						<i class="fa fa-check fa-lg"></i> <input
							class="form-control required" type="password"
							placeholder="再次输入密码" name="rpassword" />
					</div>
					<div class="form-group not-null">
						<i class="fa fa-envelope fa-lg"></i> <input
							class="form-control phone " type="text" placeholder="手机号"
							name="phone" />
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-success pull-right"
							value="注册" /> <input type="submit"
							class="btn btn-info pull-left" id="back_btn" value="返回" />
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="${ctx}/js/main.js"></script>
</body>
</html>
