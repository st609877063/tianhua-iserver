package bnu.workFlowBiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.actionsoft.awf.organization.control.MessageQueue;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.loader.core.WorkFlowStepRTClassA;
import com.actionsoft.sdk.AWSSDKException;
import com.actionsoft.sdk.local.level0.BOInstanceAPI;

//科研情况_指导专业 

public class ZdzyAfterSaveBiz extends WorkFlowStepRTClassA {
	
	public ZdzyAfterSaveBiz(UserContext uc){
		super(uc);
		super.setDescription("科研情况_指导专业_保存成功:更改教工号");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}
	
	@Override
	public boolean execute() {
		System.out.println(".....................科研情况_指导专业_保存:更改教工号.....................");
		
		int workflowInstanceId = getParameter(this.PARAMETER_INSTANCE_ID).toInt();//获得当前工作流的id
		
		Hashtable rsTable = BOInstanceAPI.getInstance().getBOData("BO_ZDZYTEMP", workflowInstanceId);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			conn.setAutoCommit(true);
			
			int mainDataId = 0;
			String loginId = "";

			//有loginId而无“教工号”
			mainDataId = Integer.parseInt(rsTable.get("ID").toString() == null ? "" : rsTable.get("ID").toString().trim());
			loginId = rsTable.get("DSLOGINID").toString() == null ? "" : rsTable.get("DSLOGINID").toString().trim();
			
			String jgh = "";
			
			String querySql = "";
			
			if(loginId!=null && !loginId.equals("") && (loginId.indexOf("<")>0)) {   //DB中存的为“loginId<中文>"格式
				loginId = loginId.substring(0, loginId.indexOf("<"));
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId+"'";
				jgh = DBSql.getString(conn, querySql, "ZGH").trim();
			} else if(loginId!=null && !loginId.equals("")) {   //DB中存的为“loginId”格式
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId+"'";
				jgh = DBSql.getString(conn, querySql, "ZGH".trim());
			}
			
			String updateInstanceSql = "update BO_ZDZYTEMP set DSBH ='" +jgh +"' "
			+ ", DSLOGINID='" +loginId + "' "
			+ " where ID="+mainDataId;
			DBSql.executeUpdate(updateInstanceSql);
			
		}catch(Exception e){
			e.printStackTrace(System.err);
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，信息保存失败！", true);
			return false;
		}finally{
			DBSql.close(conn, ps, rs);
		}
		
		return true;
	}
}
