package bnu.workFlowBiz;

import java.util.Hashtable;

import bnu.importBiz.CalMark;

import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.loader.core.WorkFlowStepRTClassA;
import com.actionsoft.sdk.local.level0.BOInstanceAPI;

//����ѧϰ 

public class GnxxjlAfterSaveBiz extends WorkFlowStepRTClassA {
	
	public GnxxjlAfterSaveBiz(UserContext uc){
		super(uc);
		super.setDescription("����ѧϰ_����ɹ�");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}
	
	@Override
	public boolean execute() {
		System.out.println(".....................����ѧϰ_����:�����ֵ.....................");
		
		int workflowInstanceId = getParameter(this.PARAMETER_INSTANCE_ID).toInt();//��õ�ǰ��������id
		Hashtable rsTable = BOInstanceAPI.getInstance().getBOData("BO_GNXXJLTEMP", workflowInstanceId);
		int dataId = 0;
		dataId = Integer.parseInt(rsTable.get("ID").toString() == null ? "" : rsTable.get("ID").toString().trim());
		
//		CalMark calMark = new CalMark();
//		int finalMark = calMark.getCalMark("BO_GNXXJLTEMP", dataId);
//		
//		String updateLoginIdSql = "";
//		updateLoginIdSql = "update BO_GNXXJLTEMP set finalMark=" +finalMark+ " where ID="+dataId;
//		DBSql.executeUpdate(updateLoginIdSql);
		
		return true;
	}
	
}
