var cookieExpiresDays = 30;
//设置cookie保存时间,天为单位
var myCookieUtil={
        set:function(name,value){
            var exp = new Date();
            exp.setTime(exp.getTime() + cookieExpiresDays*24*60*60*1000);
            document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
            return true;
        },
        get:function(name){
            var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
            if(arr=document.cookie.match(reg))
                return unescape(arr[2]);
            else
                return null;
        },
        del:function(name){
            var exp = new Date();
            exp.setTime(exp.getTime() - 1);
            var cval=this.get(name);
            if(cval!=null) {
                document.cookie= name + "="+cval+";expires="+exp.toGMTString();
                return true;
            }else{
                return false;
            }
        }
    }