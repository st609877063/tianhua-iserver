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

public class BnuHRGnxxjlWeb extends ActionsoftWeb {
	/**
	 * ���췽��
	 * @param userContext �û������Ķ���
	 */
	public BnuHRGnxxjlWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * ���췽��
	 */
	public BnuHRGnxxjlWeb() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * ����ѧϰ����--��ѯ���
	 * @param pageNow    ҳ��
	 * @param lineNumber ÿҳ����
	 * @param awsuid     AWS�ʺ�
	 * @return
	 */
	public String getReport(int pageNow,int lineNumber,String awsuid) {
		StringBuffer sb = new StringBuffer();
		Hashtable hashTags=new Hashtable();
		//û�����ݹ���ֱ����CREATEUSER�б���Ӧ���û���
		String sqlw = "select * from BO_GNXXJLTEMP where 1=1 and CREATEUSER='"+awsuid+"' order by CREATEDATE desc";
		String sqlc = "select count(*) c from BO_GNXXJLTEMP where 1=1 and CREATEUSER='"+awsuid+"'";
		
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
//			   SQL = "select top "+lineCount+" * from BO_GNXXJLTEMP " +
//			   		"where CREATEUSER='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_GNXXJLTEMP  where CREATEUSER='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }else{
		       SQL = "select * from (select rownum r,t1.* from ("+sqlw+") t1 where rownum<="+(start+lineNumber-1)+") t2 where t2.r>="+start; //oracle
			   //sql server
//			   SQL = "select top "+(start+lineNumber-1)+" * from BO_GNXXJLTEMP " +
//			   		"where CREATEUSER='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_GNXXJLTEMP  where CREATEUSER='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }
		   ps=conn.prepareStatement(SQL);
		   rs=ps.executeQuery();
		   while(rs.next()){
			   int ID = rs.getInt("ID");
			   int bindId = rs.getInt("BINDID");
			   
			   String RXSJ = rs.getString("RXSJ")==null?"":rs.getString("RXSJ");
			   String BYSJ = rs.getString("BYSJ")==null?"":rs.getString("BYSJ");
			   String BYXX = rs.getString("BYXX")==null?"":rs.getString("BYXX");
			   String SXZY = rs.getString("SXZY")==null?"":rs.getString("SXZY");
			   String YJFX = rs.getString("YJFX")==null?"":rs.getString("YJFX");
			   String XW = rs.getString("XW")==null?"":rs.getString("XW");
			   String XWSYSJ = rs.getString("XWSYSJ")==null?"":rs.getString("XWSYSJ");
			   String XXXS = rs.getString("XXXS")==null?"":rs.getString("XXXS");
			   String XZ = rs.getString("XZ")==null?"":rs.getString("XZ");
//			   String ATTACH = rs.getString("ATTACH")==null?"":rs.getString("ATTACH");
			   int STATUS = 0;
			   STATUS = rs.getInt("STATUS");
			   String aprvStatus = null;
			   if (STATUS == 0) {
				   aprvStatus = "δ����";
			   } else if (STATUS == 1) {
				   aprvStatus = "����ͨ��";
			   } else if (STATUS == 9) {
				   aprvStatus = "��������(����)";
			   }
			   if(RXSJ.length() >= 10) {
				   RXSJ = RXSJ.substring(0,10);
			   }
			   if(BYSJ.length() >= 10) {
				   BYSJ = BYSJ.substring(0,10);
			   }
			   if(XWSYSJ.length() >= 10) {
				   XWSYSJ = XWSYSJ.substring(0,10);
			   }
			   
			   sb.append("<tr bgcolor='#ffffff'>\n");
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all'><input type='checkbox' name='checkData' id='"+ID+"'/></td>\n");//checkbox
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all' display='hidden'>"+ID+"</td>\n");//ID
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+xh+"</td>\n");//���
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+(RXSJ)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(BYSJ)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(BYXX)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(SXZY)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(YJFX)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(XW)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(XWSYSJ)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(XXXS)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(XZ)+b2+"</td>\n");
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(ATTACH)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(aprvStatus)+b2+"</td>\n");
			   if (STATUS == 0) {
				   sb.append("<td"+bg+" align='left' style='word-break:break-all'>");
				   sb.append("<a href='#' onclick='modifyData("+bindId+", "+ID+");return false;'  title='����ѧϰ����'><img src='../aws_img/man.gif' border='0'>�޸�</a>&nbsp&nbsp");
//				   sb.append("<a href='#' onclick='deleteData("+ID+");return false;'>ɾ��</a></td>\n"); //����
				   sb.append("</td>\n"); //����
			   } else if (STATUS == 1 || STATUS == 9) {   
				   sb.append("<td"+bg+" align='left' style='word-break:break-all'>�޲���</td>\n"); //����
			   }
			   sb.append("</tr>\n");
			   xh++;
		   }
		   ps.close();
		   rs.close();
		   //ҳ�浼��
		   if (lineCount > lineNumber) {
			   String pageIndexStr = new PageIndex("BNU_HR_GNXXJL", pageNow, lineCount, lineNumber).toString();
			   String pageIndexStr2 = pageIndexStr.replaceAll("frmMain", "frmGnxxjl");
			   
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
			return "#����ѧϰ����_���ݲ�ѯ�쳣";
		}finally{
			DBSql.close(conn, ps, rs);
		}
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_HR_GNXXJL.html"),hashTags);
	}
	
	
	/**
	 * ɾ��
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
			sql = "delete from  BO_GNXXJLTEMP where CREATEUSER='"+awsuid+"' and ID="+Integer.parseInt(dataId);
			ps1 = conn.prepareStatement(sql);
			int r = ps1.executeUpdate();
			if(r < 0) {
				MessageQueue.getInstance().putMessage(super.getContext().getUID(),"����ɾ��ʧ��");
				return web.getMain("2",awsuid);
			}
			ps1.close();
			conn.commit();
		}catch(Exception e) {
			e.printStackTrace(System.err);
			MessageQueue.getInstance().putMessage(super.getContext().getUID(),"����ɾ��ʧ��");
			return web.getMain("2",awsuid);
		}finally {
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace(System.err);
				MessageQueue.getInstance().putMessage(super.getContext().getUID(),"����ɾ��ʧ��");
				return web.getMain("2",awsuid);
			}
			DBSql.close(conn, ps1, rs1);
		}
		MessageQueue.getInstance().putMessage(super.getContext().getUID(),"����ɾ���ɹ���");
		return web.getMain("2",awsuid);
	}
	
	/**
	 * �����޸Ĺ���ѧϰ�����Ի���
	 * @param awsuid
	 * @return
	 */
	public String configGnxxjl(String awsuid, String dataId) {
		StringBuffer response = new StringBuffer();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String RXSJ = null;
		String BYSJ = null;
		String BYXX = null;
		String SXZY = null;
		String YJFX = null;
		String XW = null;
		String XWSYSJ = null;
		String XXXS = null;
		String XZ = null;
		String ATTACH = null;
		   
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			String sql = "select * from BO_GNXXJLTEMP where CREATEUSER='"+awsuid+"' and ID="+Integer.parseInt(dataId);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				RXSJ = rs.getString("RXSJ")==null?"":rs.getString("RXSJ");
				BYSJ = rs.getString("BYSJ")==null?"":rs.getString("BYSJ");
				BYXX = rs.getString("BYXX")==null?"":rs.getString("BYXX");
				SXZY = rs.getString("SXZY")==null?"":rs.getString("SXZY");
				YJFX = rs.getString("YJFX")==null?"":rs.getString("YJFX");
				XW = rs.getString("XW")==null?"":rs.getString("XW");
				XWSYSJ = rs.getString("XWSYSJ")==null?"":rs.getString("XWSYSJ");
				XXXS = rs.getString("XXXS")==null?"":rs.getString("XXXS");
				XZ = rs.getString("XZ")==null?"":rs.getString("XZ");
				ATTACH = rs.getString("ATTACH")==null?"":rs.getString("ATTACH");
			}
			ps.close();
			rs.close();
		}catch(Exception e) {
			e.printStackTrace(System.err);
		}finally {
			DBSql.close(conn, ps, rs);
		}
		
		String dataIdBuffer = "<input type='text' name='dataId' display='none' value='" + dataId + "'>";
		String RXSJBuffer = "<input type='text' name='RXSJ' class ='actionsoftInput'  maxlength=30 size=30 value='" + RXSJ + "'>";
		String BYSJBuffer = "<input type='text' name='BYSJ' class ='actionsoftInput'  maxlength=30 size=30 value='" + BYSJ + "'>";
		String BYXXBuffer = "<input type='text' name='BYXX' class ='actionsoftInput'  maxlength=30 size=30 value='" + BYXX + "'>";
		String SXZYBuffer = "<input type='text' name='SXZY' class ='actionsoftInput'  maxlength=30 size=30 value='" + SXZY + "'>";
		String YJFXBuffer = "<input type='text' name='YJFX' class ='actionsoftInput'  maxlength=30 size=30 value='" + YJFX + "'>";
		String XWBuffer = "<input type='text' name='XW' class ='actionsoftInput'  maxlength=30 size=30 value='" + XW + "'>";
		String XWSYSJBuffer = "<input type='text' name='XWSYSJ' class ='actionsoftInput'  maxlength=30 size=30 value='" + XWSYSJ + "'>";
		String XXXSBuffer = "<input type='text' name='XXXS' class ='actionsoftInput'  maxlength=30 size=30 value='" + XXXS + "'>";
		String XZBuffer = "<input type='text' name='XZ' class ='actionsoftInput'  maxlength=30 size=30 value='" + XZ + "'>";
		String ATTACHBuffer = "<input type='text' name='ATTACH' class ='actionsoftInput'  maxlength=30 size=30 value='" + ATTACH + "'>";
		
		//table
		response.append("<table width=98% align=center border=0 cellspacing=0 cellpadding=0>");
		response.append("<tr><td><b>�������ݣ�</b>[<a href='' onclick=\"save(frmMain,'BNU_HR_GNXXJL_Save');return false;\"><img src=../aws_img/save.gif border=0>&nbsp;����</a>]<br>");
		
		response.append("<br><table width=98% align=center border=0 cellspacing=0 cellpadding=0>");
		
		response.append("<tr><td width=30% display='none'>����dataId��</td><td width=80%>").append(dataIdBuffer).append("</td></tr>\n");
		response.append("<tr><td width=30% align=left>������ѧʱ�䣺</td><td width=80%>").append(RXSJBuffer).append("</td></tr>\n");
		response.append("<tr><td width=30% align=left>������ҵʱ�䣺</td><td width=80%>").append(BYSJBuffer).append("</td></tr>\n");
		response.append("<tr><td width=30% align=left>��ѧУ��λ��</td><td width=80%>").append(BYXXBuffer).append("</td></tr>\n");
		response.append("<tr><td width=30% align=left>������ѧרҵ��</td><td width=80%>").append(SXZYBuffer).append("</td></tr>\n");
		response.append("<tr><td width=30% align=left>�����о�����</td><td width=80%>").append(YJFXBuffer).append("</td></tr>\n");
		response.append("<tr><td width=30% align=left>��������ѧλ��</td><td width=80%>").append(XWBuffer).append("</td></tr>\n");
		response.append("<tr><td width=30% align=left>ѧλ����ʱ�䣺</td><td width=80%>").append(XWSYSJBuffer).append("</td></tr>\n");
		response.append("<tr><td width=30% align=left>����ѧϰ��ʽ	��</td><td width=80%>").append(XXXSBuffer).append("</td></tr>\n");
		response.append("<tr><td width=30% align=left>		ѧ�ƣ�</td><td width=80%>").append(XZBuffer).append("</td></tr>\n");
		response.append("<tr><td width=30% align=left>	   ɨ�����</td><td width=80%>").append(ATTACHBuffer).append("</td></tr>\n");
		response.append("</table>\n");
		
		response.append("</td></tr></table>");
		return AjaxUtil.responseDialog("����ѧϰ������Ϣ", 180, 70, 400, 350, response.toString(), "" );
	}
	
	/**
	 * ����
	 * @param awsuid
	 * @return
	 */
	public String saveGnxxjl(String awsuid,String dataId, 
			String RXSJ, String BYSJ, String BYXX, String SXZY, String YJFX, 
			String XW, String XWSYSJ, String XXXS, String XZ, String ATTACH) {
		BnuMainWeb web = new BnuMainWeb(super.getContext());
		Connection conn = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(false);
			
			String sql = null;
			sql = "update BO_GNXXJLTEMP set RXSJ=?, BYSJ=?, BYXX=?, SXZY=?, YJFX=?, XW=?, XWSYSJ=?, XXXS=?, XZ=?, ATTACH=? ,UPDATEDATE=?, UPDATEUSER=? where CREATEUSER=? and ID=?";
			ps1 = conn.prepareStatement(sql);
			ps1.setString(1, RXSJ==null?"":RXSJ);
			ps1.setString(2, BYSJ==null?"":BYSJ);
			ps1.setString(3, BYXX==null?"":BYXX);
			ps1.setString(4, SXZY==null?"":SXZY);
			ps1.setString(5, YJFX==null?"":YJFX);
			ps1.setString(6, XW==null?"":XW);
			ps1.setString(7, XWSYSJ==null?"":XWSYSJ);
			ps1.setString(8, XXXS==null?"":XXXS);
			ps1.setString(9, XZ==null?"":XZ);
			ps1.setString(10, ATTACH==null?"":ATTACH);
			ps1.setTimestamp(11,new java.sql.Timestamp(new Date().getTime()));//�޸�����
			ps1.setString(12, awsuid==null?"":awsuid);
			ps1.setString(13, awsuid==null?"":awsuid);
			ps1.setInt(14,Integer.parseInt(dataId));
			int r = ps1.executeUpdate();
			if(r < 0) {
				MessageQueue.getInstance().putMessage(super.getContext().getUID(),"����ѧϰ�����޸�ʧ��");
				return web.getMain("2",awsuid);
			}
			ps1.close();
			conn.commit();
		}catch(Exception e) {
			e.printStackTrace(System.err);
			MessageQueue.getInstance().putMessage(super.getContext().getUID(),"����ѧϰ�����޸�ʧ��");
			return web.getMain("2",awsuid);
		}finally {
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace(System.err);
				MessageQueue.getInstance().putMessage(super.getContext().getUID(),"����ѧϰ�����޸�ʧ��");
				return web.getMain("2",awsuid);
			}
			DBSql.close(conn, ps1, rs1);
		}
		MessageQueue.getInstance().putMessage(super.getContext().getUID(),"����ѧϰ�����޸ĳɹ�");
		return web.getMain("2",awsuid);
	}
	
}

