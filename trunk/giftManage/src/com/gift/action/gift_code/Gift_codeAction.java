package com.gift.action.gift_code;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.gift.action.BaseAction;
import com.gift.bean.Gift_code;
import com.gift.service.Gift_codeService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class Gift_codeAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private Gift_codeService service;
	private Gift_code gift_code;
	private String name_c;
	
	// 调用分页默认值
	int currentPage = 1; // 定义没页要显示的记录数
	int lineSize = 10; // 总记录数 / 每页显示的记录数
	int allRecorders;

	public Gift_codeService getService() {
		return service;
	}

	@Resource(name = "gift_codeServiceImpl")
	public void setService(Gift_codeService service) {
		this.service = service;
	}

	public Gift_code getGift_code() {
		return gift_code;
	}

	public void setGift_code(Gift_code gift_code) {
		this.gift_code = gift_code;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getName_c() {
		return name_c;
	}

	public void setName_c(String name_c) {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (request.getMethod().equals("POST")) {
			this.name_c = name_c;

		} else {
			try {

				this.name_c = name_c;

			} catch (Exception e) {
			}
		}

	}

	public String list() throws Exception {
		Map request = (Map) ActionContext.getContext().get("request");

		request.put("list", service.findAll());
		return "listsuccess";
	}

	public String saveP() throws Exception {
		return "saveP";
	}

	public String save() throws Exception {
		try {

			this.service.save(this.gift_code);
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

	public String updateP() throws Exception {
		gift_code = this.service.findByPk_id(gift_code.getPk_id());
		return "updateP";
	}

	public String update() throws Exception {
		try {

			this.service.update(this.gift_code);
			return "updatesuccess";
		} catch (Exception e) {
			ByteArrayOutputStream msg = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(msg));
			addActionError("保存出错,堆栈信息如下:</BR>" + msg.toString());
			Logger logger = Logger.getLogger(this.getClass());
			logger.error("programe error !gift_codeupdateAction.java "); // 写到日志文件
																			// C:\\log4j_error.log
																			// 中
			return "updateerror";
		}
	}

	public String remove() throws Exception {
		this.service.delete(gift_code);
		return "removesuccess";
	}

	public String listByName() throws Exception {
		Map request = (Map) ActionContext.getContext().get("request");

		if (this.getName_c().equals("")) {
			request.put("list", service.findAll());
		} else {
			allRecorders = service.findGift_codeByName_count(this.getName_c());
			request.put("currentPage", new Integer(currentPage)); // 当前页
			request.put("allRecorders", new Integer(allRecorders));// 总记录总数
			request.put(
					"list",
					service.findGift_codeByName(this.getName_c(),
							this.getCurrentPage(), lineSize));

		}
		return "listByName";
	}

	public InputStream getDownloadFile() {

		return this.service.getInputStream(this.getName_c());
	}

	public String generateExcel() throws Exception {
		return "generateExcel";
	}
}
