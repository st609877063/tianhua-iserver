package com.res.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.res.bean.ResItems;
import com.res.bean.ResMenus;
import com.res.server.ConnectionManager2;

public class ResMenuManager{

	private static final Logger logger = Logger.getLogger(ResMenuManager.class);
	
	public List<ResMenus> getResMenusByDateType(String date, int type) {
		List<ResMenus> rtnList = new ArrayList<ResMenus>();
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ResMenus resMenus = null;
		String sql = "select i.*, m.pk_id, m.m_adduser, m.m_createtime, m.m_date, m.m_memo, m.m_money, m.m_type " +
				" from res_menus m left join res_items i on m.i_id=i.i_id where 1=1 ";
		if(date != null && !date.equals("")) {
			sql = sql + " and m.m_date='"+date+"'";
		}
		if(type != -1) {
			sql = sql + " and m.m_type="+type;
		}
		sql = sql + " order by m.m_date desc, m.m_type asc";
		
		try {
			conn = ConnectionManager2.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				resMenus = new ResMenus();
				resMenus.setPkId(resultSet.getInt("pk_id"));
				resMenus.setMenuMemo(resultSet.getString("m_memo"));
				resMenus.setMenuMoney(resultSet.getString("m_money"));
				resMenus.setMenuDate(resultSet.getString("m_date"));
				resMenus.setMenuType(resultSet.getInt("m_type"));
				
				resMenus.setItemId(resultSet.getInt("i_id"));
				resMenus.setItemNo(resultSet.getString("i_no"));
				resMenus.setItemName(resultSet.getString("i_name"));
				resMenus.setItemImg(resultSet.getString("i_img"));
				resMenus.setItemImgpath(resultSet.getString("i_imgpath"));
				resMenus.setItemMoney(resultSet.getString("i_money"));
				resMenus.setItemDesc(resultSet.getString("i_desc"));
				resMenus.setItemMemo(resultSet.getString("i_memo"));
				resMenus.setItemType(resultSet.getInt("i_type"));
				
				rtnList.add(resMenus);
			}
		} catch (Exception e) {
			logger.error("getResMenusByDateType error", e);
		}
		finally {
			ConnectionManager2.close(conn, statement, resultSet);
		}
		return rtnList;
	}
	
	
	public List<ResMenus> getDistinctMenusDateType(String date, int type) {
		List<ResMenus> rtnList = new ArrayList<ResMenus>();
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ResMenus resMenus = null;
		String sql = "select distinct(m_date), m_type from res_menus where 1=1 ";
		if(date != null && !date.equals("")) {
			sql = sql + " and m_date='"+date+"'";
		}
		if(type != -1) {
			sql = sql + " and m_type="+type;
		}
		
		try {
			conn = ConnectionManager2.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				resMenus = new ResMenus();
				resMenus.setMenuDate(resultSet.getString("m_date"));
				resMenus.setMenuType(resultSet.getInt("m_type"));
				
				rtnList.add(resMenus);
			}
		} catch (Exception e) {
			logger.error("getDistinctMenusDateType error", e);
		}
		finally {
			ConnectionManager2.close(conn, statement, resultSet);
		}
		return rtnList;
	}
	
	
	public void saveResMenu(List<ResMenus> voList) {
		String sql = ""; 
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;

		try {
			conn = ConnectionManager2.getConnection();

			sql = " insert into res_menus(i_id, i_no, m_memo, m_money, m_date, m_type, m_createtime, m_adduser)  " +
			" values(?, ?, ?, ?, ?, ?, ?, ?)";
			
			pst = conn.prepareStatement(sql);
			
			for(int i=0; voList!=null&&i<voList.size(); i++) {
				pst.setInt(1, voList.get(i).getItemId());
				pst.setString(2, voList.get(i).getItemNo());
				pst.setString(3, voList.get(i).getMenuMemo());
				pst.setString(4, voList.get(i).getMenuMoney());
				pst.setString(5, voList.get(i).getMenuDate());
				pst.setInt(6, voList.get(i).getMenuType());
				pst.setLong(7, voList.get(i).getMenuCreatetime());
				pst.setInt(8, voList.get(i).getMenuAdduser());
				
				pst.addBatch();
			}
			
			int[] re = pst.executeBatch();
			
		} catch (Exception e) {
			logger.error("saveResMenu() error", e);
		} finally {
			ConnectionManager2.close(conn, pst, resultSet);
		}
	}
	
	
	public int deleteResMuenByPkId(int pkId) {
		int re = 0;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		String sql = "delete from res_menus where pk_id="+pkId; 
		
		try {
			conn = ConnectionManager2.getConnection();
			pst = conn.prepareStatement(sql);
			re = pst.executeUpdate();
		} catch (Exception e) {
			logger.error("deleteResMuenByPkId() error", e);
		} finally {
			ConnectionManager2.close(conn, pst, resultSet);
		}
		return re;
	}
	
	public int updateMuenItemMoneyByPkId(int pkId, String money) {
		int re = 0;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		String sql = "update res_menus set m_money=? where pk_id=?"; 
		
		try {
			conn = ConnectionManager2.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, money);
			pst.setInt(2, pkId);
			re = pst.executeUpdate();
		} catch (Exception e) {
			logger.error("updateMuenItemMoneyByPkId() error", e);
		} finally {
			ConnectionManager2.close(conn, pst, resultSet);
		}
		return re;
	}
	
}
