 $(function(){
  var loginV=new Vue({
   el: '#loginDiv',
   data: {
     username: '',
 	 password: ''
   }
 });


 $("#loginBtn").click(

     function(){
     if(!loginV.password||!loginV.username){
        alert("用户名或密码不能为空");
        return false;
     }
     var param={username:loginV.username,password:hex_md5(loginV.password)};
         $.ajax({
             type:"POST",
             dataType:"JSON",
             contentType: 'application/json',
             async: false,
             url:loginUrl,
             data:JSON.stringify(param),
             success:function(res){
                 console.log(res);
             }

         });
     }
 );

 });

