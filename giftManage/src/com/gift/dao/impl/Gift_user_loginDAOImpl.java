package com.gift.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gift.bean.Gift_user;
import com.gift.dao.Gift_user_loginDAO;
import com.gift.tools.MD5Util;

@Repository
public class Gift_user_loginDAOImpl extends SqlSessionDaoSupport implements Gift_user_loginDAO{

	@Override
	public Gift_user checkLogin(Gift_user loginUser) throws Exception {
		String user_name = loginUser.getUser_name();
		String password = loginUser.getPassword();
		
		try{
			Gift_user user = getSqlSession().selectOne("findGift_userByUser_name",user_name);
			if(user != null){
				String pwd = user.getPassword();
				if(StringUtils.isNotBlank(pwd)&&pwd.equals(MD5Util.getMD5(password.getBytes()))){
					return user;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return null;
	}

}
