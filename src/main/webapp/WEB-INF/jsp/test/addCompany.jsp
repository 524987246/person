
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ include file="/WEB-INF/include/titlehead.jsp"%>
<style type="text/css">
#newCompany {
	border-color: red;
	float: left;
	width: 50%;
}

#addCompany {
	border-color: yellow;
	float: left;
	width: 50%;
}
</style>
<title>富文本功能实现</title>
</head>
<body>
	<h1>新旧公司比较</h1>
	<div id="newCompany">
		<ul id="new_company_ztree" class="ztree"></ul>
	</div>
	<div id="addCompany">
		<ul id="add_company_ztree" class="ztree"></ul>
	</div>
</body>
<script type="text/javascript" src="${ctx}/viewjs/addCompany.js"></script>
</html>