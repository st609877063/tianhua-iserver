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

public class BnuHRGjhqkWeb extends ActionsoftWeb {
	/**
	 * ���췽��
	 * @param userContext �û������Ķ���
	 */
	public BnuHRGjhqkWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * ���췽��
	 */
	public BnuHRGjhqkWeb() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * ������Ŀ--��ѯ���
	 * @param pageNow    ҳ��
	 * @param lineNumber ÿҳ����
	 * @param awsuid     AWS�ʺ�
	 * @return
	 */
	public String getReport(int pageNow,int lineNumber,String awsuid) {
		//��Ŀ���û����룬�蹲������
		StringBuffer sb = new StringBuffer();
		Hashtable hashTags=new Hashtable();
		String sqlw = "select * from BO_GJHQKTEMP where 1=1 and CREATEUSER='"+awsuid+"' order by CREATEDATE desc";
		String sqlc = "select count(*) c from BO_GJHQKTEMP where 1=1 and CREATEUSER='"+awsuid+"'";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn2 = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
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
//			   SQL = "select top "+lineCount+" * from BO_GJHQKTEMP " +
//			   		"where CREATEUSER='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_GJHQKTEMP  where CREATEUSER='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }else{
		       SQL = "select * from (select rownum r,t1.* from ("+sqlw+") t1 where rownum<="+(start+lineNumber-1)+") t2 where t2.r>="+start; //oracle
			   //sql server
//			   SQL = "select top "+(start+lineNumber-1)+" * from BO_GJHQKTEMP " +
//			   		"where CREATEUSER='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_GJHQKTEMP  where CREATEUSER='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }
		   ps=conn.prepareStatement(SQL);
		   rs=ps.executeQuery();
		   while(rs.next()){
			   int ID = rs.getInt("ID");
			   int bindId = rs.getInt("BINDID");
			   
			   String QSRQ = rs.getString("QSRQ")==null?"":rs.getString("QSRQ");          // ��ʼ����
			   String JZRQ= rs.getString("JZRQ")==null?"":rs.getString("JZRQ");     // ��ֹ���� 
			   String CFGJ1 = rs.getString("CFGJ1")==null?"":rs.getString("CFGJ1");   // ���ҵ���
			   String CFDW = rs.getString("CFDW")==null?"":rs.getString("CFDW"); // ѧϰ�ɷõ�λ
			   String CFLX = rs.getString("CFLX")==null?"":rs.getString("CFLX");   // �ɷ�����
			   String CFHDNR = rs.getString("CFHDNR")==null?"":rs.getString("CFHDNR"); // �ɹ� 
			   String QTSM = rs.getString("QTSM")==null?"":rs.getString("QTSM"); // ����˵��
			   
			   if(QSRQ!=null && QSRQ.length()>10) {
				   QSRQ = QSRQ.substring(0, 10);
			   }
			   if(JZRQ!=null && JZRQ.length()>10) {
				   JZRQ = JZRQ.substring(0, 10);
			   }
			   
			   int STATUS = 0;
			   STATUS = rs.getInt("STATUS");
			   String aprvStatus = "";
			   if (STATUS == 0) {
				   aprvStatus = "δ����";
			   } else if (STATUS == 1) {
				   aprvStatus = "����ͨ��";
			   } else if (STATUS == 9) {
				   aprvStatus = "��������(����)";
			   } 
			   
			   //���taskId
//			   int taskId = 0;
//			   String taskSql = "select ID FROM WF_TASK WHERE BIND_ID = "+bindId;
//			   taskId = DBSql.getInt(taskSql, "ID");
			   
			   sb.append("<tr bgcolor='#ffffff'>\n");
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all'><input type='checkbox' name='checkData' id='"+ID+"'/></td>\n");//checkbox
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all' display='hidden'>"+ID+"</td>\n");//ID
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+xh+"</td>\n");//���
			   

			   
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+(QSRQ)+b2+"</td>\n"); //��Ŀ����
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(JZRQ)+b2+"</td>\n"); //��Ŀ���
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CFGJ1)+b2+"</td>\n"); //����������
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CFDW)+b2+"</td>\n"); //����ѧԺ
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CFLX)+b2+"</td>\n"); //��Ŀ״̬
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CFHDNR)+b2+"</td>\n"); //��Ŀ����
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(QTSM)+b2+"</td>\n"); //��Ŀ���
			   
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(aprvStatus)+b2+"</td>\n");
			   if (STATUS == 0) {
				   sb.append("<td"+bg+" align='left' style='word-break:break-all'>");
				   sb.append("<a href='#' onclick='modifyData("+bindId+", "+ID+");return false;'  title='���ʻ����'><img src='../aws_img/man.gif' border='0'>�޸�</a>&nbsp&nbsp");
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
			   String pageIndexStr = new PageIndex("BNU_HR_GJHQK", pageNow, lineCount, lineNumber).toString();
			   String pageIndexStr2 = pageIndexStr.replaceAll("frmMain", "frmGjhqk");
			   
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
			return "#���ʻ����_���ݲ�ѯ�쳣";
		}finally{
			DBSql.close(conn, ps, rs);
			DBSql.close(conn2, ps2, rs2);
		}
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_HR_GJHQK.html"),hashTags);
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
			sql = "delete from  BO_GJHQKTEMP where CREATEUSER='"+awsuid+"' and ID="+Integer.parseInt(dataId);
			ps1 = conn.prepareStatement(sql);
			int r = ps1.executeUpdate();
			if(r < 0) {
				MessageQueue.getInstance().putMessage(super.getContext().getUID(),"����ɾ��ʧ��");
				return web.getMain("11",awsuid);
			}
			ps1.close();
			conn.commit();
		}catch(Exception e) {
			e.printStackTrace(System.err);
			MessageQueue.getInstance().putMessage(super.getContext().getUID(),"����ɾ��ʧ��");
			return web.getMain("11",awsuid);
		}finally {
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace(System.err);
				MessageQueue.getInstance().putMessage(super.getContext().getUID(),"����ɾ��ʧ��");
				return web.getMain("11",awsuid);
			}
			DBSql.close(conn, ps1, rs1);
		}
		MessageQueue.getInstance().putMessage(super.getContext().getUID(),"����ɾ���ɹ���");
		return web.getMain("11",awsuid);
	}
	
	
	
	
}

