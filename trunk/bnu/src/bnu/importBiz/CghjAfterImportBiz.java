package bnu.importBiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.actionsoft.awf.organization.control.MessageQueue;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.awf.workflow.execute.PriorityType;
import com.actionsoft.awf.workflow.execute.SynType;
import com.actionsoft.loader.core.WorkFlowStepRTClassA;
import com.actionsoft.sdk.local.level0.BOInstanceAPI;
import com.actionsoft.sdk.local.level0.WorkflowInstanceAPI;
import com.actionsoft.sdk.local.level0.WorkflowTaskInstanceAPI;

public class CghjAfterImportBiz extends WorkFlowStepRTClassA {
	
	public CghjAfterImportBiz(UserContext uc){
		super(uc);
		super.setDescription("�ɹ��񽱵���");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}

	public boolean execute() {
		System.out.println(".....................�ɹ��񽱵���begin.�������ݺ�Ϊÿ����������ݰ�����................");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conn = DBSql.open();
			conn.setAutoCommit(true);
			
//			String countSql = "select count(1) totalNum from BO_CGHJTEMP";
//			int totalNum = DBSql.getInt(conn, countSql, "totalNum");
			
			String sql = "select * from BO_CGHJTEMP";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			int dataId = 0;
			
			String havname ="";
			String awardlevel ="";
			String awardname ="";
			String man ="";
			String awarddate ="";
			String sn ="";
			String awardunit ="";
			String deptname ="";
			String attach ="";
			String cgywmc ="";
			String jldj ="";
			String sfbx ="";
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
			String ktly ="";
			String xmpzh ="";
			String cglx ="";
			String cglx2 ="";
			String zsbh ="";
			String gklx ="";
			String xkfl ="";
			String xkfl2 ="";
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
				//���ݴ��ֶ��жϸ������Ƿ񱻴������
				if(copyDataId.equals("")) {
					totalData = totalData + 1;
					dataId = rs.getInt("ID");
					
					havname = rs.getString("havname")==null ?"": rs.getString("havname").trim();
					awardlevel = rs.getString("awardlevel")==null ?"": rs.getString("awardlevel").trim();
					awardname = rs.getString("awardname")==null ?"": rs.getString("awardname").trim();
					man = rs.getString("man")==null ?"": rs.getString("man").trim();
					awarddate = rs.getString("awarddate")==null ?"": rs.getString("awarddate").trim();
					sn = rs.getString("sn")==null ?"": rs.getString("sn").trim();
					awardunit = rs.getString("awardunit")==null ?"": rs.getString("awardunit").trim();
					deptname = rs.getString("deptname")==null ?"": rs.getString("deptname").trim();
					attach = rs.getString("attach")==null ?"": rs.getString("attach").trim();
					cgywmc = rs.getString("cgywmc")==null ?"": rs.getString("cgywmc").trim();
					jldj = rs.getString("jldj")==null ?"": rs.getString("jldj").trim();
					sfbx = rs.getString("sfbx")==null ?"": rs.getString("sfbx").trim();
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
					ktly = rs.getString("ktly")==null ?"": rs.getString("ktly").trim();
					xmpzh = rs.getString("xmpzh")==null ?"": rs.getString("xmpzh").trim();
					cglx = rs.getString("cglx")==null ?"": rs.getString("cglx").trim();
					zsbh = rs.getString("zsbh")==null ?"": rs.getString("zsbh").trim();
					gklx = rs.getString("gklx")==null ?"": rs.getString("gklx").trim();
					xkfl = rs.getString("xkfl")==null ?"": rs.getString("xkfl").trim();
					cglx2 = rs.getString("CGLX2")==null ?"": rs.getString("CGLX2").trim();
					xkfl2 = rs.getString("XKFL2")==null ?"": rs.getString("XKFL2").trim();
					chargerate = rs.getInt("chargerate");
					rate1 = rs.getInt("rate1");
					rate2 = rs.getInt("rate2");
					rate3 = rs.getInt("rate3");
					rate4 = rs.getInt("rate4");
					rate5 = rs.getInt("rate5");
//				
					//Excel���С��������͡��̹��š����ޡ���¼�š���Excel�����name��jgh��ֵ����id��ֵ��
					jgh = rs.getString("JGH")==null?"":rs.getString("JGH").trim();
					jgh1 = rs.getString("JGH1")==null?"":rs.getString("JGH1").trim();
					jgh2 = rs.getString("JGH2")==null?"":rs.getString("JGH2").trim();
					jgh3 = rs.getString("JGH3")==null?"":rs.getString("JGH3").trim();
					jgh4 = rs.getString("JGH4")==null?"":rs.getString("JGH4").trim();
					jgh5 = rs.getString("JGH5")==null?"":rs.getString("JGH5").trim();
					
					if(awarddate!=null && awarddate.length()>=10) {
						awarddate = awarddate.substring(0, 10);
					}
					
					String loginId = "";
					String loginId1 = "";
					String loginId2 = "";
					String loginId3 = "";
					String loginId4 = "";
					String loginId5 = "";
					
					String querySql = "";
					if(jgh!=null && !jgh.equals("")) {
						querySql = "select loginid from BO_RSTEMP where ZGH='" +jgh+"'";
						loginId = DBSql.getString(conn, querySql, "loginid").trim();
					} else {
						noChargeJghData = noChargeJghData + 1;
					}
					if(jgh1!=null && !jgh1.equals("")) {
						querySql = "select loginid from BO_RSTEMP where ZGH='" +jgh1+"'";
						loginId1 = DBSql.getString(conn, querySql, "loginid").trim();
					}
					if(jgh2!=null && !jgh2.equals("")) {
						querySql = "select loginid from BO_RSTEMP where ZGH='" +jgh2+"'";
						loginId2 = DBSql.getString(conn, querySql, "loginid").trim();
					}
					if(jgh3!=null && !jgh3.equals("")) {
						querySql = "select loginid from BO_RSTEMP where ZGH='" +jgh3+"'";
						loginId3 = DBSql.getString(conn, querySql, "loginid").trim();
					}
					if(jgh4!=null && !jgh4.equals("")) {
						querySql = "select loginid from BO_RSTEMP where ZGH='" +jgh4+"'";
						loginId4 = DBSql.getString(conn, querySql, "loginid").trim();
					}
					if(jgh5!=null && !jgh5.equals("")) {
						querySql = "select loginid from BO_RSTEMP where ZGH='" +jgh5+"'";
						loginId5 = DBSql.getString(conn, querySql, "loginid").trim();
					}
					
					
					//�ж��˺��Ƿ����
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
							notExitUserNameList.add(man);
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
						//����AWS����-----------------Ϊ���ݰ�����
//					processInstanceId = WorkflowInstanceAPI.getInstance().createProcessInstance("007b23151fca1ffb1573a7ec1c9ec4ef", loginId, "�ɹ�������");
//					int[] processTaskInstanceIds = WorkflowTaskInstanceAPI.getInstance().createProcessTaskInstance(loginId, processInstanceId, SynType.synchronous, PriorityType.hight, 1, loginId, "�ɹ�������", false, 0);
//					System.out.println("processInstanceId="+processInstanceId+" ,processTaskInstanceIds="+processTaskInstanceIds[0]);
					}
					
					CalMark calMark = new CalMark();
					int finalMark = calMark.getCalMark("BO_CGHJTEMP", dataId);
//					calMark.setFinalMark(chargeid, loginId, jgh, "BO_CGHJTEMP", finalMark);
					String datayear = rs.getString("DATAYEAR")==null?"":rs.getString("DATAYEAR").trim();
					calMark.setFinalMarkWithYear(chargeid, loginId, jgh, "BO_CGHJTEMP", finalMark, datayear);
					
					//���������룬�趨����ֱ�Ӱ�chargeId��Ӧ��bindId
					//����STATUS״̬����Ϊ9����ʾΪ��������ʱ���õ�״̬
					String queryBindIdForChargeIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId+ "'";
					int chargeIdBindId = 0;
					chargeIdBindId = DBSql.getInt(conn, queryBindIdForChargeIdSql, "BINDID");
					
					String updateLoginIdSql = "";
//				if(processInstanceId == 0) {
//					updateLoginIdSql = "update BO_CGHJTEMP set CHARGEID ='" +loginId +"' "
//					+ ", ID1='" +loginId1 + "' "
//					+ ", ID2='" +loginId2 + "' "
//					+ ", ID3='" +loginId3 + "' "
//					+ ", ID4='" +loginId4 + "' "
//					+ ", ID5='" +loginId5 + "' "
//					+ ", AUTOADD=0 "
//					+ ", finalMark="+finalMark
//					+ " where ID="+dataId;
//				} else {
//					updateLoginIdSql = "update BO_CGHJTEMP set CHARGEID ='" +loginId +"' "
//					+ ", ID1='" +loginId1 + "' "
//					+ ", ID2='" +loginId2 + "' "
//					+ ", ID3='" +loginId3 + "' "
//					+ ", ID4='" +loginId4 + "' "
//					+ ", ID5='" +loginId5 + "' "
//					+ ", BINDID=" +processInstanceId
//					+ ", AUTOADD=0 "
//					+ ", finalMark="+finalMark
//					+ " where ID="+dataId;
//				}
					if(chargeIdBindId != 0) {
						updateLoginIdSql = "update BO_CGHJTEMP set CHARGEID ='" +loginId +"' "
						+ ", ID1='" +loginId1 + "' "
						+ ", ID2='" +loginId2 + "' "
						+ ", ID3='" +loginId3 + "' "
						+ ", ID4='" +loginId4 + "' "
						+ ", ID5='" +loginId5 + "' "
						+ ", BINDID=" +chargeIdBindId
						+ ", STATUS=9 "
						+ ", AUTOADD=0 "
						+ ", finalMark="+finalMark
						+ " where ID="+dataId;
					} else {
						updateLoginIdSql = "update BO_CGHJTEMP set CHARGEID ='" +loginId +"' "
						+ ", ID1='" +loginId1 + "' "
						+ ", ID2='" +loginId2 + "' "
						+ ", ID3='" +loginId3 + "' "
						+ ", ID4='" +loginId4 + "' "
						+ ", ID5='" +loginId5 + "' "
						+ ", STATUS=9 "
						+ ", AUTOADD=0 "
						+ ", finalMark="+finalMark
						+ " where ID="+dataId;
					}
					
					//System.out.println(updateLoginIdSql);
					DBSql.executeUpdate(updateLoginIdSql);
					
					
					//**********************���ݹ����֣�Ϊ�������˸���ͬ��������.���autoAdd�ֶ�Ϊ1**************************
					Hashtable recordData = new Hashtable();
					
					int loginId1BindId = 0;
					int loginId2BindId = 0;
					int loginId3BindId = 0;
					int loginId4BindId = 0;
					int loginId5BindId = 0;
					String queryBindIdSql = "";
					String copyDataIds = "0:";
					
					//BOInstanceAPI.getInstance().createBOData.�������ʹ�ø÷������������е�recordData���벻һ��
					if(loginId1 != null && !loginId1.equals("")) {
						recordData.put("ID1", loginId1);
						recordData.put("JGH1", jgh1);
						queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId1+ "'";
						loginId1BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //����������Ϊ����Ա��bindId
						
						int createBoId = BOInstanceAPI.getInstance().createBOData("BO_CGHJTEMP", recordData, loginId1BindId, "admin");
						if(createBoId == -1) {
							createBoFailureNum = createBoFailureNum + 1;
						}
						copyDataIds = copyDataIds + createBoId + ":";
						
//					System.out.println("Ϊ "+loginId1+" �������ݣ�createBoId="+createBoId+", BINDID="+loginId1BindId);
						String updateCreateBoSql = "UPDATE BO_CGHJTEMP SET "
							+ "  HAVNAME ='" + havname +"' "
							+ ", AWARDLEVEL ='" + awardlevel +"' "
							+ ", AWARDNAME ='" + awardname +"' "
							+ ", MAN ='" + man +"' "
							+ ", AWARDDATE = TO_DATE('" +  awarddate +"','YYYY-MM-DD') " 
							+ ", SN ='" + sn +"' "
							+ ", AWARDUNIT ='" + awardunit +"' "
							+ ", DEPTNAME ='" + deptname +"' "
							+ ", ATTACH ='" + attach +"' "
							+ ", CGYWMC ='" + cgywmc +"' "
							+ ", JLDJ ='" + jldj +"' "
							+ ", SFBX ='" + sfbx +"' "
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
							+ ", KTLY ='" + ktly +"' "
							+ ", XMPZH ='" + xmpzh +"' "
							+ ", CGLX ='" + cglx +"' "
							+ ", ZSBH ='" + zsbh +"' "
							+ ", GKLX ='" + gklx +"' "
							+ ", XKFL ='" + xkfl +"' "
							+ ", CGLX2 ='" + cglx2 +"' "
							+ ", XKFL2 ='" + xkfl2 +"' "
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
							+ ", DATAYEAR ='" + datayear +"' " + ", AUTOADD =1 "
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
						
						queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId2+ "'";
						loginId2BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //����������Ϊ����Ա��bindId
						
						int createBoId = BOInstanceAPI.getInstance().createBOData("BO_CGHJTEMP", recordData, loginId2BindId, "admin");
						if(createBoId == -1) {
							createBoFailureNum = createBoFailureNum + 1;
						}
						copyDataIds = copyDataIds + createBoId + ":";
						
//					System.out.println("Ϊ "+loginId2+" �������ݣ�createBoId="+createBoId+", BINDID="+loginId2BindId);
						String updateCreateBoSql = "UPDATE BO_CGHJTEMP SET "
							+ "  HAVNAME ='" + havname +"' "
							+ ", AWARDLEVEL ='" + awardlevel +"' "
							+ ", AWARDNAME ='" + awardname +"' "
							+ ", MAN ='" + man +"' "
							+ ", AWARDDATE = TO_DATE('" +  awarddate +"','YYYY-MM-DD') " 
							+ ", SN ='" + sn +"' "
							+ ", AWARDUNIT ='" + awardunit +"' "
							+ ", DEPTNAME ='" + deptname +"' "
							+ ", ATTACH ='" + attach +"' "
							+ ", CGYWMC ='" + cgywmc +"' "
							+ ", JLDJ ='" + jldj +"' "
							+ ", SFBX ='" + sfbx +"' "
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
							+ ", KTLY ='" + ktly +"' "
							+ ", XMPZH ='" + xmpzh +"' "
							+ ", CGLX ='" + cglx +"' "
							+ ", ZSBH ='" + zsbh +"' "
							+ ", GKLX ='" + gklx +"' "
							+ ", XKFL ='" + xkfl +"' "
							+ ", CGLX2 ='" + cglx2 +"' "
							+ ", XKFL2 ='" + xkfl2 +"' "
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
							+ ", DATAYEAR ='" + datayear +"' " + ", AUTOADD =1 "
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
						
						queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId3+ "'";
						loginId3BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //����������Ϊ����Ա��bindId
						
						int createBoId = BOInstanceAPI.getInstance().createBOData("BO_CGHJTEMP", recordData, loginId3BindId, "admin");
						if(createBoId == -1) {
							createBoFailureNum = createBoFailureNum + 1;
						}
						copyDataIds = copyDataIds + createBoId + ":";
						
//					System.out.println("Ϊ "+loginId3+" �������ݣ�createBoId="+createBoId+", BINDID="+loginId3BindId);
						String updateCreateBoSql = "UPDATE BO_CGHJTEMP SET "
							+ "  HAVNAME ='" + havname +"' "
							+ ", AWARDLEVEL ='" + awardlevel +"' "
							+ ", AWARDNAME ='" + awardname +"' "
							+ ", MAN ='" + man +"' "
							+ ", AWARDDATE = TO_DATE('" +  awarddate +"','YYYY-MM-DD') " 
							+ ", SN ='" + sn +"' "
							+ ", AWARDUNIT ='" + awardunit +"' "
							+ ", DEPTNAME ='" + deptname +"' "
							+ ", ATTACH ='" + attach +"' "
							+ ", CGYWMC ='" + cgywmc +"' "
							+ ", JLDJ ='" + jldj +"' "
							+ ", SFBX ='" + sfbx +"' "
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
							+ ", KTLY ='" + ktly +"' "
							+ ", XMPZH ='" + xmpzh +"' "
							+ ", CGLX ='" + cglx +"' "
							+ ", ZSBH ='" + zsbh +"' "
							+ ", GKLX ='" + gklx +"' "
							+ ", XKFL ='" + xkfl +"' "
							+ ", CGLX2 ='" + cglx2 +"' "
							+ ", XKFL2 ='" + xkfl2 +"' "
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
							+ ", DATAYEAR ='" + datayear +"' " + ", AUTOADD =1 "
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
						
						queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId4+ "'";
						loginId4BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //����������Ϊ����Ա��bindId
						
						int createBoId = BOInstanceAPI.getInstance().createBOData("BO_CGHJTEMP", recordData, loginId4BindId, "admin");
						if(createBoId == -1) {
							createBoFailureNum = createBoFailureNum + 1;
						}
						copyDataIds = copyDataIds + createBoId + ":";
						
//					System.out.println("Ϊ "+loginId4+" �������ݣ�createBoId="+createBoId+", BINDID="+loginId4BindId);
						String updateCreateBoSql = "UPDATE BO_CGHJTEMP SET "
							+ "  HAVNAME ='" + havname +"' "
							+ ", AWARDLEVEL ='" + awardlevel +"' "
							+ ", AWARDNAME ='" + awardname +"' "
							+ ", MAN ='" + man +"' "
							+ ", AWARDDATE = TO_DATE('" +  awarddate +"','YYYY-MM-DD') " 
							+ ", SN ='" + sn +"' "
							+ ", AWARDUNIT ='" + awardunit +"' "
							+ ", DEPTNAME ='" + deptname +"' "
							+ ", ATTACH ='" + attach +"' "
							+ ", CGYWMC ='" + cgywmc +"' "
							+ ", JLDJ ='" + jldj +"' "
							+ ", SFBX ='" + sfbx +"' "
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
							+ ", KTLY ='" + ktly +"' "
							+ ", XMPZH ='" + xmpzh +"' "
							+ ", CGLX ='" + cglx +"' "
							+ ", ZSBH ='" + zsbh +"' "
							+ ", GKLX ='" + gklx +"' "
							+ ", XKFL ='" + xkfl +"' "
							+ ", CGLX2 ='" + cglx2 +"' "
							+ ", XKFL2 ='" + xkfl2 +"' "
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
							+ ", DATAYEAR ='" + datayear +"' " + ", AUTOADD =1 "
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
						
						queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId5+ "'";
						loginId5BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //����������Ϊ����Ա��bindId
						
						int createBoId = BOInstanceAPI.getInstance().createBOData("BO_CGHJTEMP", recordData, loginId5BindId, "admin");
						if(createBoId == -1) {
							createBoFailureNum = createBoFailureNum + 1;
						}
						copyDataIds = copyDataIds + createBoId + ":";
						
//					System.out.println("Ϊ "+loginId5+" �������ݣ�createBoId="+createBoId+", BINDID="+loginId5BindId);
						String updateCreateBoSql = "UPDATE BO_CGHJTEMP SET "
							+ "  HAVNAME ='" + havname +"' "
							+ ", AWARDLEVEL ='" + awardlevel +"' "
							+ ", AWARDNAME ='" + awardname +"' "
							+ ", MAN ='" + man +"' "
							+ ", AWARDDATE = TO_DATE('" +  awarddate +"','YYYY-MM-DD') " 
							+ ", SN ='" + sn +"' "
							+ ", AWARDUNIT ='" + awardunit +"' "
							+ ", DEPTNAME ='" + deptname +"' "
							+ ", ATTACH ='" + attach +"' "
							+ ", CGYWMC ='" + cgywmc +"' "
							+ ", JLDJ ='" + jldj +"' "
							+ ", SFBX ='" + sfbx +"' "
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
							+ ", KTLY ='" + ktly +"' "
							+ ", XMPZH ='" + xmpzh +"' "
							+ ", CGLX ='" + cglx +"' "
							+ ", ZSBH ='" + zsbh +"' "
							+ ", GKLX ='" + gklx +"' "
							+ ", XKFL ='" + xkfl +"' "
							+ ", CGLX2 ='" + cglx2 +"' "
							+ ", XKFL2 ='" + xkfl2 +"' "
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
							+ ", DATAYEAR ='" + datayear +"' " + ", AUTOADD =1 "
							+ ", STATUS =0 "
							+ ", FINALMARK =0 "
							+ ", COPYDATAID ='0:' "
							+ " where ID="+createBoId;
//					System.out.println("updateCreateBoSql="+updateCreateBoSql);
						DBSql.executeUpdate(updateCreateBoSql);
					}
					
					String updateCopyDataIdSql = "update BO_CGHJTEMP set copyDataId ='" +copyDataIds +"' where ID="+dataId;
					DBSql.executeUpdate(updateCopyDataIdSql);
				}
			}
			
			
			String tip = "���ݵ���ɹ��������� "+totalData+" �����ݡ�";
			if(createBoFailureNum != 0) {
				tip = tip + "ע�⣡���ݵ���ɹ������������˺Ͳ�����֮�����ݹ���δ��ʼ���ɹ������� " +createBoFailureNum+ " ����ʼ��ʧ�ܡ�����������ݿ�����µ���Excel��";
			}
			if(noChargeJghData != 0) {
				tip = tip + "ע�⣡������������� " +noChargeJghData+ " ��û�и�������Ϣ��ֻ�в�������Ϣ���������ݲ����޸ģ�����������";
			}
			if(notExitUserNameList.size() != 0 && notExitUserNameList.size() != 1) {
				tip = tip + "ע�⣡������������� " +notExitUserNameList.size()+ " �������е���Ա��Ϣ�ڻ������в����ڡ�";
				tip = tip + "��Ա���£�";
				for(int i=0; i<notExitUserNameList.size(); i++) {
					if(!notExitUserNameList.get(i).toString().equals("")) {
						tip = tip + notExitUserNameList.get(i).toString() + "; ";
					}
				}
				tip = tip.substring(0, tip.length()-1);
			}
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), tip, true);
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "���ݵ��������Ӧ��Ա��󶨣�����Ա�޷��鿴��Щ���ݣ�", true);
			
		}catch(Exception e){
			e.printStackTrace(System.err);
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "���棬��Ϣ����ʧ�ܣ������µ���Excel", true);
			return false;
		}finally{
			DBSql.close(conn, ps, rs);
		}
		
		return true;
	}
	
}
