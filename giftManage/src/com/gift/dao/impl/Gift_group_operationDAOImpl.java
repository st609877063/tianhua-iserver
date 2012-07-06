package com.gift.dao.impl;import java.util.HashMap;import java.util.List;import java.util.Map;import org.mybatis.spring.support.SqlSessionDaoSupport;import org.springframework.stereotype.Repository;import com.gift.bean.Gift_group_operation;import com.gift.dao.Gift_group_operationDAO;/** * @Repository @Service @Controller，它们分别对应存储层Bean，业务层Bean，和展示层Bean。 * @Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。 * @scope="prototype"来保证每一个请求有一个单独的Action来处理， 避免struts中Action的线程安全问题。 */@Repository@SuppressWarnings({ "unchecked", "rawtypes" })public class Gift_group_operationDAOImpl extends SqlSessionDaoSupport implements		Gift_group_operationDAO {	public List<Gift_group_operation> findAllGift_group_operation() {		try {			return this.getSqlSession().selectList(					"findAllGift_group_operation");		} catch (RuntimeException re) {			logger.error("findAllGift_group_operation failed :{}", re);			throw re;		}	}	public Gift_group_operation findGift_group_operationByGo_id(Integer go_id) {		try {			return this.getSqlSession().selectOne(					"findGift_group_operationByGo_id", go_id);		} catch (RuntimeException re) {			logger.error("findGift_group_operationByGo_id failed :{}", re);			throw re;		}	}	public void removeGift_group_operation(			Gift_group_operation gift_group_operation) {		try {			this.getSqlSession().delete("removeGift_group_operation",					gift_group_operation);		} catch (RuntimeException re) {			logger.error("removeGift_group_operation failed :{}", re);			throw re;		}	}	public void saveGift_group_operation(			Gift_group_operation gift_group_operation) {		try {			getSqlSession().insert("saveGift_group_operation",					gift_group_operation);		} catch (RuntimeException re) {			logger.error("saveGift_group_operation failed :{}", re);			throw re;		}	}	public void updateGift_group_operation(			Gift_group_operation gift_group_operation) {		try {			this.getSqlSession().update("updateGift_group_operation",					gift_group_operation);		} catch (RuntimeException re) {			logger.error("updateGift_group_operation failed :{}", re);			throw re;		}	}	public int findGift_group_operationByGroup_id_count(Integer group_id_c) {		Map Gift_group_operationmap_count = new HashMap();		Gift_group_operationmap_count.put("group_id_c", group_id_c);		try {			return (Integer) this.getSqlSession().selectOne(					"findGift_group_operationByGroup_id_count",					Gift_group_operationmap_count);		} catch (RuntimeException re) {			logger.error("findGift_group_operationByGroup_id_count failed :{}",					re);			throw re;		}	}	public List<Gift_group_operation> findGift_group_operationByGroup_id(			Integer group_id_c, int currentPage, int lineSize) {		Map Gift_group_operationmap = new HashMap();		Gift_group_operationmap.put("group_id_c", group_id_c);		Gift_group_operationmap.put("sartRow", ((currentPage - 1) * lineSize));		Gift_group_operationmap.put("endRow", currentPage * lineSize);		try {			return this.getSqlSession().selectList(					"findGift_group_operationByGroup_id",					Gift_group_operationmap);		} catch (RuntimeException re) {			logger.error("findGift_group_operationByGroup_id failed :{}", re);			throw re;		}	}	public List<Gift_group_operation> findGift_group_operationByGroup_id_excel(			Integer group_id_c) {		Map Gift_group_operationmap_excel = new HashMap();		Gift_group_operationmap_excel.put("group_id_c", group_id_c);		try {			return this.getSqlSession().selectList(					"findGift_group_operationByGroup_id_excel",					Gift_group_operationmap_excel);		} catch (RuntimeException re) {			logger.error("findGift_group_operationByGroup_id_excel failed :{}",					re);			throw re;		}	}}