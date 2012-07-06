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

public class BnuHRJxhjWeb extends ActionsoftWeb {
	/**
	 * 构造方法
	 * @param userContext 用户上下文对象
	 */
	public BnuHRJxhjWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造方法
	 */
	public BnuHRJxhjWeb() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 教学获奖--查询结果
	 * @param pageNow    页码
	 * @param lineNumber 每页行数
	 * @param awsuid     AWS帐号
	 * @return
	 */
	public String getReport(int pageNow,int lineNumber,String awsuid) {
		//项目多用户参与，需共享数据
		StringBuffer sb = new StringBuffer();
		Hashtable hashTags=new Hashtable();
//		String sqlw = "select * from BO_JXHJTEMP where 1=1 and FZRID='"+awsuid+"' order by CREATEDATE desc";
//		String sqlc = "select count(*) c from BO_JXHJTEMP where 1=1 and FZRID='"+awsuid+"'";
		String sqlw = " select * from (" +
				" select * from BO_JXHJTEMP where  FZRID='"+awsuid+"'  and (AUTOADD is null or AUTOADD=0) " +
				" union select * from BO_JXHJTEMP where  ID1='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_JXHJTEMP where  ID2='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_JXHJTEMP where  ID3='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_JXHJTEMP where  ID4='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_JXHJTEMP where  ID5='"+awsuid+"' and AUTOADD=1 " +
				") order by CREATEDATE desc";
		String sqlc = " select count(*) c from (" +
				" select * from BO_JXHJTEMP where  FZRID='"+awsuid+"'  and (AUTOADD is null or AUTOADD=0) " +
				" union select * from BO_JXHJTEMP where  ID1='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_JXHJTEMP where  ID2='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_JXHJTEMP where  ID3='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_JXHJTEMP where  ID4='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_JXHJTEMP where  ID5='"+awsuid+"' and AUTOADD=1 )";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String bg = "",bg1 = "",b1 = "", b2 = "";
		bg = " style=\"padding:5px;\"";  
		bg1 = "../aws_img/report-bg-blue2.gif";
		
		String  CGMC = null; //成果名称
		String  JLLX = null; //奖励类型
		String  HJDJ = null; //获奖等级
		String  FZR = null; //第一完成人
		String  FZRID = null; //
		int  FZRRATE = 0; //
		String  SZDW = null; //第一完成人所在单位
		String  HJRQ = null; //获奖时间
		String  PZWH = null; //批准文号
		String  JLJF = null; //奖励经费(万)
		String  ATTCH = null; //扫描件
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
//			   SQL = "select top "+lineCount+" * from BO_JXHJTEMP " +
//			   		"where FZRID='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_JXHJTEMP  where FZRID='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }else{
		       SQL = "select * from (select rownum r,t1.* from ("+sqlw+") t1 where rownum<="+(start+lineNumber-1)+") t2 where t2.r>="+start; //oracle
			   //sql server
//			   SQL = "select top "+(start+lineNumber-1)+" * from BO_JXHJTEMP " +
//			   		"where FZRID='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_JXHJTEMP  where FZRID='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }
		   ps=conn.prepareStatement(SQL);
		   rs=ps.executeQuery();
		   while(rs.next()){
			   int ID = rs.getInt("ID");
			   int bindId = rs.getInt("BINDID");
			   
			   CGMC = rs.getString("CGMC")==null?"":rs.getString("CGMC"); //成果名称
			   JLLX = rs.getString("JLLX")==null?"":rs.getString("JLLX"); //奖励类型
			   HJDJ = rs.getString("HJDJ")==null?"":rs.getString("HJDJ"); //获奖等级
			   HJRQ = rs.getString("HJRQ")==null?"":rs.getString("HJRQ"); //获奖时间
			   FZR = rs.getString("FZR")==null?"":rs.getString("FZR"); //第一完成人
			   FZRID = rs.getString("FZRID")==null?"":rs.getString("FZRID"); //
			   FZRRATE = rs.getInt("FZRRATE"); //
			   SZDW = rs.getString("SZDW")==null?"":rs.getString("SZDW"); //第一完成人所在单位
			   PZWH = rs.getString("PZWH")==null?"":rs.getString("PZWH"); //批准文号
			   JLJF = rs.getString("JLJF")==null?"":rs.getString("JLJF"); //奖励经费(万)
			   ATTCH = rs.getString("ATTCH")==null?"":rs.getString("ATTCH"); //扫描件

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
			   
			   int AUTOADD = rs.getInt("AUTOADD"); //
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
				   autoaddStatus = "参与项目";
			   } 
			   
			   if(HJRQ.length() >= 10) {
				   HJRQ = HJRQ.substring(0, 10);
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
			   
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+(CGMC)+b2+"</td>\n"); //成果名称
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(JLLX)+b2+"</td>\n"); //奖励类型
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(HJDJ)+b2+"</td>\n"); //获奖等级
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(HJRQ)+b2+"</td>\n"); //获奖时间
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(FZR)+b2+"</td>\n"); //第一完成人
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(SZDW)+b2+"</td>\n"); //第一完成人所在单位
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(PZWH)+"%"+b2+"</td>\n"); //批准文号
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(JLJF)+b2+"</td>\n");  //奖励经费(万)
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(ATTCH)+b2+"</td>\n");  //扫描件
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(joinMan)+b2+"</td>\n"); //其他参与人数
			   
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(aprvStatus)+b2+"</td>\n");
			   if (STATUS == 0 && AUTOADD == 0) {
				   sb.append("<td"+bg+" align='left' style='word-break:break-all'><a href='#' onclick='viewDetail("+ID+");return false;'>详情</a>&nbsp&nbsp");
				   sb.append("<a href='#' onclick='modifyData("+bindId+", "+ID+");return false;'  title='教学获奖'><img src='../aws_img/man.gif' border='0'>修改</a>&nbsp&nbsp");
//				   sb.append("<a href='#' onclick='deleteData("+ID+");return false;'>删除</a></td>\n"); //操作
				   sb.append("</td>\n"); //操作
			   } else if (STATUS == 1 || AUTOADD == 1) {   
				   sb.append("<td"+bg+" align='left' style='word-break:break-all'><a href='#' onclick='viewDetail("+ID+");return false;'>详情</a></td>");
			   } else if (STATUS == 9 || AUTOADD == 0) {   
				   sb.append("<td"+bg+" align='left' style='word-break:break-all'><a href='#' onclick='viewDetail("+ID+");return false;'>详情</a>&nbsp&nbsp");
				   sb.append("<a href='#' onclick='unlockData("+ID+");return false;'><img src='../aws_img/man.gif' border='0'>解锁</a>");
				   sb.append("</td>\n");
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
			   String pageIndexStr = new PageIndex("BNU_HR_JXHJ", pageNow, lineCount, lineNumber).toString();
			   String pageIndexStr2 = pageIndexStr.replaceAll("frmMain", "frmJxhj");
			   
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
			return "#教学获奖_数据查询异常";
		}finally{
			DBSql.close(conn, ps, rs);
		}
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_HR_JXHJ.html"),hashTags);
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
			

			String copyDataStr = DBSql.getString(conn, "SELECT COPYDATAID FROM BO_JXHJTEMP WHERE ID="+Integer.parseInt(dataId), "COPYDATAID");
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
					String deleteSql = "delete from BO_JXHJTEMP where ID ="+copyDataIdInt;
					DBSql.executeUpdate(deleteSql);
				}
			}
			
			
			
			String sql = null;
			sql = "delete from  BO_JXHJTEMP where FZRID='"+awsuid+"' and ID="+Integer.parseInt(dataId);
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

		String  CGMC = null; //成果名称
		String  JLLX = null; //奖励类型
		String  HJDJ = null; //获奖等级
		String  FZR = null; //第一完成人
		String  FZRID = null; //
		int  FZRRATE = 0; //
		String  SZDW = null; //第一完成人所在单位
		String  HJRQ = null; //获奖时间
		String  PZWH = null; //批准文号
		String  JLJF = null; //奖励经费(万)
		String  ATTCH = null; //扫描件
		
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
			String sql = "select * from BO_JXHJTEMP where ID="+Integer.parseInt(dataId);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				CGMC = rs.getString("CGMC")==null?"":rs.getString("CGMC"); //成果名称
				JLLX = rs.getString("JLLX")==null?"":rs.getString("JLLX"); //奖励类型
				HJDJ = rs.getString("HJDJ")==null?"":rs.getString("HJDJ"); //获奖等级
				HJRQ = rs.getString("HJRQ")==null?"":rs.getString("HJRQ"); //获奖时间
				FZR = rs.getString("FZR")==null?"":rs.getString("FZR"); //第一完成人
				FZRID = rs.getString("FZRID")==null?"":rs.getString("FZRID"); //
				FZRRATE = rs.getInt("FZRRATE"); //
				SZDW = rs.getString("SZDW")==null?"":rs.getString("SZDW"); //第一完成人所在单位
				PZWH = rs.getString("PZWH")==null?"":rs.getString("PZWH"); //批准文号
				JLJF = rs.getString("JLJF")==null?"":rs.getString("JLJF"); //奖励经费(万)
				ATTCH = rs.getString("ATTCH")==null?"":rs.getString("ATTCH"); //扫描件
				
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
				   
				if(HJRQ.length() >= 10) {
					HJRQ = HJRQ.substring(0, 10);
				}
			}
			ps.close();
			rs.close();
		}catch(Exception e) {
			e.printStackTrace(System.err);
		}finally {
			DBSql.close(conn, ps, rs);
		}
		
		
		String CGMCBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CGMC + "'>";
		String JLLXCBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + JLLX + "'>";
		String HJDJBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + HJDJ + "'>";
		String HJRQBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + HJRQ + "'>";
		String FZRBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + FZR + "'>";
		String FZRIDBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + FZRID + "'>";
		String FZRRATEBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + FZRRATE +"%" + "'>";
		String SZDWBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + SZDW + "'>";
		String PZWHBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + PZWH + "'>";
		String JLJFBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + JLJF + "'>";
		String ATTACHBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + ATTCH + "'>";
		
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
		response.append("<td width=20% align=left>成果名称：</td><td width=35%>").append(CGMCBuffer).append("</td>\n");
		response.append("<td width=20% align=left>奖励类型：</td><td width=35%>").append(JLLXCBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>获奖等级：</td><td width=35%>").append(HJDJBuffer).append("</td>\n");
		response.append("<td width=20% align=left>获奖时间：</td><td width=35%>").append(HJRQBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>第一完成人姓名：</td><td width=35%>").append(FZRBuffer).append("ID:").append(FZRIDBuffer).append("</td>\n");
		response.append("<td width=20% align=left>工作量（%）：</td><td width=35%>").append(FZRRATEBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left colspan='2'>第一完成人所在单位：</td><td width=35%>").append(SZDWBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>批准文号：</td><td width=35%>").append(PZWHBuffer).append("</td>\n");
		response.append("<td width=20% align=left>奖励经费(万)：</td><td width=35%>").append(JLJFBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left colspan='2'>扫描件：</td><td width=35%>").append(ATTACHBuffer).append("</td>\n");
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
		
		response.append("</td></tr></table>");
		
		return AjaxUtil.responseDialog("教学获奖信息", 180, 70, 800, 500, response.toString(), "" ); //left,top,width,height
	}
	public String unlockData(String awsuid, String dataId) {
		BnuMainWeb web = new BnuMainWeb(super.getContext());
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			
			String sql = "select * from BO_JXHJTEMP where id="+Integer.parseInt(dataId);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			String loginId = "";
			int finalMark = 0;
			String datayear = "";
			while(rs.next()){
				loginId = rs.getString("FZR")==null?"":rs.getString("FZR").trim();
				finalMark = rs.getInt("FINALMARK");
				datayear = rs.getString("DATAYEAR")==null?"":rs.getString("DATAYEAR").trim();
			}
			
			//运行AWS流程-----------------为数据绑定流程
			int processInstanceId = 0;
			processInstanceId = WorkflowInstanceAPI.getInstance().createProcessInstance("007b23151fd5c34109881c2f6de06e37", loginId, "教学获奖申请");
			int[] processTaskInstanceIds = WorkflowTaskInstanceAPI.getInstance().createProcessTaskInstance(loginId, processInstanceId, SynType.synchronous, PriorityType.hight, 1, loginId, "教学获奖申请", false, 0);
			
			String updateSql = "update BO_JXHJTEMP set BINDID="+processInstanceId
					+" , STATUS=0" 
					+" , FINALMARK=NVL(FINALMARK,0)-"+finalMark
					+" WHERE ID="+Integer.parseInt(dataId);
			DBSql.executeUpdate(conn, updateSql);
			
			CalMark calMark = new CalMark();
//			calMark.reduceFinalMark(loginId, "JXHJ", finalMark);
			calMark.reduceFinalMarkWithYear(loginId, "JXHJ", finalMark, datayear);
			
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

