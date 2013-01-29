<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="java.util.*" %>
<%@ include file="common/taglibs.jsp" %>
<c:set var="user" value="${sessionScope.username}"/>
<html>
<head >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="static/images/Guide.css" type="text/css" rel="stylesheet" />
<link href="static/images/index.css" type="text/css" rel="stylesheet" />
<link href="static/images/MasterPage.css" type="text/css" rel="stylesheet" />
<link href="static/images/xtree.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript">
    function JumpToMainRight(url) { parent.frames["main_right"].location = url; }
    function ReloadMainRight() { parent.frames["main_right"].location.reload(); }
    function Switch(obj)
    {
        obj.className = (obj.className == "guideexpand") ? "guidecollapse" : "guideexpand";
        var nextDiv;
        if (obj.nextSibling)
        {
            if(obj.nextSibling.nodeName=="DIV")
            {
                nextDiv = obj.nextSibling;
            }
            else
            {
                if(obj.nextSibling.nextSibling)
                {
                    if(obj.nextSibling.nextSibling.nodeName=="DIV")
                    {
                        nextDiv = obj.nextSibling.nextSibling;
                    }
                }
            }
            if(nextDiv)
            {
                nextDiv.style.display = (nextDiv.style.display != "") ? "" : "none"; 
            }
        }
    }
    function keylock(evt)
    {
	    if((evt.keyCode == 13) && (this.OpenMainRight))
	    {
	        this.OpenMainRight();
	    }
    }
    String.prototype.trim = function()
    {
        // 用正则表达式将前后空格
        // 用空字符串替代。
        return this.replace(/(^\s*)|(\s*$)/g, "");
    }
    </script>
 <style type="text/css">
    <!--
        .skin1 {
            cursor:default;
            font:menutext;
            position:absolute;
            text-align:left;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 10pt;
            width:120px;
            background-color:#cccccc;
            border:1 solid buttonface;
            visibility:hidden;
            border:2 outset buttonhighlight;
        }
        .menuitems {
            padding-left:15px;
            padding-right:10px;
    }
    -->
    </style>
</head>
<body id="Guidebody" onkeydown="keylock(event)">
    <form name="aspnetForm" method="post" action="" id="aspnetForm">
        <div id="Guide_back">
            <ul>
                <li id="Guide_top">
                    <div id="Guide_toptext">功能导航</div>
                </li>
                <li id="Guide_main">
                	<c:if test="${!empty user}">
                    <div id="Guide_box">
				    <div class="guideexpand" onclick="Switch(this)">管理中心</div>
				    <div class="guide">
				        <ul>
							
				            <li><a href="magazineList.jsp" target="main_right">杂志管理</a></li>
				            <li><a href="userList.jsp" target="main_right">用户管理</a></li>
				            <li><a href="commentList.jsp" target="main_right">评论管理</a></li>
				            <li><a href="logList.jsp" target="main_right">日志管理</a></li> 
				            
				        </ul>
				    </div>
				    </div>
				    <div id="Guide_box">
				    <div class="guideexpand" onclick="Switch(this)">系统配置</div>
				    <div class="guide">
				     	<ul>
				     		<li><a href="#" style="COLOR: #CCCCCC ; TEXT-DECORATION: line-through ">计费设置</a></li>
				     		<li><a href="manageBackup.jsp" target="main_right">备份管理</a></li>
				     		<li><a href="manageRestore.jsp" target="main_right">还原管理</a></li>
				     	</ul>
				    </div>
				    </div>
				     </c:if>
           	   </li>
               <li id="Guide_bottom"></li>
            </ul>
        </div>
    </form>
</body>
</html>
