<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>twc</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="pub.jsp"></jsp:include>
<script type="text/javascript">
$(function(){
	$('#loginDialog').show().dialog({
		modal : false,
		closable : false,
		iconCls : 'ext-icon-lock_open',
		buttons : [ {
			text : '注册',
			handler : function() {
				location.replace('${pageContext.request.contextPath}/reg.jsp');
			}
		}, {
			id : 'loginBtn',
			text : '登录',
			handler : function() {
				loginFun();
			}
		} ],
		onOpen : function() {
			$('form :input:first').focus();
			$('form :input').keyup(function(event) {
				if (event.keyCode == 13) {
					loginFun();
				}
			});
		}
	});
	
	var loginFun = function() {
		if($("#loginForm").form('validate')){
			$('#loginBtn').linkbutton('disable');
			$.post('${pageContext.request.contextPath}/user/login',$("#loginForm").serialize(),
				function(j){
			        if(j.success){
			        	location.replace('${pageContext.request.contextPath}/main');
			        }else{
			        	$('#loginBtn').linkbutton('enable');
			        	$.messager.show({
				        	title:'提示',
				        	msg:j.msg,
				        	timeout:5000,
				        	showType:'slide'
				        });
			        }
				},'json');
		}else{
			$.messager.show({
	        	title:'提示',
	        	msg:"拜托！！！",
	        	timeout:5000,
	        	showType:'slide'
	        });
		}
	};
});
</script>
<style type="text/css">
</style>
</head>

<body>
	<div id="loginDialog" title="系统登录" class="easyui-dialog">
		<form method="post" id="loginForm">
			<table>
				<tr>
					<td><input name="name" class="easyui-textbox" autocomplete="on" autofocus="autofocus" required="required" style="width:100%;height:40px;padding:12px" data-options="prompt:'用户名',iconCls:'icon-man',iconWidth:38"/></td>
				</tr>
				<tr>
					<td><input name="pwd" class="easyui-textbox" autocomplete="on" type="password" required="required" style="width:100%;height:40px;padding:12px" data-options="prompt:'密码',iconCls:'icon-lock',iconWidth:38"/></td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>