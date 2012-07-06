package com.gift.action.gift_Items;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.gift.action.BaseAction;
import com.gift.bean.Gift_cangku;
import com.gift.bean.Gift_items;
import com.gift.bean.Gift_peoples;
import com.gift.service.Gift_cangkuService;
import com.gift.service.Gift_itemsService;
import com.gift.service.Gift_peoplesService;
import com.gift.tools.DateTools;
import com.gift.tools.FileTools;
import com.gift.tools.GiftTools;
import com.gift.tools.QRCodeTool;

@Controller
public class Gift_itemsAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	// 修改时必须传入的礼品ID
	private String updateP_i_id;

	private Gift_items giftItems;
	private Gift_cangku cangku;
	private Gift_peoples slr;
	private Gift_peoples zlr;
	private List<Gift_items> giftItemsList;
	private List<Gift_peoples> slr_list;
	private List<Gift_peoples> zlr_list;

	private Gift_itemsService giftItemsService;
	private Gift_peoplesService giftPeoplesService;
	private Gift_cangkuService giftCangkuService;

	// 受赠时间
	private String strSztime;
	
	// ----------分页 start----------
	private int pageNow = 1; // 页码数
	private int pageSize = 20; // 页面行数
	private int rowCount = 0;// 总行数
	private int pageCount = 1; // 总页数
	// ----------分页 end----------

	// ----------关键字查询 start----------
	private String srhItemNo;
	private String srhItemName;
	// ----------关键字查询  start----------
		
	@SuppressWarnings({"unchecked", "rawtypes"})
	public String list() throws Exception {
		//参数和Gift_items.xml中相匹配
		Map searchMap = new HashMap();
		searchMap.put("itemNo", srhItemNo);
		searchMap.put("itemName", srhItemName);
		
		// 关键字查询
		rowCount = this.giftItemsService.findItemsCountByKeyword(searchMap);// 计算出实际记录数
		pageCount = rowCount / pageSize;// 计算出实际页数
		if ((rowCount % pageSize != 0) || (pageCount == 0)) {
			pageCount++;
		}
		this.giftItemsList = this.giftItemsService.findItemsByKeyword(searchMap, (pageNow - 1) * pageSize, pageSize);
		
		//取出的数据再做处理
		GiftTools giftTool = new GiftTools();
		List<Gift_peoples> peopleList = this.giftPeoplesService.findAll();
		this.giftItemsList = giftTool.processGiftItemList(giftItemsList, peopleList);
		
		return "listsuccess";
	}

	public String saveP() throws Exception {
		this.giftItems = new Gift_items();
		this.slr = new Gift_peoples();
		this.zlr = new Gift_peoples();
		this.zlr_list = this.giftPeoplesService.findByFlag(0);
		this.slr_list = this.giftPeoplesService.findByFlag(1);
		return "saveP";
	}

	public String save() throws Exception {

		// 受赠时间
		try {
			this.giftItems.setI_sztime(DateTools.getTimestamp(this.strSztime).intValue());
		} catch (ParseException a) {
			this.giftItems.setI_sztime(DateTools.getTimestamp().intValue());
		}
		
		this.giftItems.setI_createtime(DateTools.getTimestamp().intValue()); // 创建时间
		this.giftItems.setI_adduser(0); // 创建人

		try {
			this.giftItems.setI_qrcode(DateTools.getTimestamp() +".png");
			
			//生成二维码
			String dirPath = ServletActionContext.getServletContext().getRealPath("/qrcode");
			String qucodePath = dirPath + "/"+ giftItems.getI_qrcode();
			QRCodeTool qRCodeTool = new QRCodeTool();
			qRCodeTool.generateQRCode(giftItems.getI_no(), qucodePath, dirPath);

			int i_id = this.giftItemsService.save(giftItems);
			this.giftItems = this.giftItemsService.findById(i_id);
			
			//仓库信息
			cangku.setCk_i_no(giftItems.getI_no());
			cangku.setCk_createtime(DateTools.getTimestamp().intValue()); // 创建时间
			cangku.setCk_adduser(0); // 创建人
			giftCangkuService.save(cangku);
			
			//return "list";
			return "savetofujian";
		} catch (Exception e) {
			ByteArrayOutputStream msg = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(msg));
			addActionError("保存出错,堆栈信息如下:</BR>" + msg.toString());
			Logger logger = Logger.getLogger(this.getClass());
			logger.error("programe error !gift_detailsaveAction.java ");
			return "saveP";
		}
	}

	public String updateP() throws Exception {
		if (!StringUtils.isNumeric(updateP_i_id)) {
			return "updatesuccess";
		}
		
		zlr_list = giftPeoplesService.findByFlag(0);
		slr_list = giftPeoplesService.findByFlag(1);
		giftItems = giftItemsService.findById(Integer.parseInt(updateP_i_id));
		strSztime = DateTools.getDisplayTime((long) giftItems.getI_sztime());
		cangku = giftCangkuService.findByItemNo(giftItems.getI_no());
		
		if(giftItems.getI_qrcode()==null || giftItems.getI_qrcode().equals("")) {
			giftItems.setI_qrcode(null);
		} else {
			String qucodePath = ServletActionContext.getServletContext().getRealPath("/qrcode")+ "/"+ giftItems.getI_qrcode();
			if(!FileTools.fileExist(qucodePath)) {
				giftItems.setI_qrcode(null);
			}
		}
		
		return "updateP";
	}

	public String update() throws Exception {
		try {
			try {
				this.giftItems.setI_sztime(DateTools.getTimestamp(this.strSztime).intValue()); // 受赠时间
			} catch (ParseException a) {
				this.giftItems.setI_sztime(DateTools.getTimestamp().intValue());
			}
			this.giftItems.setI_createtime(DateTools.getTimestamp().intValue()); // 创建时间
			this.giftItems.setI_adduser(0); // 创建人

			giftItemsService.update(giftItems);
			
			if(cangku.getCk_i_no()==null || cangku.getCk_i_no().equals("")) { //没有仓库信息，添加
				cangku.setCk_i_no(giftItems.getI_no());
				cangku.setCk_createtime(DateTools.getTimestamp().intValue()); // 创建时间
				cangku.setCk_adduser(0); // 创建人
				giftCangkuService.save(cangku);
			} else {
				giftCangkuService.update(cangku);
			}
			
			return "updatesuccess";
		} catch (Exception e) {
			ByteArrayOutputStream msg = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(msg));
			addActionError("保存出错,堆栈信息如下:</BR>" + msg.toString());
			Logger logger = Logger.getLogger(this.getClass());
			logger.error("programe error !Gift_ItemsAction.java ");
			return "updateerror";
		}
	}

	//根据传入的updateP_i_id生成二维码
	public String genQrcode() throws Exception {
		giftItems = giftItemsService.findById(Integer.parseInt(updateP_i_id));
		giftItems.setI_qrcode(DateTools.getTimestamp() +".png");
		
		//生成二维码
		String dirPath = ServletActionContext.getServletContext().getRealPath("/qrcode");
		String qucodePath = dirPath + "/"+ giftItems.getI_qrcode();
		QRCodeTool qRCodeTool = new QRCodeTool();
		boolean qrcodeResult = qRCodeTool.generateQRCode(giftItems.getI_no(), qucodePath, dirPath);
		if(qrcodeResult) {
			giftItemsService.update(giftItems);
		}
		
		return "genQrcodeSuccess";
	}
	
	
	public String listByID() throws Exception {
		return "listByID";
	}

	
	/*********setter/getter*************/
	@Resource(name = "gift_itemsServiceImpl")
	public void setGiftItemsService(Gift_itemsService giftItemsService) {
		this.giftItemsService = giftItemsService;
	}

	public Gift_peoplesService getGiftPeoplesService() {
		return giftPeoplesService;
	}

	@Resource(name = "gift_peoplesServiceImpl")
	public void setGiftPeoplesService(Gift_peoplesService giftPeoplesService) {
		this.giftPeoplesService = giftPeoplesService;
	}

	public Gift_cangkuService getGiftCangkuService() {
		return giftCangkuService;
	}

	@Resource(name = "gift_cangkuServiceImpl")
	public void setGiftCangkuService(Gift_cangkuService giftCangkuService) {
		this.giftCangkuService = giftCangkuService;
	}

	public Gift_items getGiftItems() {
		return giftItems;
	}

	public void setGiftItems(Gift_items giftItems) {
		this.giftItems = giftItems;
	}

	public List<Gift_items> getGiftItemsList() {
		return giftItemsList;
	}

	public void setGiftItemsList(List<Gift_items> giftItemsList) {
		this.giftItemsList = giftItemsList;
	}

	public Gift_itemsService getGiftItemsService() {
		return giftItemsService;
	}

	public List<Gift_peoples> getSlr_list() {
		return slr_list;
	}

	public void setSlr_list(List<Gift_peoples> slr_list) {
		this.slr_list = slr_list;
	}

	public List<Gift_peoples> getZlr_list() {
		return zlr_list;
	}

	public void setZlr_list(List<Gift_peoples> zlr_list) {
		this.zlr_list = zlr_list;
	}

	public Gift_peoples getSlr() {
		return slr;
	}

	public void setSlr(Gift_peoples slr) {
		this.slr = slr;
	}

	public Gift_peoples getZlr() {
		return zlr;
	}

	public void setZlr(Gift_peoples zlr) {
		this.zlr = zlr;
	}

	public String getStrSztime() {
		return strSztime;
	}

	public void setStrSztime(String strSztime) {
		this.strSztime = strSztime;
	}

	public String getUpdateP_i_id() {
		return updateP_i_id;
	}

	public void setUpdateP_i_id(String updateP_i_id) {
		this.updateP_i_id = updateP_i_id;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public String getSrhItemNo() {
		return srhItemNo;
	}

	public void setSrhItemNo(String srhItemNo) {
		this.srhItemNo = srhItemNo;
	}

	public String getSrhItemName() {
		return srhItemName;
	}

	public void setSrhItemName(String srhItemName) {
		this.srhItemName = srhItemName;
	}

	public Gift_cangku getCangku() {
		return cangku;
	}

	public void setCangku(Gift_cangku cangku) {
		this.cangku = cangku;
	}
}