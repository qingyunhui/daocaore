<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>广东九联科技股份有限公司SCM系统-用户登录</title>
<jsp:include page="bootstrap-ace/pub.jsp"></jsp:include>

</head>

<body class="login-layout">
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="center">
							<h1>
								<i class="ace-icon fa fa-leaf green"></i> <span class="red">SCM</span>
								<span class="white" id="id-text2">供应链管理系统</span>
							</h1>
							<h4 class="blue" id="id-company-text">&copy; 广东九联科技股份有限公司</h4>
						</div>

						<div class="space-6"></div>

						<div class="position-relative">
							<div id="login-box"
								class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header blue lighter bigger">
											<i class="ace-icon fa fa-coffee green"></i> 请输入您的信息
										</h4>

										<div class="space-6"></div>
										
										<div id="login_error_msg" class="warning" style='color:red; display:none;'></div>
										
										<form id="form_login" action="${pageContext.request.contextPath}/user/login" method="post">
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="text" class="form-control" id="inputName" title="用户名" name="name" placeholder="用户名" required autofocus/>
														<i class="ace-icon fa fa-user"></i>
												</span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="password" id="inputPwd" title="密码" name="pwd" class="form-control"
														placeholder="密码" required/> <i class="ace-icon fa fa-lock"></i>
												</span>
												</label>

												<div class="space"></div>

												<div class="clearfix">
													<label class="inline"> <input type="checkbox"
														class="ace" name="remember" value="yes"/> <span class="lbl"> 记住我</span>
													</label>

													<button id="inputSubmit" type="submit"
														class="width-35 pull-right btn btn-sm btn-primary">
														<i class="ace-icon fa fa-key"></i> <span
															class="bigger-110">登陆</span>
													</button>
												</div>

												<div class="space-4"></div>
											</fieldset>
										</form>
										
									</div>
									<!-- /.widget-main -->

									<div class="toolbar clearfix">
										<div>
											<a href="#" data-target="#forgot-box"
												class="forgot-password-link"> <i
												class="ace-icon fa fa-arrow-left"></i> 忘了密码
											</a>
										</div>

										<div>
											<a href="#" data-target="#signup-box"
												class="user-signup-link"> 注册 <i
												class="ace-icon fa fa-arrow-right"></i>
											</a>
										</div>
									</div>
								</div>
								<!-- /.widget-body -->
							</div>
							<!-- /.login-box -->

							<div id="forgot-box" class="forgot-box widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header red lighter bigger">
											<i class="ace-icon fa fa-key"></i> 找回密码
										</h4>

										<div class="space-6"></div>
										<p>输入您注册时使用的电子邮件地址</p>
										
										<div id="repwd_error_msg" class="warning" style='color:red; display:none;'></div>
										
										<form id="form_repwd" action="${pageContext.request.contextPath}/user/checkEmailForPwd" method="post">
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="email" class="form-control" id="inputEmailForPwd" title="邮箱" name="email" placeholder="Email" required autofocus/>
														<i class="ace-icon fa fa-envelope"></i>
												</span>
												</label>

												<div class="clearfix">
													<button id="inputSubmitForPwd" type="submit"
														class="width-35 pull-right btn btn-sm btn-danger">
														<i class="ace-icon fa fa-lightbulb-o"></i> <span
															class="bigger-110">发送</span>
													</button>
												</div>
											</fieldset>
										</form>
									</div>
									<!-- /.widget-main -->

									<div class="toolbar center">
										<a href="#" data-target="#login-box"
											class="back-to-login-link"> 回到登陆页面 <i
											class="ace-icon fa fa-arrow-right"></i>
										</a>
									</div>
								</div>
								<!-- /.widget-body -->
							</div>
							<!-- /.forgot-box -->

							<div id="signup-box" class="signup-box widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header green lighter bigger">
											<i class="ace-icon fa fa-users blue"></i> 新用户注册
										</h4>

										<div class="space-6"></div>
										
										<div id="reg_error_msg" class="warning" style='color:red; display:none;'></div>

										<form id="form_reg" action="${pageContext.request.contextPath}/user/regu" method="post">
											<fieldset>
											    <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="text" id="regName" title="用户名" name="name" class="form-control" placeholder="用户名" required autofocus/>
														<i class="ace-icon fa fa-user"></i>
												</span>
												</label>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="email" id="regEmail" title="邮箱" name="email" class="form-control" placeholder="邮箱验证通过才能登陆" required/>
														<i class="ace-icon fa fa-envelope"></i>
												</span>
												</label>  <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="password" id="regPwd" name="pwd" title="密码" class="form-control"
														placeholder="密码" required/> <i class="ace-icon fa fa-lock"></i>
												</span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input type="text" id="inputInvite" title="邀请码" name="invite" class="form-control" placeholder="请输入邀请函里面的邀请码"/> <i
														class="ace-icon fa fa-retweet"></i>
												</span>
												</label> <label class="block"> <input type="checkbox"
													class="ace" /> <span class="lbl"> 我接受 <a
														href="#">用户协议</a>
												</span>
												</label>

												<div class="space-24"></div>

												<div class="clearfix">
													<button type="reset" class="width-30 pull-left btn btn-sm">
														<i class="ace-icon fa fa-refresh"></i> <span
															class="bigger-110">重置</span>
													</button>

													<button id="regSubmit" type="submit"
														class="width-65 pull-right btn btn-sm btn-success">
														<span class="bigger-110">注册</span> <i
															class="ace-icon fa fa-arrow-right icon-on-right"></i>
													</button>
												</div>
											</fieldset>
										</form>
									</div>

									<div class="toolbar center">
										<a href="#" data-target="#login-box"
											class="back-to-login-link"> <i
											class="ace-icon fa fa-arrow-left"></i> 回到登陆
										</a>
									</div>
								</div>
								<!-- /.widget-body -->
							</div>
							<!-- /.signup-box -->
						</div>
						<!-- /.position-relative -->

						<div class="navbar-fixed-top align-right">
							<br /> &nbsp; <a id="btn-login-dark" href="#">Dark</a> &nbsp; <span
								class="blue">/</span> &nbsp; <a id="btn-login-blur" href="#">Blur</a>
							&nbsp; <span class="blue">/</span> &nbsp; <a id="btn-login-light" href="#">Light</a> &nbsp; &nbsp; &nbsp;
						</div>
					</div>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.main-content -->
	</div>
	<!-- /.main-container -->

	<script type="text/javascript">
jQuery(function($) {
	 $(document).on('click', '.toolbar a[data-target]', function(e) {
		e.preventDefault();
		var target = $(this).data('target');
		$('.widget-box.visible').removeClass('visible');//hide others
		$(target).addClass('visible');//show target
	 });
	});
	
	//you don't need this, just used for changing background
	jQuery(function($) {
	 $('#btn-login-dark').on('click', function(e) {
		$('body').attr('class', 'login-layout');
		$('#id-text2').attr('class', 'white');
		$('#id-company-text').attr('class', 'blue');
		
		e.preventDefault();
	 });
	 $('#btn-login-light').on('click', function(e) {
		$('body').attr('class', 'login-layout light-login');
		$('#id-text2').attr('class', 'grey');
		$('#id-company-text').attr('class', 'blue');
		
		e.preventDefault();
	 });
	 $('#btn-login-blur').on('click', function(e) {
		$('body').attr('class', 'login-layout blur-login');
		$('#id-text2').attr('class', 'white');
		$('#id-company-text').attr('class', 'light-blue');
		
		e.preventDefault();
	 });
	 
	});


$('#form_login').ajaxForm({
    beforeSubmit: function(a,f,o) {
		if($('#inputName').val().length == 0){
			$('#inputName').focus();
			$('#login_error_msg').html("请输入帐号");
			$('#login_error_msg').show();
			return false;
		}
		if($('#inputPwd').val().length == 0){
			$('#inputPwd').focus();
			$('#login_error_msg').html("请输入登录密码");
			$('#login_error_msg').show();
			return false;
		}
		$('#login_error_msg').html("");
		$("#inputSubmit").html("正在提交中。。。");
		$("#inputSubmit").attr("disabled","disabled");
    },
    success: function(json) {
    	if(json.success){
    		location.replace('${pageContext.request.contextPath}/main');
    	}else{
    		$("#inputSubmit").html("登陆");
    		$("#inputSubmit").attr("disabled",false);
    		$('#login_error_msg').html(json.msg);
    		$('#login_error_msg').show();
    	}
    }
});


$('#form_repwd').ajaxForm({
    beforeSubmit: function(a,f,o) {
		if($('#inputEmailForPwd').val().length == 0){
			$('#inputEmailForPwd').focus();
			$('#repwd_error_msg').html("请输入邮箱");
			$('#repwd_error_msg').show();
			return false;
		}
		$('#repwd_error_msg').html("");
		$("#inputSubmitForPwd").html("正在提交中。。。");
		$("#inputSubmitForPwd").attr("disabled","disabled");
    },
    success: function(json) {
    	if(json.success){
    		$('#repwd_error_msg').html(json.msg);
    		$('#repwd_error_msg').show();
    	}else{
    		$("#inputSubmitForPwd").html("提交");
    		$("#inputSubmitForPwd").attr("disabled",false);
    		$('#repwd_error_msg').html(json.msg);
    		$('#repwd_error_msg').show();
    	}
    }
});

$('#form_reg').ajaxForm({
    beforeSubmit: function(a,f,o) {
    	var name = $('#regName').val();
    	var email = $('#regEmail').val();
    	var password = $('#regPwd').val();
    	var safecode = $('#inputSafecode').val();
    	
		if(name.length>0 && name.trim()!="" && name!=null){
			$('#reg_error_msg').html("");
		}else{
			$('#regName').focus();
			$('#reg_error_msg').html("请输入用户名，以后将作为登陆的账号，请牢记");
			$('#reg_error_msg').show();
			return false;
		}
		if(password.length>0 && password.trim()!="" && password!=null){
			$('#reg_error_msg').html("");
		}else{
			$('#regPwd').focus();
			$('#reg_error_msg').html("请输入登录密码，请牢记");
			$('#reg_error_msg').show();
			return false;
		}
		
		if(email.length>0 && email.trim()!="" && email!=null){
			$('#reg_error_msg').html("");
		}else{
			$('#reg_error_msg').html("邮箱不能为空");
			$('#regEmail').focus();
			$('#reg_error_msg').show();
			return false;
		}
		
		if(safecode.length>0 && safecode.trim()!="" && safecode!=null){
			$('#reg_error_msg').html("");
		}else{
			$('#reg_error_msg').html("验证码不能为空");
			$('#inputSafecode').focus();
			$('#reg_error_msg').show();
			return false;
		}
		
		$("#regSubmit").html("正在提交中。。。");
		$("#regSubmit").attr("disabled","disabled");
    },
    success: function(json) {
    	if(!json.success){
    		$("#regSubmit").html("注册");
    		$("#regSubmit").attr("disabled",false);
    	}
    	$('#reg_error_msg').html(json.msg);
		$('#reg_error_msg').show();
    }
});
</script>
</body>
</html>
