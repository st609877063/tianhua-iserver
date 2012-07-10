package com.res.action;

import java.util.ArrayList;
import java.util.List;

import com.res.bean.ResItems;
import com.res.bean.ResMenus;
import com.res.manager.ResItemsManager;
import com.res.manager.ResMenuManager;
import com.res.tools.DateTools;

public class ResMenuAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private List<ResMenus> rtnList = new ArrayList<ResMenus>();
	private List<ResItems> zhuItemsList = new ArrayList<ResItems>();
	private List<ResItems> fuItemsList = new ArrayList<ResItems>();
	
	
	public String showMenu() throws Exception {
		
		int nowTime = DateTools.getTimestamp().intValue();
		
		ResMenuManager menuManager = new ResMenuManager();
		rtnList = menuManager.getResMenusByDate(null);
		
		return "success";
	}

	
	public String toSaveMenu() throws Exception {
		ResItemsManager itemsManager = new ResItemsManager();
		zhuItemsList = itemsManager.getResItemsByItemType(1);
		fuItemsList = itemsManager.getResItemsByItemType(2);
		
		
		return "success";
	}
	
	
	public List<ResMenus> getRtnList() {
		return rtnList;
	}

	public void setRtnList(List<ResMenus> rtnList) {
		this.rtnList = rtnList;
	}

	public List<ResItems> getZhuItemsList() {
		return zhuItemsList;
	}

	public void setZhuItemsList(List<ResItems> zhuItemsList) {
		this.zhuItemsList = zhuItemsList;
	}

	public List<ResItems> getFuItemsList() {
		return fuItemsList;
	}

	public void setFuItemsList(List<ResItems> fuItemsList) {
		this.fuItemsList = fuItemsList;
	}
	
	
}
