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
		
		  <!-- TODO 导入 header.jsp -->
		  <%@ include file="/WEB-INF/view/public/header.jsp"%>
		  
		  <!-- Left side column. contains the logo and sidebar -->
		  <aside class="main-sidebar">
		
		    <!-- sidebar: style can be found in sidebar.less -->
		    <section class="sidebar">
		
		      <!-- Sidebar user panel (optional) -->
		      <div class="user-panel">
		        <div class="pull-left image">
		          <img src="plugs/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
		        </div>
		        <div class="pull-left info">
		          <p>Alexander Pierce</p>
		          <!-- Status -->
		          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
		        </div>
		      </div>
		
		      <!-- search form (Optional) -->
		      <form action="#" method="get" class="sidebar-form">
		        <div class="input-group">
		          <input type="text" name="q" class="form-control" placeholder="Search...">
		              <span class="input-group-btn">
		                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
		                </button>
		              </span>
		        </div>
		      </form>
		      <!-- /.search form -->
		
		      <!-- Sidebar Menu -->
		      <ul class="sidebar-menu">
		        <li class="header">HEADER</li>
		        <!-- Optionally, you can add icons to the links -->
		        <li class="active">
		          <a href="#">
		            <i class="fa fa-link"></i> 
		            <span>Link</span>
		          </a>
		        </li>
		        <li class="treeview">
		          <a href="#">
		          	<i class="fa fa-link"></i> 
		          	<span>Multilevel</span>
		            <span class="pull-right-container">
		              <i class="fa fa-angle-left pull-right"></i>
		            </span>
		          </a>
		          <ul class="treeview-menu">
		            <li><a href="#">Link in level 2</a></li>
		            <li><a href="#">Link in level 2</a></li>
		          </ul>
		        </li>
		      </ul>
		      <!-- /.sidebar-menu -->
		    </section>
		    <!-- /.sidebar -->
		  </aside>
		
		  <!-- Content Wrapper. Contains page content -->
		  <div class="content-wrapper">
		    <!-- Content Header (Page header) -->
		    <section class="content-header">
		      <h1>
		        Page Header
		        <small>Optional description</small>
		      </h1>
		      <ol class="breadcrumb">
		        <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
		        <li class="active">Here</li>
		      </ol>
		    </section>
		
		    <!-- Main content -->
		    <section class="content">
		      <!-- Your Page Content Here -->
				<h1>well come!</h1>
		    </section>
		    <!-- /.content -->
		  </div>
		  <!-- /.content-wrapper -->
		
		  <!-- Main Footer -->
		  <footer class="main-footer">
		    <!-- To the right -->
		    <div class="pull-right hidden-xs">
		      Anything you want
		    </div>
		    <!-- Default to the left -->
		    <strong>Copyright &copy; 2016 <a href="#">Company</a>.</strong> All rights reserved.
		  </footer>
		
		  <!-- Control Sidebar -->
		  <aside class="control-sidebar control-sidebar-dark">
		    <!-- Create the tabs -->
		    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
		      <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
		      <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
		    </ul>
		    <!-- Tab panes -->
		    <div class="tab-content">
		      <!-- Home tab content -->
		      <div class="tab-pane active" id="control-sidebar-home-tab">
		        <h3 class="control-sidebar-heading">Recent Activity</h3>
		        <ul class="control-sidebar-menu">
		          <li>
		            <a href="javascript:;">
		              <i class="menu-icon fa fa-birthday-cake bg-red"></i>
		              <div class="menu-info">
		                <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>
		                <p>Will be 23 on April 24th</p>
		              </div>
		            </a>
		          </li>
		        </ul>
		        <!-- /.control-sidebar-menu -->
		
		        <h3 class="control-sidebar-heading">Tasks Progress</h3>
		        <ul class="control-sidebar-menu">
		          <li>
		            <a href="javascript:;">
		              <h4 class="control-sidebar-subheading">
		                Custom Template Design
		                <span class="pull-right-container">
		                  <span class="label label-danger pull-right">70%</span>
		                </span>
		              </h4>
		              <div class="progress progress-xxs">
		                <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
		              </div>
		            </a>
		          </li>
		        </ul>
		        <!-- /.control-sidebar-menu -->
		
		      </div>
		      <!-- /.tab-pane -->
		      <!-- Stats tab content -->
		      <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
		      <!-- /.tab-pane -->
		      <!-- Settings tab content -->
		      <div class="tab-pane" id="control-sidebar-settings-tab">
		        <form method="post">
		          <h3 class="control-sidebar-heading">General Settings</h3>
		          <div class="form-group">
		            <label class="control-sidebar-subheading">
		              Report panel usage
		              <input type="checkbox" class="pull-right" checked>
		            </label>
		            <p>
		              Some information about this general settings option
		            </p>
		          </div>
		          <!-- /.form-group -->
		        </form>
		      </div>
		      <!-- /.tab-pane -->
		    </div>
		  </aside>
		  <!-- /.control-sidebar -->
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