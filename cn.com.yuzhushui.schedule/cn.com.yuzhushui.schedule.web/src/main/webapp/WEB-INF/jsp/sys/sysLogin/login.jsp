<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<body>
	<h1>well come to login page!</h1>
	
	<form id="loginForm" method="post" action="sys/sysLogin/doLogin.htm">
        	账号:<input type="text" id="accounts" name="accounts" value="admin" />
        	密码:<input type="password" id="passwords" name="passwords" value="111111" />
        	<input type="submit" value="登陆">
    </form>
	
</body>