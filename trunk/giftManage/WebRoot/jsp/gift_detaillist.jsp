<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
    <head>
            <title>Gift_detail 操作主界面</title>
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
      //和ListGift_detailByGift_noAction.java文件里定义的默认值保持一致即可
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
        <s:form name="form1" action="gift_detaillistByGift_no.action" method="post" >
            <table border="1" style="BORDER-COLLAPSE: collapse" width="35%"
                align="center" bgcolor="#FFFFCC" >
                <tr align=center  bgcolor="#CCFF66">
                    <td colspan="2">
                        Gift_detail 自定义查询条件
                    </td>
                     <s:textfield id="gift_no_c" name="gift_no_c" label="gift_no"></s:textfield>
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
         <s:url id="url_savegift_detail" action="gift_detailsaveP">
             <s:param name="gift_no_c"><s:property value="#request.gift_no_c" /></s:param>
         </s:url>
         <s:a href="%{url_savegift_detail}">添加新记录</s:a>
        </table>
        <hr color="#CCCCFF">
        <table border="1" width="80%" align="center" bgcolor="#FFFFCC"
            style="BORDER-COLLAPSE: collapse" >

            <tr align="center" bgcolor="#CCFF66">

                 <td >
                    gift_id
                </td>
                 <td >
                    gift_no
                </td>
                 <td >
                    gift_name
                </td>
                 <td >
                    create_time
                </td>
                 <td >
                    add_user_id
                </td>
                 <td >
                    gift_get_time
                </td>
                 <td >
                    gift_unit
                </td>
                 <td >
                    gift_num
                </td>
                 <td >
                    gift_num_total
                </td>
                 <td >
                    keep_room_no
                </td>
                 <td >
                    keep_cabinet_no
                </td>
                 <td >
                    keep_cell_no
                </td>
                 <td >
                    gift_owner
                </td>
                 <td >
                    gift_owner_type
                </td>
                 <td >
                    gift_owner_name
                </td>
                 <td >
                    gift_owner_title
                </td>
                 <td >
                    gift_give_time
                </td>
                 <td >
                    gift_give_name
                </td>
                 <td >
                    gift_give_country
                </td>
                 <td >
                    gift_give_title
                </td>
                 <td >
                    gift_material
                </td>
                 <td >
                    gift_type
                </td>
                 <td >
                    gift_area
                </td>
                 <td >
                    gift_status
                </td>
                 <td >
                    gift_craft
                </td>
                 <td >
                    gift_background
                </td>
                 <td >
                    gift_desc
                </td>
                 <td >
                    gift_memo
                </td>
                 <td >
                    gift_video
                </td>
                 <td >
                    gift_attach
                </td>
                 <td >
                    gift_attach2
                </td>
                 <td >
                    gift_attach3
                </td>
                <td colspan="2">
                    function
                </td>
            </tr>

            <s:iterator value="#request.list" id="gift_detail" >
                <tr align="center" onmousemove="return this.bgColor='#FDF525';"  onmouseout="return this.bgColor='';">
                 <td >
                        <s:property value="#gift_detail.gift_id" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_no" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_name" />
                </td>
                 <td >
                        <s:property value="#gift_detail.create_time" />
                </td>
                 <td >
                        <s:property value="#gift_detail.add_user_id" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_get_time" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_unit" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_num" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_num_total" />
                </td>
                 <td >
                        <s:property value="#gift_detail.keep_room_no" />
                </td>
                 <td >
                        <s:property value="#gift_detail.keep_cabinet_no" />
                </td>
                 <td >
                        <s:property value="#gift_detail.keep_cell_no" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_owner" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_owner_type" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_owner_name" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_owner_title" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_give_time" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_give_name" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_give_country" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_give_title" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_material" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_type" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_area" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_status" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_craft" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_background" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_desc" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_memo" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_video" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_attach" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_attach2" />
                </td>
                 <td >
                        <s:property value="#gift_detail.gift_attach3" />
                </td>
                    <td>
                             <s:url id="url_deletegift_detail" action="gift_detailremove">
                             <s:param name="gift_detail.gift_id"><s:property value="#gift_detail.gift_id" /></s:param>
                             <s:param name="gift_no_c"><s:property value="#request.gift_no_c" /></s:param>
                               </s:url>
                               <s:a href="%{url_deletegift_detail}" onclick="return del();">删除</s:a>
                        <font color="#808080" size="3">|</font>
                             <s:url id="url_updategift_detail" action="gift_detailupdateP">
                             <s:param name="gift_detail.gift_id"><s:property value="#gift_detail.gift_id" /></s:param>
                             <s:param name="gift_no_c"><s:property value="#request.gift_no_c" /></s:param>
                               </s:url>
                               <s:a href="%{url_updategift_detail}">修改</s:a>
                        <font color="#808080" size="3">|</font>
                        <s:a href="#">其他功能</s:a>
                    </td>



                </tr>
            </s:iterator>
       <s:if test="#gift_detail">
          <% if (pageSize!=0) {%>
          <tr align="right">
              <td colspan=33>
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
              <!-- 判断 gift_detail对象是否为空的方法如下：-->
              <s:if test="!#gift_detail">
                    没有查询到任何记录!
              </s:if>
        </center><br>
        <center>
                              <s:url id="url_excelgift_detail" action="gift_detailgenerateExcel">
                              <s:param name="gift_no_c"><s:property value="#request.gift_no_c" /></s:param>
                              </s:url>
                              <s:a href="%{url_excelgift_detail}"><font color="#008000" size="3">生成 Excel 文件</font></s:a>
            <a href="index.jsp"><font color="#808080" size="3">返回首页</font>
            </a>
        </center>
    </body>
</html>

