package bnu.workFlowBiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import bnu.importBiz.CalMark;

import com.actionsoft.awf.organization.control.MessageQueue;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.loader.core.WorkFlowStepRTClassA;
import com.actionsoft.sdk.local.level0.BOInstanceAPI;

//�������_�ɹ��� �����ڵ�

public class CghjAprvAfterBiz extends WorkFlowStepRTClassA {
	
	public CghjAprvAfterBiz(UserContext uc){
		super(uc);
		super.setDescription("�������_�ɹ���_���ͨ��");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}
	
	@Override
	public boolean execute() {
		System.out.println("##########################�������_�ɹ���_���ͨ��#########################");
		
		int workflowInstanceId = getParameter(this.PARAMETER_INSTANCE_ID).toInt();//��õ�ǰ��������id
		Hashtable rsTable = BOInstanceAPI.getInstance().getBOData("BO_CGHJTEMP", workflowInstanceId);
		int id = 0;
		String idStr = rsTable.get("ID").toString() == null ? "" : rsTable.get("ID").toString();
		id = Integer.parseInt(idStr);
//		System.out.println("########�������_�ɹ���_���#######workflowInstanceId=bindid="+workflowInstanceId+", #######id="+id);
		
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		int finalMark = 0;
		
		try {
			conn=DBSql.open();
			conn.setAutoCommit(false);
			
			
			/***********************��������BindIdΪBO_RSTEMP�е�bindId****************************/
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
			
			//update BO_CGHJTEMP 
			String updateBindIdSql = null;
			updateBindIdSql = " update BO_CGHJTEMP set STATUS = 1, BINDID = "+bindId+" where ID=" + id;
			
			int r2 = DBSql.executeUpdate(conn, updateBindIdSql);
			if(r2<=0){
				try {
					conn.rollback();
				} catch (Exception e1) {
				}
				MessageQueue.getInstance().putMessage(getUserContext().getUID(), "���棬�������_�ɹ��� ����ʧ��!", true);
				return false;
			}
			/****************************************************************************/
			
			
			/*String sql = "select b.field_name as optionNm from SYS_BUSINESS_METADATA  a, SYS_BUSINESS_METADATA_MAP b " +
					" where b.metadata_id = a.id " +
					" and a.group_name = '������Ϣͳ��' " +
					" and b.display_type = '�б�'  " +
					" and a.entity_name='BO_CGHJTEMP'";
			
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				String optionNmTemp = rs.getString("optionNm")==null ? "" : rs.getString("optionNm"); //Ϊ���б�������
				String optionValue = rsTable.get(optionNmTemp).toString() == null ? "" : rsTable.get(optionNmTemp).toString(); //����û������ֵ
				String weightSql = "select weight from BO_SELECTINFO " +
						" where TABLECD='BO_CGHJTEMP' AND COLUMNCD='" +optionNmTemp + "' " +
								" AND OPTIONNM='" +optionValue+ "'";
				
				int weight = DBSql.getInt(weightSql, "weight");
				finalMark = finalMark + weight;
			}
			//update BO_CGHJTEMP 
			String updateStatusSql = null;
			updateStatusSql = " update BO_CGHJTEMP set STATUS = 1, finalMark = "+finalMark+" where ID=" + id;
			int r = DBSql.executeUpdate(conn, updateStatusSql);
			if(r<=0){
				try {
					conn.rollback();
				} catch (Exception e1) {
				}
				MessageQueue.getInstance().putMessage(getUserContext().getUID(), "���棬�������_�ɹ��� ����ʧ��!", true);
				return false;
			}*/

			String name = rsTable.get("MAN").toString() == null ? "" : rsTable.get("MAN").toString();
			String LoginId = rsTable.get("CHARGEID").toString() == null ? "" : rsTable.get("CHARGEID").toString();
			String jgh = rsTable.get("JGH").toString() == null ? "" : rsTable.get("JGH").toString();
			String markStr = rsTable.get("FINALMARK").toString() == null ? "" : rsTable.get("FINALMARK").toString();
			int getMark = 0;
			if(markStr!=null && !markStr.equals("")) {
				getMark = Integer.parseInt(markStr);
			}
			CalMark calMark = new CalMark();
//			calMark.setFinalMark(name, LoginId, jgh, "BO_CGHJTEMP", getMark);
			String datayear = rsTable.get("DATAYEAR").toString()==null?"":rsTable.get("DATAYEAR").toString().trim();
			calMark.setFinalMarkWithYear(name, LoginId, jgh, "BO_CGHJTEMP", getMark, datayear);
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "�������_�ɹ��� Ȩֵ����ʧ�ܣ��ο�����" + e.toString(), true);
			return false;
		} finally {
			try {
				conn.setAutoCommit(true);
				DBSql.close(conn, ps, rs);
				DBSql.close(conn, ps2, rs2);
			} catch (Exception e) {

			}
		}
		
		return true;
	}
	
}