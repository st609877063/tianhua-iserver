package bnu.workFlowBiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.actionsoft.awf.organization.control.MessageQueue;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.loader.core.WorkFlowStepRTClassA;
import com.actionsoft.sdk.AWSSDKException;
import com.actionsoft.sdk.local.level0.BOInstanceAPI;

//�������_ѧ������ �����ڵ�

public class XszzAprvBeforeBiz extends WorkFlowStepRTClassA {
	
	public XszzAprvBeforeBiz(UserContext uc){
		super(uc);
		super.setDescription("�������_ѧ������");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}
	
	@Override
	public boolean execute() {
		System.out.println("##########################�������_ѧ������#########################");
		
		int workflowInstanceId = getParameter(this.PARAMETER_INSTANCE_ID).toInt();//��õ�ǰ��������id
		Hashtable rsTable = BOInstanceAPI.getInstance().getBOData("BO_XSZZTEMP", workflowInstanceId);
		int id = 0;
		String idStr = rsTable.get("ID").toString() == null ? "" : rsTable.get("ID").toString();
		id = Integer.parseInt(idStr);
//		System.out.println("########�������_ѧ������_���#######workflowInstanceId=bindid="+workflowInstanceId+", #######id="+id);
		
		
		Connection conn = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		int finalMark = 0;
		
		try {
			conn=DBSql.open();
			conn.setAutoCommit(true);
			
			
			/***********************�����Ƿ��и��˻�����Ϣ****************************/
			String operUser = rsTable.get("CHARGEID").toString() == null ? "" : rsTable.get("CHARGEID").toString(); //����û�
			String sql2 = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +operUser+ "'";
			ps2 = conn.prepareStatement(sql2);
			rs2 = ps2.executeQuery();
			int bindId = 0;
			if(rs2.next()) {
				bindId = rs2.getInt("BINDID");
			} else {
				try {
					conn.rollback();
				} catch (Exception e1) {
				}
				MessageQueue.getInstance().putMessage(getUserContext().getUID(), "���棬��߸��˻�����Ϣδ��ɡ��ݲ��ܶ��������������!", true);
				return false;
			}
			
			/****************************************************************************/

			ps2.close();
			rs2.close();
		} catch (SQLException e) {
			e.printStackTrace();
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "�������_ѧ������ ������֤ʧ�ܣ��ο�����" + e.toString(), true);
			return false;
		} finally {
			try {
				conn.setAutoCommit(true);
				DBSql.close(conn, ps2, rs2);
			} catch (Exception e) {

			}
		}
		
		return true;
	}
	
}
