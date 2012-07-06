<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link type="text/css" rel="stylesheet" href="css/menu_base.css">
<link type="text/css" rel="stylesheet" href="css/frame.css">
<link type="text/css" rel="stylesheet" href="css/main.css">
<script type="text/javascript" language="javascript" src="js/jquery.js"></script>
<script type="text/javascript" language="javascript" src="js/dedeajax2.js"></script>
<script type="text/javascript" language="javascript" src="js/leftmenu.js"></script>
<script type="text/javascript" language="javascript" src="js/frame.js"></script>
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

<form name="form1" action="gift_lightbox_showP.action" method="post">
			<table bgcolor="#D6D6D6" border="0" cellpadding="0" cellspacing="1" width="98%">
				<tbody>
					<tr>
						<td style="padding-left: 10px;" background="images/tbg.gif" height="24">选择生成图册条件</td>
					</tr>
				</tbody>
			</table>
			<table bgcolor="#cfcfcf" border="0" cellpadding="0" cellspacing="1" width="98%">
				<tbody>
					<tr bgcolor="#FFFFFF">
						<td>
							<input type="radio" name="radio" value="1"><label> 全部</label>
							<br />
							<input type="radio" name="radio" value="2"><label> 中央政治局常务委员会委员</label>
							<br />
							<input type="radio" name="radio" value="3"><label> 中央政治局委员</label>
							<br />
							<input type="radio" name="radio" value="4" ><label> 中央书记处书记</label>
							<br />
							<input type="radio" name="radio" value="5"><label> 政治局和书记处</label>
							<br />
							<input type="radio" name="radio" value="6"><label> 四副两高</label>
							<br />
							<input type="radio" name="radio" value="7"><label> 历届党和国家领导人</label>
							<br />
							<input type="radio" name="radio" value="8"><label> 单人</label>
							<select name="select">
								<s:iterator value="gift_peoples_list">
								<option value ="<s:property value="p_id"/>"><s:property value="p_name"/></option>
								</s:iterator>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
			<table bgcolor="#D6D6D6" border="0" cellpadding="0" cellspacing="1" width="98%">
				<tbody>
					<tr>
						<td align="center" background="images/tbg.gif" height="24">
							<a href="#" onclick="form1.submit()">查看图册</a>
						</td>
					</tr>
				</tbody>
			</table>
</form>

			<!-- 		
							
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="98%">
				<tbody>
					<tr>
						<td align="center" valign="top">
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tbody>
									<tr bgcolor="#FFFFFF">
										<td height="4"></td>
									</tr>
								</tbody>
							</table>
							<table bgcolor="#D6D6D6" border="0" cellpadding="0" cellspacing="1" width="100%">
								<tbody>
									<tr>
										<td background="images/newlinebg3.gif" height="26">
											<table border="0" cellpadding="0" cellspacing="0" width="98%">
												<tbody>
													<tr>
														<td style="padding-left: 10px;" background="images/tbg.gif" height="24">选择图册</td>
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
								</tbody>
							</table>
							<table bgcolor="#D6D6D6" border="0" cellpadding="3" cellspacing="1" width="100%">
								<tbody>
									<tr>
										<td>
											<div style="border: 1px;border-color: red;">111</div>
											<br />
											222
										</td>
									</tr>
								</tbody>
							</table>
							
							<form name="form2" action="gift_cangku_update.action" method="post">
								<table bgcolor="#cfcfcf" border="0" cellpadding="2" cellspacing="1" width="100%">
									<tbody>
										<tr bgcolor="#E7E7E7">
											<td style="padding-left: 10px;" background="images/tbg.gif" height="24">礼品图册 &gt; 选择</td>
											<td style="padding-left: 10px;" background="images/tbg.gif" height="24"></td>
											<td style="padding-left: 10px;" background="images/tbg.gif" height="24"></td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td>
												<label>礼品编号:</label>
												<input type="text" id="cangku_i_no" name="cangku.ck_i_no" value='' readonly />
											</td>
											<td>
												<label>礼品名称:</label>
												<input type="text" id="cangku_i_name" name="cangku.i_name" value='' readonly />
											</td>
											<td>
												<input type="text" id="cangku_ck_id" name="cangku.ck_id" value='' readonly style="display: none;" />
											</td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td>
												<label>库　　房:</label>
												<input type="text" id="cangku_ck_kufang" name="cangku.ck_kufang" value='' />
											</td>
											<td>
												<label>货　　架:</label>
												<input type="text" id="cangku_ck_huojia" name="cangku.ck_huojia" value='' />
											</td>
											<td>
												<label>　　　层:</label>
												<input type="text" id="cangku_ck_ceng" name="cangku.ck_ceng" value='' />
											</td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td>
												<label>其　　他:</label>
												<input type="text" id="cangku_ck_attribute1" name="cangku.ck_attribute1" value='' />
											</td>
											<td>
												<label>其　　他:</label>
												<input type="text" id="cangku_ck_attribute2" name="cangku.ck_attribute2" value='' />
											</td>
											<td>
												<label>其　　他:</label>
												<input type="text" id="cangku_ck_attribute3" name="cangku.ck_attribute3" value='' />
											</td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td>
												<label>其　　他:</label>
												<input type="text" id="cangku_ck_attribute4" name="cangku.ck_attribute4" value='' />
											</td>
											<td>
												<label>其　　他:</label>
												<input type="text" id="cangku_ck_attribute5" name="cangku.ck_attribute5" value='' />
											</td>
											<td></td>
										</tr>
										<tr bgcolor="#ffffff">
											<td><a href="#" class="coolbg" onClick="form1.submit()">&nbsp; 保存&nbsp;</a></td>
										</tr>
									</tbody>
								</table>
							</form>
						</td>
					</tr>
				</tbody>
			</table>
			 -->
		</div>
	</div>
</body>
</html>