package com.res.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.res.bean.ResItems;
import com.res.bean.ResOrderMenu;
import com.res.bean.ResOrders;
import com.res.server.ConnectionManager2;
import com.res.tools.ComparatorTopic;
import com.res.tools.DateTools;

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
			sql = " insert into res_orders(o_no, o_user, o_phone, o_date, o_type, o_memo, o_fee, o_createtime) values(?, ?, ?, ?, ?, ?, ?, ?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, vo.getOrderNo());
			pst.setString(2, vo.getOrderUser());
			pst.setString(3, vo.getOrderPhone());
			pst.setString(4, vo.getOrderDate());
			pst.setInt(5, vo.getOrderType());
			pst.setString(6, vo.getOrderMemo());
			pst.setString(7, vo.getOrderFee());
			pst.setLong(8, vo.getOrderCreatetime());

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


	public List<ResOrders> getOrderList(String orderNoSrh, String usernameSrh, String userphoneSrh, String orderDateSrh, int orderTypeSrh) {
		List<ResOrders> rtnList = new ArrayList<ResOrders>();
		Map<Integer, ResOrders> orderMap = new HashMap<Integer, ResOrders>();
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ResOrders orderVo = null;
		ResItems itemVo = null;
		List<ResItems> itemList = null;
		
		String sql = "select o.*, om_num, i.*, m.m_money from res_orders o, res_order_menu om, res_items i, res_menus m " +
				" where o.o_no=om.o_no and om.i_id=i.i_id and m.i_id=om.i_id and o.o_date=m.m_date and o.o_type=m.m_type ";
		if(orderNoSrh != null && !orderNoSrh.equals("")) {
			sql = sql + " and o.o_no='"+orderNoSrh+"' ";
		} else {
			if(usernameSrh != null && !usernameSrh.equals("")) {
				sql = sql + " and o.o_user='"+usernameSrh+"' ";
			}
			if(userphoneSrh != null && !userphoneSrh.equals("")) {
				sql = sql + " and o.o_phone='"+userphoneSrh+"' ";
			}
		}
		if(orderDateSrh != null && !orderDateSrh.equals("")) {
			sql = sql + " and o.o_date='"+orderDateSrh+"' ";
		}
		if(orderTypeSrh != -1) {
			sql = sql + " and o.o_type="+orderTypeSrh;
		}
		
		sql = sql + "  order by o.o_no asc, i.i_type asc";
		
		try {
			conn = ConnectionManager2.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			int orderNo = 0;
			while (resultSet.next()) {
				orderNo = resultSet.getInt("o_no");
				
				if(orderMap.containsKey(orderNo)) {
					orderVo = orderMap.get(orderNo);
					itemList = orderVo.getItemList();
					if(itemList == null) {
						itemList = new ArrayList<ResItems>();
					}
					
					itemVo = new ResItems();
					itemVo.setItemId(resultSet.getInt("i_id"));
					itemVo.setItemNo(resultSet.getString("i_no"));
					itemVo.setItemName(resultSet.getString("i_name"));
					itemVo.setItemImg(resultSet.getString("i_img"));
					itemVo.setItemImgpath(resultSet.getString("i_imgpath"));
					itemVo.setItemMoney(resultSet.getString("i_money"));
					itemVo.setItemMenuMoney(resultSet.getString("m_money")); //设置的菜单价格
					itemVo.setItemDesc(resultSet.getString("i_desc"));
					itemVo.setItemMemo(resultSet.getString("i_memo"));
					itemVo.setItemType(resultSet.getInt("i_type"));
					itemVo.setItemCreatetime(resultSet.getInt("i_createtime"));
					itemVo.setItemAdduser(resultSet.getInt("i_adduser"));
					itemVo.setOrderItemNum(resultSet.getInt("om_num"));
					
					itemList.add(itemVo);
					orderVo.setItemList(itemList);
					
					orderMap.put(orderNo, orderVo);
					
				} else {
					orderVo = new ResOrders();
					orderVo.setPkId(resultSet.getInt("pk_id"));
					orderVo.setOrderNo(orderNo);
					orderVo.setOrderDate(resultSet.getString("o_date"));
					orderVo.setOrderType(resultSet.getInt("o_type"));
					orderVo.setOrderFee(resultSet.getString("o_fee"));
					orderVo.setOrderPhone(resultSet.getString("o_phone"));
					orderVo.setOrderUser(resultSet.getString("o_user"));
					orderVo.setOrderCreatetime(resultSet.getInt("o_createtime"));
					orderVo.setOrderCreateDate(DateTools.getDisplayTime(new Long(resultSet.getInt("o_createtime"))));
					
					itemVo = new ResItems();
					itemVo.setItemId(resultSet.getInt("i_id"));
					itemVo.setItemNo(resultSet.getString("i_no"));
					itemVo.setItemName(resultSet.getString("i_name"));
					itemVo.setItemImg(resultSet.getString("i_img"));
					itemVo.setItemImgpath(resultSet.getString("i_imgpath"));
					itemVo.setItemMoney(resultSet.getString("i_money"));
					itemVo.setItemMenuMoney(resultSet.getString("m_money")); //设置的菜单价格
					itemVo.setItemDesc(resultSet.getString("i_desc"));
					itemVo.setItemMemo(resultSet.getString("i_memo"));
					itemVo.setItemType(resultSet.getInt("i_type"));
					itemVo.setItemCreatetime(resultSet.getInt("i_createtime"));
					itemVo.setItemAdduser(resultSet.getInt("i_adduser"));
					itemVo.setOrderItemNum(resultSet.getInt("om_num"));
					
					itemList = orderVo.getItemList();
					if(itemList == null) {
						itemList = new ArrayList<ResItems>();
					}
					itemList.add(itemVo);
					orderVo.setItemList(itemList);
					
					orderMap.put(orderNo, orderVo);
				}
			}
			
			for(Map.Entry<Integer, ResOrders> entry: orderMap.entrySet()) {
				rtnList.add(entry.getValue());
			}
			ComparatorTopic comparator = new ComparatorTopic();
			Collections.sort(rtnList, comparator);
			
		} catch (Exception e) {
			logger.error("getOrderList error", e);
		}
		finally {
			ConnectionManager2.close(conn, statement, resultSet);
		}
		return rtnList;
	}
}
