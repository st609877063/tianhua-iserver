package com.framework.persistence.jdbc;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.CallableStatement;
import com.framework.exception.DaoException;
import com.framework.persistence.JdbcPersistenceManager;
import com.framework.util.StringUtil;

public class JdbcPersistenceManagerImpl implements JdbcPersistenceManager{

        protected final Log log = LogFactory.getLog(getClass());

        private DataSource dataSource;

        public void setDataSource(DataSource dataSource) {
                this.dataSource = dataSource;
        }

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

        //执行sql
        public void executeSql(String sql) throws DaoException{
                if(log.isDebugEnabled()){
                        log.debug(sql);
                }	                
                Connection cn = null;
                Statement stmt = null;
                try {
                        cn = getConnection();
                        stmt = cn.createStatement();
                        stmt.execute(sql);
                } catch (SQLException e) {
                        throw new DaoException(e);
                } finally {
                        try {
                                if (stmt != null) {
                                        stmt.close();
                                }
                                if (cn != null) {
                                        cn.close();
                                }
                        } catch (Exception ex) {
                                log.error(ex.getMessage());
                        }
                }
        }
        //执行sql
        public void executeSql(String sql,String[] params) throws DaoException{
                if(log.isDebugEnabled()){
                        log.debug(sql);
                }	                
                Connection cn = null;
                PreparedStatement stmt = null;
                try {
                        cn = getConnection();
                        stmt = cn.prepareStatement(sql);
                        for(int i=0;i<params.length;i++){
                                stmt.setObject(i+1,params[i]);
                        }
                        stmt.executeUpdate();
                } catch (SQLException e) {
                        e.printStackTrace();
                        throw new DaoException(e);
                } finally {
                        try {
                                if (stmt != null) {
                                        stmt.close();
                                }
                                if (cn != null) {
                                        cn.close();
                                }
                        } catch (Exception ex) {
                                log.error(ex.getMessage());
                        }
                }
        }


        //执行多条sql语句
        public void executeSql(List<String> sqls) throws DaoException{
                Connection cn = null;
                Statement stmt = null;			
                try{
                        cn = getConnection();
                        stmt = cn.createStatement();
                        for(String sql : sqls){
                                stmt.execute(sql);
                        }
                }
                catch(SQLException e){
                        throw new DaoException(e);
                }
                finally{
                        try{
                                if(stmt != null){
                                        stmt.close();
                                }
                                if(cn != null){
                                        cn.close();
                                }
                        }
                        catch(Exception ex){
                                log.error(ex.getMessage());
                        }
                }
        }

        //执行存储过程
        public void executeProc(String sp_name) throws DaoException{
                String paramsCause=sp_name.substring(sp_name.indexOf("(")+1,sp_name.lastIndexOf(")")).trim();

                String sp=sp_name;
                String[] params=null;

                if(!paramsCause.equals("")){
                        params=paramsCause.split(",");

                        StringBuffer sb=new StringBuffer();

                        for(int i=0;i<params.length;i++){
                                sb.append("?,");
                        }

                        //去掉最后一个","
                        String s=sb.toString();
                        s=s.substring(0,s.length()-1);

                        sp=StringUtil.replace(sp_name,paramsCause,s);
                }

                try{
                        Connection con=getConnection();
                        CallableStatement cs = con.prepareCall("{call "+sp+"}");

                        if(params!=null){
                                for(int i=0;i<params.length;i++){
                                        cs.setObject(i+1,params[i]); 
                                }
                        }	

                        cs.execute();
                }catch(SQLException e){
                        throw new DaoException(e);
                }	

        }

        //批量执行sql
        public void batchExecuteSql(String sql,List params) throws DaoException{

                Connection cn = null;
                PreparedStatement stmt = null;
                try {
                        cn = getConnection();

                        stmt=cn.prepareStatement(sql);

                        if(params!=null){ 			            		            
                                for(int i=0;i<params.size();i++){
                                        Object[] paramArray=(Object[])params.get(i);
                                        for(int j=0;j<paramArray.length;j++){
                                                stmt.setObject(j+1,paramArray[j]);
                                        }

                                        stmt.addBatch();

                                }
                                stmt.executeBatch();
                        }

                } catch (SQLException e) {
                        throw new DaoException(e);
                } finally {
                        try {
                                if (stmt != null) {
                                        stmt.close();
                                }
                                if (cn != null) {
                                        cn.close();
                                }
                        } catch (Exception ex) {
                                log.error(ex.getMessage());
                        }
                }
        }

}
// vim: ft=java

