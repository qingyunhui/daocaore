<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>替代物料列表</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">
var poOrderDataGrid;
$(function() {
	poOrderDataGrid = $('#poOrder_datagrid').datagrid({
		url : '${pageContext.request.contextPath}/calMaterial/getReplaceMt?FNumber=${FNumber}&ErpNo=${ErpNo}&FItemID=${FItemID}',
		fit : true,
		fitColumns : true,
		border : false,
		idField : 'fitemIdr',
		rownumbers:true,
		columns : [ [ {
			field : 'fitemIdr',
			title : '编号',
			width : 100,
			hidden : true
		}, {
			field : 'fnumberr',
			title : '物料编号',
			width : 100
		},{
			field : 'fnamer',
			title : '物料名称',
			width : 100
		}, {
			field : 'fmodelr',
			title : '物料规格',
			width : 100
		}, {
			field : 'clqty',
			title : '材料仓库存',
			width : 100
		}, {
			field : 'djqty',
			title : '待检仓库存',
			width : 100
		}, {
			field : 'tlfqty',
			title : '在制订单库存',
			width : 100
		}, {
			field : 'neednum',
			title : '需求量',
			width : 100,
			editor:{
				type:'numberbox'
		    }
		}] ],
		onClickCell: onClickCell
	});
	
});
/**
 * 转换为json对象
 */
function strToObj(json){ 
    return eval("("+json+")"); 
}
var poOrder_submitForm = function(dialog, calculDataGrid, p,field,index,v) {
	var cfs = calculDataGrid.datagrid('getColumnFields');
	var col = 0;
	for(var i=0;i<cfs.length;i++){
		if(field==cfs[i]){
			col = i;
			break;
		}
	}
	
	var nodes = poOrderDataGrid.datagrid('getRows');
	var tnum = 0,va=0;
	//var arrayObj = new Array();
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].neednum!=null && nodes[i].neednum!=''){
			//arrayObj.push(nodes[i].fitemIdr);
			
			var ts = nodes[i].clqty+nodes[i].djqty-nodes[i].neednum;
			var tol = nodes[i].clqty+nodes[i].djqty;
			var str = "";
			str += "{";
			str += "FItemID: "+nodes[i].fitemIdr+",";
			str += "version: ,";
			str += "FNumber: '"+nodes[i].fnumberr+"',";
			str += "FName: '"+nodes[i].fnamer+"',";
			str += "FModel: '"+nodes[i].fmodelr+"',";
			str += "CLQty: "+nodes[i].clqty+",";
			str += "DJQty: "+nodes[i].djqty+",";
			str += "TLFQty: "+nodes[i].tlfqty+",";
			str += "PlanNum: "+0+",";
			str += "TXqty: "+nodes[i].neednum+",";
			str += "TSqty: "+ts+",";
			
			for(var j = col-1; j>8; j--){
				if("D-"==cfs[j].substring(0,2) && "-1"==cfs[j].substring(12,14)){
					str += "'"+cfs[j]+"':"+0+",";
				}else if("D-"==cfs[j].substring(0,2) && "-2"==cfs[j].substring(12,14)){
					str += "'"+cfs[j]+"':"+tol+",";
				}else if("D-"==cfs[j].substring(0,2) && "-3"==cfs[j].substring(12,14)){
					str += "'"+cfs[j]+"':"+0+",";
				}else if("D-"==cfs[j].substring(0,2) && "-4"==cfs[j].substring(12,14)){
					str += "'"+cfs[j]+"':"+tol+",";
				}else if("E-"==cfs[j].substring(0, 2) && "-1"==cfs[j].substring(cfs[j].length-2, cfs[j].length)){
					str += "'"+cfs[j]+"':"+0+",";
				}else if("E-"==cfs[j].substring(0, 2) && "-2"==cfs[j].substring(cfs[j].length-2, cfs[j].length)){
					str += "'"+cfs[j]+"':"+tol+",";
				}
			}
			
			for(var j=col;j<cfs.length;j++){
				if("D-"==cfs[j].substring(0,2) && "-1"==cfs[j].substring(12,14)){
					str += "'"+cfs[j]+"':"+0+",";
				}else if("D-"==cfs[j].substring(0,2) && "-2"==cfs[j].substring(12,14)){
					str += "'"+cfs[j]+"':"+ts+",";
				}else if("D-"==cfs[j].substring(0,2) && "-3"==cfs[j].substring(12,14)){
					str += "'"+cfs[j]+"':"+0+",";
				}else if("D-"==cfs[j].substring(0,2) && "-4"==cfs[j].substring(12,14)){
					str += "'"+cfs[j]+"':"+ts+",";
				}else if("E-"==cfs[j].substring(0, 2) && "-1"==cfs[j].substring(cfs[j].length-2, cfs[j].length)){
					if(cfs[j]==field){
						str += "'"+cfs[j]+"':"+nodes[i].neednum+",";
					}else{
						str += "'"+cfs[j]+"':"+0+",";
					}
				}else if("E-"==cfs[j].substring(0, 2) && "-2"==cfs[j].substring(cfs[j].length-2, cfs[j].length)){
					str += "'"+cfs[j]+"':"+ts+",";
				}
			}
			str = str.substring(0, str.length-1);
			
			str += "}";
			
			calculDataGrid.datagrid('appendRow',strToObj(str));
			
			tnum += parseInt(nodes[i].neednum);
			}
		}
	
	if(v>tnum){
		va = v-tnum;
	}
	/* var qq = "{'"+field+"':"+va+"}";
	calculDataGrid.datagrid('updateRow',{
		index: index,
		row: strToObj(qq)
	}); */
	
	
	var rowData = calculDataGrid.datagrid('getSelected');
	
	var newValue=-tnum;
	rowData[field]=va;
	rowData.TXqty=parseFloat(rowData.TXqty)+newValue;
	rowData.TSqty=parseFloat(rowData.TSqty)-newValue;
	for(var i = col-1; i>8; i--){
		var f = cfs[i];
		var f1 = cfs[i+1];
		if("D-"==f.substring(0,2) && "-3"==f.substring(12,14)){
			rowData[f]=parseFloat(rowData[f])+newValue;
			rowData[f1]=parseFloat(rowData[f1])-newValue;
			break;
		}
	}
	
	for(var i = col+1,h=cfs.length;i<h;i++){
		var f = cfs[i];
		if("D-"==f.substring(0,2) && "-2"==f.substring(12,14)){
			rowData[f]=parseFloat(rowData[f])-newValue;
		}else if("D-"==f.substring(0,2) && "-4"==f.substring(12,14)){
			rowData[f]=parseFloat(rowData[f])-newValue;
		}else if("E-"==f.substring(0, 2) && "-2"==f.substring(f.length-2, f.length)){
			rowData[f]=parseFloat(rowData[f])-newValue;
		}
	}
	//console.info(rowData);
	
	calculDataGrid.datagrid('updateRow',{
		index: index,
		row: rowData
	});
	
	/* calculDataGrid.datagrid({
		rowStyler: function(index,row){
			for(var i=0;i<arrayObj.length;i++){
				if (arrayObj[i]==row.FItemID){
					return 'background-color:#6293BB;';
				}
			}
			
		}
	}); */
	dialog.dialog('destroy');
};
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
    if (poOrderDataGrid.datagrid('validateRow', editIndex)){
    	poOrderDataGrid.datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}
function onClickCell(index, field,value){
    if (endEditing()){
    	poOrderDataGrid.datagrid('selectRow', index).datagrid('editCell', {index:index,field:field});
        editIndex = index;
    }
}
</script>
</head>

<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false,title:'替代物料列表'" style="overflow: hidden;">
		<table id="poOrder_datagrid"></table>
	</div>
</div>
<c:forEach items="${uList}" var="ur">
	   <input type="hidden" name="poorderIds" value="${ur.poorderId}"/>
	</c:forEach>
</body>
</html>