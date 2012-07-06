package ecnu.importBiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.actionsoft.awf.organization.control.MessageQueue;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.loader.core.WorkFlowStepRTClassA;
import com.actionsoft.sdk.local.level0.BOInstanceAPI;

public class ZlxxAfterImportBiz extends WorkFlowStepRTClassA {
	
	public ZlxxAfterImportBiz(UserContext uc){
		super(uc);
		super.setDescription("华东师大专利信息导入");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}

	public boolean execute() {
		System.out.println(".....................华东师大专利信息导入begin.导入数据后，为每条导入的数据绑定人员................");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conn = DBSql.open();
			conn.setAutoCommit(true);
			
//			String countSql = "select count(1) totalNum from BO_ECNU_ZLXX";
//			int totalNum = DBSql.getInt(conn, countSql, "totalNum");
			
			String sql = "select * from BO_ECNU_ZLXX";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			int dataId = 0;
			
			String patentname ="";
			String patentkindsymbol ="";
			String applicant ="";
			String applydate ="";
			String publishdate ="";
			String deptname ="";
			String authorizedate ="";
			String patentee ="";
			String authorizeunit ="";
			String attach ="";
			String zlh ="";
			String zsbh ="";
			String chargeid ="";
			String name1 ="";
			String id1 ="";
			String name2 ="";
			String id2 ="";
			String name3 ="";
			String id3 ="";
			String name4 ="";
			String id4 ="";
			String name5 ="";
			String id5 ="";
			int chargerate = 0;
			int rate1 = 0;
			int rate2 = 0;
			int rate3 = 0;
			int rate4 = 0;
			int rate5 = 0;
			
			String jgh = "";
			String jgh1 = "";
			String jgh2 = "";
			String jgh3 = "";
			String jgh4 = "";
			String jgh5 = "";
			
			
			int noChargeJghData = 0;
			List notExitUserList = new ArrayList();
			List notExitUserNameList = new ArrayList();
			int totalData = 0;
			int createBoFailureNum = 0;

			while(rs.next()){
				String copyDataId = "";
				copyDataId = rs.getString("COPYDATAID")==null?"":rs.getString("COPYDATAID").trim();
				//根据此字段判断该数据是否被处理过。
				if(copyDataId.equals("")) {
					
					totalData = totalData + 1;
					dataId = rs.getInt("ID");
					
					patentname = rs.getString("patentname")==null ?"": rs.getString("patentname").trim();
					patentkindsymbol = rs.getString("patentkindsymbol")==null ?"": rs.getString("patentkindsymbol").trim();
					applicant = rs.getString("applicant")==null ?"": rs.getString("applicant").trim();
					applydate = rs.getString("applydate")==null ?"": rs.getString("applydate").trim();
					publishdate = rs.getString("publishdate")==null ?"": rs.getString("publishdate").trim();
					deptname = rs.getString("deptname")==null ?"": rs.getString("deptname").trim();
					authorizedate = rs.getString("authorizedate")==null ?"": rs.getString("authorizedate").trim();
					patentee = rs.getString("patentee")==null ?"": rs.getString("patentee").trim();
					authorizeunit = rs.getString("authorizeunit")==null ?"": rs.getString("authorizeunit").trim();
					attach = rs.getString("attach")==null ?"": rs.getString("attach").trim();
					zlh = rs.getString("zlh")==null ?"": rs.getString("zlh").trim();
					zsbh = rs.getString("zsbh")==null ?"": rs.getString("zsbh").trim();
					chargeid = rs.getString("chargeid")==null ?"": rs.getString("chargeid").trim();
					name1 = rs.getString("name1")==null ?"": rs.getString("name1").trim();
					id1 = rs.getString("id1")==null ?"": rs.getString("id1").trim();
					name2 = rs.getString("name2")==null ?"": rs.getString("name2").trim();
					id2 = rs.getString("id2")==null ?"": rs.getString("id2").trim();
					name3 = rs.getString("name3")==null ?"": rs.getString("name3").trim();
					id3 = rs.getString("id3")==null ?"": rs.getString("id3").trim();
					name4 = rs.getString("name4")==null ?"": rs.getString("name4").trim();
					id4 = rs.getString("id4")==null ?"": rs.getString("id4").trim();
					name5 = rs.getString("name5")==null ?"": rs.getString("name5").trim();
					id5 = rs.getString("id5")==null ?"": rs.getString("id5").trim();
					chargerate = rs.getInt("chargerate");
					rate1 = rs.getInt("rate1");
					rate2 = rs.getInt("rate2");
					rate3 = rs.getInt("rate3");
					rate4 = rs.getInt("rate4");
					rate5 = rs.getInt("rate5");
//				
					//Excel中有“姓名”和“教工号”，无“登录号”。Excel导入后，name和jgh有值。而id无值。
					jgh = rs.getString("JGH")==null?"":rs.getString("JGH").trim();
					jgh1 = rs.getString("JGH1")==null?"":rs.getString("JGH1").trim();
					jgh2 = rs.getString("JGH2")==null?"":rs.getString("JGH2").trim();
					jgh3 = rs.getString("JGH3")==null?"":rs.getString("JGH3").trim();
					jgh4 = rs.getString("JGH4")==null?"":rs.getString("JGH4").trim();
					jgh5 = rs.getString("JGH5")==null?"":rs.getString("JGH5").trim();
					
					if(applydate!=null && applydate.length()>=10) {
						applydate = applydate.substring(0, 10);
					}
					if(publishdate!=null && publishdate.length()>=10) {
						publishdate = publishdate.substring(0, 10);
					}
					if(authorizedate!=null && authorizedate.length()>=10) {
						authorizedate = authorizedate.substring(0, 10);
					}
					
					String loginId = "";
					String loginId1 = "";
					String loginId2 = "";
					String loginId3 = "";
					String loginId4 = "";
					String loginId5 = "";
					
					String querySql = "";
					if(jgh!=null && !jgh.equals("")) {
						querySql = "select loginid from BO_ECNU_PEOPLE where ZGH='" +jgh+"'";
						loginId = DBSql.getString(conn, querySql, "loginid").trim();
					} else {
						noChargeJghData = noChargeJghData + 1;
					}
					if(jgh1!=null && !jgh1.equals("")) {
						querySql = "select loginid from BO_ECNU_PEOPLE where ZGH='" +jgh1+"'";
						loginId1 = DBSql.getString(conn, querySql, "loginid").trim();
					}
					if(jgh2!=null && !jgh2.equals("")) {
						querySql = "select loginid from BO_ECNU_PEOPLE where ZGH='" +jgh2+"'";
						loginId2 = DBSql.getString(conn, querySql, "loginid").trim();
					}
					if(jgh3!=null && !jgh3.equals("")) {
						querySql = "select loginid from BO_ECNU_PEOPLE where ZGH='" +jgh3+"'";
						loginId3 = DBSql.getString(conn, querySql, "loginid").trim();
					}
					if(jgh4!=null && !jgh4.equals("")) {
						querySql = "select loginid from BO_ECNU_PEOPLE where ZGH='" +jgh4+"'";
						loginId4 = DBSql.getString(conn, querySql, "loginid").trim();
					}
					if(jgh5!=null && !jgh5.equals("")) {
						querySql = "select loginid from BO_ECNU_PEOPLE where ZGH='" +jgh5+"'";
						loginId5 = DBSql.getString(conn, querySql, "loginid").trim();
					}
					
					
					//判断账号是否存在
					if(loginId==null || loginId.equals("")) {
						int flag = 0;
						for(int i=0; i<notExitUserList.size(); i++) {
							if( notExitUserList.get(i).toString().equals(jgh) ) {
								flag = 1;
								break;
							}
						}
						if(flag == 0) {
							notExitUserList.add(jgh);
							notExitUserNameList.add(patentee);
						}
					}
					if(loginId1==null || loginId1.equals("")) {
						int flag = 0;
						for(int i=0; i<notExitUserList.size(); i++) {
							if( notExitUserList.get(i).toString().equals(jgh1) ) {
								flag = 1;
								break;
							}
						}
						if(flag == 0) {
							notExitUserList.add(jgh1);
							notExitUserNameList.add(name1);
						}
					}
					if(loginId2==null || loginId2.equals("")) {
						int flag = 0;
						for(int i=0; i<notExitUserList.size(); i++) {
							if( notExitUserList.get(i).toString().equals(jgh2) ) {
								flag = 1;
								break;
							}
						}
						if(flag == 0) {
							notExitUserList.add(jgh2);
							notExitUserNameList.add(name2);
						}
					}
					if(loginId3==null || loginId3.equals("")) {
						int flag = 0;
						for(int i=0; i<notExitUserList.size(); i++) {
							if( notExitUserList.get(i).toString().equals(jgh3) ) {
								flag = 1;
								break;
							}
						}
						if(flag == 0) {
							notExitUserList.add(jgh3);
							notExitUserNameList.add(name3);
						}
					}
					if(loginId4==null || loginId4.equals("")) {
						int flag = 0;
						for(int i=0; i<notExitUserList.size(); i++) {
							if( notExitUserList.get(i).toString().equals(jgh4) ) {
								flag = 1;
								break;
							}
						}
						if(flag == 0) {
							notExitUserList.add(jgh4);
							notExitUserNameList.add(name4);
						}
					}
					if(loginId5==null || loginId5.equals("")) {
						int flag = 0;
						for(int i=0; i<notExitUserList.size(); i++) {
							if( notExitUserList.get(i).toString().equals(jgh5) ) {
								flag = 1;
								break;
							}
						}
						if(flag == 0) {
							notExitUserList.add(jgh5);
							notExitUserNameList.add(name5);
						}
					}
					
					
					int processInstanceId = 0;
					
					if(!loginId.equals("")) {
						//运行AWS流程-----------------为数据绑定流程
//					processInstanceId = WorkflowInstanceAPI.getInstance().createProcessInstance("007b23151fc8e47f46212fa0cd1e141d", loginId, "华东师大专利信息申请");
//					int[] processTaskInstanceIds = WorkflowTaskInstanceAPI.getInstance().createProcessTaskInstance(loginId, processInstanceId, SynType.synchronous, PriorityType.hight, 1, loginId, "华东师大专利信息申请", false, 0);
//					System.out.println("processInstanceId="+processInstanceId+" ,processTaskInstanceIds="+processTaskInstanceIds[0]);
					}
					
					//此批量导入，设定数据直接绑定chargeId对应的bindId
					//并且STATUS状态设置为9。表示为批量导入时设置的状态
					String queryBindIdForChargeIdSql = "SELECT BINDID FROM BO_ECNU_PEOPLE WHERE LOGINID ='" +loginId+ "'";
					int chargeIdBindId = 0;
					chargeIdBindId = DBSql.getInt(conn, queryBindIdForChargeIdSql, "BINDID");
					
					String updateLoginIdSql = "";
					if(chargeIdBindId != 0) {
						updateLoginIdSql = "update BO_ECNU_ZLXX set CHARGEID ='" +loginId +"' "
						+ ", ID1='" +loginId1 + "' "
						+ ", ID2='" +loginId2 + "' "
						+ ", ID3='" +loginId3 + "' "
						+ ", ID4='" +loginId4 + "' "
						+ ", ID5='" +loginId5 + "' "
						+ ", BINDID=" +chargeIdBindId
						+ ", STATUS=9 "
						+ ", AUTOADD=0 "
						+ " where ID="+dataId;
					} else {
						updateLoginIdSql = "update BO_ECNU_ZLXX set CHARGEID ='" +loginId +"' "
						+ ", ID1='" +loginId1 + "' "
						+ ", ID2='" +loginId2 + "' "
						+ ", ID3='" +loginId3 + "' "
						+ ", ID4='" +loginId4 + "' "
						+ ", ID5='" +loginId5 + "' "
						+ ", STATUS=9 "
						+ ", AUTOADD=0 "
						+ " where ID="+dataId;
					}
					DBSql.executeUpdate(updateLoginIdSql);
					
					//**********************数据共享部分：为各参与人复制同样的数据.标记autoAdd字段为1**************************
					Hashtable recordData = new Hashtable();
					int loginId1BindId = 0;
					int loginId2BindId = 0;
					int loginId3BindId = 0;
					int loginId4BindId = 0;
					int loginId5BindId = 0;
					String queryBindIdSql = "";
					String copyDataIds = "0:";
					
					//BOInstanceAPI.getInstance().createBOData.如果两次使用该方法，则两次中的recordData必须不一致
					if(loginId1 != null && !loginId1.equals("")) {
						recordData.put("ID1", loginId1);
						recordData.put("JGH1", jgh1);
						queryBindIdSql = "SELECT BINDID FROM BO_ECNU_PEOPLE WHERE LOGINID ='" +loginId1+ "'";
						loginId1BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //绑定新增数据为该人员的bindId
						
						int createBoId = BOInstanceAPI.getInstance().createBOData("BO_ECNU_ZLXX", recordData, loginId1BindId, "admin");
						if(createBoId == -1) {
							createBoFailureNum = createBoFailureNum + 1;
						}
						copyDataIds = copyDataIds + createBoId + ":";
						
//					System.out.println("为 "+loginId1+" 共享数据：createBoId="+createBoId+", BINDID="+loginId1BindId);
						String updateCreateBoSql = "UPDATE BO_ECNU_ZLXX SET "
							+ "  PATENTNAME ='" + patentname +"' "
							+ ", PATENTKINDSYMBOL ='" + patentkindsymbol +"' "
							+ ", APPLICANT ='" + applicant +"' "
							+ ", APPLYDATE = TO_DATE('" +  applydate +"','YYYY-MM-DD') " 
							+ ", PUBLISHDATE = TO_DATE('" +  publishdate +"','YYYY-MM-DD') " 
							+ ", DEPTNAME ='" + deptname +"' "
							+ ", AUTHORIZEDATE = TO_DATE('" +  authorizedate +"','YYYY-MM-DD') " 
							+ ", PATENTEE ='" + patentee +"' "
							+ ", AUTHORIZEUNIT ='" + authorizeunit +"' "
							+ ", ATTACH ='" + attach +"' "
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
							+ ", AUTOADD =1 "
							+ ", STATUS =0 "
							+ ", FINALMARK =0 "
							+ ", COPYDATAID ='0:' "
							+ " where ID="+createBoId;
//					System.out.println("updateCreateBoSql="+updateCreateBoSql);
						DBSql.executeUpdate(updateCreateBoSql);
					}
					if(loginId2 != null && !loginId2.equals("")) {
						recordData.put("ID2", loginId2);
						recordData.put("JGH2", jgh2);
						
						queryBindIdSql = "SELECT BINDID FROM BO_ECNU_PEOPLE WHERE LOGINID ='" +loginId2+ "'";
						loginId2BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //绑定新增数据为该人员的bindId
						
						int createBoId = BOInstanceAPI.getInstance().createBOData("BO_ECNU_ZLXX", recordData, loginId2BindId, "admin");
						if(createBoId == -1) {
							createBoFailureNum = createBoFailureNum + 1;
						}
						copyDataIds = copyDataIds + createBoId + ":";
						
//					System.out.println("为 "+loginId2+" 共享数据：createBoId="+createBoId+", BINDID="+loginId2BindId);
						String updateCreateBoSql = "UPDATE BO_ECNU_ZLXX SET "
							+ "  PATENTNAME ='" + patentname +"' "
							+ ", PATENTKINDSYMBOL ='" + patentkindsymbol +"' "
							+ ", APPLICANT ='" + applicant +"' "
							+ ", APPLYDATE = TO_DATE('" +  applydate +"','YYYY-MM-DD') " 
							+ ", PUBLISHDATE = TO_DATE('" +  publishdate +"','YYYY-MM-DD') " 
							+ ", DEPTNAME ='" + deptname +"' "
							+ ", AUTHORIZEDATE = TO_DATE('" +  authorizedate +"','YYYY-MM-DD') " 
							+ ", PATENTEE ='" + patentee +"' "
							+ ", AUTHORIZEUNIT ='" + authorizeunit +"' "
							+ ", ATTACH ='" + attach +"' "
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
							+ ", AUTOADD =1 "
							+ ", STATUS =0 "
							+ ", FINALMARK =0 "
							+ ", COPYDATAID ='0:' "
							+ " where ID="+createBoId;
//					System.out.println("updateCreateBoSql="+updateCreateBoSql);
						DBSql.executeUpdate(updateCreateBoSql);
					}
					if(loginId3 != null && !loginId3.equals("")) {
						recordData.put("ID3", loginId3);
						recordData.put("JGH3", jgh3);
						
						queryBindIdSql = "SELECT BINDID FROM BO_ECNU_PEOPLE WHERE LOGINID ='" +loginId3+ "'";
						loginId3BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //绑定新增数据为该人员的bindId
						
						int createBoId = BOInstanceAPI.getInstance().createBOData("BO_ECNU_ZLXX", recordData, loginId3BindId, "admin");
						if(createBoId == -1) {
							createBoFailureNum = createBoFailureNum + 1;
						}
						copyDataIds = copyDataIds + createBoId + ":";
						
//					System.out.println("为 "+loginId3+" 共享数据：createBoId="+createBoId+", BINDID="+loginId3BindId);
						String updateCreateBoSql = "UPDATE BO_ECNU_ZLXX SET "
							+ "  PATENTNAME ='" + patentname +"' "
							+ ", PATENTKINDSYMBOL ='" + patentkindsymbol +"' "
							+ ", APPLICANT ='" + applicant +"' "
							+ ", APPLYDATE = TO_DATE('" +  applydate +"','YYYY-MM-DD') " 
							+ ", PUBLISHDATE = TO_DATE('" +  publishdate +"','YYYY-MM-DD') " 
							+ ", DEPTNAME ='" + deptname +"' "
							+ ", AUTHORIZEDATE = TO_DATE('" +  authorizedate +"','YYYY-MM-DD') " 
							+ ", PATENTEE ='" + patentee +"' "
							+ ", AUTHORIZEUNIT ='" + authorizeunit +"' "
							+ ", ATTACH ='" + attach +"' "
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
							+ ", AUTOADD =1 "
							+ ", STATUS =0 "
							+ ", FINALMARK =0 "
							+ ", COPYDATAID ='0:' "
							+ " where ID="+createBoId;
//					System.out.println("updateCreateBoSql="+updateCreateBoSql);
						DBSql.executeUpdate(updateCreateBoSql);
					}
					if(loginId4 != null && !loginId4.equals("")) {
						recordData.put("ID4", loginId4);
						recordData.put("JGH4", jgh4);
						
						queryBindIdSql = "SELECT BINDID FROM BO_ECNU_PEOPLE WHERE LOGINID ='" +loginId4+ "'";
						loginId4BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //绑定新增数据为该人员的bindId
						
						int createBoId = BOInstanceAPI.getInstance().createBOData("BO_ECNU_ZLXX", recordData, loginId4BindId, "admin");
						if(createBoId == -1) {
							createBoFailureNum = createBoFailureNum + 1;
						}
						copyDataIds = copyDataIds + createBoId + ":";
						
//					System.out.println("为 "+loginId4+" 共享数据：createBoId="+createBoId+", BINDID="+loginId4BindId);
						String updateCreateBoSql = "UPDATE BO_ECNU_ZLXX SET "
							+ "  PATENTNAME ='" + patentname +"' "
							+ ", PATENTKINDSYMBOL ='" + patentkindsymbol +"' "
							+ ", APPLICANT ='" + applicant +"' "
							+ ", APPLYDATE = TO_DATE('" +  applydate +"','YYYY-MM-DD') " 
							+ ", PUBLISHDATE = TO_DATE('" +  publishdate +"','YYYY-MM-DD') " 
							+ ", DEPTNAME ='" + deptname +"' "
							+ ", AUTHORIZEDATE = TO_DATE('" +  authorizedate +"','YYYY-MM-DD') " 
							+ ", PATENTEE ='" + patentee +"' "
							+ ", AUTHORIZEUNIT ='" + authorizeunit +"' "
							+ ", ATTACH ='" + attach +"' "
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
							+ ", AUTOADD =1 "
							+ ", STATUS =0 "
							+ ", FINALMARK =0 "
							+ ", COPYDATAID ='0:' "
							+ " where ID="+createBoId;
//					System.out.println("updateCreateBoSql="+updateCreateBoSql);
						DBSql.executeUpdate(updateCreateBoSql);
					}
					if(loginId5 != null && !loginId5.equals("")) {
						recordData.put("ID5", loginId5);
						recordData.put("JGH5", jgh5);
						
						queryBindIdSql = "SELECT BINDID FROM BO_ECNU_PEOPLE WHERE LOGINID ='" +loginId5+ "'";
						loginId5BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //绑定新增数据为该人员的bindId
						
						int createBoId = BOInstanceAPI.getInstance().createBOData("BO_ECNU_ZLXX", recordData, loginId5BindId, "admin");
						if(createBoId == -1) {
							createBoFailureNum = createBoFailureNum + 1;
						}
						copyDataIds = copyDataIds + createBoId + ":";
						
//					System.out.println("为 "+loginId5+" 共享数据：createBoId="+createBoId+", BINDID="+loginId5BindId);
						String updateCreateBoSql = "UPDATE BO_ECNU_ZLXX SET "
							+ "  PATENTNAME ='" + patentname +"' "
							+ ", PATENTKINDSYMBOL ='" + patentkindsymbol +"' "
							+ ", APPLICANT ='" + applicant +"' "
							+ ", APPLYDATE = TO_DATE('" +  applydate +"','YYYY-MM-DD') " 
							+ ", PUBLISHDATE = TO_DATE('" +  publishdate +"','YYYY-MM-DD') " 
							+ ", DEPTNAME ='" + deptname +"' "
							+ ", AUTHORIZEDATE = TO_DATE('" +  authorizedate +"','YYYY-MM-DD') " 
							+ ", PATENTEE ='" + patentee +"' "
							+ ", AUTHORIZEUNIT ='" + authorizeunit +"' "
							+ ", ATTACH ='" + attach +"' "
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
							+ ", AUTOADD =1 "
							+ ", STATUS =0 "
							+ ", FINALMARK =0 "
							+ ", COPYDATAID ='0:' "
							+ " where ID="+createBoId;
//					System.out.println("updateCreateBoSql="+updateCreateBoSql);
						DBSql.executeUpdate(updateCreateBoSql);
					}
					
					String updateCopyDataIdSql = "update BO_ECNU_ZLXX set copyDataId ='" +copyDataIds +"' where ID="+dataId;
					DBSql.executeUpdate(updateCopyDataIdSql);
				}
			}
			
			
			String tip = "数据导入成功！共导入 "+totalData+" 条数据。";
			if(createBoFailureNum != 0) {
				tip = tip + "注意！数据导入成功。但是主持人和参与人之间数据共享未初始化成功。共有 " +createBoFailureNum+ " 条初始化失败。建议清空数据库后重新导入Excel！";
			}
			if(noChargeJghData != 0) {
				tip = tip + "注意！导入的数据中有 " +noChargeJghData+ " 条没有负责人信息，只有参与人信息。此类数据不可修改，不可审批。";
			}
			if(notExitUserNameList.size() != 0 && notExitUserNameList.size() != 1) {
				tip = tip + "注意！导入的数据中有 " +notExitUserNameList.size()+ " 条数据中的人员信息在基础库中不存在。";
				tip = tip + "人员如下：";
				for(int i=0; i<notExitUserNameList.size(); i++) {
					if(!notExitUserNameList.get(i).toString().equals("")) {
						tip = tip + notExitUserNameList.get(i).toString() + "; ";
					}
				}
				tip = tip.substring(0, tip.length()-1);
			}
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), tip, true);
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "数据导入后即与相应人员相绑定，管理员无法查看这些数据！", true);
			
		}catch(Exception e){
			e.printStackTrace(System.err);
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，信息导入失败！请重新导入Excel", true);
			return false;
		}finally{
			DBSql.close(conn, ps, rs);
		}
		
		return true;
	}
	
}
