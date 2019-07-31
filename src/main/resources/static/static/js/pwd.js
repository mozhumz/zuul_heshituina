 $(function(){

  var mainV=new Vue({
   el: '#main',
   data(){
     var pwdCheck=(rule, value, callback) => {
                     if (!value) {
                       callback(new Error('请输入密码'));
                     }else if(value.length<6||value.length>18){
                        callback(new Error('密码位数必须在6-18之间'));
                     }
                     else {
                       callback();
                     }
       };
     return{
         username: '',
         password: '',
         lazyBtn_f:false,
         sendCodeBtn_f:false,
         sendCodeTxt:'发送验证码（有效期为5分钟）',
         sendCode_f:true,
         code_time:60,
         changePwdDto:{newPwd:null,code:null,type:1,isLogin:false,email:null,username:null,password2:null},
         userRules:{
            username: [
                 { required: true, message: '请输入账号', trigger: 'blur' }
               ],
             email: [
                 { required: true, message: '请输入该账户设置的邮箱', trigger: 'blur' }
               ],
             code: [
                  { required: true, message: '请输入验证码', trigger: 'blur' }
                ],
            newPwd: [
                 { required: true, validator: pwdCheck, trigger: 'blur' }
               ],
            password2: [
                    { required: true, message: '请再次输入密码', trigger: 'blur' }
                  ],
         },
     };

   },
   methods:{
    //提示消息
    open(res) {
             var msg=res.message;
             var type='error';
             if(res.status){
                 type='success';
                 msg=ok;
             }
             this.$message({
                 message: msg,
                 type: type
             });
    },

    disableBtn(type){
        if(!type){
          mainV.lazyBtn_f=true;
        }else{
          mainV.sendCodeBtn_f=true;
        }
      },
    enableBtn(type){
        setTimeout(function(){
            if(!type){
              mainV.lazyBtn_f=false;
            }else{
              mainV.sendCodeBtn_f=false;
            }
          },1000);
    },
    open2(msg) {
      var type='error';
      this.$message({
          message: msg,
          type: type
      });
   },
    //修改密码
     changePwd(formName){
       this.disableBtn();
       this.$refs[formName].validate((valid) => {
         if (valid) {
           this.disableBtn();
            if(!checkParams(this.changePwdDto.newPwd,this.changePwdDto.password2,this.changePwdDto.code)){
                this.open2(paramErr);
                this.enableBtn();
                return null;
            }
            if(this.changePwdDto.newPwd!=this.changePwdDto.password2){
                this.open2('两次输入的密码不一致');
                this.enableBtn();
                return null;
            }
            if(this.changePwdDto.newPwd=="123456"){
                this.open2('密码不能为初始密码：123456');
                this.enableBtn();
                return null;
            }
            this.changePwdDto.newPwd=hex_md5(this.changePwdDto.newPwd+DEFAULT_KEY);
            var param=this.changePwdDto;
            var res=ajax('POST',changePwdUrl,param);
            this.enableBtn();
            this.open(res);
            if(res.status){
                window.location.href='index.html';
            }
         } else {
           this.enableBtn();
           this.open2(paramErr);
           return false;
         }
       });

      },
     //发送验证码（有效期为5分钟）
      sendEmailCode(){

          this.sendCodeBtn_f=true;
          if(this.sendCode_f){
              var timer = setInterval(function () {

                  if(mainV.code_time == 60 && mainV.sendCode_f){
                      mainV.sendCode_f = false;
                        if(!mainV.changePwdDto.email){
                              mainV.open2('未设置邮箱，请联系admin管理员设置');
                              mainV.sendCode_f = true;
                              mainV.code_time = 60;
                              clearInterval(timer);
                              mainV.enableBtn(1);
                              return null;
                      }
                      var param={receiveEmail:mainV.changePwdDto.email,username:mainV.changePwdDto.username};
                      var res=ajax('POST',sendCodeUrl,param);
                      if(!res.status){
                             mainV.sendCode_f = true;
                            mainV.code_time = 60;
                            clearInterval(timer);
                            mainV.enableBtn(1);
                      }
                      mainV.open(res);
                }else if(mainV.code_time == 0){
                      mainV.sendCodeTxt='发送验证码（有效期为5分钟）';
                      mainV.enableBtn(1);
                      clearInterval(timer);
                      mainV.code_time = 60;
                      mainV.sendCode_f = true;
                  }else {
                     mainV.sendCodeTxt=mainV.code_time + " s 重新发送";
                      mainV.code_time--;
                  }
              },1000);
           }

      }
  },
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

