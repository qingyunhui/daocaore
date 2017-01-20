<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>用户授权</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">
var roleTree;
$(function(){
	roleTree = $('#tree_role').tree({
		url : '${pageContext.request.contextPath}/roles/getAll',
		parentField : 'pid',
		checkbox : true,
		formatter : function(node) {
			return node.name;
		},
		onLoadSuccess : function(node, data) {
			var ids = $("input[name='roleIds']");
			for (var i = 0; i < ids.length; i++) {
				var node1 = roleTree.tree('find', ids[i].value);
				if (node1) {
					var isLeaf = roleTree.tree('isLeaf', node1.target);
					if (isLeaf) {
						roleTree.tree('check', node1.target);
					}
				}
			}
		}
	});
});

var userrole_submitForm = function(dialog, userDataGrid, p) {
	var nodes = roleTree.tree('getChecked', [ 'checked', 'indeterminate' ]);
	var ids = [];
	for (var i = 0; i < nodes.length; i++) {
		ids.push(nodes[i].id);
	}
	$.post('${pageContext.request.contextPath}/user/role', {id:$('#userId').val(),ids:ids.join(',')}, function(j) {
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
		<ul id="tree_role"></ul>
	</fieldset>
	<c:forEach items="${ur}" var="ur">
	   <input type="hidden" name="roleIds" value="${ur.id.roleId}"/>
	</c:forEach>
</body>
</html>