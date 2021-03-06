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
	    <section class="content-header">
	      <h1 id="h1Lable">
	        <!-- <small>preview of simple tables</small> -->
	      </h1>
	      <ol class="breadcrumb">
	        <li><a href="javascript:void(0);"><i class="fa fa-dashboard"></i>Home</a></li>
	        <li><a href="javascript:void(0);" id="lableOne">a</a></li>
	        <li class="active" id="lableTowe">b</li>
	      </ol>
	    </section>
	  	
	    <!-- Main content -->
	    <section class="content">
	      <!-- Your Page Content Here -->
	      <iframe id="ifr" src="" width="100%" height="950px" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="auto"></iframe>
	    </section>
	  </div>
	  <!-- Main Footer -->
	  <%@ include file="/WEB-INF/view/public/footer.jsp"%>
	  <!-- <div class="control-sidebar-bg"></div> -->
	</div>
</body>
<script type="text/javascript">
  function jump(url,text1,text2){
	$('#h1Lable').html(text1);
	$('#lableOne').html(text1);
	$('#lableTowe').html(text2);
	$('#ifr').attr('src',url);
  }
</script>
</html>