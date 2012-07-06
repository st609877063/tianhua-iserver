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

//人才培养_任课情况 审批节点

public class RkqkValidateBiz extends WorkFlowStepRTClassA {
	
	public RkqkValidateBiz(UserContext uc){
		super(uc);
		super.setDescription("人才培养_任课情况_有效性验证");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}
	
	@Override
	public boolean execute() {
		System.out.println("########################人才培养_任课情况_有效性验证#########################");
		
		int workflowInstanceId = getParameter(this.PARAMETER_INSTANCE_ID).toInt();//获得当前工作流的id
		
//		Hashtable rsTable = BOInstanceAPI.getInstance().getBOData("BO_RKQKTEMP", workflowInstanceId);
//		
//		String id = rsTable.get("ID").toString() == null ? "" : rsTable.get("ID").toString();
//		
//		String time = rsTable.get("ZXS").toString() == null ? "" : rsTable.get("ZXS").toString(); //时间
//		if(!isNumber(time)) {
//			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，‘总学时’请输入数字或者小数，请确认!", true);
//			return false;
//		}
//
//		String datayear = rsTable.get("DATAYEAR").toString()==null?"":rsTable.get("DATAYEAR").toString().trim();
//		if(datayear.equals("") || datayear.length() != 4) {
//			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，【计算年度】必须填写，并且格式为四位数。请确认!", true);
//			return false;
//		}
		
		return true;
	}
	
	public static boolean isNumeric(String str) {   
        if(str == null)   
            return false;   
        int sz = str.length();   
        for(int i = 0; i < sz; i++)   
            if(!Character.isDigit(str.charAt(i)))   
                return false;   
  
        return true;   
    } 
	
	public static boolean isNumber(String number){   
        int index = number.indexOf(".");   
        if(index<0){   
            return isNumeric(number);   
        }else{   
            String num1 = number.substring(0,index);   
            String num2 = number.substring(index+1);               
               
            return isNumeric(num1) && isNumeric(num2);   
        }   
    }
	
}
