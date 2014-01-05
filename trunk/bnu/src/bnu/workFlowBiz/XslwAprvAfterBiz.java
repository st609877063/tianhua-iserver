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

//�������_ѧ������ �����ڵ�

public class XslwAprvAfterBiz extends WorkFlowStepRTClassA {
	
	public XslwAprvAfterBiz(UserContext uc){
		super(uc);
		super.setDescription("�������_ѧ������_���ͨ��");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}
	
	@Override
	public boolean execute() {
		System.out.println(".....................�������_ѧ������_���ͨ��.....................");
		
		int workflowInstanceId = getParameter(this.PARAMETER_INSTANCE_ID).toInt();//��õ�ǰ��������id
		Hashtable rsTable = BOInstanceAPI.getInstance().getBOData("BO_XSLWTEMP", workflowInstanceId);
		int id = 0;
		String idStr = rsTable.get("ID").toString() == null ? "" : rsTable.get("ID").toString();
		id = Integer.parseInt(idStr);
//		System.out.println("########�������_ѧ������_���#######workflowInstanceId=bindid="+workflowInstanceId+", #######id="+id);
		
		
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
			
			//update BO_XSLWTEMP 
			String updateBindIdSql = null;
			updateBindIdSql = " update BO_XSLWTEMP set STATUS = 1, BINDID = "+bindId+" where ID=" + id;
			
			int r2 = DBSql.executeUpdate(conn, updateBindIdSql);
			if(r2<=0){
				try {
					conn.rollback();
				} catch (Exception e1) {
				}
				MessageQueue.getInstance().putMessage(getUserContext().getUID(), "���棬�������_ѧ������ ����ʧ��!", true);
				return false;
			}
			/****************************************************************************/
			
			/*String sql = "select b.field_name as optionNm from SYS_BUSINESS_METADATA  a, SYS_BUSINESS_METADATA_MAP b " +
					" where b.metadata_id = a.id " +
					" and a.group_name = '������Ϣͳ��' " +
					" and b.display_type = '�б�'  " +
					" and a.entity_name='BO_XSLWTEMP'";
			
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				String optionNmTemp = rs.getString("optionNm")==null ? "" : rs.getString("optionNm"); //Ϊ���б�������
				String optionValue = rsTable.get(optionNmTemp).toString() == null ? "" : rsTable.get(optionNmTemp).toString(); //����û������ֵ
				String weightSql = "select weight from BO_SELECTINFO " +
						" where TABLECD='BO_XSLWTEMP' AND COLUMNCD='" +optionNmTemp + "' " +
								" AND OPTIONNM='" +optionValue+ "'";
				
				int weight = DBSql.getInt(weightSql, "weight");
				finalMark = finalMark + weight;
			}
			String updateSql = null;
			updateSql = " update BO_XSLWTEMP set STATUS = 1, BINDID = "+bindId+", finalMark = "+finalMark+" where ID=" + id;
			int r = DBSql.executeUpdate(conn, updateSql);
			if(r<=0){
				try {
					conn.rollback();
				} catch (Exception e1) {
				}
				MessageQueue.getInstance().putMessage(getUserContext().getUID(), "���棬�������_ѧ������ ����ʧ��!", true);
				return false;
			}*/

			String name = rsTable.get("CHARGENM").toString() == null ? "" : rsTable.get("CHARGENM").toString();
			String LoginId = rsTable.get("CHARGEID").toString() == null ? "" : rsTable.get("CHARGEID").toString();
			String jgh = rsTable.get("JGH").toString() == null ? "" : rsTable.get("JGH").toString();
			String markStr = rsTable.get("FINALMARK").toString() == null ? "" : rsTable.get("FINALMARK").toString();
			int getMark = 0;
			if(markStr!=null && !markStr.equals("")) {
				getMark = Integer.parseInt(markStr);
			}
			CalMark calMark = new CalMark();
//			calMark.setFinalMark(name, LoginId, jgh, "BO_XSLWTEMP", getMark);
			String datayear = rsTable.get("DATAYEAR").toString()==null?"":rsTable.get("DATAYEAR").toString().trim();
			String sftxzz = rs.getString("SFTXZZ")==null?"":rs.getString("SFTXZZ").trim();
			if(sftxzz.equals("��")) {
				String name1 = rsTable.get("NAME1").toString() == null ? "" : rsTable.get("NAME1").toString();
				String loginId1 = rsTable.get("ID1").toString() == null ? "" : rsTable.get("ID1").toString();
				String jgh1 = rsTable.get("JGH1").toString() == null ? "" : rsTable.get("JGH1").toString();
				
				calMark.setFinalMarkWithYear(name1, loginId1, jgh1, "BO_XSLWTEMP", finalMark, datayear);
			} else {
				calMark.setFinalMarkWithYear(name, LoginId, jgh, "BO_XSLWTEMP", getMark, datayear);
			}
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "�������_ѧ������ ����ʧ�ܣ��ο�����" + e.toString(), true);
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