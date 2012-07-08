package com.res.action;

import java.util.ArrayList;
import java.util.List;

import com.res.bean.ResMenus;
import com.res.manager.ResMenuManager;
import com.res.tools.DateTools;

public class ResMenuAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private List<ResMenus> rtnList = new ArrayList<ResMenus>();
	
	
	public String showMenu() throws Exception {
		
		int nowTime = DateTools.getTimestamp().intValue();
		String nowDate = "2012-07-08";
		
		ResMenuManager manager = new ResMenuManager();
		rtnList = manager.getResMenusByDate(nowDate);
		
		
		return "success";
	}

	
	public List<ResMenus> getRtnList() {
		return rtnList;
	}

	public void setRtnList(List<ResMenus> rtnList) {
		this.rtnList = rtnList;
	}
	
	
}
