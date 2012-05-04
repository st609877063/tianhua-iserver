package com.framework.persistence.hibernate;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.hibernate.HibernateException;
import java.sql.SQLException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.framework.exception.DaoException;
import com.framework.persistence.PersistenceManager;

import java.io.Serializable;
import java.util.Collection;
import org.hibernate.Session;


public class HibernatePersistenceManagerImpl extends HibernateDaoSupport implements PersistenceManager{

        protected final Log log = LogFactory.getLog(getClass());

        public HibernatePersistenceManagerImpl(){
                super();
        }

        //保存entity
        public void save(Object entity) throws DaoException{
                this.getHibernateTemplate().save(entity);
        }
        //保存entity,flush是否立即生效
        public void save(final Object entity,final boolean flush) throws DaoException{
                try{
                        HibernateCallback callback = new HibernateCallback(){
                                public Object doInHibernate(Session session) throws HibernateException,SQLException{
                                        session.save(entity);                                
                                        if(flush){
                                        	System.out.println("save success!");
                                                session.flush();
                                                session.clear();
                                        }
                                        return null;
                                }
                        };
                        this.getHibernateTemplate().execute(callback);
                }catch(Exception e){
                        throw new DaoException(e);
                }
        }
        //修改entity
        public void update(Object entity) throws DaoException{
                try{
                        this.getHibernateTemplate().update(entity);
                }catch(Exception e){
                        throw new DaoException(e);
                }
        }
        //删除entity
        public void remove(Object entity) throws DaoException{
                try{
                        this.getHibernateTemplate().delete(entity);
                        System.out.println("remove success!");
                }catch(Exception e){
                        throw new DaoException(e);
                }
        }
        //删除该id的entity
        public void remove(Class clz,Serializable id) throws DaoException{
                try{
                        Object entity = this.getHibernateTemplate().load(clz,id);
                        this.getHibernateTemplate().delete(entity);
                }catch(Exception e){
                        throw new DaoException(e);
                }
        }
        //删除集合中的entity
        public void removeAll(Collection entities) throws DaoException{
                try{
                        for(Object entity:entities){
                                this.getHibernateTemplate().delete(entity);
                        }
                }catch(Exception e){
                        throw new DaoException(e);
                }
        }
        //删除数组中的id的entity
        public void removeAll(Class clz,Serializable[] ids) throws DaoException{
                try{
                        for(Serializable id : ids){
                                remove(clz,id);
                        }
                }catch(Exception e){
                        throw new DaoException(e);
                }
        }
        //执行hibernateCallback,小心使用
        public Object executeCallback(HibernateCallback callback) throws DaoException{
		return getHibernateTemplate().execute(callback);
        }

}
// vim: ft=java

