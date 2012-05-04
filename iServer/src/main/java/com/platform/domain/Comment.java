package com.platform.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
@NamedQueries({
	@NamedQuery(name="getComments",query="select t from Comment t"),
	@NamedQuery(name="getCommentsByReviewState",query="select t from Comment t where t.reviewState=?"),
	@NamedQuery(name="getCommentsByArticleId",query="select t from Comment t where t.article.articleId=?"),
	@NamedQuery(name="getCommentById",query="select t from Comment t where t.commentId=?")
	})
@Entity
@Table(name="comment",schema="ipdb",uniqueConstraints={@UniqueConstraint(columnNames={"commentId"})})
public class Comment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    //@GeneratedValue(generator="system-uuid")
    //@GenericGenerator(name="system-uuid",strategy="increment")
	private String commentId;
	
	@ManyToOne(targetEntity=com.platform.domain.Article.class,fetch=FetchType.EAGER)
	@JoinColumn(name="article_id") 
	private Article article;
	
	@ManyToOne(targetEntity=com.platform.domain.User.class,fetch=FetchType.EAGER)
	@JoinColumn(name="user_id") 
	private User user;
	
	@Column(updatable = true, nullable = false,columnDefinition="TEXT")  
	private String content;
	
	@Column(updatable = true)  
	private int reviewState =0;//0未审核，1已审核
	
	@Column(updatable = true)  
	private String createDate;
	
	@Column(updatable = true)  
	private String updateDate;
	
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int isReviewState() {
		return reviewState;
	}
	public void setReviewState(int reviewState) {
		this.reviewState = reviewState;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getReviewState() {
		return reviewState;
	}
}
