var urlarray={MenusUrl:"/Reception/Menus/toMenus.html"};
$(function() {
	gainMenusTwo("4");
});

function gainMenusTwo(num){
	var url=ProjectUrl(urlarray.MenusUrl);
	$.ajax({
		url : url,
		type : "POST",
		data : num,
		async : true,
		contentType : "application/json",
		dataType : "json",
		success : function(data) {
			var menus=JSON.parse(data);
			var html="";
			$(".link").html("");
			$.each(menus, function(i, item){
				html='<li><a onclick=Main("'+ProjectUrl(item.spath)+'","'+item.sname+'")>'+item.sname+'</a></li>';
				$(".link").append(html);
				});
		},
		error:function(data) {
			self.parent.frames['main'].hint("请求异常");
		}
	});
}
function Main(str,titlename){
	$(parent.document.getElementById("titlename")).text(titlename);
	$(parent.document.getElementsByName('main')).attr("src",str);
//	self.parent.frames['main'].hint(str);
}