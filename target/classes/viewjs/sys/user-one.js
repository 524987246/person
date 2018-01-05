var urlArray = {
	check : ProjectUrl("Reception/sys/user/check.html"),
	list : ProjectUrl("Reception/sys/user/list.html"),
	dept_list : ProjectUrl("Reception/sys/dept/allList.html"),
	save : ""
}
$(function() {
	//表单验证
	$("#form-main").validate({
		submitHandler : function(form) {
			var password = $("#password").val();
			var newpassword = $("#newpassword").val();
			if (password != newpassword) {
				msgLayer("两次密码输入不相同");
				return;
			}
			if (form_url == "") {
				msgLayer("权限不足,请联系管理员");
				return;
			}
			var json = $(form).serializeObject();
			var dept_id = $("#dept_id").val();
			json.dept = {
				id : dept_id
			}
			json = JSON.stringify(json);

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
						content : data.errmsg, //传入对象,界面会报错
						btn : [ '确认', '取消' ],
						btn1 : function(index, layero) {
							layer.close(index);
							if (data.errcode == 0) {
								removeIframe(data.errcode);
							}
						}
					});
				},
				error : function(data) {
					dir("请求错误")
				}
			});
		}
	});
	if (obj.isemploy == null || obj.isemploy == "") {
		obj.isemploy = 1;
	}
	if (form_url != "") {
		urlArray.save = ProjectUrl(form_url);
	}
	$(".formControls input[name='isemploy'][value='" + obj.isemploy + "']").attr('checked', 'true');
	;
});
function save() {
	$("#form-main").submit();
}
var setting = {
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
var zTree = new zTreeUtil(setting, "#ztree");
function getZtree() {
	$.ajax({
		url : urlArray.dept_list,
		type : "POST",
		data : "{}", //有对象接收,必须带参数
		contentType : "application/json;charset=UTF-8",
		async : true, //异步请求,默认true
		dataType : "json",
		success : function(data) {
			var html = $("#select_template").html();
			var index = layer.open({
				title : "部门选择",
				content : html,
				area : [ '300px', '300px' ], //默认"auto",
				btn : [ '确认', '取消' ],
				btn1 : function(index, layero) {
					sure(index);
				}
			});
			zTree.init(data.list);
			//zTree = $.fn.zTree.init($("#ztree"), setting, data.list);
			var id = $("#dept_id").val();
			zTree.setNode(id);
		},
		error : function() {
			msgLayer("请求错误");
		}
	});
}
function sure(index) {
	var nodes = zTree.getNode();
	if (nodes == null || nodes.length == 0) {
		layer.close(index);
		return;
	}
	$("#dept_id").val(nodes[0].id);
	$("#dept_name").val(nodes[0].name);
	layer.close(index);
}
//搜索框功能实现
function zTreeByName() {
	var value = $(event.target).prev().val();
	zTree.queryByName(value);
}
/* 树形结构结束 */

jQuery.validator.addMethod("checkLoginName", function(value, element) {
	var flag = false;
	value = value.trim();
	if (value == "") {
		return false;
	}
	var json = {
		id : obj.id,
		loginName : value
	}
	json = JSON.stringify(json);
	$.ajax({
		url : urlArray.check,
		type : "POST",
		data : json, //有对象接收,必须带参数
		contentType : "application/json;charset=UTF-8",
		async : false, //异步请求,默认true
		dataType : "json",
		success : function(data) {
			if (data.errcode == "0") {
				flag = true;
			} else if (data.errcode == "500") {
				msgLayer("登录名已存在,请重新输入");
			}
		},
		error : function(data) {
			dir("请求错误")
		}
	});
	return flag;
}, "请输入登录名");