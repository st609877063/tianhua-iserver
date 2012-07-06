<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base>
        <title>Gift_operation 修改记录</title>
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
            imgsrc[1]="u_c1.gif";
            imgsrc[2]="u_c2.gif";

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
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

    </head>

    <body style="font-size:15px">

        <s:form name="form1" action="gift_operationupdate.action" method="post">
            <br>
            <table border="1" style="BORDER-COLLAPSE: collapse" width="40%"
                align="center" bgcolor="#FFFFCC">

                <tr>
                    <td align=center bgcolor="#CCFF66" colspan="2">
                        Gift_operation 修改记录
                    </td>
                    <s:hidden name="gift_operation.oper_id " value="%{gift_operation.oper_id}"></s:hidden>
                    <s:textfield name="gift_operation.oper_name" value="%{gift_operation.oper_name}" label="%{getText('oper_name')}"></s:textfield>
                    <s:textfield name="gift_operation.oper_parent" value="%{gift_operation.oper_parent}" label="%{getText('oper_parent')}"></s:textfield>
                    <s:textfield name="gift_operation.oper_uri" value="%{gift_operation.oper_uri}" label="%{getText('oper_uri')}"></s:textfield>
                    <s:textfield name="gift_operation.create_time" value="%{gift_operation.create_time}" label="%{getText('create_time')}"></s:textfield>
                    <s:textfield name="gift_operation.add_user_id" value="%{gift_operation.add_user_id}" label="%{getText('add_user_id')}"></s:textfield>
                    <td colspan="2" align="center" >
                    <IMG NAME="m1" SRC="image\u_c2.gif" BORDER="0" vspace="0" hspace="0" ONMOUSEOVER="change('1','m1')" ONMOUSEOUT= "change('2','m1')" onClick="form1.submit()">
                    </td>
                    <!-- 也可以用下面的提交方式
                    <s:submit value="修 改" align="center"></s:submit>
                    --->
                    <s:hidden id="oper_name_c" name="oper_name_c" value="%{#request.oper_name_c}"></s:hidden>
                </tr>
            </table>
            <br>
            <center>
                <s:a href="gift_operationlist.action">
                    <font color="#808080" size="3">返回查询全部页面</font>
                </s:a>
            </center>
        </s:form>
        <!-- 抛出异常 -->
        <hr color="#CCCCFF">
        <s:actionerror cssStyle="color:blue" />
    </body>
</html>

