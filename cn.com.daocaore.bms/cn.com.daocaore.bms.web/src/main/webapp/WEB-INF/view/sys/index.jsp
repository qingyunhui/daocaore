<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/public/library.jsp"%>
<html>
<head>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" href="${path}plugs/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <link rel="stylesheet" href="${path}plugs/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="${path}plugs/dist/css/skins/skin-blue.min.css">
</head>
	<body class="hold-transition skin-blue sidebar-mini">
		<div class="wrapper">
		
		  <!-- Headeer side column. -->
		  <%@ include file="/WEB-INF/view/public/header.jsp"%>
		  
		  <!-- Left side column. contains the logo and sidebar -->
		  <%@ include file="/WEB-INF/view/public/left.jsp"%>
		
		  <!-- Content Wrapper. Contains page content -->
		  <div class="content-wrapper">
		    <!-- Content Header (Page header) -->
		
		    <!-- Main content -->
		    <section class="content">
		      <!-- Your Page Content Here -->
				
		    </section>
		    <!-- /.content -->
		  </div>
		  <!-- /.content-wrapper -->
		
		  <!-- Main Footer -->
		  <%@ include file="/WEB-INF/view/public/footer.jsp"%>
		
		  <!-- Add the sidebar's background. This div must be placed
		       immediately after the control sidebar -->
		  <div class="control-sidebar-bg"></div>
		</div>
		<!-- ./wrapper -->
		
		<!-- REQUIRED JS SCRIPTS -->
		<script src="${path}js/jQuery/jquery-2.2.3.min.js"></script>
		<!-- Bootstrap 3.3.6 -->
		<script src="${path}plugs/bootstrap/js/bootstrap.min.js"></script>
		<!-- AdminLTE App -->
		<script src="${path}plugs/dist/js/app.min.js"></script>
		</body>
</html>