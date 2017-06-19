(function(window, document, undefined){
	var factory = function( $, DataTable ) {
		$.fn.extend( true, DataTable.defaults, {
			deferRender: true,
			processing : true,
			serverSide: true,
			searching: false,
			ordering: false,
			bSort: true,
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




 
