<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common/taglibs.jsp" %>
<c:set var="magazineId" value="${param.magazineId}"/>
<c:set var="sectionId" value="${param.sectionId}"/>
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
		<link href="static/css/niceforms-main.css" type="text/css"  rel="stylesheet" />
		<link href="static/jqModal/css/jqModal_blue.css" type="text/css" rel="stylesheet" >
		<link href="static/images/Guide.css" type=text/css rel=stylesheet>
		<link href="static/images/index.css"  type=text/css rel=stylesheet>
		<link href="static/images/MasterPage.css" type=text/css rel=stylesheet> 

		<script src="static/js/jquery.js" type=text/javascript></script>
		<script src="static/js/jquery.form.js" type=text/javascript></script>
		<script src="static/js/flexigrid.js" type=text/javascript></script>
		<script type="text/javascript" src="static/jqModal/dimensions.js"></script>
		<script type="text/javascript" src="static/jqModal/jqDnR.js"></script>
		<script type="text/javascript" src="static/jqModal/jqModal.js"></script>
		<script language="javascript" type="text/javascript" src="static/js/niceforms.js"></script>
		<script language=javascript>
		var method = "";
		var magazineId="";
		var sectionId="";
		var rowdbclick = function(rowData) { 
			//alert($(rowData).data("articleId").toString()); 
			window.location.href ="addArticle.jsp?method=put"+
				"&articleId="+$(rowData).data("articleId").toString()+
				"&magazineId="+'${magazineId}'+
				"&articleName="+$(rowData).data("articleName").toString()+
				"&sectionId="+$(rowData).data("sectionId").toString()+
				"&sectionName="+$(rowData).data("sectionName").toString()+
				"&magazineName="+$(rowData).data("magazineName").toString()+
				"&commentState="+$(rowData).data("commentState").toString()+
				"&articleRecommend="+$(rowData).data("articleRecommend").toString()+
				"&seq="+$(rowData).data("seq").toString()+
				"&articlePicture="+$(rowData).data("articlePicture").toString()+
				"&articleDesc="+$(rowData).data("articleDesc").toString()+
				"&articleTop="+$(rowData).data("articleTop").toString()+
				"&author="+$(rowData).data("author").toString()+
				"&shareLink="+$(rowData).data("shareLink").toString();
		}
		$(document).ready(function(){
			magazineId = '${magazineId}';
			sectionId = '${sectionId}';
			$("#grid").flexigrid({		
				url:'article?_method=get&magazineId='+magazineId+'&sectionId='+sectionId,
				dataType:'json',
					colModel : [
						{display: '文章ID', id:'articleId',name :'articleId', width : 50, sortable : false, align: 'left',hide:true},
						{display: '文章名称', id:'articleName',name :'articleName', width : 150, sortable : false, align: 'left'},
						{display: '栏目名称', id:'sectionName',name :'sectionName', width : 50, sortable : false, align: 'left'},
						{display: '杂志名称',id:'magazineName', name :'magazineName', width : 100, sortable : false, align: 'left'},
						{display: '允许评论',id:'commentState', name :'commentState', width : 50, sortable : false, align: 'left'},
						{display: '显示序号',id:'seq', name :'seq', width : 50, sortable : false, align: 'left'},
						{display: '文章图片',id:'articlePicture', name :'articlePicture', width : 100, sortable : false, align: 'left'},
						{display: '文章描述',id:'articleDesc', name :'articleDesc', width : 150, sortable : false, align: 'left'},
						{display: '推荐文章',id:'articleTop', name :'articleTop', width : 50, sortable : false, align: 'left'},
						{display: '文章作者',id:'author', name :'author', width : 100, sortable : false, align: 'left'},
						{display: '重磅推荐',id:'articleRecommend', name :'articleRecommend', width : 50, sortable : false, align: 'left'},
						{display: '栏目ID', id:'sectionId',name :'sectionId', width : 50, sortable : false, align: 'left',hide:true},
						{display: '微博分享链接', id:'shareLink',name :'shareLink', width : 150, sortable : false, align: 'left',hide:true}
					],
					buttons : [{
						name :'新增',
						bclass :'add',
						displayname :'新增',
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
						}, {
			            separator : true
		            	}, {
						name :'返回上级',
						bclass :'back',
						displayname :'返回上级',
						onpress : operate
						}  ],
					 	sortname: "seq",
						sortorder: "asc",
						usepager: true,
						title: '文章管理',
						useRp: true,
						width: 'auto',
						height:'320',
						checkbox : true,// 是否要多选框
						showTableToggleBtn :false,
						rp: 10,
						onRowDblclick:rowdbclick
				});
				
			});
		
			
			function operate(com, grid) {
					
			   switch (com) {
			    case '新增':
			     addArticle();
			     break;
			    case '查看':
			     viewArticle();
			     break;
			    case '删除':
			     deleteArticle();
			     break;
			    case '返回上级':
			    window.location.href = "magazineList.jsp";
			    break;
			   }
			}
		
			function loadleft(){
				top.frames["left"].location.reload();
			}
	
			function addArticle(){
				window.location.href ="addArticle.jsp?method=post&magazineId="+magazineId;
			}
			
			function viewArticle(){
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
				window.location.href ="addArticle.jsp?method=put"+
				"&articleId="+data[0]+
				"&magazineId="+magazineId+
				"&articleName="+data[1]+
				"&sectionName="+data[2]+
				"&sectionId="+data[10]+
				"&magazineName="+data[3]+
				"&commentState="+data[4]+
				"&seq="+data[5]+
				"&articlePicture="+data[6]+
				"&articleDesc="+data[7]+
				"&articleTop="+data[8]+
				"&author="+data[9]+
				"&articleRecommend="+data[10]+
				"&shareLink="+data[12];
			}	
			
			function deleteArticle(){
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
			      if (confirm("确定删除栏目[" + names + "]?")) {
					delArticle(ids);
			    }
			}
			
			function check(){
				
			}
			function delArticle(ids) {
				 $.ajax({
				      url : 'article?_method=delete&rowid='+ids,
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
	<title>栏目管理</title>
	</head>
	<body id="MasterPagebody" onload="loadleft()">
    <table class="user_border" cellSpacing="0" cellPadding="0" align=center style="width: 98%;" border="0" id="table1">
		<tr>
			<td vAlign="top">
			<table class="user_box" cellSpacing="0" cellPadding="5" width="100%" border="0">
				<tr>
					<td align="left">
					<span style="font-size: 12pt; font-weight: bold; color: #3666AA">
       				 <img src="static/images/icon.gif"  style="border-width:0px;" />
       				 文章管理
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
	</body>
</html>