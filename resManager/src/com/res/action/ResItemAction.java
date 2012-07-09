package com.res.action;

import java.util.ArrayList;
import java.util.List;

import com.res.bean.ResItems;
import com.res.manager.ResItemsManager;

public class ResItemAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private List<ResItems> rtnList = new ArrayList<ResItems>();
	
	// ----------关键字查询 start----------
	private String srhItemNo="";
	// ----------关键字查询  start----------
	
	public String showItems() throws Exception {
		ResItemsManager manager = new ResItemsManager();
		rtnList = manager.getResItems(srhItemNo);
		
		return "success";
	}

	
	public String toSaveItem() throws Exception {
		return "success";
	}
	
	public String saveItems() throws Exception {
		ResItemsManager manager = new ResItemsManager();
		
		rtnList = manager.getResItems(srhItemNo);
		return "success";
	}
	
	
	public String getSrhItemNo() {
		return srhItemNo;
	}

	public void setSrhItemNo(String srhItemNo) {
		this.srhItemNo = srhItemNo;
	}

	public List<ResItems> getRtnList() {
		return rtnList;
	}

	public void setRtnList(List<ResItems> rtnList) {
		this.rtnList = rtnList;
	}

}
