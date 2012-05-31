<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="common/taglibs.jsp"%>
<c:set var="magazineId" value="${param.magazineId}"/>
<c:set var="magazineName" value="${param.magazineName}"/>
<c:set var="sectionId" value="${param.sectionId}"/>
<c:set var="sectionName" value="${param.sectionName}"/>
<c:set var="articleId" value="${param.articleId}"/>
<c:set var="articleName" value="${param.articleName}"/>
<c:set var="articlePicture" value="${param.articlePicture}"/>
<c:set var="commentState" value="${param.commentState}"/>
<c:set var="seq" value="${param.seq}"/>
<c:set var="articleDesc" value="${param.articleDesc}"/>
<c:set var="articleTop" value="${param.articleTop}"/>
<c:set var="articleRecommend" value="${param.articleRecommend}"/>
<c:set var="author" value="${param.author}"/>
<c:set var="shareLink" value="${param.shareLink}"/>
<c:set var="method" value="${param.method}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
//String commentState = request.getParameter("commentState");
//String articleTop = request.getParameter("articleTop");
 %>
<c:set var="fileLocation" value="${sessionScope.fileLocation}"/>
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=utf-8">
		<link href="static/images/Guide.css" type=text/css rel=stylesheet>
		<link href="static/images/index.css" type=text/css rel=stylesheet>
		<link href="static/images/MasterPage.css" type=text/css rel=stylesheet>
		<link href="static/jqModal/css/jqModal_blue.css" type="text/css" rel="stylesheet" >
		<link href="static/css/niceforms-main.css" type="text/css" rel="stylesheet" />
		<script src="static/js/jquery.js" type=text/javascript></script>
		<script src="static/js/jquery.form.js" type=text/javascript></script>
		<!-- CKEDITOR -->
		<script type="text/javascript" src="static/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="static/ckeditor/config.js"></script>
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
		<script type="text/javascript">
		<!--
		//var editor = CKEDITOR.replace("content");
		//CKFinder.SetupCKEditor(editor) ;
		//-->
		var magazineId="";
		var articleId="";
		var method ="";
		var upload ="";
		
		function viewArticle(){

			var pic =$("#articlePicture").val();
			if(pic==""||pic.length<=1) 
				pic = "default.jpg"
			//alert(pic);
			var path = "static"+"${fileLocation}"+"/";
			var img = $("#showImage").attr("src",path+pic);
			$("#articleImage").css({'width':img.attr('width')}).css({'height':img.attr('height')}).jqmShow();	
		}

		function uploadStart(){
			upload.startUpload();
		}
		
		$(document).ready(function(){
					var date = new Date();
					var timestamp = date.getTime();
					upload = new SWFUpload({
					// Backend Settings
					upload_url: "article/upload?_method=post&timestamp="+timestamp,
					// File Upload Settings
					file_size_limit : "102400",	// 100MB
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
					button_image_url : "static/img/file.png",
					button_placeholder_id : "spanButtonPlaceHolder",
					button_width: 58,
					button_height: 20,
					// Flash Settings
					flash_url : "static/js/swfupload/swfupload.swf",
					custom_settings:{progressTarget : "fsUploadProgress",cancelButtonId : "btnCancel"},
					debug: false
				});		
				
				function uploadError(file, result) { 
					try {
						alert("文件上传失败.");
					} catch (ex) { this.debug(ex); } 
				} 
					
				function uploadSuccess(file, result) { 
					try {
						//var progress = new FileProgress(file, this.customSettings.progressTarget);
						//progress.setComplete();
						//progress.setStatus("上传完成.");
						//progress.toggleCancel(false);
						//$("#articlePicture").val(decodeURIComponent(timestamp+file.name));
						$("#articlePicture").val(decodeURIComponent(timestamp+".jpg"));
						alert("文件上传成功.");
					} catch (ex) { this.debug(ex); } 
				} 
			
				magazineId = '${magazineId}';
				articleId = '${articleId}';
				method= '${method}';
				$.ajax({
					method:'GET',
					url:'magazine/name?_method=get&magazineId='+magazineId,
					dataType:'text',
					success:function(result)
					{
						$("#magazineList").html(result);	
						$.ajax({
						method:'GET',
						url:'section/name?_method=get&magazineId='+magazineId,
						dataType:'text',
						success:function(result)
						{
							$("#sectionList").html(result);		
											
							if(method=="put"){
								getArticle();
							}
				
		 				},
						error:function()
						{
							alert("警告:服务器繁忙请稍候再试!");
						}
						});					
	 				},
					error:function()
					{
						alert("警告:服务器繁忙请稍候再试!");
					}
				});
				
				$("#articleImage").jqm({
			    modal : true,// 限制输入（鼠标点击，按键）的对话
			    overlay : 60 // 遮罩程度%
		        })
		        .jqmAddClose('.close')// 添加触发关闭的selector
		        .jqDrag('.drag');// 添加拖拽的selector
		});

	
		function getSectionName(){
				magazineId = $("#magazineName").val();
				$.ajax({
				method:'GET',
				url:'section/name?magazineId='+magazineId,
				dataType:'text',
				success:function(result)
				{
					$("#sectionList").html(result);			
 				},
				error:function()
				{
					alert("警告:服务器繁忙请稍候再试!");
				}
				});	
		}
		
		function getArticleById(articleId){
				$.ajax({
				method:'GET',
				url:'article/name?_method=get&articleId='+articleId,
				dataType:'text',
				success:function(result)
				{
						article.setData(result);
						//$("#articleContent").val(result);							
 				},
				error:function()
				{
					alert("警告:服务器繁忙请稍候再试!");
				}
				});
		}
		function getArticle(){
				$("#articleName").val("${articleName}");
				$("#magazineName").val("${magazineName}");
				$("#sectionName").val("${sectionName}");
				$("#articleDesc").val("${articleDesc}");
				$("#articleName").val("${articleName}");
				$("#articleTop").val("${articleTop}");
				$("#commentState").val("${commentState}");
				$("#articleRecommend").val("${articleRecommend}");
				$("#seq").val("${seq}");
				$("#articlePicture").val("${articlePicture}");
				$("#author").val("${author}");
				$("#shareLink").val("${shareLink}");
				$("#seq").val("${seq}");
				getArticleById(articleId);
		}
		
		function encodeStr(str)
		{
	        str = str.replaceAll("&nbsp;"," ");
	        str = str.replaceAll("&rdquo;", "”");
        	str = str.replaceAll("&ldquo;", "“");
        	str = str.replaceAll("&gt;", ">");
        	str = str.replaceAll("&lt;", "<");
        	str = str.replaceAll("&quot;", "\"");
        	str = str.replaceAll("&hellip;","...");
        	str = str.replaceAll("&middot;","·");
        	str = str.replaceAll("&mdash;","—");
        	str = str.replaceAll("&lsquo;","‘");
        	str = str.replaceAll("&rsquo;","’");
        	str = str.replaceAll("%","％");
	        return str;
		}
		String.prototype.replaceAll = function(s1,s2) {
			return this.replace(new RegExp(s1,"gm"),s2);		
		}
		function check(){
				var magazineId = $("#magazineName").val();
				var sectionId = $("#sectionName").val();
				var articleContent = encodeStr(article.getData());
				var articlePicture = $("#articlePicture").val();
				var author = $("#author").val();
				var shareLink = $("#shareLink").val();
				var articleName = $("#articleName").val();
				var commentState = $("#commentState").val();
				var articleDesc = $("#articleDesc").val();
				var articleTop = $("#articleTop").val();
				var articleRecommend = $("#articleRecommend").val();
				var seq = $("#seq").val();
				var options ;	
				$("#articleCont").val(articleContent);
				alert($("#articleCont").val())
				if(articleName==""||articleName.length==0){
					alert("文章名称不能为空!");
					return false;
				}else if(articleContent==""){
					alert("文章内容不能为空!");
					return false;
				}else if(sectionId==""){
					alert("栏目名称不能为空!");
					return false;
				}else if(author==""){
					alert("文章作者不能为空!");
					return false;
				}else if(shareLink==""){
					alert("文章分享链接不能为空!");
					return false;
				}else if(seq==""||seq.length==0){
					alert("显示序号不能为空!");
					return false;
				}else if(commentState==""||commentState.length==0){
					alert("是否评论不能为空!");
					return false;
				}else{
					if("post"==method){
						options = {
							type:"post",
							url:'article?_method=POST'+
							'&magazineId='+magazineId+
							'&sectionId='+sectionId,
							dataType:"json", 
							clearForm: true,
							success:function(json){ 
								alert(json.msg);
								window.location.href="articleList.jsp?magazineId="+magazineId;
							}
						}; 
						$("#articleForm").ajaxSubmit(options);
						return false;//为了防止刷新 
					}else if("put"==method){
						options = {
							type:"post",
							url:'article/put?_method=post'+
							'&magazineId='+magazineId+
							'&sectionId='+sectionId+
							//'&seq='+seq+
							'&articleId='+"${articleId}",							
							//'&articleName='+articleName+
							//'&commentState='+commentState+
							//'&articleDesc='+articleDesc+
							//'&articleTop='+articleTop+
							//'&articlePicture='+articlePicture+
							//'&author='+author,
							//'&articleContent='+articleContent,
							dataType:"json", 
							clearForm: true,
							success:function(json){ 					
								alert(json.msg);
								window.location.href="articleList.jsp?magazineId="+magazineId;
								}
							}; 					
							$("#articleForm").ajaxSubmit(options);
							return false;  
					}

				}
			}
  		</script>
	</head>
	
	<body id="MasterPagebody">
	<form id="articleForm"  >
		<input type="hidden" name="articleContent"  id="articleCont"  > 
		<table class="user_border" cellSpacing="0" cellPadding="0" 
			align=center style="width: 98%;" border="0" id="table1">
			<tr>
				<td valign="bottom">
					<table class="user_box" cellSpacing="0" cellPadding="5"
						width="100%" border="0">
						<tr>
							<td align="left">
								<span style="font-size: 12pt; font-weight: bold; color: #3666AA">
									<img src="static/images/icon.gif" style="border-width: 0px;" />
									新增文章 </span>
							</td>
							<td align="left" valign="bottom">
										<span >文章名称:
										<input type="text" id="articleName" name="articleName" width="100">
										</span>
										杂志名称:<span id="magazineList"></span>
										栏目名称:<span id="sectionList"></span>
										重磅推荐:<span>
										<select id="articleRecommend" name="articleRecommend">
											<option value="0" >否</option>
											<option value="1" >是</option>
										</select>
										</span>
										是否评论:<span>
										<select id="commentState" name="commentState">
											<option value="1" >是</option>
											<option value="0" >否</option>
										</select>
										</span>
										<span>
										文章置顶:
										<select id="articleTop" name="articleTop">
											<option value="0" >否</option>
											<option value="1" >是</option>
											
										</select>
										</span>
							</td>
						</tr>
						<tr>
							<td align="left">
								<span style="font-size: 12pt; font-weight: bold; color: #3666AA">
									&nbsp;&nbsp;&nbsp;&nbsp;</span>
								
							</td>
							<td align="left" valign="bottom">		
								<div id="fsUploadProgress"></div>
				 				<div>
								文章摘要:
								<input type="text" id="articleDesc" name="articleDesc" size="48" />
								文章作者:
								<input type="text" id="author" name="author" size="10" />
								文章图片:
								<input type="text" readonly="readonly" name="articlePicture" id="articlePicture">								
								<span id="spanButtonPlaceHolder"></span>
								<input id="btnCancel"  type="hidden" style="display:none" value=""  disabled="disabled" >
								<input type="button"  id="viewArticlePicture" name="viewArticlePicture" value="预览图片" onclick=viewArticle() >
								</div>
							</td>
						</tr>
						<tr>
							<td align="left">
								<span style="font-size: 12pt; font-weight: bold; color: #3666AA">&nbsp;&nbsp;&nbsp;&nbsp;</span>
							</td>
							<td align="left" valign="bottom">		
								文章微博分享链接:
								<input type="text" id="shareLink" name="shareLink" size="70" />
								显示序号:
								<input type="text" id="seq" name="seq" size="5" value="0"/>
								图片上传后，请务必“预览图片”
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<br />
		<div class="jqmWindow" id="articleImage">
			<div  class="drag"  >
				封面预览
				<div class="close" ></div>
			</div>
			<div  style="align:center">
				<img id="showImage" src="" width="350" height="255" >
			</div>
		</div>
		<textarea cols="80" id="editor1" name="editor1" >
	    </textarea>
		<script type="text/javascript">
	     var article = CKEDITOR.replace('editor1',
	     {
	      skin : 'kama',
	      language : 'zh-cn',
	      height :'260'
	     });
	   	</script>
		<span style="text-align:center">
   		<input type="submit" class="input-button" value="提交" onclick="return check();"/>
   		<input type="button" class="input-button" value="返回" onclick="javascript:history.back()" />
   		</span>
		</form>
	</body>
</html>
