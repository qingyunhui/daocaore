<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>权限添加</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">
	var permissionadd_submitForm = function(dialog, permission_datagrid, p) {
		if ($('permission_addForm').form('validate')) {
			$.post('${pageContext.request.contextPath}/permission/add', $("#permission_addForm").serialize(), function(j) {
				if (j.success) {
					permission_datagrid.datagrid('reload');
					dialog.dialog('destroy');
				}
				p.messager.show({
					title : '提示',
					msg : j.msg,
					timeout : 5000,
					showType : 'slide'
				});
			}, 'json');
		}
	};
</script>
</head>

<body>
	<form id="permission_addForm" method="post">
		<input type="hidden" name="permissionId" value="${p.permissionId}"/>
		<table style="width: 100%;">
			<tr>
				<th>权限值</th>
				<td><input name="permissionCode" value="${p.permissionCode}" maxlength="20" class="easyui-validatebox" data-options="required:true,missingMessage:'权限值必填'" /></td>
			</tr>
			<tr>
				<th>权限名称</th>
				<td><input name="permissionName" value="${p.permissionName}" class="easyui-validatebox" data-options="required:true,missingMessage:'权限名称必填'" /></td>
			</tr>
			<tr>
				<th>所属模块</th>
				<td><input name="permissionModule" value="${p.permissionModule}" maxlength="20" class="easyui-validatebox" data-options="required:true,missingMessage:'所属模块必填'" /></td>
			</tr>
		</table>
	</form>
</body>
</html>