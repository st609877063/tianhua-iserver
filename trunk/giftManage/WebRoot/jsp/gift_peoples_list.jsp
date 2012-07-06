<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><s:if test="gift_peoples.p_flag==1">受礼人</s:if><s:elseif test="gift_peoples.p_flag==0">赠礼人</s:elseif>列表</title>
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

function del_peoples(p_id){
	if(confirm("确定要删除此人物?")){
		$("#form1").attr("action","gift_peoples_remove.action?gift_peoples.p_id="+p_id);
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
						<s:form id="form1" method="post">
							<s:hidden name="gift_peoples.p_flag"></s:hidden>
							<table bgcolor="#cfcfcf" border="0" cellpadding="2"
								cellspacing="1" width="100%">
									<tr bgcolor="#E7E7E7">
										<td colspan="16" style="padding-left: 10px;"
											background="images/tbg.gif" height="24">分类信息 &gt;<s:if test="gift_peoples.p_flag==1">受礼人信息</s:if><s:elseif test="gift_peoples.p_flag==0">赠礼人信息</s:elseif></td>
									</tr>
								<tr align="center" bgcolor="#FBFCE2" height="25">
									
									<td width="6.5%">
										姓名
									</td>
									<td width="6.5%">
										类型
									</td>
									<td width="6.5%">
										配偶姓名
									</td>
								
									<td width="6.5%">
										国家
									</td>
									<td width="6.5%">
										部门
									</td>
									<td width="6.5%">
										头衔
									</td>
									<td width="6.5%">
										其他
									</td>
									<td width="6.5%">
										其他
									</td>
									<td width="6.5%">
										其他
									</td>
									<td width="6.5%">
										其他
									</td>
									<td width="6.5%">
										其他
									</td>
									<td width="10%">
										创建时间
									</td>
									<td width="6.5%">
										创建人
									</td>
									
									<td width="12%">
										管理
									</td>
									
								</tr>
								<s:if test="gift_peoples_list!=null&&!gift_peoples_list.isEmpty()">
								<s:iterator value="gift_peoples_list" id="items">
								<tr>
									
									<td height="18" bgcolor="#FFFFFF" class="STYLE2">
										<div align="center" class="STYLE2 STYLE1"><s:property value="p_name" /></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><s:property value="p_type_show" /></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><s:property value="p_spouse" /></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><s:property value="p_country" /></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><s:property value="p_bm" /></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><s:property value="p_title" /></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><s:property value="p_attribute1" /></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><s:property value="p_attribute2" /></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><s:property value="p_attribute3" /></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><s:property value="p_attribute4" /></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><s:property value="p_attribute5" /></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><s:property value="p_createtime_show" /></div>
									</td>
									<td height="18" bgcolor="#FFFFFF">
										<div align="center" class="STYLE2 STYLE1"><s:property value="p_adduser_name"/></div>
									</td>
							
									<td height="18" bgcolor="#FFFFFF">
										<div align="center">
											<span class="STYLE2"><img src="image/037.gif" width="9" height="9" /></span>
											<span class="STYLE1">[</span>
											
											<a href="gift_peoples_updateP.action?update_peoples_id=<s:property value="p_id"/>&gift_peoples.p_flag=<s:property value="gift_peoples.p_flag" />">修改</a>
											
											<span class="STYLE1">]</span>
											<span class="STYLE2"><img src="image/010.gif" width="9" height="9" /></span>
											<span class="STYLE1">[</span><a href="javascript:del_peoples(<s:property value="p_id"/>);">删除</a><span class="STYLE1">]</span>
										</div>
									</td>
								</tr>
								</s:iterator>
								<tr align="right" bgcolor="#F9FCEF">
										<td colspan="15" align="center" height="20">
共
<s:property value="rowCount" />
条纪录，当前第
<s:property value="pageNow" />
/
<s:property value="pageCount" />
页    

<s:url id="url_page1" action="gift_peoples_list">
<s:param name="pageNow"><s:property value="1" /></s:param>
<s:param name="keyword"><s:property value="keyword" /></s:param>
<s:param name="gift_peoples.p_flag"><s:property value="gift_peoples.p_flag" /></s:param>
</s:url>

<s:url id="url_page2" action="gift_peoples_list">
<s:param name="pageNow"><s:property value="pageNow-1" /></s:param>
<s:param name="keyword"><s:property value="keyword" /></s:param>
<s:param name="gift_peoples.p_flag"><s:property value="gift_peoples.p_flag" /></s:param>
</s:url>

<s:url id="url_page3" action="gift_peoples_list">
<s:param name="pageNow"><s:property value="pageNow+1" /></s:param>
<s:param name="keyword"><s:property value="keyword" /></s:param>
<s:param name="gift_peoples.p_flag"><s:property value="gift_peoples.p_flag" /></s:param>
</s:url>

<s:url id="url_page4" action="gift_peoples_list">
<s:param name="pageNow"><s:property value="pageCount" /></s:param>
<s:param name="keyword"><s:property value="keyword" /></s:param>
<s:param name="gift_peoples.p_flag"><s:property value="gift_peoples.p_flag" /></s:param>
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
										<div align="center">没有人物信息!</div>
									</td>
								</tr>
								</s:else>
							</table>
							</s:form>
							<s:form id="search_form" method="post" action="gift_peoples_list.action">
								<s:hidden name="gift_peoples.p_flag"/>
								<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tbody>
									<tr><td height="4"></td></tr>
									<tr bgcolor="#FFFFFF">
										<td height="26">
											<table bgcolor="#cfcfcf" border="0" cellpadding="1" cellspacing="1" width="100%">
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
											</table>
										</td>
									</tr>
									<tr>
										<td colspan="2" height="4"></td>
									</tr>
								</tbody>
							</table>
						</s:form>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>