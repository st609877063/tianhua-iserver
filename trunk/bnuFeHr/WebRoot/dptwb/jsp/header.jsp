<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basep = request.getScheme() + "://"+ request.getServerName() + path + "/";
	String basePath = request.getScheme() + "://"+ request.getServerName() + path + "/dptwb/";
	long currentTime = new Date().getTime();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="utf-8">
<head>
<title><s:property value="pageTitle"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="content-language" content="utf-8" />
<meta name="robots" content="all" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta property="qc:admins" content="1446632360570456375636" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">
<META HTTP-EQUIV="expires" CONTENT="0">

<!-- 
<script type='text/javascript' src='<%=basePath%>js/jquery-1.8.2.min.js'></script>
 -->
<link rel="stylesheet" href="<%=basePath%>css/public_dark.css">
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="<%=basePath%>/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>/bootstrap/bootstrap-combined.min.css">
<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="<%=basePath%>/bootstrap/bootstrap-theme.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="<%=basePath%>/bootstrap/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=basePath%>/bootstrap/bootstrap.min.js"></script>


<script type="text/javascript">
BnuFe_config = {
    user : {
		userId : <s:property value="cookieUid"/>,
		userName: '<s:property value="cookieUserName"/>',
	    nickName: '<s:property value="cookieNickName"/>'
	},
	path : '<%=basep %>',
	basepath : '<%=basePath %>',
	currenttime : '<%=currentTime %>'
}
</script>

<!-- dwr interface -->
<script type='text/javascript' src="/dwr/util.js"></script>
<script type='text/javascript' src="/dwr/engine.js"></script>
<script type='text/javascript' src="/dwr/interface/feAjax.js"></script>

<style>
.selectedPage { background: #2a6496; !important}
</style>

</head>

<body scroll=no>
<!--顶通-->
<div class="ZWT_topbar">
    <div class="tpd_fixed">
		<div class="tpb_left">
          	<div class="tpb_userCount">
            	<dl>
                	<dt class="tpb_logo "><a href="javascript:void(0)" title="教育学部人事查询系统">教育学部人事查询系统</a></dt>
                </dl>
            </div>
            
            <div class="tpb_menu">
            	<a href="/hrHome.action" class="selected"><span class="tpb_arr tpb_arr_a">人事管理</span></a>
              	<a href="javascript:alert('付费服务')" ><span class="tpb_arr tpb_arr_b">科研管理</span></a>
                <a href="javascript:alert('付费服务')" ><span class="tpb_arr tpb_arr_c">教学管理</span></a>
                <a href="javascript:alert('付费服务')"><span class="tpb_arr tpb_arr_d">其他管理</span></a>
                <a href="javascript:alert('付费服务')"><span class="tpb_arr tpb_arr_e">其他管理</span></a>
            </div>
        </div>
        <div class="tpb_right" >
            <div class="tpb_name"><a class="tpb_out" href="<%=path %>/logout.action"><s:property value="cookieUsername" /></a></div>
        </div>
    </div>
</div>
<!--/顶通-->

<div class="zwp_main_c2_a">
	<div class="clearfix m_cont">
    	<div class="m_left">
            <div class="ZWT_userInfo">
            	<div class="user_face"><img src="http://tp2.sinaimg.cn/1177559401/180/5681377510/1" width="150" height="150"/></div>
                <div class="user_name"><s:property value="cookieNickName"/></div>
                <div class="line_01"></div>
            </div>
            
            <!-- 左侧菜单 -->
            <div class="zwp_nav">
            	<!-- 人事管理 -->
                <dl class="menu <s:if test="index == 1">current</s:if> "><dt><a class="" href="/hrHome.action"><span class="nav_arr_a">信息浏览</span></a></dt></dl>
                <dl class="menu <s:if test="index == 2">current</s:if> "><dt><a class="" href="/hrDetail.action"><span class="nav_arr_f">详细信息</span></a></dt></dl>
                <dl class="menu <s:if test="index == 3">current</s:if> ">
                	<dt><a class="" href="/hrChartUser.action"><span class="nav_arr_e">数据分析</span></a></dt>
                	<dd style="display:block">
                	<ul class="sub_menu">
                    <li <s:if test="getPageType() == 1">class="current"</s:if> ><a href="/hrChartUser.action"><span class="nav_arr_a01">人员分析</span></a></li>
                	<li <s:if test="getPageType() == 2">class="current"</s:if> ><a href="/hrChartChuguo.action"><span class="nav_arr_a01">出国分析</span></a></li>
                	</ul>
                	</dd>
                </dl>
                <dl class="menu <s:if test="index == 4">current</s:if> "><dt><a class="" href="/hrHome.action"><span class="nav_arr_d">导出报表</span></a></dt></dl>
                

            </div> <!-- zwp_nav -->
        </div><!-- m_left -->
