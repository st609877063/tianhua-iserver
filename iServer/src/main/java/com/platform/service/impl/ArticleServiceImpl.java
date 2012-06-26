package com.platform.service.impl;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.sql.RowSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.framework.exception.ServiceException;
import com.framework.persistence.JdbcPersistenceManager;
import com.framework.persistence.JdbcQueryManager;
import com.framework.persistence.PersistenceManager;
import com.framework.persistence.QueryManager;
import com.framework.util.DateTime;
import com.platform.database.GlobalVariables;
import com.platform.domain.Article;
import com.platform.service.ArticleService;
import com.platform.utils.FileUtil;
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
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

	private boolean isExistFile(String filePathName) {
		File genTemp = new File(filePathName);
		return genTemp.exists();
	}
	
	
	/**
	 * 返回栏目列表
	 * @param start 开始记录,limit 显示总数, sortorder 排序方式
	 * @return String 
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public List<Article> getArtilcleList(String magazineId) throws ServiceException{
		List<Article> list = qm.findByNamedQuery("getArticlesByMagazineId",magazineId);
		return list;
	}

	/**
	 * 返回栏目列表
	 * @param start 开始记录,limit 显示总数, sortorder 排序方式
	 * @return String 
	 * @throws ServiceException
	 */
	public String getArticles(String magazineId,String sectionId,int start,int limit,String sortorder) throws ServiceException{
		String sql = "";
		if(sectionId==null||"".endsWith(sectionId)){
			sql = "select m.MAGAZINE_NAME,s.SECTION_NAME,a.* from magazine m,section s,article a " +
			" where m.magazine_id = '"+magazineId+"' " +
			" and m.magazine_id = s.magazine_id " +
			" and s.section_id = a.section_id "+
			" order by s.seq "+sortorder+" ,a.seq "+sortorder+"  limit "+start+","+limit; 
		}else{
			sql = "select m.MAGAZINE_NAME,s.SECTION_NAME,a.* from magazine m,section s,article a " +
			" where s.section_id ='" + sectionId+"'"+
			" and m.magazine_id = s.magazine_id " +
			" and s.section_id = a.section_id "+
			" order by s.seq "+sortorder+" ,a.seq "+sortorder+"  limit "+start+","+limit; 
		}
		int page = limit==0?1:(start/limit+1);
		RowSet rs = jqm.getRowSet(sql);
		JSONArray jarray = new JSONArray();
		JSONObject obj = new JSONObject();
		int size = getArtilcleList(magazineId).size();
		try {
			obj.put("total", size);
			obj.put("page", page);
			try {
				while (rs.next()) {
					JSONObject object = new JSONObject();
					object.put("articleId",rs.getString("Article_ID"));
					object.put("articleName",rs.getString("Article_NAME"));
					object.put("articleDesc",rs.getString("Article_DESC"));
					object.put("sectionId",rs.getString("SECTION_ID"));
					object.put("sectionName",rs.getString("SECTION_NAME"));
					object.put("magazineName",rs.getString("MAGAZINE_NAME"));
					object.put("seq",rs.getInt("seq"));
					object.put("articlePicture", rs.getString("Article_Picture"));
					object.put("commentState",rs.getInt("COMMENT_STATE")==1?"是":"否");
					object.put("author",rs.getString("author"));
					object.put("articleTop",rs.getInt("Article_Top")==1?"是":"否");
					object.put("articleRecommend",rs.getInt("Article_Recommend")==1?"是":"否");
					object.put("shareLink",rs.getString("share_link"));
					jarray.put(object);
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			obj.put("rows", jarray);
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		return obj.toString();
	}
	
	/**
	 * 返回文章列表
	 * @param start 开始记录,limit 显示总数, sortorder 排序方式
	 * @return String 
	 * @throws ServiceException
	 * 
	 * NEWS_RECOMMEND_URL   		http://localhost:8080/iServer/article/iphone?isRecommend=1&magazineId=#
	 * NEWS_RECOMMEND_NEXT_URL    	http://localhost:8080/iServer/article/iphone?isRecommend=1&magazineId=#&newsId=#
	 * NEWS_KIND_URL   				http://localhost:8080/iServer/section/iphone?magazineId=#&rp=100
	 * NEWS_KIND_DETAIL_URL   		http://localhost:8080/iServer/article/iphone?cid=#
	 * NEWS_KIND_DETAIL_NEXT_URL   	http://localhost:8080/iServer/article/iphone?cid=#&newsId=#
	 */
	public String getIphoneArticles(String magazineId,String sectionId,String articleId,int start,int limit,String sortorder, String isRecommend, String  isIpad, String magazineClass) throws ServiceException{
		StringBuffer sb = new StringBuffer();
		String sql = "";
		if(sectionId!=null && !"".endsWith(sectionId)){  //NEWS_KIND_DETAIL_URL & NEWS_KIND_DETAIL_NEXT_URL
			sql = "select m.MAGAZINE_NAME,s.SECTION_NAME,a.* from magazine m,section s,article a " +
					" where s.section_id ='" + sectionId+"'"+
					" and m.magazine_id = s.magazine_id " +
					" and s.section_id = a.section_id ";
			if(articleId!=null && !"".endsWith(articleId)) {
				sql = sql + " and a.article_id <"+articleId;  //NEWS_KIND_DETAIL_NEXT_URL
			}
			sql = sql + " order by a.article_id desc limit "+start+","+limit; 
		}
		else if(magazineId!=null && !"".endsWith(magazineId)){
			sql = "select m.MAGAZINE_NAME,s.SECTION_NAME,a.* from magazine m,section s,article a " +
					" where m.magazine_id = '"+magazineId+"' " +
					" and m.magazine_id = s.magazine_id " +
					" and s.section_id = a.section_id ";
			if(isRecommend != null && isRecommend.equals("1")) {  //NEWS_RECOMMEND_URL
				sql = sql + " and a.article_recommend=1 and a.article_top != 1 ";
			}
			if(articleId!=null&&!"".endsWith(articleId)) {
				sql = sql + " and a.article_id <"+articleId;
			}
			sql = sql + " order by a.article_id desc limit "+start+","+limit; 
		}
		
		sb.append("<rss version=\"2.0\">\n");
		sb.append("<channel>\n");
		RowSet rs = jqm.getRowSet(sql);
		String pic = null;
		
		try {
			while (rs.next()) {
				if(rs.isFirst()){
					sb.append("<title>文章列表</title>\n");
					sb.append("<link>http://xkzz.chinaxiaokang.com</link>\n");
					sb.append("<description>"+rs.getString("SECTION_NAME")+"</description>\n");
					sb.append("<lastBuildDate>"+DateTime.getCurrentDatetimeByString()+"</lastBuildDate>\n");
					sb.append("<language>cn</language>\n");
				}
				pic = rs.getString("article_picture");
				
				if(pic==null || "".equals(pic) || pic.trim().indexOf(".") == -1) {
					//pic = "";
					//pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/default.jpg";
					pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+magazineClass+"_validimg.jpg";
				} else {
					String pic2 = "";
					if(isIpad != null && isIpad.equals("0")) { //iphone
						pic2 = "s_"+pic;
					} else if(isIpad != null && isIpad.equals("1")) { //ipad
						pic2 = "b_"+pic;
					} else {
						pic2 = "b_"+pic;
					}
					
					if(FileUtil.fileExist(GlobalVariables.uri+GlobalVariables.fileLocation+"/"+pic2)) {
						pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+pic2;
					} else if(FileUtil.fileExist(GlobalVariables.uri+GlobalVariables.fileLocation+"/"+pic)) {
						pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+pic;
					} else {
						//pic="";
						//pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/default.jpg";
						pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+magazineClass+"_validimg.jpg";
					}
				}
				
				String pubDate = "";
				if(rs.getString("CREATE_DATE") != null && !rs.getString("CREATE_DATE").equals("") && rs.getString("CREATE_DATE").length() > 10) {
					pubDate = rs.getString("CREATE_DATE").substring(0,10);
				} else if (rs.getString("UPDATE_DATE") != null && !rs.getString("UPDATE_DATE").equals("") && rs.getString("UPDATE_DATE").length() > 10) {
					pubDate = rs.getString("UPDATE_DATE").substring(0,10);
				}
				
				sb.append("<item>\n");
				sb.append("<news_id><![CDATA[");sb.append(rs.getString("Article_ID"));sb.append("]]></news_id>\n");
				sb.append("<title><![CDATA[");sb.append(rs.getString("Article_NAME"));sb.append("]]></title>\n");
				sb.append("<news_pic><![CDATA[");sb.append(pic);sb.append("]]></news_pic>\n");
				//news_link为详细的URL+id。http://192.168.1.100:8080/iServer/article/name/iphone?nid=ab818189330c0c6a01330c0e635e0001
				//具体的时候IP变更
				sb.append("<news_link>");
				//sb.append(GlobalVariables.urlLocation+GlobalVariables.serverName+"article/name/iphone?nid=");sb.append(rs.getString("Article_ID"));
				/******
				sb.append(GlobalVariables.urlLocation+GlobalVariables.serverName+"article/name/iphone?type=");
				sb.append(magazineClass);sb.append("&amp;isIpad=");sb.append(isIpad);sb.append("&amp;nid=");sb.append(rs.getString("Article_ID"));
				 */
				//20120626BUG暂时解决之法
				sb.append(GlobalVariables.urlLocation+GlobalVariables.serverName+"article/name/iphone?mixparam=");
				sb.append(magazineClass);sb.append("|");sb.append(isIpad);sb.append("|");sb.append(rs.getString("Article_ID"));
				sb.append("</news_link>\n");
				
				sb.append("<pubDate>");sb.append(pubDate);sb.append("</pubDate>\n");
				sb.append("<description><![CDATA[ ");
				sb.append(rs.getString("ARTICLE_CONTENT"));
				sb.append("]]></description>\n");
				sb.append("</item>\n");
			}
			sb.append("</channel>\n");
			sb.append("</rss>\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * 返回文章内容
	 * @param articleId 文章ID
	 * @return Article 
	 * @throws ServiceException
	 */
	public Article getArticle(String articleId) throws ServiceException{
		List<Article> list = qm.findByNamedQuery("getArticleById", articleId);
		Article article = null;
		if(list != null && list.size()>0) {
			article = list.get(0);
		}
		return article;
	}
	
	/**
	 * @param articleId 文章ID
	 * @return Article 
	 * @throws ServiceException
	 */
	public String getTopArticle(String sectionId, String  isIpad, String magazineClass) throws ServiceException{
		List<Article> list = qm.findByNamedQuery("getTopArticleBySectionId", sectionId);
		
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		sb.append("<rss version=\"2.0\">\n");

		if(list != null && list.size()>0) {
			Article article = list.get(0);
			String pic = article.getArticlePicture();
			if(pic==null || "".equals(pic) || pic.trim().indexOf(".") == -1) {
				//置顶的必须要有图片
				//pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/default.jpg";
				pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+magazineClass+"_validimg.jpg";
			} else {
				String pic2 = "";
				if(isIpad != null && isIpad.equals("0")) { //iphone
					pic2 = "s_"+pic;
				} else if(isIpad != null && isIpad.equals("1")) { //ipad
					pic2 = "b_"+pic;
				} else {
					pic2 = "b_"+pic;
				}
				
				if(FileUtil.fileExist(GlobalVariables.uri+GlobalVariables.fileLocation+"/"+pic2)) {
					pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+pic2;
				} else if(FileUtil.fileExist(GlobalVariables.uri+GlobalVariables.fileLocation+"/"+pic)) {
					pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+article.getArticlePicture();
				} else {
					//置顶的必须要有图片
					//pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/default.jpg";
					pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+magazineClass+"_validimg.jpg";
				}
			}
			
			sb.append("<item>\n");
			sb.append("<news_id>");sb.append(article.getArticleId());sb.append("</news_id>\n");
			sb.append("<news_title><![CDATA[");sb.append(article.getArticleName());sb.append("]]></news_title>\n");
			sb.append("<news_pic>");sb.append(pic);sb.append("</news_pic>\n");
			sb.append("<news_date>");sb.append(article.getUpdateDate());sb.append("</news_date>\n");
			sb.append("<news_desc>");sb.append(article.getArticleDesc());sb.append("</news_desc>\n");
			sb.append("<news_content><![CDATA[");
			sb.append(article.getArticleContent());
			sb.append("]]></news_content>\n");
			sb.append("</item>\n");
		} else {
			//如果list为空，则取sectionId下的最新文章一篇
			String sql = "select t.* from article t where t.section_id='"+sectionId+"' order by article_id asc limit 1";
			RowSet rs = jqm.getRowSet(sql);
			String pic = null;
			try {
				while (rs.next()) {
					pic = rs.getString("article_picture");
					if(pic==null || "".equals(pic)  || pic.trim().indexOf(".") == -1) {
						//置顶的必须要有图片
						//pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/default.jpg";
						pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+magazineClass+"_validimg.jpg";
					} else {
						String pic2 = "";
						if(isIpad != null && isIpad.equals("0")) { //iphone
							pic2 = "s_"+pic;
						} else if(isIpad != null && isIpad.equals("1")) { //ipad
							pic2 = "b_"+pic;
						} else {
							pic2 = "b_"+pic;
						}
						
						if(FileUtil.fileExist(GlobalVariables.uri+GlobalVariables.fileLocation+"/"+pic2)) {
							pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+pic2;
						} else if(FileUtil.fileExist(GlobalVariables.uri+GlobalVariables.fileLocation+"/"+pic)) {
							pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+pic;
						} else {
							//pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+"default.jpg";
							pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+magazineClass+"_validimg.jpg";
						}
					}
					
					sb.append("<item>\n");
					sb.append("<news_id>");sb.append(rs.getString("article_id"));sb.append("</news_id>\n");
					sb.append("<news_title><![CDATA[");sb.append(rs.getString("article_name"));sb.append("]]></news_title>\n");
					sb.append("<news_pic>");sb.append(pic);sb.append("</news_pic>\n");
					sb.append("<news_date>");sb.append(rs.getString("update_date"));sb.append("</news_date>\n");
					sb.append("<news_desc>");sb.append(rs.getString("article_desc"));sb.append("</news_desc>\n");
					sb.append("<news_content><![CDATA[");
					sb.append(rs.getString("article_content"));
					sb.append("]]></news_content>\n");
					sb.append("</item>\n");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		sb.append("</rss>\n");
		return sb.toString();
		
	}
	
	public String getRecommendTopArticle(String magazineId, String  isIpad, String magazineClass) throws ServiceException{
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		sb.append("<rss version=\"2.0\">\n");

		String sql = "select m.MAGAZINE_NAME, s.SECTION_NAME,a.*  " +
				" from magazine m,section s,article a " +
				" where m.magazine_id = s.magazine_id " +
				" and s.section_id = a.section_id " +
				" and a.article_recommend=1 and a.article_top = 1 " +
				" and m.magazine_id = '" + magazineId + "' "+
				" order by a.article_id DESC limit 1";
		
		RowSet rs = jqm.getRowSet(sql);
		String pic = null;
		try {
			while (rs.next()) {
				pic = rs.getString("article_picture");
				
				if(pic==null || "".equals(pic)  || pic.trim().indexOf(".") == -1) {
					//置顶的必须要有图片
					//pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+"default.jpg";
					pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+magazineClass+"_validimg.jpg";
				} else {
					String pic2 = "";
					if(isIpad != null && isIpad.equals("0")) { //iphone
						pic2 = "s_"+pic;
					} else if(isIpad != null && isIpad.equals("1")) { //ipad
						pic2 = "b_"+pic;
					} else {
						pic2 = "b_"+pic;
					}
					
					if(FileUtil.fileExist(GlobalVariables.uri+GlobalVariables.fileLocation+"/"+pic2)) {
						pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+pic2;
					} else if(FileUtil.fileExist(GlobalVariables.uri+GlobalVariables.fileLocation+"/"+pic)) {
						pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+pic;
					} else {
						//置顶的必须要有图片
						//pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+"default.jpg";
						pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+magazineClass+"_validimg.jpg";
					}
				}
				
				sb.append("<item>\n");
				sb.append("<news_id>");sb.append(rs.getString("article_id"));sb.append("</news_id>\n");
				sb.append("<news_title><![CDATA[");sb.append(rs.getString("article_name"));sb.append("]]></news_title>\n");
				sb.append("<news_pic>");sb.append(pic);sb.append("</news_pic>\n");
				sb.append("<news_date>");sb.append(rs.getString("update_date"));sb.append("</news_date>\n");
				sb.append("<news_desc>");sb.append(rs.getString("article_desc"));sb.append("</news_desc>\n");
				sb.append("<news_content><![CDATA[");
				sb.append(rs.getString("article_content"));
				sb.append("]]></news_content>\n");
				sb.append("</item>\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sb.append("</rss>\n");
		return sb.toString();
	}
	
	
	/**
	 * 返回文章内容
	 * @param articleId文章ID
	 * @return Xml 
	 * @throws ServiceException
	 */
	public String getIphoneArticle(String articleId, String  isIpad, String magazineClass) throws ServiceException{
		List<Article> list = qm.findByNamedQuery("getArticleContentById", articleId);		
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		sb.append("<rss version=\"2.0\">\n");
		if(list.size()>0){
			sb.append("<item>\n");
			Article article = list.get(0);
			String pic = article.getArticlePicture();
			if(pic==null || "".equals(pic)  || pic.trim().indexOf(".") == -1) {
				//pic = "";
				//pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/default.jpg";
				pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+magazineClass+"_validimg.jpg";
			} else {
				String pic2 = "";
				if(isIpad != null && isIpad.equals("0")) { //iphone
					pic2 = "s_"+pic;
				} else if(isIpad != null && isIpad.equals("1")) { //ipad
					pic2 = "b_"+pic;
				} else {
					pic2 = "b_"+pic;
				}
				
				if(FileUtil.fileExist(GlobalVariables.uri+GlobalVariables.fileLocation+"/"+pic2)) {
					pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+pic2;
				} else if(FileUtil.fileExist(GlobalVariables.uri+GlobalVariables.fileLocation+"/"+pic)) {
					pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+pic;
				} else {
					//pic = "";
					//pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/default.jpg";
					pic = GlobalVariables.urlLocation+GlobalVariables.serverName+"static"+GlobalVariables.fileLocation+"/"+magazineClass+"_validimg.jpg";
				}
			}
			
			String date = "";
			if(article.getCreateDate() != null && !article.getCreateDate().equals("") && article.getCreateDate().length() > 10) {
				date = article.getCreateDate().substring(0,10);
			} else if (article.getUpdateDate() != null && !article.getUpdateDate().equals("") && article.getUpdateDate().length() > 10) {
				date = article.getUpdateDate().substring(0,10);
			}
			
			sb.append("<news_id>");sb.append(article.getArticleId());sb.append("</news_id>\n");
			sb.append("<title><![CDATA[");sb.append(article.getArticleName());sb.append("]]></title>\n");
			sb.append("<news_pic>");sb.append(pic);sb.append("</news_pic>\n");
			//sb.append("<news_link>");sb.append("");sb.append("</news_link>\n");
			//news_link为详细的URL+id。http://192.168.1.100:8080/iServer/article/name/iphone?nid=ab818189330c0c6a01330c0e635e0001
			//具体的时候IP变更
			sb.append("<news_link>");
			/**
			sb.append(GlobalVariables.urlLocation+GlobalVariables.serverName+"article/name/iphone?type=");
			sb.append(magazineClass);sb.append("&amp;isIpad=");sb.append(isIpad);sb.append("&amp;nid=");sb.append(article.getArticleId());
			 */
			//20120626BUG暂时解决之法
			sb.append(GlobalVariables.urlLocation+GlobalVariables.serverName+"article/name/iphone?mixparam=");
			sb.append(magazineClass);sb.append("|");sb.append(isIpad);sb.append("|");sb.append(article.getArticleId());
			sb.append("</news_link>\n");
			
			sb.append("<news_date>");sb.append(date);sb.append("</news_date>\n");
			sb.append("<news_source>");sb.append(article.getSection().getSectionName());sb.append("</news_source>\n");
			sb.append("<share_link>");sb.append(article.getShareLink());sb.append("</share_link>\n");
			
			sb.append("<news_mag>");sb.append(article.getSection().getMagazine().getMagazineName());sb.append("</news_mag>\n");
			sb.append("<news_author>");sb.append(article.getAuthor());sb.append("</news_author>\n");

			sb.append("<news_content><![CDATA[");
			sb.append(article.getArticleContent());
			sb.append("]]></news_content>\n");
			sb.append("</item>\n");
		}
		sb.append("</rss>\n");
		return sb.toString();
	}
	
	public String getMaxArticleId(){
		String sql = "";
		sql = "select  max(cast(article_id as DECIMAL)) from article";
		String maxId ="";
		RowSet rs = jqm.getRowSet(sql);
		try {
			if(rs.next()&&rs.getString(1)!=null) {
				maxId = rs.getString(1);
			}
			else maxId = "1";
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return maxId;
	}
	/**
	 * 保存文章
	 * @param section 栏目内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public boolean saveArticle(Article article) throws ServiceException{
		long maxId = Long.parseLong(getMaxArticleId())+1;
		article.setArticleId(maxId+"");
		pm.save(article);
		return true;
	}
	/**
	 * 修改文章
	 * @param section 栏目内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public boolean updateArticle(Article article) throws ServiceException{
		pm.update(article);
		return true;
	}
	/**
	 * 删除文章
	 * @param magazine 杂志内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public boolean removeArticle(Article article) throws ServiceException{
		pm.remove(article);
		return true;
	}
	/**
	 * 删除文章
	 * @param magazine 杂志内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public boolean removeArticle(Article article,String id) throws ServiceException{
		pm.remove(article.getClass(),id);
		return true;
	}
	
	public boolean removeArticles(String sectionId) throws ServiceException{
		String sql = "";
		sql = "delete from article where section_id = '"+sectionId +"'";
		jpm.executeSql(sql);
		return true;
	}
}
