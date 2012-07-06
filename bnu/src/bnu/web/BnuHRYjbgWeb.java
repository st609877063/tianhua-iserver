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

public class BnuHRYjbgWeb extends ActionsoftWeb {
	/**
	 * 构造方法
	 * @param userContext 用户上下文对象
	 */
	public BnuHRYjbgWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造方法
	 */
	public BnuHRYjbgWeb() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 成果获奖--查询结果
	 * @param pageNow    页码
	 * @param lineNumber 每页行数
	 * @param awsuid     AWS帐号
	 * @return
	 */
	public String getReport(int pageNow,int lineNumber,String awsuid) {
		//项目多用户参与，需共享数据
		StringBuffer sb = new StringBuffer();
		Hashtable hashTags=new Hashtable();
//		String sqlw = "select * from BO_YJBGTEMP where  CHARGEID='"+awsuid+"' order by CREATEDATE desc";
//		String sqlc = "select count(*) c from BO_YJBGTEMP where  CHARGEID='"+awsuid+"'";
		String sqlw = " select * from (" +
				" select * from BO_YJBGTEMP where  CHARGEID='"+awsuid+"'  and (AUTOADD is null or AUTOADD=0) " +
				" union select * from BO_YJBGTEMP where  ID1='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_YJBGTEMP where  ID2='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_YJBGTEMP where  ID3='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_YJBGTEMP where  ID4='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_YJBGTEMP where  ID5='"+awsuid+"' and AUTOADD=1 " +
				") order by CREATEDATE desc";
		String sqlc = " select count(*) c from (" +
				" select * from BO_YJBGTEMP where  CHARGEID='"+awsuid+"'  and (AUTOADD is null or AUTOADD=0) " +
				" union select * from BO_YJBGTEMP where  ID1='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_YJBGTEMP where  ID2='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_YJBGTEMP where  ID3='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_YJBGTEMP where  ID4='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_YJBGTEMP where  ID5='"+awsuid+"' and AUTOADD=1 )";
		
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String bg = "",bg1 = "",b1 = "", b2 = "";
		bg = " style=\"padding:5px;\"";  
		bg1 = "../aws_img/report-bg-blue2.gif";
		
		String CGMC = null;  // 成果名称
		String CGYWMC = null;  // 成果外文名称
		String CGLX = null;  // 成果类型
		String GKLX = null;  // 归口类型
		String PJDW = null;  // 评价单位
		String CNDW = null;  // 采纳单位
		String RDRQ = null;  // 认定日期
		String ZSBH = null;  // 证书编号
		String SUBJECT = null;  // 一级学科
		String SUBJECT2 = null;  // 二级学科
		String WCRSF = null;  // 第一完成人身份
		String SZXSJG = null;  // 所在学术机构
		String YXBM = null;  // 院系部门
		String ZZLX = null;  // 著作类型
		String FBFW = null;  // 发表范围
		String FBSJ = null;  // 发表日期
		String FBND = null;  // 发表年度
		String YJLX = null;  // 研究类别
		String XMJB = null;  // 课题来源(项目级别)
		String CGYYQK = null;  // 成果应用情况
		String SFCN = null;  // 是否司局以上采纳

		String CHARGENM = null; // 第一作者姓名
		String CHARGEID = null;   // 第一作者ID
		int CHARGERATE = 0;     // 第一作者工作量/%
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
//			   SQL = "select top "+lineCount+" * from BO_YJBGTEMP " +
//			   		"where CHARGEID='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_YJBGTEMP  where CHARGEID='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }else{
		       SQL = "select * from (select rownum r,t1.* from ("+sqlw+") t1 where rownum<="+(start+lineNumber-1)+") t2 where t2.r>="+start; //oracle
			   //sql server
//			   SQL = "select top "+(start+lineNumber-1)+" * from BO_YJBGTEMP " +
//			   		"where CHARGEID='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_YJBGTEMP  where CHARGEID='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }
		   ps=conn.prepareStatement(SQL);
		   rs=ps.executeQuery();
		   while(rs.next()){
			   int ID = rs.getInt("ID");
			   int bindId = rs.getInt("BINDID");

			   CGMC = rs.getString("CGMC")==null ? "" :rs.getString("CGMC"); //成果名称
			   CGYWMC = rs.getString("CGYWMC")==null ? "" :rs.getString("CGYWMC"); //成果外文名称
			   CGLX = rs.getString("CGLX")==null ? "" :rs.getString("CGLX"); //成果类型
			   GKLX = rs.getString("GKLX")==null ? "" :rs.getString("GKLX"); //归口类型
			   PJDW = rs.getString("PJDW")==null ? "" :rs.getString("PJDW"); //评价单位
			   CNDW = rs.getString("CNDW")==null ? "" :rs.getString("CNDW"); //采纳单位
			   RDRQ = rs.getString("RDRQ")==null ? "" :rs.getString("RDRQ"); //认定日期
			   ZSBH = rs.getString("ZSBH")==null ? "" :rs.getString("ZSBH"); //证书编号
			   SUBJECT = rs.getString("SUBJECT")==null ? "" :rs.getString("SUBJECT"); //一级学科
			   SUBJECT2 = rs.getString("SUBJECT2")==null ? "" :rs.getString("SUBJECT2"); //二级学科
			   WCRSF = rs.getString("WCRSF")==null ? "" :rs.getString("WCRSF"); //第一完成人身份
			   SZXSJG = rs.getString("SZXSJG")==null ? "" :rs.getString("SZXSJG"); //所在学术机构
			   YXBM = rs.getString("YXBM")==null ? "" :rs.getString("YXBM"); //院系部门
			   ZZLX = rs.getString("ZZLX")==null ? "" :rs.getString("ZZLX"); //著作类型
			   FBFW = rs.getString("FBFW")==null ? "" :rs.getString("FBFW"); //发表范围
			   FBSJ = rs.getString("FBSJ")==null ? "" :rs.getString("FBSJ"); //发表日期
			   FBND = rs.getString("FBND")==null ? "" :rs.getString("FBND"); //发表年度
			   YJLX = rs.getString("YJLX")==null ? "" :rs.getString("YJLX"); //研究类别
			   XMJB = rs.getString("XMJB")==null ? "" :rs.getString("XMJB"); //课题来源(项目级别)
			   CGYYQK = rs.getString("CGYYQK")==null ? "" :rs.getString("CGYYQK"); //成果应用情况
			   SFCN = rs.getString("SFCN")==null ? "" :rs.getString("SFCN"); //是否司局以上采纳

			   CHARGENM = rs.getString("CHARGENM")==null?"":rs.getString("CHARGENM"); //第一作者姓名
			   CHARGEID = rs.getString("CHARGEID")==null?"":rs.getString("CHARGEID"); //第一作者ID
			   CHARGERATE = rs.getInt("CHARGERATE"); //第一作者工作量/%
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
			   
			   if(FBSJ!=null && FBSJ.length()>=10) {
					FBSJ = FBSJ.substring(0,10);
				}
				if(RDRQ!=null && RDRQ.length()>=10) {
					RDRQ = RDRQ.substring(0,10);
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
//			   sb.append("<td"+bg+" align='left' ><input type='checkbox' name='checkData' id='"+ID+"'/></td>\n");//checkbox
//			   sb.append("<td"+bg+" align='left'  display='hidden'>"+ID+"</td>\n");//ID
			   sb.append("<td"+bg+" align='left' >"+xh+"</td>\n");//序号
			   
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+(CGMC)+b2+"</td>\n"); //成果名称
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(CGLX)+b2+"</td>\n"); //成果类型
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(PJDW)+b2+"</td>\n"); //评价单位
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(WCRSF)+b2+"</td>\n"); //第一作者身份
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(SZXSJG)+b2+"</td>\n"); //所在学术机构
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(YXBM)+b2+"</td>\n"); //院系部门
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(CHARGENM)+b2+"</td>\n"); //第一作者姓名
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(ZZLX)+b2+"</td>\n"); //著作类型
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(YJLX)+b2+"</td>\n"); //研究类别
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(XMJB)+b2+"</td>\n"); //课题来源(项目级别)
			   sb.append("<td"+bg+" align='left' >"+b1+(joinMan)+b2+"</td>\n"); //其他参与人数
			   
			   sb.append("<td"+bg+" align='left' >"+b1+(aprvStatus)+b2+"</td>\n");
			   
			   if ((STATUS == 0 && AUTOADD == 0) || !WCRSF.equals("本校教师")) {
				   sb.append("<td"+bg+" align='left' ><a href='#' onclick='viewDetail("+ID+");return false;'>详情</a>&nbsp&nbsp");
				   sb.append("<a href='#' onclick='modifyData("+bindId+", "+ID+");return false;'  title='研究报告'><img src='../aws_img/man.gif' border='0'>修改</a>&nbsp&nbsp");
//				   sb.append("<a href='#' onclick='deleteData("+ID+");return false;'>删除</a></td>\n"); //操作
				   sb.append("</td>\n"); //操作
			   } else if (STATUS == 1 || AUTOADD == 1) {   
				   sb.append("<td"+bg+" align='left' ><a href='#' onclick='viewDetail("+ID+");return false;'>详情</a></td>");
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
			   String pageIndexStr = new PageIndex("BNU_HR_YJBG", pageNow, lineCount, lineNumber).toString();
			   String pageIndexStr2 = pageIndexStr.replaceAll("frmMain", "frmYjbg");
			   
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
			return "#研究报告_数据查询异常";
		}finally{
			DBSql.close(conn, ps, rs);
		}
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_HR_YJBG.html"),hashTags);
	}
	
	public String viewDetail(String awsuid, String dataId) {
		StringBuffer response = new StringBuffer();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String CGMC = null;  // 成果名称
		String CGYWMC = null;  // 成果外文名称
		String CGLX = null;  // 成果类型
		String GKLX = null;  // 归口类型
		String PJDW = null;  // 评价单位
		String CNDW = null;  // 采纳单位
		String RDRQ = null;  // 认定日期
		String ZSBH = null;  // 证书编号
		String SUBJECT = null;  // 一级学科
		String SUBJECT2 = null;  // 二级学科
		String WCRSF = null;  // 第一完成人身份
		String SZXSJG = null;  // 所在学术机构
		String YXBM = null;  // 院系部门
		String ZZLX = null;  // 著作类型
		String FBFW = null;  // 发表范围
		String FBSJ = null;  // 发表日期
		String FBND = null;  // 发表年度
		String YJLX = null;  // 研究类别
		String XMJB = null;  // 课题来源(项目级别)
		String CGYYQK = null;  // 成果应用情况
		String SFCN = null;  // 是否司局以上采纳
		
		String CHARGENM = null; // 第一作者姓名
		String CHARGEID = null;   // 第一作者ID
		int CHARGERATE = 0;     // 第一作者工作量/%
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
		   
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			String sql = "select * from BO_YJBGTEMP where ID="+Integer.parseInt(dataId);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {

				CGMC = rs.getString("CGMC")==null ? "" :rs.getString("CGMC"); //成果名称
				CGYWMC = rs.getString("CGYWMC")==null ? "" :rs.getString("CGYWMC"); //成果外文名称
				CGLX = rs.getString("CGLX")==null ? "" :rs.getString("CGLX"); //成果类型
				GKLX = rs.getString("GKLX")==null ? "" :rs.getString("GKLX"); //归口类型
				PJDW = rs.getString("PJDW")==null ? "" :rs.getString("PJDW"); //评价单位
				CNDW = rs.getString("CNDW")==null ? "" :rs.getString("CNDW"); //采纳单位
				RDRQ = rs.getString("RDRQ")==null ? "" :rs.getString("RDRQ"); //认定日期
				ZSBH = rs.getString("ZSBH")==null ? "" :rs.getString("ZSBH"); //证书编号
				SUBJECT = rs.getString("SUBJECT")==null ? "" :rs.getString("SUBJECT"); //一级学科
				SUBJECT2 = rs.getString("SUBJECT2")==null ? "" :rs.getString("SUBJECT2"); //二级学科
				WCRSF = rs.getString("WCRSF")==null ? "" :rs.getString("WCRSF"); //第一完成人身份
				SZXSJG = rs.getString("SZXSJG")==null ? "" :rs.getString("SZXSJG"); //所在学术机构
				YXBM = rs.getString("YXBM")==null ? "" :rs.getString("YXBM"); //院系部门
				ZZLX = rs.getString("ZZLX")==null ? "" :rs.getString("ZZLX"); //著作类型
				FBFW = rs.getString("FBFW")==null ? "" :rs.getString("FBFW"); //发表范围
				FBSJ = rs.getString("FBSJ")==null ? "" :rs.getString("FBSJ"); //发表日期
				FBND = rs.getString("FBND")==null ? "" :rs.getString("FBND"); //发表年度
				YJLX = rs.getString("YJLX")==null ? "" :rs.getString("YJLX"); //研究类别
				XMJB = rs.getString("XMJB")==null ? "" :rs.getString("XMJB"); //课题来源(项目级别)
				CGYYQK = rs.getString("CGYYQK")==null ? "" :rs.getString("CGYYQK"); //成果应用情况
				SFCN = rs.getString("SFCN")==null ? "" :rs.getString("SFCN"); //是否司局以上采纳
				
				if(FBSJ!=null && FBSJ.length()>=10) {
					FBSJ = FBSJ.substring(0,10);
				}
				if(RDRQ!=null && RDRQ.length()>=10) {
					RDRQ = RDRQ.substring(0,10);
				}
				
				CHARGENM = rs.getString("CHARGENM")==null?"":rs.getString("CHARGENM"); //第一作者姓名
				CHARGEID = rs.getString("CHARGEID")==null?"":rs.getString("CHARGEID"); //第一作者ID
				CHARGERATE = rs.getInt("CHARGERATE"); //第一作者工作量/%
				
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
				   
			}
			ps.close();
			rs.close();
		}catch(Exception e) {
			e.printStackTrace(System.err);
		}finally {
			DBSql.close(conn, ps, rs);
		}
		
		
		String	CGMCBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + CGMC + "'>";
		String	CGYWMCBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=15 value='" + CGYWMC + "'>";
		String	CGLXBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=16 value='" + CGLX + "'>";
		String	GKLXBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=17 value='" + GKLX + "'>";
		String	PJDWBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=18 value='" + PJDW + "'>";
		String	CNDWBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=19 value='" + CNDW + "'>";
		String	RDRQBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=20 value='" + RDRQ + "'>";
		String	ZSBHBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=21 value='" + ZSBH + "'>";
		String	SUBJECTBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=22 value='" + SUBJECT + "'>";
		String	SUBJECT2Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=23 value='" + SUBJECT2 + "'>";
		String	WCRSFBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=24 value='" + WCRSF + "'>";
		String	SZXSJGBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=25 value='" + SZXSJG + "'>";
		String	YXBMBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=26 value='" + YXBM + "'>";
		String	ZZLXBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=27 value='" + ZZLX + "'>";
		String	FBFWBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=28 value='" + FBFW + "'>";
		String	FBSJBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=29 value='" + FBSJ + "'>";
		String	FBNDBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + FBND + "'>";
		String	YJLXBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=31 value='" + YJLX + "'>";
		String	XMJBBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=32 value='" + XMJB + "'>";
		String	CGYYQKBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=33 value='" + CGYYQK + "'>";
		String	SFCNBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=34 value='" + SFCN + "'>";
		
		String CHARGENMBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + CHARGENM + "'>";
		String CHARGEIDBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + CHARGEID + "'>";
		String CHARGERATEBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CHARGERATE +"%" + "'>";
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
		
		//table
		response.append("<table width=98% height=90% align=center border=0 cellspacing=0 cellpadding=0>");
		response.append("<tr><td><b><font size='3'>数据详细：</font></b><br><br>");
		
		response.append("<table width=98% align=center border=0 cellspacing=0 cellpadding=0>");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>成果名称: </td><td width=30%>").append(CGMCBuffer).append("</td>\n");
		response.append("<td width=20% align=left>成果外文名称: </td><td width=30%>").append(CGYWMCBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>成果类型: </td><td width=30%>").append(CGLXBuffer).append("</td>\n");
		response.append("<td width=20% align=left>归口类型: </td><td width=30%>").append(GKLXBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>评价单位: </td><td width=30%>").append(PJDWBuffer).append("</td>\n");
		response.append("<td width=20% align=left>采纳单位: </td><td width=30%>").append(CNDWBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>认定日期: </td><td width=30%>").append(RDRQBuffer).append("</td>\n");
		response.append("<td width=20% align=left>证书编号: </td><td width=30%>").append(ZSBHBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>一级学科: </td><td width=30%>").append(SUBJECTBuffer).append(" ").append(SUBJECT2Buffer).append("</td>\n");
		response.append("<td width=20% align=left>第一完成人身份: </td><td width=30%>").append(WCRSFBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>第一作者姓名：</td><td width=30%>").append(CHARGENMBuffer).append("ID:").append(CHARGEIDBuffer).append("</td>\n");
		response.append("<td width=20% align=left>工作量/%：</td><td width=30%>").append(CHARGERATEBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>所在学术机构: </td><td width=30%>").append(SZXSJGBuffer).append("</td>\n");
		response.append("<td width=20% align=left>院系部门: </td><td width=30%>").append(YXBMBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>著作类型: </td><td width=30%>").append(ZZLXBuffer).append("</td>\n");
		response.append("<td width=20% align=left>发表范围: </td><td width=30%>").append(FBFWBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>发表日期: </td><td width=30%>").append(FBSJBuffer).append("</td>\n");
		response.append("<td width=20% align=left>发表年度: </td><td width=30%>").append(FBNDBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>研究类别: </td><td width=30%>").append(YJLXBuffer).append("</td>\n");
		response.append("<td width=20% align=left>课题来源(项目级别): </td><td width=30%>").append(XMJBBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>成果应用情况: </td><td width=30%>").append(CGYYQKBuffer).append("</td>\n");
		response.append("<td width=20% align=left>是否司局以上采纳: </td><td width=30%>").append(SFCNBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>参与人1：</td><td width=30%>").append(NAME1Buffer).append("ID:").append(ID1Buffer).append("</td>\n");
		response.append("<td width=20% align=left>工作量/%：</td><td width=30%>").append(RATE1Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>参与人2：</td><td width=30%>").append(NAME2Buffer).append("ID:").append(ID2Buffer).append("</td>\n");
		response.append("<td width=20% align=left>工作量/%：</td><td width=30%>").append(RATE2Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>参与人3：</td><td width=30%>").append(NAME3Buffer).append("ID:").append(ID3Buffer).append("</td>\n");
		response.append("<td width=20% align=left>工作量/%：</td><td width=30%>").append(RATE3Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>参与人4：</td><td width=30%>").append(NAME4Buffer).append("ID:").append(ID4Buffer).append("</td>\n");
		response.append("<td width=20% align=left>工作量/%：</td><td width=30%>").append(RATE4Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>参与人5：</td><td width=30%>").append(NAME5Buffer).append("ID:").append(ID5Buffer).append("</td>\n");
		response.append("<td width=20% align=left>工作量/%：</td><td width=30%>").append(RATE5Buffer).append("</td>\n");
		response.append("</tr>\n");
		
		response.append("</td></tr></table>");
		
		return AjaxUtil.responseDialog("研究报告信息", 180, 70, 800, 500, response.toString(), "" ); //left,top,width,height
	}

	public String deleteData(String awsuid,String dataId) {
		BnuMainWeb web = new BnuMainWeb(super.getContext());
		Connection conn = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(false);
			
			String copyDataStr = DBSql.getString(conn, "SELECT COPYDATAID FROM BO_YJBGTEMP WHERE ID="+Integer.parseInt(dataId), "COPYDATAID");
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
					String deleteSql = "delete from BO_YJBGTEMP where ID ="+copyDataIdInt;
					DBSql.executeUpdate(deleteSql);
				}
			}
			
			String sql = null;
			sql = "delete from  BO_YJBGTEMP where CHARGEID='"+awsuid+"' and ID="+Integer.parseInt(dataId);
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
	
	public String unlockData(String awsuid,String dataId) {
		BnuMainWeb web = new BnuMainWeb(super.getContext());
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			
			String sql = "select * from BO_YJBGTEMP where id="+Integer.parseInt(dataId);
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
			processInstanceId = WorkflowInstanceAPI.getInstance().createProcessInstance("007b23151fd6ea094d1dade015af3e4f", loginId, "研究报告申请");
			int[] processTaskInstanceIds = WorkflowTaskInstanceAPI.getInstance().createProcessTaskInstance(loginId, processInstanceId, SynType.synchronous, PriorityType.hight, 1, loginId, "研究报告申请", false, 0);
			
			String updateSql = "update BO_YJBGTEMP set BINDID="+processInstanceId+", STATUS=0 WHERE ID="+Integer.parseInt(dataId);
			DBSql.executeUpdate(conn, updateSql);
			
			CalMark calMark = new CalMark();
//			calMark.reduceFinalMark(loginId, "YJBG", finalMark);
			calMark.reduceFinalMarkWithYear(loginId, "YJBG", finalMark, datayear);
			
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

