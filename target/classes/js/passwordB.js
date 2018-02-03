function checkPasswordB(username,password){
		//除数字字母外
		for (var i = 0; i < password.length; i++) {
			if(password.charCodeAt(i)<48 
					|| (password.charCodeAt(i)>57 && password.charCodeAt(i)<65) 
					|| (password.charCodeAt(i)>90 && password.charCodeAt(i)<97)
					|| password.charCodeAt(i)>122){
				return "正确";
			}
		}
		//连续判断
		var list=password.split("");
		var value1=list[0];
		var key1=0;
		for (var i1 = 0; i1 < list.length; i1++) {
			if(value1==list[i1]){
				key1++;
			}
		}
		if(key1==password.length){
			return "密码不得是连续相同的数字或字母";
		}
		//顺序/倒序
		var key2=password.charCodeAt(1)-password.charCodeAt(0);
		var key2_1=0;
		if(key2==1 || key2==-1){
			for (var i2 = 0; i2 < list.length-1; i2++) {
				if(password.charCodeAt(i2)+key2==password.charCodeAt(i2+1)){
					key2_1++;
				}
			}
			if(key2_1==password.length-1){
				return "密码不得是连续的数字或字母（包括顺序和倒序）";
			}
		}
		//前/后几位判断
		var msgkey="登录号码";
		var pindex=username.indexOf(password);
		if( pindex != -1 ){
			if(pindex==0){
				return "密码不得是"+msgkey+"的前n位";
			}else{
				if(pindex+password.length==username.length){
					return "密码不得是"+msgkey+"的后n位";
				}
			}
		}
		//两位顺序
		var key3=password.charCodeAt(2)-password.charCodeAt(0);
		var key3_1=0;
		var key3_2=0;
		if(password.length%2==0){
			for (var i3 = 0; i3 < list.length/2; i3++) {
				if(list[i3*2]==list[(i3*2)+1]){
					key3_1++;
				}
			}
			if(key3_1==list.length/2){
				for (var i4 = 0; i4 < list.length/2-1; i4++) {
					if(password.charCodeAt(i4*2)+key3==password.charCodeAt((i4+1)*2)){
						key3_2++;
					}
				}
			}
			if(key3_2==password.length/2-1){
				return "密码不得是连续两位数字或字母叠数（包括顺序和倒序）";
			}
		}
		//默认值
		var defuat=["fz123456","a123456"];
		for (var i5 = 0; i5 < defuat.length; i5++) {
			if(password==defuat[i5]){
				return "密码不得是系统配置的初始化密码";
			}
		}
		
		
		return "正确";
}