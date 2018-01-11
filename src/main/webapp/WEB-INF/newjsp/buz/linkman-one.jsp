<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico">
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<%@ include file="/WEB-INF/include/newtitlehead.jsp"%>
<shiro:hasPermission name="buz:linkman:update">
	<meta id="edit" content="1" />
</shiro:hasPermission>
<shiro:hasPermission name="buz:linkman:save">
	<meta id="save" content="1" />
</shiro:hasPermission>
<title>XX修改</title>
<style type="text/css">
</style>
</head>
<body>
	<article class="page-container">
		<form class="form form-horizontal" id="form-main" method="post">
			<input type="hidden" name="id" id="id" value="${buzLinkman.id }">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">姓名：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text required" value="${buzLinkman.name }"
						placeholder="姓名" id="name" name="name"> 
						<span class="c-red">*</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">部门：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text required" value="${buzLinkman.dept }"
						placeholder="部门" id="dept" name="dept"> 
						<span class="c-red">*</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">手机：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text required" value="${buzLinkman.phone }"
						placeholder="手机" id="phone" name="phone"> 
						<span class="c-red">*</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">年龄：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text required" value="${buzLinkman.age }"
						placeholder="年龄" id="age" name="age"> 
						<span class="c-red">*</span>
				</div>
			</div>
			
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button onClick="save();" class="btn btn-primary radius"
						type="button">
						<i class="Hui-iconfont">&#xe632;</i> 确定
					</button>
					<button onClick="removeIframe();" class="btn btn-default radius"
						type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
		</form>
	</article>
	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="${ctx}/statis/newjsp/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
	<script type="text/javascript"
		src="${ctx}/statis/newjsp/lib/jquery.validation/1.14.0/validate-methods.js"></script>
	<script type="text/javascript"
		src="${ctx}/statis/newjsp/lib/jquery.validation/1.14.0/messages_zh.js"></script>
	<!-- 属性选择框文本 -->
	<script type="text/javascript">
		var buzLinkman = {
			id : "${buzLinkman.id }",
			isemploy : "${buzLinkman.isemploy }"
		}
		var form_url = "";
	
		if (buzLinkman.id != null && buzLinkman.id != "") {
			if ($("#edit").attr("content") != null) {
				form_url = "Reception/buz/linkman/update.html";
			}
		} else {
			if ($("#save").attr("content") != null) {
				form_url = "Reception/buz/linkman/save.html";
			}
		}
	</script>
	<script type="text/javascript" src="${ctx}/viewjs/buz/linkman-one.js"></script>
	<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>