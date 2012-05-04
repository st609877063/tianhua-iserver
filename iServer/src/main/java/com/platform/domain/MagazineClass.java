package com.platform.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
@NamedQueries({
	@NamedQuery(name="getMagazineClass",query="select t from MagazineClass t"),
	@NamedQuery(name="getMagazineClassById",query="select t from MagazineClass t where t.magazineClassId=?")
	})
@Entity
@Table(name="magazine_class",schema="ipdb",uniqueConstraints={@UniqueConstraint(columnNames={"magazineClassId"})})
public class MagazineClass implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="increment")
	private String magazineClassId;
	
	@Column(updatable = true, nullable = false, length = 255)  
	private String magazineClassName;

	public String getMagazineClassId() {
		return magazineClassId;
	}

	public void setMagazineClassId(String magazineClassId) {
		this.magazineClassId = magazineClassId;
	}

	public String getMagazineClassName() {
		return magazineClassName;
	}

	public void setMagazineClassName(String magazineClassName) {
		this.magazineClassName = magazineClassName;
	}
	
   
}
