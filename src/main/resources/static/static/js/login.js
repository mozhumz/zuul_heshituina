 $(function(){
  var loginV=new Vue({
   el: '#main',
   data:{
     username: '',
 	 password: ''
   },
   methods:{
     open(msg) {
                     this.$alert(msg, 'ss', {
                       confirmButtonText: '确定',
                       callback: action => {
                         this.$message({
                           type: 'info',
                           message: `action: ${ action }`
                         });
                       }
                     });
                   }
   }
   });
var loginHtml=window.location.href;
console.log(loginHtml);
var webUrl=getWebUrl(loginHtml);
console.log(webUrl);
 $("#loginBtn").click(

     function(){
     console.log(loginV.username);
     console.log(loginV.password);
     if(!loginV.password||!loginV.username){
        alert("用户名或密码不能为空");
        return false;
     }
     var param={username:loginV.username,password:hex_md5(loginV.password+DEFAULT_KEY)};
     var res=ajax("POST",loginUrl,param);
     if(res.status){
        console.log("ok");
        if(webUrl){
            window.location.href=webUrl+'?token='+res.data.token;
        }else{
            window.location.href='../usermanage/index.html';
        }
     }

     }
 );

 });

