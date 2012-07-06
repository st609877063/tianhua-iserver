<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>添加用户</title>
<link href="css/menu_base.css" rel="stylesheet" type="text/css">
<link href="css/frame.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script src="js/jquery.js" language="javascript" type="text/javascript"></script>
<script src="js/dedeajax2.js" language="javascript" type="text/javascript"></script>
<script src="js/leftmenu.js" language="javascript" type="text/javascript"></script>
<script src="js/frame.js" language="javascript" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function(){
		if(!<s:property value="success"/>){
			alert('<s:property value="errMsg" escape="0"/>');
		}
		
		//初始化下拉列表
	  	$("#user_type").val('<s:property value="gift_user.user_type"/>');
	  	$("#user_admin").val('<s:property value="gift_user.user_admin"/>');
	  	$("#is_close").val('<s:property value="gift_user.is_close"/>');
	  	
	  	//初始化用户所在的分组
	  	var groupIdStr = '<s:property value="gift_user.groupIdStr"/>';
	  	if(groupIdStr!=null&&groupIdStr!=''){
			var groupIdArr = groupIdStr.split("#");
			$("[name='gift_user.groupIdStr']").each(function() {
				for ( var i = 0; i < groupIdArr.length; i++) {
					if ($(this).attr("id") == groupIdArr[i]) {
						$(this).attr("checked", 'true');
					}
				}
			});
	  	}
	});
	
	function submit(){
		if($("#user_name").val()==''){
			alert("用户名不能为空!");
		}else if($("#password").val()==''){
			alert("密码不能为空!");
		}else if($("#nick_name").val()==''){
			alert("用户昵称不能为空!");
		}else if($("#user_img").val()==''){
			alert("用户头像不能为空!");
		}else if($("#user_title").val()==''){
			alert("用户头衔不能为空!");
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
							<form id="form1" action="gift_user_save.action" method="post">
								<table bgcolor="#cfcfcf" border="0" cellpadding="2" cellspacing="1" width="100%">
									<tbody>
										<tr bgcolor="#E7E7E7">
											<td style="padding-left: 10px;" background="images/tbg.gif" height="24" width="33.4%">人物信息 &gt; 添加</td>
											<td style="padding-left: 10px;" background="images/tbg.gif" height="24" width="33.3%"></td>
											<td style="padding-left: 10px;" background="images/tbg.gif" height="24"></td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td>
												<label>名　　字:</label>
												<input type="text" id="user_name" name="gift_user.user_name" value='<s:property value="gift_user.user_name"/>' />
											</td>
											<td>
												<label>密　　码:</label>
												<input type="password" id="password"  name="gift_user.password" value=''/>
											</td>
											<td>
												<label>昵　　称:</label>
												<input type="text" id="nick_name"  name="gift_user.nick_name" value='<s:property value="gift_user.nick_name"/>'/>
											</td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td>
												<label>头　　像:</label>
												<input type="text" id="user_img"  name="gift_user.user_img" value='<s:property value="gift_user.user_img"/>'/>
											</td>
											<td>
												<label>头　　衔:</label>
												<input type="text" id="user_title"  name="gift_user.user_title" value='<s:property value="gift_user.user_title"/>'/>
											</td>
											<td>
												<label>用户类型:</label>
												<select id="user_type" name="gift_user.user_type">
													<option value="0">接受礼品者</option>
													<option value="1">系统使用者</option>
													<option value="2">两者都是</option>
												</select>
											</td>
										
										</tr>
										<tr bgcolor="#FFFFFF">
											<td>
												<label>管理类型:</label>
												<select id="user_admin" name="gift_user.user_admin">
													<option value="1">超级管理员</option>
													<option value="2">一般管理员</option>
													<option value="3">普通用户</option>
												</select>
											</td>
											<td>
												<label>是否关闭:</label>
												<select id="is_close" name="gift_user.is_close">
													<option value="0">开启</option>
													<option value="1">关闭</option>
												</select>
											</td>
											<td>
											</td>
											<td></td>
										</tr><tr bgcolor="#ffffff">
											<td height="24" colspan="3"></td>
										</tr>
									</tbody>
								</table>
								
								<table bgcolor="#cfcfcf" border="0" cellpadding="2" cellspacing="1" width="100%">
									<tbody>
										<tr bgcolor="#E7E7E7">
											<td style="padding-left: 10px;" background="images/tbg.gif" height="24" width="33.4%">分组列表</td>
											<td style="padding-left: 10px;" background="images/tbg.gif" height="24" width="33.3%"></td>
											<td style="padding-left: 10px;" background="images/tbg.gif" height="24"></td>
										</tr>
									
										
										<s:iterator value="gift_group_list" status="count">
										<s:if test="#count.index%3==0">
											<tr bgcolor="#FFFFFF">
										</s:if>
										<td>
											<input id="<s:property value="group_id"/>" type="checkbox"
													name="gift_user.groupIdStr" value="<s:property value='group_id'/>" >
											
											<s:property value='group_name' />
										</td>
										<s:if test="#count.last">
											<s:if test="#count.index%3==0">
												<td></td><td></td>
												</tr>
											</s:if>
											<s:elseif test="#count.index%3==1">
												<td></td>
												</tr>
											</s:elseif>
										</s:if>
										<s:elseif test="#count.index%3==2">
											</tr>
										</s:elseif>
										</s:iterator>
										
										<tr bgcolor="#ffffff">
											<td></td>
											<td height="24"><a href="#" class="coolbg" onClick="submit()">&nbsp; 保存&nbsp;</a></td>
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