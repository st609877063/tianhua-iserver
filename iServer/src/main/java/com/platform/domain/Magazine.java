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
	@NamedQuery(name="getMagazines",query="select t from Magazine t"),
	@NamedQuery(name="getMagazineById",query="select t from Magazine t where t.magazineId=?"),
	@NamedQuery(name="getMagazineByClass",query="select t from Magazine t where t.magazineClass.magazineClassId=?")
	})
@Entity
@Table(name="magazine",schema="ipdb",uniqueConstraints={@UniqueConstraint(columnNames={"magazineId"})})
public class Magazine implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    //@GeneratedValue(generator="system-uuid")
    //@GenericGenerator(name="system-uuid",strategy="increment")
	private String magazineId;
	
	@Column(updatable = true, nullable = false, length = 255)  
	private String magazinePicture;
	
	@ManyToOne(targetEntity=com.platform.domain.MagazineClass.class,fetch=FetchType.EAGER)
    @JoinColumn(name="magazine_class_id") 
	private MagazineClass magazineClass;
	
	@Column(updatable = true, nullable = false, length = 255)  
	private String magazineName;
	
	
    @ManyToOne(targetEntity=com.platform.domain.User.class,fetch=FetchType.EAGER)
    @JoinColumn(name="user_id") 
	private User user;
	
	@Column(updatable = true)  
	private int downloadCounter=0;
	
	@Column(updatable = true)  
	private int viewCounter=0;
	
	@Column(updatable = false)  
	private String createDate;
	
	@Column(updatable = true)  
	private String updateDate;
	
	@Column(updatable = true,nullable = false)  
	private String phase;//文章期数1到12
	
	@Column(updatable = false)  
	private int cost = 0;//收费接口
	
	public String getMagazineId() {
		return magazineId;
	}
	public void setMagazineId(String magazineId) {
		this.magazineId = magazineId;
	}
	public String getMagazineName() {
		return magazineName;
	}
	public void setMagazineName(String magazineName) {
		this.magazineName = magazineName;
	}
	public int getDownloadCounter() {
		return downloadCounter;
	}
	public void setDownloadCounter(int downloadCounter) {
		this.downloadCounter = downloadCounter;
	}
	public int getViewCounter() {
		return viewCounter;
	}
	public void setViewCounter(int viewCounter) {
		this.viewCounter = viewCounter;
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

	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}

	public User getUser() {
		return user;
	}
    
	public void setUser(User user) {
		this.user = user;
	}
	public String getMagazinePicture() {
		return magazinePicture;
	}
	public void setMagazinePicture(String magazinePicture) {
		this.magazinePicture = magazinePicture;
	}
	public MagazineClass getMagazineClass() {
		return magazineClass;
	}
	public void setMagazineClass(MagazineClass magazineClass) {
		this.magazineClass = magazineClass;
	}

}
