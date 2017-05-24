<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/public/library.jsp"%>

<link rel="stylesheet" href="${path}plugs/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="${path}plugs/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="${path}plugs/dist/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="${path}plugs/dropzone/dropzone.css">
<link rel="stylesheet" href="${path}css/common.css">

<script src="${path}js/jQuery/jquery-2.2.3.min.js"></script>
<script src="${path}plugs/bootstrap/js/bootstrap.min.js"></script>
<script src="${path}js/popup.js"></script>
<script src="${path}plugs/dropzone/dropzone.js"></script>
<p> 请拖拽文件至该区域 </p>

<form method="post" enctype="multipart/form-data" class="dropzone" id="myForm">
	<button type="submit" id="subBtn">提交</button>
</form>
<script type="text/javascript">
	$(function(){
		
		
		//Dropzone.options.myAwesomeDropzone = false; //阻止 Dropzone 自动寻找这个元素:
		Dropzone.autoDiscover = false;//禁止对所有元素的自动查找:
			
		$(".dropzone").dropzone({
	        url: "${path}upload.json?v="+new Date(), //必须填写
	        method:"post",  //也可用put
	        paramName:"file", //默认为file
	        maxFiles:10,//一次性上传的文件数量上限
	        maxFilesize: 2, //MB
	        acceptedFiles: ".jpg,.gif,.png,.rar,.zip", //上传的类型
	        parallelUploads: 3,
	        dictMaxFilesExceeded: "您最多只能上传{{maxFiles}}个文件！",
	        dictResponseError: '文件上传失败!',
	        dictInvalidFileType: "你不能上传该类型文件,文件类型只能是{{acceptedFiles}}",
	        dictFallbackMessage:"浏览器不受支持",
	        dictFileTooBig:"文件大小不能超过{{maxFilesize}}MB",
	        uploadMultiple:true,//指明是否允许 Dropzone 一次提交多个文件。默认为false。如果设为true，则相当于 HTML 表单添加multiple属性。
	        addRemoveLinks:true,//在每个预览文件下面添加一个remove[删除]
	        autoProcessQueue: false,//当设置 false必须这样 myDropzone.processQueue() 的调用来上传队列中的上传文件.
	        dictRemoveFile:"移除文件",//如果addRemoveLinks为 true，这段文本用来设置删除文件显示文本
	        dictDefaultMessage:"您还未添加任何文件哦.",//没有任何文件被添加的时候的提示文本。 
	        dictCancelUpload: "取消上传",//取消上传链接的文本。
	        dictCancelUploadConfirmation:"确定取消上传?",//取消上传确认信息的文本。
	        init:function(){
	            var dropZone = this;
	        	var submitButton = document.querySelector("#subBtn");
	             //为上传按钮添加点击事件
	            submitButton.addEventListener("click", function () {
	            	var files=dropZone.files;
	            	if(files.length==0){
	        			console.log("请上传文件.");
	        			return false;
	        		}
					dropZone.processQueue();
	            }); 
	            
	            
	            this.on("addedfile", function(file) { 
	            	//上传文件时触发的事件
	            	console.log(file);
	            });
	            this.on("queuecomplete",function(file) {
	                //上传完成后触发的方法
	            	console.log(file);
	            });
	            this.on("removedfile",function(file){
	                //删除文件时触发的方法  TODO 这里删除后，须要同时删除对应服务器上的文件...
	            	console.log(file);
	                var id=file.id;
	                $.post(
	        				'${path}doDelete.json',
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
	            this.on("maxfilesreached",function(file){
	            	//当文件数量达到最大时发生（上传数量等于限制上传数量时，才会触发.）
	            });
	            this.on("maxfilesexceeded",function(){
	            	//当文件数量超过限制时发生
	            	console.log("文件上传数量已超过最大限制数量!");
	            });
	            this.on("success",function(file,imageInfo){
	            	//文件已经成功上传，获得服务器返回信息作为第二个参数(这个时间又被称作finished)
	            	console.log("success");
	            });
	        }});
		
		//begin  回显
		
		var dataMap=${dataMap};
		
		var dropzone = Dropzone.forElement(".dropzone");
    	$.each(dataMap,function(i,item){
    		var serverFile = {name:item.fileName,size:item.fileSize,id:item.id};//accepted:true第三个参数
			dropzone.emit("addedfile", serverFile);
	 		//缩略图设置
	 		dropzone.emit("thumbnail", serverFile, '${path}'+item.url);
	  		//dropzone.emit("complete", serverFile,'${path}'+item.url);//上传完成后..
	  		dropzone.files.push(serverFile);
	  		
	  		/* var mockFile = { name: "123.jpg", accepted:true };
	  		myDropzone.emit("addedfile", mockFile);
	  		myDropzone.emit("thumbnail", mockFile, "http://edms.kitesky.com/upload/image/20170422/52edf3c2aabf171315d968d9af814d0c.jpg");
	  		myDropzone.emit("complete", mockFile); */
    	});
		
		
		
		
		//serverFile :后台返回已上传的文件列表
		
  		
  		/* dropzone.options.addedfile.call(dropzone, serverFile);
  		dropzone.options.thumbnail.call(dropzone, serverFile, "uploads/"+value.name); */
  		
  		
		
		
		//end    回显
		
	});
</script>