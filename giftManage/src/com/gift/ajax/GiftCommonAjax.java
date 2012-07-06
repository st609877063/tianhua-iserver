package com.gift.ajax;

import javax.annotation.Resource;

import com.gift.bean.Gift_cangku;
import com.gift.bean.Gift_fujian;
import com.gift.bean.Gift_items;
import com.gift.service.Gift_cangkuService;
import com.gift.service.Gift_fujianService;
import com.gift.service.Gift_itemsService;

public class GiftCommonAjax{

	private Gift_fujianService giftFujianService;
	private Gift_itemsService giftItemsService;
	private Gift_cangkuService giftCangkuService;

	/***************************** gift_item相关操作 ********************************/
	// 添加礼品时，验证礼品编号是否重复
	public String itemNoIsExist(String itemNo) {
		Gift_items itemVo = this.giftItemsService.findByItemNo(itemNo);

		if (itemVo != null && itemVo.getI_no() != null && !itemVo.getI_no().equals("")) {
			return "EXIST";
		} else {
			return "NOEXIST";
		}
	}
	
	// 删除礼品
	public String deleteItemById(String idstr) {
		int i_id = Integer.parseInt(idstr);
		Gift_items itemVo = this.giftItemsService.findById(i_id);
		int result = this.giftItemsService.delete(itemVo); //删除item
		if (result == 1) {
			Gift_fujian giftFujian = new Gift_fujian();
			giftFujian.setFj_i_no(itemVo.getI_no());
			result = this.giftFujianService.deleteByItemNo(giftFujian); //删除附件信息
			
			Gift_cangku gift_cangku = new Gift_cangku();
			gift_cangku.setI_no(itemVo.getI_no());
			result = this.giftCangkuService.deleteByItemNo(gift_cangku); //删除仓库信息
			
			return "SUCCESS";
		} else {
			return "FAILED";
		}
	}
	/***************************** gift_item相关操作 ********************************/

	/***************************** gift_fujian相关操作 ********************************/
	// 删除附件
	public String deleteFujianById(String idstr) {
		int fjId = Integer.parseInt(idstr);
		int result = this.giftFujianService.deleteById(fjId);
		if (result == 1) {
			return "SUCCESS";
		} else {
			return "FAILED";
		}
	}
	/***************************** gift_fujian相关操作 ********************************/

	/***************************** gift_cangku相关操作 ********************************/
	// 删除附件
	public String deleteCangkuByItemID(String ck_id) {
		int ckId = Integer.parseInt(ck_id);
		int result = this.giftCangkuService.deleteById(ckId);
		if (result == 1) {
			return "SUCCESS";
		} else {
			return "FAILED";
		}
	}
	/***************************** gift_cangku相关操作 ********************************/

	public Gift_fujianService getGiftFujianService() {
		return giftFujianService;
	}

	@Resource(name = "gift_fujianServiceImpl")
	public void setGiftFujianService(Gift_fujianService giftFujianService) {
		this.giftFujianService = giftFujianService;
	}

	public Gift_itemsService getGiftItemsService() {
		return giftItemsService;
	}
	
	@Resource(name = "gift_itemsServiceImpl")
	public void setGiftItemsService(Gift_itemsService giftItemsService) {
		this.giftItemsService = giftItemsService;
	}
	
	public Gift_cangkuService getGiftCangkuService() {
		return giftCangkuService;
	}
	
	@Resource(name = "gift_cangkuServiceImpl")
	public void setGiftCangkuService(Gift_cangkuService giftCangkuService) {
		this.giftCangkuService = giftCangkuService;
	}

}