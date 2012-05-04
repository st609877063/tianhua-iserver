package com.platform.service;

import java.util.List;

import com.framework.exception.ServiceException;
import com.platform.domain.Magazine;
import com.platform.domain.MagazineClass;

public interface MagazineService {
	/**
	 * 返回杂志列表
	 * @param 
	 * @return List 
	 * @throws ServiceException
	 */
	public List<Magazine> getMagazinesList() throws ServiceException;
	/**
	 * 返回杂志类型
	 * @param 
	 * @return List 
	 * @throws ServiceException
	 */
	public List<MagazineClass> getMagazineClassList() throws ServiceException;
	/**
	 * 返回杂志类型
	 * @param magazineId 杂志ID
	 * @return String 
	 * @throws ServiceException
	 */
	public MagazineClass getMagazineClass(String magazineClassId) throws ServiceException;
	/**
	 * 返回杂志列表
	 * @param start 开始记录,limit 显示总数, sortorder 排序方式
	 * @return String 
	 * @throws ServiceException
	 */
	public String getMagazines(int start,int limit,String sortorder) throws ServiceException;
	/**
	 * 返回iphone杂志列表
	 * @param start 开始记录,limit 显示总数, sortorder 排序方式
	 * @return String 
	 * @throws ServiceException
	 */
	public String getIphoneMagazinesByClass(String magazineClass,int start,int limit,String sortorder) throws ServiceException;
	/**
	 * 返回iphone杂志列表
	 * @param start 开始记录,limit 显示总数, sortorder 排序方式
	 * @return String 
	 * @throws ServiceException
	 */
	public String getIphoneMagazines(int start,int limit,String sortorder) throws ServiceException;
	/**
	 * 返回杂志内容
	 * @param magazineId 杂志ID
	 * @return String 
	 * @throws ServiceException
	 */
	public Magazine getMagazine(String magazineId) throws ServiceException;
	/**
	 * 返回iphone杂志内容
	 * @param magazineId 杂志ID
	 * @return String 
	 * @throws ServiceException
	 */
	public String getIphoneMagazine(String magazineId) throws ServiceException;
	/**
	 * 保存杂志
	 * @param magazine 杂志内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public String getMaxMagazineId() throws ServiceException;
	public boolean saveMagazine(Magazine magazine) throws ServiceException;
	/**
	 * 修改杂志
	 * @param magazine 杂志内容
	 * @return boolean 
	 * @throws ServiceException
	 */

	
	public boolean updateMagazine(Magazine magazine) throws ServiceException;
	/**
	 * 删除杂志
	 * @param magazine 杂志内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public boolean removeMagazine(Magazine magazine) throws ServiceException;
	/**
	 * 删除杂志
	 * @param magazine 杂志内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public boolean removeMagazine(Magazine magazine,String id) throws ServiceException;
}
