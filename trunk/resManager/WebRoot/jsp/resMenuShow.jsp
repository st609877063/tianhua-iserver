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
<script type='text/javascript' src="<%=path %>/dwr/interface/giftAjax.js"></script>

<style type="text/css">
</style>

<script>
	function deleteItem(Field, pkId) {
		if (confirm("确定删除？")) {
			giftAjax.deleteItemById(pkId, function(param) {
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
							<td width="5%"><div align="center">ID</div></td>
							<td width="9%"><div align="center">菜单编号</div></td>
							<td width="9%"><div align="center">菜单名称</div></td>
							<td width="9%"><div align="center">受礼人</div></td>
							<td width="9%"><div align="center">赠礼人</div></td>
							<td width="9%"><div align="center">受赠时间</div></td>
							<td width="9%"><div align="center">菜单数量</div></td>
							<td width="9%"><div align="center">菜单类型</div></td>
							<td width="9%"><div align="center">附件信息</div></td>
							<td width="9%"><div align="center">仓库信息</div></td>
							<td width="15%"><div align="center">管理</div></td>
						</tr>
						<s:if test="rtnList !=null ">
						<s:iterator value="rtnList" id="items">
						<tr onmousemove="javascript:this.bgColor='#FCFDEE';" onmouseout="javascript:this.bgColor='#FFFFFF';" align="center" bgcolor="#FFFFFF" height="22">
							<td height="20" ><div align="center"><s:property value="#items.i_id" /></div></td>
							<td height="20" ><div align="center"><s:property value="#items.i_no" /></div></td>
							<td height="20" ><div align="center"><s:property value="#items.i_name" /></div></td>
							<td height="20" ><div align="center"><s:property value="#items.i_slr_name" /></div></td>
							<td height="20" ><div align="center"><s:property value="#items.i_zlr_name" /></div></td>
							<td height="20" ><div align="center"><s:property value="#items.i_sztime_show" /></div></td>
							<td height="20" ><div align="center"><s:property value="#items.i_num" /></div></td>
							<td height="20" ><div align="center"><s:property value="#items.i_type" /></div></td>
							<td height="20" >
								<div align="center">
								[<a href='gift_fujian_preview.action?item_id=<s:property value="#items.i_id" />' target="_blank">查看</a>]
								[<a href='gift_fujian_add.action?item_id=<s:property value="#items.i_id" />'>新增</a>]
								</div>
							</td>
							<td height="20" >
								<div align="center">
									<span class="STYLE1">[</span><a href="gift_cangku_updateByItemId.action?item_id=<s:property value="#items.i_id"/>">查看</a><span class="STYLE1">]</span>
								</div>
							</td>
							<td height="20" >
								<div align="center">
									<span><img src="image/037.gif" width="9" height="9" /></span>
									<span class="STYLE1">[</span>
									<s:url id="url_update_items" action="gift_items_updateP">
										<s:param name="updateP_i_id"><s:property value="#items.i_id" /></s:param>
									</s:url>
									<s:a href="%{url_update_items}">修改</s:a>
									<span class="STYLE1">]</span>
									<span><img src="image/010.gif" width="9" height="9" /></span>
									<span class="STYLE1">[</span><a href="javascript:void(0)" name="deleteItemButton"  onclick="deleteItem(this, '<s:property value="#items.i_id"/>')">删除</a><span class="STYLE1">]</span>
								</div>
							</td>
						</tr>
						</s:iterator>
						</s:if>
						<s:else>
						<tr><td height="20" ><div align="center">没有菜单信息!</div></td></tr>
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