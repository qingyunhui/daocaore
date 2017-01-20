<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>hi</title>

</head>

<body>
	dsdsds
	<span id="msgcount"></span><br>
	<textarea id="msgtext" style="width: 300px;height: 100px;" ></textarea><input type="button" value="发送" onclick="sendMsg()"/>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/js/sockjs-0.3.4.min.js"></script>
	<script type="text/javascript">
var websocket;
if ('WebSocket' in window) {
    websocket = new WebSocket("ws://localhost:8080/tw/webSocketServer");
} else {
    websocket = new SockJS("http://localhost:8080/tw/sockjs/webSocketServer");
}
websocket.onopen = function (evnt) {
	//alert(evnt);
	//console.info(evnt);
};
websocket.onmessage = function (evnt) {
	//alert(evnt);
    $("#msgcount").html($("#msgcount").html()+"<br>(<font color='red'>"+evnt.data+"</font>)")
};
websocket.onerror = function (evnt) {
};
websocket.onclose = function (evnt) {
}

function sendMsg(){
	var msg = $("#msgtext").val();
	$.post('${pageContext.request.contextPath}/user/auditing', {msg:msg}, function(j) {
		/* if (j.success) {
			user_datagrid.datagrid('reload');
			dialog.dialog('destroy');
		}
		p.messager.show({
			title : '提示',
			msg : j.msg,
			timeout : 5000,
			showType : 'slide'
		}); */
	}, 'json');
}
</script>
</body>
</html>
