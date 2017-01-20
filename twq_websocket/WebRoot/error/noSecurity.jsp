<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8">
	try {
		parent.$.messager.alert('提示','对不起你无权操作');
	} catch (e) {
	}
</script>