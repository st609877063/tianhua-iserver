<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="common/taglibs.jsp"%>
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
		<link  href="static/css/niceforms-main.css" type="text/css" rel="stylesheet" />
		<script src="static/js/jquery.js" type=text/javascript></script>
		<!-- 表单插件 -->
		<script src="static/js/jquery.form.js" type=text/javascript></script>
		<!-- 网格插件 -->
		<script src="static/js/flexigrid.js" type=text/javascript></script>
		<!-- 模态对话框插件 -->
		<script type="text/javascript" src="static/jqModal/dimensions.js"></script>
		<script type="text/javascript" src="static/jqModal/jqDnR.js"></script>
		<script type="text/javascript" src="static/jqModal/jqModal.js"></script>
		<!-- 文件上传插件 -->
		<script type="text/javascript" src="static/js/swfupload/swfupload.js"></script>
		<script type="text/javascript" src="static/js/swfupload/swfupload.queue.js"></script>
		<script type="text/javascript" src="static/js/swfupload/handlers.js"></script>
		<!-- 表单样式插件 -->
		<script language="javascript" type="text/javascript" src="static/js/niceforms.js"></script>
		<script language=javascript>
		var rowdbclick = function(rowData) { 
			//alert($(rowData).data("articleId").toString()); 
			window.location.href ="addArticle.jsp?method=put"+
				"&articleId="+$(rowData).data("articleId").toString()+
				"&magazineId="+$(rowData).data("magazineId").toString()+
				"&articleName="+$(rowData).data("articleName").toString()+
				"&sectionName="+$(rowData).data("sectionName").toString()+
				"&magazineName="+$(rowData).data("magazineName").toString()+
				"&commentState="+$(rowData).data("commentState").toString()+
				"&seq="+$(rowData).data("seq").toString()+
				"&articlePicture="+$(rowData).data("articlePicture").toString()+
				"&articleDesc="+$(rowData).data("articleDesc").toString()+
				"&articleTop="+$(rowData).data("articleTop").toString()+
				"&author="+$(rowData).data("author").toString();
		}
		
		$(document).ready(function(){
			$("#grid").flexigrid({
				url:'comment?_method=get',
				dataType:'json',
					colModel : [
						{display: '评论ID', id:'commentId',name :'commentId', width : 200, sortable : false, align: 'left',hide:true},
						{display: '评论内容', id:'content',name :'content', width : 200, sortable : false, align: 'left'},
						{display: '评论时间', id:'createDate',name :'createDate', width : 200, sortable : false, align: 'left'},
						{display: '评论人',id:'userName', name :'userName', width : 200, sortable : false, align: 'left'},
						{display: '文章名',id:'articleName', name :'articleName', width : 200, sortable : false, align: 'left'},
						{display: '审核状态',id:'reviewState', name :'reviewState', width : 200, sortable : false, align: 'left'},
						{display: '',id:'articleId', name :'articleId', width : 0, sortable : false, align: 'left',hide:true},
						{display: '',id:'articleDesc', name :'articleDesc', width : 0, sortable : false, align: 'left',hide:true},
						{display: '',id:'sectionName', name :'sectionName', width : 0, sortable : false, align: 'left',hide:true},
						{display: '',id:'magazineId', name :'magazineId', width : 0, sortable : false, align: 'left',hide:true},
						{display: '',id:'magazineName', name :'magazineName', width : 0, sortable : false, align: 'left',hide:true},
						{display: '',id:'seq', name :'seq', width : 0, sortable : false, align: 'left',hide:true},
						{display: '',id:'articlePicture', name :'articlePicture', width : 0, sortable : false, align: 'left',hide:true},
						{display: '',id:'articleTop', name :'articleTop', width : 0, sortable : false, align: 'left',hide:true},
						{display: '',id:'commentState', name :'commentState', width : 0, sortable : false, align: 'left',hide:true},
						{display: '',id:'author', name :'author', width : 0, sortable : false, align: 'left',hide:true}
						],
					buttons : [ {
						name :'审核',
						bclass :'edit',
						displayname :'审核',
						onpress : operate
						}, {
			            separator : true
		            	}, {
						name :'查看',
						bclass :'view',
						displayname :'查看',
						onpress : operate
						} , {
			            separator : true
		            	}, {
						name :'删除',
						bclass :'delete',
						displayname :'删除',
						onpress :operate
						}],
					 	sortname: "createDate",
						sortorder: "desc",
						usepager: true,
						title: '评论管理',
						useRp: true,
						width: 'auto',
						height:'320',
						checkbox : true,// 是否要多选框
						showTableToggleBtn :false,
						rp: 10,
						onRowDblclick:rowdbclick
				});
				
				$("#comments").jqm({
			    modal : true,// 限制输入（鼠标点击，按键）的对话
			    overlay : 60 // 遮罩程度%
		        })
		        .jqmAddClose('.close')// 添加触发关闭的selector
		        .jqDrag('.drag');// 添加拖拽的selector
				NFInit();
			});
			
			function operate(com, grid) {
			   switch (com) {
			    case '审核':
			     reviewComment();
			     break;
			    case '查看':
			     viewComment();
			     break;
			    case '删除':
			     deleteComment();
			     break;
			    case '浏览文章':
			     enterArticle();
			     break;
			   }
			}
			
			function reviewComment(){
				var selected_count = $('.trSelected', grid).length;
			    if (selected_count == 0) {
				    alert('请选择一条记录!');
				    return;
			    }
			    var counts = 0;
			    $('.trSelected td:nth-child(3) div', grid).each(function(i) {
				        if (i)
					    counts++
			        });
			    ids = '';
			    $('.trSelected td:nth-child(2) div', grid).each(function(i) {
				        if (i)
					    ids += ',';
				        ids += $(this).text();
			        })
			    if (confirm("确定审核评论?")) {
					revComment(ids);
			    }
			}
			
			function viewComment(){
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
			    $("#commentId").val(data[0]);
			    $("#articleName").val(data[4]);
			    $("#userName").val(data[3]);
			    $("#content").val(data[1]);
			    $("#comments").jqmShow();
			}
			
			
			function deleteComment(){
				var selected_count = $('.trSelected', grid).length;
			    if (selected_count == 0) {
				    alert('请选择一条记录!');
				    return;
			    }
			    var counts = 0;
			    $('.trSelected td:nth-child(3) div', grid).each(function(i) {
				        if (i)
					    counts++
			        });
			    ids = '';
			    $('.trSelected td:nth-child(2) div', grid).each(function(i) {
				        if (i)
					    ids += ',';
				        ids += $(this).text();
			        })
			    if (confirm("确定删除评论?")) {
					delComment(ids);
			    }
			}
			function revComment(ids){
			 	$.ajax({
			      url : 'comment/comments?_method=put&rowid='+ids,
			      type : 'PUT',
			      dataType : 'json',
			      success : function(json) {
						alert(json.msg);
						$("#grid").flexReload();
			      }
			     });
			}
			
			function delComment(ids) {
			 	$.ajax({
			      url : 'comment?_method=delete&rowid='+ids,
			      type : 'DELETE',
			      dataType : 'json',
			      success : function(json) {
						alert(json.msg);
						$("#grid").flexReload();
			      }
			     });
			}
			
  
  			function check(){
				var id = $("#commentId").val();
				var options = {
					type:"PUT",
					url:'comment?_method=PUT&commentId='+id,
					dataType:"json", 
					success:function(json){ 
						$("#comments").jqmHide();
						$("#grid").flexReload();
						alert(json.msg);
					}
				}; 
				$("#saveComments").ajaxSubmit(options);
				return false;//为了防止刷新
			}
			
			function loadleft(){
				top.frames["left"].location.reload();
			}
		
</script>
		<title>评论管理</title>
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
								<span style="font-size: 12pt; font-weight: bold; color: #3666AA">
									<img src="static/images/icon.gif" style="border-width: 0px;" />
									评论管理 </span>
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
		<table id="grid"></table>
		<div class="jqmWindow" style="width: 350px;" id="comments">
			<div class="drag">
				评论管理
				<div class="close"></div>
			</div>
			<div class="main_form" style="width: 350px;">
				<form id="saveComments" name="saveComments" class="niceform">
					<input type="hidden" name="commentId" id="commentId">
					<input type="hidden" name="userId" id="userId" value="${userId}">
					<fieldset>
						<dl>
							<dt>
								<label for="articleName">
									文章名:
								</label>
							</dt>
							<dd>
								<input type="text" name="articleName" id="articleName"
									readonly="readonly">
							</dd>
						</dl>
						<dl>
							<dt>
								<label for="userName">
									用户名:
								</label>
							</dt>
							<dd>
								<input type="text" name="userName" id="userName"
									readonly="readonly">
							</dd>
						</dl>
						<dl>
							<dt>
								<label for="content">
									评论内容:
								</label>
							</dt>
							<dd>
								<textarea name="content" id="content" readonly="readonly" cols="32">
								</textarea>
							</dd>
						</dl>
						<!-- 
						<dl class="submit">
							<input type="button" id="submit" class="input-button" value="通过" onclick="return check();" />
						</dl>
						 -->
					</fieldset>
				</form>
			</div>
		</div>
	</body>
</html>