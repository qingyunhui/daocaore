<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<aside class="main-sidebar">
<!-- sidebar: style can be found in sidebar.less -->
<section class="sidebar">
  <!-- Sidebar user panel (optional) -->
  <div class="user-panel">
    <div class="pull-left image">
      <img src="${path}plugs/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
    </div>
    <div class="pull-left info">
      <p>Alexander Pierce</p>
      <!-- Status -->
      <a href="javascript:void(0);"><i class="fa fa-circle text-success"></i> Online</a>
    </div>
  </div>

  <!-- search form (Optional) -->
  <form action="javascript:void(0);" method="get" class="sidebar-form">
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
      <a href="javascript:void(0);" class="active">
        <i class="fa fa-link"></i> 
        <span>Link</span>
      </a>
    </li>
    <li class="treeview">
      <a href="javascript:void(0);">
        <i class="fa fa-table"></i> <span>Tables</span>
        <span class="pull-right-container">
          <i class="fa fa-angle-left pull-right"></i>
        </span>
      </a>
      <ul class="treeview-menu">
        <li><a href="javascript:void(0);" onclick="javascript:jump('${path}sys/simpleTable.htm','Tables','simpleTable');"><i class="fa fa-circle-o"></i> Simple tables</a></li>
        <li><a href="javascript:void(0);" onclick="javascript:jump('${path}sys/dataTable.htm','Tables','dataTable');"><i class="fa fa-circle-o"></i> Data tables</a></li>
        <li><a href="javascript:void(0);" onclick="javascript:jump('${path}sys/warning/list.htm','预警监控','监控列表');"><i class="fa fa-circle-o"></i> 预警监控列表</a></li>
      </ul>
    </li>
    <li class="treeview">
      <a href="javascript:void(0);">
      	<i class="fa fa-link"></i> 
      	<span>Charts</span>
        <span class="pull-right-container">
          <i class="fa fa-angle-left pull-right"></i>
        </span>
      </a>
      <ul class="treeview-menu">
        <li><a href="javascript:void(0);" onclick="javascript:jump('${path}sys/chart.htm','Charts','ChartsJS');">ChartsJS</a></li>
        <li><a href="javascript:void(0);" onclick="javascript:jump('${path}sys/morris.htm','Charts','morris');">morris</a></li>
        <li><a href="javascript:void(0);" onclick="javascript:jump('${path}sys/float.htm','Charts','float');">float</a></li>
      </ul>
    </li>
  </ul>
  <!-- /.sidebar-menu -->
</section>
</aside>
