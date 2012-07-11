package com.res.ajax;

import java.util.List;

import com.res.bean.ResItems;
import com.res.bean.ResMenus;
import com.res.manager.ResItemsManager;
import com.res.manager.ResMenuManager;

public class ResAjax{

	public String itemNoIsExist(String itemNo) {
		ResItemsManager manager = new ResItemsManager();
		ResItems itemVo = manager.findResItemsByItemNo(itemNo);

		if (itemVo != null && itemVo.getItemNo() != null && !itemVo.getItemNo().equals("")) {
			return "EXIST";
		} else {
			return "NOEXIST";
		}
	}
	
	
	// 删除菜品
	public String deleteItemById(String idstr) {
		int i_id = Integer.parseInt(idstr);
		return "SUCCESS";
	}
	
	
	public String menuIsExist(String menuDate, String menuType) {
		ResMenuManager menuManager = new ResMenuManager();
		int mType = Integer.parseInt(menuType);
		List<ResMenus> resMenu = menuManager.getResMenusByDateType(menuDate, mType);

		if (resMenu != null && resMenu.size() >0 ) {
			return "EXIST";
		} else {
			return "NOEXIST";
		}
	}
	
	
	public String deleteMuenItemById(String idstr) {
		int pkId = Integer.parseInt(idstr);
		ResMenuManager menuManager = new ResMenuManager();
		int result = menuManager.deleteResMuenByPkId(pkId);
		if (result == 1) {
			return "SUCCESS";
		} else {
			return "FAILED";
		}
	}
	
	public String updateMuenItemMoneyById(String idstr, String money) {
		int pkId = Integer.parseInt(idstr);
		ResMenuManager menuManager = new ResMenuManager();
		int result = menuManager.updateMuenItemMoneyByPkId(pkId, money);
		if (result == 1) {
			return "SUCCESS";
		} else {
			return "FAILED";
		}
	}
	
}
