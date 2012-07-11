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
<title>欢迎订餐</title>


<link href="kfc/common.css" rel="stylesheet" type="text/css">
<link href="kfc/public.css" rel="stylesheet" type="text/css">
<link href="kfc/new.css" rel="stylesheet" type="text/css">
<link href="kfc/new.css" rel="stylesheet" type="text/css">
<link href="kfc/jquery.loadmask.css" rel="stylesheet" type="text/css">

<script src="js/jquery.js" language="javascript" type="text/javascript"></script>
<script src="js/dedeajax2.js" language="javascript" type="text/javascript"></script>
<script src="js/leftmenu.js" language="javascript" type="text/javascript"></script>
<script src="js/frame.js" language="javascript" type="text/javascript"></script>

<script type='text/javascript' src="<%=path%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=path%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=path%>/dwr/interface/resAjax.js"></script>

<!--  时间控件 -->
<link href="css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.min.js"></script>
<script src="js/jquery-ui.min.js"></script>


<script>

$(function() {
	var orderResult = $("#orderResult").val();
	if(orderResult != "") {
		if(orderResult == "failed") {
			alert("订单提交失败，请重新选择提交");
		} else {
			alert("订单提交成功！订单号为："+orderResult);
		}
	}
	
});

</script>

<style type="text/css">
.main li {
	float: left;
	border: #FBE8C0 1px solid;
	width: 160px;
	height: 280px;
	text-align: left;
	background: #FFFDE4;
	margin-right: 2px;
	margin-bottom: 5px;
	-margin-bottom: 10px;
	background: #FFFDE4;
	overflow: hidden;
}
</style>

</head>

<body>

<input type="hidden" name="orderResult" id="orderResult" value="<s:property value="orderResult"/>">

<div class="public_container" id="public_container">
<div style="display: block;height: 60px">
<div class="header_box clearfix">
	<h1 class="kfcLogo">
		<a href="javascript:void(0);">网上订餐</a>
	</h1>
	<div class="headLeft">
		<div class="tfav">
			<img src="kfc/fav.gif">
		</div>
		<p><span>欢迎网上订餐！ Welcome to order online!</span>建议使用IE 6.0版本以上浏览本网站</p>
		<ul class="clearfix">
			<li class="m1">
				<a href="javascript:void(0);" class="on"><strong>首页</strong>Home</a>
			</li>
			<li class="m2">
				<a href="javascript:void(0);" ><strong>我要订餐</strong>Order Now</a>
			</li>
			<li class="m3">
				<a href="javascript:void(0);" ><strong>查询订单</strong>Check My Order</a>
			</li>
			<li class="m4">
				<a href="javascript:void(0);"><strong>我的优惠</strong>My Special Offer</a>
			</li>
		</ul>
	</div>
</div>
<!--header_box end-->
</div>
<div id="clear_div"></div>

<!--content_box start-->
<div class="spx5px"></div>
<div class="spx5px"></div>
<div class="content_box" id="content">
	<!--content_box_left start-->
	<div class="content_box_left">
		<div class="content_box_left_main">
			<!--content_box_left_main_left start-->
			<div class="content_box_left_main_left" id="showMenu">
				<div style="width: 196px; overflow: hidden; float: left; background: #FFF;" id="menu_china">
					<div style="width: 196px; overflow: hidden; background: #FFEFCD;">
						<div style="display: block;" id="china">
							<h1 class="content_box_left_main_left_h1"> 选项  </h1>
							<span class="content_box_left_main_left_c">
							<ul class="nav_left_ul" id="menu_2">
								<li>请选择日期：
									<input type="text" id="showDate" name="showDate" style="width:80px"/>
									<input type="hidden" id="showDateHidden" value="<s:property value="showDate"/>">
								</li>
								<li></li>
								<li><a href="javascript:submitSearchForm(1);" class="nav_left_n">查看午餐菜单</a></li><li></li>
								<li><a href="javascript:submitSearchForm(2);" class="nav_left_n">查看晚餐菜单</a></li><li></li>
								<li><a href="javascript:submitSearchForm(3);" class="nav_left_n">查看生食菜单</a></li>
								<input type="hidden" id="menuType" name="menuType">
							</ul>
							</span>
							<span class="content_box_left_main_left_b_2"></span>
						</div>
					</div>
				</div>
			</div>
			<!--content_box_left_main_left end-->

			<!--content_box_left_main_right start-->
			<div class="content_box_left_main_right" id="c_content" style="display: block;">
				<h1 class="content_box_left_main_right_h1" id="tab_obj" style="background-position: 0px 0px;"> 
					<span>菜单详细</span>
				</h1>
				<div id="showProductsNew">
					<div style="" id="showP_c">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tbody><tr><td class="content_box_left_main_right_c">
							<ul class="content_box_left_main_right_c_ul">
								<li>
								<s:if test="orderResult == 'failed'">
								<div>订单提交失败，请重新选择提交</div></li>
								</s:if>
								<s:else>
								<div>订单提交成功！订单号为：<s:property value="orderResult"/></div></li>
								</s:else>
								
								<div><a href="resOrderMenu.action">返回主页</a></div></li>
							</ul>
							<div id="clear_div"></div>
						</td></tr></tbody>
						</table>
						<span class="content_box_left_main_right_b"></span>
					</div>
				</div>
			</div>
			<!--content_box_left_main_right end-->
			<div id="clear_div"></div>
		</div>
		<!--content_box_left_main end-->
	</div>
	<!--content_box_left end-->
	<!--content_box_right start-->
	<div class="content_box_right" id="orderArea">
		<div class="content_box_right_dd" id="showOrder">
			<div style="" id="showO_c">
				<div class="content_box_right_dd">
					<h1 class="content_box_right_dd_h1">我的订单</h1>
					<div class="content_box_right_dd_c">
						<span class="content_box_right_dd_c_1" id="r_c">提醒：请正确填写手机号</span>

						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="order_table">
							<tbody><tr>
								<td width="50"><span class="order_td_t">品名</span></td>
								<td width="50"><span class="order_td_t">数量</span></td>
								<td><span class="order_td_t">单价</span></td>
								<td><span class="order_td_t">取消</span></td>
							</tr></tbody>
						</table>
						<div class="order_box_make" id="test">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" id="menuToal">

							</table>
						</div>
						<span class="order_bg_info">合&nbsp;&nbsp;&nbsp;&nbsp;计:<input type="text" name="totalMoney" id="totalMoney" readonly value="0" style="width:50px">元</span>
						<span class="order_bg_info">姓&nbsp;&nbsp;&nbsp;&nbsp;名:<input type="text" name="username" id="username" ></span>
						<span class="order_bg_info">手机号:<input type="text" name="userphone" id="userphone" ></span>
						<input type="hidden" id="orderDate" name="orderDate" />
						<input type="hidden" name="menuOrder" id="menuOrder">
						<span class="order_bt"> 
							<a href="javascript:submitOrderForm();">提交订单</a> 
						</span>
					</div>
					<span class="content_box_right_2_b_1"></span>
				</div>
			</div>
		</div>
		<!--content_box_right_2 start-->
		
		<div class="content_box_right_2" id="helpArea">
			<h1 class="content_box_right_3_h1">
				<div class="nrlink">
					<a href="javascript:void(0)"><font color="#EA0202"><u>订餐说明</u></font></a>
				</div>帮助中心
			</h1>
			<div class="content_box_right_2_c">
				<ul class="content_box_right_2_c_ul2">
					<li><a href="javascript:void(0)" class="nav_right_l">提交订单提示餐费若和取食的餐费不同，请以食堂餐费为准</a></li>
					<li><a href="javascript:void(0)" class="nav_right_l">订餐说明</a></li>
					<li><a href="javascript:void(0)" class="nav_right_l">订餐说明</a></li>
					<li><a href="javascript:void(0)" class="nav_right_l">订餐说明</a></li>
				</ul>
			</div>
			<span class="content_box_right_2_b"></span>
		</div>
	</div>
	<!--content_box_right end-->
</div>
<!--content_box end-->
</div>

</body>
</html>