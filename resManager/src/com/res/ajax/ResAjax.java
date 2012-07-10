package com.res.ajax;

import com.res.bean.ResItems;
import com.res.manager.ResItemsManager;

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
	
	
}
