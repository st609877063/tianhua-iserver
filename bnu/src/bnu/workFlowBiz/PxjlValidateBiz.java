package bnu.workFlowBiz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;

import com.actionsoft.awf.organization.control.MessageQueue;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.loader.core.WorkFlowStepRTClassA;
import com.actionsoft.sdk.AWSSDKException;
import com.actionsoft.sdk.local.level0.BOInstanceAPI;

//教育经历_培训经历 审批节点

public class PxjlValidateBiz extends WorkFlowStepRTClassA {
	
	public PxjlValidateBiz(UserContext uc){
		super(uc);
		super.setDescription("教育经历_培训经历_有效性验证");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}
	
	@Override
	public boolean execute() {
		System.out.println("########################教育经历_培训经历_有效性验证#########################");
		
		int workflowInstanceId = getParameter(this.PARAMETER_INSTANCE_ID).toInt();//获得当前工作流的id
		
		Hashtable rsTable = BOInstanceAPI.getInstance().getBOData("BO_PXJLTEMP", workflowInstanceId);
		
		String id = rsTable.get("ID").toString() == null ? "" : rsTable.get("ID").toString();
		
		return true;
	}
	
}
