package bnu.importBiz;

import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.loader.core.WorkFlowStepButtonClickRTClassA;

public class CalMarkResetClick extends WorkFlowStepButtonClickRTClassA {
	
	public CalMarkResetClick(UserContext uc){
		super(uc);
		super.setDescription("��ֵ����(��գ����¼���)");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}

	@Override
	public String onClick() {
		System.out.println(".....................CalMarkReset.class��ֵ���㣨��գ����¼��㣩................");
		return "����ɹ�";
	}
}
