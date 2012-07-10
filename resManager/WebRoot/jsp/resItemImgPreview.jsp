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
<title>查看菜品图片</title>
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
	<s:if test="resItem != null">
		<h2 align="left">"<s:property value="resItem.itemNo"/>"菜品菜品图片</h2>
		<div align="left"><a href="resItemImgModify.action?itemId=<s:property value="resItem.itemId"/>"  class="coolbg" style="font-size:20px; font-color:red">修改菜品图片</a></div>

		<s:if test="resItem.itemImg != null">
		<div style="border-width:2px;  border-color:#829df2; border-style:solid; width:auto">
			<div><img src="<%=fujianPath %>/<s:property value="resItem.itemImg"/>" alt="<s:property value="resItem.itemImg"/>"/></div>
			<div>下载此菜品图片<a href="imgDownloadAttach.action?itemId=<s:property value="resItem.itemId"/>" target="_blank">"<s:property value="resItem.itemImg"/>"</a></div>
		</div><br/>
		</s:if>
		<s:else>
		此菜品没有菜品图片
		</s:else>
	</s:if>
	<s:else>
		<h2 align="center" style="font-size:20px">获取菜品编号失败</h2>
	</s:else>
</div>
</div>
</body>
</html>