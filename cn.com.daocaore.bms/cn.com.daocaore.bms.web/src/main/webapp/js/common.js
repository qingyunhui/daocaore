(function(window, document, undefined){
	/**
	 * 重写DataTable，设置其默认属性
	 * */
	var factory = function( $, DataTable ) {
		$.fn.extend( true, DataTable.defaults, {
			deferRender: true,
			processing : true,
			serverSide: true,
			searching: false,
			ordering: false,
			bSort: false,
			language : {
				sProcessing : "处理中...",
				sLengthMenu : "每页 _MENU_ 条记录",
				sZeroRecords : "没有匹配结果",
				sInfo : "第 _PAGE_ 页 ( 总共 _PAGES_ 页 , 共 _TOTAL_ 条记录)",
				sInfoEmpty : "无记录",
				sInfoFiltered : "(由 _MAX_ 项结果过滤)",
				sInfoPostFix : "",
				sSearch : "搜索:",
				sUrl : "",
				sEmptyTable : "表中数据为空",
				sLoadingRecords : "载入中...",
				sInfoThousands : ",",
				oPaginate : {
					sFirst : "首页",
					sPrevious : "上页",
					sNext : "下页",
					sLast : "末页"
				},
				oAria : {
					sSortAscending : ": 以升序排列此列",
					sSortDescending : ": 以降序排列此列"
				}
			}
		});
	};
	// Define as an AMD module if possible
	if ( typeof define === 'function' && define.amd ) {
		define( ['jquery', 'datatables'], factory );
	}
	else if ( typeof exports === 'object' ) {
	    // Node/CommonJS
		factory( require('jquery'), require('datatables'));
	}
	else if ( jQuery ) {
		// Otherwise simply initialise as normal, stopping multiple evaluation
		factory( jQuery, jQuery.fn.dataTable );
	}
})(window, document);

/**
 * <p>将form表单转换成JSON数组</p>
 * @param  handleForm 待处理的表单对象
 * @param  dataTableObj dataTable对象
 * @param  return 处理后的数组对象 
 * */
function handleFormTOArray(dataTableObj,handleForm){
	var formArray = $(handleForm).serializeArray();
	var dataArray = {};
	dataArray.startRow=dataTableObj.start;
	dataArray.pageSize=dataTableObj.length;
	dataArray.isPaging=true;
    $.each(formArray,function(){
      var thisObj=handleForm;
      if(dataArray[this.name]){
        if(!dataArray[this.name].push){
        dataArray[this.name] = [dataArray[this.name]];
       }
       dataArray[this.name].push(this.value || '');
      }else{
        dataArray[this.name] = this.value || '';
      }
    });
    return dataArray;
}

/**
 * @param v 日期对象
 * @param expr 格式化
 * */
function formatDate(v,expr){
    v=new Date(v);
	if(!expr){
        expr = 'yyyy-MM-dd HH:mm:ss'
    }
    if(expr=='yyyy-MM-dd HH:mm:ss'){
        var y = v.getFullYear();
        var m = v.getMonth() + 1;
        var d = v.getDate();
        var h = v.getHours();
        var i = v.getMinutes();
        var s = v.getSeconds();
        var ms = v.getMilliseconds();
        return y + '-' + (m<10?'0'+m:m) + '-' + (d<10?'0'+d:d) + ' ' + (h<10?'0'+h:h) + ':' + (i<10?'0'+i:i) + ':' + (s<10?'0'+s:s);
    } else if(expr == 'yyyy-MM-dd'){
        var y = v.getFullYear();
        var m = v.getMonth() + 1;
        var d = v.getDate();
        return y + '-' + (m<10?'0'+m:m) + '-' + (d<10?'0'+d:d);
    } else if(expr == 'HH:mm:ss'){
        var h = v.getHours();
        var i = v.getMinutes();
        var s = v.getSeconds();
        return (h<10?'0'+h:h) + ':' + (i<10?'0'+i:i) + ':' + (s<10?'0'+s:s);
    } else if(expr == 'yy-MM-dd'){
        var y = v.getYear();
        var m = v.getMonth() + 1;
        var d = v.getDate();
        return y + '-' + (m<10?'0'+m:m) + '-' + (d<10?'0'+d:d);
    } else if(expr == 'yy-MM-dd HH:mm:ss'){
        var y = v.getYear();
        var m = v.getMonth() + 1;
        var d = v.getDate();
        var h = v.getHours();
        var i = v.getMinutes();
        var s = v.getSeconds();
        return y + '-' + (m<10?'0'+m:m) + '-' + (d<10?'0'+d:d) + ' ' + (h<10?'0'+h:h) + ':' + (i<10?'0'+i:i) + ':' + (s<10?'0'+s:s);
    } else if(expr == 'yyyy-MM-dd HH:mm:ss:ms'){
        var y = v.getFullYear();
        var m = v.getMonth() + 1;
        var d = v.getDate();
        var h = v.getHours();
        var i = v.getMinutes();
        var s = v.getSeconds();
        var ms = v.getMilliseconds();
        return y + '-' + (m<10?'0'+m:m) + '-' + (d<10?'0'+d:d) + ' ' + (h<10?'0'+h:h) + ':' + (i<10?'0'+i:i) + ':' + (s<10?'0'+s:s) + ':' + (ms<10?'0'+ms:ms);
    }
	return '';
}





 
