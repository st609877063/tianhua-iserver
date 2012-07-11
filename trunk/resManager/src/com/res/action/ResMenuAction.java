package com.res.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.struts2.ServletActionContext;

import com.res.bean.ResItems;
import com.res.bean.ResMenus;
import com.res.manager.ResItemsManager;
import com.res.manager.ResMenuManager;
import com.res.tools.DateTools;
import com.res.tools.FileTools;

public class ResMenuAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ResMenus resMenu;
	private String menuItems; //保存，使用
	private String showDate;
	private String menuType;
	
	private List<ResMenus> rtnList = new ArrayList<ResMenus>();
	private List<ResItems> zhuItemsList = new ArrayList<ResItems>();
	private List<ResItems> fuItemsList = new ArrayList<ResItems>();
	
	public void validate() {
		/**********检查fujian目录下是否有default.jpg存在********/
		String fujianPath = ServletActionContext.getServletContext().getRealPath("/fujian");
		if(!FileTools.fileExist(fujianPath)) {
			FileTools.mkdir(fujianPath);
		}
		String fujianDefaultsavePath = ServletActionContext.getServletContext().getRealPath("/fujian")+ File.separator + "default.jpg";
		if(!FileTools.fileExist(fujianDefaultsavePath)) {
			String imgDefaultsavePath = ServletActionContext.getServletContext().getRealPath("/images")+ File.separator + "default.jpg";
			File imgDefault = new File(imgDefaultsavePath);
			File fujianDefault = new File(fujianDefaultsavePath);
			FileTools.copy(imgDefault, fujianDefault);
		}
		/**********检查fujian目录下是否有default.jpg存在********/
	}
	
	
	public String showMenu() throws Exception {
		try {
			ResMenuManager menuManager = new ResMenuManager();
			List<ResMenus> menuDateTypeList = menuManager.getDistinctMenusDateType(showDate, -1);
			
			Map<Integer, String> itemTypeMap = new HashMap<Integer, String>();
			itemTypeMap.put(1, "主食");
			itemTypeMap.put(2, "副食");
			
			List<ResMenus> resMenusList = null;
			ResMenus menuVo = null;
			ResMenus menuVo2 = null;
			ResMenus menuMapVo = null;
			String itemContent = null;
			Map<Integer, ResMenus> itemMap = null;
			for(int i=0; menuDateTypeList!=null&&i<menuDateTypeList.size(); i++) {
				menuVo = menuDateTypeList.get(i);
				resMenusList = menuManager.getResMenusByDateType(menuVo.getMenuDate(), menuVo.getMenuType());
				
				itemMap = new HashMap<Integer, ResMenus>();
				for(int j=0; resMenusList!=null&&j<resMenusList.size(); j++) {
					menuVo2 = resMenusList.get(j);
					if(!itemMap.containsKey(menuVo2.getItemType())) {
						itemContent = itemTypeMap.get(menuVo2.getItemType()) + ": [" + menuVo2.getItemName() + ",价钱：" + menuVo2.getMenuMoney() + "]";
						menuVo2.setItemContent(itemContent);
						itemMap.put(menuVo2.getItemType(), menuVo2);
					} else {
						menuMapVo = itemMap.get(menuVo2.getItemType());
						itemContent = menuMapVo.getItemContent() + ", [" + menuVo2.getItemName() + ",价钱：" + menuVo2.getMenuMoney() + "]";
						menuMapVo.setItemContent(itemContent);
						itemMap.put(menuMapVo.getItemType(), menuMapVo);
					}
				}
				
				itemContent = "";
				for(Map.Entry<Integer, ResMenus> entry: itemMap.entrySet()) {
					itemContent = itemContent + entry.getValue().getItemContent();
				}
				menuVo.setItemContent(itemContent);
				
				rtnList.add(menuVo);
			}
			
			
		} catch (Exception e) {
			return "failed";
		}
		
		return "success";
	}

	
	public String showMenuDetail() throws Exception {
		if(showDate == null || showDate.equals("") || menuType == null || menuType.equals("") ) {
			return "failed";
		}
		ResMenuManager menuManager = new ResMenuManager();
		rtnList = menuManager.getResMenusByDateType(showDate, Integer.parseInt(menuType));
		
		return "success";
	}
	
	
	
	public String toSaveMenu() throws Exception {
		ResItemsManager itemsManager = new ResItemsManager();
		zhuItemsList = itemsManager.getResItemsByItemType(1);
		fuItemsList = itemsManager.getResItemsByItemType(2);
		
		return "success";
	}
	
	
	
	public String saveMenu() throws Exception {
		//处理menuItems参数
		if(menuItems == null || menuItems.equals("")) {
			return "failed";
		}
		List<ResMenus> resMenuList = new ArrayList<ResMenus>();
		ResMenus resMenu = null;
		String itemStr = null;
		String[] idmoneyArary = null;
		String[] itemsArray = menuItems.split("#");
		for(int i=0; itemsArray!=null && i<itemsArray.length; i++) {
			itemStr = itemsArray[i];
			if(itemStr != null && !itemStr.equals("")) {
				idmoneyArary = itemStr.split("=");
				if(idmoneyArary != null && idmoneyArary.length == 2) {
					resMenu = new ResMenus();
					resMenu.setItemId(Integer.parseInt(idmoneyArary[0]));
					resMenu.setMenuMoney(idmoneyArary[1]);
					resMenu.setMenuDate(this.resMenu.getMenuDate());
					resMenu.setMenuType(this.resMenu.getMenuType());
					resMenu.setMenuMemo("");
					resMenu.setMenuAdduser(0);
					resMenu.setMenuCreatetime(DateTools.getTimestamp().intValue());
					
					resMenuList.add(resMenu);
				}
			}
		}
		
		ResMenuManager menuManager = new ResMenuManager();
		try {
			menuManager.saveResMenu(resMenuList);
		} catch (Exception e) {
			return "failed";
		}
		
		showDate = this.resMenu.getMenuDate(); //跳转此日期的菜单列表
		return "success";
	}
	
	
	public String toUpdateMenu() throws Exception {
		if(showDate == null || showDate.equals("") || menuType == null || menuType.equals("") ) {
			return "failed";
		}
		ResMenuManager menuManager = new ResMenuManager();
		rtnList = menuManager.getResMenusByDateType(showDate, Integer.parseInt(menuType));
		
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

	public ResMenus getResMenu() {
		return resMenu;
	}

	public void setResMenu(ResMenus resMenu) {
		this.resMenu = resMenu;
	}

	public String getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(String menuItems) {
		this.menuItems = menuItems;
	}

	public String getShowDate() {
		return showDate;
	}

	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}


	public String getMenuType() {
		return menuType;
	}


	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	
}
