 $(function(){
  var loginV=new Vue({
   el: '#loginDiv',
   data: {
     username: '',
 	 password: ''
   }
 });
console.log("ss:"+hex_md5("123456"+"Mozhumz_Xr_WangWei"));

 $("#loginBtn").click(

     function(){
     if(!loginV.password||!loginV.username){
        alert("用户名或密码不能为空");
        return false;
     }
     var param={username:loginV.username,password:hex_md5(loginV.password+DEFAULT_KEY)};
         $.ajax({
             type:"POST",
             dataType:"JSON",
             contentType: 'application/json',
             async: false,
             url:loginUrl,
             data:JSON.stringify(param),
             success:function(res){
                 console.log(res);
//                 window.location.href='http://www.baidu.com';
             }

         });
     }
 );

 });

