<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>订单排程</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">
var planDataGrid;
$(function() {
	planDataGrid = $('#plan_datagrid').datagrid({
		url : '${pageContext.request.contextPath}/scheduling/grid',
		fit : true,
		fitColumns : true,
		border : false,
		//pagination : true,
		idField : 'finterId',
		//pagePosition : 'both',
		//checkOnSelect:true,
		//selectOnCheck:true,
		rownumbers:true,
		singleSelect:true,
		columns : [ [ {
			field : 'finterId',
			title : '编号',
			width : 100,
			hidden : true
		}, {
			field : 'erpNo',
			title : 'ERP编号',
			width : 100
		},{
			field : 'erpNoSplit',
			title : 'ERP拆分编号',
			width : 100
		}, {
			field : 'custName',
			title : '客户',
			width : 100
		}, {
			field : 'orderNum',
			title : '订单数量',
			width : 100
		}, {
			field : 'orderDate',
			title : '订单日期',
			width : 100,
			formatter: formatDatebox
		}, {
			field : 'patchPlanNum',
			title : '贴片计划套数',
			width : 100,
			editor:{
				type:'numberbox',
				options:{required:true}
		    }
		}, {
			field : 'patchPlanDate',
			title : '贴片计划日期',
			width : 100,
			formatter: formatDatebox,
			editor:{
				type:'my97Date',
				options:{
					editable:false
				}
		    }
		}, {
			field : 'pluginPlanNum',
			title : '插件计划套件',
			width : 100,
			editor:{
				type:'numberbox',
				options:{required:true}
		    }
		}, {
			field : 'pluginPlanDate',
			title : '插件计划日期',
			width : 100,
			formatter: formatDatebox,
			editor:{
				type:'my97Date',
				options:{
					editable:false
				}
		    }
		}, {
			field : 'packPlanNum',
			title : '包装计划套数',
			width : 100,
			editor:{
				type:'numberbox',
				options:{required:true}
		    }
		}, {
			field : 'packPlanDate',
			title : '包装计划日期',
			width : 100,
			formatter: formatDatebox,
			editor:{
				type:'my97Date',
				options:{
					editable:false
				}
		    }
		}] ],
		toolbar : '#toolbar',
		onDblClickCell: onClickCell
	});
	
	planDataGrid.datagrid('doCellTip',{
		onlyShowInterrupt:true,   
		position:'bottom',
		tipStyler:{'backgroundColor':'#fff000', width:'200px',borderColor:'#ff0000', boxShadow:'1px 1px 3px #292929'}
	});
	
});

function planUpload() {
	var dialog = parent.modalDialog({
		title : '导入排程',
		width : 350,
		height : 200,
		url : '${pageContext.request.contextPath}/plan/uploadPlan.jsp'
	});
}

function planInit(){
	parent.$.messager.confirm('确认','您确认想要初始化数据吗？',function(r){
		if(r){
			parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
			$.post('${pageContext.request.contextPath}/scheduling/initPlan', {}, function(j) {
				parent.$.messager.show({
					title : '提示',
					msg : j.msg,
					timeout : 5000,
					showType : 'slide'
				});
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
}

function allSave(){
	if (endEditing()) {
		if (planDataGrid.datagrid('getChanges').length) {
			parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
			var up = planDataGrid.datagrid('getChanges','updated');
			var data = new Object();
			data["rowData"] = JSON.stringify(up);
			$.post('${pageContext.request.contextPath}/umMrpSchedulingDtl/edit', data, function(j) {
				if (j.success) {
					planDataGrid.datagrid('acceptChanges');
				}else{
					planDataGrid.datagrid('rejectChanges');
					editIndex = undefined;
				}
				planDataGrid.datagrid('unselectAll');
				parent.$.messager.show({
					title : '提示',
					msg : j.msg,
					timeout : 5000,
					showType : 'slide'
				});
				parent.$.messager.progress('close');
			}, 'json');
		}
	}
}

function allReject(){
	planDataGrid.datagrid('rejectChanges');
	planDataGrid.datagrid('unselectAll');
	editIndex = undefined;
}
</script>

<script type="text/javascript">
$.extend($.fn.datagrid.methods, {
    editCell: function(jq,param){
        return jq.each(function(){
            var opts = $(this).datagrid('options');
            var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
            for(var i=0; i<fields.length; i++){
                var col = $(this).datagrid('getColumnOption', fields[i]);
                col.editor1 = col.editor;
                if (fields[i] != param.field){
                    col.editor = null;
                }
            }
            $(this).datagrid('beginEdit', param.index);
            for(var i=0; i<fields.length; i++){
            	var col = $(this).datagrid('getColumnOption', fields[i]);
                col.editor = col.editor1;
            }
        });
    }
});

var editIndex = undefined;
function endEditing(){
    if (editIndex == undefined){return true}
    if (planDataGrid.datagrid('validateRow', editIndex)){
    	planDataGrid.datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}
//var fieldName = undefined;
//var oldValue = 0;
function onClickCell(index, field,value){
    if (endEditing()){
    	planDataGrid.datagrid('selectRow', index).datagrid('editCell', {index:index,field:field});
        editIndex = index;
        //fieldName = field;
        //oldValue = value;
    }
}
</script>
</head>

<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false,title:'订单排程'" style="overflow: hidden;">
		<table id="plan_datagrid"></table>
	</div>
</div>

	<div id="toolbar" style="display: none;">
		<table>
			<tr><td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-ruby_put',plain:true" onclick="planUpload();">导入</a>
			        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-ruby_gear',plain:true" onclick="planInit();">初始化</a>
			        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-ruby_add',plain:true" onclick="allSave();">保存</a>
			        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-ruby_delete',plain:true" onclick="allReject();">撤销</a>
			        </td>
				<!-- <td>
					<form id="searchForm">
						<table>
							<tr>
								<td>ERP编号</td>
								<td><input name="erpNo"
									style="width: 100px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-zoom',plain:true"
									onclick="planDataGrid.datagrid('load',serializeObject($('#searchForm')));">过滤</a><a
									href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-zoom_out',plain:true"
									onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form>
				</td> -->
			</tr>
		</table>
	</div>
</body>
</html>