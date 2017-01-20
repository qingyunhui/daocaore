<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>菜单管理</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/treegrid-dnd.js"></script>
<script type="text/javascript">
var menu_row = -1;
var menu_treeGrid;
	$(function() {
		menu_treeGrid=$('#menu_treeGrid').treegrid({
			url : '${pageContext.request.contextPath}/menu/allTree',
			idField : 'id',
			treeField : 'name',
			parentField : 'pid',
			fit : true,
			fitColumns : false,
			border : false,
			columns : [ [ {
				title : '编号',
				field : 'id',
				width : 150,
				hidden : true
			}, {
				title : '菜单名称',
				field : 'name',
				width : 180,
				editor:{
					type:'validatebox',
					options:{
						required:true
					}
				}
			}, {
				title : 'url',
				field : 'url',
				width : 180
			}, {
				title : '类型',
				field : 'type',
				width : 180,
				formatter: function(value,row,index){
					if(value=='0'){
						return "菜单";
					}else{
						return "功能";
					}
				}
			}, {
				title : '创建日期',
				field : 'createDate',
				width : 180,
				formatter: function(value,row,index){
					return getFormatDateByLong(value);
				}
			}, {
				title : '修改日期',
				field : 'updateDate',
				width : 180,
				formatter: function(value,row,index){
					if(value!=undefined){
						return getFormatDateByLong(value);
					}
				}
			}, {
				field : 'pid',
				title : '父菜单ID',
				width : 150,
				hidden : true
			}, {
				field : 'pname',
				title : '父菜单',
				width : 80
			} ] ],
			toolbar : [ {
				iconCls : 'ext-icon-add',
				text : '添加',
				handler : function() {
					addMenuFun('c');
				}
			} ],
			onContextMenu : function(e, row) {
				e.preventDefault();
				$(this).treegrid('unselectAll');
				$(this).treegrid('select', row.id);
				$('#menu_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}/* ,
			onLoadSuccess: function(row){
				$(this).treegrid('enableDnd', row?row.id:null);
			},
			onDrop: function(targetRow, sourceRow,point){
				if(point=='append'){
					console.info(point);
				}else{
					console.info(targetRow);
					console.info(sourceRow);
					console.info(point);
				}
				
			} */
		});
	});
	
	function addMenuFun(m){
		var pid=null;
		if(menu_treeGrid.treegrid('getSelected')!=null){
			var id = menu_treeGrid.treegrid('getSelected').id;
			if(m=='p'){
				pid=id;
			}else if(m=='c'){
				var node = menu_treeGrid.treegrid('getParent',id);
				if(node!=null){
					pid = menu_treeGrid.treegrid('getParent',id).id;
				}else{
					pid=null;
				}
			}
		}else{
			pid=null;
		}
		//console.info(pid);
		var dialog = parent.modalDialog({
			title : '菜单添加',
			width : 350,
			height : 200,
			url : '${pageContext.request.contextPath}/admin/menu_add.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					//console.info($(dialog.find('iframe').get(0).contentWindow.document).find("#menu_pid").val("sss"));
				    var menu_add = dialog.find('iframe').get(0).contentWindow;
					menu_add.document.getElementById("menu_pid").value=pid;
					//menu_add.find("#menu_pid").val(pid);
					
					//console.info(menu_add.find("#menu_pid").val());
					menu_add.menuadd_submitForm(dialog, menu_treeGrid, parent.west_tree,parent.$);
				}
			} ]
		});
	}
	function deleteMenuFun(){
		var node = menu_treeGrid.treegrid('getSelected');
		if(node){
			parent.$.messager.confirm('确认','您确认想要删除记录吗？',function(r){
				if(r){
					$.post('${pageContext.request.contextPath}/menu/delete.do', {id:node.id}, function(j) {
						if (j.success) {
							menu_treeGrid.treegrid('reload');
							parent.west_tree.tree('reload');
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
	
	function editMenuFun(){
		var node = menu_treeGrid.treegrid('getSelected');
		var dialog = parent.modalDialog({
			title : '菜单修改',
			width : 350,
			height : 200,
			url : '${pageContext.request.contextPath}/menu/editUI?id='+node.id,
			buttons : [ {
				text : '提交',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.menuadd_submitForm(dialog, menu_treeGrid, parent.west_tree,parent.$);
				}
			} ]
		});
	}
</script>
</head>

<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<table id="menu_treeGrid"></table>
	</div>
</div>
<div id="menu_menu" class="easyui-menu" style="width: 120px; display: none;">
	<div onclick="addMenuFun('p');" data-options="iconCls:'icon-mini-add'">增加子节点</div>
	<div onclick="addMenuFun('c');" data-options="iconCls:'icon-mini-add'">增加同级节点</div>
	<div onclick="editMenuFun();" data-options="iconCls:'icon-mini-edit'">编辑</div>
	<div onclick="deleteMenuFun();" data-options="iconCls:'icon-cancel'">删除</div>
</div>
</body>
</html>