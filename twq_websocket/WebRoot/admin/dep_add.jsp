<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>部门管理</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">
var depadd_submitForm = function(dialog, dep_treeGrid, p) {
	if ($('dep_addForm').form('validate')) {
		$.post('${pageContext.request.contextPath}/dep/add.do', $("#dep_addForm").serialize(), function(j) {
			if (j.success) {
				dep_treeGrid.treegrid('reload');
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
	<form id="dep_addForm" method="post">
		<input type="hidden" id="dep_pid" name="parentId" value="${dep.parentId}"/> 
		<input type="hidden" name="departmentId" value="${dep.departmentId}"/>
		<table>
			<tr>
				<th>部门名称</th>
				<td><input name="departmentname" value="${dep.departmentname}" class="easyui-validatebox" data-options="required:true,missingMessage:'部门名称必填'" /></td>
			</tr>
		</table>
	</form>
</body>
</html>