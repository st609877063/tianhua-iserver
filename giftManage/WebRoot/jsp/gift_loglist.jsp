<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
    <head>
            <title>Gift_log 操作主界面</title>
<style>
.tdLabel {
    text-align: right;
}
</style>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">


    <SCRIPT LANGUAGE="JavaScript">
            imgsrc=new Array();
            imgsrc[1]="c_c1.gif";
            imgsrc[2]="c_c2.gif";

            img =new Array();
            for (i=0; i< imgsrc.length; i++) {
              img[i]=new Image();
              img[i].src="image\\"+imgsrc[i];
            }
            function change(number, picture) {
              {
                document[picture].src=img[number].src;
              }
            }
            function del()
            {
                if(confirm("你真的想删除该记录么？"))
                {
                    return true;
                }
                return false;
            }

           function openPage(curpage)
           {
                document.all("currentPage").value = curpage ;
                document.forms[0].submit();
           }

    </script>

    <link rel="stylesheet" href="css/table_design.css" type="text/css"></link></head>

    <body style="font-size:15px">
    <%
      //和ListGift_logByLog_contentAction.java文件里定义的默认值保持一致即可
      // 定义如下分页变量

      int currentPage =1;    // 1、定义当前是第几页，默认是第1页
      int lineSize= 10 ;     // 2、定义每页要显示的记录数，默认是每页10条
      int allRecorders = 0 ; // 3、计算出实际记录数，默认
      int pageSize= 0 ;      // 4、计算出实际页数，默认
    %>

    <%
      if(request.getAttribute("currentPage")!=null)
       currentPage=((Integer) request.getAttribute("currentPage") ).intValue();
      if(request.getAttribute("allRecorders")!=null)
       allRecorders=((Integer) request.getAttribute("allRecorders") ).intValue();
      if(request.getAttribute("currentPage")!=null&&request.getAttribute("allRecorders")!=null)
       pageSize = (allRecorders+lineSize-1)/lineSize ;
     %>


        <br>
        <s:form name="form1" action="gift_loglistByLog_content.action" method="post" >
            <table border="1" style="BORDER-COLLAPSE: collapse" width="35%"
                align="center" bgcolor="#FFFFCC" >
                <tr align=center  bgcolor="#CCFF66">
                    <td colspan="2">
                        Gift_log 自定义查询条件
                    </td>
                     <s:textfield id="log_content_c" name="log_content_c" label="log_content"></s:textfield>
                    <s:hidden  name="currentPage" value="1"></s:hidden>
                    <td colspan="2" align="center" >
                    <IMG NAME="m1" SRC="image\c_c2.gif" BORDER="0" vspace="0" hspace="0" ONMOUSEOVER="change('1','m1')" ONMOUSEOUT= "change('2','m1')" onClick="form1.submit()" title="温馨提示:查询全部记录，不用输入任何条件!">
                    </td>
                    <!-- 也可以用下面的提交方式
                    <s:submit name="s1" value="查 询" align="center"></s:submit>
                     -->
                </tr>
            </table>
        </s:form>
        <br>
        <table width="80%" align="center">
         <s:url id="url_savegift_log" action="gift_logsaveP">
             <s:param name="log_content_c"><s:property value="#request.log_content_c" /></s:param>
         </s:url>
         <s:a href="%{url_savegift_log}">添加新记录</s:a>
        </table>
        <hr color="#CCCCFF">
        <table border="1" width="80%" align="center" bgcolor="#FFFFCC"
            style="BORDER-COLLAPSE: collapse" >

            <tr align="center" bgcolor="#CCFF66">

                 <td >
                    log_id
                </td>
                 <td >
                    log_content
                </td>
                 <td >
                    log_uri
                </td>
                 <td >
                    log_type
                </td>
                 <td >
                    add_time
                </td>
                 <td >
                    add_user_id
                </td>
                <td colspan="2">
                    function
                </td>
            </tr>

            <s:iterator value="#request.list" id="gift_log" >
                <tr align="center" onmousemove="return this.bgColor='#FDF525';"  onmouseout="return this.bgColor='';">
                 <td >
                        <s:property value="#gift_log.log_id" />
                </td>
                 <td >
                        <s:property value="#gift_log.log_content" />
                </td>
                 <td >
                        <s:property value="#gift_log.log_uri" />
                </td>
                 <td >
                        <s:property value="#gift_log.log_type" />
                </td>
                 <td >
                        <s:property value="#gift_log.add_time" />
                </td>
                 <td >
                        <s:property value="#gift_log.add_user_id" />
                </td>
                    <td>
                             <s:url id="url_deletegift_log" action="gift_logremove">
                             <s:param name="gift_log.log_id"><s:property value="#gift_log.log_id" /></s:param>
                             <s:param name="log_content_c"><s:property value="#request.log_content_c" /></s:param>
                               </s:url>
                               <s:a href="%{url_deletegift_log}" onclick="return del();">删除</s:a>
                        <font color="#808080" size="3">|</font>
                             <s:url id="url_updategift_log" action="gift_logupdateP">
                             <s:param name="gift_log.log_id"><s:property value="#gift_log.log_id" /></s:param>
                             <s:param name="log_content_c"><s:property value="#request.log_content_c" /></s:param>
                               </s:url>
                               <s:a href="%{url_updategift_log}">修改</s:a>
                        <font color="#808080" size="3">|</font>
                        <s:a href="#">其他功能</s:a>
                    </td>



                </tr>
            </s:iterator>
       <s:if test="#gift_log">
          <% if (pageSize!=0) {%>
          <tr align="right">
              <td colspan=7>
              <input style="background-color:Transparent;border-left:none; border-top:none;border-right:none;border-bottom:none;cursor:hand;" type="button" value="首页" onClick="openPage(1)" <%=currentPage==1?"disabled":""%>>
              <input style="background-color:Transparent;border-left:none; border-top:none;border-right:none;border-bottom:none;cursor:hand;" type="button" value="上一页" onClick="openPage(<%=currentPage-1%>)" <%=currentPage==1?"disabled":""%>>
              <input style="background-color:Transparent;border-left:none; border-top:none;border-right:none;border-bottom:none;cursor:hand;" type="button" value="下一页" onClick="openPage(<%=currentPage+1%>)" <%=currentPage==pageSize?"disabled":""%>>
              <input style="background-color:Transparent;border-left:none; border-top:none;border-right:none;border-bottom:none;cursor:hand;" type="button" value="尾页" onClick="openPage(<%=pageSize%>)" <%=currentPage==pageSize?"disabled":""%>>
              <input style="background-color:Transparent;border-left:none; border-top:none;border-right:none;border-bottom:none;cursor:hand;" type="button" value=[<%=currentPage%>/<%=pageSize%>页]>              </td>
           </tr>
           <% }%>
       </s:if>
        </table>
        <br>
         <center>
              <!-- 判断 gift_log对象是否为空的方法如下：-->
              <s:if test="!#gift_log">
                    没有查询到任何记录!
              </s:if>
        </center><br>
        <center>
                              <s:url id="url_excelgift_log" action="gift_loggenerateExcel">
                              <s:param name="log_content_c"><s:property value="#request.log_content_c" /></s:param>
                              </s:url>
                              <s:a href="%{url_excelgift_log}"><font color="#008000" size="3">生成 Excel 文件</font></s:a>
            <a href="index.jsp"><font color="#808080" size="3">返回首页</font>
            </a>
        </center>
    </body>
</html>

