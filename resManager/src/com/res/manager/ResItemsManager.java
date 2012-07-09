package com.res.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.res.bean.ResItems;
import com.res.server.ConnectionManager2;

public class ResItemsManager{

	private static final Logger logger = Logger.getLogger(ResItemsManager.class);
	
	public List<ResItems> getResItems(String srhItemNo) {
		List<ResItems> rtnList = new ArrayList<ResItems>();
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ResItems resItems = null;
		String sql = " select * from res_items where i_no like '%"+srhItemNo+"%'";
		
		try {
			conn = ConnectionManager2.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				resItems = new ResItems();
				resItems.setItemId(resultSet.getInt("i_id"));
				resItems.setItemNo(resultSet.getString("i_no"));
				resItems.setItemName(resultSet.getString("i_name"));
				resItems.setItemImg(resultSet.getString("i_img"));
				resItems.setItemMoney(resultSet.getString("i_money"));
				resItems.setItemDesc(resultSet.getString("i_desc"));
				resItems.setItemMemo(resultSet.getString("i_memo"));
				resItems.setItemType(resultSet.getInt("i_type"));
				resItems.setItemCreatetime(resultSet.getInt("i_createtime"));
				resItems.setItemAdduser(resultSet.getInt("i_adduser"));
				
				rtnList.add(resItems);
			}
		} catch (Exception e) {
			logger.error("getResItems() error", e);
		}
		finally {
			ConnectionManager2.close(conn, statement, resultSet);
		}
		return rtnList;
	}
	
	
	/**
	 * 更新设置
	 */
	public int updateResItem(ResItems vo, String type) {
		String sql = ""; 
		int re = 0;
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ResItems resItems = null;

		if(type.equals("insert")) {
			sql = " insert into twitter_open_live_content(content_id, appkey)  values(?, ?)";
		} else if (type.equals("update")) {
			//不需要
		}

		try {
			conn = ConnectionManager2.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			
		} catch (Exception e) {
			logger.error("updateResItem() error", e);
		} finally {
			ConnectionManager2.close(conn, statement, resultSet);
		}
		return re;
	}
	
}
