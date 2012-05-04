package com.platform.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@NamedQueries({
	@NamedQuery(name="getLogs",query="select t from Logs t"),
	@NamedQuery(name="getLogById",query="select t from Logs t where t.logId=?")
	})
@Entity
@Table(name="logs",schema="ipdb")
public class Logs implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="increment")
	private String logId;
	
	@Column(updatable = true, nullable = true, length = 50)  
	private String type;
	
	@Column(updatable = true, nullable = true, length = 50)  
	private String name;
	
	@Column(updatable = true, nullable = false, length = 255)  
	private String message;
	
	@Column(updatable = true, nullable = false, length = 50)  
	private String username;
	
	@Column(updatable = true, nullable = false, length = 50)  
	private String createDate;
	
	private String uri;



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
