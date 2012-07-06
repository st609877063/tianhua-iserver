package bnu.workFlowBiz;

import java.util.Hashtable;

import bnu.importBiz.CalMark;

import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.loader.core.WorkFlowStepRTClassA;
import com.actionsoft.sdk.local.level0.BOInstanceAPI;

//��ͥ��Ա 

public class JtcyAfterSaveBiz extends WorkFlowStepRTClassA {
	
	public JtcyAfterSaveBiz(UserContext uc){
		super(uc);
		super.setDescription("��ͥ��Ա_����ɹ�");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}
	
	@Override
	public boolean execute() {
		System.out.println(".....................��ͥ��Ա_����:�����ֵ.....................");
		
		int workflowInstanceId = getParameter(this.PARAMETER_INSTANCE_ID).toInt();//��õ�ǰ��������id
		Hashtable rsTable = BOInstanceAPI.getInstance().getBOData("BO_JTCYTEMP", workflowInstanceId);
		int dataId = 0;
		dataId = Integer.parseInt(rsTable.get("ID").toString() == null ? "" : rsTable.get("ID").toString().trim());
		
		CalMark calMark = new CalMark();
		int finalMark = calMark.getCalMark("BO_JTCYTEMP", dataId);
		
		String updateLoginIdSql = "";
		updateLoginIdSql = "update BO_JTCYTEMP set finalMark=" +finalMark+ " where ID="+dataId;
		DBSql.executeUpdate(updateLoginIdSql);
		
		return true;
	}
	
}
