<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>角色管理</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">
var roleDataGrid;
$(function() {
	roleDataGrid = $('#role_datagrid').datagrid({
		url : '${pageContext.request.contextPath}/roles/list',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'roleId',
		pagePosition : 'both',
		checkOnSelect:true,
		selectOnCheck:true,
		columns : [ [ {
			field : 'roleId',
			title : '编号',
			//width : 100,
			checkbox : true
		}, {
			field : 'roleName',
			title : '角色名'
		}] ],
		toolbar : [ {
			text : '增加',
			iconCls : 'ext-icon-add',
			handler : function() {
				roleAdd();
			}
		}, '-', {
			text : '编辑',
			iconCls : 'ext-icon-pencil',
			handler : function() {
				roleEdit();
			}
		}, '-', {
			text : '删除',
			iconCls : 'ext-icon-pencil_delete',
			handler : function() {
				roleDelete();
			}
		}],
		onRowContextMenu:function(e, rowIndex, rowData){
			e.preventDefault();
			$(this).datagrid('unselectAll');
			$(this).datagrid('selectRow',rowIndex);
			$('#role_menu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}
	});
	
});

function roleAdd() {
	var dialog = parent.modalDialog({
		title : '权限授权',
		width : 500,
		height : 400,
		url : '${pageContext.request.contextPath}/roles/addUI',
		buttons : [ {
			text : '添加',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.roleadd_submitForm(dialog, roleDataGrid, parent.$);
			}
		} ]
	});
}

function roleEdit(){
	var rows = roleDataGrid.datagrid('getChecked');
	if(rows.length==1){
		var dialog = parent.modalDialog({
			title : '用户修改',
			width : 400,
			height : 400,
			url : '${pageContext.request.contextPath}/roles/editUI?roleId='+rows[0].roleId,
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.roleadd_submitForm(dialog, roleDataGrid, parent.$);
				}
			} ]
		});
	}else{
		parent.$.messager.alert('提示','请选择一条记录进行修改');
	}
}
function editroleForm(){
	var rows = roleDataGrid.datagrid('getChecked');
	if(rows.length==1){
		if ($("#admin_editroleForm").form('validate')) {
			$.post('${pageContext.request.contextPath}/roles/edit', $("#admin_editroleForm").serialize(), function(j) {
				if (j.success) {
					roleDataGrid.datagrid('load');
					$('#admin_editrole').dialog('close');
					roleDataGrid.datagrid('uncheckAll');
				}
				$.messager.show({
					title : '提示',
					msg : j.msg,
					timeout : 5000,
					showType : 'slide'
				});
			}, 'json');
		}
	}
}

function roleDelete(){
	var rows = roleDataGrid.datagrid('getChecked');
	if(rows.length==1){
		$.messager.confirm('确认','您确认想要删除记录吗？',function(r){
		    if (r){
				$.post('${pageContext.request.contextPath}/roles/delete', {roleId:rows[0].roleId}, function(j) {
					if (j.success) {
						roleDataGrid.datagrid('load');
					}
					roleDataGrid.datagrid('uncheckAll');
					$.messager.show({
						title : '提示',
						msg : j.msg,
						timeout : 5000,
						showType : 'slide'
					});
				}, 'json');
		    }    
		});
	}else{
		parent.$.messager.alert('提示','请选择一条记录进行删除');
	}
}
</script>
</head>

<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false,title:'用户列表'" style="overflow: hidden;">
		<table id="role_datagrid"></table>
	</div>
</div>

<div id="role_menu" class="easyui-menu" style="width: 120px;display: none;">
<div onclick="roleAdd()" iconCls="icon-add">增加</div>
<div onclick="roleEdit()" iconCls="icon-edit">编辑</div>
<div onclick="roleDelete()" iconCls="icon-remove">删除</div>
</div>
</body>
</html>