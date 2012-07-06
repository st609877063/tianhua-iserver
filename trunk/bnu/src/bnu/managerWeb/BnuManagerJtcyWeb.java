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

public class BnuManagerJtcyWeb extends ActionsoftWeb {
	/**
	 * ���췽��
	 * @param userContext �û������Ķ���
	 */
	public BnuManagerJtcyWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * ���췽��
	 */
	public BnuManagerJtcyWeb() {
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
		String sqlw = "select * from BO_JTCYTEMP order by CREATEDATE desc";
		String sqlc = "select count(*) c from BO_JTCYTEMP ";
		
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
//			   SQL = "select top "+lineCount+" * from BO_JTCYTEMP " +
//			   		"where CREATEUSER='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_JTCYTEMP  where CREATEUSER='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }else{
		       SQL = "select * from (select rownum r,t1.* from ("+sqlw+") t1 where rownum<="+(start+lineNumber-1)+") t2 where t2.r>="+start; //oracle
			   //sql server
//			   SQL = "select top "+(start+lineNumber-1)+" * from BO_JTCYTEMP " +
//			   		"where CREATEUSER='"+awsuid+"' and id not in " +
//			   		"(select top "+(start-1)+" id from BO_JTCYTEMP  where CREATEUSER='"+awsuid+"' )" +
//			   		"order by CREATEDATE desc";
		   }
		   ps=conn.prepareStatement(SQL);
		   rs=ps.executeQuery();
		   while(rs.next()){
			   int ID = rs.getInt("ID");
			   int bindId = rs.getInt("BINDID");
			   
			   String CYXM = rs.getString("CYXM")==null?"":rs.getString("CYXM");          // ��Ա����
			   String YBRGX= rs.getString("YBRGX")==null?"":rs.getString("YBRGX");     // �뱾�˹�ϵ 
			   String GZDW = rs.getString("GZDW")==null?"":rs.getString("GZDW");   // ����ѧϰ��λ
			   String ZW = rs.getString("ZW")==null?"":rs.getString("ZW"); // ְ��
			   String CSRQ = rs.getString("CSRQ")==null?"":rs.getString("CSRQ");   // ��������
			   String ZWJB = rs.getString("ZWJB")==null?"":rs.getString("ZWJB"); // ְ�񼶱� 
			   String WHCD = rs.getString("WHCD")==null?"":rs.getString("WHCD"); // �Ļ��̶�
			   String XB = rs.getString("XB")==null?"":rs.getString("XB");   // �Ա�
			   String ZZMM = rs.getString("ZZMM")==null?"":rs.getString("ZZMM"); // ������ò 
			   String LXDH = rs.getString("LXDH")==null?"":rs.getString("LXDH"); // ��ϵ�绰
			   
			   if(CSRQ!=null && CSRQ.length()>10) {
				   CSRQ = CSRQ.substring(0, 10);
			   }
			   
			   int STATUS = 0;
			   STATUS = rs.getInt("STATUS");
			   String aprvStatus = null;
			   if (STATUS == 0) {
				   aprvStatus = "δ����";
			   } else if (STATUS == 1) {
				   aprvStatus = "����ͨ��";
			   }  else if (STATUS == 9) {
				   aprvStatus = "��������(����)";
			   }
			   
			   //���taskId
//			   int taskId = 0;
//			   String taskSql = "select ID FROM WF_TASK WHERE BIND_ID = "+bindId;
//			   taskId = DBSql.getInt(taskSql, "ID");
			   
			   sb.append("<tr bgcolor='#ffffff'>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+xh+"</td>\n");//���
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+(CYXM)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(YBRGX)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(GZDW)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(ZW)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(CSRQ)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(ZWJB)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(WHCD)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(XB)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(ZZMM)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(LXDH)+b2+"</td>\n"); 
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>"+b1+(aprvStatus)+b2+"</td>\n");
			   sb.append("<td"+bg+" align='left' style='word-break:break-all'>");
			   sb.append("<a href='#' onclick='modifyData("+bindId+", "+ID+");return false;'  title='������Ŀ'><img src='../aws_img/man.gif' border='0'>�޸�</a>&nbsp&nbsp");
			   sb.append("<a href='#' onclick='deleteData("+ID+");return false;'>ɾ��</a></td>\n"); //����
			   sb.append("</tr>\n");
			   xh++;
		   }
		   ps.close();
		   rs.close();
		   
		   //ҳ�浼��
		   if (lineCount > lineNumber) {
			   sb.append("<tr bgcolor='#ffffff'><td colspan=17><div class='hide_for_jatools_print' id=AWS_REPORT_PAGEINDEX name=AWS_REPORT_PAGEINDEX ><br><br>")
			   .append(new PageIndex("BNU_MANAGER_KYXM", pageNow, lineCount, lineNumber).toString())
			   .append("</div></td></tr>");
		   }
		   hashTags.put("sid",super.getSIDFlag());
		   hashTags.put("awsuid",awsuid);
		   hashTags.put("pageNow",Integer.toString(pageNow));
		   hashTags.put("list",sb.toString());
		}catch(Exception e){
			e.printStackTrace(System.err);
			return "#��ͥ��Ա_���ݲ�ѯ�쳣";
		}finally{
			DBSql.close(conn, ps, rs);
			DBSql.close(conn2, ps2, rs2);
		}
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_MANAGER_JTCY.html"),hashTags);
	}
	
}

