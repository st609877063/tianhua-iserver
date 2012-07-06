<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>

        <title>Gift_log 新增记录</title>

<style>
.tdLabel {
    text-align: right;
}
.errorLabel {font-style:italic; color:red; }
.errorMessage {font-weight:bold; color:red; }
</style>
<link rel="stylesheet" href="css/table_design.css" type="text/css"></link>
<SCRIPT LANGUAGE="JavaScript">
            imgsrc=new Array();
            imgsrc[1]="s_c1.gif";
            imgsrc[2]="s_c2.gif";
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
</script>
    </head>

    <body style="font-size:15px">

        <s:form name="form1" action="gift_logsave.action" method="post">
            <br>
            <table border="1" style="BORDER-COLLAPSE: collapse" width="40%"
                align="center" bgcolor="#FFFFCC">
                <tr>
                    <td align=center bgcolor="#CCFF66" colspan="2">
                        Gift_log 新增记录
                    </td>
                  <s:textfield name="gift_log.log_content" label="%{getText('log_content')}"></s:textfield>
                  <s:textfield name="gift_log.log_uri" label="%{getText('log_uri')}"></s:textfield>
                  <s:textfield name="gift_log.log_type" label="%{getText('log_type')}"></s:textfield>
                  <s:textfield name="gift_log.add_time" label="%{getText('add_time')}"></s:textfield>
                  <s:textfield name="gift_log.add_user_id" label="%{getText('add_user_id')}"></s:textfield>
                    <td colspan="2" align="center" >
                    <IMG NAME="m1" SRC="image\s_c2.gif" BORDER="0" vspace="0" hspace="0" ONMOUSEOVER="change('1','m1')" ONMOUSEOUT= "change('2','m1')" onClick="form1.submit()">
                    </td>
                    <s:hidden id="log_content_c" name="log_content_c" value="%{#request.log_content_c}"></s:hidden>
                </tr>
            </table>
            <br>
            <center>
                <s:a href="gift_loglist.action">
                    <font color="#808080" size="3">返回查询全部页面</font>
                </s:a>
            </center>
        </s:form>
        <!-- 抛出异常 -->
        <hr color="#CCCCFF">
        <s:actionerror cssStyle="color:blue" />


    </body>
</html>
