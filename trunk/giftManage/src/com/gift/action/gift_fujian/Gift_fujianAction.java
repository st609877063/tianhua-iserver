package com.gift.action.gift_fujian;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.gift.action.BaseAction;
import com.gift.bean.Gift_fujian;
import com.gift.bean.Gift_items;
import com.gift.service.Gift_fujianService;
import com.gift.service.Gift_itemsService;
import com.gift.tools.DateTools;
import com.gift.tools.FileTools;

@Controller
public class Gift_fujianAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private Gift_fujianService giftFujianService;
	private Gift_itemsService giftItemsService;
	private List<Gift_fujian> giftFujianList;
	private Gift_fujian giftFujian;
	private Gift_items giftItems;
	private String item_no; //仅限上传时form提交参数使用
	private String item_id; //item标识
	private String fujianId; //附件主键ID
	
	private File uploadFile;
	private String uploadFileContentType;
	private String uploadFileFileName;
	public String save() throws Exception {
		if (StringUtils.isNotEmpty(item_no) && uploadFile!=null) {
			try {
				String fujianPath = ServletActionContext.getServletContext().getRealPath("/fujian");
				if(!FileTools.fileExist(fujianPath)) {
					FileTools.mkdir(fujianPath);
				}
				
				//fileName_new = new Date().getTime() + FileTools.getExtention(uploadFileFileName);
				String fileName_new = new Date().getTime() + "_"+ uploadFileFileName;
				String savePath = ServletActionContext.getServletContext().getRealPath("/fujian")+ File.separator + fileName_new;
				File file_new = new File(savePath);
				FileTools.copy(uploadFile, file_new);
				
				if(uploadFileContentType != null && uploadFileContentType.indexOf("/") != -1) {
					uploadFileContentType = uploadFileContentType.substring(0, uploadFileContentType.indexOf("/"));
				}
				
				//存入DB
				this.giftFujian.setFj_i_no(item_no);
				this.giftFujian.setFj_name(fileName_new);
				this.giftFujian.setFj_path(savePath);
				this.giftFujian.setFj_type(uploadFileContentType);
				this.giftFujian.setFj_createtime(DateTools.getTimestamp().intValue());
				this.giftFujian.setFj_adduser(0);
				this.giftFujianService.save(giftFujian);
				
				return "uploadSuccess";
			} catch (Exception e) {
				return "uploadFailed";
			}
		} else {
			return "uploadFailed";
		}
	}

	
	//多附件
    private List<File> uploadFileMore = new ArrayList<File>();  
    private List<String> uploadFileMoreContentType = new ArrayList<String>();  
    private List<String> uploadFileMoreFileName = new ArrayList<String>();	
	public String saveMore() throws Exception {
		if (StringUtils.isNotEmpty(item_no) && uploadFileMore!=null && uploadFileMore.size()!=0) {
			try {
				String fujianPath = ServletActionContext.getServletContext().getRealPath("/fujian");
				if(!FileTools.fileExist(fujianPath)) {
					FileTools.mkdir(fujianPath);
				}
				
				String fileName_new = null;
				for(int i=0; i<uploadFileMore.size(); i++) {
					fileName_new = new Date().getTime() + "_"+ uploadFileMoreFileName.get(i);
					File file_new = new File(ServletActionContext.getServletContext().getRealPath("/fujian")+ "/"+ fileName_new);
					FileTools.copy(uploadFileMore.get(i), file_new);
				}
				return "uploadSuccess";
			} catch (Exception e) {
				return ERROR;
			}
		} else {
			return ERROR;
		}
	}
	
	
	public String preview() throws Exception {
		if (StringUtils.isNotEmpty(item_id)) {
			int i_id = Integer.parseInt(item_id);
			this.giftItems = this.giftItemsService.findById(i_id);
			this.giftFujianList = this.giftFujianService.findByItemNo(giftItems.getI_no());
			return "preview";
		} else {
			return "fujianFailed";
		}
	}
	
	//前往添加附件页面
	public String add() throws Exception {
		if (StringUtils.isNotEmpty(item_id)) {
			int i_id = Integer.parseInt(item_id);
			this.giftItems = this.giftItemsService.findById(i_id);
			return "goAddFujian";
		} else {
			return "fujianFailed";
		}
	}
	
	public String downloadAttach() throws Exception {
		if (StringUtils.isNotEmpty(fujianId)) {
			int fj_id = Integer.parseInt(fujianId);
			Gift_fujian fujianVo = this.giftFujianService.findByID(fj_id);
			
			BufferedOutputStream bos = null;  
		    FileInputStream fis = null;  
		    HttpServletResponse response = ServletActionContext.getResponse();
		    
		    if (fujianVo.getFj_name() != null && !"".equals(fujianVo.getFj_name())) {
		        try {   
		        	String disposition = "attachment;filename=" + URLEncoder.encode(fujianVo.getFj_name(), "UTF-8"); //防止中文乱码
		  
		            response.setContentType("application/x-msdownload;charset=UTF-8");
		            response.setHeader("Content-disposition", disposition);
		              
		            fis = new FileInputStream(fujianVo.getFj_path()); 
		            bos = new BufferedOutputStream(response.getOutputStream());  
		            byte[] buffer = new byte[2048];  
		            while(fis.read(buffer) != -1){  
		                bos.write(buffer);  
		            }  
		        } catch (IOException e) {  
		            e.printStackTrace();  
		        }finally {  
		            if(fis != null){try {fis.close();} catch (IOException e) {}}  
		            if(bos != null){try {bos.close();} catch (IOException e) {}}  
		        }  
		    }
		    
			return null;
		} else {
			return "fujianFailed";
		}
	}
	
	// ----------get set----------
	public Gift_fujianService getGiftFujianService() {
		return giftFujianService;
	}

	@Resource(name = "gift_fujianServiceImpl")
	public void setGiftFujianService(Gift_fujianService giftFujianService) {
		this.giftFujianService = giftFujianService;
	}

	public Gift_itemsService getGiftItemsService() {
		return giftItemsService;
	}

	@Resource(name = "gift_itemsServiceImpl")
	public void setGiftItemsService(Gift_itemsService giftItemsService) {
		this.giftItemsService = giftItemsService;
	}
	
	public String getItem_no() {
		return item_no;
	}

	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileContentType() {
		return uploadFileContentType;
	}

	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public List<File> getUploadFileMore() {
		return uploadFileMore;
	}

	public void setUploadFileMore(List<File> uploadFileMore) {
		this.uploadFileMore = uploadFileMore;
	}

	public List<String> getUploadFileMoreContentType() {
		return uploadFileMoreContentType;
	}

	public void setUploadFileMoreContentType(List<String> uploadFileMoreContentType) {
		this.uploadFileMoreContentType = uploadFileMoreContentType;
	}

	public List<String> getUploadFileMoreFileName() {
		return uploadFileMoreFileName;
	}

	public void setUploadFileMoreFileName(List<String> uploadFileMoreFileName) {
		this.uploadFileMoreFileName = uploadFileMoreFileName;
	}

	public Gift_fujian getGiftFujian() {
		return giftFujian;
	}

	public void setGiftFujian(Gift_fujian giftFujian) {
		this.giftFujian = giftFujian;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public List<Gift_fujian> getGiftFujianList() {
		return giftFujianList;
	}

	public void setGiftFujianList(List<Gift_fujian> giftFujianList) {
		this.giftFujianList = giftFujianList;
	}

	public String getFujianId() {
		return fujianId;
	}

	public void setFujianId(String fujianId) {
		this.fujianId = fujianId;
	}

	public Gift_items getGiftItems() {
		return giftItems;
	}

	public void setGiftItems(Gift_items giftItems) {
		this.giftItems = giftItems;
	}
	
}