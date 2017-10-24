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
<%@ include file="/WEB-INF/include/newtitlehead.jsp"%>
<%--<link rel="stylesheet" href="${ctx}/viewcss/weberror.css" media="screen"
	title="no title" charset="utf-8">
--%>
<title>代码生成</title>
<body
	style="width: 100%;height:100%;background-image:url(${ctx}/images/backimage.png);">
	<button class="" data-toggle="modal" data-target="#myModal"
		hidden="hidden" id="dialogText"></button>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="top:30%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">提示</h4>
				</div>
				<div class="modal-body" id="message"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="hintclose">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 删除提示框 -->
	<button class="" data-toggle="modal" data-target="#removeInfo"
		hidden="hidden" id="removeDlg"></button>
	<div class="modal fade" id="removeInfo" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="top:30%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">是否删除</h4>
				</div>
				<div class="modal-body" id="gameSetInfo">
					<form class="form-inline" role="form">确认?请慎重</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="remove_save()">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
				</div>
			</div>
		</div>
	</div>
	<br>
	<h1 style="text-align: center;"></h1>
	<br>
	<form class="form-inline" role="form"
		style="margin-left: 10%;width: 80%;">
		<div class="form-group has-feedback">
			<label class="control-label" for="inputSuccess4">数据库类型:</label> <select
				class="form-control" id="dbtype" onchange="changeDbType(this)">
			</select>
		</div>
		<div class="form-group has-feedback">
			<label class="control-label" for="inputSuccess4">数据库地址:</label> <input
				type="text" class="form-control query" name="surl"
				value="localhost:3306">
		</div>
		<div class="form-group has-feedback">
			<label class="control-label" for="inputSuccess4">账号:</label> <input
				type="text" class="form-control query" name="username" value="root">
		</div>
		<div class="form-group has-feedback">
			<label class="control-label" for="inputSuccess4">密码:</label> <input
				type="password" class="form-control query" name="userpwd"
				value="JF1602">
		</div>
		<div class="form-group has-feedback">
			<button class="btn btn-info" type="button" style="width:70px"
				onclick="find_db()">查询</button>
			<button class="btn btn-info" type="button" style="width:80px"
				onclick="test_connection()">测试连接</button>
			<button class="btn btn-info" type="button" style="width:80px"
				onclick="generate()">生成代码</button>
		</div>
	</form>
	<br>
	<div class="table-responsive"
		style="text-align: center;width: 90%;margin-left: 5%;">
		<table class="table table-hover table-bordered" align="center"
			id="tables" width="80%">
			<thead>数据库名称,点击可查询
			</thead>
			<tr>
				<th>暂无数据</th>
			</tr>
		</table>
	</div>
	<div class="table-responsive"
		style="text-align: center;width: 90%;margin-left: 5%;">
		<table class="table table-hover table-bordered" align="center"
			id="tableinfo" width="80%">
			<thead>当前数据库:<span id="newname"></span>
			</thead>
			<tr>
				<th>暂无数据</th>
			</tr>
		</table>
	</div>
	<form class="form-inline" role="form" style="margin-left: 10%;">
		<div class="form-group has-feedback">
			<a title="添加" href="javascript:addInfo();" data-toggle="tooltip"
				data-placement="top" style="color:blue"> <span
				class="glyphicon glyphicon-plus" data-toggle='modal'
				data-target='#table-msg-list-add'></span> </a>
		</div>
		<div class="form-group has-feedback">
			<a class="margin-left-10" title="刷新" href="javascript:search();"
				data-toggle="tooltip" data-placement="top" style="color:blue"> <span
				class="glyphicon  glyphicon-refresh"></span> </a>
		</div>
		<div class="form-group has-feedback">
			<button class="btn btn-info" type="button" style="width:70px"
				onclick="search(-1)" id="up_page" disabled="disabled">上一页</button>
		</div>
		<div class="form-group has-feedback">
			<label class="control-label" for="inputSuccess4">第</label><label
				class="control-label" for="inputSuccess4" id="page_new">1</label><label
				class="control-label" for="inputSuccess4">页</label>
		</div>
		<div class="form-group has-feedback">
			<button class="btn btn-info" type="button" style="width:70px"
				onclick="search(1)" id="next_page" disabled="disabled">下一页</button>
		</div>
		<div class="form-group has-feedback">
			<select class="form-control" id="page_num"
				onchange="changepagenum(this)">
				<option>5</option>
				<option>10</option>
				<option>20</option>
			</select>
		</div>
	</form>
	<script src="${ctx}/viewjs/generate/generate.js"></script>
</body>
</html>
