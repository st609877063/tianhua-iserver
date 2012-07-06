package com.gift.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gift.bean.Gift_items;
import com.gift.service.Gift_cangkuService;
import com.gift.service.Gift_fujianService;
import com.gift.service.Gift_itemsService;
import com.gift.service.Gift_peoplesService;

public class myTest {

	Gift_itemsService items_service = null;
	Gift_peoplesService peoples_service = null;
	Gift_fujianService fujian_service = null;
	Gift_cangkuService cangku_service = null;

	@Before
	public void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
		Gift_itemsService s1 = (Gift_itemsService) context
				.getBean("gift_itemsServiceImpl");
		Gift_peoplesService s2 = (Gift_peoplesService) context
				.getBean("gift_peoplesServiceImpl");
		Gift_fujianService s3 = (Gift_fujianService) context
				.getBean("gift_fujianServiceImpl");
		Gift_cangkuService s4 = (Gift_cangkuService) context
				.getBean("gift_cangkuServiceImpl");
		this.items_service = s1;
		this.peoples_service = s2;
		this.fujian_service = s3;
		this.cangku_service = s4;
	}

	@Test
	public void doTest() {
		// List<Gift_cangku> list = service.findByI_NO("adsfasd");
		// for (Gift_cangku bean : list) {
		// System.out.println(bean.getI_name());
		// }

		// Gift_cangku tmp = service.findByI_NO("adsfasd");
		// System.out.println(tmp.getI_name());

		// List<Gift_items> items_list = items_service.findAll();
		// List<Gift_peoples> peoples_list = peoples_service.findAll();
		// List<Gift_fujian> fujian_list = fujian_service.findAll();
		// List<Gift_cangku> cangku_list = cangku_service.findAll(0, 9999);
		//
		// ExportXML ex = new ExportXML();
		// ex.writeXml(items_list, fujian_list, peoples_list, cangku_list,
		// "D:\\aaa.xml");

		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(3);
		List<Gift_items> gift_items_list = items_service
				.findGiftItemsBySlr(list);
		for (Gift_items vo : gift_items_list) {
			System.out.println(vo.getI_id());
		}
	}
}