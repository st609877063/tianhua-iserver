package com.res.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
				resItems.setItemImgpath(resultSet.getString("i_imgpath"));
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
	
	public List<ResItems> getResItemsByItemType(int type) {
		List<ResItems> rtnList = new ArrayList<ResItems>();
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ResItems resItems = null;
		String sql = " select * from res_items where i_type ="+type;
		
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
				resItems.setItemImgpath(resultSet.getString("i_imgpath"));
				resItems.setItemMoney(resultSet.getString("i_money"));
				resItems.setItemDesc(resultSet.getString("i_desc"));
				resItems.setItemMemo(resultSet.getString("i_memo"));
				resItems.setItemType(resultSet.getInt("i_type"));
				resItems.setItemCreatetime(resultSet.getInt("i_createtime"));
				resItems.setItemAdduser(resultSet.getInt("i_adduser"));
				
				rtnList.add(resItems);
			}
		} catch (Exception e) {
			logger.error("getResItemsByItemType() error", e);
		}
		finally {
			ConnectionManager2.close(conn, statement, resultSet);
		}
		return rtnList;
	}
	
	
	public ResItems findResItemsByItemNo(String itemNo) {
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ResItems resItems = null;
		String sql = " select * from res_items where i_no = '"+itemNo+"'";
		
		try {
			conn = ConnectionManager2.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				resItems = new ResItems();
				resItems.setItemId(resultSet.getInt("i_id"));
				resItems.setItemNo(resultSet.getString("i_no"));
				resItems.setItemName(resultSet.getString("i_name"));
				resItems.setItemImg(resultSet.getString("i_img"));
				resItems.setItemImgpath(resultSet.getString("i_imgpath"));
				resItems.setItemMoney(resultSet.getString("i_money"));
				resItems.setItemDesc(resultSet.getString("i_desc"));
				resItems.setItemMemo(resultSet.getString("i_memo"));
				resItems.setItemType(resultSet.getInt("i_type"));
				resItems.setItemCreatetime(resultSet.getInt("i_createtime"));
				resItems.setItemAdduser(resultSet.getInt("i_adduser"));
			}
		} catch (Exception e) {
			logger.error("findResItemsByItemNo() error", e);
		}
		finally {
			ConnectionManager2.close(conn, statement, resultSet);
		}
		return resItems;
	}
	
	
	public ResItems findResItemsByItemId(int itemId) {
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ResItems resItems = null;
		String sql = " select * from res_items where i_id = "+itemId;
		
		try {
			conn = ConnectionManager2.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				resItems = new ResItems();
				resItems.setItemId(resultSet.getInt("i_id"));
				resItems.setItemNo(resultSet.getString("i_no"));
				resItems.setItemName(resultSet.getString("i_name"));
				resItems.setItemImg(resultSet.getString("i_img"));
				resItems.setItemImgpath(resultSet.getString("i_imgpath"));
				resItems.setItemMoney(resultSet.getString("i_money"));
				resItems.setItemDesc(resultSet.getString("i_desc"));
				resItems.setItemMemo(resultSet.getString("i_memo"));
				resItems.setItemType(resultSet.getInt("i_type"));
				resItems.setItemCreatetime(resultSet.getInt("i_createtime"));
				resItems.setItemAdduser(resultSet.getInt("i_adduser"));
			}
		} catch (Exception e) {
			logger.error("findResItemsByItemId() error", e);
		}
		finally {
			ConnectionManager2.close(conn, statement, resultSet);
		}
		return resItems;
	}
	
	
	
	
	/**
	 * 更新设置
	 */
	public int updateResItem(ResItems vo, String type) {
		String sql = ""; 
		int re = 0;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;


		try {
			conn = ConnectionManager2.getConnection();

			if(type.equals("insert")) {
				sql = " insert into res_items(i_no, i_name, i_img, i_imgpath, i_money, i_desc, i_memo, i_type, i_createtime, i_adduser)  " +
					" values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				pst = conn.prepareStatement(sql);
				pst.setString(1, vo.getItemNo());
				pst.setString(2, vo.getItemName());
				pst.setString(3, vo.getItemImg());
				pst.setString(4, vo.getItemImgpath());
				pst.setString(5, vo.getItemMoney());
				pst.setString(6, vo.getItemDesc());
				pst.setString(7, vo.getItemMemo());
				pst.setLong(8, vo.getItemType());
				pst.setLong(9, vo.getItemCreatetime());
				pst.setInt(10, vo.getItemAdduser());
			} else if (type.equals("update")) {
				sql = " update res_items set i_no=?, i_name=?, i_img=?, i_imgpath=?, i_money=?, i_desc=?, i_memo=?, i_type=? where i_id=?";

				pst = conn.prepareStatement(sql);
				pst.setString(1, vo.getItemNo());
				pst.setString(2, vo.getItemName());
				pst.setString(3, vo.getItemImg());
				pst.setString(4, vo.getItemImgpath());
				pst.setString(5, vo.getItemMoney());
				pst.setString(6, vo.getItemDesc());
				pst.setString(7, vo.getItemMemo());
				pst.setLong(8, vo.getItemType());
				pst.setInt(9, vo.getItemId());
			}

			pst.executeUpdate();
			
		} catch (Exception e) {
			logger.error("updateResItem() error", e);
		} finally {
			ConnectionManager2.close(conn, pst, resultSet);
		}
		return re;
	}
	
}
