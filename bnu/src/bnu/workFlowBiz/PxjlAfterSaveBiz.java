package bnu.workFlowBiz;

import java.util.Hashtable;

import bnu.importBiz.CalMark;

import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.loader.core.WorkFlowStepRTClassA;
import com.actionsoft.sdk.local.level0.BOInstanceAPI;

//培训经历 

public class PxjlAfterSaveBiz extends WorkFlowStepRTClassA {
	
	public PxjlAfterSaveBiz(UserContext uc){
		super(uc);
		super.setDescription("培训经历_保存成功");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}
	
	@Override
	public boolean execute() {
		System.out.println(".....................培训经历_保存:计算分值.....................");
		
		int workflowInstanceId = getParameter(this.PARAMETER_INSTANCE_ID).toInt();//获得当前工作流的id
		Hashtable rsTable = BOInstanceAPI.getInstance().getBOData("BO_PXJLTEMP", workflowInstanceId);
		int dataId = 0;
		dataId = Integer.parseInt(rsTable.get("ID").toString() == null ? "" : rsTable.get("ID").toString().trim());
		
		CalMark calMark = new CalMark();
		int finalMark = calMark.getCalMark("BO_PXJLTEMP", dataId);
		
		String updateLoginIdSql = "";
		updateLoginIdSql = "update BO_PXJLTEMP set finalMark=" +finalMark+ " where ID="+dataId;
		DBSql.executeUpdate(updateLoginIdSql);
		
		return true;
	}
	
}
