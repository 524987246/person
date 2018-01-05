var urlArray = {
	check : ProjectUrl("Reception/sys/role/check.html"),
	list : ProjectUrl("Reception/sys/role/list.html"),
	menu_list : ProjectUrl("Reception/sys/menu/allMenu.html"),
	save : ""
}
$(function() {
	//表单验证
	$("#form-main").validate({
		submitHandler : function(form) {
			var json = $(form).serializeObject();
			//var menu_list = $("#dept_id").val();
			//			json.dept = {
			//				id : dept_id
			//			}
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