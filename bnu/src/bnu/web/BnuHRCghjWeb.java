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

public class BnuHRCghjWeb extends ActionsoftWeb {
	/**
	 * ���췽��
	 * @param userContext �û������Ķ���
	 */
	public BnuHRCghjWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * ���췽��
	 */
	public BnuHRCghjWeb() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * �ɹ���--��ѯ���
	 * @param pageNow    ҳ��
	 * @param lineNumber ÿҳ����
	 * @param awsuid     AWS�ʺ�
	 * @return
	 */
	public String getReport(int pageNow,int lineNumber,String awsuid) {
		//��Ŀ���û����룬�蹲������
		StringBuffer sb = new StringBuffer();
		Hashtable hashTags=new Hashtable();
//		String sqlw = "select * from BO_CGHJTEMP where 1=1 and CHARGEID='"+awsuid+"' order by CREATEDATE desc";
//		String sqlc = "select count(*) c from BO_CGHJTEMP where 1=1 and CHARGEID='"+awsuid+"'";
		String sqlw = " select * from (" +
				" select * from BO_CGHJTEMP where  CHARGEID='"+awsuid+"'  and (AUTOADD is null or AUTOADD=0) " +
				" union select * from BO_CGHJTEMP where  ID1='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_CGHJTEMP where  ID2='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_CGHJTEMP where  ID3='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_CGHJTEMP where  ID4='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_CGHJTEMP where  ID5='"+awsuid+"' and AUTOADD=1 " +
				") order by CREATEDATE desc";
		String sqlc = " select count(*) c from (" +
				" select * from BO_CGHJTEMP where  CHARGEID='"+awsuid+"'  and (AUTOADD is null or AUTOADD=0) " +
				" union select * from BO_CGHJTEMP where  ID1='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_CGHJTEMP where  ID2='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_CGHJTEMP where  ID3='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_CGHJTEMP where  ID4='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_CGHJTEMP where  ID5='"+awsuid+"' and AUTOADD=1 )";

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
//			   SQL = "select top "+lineCount+" * from BO_CGHJTEMP " +
//			   		"where CHARGEID='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_CGHJTEMP  where CHARGEID='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }else{
		       SQL = "select * from (select rownum r,t1.* from ("+sqlw+") t1 where rownum<="+(start+lineNumber-1)+") t2 where t2.r>="+start; //oracle
			   //sql server
//			   SQL = "select top "+(start+lineNumber-1)+" * from BO_CGHJTEMP " +
//			   		"where CHARGEID='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_CGHJTEMP  where CHARGEID='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }
		   ps=conn.prepareStatement(SQL);
		   rs=ps.executeQuery();
		   while(rs.next()){
			   int ID = rs.getInt("ID");
			   int bindId = rs.getInt("BINDID");
			   
			   String HAVNAME = rs.getString("HAVNAME")==null?"":rs.getString("HAVNAME");           // �ɹ�����
			   String AWARDLEVEL= rs.getString("AWARDLEVEL")==null?"":rs.getString("AWARDLEVEL");     // �������� 
			   String AWARDNAME = rs.getString("AWARDNAME")==null?"":rs.getString("AWARDNAME");  // ��������
			   String AWARDDATE = rs.getString("AWARDDATE")==null?"":rs.getString("AWARDDATE");    // ��������
			   String MAN = rs.getString("MAN")==null?"":rs.getString("MAN");  // ��һ���������
			   String SN = rs.getString("SN")==null?"":rs.getString("SN");   // ��У������
			   String AWARDUNIT = rs.getString("AWARDUNIT")==null?"":rs.getString("AWARDUNIT");  // �ڽ���λ
			   String DEPTNAME = rs.getString("DEPTNAME")==null?"":rs.getString("DEPTNAME");  // Ժϵ����
			   String ATTACH = rs.getString("ATTACH")==null?"":rs.getString("ATTACH");  // ɨ���
			   String CGYWMC = rs.getString("CGYWMC")==null?"":rs.getString("CGYWMC");  // �ɹ�Ӣ������
			   String JLDJ = rs.getString("JLDJ")==null?"":rs.getString("JLDJ");    // �����ȼ�
			   String SFBX = rs.getString("SFBX")==null?"":rs.getString("SFBX");    // ��һ���Ƿ�У
			   String CHARGEID = rs.getString("CHARGEID")==null?"":rs.getString("CHARGEID");    // ��һ�����ID
			   int CHARGERATE = rs.getInt("CHARGERATE");    // �����˹�����/%
			   String KTLY = rs.getString("KTLY")==null?"":rs.getString("KTLY");           // ��Ŀ�����⣩��Դ
			   String XMPZH = rs.getString("XMPZH")==null?"":rs.getString("XMPZH");    // ��Ŀ��׼��
			   String CGLX = rs.getString("CGLX")==null?"":rs.getString("CGLX");           // �ɹ�����
			   String ZSBH = rs.getString("ZSBH")==null?"":rs.getString("ZSBH");    // ֤����
			   String GKLX = rs.getString("GKLX")==null?"":rs.getString("GKLX");           // �������
			   String XKFL = rs.getString("XKFL")==null?"":rs.getString("XKFL");    // ѧ�Ʒ���
			   String XKFL2 = rs.getString("XKFL2")==null?"":rs.getString("XKFL2");
			   String CGLX2 = rs.getString("CGLX2")==null?"":rs.getString("CGLX2");
				  
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
					   
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(HAVNAME)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(AWARDLEVEL)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(AWARDNAME)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(AWARDUNIT)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(JLDJ)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(KTLY)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CGLX+CGLX2)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(GKLX)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(XKFL+XKFL2)+b2+"</td>\n"); 
			   
			   sb.append("<td"+bg+" align='left' >"+b1+(joinMan)+b2+"</td>\n"); //������������
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(aprvStatus)+b2+"</td>\n");
			   if (STATUS == 0 && AUTOADD == 0) {
				   sb.append("<td"+bg+" align='left' style='word-break:break-all'><a href='#' onclick='viewDetail("+ID+");return false;'>����</a>&nbsp&nbsp");
				   sb.append("<a href='#' onclick='modifyData("+bindId+", "+ID+");return false;'  title='���л�'><img src='../aws_img/man.gif' border='0'>�޸�</a>&nbsp&nbsp");
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
			   String pageIndexStr = new PageIndex("BNU_HR_CGHJ", pageNow, lineCount, lineNumber).toString();
			   String pageIndexStr2 = pageIndexStr.replaceAll("frmMain", "frmCghj");
			   
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
			return "#���л�_���ݲ�ѯ�쳣";
		}finally{
			DBSql.close(conn, ps, rs);
		}
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_HR_CGHJ.html"),hashTags);
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
			

			String copyDataStr = DBSql.getString(conn, "SELECT COPYDATAID FROM BO_CGHJTEMP WHERE ID="+Integer.parseInt(dataId), "COPYDATAID");
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
					String deleteSql = "delete from BO_CGHJTEMP where ID ="+copyDataIdInt;
					DBSql.executeUpdate(deleteSql);
				}
			}
			
			
			String sql = null;
			sql = "delete from  BO_CGHJTEMP where CHARGEID='"+awsuid+"' and ID="+Integer.parseInt(dataId);
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


		
		
		String HAVNAME = null;          // �ɹ�����
		String AWARDLEVEL= null;     // �������� 
		String AWARDNAME = null;   // ��������
		String AWARDDATE = null;   // ��������
		String MAN = null; // ��һ���������
		String SN = null;   // ��У������
		String AWARDUNIT = null; // �ڽ���λ
		String DEPTNAME = null; // Ժϵ����
		String ATTACH = null; // ɨ���
		String CGYWMC = null;   // �ɹ�Ӣ������
		String JLDJ = null;   // �����ȼ�
		String SFBX = null;   // ��һ���Ƿ�У
		String CHARGEID = null;   // ��һ�����ID
		int CHARGERATE = 0;     // �����˹�����/%
		String KTLY = null;          // ��Ŀ�����⣩��Դ
		String XMPZH = null;   // ��Ŀ��׼��
		String CGLX = null;          // �ɹ�����
		String ZSBH = null;   // ֤����
		String GKLX = null;          // �������
		String XKFL = null;   // ѧ�Ʒ���
		String STATUS = null;          // ����״̬
		String AUTOADD = null;   // �Զ����
		String XKFL2 = null;
		String CGLX2 = null;
			
		
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
			String sql = "select * from BO_CGHJTEMP where ID="+Integer.parseInt(dataId);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				   HAVNAME = rs.getString("HAVNAME")==null?"":rs.getString("HAVNAME");           // �ɹ�����
				   AWARDLEVEL= rs.getString("AWARDLEVEL")==null?"":rs.getString("AWARDLEVEL");     // �������� 
				   AWARDNAME = rs.getString("AWARDNAME")==null?"":rs.getString("AWARDNAME");  // ��������
				   AWARDDATE = rs.getString("AWARDDATE")==null?"":rs.getString("AWARDDATE");    // ��������
				   MAN = rs.getString("MAN")==null?"":rs.getString("MAN");  // ��һ���������
				   SN = rs.getString("SN")==null?"":rs.getString("SN");   // ��У������
				   AWARDUNIT = rs.getString("AWARDUNIT")==null?"":rs.getString("AWARDUNIT");  // �ڽ���λ
				   DEPTNAME = rs.getString("DEPTNAME")==null?"":rs.getString("DEPTNAME");  // Ժϵ����
				   ATTACH = rs.getString("ATTACH")==null?"":rs.getString("ATTACH");  // ɨ���
				   CGYWMC = rs.getString("CGYWMC")==null?"":rs.getString("CGYWMC");  // �ɹ�Ӣ������
				   JLDJ = rs.getString("JLDJ")==null?"":rs.getString("JLDJ");    // �����ȼ�
				   SFBX = rs.getString("SFBX")==null?"":rs.getString("SFBX");    // ��һ���Ƿ�У
				   CHARGEID = rs.getString("CHARGEID")==null?"":rs.getString("CHARGEID");    // ��һ�����ID
				   CHARGERATE = rs.getInt("CHARGERATE");    // �����˹�����/%
				   KTLY = rs.getString("CHARGEID")==null?"":rs.getString("CHARGEID");           // ��Ŀ�����⣩��Դ
				   XMPZH = rs.getString("XMPZH")==null?"":rs.getString("XMPZH");    // ��Ŀ��׼��
				   CGLX = rs.getString("CGLX")==null?"":rs.getString("CGLX");           // �ɹ�����
				   ZSBH = rs.getString("ZSBH")==null?"":rs.getString("ZSBH");    // ֤����
				   GKLX = rs.getString("GKLX")==null?"":rs.getString("GKLX");           // �������
				   XKFL = rs.getString("XKFL")==null?"":rs.getString("XKFL");    // ѧ�Ʒ���
				   XKFL2 = rs.getString("XKFL2")==null?"":rs.getString("XKFL2");
				   CGLX2 = rs.getString("CGLX2")==null?"":rs.getString("CGLX2");
					
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
				   
				   if(AWARDDATE.length() >= 10) {
					   AWARDDATE = AWARDDATE.substring(0,10);
				   }
			}
			ps.close();
			rs.close();
		}catch(Exception e) {
			e.printStackTrace(System.err);
		}finally {
			DBSql.close(conn, ps, rs);
		}		
		   
		String HAVNAMEBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + HAVNAME + "'>";   
		String AWARDLEVERBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + AWARDLEVEL + "'>";
		String AWARDNAMEBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + AWARDNAME + "'>";
		String AWARDDATEBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + AWARDDATE + "'>";
		String MANBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + MAN + "'>";
		String SNBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + SN + "'>";
		String AWARDUNITBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + AWARDUNIT + "'>";
		String DEPTNAMEBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + DEPTNAME + "'>";
		String ATTACHBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + ATTACH + "'>";
		String CGYWMCBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CGYWMC + "'>";
		String JLDJBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + JLDJ + "'>";
		String SFBXBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + SFBX + "'>";
		String CHARGEIDBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CHARGEID + "'>";
		String CHARGERATEBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CHARGERATE + "'>";
		String KTLYBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + KTLY + "'>";
		String XMPZHBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + XMPZH + "'>";
		String CGLXBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CGLX + "'>";
		String ZSBHBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + ZSBH + "'>";
		String GKLXBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + GKLX + "'>";
		String XKFLBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + XKFL + "'>";
		String XKFL2Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + XKFL2 + "'>";
		String CGLX2Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CGLX2 + "'>";
		
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
		response.append("<tr><td><b><font size='3'>������ϸ��</font></b><br><br>");
		
		response.append("<table width=98% align=center border=0 cellspacing=0 cellpadding=0>");
		
		response.append("<tr>\n");
		response.append("<td width=20% align=left>�ɹ����ƣ�</td><td width=35%>").append(HAVNAMEBuffer).append("</td>\n");
		response.append("<td width=20% align=left>�ɹ�Ӣ������	��</td><td width=35%>").append(CGYWMCBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>�������ƣ�</td><td width=35%>").append(AWARDNAMEBuffer).append("</td>\n");
		response.append("<td width=20% align=left>�������ڡ���</td><td width=35%>").append(AWARDDATEBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>��������</td><td width=35%>").append(AWARDLEVERBuffer).append("</td>\n");
		response.append("<td width=20% align=left>�����ȼ�  ��</td><td width=35%>").append(JLDJBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>֤���ţ�</td><td width=35%>").append(ZSBHBuffer).append("</td>\n");
		response.append("<td width=20% align=left>�ڽ���λ��</td><td width=35%>").append(AWARDUNITBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>��һ���Ƿ�У��</td><td width=35%>").append(SFBXBuffer).append("</td>\n");
		response.append("<td width=20% align=left>��У��������</td><td width=35%>").append(SNBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>��һ�����������</td><td width=35%>").append(MANBuffer).append("ID:").append(CHARGEIDBuffer).append("</td>\n");
		response.append("<td width=20% align=left>�����˹�����/%��</td><td width=35%>").append(CHARGERATEBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>Ժϵ���ţ�</td><td width=35%>").append("����ѧ�� ").append(DEPTNAMEBuffer).append("</td>\n");
		response.append("<td width=20% align=left>ѧ�Ʒ��ࣺ</td><td width=35%>").append(XKFLBuffer).append(" ").append(XKFL2Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>��Ŀ����</td><td width=35%>").append(KTLYBuffer).append("</td>\n");
		response.append("<td width=20% align=left>�ɹ����ͣ�</td><td width=35%>").append(CGLXBuffer).append(" ").append(CGLX2Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>������ͣ�</td><td width=35%>").append(GKLXBuffer).append("</td>\n");
		response.append("<td width=20% align=left>��Ŀ��׼�ţ�</td><td width=35%>").append(XMPZHBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>ɨ���	��</td><td width=35% colspan='3'>").append(ATTACHBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>������1��</td><td width=35%>").append(NAME1Buffer).append("ID:").append(ID1Buffer).append("</td>\n");
		response.append("<td width=20% align=left>������/%��</td><td width=35%>").append(RATE1Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>������2��</td><td width=35%>").append(NAME2Buffer).append("ID:").append(ID2Buffer).append("</td>\n");
		response.append("<td width=20% align=left>������/%��</td><td width=35%>").append(RATE2Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>������3��</td><td width=35%>").append(NAME3Buffer).append("ID:").append(ID3Buffer).append("</td>\n");
		response.append("<td width=20% align=left>������/%��</td><td width=35%>").append(RATE3Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>������4��</td><td width=35%>").append(NAME4Buffer).append("ID:").append(ID4Buffer).append("</td>\n");
		response.append("<td width=20% align=left>������/%��</td><td width=35%>").append(RATE4Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>������5��</td><td width=35%>").append(NAME5Buffer).append("ID:").append(ID5Buffer).append("</td>\n");
		response.append("<td width=20% align=left>������/%��</td><td width=35%>").append(RATE5Buffer).append("</td>\n");
		response.append("</tr>\n");
		
		response.append("</table>\n");
		response.append("</td></tr></table>");
		
		return AjaxUtil.responseDialog("���л���Ϣ", 180, 70, 800, 500, response.toString(), "" ); //left,top,width,height
	}
	public String unlockData(String awsuid, String dataId) {
		BnuMainWeb web = new BnuMainWeb(super.getContext());
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			
			String sql = "select * from BO_CGHJTEMP where id="+Integer.parseInt(dataId);
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
			
			//����AWS����-----------------Ϊ���ݰ�����
			int processInstanceId = 0;
			processInstanceId = WorkflowInstanceAPI.getInstance().createProcessInstance("007b23151fca1ffb1573a7ec1c9ec4ef", loginId, "�ɹ�������");
			int[] processTaskInstanceIds = WorkflowTaskInstanceAPI.getInstance().createProcessTaskInstance(loginId, processInstanceId, SynType.synchronous, PriorityType.hight, 1, loginId, "�ɹ�������", false, 0);
			
			String updateSql = "update BO_CGHJTEMP set BINDID="+processInstanceId
					+" , STATUS=0" 
					+" , FINALMARK=NVL(FINALMARK,0)-"+finalMark
					+" WHERE ID="+Integer.parseInt(dataId);
			DBSql.executeUpdate(conn, updateSql);
			
			CalMark calMark = new CalMark();
//			calMark.reduceFinalMark(loginId, "CGHJ", finalMark);
			calMark.reduceFinalMarkWithYear(loginId, "CGHJ", finalMark, datayear);
			
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

