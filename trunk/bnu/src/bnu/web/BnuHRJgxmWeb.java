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

public class BnuHRJgxmWeb extends ActionsoftWeb {
	/**
	 * ���췽��
	 * @param userContext �û������Ķ���
	 */
	public BnuHRJgxmWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * ���췽��
	 */
	public BnuHRJgxmWeb() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * �̸���Ŀ--��ѯ���
	 * @param pageNow    ҳ��
	 * @param lineNumber ÿҳ����
	 * @param awsuid     AWS�ʺ�
	 * @return
	 */
	public String getReport(int pageNow,int lineNumber,String awsuid) {
		//��Ŀ���û����룬�蹲������
		StringBuffer sb = new StringBuffer();
		Hashtable hashTags=new Hashtable();
//		String sqlw = "select * from BO_JGXMTEMP where 1=1 and ZCRID='"+awsuid+"' order by CREATEDATE desc";
//		String sqlc = "select count(*) c from BO_JGXMTEMP where 1=1 and ZCRID='"+awsuid+"'";
		String sqlw = " select * from (" +
				" select * from BO_JGXMTEMP where  ZCRID='"+awsuid+"'  and (AUTOADD is null or AUTOADD=0) " +
				" union select * from BO_JGXMTEMP where  ID1='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_JGXMTEMP where  ID2='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_JGXMTEMP where  ID3='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_JGXMTEMP where  ID4='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_JGXMTEMP where  ID5='"+awsuid+"' and AUTOADD=1 " +
				") order by CREATEDATE desc";
		String sqlc = " select count(*) c from (" +
				" select * from BO_JGXMTEMP where  ZCRID='"+awsuid+"'  and (AUTOADD is null or AUTOADD=0) " +
				" union select * from BO_JGXMTEMP where  ID1='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_JGXMTEMP where  ID2='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_JGXMTEMP where  ID3='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_JGXMTEMP where  ID4='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_JGXMTEMP where  ID5='"+awsuid+"' and AUTOADD=1 )";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String bg = "",bg1 = "",b1 = "", b2 = "";
		bg = " style=\"padding:5px;\"";  
		bg1 = "../aws_img/report-bg-blue2.gif";
		
		String  XMMC = null; //��Ŀ����
		String  XMLX = null; //��Ŀ����
		String  XMLB = null; //��Ŀ���
		String  LXRQ = null; //����ʱ��
		String  JTRQ = null; //����ʱ��
		String  ZCR = null; //������
		String  ZCRID = null; //
		String  ZCRRATE = null; //
		String  SZDW = null; //���ڵ�λ
		String  PZJF = null; //��׼����
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
//			   SQL = "select top "+lineCount+" * from BO_JGXMTEMP " +
//			   		"where ZCRID='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_JGXMTEMP  where ZCRID='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }else{
		       SQL = "select * from (select rownum r,t1.* from ("+sqlw+") t1 where rownum<="+(start+lineNumber-1)+") t2 where t2.r>="+start; //oracle
			   //sql server
//			   SQL = "select top "+(start+lineNumber-1)+" * from BO_JGXMTEMP " +
//			   		"where ZCRID='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_JGXMTEMP  where ZCRID='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }
		   ps=conn.prepareStatement(SQL);
		   rs=ps.executeQuery();
		   while(rs.next()){
			   int ID = rs.getInt("ID");
			   int bindId = rs.getInt("BINDID");
			   
			   XMMC = rs.getString("XMMC")==null?"":rs.getString("XMMC"); // ��Ŀ����
			   XMLX = rs.getString("XMLX")==null?"":rs.getString("XMLX"); // ��Ŀ����
			   XMLB = rs.getString("XMLB")==null?"":rs.getString("XMLB"); // ��Ŀ���
			   LXRQ = rs.getString("LXRQ")==null?"":rs.getString("LXRQ"); // ����ʱ��
			   JTRQ = rs.getString("JTRQ")==null?"":rs.getString("JTRQ"); // ����ʱ��
			   ZCR = rs.getString("ZCR")==null?"":rs.getString("ZCR"); // ������
			   ZCRID = rs.getString("ZCRID")==null?"":rs.getString("ZCRID"); //
			   ZCRRATE = rs.getString("ZCRRATE")==null?"":rs.getString("ZCRRATE"); //
			   SZDW = rs.getString("SZDW")==null?"":rs.getString("SZDW"); // ���ڵ�λ
			   PZJF = rs.getString("PZJF")==null?"":rs.getString("PZJF"); // ��׼����
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
				   autoaddStatus = "���Զ����";
			   } else if (AUTOADD == 1) {
				   autoaddStatus = "�Զ����";
			   } 
			   
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
			   
			   if (AUTOADD == 1) {
				   aprvStatus = "������Ŀ";
			   } 
			   
			   if(LXRQ.length() >= 10) {
				   LXRQ = LXRQ.substring(0, 10);
			   }
			   if(JTRQ.length() >= 10) {
				   JTRQ = JTRQ.substring(0, 10);
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
			   
			   //���taskId
//			   int taskId = 0;
//			   String taskSql = "select ID FROM WF_TASK WHERE BIND_ID = "+bindId;
//			   taskId = DBSql.getInt(taskSql, "ID");
			   
			   sb.append("<tr bgcolor='#ffffff'>\n");
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all'><input type='checkbox' name='checkData' id='"+ID+"'/></td>\n");//checkbox
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all' display='hidden'>"+ID+"</td>\n");//ID
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+xh+"</td>\n");//���
			   
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+(XMMC)+b2+"</td>\n"); //��Ŀ����
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(XMLX)+b2+"</td>\n"); //��Ŀ����
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(XMLB)+b2+"</td>\n"); //��Ŀ���
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(LXRQ)+b2+"</td>\n"); //����ʱ��
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(JTRQ)+b2+"</td>\n"); //����ʱ��
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(ZCR)+b2+"</td>\n"); //������
//			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(ZCRRATE)+"%"+b2+"</td>\n"); //
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(SZDW)+b2+"</td>\n");  //���ڵ�λ
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(PZJF)+b2+"</td>\n");  //��׼����
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(joinMan)+b2+"</td>\n"); //������������
			   
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(aprvStatus)+b2+"</td>\n");
			   if (STATUS == 0 && AUTOADD == 0) {
				   sb.append("<td"+bg+" align='left' style='word-break:break-all'><a href='#' onclick='viewDetail("+ID+");return false;'>����</a>&nbsp&nbsp");
				   sb.append("<a href='#' onclick='modifyData("+bindId+", "+ID+");return false;'  title='�̸���Ŀ'><img src='../aws_img/man.gif' border='0'>�޸�</a>&nbsp&nbsp");
//				   sb.append("<a href='#' onclick='deleteData("+ID+");return false;'>ɾ��</a></td>\n"); //����
				   sb.append("</td>\n"); //����
			   } else if (STATUS == 1 || AUTOADD == 1) {   
				   sb.append("<td"+bg+" align='left' style='word-break:break-all'><a href='#' onclick='viewDetail("+ID+");return false;'>����</a></td>");
			   } else if (STATUS == 9 || AUTOADD == 0) {   
				   sb.append("<td"+bg+" align='left' style='word-break:break-all'><a href='#' onclick='viewDetail("+ID+");return false;'>����</a>&nbsp&nbsp");
				   sb.append("<a href='#' onclick='unlockData("+ID+");return false;'><img src='../aws_img/man.gif' border='0'>����</a>");
				   sb.append("</td>\n");
			   }
			   sb.append("</tr>\n");
			   xh++;
		   }
		   ps.close();
		   rs.close();
		   //ҳ�浼��
		   if (lineCount > lineNumber) {
			   String pageIndexStr = new PageIndex("BNU_HR_JGXM", pageNow, lineCount, lineNumber).toString();
			   String pageIndexStr2 = pageIndexStr.replaceAll("frmMain", "frmJgxm");
			   
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
			return "#�̸���Ŀ_���ݲ�ѯ�쳣";
		}finally{
			DBSql.close(conn, ps, rs);
		}
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_HR_JGXM.html"),hashTags);
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
			

			String copyDataStr = DBSql.getString(conn, "SELECT COPYDATAID FROM BO_JGXMTEMP WHERE ID="+Integer.parseInt(dataId), "COPYDATAID");
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
					String deleteSql = "delete from BO_JGXMTEMP where ID ="+copyDataIdInt;
					DBSql.executeUpdate(deleteSql);
				}
			}
			
			
			
			String sql = null;
			sql = "delete from  BO_JGXMTEMP where ZCRID='"+awsuid+"' and ID="+Integer.parseInt(dataId);
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
	
	public String viewDetail(String awsuid, String dataId) {
		StringBuffer response = new StringBuffer();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String  XMMC = null; //��Ŀ����
		String  XMLX = null; //��Ŀ����
		String  XMLB = null; //��Ŀ���
		String  LXRQ = null; //����ʱ��
		String  JTRQ = null; //����ʱ��
		String  ZCR = null; //������
		String  ZCRID = null; //
		String  ZCRRATE = null; //
		String  SZDW = null; //���ڵ�λ
		String  PZJF = null; //��׼����
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
			String sql = "select * from BO_JGXMTEMP where  ID="+Integer.parseInt(dataId);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				XMMC = rs.getString("XMMC")==null?"":rs.getString("XMMC"); // ��Ŀ����
				XMLX = rs.getString("XMLX")==null?"":rs.getString("XMLX"); // ��Ŀ����
				XMLB = rs.getString("XMLB")==null?"":rs.getString("XMLB"); // ��Ŀ���
				LXRQ = rs.getString("LXRQ")==null?"":rs.getString("LXRQ"); // ����ʱ��
				JTRQ = rs.getString("JTRQ")==null?"":rs.getString("JTRQ"); // ����ʱ��
				ZCR = rs.getString("ZCR")==null?"":rs.getString("ZCR"); // ������
				ZCRID = rs.getString("ZCRID")==null?"":rs.getString("ZCRID"); //
				ZCRRATE = rs.getString("ZCRRATE")==null?"":rs.getString("ZCRRATE"); //
				SZDW = rs.getString("SZDW")==null?"":rs.getString("SZDW"); // ���ڵ�λ
				PZJF = rs.getString("PZJF")==null?"":rs.getString("PZJF"); // ��׼����
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
				   
				if(LXRQ.length() >= 10) {
					LXRQ = LXRQ.substring(0, 10);
				}
				if(JTRQ.length() >= 10) {
					JTRQ = JTRQ.substring(0, 10);
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
		String XMLXCBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + XMLX + "'>";
		String XMLBBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + XMLB + "'>";
		String LXPQHBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + LXRQ + "'>";
		String JTRQMBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + JTRQ + "'>";
		String ZCRBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + ZCR + "'>";
		String ZCRIDBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + ZCRID+"%" + "'>";
		String ZCRRATEBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + ZCRRATE+"%" + "'>";
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
		String SZDWBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + SZDW + "'>";
		String PZJFBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + PZJF + "'>";
				
		//table
		response.append("<table width=98% height=90% align=center border=0 cellspacing=0 cellpadding=0>");
		response.append("<tr><td><b><font size='3'>������ϸ��</font></b><br><br>");
		
		response.append("<table width=98% align=center border=0 cellspacing=0 cellpadding=0>");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>��Ŀ���ƣ�</td><td width=35%>").append(XMMCBuffer).append("</td>\n");
		response.append("<td width=20% align=left>��Ŀ���ͣ�</td><td width=35%>").append(XMLXCBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left colspan='2'>��Ŀ���</td><td width=35%>").append(XMLBBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>����ʱ�䣺</td><td width=35%>").append(LXPQHBuffer).append("</td>\n");
		response.append("<td width=20% align=left>����ʱ�䣺</td><td width=35%>").append(JTRQMBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>������������</td><td width=35%>").append(ZCRBuffer).append("ID:").append(ZCRIDBuffer).append("</td>\n");
		response.append("<td width=20% align=left>��������%����</td><td width=35%>").append(ZCRRATEBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>���ڵ�λ��</td><td width=35%>").append(SZDWBuffer).append("</td>\n");
		response.append("<td width=20% align=left>��׼���ѣ�</td><td width=35%>").append(PZJFBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>������1��</td><td width=35%>").append(NAME1Buffer).append("ID:").append(ID1Buffer).append("</td>\n");
		response.append("<td width=20% align=left>��������%����</td><td width=35%>").append(RATE1Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>������2��</td><td width=35%>").append(NAME2Buffer).append("ID:").append(ID2Buffer).append("</td>\n");
		response.append("<td width=20% align=left>��������%����</td><td width=35%>").append(RATE2Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>������3��</td><td width=35%>").append(NAME3Buffer).append("ID:").append(ID3Buffer).append("</td>\n");
		response.append("<td width=20% align=left>��������%����</td><td width=35%>").append(RATE3Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>������4��</td><td width=35%>").append(NAME4Buffer).append("ID:").append(ID4Buffer).append("</td>\n");
		response.append("<td width=20% align=left>��������%����</td><td width=35%>").append(RATE4Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>������5��</td><td width=35%>").append(NAME5Buffer).append("ID:").append(ID5Buffer).append("</td>\n");
		response.append("<td width=20% align=left>��������%����</td><td width=35%>").append(RATE5Buffer).append("</td>\n");
		response.append("</tr>\n");
		
		response.append("</td></tr></table>");
		
		return AjaxUtil.responseDialog("�̸���Ŀ��Ϣ", 180, 70, 800, 500, response.toString(), "" ); //left,top,width,height
	}
	public String unlockData(String awsuid, String dataId) {
		BnuMainWeb web = new BnuMainWeb(super.getContext());
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			
			String sql = "select * from BO_JGXMTEMP where id="+Integer.parseInt(dataId);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			String loginId = "";
			int finalMark = 0;
			String datayear = "";
			while(rs.next()){
				loginId = rs.getString("ZCR")==null?"":rs.getString("ZCR").trim();
				finalMark = rs.getInt("FINALMARK");
				datayear = rs.getString("DATAYEAR")==null?"":rs.getString("DATAYEAR").trim();
			}
			
			//����AWS����-----------------Ϊ���ݰ�����
			int processInstanceId = 0;
			processInstanceId = WorkflowInstanceAPI.getInstance().createProcessInstance("007b23151fd4ccbe70caeb032a57569c", loginId, "�̸���Ŀ����");
			int[] processTaskInstanceIds = WorkflowTaskInstanceAPI.getInstance().createProcessTaskInstance(loginId, processInstanceId, SynType.synchronous, PriorityType.hight, 1, loginId, "�̸���Ŀ����", false, 0);
			
			String updateSql = "update BO_JGXMTEMP set BINDID="+processInstanceId
					+" , STATUS=0" 
					+" , FINALMARK=NVL(FINALMARK,0)-"+finalMark
					+" WHERE ID="+Integer.parseInt(dataId);
			DBSql.executeUpdate(conn, updateSql);
			
			CalMark calMark = new CalMark();
//			calMark.reduceFinalMark(loginId, "JGXM", finalMark);
			calMark.reduceFinalMarkWithYear(loginId, "JGXM", finalMark, datayear);
			
		}catch(Exception e) {
			e.printStackTrace(System.err);
			MessageQueue.getInstance().putMessage(super.getContext().getUID(),"���ݽ���ʧ��");
			return web.getMain("11",awsuid);
		}finally {
			DBSql.close(conn, ps, rs);
		}
		
		MessageQueue.getInstance().putMessage(super.getContext().getUID(),"���ݽ����ɹ���");
		return web.getMain("1",awsuid);
	}
	
	
}

