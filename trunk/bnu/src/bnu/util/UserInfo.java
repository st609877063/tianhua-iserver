package bnu.util;

import com.actionsoft.awf.organization.cache.DepartmentCache;
import com.actionsoft.awf.organization.cache.UserCache;
import com.actionsoft.awf.organization.model.DepartmentModel;
import com.actionsoft.awf.organization.model.UserModel;
import com.actionsoft.awf.util.DBSql;

public class UserInfo {
	
	String uid=null;
	public UserInfo(String uid){
		this.uid=uid;
	}
	public UserModel getUserModel(){
		UserModel um = (UserModel)UserCache.getModel(uid);
		return um;
	}
	/*
	 * ȡ����ID
	 */
	public int getDepId(){
		return getUserModel()._departmentId;
	}
	public DepartmentModel getDeptModel(){
		DepartmentModel dm = (DepartmentModel)DepartmentCache.getModel(getDepId());
		return dm;
	}
	/*
	 * ȡ��������
	 */
	public String getDepName(){
		return getDeptModel()._departmentName;
		
	}
	/*
	 * ȡ����ȫ·��
	 */
	public String getDepFullName(){
		return DepartmentCache.getFullName(getDepId());
		
	}
	/*
	 * ȡ����ȫ·��ID
	 */
	public String getDepFullId(){
		return DepartmentCache.getFullID(getDepId());
		
	}
	/*
	 * ȡԱ��sap�ʺ�
	 */
	public String getSAPUid(){
		return getUserModel()._extend1;
		
	}
	/*
	 * ȡԱ������
	 */
	public String getUserName(){
		return getUserModel()._userName;
		
	}
	/*
	 * ��λ���
	 */
	public String getGwId(){
		String sql="select PLANS from BO_SAP_PA0001 where pernr='"+getSAPUid()+"' and (sysdate between begda and endda)";
		return DBSql.getString(sql,"PLANS")==null?"":DBSql.getString(sql,"PLANS");
		
	}
	/*
	 * ��λ����
	 */
	public String getGwName(){
		String sql="select STEXT from bo_sap_hrp1000 where istat='1' and otype='S' and PLVAR<>'.:' and objid='"+getGwId()+"' and (sysdate between begda and endda)";
		return DBSql.getString(sql,"STEXT")==null?"":DBSql.getString(sql,"STEXT");
		
	}
	/*
	 * ��ϵ��ʽ
	 */
	public String getLxfs(){
		String sql = "select ZUOJI from BO_HR_ARCHIVES where SAPUID='"+getSAPUid()+"'";
		return DBSql.getString(sql,"ZUOJI")==null?"":DBSql.getString(sql,"ZUOJI");
		
	}
}

