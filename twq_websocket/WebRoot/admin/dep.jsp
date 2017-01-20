<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>部门管理</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">
var dep_treeGrid;
$(function(){
	dep_treeGrid = $('#dep_treeGrid').treegrid({
		title : '',
		url : '${pageContext.request.contextPath}/dep/tree.do',
		idField : 'departmentId',
		treeField : 'departmentname',
		parentField : 'parentId',
		rownumbers : true,
		pagination : false,
		columns : [ [ {
			title : '编号',
			field : 'departmentId',
			width : 150,
			hidden : true
		}, {
			title : 'pid',
			field : 'parentId',
			width : 150,
			hidden : true
		}, {
			title : '部门名称',
			field : 'departmentname',
			width : 180
		}, {
			title : '部门电话',
			field : 'departmenttel',
			width : 180
		}] ],
		toolbar : [ {
			iconCls : 'ext-icon-add',
			text : '添加',
			handler : function() {
				addDepartFun('c');
			}
		} ],
		onContextMenu : function(e, row) {
			e.preventDefault();
			dep_treeGrid.treegrid('unselectAll');
			dep_treeGrid.treegrid('select', row.departmentId);
			$('#dep_menu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}
	});
});

function addDepartFun(m){
	var pid=null;
	if(dep_treeGrid.treegrid('getSelected')!=null){
		var id = dep_treeGrid.treegrid('getSelected').departmentId;
		if(m=='p'){
			pid=id;
		}else if(m=='c'){
			var node = dep_treeGrid.treegrid('getParent',id);
			if(node!=null){
				pid = dep_treeGrid.treegrid('getParent',id).departmentId;
			}else{
				pid=null;
			}
		}
	}else{
		pid=null;
	}
	var dialog = parent.modalDialog({
		title : '菜单添加',
		width : 300,
		height : 200,
		url : '${pageContext.request.contextPath}/admin/dep_add.jsp',
		buttons : [ {
			text : '添加',
			handler : function() {
			    var dep_add = dialog.find('iframe').get(0).contentWindow;
				dep_add.document.getElementById("dep_pid").value=pid;
				dep_add.depadd_submitForm(dialog, dep_treeGrid, parent.$);
			}
		} ]
	});
}

function editDepFun(){
	var node = dep_treeGrid.treegrid('getSelected');
	var dialog = parent.modalDialog({
		title : '菜单添加',
		width : 300,
		height : 200,
		url : '${pageContext.request.contextPath}/dep/editUI.do?id='+node.departmentId,
		buttons : [ {
			text : '添加',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.depadd_submitForm(dialog, dep_treeGrid, parent.$);
			}
		} ]
	});
}

function deleteDepFun(){
	var node = dep_treeGrid.treegrid('getSelected');
	if(node){
		parent.$.messager.confirm('确认','您确认想要删除记录吗？',function(r){
			if(r){
				$.post('${pageContext.request.contextPath}/dep/del.do', {id:node.departmentId}, function(j) {
					if (j.success) {
						dep_treeGrid.treegrid('reload');
					}
					parent.$.messager.show({
						title : '提示',
						msg : j.msg,
						timeout : 5000,
						showType : 'slide'
					});
				}, 'json');
			}
		});
	}
}
</script>
</head>

<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="部门列表" style="overflow: hidden;">
		<table id="dep_treeGrid"></table>
	</div>
</div>
<div id="dep_menu" class="easyui-menu" style="width: 120px; display: none;">
	<div onclick="addDepartFun('p');" data-options="iconCls:'icon-mini-add'">增加子节点</div>
	<div onclick="addDepartFun('c');" data-options="iconCls:'icon-mini-add'">增加同级节点</div>
	<div onclick="editDepFun();" data-options="iconCls:'icon-mini-edit'">编辑</div>
	<div onclick="deleteDepFun();" data-options="iconCls:'icon-cancel'">删除</div>
</div>
</body>
</html>