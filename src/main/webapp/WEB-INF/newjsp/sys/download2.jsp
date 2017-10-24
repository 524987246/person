<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/include/newtitlehead.jsp"%>
<title>下载2</title>
</head>
<body>
	<%
		out.clear();
		out = pageContext.pushBody();
	%>
	正在下载中....
	<script type="text/javascript">
		removeIframe();
	</script>
</body>
</html>