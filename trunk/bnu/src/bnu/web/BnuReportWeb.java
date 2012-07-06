package bnu.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.awf.util.PageIndex;
import com.actionsoft.awf.util.UtilString;
import com.actionsoft.htmlframework.htmlmodel.HtmlModelFactory;
import com.actionsoft.htmlframework.htmlmodel.RepleaseKey;
import com.actionsoft.htmlframework.web.ActionsoftWeb;

public class BnuReportWeb extends ActionsoftWeb{

	public UserContext _user;
	public static String _sid;
	
	public BnuReportWeb(UserContext userContext) {
		super(userContext);
		this._user=userContext;
		_sid="<input type=hidden name=sid value="+userContext.getSessionId()+">"; 
	}

	public BnuReportWeb() {
		super();
	}


	public String getResult(String cmd, int pageNow) {
		
		String uid = _user.getUID(); 
		StringBuffer sb = new StringBuffer("");
		Hashtable hashTags = new Hashtable();
		int lineNumber = 30; //30 lines per page
		int lineFirst = lineNumber * (pageNow - 1);
		if ( lineFirst < 0 ) {
			lineFirst = 0;
		}
		
		String sqlc = "select count(*) c from BO_RSTEMP";
		int totalNum = 0;
		totalNum = DBSql.getInt(sqlc, "c");
		
		int gnxxjl_mark = 0;
		int gjwxxjl_mark =0;
		int pxjl_mark =0;
		int gzjl_mark =0;
		int rkqk_mark =0;
		int zdzy_mark =0;
		int zdxs_mark =0;
		int jgxm_mark =0;
		int jxhj_mark =0;
		int kyxm_mark =0;
		int xslw_mark =0;
		int xszz_mark =0;
		int zlxx_mark =0;
		int qtcg_mark =0;
		int cghj_mark =0;
		int gjhqk_mark =0;
		int jtcy_mark =0;
		int total_mark = 0;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
		   int lineCount = DBSql.getInt(sqlc, "c");
		   int start = (pageNow-1)*lineNumber+1;
		   int xh=start;
		   String SQL = "";
		   conn=DBSql.open();
		   conn.setAutoCommit(true);
		   String sqlw = "select * from BO_RSTEMP where 1=1 order by CREATEDATE desc";
		   if(start+lineNumber>=lineCount){
			   SQL = "select * from (select rownum r,t1.* from ("+sqlw+") t1 where rownum<="+lineCount+") t2 where t2.r>="+start; //oracle
			   //sql server
//			   SQL = "select top "+lineCount+" * from BO_RSTEMP " +
//			   		"where id not in (select top "+(start-1)+" id from BO_RSTEMP) order by CREATEDATE desc";
		   }else{
		       SQL = "select * from (select rownum r,t1.* from ("+sqlw+") t1 where rownum<="+(start+lineNumber-1)+") t2 where t2.r>="+start; //oracle
			   //sql server
//			   SQL = "select top "+(start+lineNumber-1)+" * from BO_RSTEMP " +
//			   		"where id not in (select top "+(start-1)+" id from BO_RSTEMP) order by CREATEDATE desc";
		   }
		   ps = conn.prepareStatement(SQL);
		   rs = ps.executeQuery();
		   
		   while(rs.next()){
			   String zgh = rs.getString("ZGH")==null?"":rs.getString("ZGH");
			   String xm = rs.getString("XM")==null?"":rs.getString("XM");
			   String loginId = rs.getString("LOGINID")==null?"":rs.getString("LOGINID");
			   
			   String sql1 = "select sum(FINALMARK) FINALMARK from BO_GNXXJLTEMP where LOGINID='"+loginId+"'";
			   gnxxjl_mark = DBSql.getInt(sql1, "FINALMARK");
			   String sql2 = "select sum(FINALMARK) FINALMARK from BO_GJWXXJLTEMP where LOGINID='"+loginId+"'";
			   gjwxxjl_mark = DBSql.getInt(sql2, "FINALMARK");
			   String sql3 = "select sum(FINALMARK) FINALMARK from BO_PXJLTEMP where LOGINID='"+loginId+"'";
			   pxjl_mark = DBSql.getInt(sql3, "FINALMARK");
			   String sql4 = "select sum(FINALMARK) FINALMARK from BO_GZJLTEMP where LOGINID='"+loginId+"'";
			   gzjl_mark = DBSql.getInt(sql4, "FINALMARK");
			   String sql5 = "select sum(FINALMARK) FINALMARK from BO_RKQKTEMP where LOGINID='"+loginId+"'";
			   rkqk_mark = DBSql.getInt(sql5, "FINALMARK");
			   String sql6 = "select sum(FINALMARK) FINALMARK from BO_ZDZYTEMP where LOGINID='"+loginId+"'";
			   zdzy_mark = DBSql.getInt(sql6, "FINALMARK");
			   String sql7 = "select sum(FINALMARK) FINALMARK from BO_ZDXSTEMP where LOGINID='"+loginId+"'";
			   zdxs_mark = DBSql.getInt(sql7, "FINALMARK");
			   String sql8 = "select sum(FINALMARK) FINALMARK from BO_JGXMTEMP where LOGINID='"+loginId+"'";
			   jgxm_mark = DBSql.getInt(sql8, "FINALMARK");
			   String sql9 = "select sum(FINALMARK) FINALMARK from BO_JXHJTEMP where LOGINID='"+loginId+"'";
			   jxhj_mark = DBSql.getInt(sql9, "FINALMARK");
			   String sql10 = "select sum(FINALMARK) FINALMARK from BO_KYXMTEMP where LOGINID='"+loginId+"'";
			   kyxm_mark = DBSql.getInt(sql10, "FINALMARK");
			   String sql11 = "select sum(FINALMARK) FINALMARK from BO_XSLWTEMP where LOGINID='"+loginId+"'";
			   xslw_mark = DBSql.getInt(sql11, "FINALMARK");
			   String sql12 = "select sum(FINALMARK) FINALMARK from BO_XSZZTEMP where LOGINID='"+loginId+"'";
			   xszz_mark = DBSql.getInt(sql12, "FINALMARK");
			   String sql13 = "select sum(FINALMARK) FINALMARK from BO_ZLXXTEMP where LOGINID='"+loginId+"'";
			   zlxx_mark = DBSql.getInt(sql13, "FINALMARK");
			   String sql14 = "select sum(FINALMARK) FINALMARK from BO_QTCGTEMP where LOGINID='"+loginId+"'";
			   qtcg_mark = DBSql.getInt(sql14, "FINALMARK");
			   String sql15 = "select sum(FINALMARK) FINALMARK from BO_CGHJTEMP where LOGINID='"+loginId+"'";
			   cghj_mark = DBSql.getInt(sql15, "FINALMARK");
			   String sql16 = "select sum(FINALMARK) FINALMARK from BO_GJHQKTEMP  where LOGINID='"+loginId+"'";
			   gjhqk_mark = DBSql.getInt(sql16, "FINALMARK");
			   String sql17 = "select sum(FINALMARK) FINALMARK from BO_JTCYTEMP  where LOGINID='"+loginId+"'";
			   jtcy_mark = DBSql.getInt(sql17, "FINALMARK");
			   
			   total_mark = gnxxjl_mark + gjwxxjl_mark + pxjl_mark + gzjl_mark + rkqk_mark + zdzy_mark + zdxs_mark + jgxm_mark  
			   			+ jxhj_mark + kyxm_mark + xslw_mark + xszz_mark + zlxx_mark + qtcg_mark + cghj_mark + gjhqk_mark + jtcy_mark ;
			   
			   
			   sb.append("<tr>")
			   .append("<td background=\"../aws_img/report-bg-blue.gif\"  align='center'  >").append(loginId).append("</td>")
			   .append("<td background=\"../aws_img/report-bg-blue.gif\"  align='center'  >").append(zgh).append("</td>")
			   .append("<td background=\"../aws_img/report-bg-blue.gif\"  align='center'  >").append(xm).append("</td>")
			   .append("<td background=\"../aws_img/report-bg-blue.gif\"  align='center'  >").append(gnxxjl_mark).append("</td>")
			   .append("<td background=\"../aws_img/report-bg-blue.gif\"  align='center'  >").append(gjwxxjl_mark).append("</td>")
			   .append("<td background=\"../aws_img/report-bg-blue.gif\"  align='center'  >").append(pxjl_mark).append("</td>")
			   .append("<td background=\"../aws_img/report-bg-blue.gif\"  align='center'  >").append(gzjl_mark).append("</td>")
			   .append("<td background=\"../aws_img/report-bg-blue.gif\"  align='center'  >").append(rkqk_mark).append("</td>")
			   .append("<td background=\"../aws_img/report-bg-blue.gif\"  align='center'  >").append(zdzy_mark).append("</td>")
			   .append("<td background=\"../aws_img/report-bg-blue.gif\"  align='center'  >").append(zdxs_mark).append("</td>")
			   .append("<td background=\"../aws_img/report-bg-blue.gif\"  align='center'  >").append(jgxm_mark).append("</td>")
			   .append("<td background=\"../aws_img/report-bg-blue.gif\"  align='center'  >").append(jxhj_mark).append("</td>")
			   .append("<td background=\"../aws_img/report-bg-blue.gif\"  align='center'  >").append(kyxm_mark).append("</td>")
			   .append("<td background=\"../aws_img/report-bg-blue.gif\"  align='center'  >").append(xslw_mark).append("</td>")
			   .append("<td background=\"../aws_img/report-bg-blue.gif\"  align='center'  >").append(xszz_mark).append("</td>")
			   .append("<td background=\"../aws_img/report-bg-blue.gif\"  align='center'  >").append(zlxx_mark).append("</td>")
			   .append("<td background=\"../aws_img/report-bg-blue.gif\"  align='center'  >").append(qtcg_mark).append("</td>")
			   .append("<td background=\"../aws_img/report-bg-blue.gif\"  align='center'  >").append(cghj_mark).append("</td>")
			   .append("<td background=\"../aws_img/report-bg-blue.gif\"  align='center'  >").append(gjhqk_mark).append("</td>")
			   .append("<td background=\"../aws_img/report-bg-blue.gif\"  align='center'  >").append(jtcy_mark).append("</td>")
			   .append("<td background=\"../aws_img/report-bg-blue.gif\"  align='center'  >").append(total_mark).append("</td>")
			   .append("</tr>");
		   }
		   ps.close();
		   rs.close();
		   //页面导航
		   if (lineCount > lineNumber) {
			   sb.append("<tr bgcolor='#ffffff'><td colspan=17><div class='hide_for_jatools_print' id=AWS_REPORT_PAGEINDEX name=AWS_REPORT_PAGEINDEX ><br><br>")
			   .append(new PageIndex("BNU_HR_REPORT", pageNow, lineCount, lineNumber).toString())
			   .append("</div></td></tr>");
		   }
		   hashTags.put("sid", _sid);
//		   hashTags.put("sid",super.getSIDFlag());
		   hashTags.put("page_index", new PageIndex(cmd, pageNow, totalNum, lineNumber).toString());
		   hashTags.put("pageNow",Integer.toString(pageNow));
		   hashTags.put("list",sb.toString());
//		   hashTags.put("awsuid",awsuid);
		}catch(Exception e){
			e.printStackTrace(System.err);
			return "#报表生成失败";
		}finally{
			DBSql.close(conn, ps, rs);
		}
		
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_HR_REPORT.htm"), hashTags);
	}

}
