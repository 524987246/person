$(function() {
	//getUserInfo();
});
function getUserInfo() {
	var url = "Reception/sys/user/get.html";
	url = ProjectUrl(url);
	$.ajax({
		url : url,
		type : "POST",
		async : true, //异步请求,默认true
		contentType : "application/json;charset=UTF-8",
		dataType : "JSON",
		success : function(data) {
			//dir(data);
			createMenu(data.menulist);
		},
		error : function(data) {
			hint("请求异常");
		}
	});
}
var createMenu = function(menulist) {
	var firstMenu = new Array();
	for (var i = 0; i < menulist.length; i++) {
		var menu = menulist[i];
		if (menu.parentId == 0) {
			firstMenu.push(menu);
		}
	/*<dl id="menu-article">
		<dt>
			<i class="Hui-iconfont">&#xe616;</i> 资讯管理
			<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
		</dt>
		<dd>
			<ul>
				<li><a data-href="article-list.html" data-title="资讯管理"
					href="javascript:void(0)">资讯管理</a></li>
			</ul>
		</dd>
	</dl>*/
	}
	
	for (var i = 0; i < firstMenu.length; i++) {
		var template = '<li class="navbar-levelone"><a href="javascript:;">CONTENT</a></li>'
		var menu = firstMenu[i];
		var html = "";
		if (i == 0) {
			html = template.replace(/CONTENT/g, menu.name)
				.replace(/navbar-levelone/g, "navbar-levelone current");
		} else {
			html = template.replace(/CONTENT/g, menu.name);
		}
		dir(html);
		$(".firstmenu").append(html);
	}
}
$('#logout').on('click', function() {
	layer.confirm('确认退出？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		layer.close();
		var url = "/Reception/base/logout.html";
		url = ProjectUrl(url);
		window.location.href = url;
	}, function() {
		layer.close();
	});

});