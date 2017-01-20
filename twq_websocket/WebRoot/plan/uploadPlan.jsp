<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>计划导入</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">
var planUpload_submitForm = function(dialog, planDataGrid, p) {
	if ($('upload_addForm').form('validate')) {
		$.post('${pageContext.request.contextPath}/scheduling/uploadPlan', $("#upload_addForm").serialize(), function(j) {
			if (j.success) {
				planDataGrid.datagrid('reload');
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
<form id="upload_addForm" action="${pageContext.request.contextPath}/scheduling/uploadPlan" method="post" enctype="multipart/form-data">
	<table style="width: 100%;">
			<tr>
				<!-- <td><input class="easyui-filebox" name="file" style="width:250px" data-options="buttonText: '选择文件'"></td> -->
				<td><input type="file" name="file"/></td>
			</tr>
			<tr><td><input type="submit" value="上传"/></td></tr>
	</table>
</form>
</body>
</html>