package com.framework.persistence;

import java.io.Serializable;
import java.util.Collection;
import com.framework.exception.DaoException;
import com.framework.support.PageSupport;

import org.springframework.orm.hibernate3.HibernateCallback;

public interface PersistenceManager {
        //保存entity
        public void save(Object entity) throws DaoException;
        //保存entity,是否立即生效
        public void save(Object entity,boolean flush) throws DaoException;
        //修改entity
        public void update(Object entity) throws DaoException;
        //删除entity
        public void remove(Object entity) throws DaoException;
        //删除该id的entity
        public void remove(Class clz,Serializable id) throws DaoException;
        //删除集合中的entity
        public void removeAll(Collection entities) throws DaoException;
        //删除数组中的id的entity
        public void removeAll(Class clz,Serializable[] ids) throws DaoException;
        //执行hibernateCallback,小心使用
        public Object executeCallback(HibernateCallback callback) throws DaoException;
}
// vim: ft=java

