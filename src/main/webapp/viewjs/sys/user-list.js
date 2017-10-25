var urlArray = {
	add_edit_url : ProjectUrl("Reception/sys/user/one.html"),
	list_url : ProjectUrl("Reception/sys/user/list.html")
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
	init();
});
function init() {
	$('.table-sort').dataTable({
		"aLengthMenu" : [ 2, 4, 5 ], //更改显示记录数选项  
		"iDisplayLength" : 2, //默认显示的记录数 
		"bAutoWidth" : false, //是否自适应宽度  
		"bPaginate" : true, //是否显示（应用）分页器  
		"bInfo" : true, //是否显示页脚信息，DataTables插件左下角显示记录数  
		"bSort" : true, //是否启动各个字段的排序功能  
		"bStateSave" : true, //状态保存
		"bLengthChange" : true, //用户不可改变每页显示数量     
		"bProcessing" : true, //加载数据时显示正在加载信息     
		"bServerSide" : true, //指定从服务器端获取数据  
		"pading" : false,
		"oLanguage" : {
			"sFirst" : "第一页",
			"sPrevious" : "上一页",
			"sNext" : "下一页",
			"sLast" : "最后一页"
		},
		"ajax" : function(data, callback, settings) {
			//封装请求参数
			var user = {};
			user.page_size = data.length; //页面显示记录条数，在页面显示每页显示多少项的时候
			user.page_new = (data.start / data.length) + 1; //当前页码
			//console.dir(user);
			user = JSON.stringify(user);
			//console.dir(user);
			//ajax请求数据
			$.ajax({
				type : "POST",
				url : urlArray.list_url,
				cache : false, //禁用缓存
				data : user, //传入组装的参数
				dataType : "json",
				async : true, //异步请求,默认true
				contentType : "application/json;charset=UTF-8",
				success : function(data) {
					//console.dir(data);
					var returnData = {};
					returnData.draw = data.draw; //这里直接自行返回了draw计数器,应该由后台返回
					returnData.recordsTotal = data.object.obj.totalCount; //返回数据全部记录
					returnData.recordsFiltered = data.object.obj.totalCount; //后台不实现过滤功能，每次查询均视作全部结果
					returnData.data = data.object.page.list; //返回的数据列表
					//console.log(returnData);
					//调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
					//此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
					callback(returnData);

				}
			});
		},
		//列表表头字段
		"columns" : [
			{
				data : null,
				bSortable : false,
				className : "text-center",
				render : function(data, type, row, meta) {
					// 显示行号  
					console.dir(meta);
					console.dir(data);
					console.dir(type);
					console.dir(row);
					return '<input type="checkbox" name="" value="">';
				}
			},
			{
				data : "id",
				bSortable : false,
				className : "text-right"
			},
			{
				"data" : "name"
			},
			{
				"data" : "loginName"
			},
			{
				"data" : "phone"
			},
			{
				"data" : "grade"
			},
			{
				"data" : "dept"
			},
			{
				"data" : "isemploy"
			},
			{
				"data" : "updateDate"
			}
		],
		"aoColumnDefs" : [
			{
				"orderable" : false,
				"aTargets" : [ 0, 8 ]
			} // 不参与排序的列
		]
	});
}

function edit(obj) {
	var id = $(obj).parent().parent().parent().attr("data-id");
	var url = urlArray.add_edit_url + "?id=" + id;
	//addOrEdit("修改", url);
	Hui_admin_tab_js("修改", url);
}
function del(obj) {
	var id = $(obj).parent().parent().parent().attr("data-id");
	layer.confirm('确认删除？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		var url = "Reception/sys/user/del.html";
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
				data = data.replace(/"/g, "");
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
function datadel() {
}