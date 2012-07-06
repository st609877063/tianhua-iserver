package bnu.workFlowBiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.actionsoft.awf.organization.control.MessageQueue;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.loader.core.WorkFlowStepRTClassA;
import com.actionsoft.sdk.AWSSDKException;
import com.actionsoft.sdk.local.level0.BOInstanceAPI;

//科研情况_专利信息 

public class ZlxxAfterSaveBiz extends WorkFlowStepRTClassA {
	
	public ZlxxAfterSaveBiz(UserContext uc){
		super(uc);
		super.setDescription("科研情况_专利信息_保存成功:更改教工号,更新共享数据");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}
	
	@Override
	public boolean execute() {
		System.out.println(".....................科研情况_专利信息_保存:更改教工号,更新共享数据begin.....................");
		
		int workflowInstanceId = getParameter(this.PARAMETER_INSTANCE_ID).toInt();//获得当前工作流的id
		
		Hashtable rsTable = BOInstanceAPI.getInstance().getBOData("BO_ZLXXTEMP", workflowInstanceId);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			conn.setAutoCommit(true);
			
			int mainDataId = 0;
			String loginId = "";
			String loginId1 = "";
			String loginId2 = "";
			String loginId3 = "";
			String loginId4 = "";
			String loginId5 = "";

			String copyDataStr = rsTable.get("COPYDATAID").toString() == null ? "" : rsTable.get("COPYDATAID").toString().trim();
			String createuser = rsTable.get("CREATEUSER").toString() == null ? "" : rsTable.get("CREATEUSER").toString().trim();
			
			String patentname = rsTable.get("PATENTNAME").toString() == null ? "" : rsTable.get("PATENTNAME").toString().trim();
			String patentkindsymbol = rsTable.get("PATENTKINDSYMBOL").toString() == null ? "" : rsTable.get("PATENTKINDSYMBOL").toString().trim();
			String applicant = rsTable.get("APPLICANT").toString() == null ? "" : rsTable.get("APPLICANT").toString().trim();
			String applydate = rsTable.get("APPLYDATE").toString() == null ? "" : rsTable.get("APPLYDATE").toString().trim();
			String publishdate = rsTable.get("PUBLISHDATE").toString() == null ? "" : rsTable.get("PUBLISHDATE").toString().trim();
			String deptname = rsTable.get("DEPTNAME").toString() == null ? "" : rsTable.get("DEPTNAME").toString().trim();
			String authorizedate = rsTable.get("AUTHORIZEDATE").toString() == null ? "" : rsTable.get("AUTHORIZEDATE").toString().trim();
			String patentee = rsTable.get("PATENTEE").toString() == null ? "" : rsTable.get("PATENTEE").toString().trim();
			String authorizeunit = rsTable.get("AUTHORIZEUNIT").toString() == null ? "" : rsTable.get("AUTHORIZEUNIT").toString().trim();
			String zlh = rsTable.get("ZLH").toString() == null ? "" : rsTable.get("ZLH").toString().trim();
			String zsbh = rsTable.get("ZSBH").toString() == null ? "" : rsTable.get("ZSBH").toString().trim();
			String chargeid = rsTable.get("CHARGEID").toString() == null ? "" : rsTable.get("CHARGEID").toString().trim();
			String name1 = rsTable.get("NAME1").toString() == null ? "" : rsTable.get("NAME1").toString().trim();
			String id1 = rsTable.get("ID1").toString() == null ? "" : rsTable.get("ID1").toString().trim();
			String name2 = rsTable.get("NAME2").toString() == null ? "" : rsTable.get("NAME2").toString().trim();
			String id2 = rsTable.get("ID2").toString() == null ? "" : rsTable.get("ID2").toString().trim();
			String name3 = rsTable.get("NAME3").toString() == null ? "" : rsTable.get("NAME3").toString().trim();
			String id3 = rsTable.get("ID3").toString() == null ? "" : rsTable.get("ID3").toString().trim();
			String name4 = rsTable.get("NAME4").toString() == null ? "" : rsTable.get("NAME4").toString().trim();
			String id4 = rsTable.get("ID4").toString() == null ? "" : rsTable.get("ID4").toString().trim();
			String name5 = rsTable.get("NAME5").toString() == null ? "" : rsTable.get("NAME5").toString().trim();
			String id5 = rsTable.get("ID5").toString() == null ? "" : rsTable.get("ID5").toString().trim();
//			int chargerate = Integer.parseInt(rsTable.get("CHARGERATE").toString() == null ? "0" : rsTable.get("CHARGERATE").toString().trim());
//			int rate1 = Integer.parseInt(rsTable.get("RATE1").toString() == null ? "0" : rsTable.get("RATE1").toString().trim());
//			int rate2 = Integer.parseInt(rsTable.get("RATE2").toString() == null ? "0" : rsTable.get("RATE2").toString().trim());
//			int rate3 = Integer.parseInt(rsTable.get("RATE3").toString() == null ? "0" : rsTable.get("RATE3").toString().trim());
//			int rate4 = Integer.parseInt(rsTable.get("RATE4").toString() == null ? "0" : rsTable.get("RATE4").toString().trim());
//			int rate5 = Integer.parseInt(rsTable.get("RATE5").toString() == null ? "0" : rsTable.get("RATE5").toString().trim());
			//科研去除工作量。。。
			int chargerate = 0;
			int rate1 = 0;
			int rate2 = 0;
			int rate3 = 0;
			int rate4 = 0;
			int rate5 = 0;
			
			if(applydate!=null && applydate.length()>=10) {
				applydate = applydate.substring(0, 10);
			}
			if(publishdate!=null && publishdate.length()>=10) {
				publishdate = publishdate.substring(0, 10);
			}
			if(authorizedate!=null && authorizedate.length()>=10) {
				authorizedate = authorizedate.substring(0, 10);
			}
			
//验证begin
			String datayear = rsTable.get("DATAYEAR").toString()==null?"":rsTable.get("DATAYEAR").toString().trim();
			if(datayear.equals("") || datayear.length() != 4) {
				MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，【计算年度】必须填写，并且格式为四位数。请确认!", true);
				return false;
			}
//验证end
			
			
			//有loginId而无“教工号”
			mainDataId = Integer.parseInt(rsTable.get("ID").toString() == null ? "" : rsTable.get("ID").toString().trim());
			loginId = rsTable.get("CHARGEID").toString() == null ? "" : rsTable.get("CHARGEID").toString().trim();
			loginId1 = rsTable.get("ID1").toString() == null ? "" : rsTable.get("ID1").toString().trim();
			loginId2 = rsTable.get("ID2").toString() == null ? "" : rsTable.get("ID2").toString().trim();
			loginId3 = rsTable.get("ID3").toString() == null ? "" : rsTable.get("ID3").toString().trim();
			loginId4 = rsTable.get("ID4").toString() == null ? "" : rsTable.get("ID4").toString().trim();
			loginId5 = rsTable.get("ID5").toString() == null ? "" : rsTable.get("ID5").toString().trim();
			
			String jgh = "";
			String jgh1 = "";
			String jgh2 = "";
			String jgh3 = "";
			String jgh4 = "";
			String jgh5 = "";

			//判断前台是否设置参与人。如果没有，置空原来的数据
			if(patentee == null || patentee.equals("")) {
				loginId = "";
				jgh = "";
			}
			if(name1 == null || name1.equals("")) {
				loginId1 = "";
				jgh1 = "";
			}
			if(name2 == null || name2.equals("")) {
				loginId2 = "";
				jgh2 = "";
			}
			if(name3 == null || name3.equals("")) {
				loginId3 = "";
				jgh3 = "";
			}
			if(name4 == null || name4.equals("")) {
				loginId4 = "";
				jgh4 = "";
			}
			if(name5 == null || name5.equals("")) {
				loginId5 = "";
				jgh5 = "";
			}
			
			String querySql = "";
			
			if(loginId!=null && !loginId.equals("") && (loginId.indexOf("<")>0)) {   //DB中存的为“loginId<中文>"格式
				loginId = loginId.substring(0, loginId.indexOf("<"));
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId+"'";
				jgh = DBSql.getString(conn, querySql, "ZGH").trim();
			} else if(loginId!=null && !loginId.equals("")) {   //DB中存的为“loginId”格式
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId+"'";
				jgh = DBSql.getString(conn, querySql, "ZGH".trim());
			}
			if(loginId1!=null && !loginId1.equals("") && (loginId1.indexOf("<")>0)) {   //DB中存的为“loginId<中文>"格式
				loginId1 = loginId1.substring(0, loginId1.indexOf("<"));
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId1+"'";
				jgh1 = DBSql.getString(conn, querySql, "ZGH").trim();
			} else if(loginId1!=null && !loginId1.equals("")) {   //DB中存的为“loginId”格式
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId1+"'";
				jgh1 = DBSql.getString(conn, querySql, "ZGH").trim();
			}
			if(loginId2!=null && !loginId2.equals("") && (loginId2.indexOf("<")>0)) {   //DB中存的为“loginId<中文>"格式
				loginId2 = loginId2.substring(0, loginId2.indexOf("<"));
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId2+"'";
				jgh2 = DBSql.getString(conn, querySql, "ZGH").trim();
			} else if(loginId2!=null && !loginId2.equals("")) {   //DB中存的为“loginId”格式
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId2+"'";
				jgh2 = DBSql.getString(conn, querySql, "ZGH").trim();
			}
			if(loginId3!=null && !loginId3.equals("") && (loginId3.indexOf("<")>0)) {   //DB中存的为“loginId<中文>"格式
				loginId3 = loginId3.substring(0, loginId3.indexOf("<"));
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId3+"'";
				jgh3 = DBSql.getString(conn, querySql, "ZGH").trim();
			} else if(loginId3!=null && !loginId3.equals("")) {   //DB中存的为“loginId”格式
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId3+"'";
				jgh3 = DBSql.getString(conn, querySql, "ZGH").trim();
			}
			if(loginId4!=null && !loginId4.equals("") && (loginId4.indexOf("<")>0)) {   //DB中存的为“loginId<中文>"格式
				loginId4 = loginId4.substring(0, loginId4.indexOf("<"));
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId4+"'";
				jgh4 = DBSql.getString(conn, querySql, "ZGH").trim();
			} else if(loginId4!=null && !loginId4.equals("")) {   //DB中存的为“loginId”格式
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId4+"'";
				jgh4 = DBSql.getString(conn, querySql, "ZGH").trim();
			}
			if(loginId5!=null && !loginId5.equals("") && (loginId5.indexOf("<")>0)) {   //DB中存的为“loginId<中文>"格式
				loginId5 = loginId5.substring(0, loginId5.indexOf("<"));
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId5+"'";
				jgh5 = DBSql.getString(conn, querySql, "ZGH").trim();
			} else if(loginId5!=null && !loginId5.equals("")) {   //DB中存的为“loginId”格式
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId5+"'";
				jgh5 = DBSql.getString(conn, querySql, "ZGH").trim();
			}
			
			String updateInstanceSql = "update BO_ZLXXTEMP set JGH ='" +jgh +"' "
			+ ", JGH1='" +jgh1 + "' "
			+ ", JGH2='" +jgh2 + "' "
			+ ", JGH3='" +jgh3 + "' "
			+ ", JGH4='" +jgh4 + "' "
			+ ", JGH5='" +jgh5 + "' "
			+ ", CHARGEID='" +loginId + "' "
			+ ", ID1='" +loginId1 + "' "
			+ ", ID2='" +loginId2 + "' "
			+ ", ID3='" +loginId3 + "' "
			+ ", ID4='" +loginId4 + "' "
			+ ", ID5='" +loginId5 + "' "
			+ " where ID="+mainDataId;
			DBSql.executeUpdate(updateInstanceSql);
			
			
			
			/********************************数据共享：先删除共享信息，再添加。进行对应的操作*****************************/
			List copyDataList = new ArrayList();
			if(copyDataStr!=null) {
				String temp[] = copyDataStr.split(":");
				for(int i=0; i<temp.length; i++) {
					copyDataList.add(temp[i]);
				}
			}
			for(int i=0; i<copyDataList.size(); i++) {
				String copyDataIdTemp = copyDataList.get(i).toString();
				if(copyDataIdTemp!=null && !copyDataIdTemp.equals("")) {
					int copyDataIdInt = Integer.parseInt(copyDataIdTemp);
					String deleteSql = "delete from BO_ZLXXTEMP where ID ="+copyDataIdInt;
//					System.out.println("deleteSql="+deleteSql);
					DBSql.executeUpdate(deleteSql); //删除数据。再新增
				}
			}
			
			//**********************数据共享部分：为各参与人复制同样的数据.标记autoAdd字段为1**************************
			Hashtable recordData = new Hashtable();
			int loginId1BindId = 0;
			int loginId2BindId = 0;
			int loginId3BindId = 0;
			int loginId4BindId = 0;
			int loginId5BindId = 0;
			String queryBindIdSql = "";
			int createBoFailureNum = 0;
			String copyDataIds = "0:";
			
			//BOInstanceAPI.getInstance().createBOData.如果两次使用该方法，则两次中的recordData必须不一致
			if(loginId1 != null && !loginId1.equals("")) {
				recordData.put("ID1", loginId1);
				recordData.put("JGH1", jgh1);
				queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId1+ "'";
				loginId1BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //绑定新增数据为该人员的bindId

				int createBoId = BOInstanceAPI.getInstance().createBOData("BO_ZLXXTEMP", recordData, loginId1BindId, createuser);
				if(createBoId == -1) {
					createBoFailureNum = createBoFailureNum + 1;
				} else {
					copyDataIds = copyDataIds + createBoId + ":";
				}
				
//				System.out.println("为 "+loginId1+" 共享数据：createBoId="+createBoId+", BINDID="+loginId1BindId);
				String updateCreateBoSql = "UPDATE BO_ZLXXTEMP SET "
    				+ "  PATENTNAME ='" + patentname +"' "
					+ ", PATENTKINDSYMBOL ='" + patentkindsymbol +"' "
					+ ", APPLICANT ='" + applicant +"' "
					+ ", APPLYDATE = TO_DATE('" +  applydate +"','YYYY-MM-DD') " 
					+ ", PUBLISHDATE = TO_DATE('" +  publishdate +"','YYYY-MM-DD') " 
					+ ", DEPTNAME ='" + deptname +"' "
					+ ", AUTHORIZEDATE = TO_DATE('" +  authorizedate +"','YYYY-MM-DD') " 
					+ ", PATENTEE ='" + patentee +"' "
					+ ", AUTHORIZEUNIT ='" + authorizeunit +"' "
					+ ", ZLH ='" + zlh +"' "
					+ ", ZSBH ='" + zsbh +"' "
					+ ", CHARGEID ='" + chargeid +"' "
					+ ", NAME1 ='" + name1 +"' "
					+ ", ID1 ='" + id1 +"' "
					+ ", NAME2 ='" + name2 +"' "
					+ ", ID2 ='" + id2 +"' "
					+ ", NAME3 ='" + name3 +"' "
					+ ", ID3 ='" + id3 +"' "
					+ ", NAME4 ='" + name4 +"' "
					+ ", ID4 ='" + id4 +"' "
					+ ", NAME5 ='" + name5 +"' "
					+ ", ID5 ='" + id5 +"' "
				    + ", CHARGERATE='" + chargerate+"' "
				    + ", RATE1='" + rate1+"' "
				    + ", RATE2='" + rate2+"' "
				    + ", RATE3='" + rate3+"' "
				    + ", RATE4='" + rate4+"' "
				    + ", RATE5='" + rate5+"' "
				    + ", JGH ='" + jgh +"' "
					+ ", JGH1 ='" + jgh1 +"' "
					+ ", JGH2 ='" + jgh2 +"' "
					+ ", JGH3 ='" + jgh3 +"' "
					+ ", JGH4 ='" + jgh4 +"' "
					+ ", JGH5 ='" + jgh5 +"' "
					+ ", DATAYEAR ='" + datayear +"' "
					+ ", AUTOADD =1 "
				    + ", STATUS =0 "
				    + ", FINALMARK =0 "
				    + ", COPYDATAID ='0:' "
					+ " where ID="+createBoId;
//				System.out.println("updateCreateBoSql="+updateCreateBoSql);
				DBSql.executeUpdate(updateCreateBoSql);
			}
			if(loginId2 != null && !loginId2.equals("")) {
				recordData.put("ID2", loginId2);
				recordData.put("JGH2", jgh2);
				
				queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId2+ "'";
				loginId2BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //绑定新增数据为该人员的bindId
				
				int createBoId = BOInstanceAPI.getInstance().createBOData("BO_ZLXXTEMP", recordData, loginId2BindId, createuser);
				if(createBoId == -1) {
					createBoFailureNum = createBoFailureNum + 1;
				} else {
					copyDataIds = copyDataIds + createBoId + ":";
				}
//				System.out.println("为 "+loginId2+" 共享数据：createBoId="+createBoId+", BINDID="+loginId2BindId);
				String updateCreateBoSql = "UPDATE BO_ZLXXTEMP SET "
    				+ "  PATENTNAME ='" + patentname +"' "
					+ ", PATENTKINDSYMBOL ='" + patentkindsymbol +"' "
					+ ", APPLICANT ='" + applicant +"' "
					+ ", APPLYDATE = TO_DATE('" +  applydate +"','YYYY-MM-DD') " 
					+ ", PUBLISHDATE = TO_DATE('" +  publishdate +"','YYYY-MM-DD') " 
					+ ", DEPTNAME ='" + deptname +"' "
					+ ", AUTHORIZEDATE = TO_DATE('" +  authorizedate +"','YYYY-MM-DD') " 
					+ ", PATENTEE ='" + patentee +"' "
					+ ", AUTHORIZEUNIT ='" + authorizeunit +"' "
					+ ", ZLH ='" + zlh +"' "
					+ ", ZSBH ='" + zsbh +"' "
					+ ", CHARGEID ='" + chargeid +"' "
					+ ", NAME1 ='" + name1 +"' "
					+ ", ID1 ='" + id1 +"' "
					+ ", NAME2 ='" + name2 +"' "
					+ ", ID2 ='" + id2 +"' "
					+ ", NAME3 ='" + name3 +"' "
					+ ", ID3 ='" + id3 +"' "
					+ ", NAME4 ='" + name4 +"' "
					+ ", ID4 ='" + id4 +"' "
					+ ", NAME5 ='" + name5 +"' "
					+ ", ID5 ='" + id5 +"' "
				    + ", CHARGERATE='" + chargerate+"' "
				    + ", RATE1='" + rate1+"' "
				    + ", RATE2='" + rate2+"' "
				    + ", RATE3='" + rate3+"' "
				    + ", RATE4='" + rate4+"' "
				    + ", RATE5='" + rate5+"' "
				    + ", JGH ='" + jgh +"' "
					+ ", JGH1 ='" + jgh1 +"' "
					+ ", JGH2 ='" + jgh2 +"' "
					+ ", JGH3 ='" + jgh3 +"' "
					+ ", JGH4 ='" + jgh4 +"' "
					+ ", JGH5 ='" + jgh5 +"' "
					+ ", DATAYEAR ='" + datayear +"' "
					+ ", AUTOADD =1 "
				    + ", STATUS =0 "
				    + ", FINALMARK =0 "
				    + ", COPYDATAID ='0:' "
					+ " where ID="+createBoId;
//				System.out.println("updateCreateBoSql="+updateCreateBoSql);
				DBSql.executeUpdate(updateCreateBoSql);
			}
			if(loginId3 != null && !loginId3.equals("")) {
				recordData.put("ID3", loginId3);
				recordData.put("JGH3", jgh3);
				
				queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId3+ "'";
				loginId3BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //绑定新增数据为该人员的bindId
				
				int createBoId = BOInstanceAPI.getInstance().createBOData("BO_ZLXXTEMP", recordData, loginId3BindId, createuser);
				if(createBoId == -1) {
					createBoFailureNum = createBoFailureNum + 1;
				} else {
					copyDataIds = copyDataIds + createBoId + ":";
				}
//				System.out.println("为 "+loginId3+" 共享数据：createBoId="+createBoId+", BINDID="+loginId3BindId);
				String updateCreateBoSql = "UPDATE BO_ZLXXTEMP SET "
    				+ "  PATENTNAME ='" + patentname +"' "
					+ ", PATENTKINDSYMBOL ='" + patentkindsymbol +"' "
					+ ", APPLICANT ='" + applicant +"' "
					+ ", APPLYDATE = TO_DATE('" +  applydate +"','YYYY-MM-DD') " 
					+ ", PUBLISHDATE = TO_DATE('" +  publishdate +"','YYYY-MM-DD') " 
					+ ", DEPTNAME ='" + deptname +"' "
					+ ", AUTHORIZEDATE = TO_DATE('" +  authorizedate +"','YYYY-MM-DD') " 
					+ ", PATENTEE ='" + patentee +"' "
					+ ", AUTHORIZEUNIT ='" + authorizeunit +"' "
					+ ", ZLH ='" + zlh +"' "
					+ ", ZSBH ='" + zsbh +"' "
					+ ", CHARGEID ='" + chargeid +"' "
					+ ", NAME1 ='" + name1 +"' "
					+ ", ID1 ='" + id1 +"' "
					+ ", NAME2 ='" + name2 +"' "
					+ ", ID2 ='" + id2 +"' "
					+ ", NAME3 ='" + name3 +"' "
					+ ", ID3 ='" + id3 +"' "
					+ ", NAME4 ='" + name4 +"' "
					+ ", ID4 ='" + id4 +"' "
					+ ", NAME5 ='" + name5 +"' "
					+ ", ID5 ='" + id5 +"' "
				    + ", CHARGERATE='" + chargerate+"' "
				    + ", RATE1='" + rate1+"' "
				    + ", RATE2='" + rate2+"' "
				    + ", RATE3='" + rate3+"' "
				    + ", RATE4='" + rate4+"' "
				    + ", RATE5='" + rate5+"' "
				    + ", JGH ='" + jgh +"' "
					+ ", JGH1 ='" + jgh1 +"' "
					+ ", JGH2 ='" + jgh2 +"' "
					+ ", JGH3 ='" + jgh3 +"' "
					+ ", JGH4 ='" + jgh4 +"' "
					+ ", JGH5 ='" + jgh5 +"' "
					+ ", DATAYEAR ='" + datayear +"' "
					+ ", AUTOADD =1 "
				    + ", STATUS =0 "
				    + ", FINALMARK =0 "
				    + ", COPYDATAID ='0:' "
					+ " where ID="+createBoId;
//				System.out.println("updateCreateBoSql="+updateCreateBoSql);
				DBSql.executeUpdate(updateCreateBoSql);
			}
			if(loginId4 != null && !loginId4.equals("")) {
				recordData.put("ID4", loginId4);
				recordData.put("JGH4", jgh4);
				
				queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId4+ "'";
				loginId4BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //绑定新增数据为该人员的bindId
				
				int createBoId = BOInstanceAPI.getInstance().createBOData("BO_ZLXXTEMP", recordData, loginId4BindId, createuser);
				if(createBoId == -1) {
					createBoFailureNum = createBoFailureNum + 1;
				} else {
					copyDataIds = copyDataIds + createBoId + ":";
				}
//				System.out.println("为 "+loginId4+" 共享数据：createBoId="+createBoId+", BINDID="+loginId4BindId);
				String updateCreateBoSql = "UPDATE BO_ZLXXTEMP SET "
    				+ "  PATENTNAME ='" + patentname +"' "
					+ ", PATENTKINDSYMBOL ='" + patentkindsymbol +"' "
					+ ", APPLICANT ='" + applicant +"' "
					+ ", APPLYDATE = TO_DATE('" +  applydate +"','YYYY-MM-DD') " 
					+ ", PUBLISHDATE = TO_DATE('" +  publishdate +"','YYYY-MM-DD') " 
					+ ", DEPTNAME ='" + deptname +"' "
					+ ", AUTHORIZEDATE = TO_DATE('" +  authorizedate +"','YYYY-MM-DD') " 
					+ ", PATENTEE ='" + patentee +"' "
					+ ", AUTHORIZEUNIT ='" + authorizeunit +"' "
					+ ", ZLH ='" + zlh +"' "
					+ ", ZSBH ='" + zsbh +"' "
					+ ", CHARGEID ='" + chargeid +"' "
					+ ", NAME1 ='" + name1 +"' "
					+ ", ID1 ='" + id1 +"' "
					+ ", NAME2 ='" + name2 +"' "
					+ ", ID2 ='" + id2 +"' "
					+ ", NAME3 ='" + name3 +"' "
					+ ", ID3 ='" + id3 +"' "
					+ ", NAME4 ='" + name4 +"' "
					+ ", ID4 ='" + id4 +"' "
					+ ", NAME5 ='" + name5 +"' "
					+ ", ID5 ='" + id5 +"' "
				    + ", CHARGERATE='" + chargerate+"' "
				    + ", RATE1='" + rate1+"' "
				    + ", RATE2='" + rate2+"' "
				    + ", RATE3='" + rate3+"' "
				    + ", RATE4='" + rate4+"' "
				    + ", RATE5='" + rate5+"' "
				    + ", JGH ='" + jgh +"' "
					+ ", JGH1 ='" + jgh1 +"' "
					+ ", JGH2 ='" + jgh2 +"' "
					+ ", JGH3 ='" + jgh3 +"' "
					+ ", JGH4 ='" + jgh4 +"' "
					+ ", JGH5 ='" + jgh5 +"' "
					+ ", DATAYEAR ='" + datayear +"' "
					+ ", AUTOADD =1 "
				    + ", STATUS =0 "
				    + ", FINALMARK =0 "
				    + ", COPYDATAID ='0:' "
					+ " where ID="+createBoId;
//				System.out.println("updateCreateBoSql="+updateCreateBoSql);
				DBSql.executeUpdate(updateCreateBoSql);
			}
			if(loginId5 != null && !loginId5.equals("")) {
				recordData.put("ID5", loginId5);
				recordData.put("JGH5", jgh5);
				
				queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId5+ "'";
				loginId5BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //绑定新增数据为该人员的bindId
				
				int createBoId = BOInstanceAPI.getInstance().createBOData("BO_ZLXXTEMP", recordData, loginId5BindId, createuser);
				if(createBoId == -1) {
					createBoFailureNum = createBoFailureNum + 1;
				} else {
					copyDataIds = copyDataIds + createBoId + ":";
				}
//				System.out.println("为 "+loginId5+" 共享数据：createBoId="+createBoId+", BINDID="+loginId5BindId);
				String updateCreateBoSql = "UPDATE BO_ZLXXTEMP SET "
    				+ "  PATENTNAME ='" + patentname +"' "
					+ ", PATENTKINDSYMBOL ='" + patentkindsymbol +"' "
					+ ", APPLICANT ='" + applicant +"' "
					+ ", APPLYDATE = TO_DATE('" +  applydate +"','YYYY-MM-DD') " 
					+ ", PUBLISHDATE = TO_DATE('" +  publishdate +"','YYYY-MM-DD') " 
					+ ", DEPTNAME ='" + deptname +"' "
					+ ", AUTHORIZEDATE = TO_DATE('" +  authorizedate +"','YYYY-MM-DD') " 
					+ ", PATENTEE ='" + patentee +"' "
					+ ", AUTHORIZEUNIT ='" + authorizeunit +"' "
					+ ", ZLH ='" + zlh +"' "
					+ ", ZSBH ='" + zsbh +"' "
					+ ", CHARGEID ='" + chargeid +"' "
					+ ", NAME1 ='" + name1 +"' "
					+ ", ID1 ='" + id1 +"' "
					+ ", NAME2 ='" + name2 +"' "
					+ ", ID2 ='" + id2 +"' "
					+ ", NAME3 ='" + name3 +"' "
					+ ", ID3 ='" + id3 +"' "
					+ ", NAME4 ='" + name4 +"' "
					+ ", ID4 ='" + id4 +"' "
					+ ", NAME5 ='" + name5 +"' "
					+ ", ID5 ='" + id5 +"' "
				    + ", CHARGERATE='" + chargerate+"' "
				    + ", RATE1='" + rate1+"' "
				    + ", RATE2='" + rate2+"' "
				    + ", RATE3='" + rate3+"' "
				    + ", RATE4='" + rate4+"' "
				    + ", RATE5='" + rate5+"' "
				    + ", JGH ='" + jgh +"' "
					+ ", JGH1 ='" + jgh1 +"' "
					+ ", JGH2 ='" + jgh2 +"' "
					+ ", JGH3 ='" + jgh3 +"' "
					+ ", JGH4 ='" + jgh4 +"' "
					+ ", JGH5 ='" + jgh5 +"' "
					+ ", DATAYEAR ='" + datayear +"' "
					+ ", AUTOADD =1 "
				    + ", STATUS =0 "
				    + ", FINALMARK =0 "
				    + ", COPYDATAID ='0:' "
					+ " where ID="+createBoId;
//				System.out.println("updateCreateBoSql="+updateCreateBoSql);
				DBSql.executeUpdate(updateCreateBoSql);
			}

			String updateCopyDataIdSql = "update BO_ZLXXTEMP set copyDataId ='" +copyDataIds +"' where ID="+mainDataId;
			DBSql.executeUpdate(updateCopyDataIdSql);
			
			String tip = "数据保存成功！";
			if(createBoFailureNum != 0) {
				tip = tip + "注意！主持人和参与人之间数据共享未初始化成功。共有 " +createBoFailureNum+ " 个参与人保存失败。！";
				MessageQueue.getInstance().putMessage(getUserContext().getUID(), tip, true);
				return false;
			}
			
		}catch(Exception e){
			e.printStackTrace(System.err);
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，信息保存失败！", true);
			return false;
		}finally{
			DBSql.close(conn, ps, rs);
		}
		
		
		return true;
	}
	
}
