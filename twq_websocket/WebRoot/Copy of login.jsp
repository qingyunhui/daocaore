<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <title>广东九联科技股份有限公司SCM系统-用户登录</title>
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

      <form id="form_user" class="form-signin" action="${pageContext.request.contextPath}/user/login" method="post">
        <h2 class="form-signin-heading">请您登陆</h2>
        <div id="error_msg" class="warning" style='color:red; display:none;'></div>
        <label for="inputName" class="sr-only">用户名</label>
        <input type="text" id="inputName" name="name" class="form-control" placeholder="用户名" required autofocus>
        <label for="inputPwd" class="sr-only">密码</label>
        <input type="password" id="inputPwd" name="pwd" class="form-control" placeholder="密码" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" name="remember" value="yes"> 记住我
          </label>
          <a href="repwd.jsp" style="float: right;">忘记密码？</a>
        </div>
        <button id="inputSubmit" class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>
         还没有账号？<a href="register.jsp" >请注册</a>
      </form>

    </div>
<script type="text/javascript">
$('#form_user').ajaxForm({
    beforeSubmit: function(a,f,o) {
		if($('#inputName').val().length == 0){
			$('#inputName').focus();
			$('#error_msg').html("请输入帐号");
			$('#error_msg').show();
			return false;
		}
		if($('#inputPwd').val().length == 0){
			$('#inputPwd').focus();
			$('#error_msg').html("请输入登录密码");
			$('#error_msg').show();
			return false;
		}
		$('#error_msg').html("");
		$("#inputSubmit").html("正在提交中。。。");
		$("#inputSubmit").attr("disabled","disabled");
    },
    success: function(json) {
    	if(json.success){
    		location.replace('${pageContext.request.contextPath}/main');
    	}else{
    		$("#inputSubmit").html("登陆");
    		$("#inputSubmit").attr("disabled",false);
    		$('#error_msg').html(json.msg);
    		$('#error_msg').show();
    	}
    }
});

</script>
  </body>
</html>
