<script>
	var daoCaoRenPath="${daoCaoRenPath}";
</script>
<script src="${daoCaoRenPath}/static/resources/js/jquery-1.10.2.min.js" type="text/javascript"></script>
<%-- <script src="${daoCaoRenPath}/lib/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${daoCaoRenPath}/lib/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${daoCaoRenPath}/lib/js/app.js" type="text/javascript"></script>
<script src="${daoCaoRenPath}/lib/plugins/jqgrid/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="${daoCaoRenPath}/lib/js/DyApp.js"></script>
<script src="${daoCaoRenPath}/lib/js/validate/formValidator.js"></script>
<script src="${daoCaoRenPath}/lib/js/validate/formValidatorRegex.js"></script>
<script src="${daoCaoRenPath}/lib/plugins/My97DatePicker/WdatePicker.js"></script>
<script src="${daoCaoRenPath}/lib/js/DyJqgridUtil.js"></script> --%>
<!-- <script type="text/javascript">
  $(function(){
	  showMessage();
  });
  function showMessage(){
	 var msgObject='${message}';
	 if(msgObject && $.trim(msgObject).length>0){
		 msgObject=JSON.parse(msgObject);
		 if(msgObject && msgObject.code){
			 if(msgObject.code==200){			//200成功标识
				 $.DY.message.alert(msgObject.name, "", "success");
			 }else if(msgObject.code==400){	//400失败标识
				 $.DY.message.alert(msgObject.name, "", "warn");
			 }else if(msgObject.code==500){	//500未知的错误[如类型转换等等...],非数据操作错误!						
				 $.DY.message.alert(msgObject.name, "", "error");
			 }else{
				//其它为错误标识 (TODO 可以自定义扩展!)
				 $.DY.message.alert(msgObject.name, "", "warn");
			 }
		 }else{
			 $.DY.message.alert(msgObject, "", "warn");
		 }
	 }
  }
</script> -->
