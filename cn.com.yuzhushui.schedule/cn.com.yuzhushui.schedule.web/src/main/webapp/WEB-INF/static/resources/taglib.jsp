<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="dcrtld" uri="/dcrtldFunction" %>
<%
	String daoCaoRenPath = request.getContextPath();
	pageContext.setAttribute("daoCaoRenPath", daoCaoRenPath);
%>