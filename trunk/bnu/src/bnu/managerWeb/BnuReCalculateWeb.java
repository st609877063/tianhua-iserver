package bnu.managerWeb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import bnu.importBiz.CalMark;
import bnu.util.HRCardButton;
import bnu.web.BnuMainWeb;

import com.actionsoft.awf.organization.control.MessageQueue;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.htmlframework.htmlmodel.HtmlModelFactory;
import com.actionsoft.htmlframework.htmlmodel.RepleaseKey;
import com.actionsoft.htmlframework.web.ActionsoftWeb;
import com.actionsoft.sdk.local.level0.BOInstanceAPI;

public class BnuReCalculateWeb extends ActionsoftWeb {
	
	private static final String _returnButton = "<input type=button value='��  ��'  class ='actionsoftButton' onClick=\"window.close();return false;\"  border='0'>";

	/**
	 * ���췽��
	 * @param userContext �û������Ķ���
	 */
	public BnuReCalculateWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * ���췽��
	 */
	public BnuReCalculateWeb() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getMain(String pageType,String awsuid) {
		Hashtable<String,String> hashTags = new Hashtable<String,String>();
		String cgi = "./login.wf";
		String sid = super.getContext().getSessionId();
//		System.out.println("BnuMainWeb=====>getMain=====>sid="+sid);
//		System.out.println("BnuMainWeb=====>getMain=====>pageType="+pageType);
//		System.out.println("BnuMainWeb=====>getMain=====>awsuid="+awsuid);
		
		String button1 = "", button2 = "";
		
		if ("1".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=XSLW&awsuid="+awsuid); //�������_ѧ������
		} else if ("2".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=XSZZ&awsuid="+awsuid); //�������_ѧ������
		} else if ("3".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=KYXM&awsuid="+awsuid); //�������_������Ŀ
		} else if ("4".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=YJBG&awsuid="+awsuid); //�������_�о�����
		} else if ("5".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=CGHJ&awsuid="+awsuid); //�������_���л�
		} else if ("6".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=ZLXX&awsuid="+awsuid); //�������_ר����Ϣ
		} else if ("7".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=QTCG&awsuid="+awsuid); //�������_�����ɹ�
		} else if ("8".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=RKQK&awsuid="+awsuid); //�˲�����_�ο����
		} else if ("9".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=ZDXS&awsuid="+awsuid); //�˲�����_ָ��ѧ��
		} else if ("10".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=JGXM&awsuid="+awsuid); //�˲�����_�̸���Ŀ
		} else if ("11".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=JXHJ&awsuid="+awsuid); //�˲�����_��ѧ��
		} else if ("12".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=GZJL&awsuid="+awsuid); //��������
		} else if ("13".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=ZDZY&awsuid="+awsuid); //�˲�����_ָ��רҵ
		} else if ("14".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=GNXXJL&awsuid="+awsuid); //��������_����ѧϰ
		} else if ("15".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=GJWXXJL&awsuid="+awsuid); //��������_������ѧϰ
		} else if ("16".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=PXJL&awsuid="+awsuid); //��������_��ѵ����
		} else if ("17".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=GJHQK&awsuid="+awsuid);  //���ʻ����
		} else if ("18".equals(pageType)) { 
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=JTCY&awsuid="+awsuid); //��ͥ��Ա
		} 
		else if ("2009".equals(pageType)) {  //���þ�����Ϊ2009����ר��
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=2009&awsuid="+awsuid);
		}
		else {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=NULL&awsuid="+awsuid);
		}

		StringBuffer pageButton = new StringBuffer();
		
		pageButton.append("<table width=\"95%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bordercolor=\"#ACBCC9\" style=\"border-collapse:collapse\">");
		pageButton.append("<tr>");
		pageButton.append("<td colspan=3 height=3></td>");
		pageButton.append("</tr>");

		pageButton.append("<tr>");
		pageButton.append("<td colspan=3 align=\"center\" nowrap=\"nowrap\" style='line-height: 25px;' class=\"bg4\">�˹���Ϊ��շ�ֵ�����¼����ֵ�������ʹ�ã�</td>");
		pageButton.append("</tr>");

//		pageButton.append(new HRCardButton("�˲�����_��ѧ��|| ", "changePage(frmRecalMain,'" + 11 + "')", ("11".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//		pageButton.append(new HRCardButton("�˲�����_ָ��רҵ|| ", "changePage(frmRecalMain,'" + 13 + "')", ("13".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//		pageButton.append(new HRCardButton("��������_��ѵ����|| ", "changePage(frmRecalMain,'" + 16 + "')", ("16".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//		pageButton.append(new HRCardButton("��������_������ѧϰ| ", "changePage(frmRecalMain,'" + 15 + "')", ("15".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//		pageButton.append(new HRCardButton("���ʻ����", "changePage(frmRecalMain,'" + 17 + "')", ("17".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));		
//		pageButton.append(new HRCardButton("��������_����ѧϰ����|| ", "changePage(frmRecalMain,'" + 14 + "')", ("14".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//		pageButton.append(new HRCardButton("��ͥ��Ա", "changePage(frmRecalMain,'" + 18 + "')", ("18".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));		

		
		pageButton.append("<tr>");
		pageButton.append(new HRCardButton("�������_ѧ������", "changePage(frmRecalMain,'" + 1 + "')", ("1".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("�������_ר����Ϣ", "changePage(frmRecalMain,'" + 6 + "')", ("6".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("�˲�����_�ο����", "changePage(frmRecalMain,'" + 8 + "')", ("8".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append("</tr>");

		pageButton.append("<tr>");		
		pageButton.append(new HRCardButton("�������_ѧ������", "changePage(frmRecalMain,'" + 2 + "')", ("2".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("�������_���л�", "changePage(frmRecalMain,'" + 5 + "')", ("16".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("�˲�����_�̸���Ŀ", "changePage(frmRecalMain,'" + 10 + "')", ("10".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append("</tr>");
		
		pageButton.append("<tr>");		
		pageButton.append(new HRCardButton("�������_�����ɹ�", "changePage(frmRecalMain,'" + 7 + "')", ("7".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));	
		pageButton.append(new HRCardButton("�������_������Ŀ", "changePage(frmRecalMain,'" + 3 + "')", ("3".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("�˲�����_ָ��ѧ��", "changePage(frmRecalMain,'" + 9 + "')", ("9".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append("</tr>");
		
		pageButton.append("<tr>");
		pageButton.append(new HRCardButton("�������_�о�����", "changePage(frmRecalMain,'" + 4 + "')", ("4".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("�������", "changePage(frmRecalMain,'" + 12 + "')", ("12".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("���þ�����Ϊ2009����", "changePage(frmRecalMain,'" + 2009 + "')", ("2009".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append("</tr>");
		
		pageButton.append("</table>");
		
		hashTags.put("button1", button1);
		hashTags.put("button2", button2);
		hashTags.put("button3", _returnButton);
		hashTags.put("pageButton", pageButton.toString());
		hashTags.put("sid", "<input type=hidden name=sid value=" + super.getContext().getSessionId() + ">\n");
		hashTags.put("pageType", pageType);
		hashTags.put("awsuid", awsuid);
		
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_RECALCULATE_MAIN.htm"), hashTags);
		//end
	}
	
	
	public String reCalculate(String awsuid, String type) {
		/*
		TYPE:
		XSLW //�������_ѧ������
		XSZZ //�������_ѧ������
		KYXM //�������_������Ŀ
		YJBG //�������_�о�����
		CGHJ //�������_���л�
		ZLXX //�������_ר����Ϣ
		QTCG //�������_�����ɹ�
		RKQK //�˲�����_�ο����
		ZDXS //�˲�����_ָ��ѧ��
		JGXM //�˲�����_�̸���Ŀ
		JXHJ //�˲�����_��ѧ��
		GZJL //��������
		ZDZY //�˲�����_ָ��רҵ
		GNXXJL //��������_����ѧϰ
		GJWXXJL //��������_������ѧϰ
		PXJL //��������_��ѵ����
		GJHQK //���ʻ����
		JTCY //��ͥ��Ա
		*/
		
		System.out.println(".....................���¼����ֵ................"+type);
		
		String tableChName = "";
		if(type.equalsIgnoreCase("XSLW")) {
			tableChName = "�������_ѧ������";
		} else if(type.equalsIgnoreCase("XSZZ")) {
			tableChName = "�������_ѧ������";
		} else if(type.equalsIgnoreCase("KYXM")) {
			tableChName = "�������_������Ŀ";
		} else if(type.equalsIgnoreCase("YJBG")) {
			tableChName = "�������_�о�����";
		} else if(type.equalsIgnoreCase("CGHJ")) {
			tableChName = "�������_���л�";
		} else if(type.equalsIgnoreCase("ZLXX")) {
			tableChName = "�������_ר����Ϣ";
		} else if(type.equalsIgnoreCase("QTCG")) {
			tableChName = "�������_�����ɹ�";
		} else if(type.equalsIgnoreCase("RKQK")) {
			tableChName = "�˲�����_�ο����";
		} else if(type.equalsIgnoreCase("ZDXS")) {
			tableChName = "�˲�����_ָ��ѧ��";
		} else if(type.equalsIgnoreCase("JGXM")) {
			tableChName = "�˲�����_�̸���Ŀ";
		} else if(type.equalsIgnoreCase("JXHJ")) {
			tableChName = "�˲�����_��ѧ��";
		} else if(type.equalsIgnoreCase("GZJL")) {
			tableChName = "��������";
		} else if(type.equalsIgnoreCase("ZDZY")) {
			tableChName = "�˲�����_ָ��רҵ";
		} else if(type.equalsIgnoreCase("GNXXJL")) {
			tableChName = "��������_����ѧϰ";
		} else if(type.equalsIgnoreCase("GJWXXJL")) {
			tableChName = "��������_������ѧϰ";
		} else if(type.equalsIgnoreCase("PXJL")) {
			tableChName = "��������_��ѵ����";
		} else if(type.equalsIgnoreCase("GJHQK")) {
			tableChName = "���ʻ����";
		} else if(type.equalsIgnoreCase("JTCY")) {
			tableChName = "��ͥ��Ա";
		} else if(type.equalsIgnoreCase("NULL")) {
			tableChName = "δѡ�������";
		} 
		
		
		BnuReCalculateWeb web = new BnuReCalculateWeb();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		if(type == null || type.equalsIgnoreCase("NULL")) {
//			return web.getMain("0",awsuid);
			return "��ѡ������������÷�ֵ�������������";
		} else if(type.equals("2009")) { //���þ�����Ϊ2009����
			web.setDataYear2009();
			return "���þ�����Ϊ2009���ݳɹ���";
		}
		
		String tableName = "BO_"+type.toUpperCase()+"TEMP";
		
		try{
			
			/**********����һ:�������
			 * ���ǣ����ݹ����֡�AUTOADD=1 ����
			***********************/
			String clearSql = "";
			if(type.equalsIgnoreCase("RKQK") || type.equalsIgnoreCase("ZDXS") || type.equalsIgnoreCase("GZJL")) {
				clearSql = " UPDATE "+tableName+" SET FINALMARK=0";
			} else {
				clearSql = " UPDATE "+tableName+" SET FINALMARK=0 WHERE AUTOADD=1";
			}
			DBSql.executeUpdate(clearSql);
			
			//ZFM, DATAYEAR, JXZFM(��ѧ�ܷ�ֵ)
			String clearSql2 = " UPDATE BO_FINALMARK  SET "+type.toUpperCase()+"=0";
			DBSql.executeUpdate(clearSql2);
			String updateSql = " UPDATE BO_FINALMARK  SET ZFM = NVL(GNXXJL,0)+NVL(GJWXXJL,0)" +
					"+NVL(PXJL,0)+NVL(GZJL,0)+NVL(RKQK,0)+NVL(ZDZY,0)+NVL(ZDXS,0)" +
					"+NVL(JGXM,0)+NVL(JXHJ,0)+NVL(KYXM,0)+NVL(XSLW,0)+NVL(XSZZ,0)" +
					"+NVL(ZLXX,0)+NVL(QTCG,0)+NVL(CGHJ,0)+NVL(GJHQK,0)+NVL(JTCY,0)+NVL(YJBG,0)";
			DBSql.executeUpdate(updateSql);
			//���������������, ���˲�����_�ο������, ���˲�����_ָ��ѧ���� �������⴦������JXZFM����ѧ�ܷ�ֵ��
			String updateSql2 = " UPDATE BO_FINALMARK  SET JXZFM = NVL(GZJL,0)+NVL(RKQK,0)+NVL(ZDXS,0)";
			DBSql.executeUpdate(updateSql2);
			//����KYZFM�������ܷ�ֵ�� XSLW �������_ѧ������; XSZZ �������_ѧ������;	KYXM �������_������Ŀ; YJBG�������_�о�����;CGHJ �������_���л�;ZLXX �������_ר����Ϣ;QTCG �������_�����ɹ�
			String updateSql3 = " UPDATE BO_FINALMARK  SET KYZFM = NVL(XSLW,0)+NVL(XSZZ,0)+NVL(KYXM,0)+NVL(YJBG,0)+NVL(CGHJ,0)+NVL(ZLXX,0)+NVL(QTCG,0)";
			DBSql.executeUpdate(updateSql3);
			
			/**********����������¼����ֵ���*********************************/
			conn = DBSql.open();
			conn.setAutoCommit(true);
			
			String sql = "SELECT * FROM "+tableName;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			int dataId = 0;
			String jgh = "";
			
			while(rs.next()){
				dataId = rs.getInt("ID");
				if(type.equalsIgnoreCase("RKQK") || type.equalsIgnoreCase("ZDXS") || type.equalsIgnoreCase("GZJL")) {
					jgh = rs.getString("CHARGEID")==null?"":rs.getString("CHARGEID").trim();
				} else {
					jgh = rs.getString("JGH")==null?"":rs.getString("JGH").trim();
				}
				String datayear = rs.getString("DATAYEAR")==null?"":rs.getString("DATAYEAR").trim();
				
				CalMark calMark = new CalMark();
				int finalMark = calMark.getCalMark(tableName, dataId);
				
				String setMarkSql = "UPDATE "+tableName+" set FINALMARK="+finalMark+" where id="+dataId;
				DBSql.executeUpdate(setMarkSql);
				if(jgh!=null && !jgh.equals("")) { //����û�н̹��ŵ����ݡ���Ϊ��������
					calMark.setFinalMarkWithYear("", "", jgh, tableName, finalMark, datayear);
				}
			}
		}catch(Exception e){
			e.printStackTrace(System.err);
			MessageQueue.getInstance().putMessage(super.getContext().getUID(),tableChName+" ���÷�ֵ����");
//			return web.getMain("0",awsuid);
			return tableChName+" ���÷�ֵ����";
		}finally{
			DBSql.close(conn, ps, rs);
		}
		
		MessageQueue.getInstance().putMessage(super.getContext().getUID(),tableChName+" ���÷�ֵ�ɹ���");
//		return web.getMain("0",awsuid);
		return tableChName+" ���÷�ֵ�ɹ���";
	}
	
	
	private void setDataYear2009() {
		//ǰ̨�趨��ÿ������DATAYEAR�ֶκ󣬺�̨�������޸ľ�����
		
		String updateBOFINALSql = "UPDATE BO_FINALMARK SET DATAYEAR='2009'";
		System.out.println("����BO_FINALMARK��sql="+updateBOFINALSql);
		DBSql.executeUpdate(updateBOFINALSql);
		
		List tableList = new ArrayList();
		tableList.add("BO_XSZZTEMP");
		tableList.add("BO_ZLXXTEMP");
		tableList.add("BO_QTCGTEMP");
		tableList.add("BO_CGHJTEMP");
		tableList.add("BO_JGXMTEMP");
		tableList.add("BO_RKQKTEMP");
		tableList.add("BO_ZDXSTEMP");
		tableList.add("BO_GZJLTEMP");
		tableList.add("BO_YJBGTEMP"); //
		tableList.add("BO_XSLWTEMP"); //
		tableList.add("BO_KYXMTEMP"); //
		
		for(int i=0; i<tableList.size(); i++) {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			String tableName = (String)tableList.get(i);
			
			/**********ԭ��������û�б�ʾ��ȵ��ֶΡ�ֱ��update���****************/
			if(tableName.equals("BO_XSZZTEMP") || tableName.equals("BO_ZLXXTEMP") || tableName.equals("BO_QTCGTEMP") || tableName.equals("BO_CGHJTEMP") || tableName.equals("BO_JGXMTEMP") || tableName.equals("BO_RKQKTEMP") || tableName.equals("BO_ZDXSTEMP")|| tableName.equals("BO_GZJLTEMP")) {
				String updateDataYearSql = "UPDATE "+tableName+" SET DATAYEAR='2009'";
				System.out.println("ԭ��������ֶΡ�sql="+updateDataYearSql);
				DBSql.executeUpdate(updateDataYearSql);
			}
			
			/**********���ԭ���������ֶα�ʾ��ȣ��Ǹ��������ݡ�****************/
			// �������_�о�����(FBND), �������_ѧ������(FBND), �������_������Ŀ(LXND)
			if(tableName.equals("BO_YJBGTEMP") || tableName.equals("BO_XSLWTEMP") || tableName.equals("BO_KYXMTEMP")) {
				try{
					conn = DBSql.open();
					conn.setAutoCommit(true);
					String sql = "SELECT * FROM "+tableName;
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					
					int dataId = 0;
					while(rs.next()){
						dataId = rs.getInt("ID");
						String datayearOld = "";
						if(tableName.equals("BO_YJBGTEMP") || tableName.equals("BO_XSLWTEMP")) {
							datayearOld = rs.getString("FBND")==null?"2009":rs.getString("FBND").trim();
						} else if(tableName.equals("BO_KYXMTEMP")) {
							datayearOld = rs.getString("LXND")==null?"2009":rs.getString("FBND").trim();
						}
						String updateDataYearSql2 = "UPDATE "+tableName+" SET DATAYEAR='"+datayearOld+"' WHERE ID="+dataId;
						DBSql.executeUpdate(updateDataYearSql2);
					}
				} catch(Exception e){
					e.printStackTrace(System.err);
				} finally{
					DBSql.close(conn, ps, rs);
				}
			}
		}
	}
	
}
