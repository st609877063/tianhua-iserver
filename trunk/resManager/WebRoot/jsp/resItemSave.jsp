<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%  
	String path = request.getContextPath();
	String basep = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String fujianPath = basep + "fujian";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title>添加新菜品</title>
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

<script type='text/javascript' src="<%=path %>/dwr/util.js"></script>
<script type='text/javascript' src="<%=path %>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=path %>/dwr/interface/resAjax.js"></script>

<script>
$(function() {
	$("#datepicker").datepicker();
	$("#datepicker").datepicker("option", "dateFormat", "yy-mm-dd");
});

function submitForm() {
	var obsubmit = true;
	
	var itemNo = $("#giftItems_i_no").val();
	//验证DB中是否存在
	resAjax.itemNoIsExist(itemNo, function(param) {
		if(param == "NOEXIST") {
			//OK
			//其他校验，开始
			var itemNum = $("#giftItems_i_num").val(); //数量
			if(itemNum!="" && !isDigit(itemNum)) {
				obsubmit = false;
				alert("《菜品数量》请填写数字");
				$("#giftItems_i_num").focus();
				return false;
			}
		
			if(obsubmit) {
				form1.submit();
			}
			
		} else if(param == "EXIST") {
			alert("《菜品编号》重复");
			$("#giftItems_i_no").focus();
			return false;
		}
	}); 
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
	<form name="form1" action="resItemSave.action" method="post">
		<h2 align="center">添加新菜品</h2>
		<table border="0" cellpadding="2" cellspacing="1" width="98%">
		<tbody>
			<tr>
				<td bgcolor="#FBFCE2" height="30">菜品编号:</td>
				<td height="30"><input type="text" name="giftItems.i_no" value="" id="giftItems_i_no" /></td>
				<td bgcolor="#FBFCE2" height="30">菜品名称:</td>
				<td height="30"><input type="text" name="giftItems.i_name" value="" id="giftItems_i_name" /></td>
				<td bgcolor="#FBFCE2" height="30">受礼人:</td>
				<td height="30">
					<s:if test="slr_list != null">
					<select name="giftItems.i_slr">
						<option value="0">请选择受礼人</option>
						<s:iterator value="slr_list">
							<option value='<s:property value="p_id"/>'>
								<s:property value="p_name" />
							</option>
						</s:iterator>
					</select>
					</s:if>
					<a href="gift_peoples_save.action?gift_peoples.p_flag=1">添加受礼人</a>
				</td>
				<td bgcolor="#FBFCE2" height="30">赠礼人:</td>
				<td height="30">
					<s:if test="zlr_list != null">
					<select name="giftItems.i_zlr">
						<option value="0">请选择赠礼人</option>
						<s:iterator value="zlr_list">
							<option value='<s:property value="p_id"/>'>
								<s:property value="p_name" />
							</option>
						</s:iterator>
					</select>
					</s:if>
					<a href="gift_peoples_save.action?gift_peoples.p_flag=0">添加赠礼人</a>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FBFCE2" height="30">受赠时间:</td>
				<td height="30"><input type="text" id="datepicker" name="strSztime" /></td>
				<td bgcolor="#FBFCE2" height="30">单　　位:</td>
				<td height="30"><input type="text" name="giftItems.i_unit" value="" id="giftItems_i_unit" /></td>
				<td bgcolor="#FBFCE2" height="30">数　　量:</td>
				<td height="30"><input type="text" name="giftItems.i_num" value="" id="giftItems_i_num" /></td>
				<td bgcolor="#FBFCE2" height="30">菜品类型:</td>
				<td height="30"><input type="text" name="giftItems.i_type" value=""　id="giftItems_i_type" /></td>
			</tr>
			<tr>
				<td bgcolor="#FBFCE2" height="30">菜品工艺:</td>
				<td height="30"><input type="text" name="giftItems.i_gongyi" value=""　id="giftItems_i_gongyi" /></td>
				<td bgcolor="#FBFCE2" height="30">菜品质地:</td>
				<td height="30"><input type="text" name="giftItems.i_zhidi" value="" id="giftItems_i_zhidi" /></td>
				<td bgcolor="#FBFCE2" height="30">菜品产地:</td>
				<td height="30"><input type="text" name="giftItems.i_chandi" value="" id="giftItems_i_chandi" /></td>
				<td bgcolor="#FBFCE2" height="30">赠送背景:</td>
				<td height="30"><input type="text" name="giftItems.i_background" value="" id="giftItems_i_background" /></td>
			</tr>
			<tr>
				<td bgcolor="#FBFCE2" height="30">菜品现状:</td>
				<td height="30"><input type="text" name="giftItems.i_status" value="" id="giftItems_i_status" /></td>
				<td bgcolor="#FBFCE2" height="30">菜品说明:</td>
				<td height="30"><input type="text" name="giftItems.i_desc" value=""　id="giftItems_i_desc" /></td>
				<td bgcolor="#FBFCE2" height="30">菜品备注:</td>
				<td height="30"><input type="text" name="giftItems.i_memo" value=""　id="giftItems_i_memo" /></td>
				<td bgcolor="#FBFCE2" height="30">额外属性:</td>
				<td height="30"><input type="text" name="giftItems.i_attribute1" value=""　id="giftItems_i_attribute1" /></td>
			</tr>
			<tr>
				<td bgcolor="#FBFCE2" height="30">额外属性:</td>
				<td height="30"><input type="text" name="giftItems.i_attribute2" value=""　id="giftItems_i_attribute2" /></td>
				<td bgcolor="#FBFCE2" height="30">额外属性:</td>
				<td height="30"><input type="text" name="giftItems.i_attribute3" value=""　id="giftItems_i_attribute3" /></td>
				<td bgcolor="#FBFCE2" height="30">额外属性:</td>
				<td height="30"><input type="text" name="giftItems.i_attribute4" value="" id="giftItems_i_attribute4" /></td>
				<td bgcolor="#FBFCE2" height="30">额外属性:</td>
				<td height="30"><input type="text" name="giftItems.i_attribute5" value="" id="giftItems_i_attribute5" /></td>
			</tr>
		</tbody>
		</table><br/>

		<div align="left">仓库信息</div>
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
			<a href="javascript:void(0)"  class="coolbg" onclick="submitForm()"  style="font-size:20px">保存基本信息，开始添加附件。</a>
		</div>

	</form>
	</div>
</div>

</body>
</html>