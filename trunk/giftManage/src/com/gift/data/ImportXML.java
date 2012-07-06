package com.gift.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.gift.bean.Gift_cangku;
import com.gift.bean.Gift_fujian;
import com.gift.bean.Gift_items;
import com.gift.bean.Gift_peoples;
import com.gift.tools.DateTools;

public class ImportXML {

	@SuppressWarnings({"unused", "rawtypes"})
	public void parseXml(File xmlFile) {
		try {
			// File xmlFile = new File(xmlFileName);
			SAXReader reader = new SAXReader();
			Document xmlDoc = reader.read(xmlFile);
			Element root = xmlDoc.getRootElement();

			// items
			List<Gift_items> giftItemsList = new ArrayList<Gift_items>();
			List itemsNodesList = xmlDoc.selectNodes("//gifts/items");
			for (Iterator iterator1 = itemsNodesList.iterator(); iterator1
					.hasNext();) {
				Element element1 = (Element) iterator1.next();
				Iterator iterator2 = element1.elementIterator();
				Gift_items giftItems = new Gift_items();
				while (iterator2.hasNext()) {
					Element element2 = (Element) iterator2.next();
					if (element2.getName().equals("i_no")) {
						giftItems.setI_no(element2.getText());
					} else if (element2.getName().equals("i_name")) {
						giftItems.setI_name(element2.getText());
					} else if (element2.getName().equals("i_zlr")) {
						giftItems
								.setI_zlr(Integer.parseInt(element2.getText()));
					} else if (element2.getName().equals("i_slr")) {
						giftItems
								.setI_slr(Integer.parseInt(element2.getText()));
					} else if (element2.getName().equals("i_sztime")) {
						String i_sztime = element2.getText();
						Integer i_sztime_long = DateTools
								.getTimestamp(i_sztime).intValue();
						giftItems.setI_sztime(i_sztime_long);
					} else if (element2.getName().equals("i_unit")) {
						giftItems.setI_unit(element2.getText());
					} else if (element2.getName().equals("i_num")) {
						giftItems.setI_num(getInteger(element2.getText()));
					} else if (element2.getName().equals("i_zhidi")) {
						giftItems.setI_zhidi(element2.getText());
					} else if (element2.getName().equals("i_type")) {
						giftItems.setI_type(element2.getText());
					} else if (element2.getName().equals("i_chandi")) {
						giftItems.setI_chandi(element2.getText());
					} else if (element2.getName().equals("i_status")) {
						giftItems.setI_status(element2.getText());
					} else if (element2.getName().equals("i_gongyi")) {
						giftItems.setI_gongyi(element2.getText());
					} else if (element2.getName().equals("i_background")) {
						giftItems.setI_background(element2.getText());
					} else if (element2.getName().equals("i_desc")) {
						giftItems.setI_desc(element2.getText());
					} else if (element2.getName().equals("i_memo")) {
						giftItems.setI_memo(element2.getText());
					} else if (element2.getName().equals("i_attribute1")) {
						giftItems.setI_attribute1(element2.getText());
					} else if (element2.getName().equals("i_attribute2")) {
						giftItems.setI_attribute2(element2.getText());
					} else if (element2.getName().equals("i_attribute3")) {
						giftItems.setI_attribute3(element2.getText());
					} else if (element2.getName().equals("i_attribute4")) {
						giftItems.setI_attribute4(element2.getText());
					} else if (element2.getName().equals("i_attribute5")) {
						giftItems.setI_attribute5(element2.getText());
					}
				}
				giftItemsList.add(giftItems);
			}

			// fujian
			List<Gift_fujian> GiftFujianList = new ArrayList<Gift_fujian>();
			List fujianNodesList = xmlDoc.selectNodes("//gifts/fujian");
			for (Iterator iterator1 = fujianNodesList.iterator(); iterator1
					.hasNext();) {
				Element element1 = (Element) iterator1.next();
				Iterator iterator2 = element1.elementIterator();
				Gift_fujian giftFujian = new Gift_fujian();
				while (iterator2.hasNext()) {
					Element element2 = (Element) iterator2.next();
					if (element2.getName().equals("fj_i_no")) {
						giftFujian.setFj_i_no(element2.getText());
					} else if (element2.getName().equals("fj_name")) {
						giftFujian.setFj_name(element2.getText());
					} else if (element2.getName().equals("fj_path")) {
						giftFujian.setFj_path(element2.getText());
					} else if (element2.getName().equals("fj_desc")) {
						giftFujian.setFj_desc(element2.getText());
					} else if (element2.getName().equals("fj_attribute1")) {
						giftFujian.setFj_attribute1(element2.getText());
					} else if (element2.getName().equals("fj_attribute2")) {
						giftFujian.setFj_attribute2(element2.getText());
					} else if (element2.getName().equals("fj_attribute3")) {
						giftFujian.setFj_attribute3(element2.getText());
					} else if (element2.getName().equals("fj_attribute4")) {
						giftFujian.setFj_attribute4(element2.getText());
					} else if (element2.getName().equals("fj_attribute5")) {
						giftFujian.setFj_attribute5(element2.getText());
					}
				}
				GiftFujianList.add(giftFujian);
			}

			// cangku
			List<Gift_cangku> GiftCangkuList = new ArrayList<Gift_cangku>();
			List cangkuNodesList = xmlDoc.selectNodes("//gifts/cangku");
			for (Iterator iterator1 = cangkuNodesList.iterator(); iterator1
					.hasNext();) {
				Element element1 = (Element) iterator1.next();
				Iterator iterator2 = element1.elementIterator();
				Gift_cangku giftCangku = new Gift_cangku();
				while (iterator2.hasNext()) {
					Element element2 = (Element) iterator2.next();
					if (element2.getName().equals("ck_i_no")) {
						giftCangku.setCk_i_no(element2.getText());
					} else if (element2.getName().equals("ck_kufang")) {
						giftCangku.setCk_kufang(element2.getText());
					} else if (element2.getName().equals("ck_huojia")) {
						giftCangku.setCk_huojia(element2.getText());
					} else if (element2.getName().equals("ck_ceng")) {
						giftCangku.setCk_ceng(element2.getText());
					} else if (element2.getName().equals("ck_attribute1")) {
						giftCangku.setCk_attribute1(element2.getText());
					} else if (element2.getName().equals("ck_attribute2")) {
						giftCangku.setCk_attribute2(element2.getText());
					} else if (element2.getName().equals("ck_attribute3")) {
						giftCangku.setCk_attribute3(element2.getText());
					} else if (element2.getName().equals("ck_attribute4")) {
						giftCangku.setCk_attribute4(element2.getText());
					} else if (element2.getName().equals("ck_attribute5")) {
						giftCangku.setCk_attribute5(element2.getText());
					}
				}
				GiftCangkuList.add(giftCangku);
			}

			// peoples
			List<Gift_peoples> GiftPeoplesList = new ArrayList<Gift_peoples>();
			List peoplesNodesList = xmlDoc.selectNodes("//gifts/peoples");
			for (Iterator iterator1 = peoplesNodesList.iterator(); iterator1
					.hasNext();) {
				Element element1 = (Element) iterator1.next();
				Iterator iterator2 = element1.elementIterator();
				Gift_peoples giftPeoples = new Gift_peoples();
				while (iterator2.hasNext()) {
					Element element2 = (Element) iterator2.next();
					if (element2.getName().equals("p_name")) {
						giftPeoples.setP_name(element2.getText());
					} else if (element2.getName().equals("p_flag")) {
						giftPeoples.setP_flag(getInteger(element2.getText()));
					} else if (element2.getName().equals("p_type")) {
						giftPeoples.setP_type(getInteger(element2.getText()));
					} else if (element2.getName().equals("p_spouse")) {
						giftPeoples.setP_spouse(element2.getText());
					} else if (element2.getName().equals("p_country")) {
						giftPeoples.setP_country(element2.getText());
					} else if (element2.getName().equals("p_bm")) {
						giftPeoples.setP_bm(element2.getText());
					} else if (element2.getName().equals("p_title")) {
						giftPeoples.setP_title(element2.getText());
					} else if (element2.getName().equals("p_attribute1")) {
						giftPeoples.setP_attribute1(element2.getText());
					} else if (element2.getName().equals("p_attribute2")) {
						giftPeoples.setP_attribute2(element2.getText());
					} else if (element2.getName().equals("p_attribute3")) {
						giftPeoples.setP_attribute3(element2.getText());
					} else if (element2.getName().equals("p_attribute4")) {
						giftPeoples.setP_attribute4(element2.getText());
					} else if (element2.getName().equals("p_attribute5")) {
						giftPeoples.setP_attribute5(element2.getText());
					}
				}
				GiftPeoplesList.add(giftPeoples);
			}

		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	private Integer getInteger(String val) {
		try {
			Integer tmp = Integer.parseInt(val);
			return tmp;
		} catch (Exception ex) {
			return null;
		}
	}

}