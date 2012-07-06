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

//�������_�����ɹ� 

public class QtcgAfterSaveBiz extends WorkFlowStepRTClassA {
	
	public QtcgAfterSaveBiz(UserContext uc){
		super(uc);
		super.setDescription("�������_�����ɹ�_����ɹ�:���Ľ̹���,���¹�������");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}
	
	@Override
	public boolean execute() {
		System.out.println(".....................�������_�����ɹ�_����:���Ľ̹���,���¹�������begin.....................");
		
		int workflowInstanceId = getParameter(this.PARAMETER_INSTANCE_ID).toInt();//��õ�ǰ��������id
		
		Hashtable rsTable = BOInstanceAPI.getInstance().getBOData("BO_QTCGTEMP", workflowInstanceId);
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
			
			String havname = rsTable.get("HAVNAME").toString() == null ? "" : rsTable.get("HAVNAME").toString().trim();
			String havkindsymbol = rsTable.get("HAVKINDSYMBOL").toString() == null ? "" : rsTable.get("HAVKINDSYMBOL").toString().trim();
			String manageoffice = rsTable.get("MANAGEOFFICE").toString() == null ? "" : rsTable.get("MANAGEOFFICE").toString().trim();
			String appraiseunit = rsTable.get("APPRAISEUNIT").toString() == null ? "" : rsTable.get("APPRAISEUNIT").toString().trim();
			String appraisedate = rsTable.get("APPRAISEDATE").toString() == null ? "" : rsTable.get("APPRAISEDATE").toString().trim();
			String appraisereportcode = rsTable.get("APPRAISEREPORTCODE").toString() == null ? "" : rsTable.get("APPRAISEREPORTCODE").toString().trim();
			String subject = rsTable.get("SUBJECT").toString() == null ? "" : rsTable.get("SUBJECT").toString().trim();
			String attach = rsTable.get("ATTACH").toString() == null ? "" : rsTable.get("ATTACH").toString().trim();
			String cgywmc = rsTable.get("CGYWMC").toString() == null ? "" : rsTable.get("CGYWMC").toString().trim();
			String yxbm = rsTable.get("YXBM").toString() == null ? "" : rsTable.get("YXBM").toString().trim();
			String cndw = rsTable.get("CNDW").toString() == null ? "" : rsTable.get("CNDW").toString().trim();
			String chargenm = rsTable.get("CHARGENM").toString() == null ? "" : rsTable.get("CHARGENM").toString().trim();
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
			String wcrsf = rsTable.get("WCRSF").toString() == null ? "" : rsTable.get("WCRSF").toString().trim();
			String sdzt = rsTable.get("SDZT").toString() == null ? "" : rsTable.get("SDZT").toString().trim();
			String cbdw = rsTable.get("CBDW").toString() == null ? "" : rsTable.get("CBDW").toString().trim();
			String cbbh = rsTable.get("CBBH").toString() == null ? "" : rsTable.get("CBBH").toString().trim();
			String cbsj = rsTable.get("CBSJ").toString() == null ? "" : rsTable.get("CBSJ").toString().trim();
			String qkmc = rsTable.get("QKMC").toString() == null ? "" : rsTable.get("QKMC").toString().trim();
			String fbqk = rsTable.get("FBQK").toString() == null ? "" : rsTable.get("FBQK").toString().trim();
			String qklb = rsTable.get("QKLB").toString() == null ? "" : rsTable.get("QKLB").toString().trim();
			String ym = rsTable.get("YM").toString() == null ? "" : rsTable.get("YM").toString().trim();
			String bzmc = rsTable.get("BZMC").toString() == null ? "" : rsTable.get("BZMC").toString().trim();
			String bm = rsTable.get("BM").toString() == null ? "" : rsTable.get("BM").toString().trim();
			String zs = rsTable.get("ZS").toString() == null ? "" : rsTable.get("ZS").toString().trim();
			String fbsj = rsTable.get("FBSJ").toString() == null ? "" : rsTable.get("FBSJ").toString().trim();
//			int chargerate = Integer.parseInt(rsTable.get("CHARGERATE").toString() == null ? "0" : rsTable.get("CHARGERATE").toString().trim());
//			int rate1 = Integer.parseInt(rsTable.get("RATE1").toString() == null ? "0" : rsTable.get("RATE1").toString().trim());
//			int rate2 = Integer.parseInt(rsTable.get("RATE2").toString() == null ? "0" : rsTable.get("RATE2").toString().trim());
//			int rate3 = Integer.parseInt(rsTable.get("RATE3").toString() == null ? "0" : rsTable.get("RATE3").toString().trim());
//			int rate4 = Integer.parseInt(rsTable.get("RATE4").toString() == null ? "0" : rsTable.get("RATE4").toString().trim());
//			int rate5 = Integer.parseInt(rsTable.get("RATE5").toString() == null ? "0" : rsTable.get("RATE5").toString().trim());
			//����ȥ��������������
			int chargerate = 0;
			int rate1 = 0;
			int rate2 = 0;
			int rate3 = 0;
			int rate4 = 0;
			int rate5 = 0;
			
			if(cbsj!=null && cbsj.length()>=10) {
				cbsj = cbsj.substring(0, 10);
			}
			if(fbsj!=null && fbsj.length()>=10) {
				fbsj = fbsj.substring(0, 10);
			}
			
			//��֤begin

			String datayear = rsTable.get("DATAYEAR").toString()==null?"":rsTable.get("DATAYEAR").toString().trim();
			if(datayear.equals("") || datayear.length() != 4) {
				MessageQueue.getInstance().putMessage(getUserContext().getUID(), "���棬��������ȡ�������д�����Ҹ�ʽΪ��λ������ȷ��!", true);
				return false;
			}
			//��֤end
			
			
			//��loginId���ޡ��̹��š�
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

			//�ж�ǰ̨�Ƿ����ò����ˡ����û�У��ÿ�ԭ��������
			if(chargenm == null || chargenm.equals("")) {
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
			
			if(loginId!=null && !loginId.equals("") && (loginId.indexOf("<")>0)) {   //DB�д��Ϊ��loginId<����>"��ʽ
				loginId = loginId.substring(0, loginId.indexOf("<"));
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId+"'";
				jgh = DBSql.getString(conn, querySql, "ZGH").trim();
			} else if(loginId!=null && !loginId.equals("")) {   //DB�д��Ϊ��loginId����ʽ
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId+"'";
				jgh = DBSql.getString(conn, querySql, "ZGH".trim());
			}
			if(loginId1!=null && !loginId1.equals("") && (loginId1.indexOf("<")>0)) {   //DB�д��Ϊ��loginId<����>"��ʽ
				loginId1 = loginId1.substring(0, loginId1.indexOf("<"));
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId1+"'";
				jgh1 = DBSql.getString(conn, querySql, "ZGH").trim();
			} else if(loginId1!=null && !loginId1.equals("")) {   //DB�д��Ϊ��loginId����ʽ
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId1+"'";
				jgh1 = DBSql.getString(conn, querySql, "ZGH").trim();
			}
			if(loginId2!=null && !loginId2.equals("") && (loginId2.indexOf("<")>0)) {   //DB�д��Ϊ��loginId<����>"��ʽ
				loginId2 = loginId2.substring(0, loginId2.indexOf("<"));
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId2+"'";
				jgh2 = DBSql.getString(conn, querySql, "ZGH").trim();
			} else if(loginId2!=null && !loginId2.equals("")) {   //DB�д��Ϊ��loginId����ʽ
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId2+"'";
				jgh2 = DBSql.getString(conn, querySql, "ZGH").trim();
			}
			if(loginId3!=null && !loginId3.equals("") && (loginId3.indexOf("<")>0)) {   //DB�д��Ϊ��loginId<����>"��ʽ
				loginId3 = loginId3.substring(0, loginId3.indexOf("<"));
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId3+"'";
				jgh3 = DBSql.getString(conn, querySql, "ZGH").trim();
			} else if(loginId3!=null && !loginId3.equals("")) {   //DB�д��Ϊ��loginId����ʽ
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId3+"'";
				jgh3 = DBSql.getString(conn, querySql, "ZGH").trim();
			}
			if(loginId4!=null && !loginId4.equals("") && (loginId4.indexOf("<")>0)) {   //DB�д��Ϊ��loginId<����>"��ʽ
				loginId4 = loginId4.substring(0, loginId4.indexOf("<"));
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId4+"'";
				jgh4 = DBSql.getString(conn, querySql, "ZGH").trim();
			} else if(loginId4!=null && !loginId4.equals("")) {   //DB�д��Ϊ��loginId����ʽ
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId4+"'";
				jgh4 = DBSql.getString(conn, querySql, "ZGH").trim();
			}
			if(loginId5!=null && !loginId5.equals("") && (loginId5.indexOf("<")>0)) {   //DB�д��Ϊ��loginId<����>"��ʽ
				loginId5 = loginId5.substring(0, loginId5.indexOf("<"));
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId5+"'";
				jgh5 = DBSql.getString(conn, querySql, "ZGH").trim();
			} else if(loginId5!=null && !loginId5.equals("")) {   //DB�д��Ϊ��loginId����ʽ
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId5+"'";
				jgh5 = DBSql.getString(conn, querySql, "ZGH").trim();
			}
			
			String updateInstanceSql = "update BO_QTCGTEMP set JGH ='" +jgh +"' "
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
			
			
			
			/********************************���ݹ�����ɾ��������Ϣ������ӡ����ж�Ӧ�Ĳ���*****************************/
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
					String deleteSql = "delete from BO_QTCGTEMP where ID ="+copyDataIdInt;
//					System.out.println("deleteSql="+deleteSql);
					DBSql.executeUpdate(deleteSql); //ɾ�����ݡ�������
				}
			}
			
			//**********************���ݹ����֣�Ϊ�������˸���ͬ��������.���autoAdd�ֶ�Ϊ1**************************
			Hashtable recordData = new Hashtable();
			int loginId1BindId = 0;
			int loginId2BindId = 0;
			int loginId3BindId = 0;
			int loginId4BindId = 0;
			int loginId5BindId = 0;
			String queryBindIdSql = "";
			int createBoFailureNum = 0;
			String copyDataIds = "0:";
			
			//BOInstanceAPI.getInstance().createBOData.�������ʹ�ø÷������������е�recordData���벻һ��
			if(loginId1 != null && !loginId1.equals("")) {
				recordData.put("ID1", loginId1);
				recordData.put("JGH1", jgh1);
				queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId1+ "'";
				loginId1BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //����������Ϊ����Ա��bindId

				int createBoId = BOInstanceAPI.getInstance().createBOData("BO_QTCGTEMP", recordData, loginId1BindId, createuser);
				if(createBoId == -1) {
					createBoFailureNum = createBoFailureNum + 1;
				} else {
					copyDataIds = copyDataIds + createBoId + ":";
				}
				
//				System.out.println("Ϊ "+loginId1+" �������ݣ�createBoId="+createBoId+", BINDID="+loginId1BindId);
				String updateCreateBoSql = "UPDATE BO_QTCGTEMP SET "
				    + "  HAVNAME ='" + havname +"' "
				    + ", HAVKINDSYMBOL ='" + havkindsymbol +"' "
				    + ", MANAGEOFFICE ='" + manageoffice +"' "
				    + ", APPRAISEUNIT ='" + appraiseunit +"' "
				    + ", APPRAISEDATE ='" + appraisedate +"' "
				    + ", APPRAISEREPORTCODE ='" + appraisereportcode +"' "
				    + ", SUBJECT ='" + subject +"' "
				    + ", ATTACH ='" + attach +"' "
				    + ", CGYWMC ='" + cgywmc +"' "
				    + ", YXBM ='" + yxbm +"' "
				    + ", CNDW ='" + cndw +"' "
				    + ", CHARGENM ='" + chargenm +"' "
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
				    + ", JGH ='" + jgh +"' "
				    + ", JGH1 ='" + jgh1 +"' "
				    + ", JGH2 ='" + jgh2 +"' "
				    + ", JGH3 ='" + jgh3 +"' "
				    + ", JGH5 ='" + jgh5 +"' "
				    + ", JGH4 ='" + jgh4 +"' "
				    + ", CHARGERATE =" + chargerate
					+ ", RATE1 =" + rate1
					+ ", RATE2 =" + rate2
					+ ", RATE3 =" + rate3
					+ ", RATE4 =" + rate4
					+ ", RATE5 =" + rate5
					+ ", WCRSF ='" + wcrsf +"' "
				    + ", SDZT ='" + sdzt +"' "
				    + ", CBDW ='" + cbdw +"' "
				    + ", CBBH ='" + cbbh +"' "
				    + ", CBSJ = TO_DATE('" + cbsj +"','YYYY-MM-DD') "
				    + ", QKMC ='" + qkmc +"' "
				    + ", FBQK ='" + fbqk +"' "
				    + ", QKLB ='" + qklb +"' "
				    + ", YM ='" + ym +"' "
				    + ", BZMC ='" + bzmc +"' "
				    + ", BM ='" + bm +"' "
				    + ", ZS ='" + zs +"' "
				    + ", FBSJ = TO_DATE('" + fbsj +"','YYYY-MM-DD') "
				    + ", DATAYEAR ='" + datayear +"' "
					+ ", AUTOADD =1 "
			        + ", FINALMARK =0 "
			        + ", COPYDATAID ='0:' "
					+ " WHERE ID="+createBoId;
//				System.out.println("updateCreateBoSql="+updateCreateBoSql);
				DBSql.executeUpdate(updateCreateBoSql);
			}
			if(loginId2 != null && !loginId2.equals("")) {
				recordData.put("ID2", loginId2);
				recordData.put("JGH2", jgh2);
				
				queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId2+ "'";
				loginId2BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //����������Ϊ����Ա��bindId
				
				int createBoId = BOInstanceAPI.getInstance().createBOData("BO_QTCGTEMP", recordData, loginId2BindId, createuser);
				if(createBoId == -1) {
					createBoFailureNum = createBoFailureNum + 1;
				} else {
					copyDataIds = copyDataIds + createBoId + ":";
				}
//				System.out.println("Ϊ "+loginId2+" �������ݣ�createBoId="+createBoId+", BINDID="+loginId2BindId);
				String updateCreateBoSql = "UPDATE BO_QTCGTEMP SET "
				    + "  HAVNAME ='" + havname +"' "
				    + ", HAVKINDSYMBOL ='" + havkindsymbol +"' "
				    + ", MANAGEOFFICE ='" + manageoffice +"' "
				    + ", APPRAISEUNIT ='" + appraiseunit +"' "
				    + ", APPRAISEDATE ='" + appraisedate +"' "
				    + ", APPRAISEREPORTCODE ='" + appraisereportcode +"' "
				    + ", SUBJECT ='" + subject +"' "
				    + ", ATTACH ='" + attach +"' "
				    + ", CGYWMC ='" + cgywmc +"' "
				    + ", YXBM ='" + yxbm +"' "
				    + ", CNDW ='" + cndw +"' "
				    + ", CHARGENM ='" + chargenm +"' "
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
				    + ", JGH ='" + jgh +"' "
				    + ", JGH1 ='" + jgh1 +"' "
				    + ", JGH2 ='" + jgh2 +"' "
				    + ", JGH3 ='" + jgh3 +"' "
				    + ", JGH5 ='" + jgh5 +"' "
				    + ", JGH4 ='" + jgh4 +"' "
				    + ", CHARGERATE =" + chargerate
					+ ", RATE1 =" + rate1
					+ ", RATE2 =" + rate2
					+ ", RATE3 =" + rate3
					+ ", RATE4 =" + rate4
					+ ", RATE5 =" + rate5
					+ ", WCRSF ='" + wcrsf +"' "
				    + ", SDZT ='" + sdzt +"' "
				    + ", CBDW ='" + cbdw +"' "
				    + ", CBBH ='" + cbbh +"' "
				    + ", CBSJ = TO_DATE('" + cbsj +"','YYYY-MM-DD') "
				    + ", QKMC ='" + qkmc +"' "
				    + ", FBQK ='" + fbqk +"' "
				    + ", QKLB ='" + qklb +"' "
				    + ", YM ='" + ym +"' "
				    + ", BZMC ='" + bzmc +"' "
				    + ", BM ='" + bm +"' "
				    + ", ZS ='" + zs +"' "
				    + ", FBSJ = TO_DATE('" + fbsj +"','YYYY-MM-DD') "
				    + ", DATAYEAR ='" + datayear +"' "
					+ ", AUTOADD =1 "
			        + ", FINALMARK =0 "
			        + ", STATUS =0 "
			        + ", FINALMARK =0 "
			        + ", COPYDATAID ='0:' "
					+ " WHERE ID="+createBoId;
//				System.out.println("updateCreateBoSql="+updateCreateBoSql);
				DBSql.executeUpdate(updateCreateBoSql);
			}
			if(loginId3 != null && !loginId3.equals("")) {
				recordData.put("ID3", loginId3);
				recordData.put("JGH3", jgh3);
				
				queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId3+ "'";
				loginId3BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //����������Ϊ����Ա��bindId
				
				int createBoId = BOInstanceAPI.getInstance().createBOData("BO_QTCGTEMP", recordData, loginId3BindId, createuser);
				if(createBoId == -1) {
					createBoFailureNum = createBoFailureNum + 1;
				} else {
					copyDataIds = copyDataIds + createBoId + ":";
				}
//				System.out.println("Ϊ "+loginId3+" �������ݣ�createBoId="+createBoId+", BINDID="+loginId3BindId);
				String updateCreateBoSql = "UPDATE BO_QTCGTEMP SET "
				    + "  HAVNAME ='" + havname +"' "
				    + ", HAVKINDSYMBOL ='" + havkindsymbol +"' "
				    + ", MANAGEOFFICE ='" + manageoffice +"' "
				    + ", APPRAISEUNIT ='" + appraiseunit +"' "
				    + ", APPRAISEDATE ='" + appraisedate +"' "
				    + ", APPRAISEREPORTCODE ='" + appraisereportcode +"' "
				    + ", SUBJECT ='" + subject +"' "
				    + ", ATTACH ='" + attach +"' "
				    + ", CGYWMC ='" + cgywmc +"' "
				    + ", YXBM ='" + yxbm +"' "
				    + ", CNDW ='" + cndw +"' "
				    + ", CHARGENM ='" + chargenm +"' "
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
				    + ", JGH ='" + jgh +"' "
				    + ", JGH1 ='" + jgh1 +"' "
				    + ", JGH2 ='" + jgh2 +"' "
				    + ", JGH3 ='" + jgh3 +"' "
				    + ", JGH5 ='" + jgh5 +"' "
				    + ", JGH4 ='" + jgh4 +"' "
				    + ", CHARGERATE =" + chargerate
					+ ", RATE1 =" + rate1
					+ ", RATE2 =" + rate2
					+ ", RATE3 =" + rate3
					+ ", RATE4 =" + rate4
					+ ", RATE5 =" + rate5
					+ ", WCRSF ='" + wcrsf +"' "
				    + ", SDZT ='" + sdzt +"' "
				    + ", CBDW ='" + cbdw +"' "
				    + ", CBBH ='" + cbbh +"' "
				    + ", CBSJ = TO_DATE('" + cbsj +"','YYYY-MM-DD') "
				    + ", QKMC ='" + qkmc +"' "
				    + ", FBQK ='" + fbqk +"' "
				    + ", QKLB ='" + qklb +"' "
				    + ", YM ='" + ym +"' "
				    + ", BZMC ='" + bzmc +"' "
				    + ", BM ='" + bm +"' "
				    + ", ZS ='" + zs +"' "
				    + ", FBSJ = TO_DATE('" + fbsj +"','YYYY-MM-DD') "
				    + ", DATAYEAR ='" + datayear +"' "
					+ ", AUTOADD =1 "
			        + ", FINALMARK =0 "
			        + ", STATUS =0 "
			        + ", FINALMARK =0 "
			        + ", COPYDATAID ='0:' "
					+ " WHERE ID="+createBoId;
//				System.out.println("updateCreateBoSql="+updateCreateBoSql);
				DBSql.executeUpdate(updateCreateBoSql);
			}
			if(loginId4 != null && !loginId4.equals("")) {
				recordData.put("ID4", loginId4);
				recordData.put("JGH4", jgh4);
				
				queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId4+ "'";
				loginId4BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //����������Ϊ����Ա��bindId
				
				int createBoId = BOInstanceAPI.getInstance().createBOData("BO_QTCGTEMP", recordData, loginId4BindId, createuser);
				if(createBoId == -1) {
					createBoFailureNum = createBoFailureNum + 1;
				} else {
					copyDataIds = copyDataIds + createBoId + ":";
				}
//				System.out.println("Ϊ "+loginId4+" �������ݣ�createBoId="+createBoId+", BINDID="+loginId4BindId);
				String updateCreateBoSql = "UPDATE BO_QTCGTEMP SET "
				    + "  HAVNAME ='" + havname +"' "
				    + ", HAVKINDSYMBOL ='" + havkindsymbol +"' "
				    + ", MANAGEOFFICE ='" + manageoffice +"' "
				    + ", APPRAISEUNIT ='" + appraiseunit +"' "
				    + ", APPRAISEDATE ='" + appraisedate +"' "
				    + ", APPRAISEREPORTCODE ='" + appraisereportcode +"' "
				    + ", SUBJECT ='" + subject +"' "
				    + ", ATTACH ='" + attach +"' "
				    + ", CGYWMC ='" + cgywmc +"' "
				    + ", YXBM ='" + yxbm +"' "
				    + ", CNDW ='" + cndw +"' "
				    + ", CHARGENM ='" + chargenm +"' "
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
				    + ", JGH ='" + jgh +"' "
				    + ", JGH1 ='" + jgh1 +"' "
				    + ", JGH2 ='" + jgh2 +"' "
				    + ", JGH3 ='" + jgh3 +"' "
				    + ", JGH5 ='" + jgh5 +"' "
				    + ", JGH4 ='" + jgh4 +"' "
				    + ", CHARGERATE =" + chargerate
					+ ", RATE1 =" + rate1
					+ ", RATE2 =" + rate2
					+ ", RATE3 =" + rate3
					+ ", RATE4 =" + rate4
					+ ", RATE5 =" + rate5
					+ ", WCRSF ='" + wcrsf +"' "
				    + ", SDZT ='" + sdzt +"' "
				    + ", CBDW ='" + cbdw +"' "
				    + ", CBBH ='" + cbbh +"' "
				    + ", CBSJ = TO_DATE('" + cbsj +"','YYYY-MM-DD') "
				    + ", QKMC ='" + qkmc +"' "
				    + ", FBQK ='" + fbqk +"' "
				    + ", QKLB ='" + qklb +"' "
				    + ", YM ='" + ym +"' "
				    + ", BZMC ='" + bzmc +"' "
				    + ", BM ='" + bm +"' "
				    + ", ZS ='" + zs +"' "
				    + ", FBSJ = TO_DATE('" + fbsj +"','YYYY-MM-DD') "
				    + ", DATAYEAR ='" + datayear +"' "
					+ ", AUTOADD =1 "
			        + ", FINALMARK =0 "
			        + ", STATUS =0 "
			        + ", FINALMARK =0 "
			        + ", COPYDATAID ='0:' "
					+ " WHERE ID="+createBoId;
//				System.out.println("updateCreateBoSql="+updateCreateBoSql);
				DBSql.executeUpdate(updateCreateBoSql);
			}
			if(loginId5 != null && !loginId5.equals("")) {
				recordData.put("ID5", loginId5);
				recordData.put("JGH5", jgh5);
				
				queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId5+ "'";
				loginId5BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //����������Ϊ����Ա��bindId
				
				int createBoId = BOInstanceAPI.getInstance().createBOData("BO_QTCGTEMP", recordData, loginId5BindId, createuser);
				if(createBoId == -1) {
					createBoFailureNum = createBoFailureNum + 1;
				} else {
					copyDataIds = copyDataIds + createBoId + ":";
				}
//				System.out.println("Ϊ "+loginId5+" �������ݣ�createBoId="+createBoId+", BINDID="+loginId5BindId);
				String updateCreateBoSql = "UPDATE BO_QTCGTEMP SET "
				    + "  HAVNAME ='" + havname +"' "
				    + ", HAVKINDSYMBOL ='" + havkindsymbol +"' "
				    + ", MANAGEOFFICE ='" + manageoffice +"' "
				    + ", APPRAISEUNIT ='" + appraiseunit +"' "
				    + ", APPRAISEDATE ='" + appraisedate +"' "
				    + ", APPRAISEREPORTCODE ='" + appraisereportcode +"' "
				    + ", SUBJECT ='" + subject +"' "
				    + ", ATTACH ='" + attach +"' "
				    + ", CGYWMC ='" + cgywmc +"' "
				    + ", YXBM ='" + yxbm +"' "
				    + ", CNDW ='" + cndw +"' "
				    + ", CHARGENM ='" + chargenm +"' "
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
				    + ", JGH ='" + jgh +"' "
				    + ", JGH1 ='" + jgh1 +"' "
				    + ", JGH2 ='" + jgh2 +"' "
				    + ", JGH3 ='" + jgh3 +"' "
				    + ", JGH5 ='" + jgh5 +"' "
				    + ", JGH4 ='" + jgh4 +"' "
				    + ", CHARGERATE =" + chargerate
					+ ", RATE1 =" + rate1
					+ ", RATE2 =" + rate2
					+ ", RATE3 =" + rate3
					+ ", RATE4 =" + rate4
					+ ", RATE5 =" + rate5
					+ ", WCRSF ='" + wcrsf +"' "
				    + ", SDZT ='" + sdzt +"' "
				    + ", CBDW ='" + cbdw +"' "
				    + ", CBBH ='" + cbbh +"' "
				    + ", CBSJ = TO_DATE('" + cbsj +"','YYYY-MM-DD') "
				    + ", QKMC ='" + qkmc +"' "
				    + ", FBQK ='" + fbqk +"' "
				    + ", QKLB ='" + qklb +"' "
				    + ", YM ='" + ym +"' "
				    + ", BZMC ='" + bzmc +"' "
				    + ", BM ='" + bm +"' "
				    + ", ZS ='" + zs +"' "
				    + ", FBSJ = TO_DATE('" + fbsj +"','YYYY-MM-DD') "
				    + ", DATAYEAR ='" + datayear +"' "
					+ ", AUTOADD =1 "
			        + ", FINALMARK =0 "
			        + ", STATUS =0 "
			        + ", FINALMARK =0 "
			        + ", COPYDATAID ='0:' "
					+ " WHERE ID="+createBoId;
//				System.out.println("updateCreateBoSql="+updateCreateBoSql);
				DBSql.executeUpdate(updateCreateBoSql);
			}

			String updateCopyDataIdSql = "update BO_QTCGTEMP set copyDataId ='" +copyDataIds +"' where ID="+mainDataId;
			DBSql.executeUpdate(updateCopyDataIdSql);
			
			String tip = "���ݱ���ɹ���";
			if(createBoFailureNum != 0) {
				tip = tip + "ע�⣡�����˺Ͳ�����֮�����ݹ���δ��ʼ���ɹ������� " +createBoFailureNum+ " �������˱���ʧ�ܡ���";
				MessageQueue.getInstance().putMessage(getUserContext().getUID(), tip, true);
				return false;
			}
			
		}catch(Exception e){
			e.printStackTrace(System.err);
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "���棬��Ϣ����ʧ�ܣ�", true);
			return false;
		}finally{
			DBSql.close(conn, ps, rs);
		}
		
		
		return true;
	}
	
}
