package bnu.importBiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;

import com.actionsoft.awf.organization.control.MessageQueue;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.loader.core.WorkFlowStepRTClassA;
import com.actionsoft.sdk.local.level0.BOInstanceAPI;
import com.actionsoft.sdk.local.level0.WorkflowInstanceAPI;

public class InitDataAfterExcel2 extends WorkFlowStepRTClassA {
	
	public InitDataAfterExcel2(UserContext uc){
		super(uc);
		super.setDescription("��ʼ��������Ϣ������Excel��Ϊÿ�����ݴ���bindid.===ֱ�ӵ�������BO_RSTEMP");
		super.setVersion("V1.0");
		super.setProvider("maxwell");
	}

	public boolean execute() {
		System.out.println(".....................��ʼ��������Ϣ(ֱ�ӵ���BO_RSTEMP)begin..............");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int id = 0;
		String ZGH = "";          // �̹���
		String LOGINID = "";
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
		
		try{
			String sql = "select * from BO_RSTEMP";
			conn = DBSql.open();
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				id = rs.getInt("ID");
				
				ZGH = rs.getString("ZGH")==null?"":rs.getString("ZGH").trim(); //�̹���
				LOGINID = rs.getString("LOGINID")==null?"":rs.getString("LOGINID").trim();
				XM = rs.getString("XM")==null?"":rs.getString("XM").trim(); //����
				XB = rs.getString("XB")==null?"":rs.getString("XB").trim(); //�Ա�
				CSNY = rs.getString("CSNY")==null?"":rs.getString("CSNY").trim(); //��������
				MZ = rs.getString("MZ")==null?"":rs.getString("MZ").trim(); //����
				JG = rs.getString("JG")==null?"":rs.getString("JG").trim(); //����
				JGSHI = rs.getString("JGSHI")==null?"":rs.getString("JGSHI").trim(); //���ᣨ�У�
				JGOTHER = rs.getString("JGOTHER")==null?"":rs.getString("JGOTHER").trim();
				CJGZSJ = rs.getString("CJGZSJ")==null?"":rs.getString("CJGZSJ").trim(); //�μӹ���ʱ��
				RXGZSJ = rs.getString("RXGZSJ")==null?"":rs.getString("RXGZSJ").trim(); //��Уʱ��
				BMMC = rs.getString("BMMC")==null?"":rs.getString("BMMC").trim(); //���ڵ�λ
				JSZW = rs.getString("JSZW")==null?"":rs.getString("JSZW").trim(); //��ְ��
				JSZW2 = rs.getString("JSZW2")==null?"":rs.getString("JSZW2").trim(); //��ְ��2
				JSZWRMSJ = rs.getString("JSZWRMSJ")==null?"":rs.getString("JSZWRMSJ").trim(); //��ְʱ��
				GWLB = rs.getString("GWLB")==null?"":rs.getString("GWLB").trim(); //��λ���
				GWLB2 = rs.getString("GWLB2")==null?"":rs.getString("GWLB2").trim(); //��λ���2
				SSXK = rs.getString("SSXK")==null?"":rs.getString("SSXK").trim(); //ѧ�Ʒ���
				SSXK2 = rs.getString("SSXK2")==null?"":rs.getString("SSXK2").trim(); //ѧ�Ʒ���2 
				PGJB = rs.getString("PGJB")==null?"":rs.getString("PGJB").trim(); //Ƹλ�ȼ�
				DSLX = rs.getString("DSLX")==null?"":rs.getString("DSLX").trim(); //��ʦ����
				YHZZC = rs.getString("YHZZC")==null?"":rs.getString("YHZZC").trim(); //ѧ��ר�� 
				GJ = rs.getString("GJ")==null?"":rs.getString("GJ").trim(); //����
				GJ2 = rs.getString("GJ2")==null?"":rs.getString("GJ2").trim(); //����2
				ZGQK = rs.getString("ZGQK")==null?"":rs.getString("ZGQK").trim(); //�ڸ���� 
				ZGQK2 = rs.getString("ZGQK2")==null?"":rs.getString("ZGQK2").trim(); //�ڸ�λ��2 
				ZJLX = rs.getString("ZJLX")==null?"":rs.getString("ZJLX").trim(); //֤������
				ZJLX2 = rs.getString("ZJLX2")==null?"":rs.getString("ZJLX2").trim(); //֤������2
				SFZHM = rs.getString("SFZHM")==null?"":rs.getString("SFZHM").trim(); //֤������ 
				HKSZDCS = rs.getString("HKSZDCS")==null?"":rs.getString("HKSZDCS").trim(); //�������ڵ�
				HYZK = rs.getString("HYZK")==null?"":rs.getString("HYZK").trim(); //����״�� 
				XZZ = rs.getString("XZZ")==null?"":rs.getString("XZZ").trim(); //��ͥסַ
				SJ = rs.getString("SJ")==null?"":rs.getString("SJ").trim(); //�ƶ��绰
				BGSDH = rs.getString("BGSDH")==null?"":rs.getString("BGSDH").trim(); //�칫�绰
				EMAIL = rs.getString("EMAIL")==null?"":rs.getString("EMAIL").trim(); //��������
				ZGXW = rs.getString("ZGXW")==null?"":rs.getString("ZGXW").trim(); //����ѧλ
				ZGXW2 = rs.getString("ZGXW2")==null?"":rs.getString("ZGXW2").trim(); //���ѧλ2 
				ZGXW3 = rs.getString("ZGXW3")==null?"":rs.getString("ZGXW3").trim(); //���ѧλ3
				ZGXWHDSJ = rs.getString("ZGXWHDSJ")==null?"":rs.getString("ZGXWHDSJ").trim(); //��ҵʱ��
				WYSP = rs.getString("WYSP")==null?"":rs.getString("WYSP").trim(); //����ˮƽ
				WYYZ = rs.getString("WYYZ")==null?"":rs.getString("WYYZ").trim(); //��������
				WYYZ2 = rs.getString("WYYZ2")==null?"":rs.getString("WYYZ2").trim(); //��������2 
				ZGXL = rs.getString("ZGXL")==null?"":rs.getString("ZGXL").trim(); //���ѧ��
				LXGL = rs.getInt("LXGL"); //�������䣨�꣩
				ZZMM = rs.getString("ZZMM")==null?"":rs.getString("ZZMM").trim(); //������ò
				RDSJ = rs.getString("RDSJ")==null?"":rs.getString("RDSJ").trim(); //�뵳ʱ�� 
				DZZ = rs.getString("DZZ")==null?"":rs.getString("DZZ").trim(); //����֧/�ֵ�ί/..
				DZZ2 = rs.getString("DZZ2")==null?"":rs.getString("DZZ2").trim(); //��һ������֯
				DZB = rs.getString("DZB")==null?"":rs.getString("DZB").trim(); //��֧�� 
				DFJS = rs.getInt("DFJS"); //���ѻ���
				DNZW = rs.getString("DNZW")==null?"":rs.getString("DNZW").trim(); //����ְ�� 
				XZZW = rs.getString("XZZW")==null?"":rs.getString("XZZW").trim(); //ְ������
				XZZWJB = rs.getString("XZZWJB")==null?"":rs.getString("XZZWJB").trim(); //ְ�񼶱� 
				
				int boInstanceId = WorkflowInstanceAPI.getInstance().createProcessInstance("007b23151fb9069a05939e4ad301bcd6", "admin", "������Ϣ��ʼ��");
				System.out.println("�������ݣ�BINDID = "+boInstanceId+", ID="+id);
				String updateInstanceSql = "update BO_RSTEMP set BINDID =" +boInstanceId + " where ID="+id;
				DBSql.executeUpdate(updateInstanceSql);
				
			}
			
		}catch(Exception e){
			e.printStackTrace(System.err);
			MessageQueue.getInstance().putMessage(getUserContext().getUID(), "���棬������Ϣ��ʼ��ʧ�ܣ������µ���Excel", true);
			return false;
		}finally{
			DBSql.close(conn, ps, rs);
		}
		
		return true;
	}
	
}
