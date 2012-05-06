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
	@NamedQuery(name="getArticles",query="select t from Article t"),
	@NamedQuery(name="getArticlesBySectionId",query="select t from Article t where t.section.sectionId=?"),
	@NamedQuery(name="getTopArticleBySectionId",query="select t from Article t where t.section.sectionId=? and t.articleTop=1 order by t.seq asc"),
	@NamedQuery(name="getArticlesByMagazineId",query="select t from Article t where t.section.magazine.magazineId=?"),
	@NamedQuery(name="getArticleById",query="select t from Article t where t.articleId=?"),
	@NamedQuery(name="getArticleContentById",query="select t from Section s,Article t where s.sectionId = t.section.sectionId and t.articleId=?")
	})
@Entity
@Table(name="article",schema="ipdb",uniqueConstraints={@UniqueConstraint(columnNames={"articleId"})})
public class Article implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    //@GeneratedValue(generator="system-uuid")
    //@GenericGenerator(name="system-uuid",strategy="increment")
	private String articleId;
	
	@Column(updatable = true, nullable = false)  
	private String articleName;
	
	@ManyToOne(targetEntity=com.platform.domain.Section.class,fetch=FetchType.EAGER)
    @JoinColumn(name="section_id") 
	private Section section;
	
	@Column(updatable = true, nullable = true)  
	private String articleDesc;
	
	@Column(updatable = true, nullable = true, length = 255)  
	private String articlePicture;
	
	@Column(updatable = true, nullable = false,columnDefinition="TEXT")  
	private String articleContent;
	
	@Column(updatable = true)  
	private int articleRecommend;//1:是,0:否
	
	@Column(updatable = true)  
	private int seq;
	
	@Column(updatable = true)  
	private int commentState;//1:允许评论,0:禁止评论

	@Column(updatable = true)  
	private String createDate;
	
	@Column(updatable = true)  
	private String updateDate;
	
	@Column(updatable = true)  
	private int articleTop;
	
	@Column(updatable = true)  
	private String author;
	
	@Column(updatable = true)  
	private String shareLink;
	
	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getCommentState() {
		return commentState;
	}

	public void setCommentState(int commentState) {
		this.commentState = commentState;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
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

	public String getArticlePicture() {
		return articlePicture;
	}

	public void setArticlePicture(String articlePicture) {
		this.articlePicture = articlePicture;
	}

	public String getArticleDesc() {
		return articleDesc;
	}

	public void setArticleDesc(String articleDesc) {
		this.articleDesc = articleDesc;
	}

	public int getArticleTop() {
		return articleTop;
	}

	public void setArticleTop(int articleTop) {
		this.articleTop = articleTop;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getArticleRecommend() {
		return articleRecommend;
	}

	public void setArticleRecommend(int articleRecommend) {
		this.articleRecommend = articleRecommend;
	}

	public String getShareLink() {
		return shareLink;
	}

	public void setShareLink(String shareLink) {
		this.shareLink = shareLink;
	}
   
}
