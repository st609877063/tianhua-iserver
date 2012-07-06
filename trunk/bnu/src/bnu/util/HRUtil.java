package bnu.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.actionsoft.application.server.conf.AWFConfig;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.awf.util.Sequence;
import com.actionsoft.awf.util.SequenceException;
import com.actionsoft.plugs.workmanage.wm.model.AttacheModel;

public class HRUtil {
	/**
	 * ��������
	 * @param csrq ��������
	 * @return
	 */
	public static int getNianling(String csrq) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int nianling = 0;//����
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			String sql = "select trunc(months_between(sysdate,to_date('"+csrq+"','yyyy-mm-dd'))/12,0) c from bo_sap_pa0002";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				nianling = rs.getInt("c");//����
			}
			ps.close();
			
		}catch(Exception e) {
			e.printStackTrace(System.err);
			return -1;
		}finally {
			DBSql.close(conn, ps, rs);
		}
		return nianling;
	}
	/**
	 * ��ó�������
	 * @param csgjdm �������Ҵ���
	 * @return
	 */
	public static String getCsgj(String csgjdm) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String csgj = null;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			String sql = "select name from bo_gyl_baseinfo_s where no='"+csgjdm+"' and typeno='GJ'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				csgj = rs.getString("name");//��������
			}
			ps.close();
			
		}catch(Exception e) {
			e.printStackTrace(System.err);
			return null;
		}finally {
			DBSql.close(conn, ps, rs);
		}
		return csgj;
	}
	/**
	 * ��ó���ʡ��
	 * @param cssfdm ����ʡ�ݴ���
	 * @return
	 */
	public static String getCssf(String csgjdm,String cssfdm) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String cssf = null;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			String sql = "select name from bo_gyl_baseinfo_s where no='"+csgjdm+cssfdm+"' and typeno='CSD'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				cssf = rs.getString("name");//����ʡ��
			}
			ps.close();
			
		}catch(Exception e) {
			e.printStackTrace(System.err);
			return null;
		}finally {
			DBSql.close(conn, ps, rs);
		}
		return cssf;
	}
	/**
	 * ��ù�������
	 * @param guojidm ��������
	 * @return
	 */
	public static String getGuoji(String guojidm) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String guoji = null;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			String sql = "select name from bo_gyl_baseinfo_s where no='"+guojidm+"' and typeno='GJ'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				guoji = rs.getString("name");//����
			}
			ps.close();
			
		}catch(Exception e) {
			e.printStackTrace(System.err);
			return null;
		}finally {
			DBSql.close(conn, ps, rs);
		}
		return guoji;
	}
	/**
	 * ����ڽ�����
	 * @param zjdm �ڽ̴���
	 * @return
	 */
	public static String getZjmc(String zjdm) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String zjmc = null;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			String sql = "select name from bo_gyl_baseinfo_s where no='"+zjdm+"' and typeno='ZJ'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				zjmc = rs.getString("name");//�ڽ�
			}
			ps.close();
			
		}catch(Exception e) {
			e.printStackTrace(System.err);
			return null;
		}finally {
			DBSql.close(conn, ps, rs);
		}
		return zjmc;
	}
	/**
	 * ��û���״��
	 * @param hyzkdm ����״������
	 * @return
	 */
	public static String getHyzk(String hyzkdm) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String hyzk = null;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			String sql = "select name from bo_gyl_baseinfo_s where no='"+hyzkdm+"' and typeno='HYZK'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				hyzkdm = rs.getString("name");//����״��
			}
			ps.close();
			
		}catch(Exception e) {
			e.printStackTrace(System.err);
			return null;
		}finally {
			DBSql.close(conn, ps, rs);
		}
		return hyzkdm;
	}
	/**
	 * ��û�������
	 * @param hjxzdm �������ʴ���
	 * @return
	 */
	public static String getHjxz(String hjxzdm) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String hjxz = null;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			String sql = "select name from bo_gyl_baseinfo_s where no='"+hjxzdm+"' and typeno='HJXZ'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				hjxz = rs.getString("name");//��������
			}
			ps.close();
			
		}catch(Exception e) {
			e.printStackTrace(System.err);
			return null;
		}finally {
			DBSql.close(conn, ps, rs);
		}
		return hjxz;
	}
	/**
	 * ��û�����������
	 * @param hjsdxzdm �������ʴ���
	 * @return
	 */
	public static String getHjsdxz(String hjsdxzdm) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String hjsdxz = null;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			String sql = "select name from bo_gyl_baseinfo_s where no='"+hjsdxzdm+"' and typeno='HJSDXZ'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				hjsdxz = rs.getString("name");//�ڽ�
			}
			ps.close();
			
		}catch(Exception e) {
			e.printStackTrace(System.err);
			return null;
		}finally {
			DBSql.close(conn, ps, rs);
		}
		return hjsdxz;
	}
	/**
	 * ���Ա������
	 * @param ygjgdm Ա���������
	 * @return
	 */
	public static String getYgjg(String ygjgdm) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String ygjg = null;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			String sql = "select name from bo_gyl_baseinfo_s where no='"+ygjgdm+"' and typeno='YGJG'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				ygjg = rs.getString("name");//�ڽ�
			}
			ps.close();
			
		}catch(Exception e) {
			e.printStackTrace(System.err);
			return null;
		}finally {
			DBSql.close(conn, ps, rs);
		}
		return ygjg;
	}
	/**
	 * aws �ļ�server ���õĺ��� �Զ����ļ����Ʋ��뵽 ����������ļ������б���
	 * 
	 * @param wmAttacheId
	 *            �������� ģ�������id
	 * @param fn
	 *            ��������
	 * @return
	 */
	public boolean appendFile(String awsuid,String fn) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		String sapuid = null;
		int awsID = 0;//orguser��ID�ֶ�
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			String sql = "select * from orguser where userid='"+awsuid+"'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				sapuid = rs.getString("extend1");//SAPԱ���ʺ�
				awsID = rs.getInt("ID");//orguser��ID�ֶ�
			}else {
				sapuid = "";
			}
			ps.close();
			
			//ÿ���ϴ���Ƭʱ����BO_HR_ARCHIVES�������������Ƿ��Ѿ��ϴ�����Ƭ������Ѿ��ϴ��������޸���Ƭ�ļ����ƣ���������һ����¼
			sql = "select * from BO_HR_ARCHIVES where awsuid='"+awsuid+"' and status='1'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			//����Ѿ��ϴ��������޸���Ƭ�ļ�����
			if(rs.next()) {
				int id = rs.getInt("ID");//BO��ID�ֶ�
				sql = "update BO_HR_ARCHIVES set picfile=?,DJRQ=? where id=?";
				ps1 = conn.prepareStatement(sql);
				ps1.setString(1, fn);
				ps1.setTimestamp(2,new java.sql.Timestamp(new Date().getTime()));//�Ǽ�����
				ps1.setInt(3,id);
				int r = ps1.executeUpdate();
				if(r < 0) {
					return false;
				}
				ps1.close();
			}
			//��������һ����¼
			else {
				int id = Sequence.getSequence(Sequence.SEQUENCE_USER_WORKFLOW_REPORT);
				sql = "insert into BO_HR_ARCHIVES(ID,ORGNO,BINDID,CREATEDATE,CREATEUSER,UPDATEDATE,UPDATEUSER,WORKFLOWID,WORKFLOWSTEPID,ISEND,AWSUID,SAPUID,PICFILE,DJRQ,STATUS) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				ps1 = conn.prepareStatement(sql);
				ps1.setInt(1, id);//ID
				ps1.setString(2,"1");//ORGNO
				ps1.setInt(3, 1);//BINDID
				ps1.setTimestamp(4,new java.sql.Timestamp(new Date().getTime()));//CREATEDATE
				ps1.setString(5,awsuid);//CREATEUSER
				ps1.setTimestamp(6,new java.sql.Timestamp(new Date().getTime()));//UPDATEDATE
				ps1.setString(7,awsuid);//UPDATEUSER
				ps1.setInt(8,1);//WORKFLOWID
				ps1.setInt(9,1);//WORKFLOWSTEPID
				ps1.setInt(10,0);//ISEND
				ps1.setString(11, awsuid==null?"":awsuid);//AWSԱ���ʺ�
				ps1.setString(12, sapuid==null?"":sapuid);//SAPԱ���ʺ�
				ps1.setString(13, fn==null?"":fn);//��Ƭ�ļ�
				ps1.setTimestamp(14,new java.sql.Timestamp(new Date().getTime()));//�Ǽ�����
				ps1.setString(15, "1");//��Ч��־
				
				int r = ps1.executeUpdate();
				if(r < 0) {
					return false;
				}
			}
			ps.close();
			rs.close();
			ps1.close();
		}catch(Exception e) {
			e.printStackTrace(System.err); 
			return false;
		}finally {
			DBSql.close(ps1, null);
			DBSql.close(conn, ps, rs);
		}
		return true;
	}
}
