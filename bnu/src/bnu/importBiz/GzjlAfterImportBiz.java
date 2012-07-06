package bnu.importBiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.actionsoft.awf.organization.control.MessageQueue;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.loader.core.WorkFlowStepRTClassA;

public class GzjlAfterImportBiz extends WorkFlowStepRTClassA {
	
	public GzjlAfterImportBiz(UserContext uc){
		super(uc);
		super.setDescription("������������");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}

	public boolean execute() {
		System.out.println(".....................������������begin.�������ݺ�Ϊÿ����������ݰ�����................");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conn = DBSql.open();
			conn.setAutoCommit(true);
			
			String sql = "select * from BO_GZJLTEMP";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			int dataId = 0;
			int totalData = 0;
			int wrongData = 0;
			String wrongNm = "";
			String chargenm = ""; //��ʦ����
			String chargeid = "";
			String gztype = "";
			int rq = 1;
			String qssj = "";
			String jssj = "";
			String szdw = "";
			String crjszw = "";
			String crdzzw = "";
			String gzzw = "";
			String zmr = "";
			String zmrlxfs = "";
			
			while(rs.next()){
				totalData = totalData + 1;
				dataId = rs.getInt("ID");
				
				chargenm = rs.getString("CHARGENM")==null?"":rs.getString("CHARGENM").trim();
				chargeid = rs.getString("CHARGEID")==null?"":rs.getString("CHARGEID").trim();
				gztype = rs.getString("GZTYPE")==null?"":rs.getString("GZTYPE").trim();
				rq = rs.getInt("RQ");
				qssj = rs.getString("QSSJ")==null?"":rs.getString("QSSJ").trim();
				jssj = rs.getString("JSSJ")==null?"":rs.getString("JSSJ").trim();
				szdw = rs.getString("SZDW")==null?"":rs.getString("SZDW").trim();
				crjszw = rs.getString("CRJSZW")==null?"":rs.getString("CRJSZW").trim();
				crdzzw = rs.getString("CRDZZW")==null?"":rs.getString("CRDZZW").trim();
				gzzw = rs.getString("GZZW")==null?"":rs.getString("GZZW").trim();
				zmr = rs.getString("ZMR")==null?"":rs.getString("ZMR").trim();
				zmrlxfs = rs.getString("ZMRLXFS")==null?"":rs.getString("ZMRLXFS").trim();
				
				if(chargeid.equals("")) {
					wrongData = wrongData +1;
					wrongNm = wrongNm + chargenm + "; ";
				}
				
				int processInstanceId = 0;
				if(!chargeid.equals("")) {
					//����AWS����-----------------Ϊ���ݰ�����
//					processInstanceId = WorkflowInstanceAPI.getInstance().createProcessInstance("007b23151fd4ccbe70caeb032a57569c", loginId, "������������");
//					int[] processTaskInstanceIds = WorkflowTaskInstanceAPI.getInstance().createProcessTaskInstance(loginId, processInstanceId, SynType.synchronous, PriorityType.hight, 1, loginId, "������������", false, 0);
//					System.out.println("processInstanceId="+processInstanceId+" ,processTaskInstanceIds="+processTaskInstanceIds[0]);
				}
				
				CalMark calMark = new CalMark();
				//finalMark��ֵ��Ϊ��ֵ*ѧʱ�Ľ����
				int finalMark = calMark.getCalMark("BO_GZJLTEMP", dataId);
//				calMark.setFinalMark(chargenm, chargeid, chargeid, "BO_GZJLTEMP", finalMark);
				String datayear = rs.getString("DATAYEAR")==null?"":rs.getString("DATAYEAR").trim();
				calMark.setFinalMarkWithYear(chargenm, chargeid, chargeid, "BO_GZJLTEMP", finalMark, datayear);
				
				//���������룬�趨����ֱ�Ӱ�chargeId��Ӧ��bindId
				//����STATUS״̬����Ϊ9����ʾΪ��������ʱ���õ�״̬
				String queryBindIdForChargeIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +chargeid+ "'";
				int chargeIdBindId = 0;
				chargeIdBindId = DBSql.getInt(conn, queryBindIdForChargeIdSql, "BINDID");
				
				String updateLoginIdSql = "";
				if(chargeIdBindId != 0) {
					updateLoginIdSql = "update BO_GZJLTEMP set BINDID=" +chargeIdBindId
					+ ", STATUS=9 "
					+ ", finalMark="+finalMark
					+ " where ID="+dataId;
				} else {
					updateLoginIdSql = "update BO_GZJLTEMP set STATUS=9 "
					+ ", finalMark="+finalMark
					+ " where ID="+dataId;
				}
				
				//System.out.println(updateLoginIdSql);
				DBSql.executeUpdate(updateLoginIdSql);
			}
			
			String tip = "���ݵ���ɹ��������� "+totalData+" �����ݡ�";
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), tip, true);
			if(wrongData != 0) {
				MessageQueue.getInstance().putMessage(getUserContext().getUID(), "������������"+wrongData+"�����ݵĸ������ڻ�����Ϣ���в����ڡ�Ϊ��"+wrongNm, true);
			}
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
