package bnu.web;

import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;

import java.sql.*;

public class BnuSelectAjaxWeb {

	private UserContext me;

	public BnuSelectAjaxWeb(UserContext me) {
		this.me = me;
	}

	/*
	 * ͨ��Ŀ���������ƻ�ȡ����Ŀ���µ�����Ŀ������
	 */
	public String getResult(String input, String type) {
		String returnVal = "";
		if (input != null && !input.trim().equals("")) {
			input = input.trim();
			Connection conn = null;
			Statement stmt = null;
			ResultSet rest = null;

//			System.out.println("BnuSelectAjaxWeb@@@@@@@@@@@@@@@@@@@@@sql=\n"+sql);
			try {
				conn = DBSql.open();
				stmt = conn.createStatement();

				String sql = "";
				if(type.equalsIgnoreCase("JG")) {
					if(input.equals("����")) {
						returnVal = "�Ϸ���|�ߺ���|������|������|��ɽ��|������|ͭ����|������|��ɽ��|������|������|������|������|������|������|������|������";
					} else if(input.equals("����")) {
						returnVal = "������|������|������|������|������|��̨��|ʯ��ɽ��|������|��ͷ����|��ɽ��|ͨ����|˳����|��ƽ��|������|������|ƽ����|������|������";
					} else if(input.equals("����")) {
						returnVal = "������|������|������|��ɿ���|������|ɳƺ����|��������|�ϰ���|������|��ʢ��|˫����|�山��|������|ǭ����|������|������|�ϴ���|������|�ϴ���|�뽭��|������|ͭ����|������|�ٲ���|�ɽ��|��ƽ��|�ǿ���|�ᶼ��|�潭��|��¡��|����|����|������|�����|��ɽ��|��Ϫ��|ʯ��������������|��ɽ����������������|��������������������|��ˮ����������������";
					} else if(input.equals("����")) {
						returnVal = "������|������|������|������|Ȫ����|������|��ƽ��|������|������";
					} else if(input.equals("����")) {
						returnVal = "������|��������|�����|������|��ˮ��|������|��Ҵ��|ƽ����|��Ȫ��|������|������|¤����|���Ļ���������|���ϲ���������";
					} else if(input.equals("�㶫")) {
						returnVal = "������|�ع���|������|�麣��|��ͷ��|��ɽ��|������|տ����|ï����|������|������|÷����|��β��|��Դ��|������|��Զ��|��ݸ��|��ɽ��|������|������|�Ƹ���";
					} else if(input.equals("����")) {
						returnVal = "������|������|������|������|������|���Ǹ���|������|�����|������|��ɫ��|������|�ӳ���|������|������";
					} else if(input.equals("����")) {
						returnVal = "������|����ˮ��|������|��˳��|ͭ�ʵ���|ǭ���ϲ���������������|�Ͻڵ���|ǭ�������嶱��������|ǭ�ϲ���������������";
					} else if(input.equals("����")) {
						returnVal = "������|������|��ָɽ��|����|������|�Ĳ���|������|������|������|�Ͳ���|������|�ٸ���|��ɳ����������|��������������|�ֶ�����������|��ˮ����������|��ͤ��������������|������������������";
					} else if(input.equals("�ӱ�")) {
						returnVal = "ʯ��ׯ��|��ɽ��|�ػʵ���|������|��̨��|������|�żҿ���|�е���|������|�ȷ���|��ˮ��";
					} else if(input.equals("������")) {
						returnVal = "��������|���������|������|�׸���|˫Ѽɽ��|������|������|��ľ˹��|��̨����|ĵ������|�ں���|�绯��|���˰������";
					} else if(input.equals("����")) {
						returnVal = "֣����|������|������|ƽ��ɽ��|������|�ױ���|������|������|��Դ��|�����|�����|�����|����Ͽ��|������|������|������|�ܿ���|פ�����";
					} else if(input.equals("���")) {
						returnVal = "������|������|����|����|�ͼ�����|��ˮ����|��������|�ƴ�����|������|������|������|ɳ����|������|������|����|Ԫ����|������|�뵺��";
					} else if(input.equals("����")) {
						returnVal = "�人��|��ʯ��|ʮ����|�˲���|�差��|������|������|Т����|������|�Ƹ���|������|������|��ʩ����������������|������|Ǳ����|������|��ũ������";
					} else if(input.equals("����")) {
						returnVal = "��ɳ��|������|��̶��|������|������|������|������|�żҽ���|������|������|������|������|¦����|��������������������";
					} else if(input.equals("����")) {
						returnVal = "�Ͼ���|������|������|������|������|��ͨ��|���Ƹ���|������|�γ���|������|����|̩����|��Ǩ��";
					} else if(input.equals("����")) {
						returnVal = "�ϲ���|��������|Ƽ����|�Ž���|������|ӥ̶��|������|������|�˴���|������|������";
					} else if(input.equals("����")) {
						returnVal = "������|������|��ƽ��|��Դ��|ͨ����|��ɽ��|��ԭ��|�׳���|�ӱ߳�����������";
					} else if(input.equals("����")) {
						returnVal = "������|������|��ɽ��|��˳��|��Ϫ��|������|������|Ӫ����|������|������|�̽���|������|������|��«����";
					} else if(input.equals("����")) {
						returnVal = "����";
					} else if(input.equals("���ɹ�")) {
						returnVal = "���ͺ�����|��ͷ��|�ں���|�����|ͨ����|������˹��|���ױ�����|�����׶���|�����첼��|�˰���|���ֹ�����|��������";
					} else if(input.equals("����")) {
						returnVal = "������|ʯ��ɽ��|������|��ԭ��|������";
					} else if(input.equals("�ຣ")) {
						returnVal = "������|��������|��������������|���ϲ���������|���ϲ���������|�������������|��������������|�����ɹ������������";
					} else if(input.equals("ɽ��")) {
						returnVal = "������|�ൺ��|�Ͳ���|��ׯ��|��Ӫ��|��̨��|Ϋ����|������|̩����|������|������|������|������|������|�ĳ���|������|������";
					} else if(input.equals("�Ϻ�")) {
						returnVal = "������|¬����|�����|������|������|������|բ����|�����|������|������|��ɽ��|�ζ���|�ֶ�����|��ɽ��|�ɽ���|������|�ϻ���|������|������";
					} else if(input.equals("ɽ��")) {
						returnVal = "̫ԭ��|��ͬ��|��Ȫ��|������|������|˷����|������|�˳���|������|�ٷ���|������";
					} else if(input.equals("����")) {
						returnVal = "������|ͭ����|������|������|μ����|�Ӱ���|������|������|������|������";
					} else if(input.equals("�Ĵ�")) {
						returnVal = "�ɶ���|�Թ���|��֦����|������|������|������|��Ԫ��|������|�ڽ���|��ɽ��|�ϳ���|üɽ��|�˱���|�㰲��|������|�Ű���|������|������|���Ӳ���Ǽ��������|���β���������|��ɽ����������";
					} else if(input.equals("���")) {
						returnVal = "��ƽ��|�Ӷ���|������|�Ͽ���|�ӱ���|������|������|������|�����|������|������|������|������|������|������|������|������|����";
					} else if(input.equals("̨��")) {
						returnVal = "̨����|������|��¡��|̨����|̨����|������|������";
					} else if(input.equals("�½�")) {
						returnVal = "��³ľ����|����������|��³������|���ܵ���|��������������|���������ɹ�������|���������ɹ�������|�����յ���|�������տ¶�����������|��ʲ����|�������|���������������|���ǵ���|����̩����|ʯ������|��������|ͼľ�����|�������";
					} else if(input.equals("����")) {
						returnVal = "������|��������|ɽ�ϵ���|�տ������|��������|�������|��֥����";
					} else if(input.equals("����")) {
						returnVal = "������|������|��Ϫ��|��ɽ��|��ͨ��|������|˼é��|�ٲ���|��������������|��ӹ���������������|��ɽ׳������������|��˫���ɴ���������|�������������|�º���徰����������|ŭ��������������|�������������";
					} else if(input.equals("�㽭")) {
						returnVal = "������|������|������|������|������|������|����|������|��ɽ��|̨����|��ˮ��";
					}
				} 
				else if(type.equalsIgnoreCase("SSXK")) {
					sql = " SELECT OPTIONNM FROM BO_SELECTINFO " +
						" WHERE PARENTCD = ( " +
						" select REMARK from BO_SELECTINFO where tablecd='BO_RSTEMP' AND COLUMNCD='"+type+"' AND OPTIONNM='"+input+"' " +
						" ) order by REMARK";

					rest = stmt.executeQuery(sql);
					while (rest.next()) {
						String op = rest.getString("OPTIONNM") == null ? "" : rest.getString("OPTIONNM").trim();
						returnVal += op + "|";
					}
				}
				
				else {
					sql = " SELECT OPTIONNM FROM BO_SELECTINFO " +
						" WHERE PARENTCD = ( " +
						" select id from BO_SELECTINFO where tablecd='BO_RSTEMP' AND COLUMNCD='"+type+"' AND OPTIONNM='"+input+"' " +
						" )  order by REMARK";

					rest = stmt.executeQuery(sql);
					while (rest.next()) {
						String op = rest.getString("OPTIONNM") == null ? "" : rest.getString("OPTIONNM").trim();
						returnVal += op + "|";
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				DBSql.close(conn, stmt, rest);
			}
		}
//		System.out.println("BnuSelectAjaxWeb@@@@@@@@@@@@@@@@@@@@@returnVal=\n"+returnVal);
		return returnVal;
	}

}
