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
<%@ include file="/WEB-INF/include/titlehead.jsp"%>
<link rel="stylesheet" href="${ctx}/viewcss/web/weberror.css"
	media="screen" title="no title" charset="utf-8">
<title>部门管理</title>
<body
	style="width: 100%;height:100%;background-image:url(${ctx}/images/backimage.png);">
	<table width="80%" align="center">
		<tr>
			<td>id</td>
			<td>名称</td>
			<td>父类id</td>
		</tr>
		<tbody id="datalist1">
		</tbody>
	</table>
	<div class="wrapper">
		<div class="eg">
			<div class="M-box"></div>
			<div class="tips">
				当前是第<span class="now">1</span>页,总共<span class="total"></span>页,总共<span
					class="count"></span>条数据
			</div>
		</div>
	</div>
	<script type="text/javascript">
	</script>
	<script src="${ctx}/viewjs/sys/dept.js"></script>
</body>
</html>
