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

public class YjbgAfterImportBiz extends WorkFlowStepRTClassA {
	
	public YjbgAfterImportBiz(UserContext uc){
		super(uc);
		super.setDescription("�о����浼��");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}

	public boolean execute() {
		System.out.println(".....................�о����浼��begin.�������ݺ�Ϊÿ����������ݰ�����................");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conn = DBSql.open();
			conn.setAutoCommit(true);
			
//			String countSql = "select count(1) totalNum from BO_ECNU_YJBG";
//			int totalNum = DBSql.getInt(conn, countSql, "totalNum");
			
			String sql = "select * from BO_ECNU_YJBG";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			int dataId = 0;
			
			String chargenm = "";
			int chargerate = 0;
			String name1 = "";
			int rate1 = 0;
			String name2 = "";
			int rate2 = 0;
			String name3 = "";
			int rate3 = 0;
			String name4 = "";
			int rate4 = 0;
			String name5 = "";
			int rate5 = 0;
			String cgmc = null;  // �ɹ�����
			String cgywmc = null;  // �ɹ���������
			String cglx = null;  // �ɹ�����
			String gklx = null;  // �������
			String pjdw = null;  // ���۵�λ
			String cndw = null;  // ���ɵ�λ
			String rdrq = null;  // �϶�����
			String zsbh = null;  // ֤����
			String subject = null;  // һ��ѧ��
			String subject2 = null;  // ����ѧ��
			String wcrsf = null;  // ��һ��������
			String szxsjg = null;  // ����ѧ������
			String yxbm = null;  // Ժϵ����
			String zzlx = null;  // ��������
			String fbfw = null;  // ����Χ
			String fbsj = null;  // ��������
			String fbnd = null;  // �������
			String yjlx = null;  // �о����
			String xmjb = null;  // ������Դ(��Ŀ����)
			String cgyyqk = null;  // �ɹ�Ӧ�����
			String sfcn = null;  // �Ƿ�˾�����ϲ���
			
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
					
					chargenm = rs.getString("chargenm")==null?"":rs.getString("chargenm").trim();
					chargerate = rs.getInt("chargerate");
					name1 = rs.getString("name1")==null?"":rs.getString("name1").trim();
					rate1 = rs.getInt("rate1");
					name2 = rs.getString("name2")==null?"":rs.getString("name2").trim();
					rate2 = rs.getInt("rate2");
					name3 = rs.getString("name3")==null?"":rs.getString("name3").trim();
					rate3 = rs.getInt("rate3");
					name4 = rs.getString("name4")==null?"":rs.getString("name4").trim();
					rate4 = rs.getInt("rate4");
					name5 = rs.getString("name5")==null?"":rs.getString("name5").trim();
					rate5 = rs.getInt("rate5");
					cgmc = rs.getString("CGMC")==null ? "" :rs.getString("CGMC"); //�ɹ�����
					cgywmc = rs.getString("CGYWMC")==null ? "" :rs.getString("CGYWMC"); //�ɹ���������
					cglx = rs.getString("CGLX")==null ? "" :rs.getString("CGLX"); //�ɹ�����
					gklx = rs.getString("GKLX")==null ? "" :rs.getString("GKLX"); //�������
					pjdw = rs.getString("PJDW")==null ? "" :rs.getString("PJDW"); //���۵�λ
					cndw = rs.getString("CNDW")==null ? "" :rs.getString("CNDW"); //���ɵ�λ
					rdrq = rs.getString("RDRQ")==null ? "" :rs.getString("RDRQ"); //�϶�����
					zsbh = rs.getString("ZSBH")==null ? "" :rs.getString("ZSBH"); //֤����
					subject = rs.getString("SUBJECT")==null ? "" :rs.getString("SUBJECT"); //һ��ѧ��
					subject2 = rs.getString("SUBJECT2")==null ? "" :rs.getString("SUBJECT2"); //����ѧ��
					wcrsf = rs.getString("WCRSF")==null ? "" :rs.getString("WCRSF"); //��һ��������
					szxsjg = rs.getString("SZXSJG")==null ? "" :rs.getString("SZXSJG"); //����ѧ������
					yxbm = rs.getString("YXBM")==null ? "" :rs.getString("YXBM"); //Ժϵ����
					zzlx = rs.getString("ZZLX")==null ? "" :rs.getString("ZZLX"); //��������
					fbfw = rs.getString("FBFW")==null ? "" :rs.getString("FBFW"); //����Χ
					fbsj = rs.getString("FBSJ")==null ? "" :rs.getString("FBSJ"); //��������
					fbnd = rs.getString("FBND")==null ? "" :rs.getString("FBND"); //�������
					yjlx = rs.getString("YJLX")==null ? "" :rs.getString("YJLX"); //�о����
					xmjb = rs.getString("XMJB")==null ? "" :rs.getString("XMJB"); //������Դ(��Ŀ����)
					cgyyqk = rs.getString("CGYYQK")==null ? "" :rs.getString("CGYYQK"); //�ɹ�Ӧ�����
					sfcn = rs.getString("SFCN")==null ? "" :rs.getString("SFCN"); //�Ƿ�˾�����ϲ���
					
					//Excel���С��������͡��̹��š����ޡ���¼�š���Excel�����name��jgh��ֵ����id��ֵ��
					jgh = rs.getString("JGH")==null?"":rs.getString("JGH").trim();
					jgh1 = rs.getString("JGH1")==null?"":rs.getString("JGH1").trim();
					jgh2 = rs.getString("JGH2")==null?"":rs.getString("JGH2").trim();
					jgh3 = rs.getString("JGH3")==null?"":rs.getString("JGH3").trim();
					jgh4 = rs.getString("JGH4")==null?"":rs.getString("JGH4").trim();
					jgh5 = rs.getString("JGH5")==null?"":rs.getString("JGH5").trim();
					
					if(rdrq!=null && rdrq.length()>=10) {
						rdrq = rdrq.substring(0, 10);
					}
					
					if(fbsj!=null && fbsj.length()>=10) {
						fbsj = fbsj.substring(0, 10);
					}
					if(fbnd == null || fbnd.equals("")) {
						if(fbsj!=null && fbsj.length()==10) {
							fbnd = fbsj.substring(0, 4);
						}
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
							notExitUserNameList.add(chargenm);
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
//					processInstanceId = WorkflowInstanceAPI.getInstance().createProcessInstance("007b23151fd6ea094d1dade015af3e4f", loginId, "�о���������");
//					int[] processTaskInstanceIds = WorkflowTaskInstanceAPI.getInstance().createProcessTaskInstance(loginId, processInstanceId, SynType.synchronous, PriorityType.hight, 1, loginId, "�о���������", false, 0);
//					System.out.println("processInstanceId="+processInstanceId+" ,processTaskInstanceIds="+processTaskInstanceIds[0]);
					}
					
					//���������룬�趨����ֱ�Ӱ�chargeId��Ӧ��bindId
					//����STATUS״̬����Ϊ9����ʾΪ��������ʱ���õ�״̬
					String queryBindIdForChargeIdSql = "SELECT BINDID FROM BO_ECNU_PEOPLE WHERE LOGINID ='" +loginId+ "'";
					int chargeIdBindId = 0;
					chargeIdBindId = DBSql.getInt(conn, queryBindIdForChargeIdSql, "BINDID");
					
					String updateLoginIdSql = "";
					if(chargeIdBindId != 0) {
						updateLoginIdSql = "update BO_ECNU_YJBG set CHARGEID ='" +loginId +"' "
						+ ", ID1='" +loginId1 + "' "
						+ ", ID2='" +loginId2 + "' "
						+ ", ID3='" +loginId3 + "' "
						+ ", ID4='" +loginId4 + "' "
						+ ", ID5='" +loginId5 + "' "
						+ ", BINDID=" +chargeIdBindId
						+ ", STATUS=9 "
						+ ", AUTOADD=0 "
						+ ", FBND ='" + fbnd +"' "
						+ " where ID="+dataId;
					} else {
						updateLoginIdSql = "update BO_ECNU_YJBG set CHARGEID ='" +loginId +"' "
						+ ", ID1='" +loginId1 + "' "
						+ ", ID2='" +loginId2 + "' "
						+ ", ID3='" +loginId3 + "' "
						+ ", ID4='" +loginId4 + "' "
						+ ", ID5='" +loginId5 + "' "
						+ ", STATUS=9 "
						+ ", AUTOADD=0 "
						+ ", FBND ='" + fbnd +"' "
						+ " where ID="+dataId;
					}
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
						queryBindIdSql = "SELECT BINDID FROM BO_ECNU_PEOPLE WHERE LOGINID ='" +loginId1+ "'";
						loginId1BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //����������Ϊ����Ա��bindId
						
						int createBoId = BOInstanceAPI.getInstance().createBOData("BO_ECNU_YJBG", recordData, loginId1BindId, "admin");
						if(createBoId == -1) {
							createBoFailureNum = createBoFailureNum + 1;
						}
						copyDataIds = copyDataIds + createBoId + ":";
//					System.out.println("Ϊ "+loginId1+" �������ݣ�createBoId="+createBoId+", BINDID="+loginId1BindId);
						String updateCreateBoSql = "update BO_ECNU_YJBG set "
							+ "  CGMC ='" + cgmc +"' "
							+ ", CGYWMC ='" + cgywmc +"' "
							+ ", CGLX ='" + cglx +"' "
							+ ", GKLX ='" + gklx +"' "
							+ ", PJDW ='" + pjdw +"' "
							+ ", CNDW ='" + cndw +"' "
							+ ", RDRQ = TO_DATE('" +  rdrq +"','YYYY-MM-DD') "
							+ ", ZSBH ='" + zsbh +"' "
							+ ", SUBJECT ='" + subject +"' "
							+ ", SUBJECT2 ='" + subject2 +"' "
							+ ", WCRSF ='" + wcrsf +"' "
							+ ", SZXSJG ='" + szxsjg +"' "
							+ ", YXBM ='" + yxbm +"' "
							+ ", ZZLX ='" + zzlx +"' "
							+ ", FBFW ='" + fbfw +"' "
							+ ", FBSJ = TO_DATE('" +  fbsj +"','YYYY-MM-DD') "
							+ ", FBND ='" + fbnd +"' "
							+ ", YJLX ='" + yjlx +"' "
							+ ", XMJB ='" + xmjb +"' "
							+ ", CGYYQK ='" + cgyyqk +"' "
							+ ", SFCN ='" + sfcn +"' "
							
							+ ", CHARGENM ='" + chargenm +"' "
							+ ", CHARGEID ='" + loginId +"' "
							+ ", JGH ='" + jgh +"' "
							+ ", CHARGERATE =" + chargerate
							+ ", NAME1 ='" + name1 +"' "
							+ ", ID1 ='" + loginId1 +"' "
							+ ", JGH1 ='" + jgh1 +"' "
							+ ", RATE1 =" + rate1
							+ ", NAME2 ='" + name2 +"' "
							+ ", ID2 ='" + loginId2 +"' "
							+ ", JGH2 ='" + jgh2 +"' "
							+ ", RATE2 =" + rate2
							+ ", NAME3 ='" + name3 +"' "
							+ ", ID3 ='" + loginId3 +"' "
							+ ", JGH3 ='" + jgh3 +"' "
							+ ", RATE3 =" + rate3
							+ ", NAME4 ='" + name4 +"' "
							+ ", ID4 ='" + loginId4 +"' "
							+ ", JGH4 ='" + jgh4 +"' "
							+ ", RATE4 =" + rate4
							+ ", NAME5 ='" + name5 +"' "
							+ ", ID5 ='" + loginId5 +"' "
							+ ", JGH5 ='" + jgh5 +"' "
							+ ", RATE5 =" + rate5
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
						loginId2BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //����������Ϊ����Ա��bindId
						
						int createBoId = BOInstanceAPI.getInstance().createBOData("BO_ECNU_YJBG", recordData, loginId2BindId, "admin");
						if(createBoId == -1) {
							createBoFailureNum = createBoFailureNum + 1;
						}
						copyDataIds = copyDataIds + createBoId + ":";
//					System.out.println("Ϊ "+loginId2+" �������ݣ�createBoId="+createBoId+", BINDID="+loginId2BindId);
						String updateCreateBoSql = "update BO_ECNU_YJBG set "
							+ "  CGMC ='" + cgmc +"' "
							+ ", CGYWMC ='" + cgywmc +"' "
							+ ", CGLX ='" + cglx +"' "
							+ ", GKLX ='" + gklx +"' "
							+ ", PJDW ='" + pjdw +"' "
							+ ", CNDW ='" + cndw +"' "
							+ ", RDRQ = TO_DATE('" +  rdrq +"','YYYY-MM-DD') "
							+ ", ZSBH ='" + zsbh +"' "
							+ ", SUBJECT ='" + subject +"' "
							+ ", SUBJECT2 ='" + subject2 +"' "
							+ ", WCRSF ='" + wcrsf +"' "
							+ ", SZXSJG ='" + szxsjg +"' "
							+ ", YXBM ='" + yxbm +"' "
							+ ", ZZLX ='" + zzlx +"' "
							+ ", FBFW ='" + fbfw +"' "
							+ ", FBSJ = TO_DATE('" +  fbsj +"','YYYY-MM-DD') "
							+ ", FBND ='" + fbnd +"' "
							+ ", YJLX ='" + yjlx +"' "
							+ ", XMJB ='" + xmjb +"' "
							+ ", CGYYQK ='" + cgyyqk +"' "
							+ ", SFCN ='" + sfcn +"' "
							
							+ ", CHARGENM ='" + chargenm +"' "
							+ ", CHARGEID ='" + loginId +"' "
							+ ", JGH ='" + jgh +"' "
							+ ", CHARGERATE =" + chargerate
							+ ", NAME1 ='" + name1 +"' "
							+ ", ID1 ='" + loginId1 +"' "
							+ ", JGH1 ='" + jgh1 +"' "
							+ ", RATE1 =" + rate1
							+ ", NAME2 ='" + name2 +"' "
							+ ", ID2 ='" + loginId2 +"' "
							+ ", JGH2 ='" + jgh2 +"' "
							+ ", RATE2 =" + rate2
							+ ", NAME3 ='" + name3 +"' "
							+ ", ID3 ='" + loginId3 +"' "
							+ ", JGH3 ='" + jgh3 +"' "
							+ ", RATE3 =" + rate3
							+ ", NAME4 ='" + name4 +"' "
							+ ", ID4 ='" + loginId4 +"' "
							+ ", JGH4 ='" + jgh4 +"' "
							+ ", RATE4 =" + rate4
							+ ", NAME5 ='" + name5 +"' "
							+ ", ID5 ='" + loginId5 +"' "
							+ ", JGH5 ='" + jgh5 +"' "
							+ ", RATE5 =" + rate5
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
						loginId3BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //����������Ϊ����Ա��bindId
						
						int createBoId = BOInstanceAPI.getInstance().createBOData("BO_ECNU_YJBG", recordData, loginId3BindId, "admin");
						if(createBoId == -1) {
							createBoFailureNum = createBoFailureNum + 1;
						}
						copyDataIds = copyDataIds + createBoId + ":";
//					System.out.println("Ϊ "+loginId3+" �������ݣ�createBoId="+createBoId+", BINDID="+loginId3BindId);
						String updateCreateBoSql = "update BO_ECNU_YJBG set "
							+ "  CGMC ='" + cgmc +"' "
							+ ", CGYWMC ='" + cgywmc +"' "
							+ ", CGLX ='" + cglx +"' "
							+ ", GKLX ='" + gklx +"' "
							+ ", PJDW ='" + pjdw +"' "
							+ ", CNDW ='" + cndw +"' "
							+ ", RDRQ = TO_DATE('" +  rdrq +"','YYYY-MM-DD') "
							+ ", ZSBH ='" + zsbh +"' "
							+ ", SUBJECT ='" + subject +"' "
							+ ", SUBJECT2 ='" + subject2 +"' "
							+ ", WCRSF ='" + wcrsf +"' "
							+ ", SZXSJG ='" + szxsjg +"' "
							+ ", YXBM ='" + yxbm +"' "
							+ ", ZZLX ='" + zzlx +"' "
							+ ", FBFW ='" + fbfw +"' "
							+ ", FBSJ = TO_DATE('" +  fbsj +"','YYYY-MM-DD') "
							+ ", FBND ='" + fbnd +"' "
							+ ", YJLX ='" + yjlx +"' "
							+ ", XMJB ='" + xmjb +"' "
							+ ", CGYYQK ='" + cgyyqk +"' "
							+ ", SFCN ='" + sfcn +"' "
							
							+ ", CHARGENM ='" + chargenm +"' "
							+ ", CHARGEID ='" + loginId +"' "
							+ ", JGH ='" + jgh +"' "
							+ ", CHARGERATE =" + chargerate
							+ ", NAME1 ='" + name1 +"' "
							+ ", ID1 ='" + loginId1 +"' "
							+ ", JGH1 ='" + jgh1 +"' "
							+ ", RATE1 =" + rate1
							+ ", NAME2 ='" + name2 +"' "
							+ ", ID2 ='" + loginId2 +"' "
							+ ", JGH2 ='" + jgh2 +"' "
							+ ", RATE2 =" + rate2
							+ ", NAME3 ='" + name3 +"' "
							+ ", ID3 ='" + loginId3 +"' "
							+ ", JGH3 ='" + jgh3 +"' "
							+ ", RATE3 =" + rate3
							+ ", NAME4 ='" + name4 +"' "
							+ ", ID4 ='" + loginId4 +"' "
							+ ", JGH4 ='" + jgh4 +"' "
							+ ", RATE4 =" + rate4
							+ ", NAME5 ='" + name5 +"' "
							+ ", ID5 ='" + loginId5 +"' "
							+ ", JGH5 ='" + jgh5 +"' "
							+ ", RATE5 =" + rate5
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
						loginId4BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //����������Ϊ����Ա��bindId
						
						int createBoId = BOInstanceAPI.getInstance().createBOData("BO_ECNU_YJBG", recordData, loginId4BindId, "admin");
						if(createBoId == -1) {
							createBoFailureNum = createBoFailureNum + 1;
						}
						copyDataIds = copyDataIds + createBoId + ":";
//					System.out.println("Ϊ "+loginId4+" �������ݣ�createBoId="+createBoId+", BINDID="+loginId4BindId);
						String updateCreateBoSql = "update BO_ECNU_YJBG set "
							+ "  CGMC ='" + cgmc +"' "
							+ ", CGYWMC ='" + cgywmc +"' "
							+ ", CGLX ='" + cglx +"' "
							+ ", GKLX ='" + gklx +"' "
							+ ", PJDW ='" + pjdw +"' "
							+ ", CNDW ='" + cndw +"' "
							+ ", RDRQ = TO_DATE('" +  rdrq +"','YYYY-MM-DD') "
							+ ", ZSBH ='" + zsbh +"' "
							+ ", SUBJECT ='" + subject +"' "
							+ ", SUBJECT2 ='" + subject2 +"' "
							+ ", WCRSF ='" + wcrsf +"' "
							+ ", SZXSJG ='" + szxsjg +"' "
							+ ", YXBM ='" + yxbm +"' "
							+ ", ZZLX ='" + zzlx +"' "
							+ ", FBFW ='" + fbfw +"' "
							+ ", FBSJ = TO_DATE('" +  fbsj +"','YYYY-MM-DD') "
							+ ", FBND ='" + fbnd +"' "
							+ ", YJLX ='" + yjlx +"' "
							+ ", XMJB ='" + xmjb +"' "
							+ ", CGYYQK ='" + cgyyqk +"' "
							+ ", SFCN ='" + sfcn +"' "
							
							+ ", CHARGENM ='" + chargenm +"' "
							+ ", CHARGEID ='" + loginId +"' "
							+ ", JGH ='" + jgh +"' "
							+ ", CHARGERATE =" + chargerate
							+ ", NAME1 ='" + name1 +"' "
							+ ", ID1 ='" + loginId1 +"' "
							+ ", JGH1 ='" + jgh1 +"' "
							+ ", RATE1 =" + rate1
							+ ", NAME2 ='" + name2 +"' "
							+ ", ID2 ='" + loginId2 +"' "
							+ ", JGH2 ='" + jgh2 +"' "
							+ ", RATE2 =" + rate2
							+ ", NAME3 ='" + name3 +"' "
							+ ", ID3 ='" + loginId3 +"' "
							+ ", JGH3 ='" + jgh3 +"' "
							+ ", RATE3 =" + rate3
							+ ", NAME4 ='" + name4 +"' "
							+ ", ID4 ='" + loginId4 +"' "
							+ ", JGH4 ='" + jgh4 +"' "
							+ ", RATE4 =" + rate4
							+ ", NAME5 ='" + name5 +"' "
							+ ", ID5 ='" + loginId5 +"' "
							+ ", JGH5 ='" + jgh5 +"' "
							+ ", RATE5 =" + rate5
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
						loginId5BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //����������Ϊ����Ա��bindId
						
						int createBoId = BOInstanceAPI.getInstance().createBOData("BO_ECNU_YJBG", recordData, loginId5BindId, "admin");
						if(createBoId == -1) {
							createBoFailureNum = createBoFailureNum + 1;
						}
						copyDataIds = copyDataIds + createBoId + ":";
//					System.out.println("Ϊ "+loginId5+" �������ݣ�createBoId="+createBoId+", BINDID="+loginId5BindId);
						String updateCreateBoSql = "update BO_ECNU_YJBG set "
							+ "  CGMC ='" + cgmc +"' "
							+ ", CGYWMC ='" + cgywmc +"' "
							+ ", CGLX ='" + cglx +"' "
							+ ", GKLX ='" + gklx +"' "
							+ ", PJDW ='" + pjdw +"' "
							+ ", CNDW ='" + cndw +"' "
							+ ", RDRQ = TO_DATE('" +  rdrq +"','YYYY-MM-DD') "
							+ ", ZSBH ='" + zsbh +"' "
							+ ", SUBJECT ='" + subject +"' "
							+ ", SUBJECT2 ='" + subject2 +"' "
							+ ", WCRSF ='" + wcrsf +"' "
							+ ", SZXSJG ='" + szxsjg +"' "
							+ ", YXBM ='" + yxbm +"' "
							+ ", ZZLX ='" + zzlx +"' "
							+ ", FBFW ='" + fbfw +"' "
							+ ", FBSJ = TO_DATE('" +  fbsj +"','YYYY-MM-DD') "
							+ ", FBND ='" + fbnd +"' "
							+ ", YJLX ='" + yjlx +"' "
							+ ", XMJB ='" + xmjb +"' "
							+ ", CGYYQK ='" + cgyyqk +"' "
							+ ", SFCN ='" + sfcn +"' "
							
							+ ", CHARGENM ='" + chargenm +"' "
							+ ", CHARGEID ='" + loginId +"' "
							+ ", JGH ='" + jgh +"' "
							+ ", CHARGERATE =" + chargerate
							+ ", NAME1 ='" + name1 +"' "
							+ ", ID1 ='" + loginId1 +"' "
							+ ", JGH1 ='" + jgh1 +"' "
							+ ", RATE1 =" + rate1
							+ ", NAME2 ='" + name2 +"' "
							+ ", ID2 ='" + loginId2 +"' "
							+ ", JGH2 ='" + jgh2 +"' "
							+ ", RATE2 =" + rate2
							+ ", NAME3 ='" + name3 +"' "
							+ ", ID3 ='" + loginId3 +"' "
							+ ", JGH3 ='" + jgh3 +"' "
							+ ", RATE3 =" + rate3
							+ ", NAME4 ='" + name4 +"' "
							+ ", ID4 ='" + loginId4 +"' "
							+ ", JGH4 ='" + jgh4 +"' "
							+ ", RATE4 =" + rate4
							+ ", NAME5 ='" + name5 +"' "
							+ ", ID5 ='" + loginId5 +"' "
							+ ", JGH5 ='" + jgh5 +"' "
							+ ", RATE5 =" + rate5
							+ ", AUTOADD =1 "
							+ ", STATUS =0 "
							+ ", FINALMARK =0 "
							+ ", COPYDATAID ='0:' "
							+ " where ID="+createBoId;
//					System.out.println("updateCreateBoSql="+updateCreateBoSql);
						DBSql.executeUpdate(updateCreateBoSql);
					}
					
					String updateCopyDataIdSql = "update BO_ECNU_YJBG set copyDataId ='" +copyDataIds +"' where ID="+dataId;
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
