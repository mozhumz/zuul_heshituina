 $(function(){

  var mainV=new Vue({
   el: '#main',
   data:{
     username: '',
 	 password: '',
   },
   methods:{
     open(msg,type) {
        if(!type){
            type='error';
        }
        this.$message({
            message: msg,
            type: type
        });
     }

   }
   });
var loginHtml=window.location.href;
console.log(loginHtml);
var webUrl=getWebUrl(loginHtml);
console.log(webUrl);
var chooseBox='<template>'+
              '            <el-select v-if="false" v-model="value" placeholder="请选择">'+
              '                <el-option'+
              '                        v-for="item in options"'+
              '                        :key="item.value"'+
              '                        :label="item.label"'+
              '                        :value="item.value">'+
              '                </el-option>'+
              '            </el-select>'+
              '        </template>';
              
//登录
 $("#loginBtn").click(

     function(){
     console.log(mainV.username);
     console.log(mainV.password);
     if(!mainV.password||!mainV.username){
//        alert("用户名或密码不能为空");
        mainV.open("用户名或密码不能为空");
        return false;
     }
     var param={username:mainV.username,password:hex_md5(mainV.password+DEFAULT_KEY)};
     var res=ajax("POST",loginUrl,param);
     if(res.status){
        setCookie(userCookieName,res.data,120);
        //判断是否有多个角色
        if(res.data.roleList.length>1){
            //重定向到role.html 选择角色
            if(webUrl){
                window.location.href='role.html?webUrl='+webUrl;
                return false;
            }
            window.location.href='role.html';
            return false;
        }
        if(webUrl){
            window.location.href=webUrl+'?token='+res.data.token;
        }else{
            window.location.href=usermanageUrl;
        }
     }else{
        mainV.open(res.message);
     }

     }
 );
 //回车键绑定
 $("body").keydown(function() {
     if (event.keyCode == "13") {//keyCode=13是回车键
         $('#loginBtn').click();//换成按钮的id即可
     }
 });

 

 });

