package bnu.importBiz;

import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.loader.core.WorkFlowStepButtonClickRTClassA;

public class CalMarkResetClick extends WorkFlowStepButtonClickRTClassA {
	
	public CalMarkResetClick(UserContext uc){
		super(uc);
		super.setDescription("分值计算(清空，重新计算)");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}

	@Override
	public String onClick() {
		System.out.println(".....................CalMarkReset.class分值计算（清空，重新计算）................");
		return "计算成功";
	}
}
