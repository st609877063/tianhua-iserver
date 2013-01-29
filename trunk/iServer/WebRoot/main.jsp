<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<script language=javascript src="static/js/jquery.js" type=text/javascript></script>
<LINK href="static/images/Guide.css" type=text/css rel=stylesheet>
<LINK href="static/images/index.css" type=text/css rel=stylesheet>
<LINK href="static/images/MasterPage.css" type=text/css rel=stylesheet>
<script language=javascript>

function loadleft(){
top.frames["left"].location.reload();
}

var IsHelpShowCheck = false;

</script>
<title>首页</title>
</head>
<body id="MasterPagebody" onload="loadleft()">

        <div>  
    	<div>
 	<form name=search method=post action="../PageAction?action=searchTopic">
 	<table align=center style="width: 98%;" cellpadding="2" cellspacing="1" class="border">
    <tr>
    <td style="width: 80px" align="left" class="tdbg">
    </td>
    <td class="tdbg">
    </td>
    <td class="tdbg"></td>
        </tr>
    </table>
    </form><br />
	<table align=center style="width: 98%;" class="border" cellspacing="1" cellpadding="0" border="0" >			
	</table>
	</div>
     </div>
     <br /> 
<div>
</div>

</body>
</html>