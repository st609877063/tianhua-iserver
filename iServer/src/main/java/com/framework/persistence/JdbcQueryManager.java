package com.framework.persistence;
import javax.sql.RowSet;
import java.util.List;
import com.framework.exception.DaoException;
import com.framework.support.PageSupport;

public interface JdbcQueryManager {
        //执行select sql语句,返回RowSet
	public RowSet getRowSet(String sql)throws DaoException;
        //执行select sql语句,根据传入的class类型，返回包含实体对象的list</P>
	public List getList(String sql,Class clz)throws DaoException;
        //执行select sql语句,根据传入的分页参数，返回分页结果集</P>
	public PageSupport getRowSet(String sql,int pageNo,int pageSize)throws DaoException;
        //执行select sql语句,根据传入的分页参数和class类型，返回已转换的分页结果集</P>
	public PageSupport getList(String sql,int pageNo,int pageSize,Class clz)throws DaoException;

}
// vim: ft=java

