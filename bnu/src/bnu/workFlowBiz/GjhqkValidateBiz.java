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

//���ʻ���� �����ڵ�

public class GjhqkValidateBiz extends WorkFlowStepRTClassA {
	
	public GjhqkValidateBiz(UserContext uc){
		super(uc);
		super.setDescription("���ʻ����_��Ч����֤");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}
	
	@Override
	public boolean execute() {
		System.out.println("########################���ʻ����_��Ч����֤#########################");
		
		int workflowInstanceId = getParameter(this.PARAMETER_INSTANCE_ID).toInt();//��õ�ǰ��������id
		
		Hashtable rsTable = BOInstanceAPI.getInstance().getBOData("BO_GJHQKTEMP", workflowInstanceId);
		
		String id = rsTable.get("ID").toString() == null ? "" : rsTable.get("ID").toString();
		
		return true;
	}
	
}
