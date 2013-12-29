/** 
 * 提供的manager的公用变量和方法
 * File: BaseManager.java 
 * Date: Nov 23, 2009
 * @author yl1935
 * Email:
 */
package com.people.dptwb.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.log4j.Logger;

import com.people.dptwb.server.db.ConnectionManager;

public class TBaseManager {
	
	private static final Logger logger = Logger.getLogger(TBaseManager.class);
		
	private QueryRunner queryRunner = new QueryRunner();
	
	@SuppressWarnings("unchecked")
	private ResultSetHandler resultSetHandler = new MapListHandler();
	
	@SuppressWarnings("unchecked")
	private ResultSetHandler mapHandler = new MapHandler();
	
	protected QueryRunner getQueryRunner() {
		return this.queryRunner;
	}
	
	@SuppressWarnings("unchecked")
	protected ResultSetHandler getResultSetHandler() {
		return resultSetHandler;
	}
	
	/**
	 * 关闭连接
	 * @param conn
	 */
	protected void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				if (!conn.isClosed()) {
					setAutoCommit(conn, true);
					DbUtils.close(conn);
				}
				else {
					conn = null;
				}
			} catch (Exception e) {
				logger.error("数据库连接不能正确关闭：", e);
			}
		}
	}
	

	/**
	 * 关闭所有连接资源
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	protected void closeStatement(Statement stmt){
		try {
			DbUtils.close(stmt);		
		} catch (Exception e) {
			logger.error("连接资源不能正确关闭：" + e.getMessage());
		}
	}
	
	/**
	 * 关闭所有连接资源
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	protected void closeResultSet(ResultSet rs){
		try {
			DbUtils.close(rs);		
		} catch (Exception e) {
			logger.error("连接资源不能正确关闭：" + e.getMessage());
		}
	}
	
	/**
	 * 自动提交设置
	 * @param conn
	 * @param auto
	 */
	protected void setAutoCommit(Connection conn ,boolean auto){
		try {
			if(conn != null){
				conn.setAutoCommit(auto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改事物自动提交标记时出错：" + e.getMessage());
		}
	}
	
	/**
	 * 提交事物
	 * @param conn
	 */
	protected void commit(Connection conn){
		try {
			if(conn != null){
				conn.commit();
			}	
		} catch (Exception e) {
			logger.error("提交事物时失败：" + e.getMessage(), e);
		}
		
	}
	
	/**
	 * 回滚事物
	 * @param conn
	 */
	protected void rollBack(Connection conn){
		try {
			if(conn != null){
				conn.rollback();
			}
		} catch (Exception e) {
			logger.error("回滚事物时出错："+ e.getMessage(), e);
		}	
	}
	
	/**
	 * 插入或更新操作，带参数
	 * @param conn
	 * @param queryStr
	 * @param params
	 * @throws SQLException
	 */
	protected int update(Connection conn, String queryStr, Object... params) throws SQLException{
		return queryRunner.update(conn, queryStr, params);
	}
	
	/**
	 * 插入或更新操作，不带参数
	 * @param conn
	 * @param queryStr
	 * @throws SQLException
	 */
	protected int update(Connection conn, String queryStr) throws SQLException{
		return queryRunner.update(conn, queryStr);
	}
	
	/**
	 * 批量插入或更新操作，带参数
	 * @param conn
	 * @param queryStr
	 * @throws SQLException
	 */
	protected int[] batch(Connection conn, String queryStr, Object[][] params) throws SQLException{
		return queryRunner.batch(conn, queryStr, params);
	}
	
	
	/**
	 * 带参数查询，返回一个list，其成员对象为一个key-value对应的map，key对应字段名，value为字段值。
	 * @param conn
	 * @param queryStr
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	protected List<Map<String,Object>> query(Connection conn, String queryStr, Object... params) throws SQLException{	
		List<Map<String,Object>> result = (ArrayList<Map<String,Object>>) queryRunner.query(conn, queryStr, resultSetHandler, params);
		return result;
	}
	
	/**
	 * 不带参数查询，返回一个list，其成员对象为一个key-value对应的map，key对应字段名，value为字段值。
	 * @param conn
	 * @param queryStr
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	protected List<Map<String,Object>> query(Connection conn, String queryStr) throws SQLException{
		List<Map<String,Object>> result = (ArrayList<Map<String,Object>>) queryRunner.query(conn, queryStr, resultSetHandler);
		return result;
	}
	
	/**
	 * 带参数查询，返回一个list，其成员对象为一个Entity
	 * @param conn
	 * @param queryStr
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	protected List<? extends Object> queryEntityList(Connection conn, String queryStr, Object clazz, Object... params) throws Exception {
		return this.queryEntityList(conn, queryStr, clazz.getClass(), params);
	}
	
	
	/**
	 * 带参数查询，返回一个list，其成员对象为一个Entity
	 * @param conn
	 * @param queryStr
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	protected List<? extends Object> queryEntityList(Connection conn, String queryStr, Class<?> clazz, Object... params) throws Exception {
		RowProcessor rp = new BasicRowProcessor(new TwitterBeanProcessor());
		ResultSetHandler handler = new BeanListHandler(clazz, rp);
		List<? extends Object> result = new ArrayList<Object>();
		if (params == null) {
			result = (List<? extends Object>)queryRunner.query(conn, queryStr, handler);
		} 
		else {
			result = (List<? extends Object>)queryRunner.query(conn, queryStr, handler, params);
		}
		return result;
	}
	
	
	/**
	 * 带参数查询，返回一个Entity对象
	 * @param conn
	 * @param queryStr
	 * @param params 为null则不进行参数查询
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	protected Object queryEntity(Connection conn, String queryStr, Class<?> clazz, Object... params) throws Exception {
		RowProcessor rp = new BasicRowProcessor(new TwitterBeanProcessor());
		ResultSetHandler handler = new BeanHandler(clazz, rp);
		Object result = null;
		if (params == null) {
			result = queryRunner.query(conn, queryStr, handler);
		} 
		else {
			result = queryRunner.query(conn, queryStr, handler, params);
		}
		return result;
	}
	
	
	
	
	
	protected Map<String, Object> queryMap(Connection conn, String queryStr, Object... params) throws Exception {
		Map<String, Object> result = null;
		if (params == null) {
			result = (Map<String, Object>)queryRunner.query(conn, queryStr, mapHandler);
		}
		else {
			result = (Map<String, Object>)queryRunner.query(conn, queryStr, mapHandler, params);
		}
		return result;
	}
	

	/**
	 * 获取主连接
	 * @param conn
	 */
	protected Connection getMainConnection(){
		return ConnectionManager.getConnection();
	}
}
