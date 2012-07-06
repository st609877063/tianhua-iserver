package bnu.importBiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.actionsoft.awf.organization.control.MessageQueue;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.awf.workflow.execute.PriorityType;
import com.actionsoft.awf.workflow.execute.SynType;
import com.actionsoft.loader.core.WorkFlowStepRTClassA;
import com.actionsoft.sdk.local.level0.BOInstanceAPI;
import com.actionsoft.sdk.local.level0.WorkflowInstanceAPI;
import com.actionsoft.sdk.local.level0.WorkflowTaskInstanceAPI;

public class XslwAfterImportBiz extends WorkFlowStepRTClassA {
	
	public XslwAfterImportBiz(UserContext uc){
		super(uc);
		super.setDescription("学术论文导入");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}

	public boolean execute() {
		System.out.println(".....................学术论文导入begin.导入数据后，为每条导入的数据绑定流程................");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conn = DBSql.open();
			conn.setAutoCommit(true);
			
//			String countSql = "select count(1) totalNum from BO_XSLWTEMP";
//			int totalNum = DBSql.getInt(conn, countSql, "totalNum");
			
			String sql = "select * from BO_XSLWTEMP";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			int dataId = 0;
			
			String lwmc = "";
			String lwywmc = "";
			String lwlx = "";
			String wcxs = "";
			String chargesf = "";
			String ssyx = "";
			String chargenm = "";
			int chargerate = 0;
			String name1 = "";
			int rate1 = 0;
			String name2 = "";
			int rate2 = 0;
			String name3 = "";
			int rate3 = 0;
			String name4 = "";
			int rate4 = 0;
			String name5 = "";
			int rate5 = 0;
			String gklx = "";
			String fbkw = "";
			String fbfw = "";
			String kwjb = "";
			String kwjb2 = "";
			String nian = "";
			String juan = "";
			String qh = "";
			String ym = "";
			String lwyz = "";
			String sfyw = "";
			String lwyyqk = "";
			String yjlx = "";
			String sfzz = "";
			String zzcc = "";
			String zs = "";
			String dyzzdw = "";
			String fbsj = "";
			String fbnd = "";
			String yjly = "";
			String ktly = "";
			String zzlx = "";
			String xgxkly = "";
			String kwlb = "";
			String lwyz2 = "";
			String xkly = "";
			String attach = "";
			String sftxzz = "";
			
			String jgh = "";
			String jgh1 = "";
			String jgh2 = "";
			String jgh3 = "";
			String jgh4 = "";
			String jgh5 = "";
			
			
			int noChargeJghData = 0;
			List notExitUserList = new ArrayList();
			List notExitUserNameList = new ArrayList();
			int totalData = 0;
			int createBoFailureNum = 0;
			
			while(rs.next()){
				String copyDataId = "";
				copyDataId = rs.getString("COPYDATAID")==null?"":rs.getString("COPYDATAID").trim();
				//根据此字段判断该数据是否被处理过。
				if(copyDataId.equals("")) {
					
					totalData = totalData + 1;
					dataId = rs.getInt("ID");
					
					lwmc = rs.getString("lwmc")==null?"":rs.getString("lwmc").trim();
					lwywmc = rs.getString("lwywmc")==null?"":rs.getString("lwywmc").trim();
					lwlx = rs.getString("lwlx")==null?"":rs.getString("lwlx").trim();
					wcxs = rs.getString("wcxs")==null?"":rs.getString("wcxs").trim();
					chargesf = rs.getString("chargesf")==null?"":rs.getString("chargesf").trim();
					ssyx = rs.getString("ssyx")==null?"":rs.getString("ssyx").trim();
					chargenm = rs.getString("chargenm")==null?"":rs.getString("chargenm").trim();
					chargerate = rs.getInt("chargerate");
					name1 = rs.getString("name1")==null?"":rs.getString("name1").trim();
					rate1 = rs.getInt("rate1");
					name2 = rs.getString("name2")==null?"":rs.getString("name2").trim();
					rate2 = rs.getInt("rate2");
					name3 = rs.getString("name3")==null?"":rs.getString("name3").trim();
					rate3 = rs.getInt("rate3");
					name4 = rs.getString("name4")==null?"":rs.getString("name4").trim();
					rate4 = rs.getInt("rate4");
					name5 = rs.getString("name5")==null?"":rs.getString("name5").trim();
					rate5 = rs.getInt("rate5");
					gklx = rs.getString("gklx")==null?"":rs.getString("gklx").trim();
					fbkw = rs.getString("fbkw")==null?"":rs.getString("fbkw").trim();
					fbfw = rs.getString("fbfw")==null?"":rs.getString("fbfw").trim();
					kwjb = rs.getString("kwjb")==null?"":rs.getString("kwjb").trim();
					kwjb2 = rs.getString("kwjb2")==null?"":rs.getString("kwjb2").trim();
					nian = rs.getString("nian")==null?"":rs.getString("nian").trim();
					juan = rs.getString("juan")==null?"":rs.getString("juan").trim();
					qh = rs.getString("qh")==null?"":rs.getString("qh").trim();
					ym = rs.getString("ym")==null?"":rs.getString("ym").trim();;
					lwyz = rs.getString("lwyz")==null?"":rs.getString("lwyz").trim();
					sfyw = rs.getString("sfyw")==null?"":rs.getString("sfyw").trim();
					lwyyqk = rs.getString("lwyyqk")==null?"":rs.getString("lwyyqk").trim();
					yjlx = rs.getString("yjlx")==null?"":rs.getString("yjlx").trim();
					sfzz = rs.getString("sfzz")==null?"":rs.getString("sfzz").trim();
					zzcc = rs.getString("zzcc")==null?"":rs.getString("zzcc").trim();
					zs = rs.getString("zs")==null?"":rs.getString("zs").trim();
					dyzzdw = rs.getString("dyzzdw")==null?"":rs.getString("dyzzdw").trim();
					fbsj = rs.getString("fbsj")==null?"":rs.getString("fbsj").trim();
					fbnd = rs.getString("fbnd")==null?"":rs.getString("fbnd").trim();
					yjly = rs.getString("yjly")==null?"":rs.getString("yjly").trim();
					ktly = rs.getString("ktly")==null?"":rs.getString("ktly").trim();
					zzlx = rs.getString("zzlx")==null?"":rs.getString("zzlx").trim();
					xgxkly = rs.getString("xgxkly")==null?"":rs.getString("xgxkly").trim();
					kwlb = rs.getString("KWLB")==null?"":rs.getString("KWLB").trim();
					lwyz2 = rs.getString("LWYZ2")==null?"":rs.getString("LWYZ2").trim();
					xkly = rs.getString("XKLY")==null?"":rs.getString("XKLY").trim();
					attach = rs.getString("ATTACH")==null?"":rs.getString("ATTACH").trim();
					sftxzz = rs.getString("SFTXZZ")==null?"":rs.getString("SFTXZZ").trim();
					
					//Excel中有“姓名”和“教工号”，无“登录号”。Excel导入后，name和jgh有值。而id无值。
					jgh = rs.getString("JGH")==null?"":rs.getString("JGH").trim();
					jgh1 = rs.getString("JGH1")==null?"":rs.getString("JGH1").trim();
					jgh2 = rs.getString("JGH2")==null?"":rs.getString("JGH2").trim();
					jgh3 = rs.getString("JGH3")==null?"":rs.getString("JGH3").trim();
					jgh4 = rs.getString("JGH4")==null?"":rs.getString("JGH4").trim();
					jgh5 = rs.getString("JGH5")==null?"":rs.getString("JGH5").trim();
					
					if(fbsj!=null && fbsj.length()>=10) {
						fbsj = fbsj.substring(0, 10);
					}
					if(fbnd == null || fbnd.equals("")) {
						if(fbsj!=null && fbsj.length()==10) {
							fbnd = fbsj.substring(0, 4);
						}
					}
					
					String loginId = "";
					String loginId1 = "";
					String loginId2 = "";
					String loginId3 = "";
					String loginId4 = "";
					String loginId5 = "";
					
					String querySql = "";
					if(jgh!=null && !jgh.equals("")) {
						querySql = "select loginid from BO_RSTEMP where ZGH='" +jgh+"'";
						loginId = DBSql.getString(conn, querySql, "loginid").trim();
					} else {
						noChargeJghData = noChargeJghData + 1;
					}
					if(jgh1!=null && !jgh1.equals("")) {
						querySql = "select loginid from BO_RSTEMP where ZGH='" +jgh1+"'";
						loginId1 = DBSql.getString(conn, querySql, "loginid").trim();
					}
					if(jgh2!=null && !jgh2.equals("")) {
						querySql = "select loginid from BO_RSTEMP where ZGH='" +jgh2+"'";
						loginId2 = DBSql.getString(conn, querySql, "loginid").trim();
					}
					if(jgh3!=null && !jgh3.equals("")) {
						querySql = "select loginid from BO_RSTEMP where ZGH='" +jgh3+"'";
						loginId3 = DBSql.getString(conn, querySql, "loginid").trim();
					}
					if(jgh4!=null && !jgh4.equals("")) {
						querySql = "select loginid from BO_RSTEMP where ZGH='" +jgh4+"'";
						loginId4 = DBSql.getString(conn, querySql, "loginid").trim();
					}
					if(jgh5!=null && !jgh5.equals("")) {
						querySql = "select loginid from BO_RSTEMP where ZGH='" +jgh5+"'";
						loginId5 = DBSql.getString(conn, querySql, "loginid").trim();
					}
					
					
					//判断账号是否存在
					if(loginId==null || loginId.equals("")) {
						int flag = 0;
						for(int i=0; i<notExitUserList.size(); i++) {
							if( notExitUserList.get(i).toString().equals(jgh) ) {
								flag = 1;
								break;
							}
						}
						if(flag == 0) {
							notExitUserList.add(jgh);
							notExitUserNameList.add(chargenm);
						}
					}
					if(loginId1==null || loginId1.equals("")) {
						int flag = 0;
						for(int i=0; i<notExitUserList.size(); i++) {
							if( notExitUserList.get(i).toString().equals(jgh1) ) {
								flag = 1;
								break;
							}
						}
						if(flag == 0) {
							notExitUserList.add(jgh1);
							notExitUserNameList.add(name1);
						}
					}
					if(loginId2==null || loginId2.equals("")) {
						int flag = 0;
						for(int i=0; i<notExitUserList.size(); i++) {
							if( notExitUserList.get(i).toString().equals(jgh2) ) {
								flag = 1;
								break;
							}
						}
						if(flag == 0) {
							notExitUserList.add(jgh2);
							notExitUserNameList.add(name2);
						}
					}
					if(loginId3==null || loginId3.equals("")) {
						int flag = 0;
						for(int i=0; i<notExitUserList.size(); i++) {
							if( notExitUserList.get(i).toString().equals(jgh3) ) {
								flag = 1;
								break;
							}
						}
						if(flag == 0) {
							notExitUserList.add(jgh3);
							notExitUserNameList.add(name3);
						}
					}
					if(loginId4==null || loginId4.equals("")) {
						int flag = 0;
						for(int i=0; i<notExitUserList.size(); i++) {
							if( notExitUserList.get(i).toString().equals(jgh4) ) {
								flag = 1;
								break;
							}
						}
						if(flag == 0) {
							notExitUserList.add(jgh4);
							notExitUserNameList.add(name4);
						}
					}
					if(loginId5==null || loginId5.equals("")) {
						int flag = 0;
						for(int i=0; i<notExitUserList.size(); i++) {
							if( notExitUserList.get(i).toString().equals(jgh5) ) {
								flag = 1;
								break;
							}
						}
						if(flag == 0) {
							notExitUserList.add(jgh5);
							notExitUserNameList.add(name5);
						}
					}
					
					
					int processInstanceId = 0;
					if(!loginId.equals("")) {
						//运行AWS流程-----------------为数据绑定流程
//					processInstanceId = WorkflowInstanceAPI.getInstance().createProcessInstance("007b23151fd6ea094d1dade015af3e4f", loginId, "学术论文申请");
//					int[] processTaskInstanceIds = WorkflowTaskInstanceAPI.getInstance().createProcessTaskInstance(loginId, processInstanceId, SynType.synchronous, PriorityType.hight, 1, loginId, "学术论文申请", false, 0);
//					System.out.println("processInstanceId="+processInstanceId+" ,processTaskInstanceIds="+processTaskInstanceIds[0]);
					}
					
					CalMark calMark = new CalMark();
					int finalMark = calMark.getCalMark("BO_XSLWTEMP", dataId);
//					calMark.setFinalMark(chargenm, loginId, jgh, "BO_XSLWTEMP", finalMark);
					String datayear = rs.getString("DATAYEAR")==null?"":rs.getString("DATAYEAR").trim();
					if(sftxzz.equals("是")) {
						calMark.setFinalMarkWithYear(name1, loginId1, jgh1, "BO_XSLWTEMP", finalMark, datayear);
					} else {
						calMark.setFinalMarkWithYear(chargenm, loginId, jgh, "BO_XSLWTEMP", finalMark, datayear);
					}
					
					//此批量导入，设定数据直接绑定chargeId对应的bindId
					//并且STATUS状态设置为9。表示为批量导入时设置的状态
					String queryBindIdForChargeIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId+ "'";
					int chargeIdBindId = 0;
					chargeIdBindId = DBSql.getInt(conn, queryBindIdForChargeIdSql, "BINDID");
					
					String updateLoginIdSql = "";
//				if(processInstanceId == 0) {
//					updateLoginIdSql = "update BO_XSLWTEMP set CHARGEID ='" +loginId +"' "
//					+ ", ID1='" +loginId1 + "' "
//					+ ", ID2='" +loginId2 + "' "
//					+ ", ID3='" +loginId3 + "' "
//					+ ", ID4='" +loginId4 + "' "
//					+ ", ID5='" +loginId5 + "' "
//					+ ", AUTOADD=0 "
//					+ ", finalMark="+finalMark
//					+ " where ID="+dataId;
//				} else {
//					updateLoginIdSql = "update BO_XSLWTEMP set CHARGEID ='" +loginId +"' "
//					+ ", ID1='" +loginId1 + "' "
//					+ ", ID2='" +loginId2 + "' "
//					+ ", ID3='" +loginId3 + "' "
//					+ ", ID4='" +loginId4 + "' "
//					+ ", ID5='" +loginId5 + "' "
//					+ ", BINDID=" +processInstanceId
//					+ ", AUTOADD=0 "
//					+ ", finalMark="+finalMark
//					+ " where ID="+dataId;
//				}
					if(chargeIdBindId != 0) {
						updateLoginIdSql = "update BO_XSLWTEMP set CHARGEID ='" +loginId +"' "
						+ ", ID1='" +loginId1 + "' "
						+ ", ID2='" +loginId2 + "' "
						+ ", ID3='" +loginId3 + "' "
						+ ", ID4='" +loginId4 + "' "
						+ ", ID5='" +loginId5 + "' "
						+ ", BINDID=" +chargeIdBindId
						+ ", STATUS=9 "
						+ ", AUTOADD=0 "
						+ ", finalMark="+finalMark
						+ ", FBND ='" + fbnd +"' "
						+ " where ID="+dataId;
					} else {
						updateLoginIdSql = "update BO_XSLWTEMP set CHARGEID ='" +loginId +"' "
						+ ", ID1='" +loginId1 + "' "
						+ ", ID2='" +loginId2 + "' "
						+ ", ID3='" +loginId3 + "' "
						+ ", ID4='" +loginId4 + "' "
						+ ", ID5='" +loginId5 + "' "
						+ ", STATUS=9 "
						+ ", AUTOADD=0 "
						+ ", finalMark="+finalMark
						+ ", FBND ='" + fbnd +"' "
						+ " where ID="+dataId;
					}
					
					//System.out.println(updateLoginIdSql);
					DBSql.executeUpdate(updateLoginIdSql);
					
					
					//**********************数据共享部分：为各参与人复制同样的数据.标记autoAdd字段为1**************************
					Hashtable recordData = new Hashtable();
//				recordData.put("LWMC", lwmc);
//				recordData.put("LWYWMC", lwywmc);
//				recordData.put("LWLX", lwlx);
//				recordData.put("WCXS", wcxs);
//				recordData.put("CHARGESF", chargesf);
//				recordData.put("SSYX", ssyx);
//				recordData.put("CHARGENM", chargenm);
//				recordData.put("CHARGERATE", chargerate);
//				recordData.put("NAME1", name1);
//				recordData.put("RATE1", rate1);
//				recordData.put("NAME2", name2);
//				recordData.put("RATE2", rate2);
//				recordData.put("NAME3", name3);
//				recordData.put("RATE3", rate3);
//				recordData.put("NAME4", name4);
//				recordData.put("RATE4", rate4);
//				recordData.put("NAME5", name5);
//				recordData.put("RATE5", rate5);
//				recordData.put("GKLX", gklx);
//				recordData.put("FBKW", fbkw);
//				recordData.put("FBFW", fbfw);
//				recordData.put("KWJB", kwjb);
//				recordData.put("NIAN", nian);
//				recordData.put("JUAN", juan);
//				recordData.put("QH", qh);
//				recordData.put("YM", ym);
//				recordData.put("LWYZ", lwyz);
//				recordData.put("SFYW", sfyw);
//				recordData.put("LWYYQK", lwyyqk);
//				recordData.put("YJLX", yjlx);
//				recordData.put("SFZZ", sfzz);
//				recordData.put("ZZCC", zzcc);
//				recordData.put("ZS", zs);
//				recordData.put("DYZZDW", dyzzdw);
//				recordData.put("FBSJ", fbsj);
//				recordData.put("YJLY", yjly);
//				recordData.put("KTLY", ktly);
//				recordData.put("ZZLX", zzlx);
//				recordData.put("XGXKLY", xgxkly);
//				recordData.put("AUTOADD", 1); //自动增加
//				recordData.put("STATUS", 0);
//				recordData.put("FINALMARK", 0);
//
//				recordData.put("CHARGEID", loginId);
//				recordData.put("ID1", loginId1);
//				recordData.put("ID2", loginId2);
//				recordData.put("ID3", loginId3);
//				recordData.put("ID4", loginId4);
//				recordData.put("ID5", loginId5);
//				recordData.put("JGH", jgh);
//				recordData.put("JGH1", jgh1);
//				recordData.put("JGH2", jgh2);
//				recordData.put("JGH3", jgh3);
//				recordData.put("JGH4", jgh4);
//				recordData.put("JGH5", jgh5);
					
					int loginId1BindId = 0;
					int loginId2BindId = 0;
					int loginId3BindId = 0;
					int loginId4BindId = 0;
					int loginId5BindId = 0;
					String queryBindIdSql = "";
					String copyDataIds = "0:";
					
					//BOInstanceAPI.getInstance().createBOData.如果两次使用该方法，则两次中的recordData必须不一致
					if(loginId1 != null && !loginId1.equals("")) {
						recordData.put("ID1", loginId1);
						recordData.put("JGH1", jgh1);
						queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId1+ "'";
						loginId1BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //绑定新增数据为该人员的bindId
						
						int createBoId = BOInstanceAPI.getInstance().createBOData("BO_XSLWTEMP", recordData, loginId1BindId, "admin");
						if(createBoId == -1) {
							createBoFailureNum = createBoFailureNum + 1;
						}
						copyDataIds = copyDataIds + createBoId + ":";
//					System.out.println("为 "+loginId1+" 共享数据：createBoId="+createBoId+", BINDID="+loginId1BindId);
						String updateCreateBoSql = "update BO_XSLWTEMP set "
							+ "	 LWMC ='" + lwmc +"' "
							+ ", LWYWMC ='" + lwywmc +"' "
							+ ", LWLX ='" + lwlx +"' "
							+ ", WCXS ='" + wcxs +"' "
							+ ", CHARGESF ='" + chargesf +"' "
							+ ", SSYX ='" + ssyx +"' "
							+ ", CHARGENM ='" + chargenm +"' "
							+ ", CHARGEID ='" + loginId +"' "
							+ ", JGH ='" + jgh +"' "
							+ ", CHARGERATE =" + chargerate
							+ ", NAME1 ='" + name1 +"' "
							+ ", ID1 ='" + loginId1 +"' "
							+ ", JGH1 ='" + jgh1 +"' "
							+ ", RATE1 =" + rate1
							+ ", NAME2 ='" + name2 +"' "
							+ ", ID2 ='" + loginId2 +"' "
							+ ", JGH2 ='" + jgh2 +"' "
							+ ", RATE2 =" + rate2
							+ ", NAME3 ='" + name3 +"' "
							+ ", ID3 ='" + loginId3 +"' "
							+ ", JGH3 ='" + jgh3 +"' "
							+ ", RATE3 =" + rate3
							+ ", NAME4 ='" + name4 +"' "
							+ ", ID4 ='" + loginId4 +"' "
							+ ", JGH4 ='" + jgh4 +"' "
							+ ", RATE4 =" + rate4
							+ ", NAME5 ='" + name5 +"' "
							+ ", ID5 ='" + loginId5 +"' "
							+ ", JGH5 ='" + jgh5 +"' "
							+ ", RATE5 =" + rate5
							+ ", GKLX ='" + gklx +"' "
							+ ", FBKW ='" + fbkw +"' "
							+ ", FBFW ='" + fbfw +"' "
							+ ", KWJB ='" + kwjb +"' "
							+ ", KWJB2 ='" + kwjb2 +"' "
							+ ", NIAN ='" + nian +"' "
							+ ", JUAN ='" + juan +"' "
							+ ", QH ='" + qh +"' "
							+ ", YM ='" + ym +"' "
							+ ", LWYZ ='" + lwyz +"' "
							+ ", SFYW ='" + sfyw +"' "
							+ ", LWYYQK ='" + lwyyqk +"' "
							+ ", YJLX ='" + yjlx +"' "
							+ ", SFZZ ='" + sfzz +"' "
							+ ", ZZCC ='" + zzcc +"' "
							+ ", ZS ='" + zs +"' "
							+ ", DYZZDW ='" + dyzzdw +"' "
							+ ", FBSJ = TO_DATE('" +  fbsj +"','YYYY-MM-DD') "
							+ ", FBND ='" + fbnd +"' "
							+ ", YJLY ='" + yjly +"' "
							+ ", KTLY ='" + ktly +"' "
							+ ", ZZLX ='" + zzlx +"' "
							+ ", XGXKLY ='" + xgxkly +"' "
							+ ", KWLB ='" + kwlb +"' "
							+ ", LWYZ2 ='" + lwyz2 +"' "
							+ ", XKLY ='" + xkly +"' "
							+ ", ATTACH ='" + attach +"' "
							+ ", SFTXZZ ='" + sftxzz +"' "
					        + ", DATAYEAR ='" + datayear +"' " + ", AUTOADD =1 "
							+ ", STATUS =0 "
							+ ", FINALMARK =0 "
							+ ", COPYDATAID ='0:' "
							+ " where ID="+createBoId;
//					System.out.println("updateCreateBoSql="+updateCreateBoSql);
						DBSql.executeUpdate(updateCreateBoSql);
					}
					if(loginId2 != null && !loginId2.equals("")) {
						recordData.put("ID2", loginId2);
						recordData.put("JGH2", jgh2);
						
						queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId2+ "'";
						loginId2BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //绑定新增数据为该人员的bindId
						
						int createBoId = BOInstanceAPI.getInstance().createBOData("BO_XSLWTEMP", recordData, loginId2BindId, "admin");
						if(createBoId == -1) {
							createBoFailureNum = createBoFailureNum + 1;
						}
						copyDataIds = copyDataIds + createBoId + ":";
//					System.out.println("为 "+loginId2+" 共享数据：createBoId="+createBoId+", BINDID="+loginId2BindId);
						String updateCreateBoSql = "update BO_XSLWTEMP set "
							+ "	 LWMC ='" + lwmc +"' "
							+ ", LWYWMC ='" + lwywmc +"' "
							+ ", LWLX ='" + lwlx +"' "
							+ ", WCXS ='" + wcxs +"' "
							+ ", CHARGESF ='" + chargesf +"' "
							+ ", SSYX ='" + ssyx +"' "
							+ ", CHARGENM ='" + chargenm +"' "
							+ ", CHARGEID ='" + loginId +"' "
							+ ", JGH ='" + jgh +"' "
							+ ", CHARGERATE =" + chargerate
							+ ", NAME1 ='" + name1 +"' "
							+ ", ID1 ='" + loginId1 +"' "
							+ ", JGH1 ='" + jgh1 +"' "
							+ ", RATE1 =" + rate1
							+ ", NAME2 ='" + name2 +"' "
							+ ", ID2 ='" + loginId2 +"' "
							+ ", JGH2 ='" + jgh2 +"' "
							+ ", RATE2 =" + rate2
							+ ", NAME3 ='" + name3 +"' "
							+ ", ID3 ='" + loginId3 +"' "
							+ ", JGH3 ='" + jgh3 +"' "
							+ ", RATE3 =" + rate3
							+ ", NAME4 ='" + name4 +"' "
							+ ", ID4 ='" + loginId4 +"' "
							+ ", JGH4 ='" + jgh4 +"' "
							+ ", RATE4 =" + rate4
							+ ", NAME5 ='" + name5 +"' "
							+ ", ID5 ='" + loginId5 +"' "
							+ ", JGH5 ='" + jgh5 +"' "
							+ ", RATE5 =" + rate5
							+ ", GKLX ='" + gklx +"' "
							+ ", FBKW ='" + fbkw +"' "
							+ ", FBFW ='" + fbfw +"' "
							+ ", KWJB ='" + kwjb +"' "
							+ ", KWJB2 ='" + kwjb2 +"' "
							+ ", NIAN ='" + nian +"' "
							+ ", JUAN ='" + juan +"' "
							+ ", QH ='" + qh +"' "
							+ ", YM ='" + ym +"' "
							+ ", LWYZ ='" + lwyz +"' "
							+ ", SFYW ='" + sfyw +"' "
							+ ", LWYYQK ='" + lwyyqk +"' "
							+ ", YJLX ='" + yjlx +"' "
							+ ", SFZZ ='" + sfzz +"' "
							+ ", ZZCC ='" + zzcc +"' "
							+ ", ZS ='" + zs +"' "
							+ ", DYZZDW ='" + dyzzdw +"' "
							+ ", FBSJ = TO_DATE('" +  fbsj +"','YYYY-MM-DD') "
							+ ", FBND ='" + fbnd +"' "
							+ ", YJLY ='" + yjly +"' "
							+ ", KTLY ='" + ktly +"' "
							+ ", ZZLX ='" + zzlx +"' "
							+ ", XGXKLY ='" + xgxkly +"' "
							+ ", KWLB ='" + kwlb +"' "
							+ ", LWYZ2 ='" + lwyz2 +"' "
							+ ", XKLY ='" + xkly +"' "
							+ ", ATTACH ='" + attach +"' "
							+ ", SFTXZZ ='" + sftxzz +"' "
					        + ", DATAYEAR ='" + datayear +"' " + ", AUTOADD =1 "
							+ ", STATUS =0 "
							+ ", FINALMARK =0 "
							+ ", COPYDATAID ='0:' "
							+ " where ID="+createBoId;
//					System.out.println("updateCreateBoSql="+updateCreateBoSql);
						DBSql.executeUpdate(updateCreateBoSql);
					}
					if(loginId3 != null && !loginId3.equals("")) {
						recordData.put("ID3", loginId3);
						recordData.put("JGH3", jgh3);
						
						queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId3+ "'";
						loginId3BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //绑定新增数据为该人员的bindId
						
						int createBoId = BOInstanceAPI.getInstance().createBOData("BO_XSLWTEMP", recordData, loginId3BindId, "admin");
						if(createBoId == -1) {
							createBoFailureNum = createBoFailureNum + 1;
						}
						copyDataIds = copyDataIds + createBoId + ":";
//					System.out.println("为 "+loginId3+" 共享数据：createBoId="+createBoId+", BINDID="+loginId3BindId);
						String updateCreateBoSql = "update BO_XSLWTEMP set "
							+ "	 LWMC ='" + lwmc +"' "
							+ ", LWYWMC ='" + lwywmc +"' "
							+ ", LWLX ='" + lwlx +"' "
							+ ", WCXS ='" + wcxs +"' "
							+ ", CHARGESF ='" + chargesf +"' "
							+ ", SSYX ='" + ssyx +"' "
							+ ", CHARGENM ='" + chargenm +"' "
							+ ", CHARGEID ='" + loginId +"' "
							+ ", JGH ='" + jgh +"' "
							+ ", CHARGERATE =" + chargerate
							+ ", NAME1 ='" + name1 +"' "
							+ ", ID1 ='" + loginId1 +"' "
							+ ", JGH1 ='" + jgh1 +"' "
							+ ", RATE1 =" + rate1
							+ ", NAME2 ='" + name2 +"' "
							+ ", ID2 ='" + loginId2 +"' "
							+ ", JGH2 ='" + jgh2 +"' "
							+ ", RATE2 =" + rate2
							+ ", NAME3 ='" + name3 +"' "
							+ ", ID3 ='" + loginId3 +"' "
							+ ", JGH3 ='" + jgh3 +"' "
							+ ", RATE3 =" + rate3
							+ ", NAME4 ='" + name4 +"' "
							+ ", ID4 ='" + loginId4 +"' "
							+ ", JGH4 ='" + jgh4 +"' "
							+ ", RATE4 =" + rate4
							+ ", NAME5 ='" + name5 +"' "
							+ ", ID5 ='" + loginId5 +"' "
							+ ", JGH5 ='" + jgh5 +"' "
							+ ", RATE5 =" + rate5
							+ ", GKLX ='" + gklx +"' "
							+ ", FBKW ='" + fbkw +"' "
							+ ", FBFW ='" + fbfw +"' "
							+ ", KWJB ='" + kwjb +"' "
							+ ", KWJB2 ='" + kwjb2 +"' "
							+ ", NIAN ='" + nian +"' "
							+ ", JUAN ='" + juan +"' "
							+ ", QH ='" + qh +"' "
							+ ", YM ='" + ym +"' "
							+ ", LWYZ ='" + lwyz +"' "
							+ ", SFYW ='" + sfyw +"' "
							+ ", LWYYQK ='" + lwyyqk +"' "
							+ ", YJLX ='" + yjlx +"' "
							+ ", SFZZ ='" + sfzz +"' "
							+ ", ZZCC ='" + zzcc +"' "
							+ ", ZS ='" + zs +"' "
							+ ", DYZZDW ='" + dyzzdw +"' "
							+ ", FBSJ = TO_DATE('" +  fbsj +"','YYYY-MM-DD') "
							+ ", FBND ='" + fbnd +"' "
							+ ", YJLY ='" + yjly +"' "
							+ ", KTLY ='" + ktly +"' "
							+ ", ZZLX ='" + zzlx +"' "
							+ ", XGXKLY ='" + xgxkly +"' "
							+ ", KWLB ='" + kwlb +"' "
							+ ", LWYZ2 ='" + lwyz2 +"' "
							+ ", XKLY ='" + xkly +"' "
							+ ", ATTACH ='" + attach +"' "
							+ ", SFTXZZ ='" + sftxzz +"' "
					        + ", DATAYEAR ='" + datayear +"' " + ", AUTOADD =1 "
							+ ", STATUS =0 "
							+ ", FINALMARK =0 "
							+ ", COPYDATAID ='0:' "
							+ " where ID="+createBoId;
//					System.out.println("updateCreateBoSql="+updateCreateBoSql);
						DBSql.executeUpdate(updateCreateBoSql);
					}
					if(loginId4 != null && !loginId4.equals("")) {
						recordData.put("ID4", loginId4);
						recordData.put("JGH4", jgh4);
						
						queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId4+ "'";
						loginId4BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //绑定新增数据为该人员的bindId
						
						int createBoId = BOInstanceAPI.getInstance().createBOData("BO_XSLWTEMP", recordData, loginId4BindId, "admin");
						if(createBoId == -1) {
							createBoFailureNum = createBoFailureNum + 1;
						}
						copyDataIds = copyDataIds + createBoId + ":";
//					System.out.println("为 "+loginId4+" 共享数据：createBoId="+createBoId+", BINDID="+loginId4BindId);
						String updateCreateBoSql = "update BO_XSLWTEMP set "
							+ "	 LWMC ='" + lwmc +"' "
							+ ", LWYWMC ='" + lwywmc +"' "
							+ ", LWLX ='" + lwlx +"' "
							+ ", WCXS ='" + wcxs +"' "
							+ ", CHARGESF ='" + chargesf +"' "
							+ ", SSYX ='" + ssyx +"' "
							+ ", CHARGENM ='" + chargenm +"' "
							+ ", CHARGEID ='" + loginId +"' "
							+ ", JGH ='" + jgh +"' "
							+ ", CHARGERATE =" + chargerate
							+ ", NAME1 ='" + name1 +"' "
							+ ", ID1 ='" + loginId1 +"' "
							+ ", JGH1 ='" + jgh1 +"' "
							+ ", RATE1 =" + rate1
							+ ", NAME2 ='" + name2 +"' "
							+ ", ID2 ='" + loginId2 +"' "
							+ ", JGH2 ='" + jgh2 +"' "
							+ ", RATE2 =" + rate2
							+ ", NAME3 ='" + name3 +"' "
							+ ", ID3 ='" + loginId3 +"' "
							+ ", JGH3 ='" + jgh3 +"' "
							+ ", RATE3 =" + rate3
							+ ", NAME4 ='" + name4 +"' "
							+ ", ID4 ='" + loginId4 +"' "
							+ ", JGH4 ='" + jgh4 +"' "
							+ ", RATE4 =" + rate4
							+ ", NAME5 ='" + name5 +"' "
							+ ", ID5 ='" + loginId5 +"' "
							+ ", JGH5 ='" + jgh5 +"' "
							+ ", RATE5 =" + rate5
							+ ", GKLX ='" + gklx +"' "
							+ ", FBKW ='" + fbkw +"' "
							+ ", FBFW ='" + fbfw +"' "
							+ ", KWJB ='" + kwjb +"' "
							+ ", KWJB2 ='" + kwjb2 +"' "
							+ ", NIAN ='" + nian +"' "
							+ ", JUAN ='" + juan +"' "
							+ ", QH ='" + qh +"' "
							+ ", YM ='" + ym +"' "
							+ ", LWYZ ='" + lwyz +"' "
							+ ", SFYW ='" + sfyw +"' "
							+ ", LWYYQK ='" + lwyyqk +"' "
							+ ", YJLX ='" + yjlx +"' "
							+ ", SFZZ ='" + sfzz +"' "
							+ ", ZZCC ='" + zzcc +"' "
							+ ", ZS ='" + zs +"' "
							+ ", DYZZDW ='" + dyzzdw +"' "
							+ ", FBSJ = TO_DATE('" +  fbsj +"','YYYY-MM-DD') "
							+ ", FBND ='" + fbnd +"' "
							+ ", YJLY ='" + yjly +"' "
							+ ", KTLY ='" + ktly +"' "
							+ ", ZZLX ='" + zzlx +"' "
							+ ", XGXKLY ='" + xgxkly +"' "
							+ ", KWLB ='" + kwlb +"' "
							+ ", LWYZ2 ='" + lwyz2 +"' "
							+ ", XKLY ='" + xkly +"' "
							+ ", ATTACH ='" + attach +"' "
							+ ", SFTXZZ ='" + sftxzz +"' "
					        + ", DATAYEAR ='" + datayear +"' " + ", AUTOADD =1 "
							+ ", STATUS =0 "
							+ ", FINALMARK =0 "
							+ ", COPYDATAID ='0:' "
							+ " where ID="+createBoId;
//					System.out.println("updateCreateBoSql="+updateCreateBoSql);
						DBSql.executeUpdate(updateCreateBoSql);
					}
					if(loginId5 != null && !loginId5.equals("")) {
						recordData.put("ID5", loginId5);
						recordData.put("JGH5", jgh5);
						
						queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId5+ "'";
						loginId5BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //绑定新增数据为该人员的bindId
						
						int createBoId = BOInstanceAPI.getInstance().createBOData("BO_XSLWTEMP", recordData, loginId5BindId, "admin");
						if(createBoId == -1) {
							createBoFailureNum = createBoFailureNum + 1;
						}
						copyDataIds = copyDataIds + createBoId + ":";
//					System.out.println("为 "+loginId5+" 共享数据：createBoId="+createBoId+", BINDID="+loginId5BindId);
						String updateCreateBoSql = "update BO_XSLWTEMP set "
							+ "	 LWMC ='" + lwmc +"' "
							+ ", LWYWMC ='" + lwywmc +"' "
							+ ", LWLX ='" + lwlx +"' "
							+ ", WCXS ='" + wcxs +"' "
							+ ", CHARGESF ='" + chargesf +"' "
							+ ", SSYX ='" + ssyx +"' "
							+ ", CHARGENM ='" + chargenm +"' "
							+ ", CHARGEID ='" + loginId +"' "
							+ ", JGH ='" + jgh +"' "
							+ ", CHARGERATE =" + chargerate
							+ ", NAME1 ='" + name1 +"' "
							+ ", ID1 ='" + loginId1 +"' "
							+ ", JGH1 ='" + jgh1 +"' "
							+ ", RATE1 =" + rate1
							+ ", NAME2 ='" + name2 +"' "
							+ ", ID2 ='" + loginId2 +"' "
							+ ", JGH2 ='" + jgh2 +"' "
							+ ", RATE2 =" + rate2
							+ ", NAME3 ='" + name3 +"' "
							+ ", ID3 ='" + loginId3 +"' "
							+ ", JGH3 ='" + jgh3 +"' "
							+ ", RATE3 =" + rate3
							+ ", NAME4 ='" + name4 +"' "
							+ ", ID4 ='" + loginId4 +"' "
							+ ", JGH4 ='" + jgh4 +"' "
							+ ", RATE4 =" + rate4
							+ ", NAME5 ='" + name5 +"' "
							+ ", ID5 ='" + loginId5 +"' "
							+ ", JGH5 ='" + jgh5 +"' "
							+ ", RATE5 =" + rate5
							+ ", GKLX ='" + gklx +"' "
							+ ", FBKW ='" + fbkw +"' "
							+ ", FBFW ='" + fbfw +"' "
							+ ", KWJB ='" + kwjb +"' "
							+ ", KWJB2 ='" + kwjb2 +"' "
							+ ", NIAN ='" + nian +"' "
							+ ", JUAN ='" + juan +"' "
							+ ", QH ='" + qh +"' "
							+ ", YM ='" + ym +"' "
							+ ", LWYZ ='" + lwyz +"' "
							+ ", SFYW ='" + sfyw +"' "
							+ ", LWYYQK ='" + lwyyqk +"' "
							+ ", YJLX ='" + yjlx +"' "
							+ ", SFZZ ='" + sfzz +"' "
							+ ", ZZCC ='" + zzcc +"' "
							+ ", ZS ='" + zs +"' "
							+ ", DYZZDW ='" + dyzzdw +"' "
							+ ", FBSJ = TO_DATE('" +  fbsj +"','YYYY-MM-DD') "
							+ ", FBND ='" + fbnd +"' "
							+ ", YJLY ='" + yjly +"' "
							+ ", KTLY ='" + ktly +"' "
							+ ", ZZLX ='" + zzlx +"' "
							+ ", XGXKLY ='" + xgxkly +"' "
							+ ", KWLB ='" + kwlb +"' "
							+ ", LWYZ2 ='" + lwyz2 +"' "
							+ ", XKLY ='" + xkly +"' "
							+ ", ATTACH ='" + attach +"' "
							+ ", SFTXZZ ='" + sftxzz +"' "
					        + ", DATAYEAR ='" + datayear +"' " + ", AUTOADD =1 "
							+ ", STATUS =0 "
							+ ", FINALMARK =0 "
							+ ", COPYDATAID ='0:' "
							+ " where ID="+createBoId;
//					System.out.println("updateCreateBoSql="+updateCreateBoSql);
						DBSql.executeUpdate(updateCreateBoSql);
					}
					
					String updateCopyDataIdSql = "update BO_XSLWTEMP set copyDataId ='" +copyDataIds +"' where ID="+dataId;
					DBSql.executeUpdate(updateCopyDataIdSql);
				}
			}
			
			
			String tip = "数据导入成功！共导入 "+totalData+" 条数据。";
			if(createBoFailureNum != 0) {
				tip = tip + "注意！数据导入成功。但是主持人和参与人之间数据共享未初始化成功。共有 " +createBoFailureNum+ " 条初始化失败。建议清空数据库后重新导入Excel！";
			}
			if(noChargeJghData != 0) {
				tip = tip + "注意！导入的数据中有 " +noChargeJghData+ " 条没有负责人信息，只有参与人信息。此类数据不可修改，不可审批。";
			}
			if(notExitUserNameList.size() != 0 && notExitUserNameList.size() != 1) {
				tip = tip + "注意！导入的数据中有 " +notExitUserNameList.size()+ " 条数据中的人员信息在基础库中不存在。";
				tip = tip + "人员如下：";
				for(int i=0; i<notExitUserNameList.size(); i++) {
					if(!notExitUserNameList.get(i).toString().equals("")) {
						tip = tip + notExitUserNameList.get(i).toString() + "; ";
					}
				}
				tip = tip.substring(0, tip.length()-1);
			}
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), tip, true);
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
