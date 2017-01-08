<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/static/resources/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<title>修改用户</title>
<%@ include file="/WEB-INF/static/resources/public.css.jsp"%>
</head>
<body class="skin-blue sidebar-mini fixed skin-blue-light-frame">
	<section class="content-header">
		<h1>修改</h1>
		<ol class="breadcrumb">
			<li><a href="${diaoyuPath}/main/welcome.jsp"><i class="fa fa-home"></i>首页</a></li>
			<li class="active"><a href="#">用户管理</a></li>
			<li class="active"><a href="#">修改</a></li>
		</ol>
	</section>
	<section class="content">
		<div class=" box box-success">
			<div class="box-body">     
				<form role="form" id="updateForm" class="form-horizontal" action="${diaoyuPath}/sys/sysUser/doUpdate.htm" method="post">
				    <input type="hidden" name="userId" value="${sysUserForm.userId}" /> 
					<!-- form start -->
					<div id="baseInfo" class="box-body">
						<table class="form-table form-table-2 form-table-info">
							<tbody>
								<tr class="form-group-sm">
									<th>
			                            <label class="control-label ">用户名：</label>
			                        </th>
			                        <td>
			                        	<input class="form-control" id="userName" name="userName" value="${sysUserForm.userName}" placeholder="用户名" type="text">
			                        </td>
									<th>
			                            <label class="control-label ">性别：</label>
			                        </th>
								</tr>
								<tr class="form-group-sm">
									<th>
			                            <label class="control-label ">年龄：</label>
			                        </th>
			                        <td>
			                        	<input class="form-control" id="age" name="age" value="${sysUserForm.age}" placeholder="年龄" type="text">
			                        </td>
									<th>
			                            <label class="control-label ">联系电话：</label>
			                        </th>
			                        <td>
			                        	<input class="form-control" id="telephone" name="telephone" value="${sysUserForm.telephone}" placeholder="联系电话" type="text">
			                        </td>
								</tr>
								<tr class="form-group-sm">
									<th>
			                            <label class="control-label ">手机号：</label>
			                        </th>
			                        <td>
			                        	<input class="form-control" id="mobilephone" name="mobilephone" value="${sysUserForm.mobilephone}" placeholder="手机号" type="text">
			                        </td>
									<th>
			                            <label class="control-label ">职业：</label>
			                        </th>
			                        <td>
			                        	<input class="form-control" id="userJob" name="job" value="${sysUserForm.userJob}" placeholder="职业" type="text">
			                        </td>
								</tr>
								<tr class="form-group-sm">
									<th>
			                            <label class="control-label ">QQ：</label>
			                        </th>
			                        <td>
			                        	<input class="form-control" id="qq" name="qq" value="${sysUserForm.qq}" placeholder="QQ" type="text">
			                        </td>
									<th>
			                            <label class="control-label ">微信：</label>
			                        </th>
			                        <td>
			                        	<input class="form-control" id="weixin" name="weixin" value="${sysUserForm.weixin}" placeholder="微信" type="text">
			                        </td>
								</tr>
								<tr class="form-group-sm">
									<th>
			                            <label class="control-label ">微博：</label>
			                        </th>
			                        <td>
			                        	<input class="form-control" id="weibo" name="weibo" value="${sysUserForm.weibo}" placeholder="微博" type="text">
			                        </td>
									<th>
			                            <label class="control-label ">邮箱：</label>
			                        </th>
			                        <td>
			                        	<input class="form-control" id="email" name="email" value="${sysUserForm.email}" placeholder="邮箱" type="text">
			                        </td>
								</tr>
								<tr class="form-group-sm"></tr>
							</tbody>
						</table>
					</div>
					<!-- form end -->
					<div class="box-footer text-center">
	                    <button type="button" onclick="save();" class="btn btn-success btn-sm">保存</button>
	                </div>
				</form>
			</div>
		</div>
	</section>
<%@ include file="/WEB-INF/static/resources/public.js.jsp"%>
<script src="${diaoyuPath}/lib/js/validate/jquery.validate.js"></script>
<script src="${diaoyuPath}/lib/js/validate.expand.js"></script>
</body>
</html>