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

public class BnuManagerYjbgWeb extends ActionsoftWeb {
	/**
	 * ���췽��
	 * @param userContext �û������Ķ���
	 */
	public BnuManagerYjbgWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * ���췽��
	 */
	public BnuManagerYjbgWeb() {
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
		String sqlw = "select * from BO_YJBGTEMP order by CREATEDATE desc";
		String sqlc = "select count(*) c from BO_YJBGTEMP ";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String bg = "",bg1 = "",b1 = "", b2 = "";
		bg = " style=\"padding:5px;\"";  
		bg1 = "../aws_img/report-bg-blue2.gif";
		
		String CGMC = null;  // �ɹ�����
		String CGLX = null;  // �ɹ�����
		String PJDW = null;  // ���۵�λ
		String RDRQ = null;  // �϶�����
		String WCRSF = null;  // ��һ��������
		String SZXSJG = null;  // ����ѧ������
		String YXBM = null;  // Ժϵ����
		String ZZLX = null;  // ��������
		String FBSJ = null;  // ��������
		String YJLX = null;  // �о����
		String XMJB = null;  // ������Դ(��Ŀ����)
		String CHARGENM = null; // ��һ��������
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
			   CGLX = rs.getString("CGLX")==null ? "" :rs.getString("CGLX"); //�ɹ�����
			   PJDW = rs.getString("PJDW")==null ? "" :rs.getString("PJDW"); //���۵�λ
			   RDRQ = rs.getString("RDRQ")==null ? "" :rs.getString("RDRQ"); //�϶�����
			   WCRSF = rs.getString("WCRSF")==null ? "" :rs.getString("WCRSF"); //��һ��������
			   SZXSJG = rs.getString("SZXSJG")==null ? "" :rs.getString("SZXSJG"); //����ѧ������
			   YXBM = rs.getString("YXBM")==null ? "" :rs.getString("YXBM"); //Ժϵ����
			   ZZLX = rs.getString("ZZLX")==null ? "" :rs.getString("ZZLX"); //��������
			   FBSJ = rs.getString("FBSJ")==null ? "" :rs.getString("FBSJ"); //��������
			   YJLX = rs.getString("YJLX")==null ? "" :rs.getString("YJLX"); //�о����
			   XMJB = rs.getString("XMJB")==null ? "" :rs.getString("XMJB"); //������Դ(��Ŀ����)
			   CHARGENM = rs.getString("CHARGENM")==null?"":rs.getString("CHARGENM"); //��һ��������
			   NAME1 = rs.getString("NAME1")==null?"":rs.getString("NAME1"); //
			   NAME2 = rs.getString("NAME2")==null?"":rs.getString("NAME2"); //
			   NAME3 = rs.getString("NAME3")==null?"":rs.getString("NAME3"); //
			   NAME4 = rs.getString("NAME4")==null?"":rs.getString("NAME4"); //
			   NAME5 = rs.getString("NAME5")==null?"":rs.getString("NAME5"); //
			   
			   if(FBSJ!=null && FBSJ.length()>=10) {
					FBSJ = FBSJ.substring(0,10);
				}
				if(RDRQ!=null && RDRQ.length()>=10) {
					RDRQ = RDRQ.substring(0,10);
				}
			   
			   int AUTOADD = 0; 
			   AUTOADD = rs.getInt("AUTOADD"); //
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
			   sb.append("<td"+bg+" align='left' >");
			   sb.append("<a href='#' onclick='modifyData("+bindId+", "+ID+");return false;'  title='�о�����'><img src='../aws_img/man.gif' border='0'>�޸�</a>&nbsp&nbsp");
			   sb.append("<a href='#' onclick='deleteData("+ID+");return false;'>ɾ��</a></td>\n"); //����
			   sb.append("</tr>\n");
			   xh++;
		   }
		   ps.close();
		   rs.close();
		   //ҳ�浼��
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
			return "#�о�����_���ݲ�ѯ�쳣";
		}finally{
			DBSql.close(conn, ps, rs);
		}
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_MANAGER_YJBG.html"),hashTags);
	}
	
}

