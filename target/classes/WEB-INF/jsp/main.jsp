<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title id="titlename">主页</title>
<%@ include file="/WEB-INF/include/titlehead.jsp" %>
</head>
<frameset rows="15%,80%,5%" frameborder="yes" border="1"
	framespacing="1">
	<frame name="menusOne" src="${ctx}/Reception/goto/head.html"/>
	<frameset cols="13%,*" frameborder="yes" framespacing="1" name="middle">
	<frame name="menusTwo" src="${ctx}/Reception/goto/left.html"/>
	<frame name="main" src="${ctx}/Reception/goto/right.html"/>
	</frameset>
	<frame name="foot" src="${ctx}/Reception/goto/foot.html"/>
	</frameset> 
</html>
