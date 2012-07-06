package bnu.importBiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.actionsoft.awf.organization.control.MessageQueue;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.loader.core.WorkFlowStepRTClassA;

public class ZdxsAfterImportBiz extends WorkFlowStepRTClassA {
	
	public ZdxsAfterImportBiz(UserContext uc){
		super(uc);
		super.setDescription("指导学生导入");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}

	public boolean execute() {
		System.out.println(".....................指导学生导入begin.导入数据后，为每条导入的数据绑定流程................");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conn = DBSql.open();
			conn.setAutoCommit(true);
			
			String sql = "select * from BO_ZDXSTEMP";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			int dataId = 0;
			int totalData = 0;
			int wrongData = 0;
			String wrongNm = "";
			String chargenm = ""; //导师名称
			String chargeid = "";
			String xm = "";
			String xh = "";
			String type = ""; //*
			String time = ""; //*
			String xb = "";
			String xslb = "";
			String lb = "";
			String xy = "";
			String zy = "";
			String xjzt = "";
			
			while(rs.next()){
				totalData = totalData + 1;
				dataId = rs.getInt("ID");
				
				chargenm = rs.getString("CHARGENM")==null?"":rs.getString("CHARGENM").trim();
				chargeid = rs.getString("CHARGEID")==null?"":rs.getString("CHARGEID").trim();
				xm = rs.getString("XM")==null?"":rs.getString("XM").trim();
				xh = rs.getString("XH")==null?"":rs.getString("XH").trim();
				type = rs.getString("TYPE")==null?"":rs.getString("TYPE").trim();
				time = rs.getString("TIME")==null?"":rs.getString("TIME").trim();
				xb = rs.getString("XB")==null?"":rs.getString("XB").trim();
				xslb = rs.getString("XSLB")==null?"":rs.getString("XSLB").trim();
				lb = rs.getString("LB")==null?"":rs.getString("LB").trim();
				xy = rs.getString("XY")==null?"":rs.getString("XY").trim();
				zy = rs.getString("ZY")==null?"":rs.getString("ZY").trim();
				xjzt = rs.getString("XJZT")==null?"":rs.getString("XJZT").trim();

				if(chargeid.equals("")) {
					wrongData = wrongData +1;
					wrongNm = wrongNm + chargenm + "; ";
				}
				
				int processInstanceId = 0;
				if(!chargeid.equals("")) {
					//运行AWS流程-----------------为数据绑定流程
//					processInstanceId = WorkflowInstanceAPI.getInstance().createProcessInstance("007b23151fd4ccbe70caeb032a57569c", loginId, "指导学生申请");
//					int[] processTaskInstanceIds = WorkflowTaskInstanceAPI.getInstance().createProcessTaskInstance(loginId, processInstanceId, SynType.synchronous, PriorityType.hight, 1, loginId, "指导学生申请", false, 0);
//					System.out.println("processInstanceId="+processInstanceId+" ,processTaskInstanceIds="+processTaskInstanceIds[0]);
				}
				
				CalMark calMark = new CalMark();
				//finalMark的值即为分值*学时的结果。
				int finalMark = calMark.getCalMark("BO_ZDXSTEMP", dataId);
//				calMark.setFinalMark(chargenm, chargeid, chargeid, "BO_ZDXSTEMP", finalMark);
				String datayear = rs.getString("DATAYEAR")==null?"":rs.getString("DATAYEAR").trim();
				calMark.setFinalMarkWithYear(chargenm, chargeid, chargeid, "BO_ZDXSTEMP", finalMark, datayear);
				
				//此批量导入，设定数据直接绑定chargeId对应的bindId
				//并且STATUS状态设置为9。表示为批量导入时设置的状态
				String queryBindIdForChargeIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +chargeid+ "'";
				int chargeIdBindId = 0;
				chargeIdBindId = DBSql.getInt(conn, queryBindIdForChargeIdSql, "BINDID");
				
				String updateLoginIdSql = "";
				if(chargeIdBindId != 0) {
					updateLoginIdSql = "update BO_ZDXSTEMP set BINDID=" +chargeIdBindId
					+ ", STATUS=9 "
					+ ", finalMark="+finalMark
					+ " where ID="+dataId;
				} else {
					updateLoginIdSql = "update BO_ZDXSTEMP set STATUS=9 "
					+ ", finalMark="+finalMark
					+ " where ID="+dataId;
				}
				
				//System.out.println(updateLoginIdSql);
				DBSql.executeUpdate(updateLoginIdSql);
			}
			
			String tip = "数据导入成功！共导入 "+totalData+" 条数据。";
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), tip, true);
			if(wrongData != 0) {
				MessageQueue.getInstance().putMessage(getUserContext().getUID(), "导入数据中有"+wrongData+"条数据的负责人在基本信息库中不存在。为："+wrongNm, true);
			}
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "数据导入后即与相应人员相绑定，管理员无法查看这些数据！", true);
			
		}catch(Exception e){
			e.printStackTrace(System.err);
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，信息导入失败！请重新导入Excel", true);
			return false;
		}finally{
			DBSql.close(conn, ps, rs);
		}
		
		return true;
	}
	
}
