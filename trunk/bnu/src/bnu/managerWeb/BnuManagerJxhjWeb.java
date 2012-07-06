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

public class BnuManagerJxhjWeb extends ActionsoftWeb {
	/**
	 * ���췽��
	 * @param userContext �û������Ķ���
	 */
	public BnuManagerJxhjWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * ���췽��
	 */
	public BnuManagerJxhjWeb() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * ��ѧ��--��ѯ���
	 * @param pageNow    ҳ��
	 * @param lineNumber ÿҳ����
	 * @param awsuid     AWS�ʺ�
	 * @return
	 */
	public String getReport(int pageNow,int lineNumber,String awsuid) {
		//��Ŀ���û����룬�蹲������
		StringBuffer sb = new StringBuffer();
		Hashtable hashTags=new Hashtable();
		String sqlw = "select * from BO_JXHJTEMP  order by CREATEDATE desc";
		String sqlc = "select count(*) c from BO_JXHJTEMP ";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String bg = "",bg1 = "",b1 = "", b2 = "";
		bg = " style=\"padding:5px;\"";  
		bg1 = "../aws_img/report-bg-blue2.gif";
		
		String  CGMC = null; //�ɹ�����
		String  JLLX = null; //��������
		String  HJDJ = null; //�񽱵ȼ�
		String  FZR = null; //��һ�����
		String  SZDW = null; //��һ��������ڵ�λ
		String  HJRQ = null; //��ʱ��
		String  PZWH = null; //��׼�ĺ�
		String  JLJF = null; //��������(��)
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
			   
			   CGMC = rs.getString("CGMC")==null?"":rs.getString("CGMC"); //�ɹ�����
			   JLLX = rs.getString("JLLX")==null?"":rs.getString("JLLX"); //��������
			   HJDJ = rs.getString("HJDJ")==null?"":rs.getString("HJDJ"); //�񽱵ȼ�
			   HJRQ = rs.getString("HJRQ")==null?"":rs.getString("HJRQ"); //��ʱ��
			   FZR = rs.getString("FZR")==null?"":rs.getString("FZR"); //��һ�����
			   SZDW = rs.getString("SZDW")==null?"":rs.getString("SZDW"); //��һ��������ڵ�λ
			   PZWH = rs.getString("PZWH")==null?"":rs.getString("PZWH"); //��׼�ĺ�
			   JLJF = rs.getString("JLJF")==null?"":rs.getString("JLJF"); //��������(��)
			   NAME1 = rs.getString("NAME1")==null?"":rs.getString("NAME1"); //
			   NAME2 = rs.getString("NAME2")==null?"":rs.getString("NAME2"); //
			   NAME3 = rs.getString("NAME3")==null?"":rs.getString("NAME3"); //
			   NAME4 = rs.getString("NAME4")==null?"":rs.getString("NAME4"); //
			   NAME5 = rs.getString("NAME5")==null?"":rs.getString("NAME5"); //
			   
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
			   
			   //���taskId
//			   int taskId = 0;
//			   String taskSql = "select ID FROM WF_TASK WHERE BIND_ID = "+bindId;
//			   taskId = DBSql.getInt(taskSql, "ID");
			   
			   sb.append("<tr bgcolor='#ffffff'>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+xh+"</td>\n");//���
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+(CGMC)+b2+"</td>\n"); //�ɹ�����
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(JLLX)+b2+"</td>\n"); //��������
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(HJDJ)+b2+"</td>\n"); //�񽱵ȼ�
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(HJRQ)+b2+"</td>\n"); //��ʱ��
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(FZR)+b2+"</td>\n"); //��һ�����
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(SZDW)+b2+"</td>\n"); //��һ��������ڵ�λ
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(PZWH)+"%"+b2+"</td>\n"); //��׼�ĺ�
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(JLJF)+b2+"</td>\n");  //��������(��)
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(joinMan)+b2+"</td>\n"); //������������
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(aprvStatus)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>");
			   sb.append("<a href='#' onclick='modifyData("+bindId+", "+ID+");return false;'  title='��ѧ��'><img src='../aws_img/man.gif' border='0'>�޸�</a>&nbsp&nbsp");
			   sb.append("<a href='#' onclick='deleteData("+ID+");return false;'>ɾ��</a></td>\n"); //����
			   sb.append("</tr>\n");
			   xh++;
		   }
		   ps.close();
		   rs.close();
		   //ҳ�浼��
		   if (lineCount > lineNumber) {
			   sb.append("<tr bgcolor='#ffffff'><td colspan=17><div class='hide_for_jatools_print' id=AWS_REPORT_PAGEINDEX name=AWS_REPORT_PAGEINDEX ><br><br>")
			   .append(new PageIndex("BNU_MANAGER_JXHJ", pageNow, lineCount, lineNumber).toString())
			   .append("</div></td></tr>");
		   }
		   hashTags.put("sid",super.getSIDFlag());
		   hashTags.put("awsuid",awsuid);
		   hashTags.put("pageNow",Integer.toString(pageNow));
		   hashTags.put("list",sb.toString());
		}catch(Exception e){
			e.printStackTrace(System.err);
			return "#��ѧ��_���ݲ�ѯ�쳣";
		}finally{
			DBSql.close(conn, ps, rs);
		}
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_MANAGER_JXHJ.html"),hashTags);
	}
	
}

