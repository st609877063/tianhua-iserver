package com.gift.dao;

import com.gift.bean.Gift_user;

public interface Gift_user_loginDAO {
	public Gift_user checkLogin(Gift_user loginUser) throws Exception;
}
