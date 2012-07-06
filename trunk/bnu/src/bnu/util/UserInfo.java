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
	 * 取部门ID
	 */
	public int getDepId(){
		return getUserModel()._departmentId;
	}
	public DepartmentModel getDeptModel(){
		DepartmentModel dm = (DepartmentModel)DepartmentCache.getModel(getDepId());
		return dm;
	}
	/*
	 * 取部门名称
	 */
	public String getDepName(){
		return getDeptModel()._departmentName;
		
	}
	/*
	 * 取部门全路经
	 */
	public String getDepFullName(){
		return DepartmentCache.getFullName(getDepId());
		
	}
	/*
	 * 取部门全路经ID
	 */
	public String getDepFullId(){
		return DepartmentCache.getFullID(getDepId());
		
	}
	/*
	 * 取员工sap帐号
	 */
	public String getSAPUid(){
		return getUserModel()._extend1;
		
	}
	/*
	 * 取员工姓名
	 */
	public String getUserName(){
		return getUserModel()._userName;
		
	}
	/*
	 * 岗位编号
	 */
	public String getGwId(){
		String sql="select PLANS from BO_SAP_PA0001 where pernr='"+getSAPUid()+"' and (sysdate between begda and endda)";
		return DBSql.getString(sql,"PLANS")==null?"":DBSql.getString(sql,"PLANS");
		
	}
	/*
	 * 岗位名称
	 */
	public String getGwName(){
		String sql="select STEXT from bo_sap_hrp1000 where istat='1' and otype='S' and PLVAR<>'.:' and objid='"+getGwId()+"' and (sysdate between begda and endda)";
		return DBSql.getString(sql,"STEXT")==null?"":DBSql.getString(sql,"STEXT");
		
	}
	/*
	 * 联系方式
	 */
	public String getLxfs(){
		String sql = "select ZUOJI from BO_HR_ARCHIVES where SAPUID='"+getSAPUid()+"'";
		return DBSql.getString(sql,"ZUOJI")==null?"":DBSql.getString(sql,"ZUOJI");
		
	}
}

