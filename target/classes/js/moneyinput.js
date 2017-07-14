	 //----------金额输入判断-----------
		  //obj需要判断的对象
		  //index几位小数
		  //num判断状态:输入or失焦
		 function moneyCheckOne(obj,index,num){
			 var txt="";
			 var temp=$(obj).val();
			 for (var i = 0; i < temp.length; i++) {
				var tempchar=temp.charAt(i);
				if(IsNum(tempchar)){
					txt+=tempchar;
				}else{
					if(tempchar=="."&&i!=0){
					moneyCheckTwo(obj,index,num);
					}else if(tempchar=="."&&i==0){
					txt=temp.substring(0,temp.length-1);
					$(obj).val(txt);
					}else{
					$(obj).val(txt);
					}
					break;
				}
			}
		 }
		  //obj需要判断的对象
		  //index几位小数
		  //num判断状态:输入or失焦
		 function moneyCheckTwo(obj,index,num){
			 var objval=$(obj).val();
			 var Oneflag=objval.indexOf(".");
			 var txt="";
			 if(Oneflag!=-1){
				 var lastflag=objval.lastIndexOf(".");
				 if(Oneflag==lastflag){
					 if(Oneflag==(objval.length-1)){
					 if(num==2){//输入确认2失焦后,1是输入时
						 txt= objval.substring(0,Oneflag);
					 }else{
						 txt =objval;
					 }
					 }else{
						 txt=decimals(objval,index);
					 }
				 }else{
					 txt=decimals(objval,index);
				 }
				 $(obj).val(txt);
			 }
		 }
		 //需要切割的字符串
		 //index切割几位小数
		 function decimals(objval,index){
			 var Oneflag=objval.indexOf(".");
			var txt= objval.substring(0,Oneflag+1);
			 for (var i = Oneflag+1; i < objval.length&&i<Oneflag+index+1; i++) {
				 var tempchar=objval.charAt(i);
				 if(IsNum(tempchar)){
						txt+=tempchar;
				}else{
					break;
				}
			}
			 return txt;
		 }
		 //是否为数字判断
		 function IsNum(num){
			  var reNum=/^\d*$/;
			  return(reNum.test(num));
		}