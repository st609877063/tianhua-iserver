package bnu.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Hashtable;

import bnu.util.HRUtil;

import com.actionsoft.awf.organization.control.MessageQueue;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.AjaxUtil;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.awf.util.PageIndex;
import com.actionsoft.awf.util.Sequence;
import com.actionsoft.htmlframework.htmlmodel.HtmlModelFactory;
import com.actionsoft.htmlframework.htmlmodel.RepleaseKey;
import com.actionsoft.htmlframework.web.ActionsoftWeb;

public class BnuHRZdzyWeb extends ActionsoftWeb {
	/**
	 * 构造方法
	 * @param userContext 用户上下文对象
	 */
	public BnuHRZdzyWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造方法
	 */
	public BnuHRZdzyWeb() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 指导专业--查询结果
	 * @param pageNow    页码
	 * @param lineNumber 每页行数
	 * @param awsuid     AWS帐号
	 * @return
	 */
	public String getReport(int pageNow,int lineNumber,String awsuid) {
		StringBuffer sb = new StringBuffer();
		Hashtable hashTags=new Hashtable();
		String sqlw = "select * from BO_ZDZYTEMP where 1=1 and DSBH='"+awsuid+"' order by CREATEDATE desc";
		String sqlc = "select count(*) c from BO_ZDZYTEMP where 1=1 and DSBH='"+awsuid+"'";
		
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
//			   //sql server
//			   SQL = "select top "+lineCount+" * from BO_ZDZYTEMP " +
//			   		"where DSBH='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_ZDZYTEMP  where DSBH='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }else{
		       SQL = "select * from (select rownum r,t1.* from ("+sqlw+") t1 where rownum<="+(start+lineNumber-1)+") t2 where t2.r>="+start; //oracle
//			   //sql server
//			   SQL = "select top "+(start+lineNumber-1)+" * from BO_ZDZYTEMP " +
//			   		"where DSBH='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_ZDZYTEMP  where DSBH='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }
		   ps=conn.prepareStatement(SQL);
		   rs=ps.executeQuery();
		   while(rs.next()){
			   int ID = rs.getInt("ID");
			   int bindId = rs.getInt("BINDID");
			   String DSMC = rs.getString("DSMC")==null?"":rs.getString("DSMC"); //导师
			   String DSBH = rs.getString("DSBH")==null?"":rs.getString("DSBH"); //导师编号(教工号)
			   String DSLB = rs.getString("DSLB")==null?"":rs.getString("DSLB"); //导师类型
			   String XYMC = rs.getString("XYMC")==null?"":rs.getString("XYMC"); 
			   String DRSDRQ = rs.getString("DRSDRQ")==null?"":rs.getString("DRSDRQ"); 
			   String DRBDRQ = rs.getString("DRBDRQ")==null?"":rs.getString("DRBDRQ"); 
			   String PRQX = rs.getString("PRQX")==null?"":rs.getString("PRQX"); 
			   String ZYMC = rs.getString("ZYMC")==null?"":rs.getString("ZYMC");
			   String ZYH = rs.getString("ZYH")==null?"":rs.getString("ZYH"); 

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
			   
			   if(DRSDRQ!=null && DRSDRQ.length() >= 10) {
				   DRSDRQ = DRSDRQ.substring(0,10);
			   } else {
				   DRSDRQ = "未填报";
			   }
			   if(DRBDRQ!=null && DRBDRQ.length() >= 10) {
				   DRBDRQ = DRBDRQ.substring(0,10);
			   } else {
				   DRBDRQ = "未填报";
			   }
			   if(DRSDRQ!=null && DRBDRQ==null) {
				   DRBDRQ = "任职至今";
			   }
			   
			   sb.append("<tr bgcolor='#ffffff'>\n");
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all'><input type='checkbox' name='checkData' id='"+ID+"'/></td>\n");//checkbox
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all' display='hidden'>"+ID+"</td>\n");//ID
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+xh+"</td>\n");//序号
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+(DSMC)+b2+"</td>\n");//导师名称
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(XYMC)+b2+"</td>\n"); //所在单位
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(DRSDRQ)+b2+"</td>\n");//开始时间
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(DRBDRQ)+b2+"</td>\n");//结束时间
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(ZYMC)+b2+"</td>\n");//社会任职
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(aprvStatus)+b2+"</td>\n");//
			   if (STATUS == 0) {
				   sb.append("<td"+bg+" align='left' style='word-break:break-all'>");
				   sb.append("<a href='#' onclick='modifyData("+bindId+", "+ID+");return false;'  title='指导专业'><img src='../aws_img/man.gif' border='0'>修改</a>&nbsp&nbsp");
//				   sb.append("<a href='#' onclick='deleteData("+ID+");return false;'>删除</a></td>\n"); //操作
				   sb.append("</td>\n"); //操作
			   } else if (STATUS == 1 || STATUS == 9) {   
				   sb.append("<td"+bg+" align='left' style='word-break:break-all'>无操作</td>\n"); //操作
			   }
			   sb.append("</tr>\n");
			   xh++;
		   }
		   ps.close();
		   rs.close();
		   //页面导航
		   if (lineCount > lineNumber) {
			   String pageIndexStr = new PageIndex("BNU_HR_ZDZY", pageNow, lineCount, lineNumber).toString();
			   String pageIndexStr2 = pageIndexStr.replaceAll("frmMain", "frmZdzy");
			   
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
			return "#指导专业_数据查询异常";
		}finally{
			DBSql.close(conn, ps, rs);
		}
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_HR_ZDZY.html"),hashTags);
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
			
			String sql = null;
			sql = "delete from  BO_ZDZYTEMP where DSBH='"+awsuid+"' and ID="+Integer.parseInt(dataId);
			ps1 = conn.prepareStatement(sql);
			int r = ps1.executeUpdate();
			if(r < 0) {
				MessageQueue.getInstance().putMessage(super.getContext().getUID(),"数据删除失败");
				return web.getMain("2",awsuid);
			}
			ps1.close();
			conn.commit();
		}catch(Exception e) {
			e.printStackTrace(System.err);
			MessageQueue.getInstance().putMessage(super.getContext().getUID(),"数据删除失败");
			return web.getMain("2",awsuid);
		}finally {
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace(System.err);
				MessageQueue.getInstance().putMessage(super.getContext().getUID(),"数据删除失败");
				return web.getMain("2",awsuid);
			}
			DBSql.close(conn, ps1, rs1);
		}
		MessageQueue.getInstance().putMessage(super.getContext().getUID(),"数据删除成功！");
		return web.getMain("2",awsuid);
	}
	
}

