<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>计划跟进</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">
var planListDataGrid;
$(function() {
	planListDataGrid = $('#planList_datagrid').datagrid({
		url : '${pageContext.request.contextPath}/calMaterial/planList',
		fitColumns : false,
		rownumbers:true,//显示一个行号列
		autoRowHeight:false,//定义设置行的高度，根据该行的内容。设置为false可以提高负载性能。
		singleSelect:true,//如果为true，则只允许选择一行。
		border : false,
		idField : 'FItemID',
		frozenColumns:[[{field : 'FNumber',title : '物料编号',width : 150}]],
		columns : [${titlecols}],
		onDblClickCell: function(index,field,value){
			var rows = planListDataGrid.datagrid('getSelected');
			if(field.slice(field.length - 2,field.length)=='-1' && value>0){
				poOrderUI(rows.FNumber,field.slice(field.indexOf('-')+1,field.lastIndexOf('-')));
			}
		}

	});
	
});

function poOrderUI(fnumber,date) {
	var dialog = parent.modalDialog({
		title : '关联采购单列表',
		width : 800,
		height : 500,
		url : '${pageContext.request.contextPath}/calMaterial/getPoOrderInfoUI?FNumber='+fnumber+'&date='+date,
		buttons : [ {
			text : '关联',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.poOrder_submitForm(dialog, planListDataGrid, parent.$);
			}
		} ]
	});
}
</script>
</head>

<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false,title:'计划跟进'" style="overflow: hidden;">
		<table id="planList_datagrid" fit="true"></table>
	</div>
</div>
</body>
</html>