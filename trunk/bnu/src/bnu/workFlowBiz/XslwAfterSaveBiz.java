package bnu.workFlowBiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.actionsoft.awf.organization.control.MessageQueue;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.loader.core.WorkFlowStepRTClassA;
import com.actionsoft.sdk.local.level0.BOInstanceAPI;

//�������_ѧ������ 

public class XslwAfterSaveBiz extends WorkFlowStepRTClassA {
	
	public XslwAfterSaveBiz(UserContext uc){
		super(uc);
		super.setDescription("�������_ѧ������_����ɹ�:���Ľ̹���,���¹�������");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}
	
	@Override
	public boolean execute() {
		System.out.println(".....................�������_ѧ������_����:���Ľ̹���,���¹�������begin.....................");
		
		int workflowInstanceId = getParameter(this.PARAMETER_INSTANCE_ID).toInt();//��õ�ǰ��������id
		Hashtable rsTable = BOInstanceAPI.getInstance().getBOData("BO_XSLWTEMP", workflowInstanceId);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = DBSql.open();
			conn.setAutoCommit(true);
			
			int mainDataId = 0;
			String loginId = "";
			String loginId1 = "";
			String loginId2 = "";
			String loginId3 = "";
			String loginId4 = "";
			String loginId5 = "";

			String copyDataStr = rsTable.get("COPYDATAID").toString() == null ? "" : rsTable.get("COPYDATAID").toString().trim();
			String createuser = rsTable.get("CREATEUSER").toString() == null ? "" : rsTable.get("CREATEUSER").toString().trim();
			
			
			String lwmc = rsTable.get("LWMC").toString() == null ? "" : rsTable.get("LWMC").toString().trim();
			String lwywmc = rsTable.get("LWYWMC").toString() == null ? "" : rsTable.get("LWYWMC").toString().trim();
			String lwlx = rsTable.get("LWLX").toString() == null ? "" : rsTable.get("LWLX").toString().trim();
			String wcxs = rsTable.get("WCXS").toString() == null ? "" : rsTable.get("WCXS").toString().trim();
			String chargesf = rsTable.get("CHARGESF").toString() == null ? "" : rsTable.get("CHARGESF").toString().trim();
			String ssyx = rsTable.get("SSYX").toString() == null ? "" : rsTable.get("SSYX").toString().trim();
			String chargenm = rsTable.get("CHARGENM").toString() == null ? "" : rsTable.get("CHARGENM").toString().trim();
			String name1 = rsTable.get("NAME1").toString() == null ? "" : rsTable.get("NAME1").toString().trim();
			String name2 = rsTable.get("NAME2").toString() == null ? "" : rsTable.get("NAME2").toString().trim();
			String name3 = rsTable.get("NAME3").toString() == null ? "" : rsTable.get("NAME3").toString().trim();
			String name4 = rsTable.get("NAME4").toString() == null ? "" : rsTable.get("NAME4").toString().trim();
			String name5 = rsTable.get("NAME5").toString() == null ? "" : rsTable.get("NAME5").toString().trim();
//			int chargerate = Integer.parseInt(rsTable.get("CHARGERATE").toString() == null ? "0" : rsTable.get("CHARGERATE").toString().trim());
//			int rate1 = Integer.parseInt(rsTable.get("RATE1").toString() == null ? "0" : rsTable.get("RATE1").toString().trim());
//			int rate2 = Integer.parseInt(rsTable.get("RATE2").toString() == null ? "0" : rsTable.get("RATE2").toString().trim());
//			int rate3 = Integer.parseInt(rsTable.get("RATE3").toString() == null ? "0" : rsTable.get("RATE3").toString().trim());
//			int rate4 = Integer.parseInt(rsTable.get("RATE4").toString() == null ? "0" : rsTable.get("RATE4").toString().trim());
//			int rate5 = Integer.parseInt(rsTable.get("RATE5").toString() == null ? "0" : rsTable.get("RATE5").toString().trim());
			//����ȥ��������������
			int chargerate = 0;
			int rate1 = 0;
			int rate2 = 0;
			int rate3 = 0;
			int rate4 = 0;
			int rate5 = 0;
			
			String gklx = rsTable.get("GKLX").toString() == null ? "" : rsTable.get("GKLX").toString().trim();
			String fbkw = rsTable.get("FBKW").toString() == null ? "" : rsTable.get("FBKW").toString().trim();
			String fbfw = rsTable.get("FBFW").toString() == null ? "" : rsTable.get("FBFW").toString().trim();
			String kwjb = rsTable.get("KWJB").toString() == null ? "" : rsTable.get("KWJB").toString().trim();
			String kwjb2 = rsTable.get("KWJB2").toString() == null ? "" : rsTable.get("KWJB2").toString().trim();
			String nian = rsTable.get("NIAN").toString() == null ? "" : rsTable.get("NIAN").toString().trim();
			String juan = rsTable.get("JUAN").toString() == null ? "" : rsTable.get("JUAN").toString().trim();
			String qh = rsTable.get("QH").toString() == null ? "" : rsTable.get("QH").toString().trim();
			String ym = rsTable.get("YM").toString() == null ? "" : rsTable.get("YM").toString().trim();
			String lwyz = rsTable.get("LWYZ").toString() == null ? "" : rsTable.get("LWYZ").toString().trim();
			String sfyw = rsTable.get("SFYW").toString() == null ? "" : rsTable.get("SFYW").toString().trim();
			String lwyyqk = rsTable.get("LWYYQK").toString() == null ? "" : rsTable.get("LWYYQK").toString().trim();
			String yjlx = rsTable.get("YJLX").toString() == null ? "" : rsTable.get("YJLX").toString().trim();
			String sfzz = rsTable.get("SFZZ").toString() == null ? "" : rsTable.get("SFZZ").toString().trim();
			String zzcc = rsTable.get("ZZCC").toString() == null ? "" : rsTable.get("ZZCC").toString().trim();
			String zs = rsTable.get("ZS").toString() == null ? "" : rsTable.get("ZS").toString().trim();
			String dyzzdw = rsTable.get("DYZZDW").toString() == null ? "" : rsTable.get("DYZZDW").toString().trim();
			String fbsj = rsTable.get("FBSJ").toString() == null ? "" : rsTable.get("FBSJ").toString().trim();
			String yjly = rsTable.get("YJLY").toString() == null ? "" : rsTable.get("YJLY").toString().trim();
			String ktly = rsTable.get("KTLY").toString() == null ? "" : rsTable.get("KTLY").toString().trim();
			String zzlx = rsTable.get("ZZLX").toString() == null ? "" : rsTable.get("ZZLX").toString().trim();
			String xgxkly = rsTable.get("XGXKLY").toString() == null ? "" : rsTable.get("XGXKLY").toString().trim();
			String kwlb = rsTable.get("KWLB").toString() == null ? "" : rsTable.get("KWLB").toString().trim();
			String lwyz2 = rsTable.get("LWYZ2").toString() == null ? "" : rsTable.get("LWYZ2").toString().trim();
			String xkly = rsTable.get("XKLY").toString() == null ? "" : rsTable.get("XKLY").toString().trim();
			String attach = rsTable.get("ATTACH").toString() == null ? "" : rsTable.get("ATTACH").toString().trim();
			String sftxzz = rsTable.get("SFTXZZ").toString() == null ? "" : rsTable.get("SFTXZZ").toString().trim();
			
			if(fbsj!=null && fbsj.length()>=10) {
				fbsj = fbsj.substring(0, 10);
			}
			
//��֤begin			
			String datayear = rsTable.get("DATAYEAR").toString()==null?"":rsTable.get("DATAYEAR").toString().trim();
			if(datayear.equals("") || datayear.length() != 4) {
				MessageQueue.getInstance().putMessage(getUserContext().getUID(), "���棬��������ȡ�������д�����Ҹ�ʽΪ��λ������ȷ��!", true);
				return false;
			}
			
			if(chargesf.equals("")) {
				MessageQueue.getInstance().putMessage(getUserContext().getUID(), "���棬��ѡ�񡾵�һ������ݡ�����ȷ��!", true);
				return false;
			}
//��֤end			
			
			
			//��loginId���ޡ��̹��š�
			mainDataId = Integer.parseInt(rsTable.get("ID").toString() == null ? "" : rsTable.get("ID").toString().trim());
			loginId = rsTable.get("CHARGEID").toString() == null ? "" : rsTable.get("CHARGEID").toString().trim();
			loginId1 = rsTable.get("ID1").toString() == null ? "" : rsTable.get("ID1").toString().trim();
			loginId2 = rsTable.get("ID2").toString() == null ? "" : rsTable.get("ID2").toString().trim();
			loginId3 = rsTable.get("ID3").toString() == null ? "" : rsTable.get("ID3").toString().trim();
			loginId4 = rsTable.get("ID4").toString() == null ? "" : rsTable.get("ID4").toString().trim();
			loginId5 = rsTable.get("ID5").toString() == null ? "" : rsTable.get("ID5").toString().trim();
			
			String jgh = "";
			String jgh1 = "";
			String jgh2 = "";
			String jgh3 = "";
			String jgh4 = "";
			String jgh5 = "";
			
			if(!chargesf.equals("��У��ʦ")) {
				loginId = "";
				jgh = "";
			} else if(chargesf.equals("��У��ʦ")) {
				String sql = "select ZGH from BO_RSTEMP where xm='" +chargenm+"'";
				loginId = DBSql.getString(conn, sql, "ZGH".trim());
				jgh = loginId;
			} 
			
			//�ж�ǰ̨�Ƿ����ò����ˡ����û�У��ÿ�ԭ��������
			if(chargenm == null || chargenm.equals("")) {
				loginId = "";
				jgh = "";
			}
			if(name1 == null || name1.equals("")) {
				loginId1 = "";
				jgh1 = "";
			}
			if(name2 == null || name2.equals("")) {
				loginId2 = "";
				jgh2 = "";
			}
			if(name3 == null || name3.equals("")) {
				loginId3 = "";
				jgh3 = "";
			}
			if(name4 == null || name4.equals("")) {
				loginId4 = "";
				jgh4 = "";
			}
			if(name5 == null || name5.equals("")) {
				loginId5 = "";
				jgh5 = "";
			}
			
			String querySql = "";
			
			if(loginId!=null && !loginId.equals("") && (loginId.indexOf("<")>0)) {   //DB�д��Ϊ��loginId<����>"��ʽ
				loginId = loginId.substring(0, loginId.indexOf("<"));
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId+"'";
				jgh = DBSql.getString(conn, querySql, "ZGH").trim();
			} else if(loginId!=null && !loginId.equals("")) {   //DB�д��Ϊ��loginId����ʽ
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId+"'";
				jgh = DBSql.getString(conn, querySql, "ZGH".trim());
			}
			if(loginId1!=null && !loginId1.equals("") && (loginId1.indexOf("<")>0)) {   //DB�д��Ϊ��loginId<����>"��ʽ
				loginId1 = loginId1.substring(0, loginId1.indexOf("<"));
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId1+"'";
				jgh1 = DBSql.getString(conn, querySql, "ZGH").trim();
			} else if(loginId1!=null && !loginId1.equals("")) {   //DB�д��Ϊ��loginId����ʽ
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId1+"'";
				jgh1 = DBSql.getString(conn, querySql, "ZGH").trim();
			}
			if(loginId2!=null && !loginId2.equals("") && (loginId2.indexOf("<")>0)) {   //DB�д��Ϊ��loginId<����>"��ʽ
				loginId2 = loginId2.substring(0, loginId2.indexOf("<"));
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId2+"'";
				jgh2 = DBSql.getString(conn, querySql, "ZGH").trim();
			} else if(loginId2!=null && !loginId2.equals("")) {   //DB�д��Ϊ��loginId����ʽ
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId2+"'";
				jgh2 = DBSql.getString(conn, querySql, "ZGH").trim();
			}
			if(loginId3!=null && !loginId3.equals("") && (loginId3.indexOf("<")>0)) {   //DB�д��Ϊ��loginId<����>"��ʽ
				loginId3 = loginId3.substring(0, loginId3.indexOf("<"));
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId3+"'";
				jgh3 = DBSql.getString(conn, querySql, "ZGH").trim();
			} else if(loginId3!=null && !loginId3.equals("")) {   //DB�д��Ϊ��loginId����ʽ
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId3+"'";
				jgh3 = DBSql.getString(conn, querySql, "ZGH").trim();
			}
			if(loginId4!=null && !loginId4.equals("") && (loginId4.indexOf("<")>0)) {   //DB�д��Ϊ��loginId<����>"��ʽ
				loginId4 = loginId4.substring(0, loginId4.indexOf("<"));
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId4+"'";
				jgh4 = DBSql.getString(conn, querySql, "ZGH").trim();
			} else if(loginId4!=null && !loginId4.equals("")) {   //DB�д��Ϊ��loginId����ʽ
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId4+"'";
				jgh4 = DBSql.getString(conn, querySql, "ZGH").trim();
			}
			if(loginId5!=null && !loginId5.equals("") && (loginId5.indexOf("<")>0)) {   //DB�д��Ϊ��loginId<����>"��ʽ
				loginId5 = loginId5.substring(0, loginId5.indexOf("<"));
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId5+"'";
				jgh5 = DBSql.getString(conn, querySql, "ZGH").trim();
			} else if(loginId5!=null && !loginId5.equals("")) {   //DB�д��Ϊ��loginId����ʽ
				querySql = "select ZGH from BO_RSTEMP where loginid='" +loginId5+"'";
				jgh5 = DBSql.getString(conn, querySql, "ZGH").trim();
			}
			
			String updateInstanceSql = "update BO_XSLWTEMP set JGH ='" +jgh +"' "
			+ ", JGH1='" +jgh1 + "' "
			+ ", JGH2='" +jgh2 + "' "
			+ ", JGH3='" +jgh3 + "' "
			+ ", JGH4='" +jgh4 + "' "
			+ ", JGH5='" +jgh5 + "' "
			+ ", CHARGEID='" +loginId + "' "
			+ ", ID1='" +loginId1 + "' "
			+ ", ID2='" +loginId2 + "' "
			+ ", ID3='" +loginId3 + "' "
			+ ", ID4='" +loginId4 + "' "
			+ ", ID5='" +loginId5 + "' "
			+ " where ID="+mainDataId;
			DBSql.executeUpdate(updateInstanceSql);
			
			
			
			/********************************���ݹ�����ɾ��������Ϣ������ӡ����ж�Ӧ�Ĳ���*****************************/
			List copyDataList = new ArrayList();
			if(copyDataStr!=null) {
				String temp[] = copyDataStr.split(":");
				for(int i=0; i<temp.length; i++) {
					copyDataList.add(temp[i]);
				}
			}
			for(int i=0; i<copyDataList.size(); i++) {
				String copyDataIdTemp = copyDataList.get(i).toString();
				if(copyDataIdTemp!=null && !copyDataIdTemp.equals("")) {
					int copyDataIdInt = Integer.parseInt(copyDataIdTemp);
					String deleteSql = "delete from BO_XSLWTEMP where ID ="+copyDataIdInt;
//					System.out.println("deleteSql="+deleteSql);
					DBSql.executeUpdate(deleteSql); //ɾ�����ݡ�������
				}
			}
			
			
			//**********************���ݹ����֣�Ϊ�������˸���ͬ��������.���autoAdd�ֶ�Ϊ1**************************
			Hashtable recordData = new Hashtable();
			int loginId1BindId = 0;
			int loginId2BindId = 0;
			int loginId3BindId = 0;
			int loginId4BindId = 0;
			int loginId5BindId = 0;
			String queryBindIdSql = "";
			int createBoFailureNum = 0;
			
			String copyDataIds = "0:";
			
			//BOInstanceAPI.getInstance().createBOData.�������ʹ�ø÷������������е�recordData���벻һ��
			if(loginId1 != null && !loginId1.equals("")) {
				recordData.put("ID1", loginId1);
				recordData.put("JGH1", jgh1);
				queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId1+ "'";
				loginId1BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //����������Ϊ����Ա��bindId

				int createBoId = BOInstanceAPI.getInstance().createBOData("BO_XSLWTEMP", recordData, loginId1BindId, createuser);
				if(createBoId == -1) {
					createBoFailureNum = createBoFailureNum + 1;
				} else {
					copyDataIds = copyDataIds + createBoId + ":";
				}
				
//				System.out.println("Ϊ "+loginId1+" �������ݣ�createBoId="+createBoId+", BINDID="+loginId1BindId);
				String updateCreateBoSql = "update BO_XSLWTEMP set "
										+ "	 LWMC ='" + lwmc +"' "
										+ ", LWYWMC ='" + lwywmc +"' "
										+ ", LWLX ='" + lwlx +"' "
										+ ", WCXS ='" + wcxs +"' "
										+ ", CHARGESF ='" + chargesf +"' "
										+ ", SSYX ='" + ssyx +"' "
										+ ", CHARGENM ='" + chargenm +"' "
										+ ", CHARGEID ='" + loginId +"' "
										+ ", JGH ='" + jgh +"' "
										+ ", CHARGERATE =" + chargerate
										+ ", NAME1 ='" + name1 +"' "
										+ ", ID1 ='" + loginId1 +"' "
										+ ", JGH1 ='" + jgh1 +"' "
										+ ", RATE1 =" + rate1
										+ ", NAME2 ='" + name2 +"' "
										+ ", ID2 ='" + loginId2 +"' "
										+ ", JGH2 ='" + jgh2 +"' "
										+ ", RATE2 =" + rate2
										+ ", NAME3 ='" + name3 +"' "
										+ ", ID3 ='" + loginId3 +"' "
										+ ", JGH3 ='" + jgh3 +"' "
										+ ", RATE3 =" + rate3
										+ ", NAME4 ='" + name4 +"' "
										+ ", ID4 ='" + loginId4 +"' "
										+ ", JGH4 ='" + jgh4 +"' "
										+ ", RATE4 =" + rate4
										+ ", NAME5 ='" + name5 +"' "
										+ ", ID5 ='" + loginId5 +"' "
										+ ", JGH5 ='" + jgh5 +"' "
										+ ", RATE5 =" + rate5
										+ ", GKLX ='" + gklx +"' "
										+ ", FBKW ='" + fbkw +"' "
										+ ", FBFW ='" + fbfw +"' "
										+ ", KWJB ='" + kwjb +"' "
										+ ", KWJB2 ='" + kwjb2 +"' "
										+ ", NIAN ='" + nian +"' "
										+ ", JUAN ='" + juan +"' "
										+ ", QH ='" + qh +"' "
										+ ", YM ='" + ym +"' "
										+ ", LWYZ ='" + lwyz +"' "
										+ ", SFYW ='" + sfyw +"' "
										+ ", LWYYQK ='" + lwyyqk +"' "
										+ ", YJLX ='" + yjlx +"' "
										+ ", SFZZ ='" + sfzz +"' "
										+ ", ZZCC ='" + zzcc +"' "
										+ ", ZS ='" + zs +"' "
										+ ", DYZZDW ='" + dyzzdw +"' "
										+ ", FBSJ = TO_DATE('" +  fbsj +"','YYYY-MM-DD') "
										+ ", YJLY ='" + yjly +"' "
										+ ", KTLY ='" + ktly +"' "
										+ ", ZZLX ='" + zzlx +"' "
										+ ", XGXKLY ='" + xgxkly +"' "
										+ ", KWLB ='" + kwlb +"' "
										+ ", LWYZ2 ='" + lwyz2 +"' "
										+ ", XKLY ='" + xkly +"' "
										+ ", ATTACH ='" + attach +"' "
										+ ", SFTXZZ ='" + sftxzz +"' "
										+ ", DATAYEAR ='" + datayear +"' "
								        + ", AUTOADD =1 "
										+ ", STATUS =0 "
										+ ", FINALMARK =0 "
										+ ", COPYDATAID ='0:' "
										+ " where ID="+createBoId;
//				System.out.println("updateCreateBoSql="+updateCreateBoSql);
				DBSql.executeUpdate(updateCreateBoSql);
			}
			if(loginId2 != null && !loginId2.equals("")) {
				recordData.put("ID2", loginId2);
				recordData.put("JGH2", jgh2);
				
				queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId2+ "'";
				loginId2BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //����������Ϊ����Ա��bindId
				
				int createBoId = BOInstanceAPI.getInstance().createBOData("BO_XSLWTEMP", recordData, loginId2BindId, createuser);
				if(createBoId == -1) {
					createBoFailureNum = createBoFailureNum + 1;
				} else {
					copyDataIds = copyDataIds + createBoId + ":";
				}
//				System.out.println("Ϊ "+loginId2+" �������ݣ�createBoId="+createBoId+", BINDID="+loginId2BindId);
				String updateCreateBoSql = "update BO_XSLWTEMP set "
					+ "	 LWMC ='" + lwmc +"' "
					+ ", LWYWMC ='" + lwywmc +"' "
					+ ", LWLX ='" + lwlx +"' "
					+ ", WCXS ='" + wcxs +"' "
					+ ", CHARGESF ='" + chargesf +"' "
					+ ", SSYX ='" + ssyx +"' "
					+ ", CHARGENM ='" + chargenm +"' "
					+ ", CHARGEID ='" + loginId +"' "
					+ ", JGH ='" + jgh +"' "
					+ ", CHARGERATE =" + chargerate
					+ ", NAME1 ='" + name1 +"' "
					+ ", ID1 ='" + loginId1 +"' "
					+ ", JGH1 ='" + jgh1 +"' "
					+ ", RATE1 =" + rate1
					+ ", NAME2 ='" + name2 +"' "
					+ ", ID2 ='" + loginId2 +"' "
					+ ", JGH2 ='" + jgh2 +"' "
					+ ", RATE2 =" + rate2
					+ ", NAME3 ='" + name3 +"' "
					+ ", ID3 ='" + loginId3 +"' "
					+ ", JGH3 ='" + jgh3 +"' "
					+ ", RATE3 =" + rate3
					+ ", NAME4 ='" + name4 +"' "
					+ ", ID4 ='" + loginId4 +"' "
					+ ", JGH4 ='" + jgh4 +"' "
					+ ", RATE4 =" + rate4
					+ ", NAME5 ='" + name5 +"' "
					+ ", ID5 ='" + loginId5 +"' "
					+ ", JGH5 ='" + jgh5 +"' "
					+ ", RATE5 =" + rate5
					+ ", GKLX ='" + gklx +"' "
					+ ", FBKW ='" + fbkw +"' "
					+ ", FBFW ='" + fbfw +"' "
					+ ", KWJB ='" + kwjb +"' "
					+ ", KWJB2 ='" + kwjb2 +"' "
					+ ", NIAN ='" + nian +"' "
					+ ", JUAN ='" + juan +"' "
					+ ", QH ='" + qh +"' "
					+ ", YM ='" + ym +"' "
					+ ", LWYZ ='" + lwyz +"' "
					+ ", SFYW ='" + sfyw +"' "
					+ ", LWYYQK ='" + lwyyqk +"' "
					+ ", YJLX ='" + yjlx +"' "
					+ ", SFZZ ='" + sfzz +"' "
					+ ", ZZCC ='" + zzcc +"' "
					+ ", ZS ='" + zs +"' "
					+ ", DYZZDW ='" + dyzzdw +"' "
					+ ", FBSJ = TO_DATE('" +  fbsj +"','YYYY-MM-DD') "
					+ ", YJLY ='" + yjly +"' "
					+ ", KTLY ='" + ktly +"' "
					+ ", ZZLX ='" + zzlx +"' "
					+ ", XGXKLY ='" + xgxkly +"' "
					+ ", KWLB ='" + kwlb +"' "
					+ ", LWYZ2 ='" + lwyz2 +"' "
					+ ", XKLY ='" + xkly +"' "
					+ ", ATTACH ='" + attach +"' "
					+ ", SFTXZZ ='" + sftxzz +"' "
					+ ", DATAYEAR ='" + datayear +"' "
			        + ", AUTOADD =1 "
					+ ", STATUS =0 "
					+ ", FINALMARK =0 "
					+ ", COPYDATAID ='0:' "
					+ " where ID="+createBoId;
//				System.out.println("updateCreateBoSql="+updateCreateBoSql);
				DBSql.executeUpdate(updateCreateBoSql);
			}
			if(loginId3 != null && !loginId3.equals("")) {
				recordData.put("ID3", loginId3);
				recordData.put("JGH3", jgh3);
				
				queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId3+ "'";
				loginId3BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //����������Ϊ����Ա��bindId
				
				int createBoId = BOInstanceAPI.getInstance().createBOData("BO_XSLWTEMP", recordData, loginId3BindId, createuser);
				if(createBoId == -1) {
					createBoFailureNum = createBoFailureNum + 1;
				} else {
					copyDataIds = copyDataIds + createBoId + ":";
				}
//				System.out.println("Ϊ "+loginId3+" �������ݣ�createBoId="+createBoId+", BINDID="+loginId3BindId);
				String updateCreateBoSql = "update BO_XSLWTEMP set "
					+ "	 LWMC ='" + lwmc +"' "
					+ ", LWYWMC ='" + lwywmc +"' "
					+ ", LWLX ='" + lwlx +"' "
					+ ", WCXS ='" + wcxs +"' "
					+ ", CHARGESF ='" + chargesf +"' "
					+ ", SSYX ='" + ssyx +"' "
					+ ", CHARGENM ='" + chargenm +"' "
					+ ", CHARGEID ='" + loginId +"' "
					+ ", JGH ='" + jgh +"' "
					+ ", CHARGERATE =" + chargerate
					+ ", NAME1 ='" + name1 +"' "
					+ ", ID1 ='" + loginId1 +"' "
					+ ", JGH1 ='" + jgh1 +"' "
					+ ", RATE1 =" + rate1
					+ ", NAME2 ='" + name2 +"' "
					+ ", ID2 ='" + loginId2 +"' "
					+ ", JGH2 ='" + jgh2 +"' "
					+ ", RATE2 =" + rate2
					+ ", NAME3 ='" + name3 +"' "
					+ ", ID3 ='" + loginId3 +"' "
					+ ", JGH3 ='" + jgh3 +"' "
					+ ", RATE3 =" + rate3
					+ ", NAME4 ='" + name4 +"' "
					+ ", ID4 ='" + loginId4 +"' "
					+ ", JGH4 ='" + jgh4 +"' "
					+ ", RATE4 =" + rate4
					+ ", NAME5 ='" + name5 +"' "
					+ ", ID5 ='" + loginId5 +"' "
					+ ", JGH5 ='" + jgh5 +"' "
					+ ", RATE5 =" + rate5
					+ ", GKLX ='" + gklx +"' "
					+ ", FBKW ='" + fbkw +"' "
					+ ", FBFW ='" + fbfw +"' "
					+ ", KWJB ='" + kwjb +"' "
					+ ", KWJB2 ='" + kwjb2 +"' "
					+ ", NIAN ='" + nian +"' "
					+ ", JUAN ='" + juan +"' "
					+ ", QH ='" + qh +"' "
					+ ", YM ='" + ym +"' "
					+ ", LWYZ ='" + lwyz +"' "
					+ ", SFYW ='" + sfyw +"' "
					+ ", LWYYQK ='" + lwyyqk +"' "
					+ ", YJLX ='" + yjlx +"' "
					+ ", SFZZ ='" + sfzz +"' "
					+ ", ZZCC ='" + zzcc +"' "
					+ ", ZS ='" + zs +"' "
					+ ", DYZZDW ='" + dyzzdw +"' "
					+ ", FBSJ = TO_DATE('" +  fbsj +"','YYYY-MM-DD') "
					+ ", YJLY ='" + yjly +"' "
					+ ", KTLY ='" + ktly +"' "
					+ ", ZZLX ='" + zzlx +"' "
					+ ", XGXKLY ='" + xgxkly +"' "
					+ ", KWLB ='" + kwlb +"' "
					+ ", LWYZ2 ='" + lwyz2 +"' "
					+ ", XKLY ='" + xkly +"' "
					+ ", ATTACH ='" + attach +"' "
					+ ", SFTXZZ ='" + sftxzz +"' "
					+ ", DATAYEAR ='" + datayear +"' "
			        + ", AUTOADD =1 "
					+ ", STATUS =0 "
					+ ", FINALMARK =0 "
					+ ", COPYDATAID ='0:' "
					+ " where ID="+createBoId;
//				System.out.println("updateCreateBoSql="+updateCreateBoSql);
				DBSql.executeUpdate(updateCreateBoSql);
			}
			if(loginId4 != null && !loginId4.equals("")) {
				recordData.put("ID4", loginId4);
				recordData.put("JGH4", jgh4);
				
				queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId4+ "'";
				loginId4BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //����������Ϊ����Ա��bindId
				
				int createBoId = BOInstanceAPI.getInstance().createBOData("BO_XSLWTEMP", recordData, loginId4BindId, createuser);
				if(createBoId == -1) {
					createBoFailureNum = createBoFailureNum + 1;
				} else {
					copyDataIds = copyDataIds + createBoId + ":";
				}
//				System.out.println("Ϊ "+loginId4+" �������ݣ�createBoId="+createBoId+", BINDID="+loginId4BindId);
				String updateCreateBoSql = "update BO_XSLWTEMP set "
					+ "	 LWMC ='" + lwmc +"' "
					+ ", LWYWMC ='" + lwywmc +"' "
					+ ", LWLX ='" + lwlx +"' "
					+ ", WCXS ='" + wcxs +"' "
					+ ", CHARGESF ='" + chargesf +"' "
					+ ", SSYX ='" + ssyx +"' "
					+ ", CHARGENM ='" + chargenm +"' "
					+ ", CHARGEID ='" + loginId +"' "
					+ ", JGH ='" + jgh +"' "
					+ ", CHARGERATE =" + chargerate
					+ ", NAME1 ='" + name1 +"' "
					+ ", ID1 ='" + loginId1 +"' "
					+ ", JGH1 ='" + jgh1 +"' "
					+ ", RATE1 =" + rate1
					+ ", NAME2 ='" + name2 +"' "
					+ ", ID2 ='" + loginId2 +"' "
					+ ", JGH2 ='" + jgh2 +"' "
					+ ", RATE2 =" + rate2
					+ ", NAME3 ='" + name3 +"' "
					+ ", ID3 ='" + loginId3 +"' "
					+ ", JGH3 ='" + jgh3 +"' "
					+ ", RATE3 =" + rate3
					+ ", NAME4 ='" + name4 +"' "
					+ ", ID4 ='" + loginId4 +"' "
					+ ", JGH4 ='" + jgh4 +"' "
					+ ", RATE4 =" + rate4
					+ ", NAME5 ='" + name5 +"' "
					+ ", ID5 ='" + loginId5 +"' "
					+ ", JGH5 ='" + jgh5 +"' "
					+ ", RATE5 =" + rate5
					+ ", GKLX ='" + gklx +"' "
					+ ", FBKW ='" + fbkw +"' "
					+ ", FBFW ='" + fbfw +"' "
					+ ", KWJB ='" + kwjb +"' "
					+ ", KWJB2 ='" + kwjb2 +"' "
					+ ", NIAN ='" + nian +"' "
					+ ", JUAN ='" + juan +"' "
					+ ", QH ='" + qh +"' "
					+ ", YM ='" + ym +"' "
					+ ", LWYZ ='" + lwyz +"' "
					+ ", SFYW ='" + sfyw +"' "
					+ ", LWYYQK ='" + lwyyqk +"' "
					+ ", YJLX ='" + yjlx +"' "
					+ ", SFZZ ='" + sfzz +"' "
					+ ", ZZCC ='" + zzcc +"' "
					+ ", ZS ='" + zs +"' "
					+ ", DYZZDW ='" + dyzzdw +"' "
					+ ", FBSJ = TO_DATE('" +  fbsj +"','YYYY-MM-DD') "
					+ ", YJLY ='" + yjly +"' "
					+ ", KTLY ='" + ktly +"' "
					+ ", ZZLX ='" + zzlx +"' "
					+ ", XGXKLY ='" + xgxkly +"' "
					+ ", KWLB ='" + kwlb +"' "
					+ ", LWYZ2 ='" + lwyz2 +"' "
					+ ", XKLY ='" + xkly +"' "
					+ ", ATTACH ='" + attach +"' "
					+ ", SFTXZZ ='" + sftxzz +"' "
					+ ", DATAYEAR ='" + datayear +"' "
			        + ", AUTOADD =1 "
					+ ", STATUS =0 "
					+ ", FINALMARK =0 "
					+ ", COPYDATAID ='0:' "
					+ " where ID="+createBoId;
//				System.out.println("updateCreateBoSql="+updateCreateBoSql);
				DBSql.executeUpdate(updateCreateBoSql);
			}
			if(loginId5 != null && !loginId5.equals("")) {
				recordData.put("ID5", loginId5);
				recordData.put("JGH5", jgh5);
				
				queryBindIdSql = "SELECT BINDID FROM BO_RSTEMP WHERE LOGINID ='" +loginId5+ "'";
				loginId5BindId = DBSql.getInt(conn, queryBindIdSql, "BINDID"); //����������Ϊ����Ա��bindId
				
				int createBoId = BOInstanceAPI.getInstance().createBOData("BO_XSLWTEMP", recordData, loginId5BindId, createuser);
				if(createBoId == -1) {
					createBoFailureNum = createBoFailureNum + 1;
				} else {
					copyDataIds = copyDataIds + createBoId + ":";
				}
//				System.out.println("Ϊ "+loginId5+" �������ݣ�createBoId="+createBoId+", BINDID="+loginId5BindId);
				String updateCreateBoSql = "update BO_XSLWTEMP set "
					+ "	 LWMC ='" + lwmc +"' "
					+ ", LWYWMC ='" + lwywmc +"' "
					+ ", LWLX ='" + lwlx +"' "
					+ ", WCXS ='" + wcxs +"' "
					+ ", CHARGESF ='" + chargesf +"' "
					+ ", SSYX ='" + ssyx +"' "
					+ ", CHARGENM ='" + chargenm +"' "
					+ ", CHARGEID ='" + loginId +"' "
					+ ", JGH ='" + jgh +"' "
					+ ", CHARGERATE =" + chargerate
					+ ", NAME1 ='" + name1 +"' "
					+ ", ID1 ='" + loginId1 +"' "
					+ ", JGH1 ='" + jgh1 +"' "
					+ ", RATE1 =" + rate1
					+ ", NAME2 ='" + name2 +"' "
					+ ", ID2 ='" + loginId2 +"' "
					+ ", JGH2 ='" + jgh2 +"' "
					+ ", RATE2 =" + rate2
					+ ", NAME3 ='" + name3 +"' "
					+ ", ID3 ='" + loginId3 +"' "
					+ ", JGH3 ='" + jgh3 +"' "
					+ ", RATE3 =" + rate3
					+ ", NAME4 ='" + name4 +"' "
					+ ", ID4 ='" + loginId4 +"' "
					+ ", JGH4 ='" + jgh4 +"' "
					+ ", RATE4 =" + rate4
					+ ", NAME5 ='" + name5 +"' "
					+ ", ID5 ='" + loginId5 +"' "
					+ ", JGH5 ='" + jgh5 +"' "
					+ ", RATE5 =" + rate5
					+ ", GKLX ='" + gklx +"' "
					+ ", FBKW ='" + fbkw +"' "
					+ ", FBFW ='" + fbfw +"' "
					+ ", KWJB ='" + kwjb +"' "
					+ ", KWJB2 ='" + kwjb2 +"' "
					+ ", NIAN ='" + nian +"' "
					+ ", JUAN ='" + juan +"' "
					+ ", QH ='" + qh +"' "
					+ ", YM ='" + ym +"' "
					+ ", LWYZ ='" + lwyz +"' "
					+ ", SFYW ='" + sfyw +"' "
					+ ", LWYYQK ='" + lwyyqk +"' "
					+ ", YJLX ='" + yjlx +"' "
					+ ", SFZZ ='" + sfzz +"' "
					+ ", ZZCC ='" + zzcc +"' "
					+ ", ZS ='" + zs +"' "
					+ ", DYZZDW ='" + dyzzdw +"' "
					+ ", FBSJ = TO_DATE('" +  fbsj +"','YYYY-MM-DD') "
					+ ", YJLY ='" + yjly +"' "
					+ ", KTLY ='" + ktly +"' "
					+ ", ZZLX ='" + zzlx +"' "
					+ ", XGXKLY ='" + xgxkly +"' "
					+ ", KWLB ='" + kwlb +"' "
					+ ", LWYZ2 ='" + lwyz2 +"' "
					+ ", XKLY ='" + xkly +"' "
					+ ", ATTACH ='" + attach +"' "
					+ ", SFTXZZ ='" + sftxzz +"' "
					+ ", DATAYEAR ='" + datayear +"' "
			        + ", AUTOADD =1 "
					+ ", STATUS =0 "
					+ ", FINALMARK =0 "
					+ ", COPYDATAID ='0:' "
					+ " where ID="+createBoId;
//				System.out.println("updateCreateBoSql="+updateCreateBoSql);
				DBSql.executeUpdate(updateCreateBoSql);
			}
			
			String updateCopyDataIdSql = "update BO_XSLWTEMP set copyDataId ='" +copyDataIds +"' where ID="+mainDataId;
			DBSql.executeUpdate(updateCopyDataIdSql);
			
			
			String tip = "���ݱ���ɹ���";
			if(createBoFailureNum != 0) {
				tip = tip + "ע�⣡�����˺Ͳ�����֮�����ݹ���δ��ʼ���ɹ������� " +createBoFailureNum+ " �������˱���ʧ�ܡ���";
				MessageQueue.getInstance().putMessage(getUserContext().getUID(), tip, true);
				return false;
			}
			
		}catch(Exception e){
			e.printStackTrace(System.err);
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "���棬��Ϣ����ʧ�ܣ�", true);
			return false;
		}finally{
			DBSql.close(conn, ps, rs);
		}
		
		
		return true;
	}
	
}
