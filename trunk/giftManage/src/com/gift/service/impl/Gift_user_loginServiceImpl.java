package com.gift.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.gift.bean.Gift_user;
import com.gift.common.CommonConstant;
import com.gift.dao.Gift_user_loginDAO;
import com.gift.service.Gift_user_loginService;
import com.gift.tools.UserValidator;

@Service
public class Gift_user_loginServiceImpl implements Gift_user_loginService {
	
	@Resource(name = "gift_user_loginDAOImpl")
	private Gift_user_loginDAO dao;

	@Override
	public int doLogin(Gift_user loginUser, HttpServletRequest request,HttpServletResponse response) {
		try{
			Gift_user user = dao.checkLogin(loginUser);
		if(user != null){
			//设置cookie
			UserValidator.setCookie(new String[]{String.valueOf(user.getUser_id()), user.getUser_name()},response);
			return CommonConstant.LOGIN_SUCCESS;
		}else{
			return CommonConstant.LOGIN_FAIL;
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return CommonConstant.LOGIN_FAIL;
	}

	@Override
	public void doLogout(HttpServletRequest request,HttpServletResponse response) {
		UserValidator.clearCookie(request, response);
	}

	public void setDao(Gift_user_loginDAO dao) {
		this.dao = dao;
	}

	public Gift_user_loginDAO getDao() {
		return dao;
	}

}
