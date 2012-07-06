<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
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
		<div class="main" width="100%" align="center">
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
								<tbody><tr bgcolor="#FFFFFF"><td height="4"></td></tr></tbody>
							</table>
							<form name="form1" action="gift_group_user_save.action" method="post">
								<table bgcolor="#cfcfcf" border="0" cellpadding="2" cellspacing="1" width="100%">
									<tbody>
										<tr bgcolor="#E7E7E7">
											<td style="padding-left: 10px;" background="images/tbg.gif" height="24">用户组信息 &gt; 添加</td>
											<td style="padding-left: 10px;" background="images/tbg.gif" height="24"></td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td>
												<label>分组名:</label>
												<input type="text"  name="gift_group_user.group_id" value='<s:property value="gift_group_user.group_id"/>' />
											</td>
											<td>
												<label>用户名:</label>
												<input type="text"  name="gift_group_user.user_id" value='<s:property value="gift_group_user.user_id"/>' />
											</td>
										</tr>
									
										<tr bgcolor="#ffffff">
											<td></td>
											<td height="24"><a href="#" class="coolbg" onClick="form1.submit()">&nbsp; 保存&nbsp;</a></td>
										</tr>
									</tbody>
								</table>
							</form>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>