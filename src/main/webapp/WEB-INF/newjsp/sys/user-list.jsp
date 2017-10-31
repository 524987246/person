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
<shiro:hasPermission name="sys:user:delete">
	<meta id="del" content="1" />
</shiro:hasPermission>
<shiro:hasPermission name="sys:user:update">
	<meta id="edit" content="1" />
</shiro:hasPermission>
<shiro:hasPermission name="sys:user:save">
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
		<div class="text-c">
			<button onclick="removeIframe()" class="btn btn-primary radius">关闭选项卡</button>
			日期范围： <input type="text"
				onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'queryEndDate\')||\'%y-%M-%d\'}' })"
				id="queryBeginDate" class="input-text Wdate" style="width:120px;"> -
			<input type="text"
				onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'queryBeginDate\')}',maxDate:'%y-%M-%d' })"
				id="queryEndDate" class="input-text Wdate" style="width:120px;"> <input
				type="text" name="name" id="name" placeholder="用户名称" style="width:250px"
				class="input-text">
			<button name="" id="" class="btn btn-success" onclick="search()">
				<i class="Hui-iconfont">&#xe665;</i> 搜索
			</button>
		</div>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"> <shiro:hasPermission name="sys:user:save">
					<a class="btn btn-primary radius" data-title="添加菜单"
						data-href="${ctx }/Reception/sys/user/one.html"
						onclick="Hui_admin_tab(this)" href="javascript:;"><i
						class="Hui-iconfont">&#xe600;</i> 添加用户</a>
				</shiro:hasPermission> <a href="javascript:;" onclick="datadel()"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量删除</a>
			</span> <span class="r">共有数据：<strong>${totalCount}</strong> 条
			</span>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
				<thead>
					<tr class="text-c">
						<th><input type="checkbox" name="" value=""></th>
						<!-- <th>用户Id</th> -->
						<th>用户名</th>
						<th>登录名</th>
						<th>手机号</th>
						<th>用户等级</th>
						<th>所属部门</th>
						<th>状态</th>
						<th>更新时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
	</div>
	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript">
	</script>
	<script type="text/javascript" src="${ctx}/viewjs/sys/user-list.js"></script>
</body>
</html>