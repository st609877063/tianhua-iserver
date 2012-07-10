package com.res.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.res.bean.ResMenus;
import com.res.server.ConnectionManager2;

public class ResMenuManager{

	private static final Logger logger = Logger.getLogger(ResMenuManager.class);
	
	public List<ResMenus> getResMenusByDate(String date) {
		List<ResMenus> rtnList = new ArrayList<ResMenus>();
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ResMenus resMenus = null;
		String sql = "select * from res_menus m left join res_items i on m.i_id=i.i_id where 1=1 ";
		if(date != null && !date.equals("")) {
			sql = sql + " and m.m_date='"+date+"'";
		}
		sql = sql + " order by m.m_date desc";
		
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
			logger.error("error", e);
		}
		finally {
			ConnectionManager2.close(conn, statement, resultSet);
		}
		return rtnList;
	}
}
