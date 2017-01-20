<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <title>广东九联科技股份有限公司SCM系统-用户注册</title>
<jsp:include page="bootstrap/pub.jsp"></jsp:include>
<style type="text/css">
body {
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #eee;
}
/* label{
  float:left;
   width:120px;
   text-align:right;
   padding:4px;
   margin:1px;
} */
/* .form-signin {
  max-width: 330px;
  padding: 15px;
  margin: 0 auto;
}
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 10px;
}
.form-signin .checkbox {
  font-weight: normal;
}
.form-signin .form-control {
  position: relative;
  height: auto;
  -webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="text"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
} */

</style>
  </head>

  <body>

    <div class="container">

      <form id="form_user" class="form-signin" action="${pageContext.request.contextPath}/user/regu" method="post">
        <h2 class="form-signin-heading">请您注册</h2>
        <div id="error_msg" class="warning" style='color:red; display:none;'></div>
        <label for="inputName">用户名</label>
        <input type="text" id="inputName" name="name" style="vertical-align: middle;" class="form-control" placeholder="用户名" required autofocus/>
        <label for="inputEmail" class="sr-only">邮箱</label>
        <input type="email" id="inputEmail" name="email" class="form-control" placeholder="邮箱验证通过才能登陆" required/>
        <label for="inputPwd" class="sr-only">密码</label>
        <input type="password" id="inputPwd" name="pwd" class="form-control" placeholder="密码" required/>
        <label for="inputInvite" class="sr-only">邀请码</label>
        <input type="password" id="inputInvite" name="invite" class="form-control" placeholder="请输入邀请函里面的邀请码"/>
        <label for="inputSafecode" class="sr-only">验证码</label>
        <input type="password" id="inputSafecode" name="safecode" style="width: 100px;float: left;" class="form-control" autocomplete="off" maxlength="4" placeholder="输入验证码" required/>
        &nbsp;&nbsp;<a id="verifyA" href="javascript:void(0);">
        <img border="0" align="absmiddle" style="cursor: pointer" alt="点击刷新验证码" src="pcrimg" id="verifyImg" width="100"></a>
        <button id="inputSubmit" class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
      </form>

    </div>
<script type="text/javascript">
$(function(){
    $('#verifyImg').click(function () {//生成验证码
    	$(this).hide().attr('src', 'pcrimg?' + Math.floor(Math.random()*100) ).fadeIn(); 
    });
});
$('#form_user').ajaxForm({
    beforeSubmit: function(a,f,o) {
    	var name = $('#inputName').val();
    	var email = $('#inputEmail').val();
    	var password = $('#inputPwd').val();
    	var safecode = $('#inputSafecode').val();
    	
		if(name.length>0 && name.trim()!="" && name!=null){
			$('#error_msg').html("");
		}else{
			$('#inputName').focus();
			$('#error_msg').html("请输入用户名，以后可作为登陆的账号，请牢记");
			$('#error_msg').show();
			return false;
		}
		if(password.length>0 && password.trim()!="" && password!=null){
			$('#error_msg').html("");
		}else{
			$('#inputPwd').focus();
			$('#error_msg').html("请输入登录密码，请牢记");
			$('#error_msg').show();
			return false;
		}
		
		if(email.length>0 && email.trim()!="" && email!=null){
			$('#error_msg').html("");
		}else{
			$('#error_msg').html("邮箱不能为空");
			$('#inputEmail').focus();
			$('#error_msg').show();
			return false;
		}
		
		if(safecode.length>0 && safecode.trim()!="" && safecode!=null){
			$('#error_msg').html("");
		}else{
			$('#error_msg').html("验证码不能为空");
			$('#inputEmail').focus();
			$('#error_msg').show();
			return false;
		}
		
		$("#inputSubmit").html("正在提交中。。。");
		$("#inputSubmit").attr("disabled","disabled");
    },
    success: function(json) {
    	if(!json.success){
    		$("#inputSubmit").html("注册");
    		$("#inputSubmit").attr("disabled",false);
    	}
    	$('#error_msg').html(json.msg);
		$('#error_msg').show();
    }
});

</script>
  </body>
</html>
