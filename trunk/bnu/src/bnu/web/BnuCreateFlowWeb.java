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
	
	private static final String _returnButton = "<input type=button value='��  ��'  class ='actionsoftButton' onClick=\"window.close();return false;\"  border='0'>";

	/**
	 * ���췽��
	 * @param userContext �û������Ķ���
	 */
	public BnuCreateFlowWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * ���췽��
	 */
	public BnuCreateFlowWeb() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * ���Ա��������ҳ
	 * @param pageType tab����
	 * @param awsuid   AWSϵͳ�û��ʺ�
	 * @return
	 */
	public String startupFlow(String awsuid, String type) {
		String FenFaUUID = null;
		String title = null;
		
		if(type.equalsIgnoreCase("gnxxjl")) { //��������_����ѧϰ����
			FenFaUUID = "014bbe7b77d6d8cc5b345ffdca7f0e4b";
			title = "��������_����ѧϰ����_�";
		} else if(type.equalsIgnoreCase("kyxm")) { //�������_������Ŀ
			FenFaUUID = "016db93b762f1b862dc7f1345852dade";
			title = "�������_������Ŀ_�";
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
