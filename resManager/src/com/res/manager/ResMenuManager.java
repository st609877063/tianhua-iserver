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
		String sql = "select * from res_menus m, res_items i, res_items_img ii where 1=1";
		
		try {
			conn = ConnectionManager2.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				resMenus = new ResMenus();
				
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
