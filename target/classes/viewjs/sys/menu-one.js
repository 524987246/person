var urlArray = {
	menulist : ProjectUrl("Reception/sys/menu/list.html"),
	save : ""
}
$(function() {
	//表单验证
	$("#form-main").validate({
		submitHandler : function(form) {
			console.log("通过表单验证");
			if (form_url == "") {
				msgLayer("权限不足,请联系管理员");
				return;
			}
			var json = JSON.stringify($(form).serializeObject());
			$.ajax({
				url : urlArray.save,
				type : "POST",
				data : json, //有对象接收,必须带参数
				contentType : "application/json;charset=UTF-8",
				async : true, //异步请求,默认true
				dataType : "json",
				success : function(data) {
					var index = layer.open({
						title : "信息",
						content : data,
						btn : [ '确认', '取消' ],
						btn1 : function(index, layero) {
							layer.close(index);
							removeIframe();
						}
					});
				},
				error : function(data) {
					dir("请求错误")
				}
			});
		}
	});
	if (menu_type == null || menu_type == "") {
		menu_type = 1;
	}
	if (form_url != "") {
		urlArray.save = ProjectUrl(form_url);
	}
	$(".formControls input[name='type'][value='" + menu_type + "']").attr('checked', 'true');
	;
});
function save() {
	$("#form-main").submit();
}
var settingMenu = {
	data : {
		key : {
			title : "name", //鼠标悬停显示的信息
			name : "name" //网页上显示出节点的名称
		},
		simpleData : {
			enable : true,
			idKey : "id", //修改默认的ID为自己的ID
			pIdKey : "parentId" //修改默认父级ID为自己数据的父级ID
		}
	}
};
//初始化树形
var zTreeMenu = new zTreeUtil(settingMenu, "#menu_ztree");
function getMenuZtree() {
	$.ajax({
		url : urlArray.menulist,
		type : "POST",
		data : "{}", //有对象接收,必须带参数
		contentType : "application/json;charset=UTF-8",
		async : true, //异步请求,默认true
		dataType : "json",
		success : function(data) {
			var html = $("#menu_select_template").html();
			var index = layer.open({
				title : "菜单选择",
				content : html,
				area : [ '300px', '300px' ], //默认"auto",
				btn : [ '确认', '取消' ],
				btn1 : function(index, layero) {
					sureMenu(index);
				}
			});
			var array = new Array();
			var temp = {
				id : 0,
				name : "一级菜单",
				parentId : 0
			};
			array.push(temp);
			for (var i = 0; i < data.list.length; i++) {
				var obj = data.list[i];
				var temp = {
					id : obj.id,
					name : obj.name,
					parentId : obj.parentId
				};
				array.push(temp);
			}
			zTreeMenu.init(array);
			var id = $("#parentId").val();
			zTreeMenu.setNode(id);
		/*if (id != null && id != "") {
			var node = zTreeMenu.getNodeByParam("id", id);
			zTreeMenu.selectNode(node);
		}*/
		},
		error : function() {
			msgLayer("请求错误");
		}
	});
}
function sureMenu(index) {
	var nodes = zTreeMenu.getNode();
	if (nodes == null || nodes.length == 0) {
		layer.close(index);
		return;
	}
	$("#parentId").val(nodes[0].id);
	$("#parentName").val(nodes[0].name);
	layer.close(index);
}
function zTreeMenuByName() {
	var value = $(event.target).prev().val();
	zTreeMenu.queryByName(value);
}
/* 人员选择树形结构结束 */