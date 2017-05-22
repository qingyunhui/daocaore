<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/public/library.jsp"%>

<link rel="stylesheet" href="${path}plugs/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="${path}plugs/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="${path}plugs/dist/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="${path}plugs/dropzone/dropzone.css">

<script src="${path}js/jQuery/jquery-2.2.3.min.js"></script>
<script src="${path}plugs/bootstrap/js/bootstrap.min.js"></script>
<script src="${path}js/popup.js"></script>
<script src="${path}plugs/dropzone/dropzone.js"></script>
<p> 请拖拽文件至该区域demo2 </p>

<h1>单击下载</h1>

<a href="${path}download.json?id=75a93731-80e5-4257-906f-88060431258b">单击下载图片</a>

<br/><br/><br/><br/>

<img src="${path}download.json?id=75a93731-80e5-4257-906f-88060431258b" />

<script type="text/javascript">
	$(function(){
		
	});
</script>