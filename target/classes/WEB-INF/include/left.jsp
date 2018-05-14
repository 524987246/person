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
  <%@ include file="/WEB-INF/include/titlehead.jsp" %>
  <link rel="stylesheet" href="${ctx}/viewcss/left.css" media="screen" title="no title" charset="utf-8">
<script src="${pageContext.request.contextPath}/viewjs/left.js"></script>
<title>主页</title>
<body style="width: 100%;height:70px;overflow-x:hidden">
<nav id="nav">
	<section class="center">
		<ul class="link" >
		</ul>
	</section>
</nav>
</body>
</html>
