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

public class BnuManagerQtcgWeb extends ActionsoftWeb {
	/**
	 * 构造方法
	 * @param userContext 用户上下文对象
	 */
	public BnuManagerQtcgWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造方法
	 */
	public BnuManagerQtcgWeb() {
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
		String sqlw = "select * from BO_QTCGTEMP order by CREATEDATE desc";
		String sqlc = "select count(*) c from BO_QTCGTEMP ";

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
			   String YXBM = rs.getString("YXBM")==null?"":rs.getString("YXBM");   // 院系部门
			   String CNDW = rs.getString("CNDW")==null?"":rs.getString("CNDW");   // 采纳单位
			   String CHARGENM = rs.getString("CHARGENM")==null?"":rs.getString("CHARGENM"); // 负责人姓名
			   String NAME1 = rs.getString("NAME1")==null?"":rs.getString("NAME1"); //
			   String NAME2 = rs.getString("NAME2")==null?"":rs.getString("NAME2"); //
			   String NAME3 = rs.getString("NAME3")==null?"":rs.getString("NAME3"); //
			   String NAME4 = rs.getString("NAME4")==null?"":rs.getString("NAME4"); //
			   String NAME5 = rs.getString("NAME5")==null?"":rs.getString("NAME5"); //
			   
			   if(APPRAISEDATE!=null && APPRAISEDATE.length()>10) {
				   APPRAISEDATE = APPRAISEDATE.substring(0,10);
			   }
			   
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
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+(HAVNAME)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CHARGENM)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(HAVKINDSYMBOL)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(MANAGEOFFICE)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(APPRAISEUNIT)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(APPRAISEDATE)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(APPRAISEREPORTCODE)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(SUBJECT+SUBJECT2)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(YXBM)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CNDW)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' >"+b1+(joinMan)+b2+"</td>\n"); //其他参与人数
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(aprvStatus)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>");
			   sb.append("<a href='#' onclick='modifyData("+bindId+", "+ID+");return false;'  title='其他成果'><img src='../aws_img/man.gif' border='0'>修改</a>&nbsp&nbsp");
			   sb.append("<a href='#' onclick='deleteData("+ID+");return false;'>删除</a></td>\n"); //操作
			   sb.append("</tr>\n");
			   xh++;
		   }
		   ps.close();
		   rs.close();
		   
		   //页面导航
		   if (lineCount > lineNumber) {
			   sb.append("<tr bgcolor='#ffffff'><td colspan=17><div class='hide_for_jatools_print' id=AWS_REPORT_PAGEINDEX name=AWS_REPORT_PAGEINDEX ><br><br>")
			   .append(new PageIndex("BNU_MANAGER_QTCG", pageNow, lineCount, lineNumber).toString())
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
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_MANAGER_QTCG.html"),hashTags);
	}
	
}

