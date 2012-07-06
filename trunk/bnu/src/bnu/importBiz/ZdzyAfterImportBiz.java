package bnu.importBiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.actionsoft.awf.organization.control.MessageQueue;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.loader.core.WorkFlowStepRTClassA;

public class ZdzyAfterImportBiz extends WorkFlowStepRTClassA {
	
	public ZdzyAfterImportBiz(UserContext uc){
		super(uc);
		super.setDescription("ָ��רҵ�������ְ������");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}

	public boolean execute() {
		System.out.println(".....................ָ��רҵ�������ְ������begin.�������ݺ�Ϊÿ����������ݰ�����................");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conn = DBSql.open();
			conn.setAutoCommit(true);
			
			String sql = "select * from BO_ZDXSTEMP";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			int dataId = 0;
			int totalData = 0;
			int wrongData = 0;
			String wrongNm = "";
			
			String dsmc = ""; //��ʦ����
			String dsbh = "";
			String dsloginid = "";
			String dslb = "";
			String xymc= "";
			String drbdrq= "";
			String drsdrq= "";
			String prqx= "";
			String zymc= "";
			String zyh= "";
			
			while(rs.next()){
				totalData = totalData + 1;
				dataId = rs.getInt("ID");
				
				dsmc = rs.getString("DSMC")==null?"":rs.getString("DSMC").trim();
				dsbh = rs.getString("DSBH")==null?"":rs.getString("DSBH").trim();
				dsloginid = rs.getString("DSLOGINID")==null?"":rs.getString("DSLOGINID").trim();
				dslb = rs.getString("DSLB")==null?"":rs.getString("DSLB").trim();
				xymc = rs.getString("XYMC")==null?"":rs.getString("XYMC").trim();
				drbdrq = rs.getString("DRBDRQ")==null?"":rs.getString("DRBDRQ").trim();
				drsdrq = rs.getString("DRSDRQ")==null?"":rs.getString("DRSDRQ").trim();
				prqx = rs.getString("PRQX")==null?"":rs.getString("PRQX").trim();
				zymc = rs.getString("ZYMC")==null?"":rs.getString("ZYMC").trim();
				zyh = rs.getString("ZYH")==null?"":rs.getString("ZYH").trim();

				if(dsloginid.equals("")) {
					wrongData = wrongData +1;
					wrongNm = wrongNm + dsmc + "; ";
				}
				
				int processInstanceId = 0;
				if(!dsloginid.equals("")) {
					//����AWS����-----------------Ϊ���ݰ�����
//					processInstanceId = WorkflowInstanceAPI.getInstance().createProcessInstance("007b23151fd4ccbe70caeb032a57569c", loginId, "ָ��רҵ�������ְ������");
//					int[] processTaskInstanceIds = WorkflowTaskInstanceAPI.getInstance().createProcessTaskInstance(loginId, processInstanceId, SynType.synchronous, PriorityType.hight, 1, loginId, "ָ��רҵ�������ְ������", false, 0);
//					System.out.println("processInstanceId="+processInstanceId+" ,processTaskInstanceIds="+processTaskInstanceIds[0]);
				}
				
//				CalMark calMark = new CalMark();
				//finalMark��ֵ��Ϊ��ֵ*ѧʱ�Ľ����
//				int finalMark = calMark.getCalMark("BO_ZDXSTEMP", dataId);
//				calMark.setFinalMark(chargenm, chargeid, chargeid, "BO_ZDXSTEMP", finalMark);
				String datayear = rs.getString("DATAYEAR")==null?"":rs.getString("DATAYEAR").trim();
//				calMark.setFinalMarkWithYear(dsmc, dsbh, dsbh, "BO_ZDXSTEMP", finalMark, datayear);
				
				//���������룬�趨����ֱ�Ӱ�chargeId��Ӧ��bindId
				//����STATUS״̬����Ϊ9����ʾΪ��������ʱ���õ�״̬
				String queryBindIdForChargeIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +dsbh+ "'";
				int chargeIdBindId = 0;
				chargeIdBindId = DBSql.getInt(conn, queryBindIdForChargeIdSql, "BINDID");
				int finalMark = 0;
				
				String updateLoginIdSql = "";
				if(chargeIdBindId != 0) {
					updateLoginIdSql = "update BO_ZDXSTEMP set BINDID=" +chargeIdBindId
					+ ", STATUS=9 "
					+ ", finalMark="+finalMark
					+ " where ID="+dataId;
				} else {
					updateLoginIdSql = "update BO_ZDXSTEMP set STATUS=9 "
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
