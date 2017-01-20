<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
	function changeTheme(theme) {
		var easyuiTheme = $('#easyuiTheme');
		var url = easyuiTheme.attr('href');
		var href = url.substring(0, url.indexOf('themes')) + 'themes/' + theme + '/easyui.css';
		easyuiTheme.attr('href', href);

		var $iframe = $('iframe');
		if ($iframe.length > 0) {
			for ( var i = 0; i < $iframe.length; i++) {
				var ifr = $iframe[i];
				try {
					$(ifr).contents().find('#easyuiTheme').attr('href', href);
				} catch (e) {
					try {
						ifr.contentWindow.document.getElementById('easyuiTheme').href = href;
					} catch (e) {
					}
				}
			}
		}

		$.cookie('easyuiTheme', theme, {
			expires : 7
		});
	}
	function logoutFun() {
		$.post('${pageContext.request.contextPath}/user/logout', function(j) {
			location.replace('index.jsp');
		}, 'json');
	}
	/* function lockWindowFun() {
		$.post('user/logout', function(j) {
			$('#loginDialog').dialog('open');
		}, 'json');
	} */
	$(function() {
		$('#loginDialog').show().dialog({
			modal : true,
			closable : false,
			iconCls : 'ext-icon-lock_open',
			buttons : [ {
				id : 'loginBtn',
				text : '登录',
				handler : function() {
					loginFun();
				}
			} ],
			onOpen : function() {
				$('#weirpwd').val('');
				$('form :input').keyup(function(event) {
					if (event.keyCode == 13) {
						loginFun();
					}
				});
			}
		}).dialog('close');

	});
	function loginFun() {
		if ($("#loginForm").form('validate')) {
			$('#loginBtn').linkbutton('disable');
			$.post('user/login', $("#loginForm").serialize(), function(j) {
				if (j.success) {
					$('#loginDialog').dialog('close');
				} else {
					$.messager.alert('提示', j.msg, 'error', function() {
						$('#weirname').focus();
					});
				}
				$('#loginBtn').linkbutton('enable');
			}, 'json');
		}
	}
	$('#passwordDialog').show().dialog({
		modal : true,
		closable : true,
		iconCls : 'ext-icon-lock_edit',
		buttons : [ {
			text : '修改',
			handler : function() {
				if ($('#passwordDialog form').form('validate')) {
					$.post('${pageContext.request.contextPath}/user/editPwd', {
						pwd : $('#pwd').val()
					}, function(result) {
						if (result.success) {
							$.messager.alert('提示', '密码修改成功！', 'info');
							$('#passwordDialog').dialog('close');
						}
					}, 'json');
				}
			}
		} ],
		onOpen : function() {
			$('#passwordDialog form :input').val('');
		}
	}).dialog('close');
	$('#userDialog').show().dialog({
		modal : true,
		closable : true,
		iconCls : 'ext-icon-lock_edit',
		buttons : [ {
			text : '修改',
			handler : function() {
				if ($('#userDialog form').form('validate')) {
					$.post('${pageContext.request.contextPath}/user/editUser', $("#userForm").serialize(), function(result) {
						if (result.success) {
							$.messager.alert('提示', '资料修改成功！', 'info');
							$('#userDialog').dialog('close');
						}else{
							$.messager.alert('提示', j.msg, 'info');
							$('#userDialog').dialog('close');
						}
					}, 'json');
				}
			}
		} ]
	}).dialog('close');

	$('#inviteDialog').show().dialog({
		modal : true,
		closable : true,
		iconCls : 'ext-icon-rosette',
		buttons : [ {
			text : '邀请',
			handler : function() {
				if ($('#inviteDialog form').form('validate')) {
					$.post('${pageContext.request.contextPath}/user/invite', {
						emails : $('#emails').val()
					}, function(result) {
						if (result.success) {
							$.messager.alert('提示', '邀请已发出！', 'info');
							$('#inviteDialog').dialog('close');
						}
					}, 'json');
				}
			}
		} ],
		onOpen : function() {
			$('#inviteDialog form :input').val('');
		}
	}).dialog('close');
</script>
<div style="position: absolute; right: 0px; bottom: 0px;"><strong>hi!${U.name}</strong>
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_pfMenu',iconCls:'ext-icon-rainbow'">更换皮肤</a>
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu',iconCls:'ext-icon-cog'">控制面板</a>
</div>
<div id="layout_north_pfMenu" style="width: 80px; display: none;">
	<div onclick="changeTheme('default');" title="default">默认</div>
	<div class="menu-sep"></div>
	<div onclick="changeTheme('metro-blue');" title="metro-blue">蓝色</div>
	<div onclick="changeTheme('metro-gray');" title="metro-gray">灰色</div>
	<div onclick="changeTheme('metro-green');" title="metro-green">绿色</div>
	<div onclick="changeTheme('metro-orange');" title="metro-orange">橘色</div>
	<div onclick="changeTheme('metro-red');" title="metro-red">红色</div>
</div>
<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<div data-options="iconCls:'ext-icon-user_edit'" onclick="$('#passwordDialog').dialog('open');">修改密码</div>
	<div class="menu-sep"></div>
	<div data-options="iconCls:'ext-icon-lock'" onclick="$('#userDialog').dialog('open');">修改资料</div>
	<div class="menu-sep"></div>
	<div data-options="iconCls:'ext-icon-rosette'" onclick="$('#inviteDialog').dialog('open');">邀请供应商</div>
	<div class="menu-sep"></div>
	<div data-options="iconCls:'ext-icon-door_out'" onclick="logoutFun();">退出系统</div>
</div>

<div id="userDialog" title="修改资料" style="display: none;">
	<form method="post" id="userForm" onsubmit="return false;">
		<table class="table">
			<tr>
				<th>内部邮箱</th>
				<td><input id="email" name="email" value="${U.email}" class="easyui-validatebox" data-options="required:true,validType:'email'"/></td>
			</tr>
			<tr>
				<th>外部邮箱</th>
				<td><input id="emailw" name="emailw" value="${U.emailw}" class="easyui-validatebox" data-options="required:true,validType:'email'"/></td>
			</tr>
			<tr>
				<th>手机号</th>
				<td><input id="mphone" name="mphone" value="${U.mphone}" class="easyui-validatebox" data-options="required:true,validType:'mobile'"/></td>
			</tr>
			<tr>
				<th>座机</th>
				<td><input id="phone" name="phone" value="${U.phone}" class="easyui-validatebox" data-options="required:true,validType:'phone'"/></td>
			</tr>
			<tr>
				<th>姓名</th>
				<td><input id="realName" name="realName" value="${U.realName}" class="easyui-validatebox" data-options="required:true"/></td>
			</tr>
		</table>
	</form>
</div>

<div id="passwordDialog" title="修改密码" style="display: none;">
		<form method="post" class="form" onsubmit="return false;">
			<table class="table">
				<tr>
					<th>新密码</th>
					<td><input id="pwd" name="pwd" type="password" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
			</table>
		</form>
	</div>
<div id="inviteDialog" title="邀请供应商" style="display: none;width: 300px;height: 300px;">
		<form method="post" class="form" onsubmit="return false;">
			<table class="table">
				<tr>
					<th>邮箱</th>
					<td><textarea id="emails" name="emails" cols="100" rows="8" style="border:0;width:100%;height:100%;resize:none" class="easyui-validatebox" data-options="required:true"></textarea></td>
				</tr>
			</table>
		</form>
	</div>