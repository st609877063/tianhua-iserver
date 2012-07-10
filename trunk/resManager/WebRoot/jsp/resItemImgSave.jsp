<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>添加菜品图片</title>
<link href="css/menu_base.css" rel="stylesheet" type="text/css">
<link href="css/frame.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script src="js/jquery.js" language="javascript" type="text/javascript"></script>
<script src="js/dedeajax2.js" language="javascript" type="text/javascript"></script>
<script src="js/leftmenu.js" language="javascript" type="text/javascript"></script>
<script src="js/frame.js" language="javascript" type="text/javascript"></script>

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
		<h2 align="center">为"<s:property value="resItem.itemNo"/>"菜品添加菜品图片</h2>
		
		<s:form action="resItemImgSave.action" method="post" enctype="multipart/form-data">
		<input type="hidden" name="itemNo" value='<s:property value="resItem.itemNo"/>'>
		<table>
			<tr height="30">
				<td><s:file name="uploadFile" label="选择菜品图片" /></td>
				<td><s:submit value="添加菜品图片"/></td>
			</tr>
		</table>
		</s:form>
	</s:if>
	<s:else>
		<h2 align="center" style="font-size:20px">菜品添加失败，不能添加菜品图片</h2>
	</s:else>

	<div align="center">
		<a href="resItemsShow.action"  class="coolbg" style="font-size:20px">跳过添加菜品图片</a>
	</div>
	</div>
</div>
</body>
</html>