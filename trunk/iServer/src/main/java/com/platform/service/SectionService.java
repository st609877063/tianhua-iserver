package com.platform.service;

import java.util.List;

import com.framework.exception.ServiceException;
import com.platform.domain.Magazine;
import com.platform.domain.Section;

public interface SectionService {
	/**
	 * 返回栏目列表
	 * @param start 开始记录,limit 显示总数, sortorder 排序方式
	 * @return String 
	 * @throws ServiceException
	 */
	public List getSectionList() throws ServiceException;
	/**
	 * 返回栏目列表
	 * @param start 开始记录,limit 显示总数, sortorder 排序方式
	 * @return String 
	 * @throws ServiceException
	 */
	public List getSectionList(String magazineId) throws ServiceException;
	/**
	 * 返回栏目列表
	 * @param start 开始记录,limit 显示总数, sortorder 排序方式
	 * @return String 
	 * @throws ServiceException
	 */
	public String getSections(String magazineId,int start,int limit,String sortorder) throws ServiceException;
	/**
	 * 返回栏目列表
	 * @param start 开始记录,limit 显示总数, sortorder 排序方式
	 * @return String 
	 * @throws ServiceException
	 */
	public String getIphoneSections(String magazineId,int start,int limit,String sortorder) throws ServiceException;
	
	/**
	 * 返回栏目内容
	 * @param sectionId 栏目ID
	 * @return String 
	 * @throws ServiceException
	 */
	public Section getSection(String sectionId) throws ServiceException;
	
	public String getMaxSectionId();
	/**
	 * 保存栏目
	 * @param section 栏目内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public boolean saveSection(Section section) throws ServiceException;
	/**
	 * 保存栏目
	 * @param section 栏目内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	//小康
	public boolean saveXKSections(Magazine magazine) throws ServiceException;
	//财智
	public boolean saveCZSections(Magazine magazine) throws ServiceException;
	/**
	 * 修改栏目
	 * @param magazine 杂志内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public boolean updateSection(Section section) throws ServiceException;
	/**
	 * 删除栏目
	 * @param magazine 杂志内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public boolean removeSection(Section section) throws ServiceException;
	/**
	 * 删除栏目
	 * @param magazine 杂志内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public boolean removeSection(Section section,String id) throws ServiceException;
	
	public boolean removeSections(String magazineId) throws ServiceException;
}
