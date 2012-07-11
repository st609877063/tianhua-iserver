package com.res.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.res.bean.ResMenus;
import com.res.bean.ResOrderMenu;
import com.res.bean.ResOrders;
import com.res.server.ConnectionManager2;

public class ResOrderManager{
	private static final Logger logger = Logger.getLogger(ResOrderManager.class);
	
	public int saveResOrder(ResOrders vo) {
		String sql = ""; 
		int re = 0;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;


		try {
			conn = ConnectionManager2.getConnection();
			sql = " insert into res_orders(o_no, o_user, o_phone, o_date, o_memo, o_fee, o_createtime) values(?, ?, ?, ?, ?, ?, ?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, vo.getOrderNo());
			pst.setString(2, vo.getOrderUser());
			pst.setString(3, vo.getOrderPhone());
			pst.setString(4, vo.getOrderDate());
			pst.setString(5, vo.getOrderMemo());
			pst.setString(6, vo.getOrderFee());
			pst.setLong(7, vo.getOrderCreatetime());

			re = pst.executeUpdate();
			
		} catch (Exception e) {
			logger.error("saveResOrder() error", e);
		} finally {
			ConnectionManager2.close(conn, pst, resultSet);
		}
		return re;
	}
	
	
	public void saveResOrderMemu(List<ResOrderMenu> resOmList) {
		String sql = ""; 
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;

		try {
			conn = ConnectionManager2.getConnection();

			sql = " insert into res_order_menu(o_no, i_id, i_no, om_money, om_num, om_total, om_createtime) values(?, ?, ?, ?, ?, ?, ?)";
			
			pst = conn.prepareStatement(sql);
			
			for(int i=0; resOmList!=null&&i<resOmList.size(); i++) {
				pst.setInt(1, resOmList.get(i).getOrderNo());
				pst.setInt(2, resOmList.get(i).getItemId());
				pst.setString(3, resOmList.get(i).getItemNo());
				pst.setString(4, resOmList.get(i).getOmMoney());
				pst.setInt(5, resOmList.get(i).getOmNum());
				pst.setString(6, resOmList.get(i).getOmTotal());
				pst.setLong(7, resOmList.get(i).getOmCreatetime());
				
				pst.addBatch();
			}
			
			int[] re = pst.executeBatch();
			
		} catch (Exception e) {
			logger.error("saveResOrderMemu() error", e);
		} finally {
			ConnectionManager2.close(conn, pst, resultSet);
		}
	}
}
