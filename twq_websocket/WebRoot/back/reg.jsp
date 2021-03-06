<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>oa</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="pub.jsp"></jsp:include>
<script type="text/javascript">
$(function(){
	$('#loginDialog').show().dialog({
		modal : false,
		closable : false,
		iconCls : 'ext-icon-lock_open',
		buttons : [{
			id : 'loginBtn',
			text : '登录',
			handler : function() {
				location.replace('${pageContext.request.contextPath}/');
			}
		},{
			id : 'loginBtn',
			text : '注册',
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
			$.post('${pageContext.request.contextPath}/user/reg',$("#loginForm").serialize(),
				function(j){
			        if(j.success){
			        	location.replace('${pageContext.request.contextPath}/main');
			        }else{
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
	<div id="loginDialog" title="系统注册" class="easyui-dialog">
		<form method="post" id="loginForm">
			<table>
				<tr>
					<th>登录名</th>
					<td><input name="name" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>密码</th>
					<td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>邮件</th>
					<td><input name="email" class="easyui-validatebox" data-options="required:true,validType:'email'" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>