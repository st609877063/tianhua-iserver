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
							<form name="form1" action="gift_cangku_update.action" method="post">
								<table bgcolor="#cfcfcf" border="0" cellpadding="2" cellspacing="1" width="100%">
									<tbody>
										<tr bgcolor="#E7E7E7">
											<td style="padding-left: 10px;" background="images/tbg.gif" height="24">仓库信息 &gt; 修改</td>
											<td style="padding-left: 10px;" background="images/tbg.gif" height="24"></td>
											<td style="padding-left: 10px;" background="images/tbg.gif" height="24"></td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td>
												<label>礼品编号:</label>
												<input type="text" id="cangku_i_no" name="cangku.ck_i_no" value='<s:property value="cangku.ck_i_no"/>' readonly />
											</td>
											<td>
												<label>礼品名称:</label>
												<input type="text" id="cangku_i_name" name="cangku.i_name" value='<s:property value="cangku.i_name"/>' readonly />
											</td>
											<td>
												<input type="text" id="cangku_ck_id" name="cangku.ck_id" value='<s:property value="cangku.ck_id"/>' readonly style="display: none;" />
												<!-- cangku.i_memo用来识别update后返回页面 -->
												<input type="text" id="cangku_i_memo" name="cangku.i_memo" value='<s:property value="cangku.i_memo"/>' readonly style="display: none;" />
											</td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td>
												<label>库　　房:</label>
												<input type="text" id="cangku_ck_kufang" name="cangku.ck_kufang" value='<s:property value="cangku.ck_kufang"/>' />
											</td>
											<td>
												<label>货　　架:</label>
												<input type="text" id="cangku_ck_huojia" name="cangku.ck_huojia" value='<s:property value="cangku.ck_huojia"/>' />
											</td>
											<td>
												<label>　　　层:</label>
												<input type="text" id="cangku_ck_ceng" name="cangku.ck_ceng" value='<s:property value="cangku.ck_ceng"/>' />
											</td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td>
												<label>其　　他:</label>
												<input type="text" id="cangku_ck_attribute1" name="cangku.ck_attribute1" value='<s:property value="cangku.ck_attribute1"/>' />
											</td>
											<td>
												<label>其　　他:</label>
												<input type="text" id="cangku_ck_attribute2" name="cangku.ck_attribute2" value='<s:property value="cangku.ck_attribute2"/>' />
											</td>
											<td>
												<label>其　　他:</label>
												<input type="text" id="cangku_ck_attribute3" name="cangku.ck_attribute3" value='<s:property value="cangku.ck_attribute3"/>' />
											</td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td>
												<label>其　　他:</label>
												<input type="text" id="cangku_ck_attribute4" name="cangku.ck_attribute4" value='<s:property value="cangku.ck_attribute4"/>' />
											</td>
											<td>
												<label>其　　他:</label>
												<input type="text" id="cangku_ck_attribute5" name="cangku.ck_attribute5" value='<s:property value="cangku.ck_attribute5"/>' />
											</td>
											<td></td>
										</tr>
										<tr bgcolor="#ffffff">
											<td></td>
											<td height="24"><a href="#" class="coolbg" onClick="form1.submit()">&nbsp; 保存&nbsp;</a>&nbsp;&nbsp;&nbsp;
												<a href="gift_cangku_list.action" class="coolbg">&nbsp; 返回&nbsp;</a>
											</td>
											<td></td>
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