package bnu.importBiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;

import com.actionsoft.awf.organization.control.MessageQueue;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.loader.core.WorkFlowStepRTClassA;
import com.actionsoft.sdk.local.level0.BOInstanceAPI;
import com.actionsoft.sdk.local.level0.WorkflowInstanceAPI;

public class InitDataAfterExcel extends WorkFlowStepRTClassA {
	
	public InitDataAfterExcel(UserContext uc){
		super(uc);
		super.setDescription("初始化基本信息。导入Excel到副表BO_RSTEMP2后，为每条数据新建流程写入正式表BO_RSTEMP中");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}

	public boolean execute() {
		System.out.println(".....................初始化基本信息(导入副表BO_RSTEMP2，再入主表)begin..............");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int id = 0;
		String ZGH = "";   // 教工号
		String LOGINID = "";
		String XM= "";     // 姓名 
		String XB = "";   // 性别
		String CSNY = ""; // 出生年月
		String MZ = "";   // 民族
		String JG = ""; // 籍贯 
		String JGSHI = ""; // 籍贯（市）
		String JGOTHER = ""; // 籍贯（市）
		String CJGZSJ = ""; // 参加工作时间
		String RXGZSJ = ""; // 入校时间
		String BMMC = "";   // 所在单位
		String JSZW = "";   // 现职称
		String JSZW2 = "";   // 现职称2
		String JSZWRMSJ = "";   // 评职时间
		String GWLB = ""; // 岗位类别
		String GWLB2 = "";   // 岗位类别2
		String SSXK = "";   // 学科方向
		String SSXK2 = "";   // 学科方向2
		String PGJB = "";     // 聘位等级
		String DSLX = "";          // 导师类型
		String YHZZC = "";   // 学术专长
		String GJ = "";          // 国籍
		String GJ2 = ""; // 国籍2
		String ZGQK= "";     // 在岗情况
		String ZGQK2 = "";   // 在岗位置2
		String ZJLX = "";   // 证件类型
		String ZJLX2 = "";     // 证件类型2
		String SFZHM = ""; // 证件号码
		String HKSZDCS = "";   // 户口所在地
		String HYZK = ""; // 婚姻状况
		String XZZ = ""; // 家庭住址
		String SJ = ""; // 移动电话
		String BGSDH = "";   // 办公电话
		String EMAIL = "";   // 电子邮箱
		String ZGXW = "";   // 最终学位
		String ZGXW2 = "";          // 最后学位2
		String ZGXW3 = "";          // 最后学位3
		String ZGXWHDSJ = "";   // 毕业时间
		String WYSP = ""; // 外语水平
		String WYYZ = "";   // 外语语种
		String WYYZ2 = "";   // 外语语种2
		String ZGXL = "";     // 最后学历
		int LXGL = 0;          // 连续工龄（年）
		String ZZMM = "";   // 政治面貌
		String RDSJ = "";          // 入党时间
		String DZZ= "";     // 党总支/分党委/.. 
		String DZZ2 = "";   // 下一级党组织
		String DZB = "";   // 党支部
		int DFJS = 0; // 党费基数
		String DNZW = "";   // 党内职务
		String XZZW = ""; // 职务名称 
		String XZZWJB = ""; // 职务级别
		
		String YJLY = "";
		String ZYJZ = ""; 
		String BGDD = "";
		String BGSJ = ""; 
		String CZ = "";
		String TXDZ = ""; 
		String ZYWZ = "";
		
		String BYXX = "";
		String XXLB = "";
		String XSJG = "";
		
		String message="";
		
		try{
			conn = DBSql.open();
			conn.setAutoCommit(true);

			String sql = "select * from BO_RSTEMP2";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			int insertNum = 0;
			int updateNum = 0;
			
			while(rs.next()){
				id = rs.getInt("ID");
				
				ZGH = rs.getString("ZGH")==null?"":rs.getString("ZGH").trim(); //教工号
				LOGINID = rs.getString("LOGINID")==null?"":rs.getString("LOGINID").trim();
				XM = rs.getString("XM")==null?"":rs.getString("XM").trim(); //姓名
				XB = rs.getString("XB")==null?"":rs.getString("XB").trim(); //性别
				CSNY = rs.getString("CSNY")==null?"":rs.getString("CSNY").trim(); //出生年月
				MZ = rs.getString("MZ")==null?"":rs.getString("MZ").trim(); //民族
				JG = rs.getString("JG")==null?"":rs.getString("JG").trim(); //籍贯
				JGSHI = rs.getString("JGSHI")==null?"":rs.getString("JGSHI").trim(); //籍贯（市）
				JGOTHER = rs.getString("JGOTHER")==null?"":rs.getString("JGOTHER").trim();
				CJGZSJ = rs.getString("CJGZSJ")==null?"":rs.getString("CJGZSJ").trim(); //参加工作时间
				RXGZSJ = rs.getString("RXGZSJ")==null?"":rs.getString("RXGZSJ").trim(); //入校时间
				BMMC = rs.getString("BMMC")==null?"":rs.getString("BMMC").trim(); //所在单位
				JSZW = rs.getString("JSZW")==null?"":rs.getString("JSZW").trim(); //现职称
				JSZW2 = rs.getString("JSZW2")==null?"":rs.getString("JSZW2").trim(); //现职称2
				JSZWRMSJ = rs.getString("JSZWRMSJ")==null?"":rs.getString("JSZWRMSJ").trim(); //评职时间
				GWLB = rs.getString("GWLB")==null?"":rs.getString("GWLB").trim(); //岗位类别
				GWLB2 = rs.getString("GWLB2")==null?"":rs.getString("GWLB2").trim(); //岗位类别2
				SSXK = rs.getString("SSXK")==null?"":rs.getString("SSXK").trim(); //学科方向
				SSXK2 = rs.getString("SSXK2")==null?"":rs.getString("SSXK2").trim(); //学科方向2 
				PGJB = rs.getString("PGJB")==null?"":rs.getString("PGJB").trim(); //聘位等级
				DSLX = rs.getString("DSLX")==null?"":rs.getString("DSLX").trim(); //导师类型
				YHZZC = rs.getString("YHZZC")==null?"":rs.getString("YHZZC").trim(); //学术专长 
				GJ = rs.getString("GJ")==null?"":rs.getString("GJ").trim(); //国籍
				GJ2 = rs.getString("GJ2")==null?"":rs.getString("GJ2").trim(); //国籍2
				ZGQK = rs.getString("ZGQK")==null?"":rs.getString("ZGQK").trim(); //在岗情况 
				ZGQK2 = rs.getString("ZGQK2")==null?"":rs.getString("ZGQK2").trim(); //在岗位置2 
				ZJLX = rs.getString("ZJLX")==null?"":rs.getString("ZJLX").trim(); //证件类型
				ZJLX2 = rs.getString("ZJLX2")==null?"":rs.getString("ZJLX2").trim(); //证件类型2
				SFZHM = rs.getString("SFZHM")==null?"":rs.getString("SFZHM").trim(); //证件号码 
				HKSZDCS = rs.getString("HKSZDCS")==null?"":rs.getString("HKSZDCS").trim(); //户口所在地
				HYZK = rs.getString("HYZK")==null?"":rs.getString("HYZK").trim(); //婚姻状况 
				XZZ = rs.getString("XZZ")==null?"":rs.getString("XZZ").trim(); //家庭住址
				SJ = rs.getString("SJ")==null?"":rs.getString("SJ").trim(); //移动电话
				BGSDH = rs.getString("BGSDH")==null?"":rs.getString("BGSDH").trim(); //办公电话
				EMAIL = rs.getString("EMAIL")==null?"":rs.getString("EMAIL").trim(); //电子邮箱
				ZGXW = rs.getString("ZGXW")==null?"":rs.getString("ZGXW").trim(); //最终学位
				ZGXW2 = rs.getString("ZGXW2")==null?"":rs.getString("ZGXW2").trim(); //最后学位2 
				ZGXW3 = rs.getString("ZGXW3")==null?"":rs.getString("ZGXW3").trim(); //最后学位3
				ZGXWHDSJ = rs.getString("ZGXWHDSJ")==null?"":rs.getString("ZGXWHDSJ").trim(); //毕业时间
				WYSP = rs.getString("WYSP")==null?"":rs.getString("WYSP").trim(); //外语水平
				WYYZ = rs.getString("WYYZ")==null?"":rs.getString("WYYZ").trim(); //外语语种
				WYYZ2 = rs.getString("WYYZ2")==null?"":rs.getString("WYYZ2").trim(); //外语语种2 
				ZGXL = rs.getString("ZGXL")==null?"":rs.getString("ZGXL").trim(); //最后学历
				LXGL = rs.getInt("LXGL"); //连续工龄（年）
				ZZMM = rs.getString("ZZMM")==null?"":rs.getString("ZZMM").trim(); //政治面貌
				RDSJ = rs.getString("RDSJ")==null?"":rs.getString("RDSJ").trim(); //入党时间 
				DZZ = rs.getString("DZZ")==null?"":rs.getString("DZZ").trim(); //党总支/分党委/..
				DZZ2 = rs.getString("DZZ2")==null?"":rs.getString("DZZ2").trim(); //下一级党组织
				DZB = rs.getString("DZB")==null?"":rs.getString("DZB").trim(); //党支部 
				DFJS = rs.getInt("DFJS"); //党费基数
				DNZW = rs.getString("DNZW")==null?"":rs.getString("DNZW").trim(); //党内职务 
				XZZW = rs.getString("XZZW")==null?"":rs.getString("XZZW").trim(); //职务名称
				XZZWJB = rs.getString("XZZWJB")==null?"":rs.getString("XZZWJB").trim(); //职务级别 
				
				YJLY = rs.getString("YJLY")==null?"":rs.getString("YJLY").trim();
				ZYJZ = rs.getString("ZYJZ")==null?"":rs.getString("ZYJZ").trim();
				BGDD = rs.getString("BGDD")==null?"":rs.getString("BGDD").trim();
				BGSJ = rs.getString("BGSJ")==null?"":rs.getString("BGSJ").trim();
				CZ = rs.getString("CZ")==null?"":rs.getString("CZ").trim();
				TXDZ = rs.getString("TXDZ")==null?"":rs.getString("TXDZ").trim();
				ZYWZ = rs.getString("ZYWZ")==null?"":rs.getString("ZYWZ").trim();
				
				BYXX = rs.getString("BYXX")==null?"":rs.getString("BYXX").trim();
				XXLB = rs.getString("XXLB")==null?"":rs.getString("XXLB").trim();
				XSJG = rs.getString("XSJG")==null?"":rs.getString("XSJG").trim();
				   
				if(CSNY!=null && CSNY.length()>10) {
					CSNY = CSNY.substring(0,10);
				}
				if(CJGZSJ!=null && CJGZSJ.length()>10) {
					CJGZSJ = CJGZSJ.substring(0,10);
				}
				if(RXGZSJ!=null && RXGZSJ.length()>10) {
					RXGZSJ = RXGZSJ.substring(0,10);
				}
				if(JSZWRMSJ!=null && JSZWRMSJ.length()>10) {
					JSZWRMSJ = JSZWRMSJ.substring(0,10);
				}
				
				int checkId = 0;
				String checkSql = "select ID FROM BO_RSTEMP WHERE LOGINID = '"+LOGINID + "' and ZGH='" +ZGH+ "'";
				checkId = DBSql.getInt(conn, checkSql, "ID");
				if(checkId == 0) { //新增
					Hashtable recordData=new Hashtable();
					recordData.put("ZGH", ZGH);
					recordData.put("LOGINID", LOGINID);
					recordData.put("XM", XM);
					int boId = BOInstanceAPI.getInstance().createBOData("BO_RSTEMP", recordData, "admin");
					int boInstanceId = WorkflowInstanceAPI.getInstance().createProcessInstance("007b23151fb9069a05939e4ad301bcd6", "admin", "基本信息初始化");
					//007b23151fb9069a05939e4ad301bcd6  ===>  基本信息填报流程
//					System.out.println("新增数据：BINDID = "+boInstanceId+", ID="+boId);
					String updateInstanceSql = "update BO_RSTEMP set ZGH ='" +ZGH +"' "
							+ ", LOGINID='" + LOGINID +"' "
							+ ", XM='" + XM +"' "
							+ ", XB='" + XB +"' "
							+ ", CSNY = TO_DATE('" +  CSNY +"','YYYY-MM-DD') " 
							+ ", MZ='" + MZ +"' "
							+ ", JG='" + JG +"' "
							+ ", JGSHI='" + JGSHI +"' "
							+ ", JGOTHER='" + JGOTHER +"' "
							+ ", CJGZSJ = TO_DATE('" +  CJGZSJ +"','YYYY-MM-DD') " 
							+ ", RXGZSJ = TO_DATE('" +  RXGZSJ +"','YYYY-MM-DD') " 
							+ ", BMMC='" + BMMC +"' "
							+ ", JSZW='" + JSZW +"' "
							+ ", JSZW2='" + JSZW2 +"' "
							+ ", JSZWRMSJ = TO_DATE('" +  JSZWRMSJ +"','YYYY-MM-DD') " 
							+ ", GWLB='" + GWLB +"' "
							+ ", GWLB2='" + GWLB2 +"' "
							+ ", SSXK='" + SSXK +"' "
							+ ", SSXK2='" + SSXK2 +"' "
							+ ", PGJB='" + PGJB +"' "
							+ ", DSLX='" + DSLX +"' "
							+ ", YHZZC='" + YHZZC +"' "
							+ ", GJ='" + GJ +"' "
							+ ", GJ2='" + GJ2 +"' "
							+ ", ZGQK='" + ZGQK +"' "
							+ ", ZGQK2='" + ZGQK2 +"' "
							+ ", ZJLX='" + ZJLX +"' "
							+ ", ZJLX2='" + ZJLX2 +"' "
							+ ", SFZHM='" + SFZHM +"' "
							+ ", HKSZDCS='" + HKSZDCS +"' "
							+ ", HYZK='" + HYZK +"' "
							+ ", XZZ='" + XZZ +"' "
							+ ", SJ='" + SJ +"' "
							+ ", BGSDH='" + BGSDH +"' "
							+ ", EMAIL='" + EMAIL +"' "
							+ ", ZGXW='" + ZGXW +"' "
							+ ", ZGXW2='" + ZGXW2 +"' "
							+ ", ZGXW3='" + ZGXW3 +"' "
							+ ", ZGXWHDSJ='" + ZGXWHDSJ +"' "
							+ ", WYSP='" + WYSP +"' "
							+ ", WYYZ='" + WYYZ +"' "
							+ ", WYYZ2='" + WYYZ2 +"' "
							+ ", ZGXL='" + ZGXL +"' "
							+ ", LXGL=" + LXGL
							+ ", ZZMM='" + ZZMM +"' "
							+ ", RDSJ='" + RDSJ +"' "
							+ ", DZZ='" + DZZ +"' "
							+ ", DZZ2='" + DZZ2 +"' "
							+ ", DZB='" + DZB +"' "
							+ ", DFJS=" + DFJS
							+ ", DNZW='" + DNZW +"' "
							+ ", XZZW='" + XZZW +"' "
							+ ", XZZWJB='" + XZZWJB +"' "
							+ ", YJLY='" + YJLY +"' "
							+ ", ZYJZ='" + ZYJZ +"' "
							+ ", BGDD='" + BGDD +"' "
							+ ", BGSJ='" + BGSJ +"' "
							+ ", CZ='" + CZ +"' "
							+ ", TXDZ='" + TXDZ +"' "
							+ ", ZYWZ='" + ZYWZ +"' "
							+ ", BYXX='" + BYXX +"' "
							+ ", XXLB='" + XXLB +"' "
							+ ", XSJG='" + XSJG +"' "
							+ ", BINDID=" + boInstanceId
							+ " where ID="+boId;
					
//					System.out.println("updateInstanceSql="+updateInstanceSql);
					insertNum++;
					DBSql.executeUpdate(conn, updateInstanceSql);
				
				} else if(checkId != 0) { //修改
					String updateSql = "update BO_RSTEMP set ZGH ='" +ZGH +"' "
										+ ", LOGINID='" + LOGINID +"' "
										+ ", XM='" + XM +"' "
										+ ", XB='" + XB +"' "
										+ ", CSNY = TO_DATE('" +  CSNY +"','YYYY-MM-DD') " 
										+ ", MZ='" + MZ +"' "
										+ ", JG='" + JG +"' "
										+ ", JGSHI='" + JGSHI +"' "
										+ ", JGOTHER='" + JGOTHER +"' "
										+ ", CJGZSJ = TO_DATE('" +  CJGZSJ +"','YYYY-MM-DD') " 
										+ ", RXGZSJ = TO_DATE('" +  RXGZSJ +"','YYYY-MM-DD') " 
										+ ", BMMC='" + BMMC +"' "
										+ ", JSZW='" + JSZW +"' "
										+ ", JSZW2='" + JSZW2 +"' "
										+ ", JSZWRMSJ = TO_DATE('" +  JSZWRMSJ +"','YYYY-MM-DD') " 
										+ ", GWLB='" + GWLB +"' "
										+ ", GWLB2='" + GWLB2 +"' "
										+ ", SSXK='" + SSXK +"' "
										+ ", SSXK2='" + SSXK2 +"' "
										+ ", PGJB='" + PGJB +"' "
										+ ", DSLX='" + DSLX +"' "
										+ ", YHZZC='" + YHZZC +"' "
										+ ", GJ='" + GJ +"' "
										+ ", GJ2='" + GJ2 +"' "
										+ ", ZGQK='" + ZGQK +"' "
										+ ", ZGQK2='" + ZGQK2 +"' "
										+ ", ZJLX='" + ZJLX +"' "
										+ ", ZJLX2='" + ZJLX2 +"' "
										+ ", SFZHM='" + SFZHM +"' "
										+ ", HKSZDCS='" + HKSZDCS +"' "
										+ ", HYZK='" + HYZK +"' "
										+ ", XZZ='" + XZZ +"' "
										+ ", SJ='" + SJ +"' "
										+ ", BGSDH='" + BGSDH +"' "
										+ ", EMAIL='" + EMAIL +"' "
										+ ", ZGXW='" + ZGXW +"' "
										+ ", ZGXW2='" + ZGXW2 +"' "
										+ ", ZGXW3='" + ZGXW3 +"' "
										+ ", ZGXWHDSJ='" + ZGXWHDSJ +"' "
										+ ", WYSP='" + WYSP +"' "
										+ ", WYYZ='" + WYYZ +"' "
										+ ", WYYZ2='" + WYYZ2 +"' "
										+ ", ZGXL='" + ZGXL +"' "
										+ ", LXGL=" + LXGL
										+ ", ZZMM='" + ZZMM +"' "
										+ ", RDSJ='" + RDSJ +"' "
										+ ", DZZ='" + DZZ +"' "
										+ ", DZZ2='" + DZZ2 +"' "
										+ ", DZB='" + DZB +"' "
										+ ", DFJS=" + DFJS
										+ ", DNZW='" + DNZW +"' "
										+ ", XZZW='" + XZZW +"' "
										+ ", XZZWJB='" + XZZWJB +"' "
										+ ", YJLY='" + YJLY +"' "
										+ ", ZYJZ='" + ZYJZ +"' "
										+ ", BGDD='" + BGDD +"' "
										+ ", BGSJ='" + BGSJ +"' "
										+ ", CZ='" + CZ +"' "
										+ ", TXDZ='" + TXDZ +"' "
										+ ", ZYWZ='" + ZYWZ +"' "
										+ ", BYXX='" + BYXX +"' "
										+ ", XXLB='" + XXLB +"' "
										+ ", XSJG='" + XSJG +"' "
										+ " where ID="+checkId;
					
//					System.out.println("修改数据：ID = "+checkId);
					updateNum++;
					DBSql.executeUpdate(conn, updateSql);
				}
				
			}
			
			if(insertNum != 0) {
				message = "人员信息更新成功！新增 "+insertNum+" 人员信息。";
			} else {
				message = "人员信息更新成功！";
			}
			
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), message, true);
		}catch(Exception e){
			e.printStackTrace(System.err);
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "警告，基本信息初始化失败！请重新导入Excel", true);
			return false;
		}finally{
			DBSql.close(conn, ps, rs);
		}
		
		return true;
	}
	
}
