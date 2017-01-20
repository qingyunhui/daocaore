<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>计划物料关联采购单管理</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
<script type="text/javascript">
var poConnDataGrid;
$(function() {
	poConnDataGrid = $('#poConn_datagrid').datagrid({
		url : '${pageContext.request.contextPath}/poOrder/list',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'id',
		pagePosition : 'both',
		singleSelect:true,
		view: detailview,
		columns : [ [ {
			field : 'id',
			title : '编号',
			width : 100,
			hidden : true
		}, {
			field : 'dateStr',
			title : '计划到料日期',
			width : 50
		}, {
			field : 'fnumber',
			title : '物料编号',
			width : 70
		}, {
			field : 'username',
			title : '用户',
			width : 40
		}] ],
		detailFormatter:function(index,row){
            return '<div style="padding:2px"><table id="ddv-' + index + '"></table><table id="sddv-' + index + '"></table></div>';
        },
        onExpandRow:function(index,row){
            $('#ddv-'+index).datagrid({  
                url:'${pageContext.request.contextPath}/poOrder/getPoOrder?FNumber='+row.fnumber+'&id='+row.id,  
                fitColumns:true,  
                singleSelect:true,  
                height:'auto',
                showFooter: true,
                columns:[[  
                    {field:'fbillNo',title:'采购单编号',width:50},  
                    {field:'fdate',title:'采购日期',width:50,
            			formatter: function(value,row,index){
            				return getSmpFormatDateByLong(value,false);
            			}},
                    {field:'fname',title:'供应商',width:50},  
                    {field:'fqty',title:'采购数量',width:100},  
                    {field:'fclosed',title:'采购单状态',width:50}
                ]],  
                onResize:function(){  
                    $('#poConn_datagrid').datagrid('fixDetailRowHeight',index);  
                },
                onLoadSuccess:function(){  
                    setTimeout(function(){  
                        $('#poConn_datagrid').datagrid('fixDetailRowHeight',index);  
                    },0);  
                }  
            });
            
            $('#sddv-'+index).datagrid({  
                url:'${pageContext.request.contextPath}/poOrder/getStockBill?fnumber='+row.fnumber+'&dateStr='+row.dateStr,  
                fitColumns:true,  
                singleSelect:true,  
                height:'auto',
                showFooter: true,
                columns:[[  
                    {field:'fitemId',title:'ID',hidden:true},  
                    {field:'fnumber',title:'物料编号',width:50},
                    {field:'fname',title:'名称',width:50},  
                    {field:'fdate',title:'来料日期',width:50,
            			formatter: function(value,row,index){
            				if(value!=undefined){
            					return getSmpFormatDateByLong(value,false);
            				}
            			}},
            		{field:'fmodel',title:'规格',width:100},
                    {field:'fqty',title:'数量',width:50}
                ]],  
                onResize:function(){  
                    $('#poConn_datagrid').datagrid('fixDetailRowHeight',index);  
                },
                onLoadSuccess:function(){  
                    setTimeout(function(){  
                        $('#poConn_datagrid').datagrid('fixDetailRowHeight',index);  
                    },0);  
                }  
            });
            
            $('#poConn_datagrid').datagrid('fixDetailRowHeight',index);  
        }
	});
});

</script>
</head>

<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false,title:'计划物料关联采购单列表'" style="overflow: hidden;">
		<table id="poConn_datagrid"></table>
	</div>
</div>

</body>
</html>