<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="common/taglibs.jsp" %>
<c:set var="user" value="${sessionScope.username}"/>
<c:set var="userId" value="${sessionScope.userId}"/>
<c:if test="${empty user}">
	<script>
	window.location.href ="login.jsp";
	</script>
</c:if>
<HTML>
<HEAD>
<TITLE>后台管理</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<link href="static/css/flexigrid_blue.css" type=text/css rel=stylesheet>
		<link href="static/jqModal/css/jqModal_blue.css" type="text/css" rel="stylesheet" >
		<link href="static/images/Guide.css" type=text/css rel=stylesheet>
		<link href="static/images/index.css"  type=text/css rel=stylesheet>
		<link href="static/images/MasterPage.css" type=text/css rel=stylesheet> 
		<link rel="stylesheet" type="text/css" media="all" href="static/css/niceforms-main.css" />
		<script src="static/js/jquery.js" type=text/javascript></script>
		<script src="static/js/jquery.form.js" type=text/javascript></script>
		<script src="static/js/AdminIndex.js" type=text/javascript></script>
		<script src="static/js/FrameTab.js" type=text/javascript></script>
		<script src="static/jqModal/dimensions.js" type="text/javascript"></script>
		<script src="static/jqModal/jqDnR.js" type="text/javascript"></script>
		<script src="static/jqModal/jqModal.js" type="text/javascript"></script>
		<script language="javascript" type="text/javascript" src="static/js/niceforms.js"></script>
<script type="text/javascript">
var CheckFramesScroll = false;
$(document).ready(function(){
	$("#userPassword").jqm({
    modal : true,// 限制输入（鼠标点击，按键）的对话
    overlay : 60 // 遮罩程度%
    }).jqmAddClose('.close').jqDrag('.drag');
	NFInit();
});
function updatePassword(){
    
    $("#updatePassworForm input[type='text'],input[type='password'],input[type='hidden']").each(function() {
    	$(this).val("");
    });
    $("#userPassword").jqmShow();// 添加拖拽的selector
    
}
function check(userid){
	var name = $("#name").val();
	var oldpassword =document.getElementById("oldPassword").value;
	var password =document.getElementById("password").value;
	var newpassword =document.getElementById("newPassword").value;
	if(oldpassword==""||oldpassword.length==0){
		alert("原密码不能为空!");
		return false;
	}else if(password==""||password.length==0){
		alert("新密码不能为空!");
		return false;
	}else if(password!=newpassword){
		alert("新密码输入不一致!");
		return false;
	}else{
		var options = {
		type:"PUT",
		url:'user/password?_method=put&userId='+userid+'&oldpassword='+oldpassword+'&password='+password+'&name='+name,
		dataType:"json", 
		success:function(json){ 
			$("#userPassword").jqmHide();
			alert(json.msg); 
		}
		};

		$("#updatePassworForm").ajaxSubmit(options);
		return false;//为了防止刷新 

	}
}
</script>
<body id=Indexbody onload=onload();>
<form id="myForm">
<table cellSpacing=0 cellPadding=0 border=0>
    <tbody>
        <tr>
            <td colspan="3">
                <div id="indexContent">
                    <ul id="ChannelMenuItems">
                        <li id="Menu0" onclick="">
                        <a id="AChannelMenu_Menu0"
                            href="javascript:ShowMain('left.jsp','#')">
                            <span id="SpanChannelMenu_Menu0">首页</span>
                        </a> 
                        </li>
                         <li id="Menu0" onclick="">
                        <a id="AChannelMenu_Menu1" onclick=""
                             href="javascript:ShowMain('left.jsp','magazineList.jsp')">
                            <span id="SpanChannelMenu_Menu0">杂志管理</span>
                        </a> 
                        </li>  
                        <li id="Menu0" onclick="">
                        <a id="AChannelMenu_Menu1" onclick=""
                             href="javascript:ShowMain('left.jsp','userList.jsp')">
                            <span id="SpanChannelMenu_Menu0">用户管理</span>
                        </a> 
                        </li>   
                         <li id="Menu0" onclick="">
                        <a id="AChannelMenu_Menu1" onclick=""
                            href="javascript:ShowMain('left.jsp','commentList.jsp')">
                            <span id="SpanChannelMenu_Menu0">评论管理</span>
                        </a> 
                        </li>  
                        <li id="Menu0" onclick="">
                        <a id="AChannelMenu_Menu1" onclick=""
                            href="javascript:ShowMain('left.jsp','logList.jsp')">
                            <span id="SpanChannelMenu_Menu0">日志管理</span>
                        </a> 
                        </li>            
                    </ul>
                    <div id="SubMenu">
                        <div id="ChannelMenu_" style="width: 100%">
                            <ul>
                            	<li>您好</li>
                                <li><c:out value="${user}"/></li>
								<li><a onclick="updatePassword()">[密码变更]</a></li>
								<li><a onclick="logout('${user}')">[注销]</a></li>
                            </ul>
                        </div>
                    </div>    
                    </div> 
            	</td>
        </tr>
        <tr style="vertical-align: top">
            <td id="frmTitle">
                <iframe id="left" style="z-index: 2; visibility: inherit; width: 195px; height: 800px"
                    name="left" src="left.jsp" frameborder="0" tabid="1"></iframe>
            </td>
            <td class="but" onclick="switchSysBar();">
                <img id="switchPoint" style="border-right: 0px; border-top: 0px; border-left: 0px;
                    width: 12px; border-bottom: 0px" alt="关闭左栏" src="static/images/butClose.gif">
            </td>
            <td>
                <!-- 书签浏览 -->
                <div id="FrameTabs" style="overflow: hidden">
                    <div class="tab-right" onmouseover="this.className='tab-right tab-right-over'" onmouseout="this.className='tab-right tab-right-disabled'">
                    </div>
                    <div class="tab-left" onmouseover="this.className='tab-left tab-left-over'" onmouseout="this.className='tab-left tab-left-disabled'">
                    </div>
                    <div class="tab-strip-wrap" style="width: 8000px">
                        <ul class="tab-strip" style="float: left">
                            <li class="current" id="iFrameTab1">
                            <a href="javascript:"><span id="frameTabTitle">首页</span></a>
                            <a class="closeTab"><img src="static/images/tab-close.gif" border="0"></a>
                            </li>
                            <li id="newFrameTab"><a title="新建标签页" href="javascript:"></a></li>
                        </ul>
                    </div>
                </div>
                <!-- 书签结束 -->
                <div id="main_right_frame">
                    <iframe id="main_right" style="z-index: 2; visibility: inherit; overflow-x: hidden;
                        width: 1280px; height: 800px" name="main_right" src="main.jsp" frameborder="0"
                        scrolling="yes" onload="SetTabTitle(this)" tabid="1"></iframe>
                    <div class="clearbox2">
                    </div>
                </div>
            </td>
        </tr>
    </tbody>
</table>
</form>
<div id=iframeGuideTemplate style="DISPLAY: none">
<iframe style="Z-INDEX: 2; VISIBILITY: inherit; WIDTH: 195px; HEIGHT: 800px" src="about:blank" frameBorder=0 tabid="0">
</iframe></div>
<div id=iframeMainTemplate style="DISPLAY: none">
<iframe style="Z-INDEX: 2; VISIBILITY: inherit; OVERFLOW-X: hidden; WIDTH: 1280px; HEIGHT: 800px" src="about:blank" frameBorder=0 scrolling=yes onload=SetTabTitle(this) tabid="0">
</iframe></div>

<div class="jqmWindow" style="width: 350px;" id="userPassword">
<div class="drag">修改个人密码
<div class="close"></div>
</div>
<div class="main_form" style="width: 350px;">
<form id="updatePassworForm" name="updatePassworForm" class="niceform">
		<input type="hidden" name="name" id="name" value="${user}" readonly="readonly">
		<input type="hidden" name="userId" id="userId" value="${userId}">
		
		<dl>
			<dt>
				<label for="oldPassword">
					原密码:
				</label>
			</dt>
			<dd>
				<input type="password" name="oldPassword" id="oldPassword" >
			</dd>
		</dl>
		
		<dl>
			<dt>
				<label for="password">
					新密码:
				</label>
			</dt>
			<dd>
				<input type="password" name="password" id="password" >
			</dd>
		</dl>
		
		<dl>
			<dt>
				<label for="newPassword">
					确认新密码:
				</label>
			</dt>
			<dd>
				<input type="password" name="newPassword" id="newPassword" >
			</dd>
		</dl>
		<dl class="submit">	
			<input type="button" id="submit" class="input-button" value="提交" onclick="return check('${userId}');"/>
			<input type="reset" class="input-button" value="重置" />
		</dl>
</form>
</div>
</div>
<script type=text/javascript>
    <!--
        function onload() {
            var width = document.body.clientWidth - 207;
            var lHeight = document.body.clientHeight - 78;
            var rHeight = lHeight - (jQuery("#FrameTabs").height() || 0);
            document.getElementById("main_right").style.width = width > 0 ? width : 0;
            document.getElementById("main_right").style.height = rHeight > 0 ? rHeight : 0;
            document.getElementById("left").style.height = lHeight > 0 ? lHeight : 0;
            jQuery("#FrameTabs").width(width);
            //if (CheckFramesScroll) { CheckFramesScroll(); }
        }
        window.onresize = onload;

		function logout(name){
		var url="user/logout?_method=get&username="+name;
		var form = document.getElementById("myForm");
		form.action=url;
		form.submit();
		}
    -->	
    </script>
</body>
</HTML>
