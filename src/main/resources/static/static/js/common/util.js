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

function formatDate(date, fmt) {
  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
  }
  var o = {
    'M+': date.getMonth() + 1,
    'd+': date.getDate(),
    'h+': date.getHours(),
    'm+': date.getMinutes(),
    's+': date.getSeconds()
  };
  for (var k in o) {
    if (new RegExp(`(${k})`).test(fmt)) {
      var str = o[k] + '';
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? str : padLeftZero(str));
    }
  }
  return fmt;
};

function padLeftZero(str) {
  return ('00' + str).substr(str.length);
};

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

//将java date转为js date
function getDate(date){
    if(!date){
        return null;
    }
    return new Date(date.time);
}

//判断是否为数字
function isNum(){
    for(var arg of arguments){
        if(!isRealNum(arg)){
            return false;
        }
    }
    return true;
}

function isRealNum(val){
    // isNaN()函数 把空串 空格 以及NUll 按照0来处理 所以先去除，

　　if(!val){
        return false;
　　}
   if(!isNaN(val)){　　　　
　　//对于空数组和只有一个数值成员的数组或全是数字组成的字符串，isNaN返回false，例如：'123'、[]、[2]、['123'],isNaN返回false,
   //所以如果不需要val包含这些特殊情况，则这个判断改写为if(!isNaN(val) && typeof val === 'number' )
　　　 return true;
　　}

　else{
　　　　return false;
　　}
}


//获取url中的指定参数值
function getParamValue(variable)
{
        var search=window.location.search;
        if(!search){
            return null;
        }
       var query = unescape(search.substring(1));

       var vars = query.split("&");
       for (var i=0;i<vars.length;i++) {
               var pair = vars[i].split("=");
               if(pair[0] == variable){return pair[1];}
       }
       return null;
}

//将java date转为js date
function getDate(date){
    if(!date){
        return null;
    }
    return new Date(date.time);
}

function getGender(gender){
    if(!gender){
        return null;
    }
    if(gender==1){
        return '男';
    }

    return '女';
}

function getSelectedOpList(select){
    var list=[];
    var optionList=select.children('option');
    for(var op of optionList){
        if(op.selected){
            list.push(op);
        }
    }
    return list;
}

function checkParams(){
    for (var i  of  arguments) {
        if(!i){
            return false;
        }
        if(Array.isArray(i) ){
            if(i.length==0){
                return false;
            }
        }
    }
    return true;
}