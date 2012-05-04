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

import org.hibernate.annotations.GenericGenerator;
@NamedQueries({
	@NamedQuery(name="getSections",query="select t from Section t"),
	@NamedQuery(name="getSectionById",query="select t from Section t where t.sectionId=?"),
	@NamedQuery(name="getSectionsByMagazineId",query="select t from Section t where t.magazine.magazineId=?")
	})
@Entity
@Table(name="section",schema="ipdb")
public class Section implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    //@GeneratedValue(generator="system-uuid")
    //@GenericGenerator(name="system-uuid",strategy="increment")
	private String sectionId;
	
	@ManyToOne(targetEntity=com.platform.domain.Magazine.class,fetch=FetchType.EAGER)
    @JoinColumn(name="magazine_id") 
	private Magazine magazine;
	
	@Column(updatable = true, nullable = false, length = 255)  
	private String sectionName;
	
	@Column(updatable = true)  
	private String sectionType;
	
	@Column(updatable = true)  
	private int hide=1;//1:显示,0:隐藏

	@Column(updatable = true)  
	private int seq;
	
	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getSectionType() {
		return sectionType;
	}

	public void setSectionType(String sectionType) {
		this.sectionType = sectionType;
	}

	public int getHide() {
		return hide;
	}

	public void setHide(int hide) {
		this.hide = hide;
	}

	public Magazine getMagazine() {
		return magazine;
	}

	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}
	
}
