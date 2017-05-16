<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/public/library.jsp"%>

<link rel="stylesheet" href="${path}plugs/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="${path}plugs/datatables/dataTables.bootstrap.css">
<link rel="stylesheet" href="${path}plugs/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="${path}plugs/dist/css/skins/_all-skins.min.css">

<body class="dt-example">  
  
    <div>  
        <input type="text" id="level1">   
        <input type="button" onclick="search1()"  value="查询">  
    </div>  
  
    <table id="example" class="display" cellspacing="0" width="100%">  
        <thead>  
            <tr>  
                <th>Name</th>  
                <th>Position</th>  
            </tr>  
        </thead>  
    </table>  
  
  
</body>  
</html>  


<script src="${path}js/jQuery/jquery-2.2.3.min.js"></script>
<script src="${path}plugs/bootstrap/js/bootstrap.min.js"></script>
<script src="${path}plugs/datatables/jquery.dataTables.min.js"></script>
<script src="${path}plugs/datatables/dataTables.bootstrap.min.js"></script>
<script src="${path}plugs/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${path}plugs/fastclick/fastclick.js"></script>
<script>
$(function () {
 /*  $("#example1").DataTable(); */
  var table;  
  $(document).ready(function() {  
      table = $('#example').DataTable( {  
           pagingType: "simple_numbers",//设置分页控件的模式  
           searching: false,//屏蔽datatales的查询框  
           aLengthMenu:[10],//设置一页展示10条记录  
           bLengthChange: false,//屏蔽tables的一页展示多少条记录的下拉列表  
           oLanguage: {  //对表格国际化  
              sLengthMenu: "每页显示 _MENU_条",    
              sZeroRecords: "没有找到符合条件的数据",    
          //  "sProcessing": "&lt;img src=’./loading.gif’ /&gt;",    
              sInfo: "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",    
              sInfoEmpty: "木有记录",    
              sInfoFiltered: "(从 _MAX_ 条记录中过滤)",    
              sSearch: "搜索：",    
              oPaginate: {    
              sFirst: "首页",    
              sPrevious: "前一页",    
              sNext: "后一页",    
              sLast: "尾页"    
              }    
           },  
      	  processing: true, //打开数据加载时的等待效果  
          serverSide: true,//打开后台分页  
          ajax: {  
              url: "${path}test/doList.htm",   
              dataSrc: "aaData",   
              data: function ( d ) {  
                  var level1 = $('#level1').val();  
                  //添加额外的参数传给服务器  
                  d.extra_search = level1;  
              }  
          },  
          columns: [  
              { data: "total" },  
              { data: "level" }  
          ]  
      } );  
  } );  
    
    
  function search1()  
  {  
      table.ajax.reload();  
  }  
  
});
</script>