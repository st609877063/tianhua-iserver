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

public class BnuManagerXslwWeb extends ActionsoftWeb {
	/**
	 * 构造方法
	 * @param userContext 用户上下文对象
	 */
	public BnuManagerXslwWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造方法
	 */
	public BnuManagerXslwWeb() {
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
		String sqlw = "select * from BO_XSLWTEMP order by CREATEDATE desc";
		String sqlc = "select count(*) c from BO_XSLWTEMP";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String bg = "",bg1 = "",b1 = "", b2 = "";
		bg = " style=\"padding:5px;\"";  
		bg1 = "../aws_img/report-bg-blue2.gif";
		
		String LWMC = null;          // 论文名称
		String LWLX = null;   // 论文类型
		String WCXS = null; // 完成形式
		String CHARGESF = null;   // 第一作者身份
		String SSYX = null; // 所属院系/部门 
		String CHARGENM = null; // 第一作者姓名
		String GKLX = null;          // 归口类型
		String FBKW = null; // 发表刊物
		String YJLX = null;   // 研究类型

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
			   LWLX = rs.getString("LWLX")==null?"":rs.getString("LWLX"); //论文类型
			   WCXS = rs.getString("WCXS")==null?"":rs.getString("WCXS"); //完成形式
			   CHARGESF = rs.getString("CHARGESF")==null?"":rs.getString("CHARGESF"); //第一作者身份
			   SSYX = rs.getString("SSYX")==null?"":rs.getString("SSYX"); //所属院系/部门 
			   CHARGENM = rs.getString("CHARGENM")==null?"":rs.getString("CHARGENM"); //第一作者姓名
			   GKLX = rs.getString("GKLX")==null?"":rs.getString("GKLX"); //归口类型
			   FBKW = rs.getString("FBKW")==null?"":rs.getString("FBKW"); //发表刊物
			   YJLX = rs.getString("YJLX")==null?"":rs.getString("YJLX"); //研究类型

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
			   sb.append("<td"+bg+" align='left' >"+xh+"</td>\n");//序号
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+(LWMC)+b2+"</td>\n"); //论文名称
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(LWLX)+b2+"</td>\n"); //论文类型
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(WCXS)+b2+"</td>\n"); //完成形式
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(CHARGESF)+b2+"</td>\n"); //第一作者身份
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(SSYX)+b2+"</td>\n"); //所属院系/部门
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(CHARGENM)+b2+"</td>\n"); //第一作者姓名
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(GKLX)+b2+"</td>\n"); //归口类型
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(FBKW)+b2+"</td>\n"); //发表刊物
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(YJLX)+b2+"</td>\n"); //研究类型
			   sb.append("<td"+bg+" align='left' >"+b1+(joinMan)+b2+"</td>\n"); //其他参与人数
			   sb.append("<td"+bg+" align='left' >"+b1+(aprvStatus)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' >");
			   sb.append("<a href='#' onclick='modifyData("+bindId+", "+ID+");return false;'  title='成果获奖'><img src='../aws_img/man.gif' border='0'>修改</a>&nbsp&nbsp");
			   sb.append("<a href='#' onclick='deleteData("+ID+");return false;'>删除</a></td>\n"); //操作
			   sb.append("</tr>\n");
			   xh++;
		   }
		   ps.close();
		   rs.close();
		   //页面导航
		   if (lineCount > lineNumber) {
			   sb.append("<tr bgcolor='#ffffff'><td colspan=17><div class='hide_for_jatools_print' id=AWS_REPORT_PAGEINDEX name=AWS_REPORT_PAGEINDEX ><br><br>")
			   .append(new PageIndex("BNU_MANAGER_XSLW", pageNow, lineCount, lineNumber).toString())
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
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_MANAGER_XSLW.html"),hashTags);
	}
	
}

