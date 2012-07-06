<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%  
	String path = request.getContextPath();
	String basep = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String fujianPath = basep + "fujian";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>查看附件</title>
<link href="css/menu_base.css" rel="stylesheet" type="text/css">
<link href="css/frame.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script src="js/jquery.js" language="javascript" type="text/javascript"></script>
<script src="js/dedeajax2.js" language="javascript" type="text/javascript"></script>
<script src="js/leftmenu.js" language="javascript" type="text/javascript"></script>
<script src="js/frame.js" language="javascript" type="text/javascript"></script>

<script type='text/javascript' src="<%=path %>/dwr/util.js"></script>
<script type='text/javascript' src="<%=path %>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=path %>/dwr/interface/giftAjax.js"></script>
<script type="text/javascript">
function deleteFujian(fjId) {
	if(confirm("确定删除？")) {
		giftAjax.deleteFujianById(fjId, function(param) {
			if(param == "SUCCESS") {
				alert("删除成功");
				document.getElementById("file_"+fjId).style.display="none";
			}
		}); 
	}
}
</script>

</head>
<body class="showmenu">

<div style="display: none;" class="pagemask"></div>
<%@ include file="include_top.jsp"%>
<div class="left">
	<div class="menu" id="menu">
		<%@ include file="include_menu.jsp"%>
	</div>
</div>

<div class="right">
<div class="main" align="center">
	<s:if test="giftItems != null">
		<h2 align="left">"<s:property value="giftItems.i_no"/>"礼品附件</h2>
		<div align="left"><a href="gift_fujian_add.action?item_id=<s:property value="giftItems.i_id"/>"  class="coolbg" style="font-size:20px; font-color:red">添加附件</a></div>

		<s:if test="giftFujianList!=null && giftFujianList.size() != 0">
		<s:iterator value="giftFujianList" id="giftFujian" status="giftFujianStatus">
		<div id="file_<s:property value="#giftFujian.fj_id"/>" style="border-width:2px;  border-color:#829df2; border-style:solid; width:auto">
			<s:if test="#giftFujian.fj_type == 'image'">
			<div><img src="<%=fujianPath %>/<s:property value="#giftFujian.fj_name"/>" alt="<s:property value="#giftFujian.fj_name"/>"/></div>
			<div>下载此附件<a href="gift_fujian_downloadAttach.action?fujianId=<s:property value="#giftFujian.fj_id"/>" target="_blank">"<s:property value="#giftFujian.fj_name"/>"</a></div>
			</s:if>
			<s:else>
			<div>下载此附件<a href="gift_fujian_downloadAttach.action?fujianId=<s:property value="#giftFujian.fj_id"/>" target="_blank">"<s:property value="#giftFujian.fj_name"/>"</a></div>
			</s:else>
		 	<a href="javascript:void(0)" class="coolbg" onclick="deleteFujian('<s:property value="#giftFujian.fj_id"/>')">删除此附件</a> &nbsp;
		</div><br/>
		</s:iterator>
		</s:if>
		<s:else>
		此礼品没有附件
		</s:else>
	</s:if>
	<s:else>
		<h2 align="center" style="font-size:20px">获取礼品编号失败</h2>
	</s:else>
</div>
</div>
</body>
</html>