<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户列表</title>
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


function del_user(user_id){
	if(confirm("确定要删除此用户?")){
		$("#form1").attr("action","gift_user_remove.action?gift_user.user_id="+user_id);
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
							<table bgcolor="#cfcfcf" border="0" cellpadding="2"
								cellspacing="1" width="100%">
									<tr bgcolor="#E7E7E7">
										<td colspan="16" style="padding-left: 10px;"
											background="images/tbg.gif" height="24">分类信息 &gt;用户列表</td>
									</tr>
								<tr align="center" bgcolor="#FBFCE2" height="25">
									
									<td width="9%">
										用户名
									</td>
									<td width="9%">
										昵称
									</td>
									<td width="9%">
										照片
									</td>
									<td width="9%">
										头衔
									</td>
									<td width="9%">
										用户类型
									</td>
									<td width="9%">
										系统使用者类型
									</td>
									<td width="9%">
										是否关闭
									</td>
									<td width="9%">
										所在分组
									</td>
									<td width="9%">
										创建时间
									</td>
									<td width="8">
										创建人
									</td>
									<td width="11%">
										管理
									</td>
									
								</tr>
								<s:if test="gift_user_list!=null&&!gift_user_list.isEmpty()">
								<s:iterator value="gift_user_list" id="items">
								<tr>
								
									<td height="18" bgcolor="#FFFFFF" class="STYLE2">
										<div align="center" class="STYLE2 STYLE1"><s:property value="user_name" /></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><s:property value="nick_name" /></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><img src="<s:property value="user_img" />"/></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><s:property value="user_title" /></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><s:property value="user_type_show" /></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><s:property value="user_admin_show" /></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><s:property value="is_close_show" /></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><a href="gift_group_user_list.action?list_user_id=<s:property value="user_id" />">[查看]</a></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><s:property value="create_time_show" /></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><s:property value="add_user_name" /></div>
									</td>
								
							
									<td height="18" bgcolor="#FFFFFF">
										<div align="center">
											<span class="STYLE2"><img src="image/037.gif" width="9" height="9" /></span>
											<span class="STYLE1">[</span>
											
											<a href="gift_user_updateP.action?update_user_id=<s:property value="user_id"/>">修改</a>
											
											<span class="STYLE1">]</span>
											<span class="STYLE2"><img src="image/010.gif" width="9" height="9" /></span>
											<span class="STYLE1">[</span><a href="javascript:del_user(<s:property value="user_id"/>);">删除</a><span class="STYLE1">]</span>
										</div>
									</td>
								</tr>
								</s:iterator>
								<tr align="right" bgcolor="#F9FCEF">
										<td colspan="11" align="center" height="20">
共
<s:property value="rowCount" />
条纪录，当前第
<s:property value="pageNow" />
/
<s:property value="pageCount" />
页    

<s:url id="url_page1" action="gift_user_list">
<s:param name="pageNow"><s:property value="1" /></s:param>
<s:param name="keyword"><s:property value="keyword" /></s:param>
</s:url>

<s:url id="url_page2" action="gift_user_list">
<s:param name="pageNow"><s:property value="pageNow-1" /></s:param>
<s:param name="keyword"><s:property value="keyword" /></s:param>
</s:url>

<s:url id="url_page3" action="gift_user_list">
<s:param name="pageNow"><s:property value="pageNow+1" /></s:param>
<s:param name="keyword"><s:property value="keyword" /></s:param>
</s:url>

<s:url id="url_page4" action="gift_user_list">
<s:param name="pageNow"><s:property value="pageCount" /></s:param>
<s:param name="keyword"><s:property value="keyword" /></s:param>
</s:url>

<s:if test="pageNow==1">
<span>首页</span>&nbsp;<span>上一页</span>
</s:if><s:else>
<s:a href="%{url_page1}"><span>首页</span></s:a>
<s:a href="%{url_page2}"><span>上一页</span></s:a>
</s:else>

<s:if test="pageNow==pageCount">
<span>下一页</span>&nbsp;<span>末页</span>
</s:if><s:else> 
<s:a href="%{url_page3}">下一页</s:a>
<s:a href="%{url_page4}">末页</s:a>
</s:else>


										</td>
									</tr>
								</s:if>
								<s:else>
								<tr>
									<td height="18" bgcolor="#FFFFFF" colspan="16">
										<div align="center">没有用户信息!</div>
									</td>
								</tr>
								</s:else>
							</table>
							</s:form>
						<s:form id="search_form" method="post" action="gift_user_list.action">
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tbody>
									<tr>
										<td height="4"></td>
									</tr>
									<tr bgcolor="#FFFFFF">
										<td height="26">
											<table bgcolor="#cfcfcf" border="0" cellpadding="1"
												cellspacing="1" width="100%">
												<tbody>
													<tr bgcolor="#EEF4EA">
														<td background="images/wbg.gif">
															<table border="0" cellpadding="0" cellspacing="0" width="600">
																<tbody>
																	<tr>
																		<td width="70">关键字：</td>
																		<td width="160">
																			<input id="keyword" name="keyword" type="text" value="<s:property value="keyword" />">
																		</td>
																		<td>
																			<img src="images/button_search.gif" onClick="search_form.submit()"/>
																		</td>
																	</tr>
																</tbody>
															</table>
														</td>
													</tr>
												</tbody>
											</table></td>
									</tr>
									<tr>
										<td colspan="2" height="4"></td>
									</tr>
								</tbody>
							</table>
							</s:form></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>