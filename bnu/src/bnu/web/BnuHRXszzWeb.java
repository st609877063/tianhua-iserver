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

public class BnuHRXszzWeb extends ActionsoftWeb {
	/**
	 * 构造方法
	 * @param userContext 用户上下文对象
	 */
	public BnuHRXszzWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造方法
	 */
	public BnuHRXszzWeb() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 学术著作--查询结果
	 * @param pageNow    页码
	 * @param lineNumber 每页行数
	 * @param awsuid     AWS帐号
	 * @return
	 */
	public String getReport(int pageNow,int lineNumber,String awsuid) {
		//项目多用户参与，需共享数据
		StringBuffer sb = new StringBuffer();
		Hashtable hashTags=new Hashtable();
//		String sqlw = "select * from BO_XSZZTEMP where 1=1 and CHARGEID='"+awsuid+"' order by CREATEDATE desc";
//		String sqlc = "select count(*) c from BO_XSZZTEMP where 1=1 and CHARGEID='"+awsuid+"'";
		String sqlw = " select * from (" +
				" select * from BO_XSZZTEMP where  CHARGEID='"+awsuid+"'  and (AUTOADD is null or AUTOADD=0) " +
				" union select * from BO_XSZZTEMP where  ID1='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_XSZZTEMP where  ID2='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_XSZZTEMP where  ID3='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_XSZZTEMP where  ID4='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_XSZZTEMP where  ID5='"+awsuid+"' and AUTOADD=1 " +
				") order by CREATEDATE desc";
		String sqlc = " select count(*) c from (" +
				" select * from BO_XSZZTEMP where  CHARGEID='"+awsuid+"'  and (AUTOADD is null or AUTOADD=0) " +
				" union select * from BO_XSZZTEMP where  ID1='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_XSZZTEMP where  ID2='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_XSZZTEMP where  ID3='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_XSZZTEMP where  ID4='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_XSZZTEMP where  ID5='"+awsuid+"' and AUTOADD=1 )";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String bg = "",bg1 = "",b1 = "", b2 = "";
		bg = " style=\"padding:5px;\"";  
		bg1 = "../aws_img/report-bg-blue2.gif";
		
		String ZZMC = null;          // 著作名称
		String ZZYWMC = null;     // 著作英文名称
		String CHARGENM = null; // 第一作者姓名
		String CHARGEID = null;   // 第一作者ID
		int CHARGERATE = 0;     // 第一作者工作量/%
		String YXMC = null;   // 院系名称
		String GKLX = null;          // 归口类型
		String YZ = null;     // 语种
		String WCXS = null; // 完成形式
		String CHARGESF = null;   // 第一作者身份
		String ISBN = null; // ISBN
		String YJLX = null;   // 研究类型
		String CBSMC = null; // 出版社名称
		String CBRQ = null;   // 出版日期
		String ATTACH = null; // 扫描件

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
//			   SQL = "select top "+lineCount+" * from BO_XSZZTEMP " +
//			   		"where CHARGEID='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_XSZZTEMP  where CHARGEID='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }else{
		       SQL = "select * from (select rownum r,t1.* from ("+sqlw+") t1 where rownum<="+(start+lineNumber-1)+") t2 where t2.r>="+start; //oracle
			   //sql server
//			   SQL = "select top "+(start+lineNumber-1)+" * from BO_XSZZTEMP " +
//			   		"where CHARGEID='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_XSZZTEMP  where CHARGEID='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }
		   ps=conn.prepareStatement(SQL);
		   rs=ps.executeQuery();
		   while(rs.next()){
			   int ID = rs.getInt("ID");
			   int bindId = rs.getInt("BINDID");
			   

			   ZZMC = rs.getString("ZZMC")==null?"":rs.getString("ZZMC"); //著作名称
			   ZZYWMC = rs.getString("ZZYWMC")==null?"":rs.getString("ZZYWMC"); //著作英文名称
			   CHARGENM = rs.getString("CHARGENM")==null?"":rs.getString("CHARGENM"); //第一作者姓名
			   CHARGEID = rs.getString("CHARGEID")==null?"":rs.getString("CHARGEID"); //第一作者ID
			   CHARGERATE = rs.getInt("CHARGERATE"); //第一作者工作量/%
			   YXMC = rs.getString("YXMC")==null?"":rs.getString("YXMC"); //院系名称
			   GKLX = rs.getString("GKLX")==null?"":rs.getString("GKLX"); //归口类型
			   YZ = rs.getString("YZ")==null?"":rs.getString("YZ"); //语种
			   WCXS = rs.getString("WCXS")==null?"":rs.getString("WCXS"); //完成形式
			   CHARGESF = rs.getString("CHARGESF")==null?"":rs.getString("CHARGESF"); //第一作者身份
			   ISBN = rs.getString("ISBN")==null?"":rs.getString("ISBN"); //ISBN
			   YJLX = rs.getString("YJLX")==null?"":rs.getString("YJLX"); //研究类型
			   CBSMC = rs.getString("CBSMC")==null?"":rs.getString("CBSMC"); //出版社名称
			   CBRQ = rs.getString("CBRQ")==null?"":rs.getString("CBRQ"); //出版日期
			   ATTACH = rs.getString("ATTACH")==null?"":rs.getString("ATTACH"); //扫描件

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
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all'><input type='checkbox' name='checkData' id='"+ID+"'/></td>\n");//checkbox
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all' display='hidden'>"+ID+"</td>\n");//ID
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+xh+"</td>\n");//序号
			   
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+(ZZMC)+b2+"</td>\n"); //著作名称
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(ZZYWMC)+b2+"</td>\n"); // 著作英文名称
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CHARGENM)+b2+"</td>\n"); //第一作者姓名
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CHARGESF)+b2+"</td>\n"); // 第一作者身份
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(YXMC)+b2+"</td>\n"); //院系名称
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(GKLX)+b2+"</td>\n"); //归口类型
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(YZ)+b2+"</td>\n"); // 语种
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(WCXS)+b2+"</td>\n"); // 完成形式
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(ISBN)+b2+"</td>\n"); //ISBN
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(YJLX)+b2+"</td>\n"); //研究类型
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CBSMC)+b2+"</td>\n"); //出版社名称
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CBRQ)+b2+"</td>\n"); //出版日期
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(joinMan)+b2+"</td>\n"); //其他参与人数
			   
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(aprvStatus)+b2+"</td>\n");
			   if ((STATUS == 0 && AUTOADD == 0) || !CHARGESF.equals("本校教师")) {
				   sb.append("<td"+bg+" align='left' style='word-break:break-all'><a href='#' onclick='viewDetail("+ID+");return false;'>详情</a>&nbsp&nbsp");
				   sb.append("<a href='#' onclick='modifyData("+bindId+", "+ID+");return false;'  title='学术著作'><img src='../aws_img/man.gif' border='0'>修改</a>&nbsp&nbsp");
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
			   String pageIndexStr = new PageIndex("BNU_HR_XSZZ", pageNow, lineCount, lineNumber).toString();
			   String pageIndexStr2 = pageIndexStr.replaceAll("frmMain", "frmXszz");
			   
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
			return "#学术著作_数据查询异常";
		}finally{
			DBSql.close(conn, ps, rs);
		}
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_HR_XSZZ.html"),hashTags);
	}
	
	public String viewDetail(String awsuid, String dataId) {
		StringBuffer response = new StringBuffer();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String ZZMC = null;          // 著作名称
		String ZZYWMC = null;     // 著作英文名称
		String CHARGENM = null; // 第一作者姓名
		String CHARGEID = null;   // 第一作者ID
		int CHARGERATE = 0;     // 第一作者工作量/%
		String YXMC = null;   // 院系名称
		String GKLX = null;          // 归口类型
		String YZ = null;     // 语种
		String WCXS = null; // 完成形式
		String CHARGESF = null;   // 第一作者身份
		String ISBN = null; // ISBN
		String YJLX = null;   // 研究类型
		String ZZZS = null; // 著作字数（千字）
		String CBSMC = null; // 出版社名称
		String CBRQ = null;   // 出版日期
		String ATTACH = null; // 扫描件
		String XMLY = null;
		String LZLB = null;
		String DYZZSZDW = null;
		String XKLY = null;
		String XKLY2 = null;
		String CGYYQK = null;
		String YZ2 = null;
		String BRZXZS = null;
		String SFYCWW = null;
		
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
			String sql = "select * from BO_XSZZTEMP where ID="+Integer.parseInt(dataId);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {

				ZZMC = rs.getString("ZZMC")==null?"":rs.getString("ZZMC"); //著作名称
				ZZYWMC = rs.getString("ZZYWMC")==null?"":rs.getString("ZZYWMC"); //著作英文名称
				CHARGENM = rs.getString("CHARGENM")==null?"":rs.getString("CHARGENM"); //第一作者姓名
				CHARGEID = rs.getString("CHARGEID")==null?"":rs.getString("CHARGEID"); //第一作者ID
				CHARGERATE = rs.getInt("CHARGERATE"); //第一作者工作量/%
				YXMC = rs.getString("YXMC")==null?"":rs.getString("YXMC"); //院系名称
				GKLX = rs.getString("GKLX")==null?"":rs.getString("GKLX"); //归口类型
				YZ = rs.getString("YZ")==null?"":rs.getString("YZ"); //语种
				WCXS = rs.getString("WCXS")==null?"":rs.getString("WCXS"); //完成形式
				CHARGESF = rs.getString("CHARGESF")==null?"":rs.getString("CHARGESF"); //第一作者身份
				ISBN = rs.getString("ISBN")==null?"":rs.getString("ISBN"); //ISBN
				YJLX = rs.getString("YJLX")==null?"":rs.getString("YJLX"); //研究类型
				ZZZS = rs.getString("ZZZS")==null?"":rs.getString("ZZZS"); //著作字数（千字）
				CBSMC = rs.getString("CBSMC")==null?"":rs.getString("CBSMC"); //出版社名称
				CBRQ = rs.getString("CBRQ")==null?"":rs.getString("CBRQ"); //出版日期
				ATTACH = rs.getString("ATTACH")==null?"":rs.getString("ATTACH"); //扫描件
				
				XMLY = rs.getString("XMLY")==null?"":rs.getString("XMLY");
				LZLB = rs.getString("LZLB")==null?"":rs.getString("LZLB");
				DYZZSZDW = rs.getString("DYZZSZDW")==null?"":rs.getString("DYZZSZDW");
				XKLY = rs.getString("XKLY")==null?"":rs.getString("XKLY");
				XKLY2 = rs.getString("XKLY2")==null?"":rs.getString("XKLY2");
				CGYYQK = rs.getString("CGYYQK")==null?"":rs.getString("CGYYQK");
				YZ2 = rs.getString("YZ2")==null?"":rs.getString("YZ2");
				BRZXZS = rs.getString("BRZXZS")==null?"":rs.getString("BRZXZS");
				SFYCWW = rs.getString("SFYCWW")==null?"":rs.getString("SFYCWW");
				
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
		
		String ZZMCBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + ZZMC + "'>";
		String ZZYWMCBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + ZZYWMC + "'>";
		String CHARGENMBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + CHARGENM + "'>";
		String CHARGEIDBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + CHARGEID + "'>";
		String CHARGERATEBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CHARGERATE +"%" + "'>";
		String YXMCBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + YXMC + "'>";
		String GKLXBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + GKLX + "'>";
		String YZBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + YZ + "'>";
		String WCXSBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + WCXS + "'>";
		String CHARGESFBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CHARGESF + "'>";
		String ISBNBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + ISBN + "'>";
		String YJLXBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + YJLX + "'>";
		String ZZZSBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + ZZZS + "'>";
		String CBSMCBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CBSMC + "'>";
		String CBRQBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CBRQ + "'>";
		String ATTACHBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + ATTACH + "'>";
		String XMLYBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + XMLY + "'>";
		String LZLBBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + LZLB + "'>";
		String DYZZSZDWBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + DYZZSZDW + "'>";
		String XKLYBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + XKLY + "'>";
		String XKLY2Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + XKLY2 + "'>";
		String CGYYQKBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CGYYQK + "'>";
		String YZ2Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + YZ2 + "'>";
		String BRZXZSBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + BRZXZS + "'>";
		String SFYCWWBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + SFYCWW + "'>";
		
		
		//table
		response.append("<table width=98% height=90% align=center border=0 cellspacing=0 cellpadding=0>");
		response.append("<tr><td><b><font size='3'>数据详细：</font></b><br><br>");
		
		response.append("<table width=98% align=center border=0 cellspacing=0 cellpadding=0>");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>著作名称：</td><td width=35%>").append(ZZMCBuffer).append("</td>\n");
		response.append("<td width=20% align=left>著作英文名称：</td><td width=35%>").append(ZZMCBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>项目级别：</td><td width=35%>").append(XMLYBuffer).append("</td>\n");
		response.append("<td width=20% align=left>论著类别：</td><td width=35%>").append(LZLBBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>第一作者姓名：</td><td width=35%>").append(CHARGENMBuffer).append("ID:").append(CHARGEIDBuffer).append("</td>\n");
		response.append("<td width=20% align=left>工作量/%：</td><td width=35%>").append(CHARGERATEBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>第一作者身份：</td><td width=35%>").append(CHARGESFBuffer).append("</td>\n");
		response.append("<td width=20% align=left>第一作者所在单位：</td><td width=35%>").append(DYZZSZDWBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>院系名称：</td><td width=35%>").append(YXMCBuffer).append("</td>\n");
		response.append("<td width=20% align=left>归口类型：</td><td width=35%>").append(GKLXBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>学科领域：</td><td width=35%>").append(XKLYBuffer).append(" ").append(XKLY2Buffer).append("</td>\n");
		response.append("<td width=20% align=left>研究类型：</td><td width=35%>").append(YJLXBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>完成形式：</td><td width=35%>").append(WCXSBuffer).append("</td>\n");
		response.append("<td width=20% align=left>成果应用情况：</td><td width=35%>").append(CGYYQKBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>出版社名称：</td><td width=35%>").append(CBSMCBuffer).append("</td>\n");
		response.append("<td width=20% align=left>出版日期：</td><td width=35%>").append(CBRQBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>语种：</td><td width=35%>").append(YZBuffer).append(" ").append(YZ2Buffer).append("</td>\n");
		response.append("<td width=20% align=left>ISBN：</td><td width=35%>").append(ISBNBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>著作字数/千：</td><td width=35%>").append(ZZZSBuffer).append("</td>\n");
		response.append("<td width=20% align=left>本人撰写字数/万：</td><td width=35%>").append(BRZXZSBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>是否译成外文：</td><td width=35%>").append(SFYCWWBuffer).append("</td>\n");
		response.append("<td width=20% align=left>扫描件：</td><td width=35%>").append(ATTACHBuffer).append("</td>\n");
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
		
		return AjaxUtil.responseDialog("学术著作信息", 180, 70, 800, 600, response.toString(), "" ); //left,top,width,height
	}

	public String deleteData(String awsuid,String dataId) {
		BnuMainWeb web = new BnuMainWeb(super.getContext());
		Connection conn = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(false);
			

			String copyDataStr = DBSql.getString(conn, "SELECT COPYDATAID FROM BO_XSZZTEMP WHERE ID="+Integer.parseInt(dataId), "COPYDATAID");
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
					String deleteSql = "delete from BO_XSZZTEMP where ID ="+copyDataIdInt;
					DBSql.executeUpdate(deleteSql);
				}
			}
			
			
			String sql = null;
			sql = "delete from  BO_XSZZTEMP where CHARGEID='"+awsuid+"' and ID="+Integer.parseInt(dataId);
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
	
	
	public String unlockData(String awsuid, String dataId) {
		BnuMainWeb web = new BnuMainWeb(super.getContext());
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			
			String sql = "select * from BO_XSZZTEMP where id="+Integer.parseInt(dataId);
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
			processInstanceId = WorkflowInstanceAPI.getInstance().createProcessInstance("007b23151fd702fd52eb9f3c13ffc4ac", loginId, "学术著作申请");
			int[] processTaskInstanceIds = WorkflowTaskInstanceAPI.getInstance().createProcessTaskInstance(loginId, processInstanceId, SynType.synchronous, PriorityType.hight, 1, loginId, "学术著作申请", false, 0);
			
			String updateSql = "update BO_XSZZTEMP set BINDID="+processInstanceId
					+" , STATUS=0" 
					+" , FINALMARK=NVL(FINALMARK,0)-"+finalMark
					+" WHERE ID="+Integer.parseInt(dataId);
			DBSql.executeUpdate(conn, updateSql);
			
			CalMark calMark = new CalMark();
//			calMark.reduceFinalMark(loginId, "XSZZ", finalMark);
			calMark.reduceFinalMarkWithYear(loginId, "XSZZ", finalMark, datayear);
			
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

