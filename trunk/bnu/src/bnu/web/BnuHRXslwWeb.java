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

public class BnuHRXslwWeb extends ActionsoftWeb {
	/**
	 * 构造方法
	 * @param userContext 用户上下文对象
	 */
	public BnuHRXslwWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造方法
	 */
	public BnuHRXslwWeb() {
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
//		String sqlw = "select * from BO_XSLWTEMP where  CHARGEID='"+awsuid+"' order by CREATEDATE desc";
//		String sqlc = "select count(*) c from BO_XSLWTEMP where  CHARGEID='"+awsuid+"'";
		String sqlw = " select * from (" +
				" select * from BO_XSLWTEMP where  CHARGEID='"+awsuid+"'  and (AUTOADD is null or AUTOADD=0) " +
				" union select * from BO_XSLWTEMP where  ID1='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_XSLWTEMP where  ID2='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_XSLWTEMP where  ID3='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_XSLWTEMP where  ID4='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_XSLWTEMP where  ID5='"+awsuid+"' and AUTOADD=1 " +
				") order by CREATEDATE desc";
		String sqlc = " select count(*) c from (" +
				" select * from BO_XSLWTEMP where  CHARGEID='"+awsuid+"'  and (AUTOADD is null or AUTOADD=0) " +
				" union select * from BO_XSLWTEMP where  ID1='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_XSLWTEMP where  ID2='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_XSLWTEMP where  ID3='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_XSLWTEMP where  ID4='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_XSLWTEMP where  ID5='"+awsuid+"' and AUTOADD=1 )";
		
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String bg = "",bg1 = "",b1 = "", b2 = "";
		bg = " style=\"padding:5px;\"";  
		bg1 = "../aws_img/report-bg-blue2.gif";
		
		String LWMC = null;          // 论文名称
		String LWYWMC = null;     // 论文英文名称
		String LWLX = null;   // 论文类型
		String WCXS = null; // 完成形式
		String CHARGESF = null;   // 第一作者身份
		String SSYX = null; // 所属院系/部门 
		String CHARGENM = null; // 第一作者姓名
		String CHARGEID = null;   // 第一作者ID
		int CHARGERATE = 0;     // 第一作者工作量/%
		String GKLX = null;          // 归口类型
		String FBKW = null; // 发表刊物
		String FBFW = null;   // 发表范围
		String KWJB = null; // 刊物级别
		String NIAN = null;   // 年
		String JUAN = null; // 卷
		String QH = null;   // 期号
		String YM = null;   // 页码
		String LWYZ = null;     // 论文语种
		String SFYW = null; // 是否译文
		String LWYYQK = null; // 论文应用情况
		String YJLX = null;   // 研究类型
		String SFZZ = null; // 是否转载
		String ZZCC = null; // 转载出处

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
//			   SQL = "select top "+lineCount+" * from BO_XSLWTEMP " +
//			   		"where CHARGEID='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_XSLWTEMP  where CHARGEID='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }else{
		       SQL = "select * from (select rownum r,t1.* from ("+sqlw+") t1 where rownum<="+(start+lineNumber-1)+") t2 where t2.r>="+start; //oracle
			   //sql server
//			   SQL = "select top "+(start+lineNumber-1)+" * from BO_XSLWTEMP " +
//			   		"where CHARGEID='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_XSLWTEMP  where CHARGEID='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }
		   ps=conn.prepareStatement(SQL);
		   rs=ps.executeQuery();
		   while(rs.next()){
			   int ID = rs.getInt("ID");
			   int bindId = rs.getInt("BINDID");

			   LWMC = rs.getString("LWMC")==null?"":rs.getString("LWMC"); //论文名称
			   LWYWMC = rs.getString("LWYWMC")==null?"":rs.getString("LWYWMC"); //论文英文名称
			   LWLX = rs.getString("LWLX")==null?"":rs.getString("LWLX"); //论文类型
			   WCXS = rs.getString("WCXS")==null?"":rs.getString("WCXS"); //完成形式
			   CHARGESF = rs.getString("CHARGESF")==null?"":rs.getString("CHARGESF"); //第一作者身份
			   SSYX = rs.getString("SSYX")==null?"":rs.getString("SSYX"); //所属院系/部门 
			   CHARGENM = rs.getString("CHARGENM")==null?"":rs.getString("CHARGENM"); //第一作者姓名
			   CHARGEID = rs.getString("CHARGEID")==null?"":rs.getString("CHARGEID"); //第一作者ID
			   CHARGERATE = rs.getInt("CHARGERATE"); //第一作者工作量/%
			   GKLX = rs.getString("GKLX")==null?"":rs.getString("GKLX"); //归口类型
			   FBKW = rs.getString("FBKW")==null?"":rs.getString("FBKW"); //发表刊物
			   FBFW = rs.getString("FBFW")==null?"":rs.getString("FBFW"); //发表范围
			   KWJB = rs.getString("KWJB")==null?"":rs.getString("KWJB"); //刊物级别
			   NIAN = rs.getString("NIAN")==null?"":rs.getString("NIAN"); //年
			   JUAN = rs.getString("JUAN")==null?"":rs.getString("JUAN"); //卷
			   QH = rs.getString("QH")==null?"":rs.getString("QH"); //期号
			   YM = rs.getString("YM")==null?"":rs.getString("YM"); //页码
			   LWYZ = rs.getString("LWYZ")==null?"":rs.getString("LWYZ"); //论文语种
			   SFYW = rs.getString("SFYW")==null?"":rs.getString("SFYW"); //是否译文
			   LWYYQK = rs.getString("LWYYQK")==null?"":rs.getString("LWYYQK"); //论文应用情况
			   YJLX = rs.getString("YJLX")==null?"":rs.getString("YJLX"); //研究类型
			   SFZZ = rs.getString("SFZZ")==null?"":rs.getString("SFZZ"); //是否转载
			   ZZCC = rs.getString("ZZCC")==null?"":rs.getString("ZZCC"); //转载出处

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
			   
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+(LWMC)+b2+"</td>\n"); //论文名称
//			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(LWYWMC)+b2+"</td>\n"); //论文英文名称
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(LWLX)+b2+"</td>\n"); //论文类型
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(WCXS)+b2+"</td>\n"); //完成形式
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(CHARGESF)+b2+"</td>\n"); //第一作者身份
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(SSYX)+b2+"</td>\n"); //所属院系/部门
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(CHARGENM)+b2+"</td>\n"); //第一作者姓名
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(GKLX)+b2+"</td>\n"); //归口类型
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(FBKW)+b2+"</td>\n"); //发表刊物
//			   sb.append("<td"+bg+" align='left' >"+b1+(LWYZ)+b2+"</td>\n"); //论文语种
//			   sb.append("<td"+bg+" align='left' >"+b1+(SFYW)+b2+"</td>\n"); //是否译文
//			   sb.append("<td"+bg+" align='left' >"+b1+(LWYYQK)+b2+"</td>\n"); //论文应用情况
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(YJLX)+b2+"</td>\n"); //研究类型
			   sb.append("<td"+bg+" align='left' >"+b1+(joinMan)+b2+"</td>\n"); //其他参与人数
			   
			   sb.append("<td"+bg+" align='left' >"+b1+(aprvStatus)+b2+"</td>\n");
			   
			   if ((STATUS == 0 && AUTOADD == 0) || !CHARGESF.equals("本校教师")) {
				   sb.append("<td"+bg+" align='left' ><a href='#' onclick='viewDetail("+ID+");return false;'>详情</a>&nbsp&nbsp");
				   sb.append("<a href='#' onclick='modifyData("+bindId+", "+ID+");return false;'  title='学术论文'><img src='../aws_img/man.gif' border='0'>修改</a>&nbsp&nbsp");
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
			   String pageIndexStr = new PageIndex("BNU_HR_XSLW", pageNow, lineCount, lineNumber).toString();
			   String pageIndexStr2 = pageIndexStr.replaceAll("frmMain", "frmXslw");
			   
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
			return "#成果获奖_数据查询异常";
		}finally{
			DBSql.close(conn, ps, rs);
		}
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_HR_XSLW.html"),hashTags);
	}
	
	public String viewDetail(String awsuid, String dataId) {
		StringBuffer response = new StringBuffer();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String LWMC = null;          // 论文名称
		String LWYWMC = null;     // 论文英文名称
		String LWLX = null;   // 论文类型
		String WCXS = null; // 完成形式
		String CHARGESF = null;   // 第一作者身份
		String SSYX = null; // 所属院系/部门 
		String CHARGENM = null; // 第一作者姓名
		String CHARGEID = null;   // 第一作者ID
		int CHARGERATE = 0;     // 第一作者工作量/%
		String GKLX = null;          // 归口类型
		String FBKW = null; // 发表刊物
		String FBFW = null;   // 发表范围
		String KWJB = null; // 刊物级别
		String NIAN = null;   // 年
		String JUAN = null; // 卷
		String QH = null;   // 期号
		String YM = null;   // 页码
		String LWYZ = null;     // 论文语种
		String SFYW = null; // 是否译文
		String LWYYQK = null; // 论文应用情况
		String YJLX = null;   // 研究类型
		String SFZZ = null; // 是否转载
		String ZZCC = null; // 转载出处
		String ZS = "";
		String DYZZDW = "";
		String FBSJ = "";
		String FBND = "";
		String YJLY = "";
		String KTLY = "";
		String ZZLX = "";
		String XGXKLY = "";
		String KWLB = "";
		String LWYZ2 = "";
		String XKLY = "";
		
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
			String sql = "select * from BO_XSLWTEMP where ID="+Integer.parseInt(dataId);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {

				LWMC = rs.getString("LWMC")==null?"":rs.getString("LWMC"); //论文名称
				LWYWMC = rs.getString("LWYWMC")==null?"":rs.getString("LWYWMC"); //论文英文名称
				LWLX = rs.getString("LWLX")==null?"":rs.getString("LWLX"); //论文类型
				WCXS = rs.getString("WCXS")==null?"":rs.getString("WCXS"); //完成形式
				CHARGESF = rs.getString("CHARGESF")==null?"":rs.getString("CHARGESF"); //第一作者身份
				SSYX = rs.getString("SSYX")==null?"":rs.getString("SSYX"); //所属院系/部门 
				CHARGENM = rs.getString("CHARGENM")==null?"":rs.getString("CHARGENM"); //第一作者姓名
				CHARGEID = rs.getString("CHARGEID")==null?"":rs.getString("CHARGEID"); //第一作者ID
				CHARGERATE = rs.getInt("CHARGERATE"); //第一作者工作量/%
				GKLX = rs.getString("GKLX")==null?"":rs.getString("GKLX"); //归口类型
				FBKW = rs.getString("FBKW")==null?"":rs.getString("FBKW"); //发表刊物
				FBFW = rs.getString("FBFW")==null?"":rs.getString("FBFW"); //发表范围
				KWJB = rs.getString("KWJB")==null?"":rs.getString("KWJB"); //刊物级别
				NIAN = rs.getString("NIAN")==null?"":rs.getString("NIAN"); //年
				JUAN = rs.getString("JUAN")==null?"":rs.getString("JUAN"); //卷
				QH = rs.getString("QH")==null?"":rs.getString("QH"); //期号
				YM = rs.getString("YM")==null?"":rs.getString("YM"); //页码
				LWYZ = rs.getString("LWYZ")==null?"":rs.getString("LWYZ"); //论文语种
				SFYW = rs.getString("SFYW")==null?"":rs.getString("SFYW"); //是否译文
				LWYYQK = rs.getString("LWYYQK")==null?"":rs.getString("LWYYQK"); //论文应用情况
				YJLX = rs.getString("YJLX")==null?"":rs.getString("YJLX"); //研究类型
				SFZZ = rs.getString("SFZZ")==null?"":rs.getString("SFZZ"); //是否转载
				ZZCC = rs.getString("ZZCC")==null?"":rs.getString("ZZCC"); //转载出处
				ZS = rs.getString("ZS")==null?"":rs.getString("ZS").trim();
				DYZZDW = rs.getString("DYZZDW")==null?"":rs.getString("DYZZDW").trim();
				FBSJ = rs.getString("FBSJ")==null?"":rs.getString("FBSJ").trim();
				FBND = rs.getString("FBND")==null?"":rs.getString("FBND").trim();
				YJLY = rs.getString("YJLY")==null?"":rs.getString("YJLY").trim();
				KTLY = rs.getString("KTLY")==null?"":rs.getString("KTLY").trim();
				ZZLX = rs.getString("ZZLX")==null?"":rs.getString("ZZLX").trim();
				XGXKLY = rs.getString("XGXKLY")==null?"":rs.getString("XGXKLY").trim();
				KWLB = rs.getString("KWLB")==null?"":rs.getString("KWLB").trim();
				LWYZ2 = rs.getString("LWYZ2")==null?"":rs.getString("LWYZ2").trim();
				XKLY = rs.getString("XKLY")==null?"":rs.getString("XKLY").trim();
				
				if(FBSJ!=null && FBSJ.length()>=10) {
					FBSJ = FBSJ.substring(0,10);
				}
				
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
		
		
		String LWMCBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + LWMC + "'>";
		String LWYWMCBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + LWYWMC + "'>";
		String LWLXBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + LWLX + "'>";
		String WCXSBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + WCXS + "'>";
		String CHARGESFBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CHARGESF + "'>";
		String SSYXBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + SSYX + "'>";
		String CHARGENMBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + CHARGENM + "'>";
		String CHARGEIDBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + CHARGEID + "'>";
		String CHARGERATEBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CHARGERATE +"%" + "'>";
		String GKLXBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + GKLX + "'>";
		String FBKWBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + FBKW + "'>";
		String FBFWBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + FBFW + "'>";
		String KWJBBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + KWJB + "'>";
		String NIANBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + NIAN + "'>";
		String JUANBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + JUAN + "'>";
		String QHBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + QH + "'>";
		String YMBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + YM + "'>";
		String LWYZBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + LWYZ + "'>";
		String SFYWBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + SFYW + "'>";
		String LWYYQKBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + LWYYQK + "'>";
		String YJLXBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + YJLX + "'>";
		String SFZZBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + SFZZ+ "'>";
		String ZZCCBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + ZZCC + "'>";
		String ZSBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + ZS + "'>";
		String DYZZDWBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + DYZZDW + "'>";
		String FBSJBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + FBSJ + "'>";
		String FBNDBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + FBND + "'>";
		String YJLYBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + YJLY + "'>";
		String KTLYBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + KTLY + "'>";
		String ZZLXBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + ZZLX + "'>";
		String XGXKLYBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + XGXKLY + "'>";
		String KWLBBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + KWLB + "'>";
		String LWYZ2Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + LWYZ2 + "'>";
		String XKLYBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + XKLY + "'>";
		
		
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
		response.append("<td width=20% align=left>论文名称：</td><td width=35%>").append(LWMCBuffer).append("</td>\n");
		response.append("<td width=20% align=left>论文英文名称：</td><td width=35%>").append(LWYWMCBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>论文类型：</td><td width=35%>").append(LWLXBuffer).append("</td>\n");
		response.append("<td width=20% align=left>项目级别：</td><td width=35%>").append(KTLYBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>第一作者身份：</td><td width=35%>").append(CHARGESFBuffer).append("</td>\n");
		response.append("<td width=20% align=left>第一作者单位：</td><td width=35%>").append(DYZZDWBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>所属院系/部门 ：</td><td width=35%>").append(SSYXBuffer).append("</td>\n");
		response.append("<td width=20% align=left>完成形式：</td><td width=35%>").append(WCXSBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>发表时间：</td><td width=35%>").append(FBSJBuffer).append("</td>\n");
		response.append("<td width=20% align=left>发表年度：</td><td width=35%>").append(FBNDBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>第一作者姓名：</td><td width=35%>").append(CHARGENMBuffer).append("ID:").append(CHARGEIDBuffer).append("</td>\n");
		response.append("<td width=20% align=left>工作量/%：</td><td width=35%>").append(CHARGERATEBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>发表刊物：</td><td width=35%>").append(FBKWBuffer).append("</td>\n");
		response.append("<td width=20% align=left>发表范围：</td><td width=35%>").append(FBFWBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>刊物级别：</td><td width=35%>").append(KWJBBuffer).append("</td>\n");
		response.append("<td width=20% align=left>刊物类别：</td><td width=35%>").append(KWLBBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>研究类型：</td><td width=35%>").append(YJLXBuffer).append("</td>\n");
		response.append("<td width=20% align=left>研究领域：</td><td width=35%>").append(YJLYBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>著作类型：</td><td width=35%>").append(ZZLXBuffer).append("</td>\n");
		response.append("<td width=20% align=left>相关学科领域：</td><td width=35%>").append(XGXKLYBuffer).append(" ").append(XKLYBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>字数/千字：</td><td width=35%>").append(ZSBuffer).append("</td>\n");
		response.append("<td width=20% align=left>年/卷/期号</td><td width=35%>").append("年").append(NIANBuffer).append("卷").append(JUANBuffer).append("期号").append(QHBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>页码：</td><td width=35%>").append(YMBuffer).append("</td>\n");
		response.append("<td width=20% align=left>论文语种：</td><td width=35%>").append(LWYZBuffer).append(" ").append(LWYZ2Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>归口类型：</td><td width=35%>").append(GKLXBuffer).append("</td>\n");
		response.append("<td width=20% align=left>论文应用情况：</td><td width=35%>").append(LWYYQKBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>是否译文：</td><td width=35%>").append(SFYWBuffer).append("</td>\n");
		response.append("<td width=20% align=left>是否转载：</td><td width=35%>").append(SFZZBuffer).append("转载出处:").append(ZZCCBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>参与人1：</td><td width=35%>").append(NAME1Buffer).append("ID:").append(ID1Buffer).append("</td>\n");
		response.append("<td width=20% align=left>工作量/%：</td><td width=35%>").append(RATE1Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>参与人2：</td><td width=35%>").append(NAME2Buffer).append("ID:").append(ID2Buffer).append("</td>\n");
		response.append("<td width=20% align=left>工作量/%：</td><td width=35%>").append(RATE2Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>参与人3：</td><td width=35%>").append(NAME3Buffer).append("ID:").append(ID3Buffer).append("</td>\n");
		response.append("<td width=20% align=left>工作量/%：</td><td width=35%>").append(RATE3Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>参与人4：</td><td width=35%>").append(NAME4Buffer).append("ID:").append(ID4Buffer).append("</td>\n");
		response.append("<td width=20% align=left>工作量/%：</td><td width=35%>").append(RATE4Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>参与人5：</td><td width=35%>").append(NAME5Buffer).append("ID:").append(ID5Buffer).append("</td>\n");
		response.append("<td width=20% align=left>工作量/%：</td><td width=35%>").append(RATE5Buffer).append("</td>\n");
		response.append("</tr>\n");
		
		response.append("</td></tr></table>");
		
		return AjaxUtil.responseDialog("学术论文信息", 180, 70, 800, 500, response.toString(), "" ); //left,top,width,height
	}

	public String deleteData(String awsuid,String dataId) {
		BnuMainWeb web = new BnuMainWeb(super.getContext());
		Connection conn = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(false);
			
			String copyDataStr = DBSql.getString(conn, "SELECT COPYDATAID FROM BO_XSLWTEMP WHERE ID="+Integer.parseInt(dataId), "COPYDATAID");
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
					String deleteSql = "delete from BO_XSLWTEMP where ID ="+copyDataIdInt;
					DBSql.executeUpdate(deleteSql);
				}
			}
			
			String sql = null;
			sql = "delete from  BO_XSLWTEMP where CHARGEID='"+awsuid+"' and ID="+Integer.parseInt(dataId);
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
			
			String sql = "select * from BO_XSLWTEMP where id="+Integer.parseInt(dataId);
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
			processInstanceId = WorkflowInstanceAPI.getInstance().createProcessInstance("007b23151fd6ea094d1dade015af3e4f", loginId, "学术论文申请");
			int[] processTaskInstanceIds = WorkflowTaskInstanceAPI.getInstance().createProcessTaskInstance(loginId, processInstanceId, SynType.synchronous, PriorityType.hight, 1, loginId, "学术论文申请", false, 0);
			
			String updateSql = "update BO_XSLWTEMP set BINDID="+processInstanceId+", STATUS=0 WHERE ID="+Integer.parseInt(dataId);
			DBSql.executeUpdate(conn, updateSql);
			
			CalMark calMark = new CalMark();
//			calMark.reduceFinalMark(loginId, "XSLW", finalMark);
			calMark.reduceFinalMarkWithYear(loginId, "XSLW", finalMark, datayear);
			
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

