var urlArray = {
	to : ProjectUrl("Reception/addCompany/to.html"),
	list : ProjectUrl("Reception/addCompany/list.html"),
}
$(function() {
	init();
});
function init() {
	$.ajax({
		type : "POST",
		url : urlArray.list,
		cache : false, //禁用缓存
		//data : json, //传入组装的参数
		dataType : "json",
		async : true, //异步请求,默认true
		contentType : "application/json;charset=UTF-8",
		success : function(data) {
			console.dir(data);
			initAddZtree(data.object.addlist);
			initNewZtree(data.object.newlist);
		},
		error : function(data) {
			alert("请求错误");
		}
	});
}
function initNewZtree(array) {
	zTree_new = $.fn.zTree.init($("#new_company_ztree"), setting, array);
}
function initAddZtree(array) {
	zTree_add = $.fn.zTree.init($("#add_company_ztree"), setting, array);
}
/* 人员选择树形结构开始 */
var zTree_new;
var zTree_add;
//设置树形结构
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