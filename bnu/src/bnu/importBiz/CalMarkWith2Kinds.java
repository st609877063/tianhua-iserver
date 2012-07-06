package bnu.importBiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.actionsoft.awf.util.DBSql;

//  ----教学项
//	工作经历_导入 BO_GZJLTEMP
//	人才培养_任课情况_导入 BO_RKQKTEMP
//	人才培养_指导学生_导入 BO_ZDXSTEMP

//	人才培养_指导专业_导入 BO_ZDZYTEMP
//	人才培养_教改项目_导入 BO_JGXMTEMP
//	人才培养_教学获奖_导入 BO_JXHJTEMP
//
//	----科研项
//	科研情况_科研项目_导入 BO_KYXMTEMP
//	科研情况_学术论文_导入 BO_XSLWTEMP
//	科研情况_学术著作_导入 BO_XSZZTEMP
//	科研情况_专利信息_导入 BO_ZLXXTEMP
//	科研情况_其他成果_导入 BO_QTCGTEMP
//	科研情况_成果获奖_导入 BO_CGHJTEMP
//	科研情况_研究报告_导入 BO_YJBGTEMP

//此方法为重新计算所有分值。之前的计算结果清空。循环遍历数据表重新计算。
public class CalMarkWith2Kinds {
	
	//tableNm:传入的项的数据库名， rateColumnNm：传入的倍数的字段名， weightNm：传入的权值倍数的字段名（接口，为以后留用）
	public void callMarkByType(String tableNm, String rateColumnNm, String weightNm) {
		
		List tableNmList = null;
		
		if(tableNm!=null && !tableNm.equals("") && !tableNm.equalsIgnoreCase("ALL")) {
			tableNmList = new ArrayList();
			tableNmList.add(tableNm);
		} else if(tableNm!=null && tableNm.equalsIgnoreCase("ALL")) {
			tableNmList = new ArrayList();
			tableNmList.add("BO_GZJLTEMP");
			tableNmList.add("BO_RKQKTEMP");
			tableNmList.add("BO_ZDXSTEMP");
			// tableNmList.add("BO_ZDZYTEMP");
			tableNmList.add("BO_JGXMTEMP");
			tableNmList.add("BO_JXHJTEMP");
			tableNmList.add("BO_KYXMTEMP");
			tableNmList.add("BO_XSLWTEMP");
			tableNmList.add("BO_XSZZTEMP");
			tableNmList.add("BO_ZLXXTEMP");
			tableNmList.add("BO_QTCGTEMP");
			tableNmList.add("BO_CGHJTEMP");
			tableNmList.add("BO_YJBGTEMP");
		}
		
		Map loginIdMap = new HashMap();
		loginIdMap.put("BO_GZJLTEMP", "CHARGEID");
		loginIdMap.put("BO_RKQKTEMP", "CHARGEID");
		loginIdMap.put("BO_ZDXSTEMP", "CHARGEID");
		loginIdMap.put("BO_ZDZYTEMP", "");
		loginIdMap.put("BO_JGXMTEMP", "ZCRID");
		loginIdMap.put("BO_JXHJTEMP", "FZRID");
		loginIdMap.put("BO_KYXMTEMP", "CHARGEID");
		loginIdMap.put("BO_XSLWTEMP", "CHARGEID");
		loginIdMap.put("BO_XSZZTEMP", "CHARGEID");
		loginIdMap.put("BO_ZLXXTEMP", "CHARGEID");
		loginIdMap.put("BO_QTCGTEMP", "CHARGEID");
		loginIdMap.put("BO_CGHJTEMP", "CHARGEID");
		loginIdMap.put("BO_YJBGTEMP", "CHARGEID");
		
		Map loginNmMap = new HashMap();
		loginNmMap.put("BO_GZJLTEMP", "CHARGENM");
		loginNmMap.put("BO_RKQKTEMP", "CHARGENM");
		loginNmMap.put("BO_ZDXSTEMP", "CHARGENM");
		loginNmMap.put("BO_ZDZYTEMP", "");
		loginNmMap.put("BO_JGXMTEMP", "ZCR");
		loginNmMap.put("BO_JXHJTEMP", "FZR");
		loginNmMap.put("BO_KYXMTEMP", "CHARGENM");
		loginNmMap.put("BO_XSLWTEMP", "CHARGENM");
		loginNmMap.put("BO_XSZZTEMP", "CHARGENM");
		loginNmMap.put("BO_ZLXXTEMP", "PATENTEE");
		loginNmMap.put("BO_QTCGTEMP", "CHARGENM");
		loginNmMap.put("BO_CGHJTEMP", "MAN");
		loginNmMap.put("BO_YJBGTEMP", "CHARGENM");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs3 = null;
		PreparedStatement ps4 = null;
		ResultSet rs4 = null;
		
		try {
			conn = DBSql.open();
			conn.setAutoCommit(false);
			
			String tableName = "";
			//第一层循环：按不同类型的数据表循环。
			for(int i=0; tableNmList!=null && i<tableNmList.size(); i++) {
				tableName = (String)tableNmList.get(i);
				String updateTableNm = "";
				if(tableName!=null && !tableName.equals("") && (tableName.indexOf("_")<tableName.length()-4)) {
					updateTableNm = tableName.substring(tableName.indexOf("_")+1, tableName.length()-4);
					updateTableNm = updateTableNm.toUpperCase();
				}
				
				//置空BO_FINALMARK表。
				String emptyBoFinalMarkSql = "UPDATE BO_FINALMARK SET ZFM = NVL(ZFM, 0) - NVL(" +updateTableNm+ ",0), "	+updateTableNm+" =0";
				DBSql.executeUpdate(emptyBoFinalMarkSql);
				
				
				String queryOptionSql = "select DISTINCT COLUMNCD  from BO_SELECTINFO where TABLECD='"+tableName+"'";
				ps3 = conn.prepareStatement(queryOptionSql);
				rs3 = ps3.executeQuery();
				String optionNm = "";
				//第二层循环。为标记分值项的循环。
				while(rs3.next()) {
					optionNm = rs3.getString("COLUMNCD");
					
					//收集选项-分值的对应关系Map
					String queryMap = "select OPTIONNM, WEIGHT from BO_SELECTINFO where TABLECD='"+tableName+"' and COLUMNCD='"+optionNm+"' ";
					ps4 = conn.prepareStatement(queryMap);
					rs4 = ps4.executeQuery();
					Map weightMap = null;
					while(rs4.next()) {
						weightMap = new HashMap();
						weightMap.put(rs4.getString("OPTIONNM"), rs4.getString("WEIGHT"));
					}
					
					if(weightMap != null) {
						String queryDatayearSql = "select DISTINCT DATAYEAR from "+tableName;
						ps = conn.prepareStatement(queryDatayearSql);
						rs = ps.executeQuery();
						String dataYear = "";
						//第三层循环。年度的循环。
						while(rs.next()) {
							dataYear = rs.getString("DATAYEAR");
							
							String queryTableSql = "select "+(String)loginIdMap.get(tableNm)+" as LOGINID, " 
									+(String)loginNmMap.get(tableNm)+" as LOGINNM, " 
									+optionNm + " as OPTIONVALUE "
									+rateColumnNm + " as RATE "
									+" from "+tableName+" where DATAYEAR='" +dataYear+ "' ";
							ps2 = conn.prepareStatement(queryTableSql);
							rs2 = ps2.executeQuery();
							while(rs2.next()) {
								String loginId = "";
								String loginNm = "";
								String optionValue = "";
								loginId = rs2.getString("LOGINID");
								loginNm = rs2.getString("LOGINNM");
								optionValue = rs2.getString("OPTIONVALUE");
								String optionMark = "";
								optionMark = (String)weightMap.get(optionValue);
								int optionMarkI = 0;
								optionMarkI = Integer.parseInt(optionMark);
								
								String rateStr = rs2.getString("RATE");
								int rate = 1;
								rate = Integer.parseInt(rateStr);
								
								int finalMark = optionMarkI * rate;
								
								CalMark calMarkUtil = new CalMark();
								calMarkUtil.setFinalMarkWithYear(loginNm, loginId, loginId, tableNm, finalMark, dataYear);
							}
						}
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
				DBSql.close(conn, ps, rs);
			} catch (Exception e) {
			}
		}
		
	}
	
}
