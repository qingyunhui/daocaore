<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <title>广东九联科技股份有限公司SCM系统-找回密码</title>
<jsp:include page="bootstrap/pub.jsp"></jsp:include>
<style type="text/css">
body {
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #eee;
}

.form-signin {
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
}
</style>
  </head>

  <body>

    <div class="container">

      <form id="form_user" class="form-signin" action="${pageContext.request.contextPath}/user/checkEmailForPwd" method="post">
        <h2 class="form-signin-heading">找回登录密码</h2>
        <div id="error_msg" class="warning" style='color:red; display:none;'></div>
        <label for="inputEmail" class="sr-only">邮箱</label>
        <input type="email" id="inputEmail" name="email" class="form-control" placeholder="注册邮箱" required autofocus>
        <label for="inputSafecode" class="sr-only">验证码</label>
        <input type="password" id="inputSafecode" name="safecode" style="width: 100px;float: left;" class="form-control" autocomplete="off" maxlength="4" placeholder="输入验证码" required/>
        &nbsp;&nbsp;<a id="verifyA" href="javascript:void(0);">
        <img border="0" align="absmiddle" style="cursor: pointer;" alt="点击刷新验证码" src="pcrimg" id="verifyImg" width="100"></a>
        <button id="inputSubmit" class="btn btn-lg btn-primary btn-block" type="submit">提交</button>
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
		if($('#inputEmail').val().length == 0){
			$('#inputEmail').focus();
			$('#error_msg').html("请输入邮箱");
			$('#error_msg').show();
			return false;
		}
		if($('#inputSafecode').val().length == 0){
			$('#inputSafecode').focus();
			$('#error_msg').html("请输入验证码");
			$('#error_msg').show();
			return false;
		}
		$('#error_msg').html("");
		$("#inputSubmit").html("正在提交中。。。");
		$("#inputSubmit").attr("disabled","disabled");
    },
    success: function(json) {
    	if(json.success){
    		$('#error_msg').html(json.msg);
    		$('#error_msg').show();
    	}else{
    		$("#inputSubmit").html("提交");
    		$("#inputSubmit").attr("disabled",false);
    		$('#error_msg').html(json.msg);
    		$('#error_msg').show();
    	}
    }
});

</script>
  </body>
</html>
