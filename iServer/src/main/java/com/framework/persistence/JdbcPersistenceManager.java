package com.framework.persistence;
import com.framework.exception.DaoException;

import java.util.List;


public interface JdbcPersistenceManager {
        //执行sql语句
        public void executeSql(String sql) throws DaoException;

        public void executeSql(String sql,String[] params) throws DaoException;
        //执行多条sql语句
        public void executeSql(List<String> sqls) throws DaoException;
        //执行存储过程，参数形式为：test("xxx","yyy")
        public void executeProc(String sp_name) throws DaoException;
        //批量执行sql语句
        public void batchExecuteSql(String sql,List params) throws DaoException;
}
// vim: ft=java

