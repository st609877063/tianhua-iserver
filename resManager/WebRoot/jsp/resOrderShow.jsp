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

<title>订单列表</title>
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

<style type="text/css">
</style>

<script>

$(function() {
	$("#orderDateSrh").datepicker();
	$("#orderDateSrh").datepicker("option", "dateFormat", "yy-mm-dd");
});

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
				<table id="itemsListTable" bgcolor="#cfcfcf" border="0" cellpadding="2" cellspacing="1" width="100%">
					<tbody>
						<tr bgcolor="#E7E7E7">
							<td colspan="11" style="padding-left: 10px;" background="images/tbg.gif" height="24">
							订单信息 &nbsp; &nbsp; &nbsp; &nbsp;</a>
							</td>
						</tr>
						<tr align="center" bgcolor="#FBFCE2" height="25">
							<td width="2%"><div align="center">ID</div></td>
							<td width="7%"><div align="center">订单编号</div></td>
							<td width="7%"><div align="center">订单日期</div></td>
							<td width="5%"><div align="center">订单类型</div></td>
							<td width="7%"><div align="center">下单时间</div></td>
							<td width="7%"><div align="center">下单人</div></td>
							<td width="7%"><div align="center">手机号</div></td>
							<td width="7%"><div align="center">订单总费用</div></td>
							<td width="50%"><div align="center">订单内容</div></td>
						</tr>
						<s:if test="orderList!=null">
						<s:iterator value="orderList">
						<tr onmousemove="javascript:this.bgColor='#FCFDEE';" onmouseout="javascript:this.bgColor='#FFFFFF';" align="center" bgcolor="#FFFFFF" height="22">
							<td height="25" ><div align="center"><s:property value="pkId" /></div></td>
							<td height="25" ><div align="center"><s:property value="orderNo"/></div></td>
							<td height="25" ><div align="center"><s:property value="orderDate"/></div></td>
							<td height="25" ><div align="center">
							<s:if test="orderType==1">午餐</s:if><s:if test="orderType==2">晚餐</s:if><s:if test="orderType==3">生食</s:if>
							</div></td>
							<td height="25" ><div align="center"><s:property value="orderCreateDate" /></div></td>
							<td height="25" ><div align="center"><s:property value="orderUser" /></div></td>
							<td height="25" ><div align="center"><s:property value="orderPhone" /></div></td>
							<td height="25" ><div align="center"><s:property value="orderFee" /></div></td>
							<td height="25" ><div align="left">
								<s:if test="itemList !=null && itemList.size != 0">
								<s:iterator value="itemList" status="itemListStatus">
								【<s:property value="#itemListStatus.index+1"/>：
								编号：<s:property value="itemNo"/>, 菜名：<font color="red"><s:property value="itemName"/></font>,
								份数：<font color="red"><s:property value="orderItemNum"/></font>
								（<s:if test="itemType==1">主食</s:if><s:if test="itemType==2">副食</s:if>）
								】<br/>
								</s:iterator>
								</s:if>
								<s:else>此订单没有内容</s:else>
							</div></td>
						</tr>
						</s:iterator>
						</s:if>
						<s:else>
						<tr><td height="25" ><div align="center">没有订单信息!</div></td></tr>
						</s:else>
					</tbody>
				</table>

				<s:form name="seach_form" action="resOrderShow.action" method="post">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody>
					<tr><td height="4"></td></tr>
					<tr bgcolor="#FFFFFF">
						<td height="26">
						<table bgcolor="#cfcfcf" border="0" cellpadding="1" cellspacing="1" width="100%">
						<tbody>
						<tr bgcolor="#EEF4EA">
						<td background="images/wbg.gif">
						<table border="0" cellpadding="0" cellspacing="0" width="700">
						<tbody>
						<tr>
							<td width="80">订单日期：</td>
							<td width="160">
								<input id="orderDateSrh" name="orderDateSrh" type="text" value="<s:property value="orderDateSrh" />">
							</td>
							<td width="80">订单类型：</td>
							<td width="160">
								<select name="orderTypeSrh">
									<option value="-1" <s:if test="orderTypeSrh == -1">selected</s:if> >所有</option>
									<option value="1" <s:if test="orderTypeSrh == 1">selected</s:if> >午餐</option>
									<option value="2" <s:if test="orderTypeSrh == 2">selected</s:if> >晚餐</option>
									<option value="3" <s:if test="orderTypeSrh == 3">selected</s:if> >生食</option>
								</select>
							</td>
							<td>
								<img src="images/button_search.gif" onClick="seach_form.submit()"/>
							</td>
						</tr>
						</tbody>
						</table>
						</td>
						</tr>
						</tbody>
						</table>
						</td>
					</tr>
					<tr>
						<td colspan="2" height="4"></td>
					</tr>
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