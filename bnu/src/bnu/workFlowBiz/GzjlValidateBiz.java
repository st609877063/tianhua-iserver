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

//工作经历 审批节点

public class GzjlValidateBiz extends WorkFlowStepRTClassA {
	
	public GzjlValidateBiz(UserContext uc){
		super(uc);
		super.setDescription("工作经历_有效性验证");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}
	
	@Override
	public boolean execute() {
		System.out.println("########################工作经历_有效性验证#########################");
		
		int workflowInstanceId = getParameter(this.PARAMETER_INSTANCE_ID).toInt();//获得当前工作流的id
		
		Hashtable rsTable = BOInstanceAPI.getInstance().getBOData("BO_GZJLTEMP", workflowInstanceId);
		
		String id = rsTable.get("ID").toString() == null ? "" : rsTable.get("ID").toString();

		String datayear = rsTable.get("DATAYEAR").toString()==null?"":rsTable.get("DATAYEAR").toString().trim();
		if(datayear.equals("") || datayear.length() != 4) {
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，【计算年度】必须填写，并且格式为四位数。请确认!", true);
			return false;
		}
		
		return true;
	}
	
}
