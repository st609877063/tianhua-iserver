<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base>
        <title>Gift_detail 修改记录</title>
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

        <s:form name="form1" action="gift_detailupdate.action" method="post">
            <br>
            <table border="1" style="BORDER-COLLAPSE: collapse" width="40%"
                align="center" bgcolor="#FFFFCC">

                <tr>
                    <td align=center bgcolor="#CCFF66" colspan="2">
                        Gift_detail 修改记录
                    </td>
                    <s:hidden name="gift_detail.gift_id " value="%{gift_detail.gift_id}"></s:hidden>
                    <s:textfield name="gift_detail.gift_no" value="%{gift_detail.gift_no}" label="%{getText('gift_no')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_name" value="%{gift_detail.gift_name}" label="%{getText('gift_name')}"></s:textfield>
                    <s:textfield name="gift_detail.create_time" value="%{gift_detail.create_time}" label="%{getText('create_time')}"></s:textfield>
                    <s:textfield name="gift_detail.add_user_id" value="%{gift_detail.add_user_id}" label="%{getText('add_user_id')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_get_time" value="%{gift_detail.gift_get_time}" label="%{getText('gift_get_time')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_unit" value="%{gift_detail.gift_unit}" label="%{getText('gift_unit')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_num" value="%{gift_detail.gift_num}" label="%{getText('gift_num')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_num_total" value="%{gift_detail.gift_num_total}" label="%{getText('gift_num_total')}"></s:textfield>
                    <s:textfield name="gift_detail.keep_room_no" value="%{gift_detail.keep_room_no}" label="%{getText('keep_room_no')}"></s:textfield>
                    <s:textfield name="gift_detail.keep_cabinet_no" value="%{gift_detail.keep_cabinet_no}" label="%{getText('keep_cabinet_no')}"></s:textfield>
                    <s:textfield name="gift_detail.keep_cell_no" value="%{gift_detail.keep_cell_no}" label="%{getText('keep_cell_no')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_owner" value="%{gift_detail.gift_owner}" label="%{getText('gift_owner')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_owner_type" value="%{gift_detail.gift_owner_type}" label="%{getText('gift_owner_type')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_owner_name" value="%{gift_detail.gift_owner_name}" label="%{getText('gift_owner_name')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_owner_title" value="%{gift_detail.gift_owner_title}" label="%{getText('gift_owner_title')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_give_time" value="%{gift_detail.gift_give_time}" label="%{getText('gift_give_time')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_give_name" value="%{gift_detail.gift_give_name}" label="%{getText('gift_give_name')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_give_country" value="%{gift_detail.gift_give_country}" label="%{getText('gift_give_country')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_give_title" value="%{gift_detail.gift_give_title}" label="%{getText('gift_give_title')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_material" value="%{gift_detail.gift_material}" label="%{getText('gift_material')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_type" value="%{gift_detail.gift_type}" label="%{getText('gift_type')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_area" value="%{gift_detail.gift_area}" label="%{getText('gift_area')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_status" value="%{gift_detail.gift_status}" label="%{getText('gift_status')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_craft" value="%{gift_detail.gift_craft}" label="%{getText('gift_craft')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_background" value="%{gift_detail.gift_background}" label="%{getText('gift_background')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_desc" value="%{gift_detail.gift_desc}" label="%{getText('gift_desc')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_memo" value="%{gift_detail.gift_memo}" label="%{getText('gift_memo')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_video" value="%{gift_detail.gift_video}" label="%{getText('gift_video')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_attach" value="%{gift_detail.gift_attach}" label="%{getText('gift_attach')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_attach2" value="%{gift_detail.gift_attach2}" label="%{getText('gift_attach2')}"></s:textfield>
                    <s:textfield name="gift_detail.gift_attach3" value="%{gift_detail.gift_attach3}" label="%{getText('gift_attach3')}"></s:textfield>
                    <td colspan="2" align="center" >
                    <IMG NAME="m1" SRC="image\u_c2.gif" BORDER="0" vspace="0" hspace="0" ONMOUSEOVER="change('1','m1')" ONMOUSEOUT= "change('2','m1')" onClick="form1.submit()">
                    </td>
                    <!-- 也可以用下面的提交方式
                    <s:submit value="修 改" align="center"></s:submit>
                    --->
                    <s:hidden id="gift_no_c" name="gift_no_c" value="%{#request.gift_no_c}"></s:hidden>
                </tr>
            </table>
            <br>
            <center>
                <s:a href="gift_detaillist.action">
                    <font color="#808080" size="3">返回查询全部页面</font>
                </s:a>
            </center>
        </s:form>
        <!-- 抛出异常 -->
        <hr color="#CCCCFF">
        <s:actionerror cssStyle="color:blue" />
    </body>
</html>

