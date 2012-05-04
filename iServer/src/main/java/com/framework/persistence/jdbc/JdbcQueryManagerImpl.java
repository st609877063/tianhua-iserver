package com.framework.persistence.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;
import javax.sql.rowset.CachedRowSet;

import com.framework.exception.DaoException;
import com.framework.persistence.JdbcQueryManager;
import com.framework.support.PageSupport;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.ResultSet;
import javax.sql.RowSet;
import java.sql.DatabaseMetaData;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class JdbcQueryManagerImpl implements JdbcQueryManager{

	private Log log = LogFactory.getLog(JdbcQueryManagerImpl.class);
	private DataSource dataSource;
	private static boolean bFirst=true;//是否第一次判断数据库类型
        private static boolean bOracle=false;//是否为Oracle


        public void setDataSource(DataSource dataSource) {
                this.dataSource = dataSource;
        }

        //获取数据库连接
        private Connection getConnection() throws SQLException {
                Connection con = null;
                try {
                        if(log.isDebugEnabled()){
                                log.debug("datasource:"+dataSource.toString());
                        }	

                        con = dataSource.getConnection();
                        return con;
                } catch (SQLException e) {
                        throw e;
                }
        }

        //判断是否为oracle数据库
        private static boolean isOracle(Connection con) throws SQLException{
                if(bFirst){
                        DatabaseMetaData dbmd = con.getMetaData();
                        String s=dbmd.getDatabaseProductName().toLowerCase();
                        bOracle=s.indexOf("oracle")>=0;
                        bFirst=false;
                }
                return bOracle;
        }

        //为praparedStatment附加参数
        private void appendParams(PreparedStatement prestmt,List params) throws SQLException{
                if(params!=null && params.size()>0){
                        for(int i=0;i<params.size();i++){
                                prestmt.setObject(i+1,params.get(i));
                        }
                }
        }


        //执行sql语句,返回结果(RowSet or List)	
        protected Object getRows(String sql,List params,Class clz)throws DaoException{
                if(log.isDebugEnabled()){
                        log.debug(sql);
                }	

                Connection conn = null;
                PreparedStatement prestmt = null;
                CachedRowSet wrset = null;
                List ls=null;
                try {
                        conn = getConnection();
                        prestmt = conn.prepareStatement(sql);

                        appendParams(prestmt,params);

                        ResultSet rs = prestmt.executeQuery();

                        wrset = new CachedRowSetImpl();
                        wrset.populate(rs);
                        rs.close();

                        if(clz!=null){
                                ls=RowSet2List(wrset,clz);
                                return ls;
                        }

                } catch (Exception e) {
                        throw new DaoException(e);
                } finally {
                        try {
                                if (prestmt != null) {
                                        prestmt.close();
                                }
                                if (conn != null) {
                                        conn.close();
                                }
                        } catch (SQLException ex) {
                                log.error(ex.getMessage());
                        }
                }

                return wrset;
        }


        //返回RowSet
        public RowSet getRowSet(String sql)throws DaoException{
                return (RowSet)getRows(sql,null,null);
        }

        //返回List,每个entry为指定的class类型
        public List getList(String sql,Class clz)throws DaoException{
                if(clz==null)throw new DaoException("param clz not can be null!");
                return (List)getRows(sql,null,clz);
        } 

        //通用的获取分页结果集方法
        protected PageSupport getPageSet(String sql,List params,int pageNo,int pageSize,boolean bConvert,Class clz) throws DaoException {
                if(bConvert && clz==null){
                        throw new DaoException("param clz not can be null!");
                }

                if(log.isDebugEnabled()){
                        log.debug(sql);
                }	

                Connection conn = null;
                PreparedStatement prestmt = null;
                ResultSet rset;
                CachedRowSet wrset = null;
                PageSupport ps = null;
                int recTotal,pageTotal;

                try {
                        int actualPageNo = 1;
                        if (pageSize < 1) {
                                throw new DaoException("页面大小(pageSize)不能为0或者为负数！");
                        }
                        conn = getConnection();

                        wrset = new CachedRowSetImpl();
     
                        prestmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

                        appendParams(prestmt,params);

                        rset = prestmt.executeQuery();
                        rset.last();
                        recTotal = rset.getRow();
                        pageTotal = (recTotal - 1) / pageSize + 1;

                        //获得实际有效的页码
                        actualPageNo = pageNo < 1 ? 1 : (pageNo > pageTotal ? pageTotal : pageNo);
                        //开始定位到第pageNo页
                        int startIndex=(actualPageNo - 1) * pageSize + 1;

                        wrset.setPageSize(pageSize);
                        wrset.populate(rset,startIndex);

                        //关闭最终的结果集
                        rset.close();

                        //组织返回
                        ps = new PageSupport();
                        ps.setPageNo(pageNo);
                        ps.setPageSize(pageSize);
                        ps.setRecTotal(recTotal);

                        if(bConvert){
                                List ls=RowSet2List(wrset,clz);
                                ps.setList(ls);
                        }else{
                                ps.setRowSet(wrset);
                        }

                } catch (Exception e) {
                        throw new DaoException(e);
                } finally {
                        try {
                                if (prestmt != null) {
                                        prestmt.close();
                                }
                                if (conn != null) {
                                        conn.close();
                                }
                        } catch (Exception ex) {
                                log.error(ex.getMessage());
                        }
                }

                return ps;
        }
        //返回分页结果集(RowSet);
        public PageSupport getRowSet(String sql,int pageNo,int pageSize) throws DaoException {
                return getPageSet(sql,null,pageNo,pageSize,false,null);
        }

        //返回分页结果集(List);
        public PageSupport getList(String sql,int pageNo,int pageSize,Class clz) throws DaoException {
                return getPageSet(sql,null,pageNo,pageSize,true,clz);
        }

        //实现RowSet到List之间的转换
        protected List RowSet2List(RowSet rs,Class clz) throws Exception{
                rs.beforeFirst();
                List ls=new ArrayList();

                Method methods[] = clz.getMethods();
                while(rs.next()){
                        Object obj=clz.newInstance();
                        for(int i = 0;i<methods.length;i++){
                                String methodName = methods[i].getName();
                                if(methodName.startsWith("set"))
                                {
                                        String fieldName = methodName.substring(3);
                                        Object value=rs.getObject(fieldName);

                                        if(value!=null){
                                                Object[] p = new Object[]{value};
                                                methods[i].invoke(obj,p); 
                                        }

                                }
                        }

                        ls.add(obj);
                }      

                return ls;
        }

}
// vim: ft=java

