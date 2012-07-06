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

public class BnuManagerZlxxWeb extends ActionsoftWeb {
	/**
	 * 构造方法
	 * @param userContext 用户上下文对象
	 */
	public BnuManagerZlxxWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造方法
	 */
	public BnuManagerZlxxWeb() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 专利信息--查询结果
	 * @param pageNow    页码
	 * @param lineNumber 每页行数
	 * @param awsuid     AWS帐号
	 * @return
	 */
	public String getReport(int pageNow,int lineNumber,String awsuid) {
		//项目多用户参与，需共享数据
		StringBuffer sb = new StringBuffer();
		Hashtable hashTags=new Hashtable();
		String sqlw = "select * from BO_ZLXXTEMP order by CREATEDATE desc";
		String sqlc = "select count(*) c from BO_ZLXXTEMP ";

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
//			   SQL = "select top "+lineCount+" * from BO_ZLXXTEMP " +
//			   		"where CHARGEID='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_ZLXXTEMP  where CHARGEID='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }else{
		       SQL = "select * from (select rownum r,t1.* from ("+sqlw+") t1 where rownum<="+(start+lineNumber-1)+") t2 where t2.r>="+start; //oracle
			   //sql server
//			   SQL = "select top "+(start+lineNumber-1)+" * from BO_ZLXXTEMP " +
//			   		"where CHARGEID='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_ZLXXTEMP  where CHARGEID='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }
		   ps=conn.prepareStatement(SQL);
		   rs=ps.executeQuery();
		   while(rs.next()){
			   int ID = rs.getInt("ID");
			   int bindId = rs.getInt("BINDID");
			   
			   String PATENTNAME = rs.getString("PATENTNAME")==null?"":rs.getString("PATENTNAME");          // 专利名称
			   String PATENTKINDSYMBOL= rs.getString("PATENTKINDSYMBOL")==null?"":rs.getString("PATENTKINDSYMBOL");     // 专利类型 
			   String APPLICANT = rs.getString("APPLICANT")==null?"":rs.getString("APPLICANT");   // 申请人
			   String APPLYDATE = rs.getString("APPLYDATE")==null?"":rs.getString("APPLYDATE"); // 申请日期
			   String PUBLISHDATE = rs.getString("PUBLISHDATE")==null?"":rs.getString("PUBLISHDATE");   // 申请公开日期
			   String DEPTNAME = rs.getString("DEPTNAME")==null?"":rs.getString("DEPTNAME"); // 院系部门 
			   String AUTHORIZEDATE = rs.getString("AUTHORIZEDATE")==null?"":rs.getString("AUTHORIZEDATE"); // 授权日期
			   String PATENTEE = rs.getString("PATENTEE")==null?"":rs.getString("PATENTEE");   // 发明人
			   String AUTHORIZEUNIT = rs.getString("AUTHORIZEUNIT")==null?"":rs.getString("AUTHORIZEUNIT");   // 授权单位
			   String NAME1 = rs.getString("NAME1")==null?"":rs.getString("NAME1"); //
			   String NAME2 = rs.getString("NAME2")==null?"":rs.getString("NAME2"); //
			   String NAME3 = rs.getString("NAME3")==null?"":rs.getString("NAME3"); //
			   String NAME4 = rs.getString("NAME4")==null?"":rs.getString("NAME4"); //
			   String NAME5 = rs.getString("NAME5")==null?"":rs.getString("NAME5"); //
			   
			   if(PUBLISHDATE.length() >= 10) {
				   PUBLISHDATE = PUBLISHDATE.substring(0,10);
			   }
			   if(APPLYDATE.length() >= 10) {
				   APPLYDATE = APPLYDATE.substring(0,10);
			   }
			   if(AUTHORIZEDATE.length() >= 10) {
				   AUTHORIZEDATE = AUTHORIZEDATE.substring(0,10);
			   }
			   
			   int AUTOADD = rs.getInt("AUTOADD"); //
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
			   
			   sb.append("<tr bgcolor='#ffffff'>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+xh+"</td>\n");//序号
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+(PATENTNAME)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(PATENTKINDSYMBOL)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(APPLICANT)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(APPLYDATE)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(PUBLISHDATE)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(DEPTNAME)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(AUTHORIZEDATE)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(PATENTEE)+b2+"</td>\n");  
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(AUTHORIZEUNIT)+b2+"</td>\n");  
			   sb.append("<td"+bg+" align='left' >"+b1+(joinMan)+b2+"</td>\n"); //其他参与人数
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(aprvStatus)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>");
			   sb.append("<a href='#' onclick='modifyData("+bindId+", "+ID+");return false;'  title='专利信息'><img src='../aws_img/man.gif' border='0'>修改</a>&nbsp&nbsp");
			   sb.append("<a href='#' onclick='deleteData("+ID+");return false;'>删除</a></td>\n"); //操作
			   sb.append("</tr>\n");
			   xh++;
		   }
		   ps.close();
		   rs.close();
		   
		   //页面导航
		   if (lineCount > lineNumber) {
			   sb.append("<tr bgcolor='#ffffff'><td colspan=17><div class='hide_for_jatools_print' id=AWS_REPORT_PAGEINDEX name=AWS_REPORT_PAGEINDEX ><br><br>")
			   .append(new PageIndex("BNU_MANAGER_ZLXX", pageNow, lineCount, lineNumber).toString())
			   .append("</div></td></tr>");
		   }
		   hashTags.put("sid",super.getSIDFlag());
		   hashTags.put("awsuid",awsuid);
		   hashTags.put("pageNow",Integer.toString(pageNow));
		   hashTags.put("list",sb.toString());
		}catch(Exception e){
			e.printStackTrace(System.err);
			return "#专利信息_数据查询异常";
		}finally{
			DBSql.close(conn, ps, rs);
		}
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_MANAGER_ZLXX.html"),hashTags);
	}
	
}

