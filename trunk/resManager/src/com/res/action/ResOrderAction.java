package com.res.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.res.bean.ResMenus;
import com.res.bean.ResOrderMenu;
import com.res.bean.ResOrders;
import com.res.manager.ResMenuManager;
import com.res.manager.ResOrderManager;
import com.res.tools.DateTools;
import com.res.tools.FileTools;

public class ResOrderAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String showDate;
	private String menuType;
	private List<ResMenus> rtnList = new ArrayList<ResMenus>();
	
	//订单提交参数
	private String totalMoney;
	private String username;
	private String userphone;
	private String menuOrder;
	private String orderDate;
	private String orderResult="";
	
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
	
	
	public String showOrderMenu() throws Exception {
		if(showDate == null || showDate.equals("")) {
			showDate = DateTools.getDisplayTime(DateTools.getTimestamp());
		}
		int type = 1;
		if(menuType != null && !menuType.equals("")) {
			type = 	Integer.parseInt(menuType);
		} else {
			menuType = "1";
		}
		
		ResMenuManager menuManager = new ResMenuManager();
		rtnList = menuManager.getResMenusByDateType(showDate, type);
		
		return "success";
	}

	
	
	public String saveOrder() throws Exception {
		if(totalMoney == null || totalMoney.equals("") || username == null || username.equals("") 
				|| userphone == null || userphone.equals("") || menuOrder == null || menuOrder.equals("") 
					|| orderDate == null || orderDate.equals("")) {
			orderResult = "failed";
			return "failed";
		}
		
		//添加订单
		ResOrderManager resOrderManager = new ResOrderManager();
		ResOrders resOrderVo = new ResOrders();
		resOrderVo.setOrderCreatetime(DateTools.getTimestamp().intValue());
		resOrderVo.setOrderDate(orderDate);
		resOrderVo.setOrderFee(totalMoney);
		resOrderVo.setOrderMemo("");
		resOrderVo.setOrderNo(DateTools.getTimestamp().intValue());
		resOrderVo.setOrderPhone(userphone);
		resOrderVo.setOrderUser(username);
		int saveOrderResult = resOrderManager.saveResOrder(resOrderVo);
		
		//添加订单菜品
		if(saveOrderResult == 1) {
			List<ResOrderMenu> resOmList = new ArrayList<ResOrderMenu>();
			ResOrderMenu resOm = null;
			String itemStr = null;
			String[] idmoneyArary = null;
			String[] itemsArray = menuOrder.split("#");
			for(int i=0; itemsArray!=null && i<itemsArray.length; i++) {
				itemStr = itemsArray[i];
				if(itemStr != null && !itemStr.equals("")) {
					idmoneyArary = itemStr.split("=");
					if(idmoneyArary != null && idmoneyArary.length == 2) {
						resOm = new ResOrderMenu();
						resOm.setOrderNo(resOrderVo.getOrderNo());
						resOm.setItemId(Integer.parseInt(idmoneyArary[0]));
						resOm.setItemNo("");
						resOm.setOmMoney("");
						resOm.setOmNum(Integer.parseInt(idmoneyArary[1]));
						resOm.setOmTotal("");
						resOm.setOmCreatetime(DateTools.getTimestamp().intValue());
						
						resOmList.add(resOm);
					}
				}
			}
			
			resOrderManager.saveResOrderMemu(resOmList);
		} else {
			return "failed";
		}
		
		orderResult = String.valueOf(resOrderVo.getOrderNo());
		return "success";
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

	public List<ResMenus> getRtnList() {
		return rtnList;
	}

	public void setRtnList(List<ResMenus> rtnList) {
		this.rtnList = rtnList;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getOrderResult() {
		return orderResult;
	}

	public void setOrderResult(String orderResult) {
		this.orderResult = orderResult;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
}
