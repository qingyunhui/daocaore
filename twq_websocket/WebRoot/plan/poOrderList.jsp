<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>采购单列表</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">
var poOrderListDataGrid;
$(function() {
	poOrderListDataGrid = $('#poOrderList_datagrid').datagrid({
		url : '${pageContext.request.contextPath}/poOrder/getPoOrder?FNumber=${FNumber}&id=${id}',
		fit : true,
		fitColumns : true,
		border : false,
		idField : 'finterId',
		rownumbers:true,
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
			field : 'fname',
			title : '供应商',
			width : 100
		}, {
			field : 'fqty',
			title : '采购数量',
			width : 100
		}, {
			field : 'fclosed',
			title : '采购单状态',
			width : 100
		}] ]
	});
	
});

</script>
</head>

<body>
fdsfpdsfsdfsdfsd
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false,title:'采购单列表'" style="overflow: hidden;">
		<table id="poOrderList_datagrid"></table>
	</div>
</div>
</body>
</html>