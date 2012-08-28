<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>用户登录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow: hidden;
}

.STYLE3 {
	color: #528311;
	font-size: 12px;
}

.STYLE4 {
	color: #42870a;
	font-size: 12px;
}
-->
</style>
<script src="js/jquery.js" language="javascript" type="text/javascript"></script>
<script src="js/frame.js" language="javascript" type="text/javascript"></script>
<script type="text/javascript">
	function reset(){
		$("#userName").val('');
		$("#password").val('');
	}
	
	function submit(){
		//alert(document.getElementById("userName").value);
		if($("#userName").val()==''){
			alert("用户名不能为空!");
		}else if($("#password").val()==''){
			alert("密码不能为空!");
		}else{
			$("#loginForm").attr("action","login.action");
			$("#loginForm").submit();
		}
	}
	
	$(document).ready(function(){
		if(<s:property value="loginFail"/>){
			alert('<s:property value="errMsg" escape="0"/>');
		}
	});
</script>
</head>

<body>
<s:form method="post" id="loginForm" action="login">
	<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td bgcolor="#e5f6cf">&nbsp;</td>
		</tr>
		<tr>
			<td height="608" background="image/login_03.gif">
				<table width="862" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td height="266" background="image/login_04.gif">&nbsp;</td>
					</tr>
					<tr>
						<td height="95">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="424" height="95" background="image/login_06.gif">&nbsp;</td>
									<td width="183" background="image/login_07.gif">
										
											<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="21%" height="30">
														<div align="center">
															<span class="STYLE3">用户</span>
														</div>
													</td>
													<td width="79%" height="30">
														<input id="userName" type="text" name="userName" style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;">
													</td>
												</tr>
												<tr>
													<td height="30">
														<div align="center">
															<span class="STYLE3">密码</span>
														</div>
													</td>
													<td height="30">
														<input id="password" type="password" name="password" style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;">
													</td>
												</tr>
												<tr>
													<td height="30">&nbsp;</td>
													<td height="30">
														<img src="image/dl.gif" width="81" height="22" border="0" usemap="#Map">
													</td>
												</tr>
											</table>
										
									</td>
									<td width="255" background="image/login_08.gif">&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td height="247" valign="top" background="image/login_09.gif">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="22%" height="30">&nbsp;</td>
									<td width="56%">&nbsp;</td>
									<td width="22%">&nbsp;</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td height="30">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="44%" height="20">&nbsp;</td>
												<td width="56%" class="STYLE4">版本 2012V1.0</td>
											</tr>
										</table>
									</td>
									<td>&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td bgcolor="#a2d962">&nbsp;</td>
		</tr>
	</table>
	
	<map name="Map">
		<area shape="rect" coords="3,3,36,19" href="javascript:submit();">
		<area shape="rect" coords="40,3,78,18" href="javascript:reset();">
	</map>
	</s:form>
	
   	
</body>
</html>