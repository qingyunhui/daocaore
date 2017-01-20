<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>授权</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">

var roleadd_submitForm = function(dialog, role_treeGrid, p) {
	if ($('role_addForm').form('validate')) {
		$.post('${pageContext.request.contextPath}/roles/add', $("#role_addForm").serialize(), function(j) {
			if (j.success) {
				role_treeGrid.datagrid('reload');
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

function AllChecked(a){
	$("#ch"+a).children().each(function(){
		if(this.checked==true){
			this.checked=false;
		}else{
			this.checked=true;
		}
	});
}
</script>
</head>

<body>
	<form id="role_addForm" method="post">
	<input type="hidden" id="roleId" name="roleId" value="${roles.roleId}"/>
		<table style="width: 100%;">
			<tr>
				<th>角色名称</th>
				<td><input id="roleName" name="roleName" value="${roles.roleName}" class="easyui-validatebox"
					data-options="required:true,missingMessage:'角色名称必填'" /></td>
			</tr>
		</table>
	
	<c:forEach items="${permissions}" var="entry">
	    <fieldset><legend>${entry.permissionModule}<a href="#" class="easyui-linkbutton" onclick="AllChecked('${entry.permissionModule}')">全选</a></legend>
	    <ul>
	      <li id="ch${entry.permissionModule}">
		     <c:forEach items="${entry.permissions}" var="entry2" varStatus="statu">
				<input type="checkbox" name="permissionIds" value="${entry2.permissionId}" title="${entry2.permissionCode}" <c:forEach items="${rps}" var="sp"><c:if test="${sp.id.permissionId==entry2.permissionId}">checked="checked"</c:if></c:forEach>/>${entry2.permissionName}
				<c:if test="${statu.count%4==0}"><br></c:if>
			 </c:forEach>
		     </li>
	    </ul>
	    </fieldset>
	 </c:forEach>
	</form>
</body>
</html>