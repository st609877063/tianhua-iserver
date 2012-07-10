<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%  
	String path = request.getContextPath();
	String basep = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String qrcodePath = basep + "qrcode";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title>修改菜品基本信息</title>
<link href="css/menu_base.css" rel="stylesheet" type="text/css">
<link href="css/frame.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script src="js/jquery.js" language="javascript" type="text/javascript"></script>
<script src="js/dedeajax2.js" language="javascript" type="text/javascript"></script>
<script src="js/leftmenu.js" language="javascript" type="text/javascript"></script>
<script src="js/frame.js" language="javascript" type="text/javascript"></script>

<script>

	function submitForm() {
		var obsubmit = true;

		/*
		var itemNum = $("#resItemMoney").val();
		alert(itemNum);
		if(itemNum!="" && !isDigit(itemNum)) {
			obsubmit = false;
			alert("《菜品数量》请填写数字");
			$("#resItemMoney").focus();
			return false;
		}
		*/
	
		if(obsubmit) {
			form1.submit();
		}
	}

	function isDigit(s) { 
		var patrn=/^[0-9]{1,20}$/; 
		if (!patrn.exec(s)) return false 
		return true 
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
	<form name="form1" action="resItemUpdate.action" method="post">
		<h2 align="center">修改菜品基本信息</h2>
		<table border="0" cellpadding="2" cellspacing="1" width="98%">
		<tbody>
			<tr>
				<td bgcolor="#FBFCE2" height="30">菜品编号:</td>
				<td height="30"><input type="text" name="resItem.itemNo" value="<s:property value="resItem.itemNo"/>" id="resItemNo" readonly/></td>
				<td bgcolor="#FBFCE2" height="30">菜品名称:</td>
				<td height="30"><input type="text" name="resItem.itemName" value="<s:property value="resItem.itemName"/>" id="resItemName" /></td>
				<td bgcolor="#FBFCE2" height="30">菜品类型:</td>
				<td height="30">
					<select name="resItem.itemType">
						<option value="1" <s:if test="resItem.itemType == 1">selected</s:if> >主食</option>
						<option value="2" <s:if test="resItem.itemType == 2">selected</s:if> >副食</option>
					</select>
				</td>
			</tr>
			<tr>
			<tr>
				<td bgcolor="#FBFCE2" height="30">菜品价格:</td>
				<td height="30"><input type="text" name="resItem.itemMoney" value="<s:property value="resItem.itemMoney"/>"　id="resItemMoney" /></td>
				<td bgcolor="#FBFCE2" height="30">菜品描述:</td>
				<td height="30"><input type="text" name="resItem.itemDesc" value="<s:property value="resItem.itemDesc"/>"　id="resItemDesc" /></td>
				<td bgcolor="#FBFCE2" height="30">菜品备注:</td>
				<td height="30"><input type="text" name="resItem.itemMemo" value="<s:property value="resItem.itemMemo"/>" id="resItemMemo" /></td>
			</tr>
		</tbody>
		</table><br/>


		<div align="center">
			<a href="javascript:void(0)"  class="coolbg" onclick="submitForm()"  style="font-size:20px">提交修改</a>
		</div>
	</form>

	</div>
</div>

</body>
</html>