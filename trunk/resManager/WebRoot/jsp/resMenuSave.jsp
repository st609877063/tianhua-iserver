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
<title>添加新菜单</title>
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

<!--  时间控件 -->
<link href="css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.min.js"></script>
<script src="js/jquery-ui.min.js"></script>

<script>

$(function() {
	$("#resMenuDate").datepicker();
	$("#resMenuDate").datepicker("option", "dateFormat", "yy-mm-dd");
});

function submitForm() {
	var obsubmit = true;
	var menuDate = $("#resMenuDate").val();
	var menuType = $("#resMenuType").val();
	if(menuDate == "") {
		alert("日期为空");
		return false;
	}

	//验证DB中是否存在
	resAjax.menuIsExist(menuDate, menuType, function(param) {
		if(param == "NOEXIST") {
			//OK
			//其他校验，开始
			var menuItems = "";
			var itemId = 0;
		  	$('input[name="itemCheckbox"]:checked').each(function(){    
		   		itemId = $(this).val();
		   		menuItems = menuItems + itemId + "=" + $("#mom_"+itemId).val() + "#";
		  	});  
			if(menuItems == "") {
				obsubmit = false;
				alert("未选择菜品");
				return false;
			}
			$("#menuItems").val(menuItems);
			
			if(obsubmit) {
				form1.submit();
			}
		} else if(param == "EXIST") {
			alert("菜单已存在");
			$("#resMenuDate").focus();
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
	<form name="form1" action="resMenuSave.action" method="post">
		<h1 align="center">添加新菜单</h1>
		
		<table border="0" cellpadding="2" cellspacing="1" width="98%" >
		<tbody><tr>
			<td bgcolor="#FBFCE2" height="30">菜单日期:</td>
			<td height="30"><input type="text" id="resMenuDate" name="resMenu.menuDate" /></td>
			<td bgcolor="#FBFCE2" height="30">菜单类型:</td>
			<td height="30">
				<select name="resMenu.menuType" id="resMenuType">
					<option value="1">午餐 </option>
					<option value="2">晚餐</option>
					<option value="3">生食</option>
				</select>
			</td>
			<td width="60%"></td>
		</tr></tbody>
		</table><br/>
		
		<input type="hidden" name="menuItems" id="menuItems">
		
		<s:if test="zhuItemsList !=null  && zhuItemsList.size != 0">
		<h2 align="left">主食信息</h2>
		<table border="0" cellpadding="2" cellspacing="1" width="98%">
		<tbody><tr><td>
		<ul style="margin: 0px 0px 0px 5px;">
			<s:iterator value="zhuItemsList">
			<li>
			<span><img src="<%=fujianPath %>/<s:property value="itemImg"/>" width="150" height="150"/></span><br/>
			<div>编号：<s:property value="itemNo"/></div>
			<div>菜名：<s:property value="itemName"/></div>
			<div>价格：<s:property value="itemMoney"/></div>
			<div>说明：<s:property value="itemDesc"/></div>
			<div>外卖价格：<input type="text" id="mom_<s:property value="itemId"/>" value="<s:property value="itemMoney"/>" style="width:70px"></div>
			<div align="center" style="margin:15px"><input type="checkbox" name="itemCheckbox" value="<s:property value="itemId"/>"></div>
			</li>
			</s:iterator>
		</ul>
		</td></tr></tbody>
		</table><br/>
		</s:if>

		<s:if test="fuItemsList !=null  && fuItemsList.size != 0">
		<h2 align="left">副食信息</h2>
		<table border="0" cellpadding="2" cellspacing="1" width="98%">
		<tbody><tr><td>
		<ul style="margin: 0px 0px 0px 5px;">
			<s:iterator value="fuItemsList">
			<li>
			<span><img src="<%=fujianPath %>/<s:property value="itemImg"/>" width="150" height="150"/></span><br/>
			<div>编号：<s:property value="itemNo"/></div>
			<div>菜名：<s:property value="itemName"/></div>
			<div>价格：<s:property value="itemMoney"/></div>
			<div>说明：<s:property value="itemDesc"/></div>
			<div>外卖价格：<input type="text" id="mom_<s:property value="itemId"/>" value="<s:property value="itemMoney"/>" style="width:70px"></div>
			<div align="center" style="margin:15px"><input type="checkbox" name="itemCheckbox" value="<s:property value="itemId"/>"></div>
			</li>
			</s:iterator>
		</ul>
		</td></tr></tbody>
		</table><br/>
		</s:if>


		<div align="center">
			<a href="javascript:void(0)"  class="coolbg" onclick="submitForm()"  style="font-size:20px">保存</a>
		</div>

	</form>
	</div>
</div>

</body>
</html>