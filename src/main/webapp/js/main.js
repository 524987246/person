$().ready(function() {
	$("#login_form").validate({
		rules: {
			name: "required",
			password: {
				required: true,
				minlength: 5
			},
		},
		messages: {
			username: "请输入姓名",
			password: {
				required: "请输入密码",
				minlength: jQuery.format("密码不能小于{0}个字 符")
			},
		}
	});
	$("#register_form").validate({
		rules: {
			name: "required",
			password: {
				required: true,
				minlength: 5
			},
			rpassword: {
				equalTo: "#register_password"
			}
		},
		messages: {
			username: "请输入姓名",
			password: {
				required: "请输入密码",
				minlength: jQuery.format("密码不能小于{0}个字 符")
			},
			rpassword: {
				equalTo: "两次密码不一样"
			}
		}
	});
});
$(function() {
	$("#register_btn").click(function() {
		$("#register_form").css("display", "block");
		$("#login_form").css("display", "none");
	});
	$("#back_btn").click(function() {
		$("#register_form").css("display", "none");
		$("#login_form").css("display", "block");
	});
});
jQuery.validator.addMethod("phone", function (value, element) {
	value=value.trim();
	
	$(element).val(value);
	if(value.length==11||value.length==13){
		input_import(element,true);
		return true;
	}
	input_import(element,false);
	return false;
    //return this.optional(element) || (/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/.test(value) && 	(RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));
}, "请输入合法的手机号码");