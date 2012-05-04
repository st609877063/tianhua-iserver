package com.platform.service;

import java.util.List;

import com.framework.exception.ServiceException;
import com.platform.domain.Article;
import com.platform.domain.Section;

public interface ArticleService {
	/**
	 * 返回文章列表
	 * @param start 开始记录,limit 显示总数, sortorder 排序方式
	 * @return List 
	 * @throws ServiceException
	 */
	public List<Article> getArtilcleList(String magazineId) throws ServiceException;
	/**
	 * 返回文章列表
	 * @param start 开始记录,limit 显示总数, sortorder 排序方式
	 * @return String 
	 * @throws ServiceException
	 */
	public String getArticles(String magazineId,String sectionId,int start,int limit,String sortorder) throws ServiceException;
	
	/**
	 * 返回文章列表
	 * @param start 开始记录,limit 显示总数, sortorder 排序方式
	 * @return String 
	 * @throws ServiceException
	 */
	public String getIphoneArticles(String magazineId,String sectionId,String articleId,int start,int limit,String sortorder, String isRecommend) throws ServiceException;
	/**
	 * 返回文章内容
	 * @param articleId 文章ID
	 * @return Article 
	 * @throws ServiceException
	 */
	public Article getArticle(String articleId) throws ServiceException;
	/**
	 * 获取重磅文章内容
	 * @param articleId 文章ID
	 * @return Article 
	 * @throws ServiceException
	 */
	public String getTopArticle(String articleId) throws ServiceException;

	public String getRecommendTopArticle(String magazineId) throws ServiceException;
	/**
	 * 返回文章内容
	 * @param articleId 文章ID
	 * @return Xml 
	 * @throws ServiceException
	 */
	public String getIphoneArticle(String articleId) throws ServiceException;
	
	public String getMaxArticleId() throws ServiceException;
	/**
	 * 保存文章
	 * @param section 栏目内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public boolean saveArticle(Article article) throws ServiceException;
	/**
	 * 修改文章
	 * @param section 栏目内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public boolean updateArticle(Article article) throws ServiceException;
	/**
	 * 删除文章
	 * @param magazine 杂志内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public boolean removeArticle(Article article) throws ServiceException;
	
	public boolean removeArticles(String sectionId) throws ServiceException;
	/**
	 * 删除文章
	 * @param magazine 杂志内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public boolean removeArticle(Article article,String id) throws ServiceException;
}
