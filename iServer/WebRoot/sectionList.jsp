<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common/taglibs.jsp" %>
<c:set var="magazineId" value="${param.magazineId}"/>
<c:set var="magazineName" value="${param.magazineName}"/>
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
		<link href="static/css/niceforms-main.css" type="text/css"  rel="stylesheet" />
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
		var magazineName="";
		
		var rowdbclick = function(rowData) { 
		//alert($(rowData).data("magazineId").toString());
		window.location.href = "articleList.jsp?magazineId="+'${magazineId}'+
		"&sectionId="+$(rowData).data("sectionId").toString();
		}
	
		$(document).ready(function(){
			magazineId = '${magazineId}';
			magazineName = '${magazineName}';
			$("#grid").flexigrid({
				url:'section?_method=get&magazineId='+magazineId,
				dataType:'json',
					colModel : [
						{display: '栏目ID', id:'sectionId',name :'sectionId', width : 100, sortable : false, align: 'left',hide:true},
						{display: '栏目名', id:'sectionName',name :'sectionName', width : 200, sortable : false, align: 'left'},
						{display: '杂志名', id:'magazineName',name :'magazineName', width : 200, sortable : false, align: 'left'},
						{display: '显示序号',id:'seq', name :'seq', width : 200, sortable : false, align: 'left'},
						{display: '是否显示',id:'hide', name :'hide', width : 200, sortable : false, align: 'left'}
						],
					buttons : [{
						name :'新增',
						bclass :'add',
						displayname :'新增',
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
						}, {
			            separator : true
		            	}, {
						name :'返回上级',
						bclass :'back',
						displayname :'返回上级',
						onpress : operate
						} ],
					 	sortname: "seq",
						sortorder: "asc",
						usepager: true,
						title: '栏目列表',
						useRp: true,
						width: 'auto',
						height:'320',
						checkbox : true,// 是否要多选框
						showTableToggleBtn :false,
						rp: 10,
						onRowDblclick:rowdbclick
				});
				
				$("#sections").jqm({
			    modal : true,// 限制输入（鼠标点击，按键）的对话
			    overlay : 60 // 遮罩程度%
		        })
		        .jqmAddClose('.close')// 添加触发关闭的selector
		        .jqDrag('.drag');// 添加拖拽的selector
				NFInit();
			});
			function operate(com, grid) {
					
			   switch (com) {
			    case '新增':
			    addSection();
			    break;
			    case '修改':
			    updateSection();
			    break;
			    case '删除':
			    deleteSection();
			    break;
			    case '返回上级':
			    history.back();
			    break;
			   }
			}
			
			function addSection(){
				method = "POST";
				var data = new Array();
		  			$("#saveSections input[type='text']").each(function() {
			        $(this).val("");
		        });
		        
		        $("#magazineName").val(magazineName);
				$("#sections").jqmShow();
			}
			
			function updateSection(){
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
			    //alert(data[0]);
			    $("#sectionId").val(data[0]).attr("readonly","readonly");
			    $("#sectionName").val(data[1]);
			    $("#magazineName").val(data[2]);
			    $("#seq").val(data[3]);
			    $("#hide").val(data[4]);
			    $("#sections").jqmShow();
			}
			
			function deleteSection(ids){
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
					delSection(ids);
			    }
			}
			
			function check(){
				//var magazineId = $("#magazineId").val();
				var id = $("#sectionId").val();
				var name = $("#sectionName").val();
				var seq = $("#seq").val();
				var hide = $("#hide").val();
				//var userId = $("#userId").val();
				var options ;		
				if(name==""||name.length==0){
					alert("栏目名称不能为空!");
					return false;
				}else if(seq==""||seq.length==0){
					alert("序号不能为空!");
					return false;
				}else{
					if("POST"==method){
						options = {
							type:"POST",
							url:'section?_method=POST',
							dataType:"json", 
							success:function(json){ 
								$("#sections").jqmHide();
								$("#grid").flexReload();
								alert(json.msg);
							}
						}; 
					}else if("PUT"==method){
						options = {
							type:"PUT",
							url:'section?_method=PUT&magazineId='+magazineId+
							'&sectionId='+id+
							'&sectionName='+name+
							'&seq='+seq+
							'&hide='+hide,
							dataType:"json", 
							success:function(json){ 					
								$("#sections").jqmHide();
								$("#grid").flexReload();
								alert(json.msg);
							}
						}; 
					}
					
					$("#saveSections").ajaxSubmit(options);
					return false;//为了防止刷新 
				}
			}
			function delSection(ids) {
				 $.ajax({
				      url : 'section?_method=delete&rowid='+ids,
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
       				 栏目管理
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
		<div class="jqmWindow" style="width: 350px;" id="sections">
			<div class="drag">
			栏目信息编辑
			<div class="close"></div>
			</div>
			<div class="main_form" style="width: 350px;">
			<form id="saveSections" name="saveSections"  class="niceform">
				<input type="hidden" name="sectionId" id="sectionId" >
				<input type="hidden" name="magazineId" id="magazineId" value="${magazineId}">
					<fieldset >
					<dl>
						<dt>
							<label for="sectionName">
								栏目名:
							</label>
						</dt>
						<dd>
							<input type="text" name="sectionName" id="sectionName" >
						</dd>
					</dl>
					<dl>
						<dt>
							<label for="magazineName">
								杂志名:
							</label>
						</dt>
						<dd>
							<input type="text" name="magazineName" id="magazineName" value="${magazineName}" readonly="readonly">
						</dd>
					</dl>
					<dl>
						<dt>
							<label for="seq">
								显示序号:
							</label>
						</dt>
						<dd>
							<input type="text" name="seq" id="seq" >
						</dd>
					</dl>				
					<dl>
						<dt>
							<label for="hide">
								 是否显示:
							</label>
						</dt>
						<dd>
							<select name="hide" id="hide" size="1"  >
								<option value="1" >是</option>
								<option value="0" >否</option>
							</select>
						</dd>
					</dl>
				<dl class="submit">	
					<input type="button" id="submit" class="input-button" value="提交" onclick="return check();"/>
					<input type="reset" class="input-button" value="重置" />
				</dl>
				</fieldset>
			</form>
			</div>
		</div>
	</body>
</html>