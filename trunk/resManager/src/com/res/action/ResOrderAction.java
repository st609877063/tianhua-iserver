package com.res.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
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
	private List<ResOrders> orderList = new ArrayList<ResOrders>();
	
	//订单保存提交参数
	private String totalMoney;
	private String username;
	private String userphone;
	private String menuOrder;
	private String orderDate;
	private String orderMenuType;
	private String orderResult="";
	
	//订单查询参数(前台)
	private String usernameSrh;
	private String userphoneSrh;
	private String orderNoSrh;
	
	//订单查询参数(后台)
	private String orderDateSrh;
	private String orderTypeSrh;
	
	private boolean validUser = true;
	
	public void validate() {
		//不用频繁读取cookie.建议使用此写法
		if(getCookieUid() == null || getCookieUid() == -1) {
			setCookieInfo();
			if(getCookieUid() == null){
				setCookieUid(-1);
			}
		}
		if(getCookieUserName() != null && !getCookieUserName().equals("")) {
			validUser = false;
		}
		
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
					|| orderDate == null || orderDate.equals("") || orderMenuType == null || orderMenuType.equals("")) {
			orderResult = "failed";
			return "failed";
		}
		
		//添加订单
		ResOrderManager resOrderManager = new ResOrderManager();
		ResOrders resOrderVo = new ResOrders();
		resOrderVo.setOrderCreatetime(DateTools.getTimestamp().intValue());
		resOrderVo.setOrderDate(orderDate);
		resOrderVo.setOrderFee(totalMoney);
		resOrderVo.setOrderType(Integer.parseInt(orderMenuType));
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
	
	public String saveOrderResult() throws Exception {
		return "success";
	}
	
	public String searchOrder() throws Exception {
		if(orderNoSrh == null || orderNoSrh.equals("") ) {
			if((usernameSrh == null || usernameSrh.equals("")) && (userphoneSrh == null || userphoneSrh.equals(""))) {
				return "failed";
			}
		}
		ResOrderManager resOrderManager = new ResOrderManager();
		orderList = resOrderManager.getOrderList(orderNoSrh, usernameSrh, userphoneSrh, null, -1);
		
		return "success";
	}
	
	
	public String showOrder() throws Exception {
		if(validUser) {
			return Action.LOGIN;
		}
		
		int orderType = -1;
		if(orderTypeSrh != null && !orderTypeSrh.equals("")) {
			orderType = Integer.parseInt(orderTypeSrh);
		}
		ResOrderManager resOrderManager = new ResOrderManager();
		orderList = resOrderManager.getOrderList(orderNoSrh, usernameSrh, userphoneSrh, orderDateSrh, orderType);
		
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

	public String getUsernameSrh() {
		return usernameSrh;
	}

	public void setUsernameSrh(String usernameSrh) {
		this.usernameSrh = usernameSrh;
	}

	public String getUserphoneSrh() {
		return userphoneSrh;
	}

	public void setUserphoneSrh(String userphoneSrh) {
		this.userphoneSrh = userphoneSrh;
	}

	public String getOrderNoSrh() {
		return orderNoSrh;
	}

	public void setOrderNoSrh(String orderNoSrh) {
		this.orderNoSrh = orderNoSrh;
	}

	public List<ResOrders> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<ResOrders> orderList) {
		this.orderList = orderList;
	}

	public String getOrderMenuType() {
		return orderMenuType;
	}

	public void setOrderMenuType(String orderMenuType) {
		this.orderMenuType = orderMenuType;
	}

	public String getOrderDateSrh() {
		return orderDateSrh;
	}

	public void setOrderDateSrh(String orderDateSrh) {
		this.orderDateSrh = orderDateSrh;
	}

	public String getOrderTypeSrh() {
		return orderTypeSrh;
	}

	public void setOrderTypeSrh(String orderTypeSrh) {
		this.orderTypeSrh = orderTypeSrh;
	}
	
}
