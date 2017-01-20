<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>关联采购单列表</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">
var poOrderDataGrid;
$(function() {
	poOrderDataGrid = $('#poOrder_datagrid').datagrid({
		url : '${pageContext.request.contextPath}/calMaterial/getPoOrderInfo?FNumber=${FNumber}&date=${date}',
		fit : true,
		fitColumns : true,
		border : false,
		//pagination : true,
		idField : 'finterId',
		//pagePosition : 'both',
		//checkOnSelect:true,
		//selectOnCheck:true,
		rownumbers:true,
		//singleSelect:true,
		columns : [ [ {
			field : 'finterId',
			title : '编号',
			width : 100,
			checkbox : true
		}, {
			field : 'fbillNo',
			title : '单据编号',
			width : 100
		},{
			field : 'fdate',
			title : '单据日期',
			width : 100
		}, {
			field : 'fitemId',
			title : '物料ID',
			width : 100,
			hidden : true
		}, {
			field : 'fnumber',
			title : '物料编号',
			width : 100
		}, {
			field : 'fname',
			title : '物料名称',
			width : 100
		}, {
			field : 'fqty',
			title : '采购数量',
			width : 100
		}, {
			field : 'fmodel',
			title : '物料规格',
			width : 100
		}, {
			field : 'supplierId',
			title : '供应商ID',
			width : 100,
			hidden : true
		}, {
			field : 'supplierName',
			title : '供应商名称',
			width : 100
		}] ],
		onLoadSuccess:function(data){
			
			var ids = $("input[name='poorderIds']");
			for(var j=0;j<data.total;j++){
				for (var i = 0; i < ids.length; i++) {
					if(data.rows[j].finterId==ids[i].value){
						poOrderDataGrid.datagrid('selectRow',poOrderDataGrid.datagrid('getRowIndex',data.rows[j]));
					}
				}
			}
			
		}
	});
	
});

var poOrder_submitForm = function(dialog, planListDataGrid, p) {
	var nodes = poOrderDataGrid.datagrid('getChecked', [ 'checked', 'indeterminate' ]);
	var ids = [];
	var supplierIds = [];
	var supplierNames = [];
	for (var i = 0; i < nodes.length; i++) {
		ids.push(nodes[i].finterId);
		supplierIds.push(nodes[i].supplierId);
		supplierNames.push(nodes[i].supplierName);
	}
	$.post('${pageContext.request.contextPath}/calMaterial/savePoOrder', {FNumber:'${FNumber}',date:'${date}',finterIds:ids.join(','),supplierIds:supplierIds.join(','),supplierNames:supplierNames.join(',')}, function(j) {
		if (j.success) {
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
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false,title:'采购单列表'" style="overflow: hidden;">
		<table id="poOrder_datagrid"></table>
	</div>
</div>
<c:forEach items="${uList}" var="ur">
	   <input type="hidden" name="poorderIds" value="${ur.poorderId}"/>
	</c:forEach>
</body>
</html>