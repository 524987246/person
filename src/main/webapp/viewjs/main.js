$(function() {
	//$('.bk_2').nestable();
});
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