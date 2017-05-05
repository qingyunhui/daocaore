<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<meta charset="utf-8">
<meta name="viewport"  content="width=device-width, initial-scale=1">
<%-- <link rel="stylesheet" type="text/css" href="${path}css/style.css">
<link rel="stylesheet" type="text/css" href="${path}css/buttons.css"/>
<link rel="stylesheet" type="text/css" href="${path}css/font-awesome.min.css"> --%>
<link href="${path}css/login/frozen.css" rel="stylesheet" type="text/css"/>
<link href="${path}css/login/public.css" rel="stylesheet" type="text/css" />
<link href="${path}css/login/login.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${path}js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${path}js/layer/layer.js"></script>
<script type="text/javascript">
	/**
	 * 这里声明一些全局JS变量， 方便JS中引用
	 */
	var path = "${path}";          //path:项目名称比如:( /movie/ )结构..
	var basePath ="${basePath}";  //basePath:形如:(http://localhost:8080/movie/)结构..
	var messages="${messages}";   //后台返回的消息
	
	var device=browerType();
	
	//移动设备....
	function browerType() {
	   var sUserAgent = navigator.userAgent.toLowerCase(); //浏览器的用户代理设置为小写，再进行匹配
	   var deviceType="";//设备类型
	   if(undefined !=sUserAgent.match(/ipad/i) && null!=sUserAgent.match(/ipad/i)){
		   deviceType=sUserAgent.match(/ipad/i);//或者利用indexOf方法来匹配
	   }else if(undefined !=sUserAgent.match(/iphone os/i) && null!=sUserAgent.match(/iphone os/i)){
		   deviceType=sUserAgent.match(/iphone os/i);
	   }else if(undefined !=sUserAgent.match(/midp/i) && null!=sUserAgent.match(/midp/i)){
		   deviceType=sUserAgent.match(/midp/i);//移动信息设备描述MIDP是一套Java应用编程接口，多适用于塞班系统
	   }else if(undefined !=sUserAgent.match(/rv:1.2.3.4/i) && null!=sUserAgent.match(/rv:1.2.3.4/i)){
		   deviceType=sUserAgent.match(/rv:1.2.3.4/i); //CVS标签
	   }else if(undefined !=sUserAgent.match(/ucweb/i) && null!=sUserAgent.match(/ucweb/i)){
		   deviceType=sUserAgent.match(/ucweb/i);
	   }else if(undefined !=sUserAgent.match(/android/i) && null!=sUserAgent.match(/android/i)){
		   deviceType=sUserAgent.match(/android/i);
	   }else if(undefined !=sUserAgent.match(/windows ce/i) && null!=sUserAgent.match(/windows ce/i)){
		   deviceType=sUserAgent.match(/windows ce/i);
	   }else if(undefined !=sUserAgent.match(/windows mobil/i) && null!=sUserAgent.match(/windows mobil/i)){
		   deviceType=sUserAgent.match(/windows mobil/i);
	   }else {
		   deviceType="PC设备";
	   }
	   return deviceType;
	}
	
</script>