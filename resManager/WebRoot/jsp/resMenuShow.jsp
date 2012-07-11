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

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<title>今日菜单</title>
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

<style type="text/css">
</style>

<script>
	function deleteItem(Field, pkId) {
		if (confirm("确定删除？")) {
			resAjax.deleteItemById(pkId, function(param) {
				if (param == "SUCCESS") {
					alert("删除成功");
					var findex = getElementOrder(Field)+1;
					document.getElementById("itemsListTable").deleteRow(findex);
				}
			});
		}
	}

	//  查询出将要删除的行所在的位置index
	function getElementOrder(field) {
		var i = 0;
		var order = 0;
		var elements = document.getElementsByName(field.name);
		for (i = 0; i < elements.length; i++) {
			order++;
			if (elements[i] == field) {
				break;
			}
		}
		return order;
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
<table align="center" border="0" cellpadding="0" cellspacing="0" width="98%">
	<tbody>
		<tr>
			<td align="center" valign="top">
				<table bgcolor="#D6D6D6" border="0" cellpadding="0" cellspacing="1" width="100%">
					<tbody>
						<tr>
							<td background="images/newlinebg3.gif" height="26">
								<table border="0" cellpadding="0" cellspacing="0" width="98%">
									<tbody><tr><td align="center"></td></tr></tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>
						<tr bgcolor="#FFFFFF">
							<td height="4"></td>
						</tr>
					</tbody>
				</table>
				<s:form name="seach_form" action="resMenuSave.action" method="post">
				<table id="itemsListTable" bgcolor="#cfcfcf" border="0" cellpadding="2" cellspacing="1" width="100%">
					<tbody>
						<tr bgcolor="#E7E7E7">
							<td colspan="11" style="padding-left: 10px;" background="images/tbg.gif" height="24">菜单信息</td>
						</tr>
						<tr align="center" bgcolor="#FBFCE2" height="25">
							<td width="10%"><div align="center">菜单日期</div></td>
							<td width="10%"><div align="center">菜单类型</div></td>
							<td width="70%"><div align="center">菜单内容</div></td>
							<td width="10%"><div align="center">管理</div></td>
						</tr>
						<s:if test="rtnList !=null ">
						<s:iterator value="rtnList">
						<tr onmousemove="javascript:this.bgColor='#FCFDEE';" onmouseout="javascript:this.bgColor='#FFFFFF';" align="center" bgcolor="#FFFFFF" height="22">
							<td height="30" ><div align="center"><s:property value="menuDate" /></div></td>
							<td height="30" ><div align="center">
								<s:if test="menuType==1">午餐</s:if>
								<s:if test="menuType==2">晚餐</s:if>
								<s:if test="menuType==3">生食</s:if>
							</div></td>
							<td height="30" ><div align="center"><s:property value="itemContent" /></div></td>
							<td height="30" >
								<div align="center">
								[<a href='resMenuDetail.action?showDate=<s:property value="menuDate"/>&menuType=<s:property value="menuType"/>'>查看</a>]
								[<a href='toResMenuUpdate.action?showDate=<s:property value="menuDate"/>&menuType=<s:property value="menuType"/>'>修改</a>]
								</div>
							</td>
						</tr>
						</s:iterator>
						</s:if>
						<s:else>
						<tr><td height="30" ><div align="center">没有菜单信息!</div></td></tr>
						</s:else>
					</tbody>
				</table>
			</s:form>
			</td>
		</tr>
	</tbody>
</table>
</div>
</div>

</body>
</html>