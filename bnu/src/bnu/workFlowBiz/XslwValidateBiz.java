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

//科研情况_学术论文 审批节点

public class XslwValidateBiz extends WorkFlowStepRTClassA {
	
	public XslwValidateBiz(UserContext uc){
		super(uc);
		super.setDescription("科研情况_学术论文_有效性验证");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}
	
	@Override
	public boolean execute() {
		System.out.println(".....................科研情况_学术论文_有效性验证.....................");
		
		int workflowInstanceId = getParameter(this.PARAMETER_INSTANCE_ID).toInt();//获得当前工作流的id
		
		Hashtable rsTable = BOInstanceAPI.getInstance().getBOData("BO_XSLWTEMP", workflowInstanceId);
		
//		String name0 = rsTable.get("CHARGENM").toString() == null ? "" : rsTable.get("CHARGENM").toString();
//		String name1 = rsTable.get("NAME1").toString() == null ? "" : rsTable.get("NAME1").toString();
//		String name2 = rsTable.get("NAME2").toString() == null ? "" : rsTable.get("NAME2").toString();
//		String name3 = rsTable.get("NAME3").toString() == null ? "" : rsTable.get("NAME3").toString();
//		String name4 = rsTable.get("NAME4").toString() == null ? "" : rsTable.get("NAME4").toString();
//		String name5 = rsTable.get("NAME5").toString() == null ? "" : rsTable.get("NAME5").toString();
		
//		String RATE0Temp = rsTable.get("CHARGERATE").toString() == null ? "" : rsTable.get("CHARGERATE").toString();
//		String RATE1Temp = rsTable.get("RATE1").toString() == null ? "" : rsTable.get("RATE1").toString();
//		String RATE2Temp = rsTable.get("RATE2").toString() == null ? "" : rsTable.get("RATE2").toString();
//		String RATE3Temp = rsTable.get("RATE3").toString() == null ? "" : rsTable.get("RATE3").toString();
//		String RATE4Temp = rsTable.get("RATE4").toString() == null ? "" : rsTable.get("RATE4").toString();
//		String RATE5Temp = rsTable.get("RATE5").toString() == null ? "" : rsTable.get("RATE5").toString();
//		int rate0 = Integer.parseInt(RATE0Temp);
//		int rate1 = Integer.parseInt(RATE1Temp);
//		int rate2 = Integer.parseInt(RATE2Temp);
//		int rate3 = Integer.parseInt(RATE3Temp);
//		int rate4 = Integer.parseInt(RATE4Temp);
//		int rate5 = Integer.parseInt(RATE5Temp);
		
//		System.out.println(name0+"="+rate0+"; "+name1+"="+rate1+"; "+name2+"="+rate2+"; "+name3+"="+rate3+"; "+ name4+"="+rate4+"; "+name5+"="+rate5);
		
//		if(!name0.equals("") && rate0 == 0) {
//			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，负责人的工作量为0，不符合情况，请确认!", true);
//			return false;
//		}
//		if(!name1.equals("") && rate1 == 0) {
//			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，参与人1的工作量为0，不符合情况，请确认!", true);
//			return false;
//		}
//		if(!name2.equals("") && rate2 == 0) {
//			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，参与人2的工作量为0，不符合情况，请确认!", true);
//			return false;
//		}
//		if(!name3.equals("") && rate3 == 0) {
//			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，参与人3的工作量为0，不符合情况，请确认!", true);
//			return false;
//		}
//		if(!name4.equals("") && rate4 == 0) {
//			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，参与人4的工作量为0，不符合情况，请确认!", true);
//			return false;
//		}
//		if(!name5.equals("") && rate5 == 0) {
//			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，参与人5的工作量为0，不符合情况，请确认!", true);
//			return false;
//		}
//		
//		if(name0.equals("") && rate0 != 0) {
//			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，没有负责人数据，但其工作量为不为0，不符合情况，请确认!", true);
//			return false;
//		}
//		if(name1.equals("") && rate1 != 0) {
//			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，没有参与人1数据，但其工作量为不为0，不符合情况，请确认!", true);
//			return false;
//		}
//		if(name2.equals("") && rate2 != 0) {
//			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，没有参与人2数据，但其工作量为不为0，不符合情况，请确认!", true);
//			return false;
//		}
//		if(name3.equals("") && rate3 != 0) {
//			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，没有参与人3数据，但其工作量为不为0，不符合情况，请确认!", true);
//			return false;
//		}
//		if(name4.equals("") && rate4 != 0) {
//			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，没有参与人4数据，但其工作量为不为0，不符合情况，请确认!", true);
//			return false;
//		}
//		if(name5.equals("") && rate5 != 0) {
//			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，没有参与人5数据，但其工作量为不为0，不符合情况，请确认!", true);
//			return false;
//		}
//		
//		if(rate0+rate1+rate2+rate3+rate4+rate5 != 100) {
//			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，总工作量不为100%，不符合情况，请确认!", true);
//			return false;
//		}

		String datayear = rsTable.get("DATAYEAR").toString()==null?"":rsTable.get("DATAYEAR").toString().trim();
		if(datayear.equals("") || datayear.length() != 4) {
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，【计算年度】必须填写，并且格式为四位数。请确认!", true);
			return false;
		}
		
		String chargesf = rsTable.get("CHARGESF").toString() == null ? "" : rsTable.get("CHARGESF").toString().trim();
		if(chargesf.equals("")) {
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，请选择【第一作者身份】。请确认!", true);
			return false;
		}
		
		return true;
	}
	
}
