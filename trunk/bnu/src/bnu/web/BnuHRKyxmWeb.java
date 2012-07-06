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

public class BnuHRKyxmWeb extends ActionsoftWeb {
	/**
	 * 构造方法
	 * @param userContext 用户上下文对象
	 */
	public BnuHRKyxmWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造方法
	 */
	public BnuHRKyxmWeb() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 科研项目--查询结果
	 * @param pageNow    页码
	 * @param lineNumber 每页行数
	 * @param awsuid     AWS帐号
	 * @return
	 */
	public String getReport(int pageNow,int lineNumber,String awsuid) {
		//项目多用户参与，需共享数据
		StringBuffer sb = new StringBuffer();
		Hashtable hashTags=new Hashtable();
//		String sqlw = "select * from BO_KYXMTEMP where 1=1 and CHARGEID='"+awsuid+"' order by CREATEDATE desc";
//		String sqlc = "select count(*) c from BO_KYXMTEMP where 1=1 and CHARGEID='"+awsuid+"'";
		String sqlw = " select * from (" +
				" select * from BO_KYXMTEMP where  CHARGEID='"+awsuid+"'  and (AUTOADD is null or AUTOADD=0) " +
				" union select * from BO_KYXMTEMP where  ID1='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_KYXMTEMP where  ID2='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_KYXMTEMP where  ID3='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_KYXMTEMP where  ID4='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_KYXMTEMP where  ID5='"+awsuid+"' and AUTOADD=1 " +
				") order by CREATEDATE desc";
		String sqlc = " select count(*) c from (" +
				" select * from BO_KYXMTEMP where  CHARGEID='"+awsuid+"'  and (AUTOADD is null or AUTOADD=0) " +
				" union select * from BO_KYXMTEMP where  ID1='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_KYXMTEMP where  ID2='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_KYXMTEMP where  ID3='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_KYXMTEMP where  ID4='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_KYXMTEMP where  ID5='"+awsuid+"' and AUTOADD=1 )";
		
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
//			   SQL = "select top "+lineCount+" * from BO_KYXMTEMP " +
//			   		"where CHARGEID='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_KYXMTEMP  where CHARGEID='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }else{
		       SQL = "select * from (select rownum r,t1.* from ("+sqlw+") t1 where rownum<="+(start+lineNumber-1)+") t2 where t2.r>="+start; //oracle
			   //sql server
//			   SQL = "select top "+(start+lineNumber-1)+" * from BO_KYXMTEMP " +
//			   		"where CHARGEID='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_KYXMTEMP  where CHARGEID='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }
		   ps=conn.prepareStatement(SQL);
		   rs=ps.executeQuery();
		   while(rs.next()){
			   int ID = rs.getInt("ID");
			   int bindId = rs.getInt("BINDID");
			   
			   String XMMC = rs.getString("XMMC")==null?"":rs.getString("XMMC"); //项目名称
			   String XMYWMC = rs.getString("XMYWMC")==null?"":rs.getString("XMYWMC"); //项目英文名称
			   String XMBH = rs.getString("XMBH")==null?"":rs.getString("XMBH"); //项目编号
			   String XMPZH = rs.getString("XMPZH")==null?"":rs.getString("XMPZH"); //项目批准号
			   String CHARGENM = rs.getString("CHARGENM")==null?"":rs.getString("CHARGENM"); //负责人姓名
			   String CHARGEID = rs.getString("CHARGEID")==null?"":rs.getString("CHARGEID"); //
			   int CHARGERATE = rs.getInt("CHARGERATE"); //
			   String SSXY = rs.getString("SSXY")==null?"":rs.getString("SSXY"); //所属学院
			   String XMLY = rs.getString("XMLY")==null?"":rs.getString("XMLY"); //项目来源单位
			   String XMLYDW = rs.getString("XMLYDW")==null?"":rs.getString("XMLYDW"); //项目来源
			   String XMZT = rs.getString("XMZT")==null?"":rs.getString("XMZT"); //项目状态
			   String XMJB = rs.getString("XMJB")==null?"":rs.getString("XMJB"); //项目级别
			   String XMLB = rs.getString("XMLB")==null?"":rs.getString("XMLB"); //项目类别
			   String LXRQ = rs.getString("LXRQ")==null?"":rs.getString("LXRQ"); //立项日期
			   String LXND = rs.getString("LXND")==null?"":rs.getString("LXND"); //立项年度
			   String KSRQ = rs.getString("KSRQ")==null?"":rs.getString("KSRQ"); //项目开始日期
			   String JSRQ = rs.getString("JSRQ")==null?"":rs.getString("JSRQ"); //项目结束日期
			   String CGXS = rs.getString("CGXS")==null?"":rs.getString("CGXS"); //成果形式
			   String HDLX = rs.getString("HDLX")==null?"":rs.getString("HDLX"); //活动类型 
			   String GKLX = rs.getString("GKLX")==null?"":rs.getString("GKLX"); //归口类型
			   String ZZXS = rs.getString("ZZXS")==null?"":rs.getString("ZZXS"); //组织形式
			   String FWHY = rs.getString("FWHY")==null?"":rs.getString("FWHY"); //服务的国民经济行业
			   
			   String NAME1 = rs.getString("NAME1")==null?"":rs.getString("NAME1"); //
			   String ID1 = rs.getString("ID1")==null?"":rs.getString("ID1"); //
			   int RATE1 = rs.getInt("RATE1"); //
			   String NAME2 = rs.getString("NAME2")==null?"":rs.getString("NAME2"); //
			   String ID2 = rs.getString("ID2")==null?"":rs.getString("ID2"); //
			   int RATE2 = rs.getInt("RATE2"); //
			   String NAME3 = rs.getString("NAME3")==null?"":rs.getString("NAME3"); //
			   String ID3 = rs.getString("ID3")==null?"":rs.getString("ID3"); //
			   int RATE3 = rs.getInt("RATE3"); //
			   String NAME4 = rs.getString("NAME4")==null?"":rs.getString("NAME4"); //
			   String ID4 = rs.getString("ID4")==null?"":rs.getString("ID4"); //
			   int RATE4 = rs.getInt("RATE4"); //
			   String NAME5 = rs.getString("NAME5")==null?"":rs.getString("NAME5"); //
			   String ID5 = rs.getString("ID5")==null?"":rs.getString("ID5"); //
			   int RATE5 = rs.getInt("RATE5"); //
			   
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
			   
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+(XMMC)+b2+"</td>\n"); //项目名称
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(XMBH)+b2+"</td>\n"); //项目编号
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CHARGENM)+b2+"</td>\n"); //负责人姓名
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(SSXY)+b2+"</td>\n"); //所属学院
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(XMZT)+b2+"</td>\n"); //项目状态
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(XMJB)+b2+"</td>\n"); //项目级别
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(XMLB)+b2+"</td>\n"); //项目类别
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CGXS)+b2+"</td>\n");  //成果形式
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(HDLX)+b2+"</td>\n");  //活动类型 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(GKLX)+b2+"</td>\n"); //归口类型
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(ZZXS)+b2+"</td>\n"); //组织形式
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(FWHY)+b2+"</td>\n"); //服务的国民经济行业
			   
			   sb.append("<td"+bg+" align='left' >"+b1+(joinMan)+b2+"</td>\n"); //其他参与人数
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(aprvStatus)+b2+"</td>\n");
			   if (STATUS == 0 && AUTOADD == 0) {
				   sb.append("<td"+bg+" align='left' style='word-break:break-all'><a href='#' onclick='viewDetail("+ID+");return false;'>详情</a>&nbsp&nbsp");
				   sb.append("<a href='#' onclick='modifyData("+bindId+", "+ID+");return false;'  title='科研项目'><img src='../aws_img/man.gif' border='0'>修改</a>&nbsp&nbsp");
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
			   String pageIndexStr = new PageIndex("BNU_HR_KYXM", pageNow, lineCount, lineNumber).toString();
			   String pageIndexStr2 = pageIndexStr.replaceAll("frmMain", "frmKyxm");
			   
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
			return "#科研项目_数据查询异常";
		}finally{
			DBSql.close(conn, ps, rs);
		}
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_HR_KYXM.html"),hashTags);
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
			

			String copyDataStr = DBSql.getString(conn, "SELECT COPYDATAID FROM BO_KYXMTEMP WHERE ID="+Integer.parseInt(dataId), "COPYDATAID");
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
					String deleteSql = "delete from BO_KYXMTEMP where ID ="+copyDataIdInt;
					DBSql.executeUpdate(deleteSql);
				}
			}
			
			
			
			String sql = null;
			sql = "delete from  BO_KYXMTEMP where CHARGEID='"+awsuid+"' and ID="+Integer.parseInt(dataId);
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

		String XMMC = null; // 项目名称
		String XMYWMC = null; // 项目英文名称
		String XMBH = null; // 项目编号
		String XMPZH = null; // 项目批准号
		String SSXY = null; // 所属学院
		String XMLY = null; // 项目来源单位
		String XMLYDW = null; // 项目来源
		String XMZT = null; // 项目状态
		String XMJB = null; // 项目级别
		String XMLB = null; // 项目类别
		String LXRQ = null; // 立项日期
		String LXND = null; // 立项年度
		String KSRQ = null; // 项目开始日期
		String JSRQ = null; // 项目结束日期
		String XMJF = null; // 项目经费（万）
		String CGXS = null; // 成果形式
		String HDLX = null; // 活动类型
		String GKLX = null; //归口类型
		String ZZXS = null; //组织形式
		String FWHY = null; //服务的国民经济行业
		String XK = null;
		String XK2 = null;
		String JHWCSJ = null;
		String BKDW = null;
		String BKSJ = null;
		
		String CHARGENM = null; // 负责人姓名
		String CHARGEID = null; //
		int CHARGERATE = 0; //
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
			String sql = "select * from BO_KYXMTEMP where ID="+Integer.parseInt(dataId);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				XMMC = rs.getString("XMMC")==null?"":rs.getString("XMMC"); //项目名称
				XMYWMC = rs.getString("XMYWMC")==null?"":rs.getString("XMYWMC"); //项目英文名称
				   XMBH = rs.getString("XMBH")==null?"":rs.getString("XMBH"); //项目编号
				   XMPZH = rs.getString("XMPZH")==null?"":rs.getString("XMPZH"); //项目批准号
				   CHARGENM = rs.getString("CHARGENM")==null?"":rs.getString("CHARGENM"); //负责人姓名
				   CHARGEID = rs.getString("CHARGEID")==null?"":rs.getString("CHARGEID"); //
				   CHARGERATE = rs.getInt("CHARGERATE"); //
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
				   SSXY = rs.getString("SSXY")==null?"":rs.getString("SSXY"); //所属学院
				   XMLY = rs.getString("XMLY")==null?"":rs.getString("XMLY"); //项目来源单位
				   XMLYDW = rs.getString("XMLYDW")==null?"":rs.getString("XMLYDW"); //项目来源
				   XMZT = rs.getString("XMZT")==null?"":rs.getString("XMZT"); //项目状态
				   XMJB = rs.getString("XMJB")==null?"":rs.getString("XMJB"); //项目级别
				   XMLB = rs.getString("XMLB")==null?"":rs.getString("XMLB"); //项目类别
				   LXRQ = rs.getString("LXRQ")==null?"":rs.getString("LXRQ"); //立项日期
				   LXND = rs.getString("LXND")==null?"":rs.getString("LXND"); //立项年度
				   KSRQ = rs.getString("KSRQ")==null?"":rs.getString("KSRQ"); //项目开始日期
				   JSRQ = rs.getString("JSRQ")==null?"":rs.getString("JSRQ"); //项目结束日期
				   XMJF = rs.getString("XMJF")==null?"":rs.getString("XMJF"); //项目经费（万）
				   CGXS = rs.getString("CGXS")==null?"":rs.getString("CGXS"); //成果形式
				   HDLX = rs.getString("HDLX")==null?"":rs.getString("HDLX"); //活动类型 
				   GKLX = rs.getString("GKLX")==null?"":rs.getString("GKLX"); //归口类型
				   ZZXS = rs.getString("ZZXS")==null?"":rs.getString("ZZXS"); //组织形式
				   FWHY = rs.getString("FWHY")==null?"":rs.getString("FWHY"); //服务的国民经济行业
				   XK = rs.getString("XK")==null?"":rs.getString("XK");
				   XK2 = rs.getString("XK2")==null?"":rs.getString("XK2");
				   JHWCSJ = rs.getString("JHWCSJ")==null?"":rs.getString("JHWCSJ");
				   BKDW = rs.getString("BKDW")==null?"":rs.getString("BKDW");
				   BKSJ = rs.getString("BKSJ")==null?"":rs.getString("BKSJ");
				   
				   if(LXRQ.length() >= 10) {
					   LXRQ = LXRQ.substring(0,10);
				   }if(KSRQ.length() >= 10) {
					   KSRQ = KSRQ.substring(0,10);
				   }
				   if(JSRQ.length() >= 10) {
					   JSRQ = JSRQ.substring(0,10);
				   }
				   if(JHWCSJ.length() >= 10) {
					   JHWCSJ = JHWCSJ.substring(0,10);
				   }
				   if(BKSJ.length() >= 10) {
					   BKSJ = BKSJ.substring(0,10);
				   }
			}
			ps.close();
			rs.close();
		}catch(Exception e) {
			e.printStackTrace(System.err);
		}finally {
			DBSql.close(conn, ps, rs);
		}
		
		String XMMCBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + XMMC + "'>";
		String XMYWMCBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + XMYWMC + "'>";
		String XMBHBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + XMBH + "'>";
		String XMPZHBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + XMPZH + "'>";
		String CHARGENMBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + CHARGENM + "'>";
		String CHARGEIDBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + CHARGEID + "'>";
		String CHARGERATEBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CHARGERATE+"%" + "'>";
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
		String SSXYBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + SSXY + "'>";
		String XMLYBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + XMLY + "'>";
		String XMLYDWBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + XMLYDW + "'>";
		String XMZTBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + XMZT + "'>";
		String XMJBBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + XMJB + "'>";
		String XMLBBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + XMLB + "'>";
		String LXRQBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + LXRQ + "'>";
		String LXNDBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + LXND + "'>";
		String KSRQBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + KSRQ + "'>";
		String JSRQBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + JSRQ + "'>";
		String XMJFBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + XMJF + "'>";
		String CGXSBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CGXS + "'>";
		String HDLXBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + HDLX + "'>";
		String GKLXBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + GKLX + "'>";
		String ZZXSBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + ZZXS + "'>";
		String FWHYBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + FWHY + "'>";
		String XKBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + XK + "'>";
		String XK2Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + XK2 + "'>";
		String JHWCSJBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + JHWCSJ + "'>";
		String BKDWBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + BKDW + "'>";
		String BKSJBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + BKSJ + "'>";
		
		//table
		response.append("<table width=98% height=90% align=center border=0 cellspacing=0 cellpadding=0>");
		response.append("<tr><td><b><font size='3'>数据详细：</font></b><br><br>");
		
		response.append("<table width=98% align=center border=0 cellspacing=0 cellpadding=0>");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>项目名称：</td><td width=35%>").append(XMMCBuffer).append("</td>\n");
		response.append("<td width=20% align=left>项目英文名称：</td><td width=35%>").append(XMYWMCBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>项目编号：</td><td width=35%>").append(XMBHBuffer).append("</td>\n");
		response.append("<td width=20% align=left>项目批准号　：</td><td width=35%>").append(XMPZHBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>负责人姓名：</td><td width=35%>").append(CHARGENMBuffer).append("ID:").append(CHARGEIDBuffer).append("</td>\n");
		response.append("<td width=20% align=left>工作量/%：</td><td width=35%>").append(CHARGERATEBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>所属学院：</td><td width=35%>").append(SSXYBuffer).append("</td>\n");
		response.append("<td width=20% align=left>项目状态：</td><td width=35%>").append(XMZTBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>项目来源：</td><td width=35%>").append(XMLYDWBuffer).append("</td>\n");
		response.append("<td width=20% align=left>项目来源单位：</td><td width=35%>").append(XMLYBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>项目级别：</td><td width=35%>").append(XMJBBuffer).append("</td>\n");
		response.append("<td width=20% align=left>项目类别：</td><td width=35%>").append(XMLBBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>活动类型：</td><td width=35%>").append(HDLXBuffer).append("</td>\n");
		response.append("<td width=20% align=left>归口类型：</td><td width=35%>").append(GKLXBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>立项日期：</td><td width=35%>").append(LXRQBuffer).append("</td>\n");
		response.append("<td width=20% align=left>立项年度：</td><td width=35%>").append(LXNDBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>计划完成时间：</td><td width=35%>").append(JHWCSJBuffer).append("</td>\n");
		response.append("<td width=20% align=left>拨款时间：</td><td width=35%>").append(BKSJBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>学科：</td><td width=35%>").append(XKBuffer).append(" ").append(XK2Buffer).append("</td>\n");
		response.append("<td width=20% align=left>成果形式：</td><td width=35%>").append(CGXSBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>项目开始日期：</td><td width=35%>").append(KSRQBuffer).append("</td>\n");
		response.append("<td width=20% align=left>项目结束日期：</td><td width=35%>").append(JSRQBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>项目经费/万：</td><td width=35%>").append(XMJFBuffer).append("</td>\n");
		response.append("<td width=20% align=left>拨款单位：</td><td width=35%>").append(BKDWBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>组织形式：</td><td width=35%>").append(ZZXSBuffer).append("</td>\n");
		response.append("<td width=20% align=left>服务国民经济行业：</td><td width=35%>").append(FWHYBuffer).append("</td>\n");
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
		response.append("</table>\n");
		
		response.append("</td></tr></table>");
		
		return AjaxUtil.responseDialog("科研项目信息", 180, 70, 800, 500, response.toString(), "" ); //left,top,width,height
	}
	public String unlockData(String awsuid, String dataId) {
		BnuMainWeb web = new BnuMainWeb(super.getContext());
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			
			String sql = "select * from BO_KYXMTEMP where id="+Integer.parseInt(dataId);
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
			processInstanceId = WorkflowInstanceAPI.getInstance().createProcessInstance("016db93b762f1b862dc7f1345852dade", loginId, "科研项目申请");
			int[] processTaskInstanceIds = WorkflowTaskInstanceAPI.getInstance().createProcessTaskInstance(loginId, processInstanceId, SynType.synchronous, PriorityType.hight, 1, loginId, "科研项目申请", false, 0);
			
			String updateSql = "update BO_KYXMTEMP set BINDID="+processInstanceId
					+" , STATUS=0" 
					+" , FINALMARK=NVL(FINALMARK,0)-"+finalMark
					+" WHERE ID="+Integer.parseInt(dataId);
			DBSql.executeUpdate(conn, updateSql);
			
			CalMark calMark = new CalMark();
//			calMark.reduceFinalMark(loginId, "KYXM", finalMark);
			calMark.reduceFinalMarkWithYear(loginId, "KYXM", finalMark, datayear);
			
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

