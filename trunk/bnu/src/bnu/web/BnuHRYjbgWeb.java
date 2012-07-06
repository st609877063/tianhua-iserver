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

public class BnuHRYjbgWeb extends ActionsoftWeb {
	/**
	 * ���췽��
	 * @param userContext �û������Ķ���
	 */
	public BnuHRYjbgWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * ���췽��
	 */
	public BnuHRYjbgWeb() {
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
//		String sqlw = "select * from BO_YJBGTEMP where  CHARGEID='"+awsuid+"' order by CREATEDATE desc";
//		String sqlc = "select count(*) c from BO_YJBGTEMP where  CHARGEID='"+awsuid+"'";
		String sqlw = " select * from (" +
				" select * from BO_YJBGTEMP where  CHARGEID='"+awsuid+"'  and (AUTOADD is null or AUTOADD=0) " +
				" union select * from BO_YJBGTEMP where  ID1='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_YJBGTEMP where  ID2='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_YJBGTEMP where  ID3='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_YJBGTEMP where  ID4='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_YJBGTEMP where  ID5='"+awsuid+"' and AUTOADD=1 " +
				") order by CREATEDATE desc";
		String sqlc = " select count(*) c from (" +
				" select * from BO_YJBGTEMP where  CHARGEID='"+awsuid+"'  and (AUTOADD is null or AUTOADD=0) " +
				" union select * from BO_YJBGTEMP where  ID1='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_YJBGTEMP where  ID2='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_YJBGTEMP where  ID3='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_YJBGTEMP where  ID4='"+awsuid+"' and AUTOADD=1 " +
				" union select * from BO_YJBGTEMP where  ID5='"+awsuid+"' and AUTOADD=1 )";
		
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String bg = "",bg1 = "",b1 = "", b2 = "";
		bg = " style=\"padding:5px;\"";  
		bg1 = "../aws_img/report-bg-blue2.gif";
		
		String CGMC = null;  // �ɹ�����
		String CGYWMC = null;  // �ɹ���������
		String CGLX = null;  // �ɹ�����
		String GKLX = null;  // �������
		String PJDW = null;  // ���۵�λ
		String CNDW = null;  // ���ɵ�λ
		String RDRQ = null;  // �϶�����
		String ZSBH = null;  // ֤����
		String SUBJECT = null;  // һ��ѧ��
		String SUBJECT2 = null;  // ����ѧ��
		String WCRSF = null;  // ��һ��������
		String SZXSJG = null;  // ����ѧ������
		String YXBM = null;  // Ժϵ����
		String ZZLX = null;  // ��������
		String FBFW = null;  // ����Χ
		String FBSJ = null;  // ��������
		String FBND = null;  // �������
		String YJLX = null;  // �о����
		String XMJB = null;  // ������Դ(��Ŀ����)
		String CGYYQK = null;  // �ɹ�Ӧ�����
		String SFCN = null;  // �Ƿ�˾�����ϲ���

		String CHARGENM = null; // ��һ��������
		String CHARGEID = null;   // ��һ����ID
		int CHARGERATE = 0;     // ��һ���߹�����/%
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
//			   SQL = "select top "+lineCount+" * from BO_YJBGTEMP " +
//			   		"where CHARGEID='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_YJBGTEMP  where CHARGEID='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }else{
		       SQL = "select * from (select rownum r,t1.* from ("+sqlw+") t1 where rownum<="+(start+lineNumber-1)+") t2 where t2.r>="+start; //oracle
			   //sql server
//			   SQL = "select top "+(start+lineNumber-1)+" * from BO_YJBGTEMP " +
//			   		"where CHARGEID='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_YJBGTEMP  where CHARGEID='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }
		   ps=conn.prepareStatement(SQL);
		   rs=ps.executeQuery();
		   while(rs.next()){
			   int ID = rs.getInt("ID");
			   int bindId = rs.getInt("BINDID");

			   CGMC = rs.getString("CGMC")==null ? "" :rs.getString("CGMC"); //�ɹ�����
			   CGYWMC = rs.getString("CGYWMC")==null ? "" :rs.getString("CGYWMC"); //�ɹ���������
			   CGLX = rs.getString("CGLX")==null ? "" :rs.getString("CGLX"); //�ɹ�����
			   GKLX = rs.getString("GKLX")==null ? "" :rs.getString("GKLX"); //�������
			   PJDW = rs.getString("PJDW")==null ? "" :rs.getString("PJDW"); //���۵�λ
			   CNDW = rs.getString("CNDW")==null ? "" :rs.getString("CNDW"); //���ɵ�λ
			   RDRQ = rs.getString("RDRQ")==null ? "" :rs.getString("RDRQ"); //�϶�����
			   ZSBH = rs.getString("ZSBH")==null ? "" :rs.getString("ZSBH"); //֤����
			   SUBJECT = rs.getString("SUBJECT")==null ? "" :rs.getString("SUBJECT"); //һ��ѧ��
			   SUBJECT2 = rs.getString("SUBJECT2")==null ? "" :rs.getString("SUBJECT2"); //����ѧ��
			   WCRSF = rs.getString("WCRSF")==null ? "" :rs.getString("WCRSF"); //��һ��������
			   SZXSJG = rs.getString("SZXSJG")==null ? "" :rs.getString("SZXSJG"); //����ѧ������
			   YXBM = rs.getString("YXBM")==null ? "" :rs.getString("YXBM"); //Ժϵ����
			   ZZLX = rs.getString("ZZLX")==null ? "" :rs.getString("ZZLX"); //��������
			   FBFW = rs.getString("FBFW")==null ? "" :rs.getString("FBFW"); //����Χ
			   FBSJ = rs.getString("FBSJ")==null ? "" :rs.getString("FBSJ"); //��������
			   FBND = rs.getString("FBND")==null ? "" :rs.getString("FBND"); //�������
			   YJLX = rs.getString("YJLX")==null ? "" :rs.getString("YJLX"); //�о����
			   XMJB = rs.getString("XMJB")==null ? "" :rs.getString("XMJB"); //������Դ(��Ŀ����)
			   CGYYQK = rs.getString("CGYYQK")==null ? "" :rs.getString("CGYYQK"); //�ɹ�Ӧ�����
			   SFCN = rs.getString("SFCN")==null ? "" :rs.getString("SFCN"); //�Ƿ�˾�����ϲ���

			   CHARGENM = rs.getString("CHARGENM")==null?"":rs.getString("CHARGENM"); //��һ��������
			   CHARGEID = rs.getString("CHARGEID")==null?"":rs.getString("CHARGEID"); //��һ����ID
			   CHARGERATE = rs.getInt("CHARGERATE"); //��һ���߹�����/%
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
			   
			   if(FBSJ!=null && FBSJ.length()>=10) {
					FBSJ = FBSJ.substring(0,10);
				}
				if(RDRQ!=null && RDRQ.length()>=10) {
					RDRQ = RDRQ.substring(0,10);
				}
			   
			   int AUTOADD = 0; 
			   AUTOADD = rs.getInt("AUTOADD"); //
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
//			   sb.append("<td"+bg+" align='left' ><input type='checkbox' name='checkData' id='"+ID+"'/></td>\n");//checkbox
//			   sb.append("<td"+bg+" align='left'  display='hidden'>"+ID+"</td>\n");//ID
			   sb.append("<td"+bg+" align='left' >"+xh+"</td>\n");//���
			   
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+(CGMC)+b2+"</td>\n"); //�ɹ�����
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(CGLX)+b2+"</td>\n"); //�ɹ�����
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(PJDW)+b2+"</td>\n"); //���۵�λ
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(WCRSF)+b2+"</td>\n"); //��һ�������
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(SZXSJG)+b2+"</td>\n"); //����ѧ������
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(YXBM)+b2+"</td>\n"); //Ժϵ����
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(CHARGENM)+b2+"</td>\n"); //��һ��������
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(ZZLX)+b2+"</td>\n"); //��������
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(YJLX)+b2+"</td>\n"); //�о����
			   sb.append("<td"+bg+" align='left'  style='word-break:break-all'>"+b1+(XMJB)+b2+"</td>\n"); //������Դ(��Ŀ����)
			   sb.append("<td"+bg+" align='left' >"+b1+(joinMan)+b2+"</td>\n"); //������������
			   
			   sb.append("<td"+bg+" align='left' >"+b1+(aprvStatus)+b2+"</td>\n");
			   
			   if ((STATUS == 0 && AUTOADD == 0) || !WCRSF.equals("��У��ʦ")) {
				   sb.append("<td"+bg+" align='left' ><a href='#' onclick='viewDetail("+ID+");return false;'>����</a>&nbsp&nbsp");
				   sb.append("<a href='#' onclick='modifyData("+bindId+", "+ID+");return false;'  title='�о�����'><img src='../aws_img/man.gif' border='0'>�޸�</a>&nbsp&nbsp");
//				   sb.append("<a href='#' onclick='deleteData("+ID+");return false;'>ɾ��</a></td>\n"); //����
				   sb.append("</td>\n"); //����
			   } else if (STATUS == 1 || AUTOADD == 1) {   
				   sb.append("<td"+bg+" align='left' ><a href='#' onclick='viewDetail("+ID+");return false;'>����</a></td>");
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
			   String pageIndexStr = new PageIndex("BNU_HR_YJBG", pageNow, lineCount, lineNumber).toString();
			   String pageIndexStr2 = pageIndexStr.replaceAll("frmMain", "frmYjbg");
			   
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
			return "#�о�����_���ݲ�ѯ�쳣";
		}finally{
			DBSql.close(conn, ps, rs);
		}
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_HR_YJBG.html"),hashTags);
	}
	
	public String viewDetail(String awsuid, String dataId) {
		StringBuffer response = new StringBuffer();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String CGMC = null;  // �ɹ�����
		String CGYWMC = null;  // �ɹ���������
		String CGLX = null;  // �ɹ�����
		String GKLX = null;  // �������
		String PJDW = null;  // ���۵�λ
		String CNDW = null;  // ���ɵ�λ
		String RDRQ = null;  // �϶�����
		String ZSBH = null;  // ֤����
		String SUBJECT = null;  // һ��ѧ��
		String SUBJECT2 = null;  // ����ѧ��
		String WCRSF = null;  // ��һ��������
		String SZXSJG = null;  // ����ѧ������
		String YXBM = null;  // Ժϵ����
		String ZZLX = null;  // ��������
		String FBFW = null;  // ����Χ
		String FBSJ = null;  // ��������
		String FBND = null;  // �������
		String YJLX = null;  // �о����
		String XMJB = null;  // ������Դ(��Ŀ����)
		String CGYYQK = null;  // �ɹ�Ӧ�����
		String SFCN = null;  // �Ƿ�˾�����ϲ���
		
		String CHARGENM = null; // ��һ��������
		String CHARGEID = null;   // ��һ����ID
		int CHARGERATE = 0;     // ��һ���߹�����/%
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
			String sql = "select * from BO_YJBGTEMP where ID="+Integer.parseInt(dataId);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {

				CGMC = rs.getString("CGMC")==null ? "" :rs.getString("CGMC"); //�ɹ�����
				CGYWMC = rs.getString("CGYWMC")==null ? "" :rs.getString("CGYWMC"); //�ɹ���������
				CGLX = rs.getString("CGLX")==null ? "" :rs.getString("CGLX"); //�ɹ�����
				GKLX = rs.getString("GKLX")==null ? "" :rs.getString("GKLX"); //�������
				PJDW = rs.getString("PJDW")==null ? "" :rs.getString("PJDW"); //���۵�λ
				CNDW = rs.getString("CNDW")==null ? "" :rs.getString("CNDW"); //���ɵ�λ
				RDRQ = rs.getString("RDRQ")==null ? "" :rs.getString("RDRQ"); //�϶�����
				ZSBH = rs.getString("ZSBH")==null ? "" :rs.getString("ZSBH"); //֤����
				SUBJECT = rs.getString("SUBJECT")==null ? "" :rs.getString("SUBJECT"); //һ��ѧ��
				SUBJECT2 = rs.getString("SUBJECT2")==null ? "" :rs.getString("SUBJECT2"); //����ѧ��
				WCRSF = rs.getString("WCRSF")==null ? "" :rs.getString("WCRSF"); //��һ��������
				SZXSJG = rs.getString("SZXSJG")==null ? "" :rs.getString("SZXSJG"); //����ѧ������
				YXBM = rs.getString("YXBM")==null ? "" :rs.getString("YXBM"); //Ժϵ����
				ZZLX = rs.getString("ZZLX")==null ? "" :rs.getString("ZZLX"); //��������
				FBFW = rs.getString("FBFW")==null ? "" :rs.getString("FBFW"); //����Χ
				FBSJ = rs.getString("FBSJ")==null ? "" :rs.getString("FBSJ"); //��������
				FBND = rs.getString("FBND")==null ? "" :rs.getString("FBND"); //�������
				YJLX = rs.getString("YJLX")==null ? "" :rs.getString("YJLX"); //�о����
				XMJB = rs.getString("XMJB")==null ? "" :rs.getString("XMJB"); //������Դ(��Ŀ����)
				CGYYQK = rs.getString("CGYYQK")==null ? "" :rs.getString("CGYYQK"); //�ɹ�Ӧ�����
				SFCN = rs.getString("SFCN")==null ? "" :rs.getString("SFCN"); //�Ƿ�˾�����ϲ���
				
				if(FBSJ!=null && FBSJ.length()>=10) {
					FBSJ = FBSJ.substring(0,10);
				}
				if(RDRQ!=null && RDRQ.length()>=10) {
					RDRQ = RDRQ.substring(0,10);
				}
				
				CHARGENM = rs.getString("CHARGENM")==null?"":rs.getString("CHARGENM"); //��һ��������
				CHARGEID = rs.getString("CHARGEID")==null?"":rs.getString("CHARGEID"); //��һ����ID
				CHARGERATE = rs.getInt("CHARGERATE"); //��һ���߹�����/%
				
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
				   
			}
			ps.close();
			rs.close();
		}catch(Exception e) {
			e.printStackTrace(System.err);
		}finally {
			DBSql.close(conn, ps, rs);
		}
		
		
		String	CGMCBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + CGMC + "'>";
		String	CGYWMCBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=15 value='" + CGYWMC + "'>";
		String	CGLXBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=16 value='" + CGLX + "'>";
		String	GKLXBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=17 value='" + GKLX + "'>";
		String	PJDWBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=18 value='" + PJDW + "'>";
		String	CNDWBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=19 value='" + CNDW + "'>";
		String	RDRQBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=20 value='" + RDRQ + "'>";
		String	ZSBHBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=21 value='" + ZSBH + "'>";
		String	SUBJECTBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=22 value='" + SUBJECT + "'>";
		String	SUBJECT2Buffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=23 value='" + SUBJECT2 + "'>";
		String	WCRSFBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=24 value='" + WCRSF + "'>";
		String	SZXSJGBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=25 value='" + SZXSJG + "'>";
		String	YXBMBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=26 value='" + YXBM + "'>";
		String	ZZLXBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=27 value='" + ZZLX + "'>";
		String	FBFWBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=28 value='" + FBFW + "'>";
		String	FBSJBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=29 value='" + FBSJ + "'>";
		String	FBNDBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + FBND + "'>";
		String	YJLXBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=31 value='" + YJLX + "'>";
		String	XMJBBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=32 value='" + XMJB + "'>";
		String	CGYYQKBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=33 value='" + CGYYQK + "'>";
		String	SFCNBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=34 value='" + SFCN + "'>";
		
		String CHARGENMBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + CHARGENM + "'>";
		String CHARGEIDBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=14 value='" + CHARGEID + "'>";
		String CHARGERATEBuffer = "<input type='text' name='' class ='actionsoftInput'  maxlength=30 size=30 value='" + CHARGERATE +"%" + "'>";
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
		response.append("<td width=20% align=left>�ɹ�����: </td><td width=30%>").append(CGMCBuffer).append("</td>\n");
		response.append("<td width=20% align=left>�ɹ���������: </td><td width=30%>").append(CGYWMCBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>�ɹ�����: </td><td width=30%>").append(CGLXBuffer).append("</td>\n");
		response.append("<td width=20% align=left>�������: </td><td width=30%>").append(GKLXBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>���۵�λ: </td><td width=30%>").append(PJDWBuffer).append("</td>\n");
		response.append("<td width=20% align=left>���ɵ�λ: </td><td width=30%>").append(CNDWBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>�϶�����: </td><td width=30%>").append(RDRQBuffer).append("</td>\n");
		response.append("<td width=20% align=left>֤����: </td><td width=30%>").append(ZSBHBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>һ��ѧ��: </td><td width=30%>").append(SUBJECTBuffer).append(" ").append(SUBJECT2Buffer).append("</td>\n");
		response.append("<td width=20% align=left>��һ��������: </td><td width=30%>").append(WCRSFBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>��һ����������</td><td width=30%>").append(CHARGENMBuffer).append("ID:").append(CHARGEIDBuffer).append("</td>\n");
		response.append("<td width=20% align=left>������/%��</td><td width=30%>").append(CHARGERATEBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>����ѧ������: </td><td width=30%>").append(SZXSJGBuffer).append("</td>\n");
		response.append("<td width=20% align=left>Ժϵ����: </td><td width=30%>").append(YXBMBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>��������: </td><td width=30%>").append(ZZLXBuffer).append("</td>\n");
		response.append("<td width=20% align=left>����Χ: </td><td width=30%>").append(FBFWBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>��������: </td><td width=30%>").append(FBSJBuffer).append("</td>\n");
		response.append("<td width=20% align=left>�������: </td><td width=30%>").append(FBNDBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>�о����: </td><td width=30%>").append(YJLXBuffer).append("</td>\n");
		response.append("<td width=20% align=left>������Դ(��Ŀ����): </td><td width=30%>").append(XMJBBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>�ɹ�Ӧ�����: </td><td width=30%>").append(CGYYQKBuffer).append("</td>\n");
		response.append("<td width=20% align=left>�Ƿ�˾�����ϲ���: </td><td width=30%>").append(SFCNBuffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>������1��</td><td width=30%>").append(NAME1Buffer).append("ID:").append(ID1Buffer).append("</td>\n");
		response.append("<td width=20% align=left>������/%��</td><td width=30%>").append(RATE1Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>������2��</td><td width=30%>").append(NAME2Buffer).append("ID:").append(ID2Buffer).append("</td>\n");
		response.append("<td width=20% align=left>������/%��</td><td width=30%>").append(RATE2Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>������3��</td><td width=30%>").append(NAME3Buffer).append("ID:").append(ID3Buffer).append("</td>\n");
		response.append("<td width=20% align=left>������/%��</td><td width=30%>").append(RATE3Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>������4��</td><td width=30%>").append(NAME4Buffer).append("ID:").append(ID4Buffer).append("</td>\n");
		response.append("<td width=20% align=left>������/%��</td><td width=30%>").append(RATE4Buffer).append("</td>\n");
		response.append("</tr>\n");
		response.append("<tr>\n");
		response.append("<td width=20% align=left>������5��</td><td width=30%>").append(NAME5Buffer).append("ID:").append(ID5Buffer).append("</td>\n");
		response.append("<td width=20% align=left>������/%��</td><td width=30%>").append(RATE5Buffer).append("</td>\n");
		response.append("</tr>\n");
		
		response.append("</td></tr></table>");
		
		return AjaxUtil.responseDialog("�о�������Ϣ", 180, 70, 800, 500, response.toString(), "" ); //left,top,width,height
	}

	public String deleteData(String awsuid,String dataId) {
		BnuMainWeb web = new BnuMainWeb(super.getContext());
		Connection conn = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(false);
			
			String copyDataStr = DBSql.getString(conn, "SELECT COPYDATAID FROM BO_YJBGTEMP WHERE ID="+Integer.parseInt(dataId), "COPYDATAID");
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
					String deleteSql = "delete from BO_YJBGTEMP where ID ="+copyDataIdInt;
					DBSql.executeUpdate(deleteSql);
				}
			}
			
			String sql = null;
			sql = "delete from  BO_YJBGTEMP where CHARGEID='"+awsuid+"' and ID="+Integer.parseInt(dataId);
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
	
	public String unlockData(String awsuid,String dataId) {
		BnuMainWeb web = new BnuMainWeb(super.getContext());
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			
			String sql = "select * from BO_YJBGTEMP where id="+Integer.parseInt(dataId);
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
			processInstanceId = WorkflowInstanceAPI.getInstance().createProcessInstance("007b23151fd6ea094d1dade015af3e4f", loginId, "�о���������");
			int[] processTaskInstanceIds = WorkflowTaskInstanceAPI.getInstance().createProcessTaskInstance(loginId, processInstanceId, SynType.synchronous, PriorityType.hight, 1, loginId, "�о���������", false, 0);
			
			String updateSql = "update BO_YJBGTEMP set BINDID="+processInstanceId+", STATUS=0 WHERE ID="+Integer.parseInt(dataId);
			DBSql.executeUpdate(conn, updateSql);
			
			CalMark calMark = new CalMark();
//			calMark.reduceFinalMark(loginId, "YJBG", finalMark);
			calMark.reduceFinalMarkWithYear(loginId, "YJBG", finalMark, datayear);
			
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

