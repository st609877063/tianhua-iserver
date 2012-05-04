package com.framework.persistence.hibernate;


import java.util.List;
import com.framework.exception.DaoException;
import com.framework.persistence.QueryManager;
import com.framework.support.PageSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.io.Serializable;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import java.util.Map;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.reflect.Field;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.criterion.CriteriaSpecification;
import java.util.Collection;
import org.hibernate.criterion.Projections;

public class HibernateQueryManagerImpl extends HibernateDaoSupport implements QueryManager{

        protected final Log log = LogFactory.getLog(getClass());

        public HibernateQueryManagerImpl(){
                super();
        }

        public Object load(Class entity, Serializable id) throws DaoException{
                try{
                        return getHibernateTemplate().load(entity, id);
                }catch(Exception e){
                        throw new DaoException(e);
                }
        }

        public Object get(Class entity, Serializable id) throws DaoException{
                try{
                        return getHibernateTemplate().get(entity, id);
                }catch(Exception e){
                        throw new DaoException(e);
                }
        }

        public List find(String hql) throws DaoException{
                try{
                        return getHibernateTemplate().find(hql);
                }catch(Exception e){
                        throw new DaoException(e);
                }
        }

        public List find(String hql,Object parameter) throws DaoException{
                try{
                        return getHibernateTemplate().find(hql,parameter);
                }catch(Exception e){
                        throw new DaoException(e);
                }
        }

        public List find(String hql,Object[] parameters) throws DaoException{
                try{
                        return getHibernateTemplate().find(hql,parameters);
                }catch(Exception e){
                        throw new DaoException(e);
                }
        }

        public PageSupport find(final String hql,final int iPageNo,final int iPageSize) throws DaoException {
                return find(hql,(Object[]) null,iPageNo,iPageSize);
        }

        public PageSupport find(final String hql,final Object parameter,final int iPageNo,final int iPageSize) throws DaoException {
                return find(hql,new Object[]{parameter},iPageNo,iPageSize);
        }

        public PageSupport find(final String hql,final Object[] parameters,final int iPageNo,final int iPageSize) throws DaoException {
                HibernateCallback callback = new HibernateCallback() {
                        public Object doInHibernate(Session session) throws HibernateException, SQLException {

                                Query query = session.createQuery(hql);
                                Query query_count = session.createQuery(parseSql(hql));

                                if (parameters != null) {
                                        for (int i = 0; i < parameters.length; i++) {
                                                query.setParameter(i, parameters[i]);
                                                query_count.setParameter(i,parameters[i]);
                                        }
                                }

                                // 返回
                                return buildPS(query,query_count,iPageNo,iPageSize);
                        }
                };
                return (PageSupport) getHibernateTemplate().execute(callback,true);

        }

        public List findByNamedQuery(String namedQuery) throws DaoException {
                return findByNamedQuery(namedQuery,(Object[])null);
        }

        public List findByNamedQuery(String namedQuery,Object parameter) throws DaoException {
                return findByNamedQuery(namedQuery,new Object[]{parameter});
        }

        public List findByNamedQuery(String namedQuery,Object[] parameters) throws DaoException {
                try{
                        return getHibernateTemplate().findByNamedQuery(namedQuery,parameters);
                }catch(Exception e){
                        throw new DaoException(e);
                }
        }

        public PageSupport findByNamedQuery(final String namedQuery, final int iPageNo,final int iPageSize) throws DaoException {
                return findByNamedQuery(namedQuery,(Object[]) null,iPageNo,iPageSize);
        }

        public PageSupport findByNamedQuery(final String namedQuery, final Object parameter,final int iPageNo,final int iPageSize) throws DaoException {
                return findByNamedQuery(namedQuery,new Object[] {parameter},iPageNo,iPageSize);
        }

        public PageSupport findByNamedQuery(final String namedQuery, final Object[] parameters,final int iPageNo,final int iPageSize) throws DaoException {

                HibernateCallback callback = new HibernateCallback() {

                        public Object doInHibernate(Session session)
                                throws HibernateException, SQLException {

                                Query query = session.getNamedQuery(namedQuery);
                                Query query_count = session.createQuery(parseSql(query.getQueryString()));

                                if (parameters != null) {
                                        for (int i = 0; i < parameters.length; i++) {
                                                query.setParameter(i, parameters[i]);
                                                query_count.setParameter(i,parameters[i]);
                                        }
                                }

                                // 返回
                                return buildPS(query,query_count,iPageNo,iPageSize);
                        }
                };
                return (PageSupport) getHibernateTemplate().execute(callback,true);

        }

        //根据查询条件返回查询结果
        public List find(final DetachedCriteria cw) throws DaoException {
                try{
                        return getHibernateTemplate().findByCriteria(cw);
                }catch(Exception e){
                        throw new DaoException(e);
                }
        }

        //根据查询条件返回分页查询结果
        public PageSupport find(final DetachedCriteria cw,final int iPageNo,final int iPageSize) throws DaoException {
                return find(cw,(Order)null,iPageNo,iPageSize);
        }

        //根据查询条件和排序条件返回分页查询结果
        public PageSupport find(final DetachedCriteria cw,final Order order,final int iPageNo,final int iPageSize) throws DaoException {
                List ls=new ArrayList();
                if(order!=null){
                        ls.add(order);
                }
                return find(cw,ls,iPageNo,iPageSize);
        }

        //根据查询条件和排序条件列表返回分页查询结果
        public PageSupport find(final DetachedCriteria cw,final List orders,final int iPageNo,final int iPageSize) throws DaoException {
                HibernateCallback callback = new HibernateCallback() {
                        public Object doInHibernate(Session session) throws HibernateException {
                                Criteria criteria = cw.getExecutableCriteria(session);
                                // 返回
                                return buildPS(criteria,orders,iPageNo,iPageSize);
                        }
                };

                try{
                        return (PageSupport) getHibernateTemplate().execute(callback,true);
                }catch(Exception e){
                        throw new DaoException(e);
                }
        }

        //通过命名查询和命名参数获取数据列表

        public List findByNamedQuery(final String queryName, final Map paramMap) throws DaoException{
                HibernateCallback callback = new HibernateCallback() {
                        public Object doInHibernate(Session session) throws HibernateException {
                                Query query = session.getNamedQuery(queryName);

                                if (paramMap != null) {
                                        Iterator it=paramMap.keySet().iterator();
                                        while(it.hasNext()){
                                                String paramName = (String)it.next();
                                                Object paramValue=paramMap.get(paramName);

                                                applyNamedParameterToQuery(query, paramName, paramValue);
                                        }
                                }

                                // 返回
                                return query.list();

                        }
                };

                try{
                        return getHibernateTemplate().executeFind(callback);
                }catch(Exception e){
                        throw new DaoException(e);
                }
        }

        public PageSupport findByNamedQuery(final String queryName, final Map paramMap,final int iPageNo,final int iPageSize) throws DaoException{
                HibernateCallback callback = new HibernateCallback() {
                        public Object doInHibernate(Session session) throws HibernateException {
                                Query query = session.getNamedQuery(queryName);
                                Query query_count = session.createQuery(parseSql(query.getQueryString()));
                                if (paramMap != null) {
                                        Iterator it=paramMap.keySet().iterator();
                                        while(it.hasNext()){

                                                String paramName = (String)it.next();
                                                Object paramValue=paramMap.get(paramName);

                                                applyNamedParameterToQuery(query, paramName, paramValue);
                                                applyNamedParameterToQuery(query_count,paramName,paramValue);
                                        }
                                }

                                // 返回
                                return buildPS(query,query_count,iPageNo,iPageSize);

                        }
                };

                try{
                        return (PageSupport) getHibernateTemplate().execute(callback,true);
                }catch(Exception e){
                        //throw new DaoException(e);
                        return null;
                }
        }

        private PageSupport buildPS(Query query,Query queryCount,int iPageNo,int iPageSize){
                PageSupport ps = new PageSupport();
                ps.setPageNo(iPageNo);
                ps.setPageSize(iPageSize);
                Object result = queryCount.iterate().next();
                if(result instanceof Integer){
                        ps.setRecTotal(((Integer)result).intValue());
                }
                if(result instanceof Long){
                        ps.setRecTotal(((Long)result).intValue());
                }
                query.setFirstResult((iPageNo-1)*iPageSize);
                query.setMaxResults(iPageSize);
                ps.setList(query.list());
                return ps;
        }

        private PageSupport buildPS(Criteria criteria,List orders,int iPageNo,int iPageSize){
                PageSupport ps=new PageSupport();
                ps.setPageNo(iPageNo);
                ps.setPageSize(iPageSize);
                List orderEntrys = null;
                Field field = null;
                try{
                        CriteriaImpl impl = (CriteriaImpl)criteria;
                        field = CriteriaImpl.class.getDeclaredField("orderEntries");
                        field.setAccessible(true);
                        orderEntrys = (List)field.get(impl);
                        field.set(criteria,new ArrayList());
                }catch(Exception e){
                        log.error(e);
                }
                int rectotal = ((Integer)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
                criteria.setProjection(null);
                criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
                ps.setRecTotal(rectotal);
                //再恢复Order部分
                try{
                        if(orderEntrys!=null){
                                List innerOrderEntries = (List)field.get(criteria);
                                for(int i=0;i<orderEntrys.size();i++){
                                        innerOrderEntries.add(orderEntrys.get(i));
                                }
                        }
                }catch(Exception e){
                        log.error(e);
                }

                //为了兼容以前的实现
                if(orders!=null){
                        for(int i=0;i<orders.size();i++){
                                criteria.addOrder((Order)orders.get(i));
                        }
                }

                criteria.setFirstResult((iPageNo - 1) * iPageSize);
                criteria.setMaxResults(iPageSize);
                ps.setList(criteria.list());
                return ps;
        }
        //用于获取记录总数,修改hql,加上count(*)
        protected String parseSql(String queryString){
                //去掉硬回车和缩进字符
                queryString=queryString.replaceAll("\n", " ");
                queryString=queryString.replaceAll("\t", " ");

                //去掉order by
                int iOrderIndex=queryString.toLowerCase().indexOf(" order by ");
                if(iOrderIndex>0){
                        queryString=queryString.substring(0,iOrderIndex);
                }

                StringBuffer sb = new StringBuffer();

                if (queryString.trim().toLowerCase().indexOf("select ") == 0) {
                        int t=0;
                        //处理有子查询的情况
                        int i=queryString.trim().toLowerCase().lastIndexOf(" as ");
                        if(i > 0){
                                //主from位置
                                t=queryString.toLowerCase().indexOf(" from ",i);
                        }else{
                                t = queryString.toLowerCase().indexOf(" from ");
                        }

                        sb.append("SELECT COUNT(*) ");
                        sb.append(queryString.substring(t));

                } else {
                        sb.append("SELECT COUNT(*) ");
                        sb.append(queryString);
                }
                return sb.toString();
        }

        protected void applyNamedParameterToQuery(Query queryObject, String paramName, Object paramValue){
                if (paramValue instanceof Collection) {
                        queryObject.setParameterList(paramName, (Collection) paramValue);
                }
                else if (paramValue instanceof Object[]) {
                        queryObject.setParameterList(paramName, (Object[]) paramValue);
                }
                else {
                        queryObject.setParameter(paramName, paramValue);
                }
        }


}
// vim: ft=java

