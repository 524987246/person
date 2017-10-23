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
		var add_edit_url = "${ctx }/Reception/sys/menu/one.html";
		var edithtml = "";
		if ($("#edit").attr("content") != null) {
			edithtml = "<a style=\"text-decoration:none\" ";
			edithtml += "onClick=\"edit(this)\" href=\"javascript:;\" title=\"修改\">";
			edithtml += "<i class=\"Hui-iconfont\">&#xe6df;</i></a>";
		}
		var delhtml = "";
		if ($("#del").attr("content") != null) {
			delhtml = "<a style=\"text-decoration:none\" class=\"ml-5\" ";
			delhtml += " onClick=\"del(this)\" href=\"javascript:;\" title=\"删除\">";
			delhtml += "<i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
		}
		var html = "<div style='float:right'>" + edithtml + delhtml + "</div>";
		jQuery(function() {
			$('.dd').nestable({
				width : "100%"
			});
			stop();
			$(".dd li").addClass("dd-nodrag"); //拖拽内容允许点击事件
			$(".dd .dd-handle").append(html);
		});
		function stop() {
			$('.dd').nestable({
				itemNodeName : 'lli'
			});
		}
		function begin() {
			$('.dd').nestable({
				itemNodeName : 'li'
			});
		}
		function edit(obj) {
			var id = $(obj).parent().parent().parent().attr("data-id");
			var url = add_edit_url + "?id=" + id;
			//addOrEdit("修改", url);
			Hui_admin_tab_js("菜单修改", url);
		}
		function del(obj) {
			var id = $(obj).parent().parent().parent().attr("data-id");
			layer.confirm('确认删除？', {
				btn : [ '确定', '取消' ] //按钮
			}, function() {
				var url = "Reception/sys/menu/del.html";
				url = ProjectUrl(url);
				var json = {
					id : id
				};
				json = JSON.stringify(json);
				$.ajax({
					url : url,
					type : "POST",
					data : json,
					async : true, //异步请求,默认true
					contentType : "application/json;charset=UTF-8",
					success : function(data) {
						msgLayer(data);
					},
					error : function(data) {
						msgLayer("请求错误");
					}
				});
			}, function() {
				layer.close();
			});
		}
	
		/*资讯-添加*/
		function addOrEdit(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		function getJson() {
			var json = $('.dd').nestable('serialize');
			json = JSON.stringify(json);
			dir(json);
			var url = "Reception/sys/menu/ordersave.html";
			url = ProjectUrl(url);
			$.ajax({
				url : url,
				type : "POST",
				data : json,
				async : true, //异步请求,默认true
				contentType : "application/json;charset=UTF-8",
				dataType : "json",
				success : function(data) {
					if (typeof data == "string") {
						data = JSON.parse(data);
					}
					//dir(data);
					msgLayer(data.message);
					layer.confirm(data.message, {
						btn : [ '确定', '取消' ] //按钮
					}, function() {
						location.replace(location.href);
					}, function() {
						location.replace(location.href);
					});
				},
				error : function(data) {
					msgLayer("请求错误");
				}
			});
		}
		function openAll(obj) {
			var flag = $(obj).attr("data-title");
			var html = "";
			if (flag == "展开") {
				$('.dd').nestable('expandAll'); //展开所有节点
				$(obj).attr("data-title", "合上");
				html = "<i class=\"Hui-iconfont\">&#xe6d6;</i>&nbsp; 合上";
			} else {
				$('.dd').nestable('collapseAll'); //合上所有节点
				$(obj).attr("data-title", "展开");
				html = "<i class=\"Hui-iconfont\">&#xe6d5;</i>&nbsp; 展开";
			}
			$(obj).html(html);
	
		}
		/*资讯-删除*/
		function article_del(obj, id) {
			layer.confirm('确认要删除吗？', function(index) {
				$.ajax({
					type : 'POST',
					url : '',
					dataType : 'json',
					success : function(data) {
						$(obj).parents("tr").remove();
						layer.msg('已删除!', {
							icon : 1,
							time : 1000
						});
					},
					error : function(data) {
						console.log(data.msg);
					},
				});
			});
		}
	</script>
</body>
</html>