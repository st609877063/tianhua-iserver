package bnu.importBiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import com.actionsoft.awf.util.DBSql;
import com.actionsoft.sdk.AWSSDKException;
import com.actionsoft.sdk.local.level0.BOInstanceAPI;

public class CalMark {
	
	public int getCalMark(String tableNm, int dataId) {
		int finalMark = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBSql.open();
			conn.setAutoCommit(false);

			String sql = "select b.field_name as optionNm from SYS_BUSINESS_METADATA  a, SYS_BUSINESS_METADATA_MAP b " +
				" where b.metadata_id = a.id " +
				" and a.group_name = '基本信息统计' " +
				" and b.display_type = '列表'  " +
				" and a.entity_name='"+tableNm+"'";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String optionNmTemp = rs.getString("optionNm")==null ? "" : rs.getString("optionNm"); //为“列表”的项
				//获得用户输入的值
				String querySql = "select "+optionNmTemp+" as optionValue from "+tableNm+" where id="+dataId;
				String optionValue = DBSql.getString(conn, querySql, "optionValue");
				
				
				String weightSql = "select weight from BO_SELECTINFO " +
				" where TABLECD='" +tableNm+ "' AND COLUMNCD='" +optionNmTemp + "' " +
				" AND OPTIONNM='" +optionValue+ "'";
				
				int weight = DBSql.getInt(weightSql, "weight");
				
				int time = 1;
				String timeStr = "1";
				//针对“工作经历”, “人才培养_任课情况”, “人才培养_指导学生” 三类特殊处理。有倍数。
				if(tableNm.equals("BO_GZJLTEMP")) {
					String queryTimeSql = "select RQ as TIME from "+tableNm+" where id="+dataId;
					timeStr = DBSql.getString(conn, queryTimeSql, "TIME");
				} else if(tableNm.equals("BO_RKQKTEMP")) {
					String queryTimeSql = "select ZXS as TIME from "+tableNm+" where id="+dataId;
					timeStr = DBSql.getString(conn, queryTimeSql, "TIME");
				} else if(tableNm.equals("BO_ZDXSTEMP")) {
					String queryTimeSql = "select TIME as TIME from "+tableNm+" where id="+dataId;
					timeStr = DBSql.getString(conn, queryTimeSql, "TIME");
				}
				if(timeStr!=null && !timeStr.equals("")) {
					time = Integer.parseInt(timeStr);
				}
				finalMark = finalMark + weight*time;
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
		
		return finalMark;
	}

	
	public void setFinalMark(String name, String loginId, String jgh, String tableNm, int mark) {
		String updateTableNm = "";
		if(tableNm!=null && !tableNm.equals("") && (tableNm.indexOf("_")<tableNm.length()-4)) {
			updateTableNm = tableNm.substring(tableNm.indexOf("_")+1, tableNm.length()-4);
			updateTableNm = updateTableNm.toUpperCase();
		}
		
		//excel导入时，jgh不为空，name不为空。loginId可能为空
		//当通过AWS流程时，loginId不为空,jgh, name可能为空。
		
		String querySql = "";
		if(!jgh.equals("") && !loginId.equals("") && !name.equals("")) {
			querySql = "SELECT ID from BO_FINALMARK WHERE JGH='" +jgh+ "'";
		} else if(!jgh.equals("")) {
			querySql = "SELECT ID from BO_FINALMARK WHERE JGH='" +jgh+ "'";
			
			String queryLoginId = "SELECT LOGINID FROM BO_RSTEMP where  ZGH='" +jgh+ "'";
			String queryLoginIdResult = DBSql.getString(queryLoginId, "LOGINID").trim();
			if(queryLoginIdResult != null && !queryLoginIdResult.equals("")) {
				loginId = queryLoginIdResult;
			}
			
			String queryName = "SELECT XM FROM BO_RSTEMP where  ZGH='" +jgh+ "'";
			String queryNameResult = DBSql.getString(queryName, "XM").trim();
			if(queryNameResult != null && !queryNameResult.equals("")) {
				name = queryNameResult;
			}
		}else if(!loginId.equals("")) {
			querySql = "SELECT ID from BO_FINALMARK WHERE LOGINID='" +loginId+ "'";
			
			String queryJgh = "SELECT ZGH FROM BO_RSTEMP where  LOGINID='" +loginId+ "'";
			String queryJghReuslt = DBSql.getString(queryJgh, "ZGH").trim();
			if(queryJghReuslt != null && !queryJghReuslt.equals("")) {
				jgh = queryJghReuslt;
			}
			
			String queryName = "SELECT XM FROM BO_RSTEMP where  loginid='" +loginId+ "'";
			String queryNameResult = DBSql.getString(queryName, "XM").trim();
			if(queryNameResult != null && !queryNameResult.equals("")) {
				name = queryNameResult;
			}
		}
		
		int dataId = 0;
		dataId = DBSql.getInt(querySql, "ID");
		String updateSql = "";
		if(dataId == 0 && !name.equals("") && !loginId.equals("") && !jgh.equals("")) {
			Hashtable recordData = new Hashtable();
			recordData.put("NAME", name);
			recordData.put("LOGINID", loginId);
			recordData.put("JGH", jgh);
			recordData.put(updateTableNm, mark);
			recordData.put("ZFM", mark);
			//如果“工作经历”, “人才培养_任课情况”, “人才培养_指导学生” 三类特殊处理。叠加JXZFM（教学总分值）
			if(tableNm.equalsIgnoreCase("BO_GZJLTEMP")) {
				recordData.put("JXZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_RKQKTEMP")) {
				recordData.put("JXZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_ZDXSTEMP")) {
				recordData.put("JXZFM", mark);
			}
			//叠加KYZFM（科研总分值） XSLW 科研情况_学术论文; XSZZ 科研情况_学术著作;	KYXM 科研情况_科研项目; YJBG科研情况_研究报告;CGHJ 科研情况_科研获奖;ZLXX 科研情况_专利信息;QTCG 科研情况_其他成果
			if(tableNm.equalsIgnoreCase("BO_XSLWTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_XSZZTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_KYXMTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_YJBGTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_CGHJTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_ZLXXTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_QTCGTEMP")) {
				recordData.put("KYZFM", mark);
			}
			
			
			try {
				int createBoId = BOInstanceAPI.getInstance().createBOData("BO_FINALMARK", recordData, "admin");
				//System.out.println("setFinalMark______________for"+name+",createBoId = "+createBoId);
			} catch (AWSSDKException e) {
				e.printStackTrace();
			}
			
		} else if(dataId != 0) {
			updateSql = "UPDATE BO_FINALMARK SET NAME='" +name+ "' " +
						" , LOGINID='" +loginId+ "' " +
						" , JGH='" +jgh+ "' " +
						" , "+updateTableNm+" =NVL(" +updateTableNm+ ",0) + "+mark+ 
						" , ZFM = NVL(ZFM, 0) + " + mark;
			
			//如果“工作经历”, “人才培养_任课情况”, “人才培养_指导学生” 三类特殊处理。叠加JXZFM（教学总分值）
			if(tableNm.equals("BO_GZJLTEMP")) {
				updateSql = updateSql + " , JXZFM = NVL(JXZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equals("BO_RKQKTEMP")) {
				updateSql = updateSql + " , JXZFM = NVL(JXZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equals("BO_ZDXSTEMP")) {
				updateSql = updateSql + " , JXZFM = NVL(JXZFM, 0) + " + mark + " where ID="+dataId;
			} 
			
			//叠加KYZFM（科研总分值） XSLW 科研情况_学术论文; XSZZ 科研情况_学术著作;	KYXM 科研情况_科研项目; YJBG科研情况_研究报告;CGHJ 科研情况_科研获奖;ZLXX 科研情况_专利信息;QTCG 科研情况_其他成果
			else if(tableNm.equalsIgnoreCase("BO_XSLWTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_XSZZTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_KYXMTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_YJBGTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_CGHJTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_ZLXXTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_QTCGTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			}
			else {
				updateSql = updateSql + " where ID="+dataId;
			}
			DBSql.executeUpdate(updateSql);
		}
	}

	public void setFinalMark(String name, String loginId, String jgh, String tableNm, float mark) {
		String updateTableNm = "";
		if(tableNm!=null && !tableNm.equals("") && (tableNm.indexOf("_")<tableNm.length()-4)) {
			updateTableNm = tableNm.substring(tableNm.indexOf("_")+1, tableNm.length()-4);
			updateTableNm = updateTableNm.toUpperCase();
		}
		
		//excel导入时，jgh不为空，name不为空。loginId可能为空
		//当通过AWS流程时，loginId不为空,jgh, name可能为空。
		
		String querySql = "";
		if(!jgh.equals("") && !loginId.equals("") && !name.equals("")) {
			querySql = "SELECT ID from BO_FINALMARK WHERE JGH='" +jgh+ "'";
		} else if(!jgh.equals("")) {
			querySql = "SELECT ID from BO_FINALMARK WHERE JGH='" +jgh+ "'";
			
			String queryLoginId = "SELECT LOGINID FROM BO_RSTEMP where  ZGH='" +jgh+ "'";
			String queryLoginIdResult = DBSql.getString(queryLoginId, "LOGINID").trim();
			if(queryLoginIdResult != null && !queryLoginIdResult.equals("")) {
				loginId = queryLoginIdResult;
			}
			
			String queryName = "SELECT XM FROM BO_RSTEMP where  ZGH='" +jgh+ "'";
			String queryNameResult = DBSql.getString(queryName, "XM").trim();
			if(queryNameResult != null && !queryNameResult.equals("")) {
				name = queryNameResult;
			}
		}else if(!loginId.equals("")) {
			querySql = "SELECT ID from BO_FINALMARK WHERE LOGINID='" +loginId+ "'";
			
			String queryJgh = "SELECT ZGH FROM BO_RSTEMP where  LOGINID='" +loginId+ "'";
			String queryJghReuslt = DBSql.getString(queryJgh, "ZGH").trim();
			if(queryJghReuslt != null && !queryJghReuslt.equals("")) {
				jgh = queryJghReuslt;
			}
			
			String queryName = "SELECT XM FROM BO_RSTEMP where  loginid='" +loginId+ "'";
			String queryNameResult = DBSql.getString(queryName, "XM").trim();
			if(queryNameResult != null && !queryNameResult.equals("")) {
				name = queryNameResult;
			}
		}
		
		int dataId = 0;
		dataId = DBSql.getInt(querySql, "ID");
		String updateSql = "";
		if(dataId == 0 && !name.equals("") && !loginId.equals("") && !jgh.equals("")) {
			Hashtable recordData = new Hashtable();
			recordData.put("NAME", name);
			recordData.put("LOGINID", loginId);
			recordData.put("JGH", jgh);
			recordData.put(updateTableNm, mark);
			recordData.put("ZFM", mark);
			//如果“工作经历”, “人才培养_任课情况”, “人才培养_指导学生” 三类特殊处理。叠加JXZFM（教学总分值）
			if(tableNm.equalsIgnoreCase("BO_GZJLTEMP")) {
				recordData.put("JXZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_RKQKTEMP")) {
				recordData.put("JXZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_ZDXSTEMP")) {
				recordData.put("JXZFM", mark);
			}
			//叠加KYZFM（科研总分值） XSLW 科研情况_学术论文; XSZZ 科研情况_学术著作;	KYXM 科研情况_科研项目; YJBG科研情况_研究报告;CGHJ 科研情况_科研获奖;ZLXX 科研情况_专利信息;QTCG 科研情况_其他成果
			if(tableNm.equalsIgnoreCase("BO_XSLWTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_XSZZTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_KYXMTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_YJBGTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_CGHJTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_ZLXXTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_QTCGTEMP")) {
				recordData.put("KYZFM", mark);
			}
			
			try {
				int createBoId = BOInstanceAPI.getInstance().createBOData("BO_FINALMARK", recordData, "admin");
				//System.out.println("setFinalMark______________for"+name+",createBoId = "+createBoId);
			} catch (AWSSDKException e) {
				e.printStackTrace();
			}
			
		} else if(dataId != 0) {
			updateSql = "UPDATE BO_FINALMARK SET NAME='" +name+ "' " +
						" , LOGINID='" +loginId+ "' " +
						" , JGH='" +jgh+ "' " +
						" , "+updateTableNm+" =NVL(" +updateTableNm+ ",0) + "+mark+ 
						" , ZFM = NVL(ZFM, 0) + " + mark;
			
			//如果“工作经历”, “人才培养_任课情况”, “人才培养_指导学生” 三类特殊处理。叠加JXZFM（教学总分值）
			if(tableNm.equals("BO_GZJLTEMP")) {
				updateSql = updateSql + " , JXZFM = NVL(JXZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equals("BO_RKQKTEMP")) {
				updateSql = updateSql + " , JXZFM = NVL(JXZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equals("BO_ZDXSTEMP")) {
				updateSql = updateSql + " , JXZFM = NVL(JXZFM, 0) + " + mark + " where ID="+dataId;
			} 
			//叠加KYZFM（科研总分值） XSLW 科研情况_学术论文; XSZZ 科研情况_学术著作;	KYXM 科研情况_科研项目; YJBG科研情况_研究报告;CGHJ 科研情况_科研获奖;ZLXX 科研情况_专利信息;QTCG 科研情况_其他成果
			else if(tableNm.equalsIgnoreCase("BO_XSLWTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_XSZZTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_KYXMTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_YJBGTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_CGHJTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_ZLXXTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_QTCGTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			}
			else {
				updateSql = updateSql + " where ID="+dataId;
			}
			
			DBSql.executeUpdate(updateSql);
		}
	}

	
	public void reduceFinalMark(String loginId, String tableCd, int finalMark) {
		String updateSql = "UPDATE BO_FINALMARK SET "+tableCd+" =NVL(" +tableCd+ ",0) - "+finalMark+ 
			" , ZFM = NVL(ZFM, 0) - " + finalMark;
		
		//如果“工作经历”, “人才培养_任课情况”, “人才培养_指导学生” 三类特殊处理。叠加JXZFM（教学总分值）
		if(tableCd.equalsIgnoreCase("GZJL")) {
			updateSql = updateSql + " , JXZFM = NVL(JXZFM, 0) - " + finalMark + "  where LOGINID='"+loginId+"'";
		} else if(tableCd.equalsIgnoreCase("RKQK")) {
			updateSql = updateSql + " , JXZFM = NVL(JXZFM, 0) - " + finalMark + "  where LOGINID='"+loginId+"'";
		} else if(tableCd.equalsIgnoreCase("ZDXS")) {
			updateSql = updateSql + " , JXZFM = NVL(JXZFM, 0) - " + finalMark + "  where LOGINID='"+loginId+"'";
		} 
		//叠加KYZFM（科研总分值） XSLW 科研情况_学术论文; XSZZ 科研情况_学术著作;	KYXM 科研情况_科研项目; YJBG科研情况_研究报告;CGHJ 科研情况_科研获奖;ZLXX 科研情况_专利信息;QTCG 科研情况_其他成果
		else if(tableCd.equalsIgnoreCase("XSLW")) {
			updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + finalMark + "  where LOGINID='"+loginId+"'";
		} else if(tableCd.equalsIgnoreCase("XSZZ")) {
			updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + finalMark + "  where LOGINID='"+loginId+"'";
		} else if(tableCd.equalsIgnoreCase("KYXM")) {
			updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + finalMark + "  where LOGINID='"+loginId+"'";
		} else if(tableCd.equalsIgnoreCase("KYXM")) {
			updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + finalMark + "  where LOGINID='"+loginId+"'";
		} else if(tableCd.equalsIgnoreCase("CGHJ")) {
			updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + finalMark + "  where LOGINID='"+loginId+"'";
		} else if(tableCd.equalsIgnoreCase("ZLXX")) {
			updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + finalMark + "  where LOGINID='"+loginId+"'";
		} else if(tableCd.equalsIgnoreCase("QTCG")) {
			updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + finalMark + "  where LOGINID='"+loginId+"'";
		}
		else {
			updateSql = updateSql + "  where LOGINID='"+loginId+"'";
		}
		
		DBSql.executeUpdate(updateSql);
	}
	
	
	public void setFinalMarkWithYear(String name, String loginId, String jgh, String tableNm, int mark, String datayear) {
		String updateTableNm = "";
		if(tableNm!=null && !tableNm.equals("") && (tableNm.indexOf("_")<tableNm.length()-4)) {
			updateTableNm = tableNm.substring(tableNm.indexOf("_")+1, tableNm.length()-4);
			updateTableNm = updateTableNm.toUpperCase();
		}
		
		//excel导入时，jgh不为空，name不为空。loginId可能为空
		//当通过AWS流程时，loginId不为空,jgh, name可能为空。
		
		String querySql = "";
		if(!jgh.equals("") && !loginId.equals("") && !name.equals("")) {
			querySql = "SELECT ID from BO_FINALMARK WHERE JGH='" +jgh+ "' and DATAYEAR='"+datayear+"'";
		} else if(!jgh.equals("")) {
			querySql = "SELECT ID from BO_FINALMARK WHERE JGH='" +jgh+ "' and DATAYEAR='"+datayear+"'";
			
			String queryLoginId = "SELECT LOGINID FROM BO_RSTEMP where  ZGH='" +jgh+ "'";
			String queryLoginIdResult = DBSql.getString(queryLoginId, "LOGINID").trim();
			if(queryLoginIdResult != null && !queryLoginIdResult.equals("")) {
				loginId = queryLoginIdResult;
			}
			
			String queryName = "SELECT XM FROM BO_RSTEMP where  ZGH='" +jgh+ "'";
			String queryNameResult = DBSql.getString(queryName, "XM").trim();
			if(queryNameResult != null && !queryNameResult.equals("")) {
				name = queryNameResult;
			}
		}else if(!loginId.equals("")) {
			querySql = "SELECT ID from BO_FINALMARK WHERE LOGINID='" +loginId+ "' and DATAYEAR='"+datayear+"'";
			
			String queryJgh = "SELECT ZGH FROM BO_RSTEMP where  LOGINID='" +loginId+ "'";
			String queryJghReuslt = DBSql.getString(queryJgh, "ZGH").trim();
			if(queryJghReuslt != null && !queryJghReuslt.equals("")) {
				jgh = queryJghReuslt;
			}
			
			String queryName = "SELECT XM FROM BO_RSTEMP where  loginid='" +loginId+ "'";
			String queryNameResult = DBSql.getString(queryName, "XM").trim();
			if(queryNameResult != null && !queryNameResult.equals("")) {
				name = queryNameResult;
			}
		}
		
		int dataId = 0;
		dataId = DBSql.getInt(querySql, "ID");
		String updateSql = "";
		if(dataId == 0 && !name.equals("") && !loginId.equals("") && !jgh.equals("")) {
			Hashtable recordData = new Hashtable();
			recordData.put("NAME", name);
			recordData.put("LOGINID", loginId);
			recordData.put("JGH", jgh);
			recordData.put(updateTableNm, mark);
			recordData.put("ZFM", mark);
			recordData.put("DATAYEAR", datayear);
			//如果“工作经历”, “人才培养_任课情况”, “人才培养_指导学生” 三类特殊处理。叠加JXZFM（教学总分值）
			if(tableNm.equalsIgnoreCase("BO_GZJLTEMP")) {
				recordData.put("JXZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_RKQKTEMP")) {
				recordData.put("JXZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_ZDXSTEMP")) {
				recordData.put("JXZFM", mark);
			}
			//叠加KYZFM（科研总分值） XSLW 科研情况_学术论文; XSZZ 科研情况_学术著作;	KYXM 科研情况_科研项目; YJBG科研情况_研究报告;CGHJ 科研情况_科研获奖;ZLXX 科研情况_专利信息;QTCG 科研情况_其他成果
			if(tableNm.equalsIgnoreCase("BO_XSLWTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_XSZZTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_KYXMTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_YJBGTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_CGHJTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_ZLXXTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_QTCGTEMP")) {
				recordData.put("KYZFM", mark);
			}
			
			try {
				int createBoId = BOInstanceAPI.getInstance().createBOData("BO_FINALMARK", recordData, "admin");
				//System.out.println("setFinalMark______________for"+name+",createBoId = "+createBoId);
			} catch (AWSSDKException e) {
				e.printStackTrace();
			}
		} else if(dataId != 0) {
			updateSql = "UPDATE BO_FINALMARK SET NAME='" +name+ "' " +
						" , LOGINID='" +loginId+ "' " +
						" , JGH='" +jgh+ "' " +
						" , "+updateTableNm+" =NVL(" +updateTableNm+ ",0) + "+mark+ 
						" , ZFM = NVL(ZFM, 0) + " + mark;
			
			//如果“工作经历”, “人才培养_任课情况”, “人才培养_指导学生” 三类特殊处理。叠加JXZFM（教学总分值）
			if(tableNm.equals("BO_GZJLTEMP")) {
				updateSql = updateSql + " , JXZFM = NVL(JXZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equals("BO_RKQKTEMP")) {
				updateSql = updateSql + " , JXZFM = NVL(JXZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equals("BO_ZDXSTEMP")) {
				updateSql = updateSql + " , JXZFM = NVL(JXZFM, 0) + " + mark + " where ID="+dataId;
			} 
			//叠加KYZFM（科研总分值） XSLW 科研情况_学术论文; XSZZ 科研情况_学术著作;	KYXM 科研情况_科研项目; YJBG科研情况_研究报告;CGHJ 科研情况_科研获奖;ZLXX 科研情况_专利信息;QTCG 科研情况_其他成果
			else if(tableNm.equalsIgnoreCase("BO_XSLWTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_XSZZTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_KYXMTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_YJBGTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_CGHJTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_ZLXXTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_QTCGTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			}
			else {
				updateSql = updateSql + " where ID="+dataId;
			}
			
			DBSql.executeUpdate(updateSql);
		}
	}
	
	public void setFinalMarkWithYear(String name, String loginId, String jgh, String tableNm, float mark, String datayear) {
		String updateTableNm = "";
		if(tableNm!=null && !tableNm.equals("") && (tableNm.indexOf("_")<tableNm.length()-4)) {
			updateTableNm = tableNm.substring(tableNm.indexOf("_")+1, tableNm.length()-4);
			updateTableNm = updateTableNm.toUpperCase();
		}
		
		//excel导入时，jgh不为空，name不为空。loginId可能为空
		//当通过AWS流程时，loginId不为空,jgh, name可能为空。
		
		String querySql = "";
		if(!jgh.equals("") && !loginId.equals("") && !name.equals("")) {
			querySql = "SELECT ID from BO_FINALMARK WHERE JGH='" +jgh+ "' and DATAYEAR='"+datayear+"'";
		} else if(!jgh.equals("")) {
			querySql = "SELECT ID from BO_FINALMARK WHERE JGH='" +jgh+ "' and DATAYEAR='"+datayear+"'";
			
			String queryLoginId = "SELECT LOGINID FROM BO_RSTEMP where  ZGH='" +jgh+ "'";
			String queryLoginIdResult = DBSql.getString(queryLoginId, "LOGINID").trim();
			if(queryLoginIdResult != null && !queryLoginIdResult.equals("")) {
				loginId = queryLoginIdResult;
			}
			
			String queryName = "SELECT XM FROM BO_RSTEMP where  ZGH='" +jgh+ "'";
			String queryNameResult = DBSql.getString(queryName, "XM").trim();
			if(queryNameResult != null && !queryNameResult.equals("")) {
				name = queryNameResult;
			}
		}else if(!loginId.equals("")) {
			querySql = "SELECT ID from BO_FINALMARK WHERE LOGINID='" +loginId+ "' and DATAYEAR='"+datayear+"'";
			
			String queryJgh = "SELECT ZGH FROM BO_RSTEMP where  LOGINID='" +loginId+ "'";
			String queryJghReuslt = DBSql.getString(queryJgh, "ZGH").trim();
			if(queryJghReuslt != null && !queryJghReuslt.equals("")) {
				jgh = queryJghReuslt;
			}
			
			String queryName = "SELECT XM FROM BO_RSTEMP where  loginid='" +loginId+ "'";
			String queryNameResult = DBSql.getString(queryName, "XM").trim();
			if(queryNameResult != null && !queryNameResult.equals("")) {
				name = queryNameResult;
			}
		}
		
		int dataId = 0;
		dataId = DBSql.getInt(querySql, "ID");
		String updateSql = "";
		if(dataId == 0 && !name.equals("") && !loginId.equals("") && !jgh.equals("")) {
			Hashtable recordData = new Hashtable();
			recordData.put("NAME", name);
			recordData.put("LOGINID", loginId);
			recordData.put("JGH", jgh);
			recordData.put(updateTableNm, mark);
			recordData.put("ZFM", mark);
			recordData.put("DATAYEAR", datayear);
			//如果“工作经历”, “人才培养_任课情况”, “人才培养_指导学生” 三类特殊处理。叠加JXZFM（教学总分值）
			if(tableNm.equalsIgnoreCase("BO_GZJLTEMP")) {
				recordData.put("JXZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_RKQKTEMP")) {
				recordData.put("JXZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_ZDXSTEMP")) {
				recordData.put("JXZFM", mark);
			}
			//叠加KYZFM（科研总分值） XSLW 科研情况_学术论文; XSZZ 科研情况_学术著作;	KYXM 科研情况_科研项目; YJBG科研情况_研究报告;CGHJ 科研情况_科研获奖;ZLXX 科研情况_专利信息;QTCG 科研情况_其他成果
			if(tableNm.equalsIgnoreCase("BO_XSLWTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_XSZZTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_KYXMTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_YJBGTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_CGHJTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_ZLXXTEMP")) {
				recordData.put("KYZFM", mark);
			} else if(tableNm.equalsIgnoreCase("BO_QTCGTEMP")) {
				recordData.put("KYZFM", mark);
			}
			
			try {
				int createBoId = BOInstanceAPI.getInstance().createBOData("BO_FINALMARK", recordData, "admin");
				//System.out.println("setFinalMark______________for"+name+",createBoId = "+createBoId);
			} catch (AWSSDKException e) {
				e.printStackTrace();
			}
		} else if(dataId != 0) {
			updateSql = "UPDATE BO_FINALMARK SET NAME='" +name+ "' " +
						" , LOGINID='" +loginId+ "' " +
						" , JGH='" +jgh+ "' " +
						" , "+updateTableNm+" =NVL(" +updateTableNm+ ",0) + "+mark+ 
						" , ZFM = NVL(ZFM, 0) + " + mark;
			
			//如果“工作经历”, “人才培养_任课情况”, “人才培养_指导学生” 三类特殊处理。叠加JXZFM（教学总分值）
			if(tableNm.equals("BO_GZJLTEMP")) {
				updateSql = updateSql + " , JXZFM = NVL(JXZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equals("BO_RKQKTEMP")) {
				updateSql = updateSql + " , JXZFM = NVL(JXZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equals("BO_ZDXSTEMP")) {
				updateSql = updateSql + " , JXZFM = NVL(JXZFM, 0) + " + mark + " where ID="+dataId;
			} 
			//叠加KYZFM（科研总分值） XSLW 科研情况_学术论文; XSZZ 科研情况_学术著作;	KYXM 科研情况_科研项目; YJBG科研情况_研究报告;CGHJ 科研情况_科研获奖;ZLXX 科研情况_专利信息;QTCG 科研情况_其他成果
			else if(tableNm.equalsIgnoreCase("BO_XSLWTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_XSZZTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_KYXMTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_YJBGTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_CGHJTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_ZLXXTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			} else if(tableNm.equalsIgnoreCase("BO_QTCGTEMP")) {
				updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + mark + " where ID="+dataId;
			}
			else {
				updateSql = updateSql + " where ID="+dataId;
			}
			
			DBSql.executeUpdate(updateSql);
		}
	}
	
	
	public void reduceFinalMarkWithYear(String loginId, String tableCd, int finalMark, String datayear) {
		String updateSql = "UPDATE BO_FINALMARK SET "+tableCd+" =NVL(" +tableCd+ ",0) - "+finalMark+ 
			" , ZFM = NVL(ZFM, 0) - " + finalMark;
		
		//如果“工作经历”, “人才培养_任课情况”, “人才培养_指导学生” 三类特殊处理。叠加JXZFM（教学总分值）
		if(tableCd.equalsIgnoreCase("GZJL")) {
			updateSql = updateSql + " , JXZFM = NVL(JXZFM, 0) - " + finalMark + "  where LOGINID='"+loginId+"' and DATAYEAR='"+datayear+"'";
		} else if(tableCd.equalsIgnoreCase("RKQK")) {
			updateSql = updateSql + " , JXZFM = NVL(JXZFM, 0) - " + finalMark + "  where LOGINID='"+loginId+"' and DATAYEAR='"+datayear+"'";
		} else if(tableCd.equalsIgnoreCase("ZDXS")) {
			updateSql = updateSql + " , JXZFM = NVL(JXZFM, 0) - " + finalMark + "  where LOGINID='"+loginId+"' and DATAYEAR='"+datayear+"'";
		} 
		
		//叠加KYZFM（科研总分值） XSLW 科研情况_学术论文; XSZZ 科研情况_学术著作;	KYXM 科研情况_科研项目; YJBG科研情况_研究报告;CGHJ 科研情况_科研获奖;ZLXX 科研情况_专利信息;QTCG 科研情况_其他成果
		else if(tableCd.equalsIgnoreCase("XSLW")) {
			updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + finalMark + "  where LOGINID='"+loginId+"' and DATAYEAR='"+datayear+"'";
		} else if(tableCd.equalsIgnoreCase("XSZZ")) {
			updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + finalMark + "  where LOGINID='"+loginId+"' and DATAYEAR='"+datayear+"'";
		} else if(tableCd.equalsIgnoreCase("KYXM")) {
			updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + finalMark + "  where LOGINID='"+loginId+"' and DATAYEAR='"+datayear+"'";
		} else if(tableCd.equalsIgnoreCase("KYXM")) {
			updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + finalMark + "  where LOGINID='"+loginId+"' and DATAYEAR='"+datayear+"'";
		} else if(tableCd.equalsIgnoreCase("CGHJ")) {
			updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + finalMark + "  where LOGINID='"+loginId+"' and DATAYEAR='"+datayear+"'";
		} else if(tableCd.equalsIgnoreCase("ZLXX")) {
			updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + finalMark + "  where LOGINID='"+loginId+"' and DATAYEAR='"+datayear+"'";
		} else if(tableCd.equalsIgnoreCase("QTCG")) {
			updateSql = updateSql + " , KYZFM = NVL(KYZFM, 0) + " + finalMark + "  where LOGINID='"+loginId+"' and DATAYEAR='"+datayear+"'";
		}
		else {
			updateSql = updateSql + "  where LOGINID='"+loginId+"' and DATAYEAR='"+datayear+"'";
		}
		
		DBSql.executeUpdate(updateSql);
	}
	
	
	public static void  main(String[] args) {
//		String  tableNm = "BO_GZJLTEMP";
//		String updateTableNm = tableNm.substring(tableNm.indexOf("_")+1, tableNm.length()-4);
//		System.out.println(updateTableNm);
		
		String numStr = "2";
		float num = 0;
		if(isNumber(numStr)) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}
	
	public static boolean isNumeric(String str)   
    {   
        if(str == null)   
            return false;   
        int sz = str.length();   
        for(int i = 0; i < sz; i++)   
            if(!Character.isDigit(str.charAt(i)))   
                return false;   
  
        return true;   
    } 
	
	public static boolean isNumber(String number){   
        int index = number.indexOf(".");   
        if(index<0){   
            return isNumeric(number);   
        }else{   
            String num1 = number.substring(0,index);   
            String num2 = number.substring(index+1);               
               
            return isNumeric(num1) && isNumeric(num2);   
        }   
    }  


}
