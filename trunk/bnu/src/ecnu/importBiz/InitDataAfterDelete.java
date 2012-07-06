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
		super.setDescription("从BO_ECNU_PEOPLE2中删除数据，删除在BO_ECNU_PEOPLE中的数据");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}

	public boolean execute() {
		System.out.println(".....................从BO_ECNU_PEOPLE2中删除数据，删除在BO_ECNU_PEOPLE中的数据..............");
		
		int deleteBoId = getParameter(this.PARAMETER_USERDATA_ID).toInt(); //获得要删除的BO表的ID值
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
				xm = rs.getString("XM")==null?"":rs.getString("XM").trim(); //姓名
				
				String deleteSql = "DELETE FROM BO_ECNU_PEOPLE WHERE ZGH='" +zgh+ "' AND LOGINID='" +loginId+ "'";
				System.out.println("deleteSql="+deleteSql);
				DBSql.executeUpdate(deleteSql);
			}
		} catch(Exception e){
			e.printStackTrace(System.err);
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，"+xm+" 个人信息删除失败", true);
			return false;
		} finally{
			DBSql.close(conn, ps, rs);
		}
		
		return true;
	}
	
}
