<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%  
	String path = request.getContextPath();
	String basep = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

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
<script type='text/javascript' src="<%=path %>/dwr/util.js"></script>
<script type='text/javascript' src="<%=path %>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=path %>/dwr/interface/giftAjax.js"></script>
<script type="text/javascript">
function deleteCangku(Field, ck_id) {
	
	if(confirm("确定删除？")) {
		giftAjax.deleteCangkuByItemID(ck_id, function(param) {
			if(param == "SUCCESS") {
				alert("删除成功");
				var findex = getElementOrder(Field)+1;
				document.getElementById("cangkuListTable").deleteRow(findex);
				//location.reload();
			}
		}); 
	}
}

//查询出将要删除的行所在的位置index
function getElementOrder(field){
    var i = 0;
    var order = 0;
    var elements = document.getElementsByName(field.name);
    for(i=0;i<elements.length;i++){
        order++;
        if(elements[i]==field){
            break;
        }
    }
    return order;
}

function clearSearch() {
	$("#kfNo").val("");
	$("#hjNo").val("");
	$("#csNo").val("");
	$("#srhItemNo").val("");
	$("#srhItemName").val("");
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
												<tbody>
													<tr>
														<td align="center"></td>
													</tr>
												</tbody>
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
<s:form name="seach_form" action="gift_cangku_list.action" method="post">
							<table id="cangkuListTable" bgcolor="#cfcfcf" border="0" cellpadding="2" cellspacing="1" width="100%">
								<tbody>
									<tr bgcolor="#E7E7E7">
										<td colspan="11" style="padding-left: 10px;" background="images/tbg.gif" height="24">礼品信息 &gt; 仓库信息</td>
									</tr>
									<tr align="center" bgcolor="#FBFCE2" height="25">
										<td width="9%">库房号</td>
										<td width="9%">货架号</td>
										<td width="9%">层数</td>
										<td width="9%">礼品编号</td>
										<td width="10%">礼品名称</td>
										<td width="9%">其他</td>
										<td width="9%">其他</td>
										<td width="9%">其他</td>
										<td width="9%">其他</td>
										<td width="9%">其他</td>
										<td width="9%">操作</td>
									</tr>
									<s:iterator value="cangkuList" id="cangkus">
									<tr onmousemove="javascript:this.bgColor='#FCFDEE';" onmouseout="javascript:this.bgColor='#FFFFFF';" align="center" bgcolor="#FFFFFF" height="22">
										<td><s:property value="#cangkus.ck_kufang" /></td>
										<td><s:property value="#cangkus.ck_huojia" /></td>
										<td><s:property value="#cangkus.ck_ceng" /></td>
										<td><s:property value="#cangkus.ck_i_no" /></td>
										<td><s:property value="#cangkus.i_name" /></td>
										<td><s:property value="#cangkus.ck_attribute1" /></td>
										<td><s:property value="#cangkus.ck_attribute2" /></td>
										<td><s:property value="#cangkus.ck_attribute3" /></td>
										<td><s:property value="#cangkus.ck_attribute4" /></td>
										<td><s:property value="#cangkus.ck_attribute5" /></td>
										<td>
											<a href="gift_cangku_updateP.action?update_ck_id=<s:property value="#cangkus.ck_id" />">编辑</a> | <a href="javascript:void(0)" name="deleteItemButton" onclick="deleteCangku(this,'<s:property value="#cangkus.ck_id" />')">删除</a>
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
											
											<s:url id="url_page1" action="gift_cangku_list">
											<s:param name="pageNow"><s:property value="1" /></s:param>
											<s:param name="keyword"><s:property value="keyword" /></s:param>
											</s:url>
											
											<s:url id="url_page2" action="gift_cangku_list">
											<s:param name="pageNow"><s:property value="pageNow-1" /></s:param>
											<s:param name="keyword"><s:property value="keyword" /></s:param>
											</s:url>
											
											<s:url id="url_page3" action="gift_cangku_list">
											<s:param name="pageNow"><s:property value="pageNow+1" /></s:param>
											<s:param name="keyword"><s:property value="keyword" /></s:param>
											</s:url>
											
											<s:url id="url_page4" action="gift_cangku_list">
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
								</tbody>
							</table><br/>

							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr bgcolor="#EEF4EA">
									<td width="50">库房号：</td>
									<td width="60">
										<input id="kfNo" name="kfNo" type="text" value="<s:property value="kfNo" />">
									</td>
									<td width="50">货架号：</td>
									<td width="60">
										<input id="hjNo" name="hjNo" type="text" value="<s:property value="hjNo" />">
									</td>
									<td width="50">层数：</td>
									<td width="60">
										<input id="csNo" name="csNo" type="text" value="<s:property value="csNo" />">
									</td>
								</tr>
								<tr bgcolor="#EEF4EA">
									<td width="50">礼品编号：</td>
									<td width="60">
										<input id="srhItemNo" name="srhItemNo" type="text" value="<s:property value="srhItemNo" />">
									</td>
									<td width="50">礼品名称：</td>
									<td width="60">
										<input id="srhItemName" name="srhItemName" type="text" value="<s:property value="srhItemName" />">
									</td>
									<td width="50"><img src="images/button_search.gif" onClick="seach_form.submit()"/></td>
									<td width="60"><a class="coolbg" href="javascript:void(0)" onclick="clearSearch()">清空</a></td>
								</tr>
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