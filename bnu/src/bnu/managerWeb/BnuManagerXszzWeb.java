package bnu.managerWeb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;

import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.awf.util.PageIndex;
import com.actionsoft.htmlframework.htmlmodel.HtmlModelFactory;
import com.actionsoft.htmlframework.htmlmodel.RepleaseKey;
import com.actionsoft.htmlframework.web.ActionsoftWeb;

public class BnuManagerXszzWeb extends ActionsoftWeb {
	/**
	 * 构造方法
	 * @param userContext 用户上下文对象
	 */
	public BnuManagerXszzWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造方法
	 */
	public BnuManagerXszzWeb() {
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
		String sqlw = "select * from BO_XSZZTEMP order by CREATEDATE desc";
		String sqlc = "select count(*) c from BO_XSZZTEMP ";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String bg = "",bg1 = "",b1 = "", b2 = "";
		bg = " style=\"padding:5px;\"";  
		bg1 = "../aws_img/report-bg-blue2.gif";
		
		String ZZMC = null;          // 著作名称
		String CHARGENM = null; // 第一作者姓名
		String YXMC = null;   // 院系名称
		String GKLX = null;          // 归口类型
		String WCXS = null; // 完成形式
		String CHARGESF = null;   // 第一作者身份
		String YJLX = null;   // 研究类型
		String CBSMC = null; // 出版社名称
		String CBRQ = null;   // 出版日期
		String NAME1 = null; //
		String NAME2 = null; //
		String NAME3 = null; //
		String NAME4 = null; //
		String NAME5 = null; //
		
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
			   CHARGENM = rs.getString("CHARGENM")==null?"":rs.getString("CHARGENM"); //第一作者姓名
			   YXMC = rs.getString("YXMC")==null?"":rs.getString("YXMC"); //院系名称
			   GKLX = rs.getString("GKLX")==null?"":rs.getString("GKLX"); //归口类型
			   WCXS = rs.getString("WCXS")==null?"":rs.getString("WCXS"); //完成形式
			   CHARGESF = rs.getString("CHARGESF")==null?"":rs.getString("CHARGESF"); //第一作者身份
			   YJLX = rs.getString("YJLX")==null?"":rs.getString("YJLX"); //研究类型
			   CBSMC = rs.getString("CBSMC")==null?"":rs.getString("CBSMC"); //出版社名称
			   CBRQ = rs.getString("CBRQ")==null?"":rs.getString("CBRQ"); //出版日期
			   NAME1 = rs.getString("NAME1")==null?"":rs.getString("NAME1"); //
			   NAME2 = rs.getString("NAME2")==null?"":rs.getString("NAME2"); //
			   NAME3 = rs.getString("NAME3")==null?"":rs.getString("NAME3"); //
			   NAME4 = rs.getString("NAME4")==null?"":rs.getString("NAME4"); //
			   NAME5 = rs.getString("NAME5")==null?"":rs.getString("NAME5"); //
			   
			   int AUTOADD = 0;
			   AUTOADD = rs.getInt("AUTOADD"); //
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
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+xh+"</td>\n");//序号
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+(ZZMC)+b2+"</td>\n"); //著作名称
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CHARGENM)+b2+"</td>\n"); //第一作者姓名
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CHARGESF)+b2+"</td>\n"); // 第一作者身份
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(YXMC)+b2+"</td>\n"); //院系名称
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(GKLX)+b2+"</td>\n"); //归口类型
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(WCXS)+b2+"</td>\n"); // 完成形式
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(YJLX)+b2+"</td>\n"); //研究类型
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CBSMC)+b2+"</td>\n"); //出版社名称
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CBRQ)+b2+"</td>\n"); //出版日期
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(joinMan)+b2+"</td>\n"); //其他参与人数
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(aprvStatus)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>");
			   sb.append("<a href='#' onclick='modifyData("+bindId+", "+ID+");return false;'  title='学术著作'><img src='../aws_img/man.gif' border='0'>修改</a>&nbsp&nbsp");
			   sb.append("<a href='#' onclick='deleteData("+ID+");return false;'>删除</a></td>\n"); //操作
			   sb.append("</tr>\n");
			   xh++;
		   }
		   ps.close();
		   rs.close();
		   //页面导航
		   if (lineCount > lineNumber) {
			   sb.append("<tr bgcolor='#ffffff'><td colspan=17><div class='hide_for_jatools_print' id=AWS_REPORT_PAGEINDEX name=AWS_REPORT_PAGEINDEX ><br><br>")
			   .append(new PageIndex("BNU_MANAGER_XSZZ", pageNow, lineCount, lineNumber).toString())
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
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_MANAGER_XSZZ.html"),hashTags);
	}
	
}

