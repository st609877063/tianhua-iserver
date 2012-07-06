package com.gift.service.impl;import java.util.List;import java.util.Map;import javax.annotation.Resource;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import com.gift.bean.Gift_items;import com.gift.dao.Gift_itemsDAO;import com.gift.service.Gift_itemsService;/** * @Repository @Service @Controller，它们分别对应存储层Bean，业务层Bean，和展示层Bean。 * @Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。 * @scope="prototype"来保证每一个请求有一个单独的Action来处理， 避免struts中Action的线程安全问题。 */@Service@SuppressWarnings("rawtypes")public class Gift_itemsServiceImpl implements Gift_itemsService {	private Gift_itemsDAO giftItemsDao;	public Gift_itemsDAO getGiftItemsDao() {		return giftItemsDao;	}	@Resource(name = "gift_itemsDAOImpl")	public void setGift_detailDao(Gift_itemsDAO giftItemsDao) {		this.giftItemsDao = giftItemsDao;	}	public List<Gift_items> findAll() {		return this.giftItemsDao.findAllGiftItems();	}	public List<Gift_items> findItemsByKeyword(Map searchMap, int pageId,			int pageSize) {		return this.giftItemsDao				.findItemsByKeyword(searchMap, pageId, pageSize);	}	public int findItemsCountByKeyword(Map searchMap) {		return this.giftItemsDao.findItemsCountByKeyword(searchMap);	}	public List<Gift_items> findGiftItemsBySlr(List<Integer> list) {		return this.giftItemsDao.findGiftItemsBySlr(list);	}	@Transactional	public int save(Gift_items giftItems) {		return this.giftItemsDao.saveGiftItems(giftItems);	}	@Transactional	public int delete(Gift_items giftItems) {		return this.giftItemsDao.removeGiftItems(giftItems);	}	public Gift_items findByItemNo(String i_no) {		return this.giftItemsDao.findGiftItemsByItemNo(i_no);	}	public Gift_items findById(int i_id) {		return this.giftItemsDao.findGiftItemsById(i_id);	}	@Transactional	public void update(Gift_items giftItems) {		this.giftItemsDao.updateGiftItems(giftItems);	}}