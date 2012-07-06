<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link href="css/menu_base.css" rel="stylesheet" type="text/css">
<link href="css/frame.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script src="js/jquery.js" language="javascript" type="text/javascript"></script>
<script src="js/dedeajax2.js" language="javascript"
	type="text/javascript"></script>
<script src="js/leftmenu.js" language="javascript"
	type="text/javascript"></script>
<script src="js/frame.js" language="javascript" type="text/javascript"></script>
<script type="text/javascript">


function del_group_user(gu_id,list_user_id){
	if(confirm("确定要删除此用户组?")){
		$("#form1").attr("action","gift_group_user_remove.action?gift_group_user.gu_id="+gu_id+"&list_user_id="+list_user_id);
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
		<div class="main" style="width: 100%" align="center">
			<table align="center" border="0" cellpadding="0" cellspacing="0"
				width="100%">
				<tbody>
					<tr>
						<td align="center" valign="top">
							<table bgcolor="#D6D6D6" border="0" cellpadding="0"
								cellspacing="1" width="100%">
								<tbody>
									<tr>
										<td background="images/newlinebg3.gif" height="26">
											<table border="0" cellpadding="0" cellspacing="0" width="100%">
												<tbody>
												
												</tbody>
											</table></td>
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
	<s:form id="form1" method="post">						
			<s:hidden name="pageNow"/>
							<table bgcolor="#cfcfcf" border="0" cellpadding="2"
								cellspacing="1" width="100%">
									<tr bgcolor="#E7E7E7">
										<td colspan="16" style="padding-left: 10px;"
											background="images/tbg.gif" height="24">分类信息 &gt;用户组列表</td>
									</tr>
								<tr align="center" bgcolor="#FBFCE2" height="25">
								
									<td width="20%">
										分组名
									</td>
									<td width="20%">
										用户名
									</td>
									<td width="20%">
										创建时间
									</td>
									<td width="20%">
										创建用户
									</td>
									<td width="20%">
										管理
									</td>
								</tr>
								<s:if test="gift_group_user_list!=null&&!gift_group_user_list.isEmpty()">
								<s:iterator value="gift_group_user_list" id="items">
								<tr>
									
									<td height="18" bgcolor="#FFFFFF" class="STYLE2">
										<div align="center" class="STYLE2 STYLE1"><s:property value="group_name" /></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><s:property value="user_name" /></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><s:property value="create_time_show" /></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><s:property value="add_user_name" /></div>
									</td>
							
									<td height="18" bgcolor="#FFFFFF">
										<div align="center">
											<span class="STYLE2"><img src="image/010.gif" width="9" height="9" /></span>
											<span class="STYLE1">[</span><a href="javascript:del_group_user(<s:property value="gu_id"/>,<s:property value="list_user_id"/>);">删除</a><span class="STYLE1">]</span>
										</div>
									</td>
								</tr>
								</s:iterator>
								
								</s:if>
								<s:else>
								<tr>
									<td height="18" bgcolor="#FFFFFF" colspan="16">
										<div align="center">没有分组信息!</div>
									</td>
								</tr>
								</s:else>
							</table>
					</s:form>
			
					</tr>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>