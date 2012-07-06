<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="shortcut icon" href="images/favicon.gif" type="image/ico" />
<link rel="stylesheet" href="css/screen.css" type="text/css" media="screen" />
<link rel="stylesheet" href="css/lightbox.css" type="text/css" media="screen" />

<script src="js/jquery-1.7.2.min.js"></script>
<script src="js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="js/jquery.smooth-scroll.min.js"></script>
<script src="js/lightbox.js"></script>

<script>
	jQuery(document).ready(function($) {
		$('a').smoothScroll({
			speed : 1000,
			easing : 'easeInOutCubic'
		});

		$('.showOlderChanges').on('click', function(e) {
			$('.changelog .old').slideDown('slow');
			$(this).fadeOut();
			e.preventDefault();
		});
	});
</script>
</head>

<body>
	<div id="content">
		<div class="section" id="example">
			<h2>礼品图册</h2>
			<s:iterator value="lightboxVoList" id="item_vo">
			<h3 style="clear: both;"><s:property value="#item_vo.i_name" /></h3>
			<div class="imageRow">
				<div class="set">
					<s:iterator value="#item_vo.lightbox_fujian_vo_list" id="fujian_vo">
					<div class="single">
						<!-- 
						<a href="<s:property value="#fujian_vo.fj_path" />" rel="lightbox[plants]"
							title="<s:property value="#fujian_vo.fj_desc" />">
							<img src="<s:property value="#fujian_vo.fj_path" />" alt="" />
						</a>
						 -->
						<a href="images/examples/image-1.jpg" rel="lightbox"><img src="images/examples/thumb-1.jpg" alt="" /></a>
					</div>
					</s:iterator>
				</div>
			</div>
			</s:iterator>
		</div>
	</div>
</body>
</html>