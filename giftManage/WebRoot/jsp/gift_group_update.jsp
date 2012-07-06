<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>修改分组</title>
<link href="css/menu_base.css" rel="stylesheet" type="text/css">
<link href="css/frame.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script src="js/jquery.js" language="javascript" type="text/javascript"></script>
<script src="js/dedeajax2.js" language="javascript" type="text/javascript"></script>
<script src="js/leftmenu.js" language="javascript" type="text/javascript"></script>
<script src="js/frame.js" language="javascript" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//错误提示
		if(!<s:property value="success"/>){
			alert('<s:property value="errMsg" escape="0"/>');
		}
		
	  	$("#group_parent").val('<s:property value="gift_group.group_parent"/>');
	});
	
	function submit(){
		if($("#group_name").val()==''){
			alert("分组名不能为空!");
		}else{
			$("#form1").submit();
		}
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
							<s:form name="form1" action="gift_group_update.action" method="post">
								<s:hidden name="gift_group.group_id"/>
								<table bgcolor="#cfcfcf" border="0" cellpadding="2" cellspacing="1" width="100%">
									<tbody>
										<tr bgcolor="#E7E7E7">
											<td style="padding-left: 10px;" background="images/tbg.gif" height="24">分组信息 &gt; 修改</td>
											<td style="padding-left: 10px;" background="images/tbg.gif" height="24"></td>
											<td style="padding-left: 10px;" background="images/tbg.gif" height="24"></td>
										</tr>
									<tr bgcolor="#FFFFFF">
											<td>
												<label>分组名:</label>
												<input type="text"  name="gift_group.group_name" value='<s:property value="gift_group.group_name"/>' />
											</td>
											<td>
												<label>父分组:</label>
													<select id="group_parent" name="gift_group.group_parent">
														<option value="0">请选择父分组</option>
														<s:iterator value="gift_group_list">
															<s:if test="gift_group.group_id!=group_id">
																<option value="<s:property value='group_id'/>"><s:property value='group_name'/></option>
															</s:if>
														</s:iterator>
												</select>
											</td>
											<td>
												<label>分组描述:</label>
												<input type="text"  name="gift_group.group_desc" value='<s:property value="gift_group.group_desc"/>' />
											</td>
										</tr>
									
										<tr bgcolor="#ffffff">
											<td></td>
											<td height="24"><a href="#" class="coolbg" onClick="form1.submit()">&nbsp; 保存&nbsp;</a></td>
											<td></td>
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