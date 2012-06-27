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
import com.framework.persistence.JdbcPersistenceManager;
import com.framework.persistence.JdbcQueryManager;
import com.framework.persistence.PersistenceManager;
import com.framework.persistence.QueryManager;
import com.platform.domain.Magazine;
import com.platform.domain.Section;
import com.platform.service.SectionService;
@Service("sectionService")
public class SectionServiceImpl implements SectionService {
	private PersistenceManager pm;
	private QueryManager qm;
	private JdbcQueryManager jqm;
	private JdbcPersistenceManager jpm;
	
	public void setPersistenceManager(PersistenceManager pm) {
		this.pm = pm;
	}

	public void setQueryManager(QueryManager qm) {
		this.qm = qm;
	}

	public void setJdbcQueryManager(JdbcQueryManager jqm) {
		this.jqm = jqm;
	}
	public void setJdbcPersistenceManager(JdbcPersistenceManager jpm) {
		this.jpm = jpm;
	}
	/**
	 * 返回栏目列表
	 * @param start 开始记录,limit 显示总数, sortorder 排序方式
	 * @return String 
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public List<Section> getSectionList() throws ServiceException{
		List<Section> list = qm.findByNamedQuery("getSections");
		return list;
	}
	/**
	 * 返回栏目列表
	 * @param start 开始记录,limit 显示总数, sortorder 排序方式
	 * @return String 
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public List<Section> getSectionList(String magazineId) throws ServiceException{
		List<Section> list = qm.findByNamedQuery("getSectionsByMagazineId",magazineId);
		
		return list;
	}
	/**
	 * 返回栏目列表
	 * @param start 开始记录,limit 显示总数, sortorder 排序方式
	 * @return Json 
	 * @throws ServiceException
	 */
	public String getSections(String magazineId,int start,int limit,String sortorder) throws ServiceException{
		String sql = "select * from magazine m,section s " +
		" where m.magazine_id = '"+magazineId+"' and m.magazine_id = s.magazine_id" +
		" order by seq "+sortorder+" limit "+start+","+limit; 
		int page = limit==0?1:(start/limit+1);
		RowSet rs = jqm.getRowSet(sql);
		JSONArray jarray = new JSONArray();
		JSONObject obj = new JSONObject();
		int size = getSectionList(magazineId).size();
		try {
			obj.put("total", size);
			obj.put("page", page);
			try {
				while (rs.next()) {
					JSONObject object = new JSONObject();
					object.put("sectionId",rs.getString("SECTION_ID"));
					object.put("sectionName",rs.getString("SECTION_NAME"));
					object.put("magazineName",rs.getString("MAGAZINE_NAME"));
					object.put("seq",rs.getInt("seq"));
					object.put("hide",rs.getInt("HIDE")==0?"否":"是");
					jarray.put(object);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			obj.put("rows", jarray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj.toString();
	}
	/**
	 * 返回栏目列表
	 * @param start 开始记录,limit 显示总数, sortorder 排序方式
	 * @return Xml 
	 * @throws ServiceException
	 */
	public String getIphoneSections(String magazineId,int start,int limit,String sortorder) throws ServiceException{
		String sql = "select distinct s.section_id, s.section_name " +
				" from magazine m,section s, article a " +
				" where m.magazine_id = '"+magazineId+"'" +
				" and m.magazine_id = s.magazine_id " +
				" and a.section_id = s.section_id  " +
				" order by s.seq asc ";  
		RowSet rs = jqm.getRowSet(sql);
		StringBuffer sb = new StringBuffer();		
			try {
				sb.append("<rss version=\"2.0\">\n");
				sb.append("<info>\n");
				sb.append("<title>"+"xiaokang"+"</title>\n");
				sb.append("<dat>"+"2011-04-20"+"</dat>\n");
				sb.append("</info>\n");
				while (rs.next()) {
//					JSONObject object = new JSONObject();
//					object.put("sectionId",rs.getString("SECTION_ID"));
//					object.put("sectionName",rs.getString("SECTION_NAME"));
//					object.put("magazineName",rs.getString("MAGAZINE_NAME"));
//					object.put("seq",rs.getInt("seq"));
//					object.put("hide",rs.getInt("HIDE")==0?"否":"是");
//					jarray.put(object);
					sb.append("<item>\n");
					sb.append("<channel_id>");sb.append(rs.getString("SECTION_ID"));sb.append("</channel_id>\n");
					sb.append("<channel_name>");sb.append(rs.getString("SECTION_NAME"));sb.append("</channel_name>\n");
					sb.append("<channel_en>");sb.append("Section");sb.append("</channel_en>\n");
					sb.append("</item>\n");
				}
				sb.append("</rss>\n");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return sb.toString();
	}
	
	/**
	 * 返回栏目内容
	 * @param sectionId 栏目ID
	 * @return String 
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public Section getSection(String sectionId) throws ServiceException{
		List<Section> list = qm.findByNamedQuery("getSectionById", sectionId);
		Section section = list.get(0);
		return section;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String getMaxSectionId(){
		String sql = "";
		sql = "select max(cast(section_id as DECIMAL))  from section";
		String maxId ="";
		RowSet rs = jqm.getRowSet(sql);
		try {
			if(rs.next()&&rs.getString(1)!=null) {
				maxId = rs.getInt(1)+"";
			}
			else maxId = "1";
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return maxId;
	}
	/**
	 * 保存栏目
	 * @param section 栏目内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public boolean saveSection(Section section) throws ServiceException{
		long maxId = Long.parseLong(getMaxSectionId())+1;
		section.setSectionId(maxId+"");
		pm.save(section);
		return true;
	}
	
	public boolean saveXKSections(Magazine magazine) throws ServiceException{
		Section section1 = new Section();
		section1.setSectionName("卷首");
		section1.setSeq(1);
		section1.setMagazine(magazine);
		saveSection(section1);
		Section section2 = new Section();
		section2.setSectionName("月览天下");
		section2.setSeq(2);
		section2.setMagazine(magazine);
		saveSection(section2);
		Section section3 = new Section();
		section3.setSectionName("封面故事");
		section3.setSeq(3);
		section3.setMagazine(magazine);
		saveSection(section3);
		Section section4 = new Section();
		section4.setSectionName("指数");
		section4.setSeq(4);
		section4.setMagazine(magazine);
		saveSection(section4);
		Section section5 = new Section();
		section5.setSectionName("国际");
		section5.setSeq(5);
		section5.setMagazine(magazine);
		saveSection(section5);
		Section section6 = new Section();
		section6.setSectionName("特别报道");
		section6.setSeq(6);
		section6.setMagazine(magazine);
		saveSection(section6);
		Section section7 = new Section();
		section7.setSectionName("论坛报道");
		section7.setSeq(7);
		section7.setMagazine(magazine);
		saveSection(section7);
		Section section8 = new Section();
		section8.setSectionName("时政");
		section8.setSeq(8);
		section8.setMagazine(magazine);
		saveSection(section8);
		Section section9 = new Section();
		section9.setSectionName("文化");
		section9.setSeq(9);
		section9.setMagazine(magazine);
		saveSection(section9);
		Section section10 = new Section();
		section10.setSectionName("月报");
		section10.setSeq(10);
		section10.setMagazine(magazine);
		saveSection(section10);
		Section section11 = new Section();
		section11.setSectionName("往事");
		section11.setSeq(11);
		section11.setMagazine(magazine);
		saveSection(section11);
		Section section12 = new Section();
		section12.setSectionName("评论");
		section12.setSeq(12);
		section12.setMagazine(magazine);
		saveSection(section12);
		Section section13 = new Section();
		section13.setSectionName("专栏");
		section13.setSeq(13);
		section13.setMagazine(magazine);
		saveSection(section13);
		Section section14 = new Section();
		section14.setSectionName("书评");
		section14.setSeq(14);
		section14.setMagazine(magazine);
		saveSection(section14);
		Section section15 = new Section();
		section15.setSectionName("聊斋");
		section15.setSeq(15);
		section15.setMagazine(magazine);
		saveSection(section15);
		return true;
	}
	
	public boolean saveCZSections(Magazine magazine) throws ServiceException{
		Section section1 = new Section();
		section1.setSectionName("卷首语");
		section1.setSeq(1);
		section1.setMagazine(magazine);
		saveSection(section1);
		Section section2 = new Section();
		section2.setSectionName("资讯");
		section2.setSeq(2);
		section2.setMagazine(magazine);
		saveSection(section2);
		Section section3 = new Section();
		section3.setSectionName("特别策划");
		section3.setSeq(3);
		section3.setMagazine(magazine);
		saveSection(section3);
		Section section4 = new Section();
		section4.setSectionName("财智观察");
		section4.setSeq(4);
		section4.setMagazine(magazine);
		saveSection(section4);
		Section section5 = new Section();
		section5.setSectionName("人物");
		section5.setSeq(5);
		section5.setMagazine(magazine);
		saveSection(section5);
		Section section6 = new Section();
		section6.setSectionName("投资");
		section6.setSeq(6);
		section6.setMagazine(magazine);
		saveSection(section6);
		Section section7 = new Section();
		section7.setSectionName("汽车");
		section7.setSeq(7);
		section7.setMagazine(magazine);
		saveSection(section7);
		Section section8 = new Section();
		section8.setSectionName("公司产业");
		section8.setSeq(8);
		section8.setMagazine(magazine);
		saveSection(section8);
		Section section9 = new Section();
		section9.setSectionName("理财");
		section9.setSeq(9);
		section9.setMagazine(magazine);
		saveSection(section9);
		Section section10 = new Section();
		section10.setSectionName("财智学院");
		section10.setSeq(10);
		section10.setMagazine(magazine);
		saveSection(section10);
		Section section11 = new Section();
		section11.setSectionName("财智生活");
		section11.setSeq(11);
		section11.setMagazine(magazine);
		saveSection(section11);
		Section section12 = new Section();
		section12.setSectionName("书斋");
		section12.setSeq(12);
		section12.setMagazine(magazine);
		saveSection(section12);
		Section section13 = new Section();
		section13.setSectionName("财富测试");
		section13.setSeq(13);
		section13.setMagazine(magazine);
		saveSection(section13);
//		Section section14 = new Section();
//		section14.setSectionName("书评");
//		section14.setSeq(14);
//		section14.setMagazine(magazine);
//		saveSection(section14);
//		Section section15 = new Section();
//		section15.setSectionName("聊斋");
//		section15.setSeq(15);
//		section15.setMagazine(magazine);
//		saveSection(section15);
		return true;
	}
	/**
	 * 修改栏目
	 * @param magazine 杂志内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public boolean updateSection(Section section) throws ServiceException{
		pm.update(section);
		return true;
	}
	/**
	 * 删除栏目
	 * @param magazine 杂志内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public boolean removeSection(Section section) throws ServiceException{
		pm.remove(section);
		return true;
	}
	/**
	 * 删除栏目
	 * @param magazine 杂志内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public boolean removeSection(Section section,String id) throws ServiceException{
		pm.remove(section.getClass(),id);
		return true;
	}
	public boolean removeSections(String magazineId) throws ServiceException{
		String sql = "";
		sql = "delete from section where magazine_id = '"+magazineId +"'";
		System.out.println(sql);
		jpm.executeSql(sql);
		return true;
	}
}
