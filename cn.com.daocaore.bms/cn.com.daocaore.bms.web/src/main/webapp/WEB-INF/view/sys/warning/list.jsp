<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/public/library.jsp"%>

<link rel="stylesheet" href="${path}plugs/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="${path}plugs/datatables/dataTables.bootstrap.css">
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
        <table id="tableObjects" class="table table-bordered table-striped">
          <thead>
            <tr>
              <th>ID</th>
              <th>动作</th>
              <th>方法名</th>
              <th>注解</th>
              <th>状态</th>
              <th>操作时间</th>
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

<!--模态框-->
<div class="modal fade" id="dialogModal"  tabindex="-1" role="dialog"  aria-hidden="true">
  <div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
        	<h4 class="modal-title" >更新数据</h4>
     	</div>
     	<div class="modal-body">
			<form class="form-horizontal form" method="POST" role="form" id="dialogForm" >
				<input name="id" type="hidden"/>
                <div class="form-group">
                    <label for="firstname" class="col-sm-2 control-label">方法名</label>
                    <div class="col-sm-10"><input type="text" class="form-control" name="methodName" placeholder="请输入方法名称" maxlength="100" readonly></div>
                </div>
                <div class="form-group">
                    <label for="firstname" class="col-sm-2 control-label">动作</label>
                    <div class="col-sm-10"><input type="text" class="form-control" name="action" placeholder="请输入执行动作" maxlength="100" readonly></div>
                </div>
                <div class="form-group">
                  <label for="firstname" class="col-sm-2 control-label">状态</label>
                  <div class="col-sm-10">
                    <select class="form-control" name="status" id="status" style="width: auto;">
		                <option value="" >全部</option>
		                <c:forEach var="result" items="${tld:getEnumValues('cn.com.daocaore.bms.enums.SysWarningEnum$Status')}" >
		                	<option value="${result.value}" <c:if test="${entity.status == result.value}">selected</c:if>> ${result.name}</option>
		                </c:forEach>
		            </select>
    		      </div>
                </div>
              <div class="form-group">
                    <label for="lastname" class="col-sm-2 control-label">注解</label>
                    <div class="col-sm-10">
                        <textarea class="textarea" name="annotations" maxlength="512" placeholder="请输入注解" style="width: 100%; height: 100px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
                    </div>
                </div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary handleBtn">更新</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</form>
     	</div>
	</div>
  </div>
</div>
<!--模态框 end-->

<script src="${path}js/jQuery/jquery-2.2.3.min.js"></script>
<script src="${path}plugs/bootstrap/js/bootstrap.min.js"></script>
<script src="${path}plugs/datatables/jquery.dataTables.js"></script>
<script src="${path}plugs/datatables/dataTables.bootstrap.min.js"></script>
<script src="${path}js/common.js"></script>
<script src="${path}js/popup.js"></script>
<script>
$(function () {
	var dataTable=$("#tableObjects").dataTable({
		ajax: {
			url:"${path}/sys/warning/doDataTablePage.json",
			type:"post",
			data : function ( d ) {
				return handleFormTOArray(d,".handleForm");
			}
		},
		columns: [
			{data:  'id'},
		    {data:  'action'},
			{ data: 'methodName'},
			{ data: 'annotations',render:function(data, type, row){
				return "<div class='truncates' title='"+data+"'>"+data+"</div>";
			}},
			{ data: 'status',render:function(data, type, row){
				return ${tld:getEnumMapToJSON("cn.com.daocaore.bms.enums.SysWarningEnum$Status")}[data];
			}},
			{
				data:'gmtCreate',render:function(data,type,row){
					return formatDate(data,'yyyy-MM-dd HH:mm:ss');
				}
			},
			{ data: '操作' ,render: function ( data, type, row ) {
					 return function(){
						var html = '<p id="'+ row.id +'" '+
							' methodName="'+ row.methodName +'" '+
							' status="'+ row.status +'" '+
							' action="'+ row.action +'" '+
							' annotations="'+ row.annotations +'" '+
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
	
	//查询
	$("#searchBtn").click(function(){
		dataTable.fnDraw();
	});
	
	//详情
	$("#tableObjects").on('click', '.detail',function() {
		var curObj=this;
		initModelData(true,curObj);
	});
	
	//更新dialog model
	$("#tableObjects").on('click', '.update',function() {
		var curObj=this;
		initModelData(false,curObj);
	});
	
	//提交更新
	$("#dialogForm").submit(function(){
		$.post("${path}sys/warning/doUpdate.json", $("#dialogModal .form").serialize(), function(data, status) {
			if (data.code == "200") {
				callAlert.show(1, "更新配置成功", function(){
					$('#dialogModal').modal('hide');
					dataTable.fnDraw();
				});
			} else {
				callAlert.show(2, data.msg);
			}
		});
	});
	
	// 删除
	$("#tableObjects").on('click', '.delete',function() {
		var id=$(this).parent('p').attr("id");
		callConfirm.show("确定要删除该行?", function(){
			$.post(
				'${path}sys/warning/doDelete.json',
				{"id" : id},
				function(data, status) {
					if (data.code == "200") {
						callAlert.show(1, "删除成功", function(){
							dataTable.fnDraw();
						});
					} else {
						callAlert.show(2, data.msg);
					}
				}
			);
		});
	});
});
function initModelData(isDetail,curObj){
	if(isDetail && isDetail==true){
		$(".modal-title").html("详情");
		$(".handleBtn").hide();
	}else{
		$(".modal-title").html("更新");
		$(".handleBtn").show();
		$(".handleBtn").html("更新");
	}
	$("#dialogModal .form input[name='methodName']").val($(curObj).parent('p').attr("methodName"));
	$("#dialogModal .form select[name='status']").val($(curObj).parent('p').attr("status"));
	$("#dialogModal .form input[name='action']").val($(curObj).parent('p').attr("action"));
	$("#dialogModal .form textarea[name='annotations']").val( $(curObj).parent('p').attr("annotations") );
	$("#dialogModal .form input[name='id']").val($(curObj).parent('p').attr("id"));
	$('#dialogModal').modal('show');
}
</script>