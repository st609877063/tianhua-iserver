package ecnu.importBiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.actionsoft.awf.organization.control.MessageQueue;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.loader.core.WorkFlowStepRTClassA;

public class InitDataAfterDelete extends WorkFlowStepRTClassA {
	
	public InitDataAfterDelete(UserContext uc){
		super(uc);
		super.setDescription("��BO_ECNU_PEOPLE2��ɾ�����ݣ�ɾ����BO_ECNU_PEOPLE�е�����");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}

	public boolean execute() {
		System.out.println(".....................��BO_ECNU_PEOPLE2��ɾ�����ݣ�ɾ����BO_ECNU_PEOPLE�е�����..............");
		
		int deleteBoId = getParameter(this.PARAMETER_USERDATA_ID).toInt(); //���Ҫɾ����BO���IDֵ
		System.out.println("deleteBoId="+deleteBoId);
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String xm = "";
		try{
			conn = DBSql.open();
			conn.setAutoCommit(true);

			String sql = "select * from BO_ECNU_PEOPLE2 where ID="+deleteBoId;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()){
				String zgh = rs.getString("ZGH")==null?"":rs.getString("ZGH").trim();
				String loginId = rs.getString("LOGINID")==null?"":rs.getString("LOGINID").trim();
				xm = rs.getString("XM")==null?"":rs.getString("XM").trim(); //����
				
				String deleteSql = "DELETE FROM BO_ECNU_PEOPLE WHERE ZGH='" +zgh+ "' AND LOGINID='" +loginId+ "'";
				System.out.println("deleteSql="+deleteSql);
				DBSql.executeUpdate(deleteSql);
			}
		} catch(Exception e){
			e.printStackTrace(System.err);
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "���棬"+xm+" ������Ϣɾ��ʧ��", true);
			return false;
		} finally{
			DBSql.close(conn, ps, rs);
		}
		
		return true;
	}
	
}
