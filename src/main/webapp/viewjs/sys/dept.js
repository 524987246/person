var page_size = 4; //每页显示多少页  
$(function() {
	loadList(1);
});
PageClick = function(index) {
	loadList(index.getCurrent()); //点击分页加载列表数据  */  
}
var loadList = function(page_new) {
	var url = "/Reception/sys/list.html";
	url = ProjectUrl(url);
	var data = {
		page_new : page_new,
		page_size : page_size
	};
	data = JSON.stringify(data);
	$.ajax({
		url : url,
		type : "POST",
		data : data,
		async : true, //异步请求,默认true
		contentType : "application/json;charset=UTF-8",
		dataType : "JSON",
		success : function(data) {
			//console.dir(data);
			var count = data.page.total;
			totalRecords = count;
			totalPage = data.page.pages;
			var datalist = "";
			$.each(data.page.list, function(i, item) {
				datalist += '<tr>' +
					'<td>' + item.id + '</td>' +
					'<td>' + item.name + '</td>' +
					'<td>' + item.parentId + '</td>' +
					'</tr>';
			});
			$("#datalist1").html(datalist);
			$('.total').text(totalPage);
			$('.count').text(count);
			$('.now').text(data.page.pageNum);
			$('.M-box').pagination({
				pageCount : totalPage,
				current : page_new, //当前第几页  
				jump : true,
				count : 6, //显示多少个按钮,不含当前页
				coping : true,
				homePage : '首页',
				endPage : '末页',
				prevContent : '上页',
				nextContent : '下页',
				callback : PageClick
			});
		},
		error : function(data) {
			hint("请求异常");
		}
	});
}