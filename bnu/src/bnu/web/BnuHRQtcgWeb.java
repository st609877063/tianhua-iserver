package bnu.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import bnu.importBiz.CalMark;

import com.actionsoft.awf.organization.control.MessageQueue;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.AjaxUtil;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.awf.util.PageIndex;
import com.actionsoft.awf.workflow.execute.PriorityType;
import com.actionsoft.awf.workflow.execute.SynType;
import com.actionsoft.htmlframework.htmlmodel.HtmlModelFactory;
import com.actionsoft.htmlframework.htmlmodel.RepleaseKey;
import com.actionsoft.htmlframework.web.ActionsoftWeb;
import com.actionsoft.sdk.local.level0.WorkflowInstanceAPI;
import com.actionsoft.sdk.local.level0.WorkflowTaskInstanceAPI;

public class BnuHRQtcgWeb extends ActionsoftWeb {
	/**
	 * 构造方法
	 * @param userContext 用户上下文对象
	 */
	public BnuHRQtcgWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造方法
	 */
	public BnuHRQtcgWeb() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 其他成果--查询结果
	 * @param pageNow    页码
	 * @param lineNumber 每页行数
	 * @param awsuid     AWS帐号
	 * @return
	 */
	public String getReport(int pageNow,int lineNumber,String awsuid) {
		//项目多用户参与，需共享数据
		StringBuffer sb = new StringBuffer();
		Hashtable hashTags=new Hashtable();
//		String sqlw = "select * from BO_QTCGTEMP where 1=1 and CHARGEID='"+awsuid+"' order by CREATEDATE desc";
//		String sqlc = "select count(*) c from BO_QTCGTEMP where 1=1 and CHARGEID='"+awsuid+"'";
		String sqlw = " select * from (" +
				" select * from BO_QTCGTEMP where  CHARGEID='"+awsuid+"'  and (AUTOADD is null or AUTOADD=0) " +
				" union select * from BO_QTCGTEMP where  ID1='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_QTCGTEMP where  ID2='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_QTCGTEMP where  ID3='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_QTCGTEMP where  ID4='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_QTCGTEMP where  ID5='"+awsuid+"' and AUTOADD=1 " +
				") order by CREATEDATE desc";
		String sqlc = " select count(*) c from (" +
				" select * from BO_QTCGTEMP where  CHARGEID='"+awsuid+"'  and (AUTOADD is null or AUTOADD=0) " +
				" union select * from BO_QTCGTEMP where  ID1='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_QTCGTEMP where  ID2='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_QTCGTEMP where  ID3='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_QTCGTEMP where  ID4='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_QTCGTEMP where  ID5='"+awsuid+"' and AUTOADD=1 )";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String bg = "",bg1 = "",b1 = "", b2 = "";
		bg = " style=\"padding:5px;\"";  
		bg1 = "../aws_img/report-bg-blue2.gif";
		try{
		   int lineCount = DBSql.getInt(sqlc, "c");
		   int start = (pageNow-1)*lineNumber+1;
		   int xh=start;
		   String SQL = "";
		   conn=DBSql.open();
		   conn.setAutoCommit(true);
		   
		   if(start+lineNumber>=lineCount){
			   SQL = "select * from (select rownum r,t1.* from ("+sqlw+") t1 where rownum<="+lineCount+") t2 where t2.r>="+start; //oracle
			   //sql server
//			   SQL = "select top "+lineCount+" * from BO_QTCGTEMP " +
//			   		"where CHARGEID='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_QTCGTEMP  where CHARGEID='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }else{
		       SQL = "select * from (select rownum r,t1.* from ("+sqlw+") t1 where rownum<="+(start+lineNumber-1)+") t2 where t2.r>="+start; //oracle
			   //sql server
//			   SQL = "select top "+(start+lineNumber-1)+" * from BO_QTCGTEMP " +
//			   		"where CHARGEID='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_QTCGTEMP  where CHARGEID='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }
		   ps=conn.prepareStatement(SQL);
		   rs=ps.executeQuery();
		   while(rs.next()){
			   
			   int ID = rs.getInt("ID");
			   int bindId = rs.getInt("BINDID");
			   
			   String HAVNAME = rs.getString("HAVNAME")==null?"":rs.getString("HAVNAME");          // 成果名称
			   String HAVKINDSYMBOL= rs.getString("HAVKINDSYMBOL")==null?"":rs.getString("HAVKINDSYMBOL");     // 成果类型 
			   String MANAGEOFFICE = rs.getString("MANAGEOFFICE")==null?"":rs.getString("MANAGEOFFICE");   // 归口类型
			   String APPRAISEUNIT = rs.getString("APPRAISEUNIT")==null?"":rs.getString("APPRAISEUNIT"); // 评价单位
			   String APPRAISEDATE = rs.getString("APPRAISEDATE")==null?"":rs.getString("APPRAISEDATE");   // 认定日期
			   String APPRAISEREPORTCODE = rs.getString("APPRAISEREPORTCODE")==null?"":rs.getString("APPRAISEREPORTCODE"); // 证书编号   ？？？？？截图显示不全  需要确认 
			   String SUBJECT = rs.getString("SUBJECT")==null?"":rs.getString("SUBJECT"); // 学科领域
			   String SUBJECT2 = rs.getString("SUBJECT2")==null?"":rs.getString("SUBJECT2"); // 学科领域
			   String ATTACH = rs.getString("ATTACH")==null?"":rs.getString("ATTACH"); // 扫描件
			   String CGYWMC = rs.getString("CGYWMC")==null?"":rs.getString("CGYWMC");   // 成果英文名称
			   String YXBM = rs.getString("YXBM")==null?"":rs.getString("YXBM");   // 院系部门
			   String CNDW = rs.getString("CNDW")==null?"":rs.getString("CNDW");   // 采纳单位
			   String CHARGENM = rs.getString("CHARGENM")==null?"":rs.getString("CHARGENM"); // 负责人姓名
			   String CHARGEID = rs.getString("CHARGEID")==null?"":rs.getString("CHARGEID");   // 负责人ID
			   int CHARGERATE = rs.getInt("CHARGERATE");    // 发明人工作量（%）
			   String WCRSF = rs.getString("WCRSF")==null?"":rs.getString("WCRSF");
			   
			   String NAME1 = rs.getString("NAME1")==null?"":rs.getString("NAME1"); //
			   String ID1 = rs.getString("ID1")==null?"":rs.getString("ID1"); //
			   int RATE1 = rs.getInt("RATE1"); //
			   String NAME2 = rs.getString("NAME2")==null?"":rs.getString("NAME2"); //
			   String ID2 = rs.getString("ID2")==null?"":rs.getString("ID2"); //
			   int RATE2 = rs.getInt("RATE2"); //
			   String NAME3 = rs.getString("NAME3")==null?"":rs.getString("NAME3"); //
			   String ID3 = rs.getString("ID3")==null?"":rs.getString("ID3"); //
			   int RATE3 = rs.getInt("RATE3"); //
			   String NAME4 = rs.getString("NAME4")==null?"":rs.getString("NAME4"); //
			   String ID4 = rs.getString("ID4")==null?"":rs.getString("ID4"); //
			   int RATE4 = rs.getInt("RATE4"); //
			   String NAME5 = rs.getString("NAME5")==null?"":rs.getString("NAME5"); //
			   String ID5 = rs.getString("ID5")==null?"":rs.getString("ID5"); //
			   int RATE5 = rs.getInt("RATE5"); //
			   
			   
			   if(APPRAISEDATE!=null && APPRAISEDATE.length()>10) {
				   APPRAISEDATE = APPRAISEDATE.substring(0,10);
			   }
			   
			   int AUTOADD = 0;
			   AUTOADD = rs.getInt("AUTOADD"); //
			   String autoaddStatus = null;
			   if (AUTOADD == 0) {
				   autoaddStatus = "非自动添加";
			   } else if (AUTOADD == 1) {
				   autoaddStatus = "自动添加";
			   } 
			   
			   int STATUS = 0;
			   STATUS = rs.getInt("STATUS");
			   String aprvStatus = null;
			   if (STATUS == 0) {
				   aprvStatus = "未审批";
			   } else if (STATUS == 1) {
				   aprvStatus = "审批通过";
			   } else if (STATUS == 9) {
				   aprvStatus = "批量导入(锁定)";
			   }
			   
			   if (AUTOADD == 1) {
				   aprvStatus = "参与项目";
			   } 
			   
			   int joinMan = 0;
			   if(!NAME1.equals("")) {
				   joinMan = joinMan + 1;
			   }
			   if(!NAME2.equals("")) {
				   joinMan = joinMan + 1;
			   }
			   if(!NAME3.equals("")) {
				   joinMan = joinMan + 1;
			   }
			   if(!NAME4.equals("")) {
				   joinMan = joinMan + 1;
			   }
			   if(!NAME5.equals("")) {
				   joinMan = joinMan + 1;
			   }
			   
			   //获得taskId
//			   int taskId = 0;
//			   String taskSql = "select ID FROM WF_TASK WHERE BIND_ID = "+bindId;
//			   taskId = DBSql.getInt(taskSql, "ID");
			   
			   sb.append("<tr bgcolor='#ffffff'>\n");
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all'><input type='checkbox' name='checkData' id='"+ID+"'/></td>\n");//checkbox
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all' display='hidden'>"+ID+"</td>\n");//ID
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+xh+"</td>\n");//序号
			  
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+(HAVNAME)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CHARGENM)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(HAVKINDSYMBOL)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(MANAGEOFFICE)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(APPRAISEUNIT)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(APPRAISEDATE)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(APPRAISEREPORTCODE)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(SUBJECT+SUBJECT2)+b2+"</td>\n"); 
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(ATTACH)+b2+"</td>\n");  
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CGYWMC)+b2+"</td>\n");  
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(YXBM)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CNDW)+b2+"</td>\n");
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CHARGEID)+b2+"</td>\n"); 
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CHARGERATE)+b2+"</td>\n"); 
			   
			   sb.append("<td"+bg+" align='left' >"+b1+(joinMan)+b2+"</td>\n"); //其他参与人数
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(aprvStatus)+b2+"</td>\n");
			   
			   if ((STATUS == 0 && AUTOADD == 0) || !WCRSF.equals("本校教师")) {
				   sb.append("<td"+bg+" align='left' style='word-break:break-all'><a href='#' onclick='viewDetail("+ID+");return false;'>详情</a>&nbsp&nbsp");
				   sb.append("<a href='#' onclick='modifyData("+bindId+", "+ID+");return false;'  title='其他成果'><img src='../aws_img/man.gif' border='0'>修改</a>&nbsp&nbsp");
//				   sb.append("<a href='#' onclick='deleteData("+ID+");return false;'>删除</a></td>\n"); //操作
				   sb.append("</td>\n"); //操作
			   } else if (STATUS == 1 || AUTOADD == 1) {   
				   sb.append("<td"+bg+" align='left' style='word-break:break-all'><a href='#' onclick='viewDetail("+ID+");return false;'>详情</a></td>");
			   } else if (STATUS == 9 || AUTOADD == 0) {   
				   sb.append("<td"+bg+" align='left' style='word-break:break-all'><a href='#' onclick='viewDetail("+ID+");return false;'>详情</a>&nbsp&nbsp");
				   sb.append("<a href='#' onclick='unlockData("+ID+");return false;'><img src='../aws_img/man.gif' border='0'>解锁</a>");
				   sb.append("</td>\n");
			   }
			   sb.append("</tr>\n");
			   xh++;
		   }
		   ps.close();
		   rs.close();
		   
		   //页面导航
		   if (lineCount > lineNumber) {
			   String pageIndexStr = new PageIndex("BNU_HR_QTCG", pageNow, lineCount, lineNumber).toString();
			   String pageIndexStr2 = pageIndexStr.replaceAll("frmMain", "frmQtcg");
			   
			   sb.append("<tr bgcolor='#ffffff'><td colspan=17><div class='hide_for_jatools_print' id=AWS_REPORT_PAGEINDEX name=AWS_REPORT_PAGEINDEX ><br><br>")
			   .append(pageIndexStr2)
			   .append("</div></td></tr>");
		   }
		   hashTags.put("sid",super.getSIDFlag());
		   hashTags.put("awsuid",awsuid);
		   hashTags.put("pageNow",Integer.toString(pageNow));
		   hashTags.put("list",sb.toString());
		}catch(Exception e){
			e.printStackTrace(System.err);
			return "#其他成果_数据查询异常";
		}finally{
			DBSql.close(conn, ps, rs);
		}
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_HR_QTCG.html"),hashTags);
	}
	
	/**
	 * 删除
	 * @param awsuid
	 * @return
	 */
	public String deleteData(String awsuid,String dataId) {
		BnuMainWeb web = new BnuMainWeb(super.getContext());
		Connection conn = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(false);
			

			String copyDataStr = DBSql.getString(conn, "SELECT COPYDATAID FROM BO_QTCGTEMP WHERE ID="+Integer.parseInt(dataId), "COPYDATAID");
			List copyDataList = new ArrayList();
			if(copyDataStr!=null) {
				String temp[] = copyDataStr.split(":");
				for(int i=0; i<temp.length; i++) {
					copyDataList.add(temp[i]);
				}
			}
			for(int i=0; i<copyDataList.size(); i++) {
				String copyDataIdTemp = copyDataList.get(i).toString();
				if(copyDataIdTemp!=null && !copyDataIdTemp.equals("") && !copyDataIdTemp.equals("0")) {
					int copyDataIdInt = Integer.parseInt(copyDataIdTemp);
					String deleteSql = "delete from BO_QTCGTEMP where ID ="+copyDataIdInt;
					DBSql.executeUpdate(deleteSql);
				}
			}
			
			
			String sql = null;
			sql = "delete from  BO_QTCGTEMP where CHARGEID='"+awsuid+"' and ID="+Integer.parseInt(dataId);
			ps1 = conn.prepareStatement(sql);
			int r = ps1.executeUpdate();
			if(r < 0) {
				MessageQueue.getInstance().putMessage(super.getContext().getUID(),"数据删除失败");
				return web.getMain("11",awsuid);
			}
			ps1.close();
			conn.commit();
		}catch(Exception e) {
			e.printStackTrace(System.err);
			MessageQueue.getInstance().putMessage(super.getContext().getUID(),"数据删除失败");
			return web.getMain("11",awsuid);
		}finally {
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace(System.err);
				MessageQueue.getInstance().putMessage(super.getContext().getUID(),"数据删除失败");
				return web.getMain("11",awsuid);
			}
			DBSql.close(conn, ps1, rs1);
		}
		MessageQueue.getInstance().putMessage(super.getContext().getUID(),"数据删除成功！");
		return web.getMain("11",awsuid);
	}
	
	public String viewDetail(String awsuid, String dataId) {
		StringBuffer response = new StringBuffer();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String HAVNAME = null;          // 成果名称
		String HAVKINDSYMBOL= null;     // 成果类型 
		String MANAGEOFFICE = null;   // 归口类型
		String APPRAISEUNIT = null; // 评价单位
		String APPRAISEDATE = null;   // 认定日期
		String APPRAISEREPORTCODE = null; // 证书编号   
		String SUBJECT = null; // 学科领域
		String SUBJECT2 = null; // 学科领域
		String ATTACH = null; // 扫描件
		String CGYWMC = null;   // 成果英文名称
		String YXBM = null;   // 院系部门
		String CNDW = null;   // 采纳单位
		String WCRSF = null;   // 采纳单位
		String CHARGENM = null; // 负责人姓名
		String CHARGEID = null;   // 负责人ID
		int CHARGERATE = 0;     // 发明人工作量（%）
		
		String STATUS = null;          // 审批状态
		String AUTOADD = null;   // 自动添加
		String NAME1 = null; //
		String ID1 = null; //
		int RATE1 = 0; //
		String NAME2 = null; //
		String ID2 = null; //
		int RATE2 = 0; //
		String NAME3 = null; //
		String ID3 = null; //
		int RATE3 = 0; //
		String NAME4 = null; //
		String ID4 = null; //
		int RATE4 = 0; //
		String NAME5 = null; //
		String ID5 = null; //
		int RATE5 = 0; //
		
		String CBDW = "";
		String CBBH = "";
		String CBSJ = "";
		String QKMC = "";
		String FBQK = "";
		String QKLB = "";
		String YM = "";
		String BZMC = "";
		String BM = "";
		String ZS = "";
		String FBSJ = "";
		   
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			String sql = "select * from BO_QTCGTEMP where ID="+Integer.parseInt(dataId);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				   HAVNAME = rs.getString("HAVNAME")==null?"":rs.getString("HAVNAME");          // 成果名称
				   HAVKINDSYMBOL= rs.getString("HAVKINDSYMBOL")==null?"":rs.getString("HAVKINDSYMBOL");     // 成果类型 
				   MANAGEOFFICE = rs.getString("MANAGEOFFICE")==null?"":rs.getString("MANAGEOFFICE");   // 归口类型
				   APPRAISEUNIT = rs.getString("APPRAISEUNIT")==null?"":rs.getString("APPRAISEUNIT"); // 评价单位
				   APPRAISEDATE = rs.getString("APPRAISEDATE")==null?"":rs.getString("APPRAISEDATE");   // 认定日期
				   APPRAISEREPORTCODE = rs.getString("APPRAISEREPORTCODE")==null?"":rs.getString("APPRAISEREPORTCODE"); // 证书编号   
				   SUBJECT = rs.getString("SUBJECT")==null?"":rs.getString("SUBJECT"); // 学科领域
				   SUBJECT2 = rs.getString("SUBJECT2")==null?"":rs.getString("SUBJECT2"); // 学科领域
				   ATTACH = rs.getString("ATTACH")==null?"":rs.getString("ATTACH"); // 扫描件
				   CGYWMC = rs.getString("CGYWMC")==null?"":rs.getString("CGYWMC");   // 成果英文名称
				   YXBM = rs.getString("YXBM")==null?"":rs.getString("YXBM");   // 院系部门
				   CNDW = rs.getString("CNDW")==null?"":rs.getString("CNDW");   // 采纳单位
				   WCRSF = rs.getString("WCRSF")==null?"":rs.getString("WCRSF");
				   CHARGENM = rs.getString("CHARGENM")==null?"":rs.getString("CHARGENM"); // 负责人姓名
				   CHARGEID = rs.getString("CHARGEID")==null?"":rs.getString("CHARGEID");   // 负责人ID
				   CHARGERATE = rs.getInt("CHARGERATE");    // 发明人工作量（%）
				  
				   NAME1 = rs.getString("NAME1")==null?"":rs.getString("NAME1"); //
				   ID1 = rs.getString("ID1")==null?"":rs.getString("ID1"); //
				   RATE1 = rs.getInt("RATE1"); //
				   NAME2 = rs.getString("NAME2")==null?"":rs.getString("NAME2"); //
				   ID2 = rs.getString("ID2")==null?"":rs.getString("ID2"); //
				   RATE2 = rs.getInt("RATE2"); //
				   NAME3 = rs.getString("NAME3")==null?"":rs.getString("NAME3"); //
				   ID3 = rs.getString("ID3")==null?"":rs.getString("ID3"); //
				   RATE3 = rs.getInt("RATE3"); //
				   NAME4 = rs.getString("NAME4")==null?"":rs.getString("NAME4"); //
				   ID4 = rs.getString("ID4")==null?"":rs.getString("ID4"); //
				   RATE4 = rs.getInt("RATE4"); //
				   NAME5 = rs.getString("NAME5")==null?"":rs.getString("NAME5"); //
				   ID5 = rs.getString("ID5")==null?"":rs.getString("ID5"); //
				   RATE5 = rs.getInt("RATE5"); //
				   
				   CBDW = rs.getString("CBDW")==null?"":rs.getString("CBDW").trim();
				   CBBH = rs.getString("CBBH")==null?"":rs.getString("CBBH").trim();
				   CBSJ = rs.getString("CBSJ")==null?"":rs.getString("CBSJ").trim();
				   QKMC = rs.getString("QKMC")==null?"":rs.getString("QKMC").trim();
				   FBQK = rs.getString("FBQK")==null?"":rs.getString("FBQK").trim();
				   QKLB = rs.getString("QKLB")==null?"":rs.getString("QKLB").trim();
				   YM = rs.getString("YM")==null?"":rs.getString("YM").trim();
				   BZMC = rs.getString("BZMC")==null?"":rs.getString("BZMC").trim();
				   BM = rs.getString("BM")==null?"":rs.getString("BM").trim();
				   ZS = rs.getString("ZS")==null?"":rs.getString("ZS").trim();
				   FBSJ = rs.getString("FBSJ")==null?"":rs.getString("FBSJ").trim();
				   
				   
				   if(APPRAISEREPORTCODE.length() >= 10) {
					   APPRAISEREPORTCODE = APPRAISEREPORTCODE.substring(0,10);
				   }
				   
				   if(FBSJ!=null && FBSJ.length()>=10) {
						FBSJ = FBSJ.substring(0, 10);
					}
			}
			ps.close();
			rs.close();
		}catch(Exception e) {
			e.printStackTrace(System.err);
		}finally {
			DBSql.close(conn, ps, rs);
		}		
		
		if(APPRAISEDATE!=null && APPRAISEDATE.length()>10) {
			APPRAISEDATE = APPRAISEDATE.substring(0,10);
		}
		   
		String HAVNAMEBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + HAVNAME + "'>";
		String HAVKINDSYMBOLBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + HAVKINDSYMBOL + "'>";
		String MANAGEOFFICEBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + MANAGEOFFICE + "'>";
		String APPRAISEUNITBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + APPRAISEUNIT + "'>";
		String APPRAISEDATEBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + APPRAISEDATE + "'>";
		String APPRAISEREPORTCODEBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + APPRAISEREPORTCODE + "'>";
		String SUBJECTBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + SUBJECT + "'>";
		String SUBJECT2Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + SUBJECT2 + "'>";
		String ATTACHBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + ATTACH + "'>";
		String CGYWMCBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CGYWMC + "'>";
		String YXBMBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + YXBM + "'>";
		String CNDWBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CNDW + "'>";
		String WCRSFBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + WCRSF + "'>";
		String CHARGENMBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CHARGENM + "'>";
		String CHARGEIDBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CHARGEID + "'>";
		String CHARGERATEBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CHARGERATE + "'>";
		
		String NAME1Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + NAME1 + "'>";
		String ID1Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + ID1 + "'>";
		String RATE1Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + RATE1+"%" + "'>";
		String NAME2Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + NAME2 + "'>";
		String ID2Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + ID2 + "'>";
		String RATE2Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + RATE2+"%" + "'>";
		String NAME3Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + NAME3 + "'>";
		String ID3Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + ID3 + "'>";
		String RATE3Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + RATE3+"%" + "'>";
		String NAME4Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + NAME4 + "'>";
		String ID4Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + ID4 + "'>";
		String RATE4Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + RATE4+"%" + "'>";
		String NAME5Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + NAME5 + "'>";
		String ID5Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + ID5 + "'>";
		String RATE5Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + RATE5+"%" + "'>";
		
		String CBDWBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CBDW + "'>";
		String CBBHBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CBBH + "'>";
		String CBSJBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" +  CBSJ + "'>";
		String QKMCBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + QKMC + "'>";
		String FBQKBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + FBQK + "'>";
		String QKLBBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + QKLB + "'>";
		String YMBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + YM + "'>";
		String BZMCBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + BZMC + "'>";
		String BMBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + BM + "'>";
		String ZSBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + ZS + "'>";
		String FBSJBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + FBSJ + "'>";

		
		
		//table
		response.append("<table width=98% height=90% align=center border=0 cellspacing=0 cellpadding=0>");
		response.append("<tr><td><b><font size='3'>数据详细：</font></b><br><br>");
		response.append("<table width=98% align=center border=0 cellspacing=0 cellpadding=0>");
		
		response.append("<tr>\n");
		response.append("<td width=20% align=left>成果名称：</td><td width=35%>").append(HAVNAMEBuffer).append("</td>\n");
		response.append("<td width=20% align=left>成果英文名称	：</td><td width=35%>").append(CGYWMCBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>学科领域：</td><td width=35%>").append(SUBJECTBuffer).append(" ").append(SUBJECT2Buffer).append("</td>\n");
		response.append("<td width=20% align=left>成果类型：</td><td width=35%>").append(HAVKINDSYMBOLBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>归口类型：</td><td width=35%>").append(MANAGEOFFICEBuffer).append("</td>\n");
		response.append("<td width=20% align=left>证书编号：</td><td width=35%>").append(APPRAISEREPORTCODEBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>评价单位　：</td><td width=35%>").append(APPRAISEUNITBuffer).append("</td>\n");
		response.append("<td width=20% align=left>认定日期：</td><td width=35%>").append(APPRAISEDATEBuffer).append("ID:").append(CHARGEIDBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>采纳单位  ：</td><td width=35%>").append(CNDWBuffer).append("</td>\n");
		response.append("<td width=20% align=left>第一完成人身份 ：</td><td width=35%>").append(WCRSFBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>负责人姓名：</td><td width=35%>").append(CHARGENMBuffer).append("ID:").append(CHARGEIDBuffer).append("</td>\n");
		response.append("<td width=20% align=left>发明人工作量（%）：</td><td width=35%>").append(CHARGERATEBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>院系部门：</td><td width=35%>").append(YXBMBuffer).append("</td>\n");
		response.append("<td width=20% align=left>发表期刊：</td><td width=35%>").append(FBQKBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>出版时间：</td><td width=35%>").append(CBSJBuffer).append("</td>\n");
		response.append("<td width=20% align=left>期刊名称：</td><td width=35%>").append(QKMCBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>期刊类别：</td><td width=35%>").append(QKLBBuffer).append("</td>\n");
		response.append("<td width=20% align=left>页码：</td><td width=35%>").append(YMBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>出版单位：</td><td width=35%>").append(CBDWBuffer).append("</td>\n");
		response.append("<td width=20% align=left>出版编号：</td><td width=35%>").append(CBBHBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>报纸名称：</td><td width=35%>").append(BZMCBuffer).append("</td>\n");
		response.append("<td width=20% align=left>发表时间：</td><td width=35%>").append(FBSJBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>版面：</td><td width=35%>").append(BMBuffer).append("</td>\n");
		response.append("<td width=20% align=left>字数/千：</td><td width=35%>").append(ZSBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>参与人1：</td><td width=35%>").append(NAME1Buffer).append("ID:").append(ID1Buffer).append("</td>\n");
		response.append("<td width=20% align=left>工作量（%）：</td><td width=35%>").append(RATE1Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>参与人2：</td><td width=35%>").append(NAME2Buffer).append("ID:").append(ID2Buffer).append("</td>\n");
		response.append("<td width=20% align=left>工作量（%）：</td><td width=35%>").append(RATE2Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>参与人3：</td><td width=35%>").append(NAME3Buffer).append("ID:").append(ID3Buffer).append("</td>\n");
		response.append("<td width=20% align=left>工作量（%）：</td><td width=35%>").append(RATE3Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>参与人4：</td><td width=35%>").append(NAME4Buffer).append("ID:").append(ID4Buffer).append("</td>\n");
		response.append("<td width=20% align=left>工作量（%）：</td><td width=35%>").append(RATE4Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>参与人5：</td><td width=35%>").append(NAME5Buffer).append("ID:").append(ID5Buffer).append("</td>\n");
		response.append("<td width=20% align=left>工作量（%）：</td><td width=35%>").append(RATE5Buffer).append("</td>\n");
		response.append("</tr>\n");
		
		
		response.append("</table>\n");
		
		response.append("</td></tr></table>");
		
		return AjaxUtil.responseDialog("其他成果信息", 180, 70, 800, 500, response.toString(), "" ); //left,top,width,height
	}
	
	public String unlockData(String awsuid, String dataId) {
		BnuMainWeb web = new BnuMainWeb(super.getContext());
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			
			String sql = "select * from BO_QTCGTEMP where id="+Integer.parseInt(dataId);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			String loginId = "";
			int finalMark = 0;
			String datayear = "";
			while(rs.next()){
				loginId = rs.getString("CHARGEID")==null?"":rs.getString("CHARGEID").trim();
				finalMark = rs.getInt("FINALMARK");
				datayear = rs.getString("DATAYEAR")==null?"":rs.getString("DATAYEAR").trim();
			}
			
			//运行AWS流程-----------------为数据绑定流程
			int processInstanceId = 0;
			processInstanceId = WorkflowInstanceAPI.getInstance().createProcessInstance("007b23151fc91c3f66afec0bef03455a", loginId, "其他成果申请");
			int[] processTaskInstanceIds = WorkflowTaskInstanceAPI.getInstance().createProcessTaskInstance(loginId, processInstanceId, SynType.synchronous, PriorityType.hight, 1, loginId, "其他成果申请", false, 0);
			
			String updateSql = "update BO_QTCGTEMP set BINDID="+processInstanceId
					+" , STATUS=0" 
					+" , FINALMARK=NVL(FINALMARK,0)-"+finalMark
					+" WHERE ID="+Integer.parseInt(dataId);
			DBSql.executeUpdate(conn, updateSql);
			
			CalMark calMark = new CalMark();
//			calMark.reduceFinalMark(loginId, "QTCG", finalMark);
			calMark.reduceFinalMarkWithYear(loginId, "QTCG", finalMark, datayear);
			
		}catch(Exception e) {
			e.printStackTrace(System.err);
			MessageQueue.getInstance().putMessage(super.getContext().getUID(),"数据解锁失败");
			return web.getMain("11",awsuid);
		}finally {
			DBSql.close(conn, ps, rs);
		}
		
		MessageQueue.getInstance().putMessage(super.getContext().getUID(),"数据解锁成功！");
		return web.getMain("1",awsuid);
	}
	
	
}

