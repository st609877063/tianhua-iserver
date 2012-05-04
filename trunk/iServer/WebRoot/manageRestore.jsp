<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common/taglibs.jsp"%>
<c:set var="userId" value="${sessionScope.userId}" />
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=utf-8">
		<script src="static/js/jquery.js" type=text/javascript></script>
		<script src="static/js/jquery.form.js" type=text/javascript></script>
		<script language="javascript" type="text/javascript" src="static/js/niceforms.js"></script>
			<link rel="stylesheet" type="text/css" media="all"
			href="static/css/niceforms-select.css" />
		<script language=javascript>
		$(document).ready(function(){
			$.ajax({
					method:'GET',
					url:'operation/databaseList',
					dataType:'text',
					success:function(result)
					{
						$("#databaseList").html(result);
						NFInit();
						
  					},
					error:function()
					{
						alert("警告:服务器繁忙请稍候再试!");
					}
			});
			//NFInit();
		});
		
		function check(){
			var str = $("#backupName").val();
			if(str==""||str.length==0){
				alert("请选择需要还原的备份文件!");
				return false;
			}
			else if(str == "0"){
				alert("没有备份文件!");
				return false;
			}
			var options = {
							type:"GET",
							url:'operation/restore?_method=GET',
							dataType:"json", 
							success:function(json){ 
								alert(json.msg);
							}
				}; 
				$("#restoreForm").ajaxSubmit(options);
				return false;//为了防止刷新 
		}
		
		function loadleft(){
			top.frames["left"].location.reload();
		}
		</script>
		<title>还原管理</title>
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
											还原管理 </span>
									</td>
									<td align="center">
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<br />
				<div style="width: 100%;align:center;">
					<form id="restoreForm" name="restoreForm" class="niceform">
					<fieldset>
						<dl >
							<dt>
								<label for="databaseList">
									还原备份:
								</label>
							</dt>
							<dd id="databaseList" >
							</dd>
						</dl>
						<dl class="submit">
							<input type="button" id="submit" class="input-button" value="进行还原"
								onclick="return check();" />
						</dl>
					</fieldset>
				</form>	
			</div>
	</body>
</html>