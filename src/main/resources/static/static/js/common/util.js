function ajax(method,url,param){
var result=null;
$.ajax({
             type:method,
             dataType:"JSON",
             contentType: 'application/json',
             async: false,
             url:url,
             data:JSON.stringify(param),
             success:function(res){
                    if(!res.status&&res.code==10005){
                        //重定向到登录页
                        window.location.href=zuulUrl;
                    }
                    result= res;
             }

         });
    return result;
}

function getWebUrl(html){
    try{
        var s=html.split('?');
        if(s.length>1){
            return s[1].split('=')[1];
        }
    }catch(Error ){

    }
    return null;
}

