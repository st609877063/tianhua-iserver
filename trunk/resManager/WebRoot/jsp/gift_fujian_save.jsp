<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>添加附件</title>
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
	<s:if test="giftItems != null">
		<h2 align="center">为"<s:property value="giftItems.i_no"/>"礼品添加附件</h2>
		
		<s:form action="gift_fujian_save.action" method="post" enctype="multipart/form-data">
		<input type="hidden" name="item_no" value='<s:property value="giftItems.i_no"/>'>
		<table>
			<tr height="30">
				<td><s:file name="uploadFile" label="选择附件" /></td>
				<td><s:submit value="添加附件"/></td>
			</tr>
			<tr height="30">
				<td>附件说明</td>
				<td><input type="text" name="giftFujian.fj_desc"/></td>
				<td>额外属性1</td>
				<td><input type="text" name="giftFujian.fj_attribute1"/></td>
				<td>额外属性2</td>
				<td><input type="text" name="giftFujian.fj_attribute2"/></td>
			</tr>
			<tr height="30">
				<td>额外属性3</td>
				<td><input type="text" name="giftFujian.fj_attribute3"/></td>
				<td>额外属性4</td>
				<td><input type="text" name="giftFujian.fj_attribute4"/></td>
				<td>额外属性5</td>
				<td><input type="text" name="giftFujian.fj_attribute5"/></td>
			</tr>
		</table>
		</s:form>

		<div style="display:none">
		<h2 align="left">----------下面为多附件上传-----------</h2>
		<s:form action="gift_fujian_saveMore.action"  method="post" enctype="multipart/form-data">
			<input type="hidden" name="item_no" value='<s:property value="giftItems.i_no"/>'>
            <s:file name="uploadFileMore" label="选择附件"/>
            <s:file name="uploadFileMore" label="选择附件"/>
            <s:file name="uploadFileMore" label="选择附件"/>
            <s:submit value="提交"/>
        </s:form>		
		</div>

	</s:if>
	<s:else>
		<h2 align="center" style="font-size:20px">礼品添加失败，不能添加附件</h2>
	</s:else>

	<div align="center">
		<a href="gift_items_list.action"  class="coolbg" style="font-size:20px">跳过添加附件</a>
	</div>
	</div>
</div>
</body>
</html>