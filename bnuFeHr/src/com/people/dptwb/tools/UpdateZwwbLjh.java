package com.people.dptwb.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.people.dptwb.manager.TBaseManager;

public class UpdateZwwbLjh extends TBaseManager {
	
	public List<Integer> getWbIds() {
		Connection mainConn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String sql = "select wb_id from wb where wb_ljs=0";
		
		List<Integer> reList = new ArrayList<Integer>();
		
		try {
			mainConn = getMainConn();
			pst = mainConn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				int wbId = rs.getInt("wb_id");
				reList.add(wbId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			closeResultSet(rs);
			closeStatement(pst);
			closeConnection(mainConn);
		}
		return reList;
	}
	

	public void processIdList() {
		List<Integer> idList = getWbIds();
		
		
		if (idList != null) {
			System.out.println("共有" + idList.size() + "条数据需要插入DB中");
			
			

			Connection mainConn = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select count(*) as total from wb_url where wb_id=?";

			String urlSql = "update wb set wb_ljs=? where wb_id=?";

			
				for (int i = 0; i < idList.size(); i++) {
					int count = 0;
					int wbId = idList.get(i);
					System.out.println("开始更新ID为："+wbId+"的连接数数据");
					try {
						mainConn = getMainConn();
						pst = mainConn.prepareStatement(sql);
						pst.setInt(1,wbId);
						rs = pst.executeQuery();
						if (rs.next()) {
							count = ((Long)rs.getLong("total")).intValue();
						}
						rs.close();
						pst.close();
						if (count > 0) {
							System.out.println("ID为："+wbId+"的连接数数据不为0");
							pst = mainConn.prepareStatement(urlSql);
							pst.setInt(1, count);
							pst.setInt(2, wbId);
							int re = pst.executeUpdate();
							if (re > 0) {
								//mainConn.commit();
								System.out.println("ID为："+wbId+"的连接数数据更新成功");
							}
							else {
								System.out.println("ID为："+wbId+"的连接数数据更新失败");
							}
						}
						else {
							System.out.println("ID为："+wbId+"的连接数数据为0，不进行更新");
						}
						
					} catch (Exception e) {
						System.out.println("写入政务微博信息时出现异常");
						e.printStackTrace();
					}
					finally {
						closeResultSet(rs);
						closeStatement(pst);
						closeConnection(mainConn);

					}

				}				

			
		}
		else {
			System.out.println("Excel数据为空");
		}
	}

	

	private static Connection getMainConn() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://10.100.6.198:3306/zwwbdh?characterEncoding=utf8",
					"zwwbdh", "zwwbdh*()");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		UpdateZwwbLjh tool = new UpdateZwwbLjh();
		tool.processIdList();
	}
}
