<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>添加<s:if test="gift_peoples.p_flag==1">受礼人</s:if><s:elseif test="gift_peoples.p_flag==0">赠礼人</s:elseif></title>
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
		
	  	$("#p_type").val('<s:property value="gift_peoples.p_type"/>');
	  	
	 	//初始化人物所在的部门
	  	var p_bm = '<s:property value="gift_peoples.p_bm"/>';
	  	//alert(p_bm);
	  	if(p_bm!=null&&p_bm!=''){
			var bmIdArr = p_bm.split("#");
			$("[name='gift_peoples.p_bm']").each(function() {
				for ( var i = 0; i < bmIdArr.length; i++) {
					if ($(this).attr("id") == bmIdArr[i]) {
						$(this).attr("checked", 'true');
					}
				}
			});
	  	}
	});
	
	function submit(){
	
		if($("#p_name").val()==''){
			alert("人物姓名不能为空!");
		}else if($("#p_type").val()==''){
			alert("请选择人物类型!");
		}else if($("#p_spouse").val()==''){
			alert("人物配偶不能为空!");
		}else if($("#p_country").val()==''){
			alert("人物国家不能为空!");
		}else if($("#p_title").val()==''){
			alert("人物头衔不能为空!");
		}else if((<s:property value="gift_peoples.p_flag"/>==1&&$("input:checked[name=gift_peoples.p_bm]").length==0)||(<s:property value="gift_peoples.p_flag"/>==0&&$("#p_bm").val()=='')){
			alert("人物部门不能为空!");
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
							<form id="form1" action="gift_peoples_save.action" method="post">
								<s:hidden name="gift_peoples.p_flag"/>
								<table bgcolor="#cfcfcf" border="0" cellpadding="2" cellspacing="1" width="100%">
									<tbody>
										<tr bgcolor="#E7E7E7">
											<td style="padding-left: 10px;" background="images/tbg.gif" height="24" width="33.4%"><s:if test="gift_peoples.p_flag==1">受礼人信息</s:if><s:elseif test="gift_peoples.p_flag==0">赠礼人信息</s:elseif> &gt; 添加</td>
											<td style="padding-left: 10px;" background="images/tbg.gif" height="24" width="33.3%"></td>
											<td style="padding-left: 10px;" background="images/tbg.gif" height="24"></td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td>
												<label>名　　字:</label>
												<input type="text" id="p_name"  name="gift_peoples.p_name" value='<s:property value="gift_peoples.p_name"/>' />
											</td>
											<td>
												<label>类　　型:</label>
												<select id="p_type" name="gift_peoples.p_type">
													<option value="0">本人</option>
													<option value="1">夫人</option>
													<option value="2">夫妇</option>
												</select>
											</td>
											<td>
												<label>配偶姓名:</label>
												<input type="text" id="p_spouse" name="gift_peoples.p_spouse" value='<s:property value="gift_peoples.p_spouse"/>' />
											</td>
										</tr>
										<tr bgcolor="#FFFFFF"><%--
											<td>
												<label>标　　识:</label>
													<select id="p_flag" name="gift_peoples.p_flag">
													<option value="0">赠礼人</option>
													<option value="1">受礼人</option>
												</select>
											</td>
											--%><td>
												<label>国　　家:</label>
												<input type="text" id="p_country" name="gift_peoples.p_country" value='<s:property value="gift_peoples.p_country"/>' />
											</td>
											<td>
												<label>头　　衔:</label>
												<input type="text" id="p_title" name="gift_peoples.p_title" value='<s:property value="gift_peoples.p_title"/>' />
											</td>
											<td><s:if test="gift_peoples.p_flag==0"><label>部　　门:</label>
												<input type="text" id="p_bm" name="gift_peoples.p_bm" value='<s:property value="gift_peoples.p_bm"/>' />
											</s:if></td>
										</tr>
										<tr bgcolor="#FFFFFF">
											<td>
												<label>其　　他:</label>
												<input type="text"  name="gift_peoples.p_attribute1" value='<s:property value="gift_peoples.p_attribute1"/>' />
											</td>
											<td>
												<label>其　　他:</label>
												<input type="text"  name="gift_peoples.p_attribute2" value='<s:property value="gift_peoples.p_attribute2"/>' />
											</td>
											<td>
												<label>其　　他:</label>
												<input type="text"  name="gift_peoples.p_attribute3" value='<s:property value="gift_peoples.p_attribute3"/>' />
											</td>
											
										</tr>
										<tr bgcolor="#FFFFFF">
											<td>
												<label>其　　他:</label>
												<input type="text"  name="gift_peoples.p_attribute4" value='<s:property value="gift_peoples.p_attribute4"/>' />
											</td>
											<td>
												<label>其　　他:</label>
												<input type="text"  name="gift_peoples.p_attribute5" value='<s:property value="gift_peoples.p_attribute5"/>' />
											</td>
											<td></td>
										</tr>
										<tr bgcolor="#ffffff">
											<td></td>
											<td height="24"><s:if test="gift_peoples.p_flag==0"><a href="#" class="coolbg" onClick="submit()">&nbsp; 保存&nbsp;</a></s:if></td>
											<td></td>
										</tr>
									</tbody>
								</table>
								<!-- 如果是受礼人(p_flag==1),需要从列表中选择部门 -->
								<s:if test="gift_peoples.p_flag==1">
									<table bgcolor="#cfcfcf" border="0" cellpadding="2" cellspacing="1" width="100%">
										<tbody>
											<tr bgcolor="#E7E7E7">
												<td style="padding-left: 10px;" background="images/tbg.gif" height="24" width="33.4%">部门列表</td>
												<td style="padding-left: 10px;" background="images/tbg.gif" height="24" width="33.3%"></td>
												<td style="padding-left: 10px;" background="images/tbg.gif" height="24"></td>
											</tr>
										
											
											<s:iterator value="gift_code_list" status="count">
												<s:if test="#count.index%3==0">
													<tr bgcolor="#FFFFFF">
												</s:if>
												<td>
													<input id="<s:property value="pk_id"/>" type="checkbox"
															name="gift_peoples.p_bm" value="<s:property value='pk_id'/>" >
													
													<s:property value='name' />
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
								</s:if>
							</form>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>