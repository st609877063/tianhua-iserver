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

//科研情况_学术著作 审批节点

public class XszzAprvBeforeBiz extends WorkFlowStepRTClassA {
	
	public XszzAprvBeforeBiz(UserContext uc){
		super(uc);
		super.setDescription("科研情况_学术著作");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}
	
	@Override
	public boolean execute() {
		System.out.println("##########################科研情况_学术著作#########################");
		
		int workflowInstanceId = getParameter(this.PARAMETER_INSTANCE_ID).toInt();//获得当前工作流的id
		Hashtable rsTable = BOInstanceAPI.getInstance().getBOData("BO_XSZZTEMP", workflowInstanceId);
		int id = 0;
		String idStr = rsTable.get("ID").toString() == null ? "" : rsTable.get("ID").toString();
		id = Integer.parseInt(idStr);
//		System.out.println("########科研情况_学术著作_审核#######workflowInstanceId=bindid="+workflowInstanceId+", #######id="+id);
		
		
		Connection conn = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		int finalMark = 0;
		
		try {
			conn=DBSql.open();
			conn.setAutoCommit(true);
			
			
			/***********************查找是否有个人基本信息****************************/
			String operUser = rsTable.get("CHARGEID").toString() == null ? "" : rsTable.get("CHARGEID").toString(); //获得用户
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
				MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，填报者个人基本信息未完成。暂不能对其申请进行审批!", true);
				return false;
			}
			
			/****************************************************************************/

			ps2.close();
			rs2.close();
		} catch (SQLException e) {
			e.printStackTrace();
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "科研情况_学术著作 审批验证失败！参考错误" + e.toString(), true);
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
