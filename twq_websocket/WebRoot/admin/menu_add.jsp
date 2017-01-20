<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>部门管理</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">
var menuadd_submitForm = function(dialog, menu_treeGrid, west_tree,p) {
	if ($('menu_addForm').form('validate')) {
		$.post('${pageContext.request.contextPath}/menu/add', $("#menu_addForm").serialize(), function(j) {
			if (j.success) {
				menu_treeGrid.treegrid('reload');
				west_tree.tree('reload');
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
	<form id="menu_addForm" method="post">
	    <input type="hidden" id="menu_pid" name="pid" value="${menu.tmenu.id}"/>
		<input type="hidden" id="menu_id" name="id" value="${menu.id}"/>
		<table>
			<tr>
				<th>菜单名</th>
				<td><input type="text" name="name" value="${menu.text}" class="easyui-validatebox" data-options="required:true,missingMessage:'菜单名必填'" /></td>
			</tr>
			<tr>
				<th>URL</th>
				<td><input type="text" name="url" value="${menu.url}" size="30" /></td>
			</tr>
			<tr>
				<th>是否功能</th>
				<td>
				<input type="radio" name="type" <c:if test="${menu.type=='1'}">checked="checked"</c:if> value="1"/>是
				<input type="radio" name="type" <c:if test="${menu.type=='0'}">checked="checked"</c:if> value="0"/>否</td>
			</tr>
		</table>
	</form>
</body>
</html>