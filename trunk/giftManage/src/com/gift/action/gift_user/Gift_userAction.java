package com.gift.action.gift_user;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gift.action.BaseAction;
import com.gift.bean.Gift_group;
import com.gift.bean.Gift_group_user;
import com.gift.bean.Gift_user;
import com.gift.common.CommonConstant;
import com.gift.service.Gift_groupService;
import com.gift.service.Gift_group_userService;
import com.gift.service.Gift_userService;
import com.gift.tools.DateTools;
import com.gift.tools.MD5Util;
import com.gift.tools.UserValidator;

@Controller
@Scope("prototype")
public class Gift_userAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Resource(name = "gift_userServiceImpl")
	private Gift_userService userService;
	@Resource(name = "gift_groupServiceImpl")
	private Gift_groupService groupService;
	@Resource(name = "gift_group_userServiceImpl")
	private Gift_group_userService guService;
	
	//实体对象和结果集
	private Gift_user gift_user;
	private List<Gift_user> gift_user_list;
	private List<Gift_group> gift_group_list;
	
	private String keyword = "";
	private String pagehtml;
	private String update_user_id;

	// ----------分页 start----------
	private int pageNow = 1; // 页码数
	private int pageSize = 5; // 页面行数
	private int rowCount = 0;// 总行数
	private int pageCount = 1; // 总页数
	// ----------分页 end----------
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		// 关键字查询
		rowCount = userService.findByKeyword_count(keyword);// 计算出实际记录数
		pageCount = rowCount / pageSize;// 计算出实际页数
		if ((rowCount % pageSize != 0) || (pageCount == 0))
			pageCount++;
		gift_user_list = userService.findByKeyword(keyword, (pageNow - 1)
				* pageSize, pageSize);

		return "listsuccess";
	}
	/**
	 * 跳转到添加页面
	 * @return
	 * @throws Exception
	 */
	public String saveP() throws Exception {
		gift_group_list = groupService.findAll();
		return "saveP";
	}
	
	/**
	 * 校验输入
	 * @throws Exception 
	 */
	private int validateInput() throws Exception{
		if(StringUtils.isBlank(gift_user.getUser_name())){
			setSuccess(false);
			setErrMsg("用户名不能为空!");
			return CommonConstant.ERROR;
		}else if(gift_user.getUser_id()==null&&StringUtils.isBlank(gift_user.getPassword())){
			setSuccess(false);
			setErrMsg("密码不能为空!");
			return CommonConstant.ERROR;
		}else if(StringUtils.isBlank(gift_user.getNick_name())){
			setSuccess(false);
			setErrMsg("用户昵称不能为空!");
			return CommonConstant.ERROR;
		}else if(StringUtils.isBlank(gift_user.getUser_title())){
			setSuccess(false);
			setErrMsg("用户头衔不能为空!");
			return CommonConstant.ERROR;
		}/*else if(StringUtils.isBlank(gift_user.getUser_type())){
			setSuccess(false);
			setErrMsg("请选择用户类型!");
			return CommonConstant.ERROR;
		}else if(StringUtils.isBlank(gift_user.getUser_admin())){
			setSuccess(false);
			setErrMsg("请选择用户管理类型!");
			return CommonConstant.ERROR;
		}else if(StringUtils.isBlank(gift_user.getIs_close())){
			setSuccess(false);
			setErrMsg("请选择是否关闭!");
			return CommonConstant.ERROR;
		}*/else{
			int result = userService.checkUser(gift_user);
			if(result == CommonConstant.USER_NAME_EXIST){
				setSuccess(false);
				setErrMsg("用户名已存在!");
				return CommonConstant.ERROR;
			}else if(result == CommonConstant.USER_NICK_NAME_EXIST){
				setSuccess(false);
				setErrMsg("昵称已存在!");
				return CommonConstant.ERROR;
			}
		}
		return CommonConstant.SUCCESS;
	}
	/**
	 * 添加(保存)
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		try {
			if(validateInput()==CommonConstant.ERROR){
				gift_group_list = groupService.findAll();
				return INPUT;
			}
			gift_user.setPassword(MD5Util.getMD5(gift_user.getPassword().getBytes()));
			gift_user.setCreate_time(DateTools.getTimestamp());
			gift_user.setAdd_user_id(Integer.parseInt(UserValidator.getCookie(getRequest())[0]));
			userService.save(gift_user);
			return "savesuccess";
		} catch (Exception e) {
			e.printStackTrace();
			Logger logger = Logger.getLogger(this.getClass());
			logger.error("programe error !gift_usersaveAction.java "); // 写到日志文件
			return "saveerror";
		}
	}
	/**
	 * 跳转到更新页面
	 * @return
	 * @throws Exception
	 */
	public String updateP() throws Exception {
		try {
			int user_id = Integer.parseInt(update_user_id);
			gift_user = userService.findById(user_id);
			gift_group_list = groupService.findAll();
			List<Gift_group_user> guList = guService.findByUser(gift_user);
			if(guList!=null&&guList.size()!=0){
				StringBuilder groupIdBuilder = new StringBuilder();
				for(Gift_group_user gu:guList){
					groupIdBuilder.append(gu.getGroup_id()).append(", ");
				}
				gift_user.setGroupIdStr(groupIdBuilder.toString());
			}
			return "updateP";
		} catch (Exception e) {
			return "list";
		}
	}
	

	/**
	 * 更新
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		try {
			if(validateInput()==CommonConstant.ERROR){
				gift_group_list = groupService.findAll();
				return INPUT;
			}
			if(gift_user.getPassword()!=null&&!gift_user.getPassword().equals("")){
				gift_user.setPassword(MD5Util.getMD5(gift_user.getPassword().getBytes()));
			}
			userService.update(gift_user);
			return "updatesuccess";
		} catch (Exception e) {
			e.printStackTrace();
			Logger logger = Logger.getLogger(this.getClass());
			logger.error("programe error !gift_userupdateAction.java "); // 写到日志文件
			return "updateerror";
		}
	}


	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
	public String remove() throws Exception {
		userService.delete(gift_user);
		return "removesuccess";
	}



	public String generateExcel() throws Exception {
		return "generateExcel";
	}

	public Gift_user getGift_user() {
		return gift_user;
	}

	public void setGift_user(Gift_user gift_user) {
		this.gift_user = gift_user;
	}

	public Gift_userService getService() {
		return userService;
	}

	
	public void setService(Gift_userService service) {
		this.userService = service;
	}

	public void setGift_user_list(List<Gift_user> gift_user_list) {
		this.gift_user_list = gift_user_list;
	}

	public List<Gift_user> getGift_user_list() {
		return gift_user_list;
	}

	public void setUpdate_user_id(String update_user_id) {
		this.update_user_id = update_user_id;
	}
	public String getUpdate_user_id() {
		return update_user_id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getPagehtml() {
		return pagehtml;
	}
	public void setPagehtml(String pagehtml) {
		this.pagehtml = pagehtml;
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
	public void setGift_group_list(List<Gift_group> gift_group_list) {
		this.gift_group_list = gift_group_list;
	}
	public List<Gift_group> getGift_group_list() {
		return gift_group_list;
	}
	public void setGroupService(Gift_groupService groupService) {
		this.groupService = groupService;
	}
	public Gift_groupService getGroupService() {
		return groupService;
	}
	public void setGuService(Gift_group_userService guService) {
		this.guService = guService;
	}
	public Gift_group_userService getGuService() {
		return guService;
	}
}