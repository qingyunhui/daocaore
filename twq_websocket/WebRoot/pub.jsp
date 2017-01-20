<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%
Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
Cookie[] cookies = request.getCookies();
if (null != cookies) {
	for (Cookie cookie : cookies) {
		cookieMap.put(cookie.getName(), cookie);
	}
}
String easyuiTheme = "default";//指定如果用户未选择样式，那么初始化一个默认样式
if (cookieMap.containsKey("easyuiTheme")) {
	Cookie cookie = (Cookie) cookieMap.get("easyuiTheme");
	easyuiTheme = cookie.getValue();
}
%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<link rel="stylesheet" id="easyuiTheme" href="${pageContext.request.contextPath}/js/jquery-easyui/themes/<%=easyuiTheme%>/easyui.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery-easyui/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/syExtIcon.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/syExtCss.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ExtEasyui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Extjs.js"></script>

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/js/html5.js"></script>
    <![endif]-->