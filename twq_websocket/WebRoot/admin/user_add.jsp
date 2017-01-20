<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>用户管理</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">
	var useradd_submitForm = function(dialog, user_datagrid, p) {
		if ($('user_addForm').form('validate')) {
			$.post('${pageContext.request.contextPath}/user/add', $("#user_addForm").serialize(), function(j) {
				if (j.success) {
					user_datagrid.datagrid('reload');
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
	<form id="user_addForm" method="post">
		<input type="hidden" name="id" value="${u.id}"/>
		<table style="width: 100%;">
			<tr>
				<th>用户名</th>
				<td><input name="name" value="${u.name}" class="easyui-validatebox" data-options="required:true,missingMessage:'用户名必填'" /></td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input type="password" name="pwd" value="${u.pwd}" class="easyui-validatebox" data-options="required:true,missingMessage:'密码必填'" /></td>
			</tr>
			<tr>
				<th>邮箱</th>
				<td><input type="email" name="email" value="${u.email}" class="easyui-validatebox" data-options="required:true,validType:'email',missingMessage:'email必填'" /></td>
			</tr>
		</table>
	</form>
</body>
</html>