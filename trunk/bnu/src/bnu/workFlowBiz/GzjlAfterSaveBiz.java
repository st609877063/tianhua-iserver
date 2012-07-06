package bnu.workFlowBiz;

import java.util.Hashtable;

import bnu.importBiz.CalMark;

import com.actionsoft.awf.organization.control.MessageQueue;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.loader.core.WorkFlowStepRTClassA;
import com.actionsoft.sdk.local.level0.BOInstanceAPI;

//工作经历 

public class GzjlAfterSaveBiz extends WorkFlowStepRTClassA {
	
	public GzjlAfterSaveBiz(UserContext uc){
		super(uc);
		super.setDescription("工作经历_保存成功");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}
	
	@Override
	public boolean execute() {
		System.out.println(".....................工作经历_保存:计算分值.....................");
		
		int workflowInstanceId = getParameter(this.PARAMETER_INSTANCE_ID).toInt();//获得当前工作流的id
		Hashtable rsTable = BOInstanceAPI.getInstance().getBOData("BO_GZJLTEMP", workflowInstanceId);
		int dataId = 0;
		dataId = Integer.parseInt(rsTable.get("ID").toString() == null ? "" : rsTable.get("ID").toString().trim());
		
		//验证begin
		String datayear = rsTable.get("DATAYEAR").toString()==null?"":rsTable.get("DATAYEAR").toString().trim();
		if(datayear.equals("") || datayear.length() != 4) {
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，【计算年度】必须填写，并且格式为四位数。请确认!", true);
			return false;
		}
		//验证end
		
//		CalMark calMark = new CalMark();
//		int finalMark = calMark.getCalMark("BO_GZJLTEMP", dataId);
//		
//		
//		String updateLoginIdSql = "";
//		updateLoginIdSql = "update BO_GZJLTEMP set finalMark=" +finalMark+ " where ID="+dataId;
//		DBSql.executeUpdate(updateLoginIdSql);
		
		return true;
	}
	
}
