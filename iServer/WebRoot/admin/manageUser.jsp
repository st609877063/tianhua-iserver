<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="ctl00_Head1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="../images/Guide.css" type="text/css" rel="stylesheet" />
<link href="../images/index.css" type="text/css" rel="stylesheet" />
<link href="../images/MasterPage.css" type="text/css" rel="stylesheet" />
<link href="../images/xtree.css" type="text/css" rel="stylesheet" />
<script>
	function test(){
		alert("Forbbiden Function!");
		return false;
	}
</script>
<title>用户管理</title>
</head>
<% 
	
%>
<body id="MasterPagebody">
    <form name="aspnetForm" method="post" action="" id="aspnetForm">
        <div>  
    <div>
    <table class="user_border" cellSpacing="0" cellPadding="0" align=center style="width: 98%;" border="0" id="table1">
		<tr>
			<td vAlign="top">
			<table class="user_box" cellSpacing="0" cellPadding="5" width="100%" border="0" id="table2">
				<tr>
					<td align="left"><span style="font-size: 12pt; font-weight: bold; color: #3666AA">
        			<img src="../images/icon.gif" align="absmiddle" style="border-width:0px;" />用户管理</span></td>
					<td align="center">
					<table align="right" id="table3">
						<tr>
							<td width="80">
							<!-- 
							<a onclick="javascript:test()"><img title="添加" src="../images/add.gif" border="0"/></a>
							</td>
							 -->
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<table align=center style="width: 98%;" cellpadding="2" cellspacing="1" class="border">
        <tr>
            <td style="width: 80px" align="left" class="tdbg">
                <b>搜索选项：</b>
            </td>
            <td class="tdbg">
            <!-- 
                <select name="DrpSearchType0" size="1">
				<option selected value="1">用户ID</option>
				</select>
				
                <input name="TxtSearchKeyword" type="text" class="inputtext" />
				<input type="submit" name="BtnSearch" value="查询" id="BtnSearch" class="inputbutton" onmouseover="this.className='inputbutton_hover'" onmouseout="this.className='inputbutton'" />
			 	-->
			</td>
            <td class="tdbg"></td>
        </tr>
    </table><br />
	<table align=center style="width: 98%;" class="border" cellspacing="1" cellpadding="0" border="0" >
		<tr class="gridtitle" align="center" style="height:25px;">

			<th scope="col" style="width:10%;">用户ID</th>
			<th scope="col" style="width:10%;">注册时间</th>
			<th scope="col" style="width:10%;">管理员身份</th>
			<th scope="col" style="width:10%;">登陆次数</th>
			<th scope="col" style="width:10%;">查询次数</th>
			</tr>
			<c:set var="userList" value="<%="" %>"></c:set>
			<c:forEach var="list" items="${userList}">
			<tr class="tdbg" align="center">
				<td>${list.userid}</td>
				<td>${list.regTime}</td>
				<td>
				<c:set var="admin" value="${list.isAdmin}"></c:set>
				<c:if test="${1 eq admin}"><span style="color:red">是</span></c:if>
				<c:if test="${0 eq admin}"><span>否</span></c:if>
				</td>
				<td>${list.logintime}</td>
				<td>${list.searchtime}</td>

			
			</tr>
			</c:forEach>		
	</table>
</div>
        </div>
        <br /> 
<div>
</div></form>
</body>
</html>