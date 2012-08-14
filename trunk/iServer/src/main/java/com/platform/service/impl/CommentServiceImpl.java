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
import com.framework.util.DateTime;
import com.platform.domain.Comment;
import com.platform.domain.Section;
import com.platform.service.CommentService;
@Service("commentService")
public class CommentServiceImpl implements CommentService {
	private PersistenceManager pm;
	private QueryManager qm;
	@SuppressWarnings("unused")
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
	 * 返回评论列表
	 * @param reviewState 审核状态
	 * @return List 
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Comment> getCommentsList(int reviewState) throws ServiceException{
		List<Comment> list = qm.findByNamedQuery("getComments");
		return list;
	}
	
	/**
	 * 返回评论列表
	 * @param reviewState 审核状态
	 * @return String 
	 * @throws ServiceException
	 */
	public String getComments(int reviewState,int start,int limit,String sortorder) throws ServiceException{
		String sql = "select c.comment_id,c.content,c.create_date,c.review_state," +
				"a.article_id,a.article_name,a.article_Desc," +
				"s.section_Name,m.magazine_id,m.magazine_Name,a.seq,a.article_Picture,a.comment_State,a.author," +
				"u.name" +
		" from comment c,Users u,magazine m,section s,Article a " +
		" where c.review_state <= "+reviewState +
		" and c.article_id = a.article_id " +
		" and m.magazine_id = s.magazine_id " +
		" and s.section_id = a.section_id "+
		" and c.user_id = u.user_id " +
		" order by c.create_date "+sortorder+" limit "+start+","+limit; 
		int page = limit==0?1:(start/limit+1);
		RowSet rs = jqm.getRowSet(sql);
		JSONArray jarray = new JSONArray();
		JSONObject obj = new JSONObject();
		int size = getCommentsList(reviewState).size();
		try {
			obj.put("total", size);
			obj.put("page", page);
			try {
				while (rs.next()) {
					JSONObject object = new JSONObject();
					object.put("commentId",rs.getString("comment_id"));
					object.put("content",rs.getString("content"));
					object.put("createDate",rs.getString("create_date"));
					object.put("reviewState",rs.getInt("review_state")==1?"已审核":"未审核");
					object.put("userName",rs.getString("name"));
					object.put("articleId",rs.getString("article_id"));
					object.put("articleName",rs.getString("article_name"));
					object.put("articleDesc",rs.getString("Article_DESC"));
					object.put("sectionName",rs.getString("SECTION_NAME"));
					object.put("magazineId",rs.getString("MAGAZINE_id"));
					object.put("magazineName",rs.getString("MAGAZINE_NAME"));
					object.put("seq",rs.getInt("seq"));
					object.put("articlePicture", rs.getString("Article_Picture"));
					object.put("commentState",rs.getInt("COMMENT_STATE")==0?"否":"是");
					object.put("author",rs.getString("author"));
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
	 * 返回评论列表
	 * @param reviewState 审核状态
	 * @return String 
	 * @throws ServiceException
	 */
	public String getIphoneComments(String nid,int reviewState,int start,int limit,String sortorder) throws ServiceException{
		StringBuffer sb = new StringBuffer();
		String sql = "select u.name,c.comment_id,c.content,c.create_date,c.review_state,a.article_id,a.article_name,u.name" +
		" from comment c,Users u,Article a " +
		" where c.review_state >= "+reviewState +
		" and c.article_id = a.article_id " +
		" and c.user_id = u.user_id " +
		" and c.article_id =  '" + nid +
		"' order by c.create_date "+sortorder+" limit "+start+","+limit; 
		RowSet rs = jqm.getRowSet(sql);
		try {
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
			sb.append("<root>\n");
			while (rs.next()) {
				/*
				sb.append("<data>\n");
				sb.append("<tid>");sb.append(rs.getString("Article_ID"));sb.append("</tid>\n");
				sb.append("<commentUser>");sb.append(rs.getString("NAME"));sb.append("</commentUser>\n");
				sb.append("<content><![CDATA[ ");sb.append(rs.getString("content"));sb.append("]]></content>\n");
				sb.append("<commentDate>");sb.append(rs.getString("create_date"));sb.append("</commentDate>\n");
				sb.append("</data>\n");
				*/
				
				String name = "匿名用户";
				if(rs.getString("name") != null && !rs.getString("name").equals("")) {
					name = rs.getString("name");
				}
				sb.append("<data>\n");
				sb.append("<tid>");sb.append(rs.getString("comment_id"));sb.append("</tid>\n");
				sb.append("<subject><![CDATA[来自：");sb.append(name);sb.append("]]></subject>\n");
				sb.append("<content><![CDATA[ ");sb.append(rs.getString("content"));sb.append("]]></content>\n");
				sb.append("<replied>");sb.append(rs.getString("create_date"));sb.append("</replied>\n");
				sb.append("</data>\n");
			}
			sb.append("</root>\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return sb.toString();
	}
	/**
	 * 返回评论内容
	 * @param commentId 评论ID
	 * @return Comment 
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Comment getComment(String commentId) throws ServiceException{
		List<Comment> list = qm.findByNamedQuery("getCommentById", commentId);
		Comment comment = null;
		if(list != null && list.size()>0) {
			comment = list.get(0);
		}
		return comment;
	}
	public String getIphoneComment(String commentId) throws ServiceException{
		List<Comment> list = qm.findByNamedQuery("getCommentById", commentId);
		Comment comment = null;
		if(list != null && list.size()>0) {
			comment = list.get(0);
		}
		StringBuffer sb = new StringBuffer();
		
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><root>");
		if(comment!=null){
				sb.append("<data>" +
				"<pid>"+comment.getArticle().getArticleId()+"</pid>" +
				"<tid>"+comment.getCommentId()+"</tid>" +
				"<first>1</first>" + //第一次留言。界面样式改变标记
				"<subject><![CDATA[评论详细]]></subject>" +
				"<useip><![CDATA[来自："+comment.getUser().getName()+"]]></useip>" +
				"<dateline>"+comment.getCreateDate()+"</dateline> " +
				"<commentUser>"+comment.getUser().getName()+"</commentUser>" +
				"<content><![CDATA["+comment.getContent()+"]]></content> 	" +
				"</data>");
		}
		
		sb.append("</root>\n");
		return sb.toString();
	}
	/**
	 * 保存评论
	 * @param comment 评论内容
	 * @return String 
	 * @throws ServiceException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String getMaxCommentId(){
		String sql = "";
		sql = "select  max(cast(comment_id as DECIMAL)) from comment ";
		String maxId ="";
		RowSet rs = jqm.getRowSet(sql);
		try {
			if(rs.next()&&rs.getString(1)!=null) {
				maxId = rs.getString(1);
			}
			else maxId = "1";
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxId;
	}
	/**
	 * 保存评论
	 * @param comment 评论内容
	 * @return String 
	 * @throws ServiceException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveComment(Comment comment) throws ServiceException{
		long maxId = Long.parseLong(getMaxCommentId())+1;
		comment.setCommentId(maxId+"");
		comment.setCreateDate(DateTime.getCurrentDateByString());
		pm.save(comment);
		return comment.getCommentId()+"";
	}
	/**
	 * 删除评论
	 * @param comment 评论内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public boolean removeComment(Comment comment,String id) throws ServiceException{
		pm.remove(comment.getClass(),id);
		return true;	
	}
	/**
	 * 审核评论
	 * @param comment 评论内容
	 * @return boolean 
	 * @throws ServiceException
	 */
	public boolean reviewComment(Comment comment) throws ServiceException{
		pm.update(comment);
		return true;
	}
}
