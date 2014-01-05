<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%
	String path = request.getContextPath();
	String basep = request.getScheme() + "://"+ request.getServerName()   + path + "/";
	String basePath = request.getScheme() + "://"+ request.getServerName()  + path + "/dptwb/";
	long currentTime = new Date().getTime();
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>教育学部人事管理系统</title>
<link rel="stylesheet" href="<%=basePath%>css/public_dark.css">
<link rel="stylesheet" href="<%=basePath%>css/login.css">
<script src="<%=basePath%>js/jquery-1.8.2.min.js" type="text/javascript"></script>

<script type="text/javascript">
var errorMsg = "${errorMsg}";
function showErrorMsg() {
	if (errorMsg != "") {
		$("div.lg_msg").text(errorMsg);
		$("div.lg_msg").show();
		
		$("div.pb_layer").show();
	}
}

$(function(){
   //提交验证
   $("input.wgt_smit").click(function(){
   		if($.trim($("input[name=userName]").val())=="" || $.trim($("input[name=password]").val())==""){
   			tipsTxt.showTips("请填写完整登录信息！");
   			return;
   		} else{
   			$(this).closest("form").submit();
   		}
   });
   
   $(document).keydown(function(e){
      if(e.keyCode==13) {
         $("input.wgt_smit").click();
      }
   });

   // 显示错误信息
   showErrorMsg();
});


</script>
</head>
<body style="background:url(<%=basePath%>image/login_bg_r.png) repeat-x 0 0">

<div class="page_login">
	<div class="wrap">
    	<div class="lg_top"></div>
        <div class="lg_center">
        <form class="login_box" action="<%=path %>/login.action" method="post">
        	<div style="padding:67px 0 0 61px">
                <div class="lg_msg"><span></span></div>
                <div class="fm_group"> 
                <input type="text" class="wgt_text" name="userName">
               	<input type="password" class="wgt_text" name="password">
                
                <input type="button" class="wgt_smit" value="">
            </div>	
        </form>
        </div>
    </div>
</div>

<div class="footer">田桦版权所有，未经书面授权禁止使用</div>
</body>
</html>
