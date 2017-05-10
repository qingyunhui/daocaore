<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/public/library.jsp"%>
<html>
<head>
<%-- <%@ include file="/WEB-INF/jsp/public/head.jsp"%> --%>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" href="${path}plugs/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="${path}plugs/dist/css/AdminLTE.min.css">
<!-- iCheck -->
<link rel="stylesheet" href="${path}plugs/iCheck/square/blue.css">
<%-- <link href="${path}css/sysBills/m.common.css" rel="stylesheet" type="text/css" /> --%>
</head>
	<body class="hold-transition login-page">
		<div class="login-box">
		  <div class="login-logo">
		    <a href="../../index2.html"><b>Admin</b>LTE</a>
		  </div>
		  <!-- /.login-logo -->
		  <div class="login-box-body">
		    <p class="login-box-msg">Sign in to start your session</p>
		    <form action="../../index2.html" method="post">
		      <div class="form-group has-feedback">
		        <input type="email" class="form-control" placeholder="Email">
		        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
		      </div>
		      <div class="form-group has-feedback">
		        <input type="password" class="form-control" placeholder="Password">
		        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
		      </div>
		      <div class="row">
		        <div class="col-xs-8">
		          <div class="checkbox icheck">
		            <label>
		              <input type="checkbox"> Remember Me
		            </label>
		          </div>
		        </div>
		        <!-- /.col -->
		        <div class="col-xs-4">
		          <button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
		        </div>
		        <!-- /.col -->
		      </div>
		    </form>
		    <div class="social-auth-links text-center">
		      <p>- OR -</p>
		      <a href="javascript:void(0);" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign in using
		        Facebook</a>
		      <a href="javascript:void(0);" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign in using
		        Google+</a>
		    </div>
		    <!-- /.social-auth-links -->
		    <a href="javascript:void(0);">I forgot my password</a><br>
		    <a href="register.html" class="text-center">Register a new membership</a>
		  </div>
		  <!-- /.login-box-body -->
		</div>
		<!-- /.login-box -->
		<!-- jQuery 2.2.3 -->
		<script src="${path}js/jQuery/jquery-2.2.3.min.js"></script>
		<!-- Bootstrap 3.3.6 -->
		<script src="${path}plugs/bootstrap/js/bootstrap.min.js"></script>
		<!-- iCheck -->
		<script src="${path}plugs//iCheck/icheck.min.js"></script>
		<script>
		  $(function () {
		    $('input').iCheck({
		      checkboxClass: 'icheckbox_square-blue',
		      radioClass: 'iradio_square-blue',
		      increaseArea: '20%' // optional
		    });
		  });
		</script>
	</body>
</html>