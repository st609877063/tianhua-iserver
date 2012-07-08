<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%  
	String path = request.getContextPath();
	String basep = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String qrcodePath = basep + "qrcode";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title>修改礼品基本信息</title>
<link href="css/menu_base.css" rel="stylesheet" type="text/css">
<link href="css/frame.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script src="js/jquery.js" language="javascript" type="text/javascript"></script>
<script src="js/dedeajax2.js" language="javascript" type="text/javascript"></script>
<script src="js/leftmenu.js" language="javascript" type="text/javascript"></script>
<script src="js/frame.js" language="javascript" type="text/javascript"></script>

<!--  时间控件
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
 -->
<link href="css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.min.js"></script>
<script src="js/jquery-ui.min.js"></script>

<script>
	$(function() {
		$("#datepicker").datepicker();
		$("#datepicker").datepicker("option", "dateFormat", "yy-mm-dd");
		$("#datepicker").val($("#strSztimeValue").val());
	});

	function submitForm() {
		var obsubmit = true;

		/*
		var itemNum = $("#giftItems_i_num").val();
		alert(itemNum);
		if(itemNum!="" && !isDigit(itemNum)) {
			obsubmit = false;
			alert("《礼品数量》请填写数字");
			$("#giftItems_i_num").focus();
			return false;
		}
		*/
	
		if(obsubmit) {
			form1.submit();
		}
	}

	function isDigit(s) { 
		var patrn=/^[0-9]{1,20}$/; 
		if (!patrn.exec(s)) return false 
		return true 
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
	<form name="form1" action="gift_items_update.action" method="post">
		<h2 align="center">修改礼品基本信息</h2>
		<table border="0" cellpadding="0" cellspacing="0" width="98%">
			<tr>
				<td bgcolor="#FBFCE2" height="30">礼品编号:</td>
				<td height="30"><input type="text" name="giftItems.i_no" value='<s:property value="giftItems.i_no"/>' id="giftItems_i_no" readonly /></td>
				<td bgcolor="#FBFCE2" height="30">礼品名称:</td>
				<td height="30"><input type="text" name="giftItems.i_name" value='<s:property value="giftItems.i_name"/>'  id="giftItems_i_name" /></td>
				<td bgcolor="#FBFCE2" height="30">受礼人:</td>
				<td height="30">
					<s:if test="slr_list != null">
						<select name="giftItems.i_slr">
							<option value="0">请选择受礼人</option>
							<s:iterator value="slr_list">
								<s:if test="giftItems.i_slr == p_id">
									<option value='<s:property value="p_id"/>' selected="selected">
										<s:property value="p_name" />
									</option>
								</s:if>
								<s:else>
									<option value='<s:property value="p_id"/>'>
										<s:property value="p_name" />
									</option>
								</s:else>
							</s:iterator>
						</select>
					</s:if>
					<a href="#" target="_blank">添加受礼人</a>
				</td>
				<td bgcolor="#FBFCE2" height="30">赠礼人:</td>
				<td height="30">
					<s:if test="zlr_list != null">
						<select name="giftItems.i_zlr">
							<option value="0">请选择赠礼人</option>
							<s:iterator value="zlr_list">
								<s:if test="giftItems.i_zlr == p_id">
									<option value='<s:property value="p_id"/>' selected="selected">
										<s:property value="p_name" />
									</option>
								</s:if>
								<s:else>
									<option value='<s:property value="p_id"/>'>
										<s:property value="p_name" />
									</option>
								</s:else>
							</s:iterator>
						</select>
					</s:if>
					<a href="#" target="_blank">添加赠礼人</a>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FBFCE2" height="30">受赠时间:</td>
				<td height="30">
					<input type="text" id="datepicker" name="strSztime" value='<s:property value="strSztime"/>' />
					<input type="hidden" id="strSztimeValue" value='<s:property value="strSztime"/>' />
				</td>
				<td bgcolor="#FBFCE2" height="30">单　　位:</td>
				<td height="30"><input type="text" name="giftItems.i_unit" value='<s:property value="giftItems.i_unit"/>' id="giftItems_i_unit" /></td>
				<td bgcolor="#FBFCE2" height="30">数　　量:</td>
				<td height="30"><input type="text" name="giftItems.i_num" value='<s:property value="giftItems.i_num"/>'　id="giftItems_i_num" /></td>
				<td bgcolor="#FBFCE2" height="30">礼品类型:</td>
				<td height="30"><input type="text" name="giftItems.i_type" value='<s:property value="giftItems.i_type"/>' id="giftItems_i_type" /></td>
			</tr>
			<tr>
				<td bgcolor="#FBFCE2" height="30">礼品工艺:</td>
				<td height="30"><input type="text" name="giftItems.i_gongyi" value='<s:property value="giftItems.i_gongyi"/>' id="giftItems_i_gongyi" /></td>
				<td bgcolor="#FBFCE2" height="30">礼品质地:</td>
				<td height="30"><input type="text" name="giftItems.i_zhidi" value='<s:property value="giftItems.i_zhidi"/>' id="giftItems_i_zhidi" /></td>
				<td bgcolor="#FBFCE2" height="30">礼品产地:</td>
				<td height="30"><input type="text" name="giftItems.i_chandi" value='<s:property value="giftItems.i_chandi"/>' id="giftItems_i_chandi" /></td>
				<td bgcolor="#FBFCE2" height="30">赠送背景:</td>
				<td height="30"><input type="text" name="giftItems.i_background" value='<s:property value="giftItems.i_background"/>' id="giftItems_i_background" /></td>
			</tr>
			<tr>
				<td bgcolor="#FBFCE2" height="30">礼品现状:</td>
				<td height="30"><input type="text" name="giftItems.i_status" value='<s:property value="giftItems.i_status"/>' id="giftItems_i_status" /></td>
				<td bgcolor="#FBFCE2" height="30">礼品说明:</td>
				<td height="30"><input type="text" name="giftItems.i_desc" value='<s:property value="giftItems.i_desc"/>' id="giftItems_i_desc" /></td>
				<td bgcolor="#FBFCE2" height="30">礼品备注:</td>
				<td height="30"><input type="text" name="giftItems.i_memo" value='<s:property value="giftItems.i_memo"/>' id="giftItems_i_memo" /></td>
				<td bgcolor="#FBFCE2" height="30">额外属性:</td>
				<td height="30"><input type="text" name="giftItems.i_attribute1" value='<s:property value="giftItems.i_attribute1"/>' id="giftItems_i_attribute1" /></td>
			</tr>
			<tr>
				<td bgcolor="#FBFCE2" height="30">额外属性:</td>
				<td height="30"><input type="text" name="giftItems.i_attribute2" value='<s:property value="giftItems.i_attribute2"/>' id="giftItems_i_attribute2" /></td>
				<td bgcolor="#FBFCE2" height="30">额外属性:</td>
				<td height="30"><input type="text" name="giftItems.i_attribute3" value='<s:property value="giftItems.i_attribute3"/>' id="giftItems_i_attribute3" /></td>
				<td bgcolor="#FBFCE2" height="30">额外属性:</td>
				<td height="30"><input type="text" name="giftItems.i_attribute4" value='<s:property value="giftItems.i_attribute4"/>' id="giftItems_i_attribute4" /></td>
				<td bgcolor="#FBFCE2" height="30">额外属性:</td>
				<td height="30"><input type="text" name="giftItems.i_attribute5" value='<s:property value="giftItems.i_attribute5"/>' id="giftItems_i_attribute5" /></td>
			</tr>
		</table><br/>

		<div align="left">仓库信息</div>
		<input type="text" id="cangku_ck_id" name="cangku.ck_id" value='<s:property value="cangku.ck_id"/>' readonly style="display: none;" />
		<input type="text" id="cangku_i_no" name="cangku.ck_i_no" value='<s:property value="cangku.ck_i_no"/>' readonly  style="display: none;" />
		<table border="0" cellpadding="0" cellspacing="0" width="98%">
			<tr>
				<td bgcolor="#FBFCE2" height="30">库　　房:</td>
				<td height="30"><input type="text" id="cangku_ck_kufang" name="cangku.ck_kufang" value='<s:property value="cangku.ck_kufang"/>' /></td>
				<td bgcolor="#FBFCE2" height="30">货　　架:</td>
				<td height="30"><input type="text" id="cangku_ck_huojia" name="cangku.ck_huojia" value='<s:property value="cangku.ck_huojia"/>' /></td>
				<td bgcolor="#FBFCE2" height="30">　　　层:</td>
				<td height="30"><input type="text" id="cangku_ck_ceng" name="cangku.ck_ceng" value='<s:property value="cangku.ck_ceng"/>' /></td>
				<td bgcolor="#FBFCE2" height="30">其　　他:</td>
				<td height="30"><input type="text" id="cangku_ck_attribute1" name="cangku.ck_attribute1" value='<s:property value="cangku.ck_attribute1"/>' /></td>
			</tr>
			<tr>
				<td bgcolor="#FBFCE2" height="30">其　　他:</td>
				<td height="30"><input type="text" id="cangku_ck_attribute2" name="cangku.ck_attribute2" value='<s:property value="cangku.ck_attribute2"/>' /></td>
				<td bgcolor="#FBFCE2" height="30">其　　他:</td>
				<td height="30"><input type="text" id="cangku_ck_attribute3" name="cangku.ck_attribute3" value='<s:property value="cangku.ck_attribute3"/>' /></td>
				<td bgcolor="#FBFCE2" height="30">其　　他:</td>
				<td height="30"><input type="text" id="cangku_ck_attribute4" name="cangku.ck_attribute4" value='<s:property value="cangku.ck_attribute4"/>' /></td>
				<td bgcolor="#FBFCE2" height="30">其　　他:</td>
				<td height="30"><input type="text" id="cangku_ck_attribute5" name="cangku.ck_attribute5" value='<s:property value="cangku.ck_attribute5"/>' /></td>
			</tr>
		</table>

		<div align="center">
			<a href="javascript:void(0)"  class="coolbg" onclick="submitForm()"  style="font-size:20px">提交修改</a>
		</div>
		<input type="text" name="giftItems.i_qrcode" value='<s:property value="giftItems.i_qrcode"/>' readonly style="display: none;" />
	</form>

	<s:if test="giftItems.i_qrcode != null">
	<div align="left">二维码</div>
	<div align="left"><img src="<%=qrcodePath %>/<s:property value="giftItems.i_qrcode"/>"/></div>
	</s:if>
	<s:else>
	<div id="qrcode"  align="left">此二维码未生成</div>
	<div align="left">
 		<a href="gift_items_genQrcode.action?updateP_i_id=<s:property value="giftItems.i_id"/>" class="coolbg">生成二维码</a>
	</div>
	</s:else>

	</div>
</div>

</body>
</html>