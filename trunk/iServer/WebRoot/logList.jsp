<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>
<html>
<head>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<link href="static/css/flexigrid_blue.css" type=text/css rel=stylesheet>
		<link href="static/images/Guide.css" type=text/css rel=stylesheet>
		<link href="static/images/index.css"  type=text/css rel=stylesheet>
		<link href="static/images/MasterPage.css" type=text/css rel=stylesheet> 
		<script language=javascript src="static/js/jquery.js"
			type=text/javascript></script>
		<script language=javascript src="static/js/flexigrid.js"
			type=text/javascript></script>
		<script language=javascript>
		$(document).ready(function(){
			$("#grid").flexigrid({
				url:'log?_method=get',
				dataType:'json',
					colModel : [
						{display: '日志ID',name :'logId', width:220, sortable : false, align: 'left'},
						{display: '日志类型', name :'type',width:180,  sortable : false, align: 'left'},
						{display: '操作人', name :'username',width:180,  sortable : false, align: 'left'},
						{display: '事件名称', name :'message',width:180,  sortable : false, align: 'left'},
						{display: '创建时间', name :'createDate',width:180,  sortable : true, align: 'left'}
						],
				 	sortname: "createDate",
					sortorder: "desc",
					usepager: true,
					title: '日志列表',
					useRp: true,
					width: 'auto',
					height:'320',
					rp: 10,
					showTableToggleBtn: true
			});
		});
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
<title>日志管理</title>
</head>
<% 

%>
<body id="MasterPagebody" onload="loadleft()">
    <table class="user_border" cellSpacing="0" cellPadding="0" align=center style="width: 98%;" border="0" id="table1">
		<tr>
			<td vAlign="top">
			<table class="" cellSpacing="0" cellPadding="5" width="100%" border="0">
				<tr>
					<td align="left">
					<span style="font-size: 12pt; font-weight: bold; color: #3666AA">
       				 <img src="static/images/icon.gif"  style="border-width:0px;" />
       				 日志管理
       				 </span>
       				</td>
					<td align="center">
					<table align="right" id="table3">
						<tr>
							<td width="80">			
							</td>			 										
						</tr>
					</table>
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
<a class=tips_close title=不再提示 onclick=javascript:dosomething(); href="javascript:"></a>
<span id=LblHelpTitle></span>
</DIV>
<div id=showWindow></div>
</div>
<div onmousedown="this.style.display='none';document.getElementById('HelpTips').style.display='none';" id=ly style="DISPLAY: none; Z-INDEX: 1000; FILTER: alpha(opacity=60); LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #777; opacity: 0.5"></div>
<script language=javascript src="static/js/HelpTips.js" type=text/javascript></script>
</body>
</html>