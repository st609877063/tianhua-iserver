package com.gift.action.gift_cangku;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.gift.action.BaseAction;
import com.gift.bean.Gift_cangku;
import com.gift.bean.Gift_items;
import com.gift.service.Gift_cangkuService;
import com.gift.service.Gift_itemsService;
import com.gift.tools.DateTools;

@Controller
public class Gift_cangkuAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private Gift_cangkuService cangkuService;
	private Gift_itemsService giftItemsService;
	private Gift_cangku cangku;
	private List<Gift_cangku> cangkuList;
	private String pagehtml;
	private String update_ck_id;
	private String item_id; // item标识

	// ----------分页 start----------
	private int pageNow = 1; // 页码数
	private int pageSize = 20; // 页面行数
	private int rowCount = 0;// 总行数
	private int pageCount = 1; // 总页数
	// ----------分页 end----------

	// ----------关键字查询 start----------
	private String kfNo; // 库房号
	private String hjNo; // 货架号
	private String csNo; // 层数
	private String srhItemNo; // 礼品编号
	private String srhItemName; // 礼品名称
	// ----------关键字查询 start----------

	// ----------action start----------
	@SuppressWarnings({"unchecked", "rawtypes"})
	public String list() throws Exception {
		// 参数和Gift_cangku.xml中相匹配
		Map searchMap = new HashMap();
		searchMap.put("ck_kufang", kfNo);
		searchMap.put("ck_huojia", hjNo);
		searchMap.put("ck_ceng", csNo);
		searchMap.put("itemNo", srhItemNo);
		searchMap.put("itemName", srhItemName);

		rowCount = cangkuService.findByKeyword_count(searchMap);// 计算出实际记录数
		pageCount = rowCount / pageSize;// 计算出实际页数
		if ((rowCount % pageSize != 0) || (pageCount == 0)) {
			pageCount++;
		}
		cangkuList = cangkuService.findByKeyword(searchMap, (pageNow - 1)
				* pageSize, pageSize);

		return "listsuccess";
	}

	public String listByName() throws Exception {
		rowCount = cangkuService.findAll_count();// 计算出实际记录数
		pageCount = rowCount / pageSize;// 计算出实际页数
		if ((rowCount % pageSize != 0) || (pageCount == 0)) {
			pageCount++;
		}
		cangkuList = cangkuService.findAll((pageNow - 1) * pageSize, pageSize);

		return "listByName";
	}

	public String updateP() throws Exception {
		try {
			int ck_id = Integer.parseInt(update_ck_id);
			cangku = cangkuService.findById(ck_id);
			return "updateP";
		} catch (Exception e) {
			return "list";
		}
	}

	public String updateByItemId() throws Exception {
		if (StringUtils.isNumeric(item_id)) {
			int i_id = Integer.parseInt(item_id);
			Gift_items giftItems = this.giftItemsService.findById(i_id);
			cangku = cangkuService.findByItemNo(giftItems.getI_no());

			if (cangku == null) { // 不存在对应仓库信息
				cangku = new Gift_cangku();
				cangku.setCk_i_no(giftItems.getI_no());
				cangku.setI_name(giftItems.getI_name());
			}
			cangku.setI_memo("itemListPage"); // 借此字段，标注来自“updateByItemId”(itemList页面)
			return "updateP";
		} else {
			return "list";
		}
	}

	public String update() throws Exception {
		try {
			if (cangku.getCk_id() == null || cangku.getCk_id().equals("")) { // 没有仓库信息，添加
				cangku.setCk_createtime(DateTools.getTimestamp().intValue()); // 创建时间
				cangku.setCk_adduser(0); // 创建人
				cangkuService.save(cangku);
			} else {
				cangku.setCk_createtime(DateTools.getTimestamp().intValue());
				cangkuService.update(cangku);
			}

			if (cangku.getI_memo() != null
					&& cangku.getI_memo().equals("itemListPage")) {
				return "updatesuccessToItemList";
			} else {
				return "updatesuccess";
			}
		} catch (Exception e) {
			ByteArrayOutputStream msg = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(msg));
			addActionError("保存出错,堆栈信息如下:</BR>" + msg.toString());
			Logger logger = Logger.getLogger(this.getClass());
			logger.error("programe error !Gift_cangkuAction.java ");
			return "updateerror";
		}
	}

	public String saveP() throws Exception {
		return "saveP";
	}

	public String save() throws Exception {
		try {
			// this.service.save(this.gift_code);
			return "savesuccess";
		} catch (Exception e) {
			ByteArrayOutputStream msg = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(msg));
			addActionError("保存出错,堆栈信息如下:</BR>" + msg.toString());
			Logger logger = Logger.getLogger(this.getClass());
			logger.error("programe error !gift_codesaveAction.java "); // 写到日志文件
																		// C:\\log4j_error.log
																		// 中
			return "saveerror";
		}
	}

	// ----------get/set start----------
	public Gift_cangkuService getCangkuService() {
		return cangkuService;
	}

	@Resource(name = "gift_cangkuServiceImpl")
	public void setCangkuService(Gift_cangkuService cangkuService) {
		this.cangkuService = cangkuService;
	}

	public Gift_itemsService getGiftItemsService() {
		return giftItemsService;
	}

	@Resource(name = "gift_itemsServiceImpl")
	public void setGiftItemsService(Gift_itemsService giftItemsService) {
		this.giftItemsService = giftItemsService;
	}

	public Gift_cangku getCangku() {
		return cangku;
	}

	public void setCangku(Gift_cangku cangku) {
		this.cangku = cangku;
	}

	public List<Gift_cangku> getCangkuList() {
		return cangkuList;
	}

	public void setCangkuList(List<Gift_cangku> cangkuList) {
		this.cangkuList = cangkuList;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
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

	public String getPagehtml() {
		return pagehtml;
	}

	public void setPagehtml(String pagehtml) {
		this.pagehtml = pagehtml;
	}

	public String getUpdate_ck_id() {
		return update_ck_id;
	}

	public void setUpdate_ck_id(String update_ck_id) {
		this.update_ck_id = update_ck_id;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getKfNo() {
		return kfNo;
	}

	public void setKfNo(String kfNo) {
		this.kfNo = kfNo;
	}

	public String getHjNo() {
		return hjNo;
	}

	public void setHjNo(String hjNo) {
		this.hjNo = hjNo;
	}

	public String getCsNo() {
		return csNo;
	}

	public void setCsNo(String csNo) {
		this.csNo = csNo;
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

	// ----------get/set end----------
}
