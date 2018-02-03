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
<title>日常计费</title>
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
	<br>
	<h1 style="text-align: center;">费用管理</h1>
	<br>
	<form class="form-inline" role="form"
		style="margin-left: 10%;width: 80%;">
		<div class="form-group has-feedback">
			<label class="control-label" for="inputSuccess4">名称:</label> <input
				type="text" class="form-control query" name="payname">
		</div>
		<div class="form-group has-feedback">
			<label class="control-label" for="inputSuccess4">使用者:</label> <input
				type="text" class="form-control query" name="user">
		</div>
		<div class="form-group has-feedback">
			<label class="control-label" for="inputSuccess4">收支:</label> <select
				name="income_expend" class="query">
				<option value="0">全部</option>
				<option value="1">支出</option>
				<option value="2">收入</option>
			</select>
		</div>
		<div class="form-group has-feedback">
			<label class="control-label" for="inputSuccess4">时间:</label> <input
				type="text" class="form-control query" name="createtime"id="begintime" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')||\'%y-%M-%d\'}',dateFmt:'yyyy-MM-dd'})">
				到 <input id="endtime"
						class="form-control query" type="text" name="endtime"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'begintime\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" />
		</div>
		<div class="form-group has-feedback">
			<button class="btn btn-info" type="button" style="width:70px"
				onclick="search()">查询</button>
			<button class="btn btn-info" type="button" style="width:80px"
				onclick="batchdel()">批量删除</button>
		</div>
	</form>
	<br>
	<div class="table-responsive"
		style="text-align: center;width: 90%;margin-left: 5%;">
		<table class="table table-hover table-bordered" align="center"
			id="tableinfo" width="80%">
			<tr>
				<th>序号</th>
				<th>名称</th>
				<th>金额</th>
				<th>时间</th>
				<th>使用者</th>
				<th>收/支</th>
				<th>备注</th>
				<th>操作</th>
				<th><input type="checkbox" onclick="seletstate(this)">
				</th>
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

	<!--添加或修改-->
	<button class="" data-toggle="modal" data-target="#addOrUpdate"
		hidden="hidden" id="addOrUpdateDlg"></button>
	<div class="modal fade" id="addOrUpdate" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="top:10%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" id="close_addOrUpdate_dlg">&times;</button>
					<h4 class="modal-title titleInfo">修改</h4>
				</div>
				<div class="modal-body">
					<form class="form-inline tableContent" role="form"
						style="margin-left:10%">
						<div class="form-group">
							<input class="form-control inputcontent" name="sid" type="hidden">
							<label for="">名称:</label> <input
								class="form-control inputcontent" placeholder="请输入名称"
								name="payname" maxlength="10"> <span
								class="help-inline1"><font color="red">*</font> </span>
						</div>
						<br> <br>
						<div class="form-group">
							<label for="">金额:</label> <input
								class="form-control  inputcontent" placeholder="请输入金额"
								name="paymoney">
						</div>
						<br> <br>
						<div class="form-group">
							<label for="">时间:</label> <input
								class="form-control inputcontent" placeholder="请输入时间"
								name="createtime">
						</div>
						<br> <br>
						<div class="form-group">
							<label for="">使用者:</label> <input
								class="form-control inputcontent" placeholder="请输入使用者"
								name="user">
						</div>
						<br> <br>
						<div class="form-group">
							<label for="">收支:</label> <select class="form-control inputcontent" style="width:100%">
								<option value="1" checked="checked">支出</option>
								<option value="2">收入</option>
							</select>
						</div>
						<br> <br>
						<div class="form-group">
							<label for="">备注:</label>
						</div>
						<div class="form-group" style="width: 100%">
							<textarea class="form-control inputcontent" placeholder="请输入备注"
								name="remark" style="width: 80%"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="update_save()">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx}/viewjs/costinfo/costinfo.js"></script>
</body>
</html>
