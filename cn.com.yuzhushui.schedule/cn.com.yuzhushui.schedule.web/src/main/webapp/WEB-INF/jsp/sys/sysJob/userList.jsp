<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/static/resources/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<title>用户列表xxxxx</title>
<%@ include file="/WEB-INF/static/resources/public.css.jsp"%>
</head>
<body class="skin-blue sidebar-mini fixed skin-blue-light-frame">
	<section class="content-header">
		<h1>用户列表xxxx</h1>
		<ol class="breadcrumb">
			<li><a href="${diaoyuPath}/main/welcome.htm"><i class="fa fa-home"></i>首页</a></li>
			<li class="active"><a href="#">用户管理</a></li>
			<li class="active"><a href="#">用户列表xxxx</a></li>
		</ol>
	</section>
	<section class="content">
		<div class=" box box-success">
			<div class="box-body">
				<form  id="searchForm" method="post" >
					<div class="input">
						<div class="textalign1">用户名:</div>
	                    <div class="boxinput">
	                        <input type="text" class="textinput"  id="userName" name="userName"  placeholder="用户名" maxlength="170" />
	                    </div>
	                    <div class="textalign1">账号:</div>
	                    <div class="boxinput">
	                        <input type="text" class="textinput"  id="account" name="account"  placeholder="账号" maxlength="170" />
	                    </div>
						<div class="textalign">QQ号:</div>
	                    <div class="boxinput">
	                        <input type="text" class="textinput"  id="qq" name="qq"  placeholder="QQ" maxlength="85" />
	                    </div>
					</div>
				</form>
			</div>
		</div>
		<div class="btn-control-box">
			<button id="search" type="button" class="btn btn-primary btn-sm navbar-right">查询</button>
		</div>
		<table id="gridlist" class="jqgrid"></table>
		<div id="gridpage"></div>
	</section>
</body>
<%@ include file="/WEB-INF/static/resources/public.js.jsp"%>
</html>