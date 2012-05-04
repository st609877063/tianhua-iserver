<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>发表贴子</title>
    <META http-equiv=Content-Type content="text/html; charset=utf-8">
    <LINK href="../images/Guide.css" type=text/css rel=stylesheet>
	<LINK href="../images/index.css" type=text/css rel=stylesheet>
	<LINK href="../images/MasterPage.css" type=text/css rel=stylesheet>
	<!-- jQuery -->
	<script type="text/javascript" src="../js/jquery.js"></script>
	<script type="text/javascript" src="../markitup/jquery.markitup.pack.js"></script>
	<script type="text/javascript" src="../markitup/sets/default/set.js"></script>
	<link rel="stylesheet" type="text/css" href="../markitup/skins/markitup/style.css" />
	<link rel="stylesheet" type="text/css" href="../markitup/sets/default/style.css" />
    <script type="text/javascript">
	$(document).ready(function()	{
	$('#markItUp').markItUp(mySettings);	
	$('.add').click(function() {
 		$.markItUp( { 	
 		openWith:'<opening tag>',
		closeWith:'<\/closing tag>',
		placeHolder:"添加新内容"
					}
				);
 		return false;
	});
	
	$('.toggle').click(function() {
		if ($("#markItUp.markItUpEditor").length === 1) {
 			$("#markItUp").markItUpRemove();
			$("span", this).text("高级编辑");
		} else {
			$('#markItUp').markItUp(mySettings);
			$("span", this).text("简单编辑");
		}
 		return false;
	});
	});
	</script>
    
    <script language="javascript">
    	function check(){
    		var title = document.uploadform.title.value;
    		var content = document.uploadform.content.value;
    		var filename = document.uploadform.filename.value;
    		if(title==""){
    			alert("标题不能为空!");
    			return false;
    		}else if(content==""){
    			alert("内容不能为空!");
    			return false;
    		}else if(content.length>1000){
    			alert("内容必须少于1000字!");
    			return false;
    		}
    		return true;
    	}
    	function setfilename(){
    	    var title = 	document.uploadform.title.value;
    		var content = 	document.uploadform.content.value;
    		var filename = 	document.uploadform.filename.value;
    		
    	}
    </script> 	
	 <% 

	 %> 
  </head>
  <body>
	<br>
	
	<form name="form" action="../PageAction?action=updateTopic&topicId=<%=""%>&boardId=<%="" %>" method="post" onsubmit="return check()">
	<table >
		<tr >
			<td >公告名称</td>
			<td><input type="text" name="title" value="<%="" %>" /></td>
		</tr>
		<tr >
			<td  align="center">下载分数</td>
			<td><select id=score name=score>
			<option value="0">0分</option>
			<option value="1" selected >1分</option>
			<option value="2">2分</option>
			<option value="3">3分</option>
			<option value="4">4分</option>
			<option value="5">5分</option>
			</select></td>
		</tr>
		<tr >
			<td  align="center">附件上传</td>
			<td><input name="filename"  readonly="true" type="file" title=""></td>
		</tr>
		<tr >
			<td align="center">公告内容</td>
			<td><textarea id="markItUp" name="content">
			<%=""%>
			</textarea></td>
		</tr>
		<tr>
			<td >
			</td>
			<td>
			<input type="submit" class="btn" value=" 提 交 ">
			<input type="reset" class="btn" value=" 重 置 "></td>
		</tr>
	</table>
	</form>
  </body>
</html>
