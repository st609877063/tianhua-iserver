package com.res.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.struts2.ServletActionContext;

import com.res.bean.ResItems;
import com.res.manager.ResItemsManager;
import com.res.tools.DateTools;
import com.res.tools.FileTools;

public class ResItemAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private List<ResItems> rtnList = new ArrayList<ResItems>();
	
	private ResItems resItem;
	private String itemNo; //仅限上传时form提交参数使用
	private String itemId; //item标识
	
	// ----------关键字查询 start ----------
	private String srhItemNo="";
	// ----------关键字查询  start----------
	
	
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
		
		this.resItem.setItemCreatetime(DateTools.getTimestamp().intValue()); // 创建时间
		this.resItem.setItemAdduser(0); // 创建人
		this.resItem.setItemImg("default.jpg");
		String savePath = ServletActionContext.getServletContext().getRealPath("/fujian")+ File.separator + "default.jpg";
		this.resItem.setItemImgpath(savePath);
		
		try {
			int result = manager.updateResItem(resItem, "insert");
			this.resItem = manager.findResItemsByItemNo(resItem.getItemNo());
			return "savetofujian";
		} catch (Exception e) {
			return "failed";
		}
	}
	
	
	public String toUpdateItem() throws Exception {
		if (StringUtils.isNotEmpty(itemId)) {
			int i_id = Integer.parseInt(itemId);
			ResItemsManager manager = new ResItemsManager();
			this.resItem = manager.findResItemsByItemId(i_id);
			return "success";
		} else {
			return "failed";
		}
	}
	
	public String updateItem() throws Exception {
		ResItemsManager manager = new ResItemsManager();
		
		try {
			ResItems resItemTemp = manager.findResItemsByItemNo(this.resItem.getItemNo());
			resItemTemp.setItemName(this.resItem.getItemName());
			resItemTemp.setItemMoney(this.resItem.getItemMoney());
			resItemTemp.setItemMemo(this.resItem.getItemMemo());
			resItemTemp.setItemDesc(this.resItem.getItemDesc());
			resItemTemp.setItemType(this.resItem.getItemType());
			
			int result = manager.updateResItem(resItemTemp, "update");
			return "success";
		} catch (Exception e) {
			return "failed";
		}
	}
	
	
	private File uploadFile;
	private String uploadFileContentType;
	private String uploadFileFileName;

	public String saveItemImg() throws Exception {
		if (StringUtils.isNotEmpty(itemNo) && uploadFile!=null) {
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
				ResItemsManager manager = new ResItemsManager();
				this.resItem = manager.findResItemsByItemNo(itemNo);
				this.resItem.setItemImg(fileName_new);
				this.resItem.setItemImgpath(savePath);
				
				int result = manager.updateResItem(resItem, "update");
				
				return "uploadSuccess";
			} catch (Exception e) {
				return "uploadFailed";
			}
		} else {
			return "uploadFailed";
		}
	}
	
	public String previewItemImg() throws Exception {
		if (StringUtils.isNotEmpty(itemId)) {
			int i_id = Integer.parseInt(itemId);
			ResItemsManager manager = new ResItemsManager();
			this.resItem = manager.findResItemsByItemId(i_id);
			return "preview";
		} else {
			return "previewFailed";
		}
	}
	
	public String modifyItemImg() throws Exception {
		if (StringUtils.isNotEmpty(itemId)) {
			int i_id = Integer.parseInt(itemId);
			ResItemsManager manager = new ResItemsManager();
			this.resItem = manager.findResItemsByItemId(i_id);
			return "success";
		} else {
			return "failed";
		}
	}
	
	public String imgDownloadAttach() throws Exception {
		if (StringUtils.isNotEmpty(itemId)) {
			int i_id = Integer.parseInt(itemId);
			ResItemsManager manager = new ResItemsManager();
			this.resItem = manager.findResItemsByItemId(i_id);
			
			BufferedOutputStream bos = null;  
		    FileInputStream fis = null;  
		    HttpServletResponse response = ServletActionContext.getResponse();
		    
		    if (resItem.getItemImg() != null && !"".equals(resItem.getItemImg())) {
		        try {   
		        	String disposition = "attachment;filename=" + URLEncoder.encode(resItem.getItemImg(), "UTF-8"); //防止中文乱码
		  
		            response.setContentType("application/x-msdownload;charset=UTF-8");
		            response.setHeader("Content-disposition", disposition);
		              
		            fis = new FileInputStream(resItem.getItemImgpath()); 
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
			return "failed";
		}
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

	public ResItems getResItem() {
		return resItem;
	}

	public void setResItem(ResItems resItem) {
		this.resItem = resItem;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
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
	
}
