<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>用户管理</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">
var permissionDataGrid;
$(function() {
	permissionDataGrid = $('#permission_datagrid').datagrid({
		url : '${pageContext.request.contextPath}/permission/list',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'permissionId',
		pagePosition : 'both',
		checkOnSelect:true,
		selectOnCheck:true,
		columns : [ [ {
			field : 'permissionId',
			title : '编号',
			width : 100,
			checkbox : true
		}, {
			field : 'permissionCode',
			title : '权限值',
			width : 100
		}, {
			field : 'permissionName',
			title : '权限名称',
			width : 100
		}, {
			field : 'permissionModule',
			title : '所属模块',
			width : 100
		}] ],
		toolbar : [ {
			text : '增加',
			iconCls : 'ext-icon-add',
			handler : function() {
				permissionAdd();
			}
		}, '-', {
			text : '编辑',
			iconCls : 'ext-icon-pencil',
			handler : function() {
				permissionEdit();
			}
		}, '-', {
			text : '删除',
			iconCls : 'ext-icon-pencil_delete',
			handler : function() {
				permissionDelete();
			}
		}],
		onRowContextMenu:function(e, rowIndex, rowData){
			e.preventDefault();
			$(this).datagrid('unselectAll');
			$(this).datagrid('selectRow',rowIndex);
			$('#permission_menu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}
	});
	
});

function permissionAdd() {
	var dialog = parent.modalDialog({
		title : '权限添加',
		width : 400,
		height : 200,
		url : '${pageContext.request.contextPath}/admin/permission_add.jsp',
		buttons : [ {
			text : '添加',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.permissionadd_submitForm(dialog, permissionDataGrid, parent.$);
			}
		} ]
	});
}

function permissionEdit(){
	var rows = permissionDataGrid.datagrid('getChecked');
	if(rows.length==1){
		var dialog = parent.modalDialog({
			title : '权限修改',
			width : 350,
			height : 300,
			url : '${pageContext.request.contextPath}/permission/editUI?permissionId='+rows[0].permissionId,
			buttons : [ {
				text : '修改',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.permissionadd_submitForm(dialog, permissionDataGrid, parent.$);
				}
			} ]
		});
	}else{
		parent.$.messager.alert('提示','请选择一条记录进行修改');
	}
}

function permissionDelete(){
	var rows = permissionDataGrid.datagrid('getChecked');
	if(rows.length==1){
		$.messager.confirm('确认','您确认想要删除记录吗？',function(r){
		    if (r){
				$.post('${pageContext.request.contextPath}/permission/delete', {permissionId:rows[0].permissionId}, function(j) {
					if (j.success) {
						permissionDataGrid.datagrid('load');
					}
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
		<table id="permission_datagrid"></table>
	</div>
</div>

<div id="permission_menu" class="easyui-menu" style="width: 120px;display: none;">
<div onclick="permissionAdd()" iconCls="icon-add">增加</div>
<div onclick="permissionEdit()" iconCls="icon-edit">编辑</div>
<div onclick="permissionDelete()" iconCls="icon-remove">删除</div>
</div>
</body>
</html>