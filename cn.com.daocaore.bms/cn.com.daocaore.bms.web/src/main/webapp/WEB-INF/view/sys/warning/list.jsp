<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/public/library.jsp"%>

<link rel="stylesheet" href="${path}plugs/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<%-- <link rel="stylesheet" href="${path}plugs/datatables/dataTables.bootstrap.css"> --%>
<link rel="stylesheet" href="${path}plugs/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="${path}plugs/dist/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="${path}css/common.css">
<!--搜索条件-->
<div class="row formSearch">
  <form id="handleForm" class="handleForm" method="POST">
    <div class="col-xs-2">
        <div class="input-group">
            <span class="input-group-addon">状态</span>
            <select class="form-control" name="status" id="status" style="width: auto;">
                <option value="" >全部</option>
                <c:forEach var="result" items="${tld:getEnumValues('cn.com.daocaore.bms.enums.SysWarningEnum$Status')}" >
                	<option value="${result.value}" <c:if test="${entity.status == result.value}">selected</c:if>> ${result.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="col-xs-4">
        <div class="input-group">
            <span class="input-group-addon">说明</span>
            <input type="text" class="form-control" name="annotations" value="${entity.annotations}" autocomplete="on" >
        </div>
    </div>
    <div class="col-xs-2">
        <button class="btn btn-danger" id="searchBtn">搜索</button>
    </div>
  </form>
</div>
<!--搜索条件 end-->

<div class="row">
  <div class="col-xs-12">
    <!-- /.box -->
    <div class="box">
      <div class="box-body">
        <table id="tableObject" class="table table-bordered table-striped">
          <thead>
            <tr>
              <th>动作</th>
              <th>方法名</th>
              <th>注解</th>
              <th>状态</th>
              <th>操作</th>
            </tr>
          </thead>
        </table>
      </div>
      <!-- /.box-body -->
    </div>
    <!-- /.box -->
  </div>
  <!-- /.col -->
</div>


<script src="${path}js/jQuery/jquery-2.2.3.min.js"></script>
<script src="${path}plugs/bootstrap/js/bootstrap.min.js"></script>
<script src="${path}plugs/datatables/jquery.dataTables.js"></script>
<script src="${path}plugs/datatables/dataTables.bootstrap.min.js"></script>
<script src="${path}js/common.js"></script>
<script src="${path}plugs/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${path}plugs/fastclick/fastclick.js"></script>
<script src="${path}plugs/dist/js/app.min.js"></script>
<script src="${path}plugs/dist/js/demo.js"></script>
<script>
$(function () {
	$("#tableObject").dataTable({
		ajax: {
			url:"${path}/sys/warning/doList.htm",
			type:"post",
			data : function ( d ) {
				return handleFormTOArray(d,".handleForm");
			}
		},
		columns: [
		    {data:  'action'},
			{ data: 'methodName'},
			{ data: 'annotations'},
			{ data: 'status',render:function(data, type, row){
				return data;
				/* return ${enumTool.getEnumToJSON("com.zzjr.skyeye.enums.SkyeyeObjectsEnum$ACTION_TYPE")}[data]; */
			}},
			{ data: '操作' ,render: function ( data, type, row ) {
					 return function(){
						var html = '<p id="'+ row.id +'" '+
							' methodName="'+ row.methodName +'" '+
							' status="'+ row.status +'" '+
							' action="'+ row.action +'" '+
							'>'+
							'<button class="btn btn-warning update" type="button">编辑</button>  '+
							'<button class="btn btn-danger delete" type="button">删除</button>  '+
							'<button class="btn btn-info detail" type="button">详情</button>  '+
							'</p>';
						return html;
					}; 
				}
			}
		]
	});
});
</script>