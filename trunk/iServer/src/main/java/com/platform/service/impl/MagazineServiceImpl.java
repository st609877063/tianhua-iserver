package com.platform.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.sql.RowSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.framework.exception.ServiceException;
import com.framework.persistence.JdbcQueryManager;
import com.framework.persistence.PersistenceManager;
import com.framework.persistence.QueryManager;
import com.platform.database.GlobalVariables;
import com.platform.domain.Magazine;
import com.platform.domain.MagazineClass;
import com.platform.domain.Section;
import com.platform.service.MagazineService;
@Service("magazineService")
public class MagazineServiceImpl implements MagazineService {
	private PersistenceManager pm;
	private QueryManager qm;
	private JdbcQueryManager jqm;

	public void setPersistenceManager(PersistenceManager pm) {
		this.pm = pm;
	}

	public void setQueryManager(QueryManager qm) {
		this.qm = qm;
	}

	public void setJdbcQueryManager(JdbcQueryManager jqm) {
		this.jqm = jqm;
	}
	/**
	 * 返回杂志列表
	 * @param 
	 * @return List 
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Magazine> getMagazinesList() {
		List<Magazine> list = qm.findByNamedQuery("getMagazines");
		return list;
	}
	/**
	 * 返回杂志类型
	 * @param 
	 * @return List 
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<MagazineClass> getMagazineClassList() throws ServiceException{
		List<MagazineClass> list = qm.findByNamedQuery("getMagazineClass");
		return list;
	}
	/**
	 * 返回杂志类型
	 * @param magazineId 杂志ID
	 * @return String 
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public MagazineClass getMagazineClass(String magazineClassId) throws ServiceException{	
		List<MagazineClass> list = qm.findByNamedQuery("getMagazineClassById", magazineClassId);
		MagazineClass magazine = list.get(0);
		return magazine;
	}
	/**
	 * 返回杂志列表
	 * @param start 开始记录,limit 显示总数, sortorder 排序方式
	 * @return String 
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String getMagazines(int start,int limit,String sortorder) throws ServiceException{
		String sql = "select * from magazine m,magazine_class mc " +
				" where m.magazine_class_id = mc.magazine_class_id" +
				" order by CREATE_DATE "+sortorder+" limit "+start+","+limit; 
		int page = limit==0?1:(start/limit+1);
		RowSet rs = jqm.getRowSet(sql);
		JSONArray jarray = new JSONArray();
		JSONObject obj = new JSONObject();
		int size = getMagazinesList().size();
		try {
			obj.put("total", size);
			obj.put("page", page);
			try {
				while (rs.next()) {
					JSONObject object = new JSONObject();
					object.put("magazineId",rs.getString("MAGAZINE_ID"));
					object.put("magazineName",rs.getString("MAGAZINE_NAME"));
					object.put("magazineClassName",rs.getString("MAGAZINE_CLASS_NAME"));
					object.put("phase",rs.getString("PHASE"));
					object.put("viewCounter",rs.getString("VIEW_COUNTER"));
					object.put("downloadCounter",rs.getString("DOWNLOAD_COUNTER"));
					object.put("createDate",rs.getString("CREATE_DATE"));
					object.put("magazinePicture",rs.getString("MAGAZINE_PICTURE"));
					jarray.put(object);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			obj.put("rows", jarray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String getIphoneMagazinesByClass(String magazineClass,int start,int limit,String sortorder) throws ServiceException{
		String sql = "select * from magazine m,magazine_class mc " +
				" where m.magazine_class_id = mc.magazine_class_id and m.magazine_class_id='" + magazineClass +"'"+
				" order by CREATE_DATE DESC limit "+start+","+limit; 
		/*
		String sql = " select m.magazine_id, m.magazine_name, m.magazine_picture, IFNULL(se.section_id, -1) as section_id "+
					" from magazine_class mc,  magazine m left join section se on se.magazine_id=m.magazine_id and se.section_name='重磅推荐' " +
					" where m.magazine_class_id = mc.magazine_class_id and m.magazine_class_id='" + magazineClass +"'"+
					" order by CREATE_DATE DESC limit "+start+","+limit; 
		 */
		
		int page = limit==0?1:(start/limit+1);
		RowSet rs = jqm.getRowSet(sql);
		int size = getMagazinesList().size();
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			sb.append("<root>\n");
				while (rs.next()) {				
					String pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+rs.getString("MAGAZINE_PICTURE");
					sb.append("<data>");
					sb.append("<magId>");sb.append(rs.getString("MAGAZINE_ID"));sb.append("</magId>");
					sb.append("<magName>");sb.append(rs.getString("MAGAZINE_NAME"));sb.append("</magName>");
					sb.append("<magPicture>");sb.append(pic);sb.append("</magPicture>");
					sb.append("<bigSectionId>");sb.append("-1");sb.append("</bigSectionId>"); //每期杂志的重磅推荐
					sb.append("</data>");
				}
				sb.append("</root>\n");
				System.out.println("magazine list:"+sb.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String getIphoneMagazines(int start,int limit,String sortorder) throws ServiceException{
		String sql = "select * from magazine m,magazine_class mc " +
				" where m.magazine_class_id = mc.magazine_class_id" +
				" order by CREATE_DATE DESC limit "+start+","+limit; 
		int page = limit==0?1:(start/limit+1);
		RowSet rs = jqm.getRowSet(sql);
		int size = getMagazinesList().size();
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append("<rss version=\"2.0\">\n");
			sb.append("<channel>\n");
			sb.append("<link></link>\n");
			sb.append("<description></description>\n");
			sb.append("<lastBuildDate></lastBuildDate>\n");
			sb.append("<language>cn</language>\n>");
				while (rs.next()) {				
					String pic = rs.getString("magazine_picture");
					if(pic==null||"".equals(pic))
						pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+"default.jpg";
					sb.append("<item>");
					sb.append("<magazine_id>");sb.append(rs.getString("MAGAZINE_ID"));sb.append("</magazine_id>");
					sb.append("<title><![CDATA[");sb.append(rs.getString("MAGAZINE_NAME"));sb.append("]]></title>>");
					sb.append("<magazine_picture>");sb.append(pic);sb.append("</magazine_picture>");
					sb.append("</item>");
				}
				sb.append("</channel>\n");
				sb.append("</rss>\n");
				System.out.println("magazine:"+sb.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return sb.toString();
	}
	/**
	 * 返回杂志内容
	 * @param magazineId 杂志ID
	 * @return String 
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Magazine getMagazine(String magazineId) throws ServiceException{
		List<Magazine> list = qm.findByNamedQuery("getMagazineById", magazineId);
		Magazine magazine = list.get(0);
		return magazine;
	}
	/**
	 * 返回Iphone杂志内容
	 * @param magazineId 杂志ID
	 * @return String 
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String getIphoneMagazine(String magazineId) throws ServiceException{
		List<Magazine> list = qm.findByNamedQuery("getMagazineById", magazineId);
		Magazine magazine = list.get(0);
		StringBuffer sb = new StringBuffer();
		return "";
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String getMaxMagazineId(){
		String sql = "";
		sql = "select  max(cast(magazine_id as DECIMAL)) from magazine ";
		String maxId ="";
		RowSet rs = jqm.getRowSet(sql);
		try {
			if(rs.next()&&rs.getString(1)!=null) {
				maxId = rs.getString(1);
			}
			else maxId = "1";
			System.out.println(maxId);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxId;
	}
	/**
	 * 保存杂志
	 * @param magazine 杂志内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean saveMagazine(Magazine magazine) throws ServiceException{
		long maxId = Long.parseLong(getMaxMagazineId())+1;
		magazine.setMagazineId(maxId+"");
		pm.save(magazine, true);
		return true;
	}
	

	/**
	 * 修改杂志
	 * @param magazine 杂志内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean updateMagazine(Magazine magazine) throws ServiceException{
		pm.update(magazine);
		return true;
	}
	
	/**
	 * 删除杂志
	 * @param magazine 杂志内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean removeMagazine(Magazine magazine) throws ServiceException{
		pm.remove(magazine);
		return true;
	}
	/**
	 * 删除杂志
	 * @param magazine 杂志内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean removeMagazine(Magazine magazine,String id) throws ServiceException{
		pm.remove(magazine.getClass(),id);
		return true;
	}
}
