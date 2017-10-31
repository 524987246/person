var urlArray = {
	add_edit_url : ProjectUrl("Reception/sys/menu/one.html")
}

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
	var url = urlArray.add_edit_url + "?id=" + id;
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
				data=data.replace(/"/g,"");
				var index = layer.open({
					title : "信息",
					content : data,
					btn : [ '确认', '取消' ],
					btn1 : function(index, layero) {
						layer.close(index);
						location.replace(location.href);
					}
				});
			},
			error : function(data) {
				msgLayer("请求错误");
			}
		});
	}, function() {
		layer.close();
	});
}

function getJson() {
	var json = $('.dd').nestable('serialize');
	json = JSON.stringify(json);
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