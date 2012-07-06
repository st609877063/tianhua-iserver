package com.gift.dao.impl;import java.util.List;import java.util.Map;import org.apache.ibatis.session.RowBounds;import org.mybatis.spring.support.SqlSessionDaoSupport;import org.springframework.stereotype.Repository;import com.gift.bean.Gift_cangku;import com.gift.dao.Gift_cangkuDAO;/** * @Repository @Service @Controller，它们分别对应存储层Bean，业务层Bean，和展示层Bean。 * @Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。 * @scope="prototype"来保证每一个请求有一个单独的Action来处理， 避免struts中Action的线程安全问题。 */@Repositorypublic class Gift_cangkuDAOImpl extends SqlSessionDaoSupport		implements			Gift_cangkuDAO {	public List<Gift_cangku> findAllGift_cangku(int pageId, int pageSize) {		try {			if (pageId == 0 && pageSize == 0) {				return this.getSqlSession().selectList("findAllGift_cangku");			} else {				return this.getSqlSession().selectList("findAllGift_cangku",						null, new RowBounds(pageId, pageSize));			}		} catch (RuntimeException re) {			logger.error("findAllGift_cangku failed :{}", re);			throw re;		}	}	public Integer findAllGift_cangku_count() {		try {			return this.getSqlSession().selectOne("findAllGift_cangku_count");		} catch (RuntimeException re) {			logger.error("findAllGift_cangku_count failed :{}", re);			throw re;		}	}	public Gift_cangku findGift_cangkuById(Integer ck_id) {		try {			return this.getSqlSession().selectOne("findGift_cangkuById", ck_id);		} catch (RuntimeException re) {			logger.error("findGift_cangkuById failed :{}", re);			throw re;		}	}	public Gift_cangku findGift_cangkuByItemNo(String item_no) {		try {			return this.getSqlSession().selectOne("findGift_cangkuByItemNo",					item_no);		} catch (RuntimeException re) {			logger.error("findGift_cangkuByItemNo failed :{}", re);			throw re;		}	}	public List<Gift_cangku> findGift_cangkuByKeyword(Map searchMap,			int pageId, int pageSize) {		try {			return this.getSqlSession().selectList("findCangkuByKeyword",					searchMap, new RowBounds(pageId, pageSize));		} catch (RuntimeException re) {			logger.error("findGift_cangkuByKeyword failed :{}", re);			throw re;		}	}	public Integer findGift_cangkuByKeyword_count(Map searchMap) {		try {			return this.getSqlSession().selectOne("findCangkuCountByKeyword",					searchMap);		} catch (RuntimeException re) {			logger.error("findGift_cangkuByKeyword_count failed :{}", re);			throw re;		}	}	public void updateGift_cangku(Gift_cangku gift_cangku) {		try {			this.getSqlSession().update("updateGift_cangku", gift_cangku);		} catch (RuntimeException re) {			logger.error("updateGift_cangku failed :{}", re);			throw re;		}	}	public int saveGift_cangku(Gift_cangku gift_cangku) {		try {			this.getSqlSession().insert("saveGift_cangku", gift_cangku);			return gift_cangku.getCk_id();		} catch (RuntimeException re) {			logger.error("saveGift_cangku failed :{}", re);			throw re;		}	}	public int removeGift_cangkuByItemNo(Gift_cangku gift_cangku) {		try {			return this.getSqlSession().delete("removeGift_cangkuByItemNo",					gift_cangku);		} catch (RuntimeException re) {			logger.error("removeGift_cangku failed :{}", re);			throw re;		}	}	public Integer removeGift_cangkuById(Integer ck_id) {		try {			return this.getSqlSession().delete("removeGift_cangkuById", ck_id);		} catch (RuntimeException re) {			logger.error("removeGift_cangkuById failed :{}", re);			throw re;		}	}}