package com.gift.action.gift_lightbox;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.gift.action.BaseAction;
import com.gift.bean.Gift_fujian;
import com.gift.bean.Gift_items;
import com.gift.bean.Gift_peoples;
import com.gift.service.Gift_fujianService;
import com.gift.service.Gift_itemsService;
import com.gift.service.Gift_peoplesService;
import com.gift.tools.GiftTools;
import com.gift.vo.lightbox_item_vo;

@Controller
public class Gift_lightboxAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private Gift_itemsService gift_itemsService;
	private Gift_fujianService gift_fujianService;
	private Gift_peoplesService gift_peoplesService;

	private List<Gift_items> gift_items_list;
	private List<Gift_fujian> gift_fujian_list;
	private List<Gift_peoples> gift_peoples_list;

	private List<lightbox_item_vo> lightboxVoList;

	private Integer radio = 1;
	private Integer select = 0;

	// action selectP
	public String selectP() throws Exception {
		try {
			gift_peoples_list = gift_peoplesService.findByFlag(1);
			return "selectP";
		} catch (Exception e) {
			ByteArrayOutputStream msg = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(msg));
			addActionError("保存出错,堆栈信息如下:</BR>" + msg.toString());
			Logger logger = Logger.getLogger(this.getClass());
			logger.error("programe error !Gift_lightboxAction.java ");
			return "selectP";
		}
	}

	// action showP
	public String showP() throws Exception {

		// 根据条件查询数据
		GiftTools giftTools = new GiftTools();
		switch (radio) {
			case 1 :
				// 全部
				gift_items_list = gift_itemsService.findAll();
				gift_fujian_list = gift_fujianService.findAll();
				gift_peoples_list = gift_peoplesService.findByFlag(1);
				lightboxVoList = giftTools.processLightboxList(gift_items_list,
						gift_fujian_list, gift_peoples_list);
				break;
			case 2 :
				// 中央政治局常务委员会委员(2#)
				gift_fujian_list = gift_fujianService.findAll();
				gift_peoples_list = gift_peoplesService.findByFlag(1);

				// 循环受礼人找出符合条件的受礼人
				List<Gift_peoples> tmp_peoples_list = new ArrayList<Gift_peoples>();
				for (Gift_peoples people : gift_peoples_list) {
					String bm = people.getP_bm();
					String[] strary = bm.split("#");
					boolean ret = false;
					for (int i = 0; i < strary.length; i++) {
						if ("2".equals(strary[i])) {
							ret = true;
							break;
						}
					}
					if (ret) {
						tmp_peoples_list.add(people);
					}
				}
				gift_peoples_list = tmp_peoples_list;

				// 循环受礼人找出符合条件的礼品
				// select * from gift_items where i_slr in (1,2,3)
				List<Integer> list = new ArrayList<Integer>();
				for (int i = 0; i < gift_peoples_list.size(); i++) {
					list.add(gift_peoples_list.get(i).getP_id());
				}
				gift_items_list = gift_itemsService.findGiftItemsBySlr(list);

				lightboxVoList = giftTools.processLightboxList(gift_items_list,
						gift_fujian_list, gift_peoples_list);
				break;
			case 3 :
				// 中央政治局委员
				gift_fujian_list = gift_fujianService.findAll();
				gift_peoples_list = gift_peoplesService.findByFlag(1);

				// 循环受礼人找出符合条件的受礼人
				tmp_peoples_list = new ArrayList<Gift_peoples>();
				for (Gift_peoples people : gift_peoples_list) {
					String bm = people.getP_bm();
					String[] strary = bm.split("#");
					boolean ret = false;
					for (int i = 0; i < strary.length; i++) {
						if ("3".equals(strary[i])) {
							ret = true;
							break;
						}
					}
					if (ret) {
						tmp_peoples_list.add(people);
					}
				}
				gift_peoples_list = tmp_peoples_list;

				// 循环受礼人找出符合条件的礼品
				// select * from gift_items where i_slr in (1,2,3)
				list = new ArrayList<Integer>();
				for (int i = 0; i < gift_peoples_list.size(); i++) {
					list.add(gift_peoples_list.get(i).getP_id());
				}
				gift_items_list = gift_itemsService.findGiftItemsBySlr(list);

				lightboxVoList = giftTools.processLightboxList(gift_items_list,
						gift_fujian_list, gift_peoples_list);
				break;
			case 4 :
				// 中央书记处书记
				gift_fujian_list = gift_fujianService.findAll();
				gift_peoples_list = gift_peoplesService.findByFlag(1);

				// 循环受礼人找出符合条件的受礼人
				tmp_peoples_list = new ArrayList<Gift_peoples>();
				for (Gift_peoples people : gift_peoples_list) {
					String bm = people.getP_bm();
					String[] strary = bm.split("#");
					boolean ret = false;
					for (int i = 0; i < strary.length; i++) {
						if ("4".equals(strary[i])) {
							ret = true;
							break;
						}
					}
					if (ret) {
						tmp_peoples_list.add(people);
					}
				}
				gift_peoples_list = tmp_peoples_list;

				// 循环受礼人找出符合条件的礼品
				// select * from gift_items where i_slr in (1,2,3)
				list = new ArrayList<Integer>();
				for (int i = 0; i < gift_peoples_list.size(); i++) {
					list.add(gift_peoples_list.get(i).getP_id());
				}
				gift_items_list = gift_itemsService.findGiftItemsBySlr(list);

				lightboxVoList = giftTools.processLightboxList(gift_items_list,
						gift_fujian_list, gift_peoples_list);
				break;
			case 5 :
				// 政治局和书记处
				List<lightbox_item_vo> tmp1 = new ArrayList<lightbox_item_vo>();
				List<lightbox_item_vo> tmp2 = new ArrayList<lightbox_item_vo>();

				gift_fujian_list = gift_fujianService.findAll();
				gift_peoples_list = gift_peoplesService.findByFlag(1);

				// 循环受礼人找出符合条件的受礼人
				tmp_peoples_list = new ArrayList<Gift_peoples>();
				for (Gift_peoples people : gift_peoples_list) {
					String bm = people.getP_bm();
					String[] strary = bm.split("#");
					boolean ret = false;
					for (int i = 0; i < strary.length; i++) {
						if ("3".equals(strary[i])) {
							ret = true;
							break;
						}
					}
					if (ret) {
						tmp_peoples_list.add(people);
					}
				}
				gift_peoples_list = tmp_peoples_list;

				// 循环受礼人找出符合条件的礼品
				// select * from gift_items where i_slr in (1,2,3)
				list = new ArrayList<Integer>();
				for (int i = 0; i < gift_peoples_list.size(); i++) {
					list.add(gift_peoples_list.get(i).getP_id());
				}
				gift_items_list = gift_itemsService.findGiftItemsBySlr(list);

				tmp1 = giftTools.processLightboxList(gift_items_list,
						gift_fujian_list, gift_peoples_list);

				gift_fujian_list = gift_fujianService.findAll();
				gift_peoples_list = gift_peoplesService.findByFlag(1);

				// 循环受礼人找出符合条件的受礼人
				tmp_peoples_list = new ArrayList<Gift_peoples>();
				for (Gift_peoples people : gift_peoples_list) {
					String bm = people.getP_bm();
					String[] strary = bm.split("#");
					boolean ret = false;
					for (int i = 0; i < strary.length; i++) {
						if ("4".equals(strary[i])) {
							ret = true;
							break;
						}
					}
					if (ret) {
						tmp_peoples_list.add(people);
					}
				}
				gift_peoples_list = tmp_peoples_list;

				// 循环受礼人找出符合条件的礼品
				// select * from gift_items where i_slr in (1,2,3)
				list = new ArrayList<Integer>();
				for (int i = 0; i < gift_peoples_list.size(); i++) {
					list.add(gift_peoples_list.get(i).getP_id());
				}
				gift_items_list = gift_itemsService.findGiftItemsBySlr(list);

				tmp2 = giftTools.processLightboxList(gift_items_list,
						gift_fujian_list, gift_peoples_list);

				lightboxVoList = new ArrayList<lightbox_item_vo>();
				lightboxVoList.addAll(tmp1);
				lightboxVoList.addAll(tmp2);
				break;
			case 6 :
				// 四副两高
				gift_fujian_list = gift_fujianService.findAll();
				gift_peoples_list = gift_peoplesService.findByFlag(1);

				// 循环受礼人找出符合条件的受礼人
				tmp_peoples_list = new ArrayList<Gift_peoples>();
				for (Gift_peoples people : gift_peoples_list) {
					String bm = people.getP_bm();
					String[] strary = bm.split("#");
					boolean ret = false;
					for (int i = 0; i < strary.length; i++) {
						if ("6".equals(strary[i])) {
							ret = true;
							break;
						}
					}
					if (ret) {
						tmp_peoples_list.add(people);
					}
				}
				gift_peoples_list = tmp_peoples_list;

				// 循环受礼人找出符合条件的礼品
				// select * from gift_items where i_slr in (1,2,3)
				list = new ArrayList<Integer>();
				for (int i = 0; i < gift_peoples_list.size(); i++) {
					list.add(gift_peoples_list.get(i).getP_id());
				}
				gift_items_list = gift_itemsService.findGiftItemsBySlr(list);

				lightboxVoList = giftTools.processLightboxList(gift_items_list,
						gift_fujian_list, gift_peoples_list);
				break;
			case 7 :
				// 历届党和国家领导人
				gift_fujian_list = gift_fujianService.findAll();
				gift_peoples_list = gift_peoplesService.findByFlag(1);

				// 循环受礼人找出符合条件的受礼人
				tmp_peoples_list = new ArrayList<Gift_peoples>();
				for (Gift_peoples people : gift_peoples_list) {
					String bm = people.getP_bm();
					String[] strary = bm.split("#");
					boolean ret = false;
					for (int i = 0; i < strary.length; i++) {
						if ("5".equals(strary[i])) {
							ret = true;
							break;
						}
					}
					if (ret) {
						tmp_peoples_list.add(people);
					}
				}
				gift_peoples_list = tmp_peoples_list;

				// 循环受礼人找出符合条件的礼品
				// select * from gift_items where i_slr in (1,2,3)
				list = new ArrayList<Integer>();
				for (int i = 0; i < gift_peoples_list.size(); i++) {
					list.add(gift_peoples_list.get(i).getP_id());
				}
				gift_items_list = gift_itemsService.findGiftItemsBySlr(list);

				lightboxVoList = giftTools.processLightboxList(gift_items_list,
						gift_fujian_list, gift_peoples_list);
				break;
			case 8 :
				// 单人
				gift_fujian_list = gift_fujianService.findAll();
				Gift_peoples people = gift_peoplesService.findByID(select);
				gift_peoples_list = new ArrayList<Gift_peoples>();
				gift_peoples_list.add(people);
				// 循环受礼人找出符合条件的礼品 select * from gift_items where i_slr=?
				list = new ArrayList<Integer>();
				list.add(people.getP_id());
				gift_items_list = gift_itemsService.findGiftItemsBySlr(list);
				lightboxVoList = giftTools.processLightboxList(gift_items_list,
						gift_fujian_list, gift_peoples_list);
				break;
			default :
				break;
		}
		// 组合成页面需要的数据
		return "showsuccess";
	}

	// get/set
	public Gift_itemsService getGift_itemsService() {
		return gift_itemsService;
	}

	@Resource(name = "gift_itemsServiceImpl")
	public void setGift_itemsService(Gift_itemsService gift_itemsService) {
		this.gift_itemsService = gift_itemsService;
	}

	public Gift_fujianService getGift_fujianService() {
		return gift_fujianService;
	}

	@Resource(name = "gift_fujianServiceImpl")
	public void setGift_fujianService(Gift_fujianService gift_fujianService) {
		this.gift_fujianService = gift_fujianService;
	}

	public Gift_peoplesService getGift_peoplesService() {
		return gift_peoplesService;
	}

	@Resource(name = "gift_peoplesServiceImpl")
	public void setGift_peoplesService(Gift_peoplesService gift_peoplesService) {
		this.gift_peoplesService = gift_peoplesService;
	}

	public List<Gift_items> getGift_items_list() {
		return gift_items_list;
	}

	public void setGift_items_list(List<Gift_items> gift_items_list) {
		this.gift_items_list = gift_items_list;
	}

	public List<Gift_fujian> getGift_fujian_list() {
		return gift_fujian_list;
	}

	public void setGift_fujian_list(List<Gift_fujian> gift_fujian_list) {
		this.gift_fujian_list = gift_fujian_list;
	}

	public List<Gift_peoples> getGift_peoples_list() {
		return gift_peoples_list;
	}

	public void setGift_peoples_list(List<Gift_peoples> gift_peoples_list) {
		this.gift_peoples_list = gift_peoples_list;
	}

	public List<lightbox_item_vo> getLightboxVoList() {
		return lightboxVoList;
	}

	public void setLightboxVoList(List<lightbox_item_vo> lightboxVoList) {
		this.lightboxVoList = lightboxVoList;
	}

	public Integer getRadio() {
		return radio;
	}

	public void setRadio(Integer radio) {
		this.radio = radio;
	}

	public Integer getSelect() {
		return select;
	}

	public void setSelect(Integer select) {
		this.select = select;
	}

}