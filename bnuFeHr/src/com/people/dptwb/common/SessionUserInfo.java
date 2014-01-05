/**
 * SessionUserInfo.java
 * description: 
 * dptwb
 * author: mayq
 * 2013-7-5
 */
package com.people.dptwb.common;

import java.io.Serializable;

/**
 * 存储在SESSION中的用户信息
 * Description: 
 * @author mayq
 * 2013-7-5
 */
public class SessionUserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3571443659123365893L;
	
	private int userId;
	private String userName;
	private String nickName;
	private int accountId;
	private int isAdmin;
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * @return the accountId
	 */
	public int getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return the isAdmin
	 */
	public int getIsAdmin() {
		return isAdmin;
	}
	/**
	 * @param isAdmin the isAdmin to set
	 */
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

}
