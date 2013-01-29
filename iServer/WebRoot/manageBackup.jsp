<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
					url:'operation/databaseName',
					dataType:'text',
					success:function(result)
					{
						$("#databaseName").html(result);
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
			if($("#backupName").val()=="0"&&$("#backupName2").val()==""){
				alert("备份名称不能为空!");
				return false;
			}
			var options = {
							type:"GET",
							url:'operation/backup?_method=GET',
							dataType:"json", 
							success:function(json){ 
								alert(json.msg);
							}
				}; 
				$("#backupForm").ajaxSubmit(options);
				return false;//为了防止刷新 
		}
		
		function loadleft(){
			top.frames["left"].location.reload();
		}
		</script>
		<title>备份管理</title>
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
											备份管理 </span>
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
				<form id="backupForm" class="niceform">
					<fieldset>
						<dl>
							<dt>
								<label for="backupName">
									名称列表:
								</label>
							</dt>
							<dd id="databaseName" >
							</dd>
						</dl>
						<dl>
							<dt>
								<label for="backupName">
									备份名称:
								</label>
							</dt>
							<dd>
								<input type="text" name="backupName2" id="backupName2">
							</dd>
						</dl>
						<dl>
							<dt>
								<label for="backupScope">
									备份范围:
								</label>
							</dt>
							<dd>
								<select name="backupScope" id="backupScope">
									<option value="all">全部备份</option>
									<!-- 
										<option value="data">数据备份</option>
									 -->
								</select>
							</dd>
						</dl>
						<dl class="submit">
							<input type="button" id="submit" class="input-button" value="进行备份"
								onclick="return check();" />
						</dl>
					</fieldset>
				</form>	
			</div>
	</body>
</html>