<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="footer">
	<div class="mune" data-menu0="${path}app/appMain/myMain.htm">
    	<img src="${path}image/myMain/footer/1.png">
        <p>首页</p>
    </div>
	<div class="mune" data-menu1="${path}app/appMain/xxx1.htm">
    	<img src="${path}image/myMain/footer/2.png">
        <p>商家</p>
    </div>
	<div class="mune" data-menu2="${path}app/appMain/xxx2.htm">
    	<img src="${path}image/myMain/footer/3.png">
        <p>申请加盟</p>
    </div>
	<div class="mune" data-menu3="${path}app/appMain/test.htm">
    	<img src="${path}image/myMain/footer/4.png">
        <p>个人中心</p>
    </div>    
</div>
<script type="text/javascript">
   $(function(){
	  //处理footer的切换后的背景及颜色的变化。
	  var index=${currIndex};
	  var objs=$(".footer .mune");
	  $.each(objs, function(i,o) {    
		    var curObj=this;
		    $(curObj).click(function(){
		        //处理页面跳转
			    var curUrl=$(curObj).data("menu"+i);
			    location.href=curUrl;
		    });
		});    
  });
</script>