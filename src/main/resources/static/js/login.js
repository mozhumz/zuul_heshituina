 $(function(){
  var loginV=new Vue({
   el: '#btn',
   data: {
     username: '',
 	 password: ''
   }
 });
 new Vue().$mount('#btn')
var Main = {
    data() {
      return {
        password: ''
      }
    }
  }
  var Main2= {
    data() {
      return {
        username: ''
      }
    }
  }
var Ctor = Vue.extend(Main);
Vue.extend(Main2);
new Ctor().$mount('#btn');

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

