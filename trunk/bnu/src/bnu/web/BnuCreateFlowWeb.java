package bnu.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;

import bnu.util.HRCardButton;

import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.awf.util.UpFile;
import com.actionsoft.htmlframework.htmlmodel.HtmlModelFactory;
import com.actionsoft.htmlframework.htmlmodel.RepleaseKey;
import com.actionsoft.htmlframework.web.ActionsoftWeb;
import com.actionsoft.sdk.AWSSDKException;
import com.actionsoft.sdk.WorkflowEngineAPI;
import com.actionsoft.sdk.WorkflowTaskEngineAPI;
import com.actionsoft.sdk.local.level0.BOInstanceAPI;

public class BnuCreateFlowWeb extends ActionsoftWeb {
	
	private static final String _returnButton = "<input type=button value='关  闭'  class ='actionsoftButton' onClick=\"window.close();return false;\"  border='0'>";

	/**
	 * 构造方法
	 * @param userContext 用户上下文对象
	 */
	public BnuCreateFlowWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造方法
	 */
	public BnuCreateFlowWeb() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 获得员工自助首页
	 * @param pageType tab类型
	 * @param awsuid   AWS系统用户帐号
	 * @return
	 */
	public String startupFlow(String awsuid, String type) {
		String FenFaUUID = null;
		String title = null;
		
		if(type.equalsIgnoreCase("gnxxjl")) { //教育经历_国内学习经历
			FenFaUUID = "014bbe7b77d6d8cc5b345ffdca7f0e4b";
			title = "教育经历_国内学习经历_填报";
		} else if(type.equalsIgnoreCase("kyxm")) { //科研情况_科研项目
			FenFaUUID = "016db93b762f1b862dc7f1345852dade";
			title = "科研情况_科研项目_填报";
		} 

		int instanceID=0;
		int messageID=0;
		try {
			instanceID = WorkflowEngineAPI.getInstance().createWorkflowInstance(FenFaUUID,awsuid,title);
			messageID = WorkflowTaskEngineAPI.getInstance().createTaskInstance(awsuid,awsuid, instanceID, 1, title);
		}catch(AWSSDKException e1) {
			e1.printStackTrace(System.err);
		}
		return instanceID+";"+messageID;
	}
}
