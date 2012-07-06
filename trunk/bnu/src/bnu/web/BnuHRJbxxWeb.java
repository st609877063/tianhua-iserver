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

/**
 * Using
*  ����:������Ϣ
 * @author 
 * @version V1.0
*/
public class BnuHRJbxxWeb extends ActionsoftWeb {
	/**
	 * ���췽��
	 * @param userContext �û������Ķ���
	 */
	public BnuHRJbxxWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * ���췽��
	 */
	public BnuHRJbxxWeb() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getReport(String awsuid) {
		StringBuffer sb = new StringBuffer();
		Hashtable hashTags=new Hashtable();
		
		int id = 0;
		int bindId = 0;
		
		String ZGH = "";          // �̹���
		String XM= "";     // ���� 
		String XB = "";   // �Ա�
		String CSNY = ""; // ��������
		String MZ = "";   // ����
		String JG = ""; // ���� 
		String JGSHI = ""; // ���ᣨ�У�
		String JGOTHER = ""; // ���ᣨ�У�
		String CJGZSJ = ""; // �μӹ���ʱ��
		String RXGZSJ = ""; // ��Уʱ��
		String BMMC = "";   // ���ڵ�λ
		String JSZW = "";   // ��ְ��
		String JSZW2 = "";   // ��ְ��2
		String JSZWRMSJ = "";   // ��ְʱ��
		String GWLB = ""; // ��λ���
		String GWLB2 = "";   // ��λ���2
		String SSXK = "";   // ѧ�Ʒ���
		String SSXK2 = "";   // ѧ�Ʒ���2
		String PGJB = "";     // Ƹλ�ȼ�
		String DSLX = "";          // ��ʦ����
		String YHZZC = "";   // ѧ��ר��
		String GJ = "";          // ����
		String GJ2 = ""; // ����2
		String ZGQK= "";     // �ڸ����
		String ZGQK2 = "";   // �ڸ�λ��2
		String ZJLX = "";   // ֤������
		String ZJLX2 = "";     // ֤������2
		String SFZHM = ""; // ֤������
		String HKSZDCS = "";   // �������ڵ�
		String HYZK = ""; // ����״��
		String XZZ = ""; // ��ͥסַ
		String SJ = ""; // �ƶ��绰
		String BGSDH = "";   // �칫�绰
		String EMAIL = "";   // ��������
		String ZGXW = "";   // ����ѧλ
		String ZGXW2 = "";          // ���ѧλ2
		String ZGXW3 = "";          // ���ѧλ3
		String ZGXWHDSJ = "";   // ��ҵʱ��
		String WYSP = ""; // ����ˮƽ
		String WYYZ = "";   // ��������
		String WYYZ2 = "";   // ��������2
		String ZGXL = "";     // ���ѧ��
		int LXGL = 0;          // �������䣨�꣩
		String ZZMM = "";   // ������ò
		String RDSJ = "";          // �뵳ʱ��
		String DZZ= "";     // ����֧/�ֵ�ί/.. 
		String DZZ2 = "";   // ��һ������֯
		String DZB = "";   // ��֧��
		int DFJS = 0; // ���ѻ���
		String DNZW = "";   // ����ְ��
		String XZZW = ""; // ְ������ 
		String XZZWJB = ""; // ְ�񼶱�
		String YJLY = "";
		String ZYJZ = ""; 
		String BGDD = "";
		String BGSJ = ""; 
		String CZ = "";
		String TXDZ = ""; 
		String ZYWZ = "";
		String BYXX = "";
		String XXLB = "";
		String XSJG = "";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String bg = "",bg1 = "",b1 = "", b2 = "";
		bg = " style=\"padding:5px;\"";  
		bg1 = "../aws_img/report-bg-blue2.gif";
		try {
//			String sqlw = "select top 1 * from BO_RSTEMP where 1=1 and LOGINID='" + awsuid + "'   order by CREATEDATE desc";
			String sqlw = "select * from BO_RSTEMP where 1=1 and LOGINID='" + awsuid + "'   order by CREATEDATE desc";
			conn=DBSql.open();
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sqlw);
			rs = ps.executeQuery();
			if (rs.next()) {
			   id = rs.getInt("ID");
			   bindId = rs.getInt("BINDID");
			   
			   ZGH = rs.getString("ZGH")==null?"":rs.getString("ZGH"); //�̹���
			   XM = rs.getString("XM")==null?"":rs.getString("XM"); //����
			   XB = rs.getString("XB")==null?"":rs.getString("XB"); //�Ա�
			   CSNY = rs.getString("CSNY")==null?"":rs.getString("CSNY"); //��������
			   MZ = rs.getString("MZ")==null?"":rs.getString("MZ"); //����
			   JG = rs.getString("JG")==null?"":rs.getString("JG"); //����
			   JGSHI = rs.getString("JGSHI")==null?"":rs.getString("JGSHI"); //���ᣨ�У�
			   JGOTHER = rs.getString("JGOTHER")==null?"":rs.getString("JGOTHER");
			   CJGZSJ = rs.getString("CJGZSJ")==null?"":rs.getString("CJGZSJ"); //�μӹ���ʱ��
			   RXGZSJ = rs.getString("RXGZSJ")==null?"":rs.getString("RXGZSJ"); //��Уʱ��
			   BMMC = rs.getString("BMMC")==null?"":rs.getString("BMMC"); //���ڵ�λ
			   JSZW = rs.getString("JSZW")==null?"":rs.getString("JSZW"); //��ְ��
			   JSZW2 = rs.getString("JSZW2")==null?"":rs.getString("JSZW2"); //��ְ��2
			   JSZWRMSJ = rs.getString("JSZWRMSJ")==null?"":rs.getString("JSZWRMSJ"); //��ְʱ��
			   GWLB = rs.getString("GWLB")==null?"":rs.getString("GWLB"); //��λ���
			   GWLB2 = rs.getString("GWLB2")==null?"":rs.getString("GWLB2"); //��λ���2
			   SSXK = rs.getString("SSXK")==null?"":rs.getString("SSXK"); //ѧ�Ʒ���
			   SSXK2 = rs.getString("SSXK2")==null?"":rs.getString("SSXK2"); //ѧ�Ʒ���2 
			   PGJB = rs.getString("PGJB")==null?"":rs.getString("PGJB"); //Ƹλ�ȼ�
			   DSLX = rs.getString("DSLX")==null?"":rs.getString("DSLX"); //��ʦ����
			   YHZZC = rs.getString("YHZZC")==null?"":rs.getString("YHZZC"); //ѧ��ר�� 
			   GJ = rs.getString("GJ")==null?"":rs.getString("GJ"); //����
			   GJ2 = rs.getString("GJ2")==null?"":rs.getString("GJ2"); //����2
			   ZGQK = rs.getString("ZGQK")==null?"":rs.getString("ZGQK"); //�ڸ���� 
			   ZGQK2 = rs.getString("ZGQK2")==null?"":rs.getString("ZGQK2"); //�ڸ�λ��2 
			   ZJLX = rs.getString("ZJLX")==null?"":rs.getString("ZJLX"); //֤������
			   ZJLX2 = rs.getString("ZJLX2")==null?"":rs.getString("ZJLX2"); //֤������2
			   SFZHM = rs.getString("SFZHM")==null?"":rs.getString("SFZHM"); //֤������ 
			   HKSZDCS = rs.getString("HKSZDCS")==null?"":rs.getString("HKSZDCS"); //�������ڵ�
			   HYZK = rs.getString("HYZK")==null?"":rs.getString("HYZK"); //����״�� 
			   XZZ = rs.getString("XZZ")==null?"":rs.getString("XZZ"); //��ͥסַ
			   SJ = rs.getString("SJ")==null?"":rs.getString("SJ"); //�ƶ��绰
			   BGSDH = rs.getString("BGSDH")==null?"":rs.getString("BGSDH"); //�칫�绰
			   EMAIL = rs.getString("EMAIL")==null?"":rs.getString("EMAIL"); //��������
			   ZGXW = rs.getString("ZGXW")==null?"":rs.getString("ZGXW"); //����ѧλ
			   ZGXW2 = rs.getString("ZGXW2")==null?"":rs.getString("ZGXW2"); //���ѧλ2 
			   ZGXW3 = rs.getString("ZGXW3")==null?"":rs.getString("ZGXW3"); //���ѧλ3
			   ZGXWHDSJ = rs.getString("ZGXWHDSJ")==null?"":rs.getString("ZGXWHDSJ"); //��ҵʱ��
			   WYSP = rs.getString("WYSP")==null?"":rs.getString("WYSP"); //����ˮƽ
			   WYYZ = rs.getString("WYYZ")==null?"":rs.getString("WYYZ"); //��������
			   WYYZ2 = rs.getString("WYYZ2")==null?"":rs.getString("WYYZ2"); //��������2 
			   ZGXL = rs.getString("ZGXL")==null?"":rs.getString("ZGXL"); //���ѧ��
			   LXGL = rs.getInt("LXGL"); //�������䣨�꣩
			   ZZMM = rs.getString("ZZMM")==null?"":rs.getString("ZZMM"); //������ò
			   RDSJ = rs.getString("RDSJ")==null?"":rs.getString("RDSJ"); //�뵳ʱ�� 
			   DZZ = rs.getString("DZZ")==null?"":rs.getString("DZZ"); //����֧/�ֵ�ί/..
			   DZZ2 = rs.getString("DZZ2")==null?"":rs.getString("DZZ2"); //��һ������֯
			   DZB = rs.getString("DZB")==null?"":rs.getString("DZB"); //��֧�� 
			   DFJS = rs.getInt("DFJS"); //���ѻ���
			   DNZW = rs.getString("DNZW")==null?"":rs.getString("DNZW"); //����ְ�� 
			   XZZW = rs.getString("XZZW")==null?"":rs.getString("XZZW"); //ְ������
			   XZZWJB = rs.getString("XZZWJB")==null?"":rs.getString("XZZWJB"); //ְ�񼶱� 
			   YJLY = rs.getString("YJLY")==null?"":rs.getString("YJLY").trim();
			   ZYJZ = rs.getString("ZYJZ")==null?"":rs.getString("ZYJZ").trim();
			   BGDD = rs.getString("BGDD")==null?"":rs.getString("BGDD").trim();
			   BGSJ = rs.getString("BGSJ")==null?"":rs.getString("BGSJ").trim();
			   CZ = rs.getString("CZ")==null?"":rs.getString("CZ").trim();
			   TXDZ = rs.getString("TXDZ")==null?"":rs.getString("TXDZ").trim();
			   ZYWZ = rs.getString("ZYWZ")==null?"":rs.getString("ZYWZ").trim();
			   
			   BYXX = rs.getString("BYXX")==null?"":rs.getString("BYXX").trim();
			   XXLB = rs.getString("XXLB")==null?"":rs.getString("XXLB").trim();
			   XSJG = rs.getString("XSJG")==null?"":rs.getString("XSJG").trim();
			   
			   if(CSNY.length() >= 10) {
				   CSNY = CSNY.substring(0,10);
			   }
			   if(CJGZSJ.length() >= 10) {
				   CJGZSJ = CJGZSJ.substring(0,10);
			   }
			   if(RXGZSJ.length() >= 10) {
				   RXGZSJ = RXGZSJ.substring(0,10);
			   }
			   if(JSZWRMSJ.length() >= 10) {
				   JSZWRMSJ = JSZWRMSJ.substring(0,10);
			   }
			   if(ZGXWHDSJ.length() >= 10) {
				   ZGXWHDSJ = ZGXWHDSJ.substring(0,10);
			   }
			   if(RDSJ.length() >= 10) {
				   RDSJ = RDSJ.substring(0,10);
			   }
		   }
		   ps.close();
		   rs.close();

		   sb.append("<tr bgcolor='#CCCCCC'>\n");
		   sb.append("<td><table><td align='left' style='word-break:break-all'='style='word-break:break-all'' style='line-height: 25px; width: 200px' >");
		   sb.append("<a href='#' onclick='modify("+id+");return false;'>�޸�</a></td></table></td>");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>�̹���</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+(ZGH)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>����</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(XM)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>�Ա�</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(XB)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>��������</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(CSNY)+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>����</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+(GJ)+" "+GJ2+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>����</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+(MZ)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>����</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(JG)+" "+JGSHI+b2+JGOTHER+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>֤������/����</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(ZJLX)+" "+ZJLX2+b2+"  "+SFZHM+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>����ˮƽ</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(WYSP)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>��������</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(WYYZ)+" "+WYYZ2+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>���ѧ��</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(ZGXL)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>����ѧλ</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(ZGXW)+" "+ZGXW2+" "+ZGXW3+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>��ҵʱ��</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+(ZGXWHDSJ)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>��ҵѧУ</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+(BYXX)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>ѧУ���</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' colspan='3' style='word-break:break-all'>"+(XXLB)+b2+"</td>\n");
		   
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>�μӹ���ʱ��</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(CJGZSJ)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>��Уʱ��</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(RXGZSJ)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>���ڵ�λ</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' colspan='3' style='word-break:break-all'>"+(BMMC)+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>��ְ��</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(JSZW)+" "+JSZW2+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>��ְʱ��</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(JSZWRMSJ)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>��λ���</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(GWLB)+" "+GWLB2+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>ѧ�Ʒ���</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+(SSXK)+" "+SSXK2+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>Ƹλ�ȼ�</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(PGJB)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>��ʦ����</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(DSLX)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>ѧ��ר��</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(YHZZC)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>ѧ������</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+(XSJG)+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>�о�����</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(YJLY)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>��Ҫ��ְ</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(ZYJZ)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>�ڸ����</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(ZGQK)+" "+ZGQK2+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>�칫�ص�/ʱ��</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(BGDD)+" "+BGSJ+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>��������/��</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+(LXGL)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>����״��</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(HYZK)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>�������ڵ�</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' colspan='3' style='word-break:break-all'>"+(HKSZDCS)+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>��ͥסַ</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' colspan='3' style='word-break:break-all'>"+b1+(XZZ)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>ͨѶ��ַ</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' colspan='3' style='word-break:break-all'>"+b1+(TXDZ)+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>�ƶ��绰</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+(SJ)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>�칫�绰</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(BGSDH)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>����</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(CZ)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>��������</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(EMAIL)+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>��ҳ��ַ</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(ZYWZ)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>������ò</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(ZZMM)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>�뵳ʱ��</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(RDSJ)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>����֧/�ֵ�ί</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(DZZ)+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>��һ������֯</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+(DZZ2)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>��֧��</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(DZB)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>���ѻ���</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(DFJS)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>����ְ��</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(DNZW)+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>ְ������</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' colspan='3' style='word-break:break-all'>"+(XZZW)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>ְ�񼶱�</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' colspan='3' style='word-break:break-all'>"+b1+(XZZWJB)+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   hashTags.put("sid",super.getSIDFlag());
		   hashTags.put("awsuid",awsuid);
		   hashTags.put("list",sb.toString());
		}catch(Exception e){
			e.printStackTrace(System.err);
			return "#���˻�����Ϣ_���ݲ�ѯ�쳣";
		}finally{
			DBSql.close(conn, ps, rs);
		}
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_HR_JBXX.html"),hashTags);
	}
	
}

