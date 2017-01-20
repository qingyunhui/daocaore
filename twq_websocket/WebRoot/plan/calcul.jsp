<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>物料计算</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">
var calculDataGrid,opts01;
$(function() {
	calculDataGrid = $('#calcul_datagrid').datagrid({
		url : '${pageContext.request.contextPath}/calMaterial/calculList',
		//url : '${pageContext.request.contextPath}/calMaterial/calculList?typeFlag=${typeFlag}',
		//fit : false,
		fitColumns : false,
		rownumbers:true,//显示一个行号列
		//pagination:true,//在DataGrid控件底部显示分页工具栏
		autoRowHeight:false,//定义设置行的高度，根据该行的内容。设置为false可以提高负载性能。
		singleSelect:true,//如果为true，则只允许选择一行。
		border : false,
		idField : 'FItemID',
		frozenColumns:[[{field : 'FItemID',title : '序号',width : 50},
		                {field : 'version',title : '版本',width : 50,hidden:true},
			            {field : 'FNumber',title : '物料编号',width : 150}]],
		columns : [${titlecols}],
		toolbar : '#toolbar',
		onAfterEdit:function(rowIndex, rowData, changes){
			//console.info(oldValue);
			//console.info(changes[fieldName]);
			if(changes[fieldName]==undefined || parseFloat(changes[fieldName])==parseFloat(oldValue)){
				return;
			}
			
        	var col = 0;
        	for(var i=0;i<opts01.length;i++){
        		if(fieldName==opts01[i]){
        			col = i;
        			break;
        		}
        	}
        	//console.info(fieldName);
        	var newValue = parseFloat(changes[fieldName])-oldValue;
        	//console.info(opts01)
        	if("D-"==fieldName.substring(0,2) && "-1"==fieldName.substring(12,14)){
        		rowData.PlanNum=parseFloat(rowData.PlanNum)+newValue;
        		rowData.TSqty=parseFloat(rowData.TSqty)+newValue;
        		for(var i = col+1,h=opts01.length;i<h;i++){
        			var f = opts01[i];
        			if("D-"==f.substring(0,2) && "-2"==f.substring(12,14)){
        				//console.info(rowData[f])
        				rowData[f]=parseFloat(rowData[f])+newValue;
        			}else if("D-"==f.substring(0,2) && "-4"==f.substring(12,14)){
        				rowData[f]=parseFloat(rowData[f])+newValue;
        			}else if("E-"==f.substring(0, 2) && "-2"==f.substring(f.length-2, f.length)){
        				rowData[f]=parseFloat(rowData[f])+newValue;
        			}
        		}
        	}
        	calculDataGrid.datagrid('refreshRow',rowIndex);
		},
		onDblClickCell: function(index,field,value){
			var rows = calculDataGrid.datagrid('getSelected');
			if("E-"==field.substring(0, 2) && "-1"==field.substring(field.length-2, field.length) && value>0){
				$.post('${pageContext.request.contextPath}/calMaterial/checkReplaceMt', {FNumber:rows.FNumber,ErpNo:field.slice(field.indexOf('-')+1,field.lastIndexOf('-')),FItemID:rows.FItemID}, function(j) {
					if(j.success){
						replaceMtUI(rows.FNumber,rows.FItemID,field.slice(field.indexOf('-')+1,field.lastIndexOf('-')),field,index,value);
					}else{
						parent.$.messager.show({
							title : '提示',
							msg : j.msg,
							timeout : 5000,
							showType : 'slide'
						});
					}
					
				}, 'json');
				
			}
		},
		onClickCell: onClickCell
	});
	opts01 = calculDataGrid.datagrid('getColumnFields');
});
function replaceMtUI(FNumber,FItemID,ErpNo,field,index,v) {
	var dialog = parent.modalDialog({
		title : '替代物料列表',
		//width : 800,
		//height : 500,
		url : '${pageContext.request.contextPath}/calMaterial/getReplaceMtUI?FNumber='+FNumber+'&ErpNo='+ErpNo+'&FItemID='+FItemID,
		buttons : [ {
			text : '关联',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.poOrder_submitForm(dialog, calculDataGrid, parent.$,field,index,v);
			}
		} ]
	});
}

function saveAll(){
	var mrps = [],dayPlans = [],dayNeeds = [];
	var rows = calculDataGrid.datagrid('getRows');
	for(var i=0;i<rows.length;i++){
		if(rows[i].PlanNum>0){
			var mrp = '\'{';
			mrp +='"version":"'+rows[i].version+'",';
			mrp +='"fnumber":"'+rows[i].FNumber+'",';
			mrp +='"fname":"'+rows[i].FName+'",';
			mrp +='"fmodel":"'+rows[i].FModel+'",';
			mrp +='"fqty":'+rows[i].TXqty+',';
			mrp +='"clqty":'+rows[i].CLQty+',';
			mrp +='"djqty":'+rows[i].DJQty+',';
			mrp +='"tlfqty":'+rows[i].TLFQty+',';
			mrp +='"planNum":'+rows[i].PlanNum+'}\'';
			mrps.push(eval('(' + mrp + ')'));
			
			for(var j=8;j<opts01.length;j++){
				var f = opts01[j];
    			if("D-"==f.substring(0,2) && "-1"==f.substring(12,14)){
    				dayPlans.push(f.substring(2,12)+':'+rows[i][f]+':'+rows[i].FNumber);
    			}else if("D-"==f.substring(0,2) && "-3"==f.substring(12,14)){
    				dayNeeds.push(f.substring(2,12)+':'+rows[i][f]+':'+rows[i].FNumber);
    			}
			}
			
		}
		
	}
	parent.$.messager.progress({
		title : '提示',
		text : '数据保存中，请稍后....'
	});
	$.post('${pageContext.request.contextPath}/umMrpScheduling/save', {
		typeFlag:$("#typeFlag").val(),
		mrps:mrps.join(','),
		dayPlans:dayPlans.join(','),
		dayNeeds:dayNeeds.join(',')}, function(j) {
			parent.$.messager.progress('close');
			parent.$.messager.show({
				title : '提示',
				msg : j.msg,
				timeout : 5000,
				showType : 'slide'
			});
	}, 'json');
}

function searchMrp(){
	var number = $("#fnumber").val();
	var name = $("#fname").val();
	if((number!=null && number.trim()!='') || (name!=null && name.trim()!='')){
		calculDataGrid.datagrid('load',serializeObject($('#searchForm')));
	}else{
		parent.$.messager.show({
			title : '提示',
			msg : '物料编号 和 物料名称 不能同时为空',
			timeout : 5000,
			showType : 'slide'
		});
	}
}
</script>

<script type="text/javascript">

var fieldName = undefined;
var oldValue = 0;

$.extend($.fn.datagrid.methods, {
    editCell: function(jq,param){
        return jq.each(function(){
            //var opts = $(this).datagrid('options');
            //console.info(param);
            var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
            //console.info(fields);
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
    if (calculDataGrid.datagrid('validateRow', editIndex)){
        calculDataGrid.datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}

function onClickCell(index, field,value){
	
	if("D-"==field.substring(0,2) && "-1"==field.substring(12,14)){
		if (endEditing()){
	        calculDataGrid.datagrid('selectRow', index)
	                .datagrid('editCell', {index:index,field:field});
	        editIndex = index;
	        fieldName = field;
	        oldValue = value;
	    }
	}else{
		endEditing();
	}
}
        
</script>
</head>

<body>
<div class="easyui-layout" data-options="fit:true,border:false">
<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td>
					<form id="searchForm">
						<table>
							<tr><td>物料类型</td>
							    <td><select id="typeFlag" class="easyui-combobox" name="typeFlag" data-options="panelHeight:80" style="width:80px;">   
                                       <option value="1">贴片物料</option>   
                                       <option value="2">插件物料</option>   
                                       <option value="3">包装物料</option>   
                                    </select></td>
								<td>物料编号</td>
								<td><input id="fnumber" name="fnumber" style="width: 100px;" /></td>
								<td>物料名称</td>
								<td><input id="fname" name="fname" style="width: 100px;" /></td>
								<td>总剩余数量</td>
								<td><select id="tsFlag" class="easyui-combobox" name="tsFlag" data-options="panelHeight:50" style="width:120px;">   
                                       <option value="N">全部</option>
                                       <option value="Y">总剩余数量为负数</option>
                                    </select></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="searchMrp()">过滤</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');calculDataGrid.datagrid('load',{});">重置过滤</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_save',plain:true" onclick="saveAll()">保存</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',border:false,title:'物料计算'" style="overflow: hidden;">
		<table id="calcul_datagrid" fit="true"></table>
	</div>
</div>
</body>
</html>