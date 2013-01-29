<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="java.util.*" %>
<%@ include file="../common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=utf-8">
		<link href="static/css/flexigrid.css" type=text/css rel=stylesheet>
		<link href="static/images/Guide.css" type=text/css rel=stylesheet>
		<link href="static/images/index.css"  type=text/css rel=stylesheet>
		<link href="static/images/MasterPage.css" type=text/css rel=stylesheet> 
		<script language=javascript src="static/js/jquery.js"
			type=text/javascript></script>
		<script language=javascript src="static/js/flexigrid.js"
			type=text/javascript></script>
		<script language=javascript>
  
		function loadleft(){
			top.frames["left"].location.reload();
		}
		
		var IsHelpShowCheck = false;
		
		function InsertShow(){
			window.location.href ="addMagazine.jsp";
		}
		
		function dosomething(){
		    $("#ly").css("display", "none");
		    $("#HelpTips").css("display", "none");
		}

</script>
		<title>计费设置</title>
	</head>
	<body id="MasterPagebody" onload="loadleft()">
				<table class="user_border" cellSpacing="0" cellPadding="0"
					align=center style="width: 98%;" border="0" id="table1">
					<tr>
						<td vAlign="top">
							<table class="user_box" cellSpacing="0" cellPadding="5"
								width="100%" border="0">
								<tr>
									<td align="left">
										<span
											style="font-size: 12pt; font-weight: bold; color: #3666AA">
											<img src="static/images/icon.gif" style="border-width: 0px;" />
											计费设置 </span>
									</td>
									<td align="center">
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<br />
				<table id="grid" ></table>
				<div class=HelpTips id=HelpTips title="">
					<div class=HelpTitle id=HelpTitle>
						<a class=tips_close title=不再提示 onclick=javascript:dosomething();
							href="javascript:"></a>
						<span id=LblHelpTitle></span>
					</div>
					<div id=showWindow></div>
				</div>
				<div onmousedown="this.style.display='none';document.getElementById('HelpTips').style.display='none';"
					id=ly
					style="DISPLAY: none; Z-INDEX: 1000; FILTER: alpha(opacity = 60); LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #777; opacity: 0.5"></div>
				<script language=javascript src="static/js/HelpTips.js" type=text/javascript></script>
	</body>
</html>