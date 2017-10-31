var urlArray = {
	add_edit_url : ProjectUrl("Reception/sys/user/one.html"),
	list_url : ProjectUrl("Reception/sys/user/list.html"),
	del_url : ProjectUrl("Reception/sys/user/del.html"),
	batch_del_url : ProjectUrl("Reception/sys/user/batchdelete.html")
}
var columnsArray = [
	{
		data : null,
		bSortable : false,
		className : "text-center",
		render : function(data, type, row, meta) {
			return '<input type="checkbox" class="batch" name="" value="">';
		}
	},
	/*	{
			data : "id",
			true_col : "id"
		},*/
	{
		data : "name",
		true_col : "name"
	},
	{
		data : "loginName",
		true_col : "login_name"
	},
	{
		data : "phone",
		true_col : "phone"
	},
	{
		data : "grade",
		true_col : "grade"
	},
	{
		data : "dept",
		true_col : "dept",
		render : function(data, type, row, meta) {
			/*	console.dir(data);//本行值
				console.dir(type);
				console.dir(row);//本行对象
				console.dir(meta);*/
			return data.name;
		}
	},
	{
		data : "isemploy",
		true_col : "isemploy",
		render : function(data, type, row, meta) {
			/*	console.dir(data);//本行值
				console.dir(type);
				console.dir(row);//本行对象
				console.dir(meta);*/
			var html = '<span class="label label-danger radius">禁用</span>';
			if (data == 1) {
				html = '<span class="label label-success radius">正常</span>';
			} else if (data == 2) {
				html = '<span class="label label-warning radius">审核</span>';
			}
			return html;
		}
	},
	{
		data : "updateDate",
		true_col : "update_date"
	},
	{
		data : null,
		bSortable : false,
		className : "text-center",
		render : function(data, type, row, meta) {
			var html = edithtml + delhtml;
			return html;
		}
	}
]
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
var dataTable;
var queryobj = {};
function init() {
	dataTable = $('.table-sort').dataTable({
		"aLengthMenu" : [ 2, 4, 5 ], //更改显示记录数选项  
		"searching" : false,
		//"iDisplayLength" : 2, //默认显示的记录数 
		"bAutoWidth" : false, //是否自适应宽度  
		"bPaginate" : true, //是否显示（应用）分页器  
		"bInfo" : true, //是否显示页脚信息，DataTables插件左下角显示记录数  
		"bSort" : true, //是否启动各个字段的排序功能  
		"bStateSave" : true, //状态保存
		"bLengthChange" : true, //用户可改变每页显示数量     
		"bProcessing" : true, //加载数据时显示正在加载信息     
		"bServerSide" : true, //指定从服务器端获取数据  
		"pading" : false,
		"ajax" : function(data, callback, settings) {
			if (data.order != null && data.order.length > 0) {
				var str = columnsArray[data.order[0].column].true_col;
				if (str != null && str != '') {
					var orderby = str + " " + data.order[0].dir;
					queryobj.orderBy = orderby; //页面显示记录条数，在页面显示每页显示多少项的时候
				}
			}
			queryobj.page_size = data.length; //页面显示记录条数，在页面显示每页显示多少项的时候
			queryobj.page_new = (data.start / data.length) + 1; //当前页码
			var json = JSON.stringify(queryobj);
			//console.log(queryobj);
			//ajax请求数据
			$.ajax({
				type : "POST",
				url : urlArray.list_url,
				cache : false, //禁用缓存
				data : json, //传入组装的参数
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
					callback(returnData);

				}
			});
		},
		//列表表头字段
		"columns" : columnsArray,
		"aoColumnDefs" : [
			{
				"orderable" : false,
				"aTargets" : [ 0 ]
			} // 不参与排序的列
		]
	});
}
function search() {
	var queryBeginDate = $("#queryBeginDate").val();
	var queryEndDate = $("#queryEndDate").val();
	var name = $("#name").val();
	queryobj = {
		name : name,
		queryBeginDate : queryBeginDate,
		queryEndDate : queryEndDate
	}
	dataTable.fnClearTable(); //清空数据.fnClearTable();//清空数据  
	dataTable.fnDestroy();
	init();
}
function edit(obj) {
	//先拿到点击的行号  
	var rowIndex = $(obj).parents().parents().index();
	//此处拿到隐藏列的id 
	if (rowIndex < 0) {
		return;
	}
	var data = $('.table-sort').DataTable().row(rowIndex).data();
	var url = urlArray.add_edit_url + "?id=" + data.id;
	Hui_admin_tab_js("修改", url);
}
function del(obj) {
	layer.confirm('确认删除？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		var rowIndex = $(obj).parents().parents().index();
		//此处拿到隐藏列的id 
		if (rowIndex < 0) {
			return;
		}
		var id = $('.table-sort').DataTable().row(rowIndex).data().id;
		var json = {
			id : id
		};
		json = JSON.stringify(json);
		$.ajax({
			url : urlArray.del_url,
			type : "POST",
			data : json,
			async : true, //异步请求,默认true
			contentType : "application/json;charset=UTF-8",
			dataType : "json",
			success : function(data) {
				var index = layer.open({
					title : "信息",
					content : data.errmsg,
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
	var checkboxs = $("tbody input[class='batch']");
	var list = new Array();
	checkboxs.each(function(index, item) {
		if ($(item).is(':checked')) {
			var id = $('.table-sort').DataTable().row(index).data().id;
			list.push(id);
		}
	});
	if (list.length == 0) {
		msgLayer("请选中信息");
		return;
	}
	layer.confirm('确认删除？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		var json = {
			batchId : list
		};
		json = JSON.stringify(json);
		$.ajax({
			url : urlArray.batch_del_url,
			type : "POST",
			data : json,
			async : true, //异步请求,默认true
			contentType : "application/json;charset=UTF-8",
			dataType : "json",
			success : function(data) {
				var index = layer.open({
					title : "信息",
					content : data.errmsg,
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