 $(function(){
 console.log(getCookie(userCookieName));

  var mainV=new Vue({
   el: '#main',
   data:{
     username: '',
 	 password: '',
 	 roleId: '',
 	 roleList:getCookie(userCookieName).roleList
   },
   methods:{
     open(msg) {
                         this.$alert(msg, '提示', {
                       confirmButtonText: '确定',
                       callback: action => {
//                         this.$message({
//                           type: 'info',
//                           message: `action: ${ action }`
//                         });
                       }
                     });
                   }
   }
   });
var url=window.location.href;
console.log(url);
var webUrl=getWebUrl(url);

 //选择角色
  $("#chooseRole").click(function(){
    var param={id:mainV.roleId};
    var res=ajax("POST",setRoleUrl,param);
    if(res.status){
         //设置cookie
         console.log(getCookie(userCookieName));
         var roleList=[];
          setCookie(userCookieName,res.data,120);
         //跳转到目标页
         if(webUrl){
            window.location.href=webUrl+'?token='+res.data.token;
         }else{
            window.location.href='../usermanage/index.html';
         }
    }
  });
 

 });

