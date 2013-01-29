<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="java.util.*" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script type="text/javascript" src="static/ckeditor/config.js"></script>
  <script type="text/javascript" src="static/ckeditor/ckeditor.js"></script>
  <script type="text/javascript">
	<!--
	//var editor = CKEDITOR.replace("content");
	//CKFinder.SetupCKEditor(editor) ;
	//-->
	</script>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  </head>
  
  <body>
  <%
    String content=request.getParameter("editor1");
    if(content!=null&&!content.equals("")){
     out.println(content);
    }
   %>
     <form name="testForm" method="post" action="<%=path %>/addMagazine.jsp">
     <textarea  cols="80" id="editor1" name="editor1" rows="10">
      在此添加内容
     </textarea>
     <script type="text/javascript">
    CKEDITOR.replace( 'editor1',
     {
      skin : 'kama',
      language : 'zh-cn'
     });
   </script>
     <input type="submit" value="提交"/>
     <input type="button" value="返回" onclick="javascript:history.go(-1)"/>
    </form>
  </body>
</html>
