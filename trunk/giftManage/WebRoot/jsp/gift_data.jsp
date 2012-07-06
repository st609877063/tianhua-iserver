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
<script type="text/javascript">
function downloadScore(){
	commPostInfo("","<%=request.getContextPath()%>/json/downloadScoreFile","","");
		document.location.href = "downAction.action";
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
			<table bgcolor="#D6D6D6" border="0" cellpadding="0" cellspacing="1" width="98%">
				<tbody>
					<tr>
						<td style="padding-left: 10px;" background="images/tbg.gif" height="24">数据导入导出</td>
					</tr>
				</tbody>
			</table>
			<table bgcolor="#cfcfcf" border="0" cellpadding="0" cellspacing="1" width="98%">
				<tbody>
					<tr bgcolor="#FFFFFF">
						<td>
							<a href="gift_data_export.action">导出</a>
						</td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td>
							<form id="gift_data_import" name="gift_data_import" action="gift_data_import.action" method="post" enctype="multipart/form-data">
								<label for="gift_data_import_action_uploadFile" class="label">选择数据文件:</label>
								<input type="file" name="uploadFile" value="" id="gift_data_import_action_uploadFile"/>
								<input type="submit" id="gift_data_import_action_submit" value="&#23548;&#20837;"/>
							</form>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>