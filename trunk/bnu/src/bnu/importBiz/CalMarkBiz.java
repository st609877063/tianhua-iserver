package bnu.importBiz;

import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.loader.core.WorkFlowStepButtonClickRTClassA;

public class CalMarkBiz extends WorkFlowStepButtonClickRTClassA {
	
	public CalMarkBiz(UserContext uc){
		super(uc);
		super.setDescription("��ֵ����");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}

	@Override
	public String onClick() {
		System.out.println(".....................��ֵ����................");
		return "����ɹ�";
	}
}
