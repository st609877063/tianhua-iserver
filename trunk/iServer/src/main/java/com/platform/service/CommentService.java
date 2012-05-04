package com.platform.service;

import java.util.List;

import com.framework.exception.ServiceException;
import com.platform.domain.Comment;

public interface CommentService {
	/**
	 * 返回评论列表
	 * @param reviewState 审核状态
	 * @return List 
	 * @throws ServiceException
	 */
	public List<?> getCommentsList(int reviewState) throws ServiceException;
	/**
	 * 返回评论列表
	 * @param reviewState 审核状态
	 * @return String 
	 * @throws ServiceException
	 */
	public String getComments(int reviewState,int start,int limit,String sortorder) throws ServiceException;
	/**
	 * 返回评论列表
	 * @param reviewState 审核状态
	 * @return String 
	 * @throws ServiceException
	 */
	public String getIphoneComments(String nid,int reviewState,int start,int limit,String sortorder) throws ServiceException;
	
	/**
	 * 返回评论内容
	 * @param commentId 评论ID
	 * @return Comment 
	 * @throws ServiceException
	 */
	public Comment getComment(String commentId) throws ServiceException;
	/**
	 * 返回iphone评论内容
	 * @param commentId 评论ID
	 * @return Comment 
	 * @throws ServiceException
	 */
	public String getIphoneComment(String commentId) throws ServiceException;
	
	/**
	 * 保存评论
	 * @param comment 评论内容
	 * @return String 
	 * @throws ServiceException
	 */
	public String getMaxCommentId() throws ServiceException;
	
	public String saveComment(Comment comment) throws ServiceException;
	/**
	 * 删除评论
	 * @param comment 评论内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public boolean removeComment(Comment comment,String id) throws ServiceException;
	
	/**
	 * 审核评论
	 * @param comment 评论内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public boolean reviewComment(Comment comment) throws ServiceException;
	
}
