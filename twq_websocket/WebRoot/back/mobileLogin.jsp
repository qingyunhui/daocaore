<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">  
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>广东九联科技股份有限公司SCM系统-用户登录</title>  
    <jsp:include page="pub.jsp"></jsp:include>
</head>
<body>
    <div class="easyui-navpanel">
        <header>
            <div class="m-toolbar">
                <span class="m-title">登录界面</span>
            </div>
        </header>
        <div style="margin:20px auto;width:100px;height:100px;border-radius:100px;overflow:hidden">
            <img src="${pageContext.request.contextPath}/style/login1.jpg" style="margin:0">
        </div>
        <div style="padding:0 20px">
            <div style="margin-bottom:10px">
                <input class="easyui-textbox" data-options="prompt:'Type username',iconCls:'icon-man'" style="width:100%;height:38px">
            </div>
            <div>
                <input class="easyui-textbox" type="password" data-options="prompt:'Type password',iconCls:'icon-lock'" style="width:100%;height:38px">
            </div>
            <div style="text-align:center;margin-top:30px">
                <a href="#" class="easyui-linkbutton" style="width:100%;height:40px"><span style="font-size:16px">登陆</span></a>
            </div>
            <div style="text-align:center;margin-top:30px">
                <a href="#" class="easyui-linkbutton" plain="true" outline="true" style="width:100px;height:35px"><span style="font-size:16px">注册</span></a> 
            </div>
        </div>
    </div>
</body>    
</html>