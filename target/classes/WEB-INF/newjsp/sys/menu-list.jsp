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
<%@ include file="/WEB-INF/include/newtitlehead.jsp"%>
<shiro:hasPermission name="sys:menu:delete">
	<meta id="del" content="1" />
</shiro:hasPermission>
<shiro:hasPermission name="sys:menu:update">
	<meta id="edit" content="1" />
</shiro:hasPermission>
<shiro:hasPermission name="sys:menu:save">
	<meta id="save" content="1" />
</shiro:hasPermission>
<title>菜单管理</title>
</head>
<body>
	<nav class="breadcrumb" style="width:98%">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		资讯管理 <span class="c-gray en">&gt;</span> 资讯列表 <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px;float: right!important"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"> <shiro:hasPermission name="sys:menu:save">
					<a class="btn btn-primary radius" data-title="添加菜单"
						data-href="${ctx }/Reception/sys/menu/one.html"
						onclick="Hui_admin_tab(this)" href="javascript:;"><i
						class="Hui-iconfont">&#xe600;</i> 添加菜单</a>
				</shiro:hasPermission> <a class="btn btn-primary radius" data-title="合上"
				data-href="article-add.html" onclick="openAll(this)"
				href="javascript:;"><i class="Hui-iconfont">&#xe6d6;</i>&nbsp;
					合上</a> <shiro:hasPermission name="sys:menu:ordersave">
					<a class="btn btn-primary radius" data-title="保存菜单顺序"
						onclick="getJson(this)" href="javascript:;"><i
						class="Hui-iconfont">&#xe632;</i>&nbsp;保存菜单顺序</a>
				</shiro:hasPermission>
			</span> <span class="r">共有数据：<strong>${list.size() }</strong> 条
			</span>
		</div>
		<div class="mt-20" style="width:98%">${menuHtml }</div>
	</div>
	<!--请在下方写此页面业务相关的脚本-->
	<link rel="stylesheet" href="${ctx}/css/ace.min.css" media="screen"
		title="no title" charset="utf-8">
	<script type="text/javascript" src="${ctx}/js/jquery.nestable.min.js"
		charset="utf-8"></script>
	<script type="text/javascript"
		src="${ctx}/statis/newjsp/lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript">
	</script>
	<script type="text/javascript" src="${ctx}/viewjs/sys/menu-list.js"></script>
</body>
</html>