var urlArray = {
	save : ""
}
$(function() {
	//表单验证
	$("#form-main").validate({
		submitHandler : function(form) {
		//表单提交拦截
			var json = $(form).serializeObject();
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
	if (form_url != "") {
		urlArray.save = ProjectUrl(form_url);
	}
});
function save() {
	$("#form-main").submit();
}