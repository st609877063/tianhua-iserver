<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="common/taglibs.jsp" %>
<c:set var="user" value="${sessionScope.username}"/>
<c:set var="userId" value="${sessionScope.userId}"/>
<c:if test="${empty user}">
	<script>
	window.location.href ="login.jsp";
	</script>
</c:if>
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=utf-8">
		<link href="static/css/flexigrid_blue.css" type=text/css rel=stylesheet>
		<link href="static/jqModal/css/jqModal_blue.css" type="text/css" rel="stylesheet" >
		<link href="static/images/Guide.css" type=text/css rel=stylesheet>
		<link href="static/images/index.css"  type=text/css rel=stylesheet>
		<link href="static/images/MasterPage.css" type=text/css rel=stylesheet> 
		<link rel="stylesheet" type="text/css" media="all" href="static/css/niceforms-main.css" />
		<script src="static/js/jquery.js" type=text/javascript></script>
		<script src="static/js/jquery.form.js" type=text/javascript></script>
		<script src="static/js/flexigrid.js" type=text/javascript></script>
		<script type="text/javascript" src="static/jqModal/dimensions.js"></script>
		<script type="text/javascript" src="static/jqModal/jqDnR.js"></script>
		<script type="text/javascript" src="static/jqModal/jqModal.js"></script>
		<script language="javascript" type="text/javascript" src="static/js/niceforms.js"></script>
		<script language=javascript>
		var method = "";

		$(document).ready(function(){
			//if(userId==""){
			//	alert("您还未登陆系统，请先登陆");
			//	window.location.href="/login.jsp";
			//	return false;
			//}
			$("#grid").flexigrid({
				url:'user?_method=get',
				dataType:'json',
					colModel : [
						{display: '用户ID', id:'userId',name :'userId', width : 100, sortable : false, align: 'left',hide:true},
						{display: '用户名', id:'name',name :'name', width : 200, sortable : false, align: 'left'},
						{display: '密码', id:'password',name :'password', width : 200, sortable : false, align: 'left',hide:true},
						{display: '角色名',id:'rolename', name :'rolename', width : 200, sortable : false, align: 'left'},
						{display: '是否有效',id:'valid', name :'valid', width : 200, sortable : false, align: 'left'},
						{display: '创建时间',id:'createDate', name :'createDate', width : 200, sortable : false, align: 'left'}
						],
					buttons : [ {
						name :'添加',
						bclass :'add',
						displayname :'增加',
						onpress : operate
						}, {
			            separator : true
		            	}, {
						name :'删除',
						bclass :'delete',
						displayname :'删除',
						onpress :operate
						}, {
			            separator : true
		            	}, {
						name :'修改',
						bclass :'edit',
						displayname :'修改',
						onpress : operate
						} ],
					 	sortname: "userId",
						sortorder: "asc",
						usepager: true,
						title: '用户列表',
						useRp: true,
						width: 'auto',
						height:'320',
						checkbox : true,// 是否要多选框
						showTableToggleBtn :false,
						rp: 10
				});
				$("#users").jqm({
			    modal : true,// 限制输入（鼠标点击，按键）的对话
			    overlay : 60 // 遮罩程度%
		        })
		        .jqmAddClose('.close')// 添加触发关闭的selector
		        .jqDrag('.drag');// 添加拖拽的selector
				NFInit();
			});

		function operate(com, grid) {
				
		   switch (com) {
		    case '添加':
		     addUser();
		     break;
		    case '修改':
		     updateUser();
		     break;
		    case '删除':
		     deleteUser();
		     break;
		   }
		}

		function addUser() {
			method = "POST";
			var data = new Array();
   			$("#saveUsers input[type='text'],input[type='password'],input[type='hidden']").each(function() {
		        $(this).val("");
	        });
	        $('#saveUsers input[name="userId"]').removeAttr("readonly");
	        $('#saveUsers input[name="name"]').removeAttr("readonly");
			$('#saveUsers input[name="userId"]').attr("readonly","readonly");
			$("#users").jqmShow();
		}
		function updateUser(){
			method = "PUT";
		    var selected_count = $('.trSelected', grid).length;
			    if (selected_count == 0) {
				    alert('请选择一条记录!');
				    return;
			    }
			    if (selected_count > 1) {
				    alert('抱歉只能同时修改一条记录!');
				    return;
			    }
			    var data = new Array();
			    $('.trSelected td', grid).each(function(i) {
				        data[i] = $(this).children('div').text();
			    });
			    $('#saveUsers input[name="userId"]').val(data[0]).attr("readonly","readonly");
			    $('#saveUsers input[name="name"]').val(data[1]).attr("readonly","readonly");
			    $('#saveUsers input[name="password"]').val(data[2]);
			    $("#users").jqmShow();
		}
		
		function deleteUser(){
			var selected_count = $('.trSelected', grid).length;
		    if (selected_count == 0) {
			    alert('请选择一条记录!');
			    return;
		    }
		    names = '';
		    $('.trSelected td:nth-child(3) div', grid).each(function(i) {
			        if (i)
				    names += ',';
			        names += $(this).text();
		        });
		    ids = '';
		    $('.trSelected td:nth-child(2) div', grid).each(function(i) {
			        if (i)
				    ids += ',';
			        ids += $(this).text();
		        })
		    if (confirm("确定删除用户[" + names + "]?")) {
				delUser(ids);
		    }
		}
		
		function check(){
			var id = $("#userId").val();
			var name = $("#name").val();
			var password =$("#password").val();	
			var options ;		
			if(name==""||name.length==0){
				alert("用户名不能为空!");
				return false;
			}else if(password==""||password.length==0){
				alert("密码不能为空!");
				return false;
			}else{
				if("POST"==method){
					options = {
						type:"POST",
						url:'user?_method=POST',
						dataType:"json", 
						contentType: "application/x-www-form-urlencoded; charset=utf-8",
						success:function(json){ 
							$("#users").jqmHide();
							$("#grid").flexReload();
							alert(json.msg);
						}
					}; 
				}else if("PUT"==method){
					//alert();
					options = {
						type:"PUT",
						url:'user?_method=PUT&userId='+id+'&name='+name+'&password='+password,
						dataType:"json", 
						contentType: "application/x-www-form-urlencoded; charset=utf-8",
						success:function(json){ 					
							$("#users").jqmHide();
							$("#grid").flexReload();
							alert(json.msg);
						}
					}; 
				}
				
				$("#saveUsers").ajaxSubmit(options);
				return false;//为了防止刷新 
			}
		}
		
		function delUser(ids) {
			 $.ajax({
			      url : 'user?_method=delete&rowid='+ids,
			      type : 'DELETE',
			      dataType : 'json',
			      success : function(json) {
						//document.frames("main_right").reload();		
						alert(json.msg);
						$("#grid").flexReload();
						
			      }
			     });
		}
  
		function loadleft(){
			top.frames["left"].location.reload();
		}
		
</script>
		<title>用户管理</title>
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
											用户管理 </span>
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
				<div class="jqmWindow" style="width: 350px;" id="users">
					<div class="drag">
					用户信息编辑
					<div class="close"></div>
					</div>
					<div class="main_form" style="width: 350px;">
					<form id="saveUsers" name="saveUsers"  class="niceform">
						<input type="hidden" name="userId" id="userId" >
							<fieldset >
							<dl>
								<dt>
									<label for="name">
										用户名:
									</label>
								</dt>
								<dd>
									<input type="text" name="name" id="name" >
								</dd>
							</dl>
							<dl>
								<dt>
									<label for="password">
										密码:
									</label>
								</dt>
								<dd>
									<input type="password" name="password" id="password" >
								</dd>
							</dl>
						<dl class="submit">	
							<input type="button" id="submit" class="input-button" value="提交" onclick="return check();"/>
							<input type="reset" class="input-button" value="重置" />
						</dl>
					</form>
					</div>
				</div>
	</body>
</html>