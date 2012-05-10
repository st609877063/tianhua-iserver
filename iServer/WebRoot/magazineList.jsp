<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common/taglibs.jsp" %>
<c:set var="user" value="${sessionScope.username}"/>
<c:set var="userId" value="${sessionScope.userId}"/>
<c:if test="${empty user}">
	<script>
	window.location.href ="login.jsp";
	</script>
</c:if>
<c:set var="fileLocation" value="${sessionScope.fileLocation}"/>
<html>
<head>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
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
	var method = "";
	var upload = "";

	function uploadStart(){
		upload.startUpload();
	}
	var rowdbclick = function(rowData) { 
		//alert($(rowData).data("magazineId").toString());
		//window.location.href = "sectionList.jsp?magazineId="+$(rowData).data("magazineId").toString();
		window.location.href = "sectionList.jsp?magazineId="+$(rowData).data("magazineId").toString()+
							   "&magazineName="+$(rowData).data("magazineName").toString();
	}
	
	$(document).ready(function(){
			var date = new Date();
			var timestamp = date.getTime();
			upload = new SWFUpload({
				// Backend Settings
				upload_url: "magazine/upload?_method=post&timestamp="+timestamp,
				// File Upload Settings
				file_size_limit : "10240000",	// 10MB
				file_types : "*.*",
				file_types_description : "All Files",
				file_upload_limit : "5",
				file_queue_limit : "0",
				// Event Handler Settings (all my handlers are in the Handler.js file)
				file_queued_handler : fileQueued,
				file_queue_error_handler : fileQueueError,
				file_dialog_complete_handler : fileDialogComplete,
				upload_start_handler : uploadStart,
				upload_progress_handler : uploadProgress,
				upload_error_handler : uploadError,
				upload_success_handler : uploadSuccess,
				upload_complete_handler : uploadComplete,
				// Button Settings
				button_image_url : "static/img/file.gif",
				button_placeholder_id : "spanButtonPlaceHolder",
				button_width: 58,
				button_height: 34,
				// Flash Settings
				flash_url : "static/js/swfupload/swfupload.swf",
				custom_settings:{progressTarget : "fsUploadProgress",cancelButtonId : "btnCancel"},
				debug: false
			});
			
		function uploadSuccess(file, result) { 
			try {
				//var progress = new FileProgress(file, this.customSettings.progressTarget);
				//progress.setComplete();
				//progress.setStatus("上传完成.");
				//progress.toggleCancel(false);
				//$("#magazinePicture").val(decodeURIComponent(timestamp+file.name));
				$("#magazinePicture").val(decodeURIComponent(timestamp+".jpg"));
				alert("文件上传成功");
			} catch (ex) { this.debug(ex); } 
		} 
		
		$.ajax({
					method:'GET',
					url:'magazine/type',
					dataType:'text',
					success:function(result)
					{
						$("#mt").html(result);
						NFInit();
  					},
					error:function()
					{
						alert("警告:服务器繁忙请稍候再试!");
					}
		});
	
		$("#grid").flexigrid({
			url:'magazine?_method=get',
			dataType:'json',
				colModel : [
					{display: '杂志ID', id:'magazineId',name :'magazineId', width : 100, sortable : false, align: 'left',hide:true},
					{display: '杂志名称', id:'magazineName',name :'magazineName', width : 150, sortable : false, align: 'left'},
					{display: '杂志类型', id:'magazineClass.magazineClassName',name :'magazineClassName', width : 100, sortable : false, align: 'left'},
					{display: '杂志期数',id:'phase', name :'phase', width : 100, sortable : true, align: 'left'},
					{display: '浏览次数',id:'viewCounter', name :'viewCounter', width : 50, sortable : false, align: 'left'},
					{display: '下载次数',id:'downloadCounter', name :'downloadCounter', width : 50, sortable : false, align: 'left'},
					{display: '创建时间',id:'createDate', name :'createDate', width : 100, sortable : false, align: 'left'},
					{display: '封面图片',id:'magazinePicture', name :'magazinePicture', width : 200, sortable : false, align: 'left'},
					{display: '是否显示',id:'showStatus', name :'showStatus', width : 50, sortable : false, align: 'left'}
					],
				buttons : [ {
					name :'新增',
					bclass :'add',
					displayname :'新增',
					onpress : operate
					}, {
		            separator : true
	            	}, 
	            	{
					name :'删除',
					bclass :'delete',
					displayname :'删除',
					onpress :operate
					}, 
					{
		            separator : true
	            	}, {
					name :'修改',
					bclass :'edit',
					displayname :'修改',
					onpress : operate
					}, {
		            separator : true
	            	}, {
					name :'预览封面',
					bclass :'photo',
					displayname :'预览封面',
					onpress : operate
					}, {
		            separator : true
	            	}, {
					name :'查看栏目',
					bclass :'section',
					displayname :'查看栏目',
					onpress : operate
					}, {
		            separator : true
	            	}, {
					name :'查看文章',
					bclass :'articleList',
					displayname :'查看文章',
					onpress : operate
					}   ],
				 	sortname: "phase",
					sortorder: "asc",
					usepager: true,
					title: '杂志列表',
					useRp: true,
					width: 'auto',
					height:'320',
					showTableToggleBtn :false,
					checkbox :true,
					rp: 10,
					onRowDblclick:rowdbclick
			});
			$("#magazines").jqm({
		    modal : true,// 限制输入（鼠标点击，按键）的对话
		    overlay : 60 // 遮罩程度%
	        })
	        .jqmAddClose('.close')// 添加触发关闭的selector
	        .jqDrag('.drag');// 添加拖拽的selector
	       
	       	$("#magazineImage").jqm({
		    modal : true,// 限制输入（鼠标点击，按键）的对话
		    overlay : 60 // 遮罩程度%
	        })
	        .jqmAddClose('.close')// 添加触发关闭的selector
	        .jqDrag('.drag');// 添加拖拽的selector
			
		});
    
	function operate(com, grid) {
	   switch (com) {
	    case '新增':
	     addMagazine();
	     break;
	    case '修改':
	     updateMagazine();
	     break;
	    case '删除':
	     deleteMagazine();
	     break;
	    case '预览封面':
	     viewMagazine();
	     break;
	    case '查看栏目':
	     viewSection();
	     break;
	    case '查看文章':
	     viewArticle();
	     break;

	   }
	}
	
	function loadleft(){
		top.frames["left"].location.reload();
	}
	
	function addMagazine() {
		method = "POST";
		var data = new Array();
  			$("#saveMagazines input[type='text']").each(function() {
	        $(this).val("");
        });
		$("#magazines").jqmShow();
	}
	function updateMagazine(){
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

		    $('#saveMagazines input[name="magazineId"]').val(data[0]).attr("readonly","readonly");
		    $('#saveMagazines input[name="magazineName"]').val(data[1]);
		    $('#saveMagazines select[name="magazineClassId"]').val(data[2]);
		    $('#saveMagazines input[name="phase"]').val(data[3]);
		    $('#saveMagazines input[name="magazinePicture"]').val(data[7]);
		    $('#saveMagazines input[name="showStatus"]').val(data[8]);
		    $("#magazines").jqmShow();
	}
	
	function deleteMagazine(){
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
	    if (confirm("确定删除杂志[" + names + "]?")) {
			delMagazine(ids);
	    }
	}
	
	function viewMagazine(){
	    var selected_count = $('.trSelected', grid).length;
		    if (selected_count == 0) {
			    alert('请选择一条记录!');
			    return;
		    }
		    if (selected_count > 1) {
			    alert('最多只能选择一条记录!');
			    return;
		    }
		    var data = new Array();
		    $('.trSelected td', grid).each(function(i) {
			        data[i] = $(this).children('div').text();
		    });
		
		var pic ="";
		if(data[7]==""||data[7].length<=1) 
			pic = "default.jpg"
		else
			pic = data[7];
		var path = "static"+"${fileLocation}"+"/";
		var img = $("#showImage").attr("src",path+pic);
		$("#magazineImage").css({'width':img.attr('width')}).css({'height':img.attr('height')}).jqmShow();	
	}
	
	function viewSection(){
		var selected_count = $('.trSelected', grid).length;
	    if (selected_count == 0) {
		    alert('请选择一条记录!');
		    return;
	    }
	    if (selected_count > 1) {
		    alert('最多只能选择一条记录!');
		    return;
	    }
	    var data = new Array();
	    $('.trSelected td', grid).each(function(i) {
		        data[i] = $(this).children('div').text();
	    });
	    //top.frames["main_right"].location.reload();
	    window.location.href = "sectionList.jsp?magazineId="+data[0]+"&magazineName="+data[1];
	}	
	
	function viewArticle(){
		var selected_count = $('.trSelected', grid).length;
	    if (selected_count == 0) {
		    alert('请选择一条记录!');
		    return;
	    }
	    if (selected_count > 1) {
		    alert('最多只能选择一条记录!');
		    return;
	    }
	    var data = new Array();
	    $('.trSelected td', grid).each(function(i) {
		        data[i] = $(this).children('div').text();
	    });
	    
		window.location.href = "articleList.jsp?magazineId="+data[0];
	}
	
	function check(){
		var id = $("#magazineId").val();
		var name = $("#magazineName").val();
		var phase = $("#phase").val();
		var classid = $("#magazineClassId").val();
		var magazinePicture = $("#magazinePicture").val();
		var userId = $("#userId").val();
		var options ;		
		if(name==""||name.length==0){
			alert("杂志名称不能为空!");
			return false;
		}else if(phase==""||phase.length==0){
			alert("期数不能为空!");
			return false;
		}else{
			if("POST"==method){
				options = {
					type:"POST",
					url:'magazine?_method=POST',
					dataType:"json", 
					success:function(json){ 
						$("#magazines").jqmHide();
						$("#grid").flexReload();
						alert(json.msg);
					}
				}; 
			}else if("PUT"==method){
				options = {
					type:"PUT",
					url:'magazine?_method=PUT&magazineId='+id+
					'&magazineName='+name+
					'&phase='+phase+
					'&magazineClassId='+classid+
					'&userId='+userId+
					'&magazinePicture='+magazinePicture,
					dataType:"json", 
					success:function(json){ 					
						$("#magazines").jqmHide();
						$("#grid").flexReload();
						alert(json.msg);
					}
				}; 
			}
			
			$("#saveMagazines").ajaxSubmit(options);
			return false;//为了防止刷新 
		}
	}
	
	function delMagazine(ids) {
		 $.ajax({
		      url : 'magazine?_method=delete&rowid='+ids,
		      type : 'DELETE',
		      dataType : 'json',
		      success : function(json) {
					//document.frames("main_right").reload();		
					alert(json.msg);
					$("#grid").flexReload();
		      }
		     });
	}
</script>

<title>杂志管理</title>
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
       				 杂志管理
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
 	
	<div class="jqmWindow" id="magazineImage">
		<div  class="drag"  >
			封面预览
			<div class="close" ></div>
		</div>
		<div  style="align:center">
			<img id="showImage" src="" width="300" height="350" >
		</div>
	</div>
	
	<div class="jqmWindow" style="width: 350px;" id="magazines">
		<div class="drag">
		杂志信息编辑
		<div class="close"></div>
		</div>
		<div class="main_form" style="width: 350px;">
		<form id="saveMagazines" name="saveMagazines" class="niceform"  >
			<input type="hidden" name="magazineId" id="magazineId" >
			<input type="hidden" name="userId" id="userId" value="${userId}" >
			<fieldset>
			<dl>
				<dt><label for="magazineName">杂志名称:</label></dt>
				<dd><input type="text" name="magazineName" id="magazineName" ></dd>
			</dl>
			<dl>
				<dt><label for="mt">杂志类型:</label></dt>
				<dd id="mt"></dd>
			</dl>
			<dl>
				<dt><label for="phase">杂志期数:</label></dt>
				<dd><input type="text" name="phase" id="phase" ></dd>
			</dl>
			<dl>
				<dt><label >杂志封面:</label></dt>
				<dd>
					<div id="fsUploadProgress"></div>
	 				<div>
					<span><input type="text" width="100" readonly="readonly" name="magazinePicture" id="magazinePicture"></span>
					<span id="spanButtonPlaceHolder"></span>
					<input id="btnCancel"  type="hidden" style="display:none" value=""  disabled="disabled"/>
					</div>
				</dd>
			</dl>
			<dl>
				<dt><label for="showStatus">显示状态:</label></dt>
				<dd>
					<select id="showStatus" name="showStatus" size="1">
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
