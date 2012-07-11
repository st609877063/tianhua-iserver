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
<title>菜单内容</title>
<link href="css/menu_base.css" rel="stylesheet" type="text/css">
<link href="css/frame.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script src="js/jquery.js" language="javascript" type="text/javascript"></script>
<script src="js/dedeajax2.js" language="javascript" type="text/javascript"></script>
<script src="js/leftmenu.js" language="javascript" type="text/javascript"></script>
<script src="js/frame.js" language="javascript" type="text/javascript"></script>

<script type='text/javascript' src="<%=path %>/dwr/util.js"></script>
<script type='text/javascript' src="<%=path %>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=path %>/dwr/interface/resAjax.js"></script>

<script type="text/javascript">
function deleteMuenItem(pkId) {
	if(confirm("确定删除？")) {
		resAjax.deleteMuenItemById(pkId, function(param) {
			if(param == "SUCCESS") {
				alert("删除成功");
				document.getElementById("item_"+pkId).style.display="none";
			}
		}); 
	}
}

function updateMuenItem(pkId) {
	var newMoney = $("#mom_"+pkId).val();
	
	resAjax.updateMuenItemMoneyById(pkId, newMoney, function(param) {
		if(param == "SUCCESS") {
			alert("更新成功");
		}
	}); 
}


</script>


<style type="text/css"> 
.main li{
	float: left;
	border: #FBE8C0 1px solid;
	width: 160px;
	height: 300px;
	text-align: left;
	background: #FFFDE4;
	margin-right: 2px;
	margin-bottom: 5px;
	-margin-bottom: 10px;
	background: #FFFDE4;
	overflow: hidden;
}
</style>

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
	<form name="form1" action="toResMenuUpdate.action" method="post">
		<h1 align="center"><s:property value="showDate"/>日
			[<s:if test="menuType==1">午餐</s:if><s:if test="menuType==2">晚餐</s:if><s:if test="menuType==3">生食</s:if>]菜单内容
		</h1>
		<input type="hidden" name="showDate" value="<s:property value="showDate"/>">
		<input type="hidden" name="menuType" value="<s:property value="menuType"/>">
		
		
		<h2 align="left">菜单详细：</h2>
		<table border="0" cellpadding="2" cellspacing="1" width="98%">
		<tbody><tr><td>
		<ul style="margin: 0px 0px 0px 5px;">
			<s:if test="rtnList !=null  && rtnList.size != 0">
			<s:iterator value="rtnList">
			<li id="item_<s:property value="pkId"/>">
			<div><s:if test="itemType==1">主食</s:if><s:if test="itemType==2">副食</s:if></div>
			<span><img src="<%=fujianPath %>/<s:property value="itemImg"/>" width="150" height="150"/></span><br/>
			<div>编号：<s:property value="itemNo"/></div>
			<div>菜名：<s:property value="itemName"/></div>
			<div>说明：<s:property value="itemDesc"/></div>
			<div>价格：<s:property value="itemMoney"/></div>
			<div>外卖价格：<input type="text" id="mom_<s:property value="pkId"/>" value="<s:property value="menuMoney"/>" style="width:70px"></div>
			<div align="center" style="margin:15px">
				<a href="javascript:void(0)" onclick="updateMuenItem('<s:property value="pkId"/>')"><img src="images/update.jpg"/></a>
				<a href="javascript:void(0)" onclick="deleteMuenItem('<s:property value="pkId"/>')"><img src="images/delete.jpg"/></a>
			</div>
			</li>
			</s:iterator>
			</s:if>
			<s:else><div>今日没有菜单</div></s:else>
		</ul>
		</td></tr></tbody>
		</table><br/>


		<div align="center">
			<a href="resMenuShow.action"  class="coolbg" style="font-size:20px">返回</a>
		</div>

	</form>
	</div>
</div>

</body>
</html>