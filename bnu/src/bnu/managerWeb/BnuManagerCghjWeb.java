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

public class BnuManagerCghjWeb extends ActionsoftWeb {
	/**
	 * ���췽��
	 * @param userContext �û������Ķ���
	 */
	public BnuManagerCghjWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * ���췽��
	 */
	public BnuManagerCghjWeb() {
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
		String sqlw = "select * from BO_CGHJTEMP order by CREATEDATE desc";
		String sqlc = "select count(*) c from BO_CGHJTEMP ";

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
			   String AWARDUNIT = rs.getString("AWARDUNIT")==null?"":rs.getString("AWARDUNIT");  // �ڽ���λ
			   String JLDJ = rs.getString("JLDJ")==null?"":rs.getString("JLDJ");    // �����ȼ�
			   String KTLY = rs.getString("KTLY")==null?"":rs.getString("KTLY");           // ��Ŀ�����⣩��Դ
			   String CGLX = rs.getString("CGLX")==null?"":rs.getString("CGLX");           // �ɹ�����
			   String GKLX = rs.getString("GKLX")==null?"":rs.getString("GKLX");           // �������
			   String XKFL = rs.getString("XKFL")==null?"":rs.getString("XKFL");    // ѧ�Ʒ���
			   String XKFL2 = rs.getString("XKFL2")==null?"":rs.getString("XKFL2");
			   String CGLX2 = rs.getString("CGLX2")==null?"":rs.getString("CGLX2");
				  
			   String NAME1 = rs.getString("NAME1")==null?"":rs.getString("NAME1"); //
			   String NAME2 = rs.getString("NAME2")==null?"":rs.getString("NAME2"); //
			   String NAME3 = rs.getString("NAME3")==null?"":rs.getString("NAME3"); //
			   String NAME4 = rs.getString("NAME4")==null?"":rs.getString("NAME4"); //
			   String NAME5 = rs.getString("NAME5")==null?"":rs.getString("NAME5"); //
			   
			   int AUTOADD = rs.getInt("AUTOADD"); //
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
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>");
			   sb.append("<a href='#' onclick='modifyData("+bindId+", "+ID+");return false;'  title='���л�'><img src='../aws_img/man.gif' border='0'>�޸�</a>&nbsp&nbsp");
			   sb.append("<a href='#' onclick='deleteData("+ID+");return false;'>ɾ��</a></td>\n"); //����
			   sb.append("</tr>\n");
			   xh++;
		   }
		   ps.close();
		   rs.close();
		   
		   //ҳ�浼��
		   if (lineCount > lineNumber) {
			   sb.append("<tr bgcolor='#ffffff'><td colspan=17><div class='hide_for_jatools_print' id=AWS_REPORT_PAGEINDEX name=AWS_REPORT_PAGEINDEX ><br><br>")
			   .append(new PageIndex("BNU_MANAGER_CGHJ", pageNow, lineCount, lineNumber).toString())
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
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_MANAGER_CGHJ.html"),hashTags);
	}
}

