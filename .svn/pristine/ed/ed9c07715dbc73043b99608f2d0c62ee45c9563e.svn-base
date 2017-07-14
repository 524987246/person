$(function(){
	gainMenus();
});


var urlarray={MenusUrl:"/Reception/Menus/toMenus.html"};

function gainMenus(){
	var url=ProjectUrl(urlarray.MenusUrl);
	$.ajax({
		url : url,
		type : "POST",
		data : "0",
		async : true,
		contentType : "application/json",
		dataType : "json",
		success : function(data) {
			var menus=JSON.parse(data);
			var html="";
			$.each(menus, function(i, item){
				html='<li><a onclick=menusTwo("'+item.sid+'")>'+item.sname+'</a></li>';
				$(".link").append(html);
				});
//			menusTwo();
		},
		error:function(data) {
			self.parent.frames['main'].hint("请求异常");
		}
	});
}

function menusTwo(str){
	if(str==null){
		str="1";
	}
	self.parent.frames['menusTwo'].gainMenusTwo(str);
}