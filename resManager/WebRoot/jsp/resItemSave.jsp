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
<title>添加新菜品</title>
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

<script>

function submitForm() {
	var obsubmit = true;
	
	var itemNo = $("#resItemNo").val();
	//验证DB中是否存在
	resAjax.itemNoIsExist(itemNo, function(param) {
		if(param == "NOEXIST") {
			form1.submit();
		} else if(param == "EXIST") {
			alert("菜品编号重复");
			$("#resItemNo").focus();
			return false;
		}
	}); 
}

function isDigit(s) { 
	var patrn=/^[0-9]{1,20}$/; 
	if (!patrn.exec(s)) return false;
	return true ;
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
	<form name="form1" action="resItemSave.action" method="post">
		<h2 align="center">添加新菜品</h2>
		<table border="0" cellpadding="2" cellspacing="1" width="98%">
		<tbody>
			<tr>
				<td bgcolor="#FBFCE2" height="30">菜品编号:</td>
				<td height="30"><input type="text" name="resItem.itemNo" value="" id="resItemNo" /></td>
				<td bgcolor="#FBFCE2" height="30">菜品名称:</td>
				<td height="30"><input type="text" name="resItem.itemName" value="" id="resItemName" /></td>
				<td bgcolor="#FBFCE2" height="30">菜品类型:</td>
				<td height="30">
					<select name="resItem.itemType">
						<option value="1">主食</option>
						<option value="2">副食</option>
					</select>
				</td>
			</tr>
			<tr>
			<tr>
				<td bgcolor="#FBFCE2" height="30">菜品价格:</td>
				<td height="30"><input type="text" name="resItem.itemMoney" value=""　id="resItemMoney" /></td>
				<td bgcolor="#FBFCE2" height="30">菜品描述:</td>
				<td height="30"><input type="text" name="resItem.itemDesc" value=""　id="resItemDesc" /></td>
				<td bgcolor="#FBFCE2" height="30">菜品备注:</td>
				<td height="30"><input type="text" name="resItem.itemMemo" value="" id="resItemMemo" /></td>
			</tr>
		</tbody>
		</table><br/>

		<div align="center">
			<a href="javascript:void(0)"  class="coolbg" onclick="submitForm()"  style="font-size:20px">保存基本信息，开始添加附件。</a>
		</div>

	</form>
	</div>
</div>

</body>
</html>