<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>聊天室 websocket</title>
</head>
<body>
    <p style="width: 260px; margin: auto;font-size: x-large;">Welcome to chat&&websocket</p>
    <input id="text" type="text"/>
    <button id="sendBtn">发送消息</button>
    <hr/>
    <button id="closeBtn">关闭连接</button>
    <button id="openBtn">打开连接</button>
    <hr/>
    <div id="message"></div>
</body>
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/sockjs-0.3.min.js"></script>
<script type="text/javascript">
$(function(){
    var websocket;
   
    init();
    
    function init(){
	   	 if ('WebSocket' in window) {
	   	        console.log("WebSocket");
	   	        websocket = new WebSocket("ws://127.0.0.1:8080/socket/socketService");
	   	    } else if ('MozWebSocket' in window) {
	   	    	console.log("MozWebSocket");
	   	        websocket = new MozWebSocket("ws://socketService");
	   	    } else {
	   	    	console.log("SockJS");
	   	        websocket = new SockJS("http://127.0.0.1:8080/socket/socketService");
	   	    }
	   	    websocket.onopen = function (evnt) {
	   	        setMessageInnerHTML("链接服务器成功!");
	   	    };
	   	    websocket.onmessage = function (evnt) {
	   	    	setMessageInnerHTML(evnt.data);
	   	    };
	   	    websocket.onerror = function (evnt) {
	   	    	setMessageInnerHTML("连接error!");
	   	    };
	   	    websocket.onclose = function (evnt) {
	   	    	setMessageInnerHTML("与服务器断开了链接!"+evnt);
	   	    };
    }
    
    function initWebsocket(){
		//判断当前浏览器是否支持WebSocket
	    if ('WebSocket' in window) {
	        websocket = new WebSocket("ws://localhost:8080/socket/socketService");
	    }else if ('MozWebSocket' in window) {
            websocket = new MozWebSocket("ws://localhost:8080/socket/socketService");
        } else {
            websocket = new SockJS("http://localhost:8080/socket/socketService");
        }
	    //连接成功建立的回调方法
	    websocket.onopen = function () {
	        setMessageInnerHTML("WebSocket连接成功");
	    }
	    //连接发生错误的回调方法
	    websocket.onerror = function () {
	        setMessageInnerHTML("WebSocket连接发生错误");
	    };
	    //接收到消息的回调方法
	    websocket.onmessage = function (event) {
	        setMessageInnerHTML(event.data);
	    }
	    //连接关闭的回调方法
	    websocket.onclose = function () {
	        setMessageInnerHTML("WebSocket连接关闭");
	    }
	    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
	    window.onbeforeunload = function () {
	        closeWebSocket();
	    }
	}
    
  //发送消息 
	$("#sendBtn").click(function(){
		var message = $("#text").val().trim();
		if(message.length<1){
			alert("发送内容，不能为空。");
			return;
		}
		var state=websocket.readyState;
    	if(1!=state){
    		alert("连接已断开，请重新打开连接。");
    	}
    	websocket.send(message);
	});
  
	//关闭websocket连接
	$("#closeBtn").click(function(){
		/* readyState的代表的ReadOnly属性的连接状态。它可以有以下值：
    	一个0值表示该连接尚未建立。
    	值为1表示连接建立和沟通是可能的。
    	值为2表示连接是通过将结束握手。
    	值为3表示连接已关闭或无法打开。 */
    	var state=websocket.readyState;
    	if(3==state){
    		return;
    	}
        websocket.close();
	});
	//打开websocket连接
	$("#openBtn").click(function(){
		var state=websocket.readyState;
    	if(1!=state){
    		console.log("开始尝试，新连接..");
    		init();
    	}
	});
});
//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
	document.getElementById('message').innerHTML += innerHTML + '<br/>';
}
    
</script>
</html>