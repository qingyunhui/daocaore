<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/static/resources/taglib.jsp"%>
<%@ include file="/WEB-INF/static/resources/public.css.jsp"%>
<title>新增</title>
</head>
<body>
   <%--  <jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include> --%>
    <div class="content clearfix">
    	<%-- <jsp:include page="/WEB-INF/jsp/common/nav.jsp"></jsp:include> --%>
	    <div class="main">
		   <!-- 面包屑 开始@@@@@@@@@@@@@@@@@@ -->
			<div class="breadcrumb">
				<div class="breadcrumb-hidden">
					<span class="breadcrumb-icon"></span>
					<ol id="breadCrumb">
						<li>
							<span class="breadcrumb-line">.</span>
							<a href="list.htm">双击这里修改</a>
						</li>
						<li>
							<span class="breadcrumb-line">.</span>
							<a href="#">新增</a>
						</li>
					</ol>
				</div>
			</div>
			<!-- 面包屑  结束&&&&&&&&&&&& -->
			
			<!-- 主表单 开始@@@@@@@@@@@@@@@@@@ -->
			<div class="create-wrapper">
	            <form name="addForm" action="${daoCaoRenPath}/sys/jobInfo/doAdd2.htm" method="post">
	            	<ul class="clearfix">
						<li class="width-percent50">
		                    <label class="bold-label" for="name">名称：</label>
		                    <input name="name"/>
		                </li>
						<li class="width-percent50">
		                    <label class="bold-label" for="group">分组,一般是模块名：</label>
		                    <input name="group"/>
		                </li>
						<li class="width-percent50">
		                    <label class="bold-label" for="type">一次性任务或者cron任务：</label>
		                    <input name="type"/>
		                </li>
						<li class="width-percent50">
		                    <label class="bold-label" for="cron">cron 任务的 cron 表达式：</label>
		                    <input name="cron"/>
		                </li>
						<li class="width-percent50">
		                    <label class="bold-label" for="urls">目标服务器URL列表,如果多条使用逗号分割.：</label>
		                    <input name="urls"/>
		                </li>
						<li class="width-percent50">
		                    <label class="bold-label" for="classPath">任务类的 ClassPath：</label>
		                    <input name="classPath"/>
		                </li>
						<li class="width-percent50">
		                    <label class="bold-label" for="invokePolicy">调用策略, 优先,随机 等.：</label>
		                    <input name="invokePolicy"/>
		                </li>
						<li class="width-percent50">
		                    <label class="bold-label" for="isActivity">是否启用：</label>
		                    <input name="isActivity"/>
		                </li>
						<li class="width-percent50">
		                    <label class="bold-label" for="comments">描述信息：</label>
		                    <input name="comments"/>
		                </li>
	            	</ul>
	                <p class="break-line forms-submit-outer">
	                    <input class="sure-btn submit-layout small-hand" type="submit" value="确定"/>
                       	<a id="back" href="list.htm" class="return-btn">返回</a>
                    	<a id="reset" href="javascript:reset();"  class="reset-btn">重置</a>
	                </p>
	            </form>
            </div>
            <!-- 主表单 结束&&&&&&&&&&&& -->
	    </div>
    </div>
	<%-- <jsp:include page="../../common/bottom.jsp"></jsp:include> --%>
</body>
<%@ include file="/WEB-INF/static/resources/public.js.jsp"%>
<script type="text/javascript">
	$(function(){
		//页面加载完毕了，做点什么呢？	
	})
	
	/**
	* 表单重置按钮事件，因为要初始化执行时间状态，所以抽出
	*/
	function reset(){
		document.addForm.reset();
	}
</script>
</html>