<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>菜单授权</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">
var menuTree;
$(function(){
	menuTree = $('#tree_menu').tree({
		url : '${pageContext.request.contextPath}/menu/allTree',
		parentField : 'pid',
		checkbox : true,
		formatter : function(node) {
			return node.name;
		},
		onLoadSuccess : function(node, data) {
			var ids = $("input[name='menuIds']");
			for (var i = 0; i < ids.length; i++) {
				var node = menuTree.tree('find', ids[i].value);
				if (node) {
					var isLeaf = menuTree.tree('isLeaf', node.target);
					if (isLeaf) {
						menuTree.tree('check', node.target);
					}
				}
			}
		}
	});
});

var usermenu_submitForm = function(dialog, userDataGrid, p) {
	var nodes = menuTree.tree('getChecked', [ 'checked', 'indeterminate' ]);
	var ids = [];
	for (var i = 0; i < nodes.length; i++) {
		ids.push(nodes[i].id);
	}
	$.post('${pageContext.request.contextPath}/user/menu', {id:$('#userId').val(),ids:ids.join(',')}, function(j) {
		if (j.success) {
			userDataGrid.datagrid('reload');
			dialog.dialog('destroy');
		}
		p.messager.show({
			title : '提示',
			msg : j.msg,
			timeout : 5000,
			showType : 'slide'
		});
	}, 'json');
};
</script>
</head>

<body>
<input type="hidden" id="userId" value="${userId}"/>
	<fieldset>
		<legend>用户授权</legend>
		<ul id="tree_menu"></ul>
	</fieldset>
	<c:forEach items="${um}" var="um">
	   <input type="hidden" name="menuIds" value="${um.id.menuId}"/>
	</c:forEach>
</body>
</html>