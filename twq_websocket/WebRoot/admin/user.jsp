<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>用户管理</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">
var userDataGrid;
$(function() {
	userDataGrid = $('#user_datagrid').datagrid({
		url : '${pageContext.request.contextPath}/user/list',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'id',
		pagePosition : 'both',
		checkOnSelect:true,
		selectOnCheck:true,
		columns : [ [ {
			field : 'id',
			title : '编号',
			width : 100,
			checkbox : true
		}, {
			field : 'name',
			title : '用户名',
			width : 100
		}, {
			field : 'email',
			title : '邮箱',
			width : 100
		}, {
			field : 'emailw',
			title : '外部邮箱',
			width : 100
		}, {
			field : 'cancel',
			title : '是否注销',
			width : 100
		}, {
			field : 'createDate',
			title : '创建日期',
			width : 100,
			formatter: function(value,row,index){
				return getFormatDateByLong(row.createDate);
			}
		}] ],
		toolbar : [ {
			text : '增加',
			iconCls : 'ext-icon-add',
			handler : function() {
				userAdd();
			}
		}, '-', {
			text : '编辑',
			iconCls : 'ext-icon-pencil',
			handler : function() {
				userEdit();
			}
		}, '-', {
			text : '删除',
			iconCls : 'ext-icon-pencil_delete',
			handler : function() {
				userDelete();
			}
		}, '-', {
			text : '角色授权',
			iconCls : 'ext-icon-pencil',
			handler : function() {
				userRole();
			}
		}, '-', {
			text : '菜单授权',
			iconCls : 'ext-icon-pencil',
			handler : function() {
				userMenu();
			}
		}],
		onRowContextMenu:function(e, rowIndex, rowData){
			e.preventDefault();
			$(this).datagrid('unselectAll');
			$(this).datagrid('selectRow',rowIndex);
			$('#user_menu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}
	});
	
});

function userAdd() {
	var dialog = parent.modalDialog({
		title : '用户添加',
		width : 350,
		height : 200,
		url : '${pageContext.request.contextPath}/admin/user_add.jsp',
		buttons : [ {
			text : '添加',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.useradd_submitForm(dialog, userDataGrid, parent.$);
			}
		} ]
	});
}

function userEdit(){
	var rows = userDataGrid.datagrid('getChecked');
	if(rows.length==1){
		var dialog = parent.modalDialog({
			title : '用户修改',
			width : 350,
			height : 300,
			url : '${pageContext.request.contextPath}/user/editUI?id='+rows[0].id,
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.useradd_submitForm(dialog, userDataGrid, parent.$);
				}
			} ]
		});
	}else{
		parent.$.messager.alert('提示','请选择一条记录进行修改');
	}
}
function userDelete(){
	var rows = userDataGrid.datagrid('getChecked');
	if(rows.length==1){
		$.messager.confirm('确认','您确认想要删除记录吗？',function(r){
		    if (r){
				$.post('${pageContext.request.contextPath}/user/delete', {id:rows[0].id}, function(j) {
					if (j.success) {
						userDataGrid.datagrid('reload');
					}
					userDataGrid.datagrid('uncheckAll');
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

function userRole(){
	var rows = userDataGrid.datagrid('getChecked');
	if(rows.length==1){
		var dialog = parent.modalDialog({
			title : '用户角色授权',
			width : 350,
			height : 300,
			url : '${pageContext.request.contextPath}/user/roleUI?id='+rows[0].id,
			buttons : [ {
				text : '授权',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.userrole_submitForm(dialog, userDataGrid, parent.$);
				}
			} ]
		});
	}else{
		parent.$.messager.alert('提示','请选择一条记录进行授权');
	}
}
function userMenu(){
	var rows = userDataGrid.datagrid('getChecked');
	if(rows.length==1){
		var dialog = parent.modalDialog({
			title : '菜单授权',
			width : 350,
			height : 300,
			url : '${pageContext.request.contextPath}/user/menuUI?id='+rows[0].id,
			buttons : [ {
				text : '授权',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.usermenu_submitForm(dialog, userDataGrid, parent.$);
				}
			} ]
		});
	}else{
		parent.$.messager.alert('提示','请选择一条记录进行授权');
	}
}
</script>
</head>

<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false,title:'用户列表'" style="overflow: hidden;">
		<table id="user_datagrid"></table>
	</div>
</div>

<div id="user_menu" class="easyui-menu" style="width: 120px;display: none;">
<div onclick="userAdd()" iconCls="icon-add">增加</div>
<div onclick="userEdit()" iconCls="icon-edit">编辑</div>
<div onclick="userDelete()" iconCls="icon-remove">删除</div>
<div onclick="userRole()" iconCls="icon-add">角色授权</div>
</div>
</body>
</html>