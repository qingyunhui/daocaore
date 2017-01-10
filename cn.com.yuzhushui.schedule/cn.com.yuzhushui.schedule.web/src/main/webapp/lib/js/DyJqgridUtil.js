/**
 * 序号列宽度自适应
 * @param gridId jqgridID
 */
function autoRNWidth(gridId){
	var page = $("#"+gridId).jqGrid('getGridParam','page');
    var rows = $('#'+gridId).getGridParam("rowNum");
    var maxnum = (rows*page).toString();
    var len = (maxnum.length)*10;
    $('.jqgfirstrow').find('td').eq(0).css('width',len+'px');
    $('#'+gridId+'_rn').css('width',len+'px');
    /*jqgrid自适应*/
	$(".null-line").css("height",$(".modal-footer").height() + 14);
	var gPrevLen = $(".ui-jqgrid").prevAll().length;
	var jHeight = 0;
	if(gPrevLen > 1){
		for (var i = 0; i < gPrevLen; i++) {
			var j = $(".ui-jqgrid").prevAll().eq(i).height() + 13;
			jHeight += j;
		}
	}else{
		jHeight=$(".ui-jqgrid").prevAll().height()+13;
	};
	var tableHeight = $(window).height() - $(".content-header").height() - jHeight - $(".ui-jqgrid-titlebar").height() - $(".ui-jqgrid-hdiv").height() - $(".null-line").height();
	if($(".ui-jqgrid").parent(".content").length > 0){
    	if($(".ui-jqgrid + div.bottongroup").length > 0){
    		$(".ui-jqgrid-bdiv").css("height",tableHeight - $(".ui-jqgrid").nextAll().height()  - 66.6);
    	}
    	else{
	 		$(".ui-jqgrid-bdiv").css("height",tableHeight - 52.6);
	 		if($(".ui-jqgrid").parent("section").prev("section.content").length > 0){
				$(".ui-jqgrid-bdiv").css("height",tableHeight - ( $(".ui-jqgrid").parent("section").prev("section.content").height() + 10 ) - ($(".content-header").height() * 2 ) - 38.6);
			}
    	};
	 }
	else if($(".ui-jqgrid").parent(".box-body").length > 0){
	 	$(".ui-jqgrid-bdiv").css("height",tableHeight - $(".ui-jqgrid-bdiv").parents(".box").prev().height() - ($(".content-header").height() * 2 ) - 86.6);
	}
	else if($(".ui-jqgrid").parent("section").length > 0){
		$(".ui-jqgrid-bdiv").css("height",tableHeight - ( $(".ui-jqgrid").parent("section").prev("section").height() + 10) - ($(".content-header").height() * 2 ) - 88.6);
		$(".jqgrid").jqGrid("setGridWidth", $(".box-body:first").width() - 2);
	};
}
/**
 * 当数据只有一条时默认选中
 * @param gridId
 */
function selectByOneData(gridId){
	var num = $("#"+gridId).jqGrid('getGridParam', 'records');
    if (num == 1){
    	var ids = jQuery("#"+gridId).jqGrid('getDataIDs');
    	$("#jqg_"+gridId+"_"+ids[0]).attr('checked',true);
    	$("#"+gridId).jqGrid('setSelection',ids[0]);
    }
}


function getGridColumValue(gridId,columName){
	var rowIds = $("#"+gridId).jqGrid('getGridParam', 'selarrrow');
	if (rowIds.length < 1){
		return "";
	}
	var str = "";
	for (var i = 0;i < rowIds.length; i++){
		var rowdata = $("#"+gridId).jqGrid('getRowData', rowIds[i]);
		str += rowdata[columName]+",";
    }
	if (str != ""){
		str = str.substring(0,str.length-1)
	}
	return str;
}

function batchSwitchStatus(gridId, nameColum, statusColum, status, info){
		var rowIds = $("#"+gridId).jqGrid('getGridParam', 'selarrrow');
		if (rowIds.length < 1){
			top.$.DY.message.alert("请选择要"+status+"的" + info, "","warn");
			return;
		}
		var str = "";
		var names = "";
		var infos = new Array();
		var j=0;
		for (var i = 0; i < rowIds.length; i++) {
			
			var rowdata = $("#"+gridId).jqGrid('getRowData', rowIds[i]);
			var temp = rowdata[statusColum].replace(/<.*?>/ig,"");
			if (status != temp){
				if(j<3){
					names += rowdata[nameColum]+"<br/>";
					if(j==2){
						names += "...";
					}
				}
				str += rowIds[i]+",";
				j++;
			}
	    }
		if (names != ""){
			names = names.substring(0,names.length-1);
			str = str.substring(0,str.length-1)
		} else {
			top.$.DY.message.alert("您选择的" + info + "已经是" + status + "状态","", "warn");
	    	return;
		}
		infos[0] = str;
		infos[1] = names;
		return infos;
	}
	

$(function(){
	$.extend($.jgrid.defaults,{
		mtype: "post",
		datatype: "json",
		rownumbers: true,// 显示序号列
		jsonReader: {
			root: "list",
			total: "pages",//总页数
			page: "pageNum",//当前页
			records: "total",//查询出的记录数
			rows: "list"//包含实际数据的数组
		},
		prmNames: {// 向Server传递的参数名称
			page: "pageNum",// 请求页码的参数名称
			rows: "pageSize",// 请求行数的参数名称
			sort: "orderColumns",// 用于排序的列名的参数名称
			order: "orderDirection"//采用的排序方式的参数名称
		},
		rowNum: 10,// 每页显示记录条数
		pgbuttons: true,//是否显示翻页按钮
		rowList: [5, 10, 20],
		viewrecords: true,//定义是否要显示总记录数
		autowidth: true,//自动匹配宽度
		height: 350,
		pager: "#gridpage"// 显示分页
	});
});

//jqgrid 列合并
function Merger(gridName, CellName) {
     //得到显示到界面的id集合
     var mya = $("#" + gridName + "").getDataIDs();
     //当前显示多少条
     var length = mya.length;
     for (var i = 0; i < length; i++) {
         //从上到下获取一条信息
         var before = $("#" + gridName + "").jqGrid('getRowData', mya[i]);
         //定义合并行数
         var rowSpanTaxCount = 1;
         for (j = i + 1; j <= length; j++) {
             //和上边的信息对比 如果值一样就合并行数+1 然后设置rowspan 让当前单元格隐藏
             var end = $("#" + gridName + "").jqGrid('getRowData', mya[j]);
             if (before[CellName] == end[CellName]) {
                 rowSpanTaxCount++;
                 $("#" + gridName + "").setCell(mya[j], CellName, '', { display: 'none' });
             } else {
                 rowSpanTaxCount = 1;
                 break;
             }
             $("#" + CellName + "" + mya[i] + "").attr("rowspan", rowSpanTaxCount);
         }
     }
 }